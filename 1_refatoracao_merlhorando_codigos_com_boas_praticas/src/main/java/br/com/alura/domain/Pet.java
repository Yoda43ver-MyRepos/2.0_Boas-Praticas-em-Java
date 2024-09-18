package br.com.alura.domain;

public class Pet {
    private Long id;
    private Tipo tipo;
    private String nome;
    private String raca;
    private Integer idade;
    private String cor;
    private Double peso;

    public Pet() {
    }

    public Pet(Tipo tipo, String nome, String raca, int idade, String cor, double peso) {
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.cor = cor;
        this.peso = peso;

    }


    public long getId() {
        return this.id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }

    public String getCor() {
        return cor;
    }

    public double getPeso() {
        return peso;
    }


}
