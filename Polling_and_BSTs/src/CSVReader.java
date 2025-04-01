// //
// 
//   A Java, CSV Reader script to include in student starter files
//  
//     Author: Summer worker Mia Ellis-Einhorn
//     Date: Summer 2022
//     
// 
//

import java.io.*;
import java.util.ArrayList;

public class CSVReader {

    BufferedReader br;

    // Main function of CSV Reader class, takes in input from CSV File and then output as ArrayList. 
    public ArrayList<String[]> read(FileReader csvFile){
        ArrayList<String[]> eachLine = new ArrayList<String[]>();
        
        try {
            br = new BufferedReader(csvFile);
            String line = "";
            String[] tempArr;
            br.readLine();
            while((line = br.readLine()) != null) {
               tempArr = line.split(",");
               eachLine.add(tempArr);
            }
            
            } catch(IOException ioe) {
               ioe.printStackTrace();
            }
        return(eachLine); // output as ArrayList of Strings. 
    }

    // Close buffered reader once finished getting data from CSV. 
    public void close(){
        try {
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
         }

    }
}
