/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LOGIN;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class Player_In1 {
    public List<Creacion_Player1>users;

    public Player_In1() {
    users=new ArrayList<>();
    }
    
    public List<Creacion_Player1> getUsers() {
        return users;
    }
    
    public int search(String user){
    int a=-1;    
    for (int i=0;i<users.size();i++){
        if (users.get(i).getUser().equals(user)){
        a=i;
        break;
        }
    }
    return a;
    }
    
    public boolean insert(Creacion_Player1 user){
    if (search(user.getUser())==-1){
    users.add(user);
    return true;
    }else{
        return false;
    }
    }
    
    public boolean modify(Creacion_Player1 user){
    if (search(user.getUser())!=-1){
    Creacion_Player1 userA=obtener(user.getUser());
    userA.setPass(user.getPass());
    return true;
    }else{
    return false;
    }   
    }
    
    public boolean erase(String user){
    if (search(user)!=-1){
    users.remove(search(user));
    return true;
    }else{
    return false;
    }    
    } 
    
    public Creacion_Player1 obtener(String user){
        if(search(user)!=-1){
           return users.get(search(user));
        }else{
           return null;
        }
    } 
}

