package sequence;

import clases.Repartircartas_tab;
import clases.call_png_fichas;
import clases.Cartas_conf;
import clases.registro;
import clases.call_png_baraja;
import clases.logica_tab;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class tablero_1 extends javax.swing.JFrame {

    private logica_tab lt = new logica_tab();
    int contusuarios;
    Configuracion conf = new Configuracion();
    registro obrg = new registro();
    Repartircartas_tab repart = new Repartircartas_tab();
    int entro = 1;
    String nombre, cartjg1, cartjg2, cartjg3, cartjg4, cartg5, cartg6, cartg7, cartg8;
    int darcart = 0, contjg1 = 0, contjg2 = 0, contjg3 = 0, contjg4 = 0, contjg5 = 0, contjg6 = 0, contjg7 = 0, contjg8 = 0;
    public Timer timer;
    public Timer tij;
    private int segundos = 0, contador = 0;
    Dimension CoordjSelect = null;
    String[] botonescarta;
    boolean ordenarcartas = false, nuevoorden = false, reinicio = false, descartarcarta = false, yadescarto = false;
    String cartamover = "", cartadescartar = "";
    int posicionusada = 0;
    int cont = 0, entroya = 0;
    private List<String> ordenActualCartas;

    public tablero_1(String nombre) {
        initComponents();
        fondoTablero();
        ordenActualCartas = new ArrayList<>();
        lt.GridLayout(tab);
        this.nombre = nombre;
        inicializarJugadores();
        ImageIcon icon = new ImageIcon("src/images/mazo.png");
        sacarCarta.setIcon(icon);
        lt.elegida="a";
        lt.buscarModo(registro.getLogin());
        segundos = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lt.elegida="a";
                segundos++;
                actLabelTIME();
                if (segundos >= 120) {
                    lt.elegida="a";
                    timer.stop();
                    finTIME();
                }
            }
        });
        timer.start();

        lt.setFichaActual(lt.getColor1());
        System.out.println(" a1" + lt.getColor1() + " a2" + lt.getColor2() + "a3" + lt.getColor3() + " a4" + lt.getColor4() + " a5" + lt.getColor5() + " a6" + lt.getColor6() + " a7" + lt.getColor7() + " a8" + lt.getColor8() + "aaactual" + lt.fichaActual);
        //no ess eguro pero toca
        int retraso = 1000;
        tij = new Timer(retraso, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lt.seRealizoAccion()) {
                    paro();
                    lt.elegida="a";
                    tij.stop();
                    tij.start();
                    nuevoorden = false;
                    ordenActualCartas.clear();

                }
            }
        });

        tij.start();

    }

    private void actLabelTIME() {
        int minutos = segundos / 60;
        int segundosRestantes = segundos % 60;
        String tiempoFormateado = String.format("%02d:%02d", minutos, segundosRestantes);
        time.setText(tiempoFormateado);
    }

    public void paro() {
        timer.stop();//para timer y turno
        JOptionPane.showMessageDialog(null, "Cambio de turno");//para timer y turno
        segundos = 0; //para timer y turno
        actLabelTIME();//para timer y turno
        timer.start();//para timer y turno
        actualizarLabelT();//para timer y turno
    }

    private void finTIME() {
        JOptionPane.showMessageDialog(this, ":(\nSE TE ACABO EL TIEMPO\nCambio de turno", "Fin turno", JOptionPane.INFORMATION_MESSAGE);
        segundos = 0;
        actLabelTIME();
        timer.start();
        actualizarLabelT();
    }

    public void actualizarLabelT() {
        lt.cambiorturno();
        String turnoString = lt.txtTurnoSting();
        turno.setText("Turno de: " + turnoString);

    }

    private void mostrarCuadroDialogoConImagenes(int numcarts, String infocartas) {

        JPanel panel = new JPanel();
        String[] cartasunidas = infocartas.split("\n");
        // cartasunidas = ordenActualCartas.toArray(new String[0]);

        System.out.println("NORMALordenActualCartas" + ordenActualCartas);
        String[] cartas = new String[numcarts + 1];
        //aqui va a verificar que no haya ningun valor en blanco porque si hay que se mantega el valor anterior 
        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
            String[] ordenActu = ordenActualCartas.toArray(new String[numcarts + 1]);
            for (int i = 1; i < cartasunidas.length; i++) {

                cartasunidas[i] = ordenActu[i];
                System.out.print("ordenActu[i]= " + ordenActu[i]);
            }

        }
        for (int i = 0; i < cartasunidas.length; i++) {
            if (i < cartasunidas.length && !cartasunidas[i].equals("")) {
                cartas[i] = cartasunidas[i];
            }
        }

        if (nuevoorden == true) {
            for (int i = 1; i < cartasunidas.length; i++) {
                cartas[i] = botonescarta[i];

            }
        }

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); // Establece el layout vertical
        panel.add(Box.createRigidArea(new Dimension(15, 0)));

        //  cuadro de diálogo para mostrar la información de la carta
        JOptionPane infoCartaDialog = new JOptionPane();

        for (int i = 0; i < numcarts; i++) {
            int cartaIndex = i; // Almacena el índice de la carta para acceder a ella en el ActionListener
            JButton boton = new JButton();
            boton.setSize(new Dimension(45, 55));
            //Dimension Dimentemp = new Dimension(i, 1);
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Obtine la información de la carta correspondiente
                    String cartaSeleccionada = (cartaIndex < cartas.length) ? cartas[cartaIndex + 1] : "No hay información disponible";

                    // Mostrar la información de la carta en un cuadro de diálogo
                    //infoCartaDialog.showMessageDialog(null, cartaSeleccionada, "Información de la Carta", JOptionPane.INFORMATION_MESSAGE);
                    lt.elegida = cartaSeleccionada;
                }

            });
            String carat = (cartaIndex < cartas.length) ? cartas[cartaIndex + 1] : "No hay información disponible";
            String cartaSinGuion = carat.replace("-", "");
            boton.setIcon(call_png_baraja.obtenerFicha(cartaSinGuion));
            panel.add(boton);

        }

        CustomDialog dialog = new CustomDialog(this, panel, "PRUEBITA");
        dialog.setMinimumSize(new Dimension(700, 500));
        dialog.setMaximumSize(new Dimension(700, 500));
        panel.setOpaque(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }

    private void ordenarcartas(int numcarts, String infocartas) {
        nuevoorden = false;
        botonescarta = new String[numcarts + 1];
        int[] posicionesusadas = new int[numcarts + 1];
        for (int i = 0; i < posicionesusadas.length; i++) {
            posicionesusadas[i] = 0;
        }
        JPanel panel = new JPanel();
        System.out.println("infocartas" + infocartas);
        String[] cartasunidas = infocartas.split("\n");
        String[] cartas = new String[numcarts + 1];

        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
            String[] ordenActu = ordenActualCartas.toArray(new String[numcarts + 1]);
            for (int i = 1; i < cartasunidas.length; i++) {

                cartasunidas[i] = ordenActu[i];
                System.out.print("ordenActu[i]= " + ordenActu[i]);
            }

        }
        for (int i = 0; i < cartasunidas.length && i < numcarts + 1; i++) {
            if (!cartasunidas[i].equals("")) {
                cartas[i] = cartasunidas[i];
                botonescarta[i] = "0";
                System.out.println(" botonescarta[i]" + botonescarta[i]);
            }
        }

        System.out.println("ORDENARordenActualCartas" + ordenActualCartas);
        JButton reiniciarorden = new JButton("Reordenar");
        reiniciarorden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < cartasunidas.length && i < numcarts + 1; i++) {
                    botonescarta[i] = "0";

                }
                for (int i = 0; i < posicionesusadas.length; i++) {
                    posicionesusadas[i] = 0;
                }

                reinicio = true;
                JOptionPane.showMessageDialog(null, "Reiniciando orden otra vez");
                cartamover = "";

                posicionusada = 0;
            }

        });
        panel.add(reiniciarorden);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createRigidArea(new Dimension(20, 0)));

        JOptionPane infoCartaDialog = new JOptionPane();

        for (int i = 0; i < numcarts; i++) {
            int cartaIndex = i; // Almacena el índice de la carta para acceder a ella en el ActionListener
            JButton boton = new JButton();
            boton.setSize(new Dimension(45, 55));
            Dimension Dimentemp = new Dimension(i, 0);
            boton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    //Obtine la información de la carta correspondiente
                    String cartaSeleccionada = (cartaIndex < cartas.length) ? cartas[cartaIndex + 1] : "No hay información disponible";
                    System.out.println("cartas.lenght " + cartas.length);
                    // Mostrar la información de la carta en un cuadro de diálogo
                    //infoCartaDialog.showMessageDialog(null, cartaSeleccionada, "Información de la Carta", JOptionPane.INFORMATION_MESSAGE);
                    cartamover = cartaSeleccionada;
                    posicionusada = cartaIndex + 1;
                }
            });
            String carat = (cartaIndex < cartas.length) ? cartas[cartaIndex + 1] : "No hay información disponible";
            String cartaSinGuion = carat.replace("-", "");
            boton.setIcon(call_png_baraja.obtenerFicha(cartaSinGuion));
            boton.setPreferredSize(new Dimension(40, 65));
            panel.add(boton);
//            System.out.println("botonescarta:" + botonescarta[0][i]);
        }
        panel.add(Box.createRigidArea(new Dimension(15, 10)));
        for (int i = 1; i < numcarts + 1; i++) {
            int cartaIndex = i;
            JButton boton2 = new JButton();
            boton2.setSize(new Dimension(45, 55));
            boton2.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    //JOptionPane.showMessageDialog(null, "botonescarta[cartaIndex]" + cartaIndex);
                    //aqui guardo la posicion de la carta que selccione para que no ordene dos cartas repetidas
                    for (int j = 0; j < posicionesusadas.length; j++) {
                        if (posicionesusadas[j] == posicionusada && posicionusada != 0) {
                            cartamover = "";
                            posicionusada = 0;
                            JOptionPane.showMessageDialog(null, "Seleccione otra carta que no haya seleccionado antes");
                            return;
                        }
                    }
                    // Obtén la información de la carta correspondiente
                    if ((!cartamover.equals(""))) {
                        //aqui guardo la posicion de la carta que selccione para que no ordene dos cartas repetidas
                        // Mostrar la información de la carta en un cuadro de diálogo
                        String cartaSeleccionada = (cartaIndex < botonescarta.length) ? botonescarta[cartaIndex] : "No hay información disponible";
                        //infoCartaDialog.showMessageDialog(null, botonescarta[cartaIndex], "Información de la Carta", JOptionPane.INFORMATION_MESSAGE);

                        if (!botonescarta[cartaIndex].equals("0")) {
                            JOptionPane.showMessageDialog(null, "Ya hay una carta en esa posición");
                        } else if (botonescarta[cartaIndex].equals("0")) {
                            // Actualiza `botonescarta` en la posición correspondiente
                            botonescarta[cartaIndex] = cartamover;
                            posicionesusadas[cartaIndex] = posicionusada;
                            posicionusada = 0;
                            cont++;
                            System.out.print("cont " + cont);
                            String cartaSinGuion = cartamover.replace("-", "");
                            boton2.setIcon(call_png_baraja.obtenerFicha(cartaSinGuion));
                            cartamover = "";
                            ordenActualCartas = new ArrayList<>(Arrays.asList(botonescarta));
                            System.out.println("ORDENARordenActualCartas" + ordenActualCartas);
                            if (!ordenActualCartas.contains("0")) {
                                nuevoorden = true;
                                System.out.println("nuevoorden" + nuevoorden);
                                cont = 0;
                                JOptionPane.showMessageDialog(null, "Ya ha reeordenado todas sus cartas");
                                return;
                            }

                            panel.revalidate();
                            panel.repaint();
                        }

                    } else {
                        //este if es si el arreglo tiene un valor lo muestre en patalla y si no tiene nada que seleccione una carta 
                        if (!botonescarta[cartaIndex].equals("0")) {
                            //JOptionPane.showMessageDialog(null, "Nuevo valor:" + botonescarta[cartaIndex]);
                            return;
                        }
                        JOptionPane.showMessageDialog(null, "Seleccione una carta primero");
                    }
                }
            });

            String carat = botonescarta[cartaIndex];
            System.out.println("cartamover: " + cartamover);
            // Actualiza el ícono del botón con la nueva carta

            // Añade el botón al panel
            boton2.setPreferredSize(new Dimension(40, 65));
            boton2.revalidate();
            boton2.repaint();
            panel.add(boton2);

        }

        CustomDialog dialog = new CustomDialog(this, panel, "PRUEBITA");
        dialog.setMinimumSize(new Dimension(1100, 500));
        dialog.setMaximumSize(new Dimension(1100, 500));
        panel.setOpaque(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }
    int indiceAEliminar = 0;

    private void descartarcartas(int numcarts, String infocartas) {

        JPanel panel = new JPanel();
        String[] cartasunidas = infocartas.split("\n");
        // cartasunidas = ordenActualCartas.toArray(new String[0]);
        // System.out.print("DESCARTARLordenActualCartas" + ordenActualCartas);
        String[] cartas = new String[numcarts + 1];
        //aqui va a verificar que no haya ningun valor en blanco porque si hay que se mantega el valor anterior 
        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
            String[] ordenActu = ordenActualCartas.toArray(new String[numcarts + 1]);
            for (int i = 1; i < cartasunidas.length; i++) {

                cartasunidas[i] = ordenActu[i];
                System.out.print("ordenActu[i]= " + ordenActu[i]);
            }

        }
        for (int i = 0; i < cartasunidas.length; i++) {
            if (i < cartasunidas.length && !cartasunidas[i].equals("")) {
                cartas[i] = cartasunidas[i];
            }
        }

        if (nuevoorden == true) {
            for (int i = 1; i < cartasunidas.length; i++) {
                cartas[i] = botonescarta[i];

            }
        }

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); // Establece el layout vertical
        panel.add(Box.createRigidArea(new Dimension(15, 0)));

        //  cuadro de diálogo para mostrar la información de la carta
        JOptionPane infoCartaDialog = new JOptionPane();

        for (int i = 0; i < numcarts; i++) {
            int cartaIndex = i; // Almacena el índice de la carta para acceder a ella en el ActionListener
            JButton boton = new JButton();
            boton.setSize(new Dimension(45, 55));
            //Dimension Dimentemp = new Dimension(i, 1);
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Obtine la información de la carta correspondiente
                    if (yadescarto == true) {
                        JOptionPane.showMessageDialog(null, "Solo se puede descartar una carta por turno");
                        return;
                    }
                    String cartaSeleccionada = (cartaIndex < cartas.length) ? cartas[cartaIndex + 1] : "No hay información disponible";
                    cartadescartar = cartaSeleccionada;
                    JOptionPane.showMessageDialog(null, "carta descartar:" + cartaSeleccionada);
//                    for (int i = 1; i < cartas.length; i++) {
//                        
//                        System.out.print("sinremplazar: "+cartas[i]);
//                    }
                    for (int i = 1; i < cartas.length; i++) {
                        if (cartas[i].contains(cartadescartar)) {
                            indiceAEliminar = i;
                            break; // Detengo el bucle cuando encuentro la carta
                        }
                    }

                    // Verifica si se encontró el valor a eliminar
                    if (indiceAEliminar != -1) {
                        // Reemplaza el valor en el mismo índice con el nuevo valor

                        cartas[indiceAEliminar] = repart.siguienteCarta().getTipo() + repart.siguienteCarta().getValor();
                        System.out.println("nueva carta" + cartas[indiceAEliminar]);
                        boton.setIcon(call_png_baraja.obtenerFicha(cartas[indiceAEliminar]));
                        indiceAEliminar = 0;
                        yadescarto = true;
                        JOptionPane.showMessageDialog(null, "Carta descartada");
                        ordenActualCartas = new ArrayList<>(Arrays.asList(cartas));

                    } else {
                        System.out.println("El valor a eliminar no se encuentra en el array.");
                    }

//                    for (int i = 1; i < cartas.length; i++) {
//
//                        System.out.print("remplazado: " + cartas[i]);
//                    }
                }

            });
            String carat = (cartaIndex < cartas.length) ? cartas[cartaIndex + 1] : "No hay información disponible";
            String cartaSinGuion = carat.replace("-", "");
            boton.setIcon(call_png_baraja.obtenerFicha(cartaSinGuion));
            panel.add(boton);

        }

        CustomDialog dialog = new CustomDialog(this, panel, "PRUEBITA");
        dialog.setMinimumSize(new Dimension(700, 500));
        dialog.setMaximumSize(new Dimension(700, 500));
        panel.setOpaque(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }

     private void inicializarJugadores() {
        String[] id = nombre.split("\n");
        String nombre1 = "";
        String equipo1 = "";
        String nombre2 = "";
        String equipo2 = "";
        String nombre3 = "";
        String equipo3 = "";
        String nombre4 = "";
        String equipo4 = "";
        String nombre5 = "";
        String equipo5 = "";
        String nombre6 = "";
        String equipo6 = "";
        String nombre7 = "";
        String equipo7 = "";
        String nombre8 = "";
        String equipo8 = "";
        if (id.length >= 1) {
            String[] split1 = id[0].split("-");
            if (split1.length >= 2) {
                nombre1 = split1[0];
                equipo1 = split1[1];
            }
        }
        if (id.length >= 2) {
            String[] split2 = id[1].split("-");
            if (split2.length >= 2) {
                nombre2 = split2[0];
                equipo2 = split2[1];
            }
        }
        if (id.length >= 3) {
            String[] split3 = id[2].split("-");
            if (split3.length >= 2) {
                nombre3 = split3[0];
                equipo3 = split3[1];
            }
        }
        if (id.length >= 4) {
            String[] split4 = id[3].split("-");
            if (split4.length >= 2) {
                nombre4 = split4[0];
                equipo4 = split4[1];
            }
        }
        if (id.length >= 5) {
            String[] split5 = id[4].split("-");
            if (split5.length >= 2) {
                nombre5 = split5[0];
                equipo5 = split5[1];
            }
        }
        if (id.length >= 6) {
            String[] split6 = id[5].split("-");
            if (split6.length >= 2) {
                nombre6 = split6[0];
                equipo6 = split6[1];
            }
        }
        if (id.length >= 7) {
            String[] split7 = id[6].split("-");
            if (split7.length >= 2) {
                nombre7 = split7[0];
                equipo7 = split7[1];
            }
        }
        if (id.length >= 8) {
            String[] split8 = id[7].split("-");
            if (split8.length >= 2) {
                nombre8 = split8[0];
                equipo8 = split8[1];
            }
        }
        try {
            int c = obrg.getCantidadJ();
            if (c == 2) {
                if (equipo1.equals("TURNO 1")) {
                    nombre_J1.setText(nombre1);
                    equipo_J1.setText(equipo1);
                    lt.t1 = nombre1;
                    lt.miembros1 +=  nombre1+"-";
                    turno.setText("Turno de: " + nombre1);
                } else if (equipo1.equals("TURNO 2")) {
                    nombre_J8.setText(nombre1);
                    equipo_J8.setText(equipo1);
                    lt.t2 = nombre1;
                    lt.miembros2 += nombre1+"-";
                }
                if (equipo2.equals("TURNO 2")) {
                    nombre_J8.setText(nombre2);
                    equipo_J8.setText(equipo2);
                    lt.t2 = nombre2;
                    lt.miembros2 +=  nombre2+"-";
                } else if (equipo2.equals("TURNO 1")) {
                    nombre_J1.setText(nombre2);
                    equipo_J1.setText(equipo2);
                    lt.t1 = nombre2;
                    lt.miembros1 +=nombre2+"-";
                    turno.setText("Turno de: " + nombre2);
                }
                btn_verJ8.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ1.setIcon(call_png_baraja.imagenTrasera());
                panelJ7.setVisible(false);
                panelJ2.setVisible(false);
                panelJ6.setVisible(false);
                panelJ3.setVisible(false);
                panelJ5.setVisible(false);
                panelJ4.setVisible(false);
            } else if (c == 3) {
                if (equipo1.equals("TURNO 1")) {
                    nombre_J1.setText(nombre1);
                    equipo_J1.setText(equipo1);
                    lt.t1 = nombre1;
                    lt.miembros1 += nombre1+"-";
                    turno.setText("Turno de: " + nombre1);
                } else if (equipo1.equals("TURNO 2")) {
                    nombre_J2.setText(nombre1);
                    equipo_J2.setText(equipo1);
                    lt.t2 = nombre1;
                    lt.miembros2 += nombre1+"-";
                } else if (equipo1.equals("TURNO 3")) {
                    nombre_J8.setText(nombre1);
                    equipo_J8.setText(equipo1);
                    lt.miembros3 += nombre1+"-";
                    lt.t3 = nombre1;
                }
                if (equipo2.equals("TURNO 1")) {
                    nombre_J1.setText(nombre2);
                    equipo_J1.setText(equipo2);
                    lt.t1 = nombre2;
                    lt.miembros1 +=  nombre2+"-";
                    turno.setText("Turno de: " + nombre2);
                } else if (equipo2.equals("TURNO 2")) {
                    nombre_J2.setText(nombre2);
                    equipo_J2.setText(equipo2);
                    lt.t2 = nombre2;
                    lt.miembros2 += nombre2+"-";
                } else if (equipo2.equals("TURNO 3")) {
                    nombre_J8.setText(nombre2);
                    equipo_J8.setText(equipo2);
                    lt.t3 = nombre2;
                    lt.miembros3 += nombre2+"-";
                }
                if (equipo3.equals("TURNO 1")) {
                    nombre_J1.setText(nombre3);
                    equipo_J1.setText(equipo3);
                    lt.t1 = nombre3;
                    lt.miembros1 +=  nombre3+"-";
                    turno.setText("Turno de: " + lt.t1);
                } else if (equipo3.equals("TURNO 2")) {
                    nombre_J2.setText(nombre3);
                    equipo_J2.setText(equipo3);
                    lt.t2 = nombre3;
                    lt.miembros2 +=  nombre3+"-";
                } else if (equipo3.equals("TURNO 3")) {
                    nombre_J8.setText(nombre3);
                    equipo_J8.setText(equipo3);
                    lt.t3 = nombre3;
                    lt.miembros3 += nombre3+"-";
                }

                btn_verJ1.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ2.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ8.setIcon(call_png_baraja.imagenTrasera());
                panelJ7.setVisible(false);
                panelJ6.setVisible(false);
                panelJ3.setVisible(false);
                panelJ5.setVisible(false);
                panelJ4.setVisible(false);
            } else if (c == 4) {
                nombre_J1.setText(nombre1);
                equipo_J1.setText(equipo1);
                lt.t1 = nombre1;
                lt.miembros1 += nombre1+"-";
                turno.setText("Turno de: " + nombre1);
                nombre_J2.setText(nombre2);
                equipo_J2.setText(equipo2);
                lt.t2 = nombre2;
                lt.miembros2 +=  nombre2+"-";
                nombre_J7.setText(nombre3);
                equipo_J7.setText(equipo3);
                lt.t3 = nombre3;
                lt.miembros1 += nombre3+"-";
                nombre_J8.setText(nombre4);
                equipo_J8.setText(equipo4);
                lt.t4 = nombre4;
                lt.miembros2 +=  nombre4+"-";

                btn_verJ1.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ2.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ8.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ7.setIcon(call_png_baraja.imagenTrasera());
                panelJ6.setVisible(false);
                panelJ3.setVisible(false);
                panelJ5.setVisible(false);
                panelJ4.setVisible(false);
            } else if (c == 6) {
                nombre_J1.setText(nombre1);
                equipo_J1.setText(equipo1);
                lt.t1 = nombre1;
                lt.miembros1 += nombre1+"-";
                turno.setText("Turno de: " + nombre1);
                nombre_J2.setText(nombre2);
                equipo_J2.setText(equipo2);
                lt.t2 = nombre2;
                lt.miembros2 +=  nombre2+"-";
                nombre_J3.setText(nombre3);
                equipo_J3.setText(equipo3);
                lt.t3 = nombre3;
                lt.miembros3 += nombre3+"-";
                nombre_J6.setText(nombre4);
                equipo_J6.setText(equipo4);
                lt.t4 = nombre4;
                lt.miembros1 +=  nombre4+"-";
                nombre_J7.setText(nombre5);
                equipo_J7.setText(equipo5);
                lt.t5 = nombre5;
                lt.miembros2 += nombre5+"-";
                nombre_J8.setText(nombre6);
                equipo_J8.setText(equipo6);
                lt.t6 = nombre6;
                lt.miembros3 += nombre6+"-";

                btn_verJ8.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ7.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ6.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ1.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ2.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ3.setIcon(call_png_baraja.imagenTrasera());
                panelJ5.setVisible(false);
                panelJ4.setVisible(false);
            } else if (c == 8) {
                nombre_J1.setText(nombre1);
                equipo_J1.setText(equipo1);
                lt.t1 = nombre1;
                lt.miembros1 +=  nombre1+"-";
                turno.setText("Turno de: " + nombre1);
                nombre_J2.setText(nombre2);
                equipo_J2.setText(equipo2);
                lt.t2 = nombre2;
                lt.miembros2 +=  nombre2+"-";
                nombre_J3.setText(nombre3);
                equipo_J3.setText(equipo3);
                lt.t3 = nombre3;
                lt.miembros1 +=nombre3+"-";
                nombre_J4.setText(nombre4);
                equipo_J4.setText(equipo4);
                lt.t4 = nombre4;
                lt.miembros2 += nombre4+"-";
                nombre_J5.setText(nombre5);
                equipo_J5.setText(equipo5);
                lt.miembros1 += nombre5+"-";
                lt.t5 = nombre5;
                nombre_J6.setText(nombre6);
                equipo_J6.setText(equipo6);
                lt.t6 = nombre6;
                lt.miembros2 +=  nombre6+"-";
                nombre_J7.setText(nombre7);
                equipo_J7.setText(equipo7);
                lt.t7 = nombre7;
                lt.miembros1 +=  nombre7+"-";
                nombre_J8.setText(nombre8);
                equipo_J8.setText(equipo8);
                lt.t8 = nombre8;
                lt.miembros2 +=  nombre8+"-";

                btn_verJ8.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ7.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ6.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ5.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ1.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ2.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ3.setIcon(call_png_baraja.imagenTrasera());
                btn_verJ4.setIcon(call_png_baraja.imagenTrasera());
            } else {
            }
        } catch (IOException e) {
            System.err.println("error");
        }
    }


    private void fondoTablero() {
        ImageIcon ig = new ImageIcon("src/images/fondos/fondo_tablero.png");
        Image original = ig.getImage();
        Image scaledImage = original.getScaledInstance(fondo_tab.getWidth(), fondo_tab.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon escala = new ImageIcon(scaledImage);
        fondo_tab.setIcon(escala);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        principal = new javax.swing.JPanel();
        panel_info = new javax.swing.JPanel();
        turno = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        btn_reglas = new javax.swing.JButton();
        sacarCarta = new javax.swing.JButton();
        menu = new javax.swing.JButton();
        maz = new javax.swing.JLabel();
        Ordenarcartas = new javax.swing.JButton();
        descartar_carta = new javax.swing.JButton();
        panel_5al8 = new javax.swing.JPanel();
        panelJ8 = new javax.swing.JPanel();
        nombre_J8 = new javax.swing.JLabel();
        equipo_J8 = new javax.swing.JLabel();
        btn_verJ8 = new javax.swing.JButton();
        panelJ7 = new javax.swing.JPanel();
        nombre_J7 = new javax.swing.JLabel();
        equipo_J7 = new javax.swing.JLabel();
        btn_verJ7 = new javax.swing.JButton();
        panelJ6 = new javax.swing.JPanel();
        nombre_J6 = new javax.swing.JLabel();
        equipo_J6 = new javax.swing.JLabel();
        btn_verJ6 = new javax.swing.JButton();
        panelJ5 = new javax.swing.JPanel();
        nombre_J5 = new javax.swing.JLabel();
        equipo_J5 = new javax.swing.JLabel();
        btn_verJ5 = new javax.swing.JButton();
        noBorrar2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelJ_1al4 = new javax.swing.JPanel();
        panelJ1 = new javax.swing.JPanel();
        nombre_J1 = new javax.swing.JLabel();
        equipo_J1 = new javax.swing.JLabel();
        btn_verJ1 = new javax.swing.JButton();
        panelJ2 = new javax.swing.JPanel();
        nombre_J2 = new javax.swing.JLabel();
        equipo_J2 = new javax.swing.JLabel();
        btn_verJ2 = new javax.swing.JButton();
        panelJ3 = new javax.swing.JPanel();
        nombre_J3 = new javax.swing.JLabel();
        equipo_J3 = new javax.swing.JLabel();
        btn_verJ3 = new javax.swing.JButton();
        panelJ4 = new javax.swing.JPanel();
        nombre_J4 = new javax.swing.JLabel();
        equipo_J4 = new javax.swing.JLabel();
        btn_verJ4 = new javax.swing.JButton();
        noBorrar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tab = new javax.swing.JPanel();
        fondo_tab = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 0, 51));

        principal.setBackground(new java.awt.Color(51, 0, 51));

        panel_info.setBackground(new java.awt.Color(51, 0, 51));

        turno.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        turno.setForeground(new java.awt.Color(255, 255, 255));
        turno.setText("Turno de:");

        time.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("Turno de equipo:");

        btn_reglas.setText("?");

        sacarCarta.setBackground(new java.awt.Color(0, 0, 0));
        sacarCarta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        sacarCarta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sacarCarta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sacarCartaMouseClicked(evt);
            }
        });

        menu.setText("volver menu");
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuMouseClicked(evt);
            }
        });

        maz.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        maz.setForeground(new java.awt.Color(255, 255, 255));
        maz.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        maz.setText("mazo");
        maz.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Ordenarcartas.setText("Ordenar Cartas");
        Ordenarcartas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OrdenarcartasMouseClicked(evt);
            }
        });

        descartar_carta.setText("Descartar Cartas");
        descartar_carta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descartar_cartaMouseClicked(evt);
            }
        });
        descartar_carta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descartar_cartaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_infoLayout = new javax.swing.GroupLayout(panel_info);
        panel_info.setLayout(panel_infoLayout);
        panel_infoLayout.setHorizontalGroup(
            panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_infoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                    .addComponent(turno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(menu)
                .addGap(53, 53, 53)
                .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(maz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sacarCarta, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_infoLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(btn_reglas))
                    .addGroup(panel_infoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(descartar_carta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Ordenarcartas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panel_infoLayout.setVerticalGroup(
            panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_infoLayout.createSequentialGroup()
                .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_infoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(sacarCarta, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_infoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn_reglas))
                            .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(maz, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                .addComponent(menu))))
                    .addGroup(panel_infoLayout.createSequentialGroup()
                        .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_infoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(turno, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_infoLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(Ordenarcartas)
                                .addGap(20, 20, 20)
                                .addComponent(descartar_carta)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        sacarCarta.getAccessibleContext().setAccessibleName("mazo");

        panel_5al8.setBackground(new java.awt.Color(51, 0, 51));

        panelJ8.setBackground(new java.awt.Color(51, 0, 51));

        nombre_J8.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        nombre_J8.setForeground(new java.awt.Color(255, 255, 255));
        nombre_J8.setText("Jugador:");

        equipo_J8.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        equipo_J8.setForeground(new java.awt.Color(255, 255, 255));
        equipo_J8.setText("Equipo:");

        btn_verJ8.setBackground(new java.awt.Color(255, 232, 255));
        btn_verJ8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_verJ8.setText("Ver mis cartas");
        btn_verJ8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_verJ8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelJ8Layout = new javax.swing.GroupLayout(panelJ8);
        panelJ8.setLayout(panelJ8Layout);
        panelJ8Layout.setHorizontalGroup(
            panelJ8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJ8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_verJ8, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addComponent(nombre_J8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(equipo_J8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelJ8Layout.setVerticalGroup(
            panelJ8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJ8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombre_J8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipo_J8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_verJ8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelJ7.setBackground(new java.awt.Color(51, 0, 51));

        nombre_J7.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        nombre_J7.setForeground(new java.awt.Color(255, 255, 255));
        nombre_J7.setText("Jugador:");

        equipo_J7.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        equipo_J7.setForeground(new java.awt.Color(255, 255, 255));
        equipo_J7.setText("Equipo:");

        btn_verJ7.setBackground(new java.awt.Color(255, 232, 255));
        btn_verJ7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_verJ7.setText("Ver mis cartas");
        btn_verJ7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_verJ7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelJ7Layout = new javax.swing.GroupLayout(panelJ7);
        panelJ7.setLayout(panelJ7Layout);
        panelJ7Layout.setHorizontalGroup(
            panelJ7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJ7Layout.createSequentialGroup()
                .addGroup(panelJ7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombre_J7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(equipo_J7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelJ7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_verJ7, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelJ7Layout.setVerticalGroup(
            panelJ7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJ7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombre_J7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipo_J7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_verJ7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelJ6.setBackground(new java.awt.Color(51, 0, 51));

        nombre_J6.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        nombre_J6.setForeground(new java.awt.Color(255, 255, 255));
        nombre_J6.setText("Jugador:");

        equipo_J6.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        equipo_J6.setForeground(new java.awt.Color(255, 255, 255));
        equipo_J6.setText("Equipo:");

        btn_verJ6.setBackground(new java.awt.Color(255, 232, 255));
        btn_verJ6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_verJ6.setText("Ver mis cartas");
        btn_verJ6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_verJ6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelJ6Layout = new javax.swing.GroupLayout(panelJ6);
        panelJ6.setLayout(panelJ6Layout);
        panelJ6Layout.setHorizontalGroup(
            panelJ6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJ6Layout.createSequentialGroup()
                .addGroup(panelJ6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombre_J6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(equipo_J6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelJ6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_verJ6, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelJ6Layout.setVerticalGroup(
            panelJ6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJ6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombre_J6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipo_J6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_verJ6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelJ5.setBackground(new java.awt.Color(51, 0, 51));

        nombre_J5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        nombre_J5.setForeground(new java.awt.Color(255, 255, 255));
        nombre_J5.setText("Jugador:");

        equipo_J5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        equipo_J5.setForeground(new java.awt.Color(255, 255, 255));
        equipo_J5.setText("Equipo:");

        btn_verJ5.setBackground(new java.awt.Color(255, 232, 255));
        btn_verJ5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_verJ5.setText("Ver mis cartas");
        btn_verJ5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_verJ5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelJ5Layout = new javax.swing.GroupLayout(panelJ5);
        panelJ5.setLayout(panelJ5Layout);
        panelJ5Layout.setHorizontalGroup(
            panelJ5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJ5Layout.createSequentialGroup()
                .addGroup(panelJ5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(equipo_J5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombre_J5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelJ5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_verJ5, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelJ5Layout.setVerticalGroup(
            panelJ5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJ5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombre_J5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipo_J5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_verJ5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        noBorrar2.setBackground(new java.awt.Color(51, 0, 51));

        jLabel1.setForeground(new java.awt.Color(51, 0, 51));
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout noBorrar2Layout = new javax.swing.GroupLayout(noBorrar2);
        noBorrar2.setLayout(noBorrar2Layout);
        noBorrar2Layout.setHorizontalGroup(
            noBorrar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noBorrar2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        noBorrar2Layout.setVerticalGroup(
            noBorrar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_5al8Layout = new javax.swing.GroupLayout(panel_5al8);
        panel_5al8.setLayout(panel_5al8Layout);
        panel_5al8Layout.setHorizontalGroup(
            panel_5al8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_5al8Layout.createSequentialGroup()
                .addGroup(panel_5al8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(noBorrar2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_5al8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_5al8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panelJ5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelJ6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelJ7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelJ8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_5al8Layout.setVerticalGroup(
            panel_5al8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_5al8Layout.createSequentialGroup()
                .addComponent(panelJ8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelJ7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelJ6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelJ5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noBorrar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelJ_1al4.setBackground(new java.awt.Color(51, 0, 51));

        panelJ1.setBackground(new java.awt.Color(51, 0, 51));

        nombre_J1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        nombre_J1.setForeground(new java.awt.Color(255, 255, 255));
        nombre_J1.setText("Jugador:");

        equipo_J1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        equipo_J1.setForeground(new java.awt.Color(255, 255, 255));
        equipo_J1.setText("Equipo:");

        btn_verJ1.setBackground(new java.awt.Color(255, 232, 255));
        btn_verJ1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_verJ1.setText("Ver mis cartas");
        btn_verJ1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_verJ1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelJ1Layout = new javax.swing.GroupLayout(panelJ1);
        panelJ1.setLayout(panelJ1Layout);
        panelJ1Layout.setHorizontalGroup(
            panelJ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJ1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btn_verJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJ1Layout.createSequentialGroup()
                .addGroup(panelJ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(equipo_J1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombre_J1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelJ1Layout.setVerticalGroup(
            panelJ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJ1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombre_J1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipo_J1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_verJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelJ2.setBackground(new java.awt.Color(51, 0, 51));

        nombre_J2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        nombre_J2.setForeground(new java.awt.Color(255, 255, 255));
        nombre_J2.setText("Jugador:");

        equipo_J2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        equipo_J2.setForeground(new java.awt.Color(255, 255, 255));
        equipo_J2.setText("Equipo:");

        btn_verJ2.setBackground(new java.awt.Color(255, 232, 255));
        btn_verJ2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_verJ2.setText("Ver mis cartas");
        btn_verJ2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_verJ2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelJ2Layout = new javax.swing.GroupLayout(panelJ2);
        panelJ2.setLayout(panelJ2Layout);
        panelJ2Layout.setHorizontalGroup(
            panelJ2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJ2Layout.createSequentialGroup()
                .addGroup(panelJ2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(equipo_J2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombre_J2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelJ2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btn_verJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        panelJ2Layout.setVerticalGroup(
            panelJ2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJ2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombre_J2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipo_J2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_verJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelJ3.setBackground(new java.awt.Color(51, 0, 51));

        nombre_J3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        nombre_J3.setForeground(new java.awt.Color(255, 255, 255));
        nombre_J3.setText("Jugador:");

        equipo_J3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        equipo_J3.setForeground(new java.awt.Color(255, 255, 255));
        equipo_J3.setText("Equipo:");

        btn_verJ3.setBackground(new java.awt.Color(255, 232, 255));
        btn_verJ3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_verJ3.setText("Ver mis cartas");
        btn_verJ3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_verJ3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelJ3Layout = new javax.swing.GroupLayout(panelJ3);
        panelJ3.setLayout(panelJ3Layout);
        panelJ3Layout.setHorizontalGroup(
            panelJ3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJ3Layout.createSequentialGroup()
                .addGroup(panelJ3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(equipo_J3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombre_J3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelJ3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btn_verJ3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelJ3Layout.setVerticalGroup(
            panelJ3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJ3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombre_J3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipo_J3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_verJ3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelJ4.setBackground(new java.awt.Color(51, 0, 51));

        nombre_J4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        nombre_J4.setForeground(new java.awt.Color(255, 255, 255));
        nombre_J4.setText("Jugador:");

        equipo_J4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        equipo_J4.setForeground(new java.awt.Color(255, 255, 255));
        equipo_J4.setText("Equipo:");

        btn_verJ4.setBackground(new java.awt.Color(255, 232, 255));
        btn_verJ4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_verJ4.setText("Ver mis cartas");
        btn_verJ4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_verJ4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelJ4Layout = new javax.swing.GroupLayout(panelJ4);
        panelJ4.setLayout(panelJ4Layout);
        panelJ4Layout.setHorizontalGroup(
            panelJ4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJ4Layout.createSequentialGroup()
                .addGroup(panelJ4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(equipo_J4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombre_J4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelJ4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btn_verJ4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelJ4Layout.setVerticalGroup(
            panelJ4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJ4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombre_J4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipo_J4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_verJ4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        noBorrar.setBackground(new java.awt.Color(51, 0, 51));

        jLabel2.setForeground(new java.awt.Color(51, 0, 51));
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout noBorrarLayout = new javax.swing.GroupLayout(noBorrar);
        noBorrar.setLayout(noBorrarLayout);
        noBorrarLayout.setHorizontalGroup(
            noBorrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noBorrarLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        noBorrarLayout.setVerticalGroup(
            noBorrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelJ_1al4Layout = new javax.swing.GroupLayout(panelJ_1al4);
        panelJ_1al4.setLayout(panelJ_1al4Layout);
        panelJ_1al4Layout.setHorizontalGroup(
            panelJ_1al4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJ_1al4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJ_1al4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelJ4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJ3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJ2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJ1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelJ_1al4Layout.setVerticalGroup(
            panelJ_1al4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJ_1al4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelJ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelJ4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.setOpaque(false);

        javax.swing.GroupLayout tabLayout = new javax.swing.GroupLayout(tab);
        tab.setLayout(tabLayout);
        tabLayout.setHorizontalGroup(
            tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        tabLayout.setVerticalGroup(
            tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout principalLayout = new javax.swing.GroupLayout(principal);
        principal.setLayout(principalLayout);
        principalLayout.setHorizontalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(principalLayout.createSequentialGroup()
                .addComponent(panel_5al8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelJ_1al4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(principalLayout.createSequentialGroup()
                    .addGap(266, 266, 266)
                    .addComponent(fondo_tab, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(260, Short.MAX_VALUE)))
        );
        principalLayout.setVerticalGroup(
            principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(principalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(panel_5al8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelJ_1al4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(principalLayout.createSequentialGroup()
                    .addComponent(fondo_tab, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 132, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseClicked
        //probar
        timer.stop();
        MenuPrincipal mp = new MenuPrincipal();
        mp.setVisible(true);
        this.setVisible(false);


    }//GEN-LAST:event_menuMouseClicked

    private void btn_verJ2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_verJ2MouseClicked
        //JUGADOR 3

        if (lt.txtTurnoSting().equals(nombre_J2.getText())) {//VERIFICA QUE SEA SU TURNO, ES DECIR SI EL NOMBRE DE TURNO ES IGUAL AL NOMBRE DEL PANEL QUE ESTA EN ESTE BOTON
            if (contjg3 == 0) {
                darcart = 0;
                contjg3++;
            }
            try {

                System.out.println("" + obrg.getCantidadJ());

                if (obrg.getCantidadJ() == 3) {

                    if (darcart == 0) {
                        cartjg3 = "";
                        Cartas_conf[] cartas = repart.darcartas(6);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg3 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg3 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[7]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg3 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("cartjg3" + cartjg3);
                        mostrarCuadroDialogoConImagenes(6, cartjg3);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg3 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[7]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg3 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(6, cartjg3);
                        ordenarcartas = false;
                    }
                }
                if (obrg.getCantidadJ() == 4) {

                    if (darcart == 0) {
                        cartjg3 = "";
                        Cartas_conf[] cartas = repart.darcartas(7);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg3 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg3 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg3 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("cartjg3" + cartjg3);
                        mostrarCuadroDialogoConImagenes(7, cartjg3);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg3 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg3 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(7, cartjg3);
                        ordenarcartas = false;
                    }
                }
                if (obrg.getCantidadJ() == 6) {
                    if (darcart == 0) {
                        cartjg3 = "";
                        Cartas_conf[] cartas = repart.darcartas(5);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg3 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg3 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[6]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg3 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("cartjg2" + cartjg2);
                        mostrarCuadroDialogoConImagenes(5, cartjg3);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg3 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[6]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg3 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(5, cartjg3);
                        ordenarcartas = false;
                    }
                }
                if (obrg.getCantidadJ() == 8) {
                    if (darcart == 0) {
                        cartjg3 = "";
                        Cartas_conf[] cartas = repart.darcartas(4);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg3 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg3 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg3 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("cartjg3" + cartjg3);
                        mostrarCuadroDialogoConImagenes(4, cartjg3);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg3 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg3 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(4, cartjg3);
                        ordenarcartas = false;
                    }
                }

            } catch (IOException e) {

                System.out.println("error");

            }

        } else {

            JOptionPane.showMessageDialog(null, "No es tu turno");
        }
    }//GEN-LAST:event_btn_verJ2MouseClicked

    private void sacarCartaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sacarCartaMouseClicked
        // MAZO
//        Cartas_conf siguienteCarta = repart.siguienteCarta();
//        if (siguienteCarta != null) {
//            JOptionPane.showMessageDialog(null, "Carta sacada: " + siguienteCarta);
//         mostrarmazo(siguienteCarta.getTipo()+siguienteCarta.getValor());
//        } else {
//            JOptionPane.showMessageDialog(null, "Ya no hay más cartas, barajear de nuevo");
//        }
    }//GEN-LAST:event_sacarCartaMouseClicked

    private void btn_verJ8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_verJ8MouseClicked
        // JUGADOR 2
        //if(objjg1.||objjg3.equals(true)||objjg4.equals(true)||objjg5.equals(true)||objjg6.equals(true)||objjg7.equals(true)||objjg8.equals(true)){

        if (lt.txtTurnoSting().equals(nombre_J8.getText())) {
            if (contjg2 == 0) {
                darcart = 0;
                contjg2++;
            }
            try {

                System.out.println("" + obrg.getCantidadJ());
                if (obrg.getCantidadJ() == 2) {
                    if (darcart == 0) {
                        cartjg2 = "";
                        Cartas_conf[] cartas = repart.darcartas(7);
                        for (int i = 0; i < cartas.length; i++) {
                            cartjg2 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (descartarcarta == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg2 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg2 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("entro a descartar carta");
                        descartarcartas(7, cartjg2);
                        descartarcarta = false;
                        return;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg2 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg2 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("cartjg2" + cartjg2);
                        mostrarCuadroDialogoConImagenes(7, cartjg2);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg2 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg2 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(7, cartjg2);
                        ordenarcartas = false;
                    }
                }
                if (obrg.getCantidadJ() == 3) {
                    if (darcart == 0) {
                        cartjg2 = "";
                        Cartas_conf[] cartas = repart.darcartas(6);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg2 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg2 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[7]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg2 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("cartjg2" + cartjg2);
                        mostrarCuadroDialogoConImagenes(6, cartjg2);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg2 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[7]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg2 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(6, cartjg2);
                        ordenarcartas = false;
                    }
                }
                if (obrg.getCantidadJ() == 4) {
                    if (darcart == 0) {
                        cartjg2 = "";
                        Cartas_conf[] cartas = repart.darcartas(7);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg2 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg2 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg2 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("cartjg2" + cartjg2);
                        mostrarCuadroDialogoConImagenes(7, cartjg2);
                        JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg2 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg2 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(7, cartjg2);
                        ordenarcartas = false;
                    }
                }
                if (obrg.getCantidadJ() == 6) {
                    if (darcart == 0) {
                        cartjg2 = "";
                        Cartas_conf[] cartas = repart.darcartas(5);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg2 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg2 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[6]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg2 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("cartjg2" + cartjg2);
                        mostrarCuadroDialogoConImagenes(5, cartjg2);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg2 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[6]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg2 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(5, cartjg2);
                        ordenarcartas = false;
                    }
                }
                if (obrg.getCantidadJ() == 8) {
                    if (darcart == 0) {
                        cartjg2 = "";
                        Cartas_conf[] cartas = repart.darcartas(4);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg2 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg2 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg2 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("cartjg2" + cartjg2);
                        mostrarCuadroDialogoConImagenes(7, cartjg2);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg2 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg2 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(4, cartjg2);
                        ordenarcartas = false;
                    }
                }

            } catch (IOException e) {

                System.out.println("error");

            }

        } else {
            JOptionPane.showMessageDialog(null, "No es tu turno");
        }
    }//GEN-LAST:event_btn_verJ8MouseClicked

    private void btn_verJ1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_verJ1MouseClicked
        //JUGADOR 1

        if (lt.txtTurnoSting().equals(nombre_J1.getText())) {
            try {
                if (contjg1 == 0) {
                    darcart = 0;
                    contjg1++;
                }
                System.out.println("" + obrg.getCantidadJ());
                if (obrg.getCantidadJ() == 2) {
                    if (darcart == 0) {
                        cartjg1 = "";
                        Cartas_conf[] cartas = repart.darcartas(7);
                        for (int i = 0; i < cartas.length; i++) {
                            cartjg1 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (descartarcarta == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg1 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg1 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("entro a descartar carta");
                        descartarcartas(7, cartjg1);
                        descartarcarta = false;
                        return;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg1 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg1 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.print("cartjg1" + cartjg1);
                        mostrarCuadroDialogoConImagenes(7, cartjg1);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg1 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg1 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(7, cartjg1);
                        ordenarcartas = false;
                    }

                }
                if (obrg.getCantidadJ() == 3) {
                    if (darcart == 0) {
                        cartjg1 = "";
                        Cartas_conf[] cartas = repart.darcartas(6);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg1 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg1 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[7]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg1 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.print("cartjg1" + cartjg1);
                        mostrarCuadroDialogoConImagenes(6, cartjg1);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg1 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[7]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg1 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(6, cartjg1);
                        ordenarcartas = false;
                    }

                }
                if (obrg.getCantidadJ() == 4) {
                    if (darcart == 0) {
                        cartjg1 = "";
                        Cartas_conf[] cartas = repart.darcartas(7);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg1 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg1 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg1 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.print("cartjg1" + cartjg1);
                        mostrarCuadroDialogoConImagenes(7, cartjg1);
                        JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg1 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg1 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(7, cartjg1);
                        ordenarcartas = false;
                    }
                }
                if (obrg.getCantidadJ() == 6) {
                    if (darcart == 0) {
                        cartjg1 = "";
                        Cartas_conf[] cartas = repart.darcartas(5);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg1 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg1 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[6]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg1 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.print("cartjg1" + cartjg1);
                        mostrarCuadroDialogoConImagenes(5, cartjg1);
                        JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg1 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[6]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg1 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(5, cartjg1);
                        ordenarcartas = false;
                    }

                }
                if (obrg.getCantidadJ() == 8) {
                    if (darcart == 0) {
                        cartjg1 = "";
                        Cartas_conf[] cartas = repart.darcartas(4);
                        for (int i = 0; i < cartas.length; i++) {
                            cartjg1 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg1 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg1 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.print("cartjg1" + cartjg1);
                        mostrarCuadroDialogoConImagenes(4, cartjg1);
                        JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg1 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg1 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(4, cartjg1);
                        ordenarcartas = false;
                    }
                }
            } catch (IOException e) {
                System.out.println("error");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No es tu turno");
        }
    }//GEN-LAST:event_btn_verJ1MouseClicked

    private void btn_verJ7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_verJ7MouseClicked
        // JUGADOR 4

        if (lt.txtTurnoSting().equals(nombre_J7.getText())) {
            try {
                if (contjg4 == 0) {
                    darcart = 0;
                    contjg4++;
                }
                if (obrg.getCantidadJ() == 4) {
                    if (darcart == 0) {
                        cartjg4 = "";
                        Cartas_conf[] cartas = repart.darcartas(7);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg4 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg4 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg4 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("cartjg4" + cartjg4);
                        mostrarCuadroDialogoConImagenes(7, cartjg4);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg4 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[8]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg4 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(7, cartjg4);
                        ordenarcartas = false;
                    }
                }
                if (obrg.getCantidadJ() == 6) {
                    if (darcart == 0) {
                        cartjg4 = "";
                        Cartas_conf[] cartas = repart.darcartas(5);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg4 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg4 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[6]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg4 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("cartjg4" + cartjg4);
                        mostrarCuadroDialogoConImagenes(5, cartjg4);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg4 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[6]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg4 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(5, cartjg4);
                        ordenarcartas = false;
                    }
                }
                if (obrg.getCantidadJ() == 8) {
                    if (darcart == 0) {
                        cartjg4 = "";
                        Cartas_conf[] cartas = repart.darcartas(4);
                        for (int i = 0; i < cartas.length; i++) {

                            cartjg4 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg4 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg4 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("cartjg4" + cartjg4);
                        mostrarCuadroDialogoConImagenes(4, cartjg2);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartjg4 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartjg4 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(4, cartjg4);
                        ordenarcartas = false;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error");
            }

        } else {

            JOptionPane.showMessageDialog(null, "No es tu turno");
        }
    }//GEN-LAST:event_btn_verJ7MouseClicked

    private void btn_verJ3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_verJ3MouseClicked
        // JUGADOR 5

        if (lt.txtTurnoSting().equals(nombre_J3.getText())) {
            try {
                if (contjg5 == 0) {
                    darcart = 0;
                    contjg5++;
                }
                if (obrg.getCantidadJ() == 6) {
                    if (darcart == 0) {
                        cartg5 = "";
                        Cartas_conf[] cartas = repart.darcartas(5);
                        for (int i = 0; i < cartas.length; i++) {

                            cartg5 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartg5 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[6]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartg5 += "\n" + ordenActu[i];

                            }
                        }
                        System.out.println("cartjg3" + cartjg3);
                        mostrarCuadroDialogoConImagenes(5, cartg5);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartg5 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[6]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartg5 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(5, cartg5);
                        ordenarcartas = false;
                    }
                }
                if (obrg.getCantidadJ() == 8) {
                    if (darcart == 0) {
                        cartg5 = "";
                        Cartas_conf[] cartas = repart.darcartas(4);
                        for (int i = 0; i < cartas.length; i++) {
                            cartg5 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartg5 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartg5 += "\n" + ordenActu[i];

                            }
                        }

                        mostrarCuadroDialogoConImagenes(4, cartg5);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartg5 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartg5 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(4, cartg5);
                        ordenarcartas = false;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No es tu turno");
        }

    }//GEN-LAST:event_btn_verJ3MouseClicked

    private void btn_verJ6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_verJ6MouseClicked
        // JUGADOR 6

        if (lt.txtTurnoSting().equals(nombre_J6.getText())) {
            try {
                if (contjg6 == 0) {
                    darcart = 0;
                    contjg6++;
                }
                if (obrg.getCantidadJ() == 6) {
                    if (darcart == 0) {
                        cartg6 = "";
                        Cartas_conf[] cartas = repart.darcartas(5);
                        for (int i = 0; i < cartas.length; i++) {
                            cartg6 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartg6 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[6]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartg6 += "\n" + ordenActu[i];

                            }
                        }

                        mostrarCuadroDialogoConImagenes(5, cartg6);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartg6 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[6]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartg6 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(5, cartg6);
                        ordenarcartas = false;
                    }
                }
                if (obrg.getCantidadJ() == 8) {
                    if (darcart == 0) {
                        cartg6 = "";
                        Cartas_conf[] cartas = repart.darcartas(4);
                        for (int i = 0; i < cartas.length; i++) {
                            cartg6 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartg6 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartg6 += "\n" + ordenActu[i];

                            }
                        }

                        mostrarCuadroDialogoConImagenes(4, cartg6);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartg6 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartg6 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(4, cartg6);
                        ordenarcartas = false;
                    }

                }
            } catch (IOException e) {
                System.out.println("Error");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No es tu turno");
        }

    }//GEN-LAST:event_btn_verJ6MouseClicked

    private void btn_verJ5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_verJ5MouseClicked
        // JUGADOR 7

        if (lt.txtTurnoSting().equals(nombre_J5.getText())) {
            try {
                if (contjg7 == 0) {
                    darcart = 0;
                    contjg7++;
                }
                if (obrg.getCantidadJ() == 8) {
                    if (darcart == 0) {
                        cartg7 = "";
                        Cartas_conf[] cartas = repart.darcartas(4);
                        for (int i = 0; i < cartas.length; i++) {

                            cartg7 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartg7 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartg7 += "\n" + ordenActu[i];

                            }
                        }

                        mostrarCuadroDialogoConImagenes(4, cartg7);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartg7 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartg7 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(4, cartg7);
                        ordenarcartas = false;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No es tu turno");
        }

    }//GEN-LAST:event_btn_verJ5MouseClicked

    private void btn_verJ4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_verJ4MouseClicked
        // JUGADOR 8

        if (lt.txtTurnoSting().equals(nombre_J4.getText())) {
            try {
                if (contjg8 == 0) {
                    darcart = 0;
                    contjg8++;
                }
                if (obrg.getCantidadJ() == 8) {
                    if (darcart == 0) {
                        cartg8 = "";
                        Cartas_conf[] cartas = repart.darcartas(4);
                        for (int i = 0; i < cartas.length; i++) {

                            cartg8 += "\n" + cartas[i];
                        }
                        darcart = 1;
                    }
                    if (ordenarcartas == false) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartg8 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartg8 += "\n" + ordenActu[i];

                            }
                        }

                        mostrarCuadroDialogoConImagenes(4, cartg8);
                        //JOptionPane.showMessageDialog(null, cartjg1);
                    } else if (ordenarcartas == true) {
                        if (!ordenActualCartas.isEmpty() && !ordenActualCartas.contains("0")) {
                            cartg8 = "";
                            String[] ordenActu = ordenActualCartas.toArray(new String[5]);

                            for (int i = 1; i < ordenActu.length; i++) {
                                cartg8 += "\n" + ordenActu[i];
                            }
                        }

                        ordenarcartas(4, cartg8);
                        ordenarcartas = false;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No es tu turno");
        }

    }//GEN-LAST:event_btn_verJ4MouseClicked

    private void OrdenarcartasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrdenarcartasMouseClicked

        //Activar variable ordenar carta 
        ordenarcartas = true;
        JOptionPane.showMessageDialog(null, "Modo Ordenar Activado");
    }//GEN-LAST:event_OrdenarcartasMouseClicked

    private void descartar_cartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descartar_cartaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descartar_cartaActionPerformed

    private void descartar_cartaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descartar_cartaMouseClicked
        // AQUI PARA DESCARTAS CARTA
        descartarcarta = true;
        JOptionPane.showMessageDialog(null, "Ojo!Solo puedes descartar una por turno");
    }//GEN-LAST:event_descartar_cartaMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new tablero().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ordenarcartas;
    private javax.swing.JButton btn_reglas;
    private javax.swing.JButton btn_verJ1;
    private javax.swing.JButton btn_verJ2;
    private javax.swing.JButton btn_verJ3;
    private javax.swing.JButton btn_verJ4;
    private javax.swing.JButton btn_verJ5;
    private javax.swing.JButton btn_verJ6;
    private javax.swing.JButton btn_verJ7;
    private javax.swing.JButton btn_verJ8;
    private javax.swing.JButton descartar_carta;
    private javax.swing.JLabel equipo_J1;
    private javax.swing.JLabel equipo_J2;
    private javax.swing.JLabel equipo_J3;
    private javax.swing.JLabel equipo_J4;
    private javax.swing.JLabel equipo_J5;
    private javax.swing.JLabel equipo_J6;
    private javax.swing.JLabel equipo_J7;
    private javax.swing.JLabel equipo_J8;
    private javax.swing.JLabel fondo_tab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel maz;
    private javax.swing.JButton menu;
    private javax.swing.JPanel noBorrar;
    private javax.swing.JPanel noBorrar2;
    private javax.swing.JLabel nombre_J1;
    private javax.swing.JLabel nombre_J2;
    private javax.swing.JLabel nombre_J3;
    private javax.swing.JLabel nombre_J4;
    private javax.swing.JLabel nombre_J5;
    private javax.swing.JLabel nombre_J6;
    private javax.swing.JLabel nombre_J7;
    private javax.swing.JLabel nombre_J8;
    private javax.swing.JPanel panelJ1;
    private javax.swing.JPanel panelJ2;
    private javax.swing.JPanel panelJ3;
    private javax.swing.JPanel panelJ4;
    private javax.swing.JPanel panelJ5;
    private javax.swing.JPanel panelJ6;
    private javax.swing.JPanel panelJ7;
    private javax.swing.JPanel panelJ8;
    private javax.swing.JPanel panelJ_1al4;
    private javax.swing.JPanel panel_5al8;
    private javax.swing.JPanel panel_info;
    private javax.swing.JPanel principal;
    private javax.swing.JButton sacarCarta;
    private javax.swing.JPanel tab;
    private javax.swing.JLabel time;
    private javax.swing.JLabel turno;
    // End of variables declaration//GEN-END:variables
}
