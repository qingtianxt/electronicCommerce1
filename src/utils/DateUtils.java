package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/**
	 * ��ȡ
	 * @return
	 */
	public static String getDate(){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		return date;
	}
	/**
	* ��ȡ����
	* @return
	*/
	public static String getDateStr() {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	return sdf.format(date);
	}
	/**
	* ��ȡʱ��
	* @return
	*/
	public static String getTimeStr() {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
	return sdf.format(date);
	}
}
