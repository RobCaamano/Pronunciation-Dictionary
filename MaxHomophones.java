import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class MaxHomophones {

    public static ArrayList<String> CheckMax(int n){
        File file = new File("C:/Users/you/Desktop/cmudict.0.7a.txt");
        OALDictionary<String, ArrayList<String>> PDict = new OALDictionary<String, ArrayList<String>>();  
        int Max = 0;
        ArrayList<String> returnList = new ArrayList<String>();
        
   		try {
   			Scanner scanner = new Scanner(file);
   			for(int i = 0; i <= n; i++){
                   ArrayList<String> value = new ArrayList<String>();
                   String line = scanner.nextLine();
   				   if (line.substring(0, 3).equals(";;;"))
   					   continue; // skip comment lines
      				Pronunciation p = new Pronunciation(line);
      				if(PDict.find(p.getPhonemes()) == null){
                       value.add(p.getWord());
                       PDict.insert(p.getPhonemes(), value);
                   }
                   else{
                       value = PDict.find(p.getPhonemes());
                       value.add(p.getWord());
                       PDict.insert(p.getPhonemes(), value);
                       if(PDict.find(p.getPhonemes()).size() >= Max) {
                           if(PDict.find(p.getPhonemes()).size() == Max){
                              returnList.add("\n");
                              returnList.addAll(PDict.find(p.getPhonemes()));
                           }
                           else{
                              returnList = new ArrayList<String>(PDict.find(p.getPhonemes()));
                              returnList.add(0, String.valueOf(PDict.find(p.getPhonemes()).size()));
                              Max = PDict.find(p.getPhonemes()).size();
                           }
                       }
                   }
            }
            scanner.close();
        } 
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
        return returnList;

    }
    
	public static void main(String[] args) {
	
        Scanner scan = new Scanner(System.in);
        int inty = scan.nextInt();
        
        ArrayList<String> finalList = new ArrayList<String>();
        finalList = CheckMax(inty);
        
        for(int i = 0; i <= finalList.size()-1; i++) {   
            System.out.println(finalList.get(i));
        }  

        scan.close();
	}
}
