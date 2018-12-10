package mysql;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MySQL {
	public static final int DELETE = 1;
	public static final int INSERT = 2;
	public static final int UPDATE = 3;
	/**
	 * 查询数据库信息
	 * @param tableName 表格名
	 * @param columnIndex 选择列
	 * @param str 内容
	 * @param model 表格模板
	 */
	public static void showData(String tableName, int columnIndex, String str, DefaultTableModel model) {
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			ResultSet rs = db.executeQuery("select * from " + tableName, null);
			Vector<String> title = new Vector<String>();
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				title.add(rs.getMetaData().getColumnLabel(i));
			}
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> rowdata = new Vector<Object>();
				// 无特殊查询，则输出所有数据
				if (columnIndex == -1 || str == null) {
					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						String columnClassName = rs.getMetaData().getColumnClassName(i);// 获取数据类型
						if (columnClassName == "java.lang.Long") {
							rowdata.add(rs.getLong(i));
						}
						if (columnClassName == "java.lang.String") {
							rowdata.add(rs.getString(i));
						}
						if (columnClassName == "java.lang.Integer") {
							rowdata.add(rs.getInt(i));
						}
						if (columnClassName == "java.lang.Float") {
							rowdata.add(rs.getFloat(i));
						}
						if (columnClassName == "java.sql.Date") {
							rowdata.add(rs.getDate(i));
						}
					}
					data.add(rowdata);
				}
				else {
					// 有特殊查询时，输出符合条件的数据
					if (rs.getString(columnIndex).equals(str)) {
						for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
							String columnClassName = rs.getMetaData().getColumnClassName(i);// 获取数据类型
							if (columnClassName == "java.lang.Long") {
								rowdata.add(rs.getLong(i));
							}
							if (columnClassName == "java.lang.String") {
								rowdata.add(rs.getString(i));
							}
							if (columnClassName == "java.lang.Integer") {
								rowdata.add(rs.getInt(i));
							}
							if (columnClassName == "java.lang.Float") {
								rowdata.add(rs.getFloat(i));
							}
							if (columnClassName == "java.sql.Date") {
								rowdata.add(rs.getDate(i));
							}
						}
						data.add(rowdata);
					}
				}
			}
			model.setDataVector(data, title);
//			model.fireTableDataChanged();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeAll();
		}
	}
	/**
	 * 修改数据库信息
	 * @param i 选择操作
	 * @param tableName 表格名
	 * @param str 内容
	 * @param param[] 
	 */ 
	public static void updateData(int i, String tableName, String str, Object[] param) {
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			if (i == DELETE)// 删除
				db.executeUpdate("delete from " + tableName + " where " + str, param);// delete from table where 表头1=内容1
																						// and 表头2=内容2 and ...
			if (i == UPDATE)// 修改
				db.executeUpdate("update " + tableName + " set " + str, param);// update table 表头1=内容1,表头2=内容2,... where
																				// 表头1=内容1 and 表头2=内容2 and ...
			if (i == INSERT) {// 新增
				// 截取tableName中'('前面的部分
				String string = tableName.substring(0, tableName.indexOf("("));
				db.executeUpdate("ALTER TABLE " + string + " AUTO_INCREMENT =1", null);// 调整自动编号
				db.executeUpdate("insert into " + tableName + " values" + str, param);// insert into table(表头1,表头2,...)
																						// values(内容1,内容2,..)
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeAll();
		}
	}
	/**
	 * 查询数据库指定数据
	 * @param tableName 表格名
	 * @param str 内容
	 * @param param[] 
	 * @param column 选择列，若为null，则返回查询的所有内容
	 */ 
	public static Object selectData(String tableName, String str, Object[] param, Object column) {
		DBUtil db = new DBUtil();
		String string = "", string2 = "", columnClassName = null;
		int num = 0;
		float Profit = 0;
		try {
			db.getConnection();
			ResultSet rs = db.executeQuery("select * from " + tableName + " where " + str, param);
			while (rs.next()) {
				if (column == null)
					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						string += rs.getString(i) + "  ";
					}
				else {
					columnClassName = rs.getMetaData().getColumnClassName((int) column);
					
					if (columnClassName == "java.lang.Integer") {
						num += rs.getInt((int) column);
					} else if (columnClassName == "java.lang.Float") {
						Profit += rs.getFloat((int) column);
						}
						else {
							string2 = rs.getString((int) column);
						}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeAll();
		}
		if (column == null)
			return string;
		else {
			if (columnClassName == "java.lang.Integer")	
				return num;
			else if (columnClassName == "java.lang.Float")
				return Profit;
			else 
				return string2;
		}
	}
	//清除一周前的销售额数据
	public static void week_sale() {
		Date d = new Date();// 获取时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			ResultSet rs = db.executeQuery("select * from goods_sale", null);
			while (rs.next()) {
				//计算日期差
				Calendar aCalendar = Calendar.getInstance();
				aCalendar.setTime(rs.getDate(8));
				int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
				aCalendar.setTime(sdf.parse(sdf.format(d)));
				int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
				int days=day2-day1;
				if (days>6) {
					MySQL.updateData(MySQL.DELETE,"goods_sale","时间 = ?",new String[] {rs.getString(8)});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeAll();
		}
		
	}
}
