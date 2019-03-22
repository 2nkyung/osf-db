package osf.db.cons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student {

	public void select() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "osfu";
		String pwd = "12345678";

		Connection con = null;
		try {
			con = DriverManager.getConnection(url, id, pwd);
			Statement stmt = con.createStatement();
			String sql = "select ui_num, ui_name, ui_age from user_info";
			ResultSet rs = stmt.executeQuery(sql);
			List<HashMap<String, String>> rowList = new ArrayList<>();
			while (rs.next()) {
				HashMap<String, String> row = new HashMap<>();
				row.put("uiNum", rs.getString("ui_num"));
				row.put("uiName", rs.getString("ui_name"));
				row.put("uiAge", rs.getString("ui_age"));
				System.out.println(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "osfu";
		String pwd = "12345678";

		Connection con = null;
		try {
			con = DriverManager.getConnection(url, id, pwd);
			String sql = "insert into user_info(ui_num,ui_name,ui_age) values(80,'abc',5)";
			PreparedStatement ps = DBConnector.open().prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "osfu";
		String pwd = "12345678";

		Connection con = null;
		try {
			con = DriverManager.getConnection(url, id, pwd);
			String sql = "delete user_info where ui_num=?";
			PreparedStatement ps = DBConnector.open().prepareStatement(sql);
			ps.setString(1,"삭제할 번호");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "osfu";
		String pwd = "12345678";

		Connection con = null;
		try {
			con = DriverManager.getConnection(url, id, pwd);
			String sql = "";
			PreparedStatement ps = DBConnector.open().prepareStatement(sql);
			ps.setString(1,"삭제할 번호");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Student st = new Student();
		st.select();
	}
}