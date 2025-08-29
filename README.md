# 🏭 Teste Prático – Iniflex (Java)

Projeto desenvolvido em **Java 17** com Maven, atendendo os requisitos do teste prático.

## 📦 Como gerar o `.jar`

Na raiz do projeto, rode o comando:

```bash
mvn clean package
```

Isso vai gerar o arquivo:

```
target/industria-funcionarios-1.0.0.jar
```

## ▶️ Como executar o `.jar`

Depois de gerado, execute:

```bash
java -jar target/industria-funcionarios-1.0.0.jar
```

## 🔍 Pré-requisitos

- **Java 17+** instalado (`java -version`)
- **Maven 3.8+** instalado (`mvn -version`)

## 📂 Estrutura do projeto

- `Pessoa.java` → Classe base com `nome` e `dataNascimento`
- `Funcionario.java` → Herda de `Pessoa`, com `salario` e `funcao`
- `Principal.java` → Contém a `main` e implementa todos os requisitos do teste
- `pom.xml` → Configuração Maven (gera `.jar` executável)

## ✨ Funcionalidades implementadas

- Inserção dos funcionários (item 3.1)
- Remoção de funcionário "João" (3.2)
- Impressão formatada (datas e salários em **pt-BR**) (3.3)
- Reajuste salarial de 10% (3.4)
- Agrupamento por função (3.5 e 3.6)
- Aniversariantes em outubro e dezembro (3.8)
- Funcionário mais velho (3.9)
- Ordenação alfabética (3.10)
- Soma total dos salários (3.11)
- Equivalência em salários mínimos (3.12)
  
## 📜 Observações
- Os formatos seguem padrão brasileiro:
  - **Datas** → `dd/MM/yyyy`
  - **Números** → milhar com ponto e decimal com vírgula
