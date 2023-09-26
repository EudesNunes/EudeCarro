package com.example.application.enums;

public enum EnumStatus {
    LOCADO(1),
    EMMANUTENCAO(2),
    DISPONIVEL(3),
    QUEBRADO(4),
    EMDESUSO(5);

    private final long valor;

    private EnumStatus(long valor) {
        this.valor = valor;
    }

    public long getValor() {
        return valor;
    }
    public static EnumStatus getEnumStatusByValor(long valor) {
        for (EnumStatus status : EnumStatus.values()) {
            if (status.getValor() == valor) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nenhum EnumStatus encontrado para o valor: " + valor);
    }
    
}