package controller;

import model.Equipe;
import model.Usuario;
import service.EquipeService;
import java.util.List;

/**
 * Controller responsável por mediar as ações relacionadas a Equipes.
 */
public class EquipeController {
    private final EquipeService equipeService;

    public EquipeController() {
        this.equipeService = new EquipeService();
    }

    /**
     * Retorna a lista de todas as equipes cadastradas.
     * @return List<Equipe>
     */
    public List<Equipe> listar() {
        return equipeService.obterTodas();
    }

    /**
     * Busca uma equipe pelo ID e retorna o objeto.
     * @param id ID da equipe
     * @return Equipe
     */
    public Equipe buscar(int id) {
        return equipeService.obterPorId(id);
    }

    /**
     * Retorna os membros de uma equipe específica.
     * @param idEquipe ID da equipe
     * @return List<Usuario>
     */
    public List<Usuario> listarMembros(int idEquipe) {
        return equipeService.obterMembros(idEquipe);
    }
}