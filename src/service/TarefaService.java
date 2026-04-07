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
    /**
     * Adiciona uma nova tarefa.
     */
    public boolean salvar(Tarefa tarefa) {
        if (tarefa.getTitulo() == null || tarefa.getTitulo().trim().isEmpty()) {
            System.err.println("TÍTULO da tarefa é obrigatório.");
            return false;
        }
        return tarefaRepository.inserir(tarefa);
    }

    /**
     * Atualiza os dados de uma tarefa existente.
     */
    public boolean atualizar(Tarefa tarefa) {
        if (tarefa.getId() <= 0) {
            System.err.println("ID inválido para atualização.");
            return false;
        }
        return tarefaRepository.editar(tarefa);
    }

    /**
     * Remove uma tarefa pelo ID.
     */
    public boolean remover(int id) {
        if (id <= 0) {
            System.err.println("ID inválido para remoção.");
            return false;
        }
        return tarefaRepository.deletar(id);
    }
}
