package com.br.foodsave.backend.service;

import com.br.foodsave.backend.infrastructure.repository.PartnerEntity;
import com.br.foodsave.backend.infrastructure.repository.dao.PartnerDAOImp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartnerService {
    private PartnerDAOImp partnerDaoImp;

    public PartnerService(PartnerDAOImp partnerDaoImp) {
        this.partnerDaoImp = partnerDaoImp;
    }

    public PartnerEntity create(PartnerEntity entity) {
        return this.partnerDaoImp.create(entity);
    }

    public List<PartnerEntity> list() {
        return this.partnerDaoImp.list();
    }

    public Optional<PartnerEntity> get(int id) {
        return this.partnerDaoImp.get(id);
    }

    public void deletePartner(int id) {
        this.partnerDaoImp.delete(id);
    }
}
