package com.br.foodsave.backend.infrastructure.repository;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class PartnerEntity implements Serializable {
    private int partnerId;

    @NotBlank
    private String name;
    @NotBlank
    private String sector;

    public PartnerEntity(int partnerId, String name, String sector) {
        this.partnerId = partnerId;
        this.name = name;
        this.sector = sector;
    }

    public PartnerEntity(String name, String sector) {
        this.name = name;
        this.sector = sector;
    }

    public PartnerEntity() {
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
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
                "patnerId=" + partnerId +
                ", name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                '}';
    }

    public String info(String message) {
        return message;
    }
}
