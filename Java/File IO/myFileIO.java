import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.PrintWriter;

public  class myFileIO {
	
	public static void writeToExistingFile(){

		try {

			File file = new File("citydata.cvs");
			PrintWriter pw = new PrintWriter(new FileWriter(file.getName(), true)); // true for auto-flush
			pw.println("Line 1");
			pw.println("Line 2");
			pw.println("Line 3");
			pw.close();
		}

		catch(IOException ex){

		}
	}

	public void appendToExistingFile(){

		try {
			File file = new File("citydata.cvs");
			FileWriter fw = new FileWriter(file, true); // true for appending
			PrintWriter pw = new PrintWriter(fw, true); // true for auto-flush
			pw.println("Line 4");
			pw.println("Line 5");
			pw.println("Line 6");
			pw.close();
		}

		catch(IOException ex){

		}
	}

	// public void readFromFile(){

	// 	File file = new File("citydata.cvs");
	// 	BufferedReader br = new BufferedReader(new FileReader(file));
	// 	String line1 = br.readLine();
	// 	String line2 = br.readLine();
	// 	String line3 = br.readLine();
	// 	br.close();
	// }

	public static void main(String[] args) {
		System.out.println("Hello Seattle");
		writeToExistingFile();
	}
}