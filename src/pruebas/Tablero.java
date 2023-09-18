/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pruebas;

/**
 *
 * @author Jorge Hernandez
 */
import java.awt.Color;
import javax.swing.*;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.imageio.ImageIO;

public class Tablero extends javax.swing.JPanel {

    /**
     * Creates new form Board
     */
    final int ROWS = 10;
    final int COLUMNS = 10;
    Image tableroBg;
    ArrayList<Team> teams;
    ArrayList<Carta> cartasBaraja;
    int[] teamTurns; // representa que jugador de que equipo le toca en el siguiete turno
    int currentTeamTurn;
    Player currentPlayerTurn = null;
    int maxCards;

    public static ArrayList<Carta> loadAllCards() {
        try {

            ArrayList<Carta> cards = new ArrayList<>();
            File cardFolder = new File("src/imagenes_baraja");

            for (File child : cardFolder.listFiles()) {
                if (!child.isDirectory()) {
                    continue;
                }
                for (File cardImg : child.listFiles()) {
                    ImageIcon i = new ImageIcon(ImageIO.read(cardImg));
                    Carta c = new Carta(i);
                    cards.add(c);
                }
            }

            ArrayList<Carta> cards2 = new ArrayList<>();

            for (File child : cardFolder.listFiles()) {
                if (!child.isDirectory()) {
                    continue;
                }
                for (File cardImg : child.listFiles()) {
                    ImageIcon i = new ImageIcon(ImageIO.read(cardImg));
                    Carta c = new Carta(i);
                    cards2.add(c);
                }
            }

            // Unir otras cartas
            for (Carta c : cards2) {
                cards.add(c);
            }

            return cards;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Tablero(GameWindow gameWindow, ArrayList<Team> teams, int numCartas) {
        initComponents();
        tableroBg = new ImageIcon("src/imagenes_baraja/fondo.png").getImage(); // cargar fondo

        this.teams = teams;
        maxCards = numCartas;
        this.gameWindow = gameWindow;
        teamTurns = new int[teams.size()];

        cartasBaraja = loadAllCards();
        sequences = new ArrayList<>();

        // Configurar turnos
        /*
         * teamTurns es un array de int con n espacios y cada espacio corresponde a un
         * equipo.
         * 
         * Este int representa el jugador quien va a jugar por cada equipo en el
         * siguiente turno del equipo.
         * 
         * Por defecto, el primer jugador (pos 0 dentro del ArrayList de jugadores del
         * equipo) va a jugar al principio de cada partida.
         * 
         * Este numero va a ir cambiando a medida que los turnos pasen
         */

        // Aqui se configuran todos los turnos en 0 para cada equipo.
        for (int i = 0; i < teamTurns.length; i++) {
            teamTurns[i] = 0;
        }

        // [...]

        repartirCartas(); // Revisar funcion repartir cartas

        // Configurar por primera vez el timer
        gameWindow.timerLbl.setText("Tiempo Restante: 2:00");

    }

    private Carta getRandomCardFromMazo() {
        Random r = new Random();
        int pos = r.nextInt(0, cartasBaraja.size());
        Carta c = cartasBaraja.get(pos);
        cartasBaraja.remove(pos);
        return c;
    }

    public void advanceTurn() {
        if (currentPlayerTurn == null) {
            /*
             * Aqui entra cuando todavia nadie ha jugado y se va a determinar el primer
             * jugador en jugar.
             * El jugador 0 del ArrayList de jugadores del equipo 1 (pos 0 en el ArrayList
             * de equipos)
             */
            currentPlayerTurn = teams.get(0).players.get(0);
            currentTeamTurn = 0; // Aqui sse guarda la pos del equipo actual que esta jugando en el Arraylist de
                                 // equipos

            gameWindow.turnLabel.setText("Turno de: " + currentPlayerTurn.username);
            gameWindow.turnLabel.setForeground(resolveIconColor());
            return;
        }

        /*
         * Aqui se determina el siguiente jugador a jugar
         */

        int teamSize = teams.get(0).players.size(); // el tamano para todos los equipos

        // Avanzar el jugador al que le corresponde jugar por el equipo en el siguiente
        // turno
        if (teamTurns[currentTeamTurn] == (teamSize - 1)) { // el ultimo jugador de ese equipo es del turno actual
            /*
             * Esto pasa cuando el jugador del turno actual es el ultimo del equipo.
             * Enves de agregar una posicion mas al jugador se reinicia a 0 para que vuelva
             * a empezar desde el primer jugador de ese equipo
             * 
             * De lo contrario habria un NullPointerException
             */
            teamTurns[currentTeamTurn] = 0; // pasar el siguiente turno del equipo al otro jugador
        } else {
            // Aqui se pueden incrementar los turnos del equipo actual sin problema
            teamTurns[currentTeamTurn] += 1;
        }

        // Obtener el siguiente equipo y jugador en jugar
        if (currentTeamTurn == (teams.size() - 1)) {
            /*
             * Esto ocurre cuando el equipo actual que esta jugando es el ultimo del
             * ArrayList de los equipos.
             * Pasa lo mismo que arriba, se reincia a 0 enves de seguir incrementando.
             */
            currentTeamTurn = 0;
        } else {
            currentTeamTurn++;
        }

        /*
         * Aqui se determina el siguiente jugador tomando el siguiente equipo a jugar
         * y buscando en teamTurns [esto es, los turnos internos de cada jugador por
         * equipo]
         * que jugador de ese equipo es el que va a jugar por ese equipo
         */
        Player nextPlayer = teams.get(currentTeamTurn).players.get(teamTurns[currentTeamTurn]);
        JOptionPane.showMessageDialog(gameWindow,
                "Fin del turno de " + currentPlayerTurn.username + ". Pase el control a " + nextPlayer.username);
    }

    private void repartirCartas() {
        for (Team t : teams) {
            for (Player p : t.players) {
                while (p.mazo.size() < maxCards) {
                    Carta cartaSeleccionada = getRandomCardFromMazo();
                    p.mazo.add(cartaSeleccionada);
                }

            }
        }

    }

    private void saveBoardData() {
        int r = 0, c = 0;
        for (Component comp : getComponents()) {
            JLabel lbl = (JLabel) comp;
            boardIcons[r][c] = (ImageIcon) lbl.getIcon();
            boardLabels[r][c] = lbl;
            c++;

            if (c > 9) {
                c = 0;
                r++;
            }
        }

    }
}
