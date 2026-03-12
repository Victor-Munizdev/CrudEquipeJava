USE axis;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(100),
    cpf CHAR(11) UNIQUE,
    email VARCHAR(100) UNIQUE,
    cargo ENUM('administrador', 'gerente', 'colaborador') DEFAULT 'colaborador',
    login VARCHAR(30) UNIQUE,
    senha VARCHAR(255)
);