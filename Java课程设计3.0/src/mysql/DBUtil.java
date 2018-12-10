package mysql;

import java.sql.*;

public class DBUtil {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public Connection getConnection()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		String driver = Config.getValue("driver");
		String url = Config.getValue("url");
		String user = Config.getValue("user");
		String pwd = Config.getValue("password");
//		System.out.println(driver + "\n" + url + "\n" + user + "\n" + pwd);
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			throw new SQLException("驱动错误或连接失败");
		}
	}

	public void closeAll() {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public ResultSet executeQuery(String preparedSql, Object[] param) {
		try {
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}

	// preparedSql 进行修改数据的SQL语句，param 给占位符传参数
	public int executeUpdate(String preparedSql, Object[] param) {
		int num = 0;
		try {
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			num = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return num;
	}
	// public static void main(String[] args) throws ClassNotFoundException,
	// InstantiationException, IllegalAccessException, SQLException {
	// DBUtil db = new DBUtil();
	// db.getConnection();
	// db.closeAll();
	// }
}
