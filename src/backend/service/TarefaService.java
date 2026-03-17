package service;

import model.Tarefa;
import repository.TarefaRepository;
import java.util.List;

/**
 * Classe de serviço para gerenciar a lógica de negócio de Tarefas.
 */
public class TarefaService {
    private final TarefaRepository tarefaRepository;

    public TarefaService() {
        this.tarefaRepository = new TarefaRepository();
    }

    /**
     * Obtém a lista de todas as tarefas.
     * @return Lista de tarefas
     */
    public List<Tarefa> obterTodas() {
        return tarefaRepository.listarTodas();
    }

    /**
     * Busca uma tarefa pelo identificador.
     * @param id ID da tarefa
     * @return Tarefa encontrada ou null
     */
    public Tarefa obterPorId(int id) {
        if (id <= 0) {
            System.err.println("ID inválido para busca.");
            return null;
        }
        return tarefaRepository.buscarPorId(id);
    }
}
