package com.crud.teste.services;

import com.crud.teste.DTO.LicencaDTO;
import com.crud.teste.exceptions.BuscarLicencaException;
import com.crud.teste.exceptions.BuscarUsuarioException;
import com.crud.teste.exceptions.UsuarioAtivoException;
import com.crud.teste.models.Licenca;
import com.crud.teste.models.Usuario;
import com.crud.teste.repositories.LicencaRepository;
import com.crud.teste.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.hv.UUIDValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LicencaService {

    private final LicencaRepository licencaRepository;

    private final UsuarioRepository usuarioRepository;
    private final UUIDValidator uUIDValidator;

    private LicencaDTO toDTO(Licenca licenca) {
        return LicencaDTO.builder()
                .identificador(licenca.getIdentificador())
                .observacaoLicenca(licenca.getObservacaoLicenca())
                .dataDeExpiracao(licenca.getDataDeExpiracao())
                .usuarioId(licenca.getUsuario().getId())
                .build();
    }

    private Licenca toEntity(LicencaDTO dto, Usuario usuario) {
        return Licenca.builder()
                .identificador(dto.getIdentificador())
                .observacaoLicenca(dto.getObservacaoLicenca())
                .dataDeExpiracao(dto.getDataDeExpiracao())
                .usuario(usuario)
                .build();
    }

    public LicencaDTO criarLicenca(LicencaDTO dto) {

        Usuario usuarioAtivo = usuarioRepository.findById(dto.getUsuarioId()).orElseThrow(() -> new BuscarUsuarioException());
        if (!usuarioAtivo.getAtivo()){
            throw new UsuarioAtivoException();
        }
        Licenca licenca = toEntity(dto, usuarioAtivo);

        return toDTO(licencaRepository.save(licenca));
    }

    public LicencaDTO buscarLicencaPorId(UUID id) {
        return licencaRepository.findById(id).map(licenca -> toDTO(licenca)).orElseThrow(() -> new BuscarLicencaException());
    }

    public List<LicencaDTO> listarLicencas() {
        return licencaRepository.findAllAtivos().stream().map(licenca -> toDTO(licenca)).collect(Collectors.toList());
    }

    public void deletarLicenca(UUID id) {
        Licenca licencaExistente  = licencaRepository.findById(id).orElseThrow(() -> new BuscarLicencaException());
        if (licencaExistente.getAtivo().equals(true)){
            licencaExistente.setAtivo(false);
            licencaExistente.setDataAtualizacao(LocalDateTime.now());
            licencaRepository.save(licencaExistente);
        }
    }

    public LicencaDTO atualizarLicenca(UUID id, LicencaDTO dto) {
        Licenca licencaExistente = licencaRepository.findById(id).orElseThrow(() -> new BuscarLicencaException());
        if (licencaExistente != null) {
            licencaExistente.setDataDeExpiracao(dto.getDataDeExpiracao());
            licencaExistente.setObservacaoLicenca(dto.getObservacaoLicenca());
            licencaExistente.setIdentificador(dto.getIdentificador());
            licencaExistente.setDataAtualizacao(LocalDateTime.now());
            return toDTO(licencaRepository.save(licencaExistente));
        }

        return (null);
    }

    public String validarLicenca(UUID id) {
        Optional<Licenca> licencaExistente = licencaRepository.findById(id);
        if (licencaExistente.isPresent()) {
            Licenca licenca = licencaExistente.get();
            if (licenca.getDataDeExpiracao().isBefore(LocalDateTime.now())) {
                return "Licença " + licenca.getIdentificador() + " expirada";
            }

            return "Licença " + licenca.getIdentificador() + " válida até " + licenca.getDataDeExpiracao();
        }

        return (null);
    }


}
