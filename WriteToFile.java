import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class WriteToFile {
    public static void writeToFile(String fileName, String content, Scanner scanner) {
        System.out.println("What is this password for?");

        String passwordTo = scanner.nextLine();


        try(FileWriter writer = new FileWriter(fileName, true)) {
            //writing password to the file, writing what the password is to, and adding a new line
            writer.write(passwordTo + " - " + content + System.lineSeparator());
            //closing the writer
            writer.close();
            //info message
            System.out.println("Password has been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}