package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {


    @Autowired
    private PetRepository petRepository;


    public List<Pet> listarPetsDisponiveisParaAdocao(){
         List<Pet> petsDisponiveisParaAdocao = petRepository.encontraPetsNaoAdotados();
        return petsDisponiveisParaAdocao;
    }



}
