import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class useFile {

    static ArrayList<Word> allWords = new ArrayList<Word>();

    public useFile()
    {
    }
    public static void readFile(String file)
    {
        try
        {
            Scanner sc = new Scanner(new File(file));

            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                Word word = new Word(line.toUpperCase());
                allWords.add(word);
            }
            sc.close();
        }
        catch(FileNotFoundException x)
        {
            System.out.println("Couldnt find the file.");
        }
    }

    public static boolean dontHaveTheWord(Word userWord)
    {
        for(Word word : allWords)
        {
            if(word.getString().equals(userWord.getString()))
            {
                return(false);
            }
        }
        return(true);
    }

    public static void main (String[] args)
    {
        new useFile();
        readFile("words.txt");
        System.out.print(allWords.size());
    }
}