/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Azalia
 */
public abstract class User implements Serializable {

    public String username, password, nombreCompleto;
    public int puntos, selectedPlayers;
    File fichaFile;
    ImageIcon fichaIcon;
    Date fechaCreacion;

    public User(String username, String password, String nombreCompleto, long fechaCreacion, int pts,
            String fichaFilename, int selectedPlayers) {
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.fechaCreacion = new Date();
        this.fechaCreacion.setTime(fechaCreacion);
        this.puntos = pts;
        // System.out.println("[User] Buscando Icono: " + fichaFilename);
        this.fichaFile = new File("src/imagenes_fichas/" + fichaFilename);
        // System.out.println("Encontrado: " + fichaFile.getPath() + " | " +
        // fichaFile.exists());
        this.selectedPlayers = selectedPlayers;

        try {
            this.fichaIcon = new ImageIcon(ImageIO.read(fichaFile));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obtener la fecha de creación en un formato específico
    // (MM/dd/yyyy)
    public String getFormattedFechaCreacion() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(fechaCreacion);
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        String out = "Username: " + username + "\n";
        out += "Password: " + password + "\n";
        out += "Nombre: " + nombreCompleto + "\n";
        out += "Puntos: " + puntos + "\n";
        out += "Ficha: " + fichaFile.getName() + "\n";
        out += "Setting jugadores: " + selectedPlayers + "\n";

        return out;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setIcon(String iconName) {
        iconName += ".png";
        // System.out.println("[User.setIcon] Buscando Icono: " + iconName);
        this.fichaFile = new File("src/imagenes_fichas/" + iconName);
        // System.out.println("Encontrado: " + fichaFile.getPath() + " | " +
        // fichaFile.exists());

        try {
            this.fichaIcon = new ImageIcon(ImageIO.read(fichaFile));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Funcion abstract para puntos
    public abstract void IncrementarPuntos(int cantidad);

    // Método para guardar los datos de usuario en el archivo
    public void guardarUsuario(RandomAccessFile raf) throws IOException {
        raf.seek(raf.length());
        raf.writeUTF(username);
        raf.writeUTF(password);
        raf.writeUTF(nombreCompleto);
        raf.writeLong(fechaCreacion.getTime());
        raf.writeInt(puntos);
        raf.writeUTF(fichaFile.getName());
        raf.writeInt(selectedPlayers);
    }

}
