package platformer;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Start_Screen extends JFrame implements ActionListener
{	
	private int dims;
	
	private JPanel pan;
	private JButton start, instructions,  exit;
	private JLabel welcome;
	
	public Start_Screen()
	{
		dims = 420;
		
		createUserInterface();
	}
	
	private void createUserInterface()
	{
	  //get content pane and set layout to null
      Container contentPane = getContentPane();
      contentPane.setLayout( null );
      
      pan = new JPanel();
      pan.setLayout( null );
      pan.setBounds( dims/2 - 175, 50, 350, 300 );
      pan.setBorder( new LineBorder( Color.BLACK ) );
      contentPane.add( pan );
      
      welcome = new JLabel();
      welcome.setBounds( pan.getWidth()/2 - 150, 10, 300, 80 );
      welcome.setHorizontalAlignment( JLabel.CENTER );
      welcome.setIcon(new ImageIcon("src/pictures/title.png"));
      pan.add( welcome );
      
      start = new JButton();
      start.setText( "Start" );
      start.setBounds( pan.getWidth()/2 - 75, 100, 150, 50 );
      start.setFont( 
         new Font( "SansSerif", Font.PLAIN, 18 ) );
      start.addActionListener(this);
      pan.add( start );
            
      instructions = new JButton();
      instructions.setText( "Instructions" );
      instructions.setBounds( pan.getWidth()/2 - 75, 160, 150, 50 );
      instructions.setFont( 
         new Font( "SansSerif", Font.PLAIN, 18 ) );
      instructions.addActionListener(this);
      pan.add( instructions );
      
      exit = new JButton();
      exit.setText( "Exit" );
      exit.setBounds( pan.getWidth()/2 - 75, 220, 150, 50 );
      exit.setFont( 
         new Font( "SansSerif", Font.PLAIN, 18 ) );
      exit.addActionListener(this);
      pan.add( exit );
      
      //set properties of application's window
      setTitle( "SpiderMan!!" ); // set title bar text
      setSize( dims, dims );      // set window size
      setVisible( true );       // display window      
      setLocationRelativeTo(null); //centers the screen
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Start_Screen screen = new Start_Screen();
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		switch(e.getActionCommand())
		{
		case "Start":
			Main application = new Main();
		    application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			this.dispose();
			break;
		case "Instructions":
			instructions_form();
			break;
		case "Exit":
			System.exit(0);
			break;
		}
	}
	
	private void instructions_form()
	{
		JFrame help = new JFrame();
		JLabel keys = new JLabel(), task = new JLabel();
		JPanel panel = new JPanel();
		
		Container contentPane = help.getContentPane();
	    contentPane.setLayout( null );
		
		panel = new JPanel();
		panel.setLayout( null );
		panel.setBounds( 0,0, 600, 400 );
		panel.setBorder( new LineBorder( Color.BLACK ) );
	    panel.setBackground(Color.WHITE);
	    contentPane.add( panel );
	    
	    task = new JLabel();
	    task.setBounds( 0,0, 600, 20 );
	    task.setHorizontalAlignment( JLabel.CENTER );
	    task.setText("Colect all of the tokens.");
	    panel.add( task );
	    
	    keys = new JLabel();
	    keys.setBounds( 0,20, 600, 400 );
	    keys.setHorizontalAlignment( JLabel.CENTER );
	    keys.setIcon(new ImageIcon("src/pictures/Instructions.png"));
	    panel.add( keys );
	    
	    help.setTitle( "Instructions" ); // set title bar text
		help.setSize( 600, 420 );      // set window size
		help.setVisible( true );       // display window      
		help.setLocationRelativeTo(null); //centers the screen
	}

}
