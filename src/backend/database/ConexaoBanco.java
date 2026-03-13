package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

/**
 * Classe responsável pela conexão com o banco de dados utilizando POO.
 * Segue o padrão Singleton para garantir uma única conexão.
 */
public class ConexaoBanco {
    // Variáveis em português seguindo POO
    private static Connection conexao = null;
    private static final String CAMINHO_ENV = ".env";

    // Construtor privado para evitar instanciar a classe (Padrão Singleton)
    private ConexaoBanco() {
    }

    /**
     * Obtém a conexão ativa com o banco de dados MySQL (XAMPP).
     * 
     * @return Connection objeto de conexão
     */
    public static Connection getConexao() {
        if (conexao == null) {
            try {
                Properties configuracoes = carregarConfiguracoes();

                String host = configuracoes.getProperty("DB_HOST");
                String porta = configuracoes.getProperty("DB_PORT");
                String nomeBanco = configuracoes.getProperty("DB_NOME");
                String usuario = configuracoes.getProperty("DB_USUARIO");
                String senha = configuracoes.getProperty("DB_SENHA");

                // URL de conexão formatada
                String url = String.format("jdbc:mysql://%s:%s/%s", host, porta, nomeBanco);

                // Carrega o driver JDBC do MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                conexao = DriverManager.getConnection(url, usuario, senha);
                System.out.println("SUCESSO: Conexão com o banco '" + nomeBanco + "' estabelecida!");

            } catch (ClassNotFoundException e) {
                System.err.println("ERRO: Driver JDBC não encontrado! Adicione o MySQL Connector ao projeto.");
            } catch (SQLException e) {
                System.err.println("ERRO: Falha ao conectar ao MySQL (XAMPP): " + e.getMessage());
            } catch (IOException e) {
                System.err.println("ERRO: Não foi possível ler o arquivo de configuração .env: " + e.getMessage());
            }
        }
        return conexao;
    }

    /**
     * Lê as configurações do arquivo .env
     */
    private static Properties carregarConfiguracoes() throws IOException {
        Properties props = new Properties();
        File arquivoEnv = new File(CAMINHO_ENV);

        if (!arquivoEnv.exists()) {
            throw new IOException("Arquivo .env não encontrado em: " + arquivoEnv.getAbsolutePath());
        }

        try (FileInputStream entrada = new FileInputStream(arquivoEnv)) {
            props.load(entrada);
        }
        return props;
    }

    /**
     * Fecha a conexão com o banco de dados de forma segura.
     */
    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
                conexao = null;
                System.out.println("INFO: Conexão com o banco fechada.");
            } catch (SQLException e) {
                System.err.println("ERRO: Falha ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
