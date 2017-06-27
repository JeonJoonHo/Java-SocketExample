

import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 인디언 포커 게임에서 배팅할때 칩을 던지기 위한 Class
 * @author JEON
 */
public class Coin extends Thread{
	int bat;
	JLabel[] imgL = new JLabel[60];	//카드 번호
	JLabel[] imgL1 = new JLabel[60];
	int[] ranL = new int[60];			//칩위치
	int[] ranL1 = new int[60];		
	ImageIcon coin = new ImageIcon("D:/chip.jpg");
	
	Random random = new Random();
	
	/**
	 * 생성자로 imgL(서버 프레임) imgL1(클라이언트 프레임)에 이미지 초기화
	 * 랜덤 난수 생성하여 프레임에 위치를 저장함.
	 * @param bat int형 변수, 배팅액을 받아옴.
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
	 * 배팅액만큼 칩을 던지는 method
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
