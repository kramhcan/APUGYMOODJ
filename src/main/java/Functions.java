import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
