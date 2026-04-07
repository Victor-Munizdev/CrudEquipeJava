package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

/**
 * Classe responsável pela conexão com o banco de dados MySQL (XAMPP).
 * Gerencia o carregamento das propriedades do arquivo .env e estabelece
 * a conexão via JDBC.
 */
public class ConexaoBanco {
    private static final String CAMINHO_ENV = ".env";

    private ConexaoBanco() {
    }

    /**
     * Obtém uma nova conexão com o banco de dados MySQL (XAMPP).
     */
    public static Connection getConexao() {
        try {
            Properties configuracoes = carregarConfiguracoes();

            String host = configuracoes.getProperty("DB_HOST");
            String porta = configuracoes.getProperty("DB_PORT");
            String nomeBanco = configuracoes.getProperty("DB_NOME");
            String usuario = configuracoes.getProperty("DB_USUARIO");
            String senha = configuracoes.getProperty("DB_SENHA");

            // URL de conexão para MySQL local (XAMPP)
            // O parâmetro useSSL=false é comum em ambientes de desenvolvimento locais.
            String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC", host, porta, nomeBanco);

            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(url, usuario, senha);

        } catch (ClassNotFoundException e) {
            System.err.println("ERRO: Driver JDBC do MySQL não encontrado! Certifique-se de que o .jar está na pasta lib.");
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao conectar ao MySQL (XAMPP): " + e.getMessage());
        } catch (IOException e) {
            System.err.println("ERRO: Não foi possível ler o arquivo .env: " + e.getMessage());
        }
        return null;
    }

    private static Properties carregarConfiguracoes() throws IOException {
        Properties props = new Properties();
        File arquivoEnv = new File(CAMINHO_ENV);

        if (!arquivoEnv.exists()) {
            arquivoEnv = new File(".env");
            if (!arquivoEnv.exists()) {
                throw new IOException("Arquivo .env não encontrado.");
            }
        }

        try (FileInputStream entrada = new FileInputStream(arquivoEnv)) {
            props.load(entrada);
        }
        return props;
    }
}
