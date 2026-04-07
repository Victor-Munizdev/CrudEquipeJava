package main;

import controller.EquipeController;
import model.Equipe;
import java.util.Scanner;
import java.util.List;

/**
 * Ponto de entrada do sistema AXIO via Terminal (CLI).
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EquipeController controller = new EquipeController();

    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
                if (opcao != 0) pausar();
            } catch (NumberFormatException e) {
                System.out.println("\nERRO: Por favor, digite um número válido.");
            }
        }
        System.out.println("\nSaindo do sistema AXIO. Até mais!");
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("        AXIO - GESTÃO DE EQUIPES");
        System.out.println("=".repeat(40));
        System.out.println("1. Listar Todas as Equipes");
        System.out.println("2. Criar Nova Equipe");
        System.out.println("3. Editar Equipe Existente");
        System.out.println("4. Deletar Equipe");
        System.out.println("0. Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> listarEquipes();
            case 2 -> criarEquipe();
            case 3 -> editarEquipe();
            case 4 -> deletarEquipe();
            case 0 -> {}
            default -> System.out.println("\nOpção inválida!");
        }
    }

    private static void listarEquipes() {
        System.out.println("\n--- LISTA DE EQUIPES ---");
        List<Equipe> equipes = controller.listar();
        if (equipes.isEmpty()) {
            System.out.println("Nenhuma equipe cadastrada.");
        } else {
            for (Equipe e : equipes) {
                System.out.printf("ID: %d | Nome: %-20s | Descrição: %s\n", 
                    e.getId(), e.getNomeEquipe(), e.getDescricao());
            }
        }
    }

    private static void criarEquipe() {
        System.out.println("\n--- NOVA EQUIPE ---");
        System.out.print("Nome da Equipe: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String desc = scanner.nextLine();

        Equipe nova = new Equipe();
        nova.setNomeEquipe(nome);
        nova.setDescricao(desc);

        if (controller.criar(nova)) {
            System.out.println("Equipe criada com sucesso!");
        } else {
            System.out.println("Falha ao criar equipe.");
        }
    }

    private static void editarEquipe() {
        System.out.println("\n--- EDITAR EQUIPE ---");
        System.out.print("Digite o ID da equipe que deseja editar: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Equipe equipe = controller.buscar(id);
        if (equipe == null) {
            System.out.println("Equipe não encontrada!");
            return;
        }

        System.out.print("Novo Nome (atual: " + equipe.getNomeEquipe() + "): ");
        String nome = scanner.nextLine();
        System.out.print("Nova Descrição (atual: " + equipe.getDescricao() + "): ");
        String desc = scanner.nextLine();

        if (!nome.isEmpty()) equipe.setNomeEquipe(nome);
        if (!desc.isEmpty()) equipe.setDescricao(desc);

        if (controller.atualizar(equipe)) {
            System.out.println("Equipe atualizada com sucesso!");
        } else {
            System.out.println("Falha ao atualizar equipe.");
        }
    }

    private static void deletarEquipe() {
        System.out.println("\n--- DELETAR EQUIPE ---");
        System.out.print("Digite o ID da equipe para REMOVER: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("TEM CERTEZA? (S/N): ");
        String confirma = scanner.nextLine();

        if (confirma.equalsIgnoreCase("S")) {
            if (controller.deletar(id)) {
                System.out.println("Equipe removida com sucesso!");
            } else {
                System.out.println("Falha ao remover equipe.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
    private static void pausar() {
        System.out.println("\n\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}
