package com.example.sscapp;

import org.ssclab.pl.milp.*;
import org.springframework.stereotype.Service;

@Service
public class SimplexService {

    /**
     * Exemplo de problema de minimização:
     * Minimizar: 3x1 + 2x2
     * Sujeito a:
     *   2x1 + x2 >= 4
     *   x1 + 2x2 >= 3
     *   x1, x2 >= 0
     */
    public void solveMinimizationExample() {
        try {
            System.out.println("\n========================================");
            System.out.println("Resolvendo Problema de MINIMIZACAO");
            System.out.println("========================================");
            System.out.println("Minimizar: 3x1 + 2x2");
            System.out.println("Sujeito a:");
            System.out.println("  2x1 + x2 >= 4");
            System.out.println("  x1 + 2x2 >= 3");
            System.out.println("  x1, x2 >= 0");
            System.out.println("========================================\n");

            // Criar o problema de programação linear
            LP lp = new LP();

            // Definir a função objetivo (minimização)
            lp.setCObj(3, 2);

            // Adicionar restrições
            // 2x1 + x2 >= 4
            lp.addConstraint(new Constraint(ConsType.GE, 4), 2, 1);

            // x1 + 2x2 >= 3
            lp.addConstraint(new Constraint(ConsType.GE, 3), 1, 2);

            // Resolver o problema
            Solution solution = lp.solve();

            // Exibir resultados
            displaySolution(solution);

        } catch (Exception e) {
            System.err.println("Erro ao resolver o problema: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Exemplo de problema de maximização:
     * Maximizar: 5x1 + 4x2
     * Sujeito a:
     *   x1 + x2 <= 5
     *   3x1 + x2 <= 12
     *   x1, x2 >= 0
     */
    public void solveMaximizationExample() {
        try {
            System.out.println("\n========================================");
            System.out.println("Resolvendo Problema de MAXIMIZACAO");
            System.out.println("========================================");
            System.out.println("Maximizar: 5x1 + 4x2");
            System.out.println("Sujeito a:");
            System.out.println("  x1 + x2 <= 5");
            System.out.println("  3x1 + x2 <= 12");
            System.out.println("  x1, x2 >= 0");
            System.out.println("========================================\n");

            // Criar o problema de programação linear
            LP lp = new LP();

            // Definir a função objetivo (maximização)
            lp.setObjFunctionMax();
            lp.setCObj(5, 4);

            // Adicionar restrições
            // x1 + x2 <= 5
            lp.addConstraint(new Constraint(ConsType.LE, 5), 1, 1);

            // 3x1 + x2 <= 12
            lp.addConstraint(new Constraint(ConsType.LE, 12), 3, 1);

            // Resolver o problema
            Solution solution = lp.solve();

            // Exibir resultados
            displaySolution(solution);

        } catch (Exception e) {
            System.err.println("Erro ao resolver o problema: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Exemplo da Aula 11 - Problema de Otimização de Rotas
     * Este é o exemplo usado na apresentação (apresentacao.md)
     *
     * Maximizar: 5x1 + 4x2
     * Sujeito a:
     *   x1 + x2 <= 10  (capacidade total)
     *   2x1 + x2 <= 16 (limite de recurso)
     *   x1, x2 >= 0
     *
     * Solução esperada: x1 = 6, x2 = 4, Z = 46
     */
    public void solveApresentacaoExample() {
        try {
            System.out.println("\n========================================");
            System.out.println("EXEMPLO DA AULA 11 - APRESENTACAO");
            System.out.println("Problema de Otimizacao de Rotas");
            System.out.println("========================================");
            System.out.println("Maximizar: Z = 5x1 + 4x2");
            System.out.println("Sujeito a:");
            System.out.println("  x1 + x2 <= 10  (capacidade total)");
            System.out.println("  2x1 + x2 <= 16 (limite de recurso)");
            System.out.println("  x1, x2 >= 0");
            System.out.println("========================================\n");

            // Criar o problema de programação linear
            LP lp = new LP();

            // Definir a função objetivo (maximização)
            lp.setObjFunctionMax();
            lp.setCObj(5, 4);

            // Adicionar restrições
            // x1 + x2 <= 10
            lp.addConstraint(new Constraint(ConsType.LE, 10), 1, 1);

            // 2x1 + x2 <= 16
            lp.addConstraint(new Constraint(ConsType.LE, 16), 2, 1);

            // Resolver o problema
            Solution solution = lp.solve();

            // Exibir resultados
            displaySolution(solution);

            // Explicação adicional
            System.out.println("INTERPRETACAO:");
            System.out.println("Este resultado corresponde exatamente a solucao");
            System.out.println("obtida manualmente com o Simplex Tableau na aula!");
            System.out.println("========================================\n");

        } catch (Exception e) {
            System.err.println("Erro ao resolver o problema: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Exibe a solução encontrada
     */
    private void displaySolution(Solution solution) {
        if (solution != null) {
            System.out.println("STATUS: " + solution.getSolutionType());
            System.out.println("\nValor otimo da funcao objetivo: " + solution.getOptimalValue());

            double[] values = solution.getOptimalSolution();
            System.out.println("\nValores das variaveis:");
            for (int i = 0; i < values.length; i++) {
                System.out.printf("  x%d = %.4f%n", (i + 1), values[i]);
            }

            System.out.println("\n========================================");
        } else {
            System.out.println("Nao foi possivel encontrar uma solucao!");
        }
    }
}
