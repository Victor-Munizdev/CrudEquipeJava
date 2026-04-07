package repository;

import database.ConexaoBanco;
import model.Usuario;
import model.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelas operações de banco de dados para a entidade Usuário.
 */
public class UsuarioRepository {

    /**
     * Retorna todos os usuários cadastrados no banco de dados.
     * @return Lista de objetos Usuario
     */
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome_completo"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    Cargo.fromString(rs.getString("cargo")),
                    rs.getString("login"),
                    rs.getString("senha")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("ERRO ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    /**
     * Busca um usuário pelo seu ID.
     * @param id ID do usuário
     * @return Objeto Usuario ou null se não encontrado
     */
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = null;

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome_completo"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        Cargo.fromString(rs.getString("cargo")),
                        rs.getString("login"),
                        rs.getString("senha")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("ERRO ao buscar usuário por ID: " + e.getMessage());
        }
        return usuario;
    }
    /**
     * Insere um novo usuário no banco de dados.
     */
    public boolean inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome_completo, cpf, email, cargo, login, senha) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCargo().getDescricao());
            stmt.setString(5, usuario.getLogin());
            stmt.setString(6, usuario.getSenha());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("ERRO ao inserir usuário: " + e.getMessage());
            return false;
        }
    }

    /**
     * Atualiza os dados de um usuário existente.
     */
    public boolean editar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome_completo = ?, cpf = ?, email = ?, cargo = ?, login = ?, senha = ? WHERE id = ?";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCargo().getDescricao());
            stmt.setString(5, usuario.getLogin());
            stmt.setString(6, usuario.getSenha());
            stmt.setInt(7, usuario.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("ERRO ao editar usuário: " + e.getMessage());
            return false;
        }
    }

    /**
     * Remove um usuário pelo ID.
     */
    public boolean deletar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("ERRO ao deletar usuário: " + e.getMessage());
            return false;
        }
    }
}