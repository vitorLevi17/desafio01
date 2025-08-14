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



