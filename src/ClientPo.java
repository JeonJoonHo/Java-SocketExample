
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

public class ClientPo implements ActionListener {
	static JFrame frame = new JFrame("Client");
	
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
	static int ServerCard = 0;			//���� ī�� ��ȣ
	static int ClientCard = 0;			//Ŭ���̾�Ʈ ī�� ��ȣ
	static int[] ranN = new int[9];		//ī�� ��ȣ ���� ����
	static int[] ranN1 = new int[9];	//ī�� ��ȣ ���� ����
	static int serO = 0;				//����
	static int clO = 1;					//����
	
	String ba = "";
	
	static JOptionPane pane;
	
	public ClientPo(){
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
		eBt.setSize(150, 150);
		eBt.setLocation(30, 50);
		eBt.setBackground(Color.white);
		eBt.setMargin(new Insets(0,0,0,0));
		eBt.setBorderPainted(false);
		eBt.addActionListener(this);
		leP.add(eBt);
		lefP.add(imgB,"North");
		lefP.add(lab1, "Center");
		lefP.add(leP, "South");

		//������ �г� ����
		riP.setPreferredSize(new Dimension(0,150));
		imgB1.setPreferredSize(new Dimension(0,250));
		imgB1.setBackground(Color.white);
		imgB1.setMargin(new Insets(0,0,0,0));
		imgB1.setBorderPainted(false);
		bBt.setSize(60, 40);
		oBt.setSize(60, 40);
		gBt.setSize(60, 40);
		aBt.setSize(60, 40);
		bBt.setLocation(30, 30);
		aBt.setLocation(120, 30);
		oBt.setLocation(30, 80);
		gBt.setLocation(120, 80);
		bBt.setBackground(Color.white);
		aBt.setBackground(Color.white);
		oBt.setBackground(Color.white);
		gBt.setBackground(Color.white);
		bBt.setMargin(new Insets(0,0,0,0));
		aBt.setMargin(new Insets(0,0,0,0));
		oBt.setMargin(new Insets(0,0,0,0));
		gBt.setMargin(new Insets(0,0,0,0));
		bBt.setBorderPainted(false);
		aBt.setBorderPainted(false);
		oBt.setBorderPainted(false);
		gBt.setBorderPainted(false);
		bBt.addActionListener(this);
		aBt.addActionListener(this);
		oBt.addActionListener(this);
		gBt.addActionListener(this);
		riP.add(bBt);
		riP.add(aBt);
		riP.add(oBt);
		riP.add(gBt);
		rigP.add(imgB1,"North");
		rigP.add(lab2, "Center");
		rigP.add(riP, "South");
		eBt.setEnabled(false);
		oBt.setEnabled(false);

		frame.setSize(750, 500);
		frame.setLocation(770,300);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sBt ){
			sBt.setEnabled(false);
			text.append("\nClient���� �غ� �Ϸ�Ǿ����ϴ�.");
			if (ServerPo.sBt.isEnabled() == false) {
				text.append("\n���� �����ϰڽ��ϴ�.");
				ServerPo.text.append("\n���� �����ϰڽ��ϴ�.");
				text.append("\nī�带 �帮�ڽ��ϴ�.");
				ServerPo.text.append("\nī�带 �帮�ڽ��ϴ�");
				Open();
				ServerPo.Open();
				clC -= 1;
				ServerPo.clC -= 1;
				seC -= 1;
				ServerPo.seC -= 1;
				totalBat += 2;
				ServerPo.totalBat += 2;
				Coin thr = new Coin(2);
				Thread slab = new Thread(thr);
				slab.start();
				lab1.setText("Server���� Ĩ �� : " + seC);
				ServerPo.lab1.setText("Server���� Ĩ �� : " + seC);
				lab2.setText("Client���� Ĩ �� : " + clC);
				ServerPo.lab2.setText("Client���� Ĩ �� : " + clC);
				lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
				ServerPo.lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
			}
		} else if (e.getSource() == bBt) {
			if (clO == 0) {
				ba = JOptionPane.showInputDialog("���� �� Ĩ�� ������ ? ");
				bat = Integer.parseInt("" + ba);
				if (bat < ServerPo.bat) {
					JOptionPane.showMessageDialog(null, "�� ���ú��� ���� �� �� �����ϴ�! �ٽ� �������ּ���.");
				} else if (bat >= ServerPo.bat) {
					text.append("\nClient���� "+bat+"���� ���� �ϼ̽��ϴ�.");
					ServerPo.text.append("\nClient���� "+bat+"���� ���� �ϼ̽��ϴ�.");
					clC -= bat;
					ServerPo.clC -= bat;
					totalBat += bat;
					ServerPo.totalBat += bat;
					lab2.setText("Client���� Ĩ �� : " + clC);
					ServerPo.lab2.setText("Client���� Ĩ �� : " + clC);
					lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
					ServerPo.lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
					Coin thr = new Coin(bat);
					Thread slab = new Thread(thr);
					slab.start();
					eBt.setEnabled(true);
					if(bNum > 1 && bat == ServerPo.bat){
					oBt.setEnabled(true);
					}
				} 
			} else {
				JOptionPane.showMessageDialog(null, "��� ���ʰ� �ƴմϴ�!");
			}
		} else if (e.getSource() == eBt){
			text.append("\nServer�� �����Դϴ�.");
			ServerPo.text.append("\nServer�� �����Դϴ�.");
			bNum += 1;
			ServerPo.bNum += 1;
			oBt.setEnabled(false);
			eBt.setEnabled(false);
			ServerPo.clO = 1;
			clO = 1;
			ServerPo.serO = 0;
			serO = 0;
		} else if (e.getSource() == aBt) {
			if (bNum > 0) {
				oBt.setEnabled(true);
			}
			oBt.setEnabled(true);
			eBt.setEnabled(true);
			text.append("\nClient���� ���� �ϼ̽��ϴ�.");
			ServerPo.text.append("\nClient���� ���� �ϼ̽��ϴ�.");
			Coin thr = new Coin(clC);
			Thread slab = new Thread(thr);
			slab.start();
			totalBat += clC;
			ServerPo.totalBat += clC;
			clC = 0;
			ServerPo.clC = 0;
			lab2.setText("Client���� Ĩ �� : " + clC);
			ServerPo.lab2.setText("Client���� Ĩ �� : " + clC);
			lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
			ServerPo.lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
		} else if (e.getSource() == oBt) {
			if (ServerPo.ServerCard > ClientCard) {
				if (clC <= 0) {
					text.append("\nServer���� ���� �¸��ϼ̽��ϴ�.");
					ServerPo.text.append("\nServer���� ���� �¸��ϼ̽��ϴ�.");
					Server.serverS++;
					Client.serverS++;
					seC += totalBat;
					ServerPo.seC += totalBat;
					lab1.setText("Server���� Ĩ �� : " + seC);
					ServerPo.lab1.setText("Server���� Ĩ �� : " + seC);
					lab2.setText("Client���� Ĩ �� : 0");
					ServerPo.lab2.setText("Client���� Ĩ �� : 0");
					lab3.setText("���� ���� �� Ĩ �� : 0");
					ServerPo.lab3.setText("���� ���� �� Ĩ �� : 0");
					JOptionPane.showMessageDialog(null, "���ӿ��� �й��ϼ̽��ϴ�..", "Client", 0);
					ServerPo.pane.showMessageDialog(null, "���ӿ��� �¸��ϼ̽��ϴ�.", "Server", 1);
					Server.serverScore.setText("Server���� ���� ���ھ� : " + Server.serverS);
					Client.serverScore.setText("Server���� ���� ���ھ� : " + Server.serverS);
					Server.chA.append("\n Server���� ���� �¸��ϼ̽��ϴ�.");
					Client.chA.append("\n Server���� ���� �¸��ϼ̽��ϴ�.");
					frame.setVisible(false);
					ServerPo.frame.setVisible(false);
				} else {
					text.append("\nServer���� �¸��ϼ̽��ϴ�.\n" + totalBat +"���� Server�Բ��� �����̽��ϴ�." );
					ServerPo.text.append("\nServer���� �¸��ϼ̽��ϴ�."+ totalBat +"���� Server�Բ��� �����̽��ϴ�.");
					text.append("\nServer�� �����Դϴ�.");
					ServerPo.text.append("\nServer�� �����Դϴ�.");
					removeCoin();
					Coin thr = new Coin(2);
					Thread slab = new Thread(thr);
					slab.start();
					seC += totalBat;
					ServerPo.seC += totalBat;
					totalBat = 2;
					ServerPo.totalBat = 2;
					clC -= 1;
					ServerPo.clC -= 1;
					seC -= 1;
					ServerPo.seC -= 1;
					lab1.setText("Server���� Ĩ �� : " + seC);
					ServerPo.lab1.setText("Server���� Ĩ �� : " + seC);
					lab2.setText("Client���� Ĩ �� : " + clC);
					ServerPo.lab2.setText("Client���� Ĩ �� : " + clC);
					lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
					ServerPo.lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
					ServerPo.serO = 0;
					serO = 0;
					ServerPo.clO = 1;
					serO = 1;
				}
			} else if (ServerPo.ServerCard < ClientCard) {
				if (seC <= 0) {
					text.append("\nClient���� ���� �¸��ϼ̽��ϴ�.");
					ServerPo.text.append("\nClient���� ���� �¸��ϼ̽��ϴ�.");
					Client.clientS++;
					Server.clientS++;
					clC += totalBat;
					ServerPo.clC += totalBat;
					lab1.setText("Server���� Ĩ �� : 0");
					ServerPo.lab1.setText("Server���� Ĩ �� : 0");
					lab2.setText("Client���� Ĩ �� : " + clC);
					ServerPo.lab2.setText("Client���� Ĩ �� : " + clC);
					lab3.setText("���� ���� �� Ĩ �� : 0");
					ServerPo.lab3.setText("���� ���� �� Ĩ �� : 0");
					JOptionPane.showMessageDialog(null, "���ӿ��� �¸��ϼ̽��ϴ�!", "Client", 1);
					ServerPo.pane.showMessageDialog(null, "���ӿ��� �й��ϼ̽��ϴ�.", "Server", 0);
					Server.clientScore.setText("Client���� ���� ���ھ� : " + Client.clientS);
					Client.clientScore.setText("Client���� ���� ���ھ� : " + Client.clientS);
					Server.chA.append("\n Client���� ���� �¸��ϼ̽��ϴ�.");
					Client.chA.append("\n Client���� ���� �¸��ϼ̽��ϴ�.");
					frame.setVisible(false);
					ServerPo.frame.setVisible(false);
				} else {
					text.append("\nServer���� �¸��ϼ̽��ϴ�.\n" + totalBat +"���� Client�Բ��� �����̽��ϴ�." );
					ServerPo.text.append("\nServer���� �¸��ϼ̽��ϴ�."+ totalBat +"���� Client�Բ��� �����̽��ϴ�.");
					text.append("\nClient�� �����Դϴ�.");
					ServerPo.text.append("\nClient�� �����Դϴ�.");
					Coin thr = new Coin(2);
					Thread slab = new Thread(thr);
					slab.start();
					removeCoin();
					clC += totalBat;
					ServerPo.clC += totalBat;
					totalBat = 2;
					ServerPo.totalBat = 2;
					clC -= 1;
					ServerPo.clC -= 1;
					seC -= 1;
					ServerPo.seC -= 1;
					lab1.setText("Server���� Ĩ �� : " + seC);
					ServerPo.lab1.setText("Server���� Ĩ �� : " + seC);
					lab2.setText("Client���� Ĩ �� : " + clC);
					ServerPo.lab2.setText("Client���� Ĩ �� : " + clC);
					lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
					ServerPo.lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
					ServerPo.serO = 1;
					serO = 1;
					ServerPo.clO = 0;
					serO = 0;
				}
			} else {
				removeCoin();
				ServerPo.removeCoin();
			}
			text.append("\nī�带 �����帮�ڽ��ϴ�." );
			ServerPo.text.append("\nī�带 �����帮�ڽ��ϴ�.");
			Open();
			ServerPo.Open();
			oBt.setEnabled(false);
			eBt.setEnabled(false);
			bNum = 0;
			ServerPo.bNum = 0;
			bat = 0;
			ServerPo.bat = 0;
			totalBat = 0;
			ServerPo.totalBat = 0;
		} else if (e.getSource() == gBt) {
			Open();
			ServerPo.Open();
			ServerPo.clO = 1;
			clO = 1;
			ServerPo.serO = 0;
			serO = 0;
			Coin thr = new Coin(2);
			Thread slab = new Thread(thr);
			slab.start();
			removeCoin();
			if(ClientCard == 10){
				seC += 7;
				ServerPo.seC += 7;
				clC -= 7;
				ServerPo.clC -= 7;
			}
			seC += totalBat;
			ServerPo.seC += totalBat;
			text.append("\nClient���� ���ӿ��� �����ϼ̽��ϴ�." );
			ServerPo.text.append("\nClient���� ���ӿ��� �����ϼ̽��ϴ�.");
			text.append("\nServer���� �¸��ϼ̽��ϴ�.\n" + totalBat +"���� Server�Բ��� �����̽��ϴ�." );
			ServerPo.text.append("\nServer���� �¸��ϼ̽��ϴ�.\n"+ totalBat +"���� Server�Բ��� �����̽��ϴ�.");
			text.append("\nī�带 �����帮�ڽ��ϴ�." );
			ServerPo.text.append("\nī�带 �����帮�ڽ��ϴ�.");
			totalBat = 2;
			ServerPo.totalBat = 2;
			clC -= 1;
			ServerPo.clC -= 1;
			seC -= 1;
			ServerPo.seC -= 1;
			lab1.setText("Server���� Ĩ �� : " + seC);
			ServerPo.lab1.setText("Server���� Ĩ �� : " + seC);
			lab2.setText("Client���� Ĩ �� : " + clC);
			ServerPo.lab2.setText("Client���� Ĩ �� : " + clC);
			lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
			ServerPo.lab3.setText("���� ���� �� Ĩ �� : " + totalBat);
			oBt.setEnabled(false);
			eBt.setEnabled(false);
			bNum = 0;
			ServerPo.bNum = 0;
		}
	}

	public static void Open(){
		ServerCard = ServerPo.ServerCard;
		ClientCard = random.nextInt(9) + 1;
		ServerPo.imgB.setIcon(new ImageIcon("D:/back.jpg"));
		ServerPo.imgB1.setIcon(new ImageIcon("D:/c"+ ClientCard +".jpg"));
	}
	
	public static void Bat(JLabel[] lab) {
		for (int i = 0; i < lab.length; i++) {
			ceP.add(lab[i]);
			ceP.revalidate();
			ceP.repaint();
			ServerPo.ceP.add(lab[i]);
			ServerPo.ceP.revalidate();
			ServerPo.ceP.repaint();
		}
	}
	
	
	public static void removeCoin() {
		ceP.removeAll();
		ceP.repaint();
		sBt.setSize(100, 40);
		sBt.setLocation(105, 200);
		ceP.add(sBt);
		ServerPo.ceP.removeAll();
		ServerPo.ceP.repaint();
		ServerPo.sBt.setSize(100, 40);
		ServerPo.sBt.setLocation(105, 200);
		ServerPo.ceP.add(sBt);
		
	}
}