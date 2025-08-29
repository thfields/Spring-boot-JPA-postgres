package com.crud.teste.services;

import com.crud.teste.DTO.UsuarioDTO;
import com.crud.teste.exceptions.BuscarUsuarioException;
import com.crud.teste.exceptions.UsuarioComLicencaAtivaException;
import com.crud.teste.models.Usuario;
import com.crud.teste.repositories.LicencaRepository;
import com.crud.teste.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final LicencaRepository licencaRepository;

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
        return usuarioRepository.findAllAtivos().stream().map(usuario -> toDTO(usuario)).collect(Collectors.toList());
    }

    public void deletar(UUID id) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElseThrow(() -> new BuscarUsuarioException());

        boolean isLicencaAtiva = licencaRepository.existsByUsuarioAndAtivoTrueAndDataDeExpiracaoAfter(usuarioExistente, LocalDateTime.now());
        if (isLicencaAtiva) {
            throw new UsuarioComLicencaAtivaException(
                    "Usuario " + usuarioExistente.getNome() + " com Licenca Ativa! Só poderá ser removido se não hover nenhuma licença ativa. "
            );
        }
        if (usuarioExistente.getAtivo().equals(true)) {
            usuarioExistente.setAtivo(false);
            usuarioExistente.setDataAtualizacao(LocalDateTime.now());
            usuarioRepository.save(usuarioExistente);
        }
    }

    public UsuarioDTO atualizar(UUID id, UsuarioDTO dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElseThrow(() -> new BuscarUsuarioException());
        if (usuarioExistente != null) {
            usuarioExistente.setDataNascimento(dto.getDataNascimento());
            usuarioExistente.setEndereco(dto.getEndereco());
            usuarioExistente.setNome(dto.getNome());
            usuarioExistente.setDataAtualizacao(LocalDateTime.now());
            return toDTO(usuarioRepository.save(usuarioExistente));
        }
        return (null);
    }
}
