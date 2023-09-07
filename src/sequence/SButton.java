/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sequence;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
public class SButton extends JButton{

	//does everything that JButton does but also stores other 
	//information related to Sequence
	
	//THIS unique button's position on the board
	int i, j;
	//the TWO positions of the same card on the board
	int x1, y1, x2, y2;
	//the card number
	int cardNumber;

}
