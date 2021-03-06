package penguinGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Antarctic extends JFrame {
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	// 프레임 크기
	private int FWidth = 730;
	private int FHeight = 600;
	// 프레임 좌표
	private int FXPOS = (int) (d.getWidth() / 2 - FWidth / 2);
	private int FYPOS = (int) (d.getHeight() / 2 - FHeight / 2);

	// 펭귄
	private int X, Y; // 처음 좌표

	private boolean Left = false;
	private boolean Right = false;
	boolean Jump = false;

	private ImageIcon penguin = new ImageIcon();

	// 배경 이미지
	private ImageIcon back1 = new ImageIcon("d:\\back.png");

	// 장애물 이미지
	private ImageIcon bigHD = new ImageIcon("d:\\hurdle.png"); // 큰 장애물
	private ImageIcon smallHD = new ImageIcon("d:\\hurdle2.png"); // 작은 장애물

	// 큰장애물
	private int HurdleX = 350, HurldleY = 280; // 처음 좌표
	private int HurdleWSize = 40, HurdleHSize = 4; // 처음 사이즈

	// 작은 장애물
	private int SHurdleX = 340, SHurdleY = 280;
	private int SHurdleWSize = 40, SHurdleHSize = 7;
	
	// 펭귄 목숨
	private ImageIcon life1 = new ImageIcon("d:\\life1.png"); // 물고기 이미지
	private ImageIcon life2 = new ImageIcon("d:\\life2.png"); // 물고기 뼈 이미지 
		
	private int LifeX = 680;
	private int LifeY = 113;
	
	private int cnt = 3; // 장애물에 맞을때마다 cnt -1 ,  cnt가 0이되면 게임 오버
	
	// 시간 이미지
	private ImageIcon time = new ImageIcon();
	boolean timeM = true;
	
	
	// 게임 멈춤
	boolean hurdle = true;
	
	//타이틀
	private ImageIcon title = new ImageIcon("d:\\title2.png");
	
	ImageIcon a=new ImageIcon("d:\\1.gif");

	///////////////////////////////////////////////////////////////////////////////

	public Antarctic() {

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		this.setContentPane(new Penguin());
		this.setSize(FWidth, FHeight);
		this.setLocation(FXPOS, FYPOS);
		// this.setBounds(FXPOS, FYPOS,FWidth, FHeight);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("d:\\pen.png"));
		this.setTitle("남극 탐험");
		this.setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//////////////////////////////////////////////////////////////////////

	public class Penguin extends JPanel implements Runnable {
		// Graphics g

		public void paint(Graphics g) {
			super.paintComponent(g);
			// g.clearRect(30, 150, FWidth-77, FHeight-180);
			// g.clearRect(0, 0, FWidth, FHeight);

			g.drawImage(back1.getImage(), 0, 100, 730, 500, this); // 배경

			g.drawImage(time.getImage(),5,LifeY,this);
			
			
			
			g.drawImage(bigHD.getImage(), HurdleX, HurldleY, HurdleWSize, HurdleHSize, this);
			g.drawImage(smallHD.getImage(), SHurdleX, SHurdleY, SHurdleWSize, SHurdleHSize, this);

			g.drawImage(penguin.getImage(), X, Y, this); // 펭귄
			
			
			switch (cnt) { // 목숨
			case 3:
				g.drawImage(life1.getImage(), LifeX, LifeY, this);
				g.drawImage(life1.getImage(), LifeX-30, LifeY, this);
				g.drawImage(life1.getImage(), LifeX-60, LifeY, this);
				break;
			case 2:
				g.drawImage(life2.getImage(), LifeX, LifeY, this);
				g.drawImage(life1.getImage(), LifeX-30, LifeY, this);
				g.drawImage(life1.getImage(), LifeX-60, LifeY, this);
				break;
			case 1:
				g.drawImage(life2.getImage(), LifeX, LifeY, this);
				g.drawImage(life2.getImage(), LifeX-30, LifeY, this);
				g.drawImage(life1.getImage(), LifeX-60, LifeY, this);
				break;
			default:
				g.drawImage(life2.getImage(), LifeX, LifeY, this);
				g.drawImage(life2.getImage(), LifeX-30, LifeY, this);
				g.drawImage(life2.getImage(), LifeX-60, LifeY, this);
				break;
			}
			
			g.drawImage(title.getImage(), -35, 20, this);
			
				
			//g.drawImage(a.getImage(), 0, 0, this);
		}

		public Penguin() {
			this.setBackground(Color.black);

			this.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					System.out.println("X좌표: " + e.getX());
					System.out.println("Y좌표: " + e.getY());
				}
			});

			X = 354;
			Y = 470;

			TimeThread time = new TimeThread(Antarctic.this);
			PenguinThread m = new PenguinThread(Antarctic.this); // 펭귄 움직이는 이미지
			HurdleThread h = new HurdleThread(Antarctic.this); // 큰 장애물
			SmallHDThread s = new SmallHDThread(Antarctic.this); // 작은 장애물
			Thread t = new Thread(this);
			time.start();
			m.start();
			h.start();
			s.start();
			t.start();
			

			Antarctic.this.addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						Left = true;
						break;
					case KeyEvent.VK_RIGHT:
						Right = true;
						break;
					case KeyEvent.VK_SPACE:
						Jump = true;
						break;

					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						Left = false;
						break;
					case KeyEvent.VK_RIGHT:
						Right = false;
						break;

					}
				}
			});

		}

		public void KeyProcess() {
			if (Left == true) {
				if (X <= 50) {
					X -= 0;
				} else {
					X -= 10;
				}
			}
			if (Right == true) {
				if (X >= 610) {
					X += 0;
				} else {
					X += 10;
				}
			}

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (hurdle) {
					KeyProcess();
					repaint();
					Thread.sleep(30);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	///////////////////////////////////////////////////////////////////// GETSET

	public ImageIcon getPenguin() {
		return penguin;
	}

	public void setPenguin(ImageIcon penguin) {
		this.penguin = penguin;
	}

	public boolean isJump() {
		return Jump;
	}

	public void setJump(boolean jump) {
		Jump = jump;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getHurdleX() {
		return HurdleX;
	}

	public void setHurdleX(int hurdleX) {
		HurdleX = hurdleX;
	}

	public int getHurldleY() {
		return HurldleY;
	}

	public void setHurldleY(int hurldleY) {
		HurldleY = hurldleY;
	}

	public int getHurdleWSize() {
		return HurdleWSize;
	}

	public void setHurdleWSize(int hurdleWSize) {
		HurdleWSize = hurdleWSize;
	}

	public int getHurdleHSize() {
		return HurdleHSize;
	}

	public void setHurdleHSize(int hurdleHSize) {
		HurdleHSize = hurdleHSize;
	}

	public int getSHurdleX() {
		return SHurdleX;
	}

	public void setSHurdleX(int sHurdleX) {
		SHurdleX = sHurdleX;
	}

	public int getSHurdleY() {
		return SHurdleY;
	}

	public void setSHurdleY(int sHurdleY) {
		SHurdleY = sHurdleY;
	}

	public int getSHurdleWSize() {
		return SHurdleWSize;
	}

	public void setSHurdleWSize(int sHurdleWSize) {
		SHurdleWSize = sHurdleWSize;
	}

	public int getSHurdleHSize() {
		return SHurdleHSize;
	}

	public void setSHurdleHSize(int sHurdleHSize) {
		SHurdleHSize = sHurdleHSize;
	}
	
	public int getCnt() {
		return cnt;
	}
	
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	public ImageIcon getTime() {
		return time;
	}
	
	public void setTime(ImageIcon time) {
		this.time = time;
	}
	
	///////////////////////////////////////////////////////////////////////////




	public static void main(String[] args) {
		new Antarctic();
	}
}
