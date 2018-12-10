package statisticsDialog;

import mysql.MySQL;

import java.awt.*;
import javax.swing.*;
import java.text.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class Statistics extends JDialog {
	int x, y, xx, yy;
	boolean isDraging;
	JLabel jl1, jl2;

	public Statistics() {
		this.setModal(false);
		this.setSize(500, 550);
		this.setLocation(760, 100);
		this.setLayout(null);// 空布局
		this.getContentPane().setBackground(new Color(233, 238, 241));
		jl1 = new JLabel();
		jl2 = new JLabel();
		jl1.setText("<html><body>&nbsp&nbsp销售额<br>" + "食品:" + Statistics.week_SaleOrProfit(0) + "<br>饮料:"
				+ Statistics.week_SaleOrProfit(1) + "<br>日用品:" + Statistics.week_SaleOrProfit(2) + "<br>化妆品:"
				+ Statistics.week_SaleOrProfit(03) + "<body></html>");
		jl2.setText("<html><body>&nbsp&nbsp利润<br>" + "食品:" + Statistics.week_SaleOrProfit(4) + "<br>饮料:"
				+ Statistics.week_SaleOrProfit(5) + "<br>日用品:" + Statistics.week_SaleOrProfit(6) + "<br>化妆品:"
				+ Statistics.week_SaleOrProfit(7) + "<body></html>");
		jl1.setFont(new Font("宋体", Font.PLAIN, 13));
		jl2.setFont(new Font("宋体", Font.PLAIN, 13));
		jl1.setBounds(400, 0, 100, 260);
		jl2.setBounds(400, 270, 100, 260);
		this.add(new BarChart().getChartPanel());
		this.add(jl1);
		this.add(new TimeSeriesChart().getChartPanel());
		this.add(jl2);
		this.setVisible(false);
		this.setResizable(false);// 不可改变窗体大小，true为可改变
	}
	/**
	 * 获取一周内的销售额和利润
	 * @param i    选择返回的值：0-返回食品的销售额；1-返回饮料的销售额；2-返回日用品的销售额；3-返回化妆品的销售额；
	 * 				4-返回食品的利润；5-返回饮料的利润；6-返回日用品的利润；7-返回化妆品的利润
	 * @return 
	 */
	public static Object week_SaleOrProfit(int i) {
		int food_Sale = 0, drinks_Sale = 0, commodity_Sale = 0, cosmetics_Sale = 0;
		float food_Profit = 0, drinks_Profit = 0, commodity_Profit = 0, cosmetics_Profit = 0;
		if (i == 0) {
			if (MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "食品" }, null) != "") {
				food_Sale = (int) MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "食品" }, 6);
			}
			return food_Sale;
		}
		else if (i == 1) {
			if (MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "饮料" }, null) != "") {
				drinks_Sale = (int) MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "饮料" }, 6);
			}
			return drinks_Sale;
		}
		else if (i == 2) {
			if (MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "日用品" }, null) != "") {
				commodity_Sale = (int) MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "日用品" }, 6);
			}
			return commodity_Sale;
		}
		else if (i == 3) {
			if (MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "化妆品" }, null) != "") {
				cosmetics_Sale = (int) MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "化妆品" }, 6);
			}
			return cosmetics_Sale;
		}
		else if (i == 4) {
			if (MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "食品" }, null) != "") {
				food_Profit = (Float) MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "食品" }, 7);
			}
			return food_Profit;
		}
		else if (i == 5) {
			if (MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "饮料" }, null) != "") {
				drinks_Profit = (Float) MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "饮料" }, 7);
			}
			return drinks_Profit;
		}
		else if (i == 6) {	
			if (MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "日用品" }, null) != "") {	
				commodity_Profit = (Float) MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "日用品" }, 7);
			}
			return commodity_Profit;
		}
		else {
			if (MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "化妆品" }, null) != "") {
				cosmetics_Profit = (Float) MySQL.selectData("goods_sale", "商品类别 = ?", new String[] { "化妆品" }, 7);
			}
			return cosmetics_Profit;
		}
	}
	/**
	 * 获取某天的销售额和利润
	 * @param i    选择返回的值：0-返回食品的销售额；1-返回饮料的销售额；2-返回日用品的销售额；3-返回化妆品的销售额；
	 * 				4-返回食品的利润；5-返回饮料的利润；6-返回日用品的利润；7-返回化妆品的利润
	 * @param str  日期
	 * @return 
	 */
	public static Object day_SaleOrProfit(int i, String str) {
		int food_Sale = 0, drinks_Sale = 0, commodity_Sale = 0, cosmetics_Sale = 0;
		float food_Profit = 0, drinks_Profit = 0, commodity_Profit = 0, cosmetics_Profit = 0;
		
		if (i == 0) {
			if (MySQL.selectData("goods_sale", "商品类别 = ? and 时间 = ?", new String[] { "食品", str}, null) != "") {
				food_Sale = (int) MySQL.selectData("goods_sale", "商品类别 = ? and 时间 = ?", new String[] { "食品", str }, 6);
			}
			return food_Sale;
		}
			
		else if (i == 1) {
			if (MySQL.selectData("goods_sale", "商品类别 = ? and 时间 = ?", new String[] { "饮料", str}, null) != "") {
				drinks_Sale = (int) MySQL.selectData("goods_sale", "商品类别 = ? and 时间 = ?", new String[] { "饮料", str }, 6);
			}
			return drinks_Sale;
		}
		else if (i == 2) {
			if (MySQL.selectData("goods_sale", "商品类别 = ? and 时间 = ?", new String[] { "日用品", str}, null) != "") {
				commodity_Sale = (int) MySQL.selectData("goods_sale", "商品类别 = ? and 时间 = ?", new String[] { "日用品", str },6);
			}
			return commodity_Sale;
		}
		else if (i == 3) {
			if (MySQL.selectData("goods_sale", "商品类别 = ? and 时间 = ?", new String[] { "化妆品", str}, null) != "") {
				cosmetics_Sale = (int) MySQL.selectData("goods_sale", "商品类别 = ? and 时间 = ?", new String[] { "化妆品", str },6);
			}
			return cosmetics_Sale;
		}
		else if (i == 4) {
			if (MySQL.selectData("goods_sale", "商品类别 = ? and 时间 = ?", new String[] { "食品", str }, null) != "") {
				food_Profit = (Float) MySQL.selectData("goods_sale", "商品类别 = ? and 时间 = ?", new String[] { "食品", str }, 7);
			}
			return food_Profit;
		}
		else if (i == 5) {
			if (MySQL.selectData("goods_sale", "商品类别 = ? and 时间 = ?", new String[] { "饮料", str }, null) != "") {
				drinks_Profit = (Float)MySQL.selectData("goods_sale", "商品类别 = ? and 时间 = ?", new String[] { "饮料", str }, 7);
			}
			return drinks_Profit;
		}
		else if (i == 6) {
			if ( MySQL.selectData("goods_sale", "商品类别 = ?and 时间 = ?", new String[] { "日用品", str }, null) != "") {
				commodity_Profit = (Float) MySQL.selectData("goods_sale", "商品类别 = ?and 时间 = ?", new String[] { "日用品", str }, 7);
			}
			return commodity_Profit;
		}
		else {
			if (MySQL.selectData("goods_sale", "商品类别 = ?and 时间 = ?", new String[] { "化妆品", str }, null) != "") {
				cosmetics_Profit = (Float) MySQL.selectData("goods_sale", "商品类别 = ?and 时间 = ?", new String[] { "化妆品", str }, 7);
			}
			return cosmetics_Profit;
		}

	}
}

class BarChart {
	ChartPanel panel1;
	public BarChart() {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart("商品一周的利润", // 图表标题
				"日期", // 目录轴的显示标签
				"利润", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
		);
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

		panel1 = new ChartPanel(chart, 24, 18, 0, 0, 500, 400, true, false, false, false, false, false, false); // 这里也可以用chartFrame,可以直接生成一个独立的Frame
		panel1.setMouseZoomable(true);// 打开鼠标缩放功能
		panel1.setBounds(0, 0, 400, 260);
	}
	//返回数据集
	private static CategoryDataset getDataSet() {
		Calendar c = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 6);
		dataset.addValue((int) Statistics.day_SaleOrProfit(0, sdf1.format(c.getTime())), "食品", sdf.format(c.getTime()));// 数值，柱名，类型名
		dataset.addValue((int) Statistics.day_SaleOrProfit(1, sdf1.format(c.getTime())), "饮料", sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(2, sdf1.format(c.getTime())), "日用品",sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(3, sdf1.format(c.getTime())), "化妆品",sdf.format(c.getTime()));
		
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 5);
		dataset.addValue((int) Statistics.day_SaleOrProfit(0, sdf1.format(c.getTime())), "食品", sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(1, sdf1.format(c.getTime())), "饮料", sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(2, sdf1.format(c.getTime())), "日用品",sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(3, sdf1.format(c.getTime())), "化妆品",sdf.format(c.getTime()));
		
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 4);
		dataset.addValue((int) Statistics.day_SaleOrProfit(0, sdf1.format(c.getTime())), "食品", sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(1, sdf1.format(c.getTime())), "饮料", sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(2, sdf1.format(c.getTime())), "日用品",sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(3, sdf1.format(c.getTime())), "化妆品",sdf.format(c.getTime()));
		
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 3);
		dataset.addValue((int) Statistics.day_SaleOrProfit(0, sdf1.format(c.getTime())), "食品", sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(1, sdf1.format(c.getTime())), "饮料", sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(2, sdf1.format(c.getTime())), "日用品",sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(3, sdf1.format(c.getTime())), "化妆品",sdf.format(c.getTime()));
		
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 2);
		dataset.addValue((int) Statistics.day_SaleOrProfit(0, sdf1.format(c.getTime())), "食品", sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(1, sdf1.format(c.getTime())), "饮料", sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(2, sdf1.format(c.getTime())), "日用品",sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(3, sdf1.format(c.getTime())), "化妆品",sdf.format(c.getTime()));
		
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 1);
		dataset.addValue((int) Statistics.day_SaleOrProfit(0, sdf1.format(c.getTime())), "食品", sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(1, sdf1.format(c.getTime())), "饮料", sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(2, sdf1.format(c.getTime())), "日用品",sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(3, sdf1.format(c.getTime())), "化妆品",sdf.format(c.getTime()));
		
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE));
		dataset.addValue((int) Statistics.day_SaleOrProfit(0, sdf1.format(c.getTime())), "食品", sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(1, sdf1.format(c.getTime())), "饮料", sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(2, sdf1.format(c.getTime())), "日用品",sdf.format(c.getTime()));
		dataset.addValue((int) Statistics.day_SaleOrProfit(3, sdf1.format(c.getTime())), "化妆品",sdf.format(c.getTime()));
		
		return dataset;
	}
	public ChartPanel getChartPanel() {
		return panel1;
	}
}

class TimeSeriesChart {
	ChartPanel frame1;
	public TimeSeriesChart() {
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("商品一周的销售额", "日期", "销售额", xydataset, true, true,true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("MM-dd"));// 设置时间显示格式
		dateaxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		dateaxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = xyplot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		jfreechart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体
		
		frame1 = new ChartPanel(jfreechart, 24, 18, 0, 0, 500, 400, true, false, false, false, false, false, false);
		frame1.setBounds(0, 270, 400, 250);
		frame1.setMouseZoomable(true);// 打开鼠标缩放功能
	}

	private static XYDataset createDataset() { 
		Calendar c = new GregorianCalendar();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		TimeSeries timeseries = new TimeSeries("食品");

		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 6);
		timeseries.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(4, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 5);
		timeseries.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(4, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 4);
		timeseries.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(4, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 3);
		timeseries.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(4, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 2);
		timeseries.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(4, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 1);
		timeseries.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(4, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE));
		timeseries.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(4, sdf1.format(c.getTime())));

		TimeSeries timeseries1 = new TimeSeries("化妆品");

		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 6);
		timeseries1.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(7, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 5);
		timeseries1.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(7, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 4);
		timeseries1.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(7, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 3);
		timeseries1.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(7, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 2);
		timeseries1.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(7, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 1);
		timeseries1.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(7, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE));
		timeseries1.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(7, sdf1.format(c.getTime())));

		TimeSeries timeseries2 = new TimeSeries("日用品");

		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 6);
		timeseries2.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(6, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 5);
		timeseries2.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(6, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 4);
		timeseries2.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(6, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 3);
		timeseries2.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(6, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 2);
		timeseries2.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(6, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 1);
		timeseries2.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(6, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE));
		timeseries2.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(6, sdf1.format(c.getTime())));

		TimeSeries timeseries3 = new TimeSeries("饮料");

		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 6);
		timeseries3.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(5, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 5);
		timeseries3.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(5, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 4);
		timeseries3.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(5, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 3);
		timeseries3.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(5, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 2);
		timeseries3.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(5, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE) - 1);
		timeseries3.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(5, sdf1.format(c.getTime())));
		c.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DATE));
		timeseries3.add(new Day(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)),
				(float) Statistics.day_SaleOrProfit(5, sdf1.format(c.getTime())));

		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		timeseriescollection.addSeries(timeseries3);
		timeseriescollection.addSeries(timeseries2);
		timeseriescollection.addSeries(timeseries1);
		return timeseriescollection;
	}
	public ChartPanel getChartPanel() {
		return frame1;
	}
}
