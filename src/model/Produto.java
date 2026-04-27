package model;

public abstract class Produto {
    private final String codigo;
    private String nome;
    private int quantidade;
    private double preco;
    private static int totalProdutos = 0;

    //================== CONSTRUTOR ==================
    public Produto(String codigo, String nome, int quantidade, double preco) {
        if (codigo == null || codigo.isEmpty()) throw new IllegalArgumentException("Código inválido");
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome inválido.");
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa");
        if (preco < 0) throw new IllegalArgumentException("Preço não pode ser negativo.");

        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;

        totalProdutos++;
    }

    //================== GETTERS ==================
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public static int getTotalProdutos() {
        return totalProdutos;
    }

    //================== SETTERS ==================

    public void setNome(String nome) {
        if (!(nome == null) && !(nome.isEmpty())) this.nome = nome;

    }

    public void setPreco(double preco) {
        if (preco >= 0) this.preco = preco;
    }

    //================== CONTROLE DE ESTOQUE ==================

    public final void entradaEstoque(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade += quantidade;
        } else {
            System.out.println("Quantidade inválida");
        }
    }

    public void entradaEstoque(int quantidade, String observacao) {
        entradaEstoque(quantidade);
        System.out.println("Obs: " + observacao);
    }

    public void saidaEstoque(int quantidade) {
        if (quantidade > 0 && quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
        } else {
            System.out.println("Quantidade inválida para saída");
        }
    }

    public void saidaEstoque(int quantidade,boolean permitirZerar) {
        if (permitirZerar) {
            if (quantidade > 0 && quantidade <= this.quantidade) {
                this.quantidade -= quantidade;
            } else {
                System.out.println("Quantidade inválida para saída");
            }
        } else {
            if (quantidade > 0 && quantidade < this.quantidade) {
                this.quantidade -= quantidade;
            } else {
                System.out.println("Operação não permitida (não é possível zerar o estoque).");
            }
        }
    }

    // ================== MÉTODO ABSTRATO ==================

    public abstract String exibirDetalhes();

}
