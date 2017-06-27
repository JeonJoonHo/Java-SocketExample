
import java.awt.BorderLayout;import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerPo implements ActionListener {
	static JFrame frame = new JFrame("Server");
	
	JPanel lefP = new JPanel();
	JPanel leP = new JPanel();
	JPanel rigP = new JPanel();
	JPanel riP = new JPanel();
	JPanel cenP = new JPanel();
	static JPanel ceP =new JPanel();
	
	static JButton imgB = new JButton();
	static JButton imgB1 = new JButton();
	JButton bBt = new JButton("배팅");
	JButton gBt = new JButton("포기");
	JButton oBt = new JButton("오픈");
	JButton aBt = new JButton("올인"); 
	JButton eBt = new JButton("턴 끝내기");
	static JButton sBt = new JButton("시작하기");
	JButton img1 = new JButton(new ImageIcon("D:\1.jpg"));

	static int totalBat = 0;	//총 배팅 칩 수
	static int bNum = 0;		//배팅 수
	static int bat = 0;			//배팅 칩 수
	static int seC = 30;		//서버 칩 수
	static int clC = 30;		//클라이언트 칩 수
	static JLabel lab1 = new JLabel("Server님의 칩 수 : 30",JLabel.CENTER);
	static JLabel lab2 = new JLabel("Client님의 칩 수 : 30",JLabel.CENTER);
	static JLabel lab3 = new JLabel("현재 배팅 된 칩 수 : 0",JLabel.CENTER);
	
	static JTextArea text = new JTextArea();
	JScrollPane scr = new JScrollPane(text);
	
	ImageIcon icon;
	ImageIcon icon1;
	JScrollPane scrollPane = new JScrollPane();

	static JLabel[] imgL = new JLabel[60];

	static Random random = new Random();
	static int[] ranN = new int[9];		//카드 번호 난수 생성
	static int[] ranN1 = new int[9];	//카드 번호 난수 생성
	static int ServerCard = 0;			//서버 카드 번호
	static int ClientCard = 0;			//클라이언트 카드 번호
	static int serO = 0;				//순서
	static int clO = 1;					//순서
	
	String ba = "";
	
	static JOptionPane pane;
	public ServerPo(){
		icon = new ImageIcon("D:/10.png");
		icon1 = new ImageIcon("D:/chip.jpg");
		// 이미지를 불러옴
		JPanel pan = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		lefP.setBackground(Color.white);
		rigP.setBackground(Color.white);
		cenP.setBackground(Color.white);;
		leP.setBackground(Color.white);
		riP.setBackground(Color.white);
		ceP.setBackground(Color.white);
		
		scrollPane = new JScrollPane(pan);
		frame.setContentPane(scrollPane);
		
		pan.setLayout(new BorderLayout());
		lefP.setLayout(new BorderLayout());
		rigP.setLayout(new BorderLayout());
		cenP.setLayout(new BorderLayout());
		leP.setLayout(null);
		riP.setLayout(null);
		ceP.setLayout(null);
		
		lefP.setPreferredSize(new Dimension(210,0));
		rigP.setPreferredSize(new Dimension(210,0));
		pan.add(cenP, "Center");
		pan.add(lefP,"West");
		pan.add(rigP,"East");
		
		//중앙 패널 설정
		lab3.setPreferredSize(new Dimension(0,30));
		scr.setPreferredSize(new Dimension(0,170));
		sBt.setMargin(new Insets(0,0,0,0));
		sBt.setBorderPainted(false);
		sBt.setBackground(Color.white);
		sBt.addActionListener(this);
		sBt.setSize(100,40);
		sBt.setLocation(105, 200);
		ceP.add(sBt);
		cenP.add(scr,"South");
		cenP.add(lab3, "North");
		cenP.add(ceP, "Center");
		
		//왼쪽 패널 설정
		leP.setPreferredSize(new Dimension(0,150));
		imgB.setPreferredSize(new Dimension(0,250));
		imgB.setBackground(Color.white);
		imgB.setMargin(new Insets(0,0,0,0));
		imgB.setBorderPainted(false);
		bBt.setSize(60, 40);
		oBt.setSize(60, 40);
		gBt.setSize(60, 40);
		aBt.setSize(60, 40);
		bBt.setLocation(30, 30);
		aBt.setLocation(125, 30);
		oBt.setLocation(30, 80);
		gBt.setLocation(125, 80);
		bBt.setBackground(Color.white);
		aBt.setBackground(Color.white);
		oBt.setBackground(Color.white);
		gBt.setBackground(Color.white);
		bBt.addActionListener(this);
		aBt.addActionListener(this);
		oBt.addActionListener(this);
		gBt.addActionListener(this);
		bBt.setMargin(new Insets(0,0,0,0));
		aBt.setMargin(new Insets(0,0,0,0));
		oBt.setMargin(new Insets(0,0,0,0));
		gBt.setMargin(new Insets(0,0,0,0));
		bBt.setBorderPainted(false);
		aBt.setBorderPainted(false);
		oBt.setBorderPainted(false);
		gBt.setBorderPainted(false);
		leP.add(bBt);
		leP.add(aBt);
		leP.add(oBt);
		leP.add(gBt);
		lefP.add(imgB,"North");
		lefP.add(lab1, "Center");
		lefP.add(leP, "South");

		//오른쪽 패널 설정
		riP.setPreferredSize(new Dimension(0,150));
		imgB1.setPreferredSize(new Dimension(0,250));
		imgB1.setBackground(Color.white);
		imgB1.setMargin(new Insets(0,0,0,0));
		imgB1.setBorderPainted(false);
		eBt.setSize(150, 150);
		eBt.setLocation(30, 50);
		eBt.setBackground(Color.white);
		eBt.setMargin(new Insets(0,0,0,0));
		eBt.setBorderPainted(false);
		eBt.addActionListener(this);
		riP.add(eBt);
		rigP.add(imgB1,"North");
		rigP.add(lab2, "Center");
		rigP.add(riP, "South");
		oBt.setEnabled(false);
		eBt.setEnabled(false);
		
		frame.setSize(750, 500);
		frame.setLocation(230,60);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sBt ){
			sBt.setEnabled(false);
			text.append("\nServer님의 준비가 완료되었습니다.");
			if (ClientPo.sBt.isEnabled() == false) {
				text.append("\n게임 시작하겠습니다.");
				ClientPo.text.append("\n게임 시작하겠습니다.");
				text.append("\n카드를 드리겠습니다.");
				ClientPo.text.append("\n카드를 드리겠습니다");
				Open();
				ClientPo.Open();
				//카드를 받으면 배팅 1개 기본 설정
				seC -= 1;
				ClientPo.seC -= 1;
				clC -= 1;
				ClientPo.clC -= 1;
				totalBat += 2;
				ClientPo.totalBat += 2;
				Coin thr = new Coin(2);
				Thread slab = new Thread(thr);
				slab.start();
				lab1.setText("Server님의 칩 수 : " + seC);
				ClientPo.lab1.setText("Server님의 칩 수 : " + seC);
				lab2.setText("Client님의 칩 수 : " + clC);
				ClientPo.lab2.setText("Client님의 칩 수 : " + clC);
				lab3.setText("현재 배팅 된 칩 수 : " + totalBat);
				ClientPo.lab3.setText("현재 배팅 된 칩 수 : " + totalBat);
			}
		} else if (e.getSource() == bBt) {
			if (serO == 0) {
				ba = JOptionPane.showInputDialog("배팅 할 칩의 개수는 ? ");
				bat = Integer.parseInt("" + ba);
				if (bat < ClientPo.bat) {
					JOptionPane.showMessageDialog(null, "전 배팅보다 적게 할 수 없습니다! 다시 배팅해 주세요");
				// 전 플레이어보다 배팅액이 같거나 커야함
				} else if (bat >= ClientPo.bat) {
					text.append("\nServer님이 "+bat+"개를 배팅 하셨습니다.");
					ClientPo.text.append("\nServer님이 "+bat+"개를 배팅 하셨습니다.");
					seC -= bat;
					ClientPo.seC -= bat;
					totalBat += bat;
					ClientPo.totalBat += bat;
					lab1.setText("Server님의 칩 수 : " + seC);
					ClientPo.lab1.setText("Server님의 칩 수 : " + seC);
					lab3.setText("현재 배팅 된 칩 수 : " + totalBat);
					ClientPo.lab3.setText("현재 배팅 된 칩 수 : " + totalBat);
					Coin thr = new Coin(bat);
					Thread slab = new Thread(thr);
					slab.start();
					eBt.setEnabled(true);
					//전 플레이어와 배팅액이 같을 시에만 오픈버튼 활성화
					if(bNum > 1 && bat == ClientPo.bat){
						oBt.setEnabled(true);
					}
				} 
			} else {
				JOptionPane.showMessageDialog(null, "당신 차례가 아닙니다!");
			}
		} else if (e.getSource() == aBt) {
			if (bNum > 0) {
				oBt.setEnabled(true);
			}
			eBt.setEnabled(true);
			Coin thr = new Coin(seC);
			Thread slab = new Thread(thr);
			slab.start();
			text.append("\nServer님이 올인 하셨습니다.");
			ClientPo.text.append("\nServer님이 올인 하셨습니다.");
			totalBat += seC;
			ClientPo.totalBat += seC;
			seC = 0;
			ClientPo.seC = 0;
			lab1.setText("Server님의 칩 수 : " + seC);
			ClientPo.lab1.setText("Server님의 칩 수 : " + seC);
			lab3.setText("현재 배팅 된 칩 수 : " + totalBat);
			ClientPo.lab3.setText("현재 배팅 된 칩 수 : " + totalBat);
		} else if (e.getSource() == eBt){
			text.append("\nClient님 차례입니다.");
			ClientPo.text.append("\nClient님 차례입니다.");
			bNum += 1;
			ClientPo.bNum += 1;
			oBt.setEnabled(true);
			eBt.setEnabled(false);
			//순서 설정을 하기위한  변수에 값 입력(0일 때 자기 차례)
			ClientPo.clO = 0;
			clO = 0;
			ClientPo.serO = 1;
			serO = 1;
		} else if (e.getSource() == oBt) {
			//서버의 카드 값 이 클라이언트 카드 값보다 높을 때
			text.append("\n카드를 오픈하겠습니다.");
			ClientPo.text.append("\n카드를 오픈하겠습니다.");
			if (ServerCard > ClientCard) {
				//클라이언트의 값이 0이 되었을 때(서버가 승리할 때)
				if(clC <= 0){
					text.append("\nServer님이 최종 승리하셨습니다.");
					ClientPo.text.append("\nServer님이 최종 승리하셨습니다.");
					Server.serverS++;
					Client.serverS++;
					seC += totalBat;
					ClientPo.seC += totalBat;
					lab1.setText("Server님의 칩 수 : " + seC);
					ClientPo.lab1.setText("Server님의 칩 수 : " + seC);
					lab2.setText("Client님의 칩 수 : 0");
					ClientPo.lab2.setText("Client님의 칩 수 : 0");
					lab3.setText("현재 배팅 된 칩 수 : 0");
					ClientPo.lab3.setText("현재 배팅 된 칩 수 : 0");
					ClientPo.pane.showMessageDialog(null, "게임에서 패배하셨습니다..", "Client", 0);
					JOptionPane.showMessageDialog(null, "게임에서 승리하셨습니다!", "Server", 1);
					Server.serverScore.setText("Server님의 최종 스코어 : " + Server.serverS);
					Client.serverScore.setText("Server님의 최종 스코어 : " + Server.serverS);
					Server.chA.append("\n Server님이 최종 승리하셨습니다.");
					Client.chA.append("\n Server님이 최종 승리하셨습니다.");
					frame.setVisible(false);
					ClientPo.frame.setVisible(false);
				//게임 승부 결과가 나지 않을 때
				} else {
					text.append("\nServer님이 승리하셨습니다.\n" + totalBat +"개를 Server님께서 얻으셨습니다." );
					ClientPo.text.append("\nServer님이 승리하셨습니다."+ totalBat +"개를 Server님께서 얻으셨습니다.");
					text.append("\nServer님 차례입니다.");
					ClientPo.text.append("\nServer님 차례입니다.");
					removeCoin();
					Coin thr = new Coin(2);
					Thread slab = new Thread(thr);
					slab.start();
					seC += totalBat;
					ClientPo.seC += totalBat;
					seC -= 1;
					ClientPo.seC -= 1;
					totalBat = 2;
					ClientPo.totalBat = 2;
					clC -= 1;
					ClientPo.clC -= 1;
					lab1.setText("Server님의 칩 수 : " + seC);
					ClientPo.lab1.setText("Server님의 칩 수 : " + seC);
					lab2.setText("Client님의 칩 수 : " + clC);
					ClientPo.lab2.setText("Client님의 칩 수 : " + clC);
					lab3.setText("현재 배팅 된 칩 수 : " + totalBat);
					ClientPo.lab3.setText("현재 배팅 된 칩 수 : " + totalBat);
					ClientPo.clO = 1;
					clO = 1;
					ClientPo.serO = 0;
					serO = 0;
				}
			// 클라이언트의 카드 값이 더 높을 때
			} else if (ServerCard < ClientCard) {
				//서버 스코어가 0이 되었을 때(클라이언트가 이겼을 때)
				if (seC <= 0) {
					text.append("\nClient님이 최종 승리하셨습니다.");
					ClientPo.text.append("\nClient님이 최종 승리하셨습니다.");
					Client.clientS++;
					Server.serverS++;
					clC += totalBat;
					ClientPo.clC += totalBat;
					lab1.setText("Server님의 칩 수 : 0");
					ClientPo.lab1.setText("Server님의 칩 수 : 0");
					lab2.setText("Client님의 칩 수 : " + clC);
					ClientPo.lab2.setText("Client님의 칩 수 : " + clC);
					lab3.setText("현재 배팅 된 칩 수 : 0");
					ClientPo.lab3.setText("현재 배팅 된 칩 수 : 0");
					ClientPo.pane.showMessageDialog(null, "게임에서 승리하셨습니다!", "Client", 1);
					JOptionPane.showMessageDialog(null, "게임에서 패배하셨습니다..", "Server", 0);
					Server.clientScore.setText("Client님의 최종 스코어 : " + Client.clientS);
					Client.clientScore.setText("Client님의 최종 스코어 : " + Client.clientS);
					Server.chA.append("\n Client님이 최종 승리하셨습니다.");
					Client.chA.append("\n Client님이 최종 승리하셨습니다.");
					frame.setVisible(false);
					ClientPo.frame.setVisible(false);
				//승부의 결과가 나지 않을 때
				} else {
					text.append("\nClient님이 승리하셨습니다.\n" + totalBat +"개를 Client님께서 얻으셨습니다." );
					ClientPo.text.append("\nClient님이 승리하셨습니다."+ totalBat +"개를 Client님께서 얻으셨습니다.");
					text.append("\nClient님 차례입니다.");
					ClientPo.text.append("\nClient님 차례입니다.");
					removeCoin();
					Coin thr = new Coin(2);
					Thread slab = new Thread(thr);
					slab.start();
					clC += totalBat;
					ClientPo.clC += totalBat;
					seC -= 1;
					ClientPo.seC -= 1;
					totalBat = 2;
					ClientPo.totalBat = 2;
					clC -= 1;
					ClientPo.clC -= 1;
					lab1.setText("Server님의 칩 수 : " + seC);
					ClientPo.lab1.setText("Server님의 칩 수 : " + seC);
					lab2.setText("Client님의 칩 수 : " + clC);
					ClientPo.lab2.setText("Client님의 칩 수 : " + clC);
					lab3.setText("현재 배팅 된 칩 수 : " + totalBat);
					ClientPo.lab3.setText("현재 배팅 된 칩 수 : " + totalBat);
					ClientPo.clO = 0;
					clO = 0;
					ClientPo.serO = 1;
					serO = 1;
				}
			//무승부 일 때 재 경기
			} else {
				removeCoin();
				ClientPo.removeCoin();
			}
			text.append("\n카드를 나눠드리겠습니다." );
			ClientPo.text.append("\n카드를 나눠드리겠습니다.");
			Open();
			ClientPo.Open();
			oBt.setEnabled(false);
			eBt.setEnabled(false);
			bNum = 0;
			ClientPo.bNum = 0;
			bat = 0;
			ClientPo.bat = 0;
			totalBat = 0;
			ClientPo.totalBat = 0;
		} else if (e.getSource() == gBt) {
			Open();
			ClientPo.Open();
			bNum = 0;
			ClientPo.bNum = 0;
			ClientPo.clO = 0;
			clO = 0;
			ClientPo.serO = 1;
			serO = 1;
			removeCoin();
			if(ServerCard == 10){
				clC += 7;
				ClientPo.clC += 7;
				seC -= 7;
				ClientPo.seC -= 7;
			}
			Coin thr = new Coin(1);
			Thread slab = new Thread(thr);
			slab.start();
			clC += totalBat;
			ClientPo.clC += totalBat;
			text.append("\nServer님이 게임에서 포기하셨습니다." );
			ClientPo.text.append("\nServer님이 게임에서 포기하셨습니다.");
			text.append("\nClient님이 승리하셨습니다.\n" + totalBat +"개를 Client님께서 얻으셨습니다." );
			ClientPo.text.append("\nClient님이 승리하셨습니다.\n"+ totalBat +"개를 Client님께서 얻으셨습니다.");
			text.append("\n카드를 나눠드리겠습니다." );
			ClientPo.text.append("\n카드를 나눠드리겠습니다.");
			totalBat = 2;
			ClientPo.totalBat = 2;
			seC -= 1;
			ClientPo.seC -= 1;
			clC -= 1;
			ClientPo.clC -= 1;
			lab1.setText("Server님의 칩 수 : " + seC);
			ClientPo.lab1.setText("Server님의 칩 수 : " + seC);
			lab2.setText("Client님의 칩 수 : " + clC);
			ClientPo.lab2.setText("Client님의 칩 수 : " + clC);
			lab3.setText("현재 배팅 된 칩 수 : " + totalBat);
			ClientPo.lab3.setText("현재 배팅 된 칩 수 : " + totalBat);
			oBt.setEnabled(false);
			eBt.setEnabled(false);
		}
		
	}
	
	/**
	 * 카드를 오픈하는 method
	 */
	public static void Open(){
		ServerCard = random.nextInt(9) + 1;
		ClientCard = ClientPo.ClientCard;
		ClientPo.imgB.setIcon(new ImageIcon("D:/c" + ServerCard + ".jpg"));
		ClientPo.imgB1.setIcon(new ImageIcon("D:/back.jpg"));
	}
	
	/**
	 * 게임 프레임 패널 안에 배팅칩을 던지는 액션의 method
	 * @param lab JLabel 형 , Coin클래스에서 위치 및 이미지가 설정된 JLabel을 받아옴
	 */
	public static void Bat(JLabel[] lab) {
		for (int i = 0; i < lab.length; i++) {
			ceP.add(lab[i]);
			ceP.revalidate();
			ceP.repaint();
			ClientPo.ceP.add(lab[i]);
			ClientPo.ceP.revalidate();
			ClientPo.ceP.repaint();
		}
	}
	
	/**
	 * 패널 안에 배팅칩을 지우는 method
	 */
	public static void removeCoin() {
		ceP.removeAll();
		ceP.repaint();
		sBt.setSize(100, 40);
		sBt.setLocation(105, 200);
		ceP.add(sBt);
		ClientPo.ceP.removeAll();
		ClientPo.ceP.repaint();
		ClientPo.sBt.setSize(100, 40);
		ClientPo.sBt.setLocation(105, 200);
		ClientPo.ceP.add(sBt);
	}

}
