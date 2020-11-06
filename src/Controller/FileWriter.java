import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
package Controller;

/**
 * The purpose of this class is to have a file writing class that is smaller and less verbose then BufferedWriter.
 * All the exception handling and writing will be handled by this class so the programmer can just focus on what needs to be done.
 * Use a creational design pattern
 */
public class FileWriter {

	public FileWriter(String fileName, String line) {
		try{
			FileWriter myWriter = new FileWriter(fileName);
			myWriter.write(line);
			myWriter.write("\n");
			myWriter.close();
			System.out.println("Succesfully wrote to file");
		}catch (IOException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		
	}
	public void append(String file,String line) {
		 try {
			 
		      FileWriter myWriter = new FileWriter(file,true);
		      myWriter.write(line);
		      myWriter.write("\n");
		      myWriter.close();
		      System.out.println("Successfully appended file.");
		      
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	public void modify(String file, String line, String replace) {
		ArrayList <String> text=new ArrayList<String>();
		try {
		      FileReader myReader = new FileReader(file);
		      BufferedReader br = new BufferedReader(myReader);
		      String currentLine;
		      while ((currentLine=br.readLine())!=null) {
		    	  if (line.equals(currentLine)){
		    		  text.add(replace);
		    	  }
		    	  else {
		    		  text.add(currentLine);
		    	  }
		      }
		      myReader.close();
		      FileWriter myWriter = new FileWriter(file);
		      for (int i=0; i<text.size();i++) {
		    	  myWriter.write(text.get(i)+"\n");
		    	  
		      }
		      System.out.print("Succesfully modified file.");
		      myWriter.close();
		      
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	public void remove(String file, String line) {
		ArrayList <String> text=new ArrayList<String>();
		try {
		      FileReader myReader = new FileReader(file);
		      BufferedReader br = new BufferedReader(myReader);
		      String currentLine;
		      while ((currentLine=br.readLine())!=null) {
		    	  if (line.equals(currentLine)){
		    		  //do nothing
		    	  }
		    	  else {
		    		  text.add(currentLine);
		    	  }
		      }
		      myReader.close();
		      FileWriter myWriter = new FileWriter(file);
		      for (int i=0; i<text.size();i++) {
		    	  myWriter.write(text.get(i)+"\n");
		    	  
		      }
		      System.out.println("Succesfully removed line.");
		      myWriter.close();
		      
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
}
