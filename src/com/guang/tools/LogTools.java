package com.guang.tools;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;


public class LogTools {

	public LogTools()
	{
		URL url = LogTools.class.getClassLoader().getResource("log4j.properties");		
		PropertyConfigurator.configure( url.getPath() );
	}
	
	public void init()
	{
		
	}
	
	public void destory()
	{
		
	}
}
