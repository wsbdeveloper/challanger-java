package com.br.foodsave.backend.infrastructure.repository.cache;

import com.br.foodsave.backend.infrastructure.repository.PartnerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class RedisPartner {
    @Autowired
    private CacheManager cacheManager;
    public void cachePartner(int id, PartnerEntity partner) {
        String cacheName = "foodsavecaching";

        Cache cache = cacheManager.getCache(cacheName);

        if (cache != null) {
            cache.put(id, partner);
        }
    }
}
