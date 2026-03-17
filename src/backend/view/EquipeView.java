package view;

import controller.EquipeController;
import model.Equipe;
import model.Usuario;
import java.util.List;

/**
 * Camada de visualização (CLI) para exibir dados das equipes.
 */
public class EquipeView {
    private final EquipeController equipeController;

    public EquipeView() {
        this.equipeController = new EquipeController();
    }

    /**
     * Exibe o relatório detalhado de todas as equipes e seus membros.
     */
    public void exibirRelatorioGeral() {
        System.out.println("\n========== RELATÓRIO DE EQUIPES (AXIS) ==========");
        List<Equipe> equipes = equipeController.listar();

        if (equipes.isEmpty()) {
            System.out.println("Nenhuma equipe cadastrada para exibir.");
            return;
        }

        for (Equipe equipe : equipes) {
            System.out.println("--------------------------------------------------");
            System.out.println("EQUIPE: " + equipe.getNomeEquipe().toUpperCase());
            System.out.println("DESCRIÇÃO: " + equipe.getDescricao());
            
            List<Usuario> membros = equipeController.listarMembros(equipe.getId());
            if (membros.isEmpty()) {
                System.out.println("MEMBROS: (Sem membros vinculados)");
            } else {
                System.out.print("MEMBROS: ");
                for (int i = 0; i < membros.size(); i++) {
                    System.out.print(membros.get(i).getNomeCompleto());
                    if (i < membros.size() - 1) System.out.print(", ");
                }
                System.out.println();
            }
        }
        System.out.println("--------------------------------------------------");
        System.out.println("TOTAL DE EQUIPES: " + equipes.size());
        System.out.println("==================================================\n");
    }
}