package service;

import model.Equipe;
import model.Usuario;
import repository.EquipeRepository;
import java.util.List;

/**
 * Classe de serviço para gerenciar a lógica de negócio de Equipes.
 */
public class EquipeService {
    private final EquipeRepository equipeRepository;

    public EquipeService() {
        this.equipeRepository = new EquipeRepository();
    }

    /**
     * Obtém a lista de todas as equipes.
     * @return Lista de equipes
     */
    public List<Equipe> obterTodas() {
        return equipeRepository.listarTodos();
    }

    /**
     * Busca uma equipe pelo identificador.
     * @param id ID da equipe
     * @return Equipe encontrada ou null
     */
    public Equipe obterPorId(int id) {
        if (id <= 0) {
            System.err.println("ID inválido para busca.");
            return null;
        }
        return equipeRepository.buscarPorId(id);
    }

    /**
     * Obtém a lista de membros de uma equipe.
     * @param idEquipe ID da equipe
     * @return Lista de usuários
     */
    public List<Usuario> obterMembros(int idEquipe) {
        return equipeRepository.listarMembros(idEquipe);
    }
    /**
     * Adiciona uma nova equipe.
     * @param equipe Objeto Equipe a ser salvo
     * @return true se salvo com sucesso
     */
    public boolean salvar(Equipe equipe) {
        if (equipe.getNomeEquipe() == null || equipe.getNomeEquipe().trim().isEmpty()) {
            System.err.println("NOME da equipe é obrigatório.");
            return false;
        }
        return equipeRepository.inserir(equipe);
    }

    /**
     * Atualiza os dados de uma equipe existente.
     * @param equipe Objeto Equipe com dados atualizados
     * @return true se atualizado com sucesso
     */
    public boolean atualizar(Equipe equipe) {
        if (equipe.getId() <= 0) {
            System.err.println("ID inválido para atualização.");
            return false;
        }
        return equipeRepository.atualizar(equipe);
    }

    /**
     * Remove uma equipe pelo ID.
     * @param id ID da equipe a remover
     * @return true se removido com sucesso
     */
    public boolean remover(int id) {
        if (id <= 0) {
            System.err.println("ID inválido para remoção.");
            return false;
        }
        return equipeRepository.deletar(id);
    }
}