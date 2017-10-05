package platformer;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Platform extends JLabel
{
	public Platform(int width, int height, int theX, int theY)
	{
		setBounds(theX, theY, width, height);
		
		setBackground(Color.BLUE);
		setBorder( new LineBorder( Color.BLACK ) );
		setOpaque(true);
	}

}