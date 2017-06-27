
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

public class ClientWB implements ActionListener {
	static JFrame frame = new JFrame("Client");

	JPanel topP = new JPanel();
	JPanel underP = new JPanel();
	JPanel cenP = new JPanel();

	static JTextArea text = new JTextArea();
	JScrollPane scr = new JScrollPane(text);

	static int serS = 0; // 서버 승점
	static int clS = 0; // 클라이언트 승점

	static JLabel lab = new JLabel("Server 승점 : " + serS);
	static JLabel lab1 = new JLabel("Client 승점 : " + clS);

	static JButton[] topBt = new JButton[9];
	JButton[] underBt = new JButton[9];
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
	int[] ranN = new int[9];

	static JOptionPane pane;

	public ClientWB() {
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

		for (int i = 0; i < topBt.length; i++) {
			topP.add(topBt[i] = new JButton());
			topBt[i].addActionListener(this);
			topBt[i].setBackground(Color.white);
			topBt[i].setMargin(new Insets(0, 0, 0, 0));
			topBt[i].setBorderPainted(false);
		}

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

		text.setText("                     상  황  판 ");

		frame.setSize(500, 500);
		frame.setLocation(770,300);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 흑색, 백색 타일을 냄
		if (e.getSource() == stBt) {
			stBt.setEnabled(false);
			ServerWB.text.append("\nClient 님의 준비가 완료되었습니다.");
			if (ServerWB.stBt.isEnabled() == false) {
				text.append("\n게임을 시작하겠습니다.");
				ServerWB.text.append("\n게임을 시작하겠습니다.");
			}
			for (int i = 0; i < underBt.length; i++) {
				if (ranN[i] == 0 || ranN[i] == 2 || ranN[i] == 4 || ranN[i] == 6 || ranN[i] == 8) {
					underBt[i].setText("" + ranN[i]);
					underBt[i].setBackground(Color.black);
					underBt[i].setForeground(Color.white);
				} else {
					underBt[i].setText("" + ranN[i]);
					underBt[i].setBackground(Color.white);
				}
			}
			for (int i = 0; i < topBt.length; i++) {
				if (ranN[i] == 0 || ranN[i] == 2 || ranN[i] == 4 || ranN[i] == 6 || ranN[i] == 8) {
					ServerWB.underBt[i].setBackground(Color.black);
					ServerWB.underBt[i].setForeground(Color.white);
				} else {
					ServerWB.underBt[i].setBackground(Color.white);
				}
			}
		} else {

			if (clO == 0) {
				for (int i = 0; i < underBt.length; i++) {
					if (e.getSource() == underBt[i]) {
						if (ranN[i] == 0 || ranN[i] == 2 || ranN[i] == 4 || ranN[i] == 6 || ranN[i] == 8) {
							clBt.setBackground(Color.black);
							clBt.setForeground(Color.white);
							underBt[i].setEnabled(false);
							ServerWB.underBt[i].setText("x");
							clBt.setEnabled(true);
							cl = ranN[i];
						} else {
							clBt.setBackground(Color.white);
							underBt[i].setEnabled(false);
							ServerWB.underBt[i].setText("x");
							clBt.setEnabled(true);
							cl = ranN[i];
						}
					}
				}
			} else if (clO == 1) {
				JOptionPane.showMessageDialog(null, "당신 차례가 아닙니다!");
			}

			if (e.getSource() == inBt) {
				ServerWB.serO = 0;
				clO = 1;
				if (cl == 0 || cl == 2 || cl == 4 || cl == 6 || cl == 8) {
					ServerWB.clBt.setBackground(Color.black);
					ServerWB.clBt.setForeground(Color.white);
					ServerWB.clBt.setEnabled(true);
					ServerWB.text.append("\nClient님이 검은색 타일을 선택하셨습니다.");
					text.append("\nClient님이 검은색 타일을 선택하셨습니다.");
					if (clO == 0) {
						ServerWB.text.append("\nServer님 차례입니다.");
						text.append("\nServer님 차례입니다.");
					}
				} else {
					ServerWB.clBt.setBackground(Color.white);
					ServerWB.clBt.setEnabled(true);
					ServerWB.text.append("\nClient님이 흰색 타일을 선택하셨습니다.");
					text.append("\nClient님이 흰색 타일을 선택하셨습니다.");
					if (clO == 0) {
						ServerWB.text.append("\nServer님 차례입니다.");
						text.append("\nServer님 차례입니다.");
					}
				}

				if (serBt.isEnabled() == true && clBt.isEnabled() == true) {
					if (ServerWB.ser < cl) {
						clS++;
						text.append("\nClient님이 승리하셨습니다.");
						ServerWB.clS++;
						ServerWB.text.append("\nClient님이 승리하셨습니다.");
						lab1.setText("Client 승점 : " + clS);
						ServerWB.lab1.setText("Client 승점 : " + clS);
						if (clS > 4) {
							Client.clientS++;
							Client.clientS++;
							Server.clientScore.setText("Client님의 최종 스코어 : " + Client.clientS);
							Client.clientScore.setText("Client님의 최종 스코어 : " + Client.clientS);
							JOptionPane.showMessageDialog(null, "Client님이 최종승리하셨습니다!", "Client", 1);
							ServerWB.pane.showMessageDialog(null, "게임에서 패배하셨습니다..", "Server", 0);
							ClientPo.clO = 0;
							ServerPo.clO = 0;
							ClientPo.serO = 1;
							ServerPo.serO = 1;
							frame.setVisible(false);
							ServerWB.frame.setVisible(false);
						} else {
							ServerWB.serO = 1;
							clO = 0;
							text.append("\nCleint님 차례입니다.");
							ServerWB.text.append("\nCleint님 차례입니다.");
						}
					} else if (ServerWB.ser > cl) {
						serS++;
						text.append("\nServer님이 승리하셨습니다.");
						lab.setText("Server 승점 : " + serS);
						ServerWB.serS++;
						ServerWB.text.append("\nServer님이 승리하셨습니다.");
						ServerWB.lab.setText("Server 승점 : " + serS);
						if (serS > 4) {
							Server.serverS++;
							Client.serverS++;
							Server.serverScore.setText("Server님의 최종 스코어 : " + Server.serverS);
							Client.serverScore.setText("Server님의 최종 스코어 : " + Server.serverS);
							JOptionPane.showMessageDialog(null, "게임에서 최종승리하셨습니다!", "Server", 1);
							ClientWB.pane.showMessageDialog(null, "게임에서 패배하셨습니다..", "Client", 0);
							ClientPo.clO = 1;
							ServerPo.clO = 1;
							ClientPo.serO = 0;
							ServerPo.serO = 0;
							frame.setVisible(false);
							ServerWB.frame.setVisible(false);
						} else {
							text.append("\nServer님 차례입니다.");
							ServerWB.text.append("\nServer님 차례입니다.");
							ServerWB.serO = 0;
							clO = 1;
						}
					} else if (ServerWB.ser == cl) {
						clS++;
						serS++;
						lab1.setText("Client 승점 : " + clS);
						lab.setText("Server 승점 : " + serS);
						ServerWB.clS++;
						ServerWB.serS++;
						ServerWB.lab.setText("Server 승점 : " + serS);
						ServerWB.lab1.setText("Client 승점 : " + clS);
						if (clS > 4) {
							Client.clientS++;
							Client.clientS++;
							Server.clientScore.setText("Client님의 최종 스코어 : " + Client.clientS);
							Client.clientScore.setText("Client님의 최종 스코어 : " + Client.clientS);
							JOptionPane.showMessageDialog(null, "Client님이 최종승리하셨습니다!", "Client", 1);
							ServerWB.pane.showMessageDialog(null, "게임에서 패배하셨습니다..", "Server", 0);
							ClientPo.clO = 0;
							ServerPo.clO = 0;
							ClientPo.serO = 1;
							ServerPo.serO = 1;
							frame.setVisible(false);
							ServerWB.frame.setVisible(false);
						} else if (serS > 4) {
							Server.serverS++;
							Client.serverS++;
							Server.serverScore.setText("Server님의 최종 스코어 : " + Server.serverS);
							Client.serverScore.setText("Server님의 최종 스코어 : " + Server.serverS);
							JOptionPane.showMessageDialog(null, "게임에서 최종승리하셨습니다!", "Server", 1);
							ClientWB.pane.showMessageDialog(null, "게임에서 패배하셨습니다..", "Client", 0);
							ClientPo.clO = 1;
							ServerPo.clO = 1;
							ClientPo.serO = 0;
							ServerPo.serO = 0;
							frame.setVisible(false);
							ServerWB.frame.setVisible(false);
						}  else {
							if (clO == 0) {
								text.append("\n무승부입니다.");
								text.append("\n무승부 하였으므로 전판 승리하신");
								text.append("\nClient님 차례입니다.");
								ServerWB.serO = 1;
								clO = 0;
								ServerWB.text.append("\n무승부입니다.");
								ServerWB.text.append("\n무승부 하였으므로 전판 승리하신");
								ServerWB.text.append("\nClient님 차례입니다.");
							} else if (ServerWB.serO == 0) {
								text.append("\n무승부입니다.");
								text.append("\n무승부 하였으므로 전판 승리하신");
								text.append("\nServer님 차례입니다.");
								ServerWB.serO = 0;
								clO = 1;
								ServerWB.text.append("\n무승부입니다.");
								ServerWB.text.append("\n무승부 하였으므로 전판 승리하신");
								ServerWB.text.append("\nServer님 차례입니다.");
							}
						}
					}
					serBt.setBackground(null);
					clBt.setBackground(null);
					serBt.setEnabled(false);
					clBt.setEnabled(false);
					ServerWB.serBt.setBackground(null);
					ServerWB.clBt.setBackground(null);
					ServerWB.serBt.setEnabled(false);
					ServerWB.clBt.setEnabled(false);
				}
			}
		}

	}

}
