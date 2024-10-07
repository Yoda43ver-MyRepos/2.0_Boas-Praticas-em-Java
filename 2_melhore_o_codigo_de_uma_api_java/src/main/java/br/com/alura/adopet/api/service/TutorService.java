package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizarTutorDto;
import br.com.alura.adopet.api.dto.CadastrarTutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public ResponseEntity<String> verificaSeTutorJaEstaCadastrado(CadastrarTutorDto dto){
        boolean telefoneJaCadastrado = tutorRepository.existsByTelefone(dto.telefone());
        boolean emailJaCadastrado = tutorRepository.existsByEmail(dto.email());

        if (telefoneJaCadastrado || emailJaCadastrado) {
            return ResponseEntity.badRequest().body("Dados já cadastrados para outro tutor!");
        } else {

            tutorRepository.save(new Tutor(dto.nome(),dto.telefone(),dto.email()));
            return ResponseEntity.ok().build();
        }
    }

    public void atualizarDadosTutor(AtualizarTutorDto dto){
        Tutor tutor =  tutorRepository.getReferenceById(dto.id());
        tutor.atualizarNome(dto.nome());
        tutorRepository.save(tutor);
    }


}
