package com.br.foodsave.backend.service;

import com.br.foodsave.backend.infrastructure.repository.PartnerEntity;
import com.br.foodsave.backend.infrastructure.repository.cache.RedisPartner;
import com.br.foodsave.backend.infrastructure.repository.dao.PartnerDAOImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnerService {
    private PartnerDAOImp partnerDaoImp;

    @Autowired
    RedisPartner redisPartner;

    private String cacheName;

    public PartnerService(PartnerDAOImp partnerDaoImp) {
        this.partnerDaoImp = partnerDaoImp;
    }

    public PartnerEntity create(PartnerEntity entity) {
        return this.partnerDaoImp.create(entity);
    }

    public List<PartnerEntity> list() {
        return this.partnerDaoImp.list();
    }


    @Cacheable("foodsavecaching")
    public Optional<PartnerEntity> get(int id) {
        var cachedPartner = this.partnerDaoImp.get(id);

        if (!cachedPartner.isPresent()){
            return cachedPartner;
        }

        Optional<PartnerEntity> partner = this.partnerDaoImp.get(id);

        if (partner.isPresent()) {
            redisPartner.cachePartner(id, partner.get());
        }
        return partner;

    }

    public boolean deletePartner(int id) {
        return this.partnerDaoImp.delete(id);
    }
}
