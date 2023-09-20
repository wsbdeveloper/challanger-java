package com.br.foodsave.backend.infrastructure.repository;

public class PartnerRepository {
    private int patnerId;
    private String name;
    private String sector;

    public PartnerRepository(int patnerId, String name, String sector) {
        this.patnerId = patnerId;
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

    @Override
    public String toString() {
        return "PatnerRepository{" +
                "patnerId=" + patnerId +
                ", name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                '}';
    }
}
