package myScrollTable;

import java.awt.*;
import javax.swing.*;

//带表格的滚动面板
public class MyScrollTable extends JScrollPane {

	JTable table = new JTable();
	public MyScrollTable(int width, int height) {
		this.setViewportView(table);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);// 显示竖滚动条
//		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);// 显示横滚动条
		this.setPreferredSize(new Dimension(width, height));
//		table.setShowGrid(true);//显示框线
//		table.setGridColor(new Color(133, 138, 131));//框线颜色
//		table.setShowHorizontalLines(false);//是否放置水平线
//		table.setShowVerticalLines(false);//是否放置水平线
	}
	// 获取表格对象
	public JTable getTable() {
		return table;
	}
	
}
