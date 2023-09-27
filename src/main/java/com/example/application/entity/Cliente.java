package com.example.application.entity;

import java.util.List;

public class Cliente {
    private long id;
    private String nome;
    private String telefone;
    private long cpf;
    private String email;
    private long numCnh;
    
    public Cliente() {
    }
    public Cliente(String nome, String telefone, long cpf, String email, long numCnh) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.numCnh = numCnh;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public long getCpf() {
        return cpf;
    }
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public long getNumCnh() {
        return numCnh;
    }
    public void setNumCnh(long numCnh) {
        this.numCnh = numCnh;
    }
    @Override
    public String toString() {
        return nome +" "+ cpf; 
    }
   
    
}

