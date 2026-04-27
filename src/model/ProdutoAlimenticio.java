package model;

public class ProdutoAlimenticio extends Produto {
    private String dataValidade;
    private String lote;

    public ProdutoAlimenticio(String codigo, String nome, int quantidade, double preco, String dataValidade, String lote) {
        super(codigo, nome, quantidade, preco);

        if (dataValidade == null || dataValidade.isEmpty()) throw new IllegalArgumentException("Data de validade inválida");

        this.dataValidade = dataValidade;
        this.lote = lote;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        if (!(dataValidade == null) && !(dataValidade.isEmpty())) this.dataValidade = dataValidade;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public boolean estaVencido() {
        String hoje = java.time.LocalDateTime.now().toString();
        return dataValidade.compareTo(hoje) < 0;
    }

    public String exibirDetalhes() {
        return "==== Produto Alimentício ====\n" +
                "\nCódigo: " + getCodigo() +
                "\nNome: " + getNome() +
                "\nPreço: R$ " + getPreco() +
                "\nQuantidade: " + getQuantidade() +
                "\nData de Validade: " + dataValidade +
                "\nLote: " + (lote != null ? lote : "N/A");
    }

    public void saidaEstoque(int quantidade) {
        if (estaVencido()) {
            System.out.println("Não é possível retirar do estoque. O produto está vencido!");
            return;
        }

        super.saidaEstoque(quantidade);
    }
}
