package com.aep.inovalocal.controller;

import com.aep.inovalocal.model.Recompensa;
import com.aep.inovalocal.service.MobilidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recompensas")
public class RecompensaController {

    private final MobilidadeService service;

    public RecompensaController(MobilidadeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Recompensa>> listar() {
        return ResponseEntity.ok(service.listarRecompensas());
    }

    @PostMapping("/resgatar")
    public ResponseEntity<?> resgatar(@RequestBody Map<String, Object> body) {
        Long usuarioId = Long.valueOf(body.get("usuarioId").toString());
        Long recompensaId = Long.valueOf(body.get("recompensaId").toString());

        boolean ok = service.resgatarRecompensa(usuarioId, recompensaId);
        if (ok) return ResponseEntity.ok("Recompensa resgatada");
        return ResponseEntity.badRequest().body("Pontos insuficientes");
    }
}
