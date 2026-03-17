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
}