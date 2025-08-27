package com.crud.teste.controllers;

import com.crud.teste.DTO.LicencaDTO;
import com.crud.teste.services.LicencaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/licencas")
@RequiredArgsConstructor
public class LicencaContoller {

    private final LicencaService licencaService;

    @PostMapping("/criar")
    public LicencaDTO criarLicenca(@RequestBody LicencaDTO dto) {
        return licencaService.criarLicenca(dto);
    }

    @GetMapping("/{id}")
    public LicencaDTO buscarLicencaPorId(@PathVariable UUID id) {
        return licencaService.buscarLicencaPorId(id);
    }

    @GetMapping("/listar")
    public List<LicencaDTO> buscarLicencas() {
        return licencaService.listarLicencas();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        licencaService.deletarLicenca(id);
    }

    @PutMapping("/{id}")
    public LicencaDTO atualizarLicenca(@PathVariable UUID id, @RequestBody LicencaDTO dto) {
        return licencaService.atualizarLicenca(id, dto);
    }

    @GetMapping("/validar/{id}")
    public String validarLicenca(@PathVariable UUID id) {
        return licencaService.validarLicenca(id);
    }
}
