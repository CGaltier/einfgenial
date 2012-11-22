package com.cgaltier;

import java.util.Scanner;

public class Helper 
{
	public static String convertStreamToString(java.io.InputStream is) 
	{
		try 
		{
	        //return new java.util.Scanner(is).useDelimiter("\\A").next();
			Scanner scanner = new Scanner(is, "UTF-8").useDelimiter("\\A");
			if (scanner.hasNext()) 
				return scanner.next(); 
			return "";
	    } 
		catch (java.util.NoSuchElementException e) 
		{
	        return "";
	    }
	}
}
/*
Scanner scanner = new Scanner(is, "UTF-8").useDelimiter("\\A");
if (scanner.hasNext()) 
	return scanner.next(); return ""
 */