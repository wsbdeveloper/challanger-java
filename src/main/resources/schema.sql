CREATE TABLE partner (
    partner_id INT AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    sector VARCHAR(80) NOT NULL,
    CONSTRAINT pk_partner_partner_id PRIMARY KEY (partner_id)
);