import java.awt.*;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class RottoButton extends JButton {
	private Color txtColor = Color.WHITE;
	private Color bgColor =Color.white;;
//	private Color txtColor =Color.white;
	public RottoButton(String text) {//버튼안에 이름을 넣는다.(String text)는 JButton상속돼어있는것
		super(text);
		setBorderPainted(false);//버튼 테두리 설정
		setOpaque(false);//투명하게
	}
	public void setTxtColor(Color c) {
		txtColor = c;
	}
	public void setBgColor(Color c) {
		bgColor=c;
	}
	public void paint(Graphics g) {
		 int w=getWidth();//넓비 값
		 int h=getHeight();//길이 값 
		
//		g.setColor(Color.red);//색상
		g.setColor(bgColor);//버튼 색깔
		g.fillRoundRect(0,0, w, h, 100,100);//(x, y, width, height, arcWidth, arcHeight);

		g.setColor(txtColor);//글자 색깔 지정 지정 안하면 setColor랑똑같게돼서 안보임
		g.drawString(getText(), getWidth()/2-12, getHeight()/2+5);
	}

}
