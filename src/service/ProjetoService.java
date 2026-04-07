package service;

import model.Projeto;
import repository.ProjetoRepository;
import java.util.List;

/**
 * Classe de serviço para gerenciar a lógica de negócio de Projetos.
 */
public class ProjetoService {
    private final ProjetoRepository projetoRepository;

    public ProjetoService() {
        this.projetoRepository = new ProjetoRepository();
    }

    /**
     * Obtém a lista de todos os projetos.
     * @return Lista de projetos
     */
    public List<Projeto> obterTodos() {
        return projetoRepository.listarTodos();
    }

    /**
     * Busca um projeto pelo identificador.
     * @param id ID do projeto
     * @return Projeto encontrado ou null
     */
    public Projeto obterPorId(int id) {
        if (id <= 0) {
            System.err.println("ID inválido para busca.");
            return null;
        }
        return projetoRepository.buscarPorId(id);
    }
    /**
     * Adiciona um novo projeto.
     */
    public boolean salvar(Projeto projeto) {
        if (projeto.getNomeProjeto() == null || projeto.getNomeProjeto().trim().isEmpty()) {
            System.err.println("NOME do projeto é obrigatório.");
            return false;
        }
        return projetoRepository.inserir(projeto);
    }

    /**
     * Atualiza os dados de um projeto existente.
     */
    public boolean atualizar(Projeto projeto) {
        if (projeto.getId() <= 0) {
            System.err.println("ID inválido para atualização.");
            return false;
        }
        return projetoRepository.editar(projeto);
    }

    /**
     * Remove um projeto pelo ID.
     */
    public boolean remover(int id) {
        if (id <= 0) {
            System.err.println("ID inválido para remoção.");
            return false;
        }
        return projetoRepository.deletar(id);
    }
}
