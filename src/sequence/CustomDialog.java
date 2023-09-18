/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sequence;

import clases.registro;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class CustomDialog extends JDialog {
    private JPanel panel;
    private String name;

    public CustomDialog(JFrame parent, JPanel panel, String name) {
        super(parent, name, true);
        this.panel = panel;
        this.name = name;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon fondo = new ImageIcon("src/images/fondos/fondo_cartasjug.png");
                g.drawImage(fondo.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.CENTER);

        add(contentPane);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//despues ppner do nothing pq si o si debe hacer algo
    }
}
