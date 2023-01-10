package com.alsa.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToOne
    private LeaguePosition position;

    public Team() {}

    public Team(Integer id, String teamName) {
        this.id = id;
        this.name = teamName;
    }

    public Team(String teamName) {
        this.name = teamName;
    }


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
}

