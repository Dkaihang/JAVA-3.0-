package mysql;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
	private static Properties p = null;
	static {
		try {
			p = new Properties();
			// 加载配置类
			p.load(new FileInputStream("config/mysql.properties"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		return p.get(key).toString();
	}
}
