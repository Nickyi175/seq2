/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sequence;

import java.util.HashMap;

import javax.swing.JButton;
public class HumanSequencePlayer extends ASequencePlayer{

	private HashMap<Integer, JButton> handMap;
	
	public HumanSequencePlayer(int number) {
		
		super(number);
		
		handMap = new HashMap<Integer, JButton>();
		
	}
	
	HashMap<Integer, JButton> getHandMap() {
		return handMap;
	}
	
	void enableAllHandCards() {
		for (HashMap.Entry<Integer, JButton> mapElement : handMap.entrySet()) { 
			mapElement.getValue().setEnabled(true);
		}
	}

}
