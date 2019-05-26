package penguinGame;
 
public class HurdleThread extends Thread {
	private Antarctic adven;
	////////////////////////////// 큰 장애물
	int HX = 380, HY = 280;
	int WSize = 40, HSize = 4;
//	int StopY = 530;

	public HurdleThread(Antarctic adven) {
		super();
		this.adven = adven;
	}

	@Override
	public void run() {

		// TODO Auto-generated method stub
		//if (adven.hurdle == true) {

			while (adven.hurdle) {
				//if(adven.getSTOP()==-1) return;
				try {
					switch ((int) (Math.random()*3)) {
					case 0:
						StopThread h0=new StopThread(adven, 10, 130, 1);
						h0.start();
						bigmove(-4);
						break;
					case 1:
						StopThread h1=new StopThread(adven, 560, 650, 1);
						h1.start();
						bigmove(1);
						break;
					case 2:
						StopThread h2=new StopThread(adven, 195, 420, 1);
						h2.start();
						bigmove(-2);
						break;
					}
					/*adven.setHurdleX(HX);
					adven.setHurldleY(HY);
					adven.setHurdleWSize(WSize);
					adven.setHurdleHSize(HSize);*/

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		//}
	}

	public void bigmove(int i) { // 큰 장애물 x좌표
		while (adven.getHurldleY() <= 600) {
			//System.out.println(adven.getHurldleY());
			try {
				adven.setHurldleY(adven.getHurldleY() + 2);
				adven.setHurdleX(adven.getHurdleX() + i);
				adven.setHurdleWSize(adven.getHurdleWSize() + 3);
				adven.setHurdleHSize(adven.getHurdleWSize() / 11);
				if(adven.hurdle==false)return;
				//System.out.println(adven.getHurldleY());
				//if(adven.getSTOP()==-1) return;
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		adven.setHurdleX(HX);
		adven.setHurldleY(HY);
		adven.setHurdleWSize(WSize);
		adven.setHurdleHSize(HSize);
	}

}
