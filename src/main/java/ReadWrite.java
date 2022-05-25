import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class with methods involving file reading
 */
public class ReadWrite {
    int lastAcc;

//    public void Reader() {
//        Scanner x = new Scanner("C:\\Users\\markc\\IdeaProjects\\APUGYMOODJ\\src\\main\\resources\\ReadWrite.txt");
//        boolean character;
//        String out = "";
//
//        while ((character = x.hasNext()) != false) {
//            out = x.next();
//            System.out.print(out);
//        }
//        x.close();
//    }

//    public void Reader(String filepath) {
//        try {
//            FileReader reader = new FileReader(filepath);
//            int character;
//
//            while ((character = reader.read()) != -1) {
//                System.out.print((char) character);
//            }
//            reader.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void Writer(){
//        //Test method
//            try {
//                String text = new String();
//                text = "Testing";
//                FileWriter fw = new FileWriter("C:\\Users\\markc\\IdeaProjects\\APUGYMOODJ\\src\\main\\resources\\ReadWrite.txt",true);
//                BufferedWriter bw = new BufferedWriter(fw);
//
//                bw.write(text+","+text+"\r\n");
//                bw.close();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//    }

//    public void Writer(String username, String password){
//        try {
////            String text = new String();
////            text = "Testing";
//            FileWriter fw = new FileWriter("C:\\Users\\markc\\IdeaProjects\\APUGYMOODJ\\src\\main\\resources\\ReadWrite.txt",true);
//            BufferedWriter bw = new BufferedWriter(fw);
//
//            bw.write(username+","+password+"\r\n");
//            bw.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public boolean Login(String username,String password){
        try {
            String recordUsername = ""; String recordPassword = ""; String emptySpace = "";

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Staff.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[12];
                data = s.split(",");
                recordUsername = data[1].toString();
                recordPassword = data[2].toString();
                if(recordUsername.equals(username) && recordPassword.equals(password)){  return true;  }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void RegisterStaff(String[] input){
        try {

            FileWriter fw = new FileWriter("src/main/resources/Staff.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Staff.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[12];
                data = s.split(",");
                int i = Integer.parseInt(data[0]);
                lastAcc = i;
            }

            bw.write("\r\n"+(lastAcc+1)+","+input[0]+","+input[1]+","+input[2]+","+input[3]+","+input[4]+","+input[5]+","+input[6]+","+input[7]+","+input[8]+",Active");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean existingStaffUser(String[] input, JFrame frame){
        try {
            String recordUsername = ""; String recordPassword = ""; String emptySpace = "";

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Staff.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[12];
                data = s.split(",");
                recordUsername = data[1].toString();
                if(recordUsername.equals(input[0])){
                    JOptionPane.showMessageDialog(frame, "User with similar username exists.");
                    return true;  }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}