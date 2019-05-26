package board;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


/*CREATE TABLE BOARD(
�۹�ȣ NUMBER(5) CONSTRAINT BOARD_NUM_PK PRIMARY KEY,
���� VARCHAR2(50) CONSTRAINT BOARD_TITLE_NN NOT NULL,
�۾��� VARCHAR2(20) CONSTRAINT BOARD_WRITER_NN NOT NULL,
�ۼ��� DATE,
��ȸ NUMBER(5),
���� VARCHAR2(4000) CONSTRAINT BOARD_CON_NN NOT NULL,
��й�ȣ NUMBER(4) CONSTRAINT BOARD_CON_PASS NOT NULL);*/



/*CREATE SEQUENCE NO_SEQ
MAXVALUE 9999;
*/

/*CREATE TABLE FONT(
NO NUMBER(5),
�۾�ü VARCHAR2(20),
ũ�� VARCHAR2(10));*/

public class BoardMain extends JFrame {
	private JLabel jlb;
	private JTable jtb = new JTable();
	private JComboBox<String> jcb;
	private JTextField jtf;
	private JButton jbtnSearch, jbtnWrite;
	private JPanel jp, jpBoard, jpTable, jpCard ,jpButton, jpWrite;
	private String[] search = { "��ü", "�۾���", "����", "����" };
	private CardLayout Card=new CardLayout();
	
	
	
	
	private Object no;
	private String sql = "SELECT �۹�ȣ,����,	�۾���,�ۼ���,��ȸ  FROM BOARD ORDER BY �۹�ȣ  DESC";
	

//////// GET SET /////////////////////////////////
	
	public void setJpWrite(JPanel jpWrite) {
		this.jpWrite = jpWrite;
	}

	public JTable getJtb() {
		return jtb;
	}

	public void setJtb(JTable jtb) {
		this.jtb = jtb;
	}
	
	public JComboBox<String> getJcb() {
		return jcb;
	}
	
	public JTextField getJtf() {
		return jtf;
	}
	
	
///////////////////////////////////////////////////
	


	public void initBoard() {
	
		jpBoard = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		jpWrite=new JPanel();
		
    //////// �۾��� â �ҷ�����  ////////////////////////////////////////////	
		BoardWrite write=new BoardWrite(this);
		
		// ���
		write.getJbtRegis().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("��Ϲ�ư");
					Card.show(jpCard, "Main");					
			}
		});
		
		// ���
		write.getJbtCancle().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Card.show(jpCard, "Main");
			}
		});
		
		// ����
		write.getJbtModify().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TableDao.getInstance().modifyTable(write, no);
				Card.show(jpCard, "Main");
			}
		});
		
		//����
		write.getJbtDelete().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TableDao.getInstance().deleteTable(write, no);
				Card.show(jpCard, "Main");
				
			}
		});
		
////////////////////////////////////////////////////////////////////////	
		
		jlb = new JLabel("- �Խ��� -");
		jpBoard.add(jlb);

		jpButton = new JPanel();
		jpButton.add(jcb = new JComboBox<String>(search));
		jpButton.add(jtf = new JTextField(15));
		jpButton.add(jbtnSearch = new JButton("�˻�"));
		// �˻�
		jbtnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TableDao.getInstance().searchTable(BoardMain.this);
				
			}
		});
		
		jpButton.add(jbtnWrite = new JButton("�۾���"));
		jbtnWrite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					Card.show(jpCard, "Write");		
			}
		});
		

		// jpTable=new JPanel();
		// jpTable.add(new
		// JScrollPane(jtb,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

		jpCard= new JPanel();
		jpCard.setLayout(Card);
		
		jp=new JPanel(new BorderLayout());
		
		jp.add("North", jpBoard);
		// this.add(jpTable);
		jp.add(
				new JScrollPane(jtb, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		jtb.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2) {
					no=jtb.getValueAt(jtb.getSelectedRow(), 0);
					TableDao.getInstance().intoTable(write, no);
					TableDao.getInstance().updateHit(no);
					write.getJbtRegis().setEnabled(false);
					Card.show(jpCard, "Write");
				}
			}
			
		});
		jp.add("South", jpButton);
		
		
		
		jpCard.add("Main", jp);
		jpCard.add("Write", jpWrite);
		
		this.add(jpCard);
		TableDao.getInstance().getTable(this,sql);
	}

	
/////////////////////////////////////////////////////////////////////////////	
	
	public BoardMain() {
		initBoard();
		this.setBounds(100, 100, 500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new BoardMain();
	}
}
