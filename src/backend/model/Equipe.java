package model;

/**
 * Classe que representa a entidade Equipe no sistema.
 */
public class Equipe {
    private int id;
    private String nomeEquipe;
    private String descricao;

    // Construtor padrão
    public Equipe() {
    }

    // Construtor completo
    public Equipe(int id, String nomeEquipe, String descricao) {
        this.id = id;
        this.nomeEquipe = nomeEquipe;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", nomeEquipe='" + nomeEquipe + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}