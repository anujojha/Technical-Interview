import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



class Copa {

	String name = "miami"; 

	String address = "address";

	public String getName(){
		return name;
	}

	public String getAddress(){
		return address;
	}
}

public class myTest {

    public static void main(String[] args) {        

        System.out.println("Copenhagen");

        Copa copa = new Copa();

        if(copa ==  null){
        	System.out.println("Copa is null");
        }

        else{
        	System.out.println("Copa is not null");
        }
    }
}

