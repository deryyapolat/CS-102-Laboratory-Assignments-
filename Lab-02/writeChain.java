import java.io.FileWriter;
import java.io.IOException;

public class writeChain {

    public static void main(String[] args) {
        useFile.readFile("words.txt");
        writeValidWords.removeWords();
        Word.createPossibleChainsForAll();
        try (FileWriter fw = new FileWriter("chains.txt"))
        {
            for (Word word : writeValidWords.validWords)
            {
                String line = word.getString().toLowerCase() + ":";
                for (Word chainWord : word.possibleChains)
                {
                    line = line + chainWord.getString().toLowerCase() + ",";
                }
                fw.write(line.trim() + "\n");
            }
        } catch (IOException e)
        {
            System.out.println("cant write the chains");
        }
    }
}
