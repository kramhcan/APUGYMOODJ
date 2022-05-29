import org.apache.commons.lang3.ArrayUtils;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class with methods involving file reading
 */
abstract class ReadWrite{
    private int lastAcc;
    private String[] inputData;

    public void setInput(String[] input){
        inputData = input;
    }

    //<editor-fold desc="Staff read write methods">
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

    public String[] getStaffData(String uID){
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
            int x = 0;
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
                if(x!=0){ sb.append("\r\n"); }
                sb.append(row);
                x++;
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
    //</editor-fold>

    //<editor-fold desc="Member read write methods">
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

            bw.write("\r\n"+(lastAcc+1)+","+inputData[0]+","+inputData[1]+","+inputData[2]+","
                    +inputData[3]+","+inputData[4]+","+inputData[5]+","+inputData[6]+","+inputData[7]+","+inputData[8]+",Disabled");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object[][] memberTableData(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Member.txt"));
            String s="";
            Path path = Path.of("src/main/resources/Member.txt");
            int lines = (int) Files.lines(path).count();

            Object dataMulti[][]= new Object[lines][11];
            Object dataSingle[] = new Object[13];

            int r = 0;
            while ((s=br.readLine()) != null){
                dataSingle = s.split(",");
                for(int i = 0; i<11; i++){
                    dataMulti[r][i] = dataSingle[i];
                }
                r++;
            }
            return dataMulti;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getMemberData(String uID){
        try {
            String recordID = "";

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Member.txt"));
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

    public void updateMemberInfo(){
        try {
            String recordID = "";
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Member.txt"));
            String s="";
            int x = 0;
            while ((s=br.readLine()) != null){
                String row;
                String data[] = new String[13];
                data = s.split(",");
                recordID = data[0].toString();
                if(recordID.equals(inputData[0])){
                    row = inputData[0]+","+inputData[1]+","+inputData[2]+","+inputData[3]+","+inputData[4]+","+inputData[5]+","+inputData[6]+","+inputData[7]+","+inputData[8]+","+inputData[9]+","+inputData[10];
                } else {
                    row = s;
                }
                if(x!=0){ sb.append("\r\n"); }
                sb.append(row);
                x++;
            }
//            System.out.println(sb);
            File file = new File("src/main/resources/Member.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file,false));
            pw.print(sb);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enableMember(String ID){
        try {
            String recordID = "";
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Member.txt"));
            String s="";
            int x = 0;
            while ((s=br.readLine()) != null){
                String row;
                String data[] = new String[11];
                data = s.split(",");
                recordID = data[0].toString();
                if(recordID.equals(ID)){
                    row = data[0]+","+data[1]+","+data[2]+","+data[3]+","+data[4]+","+data[5]+","+data[6]+","+data[7]+","+data[8]+","+data[9]+",Enabled";
                } else {
                    row = s;
                }
                if(x!=0){ sb.append("\r\n"); }
                sb.append(row);
                x++;
            }
//            System.out.println(sb);
            File file = new File("src/main/resources/Member.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file,false));
            pw.print(sb);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getMemberIDs(){
        try {
            ArrayList<Object> idList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Member.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[10];
                data = s.split(",");
                idList.add(data[0]+" ; "+data[2]+" "+data[3]);
            }
            Object[] idArray = idList.toArray();
            String[] res = Arrays.copyOf(idArray, idArray.length, String[].class);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getSessionID(){
        try {
            ArrayList<Object> idList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sessions.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[0];
                data = s.split(",");
                idList.add(data[0]);
            }
            Object[] idArray = idList.toArray();
            String[] res = Arrays.copyOf(idArray, idArray.length, String[].class);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getMemberIDs(Boolean byDisabled){
        try {
            ArrayList<Object> idList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Member.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[10];
                data = s.split(",");
                if(data[10].equals("Enabled")){continue;}
                idList.add(data[0]+" ; "+data[2]+" "+data[3]);
            }
            Object[] idArray = idList.toArray();
            String[] res = Arrays.copyOf(idArray, idArray.length, String[].class);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMemberPackageByID(String ID){
        try {
            String pack = "";
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Member.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[9];
                data = s.split(",");
                if(data[0].equals(ID)) {
                    pack = data[9];
                }
            }
            return pack;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //</editor-fold>

    //<editor-fold desc="Read Write methods for sessions">
    /**
     * SELECT [UID] FROM Staff
     * WHERE EType = 'Trainer';
     **/
    public String[] getTrainerIDs(){
        try {
            String trainerCheck = "Trainer";
            ArrayList<Object> idList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Staff.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[13];
                data = s.split(",");
                if(trainerCheck.equals(data[10])){
                    idList.add(data[0]+" ; "+data[3]+" "+data[4]);   }
            }
            Object[] idArray = idList.toArray();
            String[] res = Arrays.copyOf(idArray, idArray.length, String[].class);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cancelSession(String ID){
        try {
            String recordID = "";
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sessions.txt"));
            String s="";
            int x = 0;
            while ((s=br.readLine()) != null){
                String row;
                String data[] = new String[10];
                data = s.split(",");
                recordID = data[0].toString();
                if(recordID.equals(ID)){
                    row = data[0]+","+data[1]+","+data[2]+","+data[3]+","+data[4]+","+data[5]+","+data[6]+","+data[7]+","+data[8]+",1";
                } else {
                    row = s;
                }
                if(x!=0){ sb.append("\r\n"); }
                sb.append(row);
                x++;
            }
//            System.out.println(sb);
            File file = new File("src/main/resources/Sessions.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file,false));
            pw.print(sb);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSessionPaid(String ID){
        try {
            String recordID = "";
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sessions.txt"));
            String s="";
            int x = 0;
            while ((s=br.readLine()) != null){
                String row;
                String data[] = new String[10];
                data = s.split(",");
                recordID = data[0].toString();
                if(recordID.equals(ID)){
                    row = data[0]+","+data[1]+","+data[2]+","+data[3]+","+data[4]+","+data[5]+","+data[6]+","+data[7]+",1,"+data[9];
                } else {
                    row = s;
                }
                if(x!=0){ sb.append("\r\n"); }
                sb.append(row);
                x++;
            }
//            System.out.println(sb);
            File file = new File("src/main/resources/Sessions.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file,false));
            pw.print(sb);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getMemberIDsWhereEnabled(){
        try {
            ArrayList<Object> idList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Member.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[9];
                data = s.split(",");
                idList.add(data[0]+" ; "+data[2]+" "+data[3]);
            }
            Object[] idArray = idList.toArray();
            String[] res = Arrays.copyOf(idArray, idArray.length, String[].class);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * SELECT Sessions.*, Member.First_Name, Staff.First_Name FROM Sessions
     * Left Join Sessions.Member_ID = Member.ID and Sessions.Trainer_ID = Trainer_Name;
     */
    public Object[][] sessionsTableData(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sessions.txt"));
            String s="";
            String mName = "";
            Path path = Path.of("src/main/resources/Sessions.txt");
            int lines = (int) Files.lines(path).count();

            Object dataMulti[][]= new Object[lines][10];
            Object dataSingle[] = new Object[13];

            int r = 0;
            while ((s=br.readLine()) != null){
                dataSingle = s.split(",");
                for(int i = 0; i<10; i++){
                    if(i==8){
                        if(dataSingle[i].equals("0")) {dataMulti[r][i] = "Unpaid";}
                        if(dataSingle[i].equals("1")) {dataMulti[r][i] = "Paid";}
                        continue;
                    }
                    if(i==9){
                        if(dataSingle[i].equals("0")) {dataMulti[r][i] = "No";}
                        if(dataSingle[i].equals("1")) {dataMulti[r][i] = "Yes";}
                        continue;
                    }
                    dataMulti[r][i] = dataSingle[i];
                }
                r++;
            }
            return dataMulti;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object[][] sessionsTableDataByTrainer(String TrainerID){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sessions.txt"));
            String s="";
            Path path = Path.of("src/main/resources/Sessions.txt");

            int idCount = CheckNumberOfLines(TrainerID);
            int lines = (int) Files.lines(path).count();
            int t = 0;

            Object dataMulti[][]= new Object[lines][10];
            Object dataSingle[] = new Object[13];

            int r = 0;
            while ((s=br.readLine()) != null){
                dataSingle = s.split(",");
                if(dataSingle[3].equals(TrainerID)){
                    t++;
                    for(int i = 0; i<10; i++){
                        dataMulti[t][i] = dataSingle[i];
                    }
                }
                r++;
            }
            return dataMulti;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int CheckNumberOfLines(String TrainerID){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sessions.txt"));
            String s="";
            int result = 0;
            int r = 0;
            while ((s=br.readLine()) != null){
                String[] data = new String[10];
                data = s.split(",");
                if(data[3].equals(TrainerID)){
                    result ++;
                }
                r++;
            }
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int[] calculateAvailableTime(String trainerID, String Date){
        try{
            int[] time = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sessions.txt"));
            String s="";
            int r = 0;

            while ((s=br.readLine()) != null){
                List<Integer> takenHourList = new ArrayList<>();
                String[] data = new String[10];
                data = s.split(",");
                if(!trainerID.equals(data[3])){ continue; }
                if(!Date.equals(data[5])){ continue; }
                if(data[9].equals("1")){ continue; }
                String rowTime = data[7];
                int rowTimeInt = Integer.parseInt(rowTime.substring(0, 2));
                int durationInt = Integer.parseInt(data[6]);
                for(int i = 0; i<durationInt; i++){
                    int takenHour = rowTimeInt+i;
                    takenHourList.add(takenHour);
                }
                int[] takenHourArray = takenHourList.stream().mapToInt(i->i).toArray();
                for(int i = 0; i < takenHourArray.length; i++ ){
                    int index = takenHourArray[i] - r;
                    time = ArrayUtils.remove(time, index);
                    r++;
                }
                System.out.println(Arrays.toString(time));
            }
            return time;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void newSession(){
        try {

            FileWriter fw = new FileWriter("src/main/resources/Sessions.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sessions.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[10];
                data = s.split(",");
                int i = Integer.parseInt(data[0]);
                lastAcc = i;
            }

            bw.write("\r\n"+(lastAcc+1)+","+inputData[0]+","+inputData[1]+","+inputData[2]+","+inputData[3]+","+inputData[4]+","+inputData[5]+","+inputData[6]+",0,0");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getSessionId(){
        try {
            ArrayList<Object> idList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sessions.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[10];
                data = s.split(",");
                if(data[8].equals("1")){ continue; }
                if(data[9].equals("1")){ continue; }
                if(data[0].equals("0")){ continue; }
                idList.add(data[0]);
            }
            Object[] idArray = idList.toArray();
            String[] res = Arrays.copyOf(idArray, idArray.length, String[].class);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSessionDurationByID(String ID){
        try {
            String duration = "";
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sessions.txt"));
            String s="";
            while ((s=br.readLine()) != null){
                String data[] = new String[10];
                data = s.split(",");
                if(data[0].equals(ID)) {
                    duration = data[6];
                }
            }
            return duration;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //</editor-fold>

    public boolean validateEmail(String input){
        System.out.println("To prove a point");
        return false;
    }

    public String getSessionDate(String sessionID) {
        try {
            String sessionDate = "";
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sessions.txt"));
            String s="";
            while ((s=br.readLine()) != null) {
                String data[] = new String[10];
                data = s.split(",");
                if (Objects.equals(data[0], sessionID)) {
                    sessionDate = data[5];
                }
            }
            return sessionDate;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public String[] getTrainerName(String sessionID) {
        try {
            String sessionDetails[] = new String[2];
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sessions.txt"));
            String s="";
            while ((s=br.readLine()) != null) {
                String data[] = new String[10];
                data = s.split(",");
                if (Objects.equals(data[0], sessionID)) {
                    sessionDetails[0] = data[3]; // Trainer ID
                    sessionDetails[1] = data[4]; // Trainer Name

                }
            }
            return sessionDetails;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public String[] getClientName(String sessionID) {
        try {
            String clientDetails[] = new String[2];
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sessions.txt"));
            String s="";
            while ((s=br.readLine()) != null) {
                String data[] = new String[10];
                data = s.split(",");
                if (Objects.equals(data[0], sessionID)) {
                    clientDetails[0] = data[1];
                    clientDetails[1] = data[2];
                }
            }
            return clientDetails;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void createFeedback(String[] input) {
            try {
                FileWriter fw = new FileWriter("src/main/resources/Feedback.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);

                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Feedback.txt"));
                String s="";

                while ((s=br.readLine()) != null) {
                    String data[] = new String[8];
                    data = s.split(",");
                    int i = Integer.parseInt(data[0]);
                    lastAcc = i;
                }
                bw.write("\r\n"+(lastAcc+1)+","+input[0]+","+input[1]+","+input[2]+","+input[3]+","+input[4]+","+input[5]+","+input[6]+","+input[7]);
                bw.close();
            }
            catch(IOException e) {
                System.out.println("File could not be created");
                e.printStackTrace();
            }
        }

}