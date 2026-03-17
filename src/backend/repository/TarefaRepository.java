package repository;

import database.ConexaoBanco;
import model.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelas operações de banco de dados para a entidade Tarefa.
 */
public class TarefaRepository {

    /**
     * Retorna todas as tarefas cadastradas no banco de dados.
     * @return Lista de objetos Tarefa
     */
    public List<Tarefa> listarTodas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getDate("data_vencimento"),
                    rs.getString("status"),
                    rs.getInt("id_projeto"),
                    rs.getInt("id_usuario_responsavel")
                );
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            System.err.println("ERRO ao listar tarefas: " + e.getMessage());
        }
        return tarefas;
    }

    /**
     * Busca uma tarefa pelo seu ID.
     * @param id ID da tarefa
     * @return Objeto Tarefa ou null se não encontrada
     */
    public Tarefa buscarPorId(int id) {
        String sql = "SELECT * FROM tarefas WHERE id = ?";
        Tarefa tarefa = null;

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tarefa = new Tarefa(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getDate("data_vencimento"),
                        rs.getString("status"),
                        rs.getInt("id_projeto"),
                        rs.getInt("id_usuario_responsavel")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("ERRO ao buscar tarefa por ID: " + e.getMessage());
        }
        return tarefa;
    }
}
