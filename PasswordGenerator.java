import java.util.*;

public class PasswordGenerator {

    private static final Random rand = new Random();


    private static String randomNumbers(int numberConut){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<numberConut; i++){
            int random = rand.nextInt(10) + 48;
            sb.append((char) random);
        }
        return sb.toString();
    }

    private static String randomSpecialCharacters(int specialCharactersCount){
        int[] specialCharRanges = {33, 48, 58, 65, 91, 97, 123, 127};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < specialCharactersCount; i++) {
            int rangeIndex = rand.nextInt(specialCharRanges.length / 2) * 2; // Select a random range for special characters
            int random = rand.nextInt(specialCharRanges[rangeIndex + 1] - specialCharRanges[rangeIndex]) + specialCharRanges[rangeIndex]; // Generate a random special character
            sb.append((char) random);
        }
        return sb.toString();
    }

    private static String randomUppercaseLetters(int upperCaseLetterCount){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<upperCaseLetterCount; i++){
            int random = rand.nextInt(26) + 65;
            sb.append(Character.toString((char) random));
        }
        return sb.toString();
    }

    private static String randomLowerCaseLetters(int lowerCaseLetterCount){
        StringBuilder sb = new StringBuilder();


        for(int i=0; i<lowerCaseLetterCount; i++){
            int random = rand.nextInt(26) + 97;
            sb.append(Character.toString((char) random));

        }
        return sb.toString();
    }

    private static String passwordShuffle(String randomNumbers, String randomSpecialCharacters, String randomUppercaseLetters, String randomLowerCaseLetters){
        List<Character> characters = new ArrayList<>();
        String password = randomNumbers + randomSpecialCharacters + randomUppercaseLetters + randomLowerCaseLetters;
        for(char c : password.toCharArray()){
            characters.add(c);
        }

        //shuffle the list of characters
        Collections.shuffle(characters);

        //convert the shuffled list of characters into a string
        StringBuilder shuffledString = new StringBuilder();
        for (char c : characters) {
            shuffledString.append(c);
        }

        return shuffledString.toString();

    }

    private static int getPrompt(Scanner scanner, String prompt){
        String countString;

        while(true){


            //Prompt user to input the number of numbers or special characters or uppercase letters or lowercase letters for the password
            System.out.println(prompt);
            countString = scanner.nextLine();

            //check if the input contains a character
            boolean containsChar = false;
            for (char c : countString.toCharArray()) {
                if (Character.isLetter(c)) {
                    containsChar = true;
                    System.out.println("Invalid input. Please enter a numbers only.");
                    break;
                }
            }
            //if the input contains a character
            if(containsChar){
                continue;
            }
            //parse the input to an integer and validate the input
            try{
                int count = Integer.parseInt(countString);
                if(count <= 0){//if the input is less than or equal to 0
                    System.out.println("Count must be betweem 1 and 50.");
                } else if (count>50) {//if the input is greater than 50
                    System.out.println("Enter the number between 1 and 50.");
                } else {//if the input is valid
                    return count;
                }
            }catch (NumberFormatException e){//if the input is not a number
                System.out.println("Invalid input. Please enter a number.");

            }
        }

    }
    
    //main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //TAKE DATA FROM USER

        //Prompt user to input the number of numbers for the password
        int numberCount = getPrompt(scanner, "How many numbers?"); //initializing numberCount to 0 to avoid error in

        //Prompt user to input the number of special characters for the password
        int specialCharactersCount = getPrompt(scanner, "How many special characters?"); //initializing specialCharactersCount to 0 to avoid error in

        //Prompt user to input the number of uppercase letters for the password
        int upperCaseLettersCount = getPrompt(scanner, "How many uppercase letters?"); //initializing upperCaseLettersCount to 0 to avoid error in

        //Prompt user to input the number of lowercase letters for the password
        int lowerCaseLettersCount = getPrompt(scanner, "How many lowercase letters?"); //initializing lowerCaseLettersCount to 0 to avoid error in

        //Generate random - numbers, special characters, uppercase and lowercase letters
        String randomNumbers = randomNumbers(numberCount);
        String randomSpecialCharacters = randomSpecialCharacters(specialCharactersCount);
        String randomUppercaseLetters = randomUppercaseLetters(upperCaseLettersCount);
        String randomLowerCaseLetters = randomLowerCaseLetters(lowerCaseLettersCount);

        //Generate password
        String password = passwordShuffle(randomNumbers, randomSpecialCharacters, randomUppercaseLetters, randomLowerCaseLetters);
        //Print the generated password
        System.out.println("Generated password: ");
        System.out.println(password);


        //Writing password to the file
        WriteToFile.writeToFile("passwords.txt", password, scanner);

        //Closing the scanner
        scanner.close();

    }
}