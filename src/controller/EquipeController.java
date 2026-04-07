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
    /**
     * Cria uma nova equipe seguindo o fluxo controller -> service -> repository.
     * @param equipe Objeto Equipe com os dados iniciais (nome e descrição)
     * @return true se criado, false caso contrário
     */
    public boolean criar(Equipe equipe) {
        return equipeService.salvar(equipe);
    }

    /**
     * Atualiza uma equipe no banco de dados.
     * @param equipe Objeto Equipe atualizado
     * @return true se atualizado, false caso contrário
     */
    public boolean atualizar(Equipe equipe) {
        return equipeService.atualizar(equipe);
    }

    /**
     * Deleta uma equipe do banco de dados pelo seu ID.
     * @param id ID da equipe
     * @return true se deletado, false caso contrário
     */
    public boolean deletar(int id) {
        return equipeService.remover(id);
    }
}