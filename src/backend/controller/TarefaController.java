package controller;

import model.Tarefa;
import service.TarefaService;
import java.util.List;

/**
 * Controller responsável por mediar as ações relacionadas a Tarefas.
 */
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController() {
        this.tarefaService = new TarefaService();
    }

    /**
     * Retorna a lista de todas as tarefas cadastradas.
     * @return List<Tarefa>
     */
    public List<Tarefa> listar() {
        return tarefaService.obterTodas();
    }

    /**
     * Busca uma tarefa pelo ID e retorna o objeto.
     * @param id ID da tarefa
     * @return Tarefa
     */
    public Tarefa buscar(int id) {
        return tarefaService.obterPorId(id);
    }
}
