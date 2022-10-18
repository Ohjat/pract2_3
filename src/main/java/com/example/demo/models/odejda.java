package com.example.demo.models;
import javax.persistence.*;
import java.sql.Date;
@Entity
public class odejda {
    public odejda(String tip, Date gotvipuska, boolean nowlineka, int partia, double razmer) {
        this.tip = tip;
        this.gotvipuska = gotvipuska;
        this.nowlineka = nowlineka;
        this.partia = partia;
        this.razmer = razmer;
    }
    public odejda() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public Long getId() {
        return id;
    }
    public String tip;
    public Date gotvipuska;
    public boolean nowlineka;
    public int partia;
    public double razmer;
}
