package com.tictactoe.player;

import com.tictactoe.field.FieldController;

/**
 * Date: 09.08.13
 * Time: 15:31
 */
public class Player {
    private String name;
    private PlayerState state;

    public Player(){
        this("Player");
    }

    public Player(String name) {
        this.name = name;
        state = PlayerState.playing;
    }

    public void setName(String newName){
        if(newName.length() > 0)
            name = newName;
        else
            System.out.println("Введите корректную фамилию");
    }

    public String getName(){
        return name;
    }

    public void setState(PlayerState newState){
        state = newState;
        System.out.println(this.name + " is " + newState.name() + "!");
    }

    public PlayerState getState(){
        return state;
    }

    public int play(FieldController fieldController, char symbol){
        return 0;
    }
}
