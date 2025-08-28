package com.crud.teste.services;

import com.crud.teste.DTO.UsuarioDTO;
import com.crud.teste.exceptions.BuscarUsuarioException;
import com.crud.teste.models.Usuario;
import com.crud.teste.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
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
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new BuscarUsuarioException());
        return toDTO(usuario);
    }

    public List<UsuarioDTO> todosUsuarios() {
        return usuarioRepository.findAll().stream().map(usuario -> toDTO(usuario)).collect(Collectors.toList());
    }

    public void deletar(UUID id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO atualizar(UUID id, UsuarioDTO dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElseThrow(() -> new BuscarUsuarioException());
        if (usuarioExistente != null) {
            usuarioExistente.setDataNascimento(dto.getDataNascimento());
            usuarioExistente.setEndereco(dto.getEndereco());
            usuarioExistente.setNome(dto.getNome());
            return toDTO(usuarioRepository.save(usuarioExistente));
        }
        return (null);
    }
}
