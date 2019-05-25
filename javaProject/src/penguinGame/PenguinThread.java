package penguinGame;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;


public class PenguinThread extends Thread {

	private Antarctic adven;
	private String[] penguinImg = { "d:\\penguin.png", "d:\\left.png", "d:\\right.png" };
	private String[] jumpImg = { "d:\\jump1.png", "d:\\jump2.png" };
	private ImageIcon img;

	public PenguinThread(Antarctic adven) {
		// super();
		this.adven = adven;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int tmp = 470;
		int jump = 400;
		while (true) {
			//////////////////////////////////////////////////////////////////// Æë±Ï Á¡ÇÁ ÀÌ¹ÌÁö
			if (adven.isJump() == true) { 
				try {
					while (adven.getY() >= jump) {
						for (int i = 0; i < jumpImg.length; i++) {
							Thread.sleep(20);
							adven.setPenguin(img = new ImageIcon(jumpImg[i]));
							adven.setY(adven.getY() - 5);
						}
					}
					while (adven.getY() <= tmp) {
						for (int i = 0; i < jumpImg.length; i++) {
							Thread.sleep(20);
							adven.setPenguin(img = new ImageIcon(jumpImg[i]));
							adven.setY(adven.getY() + 2);
						}
					}
					adven.setJump(false);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
//////////////////////////////////////////////////////////////////////////////// Æë±Ï °È´Â ÀÌ¹ÌÁö
			if (adven.isJump() == false) {
				try {
					for (int i = 0; i < penguinImg.length; i++) {
						Thread.sleep(8);
						adven.setPenguin(img = new ImageIcon(penguinImg[i]));
						// adven.repaint();

					}

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		
		}
	}
	
}
