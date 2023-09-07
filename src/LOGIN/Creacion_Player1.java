/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LOGIN;

public class Creacion_Player1 {
    private String User;
    private String Pass;
    public int puntos;
    
    public Creacion_Player1(String User, String Pass) {
        this.User = User;
        this.Pass = Pass;
        puntos=0;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getUser() {
        return User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPuntos(int puntos){
        this.puntos = puntos;    
    }
            
    public void setUser(String User) {
        this.User = User;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }
    
    
    
}
