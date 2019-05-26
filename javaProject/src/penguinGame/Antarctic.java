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
	// ÇÁ·¹ÀÓ Å©±â
	private int FWidth = 730;
	private int FHeight = 600;
	// ÇÁ·¹ÀÓ ÁÂÇ¥
	private int FXPOS = (int) (d.getWidth() / 2 - FWidth / 2);
	private int FYPOS = (int) (d.getHeight() / 2 - FHeight / 2);

	// Æë±Ï
	private int X, Y; // Ã³À½ ÁÂÇ¥

	private boolean Left = false;
	private boolean Right = false;
	boolean Jump = false;

	private ImageIcon penguin = new ImageIcon();

	// ¹è°æ ÀÌ¹ÌÁö
	private ImageIcon back1 = new ImageIcon("d:\\back.png");

	// Àå¾Ö¹° ÀÌ¹ÌÁö
	private ImageIcon bigHD = new ImageIcon("d:\\hurdle.png"); // Å« Àå¾Ö¹°
	private ImageIcon smallHD = new ImageIcon("d:\\hurdle2.png"); // ÀÛÀº Àå¾Ö¹°

	// Å«Àå¾Ö¹°
	private int HurdleX = 350, HurldleY = 280; // Ã³À½ ÁÂÇ¥
	private int HurdleWSize = 40, HurdleHSize = 4; // Ã³À½ »çÀÌÁî

	// ÀÛÀº Àå¾Ö¹°
	private int SHurdleX = 340, SHurdleY = 280;
	private int SHurdleWSize = 40, SHurdleHSize = 7;
	
	// Æë±Ï ¸ñ¼û
	private ImageIcon life1 = new ImageIcon("d:\\life1.png"); // ¹°°í±â ÀÌ¹ÌÁö
	private ImageIcon life2 = new ImageIcon("d:\\life2.png"); // ¹°°í±â »À ÀÌ¹ÌÁö 
		
	private int LifeX = 680;
	private int LifeY = 113;
	
	private int cnt = 3; // Àå¾Ö¹°¿¡ ¸ÂÀ»¶§¸¶´Ù cnt -1 ,  cnt°¡ 0ÀÌµÇ¸é °ÔÀÓ ¿À¹ö
	
	// ½Ã°£ ÀÌ¹ÌÁö
	private ImageIcon time = new ImageIcon();
	boolean timeM = true;
	
	
	// °ÔÀÓ ¸ØÃã
	boolean hurdle = true;
	
	//Å¸ÀÌÆ²
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
		this.setTitle("³²±Ø Å½Çè");
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

			g.drawImage(back1.getImage(), 0, 100, 730, 500, this); // ¹è°æ

			g.drawImage(time.getImage(),5,LifeY,this);
			
			
			
			g.drawImage(bigHD.getImage(), HurdleX, HurldleY, HurdleWSize, HurdleHSize, this);
			g.drawImage(smallHD.getImage(), SHurdleX, SHurdleY, SHurdleWSize, SHurdleHSize, this);

			g.drawImage(penguin.getImage(), X, Y, this); // Æë±Ï
			
			
			switch (cnt) { // ¸ñ¼û
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
					System.out.println("XÁÂÇ¥: " + e.getX());
					System.out.println("YÁÂÇ¥: " + e.getY());
				}
			});

			X = 354;
			Y = 470;

			TimeThread time = new TimeThread(Antarctic.this);
			PenguinThread m = new PenguinThread(Antarctic.this); // Æë±Ï ¿òÁ÷ÀÌ´Â ÀÌ¹ÌÁö
			HurdleThread h = new HurdleThread(Antarctic.this); // Å« Àå¾Ö¹°
			SmallHDThread s = new SmallHDThread(Antarctic.this); // ÀÛÀº Àå¾Ö¹°
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
