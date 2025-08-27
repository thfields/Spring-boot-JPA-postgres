package com.crud.teste.services;

import com.crud.teste.DTO.UsuarioDTO;
import com.crud.teste.models.Usuario;
import com.crud.teste.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .dataNascimento(usuario.getDataNascimento())
                .endereco(usuario.getEndereco())
                .build();
    }

    private Usuario toEntity(UsuarioDTO dto) {
        return Usuario.builder()
                .nome(dto.getNome())
                .dataNascimento(dto.getDataNascimento())
                .endereco(dto.getEndereco())
                .build();
    }

    public UsuarioDTO criar(UsuarioDTO dto) {
        Usuario usuario = toEntity(dto);
        return  toDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO buscarPorId(UUID id) {
        return usuarioRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public List<UsuarioDTO> todosUsuarios() {
        return usuarioRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void deletar(UUID id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO atualizar(UUID id, UsuarioDTO dto) {
        return usuarioRepository.findById(id).map(usuario ->  {
            usuario.setNome(dto.getNome());
            usuario.setDataNascimento(dto.getDataNascimento());
            usuario.setEndereco(dto.getEndereco());
            return toDTO(usuarioRepository.save(usuario));
        }).orElse(null);
    }
}
