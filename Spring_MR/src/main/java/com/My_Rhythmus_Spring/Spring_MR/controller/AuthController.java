package com.My_Rhythmus_Spring.Spring_MR.controller;

import com.My_Rhythmus_Spring.Spring_MR.model.Usuario;
import com.My_Rhythmus_Spring.Spring_MR.repository.UsuarioRepository;
import com.My_Rhythmus_Spring.Spring_MR.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * Recebe email e senha, valida e retorna um token JWT se correto.
     *
     * Map<String, String> — forma simples de receber JSON { "email": "...", "senha": "..." }
     * sem precisar criar um DTO específico para login.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String senha = body.get("senha");

        // Busca o usuário pelo e-mail — lança erro se não existir
        Usuario usuario = usuarioRepository.findByEmailUser(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        // passwordEncoder.matches() compara a senha em texto puro com o hash salvo no banco
        // NUNCA comparamos as strings diretamente — o BCrypt faz isso de forma segura
        if (!passwordEncoder.matches(senha, usuario.getSenhaUser())) {
            return ResponseEntity.status(401).body("Senha incorreta.");
        }

        // Gera o token JWT com o e-mail como identificador
        String token = jwtUtil.gerarToken(email);

        // Retorna o token para o cliente guardar e usar nas próximas requisições
        return ResponseEntity.ok(Map.of("token", token));
    }
}