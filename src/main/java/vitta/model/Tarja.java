package vitta.model;

/**
 * Enum que representa os tipos de tarja de medicamentos
 * conforme o tipo ENUM 'tarja_tipo' do banco de dados.
 */
public enum Tarja {
    BRANCA("Branca"),
    AMARELA("Amarela"),
    VERMELHA("Vermelha"),
    PRETA("Preta");

    private final String descricao;

    Tarja(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método auxiliar para converter texto vindo do banco em enum Java
    public static Tarja fromString(String valor) {
        if (valor == null) return null;
        switch (valor.toLowerCase()) {
            case "branca": return BRANCA;
            case "amarela": return AMARELA;
            case "vermelha": return VERMELHA;
            case "preta": return PRETA;
            default: throw new IllegalArgumentException("Tarja inválida: " + valor);
        }
    }

    @Override
    public String toString() {
        return descricao;
    }
}

