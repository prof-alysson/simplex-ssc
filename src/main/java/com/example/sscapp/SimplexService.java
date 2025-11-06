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
            // Minimização: estrutura da função objetivo e restrições
            LinearObjectiveFunction objective = new LinearObjectiveFunction(
                new double[]{3, 2},
                GoalType.MIN
            );

            // Criar lista de restrições
            ListConstraints constraints = new ListConstraints();

            // 2x1 + x2 >= 4
            constraints.add(new Constraint(new double[]{2, 1}, ConsType.GE, 4));

            // x1 + 2x2 >= 3
            constraints.add(new Constraint(new double[]{1, 2}, ConsType.GE, 3));

            // Criar e resolver o problema
            LP lp = new LP(objective, constraints);
            SolutionType result = lp.resolve();

            // Exibir resultados
            displaySolution(lp, result);

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
            // Maximização: estrutura da função objetivo e restrições
            LinearObjectiveFunction objective = new LinearObjectiveFunction(
                new double[]{5, 4},
                GoalType.MAX
            );

            // Criar lista de restrições
            ListConstraints constraints = new ListConstraints();

            // x1 + x2 <= 5
            constraints.add(new Constraint(new double[]{1, 1}, ConsType.LE, 5));

            // 3x1 + x2 <= 12
            constraints.add(new Constraint(new double[]{3, 1}, ConsType.LE, 12));

            // Criar e resolver o problema
            LP lp = new LP(objective, constraints);
            SolutionType result = lp.resolve();

            // Exibir resultados
            displaySolution(lp, result);

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
            // Maximização: estrutura da função objetivo e restrições
            LinearObjectiveFunction objective = new LinearObjectiveFunction(
                new double[]{5, 4},
                GoalType.MAX
            );

            // Criar lista de restrições
            ListConstraints constraints = new ListConstraints();

            // x1 + x2 <= 10
            constraints.add(new Constraint(new double[]{1, 1}, ConsType.LE, 10));

            // 2x1 + x2 <= 16
            constraints.add(new Constraint(new double[]{2, 1}, ConsType.LE, 16));

            // Criar e resolver o problema
            LP lp = new LP(objective, constraints);
            SolutionType result = lp.resolve();

            // Exibir resultados
            displaySolution(lp, result);

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
    private void displaySolution(LP lp, SolutionType result) {
        System.out.println("STATUS: " + result);

        if (result == SolutionType.OPTIMAL) {
            Solution solution = lp.getSolution();
            System.out.println("\nValor otimo da funcao objetivo: " + solution.getOptimumValue());

            System.out.println("\nValores das variaveis:");
            for (Variable var : solution.getVariables()) {
                System.out.printf("  %s = %.4f%n", var.getName(), var.getValue());
            }

            System.out.println("\n========================================");
        } else if (result == SolutionType.UNBOUNDED) {
            System.out.println("\nProblema ILIMITADO (unbounded)!");
            System.out.println("A funcao objetivo pode crescer indefinidamente.");
            System.out.println("========================================");
        } else if (result == SolutionType.INFEASIBLE) {
            System.out.println("\nProblema INVIAVEL (infeasible)!");
            System.out.println("Nao existe solucao que satisfaca todas as restricoes.");
            System.out.println("========================================");
        } else {
            System.out.println("\nNao foi possivel encontrar uma solucao!");
            System.out.println("========================================");
        }
    }
}
