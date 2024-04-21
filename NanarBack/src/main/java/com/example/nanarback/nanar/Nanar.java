package com.example.nanarback.nanar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Nanar {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long  idNanar;

    private String nomNanar;

    private long nanarditude;

    private long legendarite;

    public Nanar() {
    }

    public Nanar(long idNanar, String nomNanar, long nanarditude, long legendarite) {
        this.idNanar = idNanar;
        this.nomNanar = nomNanar;
        this.nanarditude = nanarditude;
        this.legendarite = legendarite;
    }

    public long getIdNanar() {
        return idNanar;
    }

    public void setIdNanar(long idNanar) {
        this.idNanar = idNanar;
    }

    public String getNomNanar() {
        return nomNanar;
    }

    public void setNomNanar(String nomNanar) {
        this.nomNanar = nomNanar;
    }

    public long getNanarditude() {
        return nanarditude;
    }

    public void setNanarditude(long nanarditude) {
        this.nanarditude = nanarditude;
    }

    public long getLegendarite() {
        return legendarite;
    }

    public void setLegendarite(long legendarite) {
        this.legendarite = legendarite;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nanar nanar = (Nanar) o;
        return idNanar == nanar.idNanar && nanarditude == nanar.nanarditude && legendarite == nanar.legendarite && Objects.equals(nomNanar, nanar.nomNanar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNanar, nomNanar, nanarditude, legendarite);
    }


}
