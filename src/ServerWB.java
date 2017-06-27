
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

	static int serS = 0; // ���� ����
	static int clS = 0; // Ŭ���̾�Ʈ ����

	static JLabel lab = new JLabel("Server ���� : " + serS);
	static JLabel lab1 = new JLabel("Client ���� : " + clS);

	JButton[] topBt = new JButton[9];
	static JButton[] underBt = new JButton[9];
	static JButton serBt = new JButton();
	static JButton clBt = new JButton();
	JButton inBt = new JButton("�Է�");
	static JButton stBt = new JButton("�����ϱ�");

	static int serO = 0; // ���� ����
	static int clO = 0; // Ŭ���̾�Ʈ ����
	private int nBt = 0;
	static int ser = 0; // ������ư ��
	static int cl = 0; // Ŭ���̾�Ʈ��ư ��

	ImageIcon icon;
	ImageIcon icon1;
	JScrollPane scrollPane = new JScrollPane();

	Random random = new Random();
	static int[] ranN = new int[9];		//Ÿ���� ���� �������� �����ϱ� ���� ���� ��������

	static JOptionPane pane;

	public ServerWB() {
		icon = new ImageIcon("D:/09.jpg");
		// �̹����� �ҷ���
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

		//ī���� ���� �����ϰ� �ֱ� ���� 
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

		// ServerŸ�� ���� ũ�� ����
		for (int i = 0; i < topBt.length; i++) {
			topP.add(topBt[i] = new JButton());
			topBt[i].addActionListener(this);
			topBt[i].setBackground(Color.white);
			topBt[i].setMargin(new Insets(0, 0, 0, 0));
			topBt[i].setBorderPainted(false);
		}

		// ClientŸ�� ���� ũ�� ����
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
		// �̹����� �ҷ���
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
		inBt.setFont(new Font("�ü�", Font.ITALIC, 14));

		stBt.setSize(100, 40);
		stBt.setLocation(160, 270);
		stBt.addActionListener(this);
		stBt.setBackground(Color.white);
		stBt.setForeground(Color.gray);
		stBt.setMargin(new Insets(0, 0, 0, 0));
		stBt.setBorderPainted(false);
		stBt.setFont(new Font("�ü�", Font.ITALIC, 18));

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

		text.setText("                          ��  Ȳ  �� ");

		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setLocation(230,60);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ���, ��� Ÿ���� ��
		if (e.getSource() == stBt) {
			stBt.setEnabled(false);
			ClientWB.text.append("\nServer ���� �غ� �Ϸ�Ǿ����ϴ�.");
			if (ClientWB.stBt.isEnabled() == false) {
				text.append("\n������ �����ϰڽ��ϴ�.");
				ClientWB.text.append("\n������ �����ϰڽ��ϴ�.");
			}
			//Server Ÿ�Ͽ� ���� ���� ������
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
			//Client Ÿ�Ͽ� ���� ����
			for (int i = 0; i < topBt.length; i++) {
				if (ranN[i] == 0 || ranN[i] == 2 || ranN[i] == 4 || ranN[i] == 6 || ranN[i] == 8) {
					ClientWB.topBt[i].setBackground(Color.black);
					ClientWB.topBt[i].setForeground(Color.white);
				} else {
					ClientWB.topBt[i].setBackground(Color.white);
				}
			}
		} else {
			//serO�� ���� �� ��
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
				JOptionPane.showMessageDialog(null, "��� ���ʰ� �ƴմϴ�!");
			}
			// �� �Է� ��ư
			if (e.getSource() == inBt) {
				serO = 1;
				ClientWB.clO = 0;
				//��ư Ŭ�� ���� �� �̺�Ʈ ����(�ؽ�Ʈ â�� �÷��̾��� �ൿ �Է�)
				if (ser == 0 || ser == 2 || ser == 4 || ser == 6 || ser == 8) {
					ClientWB.serBt.setBackground(Color.black);
					ClientWB.serBt.setForeground(Color.white);
					ClientWB.serBt.setEnabled(true);
					ClientWB.text.append("\nServer���� ������ Ÿ���� �����ϼ̽��ϴ�.");
					text.append("\nServer���� ������ Ÿ���� �����ϼ̽��ϴ�.");
					if (serO == 0) {
						ClientWB.text.append("\nClient�� �����Դϴ�.");
						text.append("\nClient�� �����Դϴ�.");
					}
				} else {
					ClientWB.serBt.setBackground(Color.white);
					ClientWB.serBt.setEnabled(true);
					ClientWB.text.append("\nServer���� ��� Ÿ���� �����ϼ̽��ϴ�.");
					text.append("\nServer���� ��� Ÿ���� �����ϼ̽��ϴ�.");
					if (serO == 0) {
						ClientWB.text.append("\nClient�� �����Դϴ�.");
						text.append("\nClient�� �����Դϴ�.");
					}
				}
				//�� �÷��̾ �� �Է����� �� �÷��̾� ���� �� ��
				if (serBt.isEnabled() == true && clBt.isEnabled() == true) {
					//Server�� ���� �� Ŭ ���
					if (ser > ClientWB.cl) {
						serS++;
						text.append("\nServer���� �¸��ϼ̽��ϴ�.");
						lab.setText("Server ���� : " + serS);
						ClientWB.serS++;
						ClientWB.text.append("\nServer���� �¸��ϼ̽��ϴ�.");
						ClientWB.lab.setText("Server ���� : " + serS);
						//Server�� ���� 5�� ������ ���� �¸�
						if (serS > 4) {
							JOptionPane.showMessageDialog(null, "���ӿ��� �����¸��ϼ̽��ϴ�!", "Server", 1);
							ClientWB.pane.showMessageDialog(null, "���ӿ��� �й��ϼ̽��ϴ�..", "Client", 0);
							Server.serverS++;
							Client.serverS++;
							Server.serverScore.setText("Server���� ���� ���ھ� : " + Server.serverS);
							Client.serverScore.setText("Server���� ���� ���ھ� : " + Server.serverS);
							ClientPo.clO = 1;
							ServerPo.clO = 1;
							ClientPo.serO = 0;
							ServerPo.serO = 0;
							frame.setVisible(false);
							ClientWB.frame.setVisible(false);
						//5�� ���� ���� ��� ��� ����
						} else {
							text.append("\nServer�� �����Դϴ�.");
							ClientWB.text.append("\nServer�� �����Դϴ�.");
							serO = 0;
							ClientWB.clO = 1;
						}
					//Client�� ���� �� Ŭ���
					} else if (ser < ClientWB.cl) {
						clS++;
						text.append("\nClient���� �¸��ϼ̽��ϴ�.");
						lab1.setText("Client ���� : " + clS);
						ClientWB.clS++;
						ClientWB.text.append("\nClient���� �¸��ϼ̽��ϴ�.");
						ClientWB.lab1.setText("Client ���� : " + clS);
					//Client�� ���ھ 5�� ���� ��� �����¸�
						if (clS > 4) {
							JOptionPane.showMessageDialog(null, "Client���� �����¸��ϼ̽��ϴ�!", "Client", 1);
							ServerWB.pane.showMessageDialog(null, "���ӿ��� �й��ϼ̽��ϴ�..", "Server", 0);
							Client.clientS++;
							Server.clientS++;
							Server.clientScore.setText("Client���� ���� ���ھ� : " + Client.clientS);
							Client.clientScore.setText("Client���� ���� ���ھ� : " + Client.clientS);
							ClientPo.clO = 0;
							ServerPo.clO = 0;
							ClientPo.serO = 1;
							ServerPo.serO = 1;
							frame.setVisible(false);
							ClientWB.frame.setVisible(false);
						//�� ���� ��� ��� ����
						} else {
							serO = 1;
							ClientWB.clO = 0;
							text.append("\nClient�� �����Դϴ�.");
							ClientWB.text.append("\nClient�� �����Դϴ�.");
						}
					// ����� ��
					} else if (ser == ClientWB.cl) {
						serS++;
						clS++;
						lab.setText("Server ���� : " + serS);
						lab1.setText("Client ���� : " + clS);
						ClientWB.serS++;
						ClientWB.clS++;
						ClientWB.lab.setText("Server ���� : " + serS);
						ClientWB.lab1.setText("Client ���� : " + clS);
					// Server�� Client�� ���ھ 5�� �Ѿ��� �� �����¸�
						if (serS > 4 ) {
							JOptionPane.showMessageDialog(null, "���ӿ��� �����¸��ϼ̽��ϴ�!", "Server", 1);
							ClientWB.pane.showMessageDialog(null, "���ӿ��� �й��ϼ̽��ϴ�..", "Client", 0);
							Server.serverS++;
							Client.serverS++;
							Server.serverScore.setText("Server���� ���� ���ھ� : " + Server.serverS);
							Client.serverScore.setText("Server���� ���� ���ھ� : " + Server.serverS);
							ClientPo.clO = 1;
							ServerPo.clO = 1;
							ClientPo.serO = 0;
							ServerPo.serO = 0;
							frame.setVisible(false);
							ClientWB.frame.setVisible(false);
					// �Ѵ� �ȳѾ��� �� ��� ����
						} else if (clS > 4){
							JOptionPane.showMessageDialog(null, "Client���� �����¸��ϼ̽��ϴ�!", "Client", 1);
							ServerWB.pane.showMessageDialog(null, "���ӿ��� �й��ϼ̽��ϴ�..", "Server", 0);
							Client.clientS++;
							Server.clientS++;
							Server.clientScore.setText("Client���� ���� ���ھ� : " + Client.clientS);
							Client.clientScore.setText("Client���� ���� ���ھ� : " + Client.clientS);
							ClientPo.clO = 0;
							ServerPo.clO = 0;
							ClientPo.serO = 1;
							ServerPo.serO = 1;
							frame.setVisible(false);
							ClientWB.frame.setVisible(false);
						} else {
							if (serO == 0) {
								text.append("\n���º��Դϴ�.");
								text.append("\n���º� �Ͽ����Ƿ� ���� �¸��Ͻ�");
								text.append("\nServer�� �����Դϴ�.");
								ClientWB.text.append("\n���º��Դϴ�.");
								ClientWB.text.append("\n���º� �Ͽ����Ƿ� ���� �¸��Ͻ�");
								ClientWB.text.append("\nServer�� �����Դϴ�.");
								serO = 0;
								ClientWB.clO = 1;
							} else if (ClientWB.clO == 0) {
								text.append("\n���º��Դϴ�.");
								text.append("\n���º� �Ͽ����Ƿ� ���� �¸��Ͻ�");
								text.append("\nClient�� �����Դϴ�.");
								ClientWB.text.append("\n���º��Դϴ�.");
								ClientWB.text.append("\n���º� �Ͽ����Ƿ� ���� �¸��Ͻ�");
								ClientWB.text.append("\nClient�� �����Դϴ�.");
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
