 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sequence;

import clases.Repartircartas_tab;
import clases.registro;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MenuPrincipal extends javax.swing.JFrame {

    registro obrg = new registro();

    public MenuPrincipal() {
        initComponents();
//        ImageIcon icon = new ImageIcon("src/images/fondos/fondo_menu_principal.png");
//        fondo_m.setIcon(icon);
        
        try {
            obrg.imprimirModo();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SignOut = new javax.swing.JButton();
        configuracion = new javax.swing.JButton();
        reportes = new javax.swing.JButton();
        jugar = new javax.swing.JButton();
        fondo_m = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setOpaque(false);

        SignOut.setBackground(new java.awt.Color(0, 0, 0));
        SignOut.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        SignOut.setForeground(new java.awt.Color(255, 255, 255));
        SignOut.setText("CERRAR SESIÓN");
        SignOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignOutMouseClicked(evt);
            }
        });

        configuracion.setBackground(new java.awt.Color(0, 0, 0));
        configuracion.setFont(new java.awt.Font("Arial", 0, 32)); // NOI18N
        configuracion.setForeground(new java.awt.Color(255, 255, 255));
        configuracion.setText("CONFIGURACIÓN");
        configuracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                configuracionMouseClicked(evt);
            }
        });

        reportes.setBackground(new java.awt.Color(0, 0, 0));
        reportes.setFont(new java.awt.Font("Arial", 0, 32)); // NOI18N
        reportes.setForeground(new java.awt.Color(255, 255, 255));
        reportes.setText("REPORTES");
        reportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportesMouseClicked(evt);
            }
        });

        jugar.setBackground(new java.awt.Color(0, 0, 0));
        jugar.setFont(new java.awt.Font("Arial", 0, 32)); // NOI18N
        jugar.setForeground(new java.awt.Color(255, 255, 255));
        jugar.setText("JUGAR");
        jugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jugarMouseClicked(evt);
            }
        });
        jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SignOut)
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reportes, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(configuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jugar, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(194, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {configuracion, jugar, reportes});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SignOut)
                .addGap(95, 95, 95)
                .addComponent(jugar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(configuracion)
                .addGap(18, 18, 18)
                .addComponent(reportes, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {configuracion, jugar, reportes});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 692, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(fondo_m, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(fondo_m, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SignOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignOutMouseClicked
        MenuInicio mi = new MenuInicio();
        mi.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_SignOutMouseClicked

    private void configuracionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configuracionMouseClicked
        Configuracion objconf = new Configuracion();
        objconf.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_configuracionMouseClicked

    private void reportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportesMouseClicked
//        try {
//            obrg.agregarReportes("NO");        
//        } catch (IOException e) {
//            e.printStackTrace(); 
//        }
        reportes mi=new reportes();
        mi.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_reportesMouseClicked

    private void jugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jugarMouseClicked

        try {
            registro r = new registro();
           // System.out.println(r.contarUsuarios());
           
            System.out.println();
            if (r.contarUsuarios() >= r.getCantidadJ()) {
                multiJugador c = new multiJugador();
                c.setVisible(true);
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "No hay suficientes jugadores para la partida");
                
            }
        } catch (IOException e) {
            System.out.println("error");

        }
    }//GEN-LAST:event_jugarMouseClicked

    private void jugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jugarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SignOut;
    private javax.swing.JButton configuracion;
    private javax.swing.JLabel fondo_m;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jugar;
    private javax.swing.JButton reportes;
    // End of variables declaration//GEN-END:variables
}
