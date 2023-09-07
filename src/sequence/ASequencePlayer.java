/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sequence;
import java.util.LinkedList;
public class ASequencePlayer {
protected int playerNumber;
	protected char playerColor;
	String playerName;
	LinkedList<ASequenceCard> hand = new LinkedList<ASequenceCard>();

	public ASequencePlayer(int number) {
		
		playerNumber = number;
		
	}
	
	void printHand() {
		for(ASequenceCard i : hand)
			System.out.print(i.getCardName() + " ");
		System.out.println("\n");
	}
	
	String getHand() {
		String s = "";
		for(ASequenceCard i : hand)
			s+=i.getCardName() + ", ";
		return s;
	}
	
}