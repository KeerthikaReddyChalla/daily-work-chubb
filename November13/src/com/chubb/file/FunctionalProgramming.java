package com.chubb.file;

import java.io.IOException;
import java.nio.file.*;

public class FunctionalProgramming {
public static void main(String[] args) {
	String path="input.txt";
	
	try {   
		long count=Files.lines(Paths.get(path)).flatMap(line-> java.util.Arrays.stream(line.split(" ")))
				  .filter(word->word.equalsIgnoreCase("India")).count();
		
		System.out.println("count is: "+count);
	}
	catch(IOException e) {
		e.printStackTrace();
	}
}
}
