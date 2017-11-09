package com.test;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ReadJsonObject read = new ReadJsonObject();
        try {
			read.aptTesting();
		} catch (Exception e) {
			e.printStackTrace();
		}
        System.out.println("end");
    }
}
