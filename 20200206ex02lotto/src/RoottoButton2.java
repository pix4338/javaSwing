import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class RoottoButton2 extends JButton{
	private Color bgColor2 = Color.lightGray;
	
	public RoottoButton2(String text) {
		super(text);
		setBorderPainted(false);
		setOpaque(false);
	}
	
	public void BgColor(Color c) {
		bgColor2 = c;
	}

	public void paint(Graphics g) {
		int w=getWidth();
		int h=getHeight();
		g.setColor(bgColor2);
		g.fillRoundRect(0, 0, w, h, 100, 100);
	}

}
