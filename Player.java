package platformer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel
{
	int health = 100, x = 550, y = 260;

	public Player()
	{   //              20, 50
		setBounds(x, y, 36, 66);
		
		setIcon(new ImageIcon("src/pictures/Right/SMW0R.png"));
	}
	
	public void setY(int adj){this.setLocation(x, (y = adj));}
	public void setX(int adj){this.setLocation((x = adj), y);}	
	
}