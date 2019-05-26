package board;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


	public class TableDao {
	private static TableDao tableDao;
	
	
	public static TableDao getInstance() {
		if(tableDao==null) {
			tableDao=new TableDao(); 
		}
		return tableDao;
	}

	
	// ��ȸ��+1
	public void updateHit(Object no) {
		Connection con=ServiceUtil.getConnection();
		PreparedStatement pstmt=null;
		String sql="UPDATE BOARD SET ��ȸ=��ȸ+1 WHERE �۹�ȣ=? ";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setObject(1, no);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// �˻�
	public void searchTable(BoardMain main) {
		String sql=null;
		String search=main.getJtf().getText().trim().toString();
		String value = main.getJcb().getSelectedItem().toString();
		Connection con=ServiceUtil.getConnection();
		PreparedStatement psmt=null;
		
		
	// �۾��� �˻�
		if(value.equals("�۾���")) {
			sql=searchSql("�۾���",search);
			System.out.println(sql);
			
			}
	// ���� �˻�
		else if(value.equals("����")){
			sql=searchSql("����",search);
			
		}
	// ���� �˻�
		else if(value.equals("����")){
			sql=searchSql("����",search);
			
		}
	// ��ü �˻�
		else {
			sql="SELECT �۹�ȣ,����,�۾���, �ۼ���,��ȸ FROM BOARD ORDER BY �۹�ȣ DESC";
			
		}
		getTable(main, sql);
	}
	
	public String searchSql(String val, String search) {
		return "SELECT �۹�ȣ,����,�۾���,�ۼ���,��ȸ FROM BOARD WHERE "+val+" like '%"+search+"%' ORDER BY �۹�ȣ DESC";
	}
	
	// ����
	public void deleteTable(BoardWrite write, Object no) {
		Connection con=ServiceUtil.getConnection();
		PreparedStatement psmt=null;
		
		try {
			psmt=con.prepareStatement("DELETE FROM BOARD WHERE �۹�ȣ=?");
			psmt.setObject(1, no);
			psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	// ����
	public void modifyTable(BoardWrite write, Object no) {
		Connection con=ServiceUtil.getConnection();
		PreparedStatement psmt=null;
		PreparedStatement psmtFont=null;
		
		try {
			psmt=con.prepareStatement("UPDATE BOARD SET ����=?, ����=? WHERE �۹�ȣ=? ");
			psmt.setString(1, write.getJtfTitle().getText().trim().toString());
			psmt.setString(2, write.getJta().getText().trim().toString());
			psmt.setObject(3, no);
			psmt.executeUpdate();
			
			psmtFont=con.prepareStatement("UPDATE FONT SET �۾�ü=?, ũ��=? WHERE NO=?");
			psmtFont.setString(1,write.getComboFont().getSelectedItem().toString());
			psmtFont.setString(2,write.getComboSize().getSelectedItem().toString().substring(0, 2));
			psmtFont.setObject(3, no);
			psmtFont.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// �� ���� Ȯ��
	public void intoTable(BoardWrite write, Object no) {
		Connection con=ServiceUtil.getConnection();
		PreparedStatement psmt=null;
		PreparedStatement psmtFont=null;
		ResultSet rs=null;
		ResultSet rsFont=null;
		try {
			psmt=con.prepareStatement("SELECT * FROM BOARD WHERE �۹�ȣ=?");
			psmt.setObject(1, no);
			rs=psmt.executeQuery();
			if(rs.next()) {
			write.getJtfTitle().setText(rs.getString(2));
			write.getJtfWriter().setText(rs.getString(3));
			write.getJta().setText(rs.getString(6));
			}
			psmtFont=con.prepareStatement("SELECT * FROM FONT WHERE NO=?");
			psmtFont.setObject(1, no);
			rsFont=psmtFont.executeQuery();
		
			if(rsFont.next()) {
				System.out.println("ddd");
				write.getJta().setFont(new Font(rsFont.getString(2),Font.PLAIN,rsFont.getInt(3)));
			}
	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// �۾���
	public void writeTable(BoardWrite boardWrite) {
		System.out.println("TableDao");
		Connection con=ServiceUtil.getConnection();
		PreparedStatement psmt=null;
		PreparedStatement psmtF=null;
		
		try {
			psmt=con.prepareStatement("INSERT INTO BOARD VALUES(NO_SEQ.NEXTVAL,?,?,SYSDATE,0,?,?)");
			psmt.setString(1, boardWrite.getJtfTitle().getText().trim().toString());
			psmt.setString(2, boardWrite.getJtfWriter().getText().trim().toString());
			psmt.setString(3, boardWrite.getJta().getText().trim().toString());
			psmt.setObject(4, boardWrite.getJpfPass().getText().trim().toString());
			psmt.executeUpdate();
			
			psmtF=con.prepareStatement("INSERT INTO FONT VALUES(NO_SEQ.CURRVAL,?,?)");
			psmtF.setString(1,boardWrite.getComboFont().getSelectedItem().toString());
			psmtF.setString(2,boardWrite.getComboSize().getSelectedItem().toString().substring(0, 2));
			psmtF.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(psmt!=null)psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	
	// ��Ϻ���
	public void getTable(BoardMain bm, String sql) {
		Connection con=ServiceUtil.getConnection();
		PreparedStatement psmt = null;
		Object[][] data = null;
		//String sql = "SELECT �۹�ȣ,����,	�۾���,TO_CHAR(�ۼ���,('YYYY-MM-DD')) �ۼ�,��ȸ  FROM BOARD ORDER BY �۹�ȣ  DESC" ;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String[] col = null;

		try {
			psmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = psmt.executeQuery();
			rsmd = rs.getMetaData();
			rs.last();
			data = new Object[rs.getRow()][rsmd.getColumnCount()];
			int i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				for (int j = 0; j < rsmd.getColumnCount(); j++) {
					data[i][j] = rs.getString(j + 1);
				}
				i++;
			}
			col = new String[rsmd.getColumnCount()];
			for (int j = 0; j < col.length; j++) {
				col[j] = rsmd.getColumnName(j + 1);
			}
			//System.out.println(data[0][0]);
			BoardJTable boardJTable = new BoardJTable(data);
			boardJTable.setColName(col);
			bm.getJtb().setModel(boardJTable);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (psmt != null)
					psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
