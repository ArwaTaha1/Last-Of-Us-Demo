package views;



	import java.awt.BorderLayout;

	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.FlowLayout;
	import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
	import java.awt.Image;
	import java.awt.TextArea;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
	import javax.swing.*;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.*;
import model.characters.Character;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;


	public class view extends JFrame implements ActionListener{
	    private JPanel mainPanel;
	    private JPanel WorldPanel;
	    private JPanel characterPanel = new JPanel();
	    private JPanel functionPanel;
	    private TextArea Info;
	    private TextArea characterInfo=new TextArea();
	    private TextArea errorMessage=new TextArea();
	    private TextArea selectedHeroTextArea= new TextArea();
	    private TextArea selectedTargetTextArea=new TextArea();
	    private JButton endTurn;
	    private JButton World [][];
	    private Character selectedCharacter;
	    private Hero selectedAttacker;
	    private Character selectedTarget;
	    

	    public view() throws Exception {
//	    	this.addKeyListener(new KeyListener() {
//	    		@Override
//	    		public void keyTyped(KeyEvent e) {
//	    			System.out.println("reads");
//
//	    			// TODO Auto-generated method stub
//	    			
//	    		}
//	    		@Override
//	    		public void keyPressed(KeyEvent e) {
//	    			int code = e.getKeyCode();
//	    			System.out.println("reads");
//	    			
//	    			if (code!=0){
//	    				try {
//	    					selectedAttacker.move(Direction.UP);
//	    					updateMap();
//	    					System.out.println("test");
//	    				} catch (MovementException e1) {
//	    					// TODO Auto-generated catch block
//	    					e1.printStackTrace();
//	    				} catch (NotEnoughActionsException e1) {
//	    					// TODO Auto-generated catch block
//	    					e1.printStackTrace();
//	    				}
//	    			}
//	    			if (code==KeyEvent.VK_S){
//	    			}
//	    			if (code==KeyEvent.VK_A){
//	    			}
//	    			if (code==KeyEvent.VK_D) {
//	    			}
//	    			// TODO Auto-generated method stub
//	    			
//	    		}
//	    		@Override
//	    		public void keyReleased(KeyEvent e) {
//	    			// TODO Auto-generated method stub
//	    			
//	    		}
//
//	    	});


	        mainPanel = new JPanel(new BorderLayout(8,8));
	        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,8,8));
	        mainPanel.setFocusable(true);
	        World = new JButton [15][15]; 
	        //BorderFactory.createEmptyBorder(top, left, bottom, right);
	        getContentPane().add(mainPanel,BorderLayout.CENTER);
	        setTitle("Last Of Us");
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setBounds(100, 0, 1500, 1000);
	        mainPanel.setLayout(null);
	        System.out.println("teststsatsad");
	        //pack(); //sets JFrame to minimum size to display all elements inside it.
	        createWorldPanel();
	        updateMap();
	        createInfo();
	        createActionPanel();
	        createErrorInfo();
	        createSelectionPanel();
	        createButtonPad();
	        createEndTurnButton();
	        setVisible(true);
	    }
	    public void reset() {
	    	createInfo();
	    	selectedCharacter=null;
	    	selectedAttacker=null;
	    	selectedTarget=null;
	    	characterInfo=new TextArea();
		    errorMessage.setText("");;
		    selectedHeroTextArea.setText("");
		    selectedTargetTextArea.setText("");
		    

	    	
	    }
	    
		public void createInfo(){
			JPanel InfoPanel = new JPanel();
			InfoPanel.setBounds(0,260,600,100);
			InfoPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			InfoPanel.setBackground(Color.white);
			Info = new TextArea();
			Info.setPreferredSize(new Dimension(600,300));
			Info.setEditable(false);
			Info.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			Info.setBackground(Color.WHITE);
			String infoText="Number of Zombies: "+Game.zombies.size()+"\n";
			infoText+="Number Of Heroes: "+Game.heroes.size();
			
			Info.setText(infoText);
			characterPanel.setBounds(0,350,600,150);
			characterPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			characterPanel.setBackground(Color.white);
			characterInfo.setPreferredSize(new Dimension(600,300));
			characterInfo.setEditable(false);
			characterInfo.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
			characterInfo.setBackground(Color.WHITE);
			characterPanel.add(characterInfo);
			InfoPanel.add(Info,BorderLayout.NORTH);
			mainPanel.add(InfoPanel, BorderLayout.WEST);
			mainPanel.add(characterPanel);
		}
		
		public void createEndTurnButton() {
	    	JButton endTurn=new JButton("End Turn");
	    	endTurn.setPreferredSize(new Dimension(200,200));
	    	endTurn.setBounds(300, 700, 100, 100);
	    	endTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						Game.endTurn();
						updateMap();
						refreshWorldPanel();
						reset();
						
						}catch(NullPointerException e1) {
						errorMessage.setText("Please choose a target");
					} 
					catch (NotEnoughActionsException e1) {
							// TODO Auto-generated catch block
							errorMessage.setText(e1.getMessage());
						} catch (InvalidTargetException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			}
		});	 

	     mainPanel.add(endTurn);
			
		}
		public void FourthScene() {
			if(Game.checkWin()) {
		        JPanel endGame = new JPanel(new BorderLayout(8,8));
		        endGame.setBorder(BorderFactory.createEmptyBorder(15,15,8,8));
		        TextArea endGameText=new TextArea();
		        endGameText.setText("You Survived!");
		        
		        endGameText.setPreferredSize(new Dimension(600,300));
		        endGameText.setEditable(false);
		        endGameText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		        endGameText.setBackground(Color.WHITE);
		        endGame.add(endGameText);
		        this.add(endGame);

		        
 
				
			}else {
				if(Game.checkGameOver()) {
					 JPanel endGame = new JPanel(new BorderLayout(8,8));
				        endGame.setBorder(BorderFactory.createEmptyBorder(15,15,8,8));
				        TextArea endGameText=new TextArea();
				        endGameText.setText("You Lost!");
				        
				        endGameText.setPreferredSize(new Dimension(600,300));
				        endGameText.setEditable(false);
				        endGameText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
				        endGameText.setBackground(Color.WHITE);
				        endGame.add(endGameText);
				        this.add(endGame);

				}
			}
		}
		public void createButtonPad() {
			JPanel buttonPad=new JPanel();
			buttonPad.setBounds(0,700,200,100);
			buttonPad.setLayout(new GridLayout(2,3));
			buttonPad.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			buttonPad.setBackground(Color.darkGray);
	    	TextArea blank=new TextArea();
	    	JButton Up=new JButton("UP");
	    	Up.setPreferredSize(new Dimension(200,200));
	    	Up.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						if(selectedAttacker!=null) {
							selectedAttacker.move(Direction.UP);
							updateMap();
							refreshWorldPanel();
						}else {
							
						}
						}catch(NullPointerException e1) {
						errorMessage.setText("Please choose a target");
					} catch (MovementException e1) {
							// TODO Auto-generated catch block
						errorMessage.setText(e1.getMessage());
						} catch (NotEnoughActionsException e1) {
							// TODO Auto-generated catch block
							errorMessage.setText(e1.getMessage());
						}
			}
		});	 
	    	JButton down=new JButton("DOWN");
	    	down.setPreferredSize(new Dimension(200,200));
	    	down.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						if(selectedAttacker!=null) {
							selectedAttacker.move(Direction.DOWN);
							updateMap();
							refreshWorldPanel();
						}else {
							
						}
						}catch(NullPointerException e1) {
						errorMessage.setText("Please choose a target");
					} catch (MovementException e1) {
							// TODO Auto-generated catch block
						errorMessage.setText(e1.getMessage());
						} catch (NotEnoughActionsException e1) {
							// TODO Auto-generated catch block
							errorMessage.setText(e1.getMessage());
						}
			}
		});	 
	    	JButton left=new JButton("LEFT");
	    	left.setPreferredSize(new Dimension(200,200));
	    	left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						if(selectedAttacker!=null) {
							selectedAttacker.move(Direction.LEFT);
							updateMap();
							refreshWorldPanel();

						}else {
							
						}
						}catch(NullPointerException e1) {
						errorMessage.setText("Please choose a target");
					} catch (MovementException e1) {
							// TODO Auto-generated catch block
						errorMessage.setText(e1.getMessage());
						} catch (NotEnoughActionsException e1) {
							// TODO Auto-generated catch block
							errorMessage.setText(e1.getMessage());
						}
			}
		});	 
	    	
	    	JButton right=new JButton("RIGHT");
	    	right.setPreferredSize(new Dimension(200,200));
	    	right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						if(selectedAttacker!=null) {
							selectedAttacker.move(Direction.RIGHT);
							updateMap();
							refreshWorldPanel();

						}else {
							
						}
						}catch(NullPointerException e1) {
						errorMessage.setText("Please choose a target");
					} catch (MovementException e1) {
							// TODO Auto-generated catch block
						errorMessage.setText(e1.getMessage());
						} catch (NotEnoughActionsException e1) {
							// TODO Auto-generated catch block
							errorMessage.setText(e1.getMessage());
						}
			}
		});	 
	    	
	    	
	    	buttonPad.add(new JPanel());
	    	buttonPad.add(Up);
	    	buttonPad.add(new JPanel());
	    	buttonPad.add(left);
	    	buttonPad.add(down);
	    	buttonPad.add(right);

	    	mainPanel.add(buttonPad);
	    	

	    	
	    	
		}
		public void updateInfo() {
			String infoText="Number of Zombies: "+Game.zombies.size()+"\n";
			infoText+="Number Of Heroes: "+Game.heroes.size();
			
			if(selectedCharacter!=null) {
				String characterInfoText="";
				if(selectedCharacter instanceof Hero) {
					
					Hero selectedHero=(Hero) selectedCharacter;
					characterInfoText="Type: Hero"+"\n";
					characterInfoText+="Name: "+selectedHero.getName()+"\n";
					characterInfoText+="Class: "+selectedHero.getClass().getName()+ "\n";
					characterInfoText+="Health: "+selectedHero.getCurrentHp()+"\n";
					characterInfoText+="Attack Damage: "+selectedHero.getAttackDmg()+"\n";
					characterInfoText+="No of Actions: "+selectedHero.getActionsAvailable()+"\n";
					characterInfoText+="No of vaccines: "+selectedHero.getVaccineInventory().size()+"\n";
					characterInfoText+="No of Supplies: "+selectedHero.getSupplyInventory().size();
					
					
					
				}else {
					characterInfoText="Type: Zombie"+"\n";
					characterInfoText+="Health: "+selectedCharacter.getCurrentHp()+"\n";
				}
				characterInfo.setText(characterInfoText);
			}
			
			Info.setText(infoText);
			
		}
		
		public void createSelectionPanel() {
	    	JPanel selectionPanel=new JPanel();
	    	selectionPanel.setBounds(0,500,600,200);
	    	selectionPanel.setLayout(new GridLayout(2,2));
	    	selectionPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	    	selectionPanel.setBackground(Color.darkGray);
	    	selectedHeroTextArea=new TextArea();
	    	selectedHeroTextArea.setPreferredSize(new Dimension(600,300));
	    	selectedHeroTextArea.setEditable(false);
	    	selectedHeroTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
	    	selectedHeroTextArea.setBackground(Color.WHITE);
			selectionPanel.add(selectedHeroTextArea);
	    	JButton selectHero=new JButton("Select Hero");
	    	selectHero.setPreferredSize(new Dimension(200,200));

	    	selectHero.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if(!(selectedCharacter instanceof Hero)) {
							errorMessage.setText("Please Select A Hero");
							selectedHeroTextArea.setText("");
							selectedAttacker=null;
							
						}else {
						Hero selectedHero=(Hero) selectedCharacter;
						selectedAttacker=selectedHero;

						String characterInfoText="";
						characterInfoText="Type: Hero"+"\n";
						characterInfoText+="Name: "+selectedHero.getName()+"\n";
						characterInfoText+="Class: "+selectedHero.getClass().getName()+ "\n";
						characterInfoText+="Health: "+selectedHero.getCurrentHp()+"\n";
						characterInfoText+="Attack Damage: "+selectedHero.getAttackDmg()+"\n";
						characterInfoText+="No of Actions: "+selectedHero.getActionsAvailable()+"\n";
						characterInfoText+="No of vaccines: "+selectedHero.getVaccineInventory().size()+"\n";
						characterInfoText+="No of Supplies: "+selectedHero.getSupplyInventory().size();						updateMap();
						selectedHeroTextArea.setText(characterInfoText);
						}
						}catch(NullPointerException e1) {
						errorMessage.setText("Please choose a target");
					}
			}
		});
	    	JButton selectTarget=new JButton("Select Target");
	    	selectTarget.setPreferredSize(new Dimension(200,200));
	    	selectTarget.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String characterInfoText="";
						if(selectedCharacter instanceof Hero) {
							
							Hero selectedHero=(Hero) selectedCharacter;
							characterInfoText="Type: Hero"+"\n";
							characterInfoText+="Name: "+selectedHero.getName()+"\n";
							characterInfoText+="Class: "+selectedHero.getClass().getName()+ "\n";
							characterInfoText+="Health: "+selectedHero.getCurrentHp()+"\n";
							characterInfoText+="Attack Damage: "+selectedHero.getAttackDmg()+"\n";
							characterInfoText+="No of Actions: "+selectedHero.getActionsAvailable()+"\n";
							characterInfoText+="No of vaccines: "+selectedHero.getVaccineInventory().size()+"\n";
							characterInfoText+="No of Supplies: "+selectedHero.getSupplyInventory().size();
							
							
							
						}else {
							characterInfoText="Type: Zombie"+"\n";
							characterInfoText+="Health: "+selectedCharacter.getCurrentHp()+"\n";
						}
						selectedTarget=selectedCharacter;
						selectedTargetTextArea.setText(characterInfoText);
						

						
					} catch(NullPointerException e1) {
						errorMessage.setText("Please choose a target");
					}
			}
		});
	    	
	  	    selectionPanel.add(selectHero);
	    	selectionPanel.add(selectedTargetTextArea);
	    	selectionPanel.add(selectTarget);
	    	
	    	mainPanel.add(selectionPanel);

		}

	    public void createActionPanel() {
	    	functionPanel=new JPanel();
	    	functionPanel.setBounds(0,0,600,200);
	    	functionPanel.setLayout(new GridLayout(3,1));
	    	functionPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	    	functionPanel.setBackground(Color.darkGray);
	    	JButton attack=new JButton("Attack");
            attack.setPreferredSize(new Dimension(200,200));

	    	attack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						selectedAttacker.setTarget(selectedTarget);
						selectedAttacker.attack();
						updateMap();
						refreshWorldPanel();
						selectedCharacter=null;
						updateInfo();
						
					} catch (NotEnoughActionsException e1) {
						errorMessage.setText(e1.getMessage());
						e1.printStackTrace();
					} catch (InvalidTargetException e1) {
						errorMessage.setText(e1.getMessage());
						// TODO Auto-generated catch block
					}catch(NullPointerException e1) {
						errorMessage.setText("Please choose a valid hero and target");
					}
			}
		});
	    	JButton useSpeical=new JButton("Use Special");
	    	useSpeical.setPreferredSize(new Dimension(200,200));
	    	useSpeical.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Hero selectedHero=(Hero) selectedCharacter;
						selectedHero.useSpecial();
						updateMap();
						refreshWorldPanel();
						selectedCharacter=null;
						updateInfo();
						
					} catch (InvalidTargetException e1) {
						// TODO Auto-generated catch block
						errorMessage.setText(e1.getMessage());
					} catch (NoAvailableResourcesException e1) {
						// TODO Auto-generated catch block
						errorMessage.setText(e1.getMessage());
					}catch(NullPointerException e1) {
						errorMessage.setText("Please choose a target");
					}
			}
		});
	    	
	    	JButton cure=new JButton("Cure");
	    	cure.setPreferredSize(new Dimension(200,200));
	    	cure.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						selectedAttacker.setTarget(selectedTarget);
						selectedAttacker.cure();
						updateMap();
						refreshWorldPanel();
						selectedCharacter=null;
						functionPanel.setVisible(false);
						updateInfo();
						
					} catch (InvalidTargetException e1) {
						errorMessage.setText(e1.getMessage());
						e1.printStackTrace();
					} catch (NoAvailableResourcesException e1) {
						errorMessage.setText(e1.getMessage());
						e1.printStackTrace();
					} catch (NotEnoughActionsException e1) {
						errorMessage.setText(e1.getMessage());
						e1.printStackTrace();
					} catch(NullPointerException e1) {
						errorMessage.setText("Please choose a target");
					}
			}
		});
	    	functionPanel.add(attack);
	    	functionPanel.add(useSpeical);
	    	functionPanel.add(cure);
	    	
	    	mainPanel.add(functionPanel);

	    	
	    	
	    }
	    public void refreshWorldPanel() {
	    	
	    	WorldPanel.removeAll();
	    	mainPanel.validate();

	    	WorldPanel.validate();
	    	WorldPanel.updateUI();
	    

	    	 for(int i=0;i<15;i++) {
		            for(int j=0;j<15;j++) {
		                WorldPanel.add(World[i][j]);
		                WorldPanel.validate();
		                
		                
		            }
		        }
		    	mainPanel.validate();

	    	WorldPanel.validate();
	    	WorldPanel.updateUI();

	    	mainPanel.validate();
	    	this.validate();
	    	FourthScene();
	    }
	    public void createWorldPanel() {
	        WorldPanel = new JPanel();
	        WorldPanel.setBounds(630,10,750,750);
	        WorldPanel.setLayout(new GridLayout(15,15));
	        WorldPanel.setBorder(BorderFactory.createEmptyBorder(15,15,8,8));
	        WorldPanel.setBackground(Color.DARK_GRAY);
	        for(int i=0;i<15;i++) {
	            for(int j=0;j<15;j++) {
	                JButton button= new JButton();
	                button.setPreferredSize(new Dimension(90,90));
	                World[i][j] = button;
	                WorldPanel.add(World[i][j]);
	                
	                
	                button.setOpaque(true);
	            }
	        }
	        mainPanel.add(WorldPanel);
	        this.add(mainPanel);
//	        try {
//				ImageIcon  image = new ImageIcon(getClass().getResource("grey.jpeg"));
//				  Image i = image.getImage();
//				  Image im = i.getScaledInstance(1500, 1200, java.awt.Image.SCALE_SMOOTH);
//				  image = new ImageIcon(im);
//			      JLabel l = new JLabel(image);
//			      Dimension size = l.getPreferredSize();
//			      l.setBounds(0, 0, size.width, size.height);
//			      mainPanel.add(l);
//				
//			} catch (Exception e) {
//				System.out.print("File Not Found");
//			}
	    }
	    public void createErrorInfo() {
			JPanel errorInfo = new JPanel();
	    	errorInfo.setPreferredSize(new Dimension(20,20));
	    	errorInfo.setLayout(new FlowLayout());
	    	errorInfo.setBackground(Color.white);
	    	errorInfo.setBounds(0, 200, 400, 50);
	    	errorMessage=new TextArea();
	    	errorMessage.setPreferredSize(new Dimension(400,50));
	    	errorMessage.setEditable(false);
	    	errorMessage.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
	    	errorMessage.setBackground(Color.WHITE);	    
	    	errorInfo.add(errorMessage);
	    	

			
			mainPanel.add(errorInfo);

	    	
	    }
	    
	    public void updateMap() {
	    	for(int i=0;i<World.length;i++) {
	    		for(int j=0;j<World[i].length;j++) {
	    			Cell currentCell=Game.map[i][j];
	    			if(currentCell.isVisible()) {
	    			if(currentCell instanceof CharacterCell) {
	    				CharacterCell currentCharacterCell=(CharacterCell) currentCell;
	    				Character currentCharacter=currentCharacterCell.getCharacter();
	    				if(currentCharacter!=null) {
	    					if(currentCharacter instanceof Zombie) {
	    						Zombie currentZombie=(Zombie) currentCharacter;
	    			    		int x=currentZombie.getLocation().x;
	    			    		int y=currentZombie.getLocation().y;
	    			    		 ImageIcon zombieIcon=new ImageIcon(getClass().getResource("zombie1.png"));
	    			    		Image img=zombieIcon.getImage();
	    						img = img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
	    			    		zombieIcon.setImage(img);
	    						World[14-i][j].setIcon(zombieIcon);
	    						World[14-i][j].addActionListener(new ActionListener() {
	    							@Override
	    							public void actionPerformed(ActionEvent e) {
	    									selectedCharacter=currentZombie;
	    									updateInfo();
	    									
	    									
	    								
	    						}
	    					});
	    					}else {
	    						Hero currentHero=(Hero) currentCharacter;
	    						String[] names=currentHero.getName().split(" ");
	    						String iconName=names[0].toLowerCase()+".png";
	    						int x=currentHero.getLocation().x;
	    			    		int y=currentHero.getLocation().y;
	    			    		ImageIcon heroIcon=new ImageIcon(getClass().getResource(iconName));
	    			    		Image img=heroIcon.getImage();
	    						img = img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
	    			    		heroIcon.setImage(img);
	    			    		System.out.println(i);
	    						World[14-i][j].setIcon(heroIcon);
	    						World[14-i][j].addActionListener(new ActionListener() {
	    							@Override
	    							public void actionPerformed(ActionEvent e) {
	    									selectedCharacter=currentHero;
	    									updateInfo();				
	    						}
	    					});
	    					}
	    				}else {
	    					World[14-i][j]=new JButton();
	    					World[14-i][j].setPreferredSize(new Dimension(90,90));

	    				}
	
	    			}else {
	    				if(currentCell instanceof CollectibleCell) {
	    					CollectibleCell collectibleCell=(CollectibleCell) currentCell;
	    					if(collectibleCell.getCollectible() instanceof Vaccine) {
	    						ImageIcon vaccineIcon=new ImageIcon(getClass().getResource("vaccine.png"));
	    			    		Image img=vaccineIcon.getImage();
	    						img = img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
	    						vaccineIcon.setImage(img);
	    						World[14-i][j].setIcon(vaccineIcon);
	    					}else {
	    						ImageIcon vaccineIcon=new ImageIcon(getClass().getResource("supply.png"));
	    			    		Image img=vaccineIcon.getImage();
	    						img = img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
	    						vaccineIcon.setImage(img);
	    						World[14-i][j].setIcon(vaccineIcon);

	    					}
	    				}
	    		
	    			}
	    			World[14-i][j].validate();
	    			World[14-i][j].repaint();
	    		}else {
	    			World[14-i][j]=new JButton();
					World[14-i][j].setPreferredSize(new Dimension(90,90));
	    		}
	    		}
	    	}
	    	
	    	
	    }
	    
	    
	    
		
		public void actionPerformed(ActionEvent e) {
			
			
		}
	//	public void paint(Graphics g) {
			
		//	super.paint(g);
		//	Graphics g2 = (Graphics2D)g;
		//	g2.setColor(Color.WHITE);
		//	g2.fillRect(0,0, 90 , 90);
			
	        
	//	}
		public static void main (String [] args) throws Exception {
	        new view();
	     }
	
}
