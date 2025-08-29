# Teste Prático – Indústria (Java)

Projeto simples em **Java 17** que atende aos requisitos: classes `Pessoa` e `Funcionario`,
e uma classe `Principal` que realiza todas as operações pedidas (itens 3.1 a 3.12).
Formatos seguem **pt-BR** (datas `dd/MM/yyyy`, números com ponto para milhar e vírgula para decimais).

## Como executar

### Com Maven
```bash
cd industria-funcionarios
mvn -q -DskipTests package
java -cp target/industria-funcionarios-1.0.0.jar com.exame.Principal
```

### Sem Maven (usando `javac`/`java`)
```bash
cd industria-funcionarios/src/main/java
javac com/exame/*.java
java com.exame.Principal
```

Sinta-se à vontade para forkar e enviar o link.
