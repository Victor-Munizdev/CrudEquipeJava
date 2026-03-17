package service;

import model.Usuario;
import repository.UsuarioRepository;
import java.util.List;

/**
 * Classe de serviço para gerenciar a lógica de negócio de Usuários.
 */
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }

    /**
     * Obtém a lista de todos os usuários.
     * @return Lista de usuários
     */
    public List<Usuario> obterTodos() {
        return usuarioRepository.listarTodos();
    }

    /**
     * Busca um usuário pelo identificador.
     * @param id ID do usuário
     * @return Usuário encontrado ou null
     */
    public Usuario obterPorId(int id) {
        if (id <= 0) {
            System.err.println("ID inválido para busca.");
            return null;
        }
        return usuarioRepository.buscarPorId(id);
    }
}
