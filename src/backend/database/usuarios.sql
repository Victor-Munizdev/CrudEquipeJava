USE axis;

CREATE TABLE usuarios (
id INT AUTO_INCREMENT PRIMARY KEY,
nome_completo VARCHAR(30),
cpf TEXT(11),
email VARCHAR(100),
cargo ('administrador','gerente','colaborador') DEFAULT ('colaborador')
apelido_usuario VARCHAR(30),
senha VARCHAR(255),
);