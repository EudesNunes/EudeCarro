package com.example.application.entity;

import com.example.application.enums.EnumStatus;
import com.example.application.enums.EnumTipo;

public class Veiculo {
    private long id;
    private EnumTipo tipoVeiculo; 
    private String marca;
    private String combustivel;
    private long km;
    private EnumStatus status;
    private String modelo;
    private long renavan;
    private String placa;

    
    
    public Veiculo() {
    }
    public Veiculo(EnumTipo tipoVeiculo, String marca, String combustivel, long km, EnumStatus status, String modelo,
            long renavan, String placa) {
        this.tipoVeiculo = tipoVeiculo;
        this.marca = marca;
        this.combustivel = combustivel;
        this.km = km;
        this.status = status;
        this.modelo = modelo;
        this.renavan = renavan;
        this.placa = placa;
    }
    public EnumTipo getTipoVeiculo() {
        return tipoVeiculo;
    }
    public void setTipoVeiculo(EnumTipo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getCombustivel() {
        return combustivel;
    }
    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }
    public long getKm() {
        return km;
    }
    public void setKm(long km) {
        this.km = km;
    }
    public EnumStatus getStatus() {
        return status;
    }
    public void setStatus(EnumStatus status) {
        this.status = status;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public long getRenavan() {
        return renavan;
    }
    public void setRenavan(long renavan) {
        this.renavan = renavan;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    
}
