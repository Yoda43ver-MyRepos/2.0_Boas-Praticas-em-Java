package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
    boolean existsByNome(String nome);

    boolean existsByTelefone(String telefone);

    boolean existsByEmail(String email);

    Abrigo findByNome(String nome);

    @Query("SELECT COUNT(p) > 0 FROM Pessoa p WHERE p.nome = :nome OR p.telefone = :telefone OR p.email = :email")
    boolean existsByNomeOrTelefoneOrEmail(String nome, String telefone, String email);


    @Query("SELECT p FROM Pet p WHERE p.abrigo.id = :abrigoId")
    List<Pet> findPetsByAbrigoId(Long abrigoId);

    @Query("SELECT p FROM Pet p WHERE p.abrigo.name = :name")
    List<Pet> findPetsByAbrigoName(String  name);

}

