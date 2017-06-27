import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class MAIN implements ActionListener {
	JFrame frame = new JFrame();
	
	JPanel rPa = new JPanel();
	JPanel tPa = new JPanel();
	JPanel tPa1 = new JPanel();
	JPanel tPa2 = new JPanel();
	JPanel btPa = new JPanel();
	JPanel unPa = new JPanel();
	
	JButton startBt = new JButton("시작하기");
	
	static JRadioButton hostBt = new JRadioButton("HOST");
	JRadioButton guestBt = new JRadioButton("GUEST");
	ButtonGroup group = new ButtonGroup();
	
	JTextField ipT = new JTextField("localhost");
	JTextField portT = new JTextField(5);
	JLabel ipL = new JLabel("HOST IP");
	JLabel portL = new JLabel("Port       ");
	
	public MAIN(){
		frame.setLayout(new GridLayout(2,1));
		rPa.setLayout(new GridLayout(1,2));
		group.add(guestBt);
		group.add(hostBt);
		rPa.add(hostBt);
		rPa.add(guestBt);
		hostBt.setSelected(true);
		hostBt.addActionListener(this);
		guestBt.addActionListener(this);
		
		unPa.setLayout(new GridLayout(1,2));
		unPa.add(tPa);
		tPa.setLayout(new GridLayout(2,1));
		tPa.add(tPa1);
		tPa.add(tPa2);
		ipT.setEnabled(false);
		tPa1.add(ipL);
		tPa1.add(ipT);
		tPa2.add(portL);
		tPa2.add(portT);
		
		unPa.add(btPa);
		btPa.add(startBt);
		startBt.addActionListener(this);
		
		frame.add(rPa);
		frame.add(unPa);

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MAIN main = new MAIN();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (hostBt.isSelected()) {
			if (e.getSource() == startBt) {
				int port1 = Integer.parseInt("" + portT.getText());
				try {
					Thread ser = new Server(port1);
					ser.start();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (guestBt.isSelected()) {
			if (e.getSource() == startBt) {
				String ip = "" + ipT.getText();
				int port2 = Integer.parseInt("" + portT.getText());
				Thread cl = new Client(ip, port2);
				cl.start();
				frame.setVisible(false);
			}
		}
	}

}
