package model;

import java.sql.Date;

/**
 * Classe que representa a entidade Projeto no sistema.
 */
public class Projeto {
    private int id;
    private String nomeProjeto;
    private String descricao;
    private Date dataInicio;
    private Date dataTerminoPrevista;
    private String status;
    private int idGerenteResponsavel;

    // Construtor padrão
    public Projeto() {
    }

    // Construtor completo
    public Projeto(int id, String nomeProjeto, String descricao, Date dataInicio, Date dataTerminoPrevista, String status, int idGerenteResponsavel) {
        this.id = id;
        this.nomeProjeto = nomeProjeto;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTerminoPrevista = dataTerminoPrevista;
        this.status = status;
        this.idGerenteResponsavel = idGerenteResponsavel;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
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

    public Date getDataTerminoPrevista() {
        return dataTerminoPrevista;
    }

    public void setDataTerminoPrevista(Date dataTerminoPrevista) {
        this.dataTerminoPrevista = dataTerminoPrevista;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdGerenteResponsavel() {
        return idGerenteResponsavel;
    }

    public void setIdGerenteResponsavel(int idGerenteResponsavel) {
        this.idGerenteResponsavel = idGerenteResponsavel;
    }

    @Override
    public String toString() {
        return "Projeto{" +
                "id=" + id +
                ", nomeProjeto='" + nomeProjeto + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}