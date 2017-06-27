
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ServerWB implements ActionListener {
	static JFrame frame = new JFrame("Server");

	JPanel topP = new JPanel();
	JPanel underP = new JPanel();
	JPanel cenP = new JPanel();

	static JTextArea text = new JTextArea();
	JScrollPane scr = new JScrollPane(text);

	static int serS = 0; // 서버 승점
	static int clS = 0; // 클라이언트 승점

	static JLabel lab = new JLabel("Server 승점 : " + serS);
	static JLabel lab1 = new JLabel("Client 승점 : " + clS);

	JButton[] topBt = new JButton[9];
	static JButton[] underBt = new JButton[9];
	static JButton serBt = new JButton();
	static JButton clBt = new JButton();
	JButton inBt = new JButton("입력");
	static JButton stBt = new JButton("시작하기");

	static int serO = 0; // 서버 순서
	static int clO = 0; // 클라이언트 순서
	private int nBt = 0;
	static int ser = 0; // 서버버튼 값
	static int cl = 0; // 클라이언트버튼 값

	ImageIcon icon;
	ImageIcon icon1;
	JScrollPane scrollPane = new JScrollPane();

	Random random = new Random();
	static int[] ranN = new int[9];		//타일의 값을 랜덤으로 설정하기 위한 랜덤 난수생성

	static JOptionPane pane;

	public ServerWB() {
		icon = new ImageIcon("D:/09.jpg");
		// 이미지를 불러옴
		JPanel pan = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		scrollPane = new JScrollPane(pan);
		frame.setContentPane(scrollPane);

		pan.setLayout(new BorderLayout());
		topP.setLayout(new GridLayout(1, 9));
		underP.setLayout(new GridLayout(1, 9));
		cenP.setLayout(new BorderLayout());

		//카드의 값을 랜덤하게 넣기 위해 
		for (int i = 0; i < 9; i++) {
			int k = random.nextInt(9);
			ranN[i] = k;
			for (int j = 0; j < i; j++) {
				if (ranN[i] == ranN[j]) {
					k = random.nextInt(9);
					ranN[i] = k;
					i = i - 1;
					break;
				}
			}
		}

		// Server타일 색과 크기 설정
		for (int i = 0; i < topBt.length; i++) {
			topP.add(topBt[i] = new JButton());
			topBt[i].addActionListener(this);
			topBt[i].setBackground(Color.white);
			topBt[i].setMargin(new Insets(0, 0, 0, 0));
			topBt[i].setBorderPainted(false);
		}

		// Client타일 색과 크기 설정
		for (int i = 0; i < underBt.length; i++) {
			underP.add(underBt[i] = new JButton());
			underBt[i].addActionListener(this);
			underBt[i].setBackground(Color.white);
			underBt[i].setMargin(new Insets(0, 0, 0, 0));
			underBt[i].setBorderPainted(false);
		}

		topP.setPreferredSize(new Dimension(70, 70));
		underP.setPreferredSize(new Dimension(70, 70));
		scr.setPreferredSize(new Dimension(215, 100));

		icon1 = new ImageIcon("D:/09.jpg");
		// 이미지를 불러옴
		JPanel cenP = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon1.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		cenP.setLayout(null);

		serBt.setSize(80, 110);
		serBt.setLocation(40, 100);
		serBt.setEnabled(false);
		serBt.setBackground(null);

		clBt.setSize(80, 110);
		clBt.setLocation(160, 100);
		clBt.setEnabled(false);
		clBt.setBackground(null);

		inBt.setSize(60, 20);
		inBt.setLocation(195, 220);
		inBt.addActionListener(this);
		inBt.setBackground(Color.white);
		inBt.setForeground(Color.gray);
		inBt.setMargin(new Insets(0, 0, 0, 0));
		inBt.setBorderPainted(false);
		inBt.setFont(new Font("궁서", Font.ITALIC, 14));

		stBt.setSize(100, 40);
		stBt.setLocation(160, 270);
		stBt.addActionListener(this);
		stBt.setBackground(Color.white);
		stBt.setForeground(Color.gray);
		stBt.setMargin(new Insets(0, 0, 0, 0));
		stBt.setBorderPainted(false);
		stBt.setFont(new Font("궁서", Font.ITALIC, 18));

		lab.setSize(150, 50);
		lab.setLocation(40, 40);

		lab1.setSize(150, 50);
		lab1.setLocation(40, 220);

		cenP.add(inBt);
		cenP.add(clBt);
		cenP.add(serBt);
		cenP.add(lab);
		cenP.add(lab1);
		cenP.add(stBt);

		pan.add(topP, "North");
		pan.add(underP, "South");
		pan.add(scr, "East");
		pan.add(cenP);

		text.setText("                          상  황  판 ");

		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setLocation(230,60);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 흑색, 백색 타일을 냄
		if (e.getSource() == stBt) {
			stBt.setEnabled(false);
			ClientWB.text.append("\nServer 님의 준비가 완료되었습니다.");
			if (ClientWB.stBt.isEnabled() == false) {
				text.append("\n게임을 시작하겠습니다.");
				ClientWB.text.append("\n게임을 시작하겠습니다.");
			}
			//Server 타일에 값과 색을 설정함
			for (int i = 0; i < topBt.length; i++) {
				if (ranN[i] == 0 || ranN[i] == 2 || ranN[i] == 4 || ranN[i] == 6 || ranN[i] == 8) {
					topBt[i].setText("" + ranN[i]);
					topBt[i].setBackground(Color.black);
					topBt[i].setForeground(Color.white);
				} else {
					topBt[i].setText("" + ranN[i]);
					topBt[i].setBackground(Color.white);
				}
			}
			//Client 타일에 색만 설정
			for (int i = 0; i < topBt.length; i++) {
				if (ranN[i] == 0 || ranN[i] == 2 || ranN[i] == 4 || ranN[i] == 6 || ranN[i] == 8) {
					ClientWB.topBt[i].setBackground(Color.black);
					ClientWB.topBt[i].setForeground(Color.white);
				} else {
					ClientWB.topBt[i].setBackground(Color.white);
				}
			}
		} else {
			//serO의 차례 일 때
			if (serO == 0) {
				for (int i = 0; i < topBt.length; i++) {
					if (e.getSource() == topBt[i]) {
						if (ranN[i] == 0 || ranN[i] == 2 || ranN[i] == 4 || ranN[i] == 6 || ranN[i] == 8) {
							serBt.setBackground(Color.black);
							serBt.setForeground(Color.white);
							topBt[i].setEnabled(false);
							ClientWB.topBt[i].setText("x");
							serBt.setEnabled(true);
							ser = ranN[i];
						} else {
							serBt.setBackground(Color.white);
							topBt[i].setEnabled(false);
							serBt.setEnabled(true);
							ClientWB.topBt[i].setText("x");
							ser = ranN[i];
						}
					}
				}
			} else if (serO == 1) {
				JOptionPane.showMessageDialog(null, "당신 차례가 아닙니다!");
			}
			// 값 입력 버튼
			if (e.getSource() == inBt) {
				serO = 1;
				ClientWB.clO = 0;
				//버튼 클릭 했을 때 이벤트 설정(텍스트 창에 플레이어의 행동 입력)
				if (ser == 0 || ser == 2 || ser == 4 || ser == 6 || ser == 8) {
					ClientWB.serBt.setBackground(Color.black);
					ClientWB.serBt.setForeground(Color.white);
					ClientWB.serBt.setEnabled(true);
					ClientWB.text.append("\nServer님이 검은색 타일을 선택하셨습니다.");
					text.append("\nServer님이 검은색 타일을 선택하셨습니다.");
					if (serO == 0) {
						ClientWB.text.append("\nClient님 차례입니다.");
						text.append("\nClient님 차례입니다.");
					}
				} else {
					ClientWB.serBt.setBackground(Color.white);
					ClientWB.serBt.setEnabled(true);
					ClientWB.text.append("\nServer님이 흰색 타일을 선택하셨습니다.");
					text.append("\nServer님이 흰색 타일을 선택하셨습니다.");
					if (serO == 0) {
						ClientWB.text.append("\nClient님 차례입니다.");
						text.append("\nClient님 차례입니다.");
					}
				}
				//두 플레이어가 다 입력했을 때 플레이어 간에 값 비교
				if (serBt.isEnabled() == true && clBt.isEnabled() == true) {
					//Server의 값이 더 클 경우
					if (ser > ClientWB.cl) {
						serS++;
						text.append("\nServer님이 승리하셨습니다.");
						lab.setText("Server 승점 : " + serS);
						ClientWB.serS++;
						ClientWB.text.append("\nServer님이 승리하셨습니다.");
						ClientWB.lab.setText("Server 승점 : " + serS);
						//Server의 값이 5가 넘으면 최종 승리
						if (serS > 4) {
							JOptionPane.showMessageDialog(null, "게임에서 최종승리하셨습니다!", "Server", 1);
							ClientWB.pane.showMessageDialog(null, "게임에서 패배하셨습니다..", "Client", 0);
							Server.serverS++;
							Client.serverS++;
							Server.serverScore.setText("Server님의 최종 스코어 : " + Server.serverS);
							Client.serverScore.setText("Server님의 최종 스코어 : " + Server.serverS);
							ClientPo.clO = 1;
							ServerPo.clO = 1;
							ClientPo.serO = 0;
							ServerPo.serO = 0;
							frame.setVisible(false);
							ClientWB.frame.setVisible(false);
						//5가 넘지 않을 경우 계속 진행
						} else {
							text.append("\nServer님 차례입니다.");
							ClientWB.text.append("\nServer님 차례입니다.");
							serO = 0;
							ClientWB.clO = 1;
						}
					//Client의 값이 더 클경우
					} else if (ser < ClientWB.cl) {
						clS++;
						text.append("\nClient님이 승리하셨습니다.");
						lab1.setText("Client 승점 : " + clS);
						ClientWB.clS++;
						ClientWB.text.append("\nClient님이 승리하셨습니다.");
						ClientWB.lab1.setText("Client 승점 : " + clS);
					//Client의 스코어가 5가 넘을 경우 최종승리
						if (clS > 4) {
							JOptionPane.showMessageDialog(null, "Client님이 최종승리하셨습니다!", "Client", 1);
							ServerWB.pane.showMessageDialog(null, "게임에서 패배하셨습니다..", "Server", 0);
							Client.clientS++;
							Server.clientS++;
							Server.clientScore.setText("Client님의 최종 스코어 : " + Client.clientS);
							Client.clientScore.setText("Client님의 최종 스코어 : " + Client.clientS);
							ClientPo.clO = 0;
							ServerPo.clO = 0;
							ClientPo.serO = 1;
							ServerPo.serO = 1;
							frame.setVisible(false);
							ClientWB.frame.setVisible(false);
						//안 넘을 경우 계속 진행
						} else {
							serO = 1;
							ClientWB.clO = 0;
							text.append("\nClient님 차례입니다.");
							ClientWB.text.append("\nClient님 차례입니다.");
						}
					// 비겼을 때
					} else if (ser == ClientWB.cl) {
						serS++;
						clS++;
						lab.setText("Server 승점 : " + serS);
						lab1.setText("Client 승점 : " + clS);
						ClientWB.serS++;
						ClientWB.clS++;
						ClientWB.lab.setText("Server 승점 : " + serS);
						ClientWB.lab1.setText("Client 승점 : " + clS);
					// Server나 Client의 스코어가 5가 넘었을 때 최종승리
						if (serS > 4 ) {
							JOptionPane.showMessageDialog(null, "게임에서 최종승리하셨습니다!", "Server", 1);
							ClientWB.pane.showMessageDialog(null, "게임에서 패배하셨습니다..", "Client", 0);
							Server.serverS++;
							Client.serverS++;
							Server.serverScore.setText("Server님의 최종 스코어 : " + Server.serverS);
							Client.serverScore.setText("Server님의 최종 스코어 : " + Server.serverS);
							ClientPo.clO = 1;
							ServerPo.clO = 1;
							ClientPo.serO = 0;
							ServerPo.serO = 0;
							frame.setVisible(false);
							ClientWB.frame.setVisible(false);
					// 둘다 안넘었을 때 계속 진행
						} else if (clS > 4){
							JOptionPane.showMessageDialog(null, "Client님이 최종승리하셨습니다!", "Client", 1);
							ServerWB.pane.showMessageDialog(null, "게임에서 패배하셨습니다..", "Server", 0);
							Client.clientS++;
							Server.clientS++;
							Server.clientScore.setText("Client님의 최종 스코어 : " + Client.clientS);
							Client.clientScore.setText("Client님의 최종 스코어 : " + Client.clientS);
							ClientPo.clO = 0;
							ServerPo.clO = 0;
							ClientPo.serO = 1;
							ServerPo.serO = 1;
							frame.setVisible(false);
							ClientWB.frame.setVisible(false);
						} else {
							if (serO == 0) {
								text.append("\n무승부입니다.");
								text.append("\n무승부 하였으므로 전판 승리하신");
								text.append("\nServer님 차례입니다.");
								ClientWB.text.append("\n무승부입니다.");
								ClientWB.text.append("\n무승부 하였으므로 전판 승리하신");
								ClientWB.text.append("\nServer님 차례입니다.");
								serO = 0;
								ClientWB.clO = 1;
							} else if (ClientWB.clO == 0) {
								text.append("\n무승부입니다.");
								text.append("\n무승부 하였으므로 전판 승리하신");
								text.append("\nClient님 차례입니다.");
								ClientWB.text.append("\n무승부입니다.");
								ClientWB.text.append("\n무승부 하였으므로 전판 승리하신");
								ClientWB.text.append("\nClient님 차례입니다.");
								serO = 1;
								ClientWB.clO = 0;
							}
						}
					}
					serBt.setBackground(null);
					clBt.setBackground(null);
					serBt.setEnabled(false);
					clBt.setEnabled(false);
					ClientWB.serBt.setBackground(null);
					ClientWB.clBt.setBackground(null);
					ClientWB.serBt.setEnabled(false);
					ClientWB.clBt.setEnabled(false);
				}
			}
		}

	}

}
