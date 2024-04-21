package com.example.nanarback.nanar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/nanars")
@CrossOrigin(origins = "*")
public class NanarController {


    private NanarService nanarService;

    @Autowired
    public NanarController(NanarService nanarService) {
        this.nanarService = nanarService;
    }

    @GetMapping("")
    public List<Nanar> getAll() {
        return nanarService.getAll();
    }

    @GetMapping("/{id}")
    public Nanar getById(@PathVariable Long id) {
        return nanarService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity createNanar(@RequestBody Nanar nanar) {
        Nanar created_nanar = nanarService.create(nanar);
        return ResponseEntity.created(URI.create("/nanars/"+ created_nanar.getIdNanar())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateNanar(@PathVariable Long id, @RequestBody Nanar nanar) {
        nanarService.update(id, nanar);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNanar(@PathVariable Long id) {
        nanarService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
