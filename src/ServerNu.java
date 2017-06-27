
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
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerNu implements ActionListener {
	static JFrame frame = new JFrame("Server");
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	static JTextArea text = new JTextArea();
	static JTextArea text1 = new JTextArea();
	JTextField textField = new JTextField();
	
	JButton bt = new JButton();
	JButton[] nbt = new JButton[10];	//입력 숫자
	JButton[] underBt = new JButton[4]; //입력 받는 숫자
	static JButton[] serverBt = new JButton[4];  //서버
	static JButton[] clientBt = new JButton[4];  //클라이언트
	JButton moveBt = new JButton();   //기타 버튼
	JButton deleteBt = new JButton(); 
	static String[] cNu = new String[4];   //비교하기 위한 클라이언트 4자리 수
	static String[] sNu = new String[4];   //비교하가 위한 서버 4자리 수
	
	private int numC = 0;
	private int btC = 0;
	private int ball = 0;
	private int strike = 0;
	private int count = 0;
	
	Socket socket;
	ImageIcon icon;
	JScrollPane scrollPane = new JScrollPane();

	static JOptionPane pane;
	public ServerNu() {

		icon = new ImageIcon("D:/09.jpg");
		// 배경 이미지를 불러옴
		JPanel pan = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		scrollPane = new JScrollPane(pan);
		frame.setContentPane(scrollPane);
		
		pan.setLayout(new BorderLayout(0,90));
		panel.setLayout(new GridLayout(2, 5));
		panel1.setLayout(new GridLayout(1,2));

		//각 버튼의 이미지 생성
		nbt[0] = new JButton(new ImageIcon("D:/n0.jpg"));
		nbt[1] = new JButton(new ImageIcon("D:/n1.jpg"));
		nbt[2] = new JButton(new ImageIcon("D:/n2.jpg"));
		nbt[3] = new JButton(new ImageIcon("D:/n3.jpg"));
		nbt[4] = new JButton(new ImageIcon("D:/n4.jpg"));
		nbt[5] = new JButton(new ImageIcon("D:/n5.jpg"));
		nbt[6] = new JButton(new ImageIcon("D:/n6.jpg"));
		nbt[7] = new JButton(new ImageIcon("D:/n7.jpg"));
		nbt[8] = new JButton(new ImageIcon("D:/n8.jpg"));
		nbt[9] = new JButton(new ImageIcon("D:/n9.jpg"));
		
		for (int i = 0; i < nbt.length; i++) {
			nbt[i].setText(""+i);
			panel.add(nbt[i]);
			nbt[i].setForeground(Color.white);
			nbt[i].addActionListener(this);
			nbt[i].setMargin(new Insets(0,0,0,0));
			nbt[i].setBorderPainted(false);
			nbt[i].setBackground(Color.white);
		}
		
		for (int i = 0; i < underBt.length; i++) {
			underBt[i] = new JButton();
			frame.add(underBt[i]);
			underBt[i].setSize(45, 80);
			underBt[i].setLocation(110 + (i * 45), 390);
			underBt[i].addActionListener(this);
			underBt[i].setBackground(Color.white);
			underBt[i].setForeground(Color.blue);
			underBt[i].setMargin(new Insets(0,0,0,0));
			underBt[i].setBorderPainted(false);
			underBt[i].setFont(new Font("궁서", Font.ITALIC, 15));
		}
		// 아래쪽 4자리 숫자
		for (int i = 0; i < serverBt.length; i++) {
			frame.add(serverBt[i] = new JButton());
			serverBt[i].setSize(45, 70);
			serverBt[i].setLocation(30 + (i * 45), 30);
			serverBt[i].addActionListener(this);
			serverBt[i].setBackground(Color.white);
			serverBt[i].setForeground(Color.blue);
			serverBt[i].setMargin(new Insets(0,0,0,0));
			serverBt[i].setBorderPainted(false);
			serverBt[i].setFont(new Font("궁서", Font.ITALIC, 15));
			
		}
		// 왼쪽 4자리의 숫자

		for (int i = 0; i < clientBt.length; i++) {
			frame.add(clientBt[i] = new JButton());
			clientBt[i].setSize(45, 70);
			clientBt[i].setLocation(290 + (i * 45), 30);
			clientBt[i].addActionListener(this);
			clientBt[i].setBackground(Color.white);
			clientBt[i].setForeground(Color.red);
			clientBt[i].setMargin(new Insets(0,0,0,0));
			clientBt[i].setBorderPainted(false);
			clientBt[i].setFont(new Font("궁서", Font.ITALIC, 15));
		}
		// 오른쪽 4자리의 숫자
		
		pan.add(moveBt = new JButton("입력"));
		moveBt.setSize(60, 35);
		moveBt.setLocation(300, 435);
		moveBt.addActionListener(this);
		moveBt.setBackground(Color.white);
		moveBt.setForeground(Color.blue);
		moveBt.setFont(new Font("궁서", Font.ITALIC, 15));
		moveBt.setMargin(new Insets(0,0,0,0));
		moveBt.setBorderPainted(false);
		
		pan.add(deleteBt = new JButton("정정"));
		deleteBt.setSize(60, 35);
		deleteBt.setLocation(360, 435);
		deleteBt.addActionListener(this);
		deleteBt.setBackground(Color.white);
		deleteBt.setForeground(Color.blue);
		deleteBt.setFont(new Font("궁서", Font.ITALIC, 15));
		deleteBt.setMargin(new Insets(0,0,0,0));
		deleteBt.setBorderPainted(false);
		//입력, 정정 버튼

		bt.setEnabled(false);
		bt.setBackground(Color.white);
		bt.setMargin(new Insets(0,0,0,0));
		bt.setBorderPainted(false);
		
		panel1.add(text);
		panel1.add(text1);
		pan.add(bt,"North");
		pan.add(panel1,"Center");
		pan.add(panel, "South");

		text.setFont(new Font("궁서", Font.ITALIC, 20));
		text1.setFont(new Font("궁서", Font.ITALIC, 20));

		frame.setSize(525, 570);
		frame.setVisible(true);
		text.setEditable(false);
		text1.setEditable(false);
		frame.setResizable(false);
		
		frame.setLocation(230,60);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nbt.length; i++) {
			if (e.getSource() == nbt[i]) {
				if (numC == 0) {
					if (e.getSource() == nbt[0]) {
						JOptionPane.showMessageDialog(null, "0은 1의 자리에 넣을 수 없습니다!");
					} else {
						numC++;
						underBt[0].setText("" + i);
					}
				} else if (numC == 1) {
					if (nbt[i].getText().equals(underBt[0].getText())) {
						JOptionPane.showMessageDialog(null, "중복 되는 수를 넣을 수 없습니다!");
					} else {
						numC++;
						underBt[1].setText("" + i);
					}
				} else if (numC == 2) {
					if (nbt[i].getText().equals(underBt[0].getText())
							|| nbt[i].getText().equals(underBt[1].getText())) {
						JOptionPane.showMessageDialog(null, "중복 되는 수를 넣을 수 없습니다!");
					} else {
						numC++;
						underBt[2].setText("" + i);
					}
				} else if (numC == 3) {
					if (nbt[i].getText().equals(underBt[0].getText()) || nbt[i].getText().equals(underBt[1].getText())
							|| nbt[i].getText().equals(underBt[2].getText())) {
						JOptionPane.showMessageDialog(null, "중복 되는 수를 넣을 수 없습니다!");
					} else {
						numC++;
						underBt[3].setText("" + i);
					}
				}
			}
		}
		if (e.getSource() == moveBt) {
			//아래 버튼에 값을 다 입력하지 않고 입력했을 씨 경고메세지 출력
			if (underBt[0].getText() == null) {
				JOptionPane.showMessageDialog(null, "값을 넣어주세요!");
			} else if (underBt[1].getText() == null) {
				JOptionPane.showMessageDialog(null, "값을 넣어주세요!");
			} else if (underBt[2].getText() == null) {
				JOptionPane.showMessageDialog(null, "값을 넣어주세요!");
			} else if (underBt[3].getText() == null) {
				JOptionPane.showMessageDialog(null, "값을 넣어주세요!");
			} else if (btC == 0) {
				btC++;
					for ( int i = 0; i < serverBt.length; i++) {
						serverBt[i].setText("" + underBt[i].getText());
					}
					for (int i = 0; i < serverBt.length; i++) {
						ClientNu.serverBt[i].setText("*");
					}
					for (int i = 0; i < cNu.length; i++) {
						ClientNu.sNu[i] = "" + serverBt[i].getText();
					}
					for (int i = 0; i < underBt.length; i++) {
						underBt[i].setText(null);
						numC = 0;
					}
			} else if (btC == 1) {
					for (int i = 0; i < underBt.length; i++) {
						if (underBt[0].getText().equals(cNu[i])) {
							if (underBt[0].getText().equals(cNu[0])) {
								strike++;
							} else {
								ball++;
							}
						} else if (underBt[1].getText().equals(cNu[i])) {
							if (underBt[1].getText().equals(cNu[1])) {
								strike++;
							} else {
								ball++;
							}
						} else if (underBt[2].getText().equals(cNu[i])) {
							if (underBt[2].getText().equals(cNu[2])) {
								strike++;
							} else {
								ball++;
							}
						} else if (underBt[3].getText().equals(cNu[i])) {
							if (underBt[3].getText().equals(cNu[3])) {
								strike++;
							} else {
								ball++;
							}
						}
					}
					// 스트라이크, 볼 둘다 해당 사항이 없을 때
					if (strike == 0 && ball == 0) {
						text.append(underBt[0].getText() + underBt[1].getText() + underBt[2].getText()
								+ underBt[3].getText() + "\t" + "OUT" + "\n");
						ClientNu.text.append(underBt[0].getText() + underBt[1].getText() + underBt[2].getText()
								+ underBt[3].getText() + "\t" + "OUT" + "\n");
						for (int i = 0; i < underBt.length; i++) {
							underBt[i].setText(null);
							numC = 0;
						}
					// 정답일 때
					} else if (strike == 4){
						Server.serverS++;
						Client.serverS++;
						JOptionPane.showMessageDialog(null, "게임에서 승리하셨습니다!", "Server", 1);
						ClientPo.pane.showMessageDialog(null, "게임에서 패배하셨습니다.", "Client", 0);
						Server.serverScore.setText("Server님의 최종 스코어 : " + Server.serverS);
						Client.serverScore.setText("Server님의 최종 스코어 : " + Server.serverS);
						frame.setVisible(false);
						ClientNu.frame.setVisible(false);
						ClientWB.clO = 1;
						ServerWB.clO = 1;
						ClientWB.serO = 0;
						ServerWB.serO = 0;
						text.append(underBt[0].getText() + underBt[1].getText() + underBt[2].getText()
								+ underBt[3].getText() + "\t" + strike + "S " + "\n");
						ClientNu.text.append(underBt[0].getText() + underBt[1].getText() + underBt[2].getText()
								+ underBt[3].getText() + "\t" + strike + "S " + "\n");
						for (int i = 0; i < underBt.length; i++) {
							underBt[i].setText(null);
							numC = 0;
							strike = 0;
							ball = 0;
						}
					// 그 밖에
					} else {
						text.append(underBt[0].getText() + underBt[1].getText() + underBt[2].getText()
								+ underBt[3].getText() + "\t" + strike + "S " + ball + "B" + "\n");
						ClientNu.text.append(underBt[0].getText() + underBt[1].getText() + underBt[2].getText()
								+ underBt[3].getText() + "\t" + strike + "S " + ball + "B" + "\n");
						for (int i = 0; i < underBt.length; i++) {
							underBt[i].setText(null);
							numC = 0;
							strike = 0;
							ball = 0;
						}
					}
				} 
			} else if (e.getSource() == deleteBt) {
			if (underBt[0].getText() == null) {
				JOptionPane.showMessageDialog(null, "지울 값이 없습니다!");
			} else if (numC == 0) {
				JOptionPane.showMessageDialog(null, "지울 값이 없습니다!");
			} else {
				underBt[numC - 1].setText(null);
				numC--;
			}
		}
	}
}