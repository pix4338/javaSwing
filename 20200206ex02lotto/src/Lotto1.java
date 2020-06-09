import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Random;
import java.util.ResourceBundle;

import javax.management.StringValueExp;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class Lotto1 extends  JFrame implements MouseListener, KeyListener {
	//첫번쨰 화면에 보여주는 JPanel
	JPanel pan1 = new JPanel();
	
//	JPanel lotto log 사진
	JLabel lottoLb = new JLabel
	(new ImageIcon(new ImageIcon("img/France-Lotto2.png").getImage().getScaledInstance(90, 40, Image.SCALE_SMOOTH)));
	
//	다음 JPanel으로 넘어가는 버튼
	JButton startBt = new JButton("로또 번호 조회 밎 확인");
	JButton startBt2 = new JButton("로또 번호 랜덤 추첨");
//	----------------------------------------------------------------------------------------------
//	로또 번호 조회 밎 확인 을 눌렀을때 보여주는 JPanel
	JPanel pan2 = new JPanel();
	
//  로또 번호 회차 추출해서 보여주는 버튼들
	RottoButton[]  mbt = new RottoButton[7];
//	text입력시 조건에 맞는 라벨을 출력해주는 버튼
	JButton subMbt = new JButton("확인");
	JButton subMbt2 = new JButton("해당 회차로");
	
//	제목라벨
	JLabel titleL = new JLabel("로또 번호 조회");	
	JLabel subL = new JLabel("당첨확인");
	JLabel subL2 = new JLabel("번호 입력:");
//	경고 메세지 or 회차 번호를 알려주는 라벨
	JLabel subL3 = new JLabel();
//	로또 번호 일치 개수 출력 라벨
	JLabel subL4 = new JLabel("");
//	로또 번호 등수 출력 라벨
	JLabel subL5 = new JLabel("");//몇등입니다.
//	로또번호 일치 번호 출력 라벨
	JLabel[] subL7 = new JLabel[7];
	JLabel SubBackL = new JLabel("뒤로가기");
//	  로또 번호 회차 추출 번호 버튼 사이 + 로 꾸며주는 라벨
	JLabel ple1 = new JLabel("+");
//	로또 번호 일치 번호 출력 라벨 사이 + 로 꾸며주는 라벨
	JLabel ple3 = new JLabel("");//+
	
// 사용자가 로또 번호 입력하는 text
	JTextField tex1 = new JTextField();
	JTextField tex2 = new JTextField();
	JTextField tex3 = new JTextField();
	JTextField tex4 = new JTextField();
	JTextField tex5 = new JTextField();
	JTextField tex6 = new JTextField();
//	사용자가 해당회차로 입력하는 text
	JTextField mbt2Tex = new JTextField("");

//  로또 번호 랜덤 추첨눌렀을때 보여주는 JPanel
	JPanel pan3 = new JPanel();
	
//	난수를 이용해 랜덤 번호 추출
	Random rd = new Random();
	
//	랜덤 번호 중복 비교 하는 함수
	int[] num = new int[7];
	
//	 랜덤로또 번호 추출 번호 버튼 사이 + 로 꾸며주는 라벨
	JLabel ple2 = new JLabel("+");//+


//	랜덤 번호 추출해서 보여주는 버튼들
	RdButton[] rdBt2 = new RdButton[7];
//	evant 버튼
	JButton rdBt = new JButton("랜덤 추첨");
	JButton rdBackBt = new JButton("뒤로가기");
	
	Lotto1(){
		super("로또번호 조회");
		event();
		paninit();
		pan2init();
		pan3init();
		
		
		setSize(620, 500);
		setVisible(true);
		setLocationRelativeTo(null);//창을 가운데에서 뜨게함
		setResizable(false);//창 크기 조절 못하게함
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void  paninit(){
		pan1.setBounds(0,0,620, 500);
		pan1.setLayout(null);
		pan1.setVisible(true);
		add(pan1);
		
		lottoLb.setBounds(5,5,90, 40);
//		pan1.setLayout(null);
//		pan1.setVisible(true);
		pan1.add(lottoLb);
		
		int h=100;
		startBt.setBounds(50,50+h,200,100);
		startBt.setFont(startBt.getFont().deriveFont(15f));
		pan1.add(startBt);
		
		startBt2.setBounds(360,50+h,200,100);
		startBt2.setFont(startBt2.getFont().deriveFont(15f));
		pan1.add(startBt2);
	}
	
	public void  pan3init(){
		pan3.setBounds(0,0,620, 500);
		pan3.setLayout(null);
		pan3.setVisible(false);
		add(pan3);
		
		
		
		for(int i=0;i<rdBt2.length;i++) {
			rdBt2[i] = new RdButton("00");
			rdBt2[i].getBorder();
			rdBt2[i].setFont(rdBt2[i].getFont().deriveFont(14.5f));
			pan3.add(rdBt2[i]);
		}	
		int w=75; int wn=140;		
		rdBt2[0].setBounds(10, 63, 70, 70);//공 위치 설정
		rdBt2[1].setBounds(10+w, 63, 70, 70);
		rdBt2[2].setBounds(10+w*2, 63, 70, 70);
		rdBt2[3].setBounds(10+w*3, 63, 70, 70);
		rdBt2[4].setBounds(10+w*4, 63, 70, 70);
		rdBt2[5].setBounds(10+w*5, 63, 70, 70);
		ple2.setBounds(20+w*6, 63, 70, 70);//+기호 위치 설정
		ple2.setFont(ple2.getFont().deriveFont(30.30f));
//		getContentPane().add(ple1);
		pan3.add(ple2);
		rdBt2[6].setBounds(50+w*6, 63, 70, 70);
		rdBt2[6].setTColor(Color.red);
		
		for(int i=0;i<rdBt2.length;i++) {
			if(i>0&&i<4) {
				rdBt2[i].setBColor(Color.red);
			}
			else if(i==0) {
				rdBt2[i].setBColor(Color.blue);
			}
			else if(i==4) {
				rdBt2[i].setBColor(Color.lightGray);
			}
			else if(i==5) {
				rdBt2[i].setBColor(Color.green);
			}
			else {
				rdBt2[i].setBColor(Color.yellow);
			}
		}
		
		rdBt.setBounds(110+41*8, wn+50,100, 40);
		rdBt.setFont(rdBt.getFont().deriveFont(15.20f));
//		rdBt.setBounds(50,200,200,100);
		pan3.add(rdBt);
		
		rdBackBt.setBounds(110+41*5, wn+50,100, 40);
		rdBackBt.setFont(rdBackBt.getFont().deriveFont(15.20f));
		pan3.add(rdBackBt);
		
	}
	public void pan2init() {
//		getContentPane().setLayout(null);
		
		
		pan2.setBounds(0,0,620, 500);
		pan2.setLayout(null);
		pan2.setVisible(false);
		add(pan2);
		
		
		
		for(int i=0;i<mbt.length;i++) {
			mbt[i]=new RottoButton("00");
			mbt[i].setFont(mbt[i].getFont().deriveFont(14.5f));
			}
		int w=75; int wn=140;
		
		mbt[0].setBounds(10, 63, 70, 70);//공 위치 설정
		mbt[1].setBounds(10+w, 63, 70, 70);
		mbt[2].setBounds(10+w*2, 63, 70, 70);
		mbt[3].setBounds(10+w*3, 63, 70, 70);
		mbt[4].setBounds(10+w*4, 63, 70, 70);
		mbt[5].setBounds(10+w*5, 63, 70, 70);
		ple1.setBounds(20+w*6, 63, 70, 70);//+기호 위치 설정
		ple1.setFont(ple1.getFont().deriveFont(30.30f));
//		getContentPane().add(ple1);
		pan2.add(ple1);
		mbt[6].setBounds(50+w*6, 63, 70, 70);
		mbt[6].setTxtColor(Color.red);
		
		for(int i=0;i<mbt.length;i++) {
			if(i>0&&i<4) {
				mbt[i].setBgColor(Color.red);
			}
			else if(i==0) {
			mbt[i].setBgColor(Color.blue);
			}
			else if(i==4) {
				mbt[i].setBgColor(Color.lightGray);
			}
			else if(i==5) {
				mbt[i].setBgColor(Color.green);
			}
			else {
				mbt[i].setBgColor(Color.yellow);
			}
		}
	
		
		for(int i=0;i<mbt.length;i++) {
//		getContentPane().add(mbt[i]) ;
		pan2.add(mbt[i]);
		}
//	    mbt[0]=new RottoButton("252");
		titleL.setBounds(5, 5, 150, 60);
		titleL.setFont(titleL.getFont().deriveFont(15.5f));//폰트크기 설정
//		getContentPane().add(titleL);
		pan2.add(titleL);
		
		subL3.setBounds(130,5,300,60);
		subL3.setFont(subL3.getFont().deriveFont(14.5f));
//		getContentPane().add(subL3);
		pan2.add(subL3);
		
			subL.setBounds(10, wn, 150, 60);
			subL.setFont(subL.getFont().deriveFont(16.5f));
//			getContentPane().add(subL);
			pan2.add(subL);
			
			subL2.setBounds(10, wn+40, 150, 60);
			subL2.setFont(subL2.getFont().deriveFont(15.8f));
//			getContentPane().add(subL2);
			pan2.add(subL2);
			
			tex1.setBounds(95, wn+50,35, 40);
//			getContentPane().add(tex1);
			tex2.setBounds(95+42, wn+50,35, 40);
//			getContentPane().add(tex2);
			tex3.setBounds(95+42*2, wn+50,35, 40);
//			getContentPane().add(tex3);
			tex4.setBounds(95+42*3, wn+50,35, 40);
//			getContentPane().add(tex4);
			tex5.setBounds(95+42*4, wn+50,35, 40);
//			getContentPane().add(tex5);
			tex6.setBounds(95+42*5, wn+50,35, 40);
//			getContentPane().add(tex6);
			pan2.add(tex1);
			pan2.add(tex2);
			pan2.add(tex3);
			pan2.add(tex4);
			pan2.add(tex5);
			pan2.add(tex6);
			
			subL4.setBounds(93+50*3, wn+80+48, 200, 60);
			subL4.setFont(subL4.getFont().deriveFont(15.20f));
//			getContentPane().add(subL4);
			subL5.setBounds(100+50*4, wn+80+27, 250, 100);
			subL5.setFont(subL5.getFont().deriveFont(15.0f));
//			getContentPane().add(subL5);
//			pan2.add(subL4);
			pan2.add(subL4);
//			pan2.add(subL5);
			pan2.add(subL5);
			
			for(int i=0;i<7;i++) {
			subL7[i]= new JLabel("");
			subL7[i].setFont(subL7[i].getFont().deriveFont(15.20f));
//			getContentPane().add(subL7[i]);
//			pan2.add(subL7[i]);
			pan2.add(subL7[i]);
			}			
			subL7[0].setBounds(10+40*2-10, wn+80+48, 200, 60);
			subL7[1].setBounds(13+50*2-13, wn+80+48, 200, 60);
			subL7[2].setBounds(30+50*2-10, wn+80+48, 200, 60);
			subL7[3].setBounds(50+50*2-10, wn+80+48, 200, 60);
			subL7[4].setBounds(70+50*2-10, wn+80+48, 200, 60);
			subL7[5].setBounds(90+50*2-10, wn+80+48, 200, 60);
			ple3.setBounds(100+50*2, wn+80+48, 200, 60);
			ple3.setFont(ple3.getFont().deriveFont(15.20f));
//			getContentPane().add(ple2);
			subL7[6].setBounds(110+50*2, wn+80+48, 200, 60);
			pan2.add(ple3);
			
			
			
			subMbt.setBounds(110+41*8, wn+50,100, 40);
			subMbt.setFont(subMbt.getFont().deriveFont(15.20f));
//			getContentPane().add(subMbt);
			pan2.add(subMbt);
			
			mbt2Tex.setBounds(210+36*2,80+42*6,100,40);
//			getContentPane().add(mbt2Tex);
			subMbt2.setBounds(215+44*4,80+42*6,115,40);
			subMbt2.setFont(subMbt2.getFont().deriveFont(15.20f));
//			getContentPane().add(subMbt2);
			pan2.add(mbt2Tex);
			pan2.add(subMbt2);
			
//			SubBackBt.setOpaque(true);
			SubBackL.setBounds(215+44*7,80+42*8,100,40);
			SubBackL.setFont(SubBackL.getFont().deriveFont(18f));
			pan2.add(SubBackL);
			
			
			
		}
	
	public void event() {
		subMbt.addMouseListener(this);
		subMbt2.addMouseListener(this);
		mbt2Tex.addMouseListener(this);
		
		tex1.addMouseListener(this);
		tex2.addMouseListener(this);
		tex3.addMouseListener(this);
		tex4.addMouseListener(this);
		tex5.addMouseListener(this);
		tex6.addMouseListener(this);
		
		startBt2.addMouseListener(this);
		startBt.addMouseListener(this);
		rdBt.addMouseListener(this);
		rdBackBt.addMouseListener(this);
		SubBackL.addMouseListener(this);
		subMbt.addMouseListener(this);
		
		
//		mbt2Tex
	}
	
	void clearMbt2Tex() {//텍스트를 초기화 or 지워준다
		mbt2Tex.setText("");
	}
	void clearsubL7() {
		for(int i=0;i<subL7.length;i++) {
		subL7[i].setText("");
		}
		ple3.setText("");
//		subL7[0].setText("00");
	}
	void clearTex() {
		tex1.setText("");
		tex2.setText("");
		tex3.setText("");
		tex4.setText("");
		tex5.setText("");
		tex6.setText("");
		
	}
	void clearColorTex() {
		for(int i=0;i<subL7.length;i++) {
		subL7[i].setForeground(Color.black);
				}
		}
	
	//해당 회차로 or 확인 버튼 event 실행
	void showResult() {
		subL3.setForeground(Color.black);//오류 라벨 출력시 빨강색으로 되어 검정으로 원래 상태로 돌림
		String turnNum=mbt2Tex.getText(); //비교와 원하는 정보를 추출하기 위해 회차로 입력 정보를 turnNum에 할당받는다.
		try {
			int a = Integer.parseInt(turnNum); //회차로 입력 정보 할당 받은 turnNum은 비교를 하기 위해 int형으로 바꾼다.
			if(a<1 || a>1000) {
				subL3.setText("(1~1000)범위의 숫자를 입력해주세요.");
				subL3.setForeground(Color.red); //오류라벨은 눈에 띄게 빨강색으로 표시한다.
				clearMbt2Tex();//해당 회차로 text 초기화
				return;
			}
		}
		catch(Exception e) {
				subL3.setText("회차 번호다시 입력해 주세요.");
				subL3.setForeground(Color.red);
				clearMbt2Tex();//해당 회차로 text 초기화
				return;
			}
		
try {
	JsonReader jr = new JsonReader ();
	JSONObject jo = jr.connectionUrlToJSON(mbt2Tex.getText());
	//  회차 정보를 입력 안했을시 오류 라벨 띄움
	// mbt2Tex가 null값이면 jo도 null값이므로 접속 실패 오류 라벨 띄움
	if(jo==null) {
		subL3.setText("접속에 실패 하였습니다.");
		subL3.setForeground(Color.red);
		clearMbt2Tex();//해당 회차로 text 초기화
		return;
	}
//	회차정보가 없을때 오류 라벨 띄움
//	jo.get을해 "returnValue"넣어 리턴 값을 string으로 변환해 fail 나오면
//	회차 정보가 없으므로 오류를 띄우기 위해 if문 실행 
	if(String.valueOf(jo.get("returnValue")).equals("fail")) {
		JOptionPane.showMessageDialog(null, "회차 정보가 없습니다.");
		subL3.setText("회차 정보가 없습니다.");
		subL3.setForeground(Color.red);
		clearMbt2Tex();//해당 회차로 text 초기화
		return;
	}
	
//	jo.get을해서 일치하는 정보를 추출해와 mbt배열에 string형으로 넣어준다.
	mbt[0].setText(String.valueOf(jo.get("drwtNo1")));
	mbt[1].setText(String.valueOf(jo.get("drwtNo2")));
	mbt[2].setText(String.valueOf(jo.get("drwtNo3")));
	mbt[3].setText(String.valueOf(jo.get("drwtNo4")));
	mbt[4].setText(String.valueOf(jo.get("drwtNo5")));
	mbt[5].setText(String.valueOf(jo.get("drwtNo6")));
	mbt[6].setText(String.valueOf(jo.get("bnusNo")));
//	회차번호 입력한 값을 get해와서 라벨에 set해준다.
	subL3.setText(mbt2Tex.getText()+"회차");

}
	catch (Exception e) {
		
		e.printStackTrace();
		subL3.setText("예기치 못한 오류 발생 했습니다.다시 시도 해주세요");
		subL3.setForeground(Color.red);
		return;
	}
	}
	
	

	void Value() {
		subL3.setForeground(Color.black);
		try{
			//비교해서 등수 구하기
			clearColorTex(); //일치 번호 라벨을 검정색으로 초기화 시켜준다.
			clearsubL7(); // 일치 번호 라벨을 00으로 초기화 시켜준다.
 			ple3.setText("+"); // set을해 "+"가 화면에 나오게 해줌
			JsonReader jr = new JsonReader ();
			JSONObject jo = jr.connectionUrlToJSON(mbt2Tex.getText());
			int[] a = new int[7];
			//받아온 로또 번호를 int형으로  a배열에 저장
			a[0]=Integer.parseInt(String.valueOf(jo.get("drwtNo1")));
			a[1]=Integer.parseInt(String.valueOf(jo.get("drwtNo2")));
			a[2]=Integer.parseInt(String.valueOf(jo.get("drwtNo3")));
			a[3]=Integer.parseInt(String.valueOf(jo.get("drwtNo4")));
			a[4]=Integer.parseInt(String.valueOf(jo.get("drwtNo5")));
			a[5]=Integer.parseInt(String.valueOf(jo.get("drwtNo6")));
			a[6]=Integer.parseInt(String.valueOf(jo.get("bnusNo")));
			int[] tex = new int[6];
			
			//내가 입력한 로또번호를 숫자형태로 바꿔서 tex배열에 넣음 보너스 번호 제외
			tex[0]= Integer.parseInt(tex1.getText());
			tex[1]= Integer.parseInt(tex2.getText());
			tex[2]= Integer.parseInt(tex3.getText());
			tex[3]= Integer.parseInt(tex4.getText());
			tex[4]= Integer.parseInt(tex5.getText());
			tex[5]= Integer.parseInt(tex6.getText());
			
			int cut=0; //로또 번호 등수 체크 
			int bnusNo=0; //로또 번호 보너스 번호 체크

//			추출해온 로또 번호와 사용자가 입력한 로또 번호와 일치하는지 체크해서 
//			일치하면 일치한번호를출력한다.
			for(int i=0;i<6;i++) { 
			for(int j=0;j<6;j++){
				if(a[i]==tex[j]) {
					cut+=1; //로또 번호 일치 개수를 체크
//					일치한 로또번호는 라벨에 출력하여 사용자에게 보여줌
					subL7[i].setText(String.valueOf(tex[j]));
					subL7[i].setForeground(Color.red);	
				}
				}
			}
			//추출해온 보너스 번호와 사용자가 입력한 로또 번호와 일치하는지 체크해서 일치하면 
			//보너스 번호를 체크해 준다.
			for(int j=0;j<6;j++) {
				if(a[6]==tex[j]) {
//					일치한 보너스번호는 라벨에 출력하여 사용자에게 보여줌
					subL7[6].setText(String.valueOf(tex[j]));
					subL7[6].setForeground(Color.orange);
					bnusNo +=1; //보너스 번호 일치 개수를 체크
				}
			}
//			일치한 개수를 문자열과 더해 라벨에 출력
			subL4.setText(bnusNo +cut+"개일치");
			
//			앞에서 체크한 값들을 비교해서 등수 체크
			if(cut==6) {
				subL5.setText(" ->  1등입니다!");
			}
			else if(cut==5&&bnusNo==1) {
				subL5.setText(" ->  2등 입니다");
			}
			else if(cut==5) {
				subL5.setText(" ->  3등 입니다");
			}
			else if(cut==4) {
				subL5.setText(" ->  4등 입니다");
			}
			else if(cut==3) {
				subL5.setText(" ->  5등 입니다");
			}
			else {
				subL5.setText(" ->  0등 입니다");
			}
		
		}
		catch(Exception e){
			e.printStackTrace();
			clearTex();
			clearsubL7();
			subL3.setText("입력란에 번호를 다 입력해주세요");
			subL3.setForeground(Color.red);
			return;
		}
	}
	
	
	void Rdsuget(){
		subL3.setForeground(Color.black);
		try {
			for(int i=0; i<num.length; i++) {
				num[i] = rd.nextInt(45)+1; //난수를 이용해 랜덤으로 7개 숫자를 받는다.
				for(int j=0; j<i; j++) { //비교를해 중복된 숫자는 제외
					if(num[i] == num[j]) {
						i--;
						break;
					}
				}
			}
			
			//string 으로 변환해 라벨에 넣어준다.
			for(int i=0;i<7;i++) {
				rdBt2[i].setText((String.valueOf(num[i])));
					
			}
			
		} catch (Exception e) {
			e.printStackTrace();

			subL3.setText("예기치 못한 오류 발생 했습니다.다시 시도 해주세요");
			subL3.setForeground(Color.red);
			
			return;
		}
	}

public static void main(String[] args) throws Exception {
		
		new Lotto1();
	}

@Override
public void keyTyped(KeyEvent e) {
	
	
	
}

@Override
public void keyPressed(KeyEvent e) {
//	if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
//		clearsubL7();
//		clearMbt2Tex();
//	}
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO 자동 생성된 메소드 스텁
	
}

@Override
public void mouseClicked(MouseEvent e) {
	//클릭했을시 Console창에 Clicked 출력
	System.out.println("Clicked");
	
	//판이동
	if(e.getSource()==startBt) {
		pan2.setVisible(true); //판 보이게함
		pan1.setVisible(false);//판 안보이게함
	}
	if(e.getSource()==startBt2) {
	pan3.setVisible(true);
	pan1.setVisible(false);
	}
	//뒤로가기
	if(e.getSource()==rdBackBt) {
		pan3.setVisible(false);
		pan1.setVisible(true);
	}
	//뒤로가기
	if(e.getSource()==SubBackL) {
		pan2.setVisible(false);
		pan1.setVisible(true);
	}
	// 버튼 눌렀을시 Rdsuget메소드 실행
	//(Rdsuget메소드 :랜덤번호 추출하는 메소드이다.)
	if(e.getSource()==rdBt) {
		Rdsuget();
	}
	// 버튼 눌렀을시 Value,showResult메소드 실행
	//(Value :비교해서 등수 구하기, 로또번호 일치시 라벨 출력하는 메소드 이다.)
	//(showResult: 회차 오류 입력시 예외처리,
	// 로또 번호 추출하여 버튼에 출력하는 메소드 이다.)
	if(e.getSource()==subMbt) {
		Value();
		showResult();
	}
	if(e.getSource()==subMbt2) {
		showResult();
	}
	if(e.getSource()==mbt2Tex) {//text클릭시 text초기화됌
		clearMbt2Tex();
	}
// 로또번호입력 text클릭시 초기화 (공백)
	if(e.getSource()==tex1) {
		tex1.setText("");
	}
	if(e.getSource()==tex2) {
		tex2.setText("");
	}
	if(e.getSource()==tex3) {
		tex3.setText("");
	}
	if(e.getSource()==tex4) {
		tex4.setText("");
	}
	if(e.getSource()==tex5) {
		tex5.setText("");
	}
	if(e.getSource()==tex6) {
		tex6.setText("");
	}
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO 자동 생성된 메소드 스텁
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO 자동 생성된 메소드 스텁
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO 자동 생성된 메소드 스텁
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO 자동 생성된 메소드 스텁
	
}
	
}
