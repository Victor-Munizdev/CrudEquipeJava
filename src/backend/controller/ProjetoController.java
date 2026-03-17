package controller;

import model.Projeto;
import service.ProjetoService;
import java.util.List;

/**
 * Controller responsável por mediar as ações relacionadas a Projetos.
 */
public class ProjetoController {
    private final ProjetoService projetoService;

    public ProjetoController() {
        this.projetoService = new ProjetoService();
    }

    /**
     * Retorna a lista de todos os projetos cadastrados.
     * @return List<Projeto>
     */
    public List<Projeto> listar() {
        return projetoService.obterTodos();
    }

    /**
     * Busca um projeto pelo ID e retorna o objeto.
     * @param id ID do projeto
     * @return Projeto
     */
    public Projeto buscar(int id) {
        return projetoService.obterPorId(id);
    }
}
