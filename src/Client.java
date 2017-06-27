

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Client extends Thread implements ActionListener {
	static String serverIP;
	static int port;
	public static DataInputStream in;
	public static DataOutputStream out;
	public static Socket socket;
	static String msg;

	JFrame frame = new JFrame("Client");
	
	JLabel label = new JLabel("게임을 선택해주세요.",JLabel.CENTER);
	JLabel name = new JLabel(new ImageIcon("D:/name.jpg"), JLabel.CENTER);
	static JLabel serverScore = new JLabel();
	static JLabel clientScore = new JLabel();
	static int serverS = 0;
	static int clientS = 0;
	
	JPanel chP = new JPanel();
	JPanel leftP = new JPanel();
	JPanel teP = new JPanel();
	JPanel gaP = new JPanel();

	JButton numBt = new JButton(new ImageIcon("D:/base.jpg"));
	JButton bwBt = new JButton(new ImageIcon("D:/white.jpg"));
	JButton poBt = new JButton(new ImageIcon("D:/poker.jpg"));
	
	static JTextArea chA = new JTextArea();
	static JTextField chF = new JTextField(10);
	JScrollPane scroll = new JScrollPane(chA);
	JTextPane textp = new JTextPane();
	
	JPanel panel = new JPanel();
	
	static Random random = new Random();
	
	static int dice = 0;
	public Client(String serverName, int port){
		frame.setLayout(new BorderLayout());
		
		leftP.setLayout(new GridLayout(2,1));
		label.setFont(new Font("Gulim",Font.BOLD,30));
		label.setForeground(Color.black);
		serverScore.setFont(new Font("궁서",Font.ITALIC,15));
		clientScore.setFont(new Font("궁서",Font.ITALIC,15));
		label.setFont(new Font("궁서",Font.ITALIC,24));
		teP.setLayout(new BorderLayout());
		teP.setBackground(Color.white);
		name.setPreferredSize(new Dimension(0,140));
		serverScore.setText("Server님의 최종 스코어 : " + serverS);
		clientScore.setText("Client님의 최종 스코어 : " + clientS);
		teP.add(name, "North");
		teP.add(serverScore, "West");
		teP.add(clientScore, "East");
		teP.add(label,"South");
		leftP.add(teP);

		gaP.setLayout(new GridLayout(1,3));
		numBt.setBackground(Color.black);
		bwBt.setBackground(Color.black);
		poBt.setBackground(Color.black);
		numBt.setMargin(new Insets(0,0,0,0));
		bwBt.setMargin(new Insets(0,0,0,0));
		poBt.setMargin(new Insets(0,0,0,0));
		numBt.setBorderPainted(false);
		bwBt.setBorderPainted(false);
		poBt.setBorderPainted(false);
		numBt.addActionListener(this);
		bwBt.addActionListener(this);
		poBt.addActionListener(this);
		gaP.add(numBt);
		gaP.add(bwBt);
		gaP.add(poBt);
		leftP.add(gaP);
		
		chP.setLayout(new BorderLayout());
		chP.setPreferredSize(new Dimension(170,200));
		chP.add(chF, "South");
		chP.add(scroll);
		
		frame.add(leftP);
		frame.add(chP, "West");
		
		chF.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						String msg = chF.getText();
						
						if ("".equals(msg)) {
							return;
						}
						chA.append("Send : " + msg + "\n");
						chF.setText("");

						if (Client.out != null) {
							try {
								Client.out.writeUTF("Receive : " + msg + "\n");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}if(msg.equals("/dice") ){
							dice = random.nextInt(10);
							Server.dice = Server.random.nextInt(10);
							chA.append("\nSend : Client님이 주사위를" + "\n" + "             굴리셨습니다.\n");
							chA.append("===========결과=========\n");
							chA.append("Server : " + dice + "    Client : " + Server.dice + "\n");
							Server.chA.append("\nSend : Client님이 주사위를" + "\n" + "             굴리셨습니다.\n");
							Server.chA.append("==========결과========\n");
							Server.chA.append("Server : " + dice + "    Client : " + Server.dice +"\n");
							if(dice > Server.dice){
								chA.append("Client님이 " +dice+ "로 승리하셨습니다!\n");
								Server.chA.append("Client님이 " +dice+ "로 승리하셨습니다!\n");
								chA.append("========================\n");
								Server.chA.append("========================\n");
							} else if (dice < Server.dice){
								chA.append("Server님이 " +Server.dice + "로 승리하셨습니다!\n");
								Server.chA.append("Server님이 " +Server.dice + "로 승리하셨습니다!\n");
								chA.append("========================\n");
								Server.chA.append("========================\n");
							} else if (dice == Server.dice){
								chA.append("무승부입니다.." +"\n" + "다시 주사위를 돌려주세요\n");
								Server.chA.append("무승부입니다.." +"\n" + "다시 주사위를 돌려주세요\n"); 
							}
						}
					} 

			}
		});

		chA.setEditable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 450);
		frame.setVisible(true);
		frame.setLocation(600,300);
		frame.setResizable(false);
		
		this.serverIP = serverName;
		this.port = port;
	}
	
	public void run(){
			try {
				socket = new Socket(serverIP, port);
				chA.setText("서버에 연결되었습니다.\n");
				in = new DataInputStream(Client.socket.getInputStream());
				out = new DataOutputStream(Client.socket.getOutputStream());
				while (in != null) {
					msg = in.readUTF();
					chA.append(msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == numBt) {
			ClientNu cl = new ClientNu();
		} else if (e.getSource() == bwBt) {
			ClientWB cl1 = new ClientWB();
		} else if (e.getSource() == poBt) {
			ClientPo cl2 = new ClientPo();
		}
	}
}
