package util;

public class GeradorCodigo {
    private static int sequencial = 1;

    public static String gerarCodigo(String prefixo) {
        if (prefixo == null || prefixo.isEmpty()) throw new IllegalArgumentException("Prefixo inválido");

        String codigo = String.format("%s-%03d", prefixo.toUpperCase(), sequencial);
        sequencial++;

        return codigo;
    }
}
