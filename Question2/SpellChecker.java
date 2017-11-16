
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpellChecker {

   /**
    * This method takes in a filename and spells check it	
    * @param filename an input file stream (i.e. bonk.txt)	
    * @return ArrayList<String> list of words not found in dictionary 
    */
    public ArrayList<String> spell(String filename) {
	// method body
        ArrayList<String> misspelledWords = new ArrayList<>();
         try {
            // Use this for reading the data.
            System.out.println("Reading input file: " + filename);
            byte[] buffer = new byte[5000];
            byte[] dicBuffer = new byte[5000];

            FileInputStream inputStream = new FileInputStream(filename);
            FileInputStream dictonaryFile = new FileInputStream("dictionary.txt");
             
            // read fills buffer with data and returns
            // the number of bytes read (which of course
            // may be less than the buffer size, but
            // it will never be more)  
            while((inputStream.read(buffer)) != -1) { }
            while((dictonaryFile.read(dicBuffer)) != -1) { } 
            
            String [] dictionary = new String(dicBuffer).split("\\R+");
            String [] strArr = new String(buffer).replaceAll("[^a-zA-Z]+", " ").split(" ");                   
           
            for (int i = 0; i < strArr.length; i++) {
                if(!inDictionary(strArr[i], dictionary)){
                  misspelledWords.add(strArr[i]);
                }
            }                 
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                filename + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + filename + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
         
        return misspelledWords;
    }


   /**
    * This method takes the list of words not found in the dictionary	
    * and prints a list of non-duplicate words
     * @param <error>	
    */
    public void printNonDuplicates(ArrayList<String> printArr) {
	// method body
        ArrayList<String> nonDuplicantArr = new ArrayList<>();
        for (int i = 0; i < printArr.size(); i++) {
            if(!nonDuplicantArr.contains(printArr.get(i))){
                nonDuplicantArr.add(printArr.get(i));
            }
        }
        
        for (int i = 0; i < nonDuplicantArr.size(); i++) {
            System.out.println(nonDuplicantArr.get(i));
        }
    }

    public boolean inDictionary(String word, String[] dictionary) {
        for (int i = 0; i < dictionary.length; i++) {
            if(word.equalsIgnoreCase(dictionary[i])){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
      // Prompt the user to enter a file to be spell checked
      SpellChecker sc = new SpellChecker();
      ArrayList<String> misspelledSpells = sc.spell("bonk.txt");
      sc.printNonDuplicates(misspelledSpells);
    }
}