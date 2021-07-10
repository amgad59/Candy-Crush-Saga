package com.company;


public class Players extends Users{


    private int current_score;


    public String getCurrent_username() {
        return current_username;
    }


    public void setCurrent_username(String current_username) {
        this.current_username = current_username;
    }

    public int getCurrent_score() {
        return current_score;
    }

    public void setCurrent_score(int current_score) {
        this.current_score = current_score;
    }

    @Override
    public String toString() {
        return "Score{" + "current_username=" + current_username + ", current_score=" + current_score + '}';
    }



}
