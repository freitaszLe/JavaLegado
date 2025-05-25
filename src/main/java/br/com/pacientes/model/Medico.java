package br.com.pacientes.model;

import javax.persistence.*;

@Entity
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade; // Área de atuação

    // Enum com as especialidades
    public enum Especialidade {
        ORTOPEDIA,
        GINECOLOGIA,
        DERMATOLOGIA,
        CARDIOLOGIA,
        PEDIATRIA
    }

    // Getters e Setters (gerar todos)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public Especialidade getEspecialidade() { return especialidade; }
    public void setEspecialidade(Especialidade especialidade) { this.especialidade = especialidade; }
}