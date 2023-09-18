/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author Jorge Hernandez
 */
public class DataManager {

    private ArrayList<Player> listaUsuarios;
    private static final String USUARIOS_FOLDER = "jugadores";
    private static final String USUARIOS_FILE = USUARIOS_FOLDER + "/usuariosF.bts";

    /*
     * FORMATO:
     * String user
     * String password
     * String completeName
     */
    private Player usuarioLogeado;

    public DataManager() {
        listaUsuarios = new ArrayList<Player>();
        verificarArchivos();
        cargarUsuarios();
    }

    private void verificarArchivos() {
        File folder = new File(USUARIOS_FOLDER);
        if (!folder.exists()) {
            folder.mkdir(); // Crear la carpeta "jugadores"
        }

        File file = new File(USUARIOS_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File logged = new File("jugadores/logged.bts");
        if (!logged.exists()) {
            try {
                logged.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void cargarUsuarios() {
        RandomAccessFile raf = null;
        RandomAccessFile loggedRaf = null;
        try {
            System.out.println("[cargarUsuarios] Cargando usuarios existentes...");
            raf = new RandomAccessFile(USUARIOS_FILE, "rw");
            raf.seek(0);

            while (raf.getFilePointer() < raf.length()) {
                String user = raf.readUTF();
                String password = raf.readUTF();
                String completeName = raf.readUTF();
                long fechaCreacion = raf.readLong();
                int points = raf.readInt();
                String fichaPath = raf.readUTF();
                int selectedPlayers = raf.readInt();

                Player p = new Player(user, password, completeName, fechaCreacion, points, fichaPath, selectedPlayers);
                listaUsuarios.add(p);
            }
            raf.close();

            System.out.println("[cargarUsuarios] Cargando jugador loggeado...");
            loggedRaf = new RandomAccessFile("jugadores/logged.bts", "r");
            loggedRaf.seek(0);

            if (loggedRaf.length() == 0) {
                System.out.println("[cargarUsuarios] No hay jugador loggeado.");
                usuarioLogeado = null;
                loggedRaf.close();
                return;
            }

            String username = loggedRaf.readUTF();
            usuarioLogeado = buscarUsuario(username);
            System.out.println("[cargarUsuarios] Jugador loggead cargado");
            loggedRaf.close();

        } catch (IOException e) {
            verificarArchivos();
            e.printStackTrace();
        } finally {
            try {
                if (raf != null) {
                    raf.close(); // cerrar el archivo para evitar que se corrompa
                }
            } catch (Exception ex) {
            }
        }
    }

    private boolean isValidUsername(String username) {
        for (Player p : listaUsuarios) {
            if (p.getUsername().equals(username)) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<String> getAllAvailableFichas() {
        try {

            ArrayList<String> fichaPaths = new ArrayList<>();

            for (File icon : new File("src/imagenes_fichas").listFiles()) {
                System.out.println("[getAllAvailableFichas] Agregando: " + icon.getName());
                fichaPaths.add(icon.getName());
            }

            // Eliminar los paths que ya esten ocupados
            for (Player p : listaUsuarios) {
                fichaPaths.remove(p.fichaFile.getName());
            }

            if (fichaPaths.isEmpty()) {
                return null;
            }
            return fichaPaths;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getFichasForCombo() {
        try {
            ArrayList<String> fichaNames = new ArrayList<>();

            for (File icon : new File("src/imagenes_fichas").listFiles()) {
                fichaNames.add(icon.getName());
            }
            // Filtrar las que ya estan en uso
            for (Player p : listaUsuarios) {
                if (p.username.equals(usuarioLogeado.username)) {
                    continue;
                }
                fichaNames.remove(p.fichaFile.getName());
            }
            return fichaNames;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getAvailableFichaPath() {
        // Cargar todos los iconos
        return getAllAvailableFichas().get(0);
    }

    public boolean guardarPlayer(String name, String password, String completeName) {
        if (!isValidUsername(name)) {
            return false;
        }

        long fechaCreacion = new Date().getTime();
        Player p = new Player(name, password, completeName, fechaCreacion, 0, getAvailableFichaPath(), 4);
        listaUsuarios.add(p);

        // Guardar en el archivo
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(USUARIOS_FILE, "rw");
            raf.seek(raf.length());
            p.guardarUsuario(raf);

            // Crear carpeta para el usuario
            File userFolder = new File("jugadores/" + name);
            userFolder.mkdirs();
        } catch (IOException e) {
            verificarArchivos();
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public void eliminarPlayer(String username) {
        for (Player p : listaUsuarios) {
            if (p.username.equals(username)) {
                listaUsuarios.remove(p);
            }
        }

        deleteUserFolder(username);
        // Actualizar el archivo de jugadores
        RandomAccessFile raf = null;

        try {
            raf = new RandomAccessFile(USUARIOS_FILE, "rw");
            raf.seek(0);

            for (Player p : listaUsuarios) {
                p.guardarUsuario(raf);
            }
        } catch (IOException e) {
            verificarArchivos();
            e.printStackTrace();
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    private void deleteUserFolder(String username) {
        File userFolder = new File("jugadores/" + username);
        if (!userFolder.exists() && !userFolder.isDirectory()) {
            return;
        }

        for (File f : userFolder.listFiles()) {
            f.delete();
        }

        userFolder.delete();
    }

    public Player buscarUsuario(String username) {
        for (Player p : listaUsuarios) {
            if (p.username.equals(username)) {
                return p;
            }
        }

        return null;
    }

    public int getPlayersConfig() {
        return getUsuarioLogeado().selectedPlayers;
    }

    public void setPlayersConfig(int players) {
        try {
            usuarioLogeado.selectedPlayers = players;
            saveAllPlayers();
            // setUsuarioLogeado(usuarioLogeado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalPlayers() {
        return listaUsuarios.size();
    }

    public ArrayList<Player> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @return the usuarioLogeado
     */
    public Player getUsuarioLogeado() {
        return usuarioLogeado;
    }

    /**
     * @param usuarioLogeado the usuarioLogeado to set
     */
    public void setUsuarioLogeado(Player usuarioLogeado) {

        try {
            File f = new File("jugadores/logged.bts");
            if (f.exists()) {
                f.delete();
                System.out.println("[setUsuarioLogeado] logged.bts eliminado.");
                f.createNewFile();
            }

            if (usuarioLogeado == null) {
                return;
            }

            RandomAccessFile raf = new RandomAccessFile("jugadores/logged.bts", "rw");
            System.out.println("Guardando logged user con:");
            System.out.println(usuarioLogeado.toString());
            System.out.println("-------------------------");
            usuarioLogeado.guardarUsuario(raf);
            this.usuarioLogeado = usuarioLogeado;
            raf.close();

            System.out.println("[setUsuarioLogeado] actualizando lista de usuarios..");
            cargarUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setLoggedUserIcon(String iconName) {
        usuarioLogeado.setIcon(iconName);
        saveAllPlayers();
    }

    public void saveAllPlayers() {
        try {

            File f = new File(USUARIOS_FILE);
            f.delete();

            RandomAccessFile raf = new RandomAccessFile(USUARIOS_FILE, "rw");
            // raf.seek(0);

            for (Player p : listaUsuarios) {
                System.out.println("[saveAllPlayers] Guardando al RAF de jugadores datos: ");
                System.out.println(p.toString());
                p.guardarUsuario(raf);
            }

            raf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
