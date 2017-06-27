

import java.awt.event.*;
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

import javax.swing.*;

import java.awt.*;
	
public class Server extends Thread implements ActionListener{
	static int port;
	static ServerSocket serverSocket;
	static Socket server;
	public static DataInputStream in;
	public static DataOutputStream out;
	public static Socket socket;
	static String msg;
	
	JFrame frame = new JFrame("Server");
	JInternalFrame fr = new JInternalFrame("in",true,true,true,true);
	JInternalFrame fr1;
	JDesktopPane desk = new JDesktopPane();
	
	JLabel label = new JLabel("������ �������ּ���.",JLabel.CENTER);
	JLabel name = new JLabel(new ImageIcon("D:/name.jpg"), JLabel.CENTER);
	static JLabel serverScore = new JLabel();		//server�� ���ھ� ��
	static JLabel clientScore = new JLabel();		//Client�� ���ھ� ��
	static int serverS = 0;
	static int clientS = 0;
	
	JPanel chP = new JPanel();
	JPanel rightP = new JPanel();
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
	
	static int location = 0; // ä��â ��ġ
	
	static Random random = new Random();
	
	static int dice = 0;
	public Server(int port) throws IOException {

		frame.setLayout(new BorderLayout());
		
		//������ ���� �̸�, ���� ���ھ� ���� ���� �г�
		rightP.setLayout(new GridLayout(2,1));
		label.setFont(new Font("Gulim",Font.BOLD,30));
		label.setForeground(Color.black);
		serverScore.setFont(new Font("�ü�",Font.ITALIC,15));
		clientScore.setFont(new Font("�ü�",Font.ITALIC,15));
		label.setFont(new Font("�ü�",Font.ITALIC,24));
		teP.setLayout(new BorderLayout());
		teP.setBackground(Color.white);
		name.setPreferredSize(new Dimension(0,140));
		serverScore.setText("Server���� ���� ���ھ� : " + serverS);
		clientScore.setText("Client���� ���� ���ھ� : " + clientS);
		teP.add(name, "North");
		teP.add(serverScore, "West");
		teP.add(clientScore, "East");
		teP.add(label,"South");
		rightP.add(teP);

		//���� �̸�, �̹��� ���� �г�
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
		rightP.add(gaP);
		
		//ä��â �г�
		chP.setLayout(new BorderLayout());
		chP.setPreferredSize(new Dimension(170,0));
		chP.add(chF, "South");
		chP.add(scroll);
		
		frame.add(rightP);
		frame.add(chP, "West");
		
		//ENTERŰ�� ����� �޼����� ������ ���� Ű���帮���� ����
		chF.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						String msg = chF.getText();
						if ("".equals(msg)) {
							return;
						}
						chA.append("Send : " + msg + "\n");
						chF.setText("");

						if (Server.out != null) {
							try {
								Server.out.writeUTF("Receive : " + msg + "\n");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						if(msg.equals("/dice")){
							dice = random.nextInt(10);
							Client.dice = Client.random.nextInt(10);
							chA.append("\nSend : Server���� �ֻ�����" + "\n" + "             �����̽��ϴ�.\n");
							chA.append("===========���=========\n");
							chA.append("Server : " + dice + "    Client : " + Client.dice + "\n");
							Client.chA.append("\nSend : Server���� �ֻ�����" + "\n" + "             �����̽��ϴ�.\n");
							Client.chA.append("==========���========\n");
							Client.chA.append("Server : " + dice + " Client : " + Client.dice +"\n");
							if(dice < Client.dice){
								chA.append("Client���� " +Client.dice+ "�� �¸��ϼ̽��ϴ�!\n");
								Client.chA.append("Client���� " +Client.dice+ "�� �¸��ϼ̽��ϴ�!\n");
								chA.append("========================\n");
								Client.chA.append("========================\n");
							} else if (dice > Client.dice){
								chA.append("Server���� " +dice + "�� �¸��ϼ̽��ϴ�!\n");
								Client.chA.append("Server���� " +dice + "�� �¸��ϼ̽��ϴ�!\n");
								chA.append("========================\n");
								Client.chA.append("=============="
										+ "==========\n");
							} else if (dice == Server.dice){
								chA.append("���º��Դϴ�.." +"\n" + "�ٽ� �ֻ����� �����ּ���\n");
								Client.chA.append("���º��Դϴ�.." + "\n" + "�ٽ� �ֻ����� �����ּ���\n");
							}
							
						}
						
					}
			}
		});

		chA.setEditable(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 450);
		frame.setVisible(true);
		frame.setLocation(60, 60);
		frame.setResizable(false);
		
		this.port = port;
		serverSocket = new ServerSocket(port);
	}
	
	/**
	 * ä���� ���� thread����
	 */
	public void run() {
			try {
				chA.setText("������ �غ�Ǿ����ϴ�.\n");
				server = serverSocket.accept();
				chA.setText("����� ����Ǿ����ϴ�.\n");
				in = new DataInputStream(server.getInputStream());
				out = new DataOutputStream(server.getOutputStream());
				//�޼����� �а� ����.
				while (in != null) {
					msg = in.readUTF();
					chA.append(msg);
				}	
			} catch (IOException e) {
				chA.setText("�������� ���� ����\n");
			}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == numBt){
			ServerNu ser = new ServerNu();
		} else if(e.getSource() == bwBt){
			ServerWB ser1 = new ServerWB();
		} else if(e.getSource() == poBt){
			ServerPo ser2 = new ServerPo();
		}
	}
}
