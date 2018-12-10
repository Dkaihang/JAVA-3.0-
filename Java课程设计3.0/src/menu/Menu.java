package menu;

import mysql.MySQL;
import statisticsDialog.Statistics;
import myScrollTable.MyScrollTable;
import tableFormat.*;
import dataDialog.ShipmentDialog;
import dateChoose.DateChoose;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu extends JFrame {
	public static final int STOCK = 1;// 进货
	public static final int SHIPMENT = 2;// 出货
	public static final int INQUIRE = 3;// 查询
	public static final int DELETE = 4;// 删除
	public static final int INSERT = 5;// 新增
	public static final int UPDATE = 6;// 修改
	String goods_Type, goods_Name;
	int x, y, xx, yy;
	boolean sign;
	JButton b1, b2, b3, b4, b5, min, close, add, jian, choose_insert, choose_inquire, choose_update, choose_delete,
			insert, inquire, update, back, delete;
	JPanel tip, pLift, pRight, p1, p2, p4, p4_insert, p4_inquire, p4_update, p4_delete;
	JLabel lTip, bJing, lab1, lab2, lab3_1, lab3_2, lab4, lab4_1, lab4_2, lab4_3, lab4_4;
	JComboBox jc1, jc2, jc4_1, jc4_2, jc4_3, jc4_4;
	JTextField jtf1_1, jtf1_2, jtf1_3, jtf1_4, jtf1_5, jtf2, jtf4_1_1, jtf4_1_2, jtf4_1_3, jtf4_1_4, jtf4_1_5, jtf4_2_1,
			jtf4_3_1, jtf4_3_2, jtf4_4_1;
	Color color1 = new Color(31, 36, 56);// #1F2438
	Color color2 = new Color(233, 238, 241);// #E9EEF1
	Font font1 = new Font("微软雅黑", Font.PLAIN, 13);

	public DefaultTableModel model = new DefaultTableModel();
	public DefaultTableModel model1 = new DefaultTableModel();
	public DefaultTableModel model2 = new DefaultTableModel();
	MyScrollTable sp1;
	public MyScrollTable sp2;
	public MyScrollTable sp3;
	public MyScrollTable sp3_2;
	MyScrollTable sp4;
	MyScrollTable sp5;
	ShipmentDialog sDialog;
	TableColumn column1, column2, column3, column4, column5, column6, column7, column8 = null;// 列
	Statistics st;
	DateChoose date1, date2;
	Menu jFrame = this;// 获取窗体对象

	public Menu() {
		this.setSize(550, 400);
		this.setLocation(200, 100);
		frameListener();

		lTip = new JLabel(new ImageIcon("image\\tip10.jpg")); // 标题背景
		bJing = new JLabel(new ImageIcon("image\\bjing8.jpg"));// 右面板初始图片
		lab1 = new JLabel(" 商品类型   商品名称   进货数量     进 价     进货日期   生产厂家");
		lab2 = new JLabel("                    商品类型   商品名称");
		lab1.setPreferredSize(new Dimension(466, 20));
		lab2.setPreferredSize(new Dimension(466, 20));
		lab1.setFont(new Font("宋体", Font.PLAIN, 13));
		lab2.setFont(new Font("宋体", Font.PLAIN, 13));

		lab3_1 = new JLabel(" 当前商品库存");
		lab3_2 = new JLabel(" 商品一周的销售额和利润");
		lab3_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lab3_2.setFont(new Font("宋体", Font.PLAIN, 15));
		lab3_1.setPreferredSize(new Dimension(466, 24));
		lab3_2.setPreferredSize(new Dimension(466, 23));
		
		lab4 = new JLabel("        请选择以下功能（注：修改功能在查询功能使用后自动显示）         ");
		lab4_1 = new JLabel("<html>&nbsp&nbsp新增功能:<br/>"
				+ "&nbsp&nbsp商品类型   &nbsp商品名称  &nbsp 进货数量     &nbsp&nbsp进 价 &nbsp&nbsp进货日期   &nbsp&nbsp生产厂家 &nbsp&nbsp</html>");// 通过html形式实现换行
		lab4_2 = new JLabel("<html><body>查询功能:<br>" + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + "查询类型 "
				+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<body></html>");
		lab4_3 = new JLabel("<html><body>&nbsp修改功能（注：选择ID定位后，选择修改类型进行修改，返回键返回上一级）:<br>" + "&nbsp&nbsp&nbsp&nbsp&nbsp"
				+ "输入ID &nbsp&nbsp&nbsp修改类型 "
				+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<body></html>");
		lab4_4 = new JLabel("<html><body>删除功能:<br>" + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + "商品类型 &nbsp商品名称"
				+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<body></html>");
		lab4.setFont(new Font("宋体", Font.PLAIN, 13));
		lab4_1.setFont(new Font("宋体", Font.PLAIN, 13));
		lab4_2.setFont(new Font("宋体", Font.PLAIN, 13));
		lab4_3.setFont(new Font("宋体", Font.PLAIN, 13));
		lab4_4.setFont(new Font("宋体", Font.PLAIN, 13));

		jc1 = new JComboBox(new String[] { "食品", "饮料", "日用品", "化妆品" });
		jc1.setPreferredSize(new Dimension(70, 30));
		jc2 = new JComboBox(new String[] { "食品", "饮料", "日用品", "化妆品" });
		jc2.setPreferredSize(new Dimension(70, 30));
		jc4_1 = new JComboBox(new String[] { "食品", "饮料", "日用品", "化妆品" });
		jc4_1.setPreferredSize(new Dimension(70, 30));
		jc4_2 = new JComboBox(new String[] { "商品类型", "商品名称", "进货日期", "生产厂家" });
		jc4_2.setPreferredSize(new Dimension(100, 30));
		jc4_3 = new JComboBox(new String[] { "商品类型", "商品名称", "库存", "进价", "进货日期", "生产厂家" });
		jc4_3.setPreferredSize(new Dimension(100, 30));
		jc4_4 = new JComboBox(new String[] { "食品", "饮料", "日用品", "化妆品" });
		jc4_4.setPreferredSize(new Dimension(70, 30));

		jc1.setFont(new Font("宋体", Font.PLAIN, 12));
		jc2.setFont(new Font("宋体", Font.PLAIN, 12));
		jc4_1.setFont(new Font("宋体", Font.PLAIN, 12));
		jc4_2.setFont(new Font("宋体", Font.PLAIN, 12));
		jc4_3.setFont(new Font("宋体", Font.PLAIN, 12));
		jc4_4.setFont(new Font("宋体", Font.PLAIN, 12));

		jtf1_1 = new JTextField();
		jtf1_1.setPreferredSize(new Dimension(80, 30));
		jtf1_2 = new JTextField();
		jtf1_2.setPreferredSize(new Dimension(80, 30));
		jtf1_3 = new JTextField();
		jtf1_3.setPreferredSize(new Dimension(80, 30));

		jtf1_4 = new JTextField();
		jtf1_4.setPreferredSize(new Dimension(80, 30));

		jtf1_5 = new JTextField();
		jtf1_5.setPreferredSize(new Dimension(80, 30));

		jtf1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				date1.dispose();
			}
		});
		jtf1_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				date1.dispose();
			}
		});
		jtf1_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				date1.dispose();
			}
		});
		jtf1_5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				date1.dispose();
			}
		});

		jtf2 = new JTextField();
		jtf2.setPreferredSize(new Dimension(80, 30));

		jtf4_1_1 = new JTextField();
		jtf4_1_1.setPreferredSize(new Dimension(80, 30));
		jtf4_1_2 = new JTextField();
		jtf4_1_2.setPreferredSize(new Dimension(80, 30));
		jtf4_1_3 = new JTextField();
		jtf4_1_3.setPreferredSize(new Dimension(80, 30));
		jtf4_1_4 = new JTextField();
		jtf4_1_4.setPreferredSize(new Dimension(80, 30));
		jtf4_1_5 = new JTextField();
		jtf4_1_5.setPreferredSize(new Dimension(80, 30));
		jtf4_1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				date2.dispose();
			}
		});
		jtf4_1_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				date2.dispose();
			}
		});
		jtf4_1_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				date2.dispose();
			}
		});
		jtf4_1_5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				date2.dispose();
			}
		});

		jtf4_2_1 = new JTextField();
		jtf4_2_1.setPreferredSize(new Dimension(80, 30));
		jtf4_3_1 = new JTextField();
		jtf4_3_1.setPreferredSize(new Dimension(80, 30));
		jtf4_3_2 = new JTextField();
		jtf4_3_2.setPreferredSize(new Dimension(80, 30));
		jtf4_4_1 = new JTextField();
		jtf4_4_1.setPreferredSize(new Dimension(80, 30));

		sp1 = new MyScrollTable(470, 260);
		sp1.setVisible(false);
		sp2 = new MyScrollTable(470, 260);
		sp2.setVisible(false);
		sp3 = new MyScrollTable(470, 160);
		sp3.setVisible(false);
		sp3_2 = new MyScrollTable(470, 160);
		sp3_2.setVisible(false);
		sp4 = new MyScrollTable(470, 180);
		sp4.setVisible(false);
		sp5 = new MyScrollTable(470, 367);
		sp5.setVisible(false);

		sp1.getTable().setEnabled(false);// 表格不可编辑
		sp2.getTable().setEnabled(false);
		sp3.getTable().setEnabled(false);
		sp3_2.getTable().setEnabled(false);
		sp4.getTable().setEnabled(false);
		sp5.getTable().setEnabled(false);
		TableFormat.setDataAlignment(sp1.getTable(), 2);// 数据右对齐显示
		TableFormat.setDataAlignment(sp2.getTable(), 2);
		TableFormat.setDataAlignment(sp3.getTable(), 2);
		TableFormat.setDataAlignment(sp3_2.getTable(), 2);
		TableFormat.setDataAlignment(sp4.getTable(), 2);
		TableFormat.setDataAlignment(sp5.getTable(), 2);

		button();
		panel();
		this.add(min);
		this.add(close);
		this.add(pLift);
		this.add(pRight);
		this.add(tip);
		this.setUndecorated(true);// 去掉窗口自带的边框
		this.setVisible(true);
		this.setResizable(false);// 不可改变窗体大小，true为可改变
	}
	/**
	 * 窗口事件监听
	 * 
	 */
	public void frameListener() {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				sign = true;
				xx = e.getX();
				yy = e.getY();
			}
			public void mouseReleased(MouseEvent e) {
				sign = false;
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (sign) {
					int left = getLocation().x;
					int top = getLocation().y;
					setLocation(left + e.getX() - xx, top + e.getY() - yy);
					// 必须在父窗口获取新的坐标后使用
					if (st != null)
						st.setLocation(getLocation().x + 560, getLocation().y);// 移动统计图窗口
					date1.setLocation(getLocation().x + 365, getLocation().y + jtf1_4.getHeight() + 310);
					date2.setLocation(getLocation().x + 365, getLocation().y + jtf4_1_4.getHeight() + 310);
					if (sDialog != null)
						sDialog.setLocation(getLocation().x + 100, getLocation().y + 100);
				}
			}
		});
		// 窗口图标化后在正常显示
		this.addWindowStateListener(new WindowAdapter() {
			@Override
			public void windowStateChanged(WindowEvent e) {// 状态监听
				if (e.getOldState() == JFrame.ICONIFIED) {
					setExtendedState(JFrame.NORMAL);// 正常化
					if (sp3.isVisible() == true && st != null) {
						st.setVisible(true);// 正常化
					}
				}
			}

		});
		// 窗体事件检测监听
		this.addWindowListener(new WindowAdapter() {
			// 窗体最小化(图标化)事件触发
			public void windowIconified(WindowEvent e) {
				date1.dispose(); // 关闭日期选择器
				date2.dispose();
				if (st != null)
					st.dispose(); // 关闭统计图
				if (sDialog != null)
					sDialog.dispose();
				// System.out.println("最小化...");
			}
		});
	}
	/**
	 * 界面的所有面板
	 * 
	 */
	private void panel() {
		tip = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		pLift = new JPanel(new FlowLayout(FlowLayout.LEFT, -4, 0));
		pRight = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, -1, 0));
		p2 = new JPanel();
		p4 = new JPanel();
		p4_insert = new JPanel(new FlowLayout(FlowLayout.CENTER, -1, 0));
		p4_inquire = new JPanel();
		p4_update = new JPanel();
		p4_delete = new JPanel();

		tip.setOpaque(false);
		tip.setBounds(0, 0, 550, 32);
		pLift.setBounds(0, 32, 80, 367);
		pRight.setBounds(80, 32, 470, 367);
		pLift.setBackground(color1);
		pRight.setBackground(color2);

		p1.setPreferredSize(new Dimension(466, 100));
		p2.setPreferredSize(new Dimension(466, 100));
		p4.setPreferredSize(new Dimension(466, 65));
		p4_insert.setPreferredSize(new Dimension(466, 100));
		p4_inquire.setPreferredSize(new Dimension(466, 100));
		p4_update.setPreferredSize(new Dimension(466, 100));
		p4_delete.setPreferredSize(new Dimension(466, 100));
		p1.setBackground(color2);
		p2.setBackground(color2);
		p4.setBackground(color2);
		p4_insert.setBackground(color2);
		p4_inquire.setBackground(color2);
		p4_update.setBackground(color2);
		p4_delete.setBackground(color2);

		date1 = new DateChoose(this, jtf1_4, "yyyy-MM-dd");
		date1.initCalendarPanel();
		date2 = new DateChoose(this, jtf4_1_4, "yyyy-MM-dd");
		date2.initCalendarPanel();
		
		// 加边框
		pLift.setBorder(BorderFactory.createLineBorder(color1));
		pRight.setBorder(BorderFactory.createLineBorder(color1));

		tip.add(lTip);
		p1.add(lab1);
		p1.add(jc1);
		p1.add(jtf1_1);
		p1.add(jtf1_2);
		p1.add(jtf1_3);
		p1.add(jtf1_4);
		p1.add(jtf1_5);
		p1.add(add);
		
		p2.add(lab2);
		p2.add(jc2);
		p2.add(jtf2);
		p2.add(jian);

		p4.add(lab4);
		p4.add(choose_insert);
		p4.add(choose_inquire);
		p4.add(choose_delete);

		p4_insert.add(lab4_1);
		p4_insert.add(jc4_1);
		p4_insert.add(jtf4_1_1);
		p4_insert.add(jtf4_1_2);
		p4_insert.add(jtf4_1_3);
		p4_insert.add(jtf4_1_4);
		p4_insert.add(jtf4_1_5);
		p4_insert.add(insert);

		p4_inquire.add(lab4_2);
		p4_inquire.add(jc4_2);
		p4_inquire.add(jtf4_2_1);
		p4_inquire.add(inquire);

		p4_update.add(lab4_3);
		p4_update.add(jtf4_3_1);
		p4_update.add(jc4_3);
		p4_update.add(jtf4_3_2);
		p4_update.add(update);
		p4_update.add(back);

		p4_delete.add(lab4_4);
		p4_delete.add(jc4_4);
		p4_delete.add(jtf4_4_1);
		p4_delete.add(delete);

		pLift.add(b1);
		pLift.add(b2);
		pLift.add(b3);
		pLift.add(b4);
		pLift.add(b5);

		pRight.add(bJing);
		pRight.add(sp1);
		pRight.add(sp2);
		pRight.add(lab3_1);
		pRight.add(sp3);
		pRight.add(lab3_2);
		pRight.add(sp3_2);
		pRight.add(sp4);
		pRight.add(sp5);
		pRight.add(p1);
		pRight.add(p2);
		pRight.add(p4);
		pRight.add(p4_insert);
		pRight.add(p4_inquire);
		pRight.add(p4_update);
		pRight.add(p4_delete);
	}
	/**
	 * 错误消息提示框
	 * @param i       选择提示消息内容：1-库存不足；2-记录不存在；3-商品不存在
	 */
	public void messageDialog(int i) {
		if (i == 1)
			JOptionPane.showMessageDialog(this, "库存不足", "错误", JOptionPane.ERROR_MESSAGE);
		if (i == 2)
			JOptionPane.showMessageDialog(this, "该记录不存在", "错误", JOptionPane.ERROR_MESSAGE);
		if (i == 3)
			JOptionPane.showMessageDialog(this, "该商品不存在", "错误", JOptionPane.ERROR_MESSAGE);

	}
	/**
	 * 界面中所有的按钮
	 */
	private void button() {

		b1 = new JButton(new ImageIcon("image\\an01.png"));// "进货"
		b2 = new JButton(new ImageIcon("image\\an02.png"));// "出货"
		b3 = new JButton(new ImageIcon("image\\an03.png"));// "统计"
		b4 = new JButton(new ImageIcon("image\\an04.png"));// "管理"
		b5 = new JButton(new ImageIcon("image\\an05.png"));// "日志"

		// 设置面板内组件大小
		b1.setPreferredSize(new Dimension(90, 35));
		b2.setPreferredSize(new Dimension(90, 35));
		b3.setPreferredSize(new Dimension(90, 35));
		b4.setPreferredSize(new Dimension(90, 35));
		b5.setPreferredSize(new Dimension(90, 35));

		b1.setContentAreaFilled(false); // 设置背景透明方法二
		b1.setFocusPainted(false);
		b1.setBorderPainted(false); // 去除按钮边框
		b1.setRolloverIcon(new ImageIcon("image\\an11.png"));// 鼠标经过按钮时显示图标

		b1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu.this.setVisible(1);
			}
		});

		b2.setContentAreaFilled(false); // 设置背景透明方法二
		b2.setFocusPainted(false);
		b2.setBorderPainted(false); // 去除按钮边框
		b2.setRolloverIcon(new ImageIcon("image\\an12.png"));// 鼠标经过按钮时显示图标

		b2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu.this.setVisible(2);
			}
		});

		b3.setContentAreaFilled(false); // 设置背景透明方法二
		b3.setFocusPainted(false);
		b3.setBorderPainted(false); // 去除按钮边框
		b3.setRolloverIcon(new ImageIcon("image\\an13.png"));// 鼠标经过按钮时显示图标

		b3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu.this.setVisible(3);
			}
		});
		b4.setContentAreaFilled(false); // 设置背景透明方法二
		b4.setFocusPainted(false);
		b4.setBorderPainted(false); // 去除按钮边框
		b4.setRolloverIcon(new ImageIcon("image\\an14.png"));// 鼠标经过按钮时显示图标
		b4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu.this.setVisible(4);
			}
		});

		b5.setContentAreaFilled(false); // 设置背景透明方法二
		b5.setFocusPainted(false);
		b5.setBorderPainted(false); // 去除按钮边框
		b5.setRolloverIcon(new ImageIcon("image\\an15.png"));// 鼠标经过按钮时显示图标
		b5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu.this.setVisible(5);
			}
		});

		min = new JButton(new ImageIcon("image\\3.png")); // 最小化
		close = new JButton(new ImageIcon("image\\1.png")); // 关闭

		min.setContentAreaFilled(false); // 设置背景透明方法二setOpaque(true);//
		min.setBorderPainted(false); // 去除按钮边框

		min.setRolloverIcon(new ImageIcon("image\\4.png"));// 鼠标经过按钮时显示图标
		min.setBounds(486, 0, 33, 33);
		// 最小化
		min.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED);
				if(st!=null)
					st.dispose();
				date1.dispose();
				date2.dispose();
			}
		});

		close.setContentAreaFilled(false); // 设置背景透明方法二
		close.setBorderPainted(false); // 去除按钮边框

		close.setRolloverIcon(new ImageIcon("image\\2.png"));// 鼠标经过按钮时显示图标
		close.setBounds(519, 0, 33, 33);
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		add = new JButton("进货"); // 进货
		jian = new JButton("出货"); // 出货
		add.setFont(new Font("宋体", Font.PLAIN, 12));
		jian.setFont(new Font("宋体", Font.PLAIN, 12));
		add.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				date1.dispose();
				// 记录进货数据 
				MySQL.updateData(MySQL.INSERT, "goodsin(商品类别,名称,数量,进价,进货日期,生产厂家)", "(?,?,?,?,?,?)",
						new String[] { (String) jc1.getSelectedItem(), jtf1_1.getText(), jtf1_2.getText(),
								jtf1_3.getText(), jtf1_4.getText(), jtf1_5.getText() });
				// 在库存中增加商品数据
				if (MySQL.selectData("goods", "商品类别 = ? and 名称 = ?",
						new String[] { (String) jc1.getSelectedItem(), jtf1_1.getText() }, null) == "")
					MySQL.updateData(MySQL.INSERT, "goods(商品类别,名称,库存,进价,进货日期,生产厂家)", "(?,?,?,?,?,?)",
							new String[] { (String) jc1.getSelectedItem(), jtf1_1.getText(), jtf1_2.getText(),
									jtf1_3.getText(), jtf1_4.getText(), jtf1_5.getText() });
				else {
					// 库存增加
					String str = Integer.toString((int) MySQL.selectData("goods", "商品类别 = ? and 名称 = ?",
							new String[] { (String) jc1.getSelectedItem(), jtf1_1.getText() }, 4)
							+ Integer.parseInt(jtf1_2.getText()));
					MySQL.updateData(MySQL.UPDATE, "goods",
							"库存 = ? , 进价 = ? , 进货日期 = ? , 生产厂家 = ? where 商品类别 = ? and 名称 = ?",
							new String[] { str, jtf1_3.getText(), jtf1_4.getText(), jtf1_5.getText(),
									(String) jc1.getSelectedItem(), jtf1_1.getText() });
				}
				logrecData(STOCK);// 日志记录
				MySQL.showData("goodsin", -1, null, model);
				sp1.getTable().setModel(model);
				columnWidth(1);
				TableFormat.scrollRectToVisible_Row(sp1.getTable(), sp1.getTable().getRowCount() - 1,
						sp1.getTable().getRowCount() - 1, Color.green);
				jc1.setSelectedIndex(0);
				jtf1_1.setText("");
				jtf1_2.setText("");
				jtf1_3.setText("");
				jtf1_4.setText("");
				jtf1_5.setText("");
				validate();// 刷新组件
			}
		});
		jian.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				goods_Type = (String) jc2.getSelectedItem();
				goods_Name = jtf2.getText();

				if (MySQL.selectData("goods", "商品类别 = ? and 名称 = ?", new String[] { goods_Type, goods_Name },
						null) == "") {
					messageDialog(2);
					logrecData(SHIPMENT);// 日志记录
				} else {
					sDialog = new ShipmentDialog(jFrame,
							(String) MySQL.selectData("goods", "商品类别 = ? and 名称 = ?",
									new String[] { goods_Type, goods_Name }, 3) + "                   "
									+ Integer.toString((int) MySQL.selectData("goods", "商品类别 = ? and 名称 = ?",
											new String[] { goods_Type, goods_Name }, 4)));
				}
				MySQL.showData("goodsout", -1, null, model);
				sp2.getTable().setModel(model);
				columnWidth(2);
				
				jc2.setSelectedIndex(0);
				jtf2.setText("");
				validate();// 刷新组件
			}
		});
		choose_insert = new JButton("新增"); // 选择 新增 功能
		choose_inquire = new JButton("查询"); // 选择 查询 功能
		// choose_update = new JButton("修改"); // 选择 修改 功能
		choose_delete = new JButton("删除"); // 选择 删除 功能
		insert = new JButton("新增"); // 新增
		inquire = new JButton("查询"); // 查询
		update = new JButton("修改"); // 修改
		back = new JButton("返回"); // 返回
		delete = new JButton("删除"); // 删除
		choose_insert.setFont(new Font("宋体", Font.PLAIN, 12));
		choose_inquire.setFont(new Font("宋体", Font.PLAIN, 12));
		// choose_update.setFont(new Font("宋体", Font.PLAIN, 12));
		choose_delete.setFont(new Font("宋体", Font.PLAIN, 12));
		insert.setFont(new Font("宋体", Font.PLAIN, 12));
		inquire.setFont(new Font("宋体", Font.PLAIN, 12));
		update.setFont(new Font("宋体", Font.PLAIN, 12));
		back.setFont(new Font("宋体", Font.PLAIN, 12));
		delete.setFont(new Font("宋体", Font.PLAIN, 12));

		choose_insert.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				p4_setVisible(INSERT);
			}
		});
		choose_inquire.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				p4_setVisible(INQUIRE);
			}
		});
		choose_delete.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				p4_setVisible(DELETE);
			}
		});
		insert.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				date2.dispose();
				if (MySQL.selectData("goods", "商品类别 = ? and 名称 = ?",
						new String[] { (String) jc4_1.getSelectedItem(), jtf4_1_1.getText() }, null) == "")
					MySQL.updateData(MySQL.INSERT, "goods(商品类别,名称,库存,进价,进货日期,生产厂家)", "(?,?,?,?,?,?)",
							new String[] { (String) jc4_1.getSelectedItem(), jtf4_1_1.getText(), jtf4_1_2.getText(),
									jtf4_1_3.getText(), jtf4_1_4.getText(), jtf4_1_5.getText() });
				else {
					// 库存增加
					String str = Integer.toString((int) MySQL.selectData("goods", "商品类别 = ? and 名称 = ?",
							new String[] { (String) jc4_1.getSelectedItem(), jtf4_1_1.getText() }, 4)
							+ Integer.parseInt(jtf4_1_2.getText()));
					MySQL.updateData(MySQL.UPDATE, "goods",
							"库存 = ? , 进价 = ? , 进货日期 = ? , 生产厂家 = ? where 商品类别 = ? and 名称 = ?",
							new String[] { str, jtf4_1_3.getText(), jtf4_1_4.getText(), jtf4_1_5.getText(),
									(String) jc4_1.getSelectedItem(), jtf4_1_1.getText() });
				}
				logrecData(INSERT);// 日志记录
				MySQL.showData("goods", -1, null, model);
				sp4.getTable().setModel(model);
				columnWidth(4);
				TableFormat.scrollRectToVisible_Row(sp4.getTable(), sp4.getTable().getRowCount() - 1,
						sp4.getTable().getRowCount() - 1, Color.green);
				jc4_1.setSelectedIndex(0);
				jtf4_1_1.setText("");
				jtf4_1_2.setText("");
				jtf4_1_3.setText("");
				jtf4_1_4.setText("");
				jtf4_1_5.setText("");
				validate();// 刷新组件
			}
		});
		inquire.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 获取表头
				int col = sp4.getTable().getColumnCount();
				Object[] tableHead = new Object[col];
				for (int i = 0; i < sp4.getTable().getColumnCount(); i++) {
					tableHead[i] = sp4.getTable().getColumnName(i);
				}
				
				String string = null;
				if(jc4_2.getSelectedIndex()<2)
					string = (String)tableHead[jc4_2.getSelectedIndex() + 1];
				else
					string = (String)tableHead[jc4_2.getSelectedIndex() + 3];
				
				if (MySQL.selectData("goods", string+ "= ?",
						new String[] { jtf4_2_1.getText() }, null) == "") {
					messageDialog(2);
					logrecData(INQUIRE);// 日志记录
				}
				else {
					if (jc4_2.getSelectedIndex() == 1)
						TableFormat.getOneRow(sp4.getTable(), jtf4_2_1.getText());
					else {
						if(jc4_2.getSelectedIndex()<2)
							MySQL.showData("goods", jc4_2.getSelectedIndex() + 2, jtf4_2_1.getText(), model);
						else
							MySQL.showData("goods", jc4_2.getSelectedIndex() + 4, jtf4_2_1.getText(), model);	
						sp4.getTable().setModel(model);
						columnWidth(4);
						logrecData(INQUIRE + 5);// 日志记录
					}
					p4.setVisible(false);
					p4_inquire.setVisible(false);
					p4_update.setVisible(true);
				}
				jc4_2.setSelectedIndex(0);
				jtf4_2_1.setText("");
				validate();// 刷新组件
			}
		});
		update.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 获取表头
				int col = sp4.getTable().getColumnCount();
				Object[] tableHead = new Object[col];
				for (int i = 0; i < sp4.getTable().getColumnCount(); i++) {
					tableHead[i] = sp4.getTable().getColumnName(i);
				}
				
				MySQL.updateData(MySQL.UPDATE, "goods",tableHead[jc4_3.getSelectedIndex() + 1]+"= ? where ID = ?",				
						new String[] { jtf4_3_2.getText(),jtf4_3_1.getText()});
				logrecData(UPDATE + 5);
				MySQL.showData("goods", -1, null, model);
				sp4.getTable().setModel(model);
				columnWidth(4);
				TableFormat.scrollRectToVisible_Row(sp4.getTable(), sp4.getTable().getRowCount() - 1,
						sp4.getTable().getRowCount() - 1, Color.green);
				jc4_3.setSelectedIndex(0);
				jtf4_3_1.setText("");
				jtf4_3_2.setText("");
				validate();// 刷新组件
			}
		});
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				MySQL.showData("goods", -1, null, model);
				sp4.getTable().setModel(model);
				columnWidth(4);
				p4.setVisible(true);
				p4_inquire.setVisible(true);
				p4_update.setVisible(false);
				validate();// 刷新组件
			}
		});
		delete.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (MySQL.selectData("goods", "商品类别 = ? and 名称 = ?",
						new String[] { (String) jc4_4.getSelectedItem(), jtf4_4_1.getText() }, null) == "") {
					messageDialog(3);
					logrecData(DELETE);// 日志记录
				}
				else {
					int j = JOptionPane.showConfirmDialog(jFrame, "您确定要删除吗", "删除", JOptionPane.OK_CANCEL_OPTION);
					if (j == JOptionPane.OK_OPTION) {
						MySQL.updateData(MySQL.DELETE, "goods", "商品类别 = ? and 名称 = ?",
								new String[] { (String) jc4_4.getSelectedItem(), jtf4_4_1.getText() });
						logrecData(DELETE + 5);// 日志记录
					} else {
						logrecData(DELETE);// 日志记录
					}
				}
				MySQL.showData("goods", -1, null, model);
				sp4.getTable().setModel(model);
				columnWidth(4);
				jc4_4.setSelectedIndex(0);
				jtf4_4_1.setText("");
				validate();// 刷新组件
			}
		});
	}
	/**
	 * 对表格某一列从大到小排序，其他列不排序
	 * @param tab 需要排序的表格
	 * @param column 表格中需要排序的列
	 */
	public void rowSorter(JTable tab, int column) {
		int col, row;
		col = tab.getColumnCount();
		row = tab.getRowCount();
		Object[] tableHead = new Object[col];
		Object[][] data = new Object[row][col];
		for (int i = 0; i < tab.getColumnCount(); i++) {
			tableHead[i] = tab.getColumnName(i);
		}
		for (int i = 0; i < tab.getColumnCount(); i++) {
			for (int j = 0; j < tab.getRowCount(); j++) {
				data[j][i] = tab.getValueAt(j, i);
				System.out.println(data[j][i]);
			}
		}
		TableFormat.changeAndSortTable(tab, tableHead, data, column);
	}
	/**
	 * 设置表格列的宽度
	 * @param i   选择表格：1-进货界面的表格；2-出货界面的表格；3-统计界面的表格；
	 * 	4-管理界面的表格；5-日志界面的表格
	 */
	public void columnWidth(int i) {
		if (i == 1) {
			//获取列
			column1 = sp1.getTable().getColumnModel().getColumn(0);
			/* 设置每一列宽度 */
			column1.setPreferredWidth(38);
			column2 = sp1.getTable().getColumnModel().getColumn(1);
			column2.setPreferredWidth(60);
			column3 = sp1.getTable().getColumnModel().getColumn(2);
			column3.setPreferredWidth(70);
			column4 = sp1.getTable().getColumnModel().getColumn(3);
			column4.setPreferredWidth(60);
			column5 = sp1.getTable().getColumnModel().getColumn(4);
			column5.setPreferredWidth(50);
			column6 = sp1.getTable().getColumnModel().getColumn(5);
			column6.setPreferredWidth(80);
			column7 = sp1.getTable().getColumnModel().getColumn(6);
			column7.setPreferredWidth(90);
		}
		if (i == 2) {
			column1 = sp2.getTable().getColumnModel().getColumn(0);
			column1.setPreferredWidth(30);
			column2 = sp2.getTable().getColumnModel().getColumn(1);
			column2.setPreferredWidth(60);
			column3 = sp2.getTable().getColumnModel().getColumn(2);
			column3.setPreferredWidth(75);
			column4 = sp2.getTable().getColumnModel().getColumn(3);
			column4.setPreferredWidth(40);
			column5 = sp2.getTable().getColumnModel().getColumn(4);
			column5.setPreferredWidth(45);
			column6 = sp2.getTable().getColumnModel().getColumn(5);
			column6.setPreferredWidth(45);
			column7 = sp2.getTable().getColumnModel().getColumn(6);
			column7.setPreferredWidth(74);
			column8 = sp2.getTable().getColumnModel().getColumn(7);
			column8.setPreferredWidth(80);
		}
		if (i == 3) {
			column1 = sp3.getTable().getColumnModel().getColumn(0);
			column1.setPreferredWidth(38);
			column2 = sp3.getTable().getColumnModel().getColumn(1);
			column2.setPreferredWidth(60);
			column3 = sp3.getTable().getColumnModel().getColumn(2);
			column3.setPreferredWidth(70);
			column4 = sp3.getTable().getColumnModel().getColumn(3);
			column4.setPreferredWidth(60);
			column5 = sp3.getTable().getColumnModel().getColumn(4);
			column5.setPreferredWidth(50);
			column6 = sp3.getTable().getColumnModel().getColumn(5);
			column6.setPreferredWidth(80);
			column7 = sp3.getTable().getColumnModel().getColumn(6);
			column7.setPreferredWidth(90);
			
			column1 = sp3_2.getTable().getColumnModel().getColumn(0);
			column1.setPreferredWidth(30);
			column2 = sp3_2.getTable().getColumnModel().getColumn(1);
			column2.setPreferredWidth(60);
			column3 = sp3_2.getTable().getColumnModel().getColumn(2);
			column3.setPreferredWidth(70);
			column4 = sp3_2.getTable().getColumnModel().getColumn(3);
			column4.setPreferredWidth(45);
			column5 = sp3_2.getTable().getColumnModel().getColumn(4);
			column5.setPreferredWidth(45);
			column6 = sp3_2.getTable().getColumnModel().getColumn(5);
			column6.setPreferredWidth(62);
			column7 = sp3_2.getTable().getColumnModel().getColumn(6);
			column7.setPreferredWidth(62);
			column8 = sp3_2.getTable().getColumnModel().getColumn(7);
			column8.setPreferredWidth(74);
		}
		if (i == 4) {
			column1 = sp4.getTable().getColumnModel().getColumn(0);
			column1.setPreferredWidth(38);
			column2 = sp4.getTable().getColumnModel().getColumn(1);
			column2.setPreferredWidth(60);
			column3 = sp4.getTable().getColumnModel().getColumn(2);
			column3.setPreferredWidth(70);
			column4 = sp4.getTable().getColumnModel().getColumn(3);
			column4.setPreferredWidth(60);
			column5 = sp4.getTable().getColumnModel().getColumn(4);
			column5.setPreferredWidth(50);
			column6 = sp4.getTable().getColumnModel().getColumn(5);
			column6.setPreferredWidth(80);
			column7 = sp4.getTable().getColumnModel().getColumn(6);
			column7.setPreferredWidth(90);
		}
		if (i == 5) {
			column1 = sp5.getTable().getColumnModel().getColumn(0);
			column1.setPreferredWidth(30);
			column2 = sp5.getTable().getColumnModel().getColumn(1);
			column2.setPreferredWidth(45);
			column3 = sp5.getTable().getColumnModel().getColumn(2);
			column3.setPreferredWidth(70);
			column4 = sp5.getTable().getColumnModel().getColumn(3);
			column4.setPreferredWidth(174);
			column5 = sp5.getTable().getColumnModel().getColumn(4);
			column5.setPreferredWidth(50);
			column6 = sp5.getTable().getColumnModel().getColumn(5);
			column6.setPreferredWidth(80);
		}
		//  设置JTable自动调整列表的状态，此处设置为关闭 
		sp1.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		sp2.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		sp3.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		sp3_2.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		sp4.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		sp5.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}
	//设置管理界面显示的小界面
	public void p4_setVisible(int i) {
		date2.dispose();
		p4_insert.setVisible(false);
		p4_inquire.setVisible(false);
		p4_update.setVisible(false);
		p4_delete.setVisible(false);
		if (i == INSERT) {
			p4_insert.setVisible(true);
		}
		if (i == INQUIRE) {
			p4_inquire.setVisible(true);
		}
		if (i == DELETE) {
			p4_delete.setVisible(true);
		}
	}

	public void visible(boolean i) {
		if (i) {
			lab3_1.setVisible(true);
			lab3_2.setVisible(true);
		} else {
			lab3_1.setVisible(false);
			lab3_2.setVisible(false);
		}
	}
	/**
	 * 选择显示界面
	 * @param i   选择：1-显示进货界面；2-显示出货界面；3-显示统计界面；
	 * 	4-显示管理界面；5-显示日志界面
	 */
	public void setVisible(int i) {
		date1.dispose();
		date2.dispose();
		if (st != null)
			st.dispose();
		if (sDialog != null)
			sDialog.dispose();
		b1.setIcon(new ImageIcon("image\\an01.png"));
		b2.setIcon(new ImageIcon("image\\an02.png"));
		b3.setIcon(new ImageIcon("image\\an03.png"));
		b4.setIcon(new ImageIcon("image\\an04.png"));
		b5.setIcon(new ImageIcon("image\\an05.png"));
		sp1.setVisible(false);
		bJing.setVisible(false);
		sp2.setVisible(false);
		sp3.setVisible(false);
		sp3_2.setVisible(false);
		sp4.setVisible(false);
		sp5.setVisible(false);
		visible(false);
		p1.setVisible(false);
		p2.setVisible(false);
		p4.setVisible(false);
		p4_insert.setVisible(false);
		p4_inquire.setVisible(false);
		p4_update.setVisible(false);
		p4_delete.setVisible(false);
		if (i == 1) {
			sp1.setVisible(true);
			b1.setIcon(new ImageIcon("image\\an11.png"));
			p1.setVisible(true);
			MySQL.showData("goodsin", -1, null, model);
			sp1.getTable().setModel(model);
			columnWidth(1);
		}
		if (i == 2) {
			sp2.setVisible(true);
			b2.setIcon(new ImageIcon("image\\an12.png"));
			p2.setVisible(true);
			MySQL.showData("goodsout", -1, null, model);
			sp2.getTable().setModel(model);
			columnWidth(2);
		}
		if (i == 3) {
			MySQL.week_sale();//清除一周前的销售额数据
			st = new Statistics();
			st.setLocation(getLocation().x + 560, getLocation().y);
			st.setVisible(true);
			visible(true);
			sp3.setVisible(true);
			sp3_2.setVisible(true);
			b3.setIcon(new ImageIcon("image\\an13.png"));
			MySQL.showData("goods", -1, null, model1);
			sp3.getTable().setModel(model1);
			rowSorter(sp3.getTable(),4);
//			validate();
			MySQL.showData("goods_sale", -1, null, model2);
			sp3_2.getTable().setModel(model2);
			rowSorter(sp3_2.getTable(),7);
			columnWidth(3);
		}
		if (i == 4) {
			b4.setIcon(new ImageIcon("image\\an14.png"));
			sp4.setVisible(true);
			MySQL.showData("goods", -1, null, model);
			sp4.getTable().setModel(model);
			columnWidth(4);
			p4.setVisible(true);
		}
		if (i == 5) {
			b5.setIcon(new ImageIcon("image\\an15.png"));
			sp5.setVisible(true);
			MySQL.showData("goods_logrec", -1, null, model);
			sp5.getTable().setModel(model);
			columnWidth(5);
		}
		validate();// 刷新组件
	}
	//日志记录
	public void logrecData(int i) {
		Date d = new Date();// 获取时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (i == STOCK) {
			MySQL.updateData(MySQL.INSERT, "goods_logrec(操作,类型,商品名称,数量,日期)", "(?,?,?,?,?)", new String[] { "进货",
					(String) jc1.getSelectedItem(), jtf1_1.getText(), jtf1_2.getText(), sdf.format(d) });
		}
		if (i == SHIPMENT) {// 库存不足时的记录
			MySQL.updateData(MySQL.INSERT, "goods_logrec(操作,类型,商品名称,数量,日期)", "(?,?,?,?,?)",
					new String[] { "出货", goods_Type, goods_Name, "0", sdf.format(d) });
		}
		if (i == SHIPMENT + 5) {
			MySQL.updateData(MySQL.INSERT, "goods_logrec(操作,类型,商品名称,数量,日期)", "(?,?,?,?,?)",
					new String[] { "出货", goods_Type, goods_Name, sDialog.jtf1.getText(), sdf.format(d) });
		}
		if (i == INSERT) {
			MySQL.updateData(MySQL.INSERT, "goods_logrec(操作,类型,商品名称,数量,日期)", "(?,?,?,?,?)", new String[] { "新增",
					(String) jc4_1.getSelectedItem(), jtf4_1_1.getText(), jtf4_1_2.getText(), sdf.format(d) });
		}
		if (i == INQUIRE) {
			MySQL.updateData(MySQL.INSERT, "goods_logrec(操作,类型,商品名称,数量,日期)", "(?,?,?,?,?)",
					new String[] { "查询", (String) jc4_2.getSelectedItem(), jtf4_2_1.getText(), "0", sdf.format(d) });
		}
		if (i == INQUIRE + 5) {
			MySQL.updateData(MySQL.INSERT, "goods_logrec(操作,类型,商品名称,数量,日期)", "(?,?,?,?,?)",
					new String[] { "查询", (String) jc4_2.getSelectedItem(), jtf4_2_1.getText(), "1", sdf.format(d) });
		}
		if (i == UPDATE) {
			MySQL.updateData(MySQL.INSERT, "goods_logrec(操作,类型,商品名称,数量,日期)", "(?,?,?,?,?)",
					new String[] { "修改", (String) jc4_3.getSelectedItem(), "未修改", "0", sdf.format(d) });
		}
		if (i == UPDATE + 5) {
			MySQL.updateData(MySQL.INSERT, "goods_logrec(操作,类型,商品名称,数量,日期)", "(?,?,?,?,?)",
					new String[] { "修改", (String) jc4_3.getSelectedItem(),
							"ID=" + jtf4_3_1.getText() + "  "
							+ (String) MySQL.selectData("goods", "ID = ?", new String[] { jtf4_3_1.getText() },
							jc4_3.getSelectedIndex() + 2)+ "  改为：" + jtf4_3_2.getText(),"1", sdf.format(d) });
		}
		if (i == DELETE) {
			MySQL.updateData(MySQL.INSERT, "goods_logrec(操作,类型,商品名称,数量,日期)", "(?,?,?,?,?)",
					new String[] { "删除", (String) jc4_4.getSelectedItem(), jtf4_4_1.getText(), "0", sdf.format(d) });
		}
		if (i == DELETE + 5) {
			MySQL.updateData(MySQL.INSERT, "goods_logrec(操作,类型,商品名称,数量,日期)", "(?,?,?,?,?)",
					new String[] { "删除", (String) jc4_4.getSelectedItem(), jtf4_4_1.getText(), "1", sdf.format(d) });
		}
	}

	public static void main(String[] args) {
		try {
			// 界面风格
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");// Nimbus风格，jdk6
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Menu();
	}

}
