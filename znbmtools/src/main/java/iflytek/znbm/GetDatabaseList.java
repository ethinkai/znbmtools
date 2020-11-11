package iflytek.znbm;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class GetDatabaseList {
	/**
	 * 从ed_dossier中获取id和id最后一位
	 * @param endNum  浏览器URL--dossier_no后几位
	 * @param idOrSubid 确定获取最后一位还是id,Subid时为最后一位，其他id
	 * @return
	 */
	public String getEndNum(String endNum,String idOrSubid) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs =null;
		String id=null;
		String url ="jdbc:mysql://139.2.14.83:3306/scs2.5_dev?serverTimezone=UTC&useSSL=false";
		String username="root";
		String password="iflytek";
		String sql="select id from ed_dossier where dossier_no like "+"\'%"+endNum+"\'"; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url,username,password);
			ps = (PreparedStatement) conn.prepareStatement(sql);
			System.out.println(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getString(1);
			}
			int start = id.length()-1;
			int end = id.length();
			if(idOrSubid == "Subid") {
				return id.substring(start,end);
			}else {
				return id;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "无法获取";
		}finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}
	/**
	 * 从 ed_file_N中获取id以及parent_id的list集合
	 * @param sql
	 * @return
	 */
	public List<String> getList(String sql) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs =null;
		String id=null;
		String url ="jdbc:mysql://139.2.14.83:3306/scs2.5_dev?serverTimezone=UTC&useSSL=false";
		String username="root";
		String password="iflytek";
		List<String> list = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url,username,password);
			ps = (PreparedStatement) conn.prepareStatement(sql);
			System.out.println(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public static void main(String[] args) {
		
//		String str="123145136";
//		int start=str.length()-1;
//		int end=str.length();
//		System.out.print(str.substring(start,end));
//		String endStr = new GetDatabaseList().getEndNum("98b65ad4");
//		System.out.println(endStr);
		//98b65ad4为URL后面的dossier_no后几位
		String endNum="cb2ba8b";
		
		String endStr = new GetDatabaseList().getEndNum(endNum, "Subid");
		String id =  new GetDatabaseList().getEndNum(endNum,"");
		String sql1 = "SELECT id  from ed_file_"+ endStr + " WHERE dossier_id="+"\'"+ id+"\'" +" and type=2 ORDER BY id desc;";
		String sql2 = "SELECT distinct parent_id  from ed_file_"+ endStr + " WHERE dossier_id="+"\'"+ id+"\'" +" and type=1 ;";
		List<String> listA = new GetDatabaseList().getList(sql1);
		List<String> listB = new GetDatabaseList().getList(sql2);
		//获取不同的目录ID，便于从数据库修改修改，参考下面语句
		//select id,parent_id,cont_name,manual_name from ed_file_6 where dossier_id='1458836' and type=1;
		new ZnbmTools().getDiffer(listB, listA);
	}

}
