package com.wipro.teste.bradesco.enums;

public enum EstadosEnum {


    AC("NORTE", 20.83),
    AL("NORDESTE", 15.98),
    AP("NORTE", 20.83),
    AM("NORTE", 20.83),
    BA("NORDESTE", 15.98),
    CE("NORDESTE", 15.98),
    DF("CENTRO-OESTE", 12.50),
    ES("SUDESTE", 7.85),
    GO("CENTRO-OESTE", 12.50),
    MA("NORDESTE", 15.98),
    MT("CENTRO-OESTE", 12.50),
    MS("CENTRO-OESTE", 12.50),
    MG("SUDESTE", 7.85),
    PA("NORTE", 20.83),
    PB("NORDESTE", 15.98),
    PR("SUL", 17.30),
    PE("NORDESTE", 15.98),
    PI("NORDESTE", 15.98),
    RJ("SUDESTE", 7.85),
    RN("NORDESTE", 15.98),
    RS("SUL", 17.30),
    RO("NORTE", 20.83),
    RR("NORTE", 20.83),
    SC("SUL", 17.30),
    SP("SUDESTE", 7.85),
    SE("NORDESTE", 15.98),
    TO("NORTE", 20.83);

    private final String regiao;

    private final Double preço;

    EstadosEnum(String regiao, Double preço) {
        this.regiao = regiao;
        this.preço = preço;
    }

    public String getRegiao() {
        return regiao;
    }

    public Double getPreço(){
        return preço;
    }
}

