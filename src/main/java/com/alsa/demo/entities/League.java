package com.alsa.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "leagues", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne
    @JoinColumn(name = "leaguepositions.leagueId")
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public League() {}

    public League(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public League(String name) {
        this.name = name;
    }


}
