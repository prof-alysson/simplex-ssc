---
marp: true
theme: default
paginate: true
backgroundColor: #fff
header: 'Otimização de Sistemas - Aula 11'
footer: 'Prof. Alysson M. Bruno | Unitins | 2025.2'
---

<!-- _class: lead -->
# Programação Linear e Método Simplex
## Aplicação Prática com SSC/Java

**Aula 11 - Otimização de Sistemas**
Professor: Alysson M. Bruno

---

## Roteiro da Aula

1. **Revisão**: Programação Linear
2. **O Método Simplex**
3. **Simplex Tableau**
4. **Problema Prático**: Distribuição de Rotas
5. **Resolução Manual** (Simplex Tableau)
6. **Implementação em Java** (Biblioteca SSC)
7. **Demonstração Prática**

---

<!-- _class: lead -->
# Parte 1
## Revisão de Programação Linear

---

## O que é Programação Linear?

**Programação Linear (PL)** é uma técnica matemática para otimização de recursos limitados.

### Componentes de um Problema de PL:

1. **Função Objetivo**: O que queremos maximizar ou minimizar
   - Ex: Maximizar lucro, Minimizar custo

2. **Variáveis de Decisão**: O que podemos controlar
   - Ex: x₁, x₂, x₃ (quantidades, rotas, recursos)

3. **Restrições**: Limitações do problema
   - Ex: Capacidade, tempo, orçamento

---

## Forma Geral de um Problema de PL

### Maximização:
```
Maximizar:   Z = c₁x₁ + c₂x₂ + ... + cₙxₙ

Sujeito a:   a₁₁x₁ + a₁₂x₂ + ... + a₁ₙxₙ ≤ b₁
             a₂₁x₁ + a₂₂x₂ + ... + a₂ₙxₙ ≤ b₂
             ...
             aₘ₁x₁ + aₘ₂x₂ + ... + aₘₙxₙ ≤ bₘ

             x₁, x₂, ..., xₙ ≥ 0
```

**Importante**: Todas as relações são lineares!

---

## Exemplo Simples

Uma fábrica produz dois produtos: A e B

| Produto | Tempo (h) | Lucro (R$) |
|---------|-----------|------------|
| A       | 2         | 30         |
| B       | 3         | 50         |

**Tempo disponível**: 12 horas/dia

**Problema**: Quanto produzir de cada produto para maximizar o lucro?

---

## Modelagem do Exemplo

**Variáveis de decisão:**
- x₁ = quantidade do produto A
- x₂ = quantidade do produto B

**Função objetivo:**
```
Maximizar: Z = 30x₁ + 50x₂
```

**Restrições:**
```
2x₁ + 3x₂ ≤ 12  (tempo disponível)
x₁, x₂ ≥ 0      (não-negatividade)
```

---

<!-- _class: lead -->
# Parte 2
## O Método Simplex

---

## Método Simplex

Desenvolvido por **George Dantzig** em 1947, é o algoritmo mais utilizado para resolver problemas de PL.

### Características:

- Método iterativo (passo a passo)
- Percorre vértices da região viável
- Sempre melhora ou mantém o valor da função objetivo
- Garante encontrar a solução ótima (se existir)

### Ideia Principal:
> Partir de uma solução viável e melhorá-la iterativamente até não ser mais possível melhorar

---

## Conceitos Importantes

### 1. Forma Padrão
Todas as restrições devem ser igualdades (=) e variáveis não-negativas

### 2. Variáveis de Folga
Transformam desigualdades (≤) em igualdades (=)
```
2x₁ + 3x₂ ≤ 12  →  2x₁ + 3x₂ + s₁ = 12, s₁ ≥ 0
```

### 3. Solução Básica
Solução nos vértices da região viável

### 4. Solução Ótima
Melhor valor possível da função objetivo

---

## Simplex Tableau

É uma **tabela** que organiza os cálculos do método Simplex de forma sistemática.

### Estrutura do Tableau:

|       | x₁  | x₂  | s₁  | s₂  | RHS |
|-------|-----|-----|-----|-----|-----|
| Z     |     |     |     |     |     |
| s₁    |     |     |     |     |     |
| s₂    |     |     |     |     |     |

- **Z**: Linha da função objetivo
- **RHS**: Right Hand Side (lado direito das restrições)
- **s₁, s₂**: Variáveis de folga (slack variables)

---

<!-- _class: lead -->
# Parte 3
## Problema de Distribuição de Rotas

---

## Problema: Entrega de Mercadorias

Uma empresa de logística precisa entregar mercadorias para duas cidades (A e B) usando dois tipos de caminhões:

| Caminhão | Capacidade (ton) | Custo/viagem (R$) |
|----------|------------------|-------------------|
| Pequeno  | 3                | 200               |
| Grande   | 5                | 300               |

### Demandas:
- **Cidade A**: 15 toneladas
- **Cidade B**: 20 toneladas

---

### Restrições:
- Máximo de 8 caminhões pequenos disponíveis
- Máximo de 6 caminhões grandes disponíveis

---

## Versão Simplificada do Problema

Para facilitar o entendimento, vamos resolver uma **versão simplificada**:

**Objetivo**: Minimizar o custo de transporte para atender a demanda total de **35 toneladas**

**Variáveis:**
- x₁ = número de caminhões pequenos
- x₂ = número de caminhões grandes

---

## Modelagem Matemática

```
Minimizar:   Z = 200x₁ + 300x₂

Sujeito a:   3x₁ + 5x₂ ≥ 35  (atender demanda)
             x₁ ≤ 8           (limite de caminhões pequenos)
             x₂ ≤ 6           (limite de caminhões grandes)
             x₁, x₂ ≥ 0       (não-negatividade)
```

### Nota:
Para usar o Simplex padrão, vamos converter para a forma padrão com variáveis de folga e excesso.

---

## Conversão para Forma Padrão

### Problema Original:
```
Minimizar:   Z = 200x₁ + 300x₂
Sujeito a:   3x₁ + 5x₂ ≥ 35
             x₁ ≤ 8
             x₂ ≤ 6
```

### Forma Padrão (para Simplex):
```
Minimizar:   Z = 200x₁ + 300x₂

             3x₁ + 5x₂ - s₁ = 35  (s₁: variável de excesso)
             x₁ + s₂ = 8          (s₂: variável de folga)
             x₂ + s₃ = 6          (s₃: variável de folga)

             x₁, x₂, s₁, s₂, s₃ ≥ 0
```

---

<!-- _class: lead -->
# Parte 4
## Resolução com Simplex Tableau

---

## Tableau Inicial

Para **minimização**, podemos:
1. Converter para maximização: `Max (-Z) = -200x₁ - 300x₂`
2. Ou trabalhar diretamente com minimização

Vamos usar a abordagem de **maximização de -Z**:

| Base | x₁   | x₂   | s₁  | s₂  | s₃  | RHS |
|------|------|------|-----|-----|-----|-----|
| -Z   | 200  | 300  | 0   | 0   | 0   | 0   |
| s₁   | -3   | -5   | -1  | 0   | 0   | -35 |
| s₂   | 1    | 0    | 0   | 1   | 0   | 8   |
| s₃   | 0    | 1    | 0   | 0   | 1   | 6   |

---

## Interpretação do Tableau

### Linha -Z (Função Objetivo):
- Coeficientes positivos indicam que aumentar essas variáveis **piora** a solução
- Queremos todos os coeficientes ≤ 0 para minimização

### Coluna RHS (Right Hand Side):
- Valores negativos indicam que a restrição não está sendo atendida
- Precisamos de uma **solução básica viável inicial**

### Observação:
Neste problema, precisamos usar **Método das Duas Fases** ou **Big M** devido à restrição ≥

---

## Simplificação Didática

Para fins didáticos, vamos resolver uma **versão ainda mais simples** do problema:

```
Maximizar:   Z = 5x₁ + 4x₂

Sujeito a:   x₁ + x₂ ≤ 10  (capacidade total)
             2x₁ + x₂ ≤ 16 (limite de recurso)
             x₁, x₂ ≥ 0
```

**Contexto**: Maximizar eficiência de rotas com capacidades limitadas

---

## Tableau Inicial - Problema Simplificado

### Forma Padrão:
```
Max Z = 5x₁ + 4x₂
        x₁ + x₂ + s₁ = 10
        2x₁ + x₂ + s₂ = 16
```

---

### Tableau Inicial:

| Base | x₁  | x₂  | s₁  | s₂  | RHS |
|------|-----|-----|-----|-----|-----|
| Z    | -5  | -4  | 0   | 0   | 0   |
| s₁   | 1   | 1   | 1   | 0   | 10  |
| s₂   | 2   | 1   | 0   | 1   | 16  |

**Solução inicial**: x₁=0, x₂=0, s₁=10, s₂=16, Z=0

---

## Iteração 1: Escolha da Variável de Entrada

### Regra: Coluna com o coeficiente mais negativo na linha Z
→ x₁ (coeficiente -5)

### Teste da Razão (escolha da variável de saída):
- Linha s₁: 10/1 = **10**
- Linha s₂: 16/2 = **8** ← menor razão positiva

---

**Elemento pivô**: 2 (interseção de x₁ e s₂)

### Tableau após pivoteamento:

| Base | x₁  | x₂  | s₁  | s₂  | RHS |
|------|-----|-----|-----|-----|-----|
| Z    | 0   | -1.5| 0   | 2.5 | 40  |
| s₁   | 0   | 0.5 | 1   |-0.5 | 2   |
| x₁   | 1   | 0.5 | 0   | 0.5 | 8   |

---

## Iteração 2: Continuação

### Variável de entrada: x₂ (coeficiente -1.5)

### Teste da Razão:
- Linha s₁: 2/0.5 = **4** ← menor razão
- Linha x₁: 8/0.5 = 16

**Elemento pivô**: 0.5 (interseção de x₂ e s₁)

---

### Tableau Final:

| Base | x₁  | x₂  | s₁  | s₂  | RHS |
|------|-----|-----|-----|-----|-----|
| Z    | 0   | 0   | 3   | 1   | 46  |
| x₂   | 0   | 1   | 2   | -1  | 4   |
| x₁   | 1   | 0   | -1  | 1   | 6   |

---

## Solução Ótima

### Tableau Final (todos coeficientes em Z são ≥ 0):

| Base | x₁  | x₂  | s₁  | s₂  | RHS |
|------|-----|-----|-----|-----|-----|
| Z    | 0   | 0   | 3   | 1   | **46** |
| x₂   | 0   | 1   | 2   | -1  | 4   |
| x₁   | 1   | 0   | -1  | 1   | 6   |

### Interpretação:
- **x₁ = 6** (6 unidades da variável 1)
- **x₂ = 4** (4 unidades da variável 2)
- **Z = 46** (valor ótimo da função objetivo)
- s₁ = 0, s₂ = 0 (ambas as restrições são ativas)

---

<!-- _class: lead -->
# Parte 5
## Implementação em Java com SSC

---

## Biblioteca SSC (Software for Simplex Calculation)

### O que é?
Biblioteca Java open-source para resolver problemas de Programação Linear

### Características:
- Implementa o Método Simplex
- Suporta LP (Linear Programming) e MILP (Mixed Integer LP)
- Fácil de usar e bem documentada
- Disponível no Maven Repository

### Site oficial:
https://www.ssclab.org/

---

## Dependência Maven

Adicione no `pom.xml`:

```xml
<!-- https://mvnrepository.com/artifact/org.ssclab/SSC-LP -->
<dependency>
    <groupId>org.ssclab</groupId>
    <artifactId>SSC-LP</artifactId>
    <version>4.7.8</version>
</dependency>
```

### Requisitos:
- JDK 17 ou superior (recomendado JDK 23)
- Maven 3.6+

---

## Estrutura Básica do Código

```java
import org.ssclab.pl.milp.*;

public class SimplexExample {
    public static void main(String[] args) throws Exception {
        // 1. Criar função objetivo
        LinearObjectiveFunction objective = new LinearObjectiveFunction(
            new double[]{5, 4},    // coeficientes
            GoalType.MAX           // MAX ou MIN
        );

        // 2. Criar lista de restrições
        ListConstraints constraints = new ListConstraints();
        constraints.add(new Constraint(new double[]{1, 1}, ConsType.LE, 10));
        constraints.add(new Constraint(new double[]{2, 1}, ConsType.LE, 16));

        // 3. Criar e resolver o problema
        LP lp = new LP(objective, constraints);
        SolutionType result = lp.resolve();

        // 4. Obter resultados
        if (result == SolutionType.OPTIMAL) {
            Solution solution = lp.getSolution();
            System.out.println("Valor ótimo: " + solution.getOptimumValue());
        }
    }
}
```

---

## Exemplo Completo: Problema de Rotas

```java
import org.ssclab.pl.milp.*;

public class RotasOptimization {

    public void resolverProblemaRotas() throws Exception {
        System.out.println("Resolvendo Problema de Otimização de Rotas");
        System.out.println("Maximizar: 5x1 + 4x2");
        System.out.println("Sujeito a:");
        System.out.println("  x1 + x2 <= 10");
        System.out.println("  2x1 + x2 <= 16");

        // Criar função objetivo (maximização)
        LinearObjectiveFunction objective = new LinearObjectiveFunction(
            new double[]{5, 4},   // coeficientes: 5x1 + 4x2
            GoalType.MAX
        );

        // Criar lista de restrições
        ListConstraints constraints = new ListConstraints();
```

---

## Exemplo Completo: Adicionando Restrições

```java
        // Adicionar primeira restrição: x1 + x2 <= 10
        constraints.add(new Constraint(
            new double[]{1, 1},    // coeficientes: 1x1 + 1x2
            ConsType.LE,           // <=
            10                     // lado direito
        ));

        // Adicionar segunda restrição: 2x1 + x2 <= 16
        constraints.add(new Constraint(
            new double[]{2, 1},    // coeficientes: 2x1 + 1x2
            ConsType.LE,           // <=
            16                     // lado direito
        ));

        // Criar e resolver o problema
        LP lp = new LP(objective, constraints);
        SolutionType result = lp.resolve();

        // Exibir resultados
        exibirSolucao(lp, result);
    }
```

---

## Exemplo Completo: Exibindo Resultados

```java
    private void exibirSolucao(LP lp, SolutionType result) {
        System.out.println("\n========= SOLUÇÃO =========");
        System.out.println("Status: " + result);

        if (result == SolutionType.OPTIMAL) {
            Solution solution = lp.getSolution();

            // Valor ótimo da função objetivo
            double valorOtimo = solution.getOptimumValue();
            System.out.printf("Valor Ótimo Z: %.2f\n", valorOtimo);

            // Valores das variáveis
            System.out.println("\nValores das variáveis:");
            for (Variable var : solution.getVariables()) {
                System.out.printf("  %s = %.2f\n", var.getName(), var.getValue());
            }

            System.out.println("==============================\n");
        } else {
            System.out.println("Problema não tem solução ótima!");
        }
    }
}
```

---

## Tipos de Restrições no SSC

### ConsType (Tipo de Restrição):

```java
// Menor ou igual
ConsType.LE  →  ≤

// Maior ou igual
ConsType.GE  →  ≥

// Igual
ConsType.EQ  →  =
```

### Exemplo de uso:
```java
// Restrição: 3x1 + 2x2 >= 10
constraints.add(new Constraint(
    new double[]{3, 2},    // coeficientes
    ConsType.GE,           // >=
    10                     // lado direito
));
```

---

## Exemplo: Problema de Minimização

```java
public void problemaMinimizacao() throws Exception {
    System.out.println("Minimizar: 200x1 + 300x2");
    System.out.println("Sujeito a: 3x1 + 5x2 >= 35");

    // Criar função objetivo (minimização)
    LinearObjectiveFunction objective = new LinearObjectiveFunction(
        new double[]{200, 300},
        GoalType.MIN
    );

    // Criar lista de restrições
    ListConstraints constraints = new ListConstraints();

    // Restrição: 3x1 + 5x2 >= 35
    constraints.add(new Constraint(
        new double[]{3, 5},
        ConsType.GE,
        35
    ));

    // Criar e resolver
    LP lp = new LP(objective, constraints);
    SolutionType result = lp.resolve();
    exibirSolucao(lp, result);
}
```

---

## Saída Esperada

```
Resolvendo Problema de Otimização de Rotas
Maximizar: 5x1 + 4x2
Sujeito a:
  x1 + x2 <= 10
  2x1 + x2 <= 16

========= SOLUÇÃO ÓTIMA =========
Status: OPTIMAL
Valor Ótimo Z: 46.00

Valores das variáveis:
  x1 = 6.00
  x2 = 4.00
================================
```

**Resultado idêntico ao obtido manualmente com o Simplex Tableau!**

---

## Integração com Spring Boot

Nossa aplicação criada utiliza **Spring Boot** para facilitar a execução:

### Estrutura:
```
ssc-terminal-app/
├── SimplexRunner.java       # Interface de terminal
├── SimplexService.java      # Lógica de otimização
└── SscTerminalApplication.java  # Aplicação Spring Boot
```

### Vantagens:
- Injeção de dependências
- Facilidade de configuração
- CommandLineRunner para interação via terminal
- Fácil expansão e manutenção

---

## Executando a Aplicação

### Compilar:
```bash
mvn clean install
```

### Executar:
```bash
mvn spring-boot:run
```

### Menu Interativo:
```
===========================================
  SSC - Software for Simplex Calculation
===========================================

Escolha uma opcao:
1 - Resolver exemplo de Programacao Linear
2 - Resolver exemplo de Maximizacao
3 - Resolver problema personalizado
0 - Sair
```

---

## Demonstração Prática

### Vamos executar nosso programa!

1. Resolver o problema de maximização (opção 2)
2. Verificar os resultados
3. Comparar com a solução manual do Simplex Tableau

### Código disponível em:
```
ssc-terminal-app/src/main/java/com/example/sscapp/
```

---

<!-- _class: lead -->
# Parte 6
## Aplicações Práticas

---

## Aplicações de Programação Linear

### 1. Logística e Transporte
- Otimização de rotas de entrega
- Distribuição de mercadorias
- Gestão de frota

### 2. Produção
- Planejamento de produção
- Mix de produtos
- Alocação de recursos

---

## Aplicações de Programação Linear (cont.)

### 3. Finanças
- Otimização de portfólio
- Gestão de riscos
- Alocação de investimentos

### 4. Agricultura
- Planejamento de cultivo
- Uso de fertilizantes
- Irrigação

---

## Aplicações de Programação Linear (cont.)

### 5. Telecomunicações
- Roteamento de chamadas
- Alocação de banda
- Design de redes

### 6. Energia
- Despacho de energia
- Planejamento de manutenção
- Gestão de recursos

---

## Vantagens do Método Simplex

### ✓ Eficiência
Resolve problemas com milhares de variáveis rapidamente

### ✓ Garantia de Otimalidade
Se existe solução ótima, o Simplex a encontra

### ✓ Flexibilidade
Adapta-se a diversos tipos de problemas

### ✓ Implementação
Bibliotecas maduras e bem testadas (SSC, CPLEX, Gurobi)

---

## Limitações e Considerações

### Linearidade
Todas as relações devem ser lineares (não funciona para problemas não-lineares)

### Escala
Problemas muito grandes podem ser computacionalmente custosos

### Dados Precisos
Requer coeficientes bem definidos e precisos

### Solução:
Para problemas não-lineares → Programação Não-Linear
Para problemas grandes → Heurísticas e metaheurísticas

---

## Extensões da Programação Linear

### 1. Programação Linear Inteira (ILP)
Variáveis devem ser inteiras
- Ex: número de caminhões, pessoas, produtos

### 2. Programação Linear Inteira Mista (MILP)
Algumas variáveis inteiras, outras contínuas

### 3. Programação por Metas
Múltiplos objetivos com prioridades

### 4. Programação Estocástica
Incorpora incerteza nos parâmetros

---

## SSC: Recursos Avançados

A biblioteca SSC suporta:

### MILP (Mixed Integer Linear Programming)
```java
// Criar problema como de costume
LinearObjectiveFunction objective = new LinearObjectiveFunction(...);
ListConstraints constraints = new ListConstraints();
// ... adicionar restrições ...

// Usar classe MILP para variáveis inteiras
MILP milp = new MILP(objective, constraints);
// Configurar tipos de variáveis conforme necessário
SolutionType result = milp.resolve();
```

### Special Ordered Sets (SOS)
Conjuntos especiais de variáveis com restrições

### Múltiplos Formatos de Entrada
- Texto, coeficientes, matriz, sparse, JSON

---

<!-- _class: lead -->
# Conclusão

---

## Resumo da Aula

### 1. Revisamos os conceitos de Programação Linear
- Função objetivo, variáveis de decisão, restrições

### 2. Estudamos o Método Simplex
- Algoritmo iterativo, Simplex Tableau

### 3. Resolvemos um Problema Prático
- Otimização de rotas de distribuição
- Solução manual passo a passo

### 4. Implementamos em Java com SSC
- Código limpo e eficiente
- Mesmos resultados da solução manual

---

## Pontos-Chave para Lembrar

### O Simplex é poderoso
Resolve eficientemente problemas complexos de otimização

### Biblioteca SSC é prática
Abstrai a complexidade matemática, foca no problema

### PL é amplamente aplicável
Logística, produção, finanças, agricultura, energia...

### Modelagem é fundamental
A qualidade da solução depende da qualidade do modelo

---

## Próximos Passos

### 1. Prática
Execute o código, teste com diferentes problemas

### 2. Expansão
Adicione novos problemas ao SimplexService.java

### 3. MILP
Experimente problemas com variáveis inteiras

### 4. Visualização
Considere adicionar gráficos da região viável (2D)

### 5. Análise de Sensibilidade
Estude como mudanças nos parâmetros afetam a solução

---

## Recursos Adicionais

### Documentação SSC
https://www.ssclab.org/

### Código da Aula
`ssc-terminal-app/` - Projeto completo com exemplos

### Livros Recomendados
- "Introduction to Operations Research" - Hillier & Lieberman
- "Linear Programming" - Vasek Chvatal

### Ferramentas Online
- GeoGebra (visualização de PL em 2D)
- GNU Linear Programming Kit (GLPK)

---

## Exercícios Propostos

### 1. Modifique o problema de rotas
Adicione uma terceira restrição e observe o impacto

### 2. Problema de Dieta
Um restaurante quer minimizar o custo da comida atendendo requisitos nutricionais

### 3. Problema de Produção
Uma fábrica produz 3 produtos com recursos limitados. Maximize o lucro.

### 4. Compare métodos
Resolva o mesmo problema manualmente e com SSC. Verifique os resultados.

---

<!-- _class: lead -->
# Perguntas?

**Prof. Alysson M. Bruno**
Otimização de Sistemas - Unitins

---

<!-- _class: lead -->
# Obrigado!

## Bons estudos e boa prática!

**Otimização de Sistemas - Aula 11**
Prof. Alysson M. Bruno
Unitins - 2025.2
