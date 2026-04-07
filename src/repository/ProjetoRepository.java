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
    /**
     * Insere um novo projeto no banco de dados.
     */
    public boolean inserir(Projeto projeto) {
        String sql = "INSERT INTO projetos (nome_projeto, descricao, data_inicio, data_termino_prevista, status, id_gerente_responsavel) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getDescricao());
            stmt.setDate(3, projeto.getDataInicio());
            stmt.setDate(4, projeto.getDataTerminoPrevista());
            stmt.setString(5, projeto.getStatus());
            stmt.setInt(6, projeto.getIdGerenteResponsavel());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("ERRO ao inserir projeto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Atualiza os dados de um projeto existente.
     */
    public boolean editar(Projeto projeto) {
        String sql = "UPDATE projetos SET nome_projeto = ?, descricao = ?, data_inicio = ?, data_termino_prevista = ?, status = ?, id_gerente_responsavel = ? WHERE id = ?";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getDescricao());
            stmt.setDate(3, projeto.getDataInicio());
            stmt.setDate(4, projeto.getDataTerminoPrevista());
            stmt.setString(5, projeto.getStatus());
            stmt.setInt(6, projeto.getIdGerenteResponsavel());
            stmt.setInt(7, projeto.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("ERRO ao editar projeto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Remove um projeto pelo ID.
     */
    public boolean deletar(int id) {
        String sql = "DELETE FROM projetos WHERE id = ?";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("ERRO ao deletar projeto: " + e.getMessage());
            return false;
        }
    }
}
