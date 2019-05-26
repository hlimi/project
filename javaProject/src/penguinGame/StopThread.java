package penguinGame;
 
import javax.swing.JOptionPane;

public class StopThread extends Thread {
	private Antarctic adven;
	private int START;
	private int END;
	private int a;

	public StopThread(Antarctic adven, int start, int end, int a) {
		super();
		this.adven = adven;
		this.START = start;
		this.END = end;
		this.a = a;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("stopthread");
		// while (adven.hurdle) {
		switch (a) {
		case 1: // 큰 장애물
			while (adven.hurdle) {
				System.out.println();
				if (adven.getHurldleY() > 500) {
					stopHurdle();
					break;
				}
			}
			break;
		case 2: // 작은 장애물
			while (adven.hurdle) {
				System.out.println();
				if (adven.getSHurdleY() > 510) {
					stopHurdle();
					break;
				}
			}
			break;
		}
		// }
	}

	public void stopHurdle() {
		
			if (adven.getX() >= START && adven.getX() <= END) {
				if (adven.getY() >= 460) {
					adven.setCnt(adven.getCnt() - 1);
				} 
					
				if (adven.getCnt() == 0) {
					adven.repaint();
					adven.hurdle = false;
					JOptionPane.showMessageDialog(null,"GAME OVER","GAME OVER",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		}
	}
