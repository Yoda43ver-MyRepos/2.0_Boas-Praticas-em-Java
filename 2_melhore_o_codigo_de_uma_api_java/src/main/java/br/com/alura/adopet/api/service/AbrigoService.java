package br.com.alura.adopet.api.service;


import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRepository;


    public ResponseEntity<String> verificarSeAbrigoJaExiste(CadastrarAbrigoDto dto){
        boolean abrigoJaCadastrado = abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(),dto.telefone(),dto.email());

        if (abrigoJaCadastrado) {
            return ResponseEntity.badRequest().body("Dados já cadastrados para outro abrigo!");
        } else {
            Abrigo abrigo = new Abrigo(dto.nome(), dto.telefone(), dto.email());
            abrigoRepository.save(abrigo);
            return ResponseEntity.ok().build();
        }
    }


    public ResponseEntity<List<Pet>> econtrarListaDePetsNoAbrigo(String idOuNome){
        try {
            Long id = Long.parseLong(idOuNome);
            List<Pet> pets = abrigoRepository.findPetsByAbrigoId(id);
            return ResponseEntity.ok(pets);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (NumberFormatException e) {
            try {
                List<Pet> pets = abrigoRepository.findPetsByAbrigoName(idOuNome);
                return ResponseEntity.ok(pets);
            } catch (EntityNotFoundException ex) {
                return ResponseEntity.notFound().build();
            }
        }
    }


    public ResponseEntity<String> cadastrarPetEmUmAbrigo(String idOuNome, Pet pet){
        try {
            Long id = Long.parseLong(idOuNome);
            Abrigo abrigo = abrigoRepository.getReferenceById(id);

            pet.cadastraPetNoAbrigo(abrigo);
            abrigo.adicionaPetsNoAbrigo(pet);
          //

            abrigoRepository.save(abrigo);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build();
        } catch (NumberFormatException nfe) {
            try {
                Abrigo abrigo = abrigoRepository.findByNome(idOuNome);
                pet.cadastraPetNoAbrigo(abrigo);
                abrigo.getPets().add(pet);
                abrigoRepository.save(abrigo);
                return ResponseEntity.ok().build();
            } catch (EntityNotFoundException enfe) {
                return ResponseEntity.notFound().build();
            }
        }
    }


}

