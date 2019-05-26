package board;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BoardWrite extends JFrame  {
	private JLabel jlb, jlbWriter, jlbPass;
	private JTextField jtfTitle, jtfWriter;
	private JPasswordField jpfPass;
	private JTextArea jta;
	private JButton jbtRegis, jbtCancle, jbtModify, jbtDelete;
	private JPanel jp, jpBoard, jpCenter, jpButton;
	private JScrollPane jsp;
	
	private JComboBox<String> ComboFont;
	private String [] font = { "굴림", "바탕", "돋움", "궁서" };
	
	private JComboBox<String> ComboSize;
	private String [] size = { "10pt", "12pt" , "14pt" ,"18pt", "24pt" } ;
	
	private String sql = "SELECT 글번호,제목,	글쓴이, 작성일,조회  FROM BOARD ORDER BY 글번호  DESC";
	
	
	private String fo="돋움";
	private int si=12;
	
	//private JComboBox jbFont, jbSize;
	
	
	
	
	

	public JComboBox<String> getComboFont() {
		return ComboFont;
	}

	public void setFo(String fo) {
		this.fo = fo;
	}

	public void setSi(int si) {
		this.si = si;
	}

	public JComboBox<String> getComboSize() {
		return ComboSize;
	}

	public JButton getJbtDelete() {
		return jbtDelete;
	}

	public JPasswordField getJpfPass() {
		return jpfPass;
	}

	public JButton getJbtModify() {
		return jbtModify;
	}

	public void setJta(JTextArea jta) {
		this.jta = jta;
	}

	public JTextArea getJta() {
		return jta;
	}

	public JButton getJbtRegis() {
		return jbtRegis;
	}

	public JButton getJbtCancle() {
		return jbtCancle;
	}

	public JTextField getJtfTitle() {
		return jtfTitle;
	}

	public void setJtfTitle(JTextField jtfTitle) {
		this.jtfTitle = jtfTitle;
	}

	public JTextField getJtfWriter() {
		return jtfWriter;
	}

	public void setJtfWriter(JTextField jtfWriter) {
		this.jtfWriter = jtfWriter;
	}

	public JPanel getJp() {
		return jp;
	}

	public void setJp(JPanel jp) {
		this.jp = jp;
	}



	public BoardWrite(BoardMain boardMain) {
		//TableDao.getInstance().writeTable(this);
		//TableDao.getInstance().getTable(boardMain);
		
		jpBoard=new JPanel(new FlowLayout(FlowLayout.LEFT,20, 20));
		jpBoard.add(jlb=new JLabel("게시판"));
		
		
		jpCenter=new JPanel(null);
	
		jpCenter.add(jtfTitle= new JTextField("제목을 입력하세요"));
		jtfTitle.setBounds(20,0,450,20);
		
		jpCenter.add(jtfWriter= new JTextField());
		jpCenter.add(jlbWriter= new JLabel("글쓴이"));
		jlbWriter.setBounds(20,30,50,20);
		jtfWriter.setBounds(70,30,100,20);
		
		jpCenter.add(jlbPass=new JLabel("비밀번호"));
		jpCenter.add(jpfPass=new JPasswordField());
		jlbPass.setBounds(190, 30, 70, 20);
		jpfPass.setBounds(260,30,100,20);
		
		
		
		jpCenter.add(ComboFont=new JComboBox<String>(font));
		ComboFont.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox jb= (JComboBox)e.getSource();
				fo=jb.getSelectedItem().toString();
				//System.out.println(fo);
				jta.setFont(new Font(fo, Font.PLAIN, si));
			}
		});
	
		ComboFont.setBounds(20,60,70,20);
		
		jpCenter.add(ComboSize=new JComboBox<String>(size));
		ComboSize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox jbs=(JComboBox)e.getSource();
				si=Integer.parseInt(jbs.getSelectedItem().toString().substring(0, 2));
				jta.setFont(new Font(fo,Font.PLAIN,si));
				
				
			}
		});
		ComboSize.setBounds(95,60,70,20);
		
		jpCenter.add(jsp=new JScrollPane(jta=new JTextArea(),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		System.out.println(fo);
		
		//jta.setFont(new Font(ComboFont.getSelectedItem().toString(), Font.PLAIN, Integer.parseInt(ComboSize.getSelectedItem().toString().substring(0, 2))));
		//jta.setFont(new Font(jbFont.getSelectedItem().toString(), Font.PLAIN, Integer.parseInt(jbSize.getSelectedItem().toString().substring(0, 2))));
		//jta.setFont(new Font(combofont,Font.PLAIN,combosize));
		
		jta.setLineWrap(true);
		
		
		jsp.setBounds(20,90,450,246);
		
		
		
		jpButton=new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 20));
		jpButton.add(jbtRegis=new JButton("등록"));
		jbtRegis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("write 등록");
				TableDao.getInstance().writeTable(BoardWrite.this);
				TableDao.getInstance().getTable(boardMain,sql);
				jta.setText("");
				jtfTitle.setText("제목을 입력하세요");
				jtfWriter.setText("");
				jpfPass.setText("");
				jta.setFont(new Font("돋움", Font.PLAIN, 12));
			}
		});
		 
		jpButton.add(jbtModify=new JButton("수정"));
		jbtModify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TableDao.getInstance().getTable(boardMain,sql);
				jbtRegis.setEnabled(true);
				jta.setText("");
				jtfTitle.setText("제목을 입력하세요");
				jtfWriter.setText("");
				jpfPass.setText("");
				jta.setFont(new Font("돋움", Font.PLAIN, 12));
			}
		});
		
		jpButton.add(jbtDelete = new JButton("삭제"));
		jbtDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TableDao.getInstance().getTable(boardMain,sql);
				jbtRegis.setEnabled(true);
				jta.setText("");
				jtfTitle.setText("제목을 입력하세요");
				jtfWriter.setText("");
				jpfPass.setText("");
				jta.setFont(new Font("돋움", Font.PLAIN, 12));
			}
		});
		
		jpButton.add(jbtCancle=new JButton("취소"));
		jbtCancle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jbtRegis.setEnabled(true);
				TableDao.getInstance().getTable(boardMain,sql);
				jta.setText("");
				jtfTitle.setText("제목을 입력하세요");
				jtfWriter.setText("");
				jpfPass.setText("");
				jta.setFont(new Font("돋움", Font.PLAIN, 12));
			}
		});
		
		jp=new JPanel(new BorderLayout());
		
		jp.add("North",jpBoard);
		jp.add(jpCenter);
		jp.add("South",jpButton);
		
		boardMain.setJpWrite(jp);
		
	}
}
