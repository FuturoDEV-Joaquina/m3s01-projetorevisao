package br.com.joaquina.m3s01projetorevisao.controllers;

import br.com.joaquina.m3s01projetorevisao.entities.Demand;
import br.com.joaquina.m3s01projetorevisao.services.DemandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("demand")
public class DemandController {

    private DemandService demandService;

    public DemandController(DemandService demandService) {
        this.demandService = demandService;
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Demand demand) {
        try {
            return ResponseEntity.ok(demandService.create(demand));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok(demandService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getId(@PathVariable Long id) {
        try {
            Demand demand = demandService.getById(id);
            return ResponseEntity.ok(demand);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
