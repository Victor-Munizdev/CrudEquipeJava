package test;

import controller.EquipeController;
import model.Equipe;
import java.util.List;

/**
 * Classe de teste para demonstrar as operações de CRUD (Create, Read, Update, Delete)
 * na entidade Equipe.
 */
public class CrudTest {

    public static void main(String[] args) {
        EquipeController controller = new EquipeController();
        System.out.println("=== TESTE DE CRUD COMPLETO (EQUIPE) ===");

        // 1. CREATE
        System.out.println("\n>>> [CREATE] Criando uma nova equipe...");
        Equipe novaEquipe = new Equipe();
        novaEquipe.setNomeEquipe("Equipe de Inovação");
        novaEquipe.setDescricao("Focada em novas tecnologias e PD&I");
        
        boolean criado = controller.criar(novaEquipe);
        if (criado) {
            System.out.println("Equipe criada com sucesso!");
        } else {
            System.err.println("Falha ao criar equipe.");
        }

        // 2. READ (Listagem)
        System.out.println("\n>>> [READ] Listando equipes cadastradas:");
        List<Equipe> equipes = controller.listar();
        Equipe equipeParaEditar = null;
        for (Equipe e : equipes) {
            System.out.println("ID: " + e.getId() + " | Nome: " + e.getNomeEquipe());
            // Guardar a última criada para teste de update e delete
            if (e.getNomeEquipe().equals("Equipe de Inovação")) {
                equipeParaEditar = e;
            }
        }

        // 3. UPDATE
        if (equipeParaEditar != null) {
            System.out.println("\n>>> [UPDATE] Editando a equipe de ID: " + equipeParaEditar.getId());
            equipeParaEditar.setDescricao("Descrição atualizada via CrudTest!");
            boolean atualizado = controller.atualizar(equipeParaEditar);
            if (atualizado) {
                System.out.println("Equipe atualizada com sucesso!");
                Equipe conferida = controller.buscar(equipeParaEditar.getId());
                System.out.println("Nova descrição no banco: " + conferida.getDescricao());
            } else {
                System.err.println("Falha ao atualizar equipe.");
            }

            // 4. DELETE
            System.out.println("\n>>> [DELETE] Removendo a equipe de ID: " + equipeParaEditar.getId());
            boolean removido = controller.deletar(equipeParaEditar.getId());
            if (removido) {
                System.out.println("Equipe removida com sucesso!");
            } else {
                System.err.println("Falha ao remover equipe.");
            }
        } else {
            System.err.println("\nNão foi possível localizar a equipe criada para prosseguir com UPDATE/DELETE.");
        }

        System.out.println("\n=== FIM DO TESTE DE CRUD ===");
    }
}
