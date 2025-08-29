package com.exame;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    private static final Locale PT_BR = new Locale("pt", "BR");
    private static final DateTimeFormatter DATA_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat NUM_BR = NumberFormat.getNumberInstance(PT_BR);
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    static {
        NUM_BR.setMinimumFractionDigits(2);
        NUM_BR.setMaximumFractionDigits(2);
    }

    public static void main(String[] args) {
        // 3.1 – Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria",  LocalDate.of(2000, 10, 18), bd("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João",   LocalDate.of(1990, 5, 12),  bd("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio",   LocalDate.of(1961, 5, 2),   bd("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), bd("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice",  LocalDate.of(1995, 1, 5),   bd("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), bd("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31),  bd("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura",  LocalDate.of(1994, 7, 8),   bd("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa",LocalDate.of(2003, 5, 24),  bd("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2),   bd("2799.93"), "Gerente"));

        // 3.2 – Remover o funcionário “João”
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // 3.3 – Imprimir todos os funcionários formatados
        System.out.println("=== 3.3 Funcionários (formatados) ===");
        funcionarios.forEach(Principal::imprimirFuncionario);

        // 3.4 – Reajuste de 10%
        funcionarios.forEach(f ->
            f.setSalario(f.getSalario().multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_UP))
        );

        System.out.println("\n=== 3.4 Após reajuste de 10% ===");
        funcionarios.forEach(Principal::imprimirFuncionario);

        // 3.5 – Agrupar por função
        Map<String, List<Funcionario>> porFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao, LinkedHashMap::new, Collectors.toList()));

        // 3.6 – Imprimir agrupados por função
        System.out.println("\n=== 3.6 Funcionários por função ===");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(Principal::imprimirFuncionario);
        });

        // 3.8 – Aniversariantes de outubro e dezembro
        System.out.println("\n=== 3.8 Aniversariantes (outubro e dezembro) ===");
        funcionarios.stream()
                .filter(f -> {
                    Month m = f.getDataNascimento().getMonth();
                    return m == Month.OCTOBER || m == Month.DECEMBER;
                })
                .forEach(Principal::imprimirFuncionario);

        // 3.9 – Funcionário com maior idade
        System.out.println("\n=== 3.9 Funcionário mais velho ===");
        funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento)) // data mais antiga = mais velho
                .ifPresent(f -> {
                    int idade = Period.between(f.getDataNascimento(), LocalDate.now()).getYears();
                    System.out.printf("Nome: %s | Idade: %d anos%n", f.getNome(), idade);
                });

        // 3.10 – Lista por ordem alfabética
        System.out.println("\n=== 3.10 Ordenados por nome ===");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(Principal::imprimirFuncionario);

        // 3.11 – Total dos salários
        System.out.println("\n=== 3.11 Total dos salários ===");
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total: R$ " + NUM_BR.format(total));

        // 3.12 – Quantos salários mínimos cada um ganha
        System.out.println("\n=== 3.12 Equivalência em salários mínimos (R$ 1.212,00) ===");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.printf("%s: %s salários mínimos%n", f.getNome(), NUM_BR.format(qtd));
        });
    }

    private static void imprimirFuncionario(Funcionario f) {
        String data = f.getDataNascimento().format(DATA_BR);
        String salario = NUM_BR.format(f.getSalario());
        System.out.printf("Nome: %s | Nascimento: %s | Salário: R$ %s | Função: %s%n",
                f.getNome(), data, salario, f.getFuncao());
    }

    private static BigDecimal bd(String val) {
        return new BigDecimal(val);
    }
}
