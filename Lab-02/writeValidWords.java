import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class writeValidWords {

    static ArrayList<Word> validWords = new ArrayList<Word>(); 

    public static void removeWords()
    {
        for(Word current : useFile.allWords)
        {
            for(Word other: useFile.allWords)
            {
                if(!current.equals(other)&&current.canChain(other))
                {
                    validWords.add(current);
                    break;
                }
            }
        }
        useFile.allWords = validWords;
    }

    public static void main(String[] args)
    {
        useFile.readFile("words.txt");
        removeWords();
        ArrayList<String> tempWordsList = new ArrayList<String>();
        for(Word word : validWords)
        {
            System.out.println(word.getString());
            tempWordsList.add(word.getString().toLowerCase());
        }

        String filename = "filtered_words.txt";

        try(FileWriter fw = new FileWriter(filename);)
        {
            for (String word : tempWordsList)
            {
                fw.write(word + "\n");
            }
            fw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}