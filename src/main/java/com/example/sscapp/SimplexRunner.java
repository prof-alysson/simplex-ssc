package com.example.sscapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SimplexRunner implements CommandLineRunner {

    private final SimplexService simplexService;

    public SimplexRunner(SimplexService simplexService) {
        this.simplexService = simplexService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================================");
        System.out.println("  SSC - Software for Simplex Calculation  ");
        System.out.println("===========================================");
        System.out.println("   Aula 11 - Otimizacao de Sistemas");
        System.out.println("   Prof. Alysson M. Bruno - Unitins");
        System.out.println("===========================================");
        System.out.println();

        boolean running = true;

        while (running) {
            System.out.println("\n=========== MENU DE OPCOES ===========");
            System.out.println("1 - [AULA 11] Exemplo da Apresentacao (Rotas)");
            System.out.println("2 - Exemplo de Minimizacao");
            System.out.println("3 - Exemplo de Maximizacao");
            System.out.println("4 - Resolver problema personalizado");
            System.out.println("0 - Sair");
            System.out.println("======================================");
            System.out.print("\nOpcao: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    simplexService.solveApresentacaoExample();
                    break;
                case "2":
                    simplexService.solveMinimizationExample();
                    break;
                case "3":
                    simplexService.solveMaximizationExample();
                    break;
                case "4":
                    System.out.println("\nFuncionalidade em desenvolvimento...");
                    break;
                case "0":
                    running = false;
                    System.out.println("\n=====================================");
                    System.out.println("  Encerrando aplicacao...");
                    System.out.println("  Obrigado por usar o SSC!");
                    System.out.println("=====================================\n");
                    break;
                default:
                    System.out.println("\n[ERRO] Opcao invalida! Tente novamente.");
            }
        }

        scanner.close();
    }
}
