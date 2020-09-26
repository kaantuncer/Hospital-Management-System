

import java.io.File;
import java.io.FileOutputStream;

public class FileWriter {


    //This function takes in a file path and a string to write to a file.If there is no such file it will create one if the file already exist it will append to the file.

    public static void writeToNewFile(String fileName , String content){

        File file = new File(fileName);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            byte[] byteArray = content.getBytes();
            fos.write(byteArray);


            fos.close();

        }
        catch (Exception e){

            System.out.println("An error occurred while writing to file");

        }


    }
    public static void AppendToFile(String fileName , String content){

        File file = new File(fileName);

        try {
            FileOutputStream fos = new FileOutputStream(file,true);
            byte[] byteArray = content.getBytes();
            fos.write(byteArray);


            fos.close();

        }
        catch (Exception e){

            System.out.println("An error occurred while writing to file");

        }


    }

}
