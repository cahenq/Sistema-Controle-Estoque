package service;

import model.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Estoque {
    private HashMap<String, Produto> produtosMap;
    private List<Produto> produtoList;

    public Estoque() {
        produtosMap = new HashMap<>();
        produtoList = new ArrayList<>();
    }

    public boolean cadastrarProduto(Produto produto) {
        if (produto == null) return false;

        String codigo = produto.getCodigo();

        if (produtosMap.containsKey(codigo)) {
            System.out.println("Produto com código já existente.");
            return false;
        }

        produtosMap.put(codigo, produto);
        produtoList.add(produto);

        return true;
    }

    public Produto buscarProdutoPorCodigo(String codigo) {
        return produtosMap.get(codigo);
    }

    public boolean removerProduto(String codigo) {
        Produto produto = produtosMap.remove(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return false;
        }

        produtoList.remove(produto);
        return true;
    }

    public boolean registrarEntrada(String codigo, int quantidade) {
        Produto produto = produtosMap.get(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado");
            return false;
        }

        produto.entradaEstoque(quantidade);
        return true;
    }

    public boolean registrarSaida(String codigo, int quantidade) {
        Produto produto = produtosMap.get(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado");
            return false;
        }

        if (quantidade > produto.getQuantidade()) {
            System.out.println("Quantidade insuficiente no estoque.");
            return false;
        }

        produto.saidaEstoque(quantidade);
        return true;
    }

    public List<Produto> listaProdutos() {
        return produtoList;
    }

    public String gerarRelatorioGeral() {
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("===== RELATÓRIO DE ESTOQUE =====\n");

        for (Produto produto : produtoList) {
            //polimofirsmo aq!!
            relatorio.append(produto.exibirDetalhes()).append("\n");
            relatorio.append("--------------------------------\n");
        }

        relatorio.append("Total de produtos cadastrados: ").append(Produto.getTotalProdutos()).append("\n");

        return relatorio.toString();
    }

    public List<Produto> listarProdutosComEstoqueBaixo(int limite) {
        List<Produto> resultado = new ArrayList<>();

        for (Produto produto : produtoList) {
            if (produto.getQuantidade() <= limite) {
                resultado.add(produto);
            }
        }

        return resultado;
    }
}
