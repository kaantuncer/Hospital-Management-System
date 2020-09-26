

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileReader {

    //This class reads a given txt file and returns an arraylist.

    public static ArrayList<String[]> readToList(String path){
        try{

            ArrayList<String[]> results = new ArrayList<String[]>();
            for(String line : Files.readAllLines(Paths.get(path))){
                String[] resultsArray = line.split("\t");
                results.add(resultsArray);

            }
            return results;
        }


        catch(Exception e){
            e.printStackTrace();
            System.out.println("File with path:("+path+") is missing");
            return null;


        }

    }
    public static ArrayList<String[]> readToListSpace(String path){
        try{

            ArrayList<String[]> results = new ArrayList<String[]>();
            for(String line : Files.readAllLines(Paths.get(path))){
                String[] resultsArray = line.split(" ");
                results.add(resultsArray);

            }
            return results;
        }


        catch(Exception e){
            e.printStackTrace();
            System.out.println("File with path:("+path+") is missing");
            return null;


        }

    }


}
