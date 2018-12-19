package com.cota.util;

public class StringUtil {
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * nvl
	 * @param obj
	 * @param defValue
	 * @return
	 */
	public static String nvl(Object obj, String defValue) {
		String retStr = "";
		if (null == obj || obj.equals("")) {
			retStr = defValue;
		} else {
			retStr = obj.toString();
		}
		return retStr;
	}
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * check whether null or not
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		boolean flag = false;
		if (null == str || str.trim().equals("")) {
			flag = true;
		}
		return flag;
	}
	
	// ------------------------------------------------------------------------------ //
}
