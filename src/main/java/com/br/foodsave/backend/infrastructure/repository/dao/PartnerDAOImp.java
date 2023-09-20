package com.br.foodsave.backend.infrastructure.repository.dao;

import com.br.foodsave.backend.infrastructure.repository.PartnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PartnerDAOImp implements PartnerDAO<PartnerRepository>{
    private static final Logger log = LoggerFactory.getLogger(PartnerDAOImp.class);
    private JdbcTemplate jdbcTemplate;

    RowMapper<PartnerRepository> rowMapper = (rs, rowNum) -> {
        PartnerRepository partner = new PartnerRepository();
        partner.setPatnerId(rs.getInt("partner_id"));
        partner.setName(rs.getString("name"));
        partner.setSector(rs.getString("sector"));

        return partner;
    };

    public PartnerDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(PartnerRepository partnerRepository) {
        return;
    }

    @Override
    public List<PartnerRepository> list() {
        String query = "SELECT partner_id, name, sector FROM partner";

        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public Optional<PartnerRepository> get(int id) {
        return Optional.empty();
    }

    @Override
    public void delete(int id) {

    }
}
