package model;

public class ProdutoVestuario extends Produto {
    private String tamanho;
    private String material;

    public ProdutoVestuario(String codigo, String nome, int quantidade, double preco, String tamanho, String material) {
        super(codigo, nome, quantidade, preco);

        if (tamanho == null || tamanho.isEmpty()) throw new IllegalArgumentException("Tamanho inválido");

        this.tamanho = tamanho;
        this.material = material;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        if (!(tamanho == null) && !(tamanho.isEmpty())) {
            this.tamanho = tamanho;
        }
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isTamanhoUnico() {
        return tamanho.equalsIgnoreCase("único") || tamanho.equalsIgnoreCase("unico");
    }

    public double calcularPrecoPromocional(double percentual) {
        if (percentual < 0 || percentual > 100) {
            System.out.println("Percentual inválido");
            return getPreco();
        } else {
            return getPreco() - (getPreco() * percentual/100);
        }
    }

    public String exibirDetalhes() {
        return "==== Produto Vestuário ====\n" +
                "\nCódigo: " + getCodigo() +
                "\nNome: " + getNome() +
                "\nPreço: " + getPreco() +
                "\nQuantidade: " + getQuantidade() +
                "\nTamanho: " + tamanho +
                "\nMaterial: " + (material != null ? material : "N/A");
    }
}
