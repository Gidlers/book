package com.it.tool;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Color;
import java.awt.Component;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

public class Tool {
	private static ThreadLocal<DataSource> THREALOCAL = new ThreadLocal<DataSource>();

	public static String nextLine(String message) {
		return JOptionPane.showInputDialog(message);
	}

	public static int nextInt(String message) {
		return Integer.parseInt(nextLine(message));
	}

	public static double nextDouble(String message) {
		return Double.parseDouble(nextLine(message));
	}

	public static void showMessage(Object obj) {
		JOptionPane.showMessageDialog((Component) null, obj);
	}

	public static int rad(int min, int max) {
		return (int) ((double) min + Math.random() * (double) (max - min));
	}

	public static int rad(int max) {
		return rad(0, max);
	}

	public static int rad() {
		return rad(0, 100);
	}

	public static int[] radArrays(int min, int max, int index) {
		int[] a = new int[index];

		for (int i = 0; i < a.length; ++i) {
			a[i] = rad(min, max);
		}

		return a;
	}

	public static BigInteger factorial(int n) {
		BigInteger result = BigInteger.ONE;
		if (n < 2) {
			return result;
		} else {
			for (int i = 2; i <= n; ++i) {
				result = result.multiply(BigInteger.valueOf((long) i));
			}

			return result;
		}
	}

	public static Gson getGson() {
		return (new GsonBuilder()).serializeNulls().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
	}

	public static DataSource getDataSource(String db) {
		if (THREALOCAL.get() == null) {
			DruidDataSource dds = new DruidDataSource();
			dds.setUsername("root");
			dds.setPassword("root");
			dds.setUrl("jdbc:mysql:///" + db);
			dds.setDriverClassName("com.mysql.jdbc.Driver");
			dds.setMaxWait(10000L);
			THREALOCAL.set(dds);
		}

		return (DataSource) THREALOCAL.get();
	}

	public static void release(DataSource ds) {
		if (ds instanceof DruidDataSource) {
			DruidDataSource dds = (DruidDataSource) ds;
			dds.close();
		}

	}

	public static void release(AutoCloseable... resources) {
		AutoCloseable[] var4 = resources;
		int var3 = resources.length;

		for (int var2 = 0; var2 < var3; ++var2) {
			AutoCloseable resource = var4[var2];
			if (resource != null) {
				try {
					resource.close();
				} catch (Exception var6) {
					var6.printStackTrace();
				}
			}
		}

	}

	public static Color getColorC() {
		return new Color(rad(256), rad(256), rad(256));
	}

	public static String getColor() {
		String color = "rgb(" + rad(256) + "," + rad(256) + "," + rad(256) + ")";
		return color;
	}

	public static Date getDate() {
		long la = Date.valueOf("1970-1-1").getTime();
		long lb = Date.valueOf("2020-1-1").getTime();
		long times = ThreadLocalRandom.current().nextLong(la, lb);
		return new Date(times);
	}

	public static String getEncoder(String str) {
		return new String(Base64.getEncoder().encode(str.getBytes()));
	}

	public static String getMd5(String str) {
		BigInteger big = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			big = new BigInteger(md.digest());
		} catch (NoSuchAlgorithmException var3) {
			var3.printStackTrace();
		}

		return big.toString();
	}

	public static String getDecoder(String dest) {
		return new String(Base64.getDecoder().decode(dest.getBytes()));
	}

	public static int upLimit(double str) {
		int ss;
		if (str > (double) ((int) str)) {
			ss = (int) str + 1;
		} else {
			ss = (int) str;
		}

		return ss;
	}
}