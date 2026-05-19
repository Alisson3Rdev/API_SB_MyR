package com.My_Rhythmus_Spring.Spring_MR.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Na versão 0.12.x do jjwt a API foi reformulada:
 * - SignatureAlgorithm foi removido dos parâmetros do signWith()
 * - parserBuilder() virou parser()
 * - parseClaimsJws() virou parseSignedClaims()
 * - Keys.hmacShaKeyFor() agora retorna SecretKey em vez de Key
 */
@Component
public class JwtUtil {

    // Chave secreta — em produção coloque numa variável de ambiente
    private static final String SECRET = "minha-chave-super-secreta-32-chars!!";

    // 24 horas em milissegundos
    private static final long EXPIRATION_MS = 86400000;

    // Gera a chave criptográfica a partir da string secreta
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());


    /**
     * Gera um token JWT assinado com o e-mail do usuário.
     * Na 0.12.x o signWith() detecta o algoritmo automaticamente pela chave.
     */
    public String gerarToken(String email) {
        return Jwts.builder()
                .subject(email)                                                      // quem é o dono
                .issuedAt(new Date())                                                // quando foi criado
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))   // quando expira
                .signWith(key)                                                       // assina (algoritmo automático)
                .compact();
    }

    /**
     * Extrai o e-mail (subject) de dentro do token.
     * Na 0.12.x: parser() e parseSignedClaims() substituem os métodos antigos.
     */
    public String extrairEmail(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Retorna true se o token for válido (assinatura correta e não expirado).
     */
    public boolean validarToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    private static final long REFRESH_EXPIRATION_MS = 604800000;
    public String gerarRefreshToken(String email){
        return Jwts.builder()
        .subject(email)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis()+REFRESH_EXPIRATION_MS))
        .signWith(key)
        .compact();
    }
    /**
     * Método auxiliar que faz o parse do token e retorna os Claims (payload).
     * Centralizado aqui para não repetir nos outros métodos.
     */
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)            // informa a chave para verificar a assinatura
                .build()
                .parseSignedClaims(token)   // faz o parse — lança exceção se inválido
                .getPayload();
    }
}