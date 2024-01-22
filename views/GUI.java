package views;
import java.awt.Color;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.awt.Graphics;


import javax.swing.*;

import engine.Game;

public class GUI extends JFrame{
	
	Graphics g2;
	JPanel mainPanel,sub1,sub2,sub3;
	JLabel l;
	ImageIcon image;
	JButton b1;
	ImageIcon background;
	JCheckBox h1,h2,h3,h4,h5,h6,h7,h8; 
	ButtonGroup checked;

	public GUI() {
		 
		FirstScene();
		
	}
		
	
	public static void main(String [] args ) {
		GUI gui = new GUI();

} 
	public void FirstScene() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,700);
		this.setTitle("The Last Of Us");
		this.setLocationRelativeTo(null);
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);

		try {
			  image = new ImageIcon(getClass().getResource("bye.jpeg"));
		      this.getContentPane();
		      l = new JLabel(image);
		      Dimension size = l.getPreferredSize();
		      l.setBounds(-25, -250, size.width, size.height);
		      this.add(l);
		      this.add(mainPanel);
			
		} catch (Exception e) {
			System.out.print("File Not Found");
		}
		
		this.setVisible(true);
		b1 = new JButton("START GAME");
		b1.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC , 30));
	    b1.revalidate();
		b1.setBounds(200, 450, 300, 70);
		mainPanel.add(b1);	
		this.add(mainPanel);
		
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SecondScene();
				} catch (IOException e1) {
				
					e1.printStackTrace();
				}
		}
	});
		}
	
	public void SecondScene() throws IOException {
		 this.getContentPane().removeAll();
		 this.repaint();
		 mainPanel = new JPanel();
		 mainPanel.setLayout(new GridLayout(3, 1));
		 mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	    // mainPanel.setBackground(Color.GRAY);
	     sub1 = new JPanel();
	     sub1.setBounds(0, 0, 700,250 );
	    // sub1.setBackground(Color.GRAY);
	     sub2 = new JPanel();
	    // sub2.setBackground(Color.GRAY);
	     sub2.setBounds(0, 0, 350, 700);
	     sub3 = new JPanel();
	   //  sub3.setBackground(Color.GRAY);
	     sub3.setBounds(350, 0, 350, 700);
	     add(mainPanel);
		 this.setVisible(true);
		 try {
			  image = new ImageIcon(getClass().getResource("hello.png"));
			  Image i = image.getImage();
			  Image im = i.getScaledInstance(600, 90, java.awt.Image.SCALE_SMOOTH);
			  image = new ImageIcon(im);
		      l = new JLabel(image);
		      Dimension size = l.getPreferredSize();
		      l.setBounds(50, 40, size.width, size.height);
		      sub1.add(l);
		      mainPanel.add(sub1);
			
		} catch (Exception e) {
			System.out.print("File Not Found");
		}
		 
		 
		 l = new JLabel("Choose Your Hero");
		 l.setFont(new Font("Monospaced", Font.BOLD, 30));
		 l.setBounds(200, 150, 500, 100);
		 sub1.add(l);
		 
		 Game.loadHeroes("heroes.csv");
		 
		 h1 = new JCheckBox(Game.availableHeroes.get(0).getName() + "");
		 h1.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
						JLabel name = new JLabel("Name : " +Game.availableHeroes.get(0).getName() + "");
						JLabel maxhp = new JLabel("Max HP : " + Game.availableHeroes.get(0).getMaxHp() + "");
						JLabel attackDmg = new JLabel("Attack Damage : " + Game.availableHeroes.get(0).getAttackDmg() + "");
						JLabel maxActions = new JLabel("Max Actions : " + Game.availableHeroes.get(0).getMaxActions() + "");
						sub3.setBounds(0, 0,700, 700);
						image = new ImageIcon(getClass().getResource("joel.png"));
						Image i = image.getImage();
					    Image im = i.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
					    image = new ImageIcon(im);
					    l = new JLabel(image);
					    Dimension size = l.getPreferredSize();
					    l.setBounds(400, 290, size.width, size.height);
					    sub3.add(l);
					    
					    name.setBounds(400, 440, 200, 50);
					    name.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(name);
					    
					    maxhp.setBounds(400,460,200,50);
					    maxhp.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxhp);
					    
					    attackDmg.setBounds(400, 480, 200, 50);
					    attackDmg.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(attackDmg);
					    
					    maxActions.setBounds(400,500,200,50);
					    maxActions.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxActions);
					    
					    JButton cont = new JButton("Continue ->");
					    cont.setBounds(520, 570, 100, 20);
					    sub3.add(cont);
					    
					    JButton back = new JButton("Return <-");
					    back.setBounds(350, 570, 100, 20);
					    sub3.add(back);
					    
					    back.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									SecondScene();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    cont.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									Game.startGame(Game.availableHeroes.get(0));
									ThirdScene();
									
								} catch (Exception e1) {
									
									e1.printStackTrace();
								}
						}
					});
					    
					    mainPanel.add(sub3);
					    
					}
					
				
		});
		 
		 
		 h2 = new JCheckBox(Game.availableHeroes.get(1).getName() + "");
		 h2.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
						JLabel name = new JLabel("Name : " +Game.availableHeroes.get(1).getName() + "");
						JLabel maxhp = new JLabel("Max HP : " + Game.availableHeroes.get(1).getMaxHp() + "");
						JLabel attackDmg = new JLabel("Attack Damage : " + Game.availableHeroes.get(1).getAttackDmg() + "");
						JLabel maxActions = new JLabel("Max Actions : " + Game.availableHeroes.get(1).getMaxActions() + "");
						sub3.setBounds(0, 0,700, 700);
						image = new ImageIcon(getClass().getResource("ellie.png"));
						Image i = image.getImage();
					    Image im = i.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
					    image = new ImageIcon(im);
					    l = new JLabel(image);
					    Dimension size = l.getPreferredSize();
					    l.setBounds(400, 290, size.width, size.height);
					    sub3.add(l);
					    
					    name.setBounds(400, 440, 200, 50);
					    name.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(name);
					    
					    maxhp.setBounds(400,460,200,50);
					    maxhp.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxhp);
					    
					    attackDmg.setBounds(400, 480, 200, 50);
					    attackDmg.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(attackDmg);
					    
					    maxActions.setBounds(400,500,200,50);
					    maxActions.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    
					    JButton cont = new JButton("Continue ->");
					    cont.setBounds(520, 570, 100, 20);
					    sub3.add(cont);
					    
					    JButton back = new JButton("Return <-");
					    back.setBounds(350, 570, 100, 20);
					    sub3.add(back);
					    
					    back.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									SecondScene();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    cont.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									Game.startGame(Game.availableHeroes.get(1));

									ThirdScene();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    
					    mainPanel.add(sub3);
					    
					}
					    
					
					
				
		});
		  

		 h3 = new JCheckBox(Game.availableHeroes.get(2).getName() + "");
		 h3.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
						JLabel name = new JLabel("Name : " +Game.availableHeroes.get(2).getName() + "");
						JLabel maxhp = new JLabel("Max HP : " + Game.availableHeroes.get(2).getMaxHp() + "");
						JLabel attackDmg = new JLabel("Attack Damage : " + Game.availableHeroes.get(2).getAttackDmg() + "");
						JLabel maxActions = new JLabel("Max Actions : " + Game.availableHeroes.get(2).getMaxActions() + "");
						sub3.setBounds(0, 0,700, 700);
						image = new ImageIcon(getClass().getResource("tess.png"));
						Image i = image.getImage();
					    Image im = i.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
					    image = new ImageIcon(im);
					    l = new JLabel(image);
					    Dimension size = l.getPreferredSize();
					    l.setBounds(400, 290, size.width, size.height);
					    sub3.add(l);
					    
					    name.setBounds(400, 440, 200, 50);
					    name.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(name);
					    
					    maxhp.setBounds(400,460,200,50);
					    maxhp.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxhp);
					    
					    attackDmg.setBounds(400, 480, 200, 50);
					    attackDmg.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(attackDmg);
					    
					    maxActions.setBounds(400,500,200,50);
					    maxActions.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxActions);
					    
					    JButton cont = new JButton("Continue ->");
					    cont.setBounds(520, 570, 100, 20);
					    sub3.add(cont);
					    
					    JButton back = new JButton("Return <-");
					    back.setBounds(350, 570, 100, 20);
					    sub3.add(back);
					    
					    back.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									SecondScene();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    cont.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									Game.startGame(Game.availableHeroes.get(2));

									ThirdScene();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    
					    mainPanel.add(sub3);
					    
					}
					    
					
					
				
		});
			
		 h4 = new JCheckBox(Game.availableHeroes.get(3).getName() + "");
		 h4.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
						JLabel name = new JLabel("Name : " +Game.availableHeroes.get(3).getName() + "");
						JLabel maxhp = new JLabel("Max HP : " + Game.availableHeroes.get(3).getMaxHp() + "");
						JLabel attackDmg = new JLabel("Attack Damage : " + Game.availableHeroes.get(3).getAttackDmg() + "");
						JLabel maxActions = new JLabel("Max Actions : " + Game.availableHeroes.get(3).getMaxActions() + "");
						sub3.setBounds(0, 0,700, 700);
						image = new ImageIcon(getClass().getResource("riley.png"));
						Image i = image.getImage();
					    Image im = i.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
					    image = new ImageIcon(im);
					    l = new JLabel(image);
					    Dimension size = l.getPreferredSize();
					    l.setBounds(400, 290, size.width, size.height);
					    sub3.add(l);
					    
					    name.setBounds(400, 440, 200, 50);
					    name.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(name);
					    
					    maxhp.setBounds(400,460,200,50);
					    maxhp.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxhp);
					    
					    attackDmg.setBounds(400, 480, 200, 50);
					    attackDmg.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(attackDmg);
					    
					    maxActions.setBounds(400,500,200,50);
					    maxActions.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxActions);
					    
					    JButton cont = new JButton("Continue ->");
					    cont.setBounds(520, 570, 100, 20);
					    sub3.add(cont);
					    
					    JButton back = new JButton("Return <-");
					    back.setBounds(350, 570, 100, 20);
					    sub3.add(back);
					    
					    back.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									SecondScene();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    cont.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									Game.startGame(Game.availableHeroes.get(3));

									ThirdScene();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    
					    mainPanel.add(sub3);
					    
					}
					
				
		});

		 h5 = new JCheckBox(Game.availableHeroes.get(4).getName() + "");
		 h5.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
						JLabel name = new JLabel("Name : " +Game.availableHeroes.get(4).getName() + "");
						JLabel maxhp = new JLabel("Max HP : " + Game.availableHeroes.get(4).getMaxHp() + "");
						JLabel attackDmg = new JLabel("Attack Damage : " + Game.availableHeroes.get(4).getAttackDmg() + "");
						JLabel maxActions = new JLabel("Max Actions : " + Game.availableHeroes.get(4).getMaxActions() + "");
						sub3.setBounds(0, 0,700, 700);
						image = new ImageIcon(getClass().getResource("tommy.png"));
						Image i = image.getImage();
					    Image im = i.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
					    image = new ImageIcon(im);
					    l = new JLabel(image);
					    Dimension size = l.getPreferredSize();
					    l.setBounds(400, 290, size.width, size.height);
					    sub3.add(l);
					    
					    name.setBounds(400, 440, 200, 50);
					    name.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(name);
					    
					    maxhp.setBounds(400,460,200,50);
					    maxhp.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxhp);
					    
					    attackDmg.setBounds(400, 480, 200, 50);
					    attackDmg.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(attackDmg);
					    
					    maxActions.setBounds(400,500,200,50);
					    maxActions.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxActions);
					    
					    JButton cont = new JButton("Continue ->");
					    cont.setBounds(520, 570, 100, 20);
					    sub3.add(cont);
					    
					    JButton back = new JButton("Return <-");
					    back.setBounds(350, 570, 100, 20);
					    sub3.add(back);
					    
					    back.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									SecondScene();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    cont.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									Game.startGame(Game.availableHeroes.get(4));

									ThirdScene();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    
					    mainPanel.add(sub3);
					    
					}
					  
					
				
		});

		 h6 = new JCheckBox(Game.availableHeroes.get(5).getName() + "");
		 h6.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
						JLabel name = new JLabel("Name : " +Game.availableHeroes.get(5).getName() + "");
						JLabel maxhp = new JLabel("Max HP : " + Game.availableHeroes.get(5).getMaxHp() + "");
						JLabel attackDmg = new JLabel("Attack Damage : " + Game.availableHeroes.get(5).getAttackDmg() + "");
						JLabel maxActions = new JLabel("Max Actions : " + Game.availableHeroes.get(5).getMaxActions() + "");
						sub3.setBounds(0, 0,700, 700);
						image = new ImageIcon(getClass().getResource("bill.png"));
						Image i = image.getImage();
					    Image im = i.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
					    image = new ImageIcon(im);
					    l = new JLabel(image);
					    Dimension size = l.getPreferredSize();
					    l.setBounds(400, 290, size.width, size.height);
					    sub3.add(l);
					    
					    name.setBounds(400, 440, 200, 50);
					    name.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(name);
					    
					    maxhp.setBounds(400,460,200,50);
					    maxhp.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxhp);
					    
					    attackDmg.setBounds(400, 480, 200, 50);
					    attackDmg.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(attackDmg);
					    
					    maxActions.setBounds(400,500,200,50);
					    maxActions.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxActions);
					    
					    JButton cont = new JButton("Continue ->");
					    cont.setBounds(520, 570, 100, 20);
					    sub3.add(cont);
					    
					    JButton back = new JButton("Return <-");
					    back.setBounds(350, 570, 100, 20);
					    sub3.add(back);
					    
					    back.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									SecondScene();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    cont.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									Game.startGame(Game.availableHeroes.get(5));

									ThirdScene();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    
					    mainPanel.add(sub3);
					    
					}
					
				
		});

		 h7 = new JCheckBox(Game.availableHeroes.get(6).getName() + "");
		 h7.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
						JLabel name = new JLabel("Name : " +Game.availableHeroes.get(6).getName() + "");
						JLabel maxhp = new JLabel("Max HP : " + Game.availableHeroes.get(6).getMaxHp() + "");
						JLabel attackDmg = new JLabel("Attack Damage : " + Game.availableHeroes.get(6).getAttackDmg() + "");
						JLabel maxActions = new JLabel("Max Actions : " + Game.availableHeroes.get(6).getMaxActions() + "");
						sub3.setBounds(0, 0,700, 700);
						image = new ImageIcon(getClass().getResource("david.png"));
						Image i = image.getImage();
					    Image im = i.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
					    image = new ImageIcon(im);
					    l = new JLabel(image);
					    Dimension size = l.getPreferredSize();
					    l.setBounds(400, 290, size.width, size.height);
					    sub3.add(l);
					    
					    name.setBounds(400, 440, 200, 50);
					    name.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(name);
					    
					    maxhp.setBounds(400,460,200,50);
					    maxhp.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxhp);
					    
					    attackDmg.setBounds(400, 480, 200, 50);
					    attackDmg.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(attackDmg);
					    
					    maxActions.setBounds(400,500,200,50);
					    maxActions.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxActions);
					    JButton cont = new JButton("Continue ->");
					    cont.setBounds(520, 570, 100, 20);
					    sub3.add(cont);
					    
					    JButton back = new JButton("Return <-");
					    back.setBounds(350, 570, 100, 20);
					    sub3.add(back);
					    
					    back.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									SecondScene();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    cont.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									Game.startGame(Game.availableHeroes.get(6));
									ThirdScene();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    
					    mainPanel.add(sub3);
					    
					}
					
				
		});

		 h8 = new JCheckBox(Game.availableHeroes.get(7).getName() + "");
		 h8.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
						JLabel name = new JLabel("Name : " +Game.availableHeroes.get(7).getName() + "");
						JLabel maxhp = new JLabel("Max HP : " + Game.availableHeroes.get(7).getMaxHp() + "");
						JLabel attackDmg = new JLabel("Attack Damage : " + Game.availableHeroes.get(7).getAttackDmg() + "");
						JLabel maxActions = new JLabel("Max Actions : " + Game.availableHeroes.get(7).getMaxActions() + "");
						sub3.setBounds(0, 0,700, 700);
						image = new ImageIcon(getClass().getResource("henry.png"));
						Image i = image.getImage();
					    Image im = i.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
					    image = new ImageIcon(im);
					    l = new JLabel(image);
					    Dimension size = l.getPreferredSize();
					    l.setBounds(400, 290, size.width, size.height);
					    sub3.add(l);
					    
					    name.setBounds(400, 440, 200, 50);
					    name.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(name);
					    
					    maxhp.setBounds(400,460,200,50);
					    maxhp.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxhp);
					    
					    attackDmg.setBounds(400, 480, 200, 50);
					    attackDmg.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(attackDmg);
					    
					    maxActions.setBounds(400,500,200,50);
					    maxActions.setFont(new Font("Monospaced", Font.ITALIC  , 15));
					    sub3.add(maxActions);
					   
					    JButton cont = new JButton("Continue ->");
					    cont.setBounds(520, 570, 100, 20);
					    sub3.add(cont);
					    
					    JButton back = new JButton("Return <-");
					    back.setBounds(350, 570, 100, 20);
					    sub3.add(back);
					    
					    back.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									SecondScene();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    cont.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									Game.startGame(Game.availableHeroes.get(7));
									ThirdScene();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
					    
					    mainPanel.add(sub3);
					    
					}
					
				
		});

		 
		 h1.setBounds(70,290,150,20);
		 h1.setFont(new Font("Monospaced", Font.BOLD  , 15));
		 h2.setBounds(70,330,170,20);
		 h2.setFont(new Font("Monospaced", Font.BOLD  , 15));
		 h3.setBounds(70,370,150,20);
		 h3.setFont(new Font("Monospaced", Font.BOLD  , 15));
		 h4.setBounds(70,410,150,20);
		 h4.setFont(new Font("Monospaced", Font.BOLD  , 15));
		 h5.setBounds(70,450,150,20);
		 h5.setFont(new Font("Monospaced", Font.BOLD  , 15));
		 h6.setBounds(70,490,150,20);
		 h6.setFont(new Font("Monospaced", Font.BOLD  , 15));
		 h7.setBounds(70,530,150,20);
		 h7.setFont(new Font("Monospaced", Font.BOLD  , 15));
		 h8.setBounds(70,570,150,20);
		 h8.setFont(new Font("Monospaced", Font.BOLD  , 15));
		 
		 sub2.add(h1);
		 sub2.add(h2);
		 sub2.add(h3);
		 sub2.add(h4);
		 sub2.add(h5);
		 sub2.add(h6);
		 sub2.add(h7);
		 sub2.add(h8);
		 mainPanel.add(sub2);
		 mainPanel.add(sub3);
		 
		 checked = new ButtonGroup();
		 
		 checked.add(h1);
		 checked.add(h2);
		 checked.add(h3);
		 checked.add(h4);
		 checked.add(h5);
		 checked.add(h6);
		 checked.add(h7);
		 checked.add(h8);
		 
		 
		 this.add(mainPanel);
		 	
	}
	
	public void ThirdScene() throws Exception {
		
		this.dispose();
		Main.main(null);
	}	
	
	
	}	
		

	
