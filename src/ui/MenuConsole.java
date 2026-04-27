package ui;

import model.*;
import service.Estoque;
import util.*;

import java.util.List;
import java.util.Scanner;

public class MenuConsole {

    private Scanner scanner;
    private Estoque estoque;

    public MenuConsole(Estoque estoque) {
        this.estoque = estoque;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> buscarProduto();
                case 4 -> registrarEntrada();
                case 5 -> registrarSaida();
                case 6 -> exibirRelatorio();
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Listar Produtos");
        System.out.println("3 - Buscar Produto");
        System.out.println("4 - Registrar Entrada");
        System.out.println("5 - Registrar Saída");
        System.out.println("6 - Relatório Geral");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private void cadastrarProduto() {
        System.out.println("\nTipo de produto: ");
        System.out.println("1 - Alimentício");
        System.out.println("2 - Eletrônico");
        System.out.println("3 - Vestuário");
        System.out.print("Escolha: ");

        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        Produto produto = null;

        switch (tipo) {
            case 1 -> {
                String codigo = GeradorCodigo.gerarCodigo("ALI");

                System.out.print("Data de validade (YYYY-MM-DD): ");
                String validade = scanner.nextLine();

                System.out.print("Lote (opcional): ");
                String lote = scanner.nextLine();

                produto = new ProdutoAlimenticio(codigo, nome, quantidade, preco, validade, lote);
            }

            case 2 -> {
                String codigo = GeradorCodigo.gerarCodigo("ELE");

                System.out.print("Garantia (meses): ");
                int garantia = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Voltagem (opcional): ");
                String voltagem = scanner.nextLine();

                produto = new ProdutoEletronico(
                        codigo, nome, quantidade, preco, garantia, voltagem
                );
            }

            case 3 -> {
                String codigo = GeradorCodigo.gerarCodigo("VES");

                System.out.print("Tamanho: ");
                String tamanho = scanner.nextLine();

                System.out.print("Material: ");
                String material = scanner.nextLine();

                produto = new ProdutoVestuario(codigo, nome, quantidade, preco, tamanho, material);
            }

            default -> {
                System.out.println("Tipo inválido");
                return;
            }

        }

        boolean sucesso = estoque.cadastrarProduto(produto);

        if (sucesso) {
            System.out.println("Produto cadastrado com sucesso!");
        }
    }

    private void listarProdutos() {
        List<Produto> produtos = estoque.listaProdutos();
        System.out.println(FormatadorRelatorio.formatarListaProdutos(produtos));
    }

    private void buscarProduto() {
        System.out.print("Digite o código: ");
        String codigo = scanner.nextLine();

        Produto produto = estoque.buscarProdutoPorCodigo(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado");
        } else {
            FormatadorRelatorio.formatarProduto(produto);
        }
    }

    private void registrarEntrada() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();

        if (estoque.registrarEntrada(codigo, quantidade)) {
            System.out.println("Entrada registrada.");
        }
    }

    private void registrarSaida() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();

        if (estoque.registrarSaida(codigo, quantidade)) {
            System.out.println("Saída registrada.");
        }
    }

    private void exibirRelatorio() {
        System.out.println(
                FormatadorRelatorio.formatarRelatorioEstoque(estoque.listaProdutos())
        );
    }
}
