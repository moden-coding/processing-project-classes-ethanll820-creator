import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.nio.file.Paths;

public class HighScore {

   public HighScore() { //doesnt do anything because it doesnt need to

    }

    String filePath = "data/highscoreCoins.txt";

    public void saveHighScore(int highScoreCoins) {// SAVE high score coins

        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(highScoreCoins); // Writes the integer to the file
            writer.close(); // Closes the writer and saves the file
            System.out.println("High score coins saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the high score.");
            e.printStackTrace();
        }
    }

    public int loadHighScore() {

        int highScoreCoins = 0;

        try (Scanner scanner = new Scanner(Paths.get(filePath))) {// make scanner

            while (scanner.hasNextLine()) {//read file
                String row = scanner.nextLine();//read one line
                highScoreCoins = Integer.parseInt(row);// convert line to integer
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return highScoreCoins;
    }
}
