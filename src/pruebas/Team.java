/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import java.util.ArrayList;

/**
 *
 * @author Jorge Hernandez
 */
public class Team {
    int maxSize;
    int sequences;
    ArrayList<Player> players;

    public Team(int size) {
        this.maxSize = size;
        this.players = new ArrayList<>();
        sequences = 0;
    }
    
    public void add(Player p) {
        if (isFull()) {
            return;
        }
        players.add(p);
    }
    
    public boolean isFull() {
        return players.size() == maxSize;
    }
    
    public int size() {
        return players.size();
    }
    
    
}
