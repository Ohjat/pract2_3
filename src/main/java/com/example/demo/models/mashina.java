package com.example.demo.models;
import javax.persistence.*;
import java.sql.Date;
@Entity
public class mashina {
    public mashina(String marca, Date godsodania, boolean impor, int obem, double litrs) {
        this.marca = marca;
        this.godsodania = godsodania;
        this.impor = impor;
        this.obem = obem;
        this.litrs = litrs;
    }
    public mashina() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public Long getId() {
        return id;
    }
    public String marca;
    public Date godsodania;
    public boolean impor;
    public int obem;
    public double litrs;
}
