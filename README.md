# SSC Terminal Application

Aplicação Spring Boot que utiliza a biblioteca SSC (Software for Simplex Calculation) para resolver problemas de Programação Linear através do terminal.

## Requisitos

- JDK 17 ou superior
- Maven 3.6 ou superior

## Sobre a Biblioteca SSC

SSC é uma biblioteca Java de código aberto para resolver problemas de otimização de programação linear. Ela suporta:
- Problemas de Programação Linear (LP)
- Programação Linear Inteira Mista (MILP)
- Variáveis inteiras, binárias e semi-contínuas
- Conjuntos Especiais Ordenados (SOS) tipos 1 e 2

Mais informações: https://www.ssclab.org/

## Estrutura do Projeto

```
ssc-terminal-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/sscapp/
│   │   │       ├── SscTerminalApplication.java   # Classe principal
│   │   │       ├── SimplexRunner.java             # CommandLineRunner
│   │   │       └── SimplexService.java            # Lógica de negócio
│   │   └── resources/
│   │       └── application.properties             # Configurações
│   └── test/
│       └── java/
├── pom.xml                                        # Configuração Maven
└── README.md
```

## Como Compilar

```bash
cd ssc-terminal-app
mvn clean install
```

## Como Executar

```bash
mvn spring-boot:run
```

Ou, após compilar:

```bash
java -jar target/ssc-terminal-app-1.0.0.jar
```

## Funcionalidades

A aplicação oferece um menu interativo no terminal com as seguintes opções:

1. **Resolver exemplo de Programação Linear** - Problema de minimização
   - Minimizar: 3x₁ + 2x₂
   - Sujeito a: 2x₁ + x₂ ≥ 4, x₁ + 2x₂ ≥ 3, x₁, x₂ ≥ 0

2. **Resolver exemplo de Maximização**
   - Maximizar: 5x₁ + 4x₂
   - Sujeito a: x₁ + x₂ ≤ 5, 3x₁ + x₂ ≤ 12, x₁, x₂ ≥ 0

3. **Resolver problema personalizado** (em desenvolvimento)

## Exemplos de Uso

### Exemplo de Minimização

```
Minimizar: 3x1 + 2x2
Sujeito a:
  2x1 + x2 >= 4
  x1 + 2x2 >= 3
  x1, x2 >= 0
```

### Exemplo de Maximização

```
Maximizar: 5x1 + 4x2
Sujeito a:
  x1 + x2 <= 5
  3x1 + x2 <= 12
  x1, x2 >= 0
```

## Personalização

Para adicionar seus próprios problemas de programação linear, edite o arquivo `SimplexService.java` e adicione novos métodos seguindo o padrão dos exemplos existentes.

### Exemplo de Código

```java
LP lp = new LP();

// Definir função objetivo (maximização ou minimização)
lp.setObjFunctionMax(); // ou setObjFunctionMin()
lp.setCObj(5, 4); // coeficientes da função objetivo

// Adicionar restrições
lp.addConstraint(new Constraint(ConsType.LE, 5), 1, 1);
lp.addConstraint(new Constraint(ConsType.GE, 3), 2, 1);

// Resolver
Solution solution = lp.solve();

// Obter resultados
double[] values = solution.getOptimalSolution();
double optimal = solution.getOptimalValue();
```

## Tipos de Restrições

- `ConsType.LE` - Menor ou igual (≤)
- `ConsType.GE` - Maior ou igual (≥)
- `ConsType.EQ` - Igual (=)

## Dependências Principais

- Spring Boot 3.2.0
- SSC-LP 3.2.0

## Licença

Este projeto é um exemplo educacional.
