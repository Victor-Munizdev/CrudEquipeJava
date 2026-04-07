package repository;

import database.ConexaoBanco;
import model.Equipe;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelas operações de banco de dados para a entidade Equipe.
 */
public class EquipeRepository {

    /**
     * Retorna todas as equipes cadastradas no banco de dados.
     * @return Lista de objetos Equipe
     */
    public List<Equipe> listarTodos() {
        List<Equipe> equipes = new ArrayList<>();
        String sql = "SELECT * FROM equipes";

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Equipe equipe = new Equipe(
                    rs.getInt("id"),
                    rs.getString("nome_equipe"),
                    rs.getString("descricao")
                );
                equipes.add(equipe);
            }
        } catch (SQLException e) {
            System.err.println("ERRO ao listar equipes: " + e.getMessage());
        }
        return equipes;
    }

    /**
     * Busca uma equipe pelo seu ID.
     * @param id ID da equipe
     * @return Objeto Equipe ou null se não encontrada
     */
    public Equipe buscarPorId(int id) {
        String sql = "SELECT * FROM equipes WHERE id = ?";
        Equipe equipe = null;

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    equipe = new Equipe(
                        rs.getInt("id"),
                        rs.getString("nome_equipe"),
                        rs.getString("descricao")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("ERRO ao buscar equipe por ID: " + e.getMessage());
        }
        return equipe;
    }

    /**
     * Retorna a lista de usuários que pertencem a uma equipe.
     * @param idEquipe ID da equipe
     * @return Lista de objetos Usuario
     */
    public List<Usuario> listarMembros(int idEquipe) {
        List<Usuario> membros = new ArrayList<>();
        String sql = "SELECT u.* FROM usuarios u " +
                     "JOIN equipe_membros em ON u.id = em.id_usuario " +
                     "WHERE em.id_equipe = ?";

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEquipe);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome_completo"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        model.Cargo.fromString(rs.getString("cargo")),
                        rs.getString("login"),
                        rs.getString("senha")
                    );
                    membros.add(usuario);
                }
            }
        } catch (SQLException e) {
            System.err.println("ERRO ao listar membros da equipe: " + e.getMessage());
        }
        return membros;
    }
    /**
     * Insere uma nova equipe no banco de dados.
     * @param equipe Objeto Equipe contendo os dados (nome_equipe e descricao)
     * @return true se inserido com sucesso, false caso contrário
     */
    public boolean inserir(Equipe equipe) {
        String sql = "INSERT INTO equipes (nome_equipe, descricao) VALUES (?, ?)";
        
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, equipe.getNomeEquipe());
            stmt.setString(2, equipe.getDescricao());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("ERRO ao inserir equipe: " + e.getMessage());
            return false;
        }
    }

    /**
     * Atualiza os dados de uma equipe existente.
     * @param equipe Objeto Equipe com os novos dados e o ID da equipe a ser atualizada
     * @return true se atualizado com sucesso, false caso contrário
     */
    public boolean atualizar(Equipe equipe) {
        String sql = "UPDATE equipes SET nome_equipe = ?, descricao = ? WHERE id = ?";
        
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, equipe.getNomeEquipe());
            stmt.setString(2, equipe.getDescricao());
            stmt.setInt(3, equipe.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("ERRO ao atualizar equipe: " + e.getMessage());
            return false;
        }
    }

    /**
     * Remove uma equipe do banco de dados pelo seu ID.
     * @param id ID da equipe a ser removida
     * @return true se removida com sucesso, false caso contrário
     */
    public boolean deletar(int id) {
        String sql = "DELETE FROM equipes WHERE id = ?";
        
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("ERRO ao deletar equipe: " + e.getMessage());
            return false;
        }
    }
}