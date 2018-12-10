package tableFormat;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;
import sun.swing.table.DefaultTableCellHeaderRenderer;

public class TableFormat {
	/**
	 * 设置表头对齐方式
	 * @param jTable
	 * @param i
	 */
	public static void setHeaderAlignment(JTable jTable, int i) {
		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		if (i == 0) { // 设置表头数据左对齐
			hr.setHorizontalAlignment(JLabel.LEFT);
			jTable.getTableHeader().setDefaultRenderer(hr);
		}
		if (i == 1) { // 设置表头数据居中
			hr.setHorizontalAlignment(JLabel.CENTER);
			jTable.getTableHeader().setDefaultRenderer(hr);
		}
		if (i == 2) { // 设置表头数据右对齐
			hr.setHorizontalAlignment(JLabel.RIGHT);
			jTable.getTableHeader().setDefaultRenderer(hr);
		}
	}
	/**
	 * 设置表单的表头高度
	 * @param table
	 * @param height
	 */
	public static void setTableHeadHeight(JTable table, int height) {
		table.getTableHeader().setPreferredSize(new Dimension(1, height));
	}

	/**
	 * 设置表头的字体
	 * @param table
	 * @param font
	 */
	public static void setTableHeadFont(JTable table, Font font) {
		table.getTableHeader().setFont(font);
	}

	/**
	 * 设置表头的背景颜色
	 * @param table
	 * @param color
	 */
	public static void setTableHeadColor(JTable table, Color color) {
		table.getTableHeader().setBackground(color);
	}

	/**
	 * 设置表头文字的颜色
	 * @param table
	 * @param color
	 */
	public static void setTableHeadFontColor(JTable table, Color color) {
		table.getTableHeader().setForeground(color);
	}

	private boolean autoCreateRowSorter;
	/**
	 * 设置表格的所有行的行高
	 * @param table
	 * @param height
	 */
	public static void setTableAllRowHeight(JTable table, int height) {
		table.setRowHeight(height);
	}

	/**
	 * 设置表格的某一行的行高
	 * @param table
	 * @param row
	 * @param height
	 */
	public static void setTableOneRowHeight(JTable table, int row, int height) {
		table.setRowHeight(row, height);
	}

	/**
	 * 设置列表某一列的宽度
	 * @param table
	 * @param i
	 * @param preferedWidth
	 * @param maxWidth
	 * @param minWidth
	 */
	public static void setColumnSize(JTable table, int i, int preferedWidth, int maxWidth, int minWidth) {
		// 表格的列模型
		TableColumnModel cm = table.getColumnModel();
		// 得到第i个列对象
		TableColumn column = cm.getColumn(i);
		column.setPreferredWidth(preferedWidth);
		column.setMaxWidth(maxWidth);
		column.setMinWidth(minWidth);
	}

	/**
	 * 设置列表某几列的宽度
	 * @param table
	 * @param i
	 * @param preferedWidth
	 * @param maxWidth
	 * @param minWidth
	 */
	public static void setSomeColumnSize(JTable table, int[] i, int preferedWidth, int maxWidth, int minWidth) {
		TableColumnModel cm = table.getColumnModel();
		if (i.length == 0) {
			return;
		}
		for (int j = 0; j < i.length; j++) {
			TableColumn column = cm.getColumn(i[j]);
			column.setPreferredWidth(preferedWidth);
			column.setMaxWidth(maxWidth);
			column.setMinWidth(minWidth);
		}
	}

	/**
	 * 填充数据并排序后显示
	 * @param table
	 * @param tableHead
	 * @param data
	 * @param colu
	 */
	public static void changeAndSortTable(JTable table, Object[] tableHead, Object[][] data, int colu) {
		TableModel model = new DefaultTableModel(data, tableHead) {
			private static final long serialVersionUID = 1L;

			// 设置表格不可编辑
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				Class<Integer> returnValue = Integer.class;
				return returnValue;
			}
		};
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);

		// 对某列进行从大到小排序
		List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		sortKeys.add(new RowSorter.SortKey(colu - 1, SortOrder.DESCENDING));// 列，排序方式 //从大到小排序
		sorter.setSortKeys(sortKeys);

		// 设置某一列可以排序其他不行
		sorter.setSortable(colu - 1, true);
		for (int i = 0; i < table.getColumnCount(); i++) {
			if (i != colu - 1)
				sorter.setSortable(i, false);
		}
	}
	/**
	 * 设置数据对齐方式
	 * @param jTable
	 * @param i
	 */
	public static void setDataAlignment(JTable jTable, int i) {

		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		if (i == 0) { // 设置表格数据左对齐
			r.setHorizontalAlignment(JLabel.LEFT);
			jTable.setDefaultRenderer(Object.class, r);
		}
		if (i == 1) { // 设置表格数据居中
			r.setHorizontalAlignment(JLabel.CENTER);
			jTable.setDefaultRenderer(Object.class, r);
		}
		if (i == 2) { // 设置表格数据右对齐
			r.setHorizontalAlignment(JLabel.RIGHT);
			jTable.setDefaultRenderer(Object.class, r);
		}
	}
	/**
	 * 根据值获取表格中对应的行
	 * @param table
	 * @param str
	 */
	public static int getOneRow(JTable table, String str) {
		int col, row,row1 = -1;
		col = table.getColumnCount();
		row = table.getRowCount();
		Object[][] data = new Object[row][col];
		for (int i = 0; i < table.getColumnCount(); i++) {
			for (int j = 0; j < table.getRowCount(); j++) {
				if(str.equals(table.getValueAt(j, i))==true)
					row1 = j;
			}
		}
		if(row1!=-1) {
			table.setRowSelectionInterval(row1, row1);//定位
			table.scrollRectToVisible(table.getCellRect(row1, 0, true));
			table.setSelectionBackground(Color.BLACK);
		}
		return row1;
	}
	/**
	 * 表格定位到某一行
	 * @param table
	 * @param row1
	 * @param row2
	 */
	public static void scrollRectToVisible_Row(JTable table, int row1,int row2,
			Color color) {
		table.setRowSelectionInterval(row1, row2);//定位
		table.scrollRectToVisible(table.getCellRect(row1, 0, true));
		table.setSelectionBackground(color);
	}
	/**
	 * 设置表格的某一列的背景色
	 * @param table
	 * @param ColumnIndex
	 * @param color
	 */
	public static void setOneColumnBackgroundColor(JTable table, int ColumnIndex,
			Color color) {
		//获取某一列
		TableColumn tableColumn = table.getColumnModel().getColumn(ColumnIndex); 
		//DefaultTableCellRenderer类可以绘制单元格的背景、字体颜色等功能   
		DefaultTableCellRenderer backGroundColor = new DefaultTableCellRenderer();   
	 	//绘制列的背景色   
		backGroundColor.setBackground(color);   
		tableColumn.setCellRenderer(backGroundColor);  
	}
	/**
	 * 设置表格的某一行的背景色
	 * @param table
	 * @param row1
	 * @param row2
	 */
	public static void setOneRowBackgroundColor(JTable table, int row1,int row2,
			Color color) {
		table.setRowSelectionInterval(row1, row2);//定位
		table.setSelectionBackground(color);
	}
}
