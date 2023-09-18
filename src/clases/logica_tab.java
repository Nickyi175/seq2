/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import clases.cartas;
import clases.call_png_baraja;
import clases.call_png_fichas;


import java.awt.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;


public class logica_tab {
   public JButton[][] matrizButtonsUI;//ACCEDE A LOS BOTONES
    public cartas[][] matrizBotones; //de JButton a Pieza por valores
    private call_png_baraja baraja; 
    public int s=1;
    //NOMBRE JUGADORS
    public String t1, t2, t3, t4, t5, t6, t7, t8;
        public int turno=1;
        //POR SI NO TIENE ASIGNADO
        private ArrayList<String> todoColores;
     //colores JUGADORES
    public String color1, color2, color3, color4, color5, color6, color7, color8;
    public String fichaActual="ROJO";
    public String miembros1="";
    public String miembros2="";
    public String miembros3="";
    public String posesion="EQUIPO 1";
    registro r = new registro();
    private boolean cambioValido = false;
    public  String elegida="a";
    public int secuencia1=0,secuencia2=0,secuencia3=0;
   

    public logica_tab() {
        matrizButtonsUI = new JButton[10][10]; 
        baraja = new call_png_baraja(); 
        turno=1;
        t1=""; t2=""; t3=""; t4="";
        t5=""; t6=""; t7=""; t8="";
        
        fichaActual="ROJO";
    } 
    public void GridLayout(JPanel tab) {
        int filas=10;
        int col=10;
        GridLayout gridLayout=new GridLayout(filas, col);
        tab.setLayout(gridLayout);
        matrizBotones=new cartas[filas][col];
        posicionarTablero();
        tab.setOpaque(false);
        
        todoColores = new ArrayList<>();
        String[] coloresDisponibles = {"ROJO","VERDE","AZUL","AMARILLO", "AQUA", "AZUL ELECTRICO", "BORGOÑA", "CELESTE", "CEREZA", "FUSCIA", "LILA", "LIMA", "MANGO", "MORADO", "MOSTAZA", "NARANJA", "OLIVA", "ROSA", "TURQUESA", "VINO", "ZAFIRO"};

        for (String color : coloresDisponibles) {
            todoColores.add(color);
        }
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < col; j++) {
                final cartas carta=matrizBotones[i][j]; 
                JButton button=new JButton(); //CREAR JBUTTON
                matrizButtonsUI[i][j]=button;
                tab.add(button);
                
                int ancho=(int)(1.4*40);
                int largo=(int)(1.4*40);
                
                button.setPreferredSize(new Dimension(ancho, largo));
                button.setOpaque(false);
                button.setContentAreaFilled(false);
             //button.setBorder(BorderFactory.createLineBorder(Color.red));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                         p( button, carta.getFila(),carta.getColumna()); 
//                         System.out.println(miembros1);
//                         System.out.println(miembros2);
//                         System.out.println(miembros3);
                    }
                });
              //button.setBackground(Color.black);
            }
        }
    }
    private void secuenciaHorizontal(int fila, int columna) {
        if (fila < 0 || fila >= 10 || columna < 0 || columna >= 10) {
            System.out.println("...");
            return;
        }
 matrizBotones[0][0].setPosesion(posesion);
            matrizBotones[0][9].setPosesion(posesion);
            matrizBotones[9][0].setPosesion(posesion);
            matrizBotones[9][9].setPosesion(posesion);
        String[] horizontal = new String[10]; 

        for (int i = 0; i < 10; i++) {
            horizontal[i] = matrizBotones[fila][i].getPosesion();
        }

        int numSeguidos = 0;
        if (horizontal != null) {
         //   System.out.println("Horizontal:");

            for (int i = 0; i < horizontal.length; i++) {
                String valor = horizontal[i];

                if (valor != null && (valor.equals(posesion) ||  valor.equals(posesion.replaceAll("\\s", "")+".") ||  valor.equals(posesion.replaceAll("\\s", "")+",") ||  valor.equals(posesion.replaceAll("\\s", "")+"+"))) {
                    numSeguidos++;
                } else {
                    numSeguidos = 0; 
                }
                if (numSeguidos == 5) {
                    String que = "";
                    if(posesion.equals("EQUIPO 1")){
                        secuencia1++;
                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                        for (int j = 0; j < 5; j++) {
                            matrizBotones[fila][i - j].setPosesion("EQUIPO1");
                            String tipo = matrizBotones[fila][i - j].getTipo();
                            JLabel label = new JLabel();
                            label.setIcon(call_png_baraja.obtenerFicha(tipo));
                            panel.add(label);
                            que += tipo + ", ";
                        }
                        JOptionPane.showMessageDialog(null, panel, "EQUIPO 1 ha hecho UNA SECUENCIA HORIZONTAL\n"+que, JOptionPane.INFORMATION_MESSAGE);break; 

                    }else if(posesion.equals("EQUIPO 2")){
                        secuencia2++;
                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                        for (int j = 0; j < 5; j++) {
                            matrizBotones[fila][i - j].setPosesion("EQUIPO2");
                            String tipo = matrizBotones[fila][i - j].getTipo();
                            JLabel label = new JLabel();
                            label.setIcon(call_png_baraja.obtenerFicha(tipo));
                            panel.add(label);
                            que += tipo + ", ";
                        }
                        JOptionPane.showMessageDialog(null, panel, "EQUIPO 2 ha hecho UNA SECUENCIA HORIZONTAL\n"+que, JOptionPane.INFORMATION_MESSAGE);break; 

                    }else if(posesion.equals("EQUIPO 3")){
                        secuencia3++;
                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                        for (int j = 0; j < 5; j++) {
                            matrizBotones[fila][i - j].setPosesion("EQUIPO3");
                            String tipo = matrizBotones[fila][i - j].getTipo();
                            JLabel label = new JLabel();
                            label.setIcon(call_png_baraja.obtenerFicha(tipo));
                            panel.add(label);
                            que += tipo + ", ";
                        }
                        JOptionPane.showMessageDialog(null, panel, "EQUIPO 3 ha hecho UNA SECUENCIA HORIZONTAL\n"+que, JOptionPane.INFORMATION_MESSAGE);break; 
                    }
                }
            }
        } else {
            System.out.println("A MIMIR");
        }
    }

   private void secuenciaVertical(int columna){
        int numSeguidos=0;
        String valorAnterior = "";
        boolean secuenciaFormada = false;

        for (int i = 0; i < 10; i++) {
            matrizBotones[0][0].setPosesion(posesion);
            matrizBotones[0][9].setPosesion(posesion);
            matrizBotones[9][0].setPosesion(posesion);
            matrizBotones[9][9].setPosesion(posesion);
            String valorActual = matrizBotones[i][columna].getPosesion();
          //  System.out.println(s);
            
            String nee=posesion.replaceAll("\\s", "");
            if (valorActual.equals(valorAnterior)  && (valorActual.equals(posesion)
                   || valorActual.equals(nee+",")|| valorActual.equals(posesion.replaceAll("\\s", ""))|| valorActual.equals(nee+"+")))   {
                numSeguidos++;

            } else {
                numSeguidos = 1;
                valorAnterior = valorActual;
                secuenciaFormada = false;
            }
            if(numSeguidos==5){
                String que="";
                secuenciaFormada = true;
                if(posesion.equals("EQUIPO 1")){
                    secuencia1++;
                    for (int j = i; j > i - 5; j--) {
                        
                            matrizBotones[j][columna].setPosesion("EQUIPO1.");
                            que+=matrizBotones[j][columna].getTipo()+",";
                    }
                    JOptionPane.showMessageDialog(null, "EQUIPO 1 ha hecho UNA SECUENCIA VERTICAL\n"+que, "SECUENCIA", JOptionPane.INFORMATION_MESSAGE);
                }else if(posesion.equals("EQUIPO 2")){
                    secuencia2++;
                    for (int j = i; j > i - 5; j--) {
                            matrizBotones[j][columna].setPosesion("EQUIPO2.");
                            que+=matrizBotones[j][columna].getTipo()+",";
                        
                    }
                    JOptionPane.showMessageDialog(null, "EQUIPO 2 ha hecho UNA SECUENCIA VERTICAL\n"+que, "SECUENCIA", JOptionPane.INFORMATION_MESSAGE);
                }else if(posesion.equals("EQUIPO 3")){
                    secuencia3++;
                    for (int j = i; j > i - 5; j--) {
                            matrizBotones[j][columna].setPosesion("EQUIPO3.");
                            que+=matrizBotones[j][columna].getTipo()+",";
                        
                    }
                    JOptionPane.showMessageDialog(null, "EQUIPO 3 ha hecho UNA SECUENCIA VERTICAL\n"+que, "SECUENCIA", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
   }
  

// Función para obtener la diagonal de izquierda a derecha
    private void obtenerDiagonalIzquierdaDerecha(int fila, int columna) {
           
        if (fila < 0 || fila >= 10 || columna < 0 || columna >= 10) {
            System.out.println("NOOO LA DIAGONAL DERECHA IZQUIERDA");
            return;
        }
        matrizBotones[0][0].setPosesion(posesion);
        matrizBotones[0][9].setPosesion(posesion);
        matrizBotones[9][0].setPosesion(posesion);
        matrizBotones[9][9].setPosesion(posesion);

        String[] diagonal = new String[10]; 

        int delta = Math.min(fila, columna); 
        fila -= delta;
        columna -= delta;

        for (int i = 0; i < 10 && fila + i < 10 && columna + i < 10; i++) {
            diagonal[i] = matrizBotones[fila + i][columna + i].getPosesion();

        }
        int numSeguidos=0;
        if (diagonal != null) {
       // System.out.println("Diagonal de izquierda a derecha:");

            for (int i = 0; i < diagonal.length; i++) {
                String valor = diagonal[i];

                if (valor != null && (valor.equals(posesion) || valor.equals(posesion.replaceAll("\\s", "")) || valor.equals(posesion.replaceAll("\\s", "") + ".") || valor.equals(posesion.replaceAll("\\s", "")+"+")))  {
                    numSeguidos++;
                } else {
                    numSeguidos = 0; 
                }

              //  System.out.println(valor);

                if (numSeguidos == 5) {
                    String que="";
                    if(posesion.equals("EQUIPO 1")){
                        secuencia1++;
                        for (int j = 0; j < 5; j++) {
                            matrizBotones[fila + i - j][columna + i - j].setPosesion("EQUIPO1,");
                            que+=matrizBotones[fila + i - j][columna + i - j].getTipo()+", ";
                        }
                        JOptionPane.showMessageDialog(null, "EQUIPO 1 ha hecho UNA SECUENCIA DIAGONAL\n"+que, "SECUENCIA", JOptionPane.INFORMATION_MESSAGE);
                        break; 
                    }else if(posesion.equals("EQUIPO 2")){
                        secuencia2++;

                        for (int j = 0; j < 5; j++) {
                            matrizBotones[fila + i - j][columna + i - j].setPosesion("EQUIPO2,");
                            que+=matrizBotones[fila + i - j][columna + i - j].getTipo()+", ";
                        }
                        JOptionPane.showMessageDialog(null, "EQUIPO 2 ha hecho UNA SECUENCIA DIAGONAL\n"+que, "SECUENCIA", JOptionPane.INFORMATION_MESSAGE);
                        break; 
                    }else if(posesion.equals("EQUIPO 3")){
                        secuencia3++;

                        for (int j = 0; j < 5; j++) {
                            matrizBotones[fila + i - j][columna + i - j].setPosesion("EQUIPO3,");
                            que+=matrizBotones[fila + i - j][columna + i - j].getTipo()+", ";
                        }
                        JOptionPane.showMessageDialog(null, "EQUIPO 3 ha hecho UNA SECUENCIA DIAGONAL\n"+que, "SECUENCIA", JOptionPane.INFORMATION_MESSAGE);
                        break; 
                    }
                }
            }
        } else {
           // System.out.println("...");
        }
    }

    private void obtenerDiagonalDerechaIzquierda(int fila, int columna) {
        if (fila < 0 || fila >= 10 || columna < 0 || columna >= 10) {
            System.out.println("...");
            return;
        }
        matrizBotones[0][0].setPosesion(posesion);
        matrizBotones[0][9].setPosesion(posesion);
        matrizBotones[9][0].setPosesion(posesion);
        matrizBotones[9][9].setPosesion(posesion);

        String[] diagonal = new String[10]; 

        int delta = Math.min(fila, 9 - columna); 
        fila -= delta;
        columna += delta;

        for (int i = 0; i < 10 && fila + i < 10 && columna - i >= 0; i++) {
            diagonal[i] = matrizBotones[fila + i][columna - i].getPosesion();
        }

        int numSeguidos = 0;
        if (diagonal != null) {
          //  System.out.println("Diagonal de derecha a izquierda:");

            for (int i = 0; i < diagonal.length; i++) {
                String valor = diagonal[i];

                if (valor != null && (valor.equals(posesion) || valor.equals(posesion.replaceAll("\\s", "")) || valor.equals(posesion.replaceAll("\\s", "") + ".") || valor.equals(posesion.replaceAll("\\s", "") + ",")))  {
                    numSeguidos++;
                } else {
                    numSeguidos = 0; 
                }

                if (numSeguidos == 5) {
                    String que = "";
                    if(posesion.equals("EQUIPO 1")){
                        secuencia1++;
                        for (int j = 0; j < 5; j++) {
                            matrizBotones[fila + i - j][columna - i + j].setPosesion("EQUIPO1+");
                            que += matrizBotones[fila + i - j][columna - i + j].getTipo() + ", ";
                        }
                        JOptionPane.showMessageDialog(null, "EQUIPO 1 ha hecho UNA SECUENCIA DIAGONAL\n"+que, "SECUENCIA", JOptionPane.INFORMATION_MESSAGE);
                        break; 
                    }else if(posesion.equals("EQUIPO 2")){
                        secuencia2++;

                        for (int j = 0; j < 5; j++) {
                            matrizBotones[fila + i - j][columna - i + j].setPosesion("EQUIPO2+");
                            que += matrizBotones[fila + i - j][columna - i + j].getTipo() + ", ";
                        }
                        JOptionPane.showMessageDialog(null, "EQUIPO 2 ha hecho UNA SECUENCIA DIAGONAL\n"+que, "SECUENCIA", JOptionPane.INFORMATION_MESSAGE);
                        break; 
                    }else if(posesion.equals("EQUIPO 3")){
                        secuencia3++;

                        for (int j = 0; j < 5; j++) {
                            matrizBotones[fila + i - j][columna - i + j].setPosesion("EQUIPO3+");
                            que += matrizBotones[fila + i - j][columna - i + j].getTipo() + ", ";
                        }
                        JOptionPane.showMessageDialog(null, "EQUIPO 3 ha hecho UNA SECUENCIA DIAGONAL\n"+que, "SECUENCIA", JOptionPane.INFORMATION_MESSAGE);
                        break; 
                    }
                }
            }
        } else {
          //  System.out.println("valio");
        }
    }

    private void p(JButton button, int fila, int columa) {
        cartas carta = matrizBotones[fila][columa];
        matrizBotones[0][0].setPosesion("NO APLICA");
        matrizBotones[0][9].setPosesion("NO APLICA");
        matrizBotones[9][0].setPosesion("NO APLICA");
        matrizBotones[9][9].setPosesion("NO APLICA");
        if(elegida.equals("b")){
            JOptionPane.showMessageDialog(null, "NO HAS ELEGIDO UNA CARTA", "Informacion de Carta", JOptionPane.WARNING_MESSAGE);
        }else if(carta.getPosesion().equals("NADIE") && !carta.getTipo().equals("ESQUINA") ) {//
            button.setIcon(call_png_fichas.obtenerFicha(fichaActual));
            carta.setPosesion(posesion);
            
            secuenciaHorizontal( fila,columa);
            secuenciaVertical( columa);
            obtenerDiagonalIzquierdaDerecha( fila, columa) ;
            obtenerDiagonalDerechaIzquierda( fila,columa) ;
            try{
                
                if (secuencia1 == 2) {
                    if(r.getCantidadJ()==2){
                        String smsG="GANASTE: TURNO 1 {"+t1+"} Victoria sobre  TURNO 2 {"+t2+"}";
                        r.agregarReportesTodos(t1, smsG);
                        JOptionPane.showMessageDialog(null, smsG, "Informacion FINAL", JOptionPane.WARNING_MESSAGE);
                        String smsV="PERDISTE: TURNO 1 {"+t1+"} Victoria sobre TURNO 2 {"+t2+"}";
                        r.agregarReportesTodos(t2, smsV);
                        
                    }else if(r.getCantidadJ()==3){
                        String smsG="GANASTE: TURNO 1 {"+t1+"} Victoria sobre  TURNO 2 {"+t2+"} y TURNO 3{"+t3+"}";
                        r.agregarReportesTodos(t1, smsG);
                        JOptionPane.showMessageDialog(null, smsG, "Informacion FINAL", JOptionPane.WARNING_MESSAGE);
                        String smsV="PERDISTE: TURNO 1 {"+t1+"} Victoria sobre  TURNO 2 {"+t2+"} y TURNO 3{"+t3+"}";
                        r.agregarReportesTodos(t2, smsV);
                        r.agregarReportesTodos(t3, smsV);

                    }else if(r.getCantidadJ()== 4 || r.getCantidadJ()==8){
                        String smsG="GANASTE: EQUIPO 1 {"+miembros1+"} Victoria sobre EQUIPO 2 {"+miembros2+"}";
                        String[] miembros = miembros1.split("-");
                        JOptionPane.showMessageDialog(null, smsG, "Informacion FINAL", JOptionPane.WARNING_MESSAGE);
                        for (String nombre : miembros) {
                            r.agregarReportesTodos(nombre, smsG);
                        }
                        String smsV="PERDISTE: EQUIPO 1 {"+miembros1+"} Victoria sobre EQUIPO 2 {"+miembros2+"}";
                        String[] miembro2 = miembros2.split("-");
                        for (String nombre2 : miembro2) {
                            r.agregarReportesTodos(nombre2, smsV);
                        }
                    }else if(r.getCantidadJ()==6){
                        String smsG="GANASTE: EQUIPO 1 {"+miembros1+"} Victoria sobre EQUIPO 2 {"+miembros2+"} y EQUIPO 3{"+miembros3+"}";
                        String[] miembros = miembros1.split("-");
                        JOptionPane.showMessageDialog(null, smsG, "Informacion FINAL", JOptionPane.WARNING_MESSAGE);
                        for (String nombre : miembros) {
                            r.agregarReportesTodos(nombre, smsG);
                        }
                        String smsV="PERDISTE: EQUIPO 1 {"+miembros1+"} Victoria sobre EQUIPO 2 {"+miembros2+"} y EQUIPO 3{"+miembros3+"}";
                        String[] miembro2 = miembros2.split("-");
                        for (String nombre2 : miembro2) {
                            r.agregarReportesTodos(nombre2, smsV);
                        }
                        String[] miembro3 = miembros3.split("-");
                        for (String nombre3 : miembro3) {
                            r.agregarReportesTodos(nombre3, smsV);
                        }
                    }
                        
                } else if (secuencia2 == 2) {
                    if(r.getCantidadJ()==2){
                        String smsG="GANASTE: TURNO 2 {"+t2+"} Victoria sobre  TURNO 1 {"+t1+"}";
                        r.agregarReportesTodos(t2, smsG);
                        JOptionPane.showMessageDialog(null, smsG, "Informacion FINAL", JOptionPane.WARNING_MESSAGE);
                        String smsV="PERDISTE: TURNO 2 {"+t2+"} Victoria sobre TURNO 1 {"+t1+"}";
                        r.agregarReportesTodos(t1, smsV);
                        
                    }else if(r.getCantidadJ()==3){
                        String smsG="GANASTE: TURNO 2 {"+t2+"} Victoria sobre  TURNO 1 {"+t1+"} y TURNO 3{"+t3+"}";
                        r.agregarReportesTodos(t2, smsG);
                        JOptionPane.showMessageDialog(null, smsG, "Informacion FINAL", JOptionPane.WARNING_MESSAGE);
                        String smsV="PERDISTE: TURNO 2 {"+t2+"} Victoria sobre  TURNO 1 {"+t1+"} y TURNO 3{"+t3+"}";
                        r.agregarReportesTodos(t1, smsV);
                        r.agregarReportesTodos(t3, smsV);

                    }else if(r.getCantidadJ()== 4 || r.getCantidadJ()==8){
                        String smsG="GANASTE: EQUIPO 2 {"+miembros2+"} Victoria sobre EQUIPO 1 {"+miembros1+"}";
                        String[] miembros = miembros2.split("-");
                        JOptionPane.showMessageDialog(null, smsG, "Informacion FINAL", JOptionPane.WARNING_MESSAGE);
                        for (String nombre : miembros) {
                            r.agregarReportesTodos(nombre, smsG);
                        }
                        String smsV="PERDISTE: EQUIPO 2 {"+miembros2+"} Victoria sobre EQUIPO 1 {"+miembros1+"}";
                        String[] miembro2 = miembros1.split("-");
                        for (String nombre2 : miembro2) {
                            r.agregarReportesTodos(nombre2, smsV);
                        }
                    }else if(r.getCantidadJ()==6){
                        String smsG="GANASTE: EQUIPO 2 {"+miembros2+"} Victoria sobre EQUIPO 1 {"+miembros1+"} y EQUIPO 3{"+miembros3+"}";
                        String[] miembros = miembros2.split("-");
                        JOptionPane.showMessageDialog(null, smsG, "Informacion FINAL", JOptionPane.WARNING_MESSAGE);
                        for (String nombre : miembros) {
                            r.agregarReportesTodos(nombre, smsG);
                        }
                        String smsV="PERDISTE: EQUIPO 2 {"+miembros2+"} Victoria sobre EQUIPO 1 {"+miembros1+"} y EQUIPO 3{"+miembros3+"}";
                        String[] miembro2 = miembros1.split("-");
                        for (String nombre2 : miembro2) {
                            r.agregarReportesTodos(nombre2, smsV);
                        }
                        String[] miembro3 = miembros3.split("-");
                        for (String nombre3 : miembro3) {
                            r.agregarReportesTodos(nombre3, smsV);
                        }
                    }
                } else if (secuencia3 == 2) {
                     if(r.getCantidadJ()==3){
                        String smsG="GANASTE: TURNO 3 {"+t3+"} Victoria sobre  TURNO 1 {"+t1+"} y TURNO 2{"+t2+"}";
                        r.agregarReportesTodos(t3, smsG);
                        JOptionPane.showMessageDialog(null, smsG, "Informacion FINAL", JOptionPane.WARNING_MESSAGE);
                        String smsV="PERDISTE: TURNO 3 {"+t3+"} Victoria sobre  TURNO 1 {"+t1+"} y TURNO 2{"+t2+"}";
                        r.agregarReportesTodos(t1, smsV);
                        r.agregarReportesTodos(t2, smsV);

                    }else if(r.getCantidadJ()==6){
                        String smsG="GANASTE: EQUIPO 3 {"+miembros3+"} Victoria sobre EQUIPO 1 {"+miembros1+"} y EQUIPO 2{"+miembros2+"}";
                        String[] miembros = miembros3.split("-");
                        JOptionPane.showMessageDialog(null, smsG, "Informacion FINAL", JOptionPane.WARNING_MESSAGE);
                        for (String nombre : miembros) {
                            r.agregarReportesTodos(nombre, smsG);
                        }
                        String smsV="PERDISTE: EQUIPO 3 {"+miembros3+"} Victoria sobre EQUIPO 1 {"+miembros1+"} y EQUIPO 2{"+miembros2+"}";
                        String[] miembro2 = miembros1.split("-");
                        for (String nombre2 : miembro2) {
                            r.agregarReportesTodos(nombre2, smsV);
                        }
                        String[] miembro3 = miembros2.split("-");
                        for (String nombre3 : miembro3) {
                            r.agregarReportesTodos(nombre3, smsV);
                        }
                    }
                } else {
                        cambioValido = true;
                        elegida = "a";
                    }
                
            }catch(IOException e){
                
            }
        }else{
            if(carta.getTipo().equals("ESQUINA")){
                JOptionPane.showMessageDialog(null, "No puedes poner NADA aqui", "Informacion de Carta", JOptionPane.WARNING_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "NO COINCIDE CON TU ELECCION", "Informacion de Carta", JOptionPane.WARNING_MESSAGE);
            mostrarInformacionPieza(carta);
        }  
        
    }

    public synchronized boolean seRealizoAccion() {
        if (cambioValido) {
            cambioValido = false; 
            return true;
        } else {
            return false;
        }
    }
    private void mostrarInformacionPieza(cartas carta) {
        if (carta!=null) {
            String info="Tipo: "+carta.getTipo()+"\nValor: "+carta.getValor()+"\nPosesion: "+carta.getPosesion()+"\nFila:"+carta.getFila()+"\nColumna: "+carta.getColumna();
                JOptionPane.showMessageDialog(null, info, "Informacion de Carta", JOptionPane.INFORMATION_MESSAGE);
        }else{
            
        }
    }

    private void posicionarTablero(){
    //esquinas 
        String posesionInicialE="NO APLICA";
        matrizBotones[0][0]=new cartas("ESQUINA", 0, posesionInicialE,0,0);
        matrizBotones[0][9]=new cartas("ESQUINA", 0, posesionInicialE,0,9);
        matrizBotones[9][0]=new cartas("ESQUINA", 0, posesionInicialE,9,0);
        matrizBotones[9][9]=new cartas("ESQUINA", 0, posesionInicialE,9,9);
    //columan 0
        int valorPicaColumna0=9;//columna 0 fila del 1 al 8
        String posesionInicial="NADIE";
        for (int f = 1; f < 9; f++) {
            matrizBotones[f][0]=new cartas("PICA", valorPicaColumna0, posesionInicial,f,0);
            valorPicaColumna0--;//llega hasta 2
        }
    //columna 9
        int valorDiamanteColumna9=6;//columna 9 fila del 1 al 7
        for (int f = 1; f < 8; f++) {
            matrizBotones[f][9]=new cartas("DIAMANTE"+valorDiamanteColumna9, valorDiamanteColumna9, posesionInicial,f,9);
            valorDiamanteColumna9++;//llega hasta 2
        }
        matrizBotones[8][9]=new cartas("DIAMANTE1", 1, posesionInicial,8,9);
    //fila 9
        int valorTrebolFila9=6;//fila 9 columna 1 al 7
        for (int c = 1; c < 8; c++) {
            matrizBotones[9][c]=new cartas("TREBOL"+valorTrebolFila9, valorTrebolFila9, posesionInicial,9,c);
            valorTrebolFila9++;//llega hasta 12
        }
        matrizBotones[9][8]=new cartas("TREBOL1", 1, posesionInicial,9,8);
    //fila 0 
        int valorDimanteFila0=2;//fila 0 columna  5 al 8
        for (int c = 5; c < 9; c++) {
            matrizBotones[0][c]=new cartas("DIAMANTE"+valorDimanteFila0, valorDimanteFila0, posesionInicial,0,c);
            valorDimanteFila0++;//llega hasta 5
        }
        matrizBotones[0][1]=new cartas("PICA10", 10, posesionInicial,0,1);
        matrizBotones[0][2]=new cartas("PICA11", 11, posesionInicial,0,2);
        matrizBotones[0][3]=new cartas("PICA12", 12, posesionInicial,0,3);
        matrizBotones[0][4]=new cartas("PICA1", 1, posesionInicial,0,4);
    //fila 1
        int valorCorazonFila1=10;//fila 1 columna  1 al 8
        for (int c = 1; c < 9; c++) {
            matrizBotones[1][c]=new cartas("CORAZON"+valorCorazonFila1, valorCorazonFila1, posesionInicial,1,c);
            valorCorazonFila1--;//llega hasta 3
        }
    //fila 8
        int valorPicaFila8=12;//fila 8 columna 3 al 8 
        for (int c = 3; c < 9; c++) {
            matrizBotones[8][c]=new cartas("PICA"+valorPicaFila8, valorPicaFila8, posesionInicial,8,c);
            valorPicaFila8--;//llega hasta 7
        }
        matrizBotones[8][2]=new cartas("PICA1", 1, posesionInicial,8,2);    
    //fila 2
        int valorDiamanteFila2=7;//fila 2 columna 2 al 7 
        for (int c = 2; c < 8; c++) {
            matrizBotones[2][c]=new cartas("DIAMANTE"+valorDiamanteFila2, valorDiamanteFila2, posesionInicial,2,c);
            valorDiamanteFila2++;//llega hasta 12
        }
        matrizBotones[2][1]=new cartas("CORAZON11", 11, posesionInicial,2,1);  
        matrizBotones[2][8]=new cartas("CORAZON2", 2, posesionInicial,2,8);
    //columna 1 faltantes desde fila 3 al 8    
        matrizBotones[3][1]=new cartas("CORAZON12", 12, posesionInicial,3,1);  
        matrizBotones[4][1]=new cartas("CORAZON1", 1, posesionInicial,4,1);
        matrizBotones[5][1]=new cartas("TREBOL2", 2, posesionInicial,5,1);  
        matrizBotones[6][1]=new cartas("TREBOL3", 3, posesionInicial,6,1);
        matrizBotones[7][1]=new cartas("TREBOL4", 4, posesionInicial,7,1);  
        matrizBotones[8][1]=new cartas("TREBOL5", 5, posesionInicial,8,1);
    //columna 2 fila 3 al 7  
        int valorDiamanteColumna2=6;
        for (int f = 3; f < 8; f++) {
            matrizBotones[f][2]=new cartas("DIAMANTE"+valorDiamanteColumna2, valorDiamanteColumna2, posesionInicial,f,2);
            valorDiamanteColumna2--;//llega hasta 2
        }
    //fila 7 columna 3 al 7
        int valorTrebolFila7=6;
        for (int c = 3; c < 8; c++) {
            matrizBotones[7][c]=new cartas("TREBOL"+valorTrebolFila7, valorTrebolFila7, posesionInicial,7,c);
            valorTrebolFila7++;//llega hasta 10 
        }   
    //columna 3 faltantes desde fila 3 al 6     
        int valorTrebolColumna3=2;
        for (int f = 3; f < 7; f++) {
            matrizBotones[f][3]=new cartas("TREBOL"+valorTrebolColumna3,  valorTrebolColumna3, posesionInicial,f,3);
            valorTrebolColumna3++;//llega hasta 5
        } 
    //columna 4 faltantes desde fila 3 al 6    
        matrizBotones[3][4]=new cartas("CORAZON1", 1, posesionInicial,3,4);  
        matrizBotones[4][4]=new cartas("CORAZON4", 4, posesionInicial,4,4);
        matrizBotones[5][4]=new cartas("CORAZON5", 5, posesionInicial,5,4);  
        matrizBotones[6][4]=new cartas("CORAZON6", 6, posesionInicial,6,4); 
    //columna 5 faltantes desde fila 3 al 6    
        matrizBotones[3][5]=new cartas("CORAZON12", 12, posesionInicial,3,5);  
        matrizBotones[4][5]=new cartas("CORAZON3", 3, posesionInicial,4,5);
        matrizBotones[5][5]=new cartas("CORAZON2", 2, posesionInicial,5,5);  
        matrizBotones[6][5]=new cartas("CORAZON7", 7, posesionInicial,6,5);
    //columna 6 faltantes desde fila 3 al 6    
        int valorCorazonColumna6=11;
        for (int f = 3; f < 7; f++) {
            matrizBotones[f][6]=new cartas("CORAZON"+valorCorazonColumna6, valorCorazonColumna6, posesionInicial,f,6);
            valorCorazonColumna6--;//llega hasta 8
        }
    //columna 7 faltantes desde fila 3 al 6    
        matrizBotones[3][7]=new cartas("DIAMANTE1", 1, posesionInicial,3,7);  
        matrizBotones[4][7]=new cartas("TREBOL1", 1, posesionInicial,4,7);
        matrizBotones[5][7]=new cartas("TREBOL12", 12, posesionInicial,5,7);  
        matrizBotones[6][7]=new cartas("TREBOL11", 11, posesionInicial,6,7);    
    //columna 8 faltantes desde fila 3 al 7   
        int valorPicaColumna8=2;
        for (int f = 3; f < 8; f++) {
            matrizBotones[f][8]=new cartas("PICA"+valorPicaColumna8,   valorPicaColumna8, posesionInicial,f,8);
            valorPicaColumna8++;//llega hasta 6
        }   
    }
    //TODO LO QUE TENGA QUE VER CON TURNOS
    public void cambiorturno(){
        registro r=new registro();
        try {
            if(r.getCantidadJ()==2){
                if(turno==1){       posesion="EQUIPO 2";turno=2; fichaActual=color2;
                }else{              posesion="EQUIPO 1";turno=1; fichaActual=color1;
                }
            }else if(r.getCantidadJ()==3){
                if(turno==1){       posesion="EQUIPO 2";turno=2;fichaActual=color2;
                }else if(turno==2){ posesion="EQUIPO 3";turno=3;fichaActual=color3;
                }else if(turno==3){ posesion="EQUIPO 1"; turno=1; fichaActual=color1;
                }
            }else if(r.getCantidadJ()==4){
                if(turno==1){       posesion="EQUIPO 2";turno=2; fichaActual=color2;
                }else if(turno==2){ posesion="EQUIPO 1";turno=3; fichaActual=color3;
                }else if(turno==3){ posesion="EQUIPO 2";turno=4; fichaActual=color4;
                }else if(turno==4){ posesion="EQUIPO 1";turno=1; fichaActual=color1;
                }
            }else if(r.getCantidadJ()==6){
                if(turno==1){       posesion="EQUIPO 2";turno=2; fichaActual=color2;
                }else if(turno==2){ posesion="EQUIPO 3";turno=3; fichaActual=color3;
                }else if(turno==3){ posesion="EQUIPO 1";turno=4; fichaActual=color4;
                }else if(turno==4){ posesion="EQUIPO 2";turno=5; fichaActual=color5;
                }else if(turno==5){ posesion="EQUIPO 3";turno=6; fichaActual=color6;
                }else if(turno==6){ posesion="EQUIPO 1";turno=1; fichaActual=color1;
                }               
            } else if(r.getCantidadJ()==8){
                if(turno==1){       posesion="EQUIPO 2";turno=2; fichaActual=color2;
                }else if(turno==2){ posesion="EQUIPO 1";turno=3; fichaActual=color3;
                }else if(turno==3){ posesion="EQUIPO 2";turno=4; fichaActual=color4;
                }else if(turno==4){ posesion="EQUIPO 1";turno=5; fichaActual=color5;
                }else if(turno==5){ posesion="EQUIPO 2";turno=6; fichaActual=color6;
                }else if(turno==6){ posesion="EQUIPO 1";turno=7; fichaActual=color7;
                }else if(turno==7){ posesion="EQUIPO 2";turno=8; fichaActual=color8;
                }else if(turno==8){ posesion="EQUIPO 1";turno=1; fichaActual=color1;
                }
            }
        } catch (IOException e) {
             
        }
    }
    public String txtTurnoSting(){//LISTO
        registro r=new registro();
        try {
            if(r.getCantidadJ()==2){
                if(turno==1){       fichaActual=color1;return t1;
                }else{              fichaActual=color2; return t2;
                }
            }else if(r.getCantidadJ()==3){
                if(turno==1){       fichaActual=color1;return t1;
                }else if(turno==2){ fichaActual=color2;return t2;
                }else if(turno==3){ fichaActual=color3;return t3;
                }
            }else if(r.getCantidadJ()==4){
                if(turno==1){       fichaActual=color1;return t1;
                }else if(turno==2){ fichaActual=color2;return t2;
                }else if(turno==3){ fichaActual=color3;return t3;
                }else if(turno==4){ fichaActual=color4;return t4;
                }
            }else if(r.getCantidadJ()==6){
                if(turno==1){       fichaActual=color1;return t1;
                }else if(turno==2){ fichaActual=color2;return t2;
                }else if(turno==3){ fichaActual=color3;return t3;
                }else if(turno==4){ fichaActual=color4;return t4;
                }else if(turno==5){ fichaActual=color5;return t5;
                }else if(turno==6){ fichaActual=color6;return t6;
                }               
            } else if(r.getCantidadJ()==8){
                if(turno==1){       fichaActual=color1;return t1;
                }else if(turno==2){ fichaActual=color2;return t2;
                }else if(turno==3){ fichaActual=color3;return t3;
                }else if(turno==4){ fichaActual=color4;return t4;
                }else if(turno==5){ fichaActual=color5;return t5;
                }else if(turno==6){ fichaActual=color6;return t6;
                }else if(turno==7){ fichaActual=color7;return t7;
                }else if(turno==8){ fichaActual=color8;return t8;
                }
            }
        } catch (IOException e) {
             
        }
        return ";";
    }
    public String nombreTurno(){
        return "";   
    } public int getTurno() {
        return turno;
    }

    //LOGICA PARA FICHA GETTER Y SETTER
      public void buscarModo(String login){//busca el modo del que esta login si su modo es 0 entonces retorna color en equipo  si uno debe haer busqueda individual
        registro r = new registro();
        try{
            if(r.getModo()==0){
                System.out.println("modo equipo");
                coloresEquipo();//por codigo
            }else{
                System.out.println("modo individual");
                coloresIndividual();
            }
        }catch(IOException ex){
            System.out.println("ERROR AL OBTENER MODO EN OPONENTE");
            
        }
    }
     private void coloresEquipo() {
        try {
            registro r = new registro();
            logica_tab t = new logica_tab();
            int c = r.getCantidadJ();

            if (c >= 2) { 
                String listaColores = r.getColorEquipo();
                String[] colores = listaColores.split("-");

                ArrayList<String> listaDeColores = new ArrayList<>();//los colores que hay en mi unidimensional colores
                while (colores.length < c && c==3) {
                    String nuevoColor = obtenerColorNoUtilizado(listaDeColores);
                    listaColores += "-" + nuevoColor; 
                    colores = listaColores.split("-"); 
                }
               
                for (int i = 0; i < listaDeColores.size(); i++) {
                    String color = listaDeColores.get(i);
                    if (r.getColorEquipo().equals("NO APLICA") || r.getColorEquipo().equals("NOAPLICA")  ) {
                        listaDeColores.remove(i);
                        String nuevoColor = obtenerColorNoUtilizado(listaDeColores);

                        listaDeColores.add(i, nuevoColor);
                    }
                    listaDeColores.add(color);
                }
                
                while (listaDeColores.size() < c) {
                    String nuevoColor = obtenerColorNoUtilizado(listaDeColores);
                    listaDeColores.add(nuevoColor);
                }
//esto puede aplicar para el individual 
                if(r.getColorEquipo().equals("NO APLICA") && (c<=4)){
                    for (int i = 0; i < c; i++) {
                        if (i == 0 && r.getColorEquipo().equals("NO APLICA") ) {
                            setColor1(listaDeColores.get(i));
                            fichaActual=color1;
                        } else if (i == 1  && (r.getColorEquipo().equals("NO APLICA") || c==2 || c==3)) {
                            setColor2(listaDeColores.get(i));
                        } else if (i == 2  &&  (r.getColorEquipo().equals("NO APLICA") || c==2 || c==3)) {
                            setColor3(listaDeColores.get(i));
                        }else if (i == 3  &&  (r.getColorEquipo().equals("NO APLICA") || c==2 || c==3)) {
                            setColor4(listaDeColores.get(i));
                        }else if (i == 4  &&  (r.getColorEquipo().equals("NO APLICA") || c==2 || c==3)) {
                            setColor5(listaDeColores.get(i));
                        }
                    }
                }
                if(r.getColorEquipo().equals("NO APLICA") && (c==8)){
                    for (int i = 0; i < c; i++) {
                        if (i == 0 ) {
                            setColor1(listaDeColores.get(0));
                            fichaActual=color1;
                        } else if (i == 1  ) {
                            setColor2(listaDeColores.get(1));
                        } else if (i == 2 ) {
                            setColor3(listaDeColores.get(0));
                        } else if (i == 3 ) {
                            setColor4(listaDeColores.get(1));
                        } else if (i == 4 ) {
                            setColor5(listaDeColores.get(0));
                        } else if (i == 5  ) {
                            setColor6(listaDeColores.get(1));
                        } else if (i == 6 ) {
                            setColor7(listaDeColores.get(0));
                        } else if (i == 7  ) {
                            setColor8(listaDeColores.get(1));
                        } 
                    }
                }
                if(r.getColorEquipo().equals("NO APLICA") && (c==6)){
                    for (int i = 0; i < c; i++) {
                        if (i == 0 ) {
                            setColor1(listaDeColores.get(0));
                            fichaActual=color1;
                        } else if (i == 1  ) {
                            setColor2(listaDeColores.get(1));
                        } else if (i == 2 ) {
                            setColor3(listaDeColores.get(2));
                        } else if (i == 3 ) {
                            setColor4(listaDeColores.get(0));
                        } else if (i == 4 ) {
                            setColor5(listaDeColores.get(1));
                        } else if (i == 5  ) {
                            setColor6(listaDeColores.get(2));
                        }
                    }
                }
                if(!r.getColorEquipo().equals("NO APLICA") && (c==2 || c==3)){
                    for (int i = 0; i < c; i++) {
                        if (i == 0 && !r.getColorEquipo().equals("NO APLICA") ) {
                            setColor1(colores[0]);
                            fichaActual=color1;
                        } else if (i == 1  && !r.getColorEquipo().equals("NO APLICA")) {
                            setColor2(colores[1]);
                        } else if (i == 2  &&  !r.getColorEquipo().equals("NO APLICA")) {
                            setColor3(colores[2]);
                        }
                    }
                }
                if(!r.getColorEquipo().equals("NO APLICA") && (c==4 || c==8)){
                    for (int i = 0; i < c; i++) {
                        if (i == 0 ) {
                            setColor1(colores[0]);
                            fichaActual=color1;
                        } else if (i == 1  ) {
                            setColor2(colores[1]);
                        } else if (i == 2 ) {
                            setColor3(colores[0]);
                        } else if (i == 3 ) {
                            setColor4(colores[1]);
                        } else if (i == 4 ) {
                            setColor5(colores[0]);
                        } else if (i == 5  ) {
                            setColor6(colores[1]);
                        } else if (i == 6 ) {
                            setColor7(colores[0]);
                        } else if (i == 7  ) {
                            setColor8(colores[1]);
                        } 
                    }
                }
                if(!r.getColorEquipo().equals("NO APLICA") && c==6){
                    for (int i = 0; i < c; i++) {
                        if (i == 0 ) {
                            setColor1(colores[0]);
                            fichaActual=color1;
                        } else if (i == 1  ) {
                            setColor2(colores[1]);
                        } else if (i == 2 ) {
                            setColor3(colores[2]);
                        } else if (i == 3 ) {
                            setColor4(colores[0]);
                        } else if (i == 4 ) {
                            setColor5(colores[1]);
                        } else if (i == 5  ) {
                            setColor6(colores[2]);
                        }
                    }
                }
                
            }
        } catch (IOException ex) {
            System.out.println("error en color equipo");
        }
    }

    private String obtenerColorNoUtilizado(ArrayList<String> listaDeColores) {
        for (String color : todoColores) {
            if (!listaDeColores.contains(color)) {
                return color;
            }
        }
        return "AQUA";
    }

    private void coloresIndividual(){
        ArrayList<String> todos = new ArrayList<>();
        ArrayList<String> nuevo = new ArrayList<>();
        registro r = new registro();
        logica_tab l=new logica_tab();
        try{
            int c = r.getCantidadJ();
            if(c>=2){
                String color="";
                if(c==2){
                    color=r.getColorIndividual(t1);
                    todos.add(color);
                    color=r.getColorIndividual(t2);
                    todos.add(color);
                }else if(c==3){
                    color=r.getColorIndividual(t1);
                    todos.add(color);
                    color=r.getColorIndividual(t2);
                    todos.add(color);
                    color=r.getColorIndividual(t3);
                    todos.add(color);
                }else if(c==4){
                    color=r.getColorIndividual(t1);
                    todos.add(color);
                    color=r.getColorIndividual(t2);
                    todos.add(color);
                    color=r.getColorIndividual(t3);
                    todos.add(color);
                    color=r.getColorIndividual(t4);
                    todos.add(color);
                }else if(c==6){
                    color=r.getColorIndividual(t1);
                    todos.add(color);
                    color=r.getColorIndividual(t2);
                    todos.add(color);
                    color=r.getColorIndividual(t3);
                    todos.add(color);
                    color=r.getColorIndividual(t4);
                    todos.add(color);
                    color=r.getColorIndividual(t5);
                    todos.add(color);
                    color=r.getColorIndividual(t6);
                    todos.add(color);
                }else if(c==8){
                    color=r.getColorIndividual(t1);
                    todos.add(color);
                    color=r.getColorIndividual(t2);
                    todos.add(color);
                    color=r.getColorIndividual(t3);
                    todos.add(color);
                    color=r.getColorIndividual(t4);
                    todos.add(color);
                    color=r.getColorIndividual(t5);
                    todos.add(color);
                    color=r.getColorIndividual(t6);
                    todos.add(color);
                    color=r.getColorIndividual(t7);
                    todos.add(color);
                    color=r.getColorIndividual(t8);
                    todos.add(color);
                }
                for (int i = 0; i < todos.size(); i++) {
                    String co = todos.get(i);
                    if (co.equals("NO APLICA") || co.equals("NOAPLICA")) {
                        todos.remove(i);
                        String nuevoColor = obtenerColorNoUtilizado(todos); // Generar nuevo color

                        todos.add(i, nuevoColor); // Agregar el nuevo color en el mismo índice
                    }
                    System.out.println(todos.get(i)); // Imprimir el color
                }

            }
            for(String t: todos){
                System.out.println(t);
            }
            for (int i = 0; i < c; i++) {
                if (i == 0 ) {
                    setColor1(todos.get(0));
                    fichaActual=color1;
                } else if (i == 1  ) {
                    setColor2(todos.get(1));
                } else if (i == 2 ) {
                    setColor3(todos.get(2));
                } else if (i == 3 ) {
                    setColor4(todos.get(3));
                } else if (i == 4 ) {
                    setColor5(todos.get(4));
                } else if (i == 5  ) {
                    setColor6(todos.get(5));
                } else if (i == 6 ) {
                    setColor7(todos.get(6));
                } else if (i == 7  ) {
                    setColor8(todos.get(4));
                } 
            }
        }catch(IOException ex){
            
        }  
    }
    public String getColor1() {
        return color1;
    }public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor3() {
        return color3;
    }public void setColor3(String color3) {
        this.color3 = color3;
    }

    public String getColor4() {
        return color4;
    }public void setColor4(String color4) {
        this.color4 = color4;
    }

    public String getColor5() {
        return color5;
    }public void setColor5(String color5) {
        this.color5 = color5;
    }

    public String getColor6() {
        return color6;
    }public void setColor6(String color6) {
        this.color6 = color6;
    }

    public String getColor7() {
        return color7;
    }public void setColor7(String color7) {
        this.color7 = color7;
    }

    public String getColor8() {
        return color8;
    }public void setColor8(String color8) {
        this.color8 = color8;
    }

    public String getFichaActual() {
        return fichaActual;
    }public void setFichaActual(String fichaActual) {
        this.fichaActual = fichaActual;
    }
    
    
    
}
