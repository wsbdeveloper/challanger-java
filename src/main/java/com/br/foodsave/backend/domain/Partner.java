package com.br.foodsave.backend.domain;

public class Partner {
    private int patnerId;
    private String name;
    private String sector;

    public void changeSectorPartner(String newSector){
        // Todo
    }

    public Partner() {
    }

    public Partner(String name, String sector) {
        this.name = name;
        this.sector = sector;
    }

    public int getPatnerId() {
        return patnerId;
    }

    public void setPatnerId(int patnerId) {
        this.patnerId = patnerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
