package penguinGame;

public class SmallHDThread extends Thread {
	private Antarctic adven;
	////////////////////////////// 작은 장애물
	int SX = 370, SY = 280;
	int SWSize = 40, SHSize = 7;
	//int StopY = 540;

	public SmallHDThread(Antarctic adven) {
		super();
		this.adven = adven;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//if (adven.hurdle == true) {

			while (adven.hurdle) {
				switch ((int) (Math.random()*3)) {
				case 0:
					StopThread s0=new StopThread(adven, 20, 62,2);
					s0.start();
					smallmove(-3);
					break;
				case 1:
					StopThread s1=new StopThread(adven, 235, 355,2);
					s1.start();
					smallmove(-1);
					break;
				case 2:
					StopThread s2=new StopThread(adven, 600, 690,2);
					s2.start();
					smallmove(2);
					break;
				}
			}
		//}
	}

	public void smallmove(int i) {
		while (adven.getSHurdleY() <= 600) {
			try {
				adven.setSHurdleY(adven.getSHurdleY() + 2);
				adven.setSHurdleX(adven.getSHurdleX() + i);
				adven.setSHurdleWSize(adven.getSHurdleWSize() + 1);
				adven.setSHurdleHSize(adven.getSHurdleWSize() / 6);
				if(adven.hurdle==false)return;
				Thread.sleep(70);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		adven.setSHurdleX(SX);
		adven.setSHurdleY(SY);
		adven.setSHurdleWSize(SWSize);
		adven.setSHurdleHSize(SHSize);
	}
}
