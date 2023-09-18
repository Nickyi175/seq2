/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pruebas;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author Azalia
 */
public class Menu extends javax.swing.JFrame {
    DataManager mgr;

    // Variables para configurar juego
    int teamSize;
    int numTeams;
    int maxCards;
    boolean sameColor = false;
    ArrayList<Team> teams;

    /**
     * Creates new form Menu1
     */
    public Menu() {

        teams = new ArrayList<>();

        initComponents();
        mgr = new DataManager();
        setLocationRelativeTo(this);

        System.out.println("");
        System.out.println("");
        System.out.println("==== Info Usuario Logeado =====");
        System.out.println(mgr.getUsuarioLogeado().toString());
        System.out.println("==================================");
        System.out.println("");
        System.out.println("");
    }

    /*
     * ...
     */

    private void btnJugarMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here: int teamSize = -1;
        int playerSettings = mgr.getPlayersConfig();

        // Calcular equipos y tamano
        switch (playerSettings) {
            case 2:
                teamSize = 1;
                numTeams = 2;
                maxCards = 7;
                break;
            case 3:
                teamSize = 1;
                numTeams = 3;
                maxCards = 6;

                break;
            case 4:
                teamSize = 2;
                numTeams = 2;
                maxCards = 7;

                break;
            case 6:
                teamSize = 2;
                numTeams = 3;
                maxCards = 5;

                break;
            case 8:
                teamSize = 4;
                numTeams = 2;
                maxCards = 4;
                break;
        }

        if (mgr.getTotalPlayers() < mgr.getPlayersConfig()) {
            JOptionPane.showMessageDialog(null, "Necesita al menos " + mgr.getPlayersConfig()
                    + " registrados. Actualmente solo existen " + mgr.getTotalPlayers() + ". Intente mas tarde.");
            return;
        }

        // Cargar los jugadores al combo box

        ArrayList<Player> jugadores = mgr.getListaUsuarios();

        for (Player p : jugadores) {
            p.team = -1;
            playersCombo.addItem(p.username);
        }

        // Cargar los equipos
        teams = new ArrayList<>();
        for (int i = 1; i <= numTeams; i++) {
            teamCombo.addItem("" + i);
            teams.add(new Team(teamSize));
        }
        this.setVisible(false);
        updatePlayersText();

        // Generar Texto para los equipos
        startGameDialog.setMinimumSize(new Dimension(500, 450));
        startGameDialog.setVisible(true);
        startGameDialog.setLocation((int) getSize().getWidth() / 2, (int) getLocation().y);
    }

    private void teamColorCheckboxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        boolean nuevoState = !sameColor;
        System.out.println("Nuevo state: " + nuevoState);
        sameColor = nuevoState;
    }

    private void addPlayerActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        // Cuando se selecciona un jugador del combo box y se le da click en agregar
        Player playerSeleccionado = mgr.buscarUsuario((String) playersCombo.getSelectedItem());
        int selectedTeam = Integer.parseInt((String) teamCombo.getSelectedItem());

        playerSeleccionado.team = selectedTeam;
        playersCombo.removeItem(playersCombo.getSelectedItem());

        // Agregar el jugador al arraylist del team
        teams.get(selectedTeam - 1).add(playerSeleccionado);
        System.out.println(teams.get(selectedTeam - 1).size());
        System.out.println(teams.get(selectedTeam - 1).isFull());
        if (teams.get(selectedTeam - 1).isFull()) {
            teamCombo.removeItem("" + selectedTeam);
        }
        updatePlayersText();

        if (areTeamsFull()) {

            if (!(currentPlayerInGame())) {
                JOptionPane.showMessageDialog(startGameDialog,
                        "El jugador (" + mgr.getUsuarioLogeado().username + ") actual debe pertenecer a un equipo.");
                startGameDialog.dispose();
                btnJugarMousePressed(null);
                return;
            }

            startGameDialog.setVisible(false);
            int op = JOptionPane.showConfirmDialog(startGameDialog,
                    "Esta seguro que desea continuar con estos equipos?\n\n" + updatePlayersText());
            if (op == 0) {
                // START GAME
                new GameWindow(teams, maxCards, sameColor).setVisible(true);
                dispose();
            }
            if (op == 1) {
                // Volver a presionar el boton de jugar
                startGameDialog.dispose();
                btnJugarMousePressed(null);
            }
            if (op == 2) {
                startGameDialog.dispose();
                setVisible(true);
            }
        }
    }

    private boolean currentPlayerInGame() {
        Player currentPlayer = mgr.getUsuarioLogeado();
        for (Team t : teams) {
            for (Player p : t.players) {
                if (currentPlayer == p)
                    return true;
            }
        }

        return false;

    }

    private String updatePlayersText() {
        String out = "";

        for (int i = 0; i < teams.size(); i++) {
            Team equipo = teams.get(i);

            String lleno = (teams.get(i).players.size() == teamSize) ? " (LLENO)" : "";
            out += "Equipo #" + (i + 1) + " - " + equipo.size() + " integrantes" + lleno + "\n";

            for (Player p : equipo.players) {
                out += "\t" + p.username + "\n";
            }
        }

        playersText.setText(out);
        return out;
    }

    private boolean areTeamsFull() {
        for (Team t : teams) {
            if (!t.isFull())
                return false;
        }
        return true;
    }

    // Variables declaration - do not modify
    private javax.swing.JButton addPlayer;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnJugar;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> playersCombo;
    public javax.swing.JPanel playersContainer;
    private javax.swing.JTextArea playersText;
    private javax.swing.JDialog startGameDialog;
    private javax.swing.JCheckBox teamColorCheckbox;
    private javax.swing.JComboBox<String> teamCombo;
    // End of variables declaration
}
