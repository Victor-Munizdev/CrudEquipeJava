package repository;

import database.ConexaoBanco;
import model.Projeto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelas operações de banco de dados para a entidade Projeto.
 */
public class ProjetoRepository {

    /**
     * Retorna todos os projetos cadastrados no banco de dados.
     * @return Lista de objetos Projeto
     */
    public List<Projeto> listarTodos() {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM projetos";

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Projeto projeto = new Projeto(
                    rs.getInt("id"),
                    rs.getString("nome_projeto"),
                    rs.getString("descricao"),
                    rs.getDate("data_inicio"),
                    rs.getDate("data_termino_prevista"),
                    rs.getString("status"),
                    rs.getInt("id_gerente_responsavel")
                );
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            System.err.println("ERRO ao listar projetos: " + e.getMessage());
        }
        return projetos;
    }

    /**
     * Busca um projeto pelo seu ID.
     * @param id ID do projeto
     * @return Objeto Projeto ou null se não encontrado
     */
    public Projeto buscarPorId(int id) {
        String sql = "SELECT * FROM projetos WHERE id = ?";
        Projeto projeto = null;

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    projeto = new Projeto(
                        rs.getInt("id"),
                        rs.getString("nome_projeto"),
                        rs.getString("descricao"),
                        rs.getDate("data_inicio"),
                        rs.getDate("data_termino_prevista"),
                        rs.getString("status"),
                        rs.getInt("id_gerente_responsavel")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("ERRO ao buscar projeto por ID: " + e.getMessage());
        }
        return projeto;
    }
}
