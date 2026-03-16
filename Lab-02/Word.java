import java.util.ArrayList;

public class Word
{
    String line;
    ArrayList<Word> possibleChains;

    public Word(String line)
    {
        this.line=line.toUpperCase();
        this.possibleChains = new ArrayList<Word>();
    }

    public String getString()
    {
        String wordString = this.line;
        return(wordString);
    }

    public boolean canChain(Word otherWord)
    {
        String word1String = this.getString();
        String word2String = otherWord.getString();

        int word1Length = word1String.length();
        int word2Length = word2String.length();


        if(Math.abs(word1Length-word2Length)>1)
        {
            return(false);
        }
        else if(word1Length==word2Length)
        {
            int differentCharCount=0;
            for(int i=0; i< word1Length; i++)
            {
                if(word1String.charAt(i)!=word2String.charAt(i))
                {
                    differentCharCount++;
                }
                if(differentCharCount>1)
                {
                    return(false);
                }
            }
            return(differentCharCount==1);
        }
        else
        {
            //word1 should be shorter
            if(word1Length>word2Length)
            {
                String temp=word1String;
                word1String=word2String;
                word2String= temp;
            }

            int charFor1=0;
            int charFor2=0;
            boolean isDifferent = false;

            while(charFor1<word1String.length()&&charFor2<word2String.length())
            {
                if(word1String.charAt(charFor1)!=word2String.charAt(charFor2))
                {
                    if(isDifferent)
                    {
                        return(false);
                    }   
                    isDifferent = true;
                    charFor2++;
                }
                else
                {
                    charFor1++;
                    charFor2++;
                }
            }
            return(true);
        }
    }

    public void checkChainsForEveryWord()
    {
        for(int i =0; i< useFile.allWords.size(); i++)
        {
            Word otherWord =useFile.allWords.get(i);
            if(this.canChain(otherWord))
            {
                this.addToPossibleChains(otherWord);
            }
        }
    }

    public void addToPossibleChains(Word chainWord)
    {
        possibleChains.add(chainWord);
    }

    public static void createPossibleChainsForAll()
    {
        for(int i=0; i<useFile.allWords.size(); i++)
        {
            useFile.allWords.get(i).checkChainsForEveryWord();
        }
    }
}