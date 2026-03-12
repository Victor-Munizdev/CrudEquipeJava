USE axis;

CREATE TABLE equipes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_equipe VARCHAR(100),
    descricao TEXT
);

CREATE TABLE equipe_membros (
    id_equipe INT,
    id_usuario INT,
    PRIMARY KEY (id_equipe, id_usuario),
    FOREIGN KEY (id_equipe) REFERENCES equipes(id) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE projeto_equipes (
    id_projeto INT,
    id_equipe INT,
    PRIMARY KEY (id_projeto, id_equipe),
    FOREIGN KEY (id_projeto) REFERENCES projetos(id) ON DELETE CASCADE,
    FOREIGN KEY (id_equipe) REFERENCES equipes(id) ON DELETE CASCADE
);