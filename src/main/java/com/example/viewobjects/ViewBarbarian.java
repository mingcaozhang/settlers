package com.example.viewobjects;

/**
 * Created by Ming-PC on 4/12/2017.
 */
public class ViewBarbarian {
    private boolean p1win;
    private boolean p2win;
    private boolean p3win;
    private boolean p4win;
    private boolean victory;

    public ViewBarbarian(){
    }

    public boolean isP1win() {
        return p1win;
    }

    public void setP1win(boolean p1win) {
        this.p1win = p1win;
    }

    public boolean isP2win() {
        return p2win;
    }

    public void setP2win(boolean p2win) {
        this.p2win = p2win;
    }

    public boolean isP3win() {
        return p3win;
    }

    public void setP3win(boolean p3win) {
        this.p3win = p3win;
    }

    public boolean isP4win() {
        return p4win;
    }

    public void setP4win(boolean p4win) {
        this.p4win = p4win;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }
}
