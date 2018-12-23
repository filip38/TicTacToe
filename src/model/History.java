/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Filip
 */
@Entity
public class History {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_history;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user1_id")
    private User user1;
    private String user2;
    private String move1, move2, move3, move4, move5, move6, move7, move8, move9, score;

    public History() {
    }

    public History(User user1, String user2, String move1, String move2, String move3, 
            String move4, String move5, String move6, String move7, String move8, String move9, String score) {
        this.user1 = user1;
        this.user2 = user2;
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.move4 = move4;
        this.move5 = move5;
        this.move6 = move6;
        this.move7 = move7;
        this.move8 = move8;
        this.move9 = move9;
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getId_history() {
        return id_history;
    }

    public void setId_history(int id_history) {
        this.id_history = id_history;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public String getUser2_id() {
        return user2;
    }

    public void setUser2_id(String user2_id) {
        this.user2 = user2;
    }

    public String getMove1() {
        return move1;
    }

    public void setMove1(String move1) {
        this.move1 = move1;
    }

    public String getMove2() {
        return move2;
    }

    public void setMove2(String move2) {
        this.move2 = move2;
    }

    public String getMove3() {
        return move3;
    }

    public void setMove3(String move3) {
        this.move3 = move3;
    }

    public String getMove4() {
        return move4;
    }

    public void setMove4(String move4) {
        this.move4 = move4;
    }

    public String getMove5() {
        return move5;
    }

    public void setMove5(String move5) {
        this.move5 = move5;
    }

    public String getMove6() {
        return move6;
    }

    public void setMove6(String move6) {
        this.move6 = move6;
    }

    public String getMove7() {
        return move7;
    }

    public void setMove7(String move7) {
        this.move7 = move7;
    }

    public String getMove8() {
        return move8;
    }

    public void setMove8(String move8) {
        this.move8 = move8;
    }

    public String getMove9() {
        return move9;
    }

    public void setMove9(String move9) {
        this.move9 = move9;
    }

    @Override
    public String toString() {
        return "History{" + "id_history=" + id_history + ", user1=" + user1 + ", user2=" 
                + user2 + ", move1=" + move1 + ", move2=" + move2 + ", move3=" + move3 + 
                ", move4=" + move4 + ", move5=" + move5 + ", move6=" + move6 + ", move7=" 
                + move7 + ", move8=" + move8 + ", move9=" + move9 + ", score=" + score + '}';
    }

    

}
