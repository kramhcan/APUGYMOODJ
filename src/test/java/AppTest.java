import java.util.Scanner;

public class AppTest {
    public static void main(String[] args) {
        Boolean x = Boolean.FALSE;
        ReadWriteTest readWriteTest = new ReadWriteTest();

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Username:");
//        String username = sc.nextLine();
//        System.out.println("Password:");
//        String password = sc.nextLine();

//        readWriteTest.Writer("mark","chan");
//        readWriteTest.Reader("C:\\Users\\markc\\IdeaProjects\\APUGYMOODJ\\src\\main\\resources\\ReadWrite.txt");

        System.out.println(readWriteTest.Login("mark","chan"));
    }
}
