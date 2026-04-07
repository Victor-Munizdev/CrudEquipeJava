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
    /**
     * Adiciona um novo usuário.
     */
    public boolean salvar(Usuario usuario) {
        if (usuario.getNomeCompleto() == null || usuario.getNomeCompleto().trim().isEmpty()) {
            System.err.println("NOME do usuário é obrigatório.");
            return false;
        }
        return usuarioRepository.inserir(usuario);
    }

    /**
     * Atualiza os dados de um usuário existente.
     */
    public boolean atualizar(Usuario usuario) {
        if (usuario.getId() <= 0) {
            System.err.println("ID inválido para atualização.");
            return false;
        }
        return usuarioRepository.editar(usuario);
    }

    /**
     * Remove um usuário pelo ID.
     */
    public boolean remover(int id) {
        if (id <= 0) {
            System.err.println("ID inválido para remoção.");
            return false;
        }
        return usuarioRepository.deletar(id);
    }
}
