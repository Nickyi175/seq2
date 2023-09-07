/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LOGIN;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author MARTI
 */
        
public class Player_Comprobante1 {

    private static Player_In1 userIn = new Player_In1();

    public static boolean prove(String username, String password) {
        if (obtener(username) != null) {
            Creacion_Player1 userActual = obtener(username);
            if (userActual.getUser().equals(username) && userActual.getPass().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean insert(Creacion_Player1 username) {
        return userIn.insert(username);
    }

    public static boolean modify(Creacion_Player1 username) {
        return userIn.modify(username);
    }

    public static boolean erase(String username) {
        return userIn.erase(username);
    }

    public static Creacion_Player1 obtener(String username) {
        return userIn.obtener(username);
    }
    private static void guardarUsuarios() {
        try {
            FileOutputStream fileOut = new FileOutputStream("usuarios.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(userIn.getUsers());
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
