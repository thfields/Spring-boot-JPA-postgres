package com.crud.teste.controllers;

import com.crud.teste.DTO.UsuarioDTO;
import com.crud.teste.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/criar")
    public UsuarioDTO criar(@RequestBody UsuarioDTO dto) {
        return usuarioService.criar(dto);

    }

    @GetMapping("/{id}")
    public UsuarioDTO buscarPorId(@PathVariable UUID id) {
        return usuarioService.buscarPorId(id);
    }

    @GetMapping("/listar")
    public List<UsuarioDTO> todosUsuarios() {
        return usuarioService.todosUsuarios();
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable UUID id) {
        usuarioService.deletar(id);
    }

    @PutMapping("/{id}")
    public UsuarioDTO atualizarUsuario(@PathVariable UUID id, @RequestBody UsuarioDTO dto) {
        return usuarioService.atualizar(id, dto);
    }
}
