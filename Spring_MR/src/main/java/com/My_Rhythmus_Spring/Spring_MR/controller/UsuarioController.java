package com.My_Rhythmus_Spring.Spring_MR.controller;

import com.My_Rhythmus_Spring.Spring_MR.dto.UsuarioDTO;
import com.My_Rhythmus_Spring.Spring_MR.dto.UsuarioRequestDTO;
import com.My_Rhythmus_Spring.Spring_MR.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelos endpoints de usuário.
 * Ele é a "porta de entrada" da API — recebe as requisições HTTP,
 * repassa para o Service e devolve a resposta formatada.
 * Não contém lógica de negócio — só orquestra.
 */
@RestController // Combina @Controller + @ResponseBody: todos os métodos retornam JSON automaticamente
@RequestMapping("/api/usuarios") // Prefixo comum a todos os endpoints desta classe
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * POST /api/usuarios/cadastrar
     * Cria um novo usuário. Rota pública (sem necessidade de token).
     * @RequestBody — o Spring deserializa o JSON recebido para UsuarioRequestDTO
     * ResponseEntity.status(201) — retorna HTTP 201 Created, padrão para criação de recurso
     */
    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.status(201).body(usuarioService.cadastrar(dto));
    }

    /**
     * GET /api/usuarios
     * Lista todos os usuários. Rota protegida — precisa de token JWT.
     * ResponseEntity.ok() — retorna HTTP 200 OK com o corpo da resposta
     */
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    /**
     * GET /api/usuarios/{id}
     * Busca um usuário específico pelo ID.
     * @PathVariable — captura o {id} da URL e injeta como parâmetro
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    /**
     * PUT /api/usuarios/{id}
     * Atualiza todos os dados de um usuário.
     * Combina @PathVariable (ID da URL) + @RequestBody (novos dados do corpo)
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id,
                                                @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    /**
     * DELETE /api/usuarios/{id}
     * Remove um usuário pelo ID.
     * ResponseEntity<Void> — retorna HTTP 204 No Content (sem corpo na resposta)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}