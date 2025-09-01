CREATE TABLE role (
    id BIGSERIAL PRIMARY KEY,
    tipo_usuario VARCHAR(255) UNIQUE
);
INSERT INTO role (tipo_usuario) VALUES ('Dono');
INSERT INTO role (tipo_usuario) VALUES ('Cliente');

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nome VARCHAR(255),
    email VARCHAR(255),
    endereco VARCHAR(255),
    dt_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, password, nome, email, endereco)
VALUES (
    'dono',
    '',
    'Usuário Dono',
    'dono@example.com',
    'Endereço do Dono'
);

CREATE TABLE tipo_cozinha(
    id BIGSERIAL PRIMARY KEY,
    tipo VARCHAR(255) NOT NULL
);
INSERT INTO tipo_cozinha(tipo) VALUES('Ilha');
INSERT INTO tipo_cozinha(tipo) VALUES('Zonas');
INSERT INTO tipo_cozinha(tipo) VALUES('Montagem');
INSERT INTO tipo_cozinha(tipo) VALUES('Paralela');
INSERT INTO tipo_cozinha(tipo) VALUES('Em U');

