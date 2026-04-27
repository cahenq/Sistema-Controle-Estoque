package model;

public class ProdutoEletronico extends Produto {
    private int garantiaMeses;
    private String voltagem;

    public ProdutoEletronico(String codigo, String nome, int quantidade, double preco, int garantiaMeses, String voltagem) {
        super(codigo, nome, quantidade, preco);

        if (garantiaMeses < 0) throw new IllegalArgumentException("Garantia inválida");

        this.garantiaMeses = garantiaMeses;
        this.voltagem = voltagem;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        if (garantiaMeses >= 0) this.garantiaMeses = garantiaMeses;
    }

    public String getVoltagem() {
        return voltagem;
    }

    public void setVoltagem(String voltagem) {
        this.voltagem = voltagem;
    }

    public boolean possuiGarantiaEstentidade() {
        return garantiaMeses > 12;
    }

    public double calcularTaxaArmazenamento() {
        return getPreco() * 0.02 * getQuantidade();
    }

    public String exibirDetalhes() {
        return "==== Produto Eletrônico ====\n" +
                "\nCódigo: " + getCodigo() +
                "\nNome: " + getNome() +
                "\nPreço: " + getPreco() +
                "\nQuantidade: " + getQuantidade() +
                "\nGarantia: " + getGarantiaMeses() +
                "\nVoltagem: " + getVoltagem();

    }
}
