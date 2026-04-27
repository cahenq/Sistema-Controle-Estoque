package util;

import model.Produto;

import java.util.List;

public class FormatadorRelatorio {
    public static String formatarProduto(Produto produto) {
        if (produto == null) {
            return "Produto inválido.\n";
        }

        //disk polimorfismo acontece aqui tbm
        return produto.exibirDetalhes() + "\n";
    }

    public static String formatarListaProdutos(List<Produto> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            return "Nenhum produto encontrado.\n";
        }

        StringBuilder sb = new StringBuilder();

        for (Produto p : produtos) {
            sb.append(formatarProduto(p));
            sb.append("-------------------------------\n");
        }

        return sb.toString();
    }

    public static String formatarRelatorioEstoque(List<Produto> produtos) {
        StringBuilder sb = new StringBuilder();

        sb.append("===== RELATÓRIO DE ESTOQUE =====\n");

        if (produtos == null || produtos.isEmpty()) {
            return "Nenhum produto cadastrado.\n";
        } else {
            for (Produto p :  produtos) {
                sb.append(formatarProduto(p));
                sb.append("-------------------------------\n");
            }
        }

        sb.append("Total de produtos cadastrados: ").append(Produto.getTotalProdutos()).append("\n");

        return sb.toString();
    }
}
