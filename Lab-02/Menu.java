import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class Menu {

    public static void chainGenerator()
    {   
        Scanner scan = new Scanner(System.in);
        System.out.print("\nWhat word would you like to create a chain for?: ");
        String userInput = scan.nextLine().toUpperCase();
        Word userWord = new Word (userInput);
        while(useFile.dontHaveTheWord(userWord))
        {
            System.out.print("\nCant find the word (" + userWord.getString() + ") in our dictionary. \nPlease chose a new one: ");
            userInput = scan.nextLine().toUpperCase();
            userWord= new Word (userInput);
        }

        Random random = new Random();
        ArrayList<Word> chainForUser = new ArrayList<Word>();
        Word previousWord = userWord;
        Word chainForPrevious;

        while(chainForUser.size()<9)
        {
            previousWord.checkChainsForEveryWord();
            chainForPrevious = previousWord.possibleChains.get(random.nextInt(previousWord.possibleChains.size()));
            if(!chainForUser.contains(chainForPrevious))
            {
                chainForUser.add(chainForPrevious);
                previousWord = chainForPrevious;
            }
        }

        System.out.println("\nYour chain: ");
        System.out.print(userWord.getString());

        for(int i=0; i<chainForUser.size(); i++)
        {
            System.out.print(" - " + chainForUser.get(i).getString());
        }

    }

    public static void playTheGame()
    {
        Random random = new Random();
        Scanner scan = new Scanner(System.in);

        Word firstWord = useFile.allWords.get(random.nextInt(useFile.allWords.size()));
        firstWord.checkChainsForEveryWord();

        Word secondWord = firstWord.possibleChains.get(random.nextInt(firstWord.possibleChains.size()));
        secondWord.checkChainsForEveryWord();
        //to see the answer delete the "//" below
        //System.out.println(secondWord.getString());

        Word thirdWord = secondWord.possibleChains.get(random.nextInt(secondWord.possibleChains.size()));
        while(thirdWord.getString().equals(firstWord.getString()))
        {
            thirdWord=secondWord.possibleChains.get(random.nextInt(secondWord.possibleChains.size()));
        }

        System.out.println("\nGAME RULES: \n You will be given a chain of three words. \n You will try to guess the middle word. \n You have 3 chances.");

        System.out.println("\nThe chain is: ");
        System.out.println(firstWord.getString() + " - ? - " + thirdWord.getString());
        System.out.print("\nGuess the middle word: ");

        String answer = scan.nextLine().toUpperCase();
        int userChance = 3;

        while(!answer.equals(secondWord.getString())&&userChance>1)
        {
            userChance--;
            System.out.print("\nYou now have " + userChance + " more chances left.\n Try again: ");
            answer = scan.nextLine().toUpperCase();
        }

        if(answer.toUpperCase().equals(secondWord.getString()))
        {
            System.out.print("\nYou guessed correctly. Congrats!");
        }
        else
        {
            System.out.println("\nYou are out of chances. The answer was " + secondWord.getString() +"!");
            System.out.println(firstWord.getString() + " - " + secondWord.getString() + " - " + thirdWord.getString());
            System.out.print("Maybe next time?");
        }
    }

    public static void displayMenuAgain()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("\n \nWelcome Again! ");
        System.out.print("\n Select 1 to generate a chain. \n Select 2 to play the Chain Game! \n Select 3 to exit the menu. \n Choice: ");
        int choice = scan.nextInt();
        switch(choice)
        {
            case 1:
                chainGenerator();
                displayMenuAgain();
                break;
            case 2:
                playTheGame();
                displayMenuAgain();
                break;
            case 3:
                break;
        }  
    }

    public static void main (String[] args)
    {
        new useFile();
        useFile.readFile("filtered_words.txt");
        Scanner scan = new Scanner(System.in);
        System.out.print("Welcome!");
        System.out.print("\n Select 1 to generate a chain. \n Select 2 to play the Chain Game! \n Select 3 to exit the menu. \n Choice: ");
        int choice = scan.nextInt();
        switch(choice)
        {
            case 1:
                chainGenerator();
                displayMenuAgain();
                break;
            case 2:
                playTheGame();
                displayMenuAgain();
                break;
            case 3:
                break;
        }        
    }
}
