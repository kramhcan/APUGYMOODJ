import java.io.*;
import java.util.Scanner;

/**
 * Class with read + write methods
 */
public class ReadWrite {

    public void Reader() {
        Scanner x = new Scanner("C:\\Users\\markc\\IdeaProjects\\APUGYMOODJ\\src\\main\\resources\\ReadWrite.txt");
        boolean character;
        String out = "";

        while ((character = x.hasNext()) != false) {
            out = x.next();
            System.out.print(out);
        }
        x.close();
    }

    public void Reader(String filepath) {
        try {
            FileReader reader = new FileReader(filepath);
            int character;

            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Writer(){
        //Test method
            try {
                String text = new String();
                text = "Testing";
                FileWriter fw = new FileWriter("C:\\Users\\markc\\IdeaProjects\\APUGYMOODJ\\src\\main\\resources\\ReadWrite.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(text+","+text+"\r\n");
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void Writer(String username, String password){
        try {
//            String text = new String();
//            text = "Testing";
            FileWriter fw = new FileWriter("C:\\Users\\markc\\IdeaProjects\\APUGYMOODJ\\src\\main\\resources\\ReadWrite.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(username+","+password+"\r\n");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean Login(String username,String password){
        try {
            String recordUsername = ""; String recordPassword = ""; String emptySpace = "";
//            File file = new File("C:\\Users\\markc\\IdeaProjects\\APUGYMOODJ\\src\\main\\resources\\ReadWrite.txt");
//
//            FileWriter fw = new FileWriter(file,true);
//            BufferedWriter bw = new BufferedWriter(fw);
//
//            PrintWriter pw = new PrintWriter(bw);

            Scanner x = new Scanner(new File("C:\\Users\\markc\\IdeaProjects\\APUGYMOODJ\\src\\main\\resources\\ReadWrite.txt"));
            x.useDelimiter("[,\r\n]");

            while(x.hasNext())
            {
                    recordUsername = x.next();
                    recordPassword = x.next();
                    emptySpace = x.next();
                    if(recordUsername.equals(username) && recordPassword.equals(password)){  return true;  }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}