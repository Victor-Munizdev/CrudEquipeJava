package model;

import java.sql.Date;

/**
 * Classe que representa a entidade Tarefa no sistema.
 */
public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private Date dataInicio;
    private Date dataTermino;
    private String status;
    private int idProjeto;
    private int idUsuarioResponsavel;

    // Construtor padrão
    public Tarefa() {
    }

    // Construtor completo
    public Tarefa(int id, String titulo, String descricao, Date dataInicio, Date dataTermino, String status, int idProjeto, int idUsuarioResponsavel) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.status = status;
        this.idProjeto = idProjeto;
        this.idUsuarioResponsavel = idUsuarioResponsavel;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdUsuarioResponsavel() {
        return idUsuarioResponsavel;
    }

    public void setIdUsuarioResponsavel(int idUsuarioResponsavel) {
        this.idUsuarioResponsavel = idUsuarioResponsavel;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}