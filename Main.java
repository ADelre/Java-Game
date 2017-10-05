//NAME: Anthony Delre
//PROG: Main.java
//DATE: 10/22/14

package platformer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends JFrame implements KeyListener, ActionListener
{
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private boolean doubleJumped = false, gravOn = true, onPlat = false;
	private int width = 1200, height = 400, points = 0, timerCount = 0,
			oldX, oldY, activePlat, spriteCount = 1, spriteDirection = 0;
	private JPanel pan;
	private JLabel backGround, score;
	
	private String[][] sprites = new String [2][8];
	private Token[] coins = new Token[6];
	private Platform[] pads = new Platform[7];
	private Player toon = new Player();
	
	private int[][] quards = new int[6][2];
	
	private Timer timer = new Timer(100, this), gravity = new Timer (100, this);
	
	public Main()
	{
		sprites[0][0] = "src/pictures/Left/SMW0L.png";
		sprites[0][1] = "src/pictures/Left/SMW1L.png";
		sprites[0][2] = "src/pictures/Left/SMW2L.png";
		sprites[0][3] = "src/pictures/Left/SMW3L.png";
		sprites[0][4] = "src/pictures/Left/SMW4L.png";
		sprites[0][5] = "src/pictures/Left/SMJ5L.png";
		sprites[0][6] = "src/pictures/Left/SMF6L.png";
		sprites[0][7] = "src/pictures/Left/SMF7L.png";
		
		sprites[1][0] = "src/pictures/Right/SMW0R.png";
		sprites[1][1] = "src/pictures/Right/SMW1R.png";
		sprites[1][2] = "src/pictures/Right/SMW2R.png";
		sprites[1][3] = "src/pictures/Right/SMW3R.png";
		sprites[1][4] = "src/pictures/Right/SMW4R.png";
		sprites[1][5] = "src/pictures/Right/SMJ5R.png";
		sprites[1][6] = "src/pictures/Right/SMF6R.png";
		sprites[1][7] = "src/pictures/Right/SMF7R.png";
		
		createUserInterface();
		gameLoop();		
	}
	
	private void createUserInterface()
    {
		Container contentPane = getContentPane();
     	contentPane.setLayout( null );
     	
     	contentPane.addKeyListener(this);   
     	
     	quards[0][0] = 150;
     	quards[0][1] = 180;
     	quards[1][0] = 250;
     	quards[1][1] = 240;
     	quards[2][0] = 350;
     	quards[2][1] = 300;
     	
     	quards[3][0] = 700;
     	quards[3][1] = 300;
     	quards[4][0] = 800;
     	quards[4][1] = 240;
     	quards[5][0] = 900;
     	quards[5][1] = 180;
      
     	// set up numberJPanel
     	pan = new JPanel();
     	pan.setLayout( null );
     	pan.setBounds( 0, 0, width, height );
     	pan.setBackground(Color.WHITE);
     	//pan.addKeyListener(this);
        contentPane.add( pan );
        
        backGround = new JLabel();
        backGround.setLayout( null );
        backGround.setBounds( 0, 0, width-16, height-38);
        backGround.setHorizontalAlignment(backGround.CENTER);
        backGround.setVerticalAlignment(backGround.CENTER);
        pan.add(backGround);
        
        score = new JLabel();
        score.setText("  Score: " + points + "/" + coins.length);
        score.setBounds(width/2 - 50, 0, 100, 25);
        score.setBackground(Color.WHITE);
        score.setOpaque(true);
        pan.add(score);
        
        for(int x=0; x<6; x++)
        {        	
        	pads[x] = new Platform(60,10, quards[x][0], quards[x][1]);
        	pan.add(pads[x]);
        	
        	coins[x] = new Token(100, (pads[x].getX() + ((pads[x].getWidth() / 10) * 3)), (pads[x].getY() - 30));
        	pan.add(coins[x]);
        }
        
        pads[6] = new Platform(width-16, 23, 0, height - 60);
        pads[6].setBackground(Color.GRAY);
        pads[6].setOpaque(true);
        pan.add(pads[6]);
        pan.add(toon);
        
		//set properties of application's window
     	setTitle( "THE GAME!!" ); // set title bar text
     	setSize( width, height );      // set window size
     	setVisible( true );       // display window      
     	setLocationRelativeTo(null); //centers the program
     	this.addKeyListener(this);
    }//end create user interface
	
	private void gameLoop()
	{
		gravity.start();
	}//end game loop	
	
	private void checkCollision()
	{
		int pointX = toon.getX(), pointY = toon.getY(), w = toon.getWidth();
		
		//checks for collision with tokens
		for(int x = 0; x < coins.length; x++)
		{
			if(coins[x].isVisible() == true)
			{
				for(int z = 0; z < toon.getHeight(); z++)
				{
					if(pointX > coins[x].getX() && (pointY + z) > coins[x].getY())
					{
						if(pointX < (coins[x].getX() + coins[x].getWidth()) && (pointY + z) < (coins[x].getY() + coins[x].getHeight()))
						{
							//points += coins[x].getPoints();
							points++;
							score.setText("  Tokens: " + points + "/" + coins.length);
							coins[x].setVisible(false);
							
							if(points == 6)
							{
								victory_screen();
							}
							
							x = 6;
							break;
						}
					}
					else if((w + pointX) > coins[x].getX() && (pointY + z) > coins[x].getY())
					{
						if((w + pointX) < (coins[x].getX() + coins[x].getWidth()) && (pointY + z) < (coins[x].getY() + coins[x].getHeight()))
						{
							//points += coins[x].getPoints();
							points++;
							score.setText("  Tokens: " + points + "/" + coins.length);
							coins[x].setVisible(false);
							
							if(points == 6)
							{
								victory_screen();
							}
							
							x = 6;
							break;
						}
					}
				}//end height counter
			}//end if visible if
		}//end coins for counter
		
		
		//checks for collisions with platforms
		for(int x = 0; x < pads.length; x++)
		{
			if(pads[x].isVisible() == true)
			{
				for(int z = 0; z < toon.getHeight(); z++)
				{
					if(pointX > pads[x].getX() && (pointY + z) > pads[x].getY())
					{
						if(pointX < (pads[x].getX() + pads[x].getWidth()) && (pointY + z) < (pads[x].getY() + pads[x].getHeight()))
						{
							toon.setX(oldX);
							toon.setY(oldY);
							gravity.stop();
							doubleJumped = false;
							
							if(spriteCount < 5)
							{
								toon.setIcon(new ImageIcon(sprites[spriteDirection][spriteCount]));
								spriteCount++;
								
								if(spriteCount == 5)
								{
									spriteCount = 1;
								}				
							}
							
							if(x < coins.length)
							{
								onPlat = true;
								activePlat = x;
								//toon.setIcon(new ImageIcon(sprites[spriteDirection][0]));
								
								if((toon.getX() + toon.getWidth()) < pads[x].getX())
								{
									toon.setIcon(new ImageIcon(sprites[0][7]));
								}
								else if(toon.getX() >= (pads[x].getX() + pads[x].getWidth()))
								{
									toon.setIcon(new ImageIcon(sprites[1][7]));
								}
							}							
							
							x = 6;
							break;
						}
					}
					else if((w + pointX) > pads[x].getX() && (pointY + z) > pads[x].getY())
					{
						if((w + pointX) < (pads[x].getX() + pads[x].getWidth()) && (pointY + z) < (pads[x].getY() + pads[x].getHeight()))
						{
							toon.setX(oldX);
							toon.setY(oldY);
							gravity.stop();
							doubleJumped = false;
							
							if(spriteCount < 5)
							{
								toon.setIcon(new ImageIcon(sprites[spriteDirection][spriteCount]));
								spriteCount++;
								
								if(spriteCount == 5)
								{
									spriteCount = 1;
								}				
							}
							
							if(x < coins.length)
							{
								onPlat = true;
								activePlat = x;
								//toon.setIcon(new ImageIcon(sprites[spriteDirection][0]));
								
								if((toon.getX() + toon.getWidth()) < pads[x].getX())
								{
									toon.setIcon(new ImageIcon(sprites[0][7]));
								}
								else if(toon.getX() >= (pads[x].getX() + pads[x].getWidth()))
								{
									toon.setIcon(new ImageIcon(sprites[1][7]));
								}
							}							
						
							x = 6;
							break;
						}
					}
				}//end height counter
			}//end if visible if
		}//end pads for counter
		
		onPlat = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		oldX = toon.getX();
		oldY = toon.getY();
		gravity.start();
		
		switch(e.getKeyCode())
		{
		case 27://escape key
			gravity.stop();
			timer.stop();
			System.exit(0);
			break;
		case 32://spacebar
			break;
		case 38://up arrow
			if(doubleJumped == false)
			{
				toon.setIcon(new ImageIcon(sprites[spriteDirection][5]));
				gravity.stop();
				timer.start();
				doubleJumped = true;
			}
			break;
		case 37://left arrow
			spriteDirection = 0;
			
			toon.setX(toon.getX() - 10);
			
			
			//checkFloor();
			break;
		case 39://right arrow
			spriteDirection = 1;
			
			toon.setX(toon.getX() + 10);
			
			
			break;
		case 40://down arrow
			toon.setY(toon.getY() + 10);
			//;checkFloor();
			break;
		}
		
		checkCollision();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null,e.getKeyCode(), "Finished!!",  JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null,e.getKeyCode(), "Finished!!",  JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource() == timer)
		{
			if(timerCount < 5)
			{
				toon.setY(toon.getY() - 10);
				timerCount++;
			}
			else
			{
				timer.stop();
				timerCount = 0;
				gravity.start();
				toon.setY(toon.getY() + 5);
			}
		}
		else if(e.getSource() == gravity)
		{
			oldX = toon.getX();
			oldY = toon.getY();
			
			toon.setY(toon.getY() + 5);
			toon.setIcon(new ImageIcon(sprites[spriteDirection][6]));
			checkCollision();
		}		
	}

	private void victory_screen()
	{
		JOptionPane.showMessageDialog(null,"You've won!!", "Victory!!",  JOptionPane.PLAIN_MESSAGE);
		
		this.dispose();
		
		Start_Screen screen = new Start_Screen();
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
