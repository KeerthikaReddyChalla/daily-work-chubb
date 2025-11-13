package com.chubb.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileOperations{
	public static void main(String[] args) {
		String path="input.txt";
		int count=0;
		
		try(BufferedReader br=new BufferedReader(new FileReader(path))){
			String line;
			while((line=br.readLine())!=null) {
				String[] words= line.split(" ");
				
				for(String word:words) {
					if(word.equalsIgnoreCase("India")) {
						count++;
					}
				}
			}
			System.out.println("count is: "+ (count));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
