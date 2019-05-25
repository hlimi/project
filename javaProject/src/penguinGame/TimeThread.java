package penguinGame;

import javax.swing.ImageIcon;

public class TimeThread extends Thread {
	private Antarctic adven;
	private ImageIcon img;

	public TimeThread(Antarctic adven) {
		super();
		this.adven = adven;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			for (int i = 0; i < 61; i++) {
				Thread.sleep(500);
				adven.setTime(img = new ImageIcon("d:\\time\\" + i + ".png"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
