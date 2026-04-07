package model;

/**
 * Enum que representa os cargos disponíveis no sistema.
 */
public enum Cargo {
    ADMINISTRADOR("administrador"),
    GERENTE("gerente"),
    COLABORADOR("colaborador");

    private final String descricao;

    Cargo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Cargo fromString(String text) {
        for (Cargo c : Cargo.values()) {
            if (c.descricao.equalsIgnoreCase(text)) {
                return c;
            }
        }
        return COLABORADOR; // Default
    }
}