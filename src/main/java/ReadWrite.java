import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Class with methods involving file reading
 */
public class ReadWrite {
    int lastAcc;
    String[] inputData;

    public void setInput(String[] input){
        inputData = input;
    }

    public boolean Login(String username,String password){
        try {
            String recordUsername = ""; String recordPassword = ""; String staffStatus = "";

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Staff.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[13];
                data = s.split(",");
                recordUsername = data[1].toString();
                recordPassword = data[2].toString();
                staffStatus = data[12].toString();
                if(staffStatus.equals("Disabled")){ JOptionPane.showMessageDialog(null, "This account has been deactivated");
                    return false;   }
                if(!recordUsername.equals(username) && !recordPassword.equals(password)){  JOptionPane.showMessageDialog(null, "Account or Password is incorrect");
                    return false;   }
                return true;
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
                String data[] = new String[13];
                data = s.split(",");
                int i = Integer.parseInt(data[0]);
                lastAcc = i;
            }

            bw.write("\r\n"+(lastAcc+1)+","+input[0]+","+input[1]+","+input[2]+","+input[3]+","+input[4]+","+input[5]+","+input[6]+","+input[7]+","+input[8]+","+input[9]+","+input[10]+",Enabled");
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
                String data[] = new String[13];
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

    public Object[][] staffTableData(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Staff.txt"));
            String s="";
            Path path = Path.of("src/main/resources/Staff.txt");
            int lines = (int) Files.lines(path).count();

            Object dataMulti[][]= new Object[lines][11];
            Object dataSingle[] = new Object[13];

            int r = 0;
            while ((s=br.readLine()) != null){
                dataSingle = s.split(",");
                dataMulti[r][0] = dataSingle[0];
                dataMulti[r][1] = dataSingle[3];
                dataMulti[r][2] = dataSingle[4];
                dataMulti[r][3] = dataSingle[5];
                dataMulti[r][4] = dataSingle[6];
                dataMulti[r][5] = dataSingle[7];
                dataMulti[r][6] = dataSingle[8];
                dataMulti[r][7] = dataSingle[9];
                dataMulti[r][8] = dataSingle[10];
                dataMulti[r][9] = dataSingle[11];
                dataMulti[r][10] = dataSingle[12];
                r++;
            }
            return dataMulti;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getUserData(String uID){
        try {
            String recordID = "";

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Staff.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[13];
                data = s.split(",");
                recordID = data[0].toString();
                if(recordID.equals(uID)){ return data; }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStaffInfo(String[] input){
        try {
            String recordID = "";
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Staff.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String row;
                String data[] = new String[13];
                data = s.split(",");
                recordID = data[0].toString();
                if(recordID.equals(input[0])){
                    row = input[0]+","+input[1]+","+input[2]+","+input[3]+","+input[4]+","+input[5]+","+input[6]+","+input[7]+","+input[8]+","+data[9]+","+data[10]+","+data[11]+","+input[9];
                } else {
                    row = s;
                }
                sb.append(row); sb.append("\r\n");
            }
//            System.out.println(sb);
            File file = new File("src/main/resources/Staff.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file,false));
            pw.print(sb);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Member/clients section
    public boolean existingMember(JFrame frame){
        try {
            String recordIC = "";
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Staff.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[13];
                data = s.split(",");
                recordIC = data[0].toString();
                if(recordIC.equals(inputData[0])){
                    JOptionPane.showMessageDialog(frame, "User with same IC exists.");
                    return true;  }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void registerMember(){
        try {

            FileWriter fw = new FileWriter("src/main/resources/Member.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Member.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[13];
                data = s.split(",");
                int i = Integer.parseInt(data[0]);
                lastAcc = i;
            }

            bw.write("\r\n"+(lastAcc+1)+","+inputData[0]+","+inputData[1]+","+inputData[2]+","+inputData[3]+","+inputData[4]+","+inputData[5]+","+inputData[6]+","+inputData[7]+",Enabled");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}