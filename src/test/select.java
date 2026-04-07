package test;

import controller.EquipeController;
import controller.UsuarioController;
import controller.ProjetoController;
import controller.TarefaController;
import model.Equipe;
import model.Usuario;
import model.Projeto;
import java.util.List;

/**
 * Classe de teste para demonstrar as operações GET (Listar) utilizando a
 * arquitetura POO.
 */
public class select {

    public static void main(String[] args) {
        System.out.println("=== TESTE DE LISTAGEM POO (AXIO) ===");

        // Instanciando os controllers
        EquipeController equipeController = new EquipeController();
        UsuarioController usuarioController = new UsuarioController();
        ProjetoController projetoController = new ProjetoController();
        TarefaController tarefaController = new TarefaController();

        // 1. Testando Listagem de Equipes
        System.out.println("\n>>> Listando Equipes:");
        List<Equipe> equipes = equipeController.listar();
        if (equipes.isEmpty()) {
            System.out.println("Nenhuma equipe encontrada.");
        } else {
            for (Equipe e : equipes) {
                System.out.println("ID: " + e.getId() + " | Nome: " + e.getNomeEquipe());
            }
        }

        // 2. Testando Listagem de Usuários
        System.out.println("\n>>> Listando Usuários:");
        List<Usuario> usuarios = usuarioController.listar();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            for (Usuario u : usuarios) {
                System.out
                        .println("ID: " + u.getId() + " | Nome: " + u.getNomeCompleto() + " | Cargo: " + u.getCargo());
            }
        }

        // 3. Testando Listagem de Projetos
        System.out.println("\n>>> Listando Projetos:");
        List<Projeto> projetos = projetoController.listar();
        if (projetos.isEmpty()) {
            System.out.println("Nenhum projeto encontrado.");
        } else {
            for (Projeto p : projetos) {
                System.out
                        .println("ID: " + p.getId() + " | Nome: " + p.getNomeProjeto() + " | Status: " + p.getStatus());
            }
        }

        // 4. Testando Listagem de Tarefas
        System.out.println("\n>>> Listando Tarefas:");
        List<model.Tarefa> tarefas = tarefaController.listar();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            for (model.Tarefa t : tarefas) {
                System.out.println("ID: " + t.getId() + " | Título: " + t.getTitulo() + " | Status: " + t.getStatus());
            }
        }

        // 5. Testando Relatório Detalhado (MVC Completo)
        System.out.println("\n>>> Gerando Relatório Detalhado de Equipes:");
        view.EquipeView equipeView = new view.EquipeView();
        equipeView.exibirRelatorioGeral();

        System.out.println("\n=== Fim do Teste ===");
    }
}
