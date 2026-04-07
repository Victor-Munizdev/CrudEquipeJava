package main;

import controller.EquipeController;
import controller.UsuarioController;
import controller.ProjetoController;
import controller.TarefaController;
import model.Equipe;
import model.Usuario;
import model.Projeto;
import model.Tarefa;
import model.Cargo;
import java.sql.Date;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EquipeController equipeController = new EquipeController();
    private static final UsuarioController usuarioController = new UsuarioController();
    private static final ProjetoController projetoController = new ProjetoController();
    private static final TarefaController tarefaController = new TarefaController();

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n" + "=".repeat(45));
            System.out.println("          AXIO - SISTEMA DE GESTÃO");
            System.out.println("=".repeat(45));
            System.out.println("1. [CADASTRO] Usuários (Req. 1)");
            System.out.println("2. [CADASTRO] Projetos (Req. 2)");
            System.out.println("3. [CADASTRO] Equipes  (Req. 3)");
            System.out.println("4. [GESTÃO]   Tarefas do Projeto");
            System.out.println("5. [GESTÃO]   Relatórios de Acompanhamento");
            System.out.println("0. SAIR");
            System.out.print("\nEscolha um módulo: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> menuUsuarios();
                    case 2 -> menuProjetos();
                    case 3 -> menuEquipes();
                    case 4 -> menuTarefas();
                    case 5 -> gerarRelatorio();
                    case 0 -> {}
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida.");
            }
        }
        System.out.println("\nSaindo do sistema AXIO. Até mais!");
        scanner.close();
    }

    private static void menuUsuarios() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- [1] CADASTRO DE USUÁRIOS ---");
            System.out.println("1. Listar Usuários");
            System.out.println("2. Criar Novo Usuário");
            System.out.println("0. VOLTAR");
            System.out.print("Escolha: ");
            op = Integer.parseInt(scanner.nextLine());

            if (op == 2) {
                Usuario u = new Usuario();
                System.out.print("Nome Completo: "); u.setNomeCompleto(scanner.nextLine());
                System.out.print("CPF: "); u.setCpf(scanner.nextLine());
                System.out.print("Email: "); u.setEmail(scanner.nextLine());
                System.out.println("Cargo: [1] Admin | [2] Gerente | [3] Colaborador");
                int cargoOp = Integer.parseInt(scanner.nextLine());
                u.setCargo(switch (cargoOp) {
                    case 1 -> Cargo.ADMINISTRADOR;
                    case 2 -> Cargo.GERENTE;
                    default -> Cargo.COLABORADOR;
                });
                System.out.print("Login: "); u.setLogin(scanner.nextLine());
                System.out.print("Senha: "); u.setSenha(scanner.nextLine());
                if (usuarioController.criar(u)) System.out.println("Usuário cadastrado!");
                pausar();
            } else if (op == 1) {
                usuarioController.listar().forEach(u -> System.out.println("ID: " + u.getId() + " | " + u.getNomeCompleto() + " (" + u.getCargo().getDescricao() + ")"));
                pausar();
            }
        }
    }

    private static void menuProjetos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- [2] CADASTRO DE PROJETOS ---");
            System.out.println("1. Listar Projetos");
            System.out.println("2. Criar Novo Projeto");
            System.out.println("0. VOLTAR");
            System.out.print("Escolha: ");
            op = Integer.parseInt(scanner.nextLine());

            if (op == 2) {
                Projeto p = new Projeto();
                System.out.print("Nome: "); p.setNomeProjeto(scanner.nextLine());
                System.out.print("Início (YYYY-MM-DD): "); p.setDataInicio(Date.valueOf(scanner.nextLine()));
                System.out.print("Término (YYYY-MM-DD): "); p.setDataTerminoPrevista(Date.valueOf(scanner.nextLine()));
                System.out.println("Status: [1] Planejado | [2] Em Andamento | [3] Concluído | [4] Cancelado");
                int st = Integer.parseInt(scanner.nextLine());
                p.setStatus(switch(st) {
                    case 1 -> "planejado";
                    case 2 -> "em andamento";
                    case 3 -> "concluído";
                    case 4 -> "cancelado";
                    default -> "planejado";
                });
                System.out.print("ID Gerente Responsável: "); p.setIdGerenteResponsavel(Integer.parseInt(scanner.nextLine()));
                if (projetoController.criar(p)) System.out.println("Projeto cadastrado!");
                pausar();
            } else if (op == 1) {
                projetoController.listar().forEach(p -> System.out.println("ID: " + p.getId() + " | Nome: " + p.getNomeProjeto() + " | Status: " + p.getStatus()));
                pausar();
            }
        }
    }

    private static void menuEquipes() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- [3] CADASTRO DE EQUIPES ---");
            System.out.println("1. Listar Equipes");
            System.out.println("2. Criar Nova Equipe");
            System.out.println("3. Vincular Usuário à Equipe (Req. 3.e)");
            System.out.println("0. VOLTAR");
            System.out.print("Escolha: ");
            op = Integer.parseInt(scanner.nextLine());

            if (op == 2) {
                Equipe e = new Equipe();
                System.out.print("Nome: "); e.setNomeEquipe(scanner.nextLine());
                System.out.print("Descrição: "); e.setDescricao(scanner.nextLine());
                if (equipeController.criar(e)) System.out.println("Equipe criada!");
                pausar();
            } else if (op == 3) {
                System.out.print("ID do Usuário: "); int idU = Integer.parseInt(scanner.nextLine());
                System.out.print("ID da Equipe: "); int idE = Integer.parseInt(scanner.nextLine());
                if (equipeController.adicionarMembro(idE, idU)) System.out.println("Vínculo realizado!");
                pausar();
            } else if (op == 1) {
                equipeController.listar().forEach(e -> System.out.println("ID: " + e.getId() + " | " + e.getNomeEquipe()));
                pausar();
            }
        }
    }

    private static void menuTarefas() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- [4] GESTÃO DE TAREFAS ---");
            System.out.println("1. Listar Tarefas");
            System.out.println("2. Criar Nova Tarefa");
            System.out.println("0. VOLTAR");
            System.out.print("Escolha: ");
            op = Integer.parseInt(scanner.nextLine());

            if (op == 2) {
                Tarefa t = new Tarefa();
                System.out.print("Título: "); t.setTitulo(scanner.nextLine());
                System.out.print("Início (YYYY-MM-DD): "); t.setDataInicio(Date.valueOf(scanner.nextLine()));
                System.out.print("Término (YYYY-MM-DD): "); t.setDataTermino(Date.valueOf(scanner.nextLine()));
                System.out.println("Status: [1] Pendente | [2] Em Execução | [3] Finalizada");
                int st = Integer.parseInt(scanner.nextLine());
                t.setStatus(switch(st) {
                    case 1 -> "pendente";
                    case 2 -> "em execução";
                    default -> "finalizada";
                });
                System.out.print("ID Projeto: "); t.setIdProjeto(Integer.parseInt(scanner.nextLine()));
                System.out.print("ID Usuário Responsável: "); t.setIdUsuarioResponsavel(Integer.parseInt(scanner.nextLine()));
                if (tarefaController.criar(t)) System.out.println("Tarefa criada!");
                pausar();
            } else if (op == 1) {
                tarefaController.listar().forEach(t -> System.out.println("ID: " + t.getId() + " | " + t.getTitulo() + " | Status: " + t.getStatus()));
                pausar();
            }
        }
    }

    private static void gerarRelatorio() {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("      RELATÓRIO GERAL DE ACOMPANHAMENTO");
        System.out.println("=".repeat(45));
        System.out.println("Total de Projetos: " + projetoController.listar().size());
        System.out.println("Total de Usuários: " + usuarioController.listar().size());
        System.out.println("Total de Equipes : " + equipeController.listar().size());
        System.out.println("Total de Tarefas : " + tarefaController.listar().size());
        System.out.println("=".repeat(45));
        pausar();
    }

    private static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}
