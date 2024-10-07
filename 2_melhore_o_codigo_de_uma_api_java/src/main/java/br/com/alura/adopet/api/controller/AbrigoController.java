package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.dto.CadastrarPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<List<Abrigo>> listar() {
        return ResponseEntity.ok(abrigoRepository.findAll());
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CadastrarAbrigoDto dto) {
        this.abrigoService.verificarSeAbrigoJaExiste(dto);
    }

    @GetMapping("/{idOuNome}/pets")
    public void listarPets(@PathVariable String idOuNome) {
        this.abrigoService.econtrarListaDePetsNoAbrigo(idOuNome);
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public void cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid CadastrarPetDto dto) {
        Pet pet =  new Pet(dto.tipo(), dto.nome(),dto.raca(),dto.idade(),dto.cor(),dto.peso());
        abrigoService.cadastrarPetEmUmAbrigo(idOuNome, pet);
    }

}
