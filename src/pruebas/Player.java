/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Azalia
 */
public final class Player extends User implements Serializable {
    // private ArrayList<String> resultadosJuegos=new ArrayList();

    // Estas variables no son guardadas en el archivo de jugadores. Solo sirven para
    // guardar temporalmente en la RAM
    // la informacion del jugador dentro de una partida.
    int team = -1;
    ArrayList<Carta> mazo;
    ArrayList<Carta> cartasJugadas;

    public Player(String username, String password, String nombreCompleto, long fechaCreacion, int pts,
            String fichaPath, int selectedPlayers) {
        super(username, password, nombreCompleto, fechaCreacion, pts, fichaPath, selectedPlayers);

        mazo = new ArrayList<>();
        cartasJugadas = new ArrayList<>();

    }

    /**
     * Cambia las cartas ubicadas en esa posicion
     */
    public void switchMazoPos(int oldPos, int newPos) {
        Carta oldCarta = mazo.get(oldPos);
        Carta newCarta = mazo.get(newPos);

        mazo.set(oldPos, newCarta);
        mazo.set(newPos, oldCarta);
    }

    // metodo para obtener puntos herencia
    @Override
    public void IncrementarPuntos(int cantidad) {
        int puntosActuales = getPuntos();
        super.puntos = puntosActuales + (cantidad - (puntosActuales % cantidad));
    }

}
