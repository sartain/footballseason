package com.alsa.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "teams", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne
    @JoinColumn(name = "leaguepositions.teamId")
    private Integer id;
    private String name;

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

