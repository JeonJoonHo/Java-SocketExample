
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
	JButton bBt = new JButton("����");
	JButton gBt = new JButton("����");
	JButton oBt = new JButton("����");
	JButton aBt = new JButton("����"); 
	JButton eBt = new JButton("�� ������");
	static JButton sBt = new JButton("�����ϱ�");
	JButton img1 = new JButton(new ImageIcon("D:\1.jpg"));

	static int totalBat = 0;	//�� ���� Ĩ ��
	static int bNum = 0;		//���� ��
	static int bat = 0;			//���� Ĩ ��
	static int seC = 30;		//���� Ĩ ��
	static int clC = 30;		//Ŭ���̾�Ʈ Ĩ ��
	static JLabel lab1 = new JLabel("Server���� Ĩ �� : 30",JLabel.CENTER);
	static JLabel lab2 = new JLabel("Client���� Ĩ �� : 30",JLabel.CENTER);
	static JLabel lab3 = new JLabel("���� ���� �� Ĩ �� : 0",JLabel.CENTER);
	
	static JTextArea text = new JTextArea();
	JScrollPane scr = new JScrollPane(text);
	
	ImageIcon icon;
	ImageIcon icon1;
	JScrollPane scrollPane = new JScrollPane();

	static JLabel[] imgL = new JLabel[60];

	static Random random = new Random();
	static int[] ranN = new int[9];		//ī�� ��ȣ ���� ����
	static int[] ranN1 = new int[9];	//ī�� ��ȣ ���� ����
	static int ServerCard = 0;			//���� ī�� ��ȣ
	static int ClientCard = 0;			//Ŭ���̾�Ʈ ī�� ��ȣ
	static int serO = 0;				//����
	static int clO = 1;					//����
	
	String ba = "";
	
	static JOptionPane pane;
	public ServerPo(){
		icon = new ImageIcon("D:/10.png");
		icon1 = new ImageIcon("D:/chip.jpg");
		// �̹����� �ҷ���
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
		
		//�߾� �г� ����
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
		
		//���� �г� ����
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

		//������ �г� ����
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
			text.append("\nServer���� �غ� �Ϸ�Ǿ����ϴ�.");
			if (ClientPo.sBt.isEnabled() == false) {
				text.append("\n���� �����ϰڽ��ϴ�.");
				ClientPo.text.append("\n���� �����ϰڽ��ϴ�.");
				text.append("\nī�带 �帮�ڽ��ϴ�.");
				ClientPo.text.append("\nī�带 �帮�ڽ��ϴ�");
				Open();
				ClientPo.Open();
				//ī�带 ������ ���� 1�� �⺻ ����
				seC -= 1;
				ClientPo.seC -= 1;
				clC -= 1;
				ClientPo.clC -= 1;
				totalBat += 2;
				ClientPo.totalBat += 2;
				Coin thr = new Coin(2);
				Thread slab = new Thread(thr);
				slab.start();
				lab1.setText("Server���� Ĩ �� : " + seC);
				ClientPo.lab1.setText("Server���� Ĩ �� : " + seC);
				lab2.setText("Client���� Ĩ �� : " + clC);
				ClientPo.lab2.setText("Client���� Ĩ �� : " + clC);
				lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
				ClientPo.lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
			}
		} else if (e.getSource() == bBt) {
			if (serO == 0) {
				ba = JOptionPane.showInputDialog("���� �� Ĩ�� ������ ? ");
				bat = Integer.parseInt("" + ba);
				if (bat < ClientPo.bat) {
					JOptionPane.showMessageDialog(null, "�� ���ú��� ���� �� �� �����ϴ�! �ٽ� ������ �ּ���");
				// �� �÷��̾�� ���þ��� ���ų� Ŀ����
				} else if (bat >= ClientPo.bat) {
					text.append("\nServer���� "+bat+"���� ���� �ϼ̽��ϴ�.");
					ClientPo.text.append("\nServer���� "+bat+"���� ���� �ϼ̽��ϴ�.");
					seC -= bat;
					ClientPo.seC -= bat;
					totalBat += bat;
					ClientPo.totalBat += bat;
					lab1.setText("Server���� Ĩ �� : " + seC);
					ClientPo.lab1.setText("Server���� Ĩ �� : " + seC);
					lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
					ClientPo.lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
					Coin thr = new Coin(bat);
					Thread slab = new Thread(thr);
					slab.start();
					eBt.setEnabled(true);
					//�� �÷��̾�� ���þ��� ���� �ÿ��� ���¹�ư Ȱ��ȭ
					if(bNum > 1 && bat == ClientPo.bat){
						oBt.setEnabled(true);
					}
				} 
			} else {
				JOptionPane.showMessageDialog(null, "��� ���ʰ� �ƴմϴ�!");
			}
		} else if (e.getSource() == aBt) {
			if (bNum > 0) {
				oBt.setEnabled(true);
			}
			eBt.setEnabled(true);
			Coin thr = new Coin(seC);
			Thread slab = new Thread(thr);
			slab.start();
			text.append("\nServer���� ���� �ϼ̽��ϴ�.");
			ClientPo.text.append("\nServer���� ���� �ϼ̽��ϴ�.");
			totalBat += seC;
			ClientPo.totalBat += seC;
			seC = 0;
			ClientPo.seC = 0;
			lab1.setText("Server���� Ĩ �� : " + seC);
			ClientPo.lab1.setText("Server���� Ĩ �� : " + seC);
			lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
			ClientPo.lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
		} else if (e.getSource() == eBt){
			text.append("\nClient�� �����Դϴ�.");
			ClientPo.text.append("\nClient�� �����Դϴ�.");
			bNum += 1;
			ClientPo.bNum += 1;
			oBt.setEnabled(true);
			eBt.setEnabled(false);
			//���� ������ �ϱ�����  ������ �� �Է�(0�� �� �ڱ� ����)
			ClientPo.clO = 0;
			clO = 0;
			ClientPo.serO = 1;
			serO = 1;
		} else if (e.getSource() == oBt) {
			//������ ī�� �� �� Ŭ���̾�Ʈ ī�� ������ ���� ��
			text.append("\nī�带 �����ϰڽ��ϴ�.");
			ClientPo.text.append("\nī�带 �����ϰڽ��ϴ�.");
			if (ServerCard > ClientCard) {
				//Ŭ���̾�Ʈ�� ���� 0�� �Ǿ��� ��(������ �¸��� ��)
				if(clC <= 0){
					text.append("\nServer���� ���� �¸��ϼ̽��ϴ�.");
					ClientPo.text.append("\nServer���� ���� �¸��ϼ̽��ϴ�.");
					Server.serverS++;
					Client.serverS++;
					seC += totalBat;
					ClientPo.seC += totalBat;
					lab1.setText("Server���� Ĩ �� : " + seC);
					ClientPo.lab1.setText("Server���� Ĩ �� : " + seC);
					lab2.setText("Client���� Ĩ �� : 0");
					ClientPo.lab2.setText("Client���� Ĩ �� : 0");
					lab3.setText("���� ���� �� Ĩ �� : 0");
					ClientPo.lab3.setText("���� ���� �� Ĩ �� : 0");
					ClientPo.pane.showMessageDialog(null, "���ӿ��� �й��ϼ̽��ϴ�..", "Client", 0);
					JOptionPane.showMessageDialog(null, "���ӿ��� �¸��ϼ̽��ϴ�!", "Server", 1);
					Server.serverScore.setText("Server���� ���� ���ھ� : " + Server.serverS);
					Client.serverScore.setText("Server���� ���� ���ھ� : " + Server.serverS);
					Server.chA.append("\n Server���� ���� �¸��ϼ̽��ϴ�.");
					Client.chA.append("\n Server���� ���� �¸��ϼ̽��ϴ�.");
					frame.setVisible(false);
					ClientPo.frame.setVisible(false);
				//���� �º� ����� ���� ���� ��
				} else {
					text.append("\nServer���� �¸��ϼ̽��ϴ�.\n" + totalBat +"���� Server�Բ��� �����̽��ϴ�." );
					ClientPo.text.append("\nServer���� �¸��ϼ̽��ϴ�."+ totalBat +"���� Server�Բ��� �����̽��ϴ�.");
					text.append("\nServer�� �����Դϴ�.");
					ClientPo.text.append("\nServer�� �����Դϴ�.");
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
					lab1.setText("Server���� Ĩ �� : " + seC);
					ClientPo.lab1.setText("Server���� Ĩ �� : " + seC);
					lab2.setText("Client���� Ĩ �� : " + clC);
					ClientPo.lab2.setText("Client���� Ĩ �� : " + clC);
					lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
					ClientPo.lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
					ClientPo.clO = 1;
					clO = 1;
					ClientPo.serO = 0;
					serO = 0;
				}
			// Ŭ���̾�Ʈ�� ī�� ���� �� ���� ��
			} else if (ServerCard < ClientCard) {
				//���� ���ھ 0�� �Ǿ��� ��(Ŭ���̾�Ʈ�� �̰��� ��)
				if (seC <= 0) {
					text.append("\nClient���� ���� �¸��ϼ̽��ϴ�.");
					ClientPo.text.append("\nClient���� ���� �¸��ϼ̽��ϴ�.");
					Client.clientS++;
					Server.serverS++;
					clC += totalBat;
					ClientPo.clC += totalBat;
					lab1.setText("Server���� Ĩ �� : 0");
					ClientPo.lab1.setText("Server���� Ĩ �� : 0");
					lab2.setText("Client���� Ĩ �� : " + clC);
					ClientPo.lab2.setText("Client���� Ĩ �� : " + clC);
					lab3.setText("���� ���� �� Ĩ �� : 0");
					ClientPo.lab3.setText("���� ���� �� Ĩ �� : 0");
					ClientPo.pane.showMessageDialog(null, "���ӿ��� �¸��ϼ̽��ϴ�!", "Client", 1);
					JOptionPane.showMessageDialog(null, "���ӿ��� �й��ϼ̽��ϴ�..", "Server", 0);
					Server.clientScore.setText("Client���� ���� ���ھ� : " + Client.clientS);
					Client.clientScore.setText("Client���� ���� ���ھ� : " + Client.clientS);
					Server.chA.append("\n Client���� ���� �¸��ϼ̽��ϴ�.");
					Client.chA.append("\n Client���� ���� �¸��ϼ̽��ϴ�.");
					frame.setVisible(false);
					ClientPo.frame.setVisible(false);
				//�º��� ����� ���� ���� ��
				} else {
					text.append("\nClient���� �¸��ϼ̽��ϴ�.\n" + totalBat +"���� Client�Բ��� �����̽��ϴ�." );
					ClientPo.text.append("\nClient���� �¸��ϼ̽��ϴ�."+ totalBat +"���� Client�Բ��� �����̽��ϴ�.");
					text.append("\nClient�� �����Դϴ�.");
					ClientPo.text.append("\nClient�� �����Դϴ�.");
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
					lab1.setText("Server���� Ĩ �� : " + seC);
					ClientPo.lab1.setText("Server���� Ĩ �� : " + seC);
					lab2.setText("Client���� Ĩ �� : " + clC);
					ClientPo.lab2.setText("Client���� Ĩ �� : " + clC);
					lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
					ClientPo.lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
					ClientPo.clO = 0;
					clO = 0;
					ClientPo.serO = 1;
					serO = 1;
				}
			//���º� �� �� �� ���
			} else {
				removeCoin();
				ClientPo.removeCoin();
			}
			text.append("\nī�带 �����帮�ڽ��ϴ�." );
			ClientPo.text.append("\nī�带 �����帮�ڽ��ϴ�.");
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
			text.append("\nServer���� ���ӿ��� �����ϼ̽��ϴ�." );
			ClientPo.text.append("\nServer���� ���ӿ��� �����ϼ̽��ϴ�.");
			text.append("\nClient���� �¸��ϼ̽��ϴ�.\n" + totalBat +"���� Client�Բ��� �����̽��ϴ�." );
			ClientPo.text.append("\nClient���� �¸��ϼ̽��ϴ�.\n"+ totalBat +"���� Client�Բ��� �����̽��ϴ�.");
			text.append("\nī�带 �����帮�ڽ��ϴ�." );
			ClientPo.text.append("\nī�带 �����帮�ڽ��ϴ�.");
			totalBat = 2;
			ClientPo.totalBat = 2;
			seC -= 1;
			ClientPo.seC -= 1;
			clC -= 1;
			ClientPo.clC -= 1;
			lab1.setText("Server���� Ĩ �� : " + seC);
			ClientPo.lab1.setText("Server���� Ĩ �� : " + seC);
			lab2.setText("Client���� Ĩ �� : " + clC);
			ClientPo.lab2.setText("Client���� Ĩ �� : " + clC);
			lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
			ClientPo.lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
			oBt.setEnabled(false);
			eBt.setEnabled(false);
		}
		
	}
	
	/**
	 * ī�带 �����ϴ� method
	 */
	public static void Open(){
		ServerCard = random.nextInt(9) + 1;
		ClientCard = ClientPo.ClientCard;
		ClientPo.imgB.setIcon(new ImageIcon("D:/c" + ServerCard + ".jpg"));
		ClientPo.imgB1.setIcon(new ImageIcon("D:/back.jpg"));
	}
	
	/**
	 * ���� ������ �г� �ȿ� ����Ĩ�� ������ �׼��� method
	 * @param lab JLabel �� , CoinŬ�������� ��ġ �� �̹����� ������ JLabel�� �޾ƿ�
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
	 * �г� �ȿ� ����Ĩ�� ����� method
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
