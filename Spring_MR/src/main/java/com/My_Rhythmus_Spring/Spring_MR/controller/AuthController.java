package com.My_Rhythmus_Spring.Spring_MR.controller;

import com.My_Rhythmus_Spring.Spring_MR.model.Usuario;
import com.My_Rhythmus_Spring.Spring_MR.repository.UsuarioRepository;
import com.My_Rhythmus_Spring.Spring_MR.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

/**
 * Controller responsável pela autenticação.
 * Rota pública — qualquer um pode chamar para obter um token JWT.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;

    /**
     * POST /api/auth/login
     * Recebe email e senha, valida e retorna access token + refresh token.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String senha = body.get("senha");

        Usuario usuario = usuarioRepository.findByEmailUser(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (!passwordEncoder.matches(senha, usuario.getSenhaUser())) {
            return ResponseEntity.status(401).body("Senha incorreta.");
        }

        String accessToken = jwtUtil.gerarToken(email);
        String refreshToken = jwtUtil.gerarRefreshToken(email);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return ResponseEntity.ok(tokens);
    }

    /**
     * POST /api/auth/refresh
     * Recebe o refresh token e retorna um novo access token.
     */
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");

        if (!jwtUtil.validarToken(refreshToken)) {
            return ResponseEntity.status(401).body("Refresh token inválido ou expirado.");
        }

        String email = jwtUtil.extrairEmail(refreshToken);
        String novoAccessToken = jwtUtil.gerarToken(email);

        Map<String, String> response = new HashMap<>();
        response.put("accessToken", novoAccessToken);

        return ResponseEntity.ok(response);
    }
}