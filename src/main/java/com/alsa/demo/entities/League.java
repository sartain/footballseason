package com.alsa.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "leagues")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne
    private LeaguePosition position;

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
