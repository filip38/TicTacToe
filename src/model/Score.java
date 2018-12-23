/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



/**
 *
 * @author Filip
 */
@Entity
public class Score {
    @Id
    private int id_score;
    private int wins, draws, loses;
    private double win_ratio;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    
    public Score(int id_user, int id_score) {
        this.id_score = id_user;
    }
    
    public Score() {
    }

    public Score(int id_user,  int wins, int draws, int loses, double win_ratio) {
        this.id_score = id_user;
        this.wins = wins;
        this.draws = draws;
        this.loses = loses;
        this.win_ratio = win_ratio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public int getId_user() {
        return id_score;
    }

    public void setId_user(int id_user) {
        this.id_score = id_user;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public double getWin_ratio() {
        return win_ratio;
    }

    public void setWin_ratio(double win_ratio) {
        this.win_ratio = win_ratio;
    }
    
    
}
