package controller;

import model.Usuario;
import service.UsuarioService;
import java.util.List;

/**
 * Controller responsável por mediar as ações relacionadas a Usuários.
 */
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    /**
     * Retorna a lista de todos os usuários cadastrados.
     * @return List<Usuario>
     */
    public List<Usuario> listar() {
        return usuarioService.obterTodos();
    }

    /**
     * Busca um usuário pelo ID e retorna o objeto.
     * @param id ID do usuário
     * @return Usuario
     */
    public Usuario buscar(int id) {
        return usuarioService.obterPorId(id);
    }
    /**
     * Cria um novo usuário.
     */
    public boolean criar(Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    /**
     * Atualiza um usuário.
     */
    public boolean atualizar(Usuario usuario) {
        return usuarioService.atualizar(usuario);
    }

    /**
     * Deleta um usuário.
     */
    public boolean deletar(int id) {
        return usuarioService.remover(id);
    }
}
