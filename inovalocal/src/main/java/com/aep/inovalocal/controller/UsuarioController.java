package com.aep.inovalocal.controller;

import com.aep.inovalocal.model.Usuario;
import com.aep.inovalocal.service.MobilidadeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final MobilidadeService service;

    public UsuarioController(MobilidadeService service) {
        this.service = service;
    }

    @PostMapping // sem consumes/produces
    public ResponseEntity<Usuario> criar(@RequestBody Usuario body) {
        var criado = service.criarUsuario(body.getNome(), body.getEmail(), body.getSenha());
        return ResponseEntity.created(URI.create("/api/usuarios/" + criado.getId())).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listarUsuarios());
    }
}
