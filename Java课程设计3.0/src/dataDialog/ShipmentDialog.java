package dataDialog;

import menu.Menu;
import mysql.MySQL;

import java.awt.Color;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class ShipmentDialog extends JDialog {
	JLabel lab1, lab2, lab3, lab4;
	public JTextField jtf1,jtf2;
	JButton confirm;

	public ShipmentDialog(Menu jFrame, String str) {
		this.setTitle("商品出库");
		this.setSize(250, 200);
		this.setLocation(jFrame.getX()+100, jFrame.getY()+100);
		this.setLayout(null);
		this.setAlwaysOnTop(true);//最上层
		lab1 = new JLabel("出货商品名称             库存        ");
		lab2 = new JLabel(str);
		lab3 = new JLabel("出货数量：");
		lab4 = new JLabel("售价：");
		lab1.setBounds(2, 0, 200, 30);
		lab2.setBounds(15, 40, 185, 30);
		lab3.setBounds(2, 80, 70, 30);
		lab4.setBounds(125, 80, 50, 30);
		jtf1 = new JTextField();
		jtf1.setBounds(70, 80, 50, 30);
		jtf2 = new JTextField();
		jtf2.setBounds(170, 80, 50, 30);

		confirm = new JButton("确认");
		confirm.setBounds(90, 110, 60, 30);
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (Integer.parseInt(jtf1.getText()) > Integer.parseInt(str.substring(str.indexOf(" ") + 19))) {
					jFrame.messageDialog(1);
					jFrame.logrecData(jFrame.SHIPMENT);//日志记录
				}
				else {
					Date d = new Date();// 获取时间
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					
					String goods_Type,goods_Name,goods_Pur,goods_Sell,goods_Sale,goods_Profit;
					goods_Type = (String) MySQL.selectData("goods", "名称 = ?",
									new String[] { str.substring(0, str.indexOf(" ")) }, 2);
					goods_Name = str.substring(0, str.indexOf(" "));
					goods_Pur = Float.toString((Float) MySQL.selectData("goods", "名称 = ?",
									new String[] { str.substring(0, str.indexOf(" ")) }, 5));
					goods_Sell = jtf2.getText();
					goods_Sale = jtf1.getText();
					goods_Profit = Float.toString((Float.parseFloat(goods_Sell)-Float.parseFloat(goods_Pur))*Float.parseFloat(goods_Sale));
					//记录出货数据
					MySQL.updateData(MySQL.INSERT, "goodsout(商品类别,名称,数量,进价,售价,出货日期,生产厂家)", "(?,?,?,?,?,?,?)",
							new String[] {
									goods_Type,goods_Name,goods_Sale,goods_Pur,goods_Sell,sdf.format(d),
									(String) MySQL.selectData("goods", "名称 = ?",
											new String[] { str.substring(0, str.indexOf(" ")) }, 7) });
					//库存减少
					String strNum = Integer.toString((int) MySQL.selectData("goods", "名称 = ?",
							new String[] { str.substring(0, str.indexOf(" "))}, 4)
							- Integer.parseInt(jtf1.getText()));
					MySQL.updateData(MySQL.UPDATE, "goods","库存 = ? where 名称 = ?",				
							new String[] { strNum,str.substring(0, str.indexOf(" "))});
					//记录商品销售额和利润
					MySQL.updateData(MySQL.INSERT, "goods_sale(商品类别,名称,进价,售价,销售额,利润,时间)", "(?,?,?,?,?,?,?)",
							new String[] {goods_Type,goods_Name,goods_Pur,goods_Sell,goods_Sale,goods_Profit,sdf.format(d)});					
					jFrame.logrecData(jFrame.SHIPMENT+5);//日志记录
					MySQL.showData("goodsout",-1,null, jFrame.model);
					jFrame.sp2.getTable().setModel(jFrame.model);
					jFrame.columnWidth(2);
//					MySQL.showData("goods", -1, null, jFrame.model1);
//					jFrame.sp3.getTable().setModel(jFrame.model1);
//					jFrame.rowSorter(jFrame.sp3.getTable(),4);
//					
//					MySQL.showData("goods_sale", -1, null, jFrame.model2);
//					jFrame.sp3_2.getTable().setModel(jFrame.model2);
//					jFrame.rowSorter(jFrame.sp3_2.getTable(),7);
//					jFrame.columnWidth(3);
					
					ShipmentDialog.this.dispose();
				}
			}
		});
		this.add(lab1);
		this.add(lab2);
		this.add(lab3);
		this.add(lab4);
		this.add(jtf1);
		this.add(jtf2);
		this.add(confirm);
		this.getContentPane().setBackground(new Color(233, 238, 241));
		this.setVisible(true);
	}
}