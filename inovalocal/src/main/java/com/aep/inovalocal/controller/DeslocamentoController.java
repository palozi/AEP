package com.aep.inovalocal.controller;

import com.aep.inovalocal.model.Deslocamento;
import com.aep.inovalocal.service.MobilidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/deslocamentos")
public class DeslocamentoController {

    private final MobilidadeService service;

    public DeslocamentoController(MobilidadeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Deslocamento> registrar(@RequestBody Map<String, Object> body) {
        Long usuarioId = Long.valueOf(body.get("usuarioId").toString());
        Deslocamento.TipoTransporte tipo = Deslocamento.TipoTransporte.valueOf(body.get("tipo").toString());
        double distancia = Double.parseDouble(body.get("distancia").toString());

        Deslocamento salvo = service.registrarDeslocamento(usuarioId, tipo, distancia);
        return ResponseEntity.created(URI.create("/api/deslocamentos/" + salvo.getId())).body(salvo);
    }
}
