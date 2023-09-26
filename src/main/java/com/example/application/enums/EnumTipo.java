package com.example.application.enums;

public enum EnumTipo {
    HATCH(1),
    SEDA(2),
    CONVERSIVEL(3),
    CUPE(4),
    SUV(5),
    PICAPE(6),
    VAN(7);

    private final long valor;

    private EnumTipo(long valor) {
        this.valor = valor;
    }
    

    public long getValor() {
        return valor;
    }
    public static EnumTipo getEnumTipoByValor(long valor) {
        for (EnumTipo tipo : EnumTipo.values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nenhum EnumTipo encontrado para o valor: " + valor);
    }
}