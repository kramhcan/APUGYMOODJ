import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class with general functions/methods
 **/
public class Functions {
    public boolean checkMissingInput(String[] input,String[] fieldName, JFrame frame){
        List<Integer> m = new ArrayList<>();

        for(int i=0; i<input.length; i++){
            if(input[i].isBlank()){ m.add(i); }
        }

        int[] res = m.stream().mapToInt(i->i).toArray();
//        System.out.println(Arrays.toString(res));
        if(res.length>0){
            List<String> missingFieldNamesList = new ArrayList<>();
            for(int i = 0; i < res.length; i++){
                missingFieldNamesList.add(fieldName[res[i]]);}
            String[] missingFieldNamesIndex = missingFieldNamesList.toArray(String[]::new);

            JOptionPane.showMessageDialog(frame, "There are missing fields,\nPlease check the following fields:\n" + Arrays.toString(missingFieldNamesIndex));
            return true;
        }
        return false;
    }

    public boolean validateEmail(String input){
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(input);
        return matcher.find();
    }

    public boolean validateContact(String input){
        String conRegex = "^(01)[0-46-9]*[0-9]{7,8}$";
        Pattern conPattern = Pattern.compile(conRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = conPattern.matcher(input);
        return matcher.find();
    }

    public boolean generalValidation(String input){
        String conRegex = "^[a-zA-Z0-9 ()]*$";
        Pattern conPattern = Pattern.compile(conRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = conPattern.matcher(input);
        return matcher.find();
    }
    public String addressReplace(String input){
        String str = input.replaceAll("[^a-zA-Z0-9 /.]","");
        return str;
    }

//    public void calculateAvailableTime(){
//        int[] time = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
//    }
}

