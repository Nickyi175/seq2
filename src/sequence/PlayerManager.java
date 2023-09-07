/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sequence;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private List<Player> players = new ArrayList<>();
    private static final String FILENAME = "players.dat";

    // Método para cargar jugadores desde el archivo binario
    public void loadPlayers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            players = (List<Player>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para guardar jugadores en el archivo binario
    public void savePlayers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para seleccionar jugadores para el juego
    public List<Player> selectPlayersForGame(int numberOfPlayers) {
        List<Player> selectedPlayers = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            if (i < players.size()) {
                selectedPlayers.add(players.get(i));
            } else {
                // Manejar el caso en el que no hay suficientes jugadores registrados
                // Puedes mostrar un mensaje de error o permitir la creación de nuevos jugadores.
                System.out.println("No hay suficientes jugadores registrados.");
                break;
            }
        }
        return selectedPlayers;
    }
}
