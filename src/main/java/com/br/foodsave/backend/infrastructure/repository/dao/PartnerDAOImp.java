package com.br.foodsave.backend.infrastructure.repository.dao;

import com.br.foodsave.backend.infrastructure.repository.PartnerEntity;
import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class PartnerDAOImp implements PartnerDAO<PartnerEntity>{
    private static final Logger log = LoggerFactory.getLogger(PartnerDAOImp.class);
    private JdbcTemplate jdbcTemplate;

    RowMapper<PartnerEntity> rowMapper = (rs, rowNum) -> {
        PartnerEntity partner = new PartnerEntity();
        partner.setPartnerId(rs.getInt("partner_id"));
        partner.setName(rs.getString("name"));
        partner.setSector(rs.getString("sector"));

        return partner;
    };

    public PartnerDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PartnerEntity create(PartnerEntity partnerEntity) {
        String query = "INSERT INTO partner(name, sector) VALUES (?, ?)";

        // Gerenciador do ID retornado do INSERT
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, partnerEntity.getName());
            ps.setString(2, partnerEntity.getSector());
            return ps;
        }, keyHolder);

        // Atribuindo a generatedID o id retornado
        Long generatedId = keyHolder.getKey().longValue();

        String selectQuery = "SELECT partner_id, name, sector FROM partner WHERE partner_id = ?";
        PartnerEntity updatedPartnerEntity = jdbcTemplate.queryForObject(
                selectQuery,
                new Object[]{generatedId},
                (rs, rowNum) -> {
                    PartnerEntity entity = new PartnerEntity();
                    entity.setName(rs.getString("name"));
                    entity.setSector(rs.getString("sector"));
                    entity.setPartnerId(rs.getInt("partner_id"));
                    return entity;
                }
        );

        return updatedPartnerEntity;
    }

    @Override
    public List<PartnerEntity> list() {
        String query = "SELECT partner_id, name, sector FROM partner";

        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public Optional<PartnerEntity> get(int id) {
        String query = "SELECT partner_id, name, sector FROM partner WHERE partner_id = ?";
        PartnerEntity partnerDao = null;

        try {
            partnerDao = jdbcTemplate.queryForObject(query, new Object[]{id}, rowMapper);
        } catch (DataException ex) {
            log.info("Parceiro não encontrado: " + id);
        }

        return Optional.ofNullable(partnerDao);
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from partner where partner_id = ?";
        int rows = jdbcTemplate.update(query, id);

        return rows > 0;
    }
}
