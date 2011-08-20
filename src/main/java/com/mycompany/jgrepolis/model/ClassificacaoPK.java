/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jgrepolis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Felipe
 */
@Embeddable
public class ClassificacaoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "PLAYER", nullable = false, length = 30)
    private String player;

    public ClassificacaoPK() {
    }

    public ClassificacaoPK(Date date, String player) {
        this.date = date;
        this.player = player;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (date != null ? date.hashCode() : 0);
        hash += (player != null ? player.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassificacaoPK)) {
            return false;
        }
        ClassificacaoPK other = (ClassificacaoPK) object;
        if ((this.date == null && other.date != null) || (this.date != null && !this.date.equals(other.date))) {
            return false;
        }
        if ((this.player == null && other.player != null) || (this.player != null && !this.player.equals(other.player))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.jgrepolis.model.ClassificacaoPK[ date=" + date + ", player=" + player + " ]";
    }
    
}
