import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Class with read + write methods
 */
public class ReadWriteTest {

    public void Reader() {
        try {
            FileReader reader = new FileReader("C:\\Users\\markc\\IdeaProjects\\APUGYMOODJ\\src\\main\\resources\\ReadWrite.txt");
            int character;

            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            String text = new String();
            text = "Testing";
            FileWriter fw = new FileWriter("C:\\Users\\markc\\IdeaProjects\\APUGYMOODJ\\src\\main\\resources\\ReadWrite.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(text+","+text+",\r\n");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}