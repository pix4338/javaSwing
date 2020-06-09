import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class RdButton extends JButton {
	private Color TColor = Color.white;
	private Color BColor = Color.black;
	public RdButton(String s) {
		super(s);
		setBorderPainted(false);//버튼 테두리 설정
		setOpaque(false);//투명하게
	}
	public void setTColor(Color c) {
		TColor = c;
	}
	public void setBColor(Color c) {
		BColor = c;
	}
	public void paint(Graphics g) {
		 int w=getWidth();//넓비 값
		 int h=getHeight();//길이 값 
		
		g.setColor(BColor);
		g.fillRoundRect(0,0, w, h, 100,100);//(x, y, width, height, arcWidth, arcHeight);

		g.setColor(TColor);
		g.drawString(getText(), getWidth()/2-12, getHeight()/2+5);
	}
}
