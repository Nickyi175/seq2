/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sequence;
import java.util.Random;
public class CpuSequencePlayer extends ASequencePlayer{
Random r = new Random();
	char[] colorSelection = {'r', 'b', 'g','y','p','n','o','c'};

	public CpuSequencePlayer(int number) {

		super(number);
		playerName = "CPU Overlord";

	}

	void makeAMove(SequenceGame game) {
		boolean isPlayed = false;
		SequenceGameGUI gui = game.gui;

		do {
			int len = hand.size();
			int index = r.nextInt(len);
			ASequenceCard c = hand.get(index);
			game.lastPlayedCard = c;
			
			if(c.getIsOneEyedJack()) {
				//one eyed jack

				outerloop:
				for(int i=0; i<10; i++)
					for(int j=0; j<10; j++)
						if(game.board[i][j]!=' ' && game.board[i][j]!='C'
							&& game.board[i][j]!=playerColor && r.nextInt(10)==5) {
							//a token found at this location, 1/10 chance to be selected
							game.board[i][j] = ' ';
							gui.tokenButtons[i][j].setIcon(null);
							hand.remove(index);
							game.oneEyedJackIsPlayed = true;
							game.lastPlayedX = i;
							game.lastPlayedY = j;

							//System.out.println("CPU's move: " + i + ", " + j);
							isPlayed = true;
							break outerloop;
						}
			}	
			else if(c.getIsTwoEyedJack()) {
				outerloop:
				for(int i=0; i<10; i++)
					for(int j=0; j<10; j++)
						if(game.board[i][j]==' ' && r.nextInt(80)==5) {
							//no token found at this location, 1/80 chance to be selected
							game.board[i][j] = playerColor;
							switch(playerColor) {
							case 'r': gui.tokenButtons[i][j].setIcon(gui.rojoToken); 
									  gui.tokenButtons[i][j].setDisabledIcon(gui.rojoToken); 
									  break;
							case 'b': gui.tokenButtons[i][j].setIcon(gui.azulToken); 
									  gui.tokenButtons[i][j].setDisabledIcon(gui.azulToken); 
									  break;
							case 'g': gui.tokenButtons[i][j].setIcon(gui.verdeToken); 
									  gui.tokenButtons[i][j].setDisabledIcon(gui.verdeToken);
                                                                          break;
							case 'p': gui.tokenButtons[i][j].setIcon(gui.moradoToken); 
									  gui.tokenButtons[i][j].setDisabledIcon(gui.moradoToken); 
									  break;
							case 'y': gui.tokenButtons[i][j].setIcon(gui.amarilloToken); 
									  gui.tokenButtons[i][j].setDisabledIcon(gui.amarilloToken); 
									  break;
							case 'c': gui.tokenButtons[i][j].setIcon(gui.cafeToken); 
									  gui.tokenButtons[i][j].setDisabledIcon(gui.cafeToken);
                                                                          break;
							case 'n': gui.tokenButtons[i][j].setIcon(gui.negroToken); 
									  gui.tokenButtons[i][j].setDisabledIcon(gui.negroToken); 
									  break;
							case 'o': gui.tokenButtons[i][j].setIcon(gui.naranjaToken); 
									  gui.tokenButtons[i][j].setDisabledIcon(gui.naranjaToken); 
									  break;
							   }
							hand.remove(index);
							game.lastPlayedX = i;
							game.lastPlayedY = j;

							//System.out.println("CPU's move: " + i + ", " + j);
							isPlayed = true;
							break outerloop;
						}
			}
			else {
				//non-jack card
				int x1 = c.getX1(), y1 = c.getY1();
				int x2 = c.getX2(), y2 = c.getY2();
				
				if(game.board[x1][y1]==' ' && game.board[x2][y2]==' ') {
					int choice = r.nextInt(1);
					if(choice==0) {
						game.board[x1][y1] = playerColor;
						switch(playerColor) {
						case 'r': gui.tokenButtons[x1][y1].setIcon(gui.rojoToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.rojoToken); 
								  break;
						case 'b': gui.tokenButtons[x1][y1].setIcon(gui.azulToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.azulToken); 
								  break;
						case 'g': gui.tokenButtons[x1][y1].setIcon(gui.verdeToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.verdeToken);
                                                                  break;
						case 'p': gui.tokenButtons[x1][y1].setIcon(gui.moradoToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.moradoToken); 
								  break;
						case 'y': gui.tokenButtons[x1][y1].setIcon(gui.amarilloToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.amarilloToken); 
								  break;
						case 'c': gui.tokenButtons[x1][y1].setIcon(gui.cafeToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.cafeToken);
                                                                  break;
						case 'n': gui.tokenButtons[x1][y1].setIcon(gui.negroToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.negroToken); 
								  break;
						case 'o': gui.tokenButtons[x1][y1].setIcon(gui.naranjaToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.naranjaToken); 
								  break;
						
                                                }//switch
						
						game.lastPlayedX = x1;
						game.lastPlayedY = y1;
						
						//System.out.println("CPU's move: " + x1 + ", " + y1);
					}//inner if
					else {
						game.board[x2][y2] = playerColor;
						switch(playerColor) {
						case 'r': gui.tokenButtons[x2][y2].setIcon(gui.rojoToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.rojoToken); 
								  break;
						case 'b': gui.tokenButtons[x2][y2].setIcon(gui.azulToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.azulToken); 
								  break;
						case 'g': gui.tokenButtons[x2][y2].setIcon(gui.verdeToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.verdeToken);
                                                                  break;
                                                case 'p': gui.tokenButtons[x2][y2].setIcon(gui.moradoToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.moradoToken); 
								  break;
						case 'y': gui.tokenButtons[x2][y2].setIcon(gui.amarilloToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.amarilloToken); 
								  break;
						case 'c': gui.tokenButtons[x2][y2].setIcon(gui.cafeToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.cafeToken);
                                                                  break;
                                                case 'n': gui.tokenButtons[x2][y2].setIcon(gui.negroToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.negroToken); 
								  break;
						case 'o': gui.tokenButtons[x2][y2].setIcon(gui.naranjaToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.naranjaToken); 
								  break;
						}//switch

						game.lastPlayedX = x2;
						game.lastPlayedY = y2;

						//System.out.println("CPU's move: " + x2 + ", " + y2);
					}//inner else
					
					hand.remove(index);
					isPlayed = true;
				}//outer if
				else if(game.board[x1][y1]==' ') {
						game.board[x1][y1] = playerColor;
						switch(playerColor) {
						case 'r': gui.tokenButtons[x1][y1].setIcon(gui.rojoToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.rojoToken); 
								  break;
						case 'b': gui.tokenButtons[x1][y1].setIcon(gui.azulToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.azulToken); 
								  break;
						case 'g': gui.tokenButtons[x1][y1].setIcon(gui.verdeToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.verdeToken);
                                                                  break;
						case 'p': gui.tokenButtons[x1][y1].setIcon(gui.moradoToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.moradoToken); 
								  break;
						case 'y': gui.tokenButtons[x1][y1].setIcon(gui.amarilloToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.amarilloToken); 
								  break;
						case 'c': gui.tokenButtons[x1][y1].setIcon(gui.cafeToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.cafeToken);
                                                                  break;
                                                case 'n': gui.tokenButtons[x1][y1].setIcon(gui.negroToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.negroToken); 
								  break;
						case 'o': gui.tokenButtons[x1][y1].setIcon(gui.naranjaToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.naranjaToken); 
								  break;
						}//switch

						game.lastPlayedX = x1;
						game.lastPlayedY = y1;
						
						hand.remove(index);
						isPlayed = true;
						
						//System.out.println("CPU's move: " + x1 + ", " + y1);
				}
				else if(game.board[x2][y2]==' ') {
					game.board[x2][y2] = playerColor;
					switch(playerColor) {
                                        case 'r': gui.tokenButtons[x2][y2].setIcon(gui.rojoToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.rojoToken); 
								  break;
					case 'b': gui.tokenButtons[x2][y2].setIcon(gui.azulToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.azulToken); 
								  break;
                                	case 'g': gui.tokenButtons[x2][y2].setIcon(gui.verdeToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.verdeToken);
                                                                  break;
					case 'p': gui.tokenButtons[x2][y2].setIcon(gui.moradoToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.moradoToken); 
								  break;
					case 'y': gui.tokenButtons[x2][y2].setIcon(gui.amarilloToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.amarilloToken); 
								  break;
					case 'c': gui.tokenButtons[x2][y2].setIcon(gui.cafeToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.cafeToken);
                                                                  break;
                                        case 'n': gui.tokenButtons[x2][y2].setIcon(gui.negroToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.negroToken); 
								  break;
					case 'o': gui.tokenButtons[x2][y2].setIcon(gui.naranjaToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.naranjaToken); 
								  break;
						}//switch

					game.lastPlayedX = x2;
					game.lastPlayedY = y2;
					
					hand.remove(index);
					isPlayed = true;

					//System.out.println("CPU's move: " + x2 + ", " + y2);
				}
				else {
					//recycle card and play a new card
					//***isPlayed should be left as FALSE
					ASequenceCard temp = hand.remove(index);
					//CPU recycled a card:
					game.log.updateLog("Carta reciclada " + temp.getCardName());
					
					game.Chris.dealCard(this);

					
				}

			}
		}while(!isPlayed);
		
		game.Chris.dealCard(this);

		//System.out.println("CPU ended turn");
	}//end of makeAMove
	
	void elegirColor(int i) {
		int s;
		do {
			s = r.nextInt(8);
		}while(s==i);
		
		playerColor = colorSelection[s];
	}
}//end of class