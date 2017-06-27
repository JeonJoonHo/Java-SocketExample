

import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * �ε�� ��Ŀ ���ӿ��� �����Ҷ� Ĩ�� ������ ���� Class
 * @author JEON
 */
public class Coin extends Thread{
	int bat;
	JLabel[] imgL = new JLabel[60];	//ī�� ��ȣ
	JLabel[] imgL1 = new JLabel[60];
	int[] ranL = new int[60];			//Ĩ��ġ
	int[] ranL1 = new int[60];		
	ImageIcon coin = new ImageIcon("D:/chip.jpg");
	
	Random random = new Random();
	
	/**
	 * �����ڷ� imgL(���� ������) imgL1(Ŭ���̾�Ʈ ������)�� �̹��� �ʱ�ȭ
	 * ���� ���� �����Ͽ� �����ӿ� ��ġ�� ������.
	 * @param bat int�� ����, ���þ��� �޾ƿ�.
	 */
	public Coin(int bat) {
		this.bat = bat;
		imgL = new JLabel[bat];
		imgL1 = new JLabel[bat];
		ranL = new int[bat];
		ranL1 = new int[bat];
	
		for (int i = 0; i < bat; i++) {
			imgL[i] = new JLabel(""+i);
			imgL[i].setIcon(coin);
		}
		
		for (int i = 0; i < bat; i++) {
			imgL1[i] = new JLabel(""+i);
			imgL1[i].setIcon(coin);
		}
		
		for(int i=0; i<bat; i++){
			int k = random.nextInt(300);
			ranL[i] = k;
			for(int j=0; j<i; j++){
				if(ranL[i] == ranL[j]){
					k = random.nextInt(300);
					ranL[i] = k;
					i = i-1;
					break;
				}
			}
		}
		
		for(int i=0; i<bat; i++){
			int k = random.nextInt(180);
			ranL1[i] = k;
			for(int j=0; j<i; j++){
				if(ranL1[i] == ranL1[j]){
					k = random.nextInt(180);
					ranL1[i] = k;
					i = i-1;
					break;
				}
			}
		}
	}

	/**
	 * ���þ׸�ŭ Ĩ�� ������ method
	 */
	public void Batting(){
		for (int i = 0; i < bat; i++) {
			imgL[i].setSize(15, 15);
			imgL[i].setLocation(ranL[i], ranL1[i]);
			ServerPo.Bat(imgL);
			imgL1[i].setSize(15, 15);
			imgL1[i].setLocation(ranL[i], ranL1[i]);
			ClientPo.Bat(imgL1);
		}
	}
	
	public void run(){
			Batting();
	}
}
