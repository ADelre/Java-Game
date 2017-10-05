package platformer;

import java.awt.Color;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Token extends JLabel
{
	private int points;
	
	public Token(int p, int x, int y)
	{
		points = p;
		this.setBounds(x, y, 20, 20);
		
		setBackground(Color.YELLOW);
		setBorder( new LineBorder( Color.BLACK ) );
		setOpaque(true);
	}
	
	int getPoints()
	{
		return points;
	}
}