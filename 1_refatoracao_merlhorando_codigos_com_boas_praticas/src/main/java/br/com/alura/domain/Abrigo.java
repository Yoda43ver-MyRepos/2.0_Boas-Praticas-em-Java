package br.com.alura.domain;

public class Abrigo {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private Pet[] pets;

    public Abrigo(){}

    public Abrigo(String nome, String telefone, String email){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public long getId() {
        return this.id;
    }

    public String getTelefone(){
        return telefone;
    }

    public String getEmail(){
        return email;
    }

    public String getNome() {
        return this.nome;
    }

    public Pet[] getPets(){
        return pets;
    }
}
