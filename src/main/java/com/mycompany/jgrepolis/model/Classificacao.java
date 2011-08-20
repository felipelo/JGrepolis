/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jgrepolis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "CLASSIFICACAO", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classificacao.findAll", query = "SELECT c FROM Classificacao c"),
    @NamedQuery(name = "Classificacao.findByDate", query = "SELECT c FROM Classificacao c WHERE c.classificacaoPK.date = :date"),
    @NamedQuery(name = "Classificacao.findByPlayer", query = "SELECT c FROM Classificacao c WHERE c.classificacaoPK.player = :player"),
    @NamedQuery(name = "Classificacao.findByRank", query = "SELECT c FROM Classificacao c WHERE c.rank = :rank"),
    @NamedQuery(name = "Classificacao.findByPoints", query = "SELECT c FROM Classificacao c WHERE c.points = :points"),
    @NamedQuery(name = "Classificacao.findByAlly", query = "SELECT c FROM Classificacao c WHERE c.ally = :ally"),
    @NamedQuery(name = "Classificacao.findByCities", query = "SELECT c FROM Classificacao c WHERE c.cities = :cities"),
    @NamedQuery(name = "Classificacao.findByPointsAvg", query = "SELECT c FROM Classificacao c WHERE c.pointsAvg = :pointsAvg"),
    @NamedQuery(name = "Classificacao.findByOcean", query = "SELECT c FROM Classificacao c WHERE c.ocean = :ocean")})
public class Classificacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClassificacaoPK classificacaoPK;
    @Column(name = "RANK")
    private Integer rank;
    @Column(name = "POINTS")
    private Integer points;
    @Column(name = "ALLY", length = 30)
    private String ally;
    @Column(name = "CITIES")
    private Integer cities;
    @Column(name = "POINTS_AVG")
    private Integer pointsAvg;
    @Column(name = "OCEAN")
    private Short ocean;

    public Classificacao() {
    }

    public Classificacao(ClassificacaoPK classificacaoPK) {
        this.classificacaoPK = classificacaoPK;
    }

    public Classificacao(Date date, String player) {
        this.classificacaoPK = new ClassificacaoPK(date, player);
    }

    public ClassificacaoPK getClassificacaoPK() {
        return classificacaoPK;
    }

    public void setClassificacaoPK(ClassificacaoPK classificacaoPK) {
        this.classificacaoPK = classificacaoPK;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getAlly() {
        return ally;
    }

    public void setAlly(String ally) {
        this.ally = ally;
    }

    public Integer getCities() {
        return cities;
    }

    public void setCities(Integer cities) {
        this.cities = cities;
    }

    public Integer getPointsAvg() {
        return pointsAvg;
    }

    public void setPointsAvg(Integer pointsAvg) {
        this.pointsAvg = pointsAvg;
    }

    public Short getOcean() {
        return ocean;
    }

    public void setOcean(Short ocean) {
        this.ocean = ocean;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classificacaoPK != null ? classificacaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classificacao)) {
            return false;
        }
        Classificacao other = (Classificacao) object;
        if ((this.classificacaoPK == null && other.classificacaoPK != null) || (this.classificacaoPK != null && !this.classificacaoPK.equals(other.classificacaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classificacao[ classificacaoPK=" + classificacaoPK + " ]";
    }
    
}
