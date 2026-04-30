package com.My_Rhythmus_Spring.Spring_MR.service;

import com.My_Rhythmus_Spring.Spring_MR.dto.UsuarioDTO;
import com.My_Rhythmus_Spring.Spring_MR.dto.UsuarioRequestDTO;
import com.My_Rhythmus_Spring.Spring_MR.model.Usuario;
import com.My_Rhythmus_Spring.Spring_MR.repository.UsuarioRepository;
import com.My_Rhythmus_Spring.Spring_MR.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDTO cadastrar(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmailUser(dto.getEmailUser())) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNomeUser(dto.getNomeUser());
        usuario.setApelidoUser(dto.getApelidoUser());
        usuario.setEmailUser(dto.getEmailUser());
        usuario.setSenhaUser(passwordEncoder.encode(dto.getSenhaUser()));
        usuario.setNascimentoUser(dto.getNascimentoUser());
        usuario.setPhoneUser(dto.getPhoneUser());

        Usuario salvo = usuarioRepository.save(usuario);
        return toDTO(salvo);
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        String emailLogado = SecurityUtils.getEmailUsuarioLogado();
        if (!usuario.getEmailUser().equals(emailLogado)) {
            throw new RuntimeException("Acesso negado.");
        }

        return toDTO(usuario);
    }

    public UsuarioDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        String emailLogado = SecurityUtils.getEmailUsuarioLogado();
        if (!usuario.getEmailUser().equals(emailLogado)) {
            throw new RuntimeException("Acesso negado.");
        }

        usuario.setNomeUser(dto.getNomeUser());
        usuario.setApelidoUser(dto.getApelidoUser());
        usuario.setEmailUser(dto.getEmailUser());
        usuario.setNascimentoUser(dto.getNascimentoUser());
        usuario.setPhoneUser(dto.getPhoneUser());

        if (dto.getSenhaUser() != null && !dto.getSenhaUser().isBlank()) {
            usuario.setSenhaUser(passwordEncoder.encode(dto.getSenhaUser()));
        }

        return toDTO(usuarioRepository.save(usuario));
    }

    public void deletar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        String emailLogado = SecurityUtils.getEmailUsuarioLogado();
        if (!usuario.getEmailUser().equals(emailLogado)) {
            throw new RuntimeException("Acesso negado.");
        }

        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO toDTO(Usuario u) {
        return new UsuarioDTO(
                u.getIdUser(),
                u.getNomeUser(),
                u.getApelidoUser(),
                u.getEmailUser(),
                u.getNascimentoUser(),
                u.getPhoneUser()
        );
    }
}