package com.guang.tools;

public class StringTools {

	public static boolean isEmpty(String str)
	{
		if(str == null || "".equals(str) || "".equals(str.trim()))
			return true;
		return false;
	}
}
