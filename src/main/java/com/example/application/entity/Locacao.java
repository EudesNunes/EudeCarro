package com.example.application.entity;

import java.util.Date;

public class Locacao {
    private long id;
    private Date dataSaida;
    private Date dataPrevDev; 
    private Date dataDev;
    private double valor;
    private Veiculo veiculoAlocado;
    private Cliente cliente;
    
    
    public Locacao() {
    }   

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Date getDataSaida() {
        return dataSaida;
    }
    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }
    public Date getDataPrevDev() {
        return dataPrevDev;
    }
    public void setDataPrevDev(Date dataPrevDev) {
        this.dataPrevDev = dataPrevDev;
    }
    public Date getDataDev() {
        return dataDev;
    }
    public void setDataDev(Date dataDev) {
        this.dataDev = dataDev;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public Veiculo getVeiculoAlocado() {
        return veiculoAlocado;
    }
    public void setVeiculoAlocado(Veiculo veiculoAlocado) {
        this.veiculoAlocado = veiculoAlocado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    



}
