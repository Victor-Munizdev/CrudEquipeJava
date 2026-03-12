import database.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class select {

    public static void main(String[] args) {
        System.out.println("=== TESTE DE SINCRONISMO TERMINAL (AXIS) ===");

        try (Connection conn = ConexaoBanco.getConexao()) {
            if (conn != null) {
                System.out.println(">>> SUCESSO: Conectado ao MySQL via XAMPP!");

                // Teste simples de leitura da tabela equipes para provar o sincronismo
                String sql = "SELECT * FROM equipes LIMIT 5";
                try (PreparedStatement stmt = conn.prepareStatement(sql);
                        ResultSet rs = stmt.executeQuery()) {

                    System.out.println("\n--- Dados atuais no Banco ---");
                    boolean temDados = false;
                    while (rs.next()) {
                        temDados = true;
                        System.out.println("ID: " + rs.getInt("id") + " | Nome: " + rs.getString("nome_equipe"));
                    }
                    if (!temDados)
                        System.out.println("Sem equipes cadastradas ainda.");
                }

                System.out.println("\nO banco está pronto e conectado");
            }
        } catch (SQLException e) {
            System.err.println("ERRO: Problema na conexão ou na tabela. Verifique o XAMPP.");
            System.err.println("Mensagem: " + e.getMessage());
        }
    }
}
