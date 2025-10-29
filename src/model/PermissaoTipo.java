package model;

/**
 * Enum que representa o tipo de permissão do usuário
 * conforme o ENUM 'permissao_tipo' do banco.
 */
public enum PermissaoTipo {
    ADMINISTRADOR("A"),
    MEDICO("M"),
    RECEPCIONISTA("R"),
    PACIENTE("P");

    private final String codigo;

    PermissaoTipo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    // Converte valor do banco (A, M, R, P) para enum Java
    public static PermissaoTipo fromString(String valor) {
        if (valor == null) return null;
        switch (valor.toUpperCase()) {
            case "A": return ADMINISTRADOR;
            case "M": return MEDICO;
            case "R": return RECEPCIONISTA;
            case "P": return PACIENTE;
            default: throw new IllegalArgumentException("Permissão inválida: " + valor);
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case ADMINISTRADOR: return "Administrador";
            case MEDICO: return "Médico";
            case RECEPCIONISTA: return "Recepcionista";
            case PACIENTE: return "Paciente";
            default: return super.toString();
        }
    }
}

