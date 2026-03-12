USE axis;

CREATE TABLE projetos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_projeto VARCHAR(100),
    descricao TEXT,
    data_inicio DATE,
    data_termino_prevista DATE,
    status ENUM('planejado', 'em_andamento', 'concluido', 'cancelado') DEFAULT 'planejado',
    id_gerente_responsavel INT,
    FOREIGN KEY (id_gerente_responsavel) REFERENCES usuarios(id)
);