import java.util.Random;
import java.util.Scanner;

public class Main
{
    static User[] users = new User[100000];
    static User[] originalUsers = new User[100000];
    static int userCount = 0;
    static int actualUserCount = 0;
    static String[] firstName = { "Haydar", "Ayşegül", "Deniz", "Derya", "Gün", "Sebahat", "Riza", "Fuat", "Döndü", "Hidir", "Taylan","Çağan", "Ozan", "Oya", "Güngör", "Melih", "Levent"};
    static String[] lastName = { "Polat", "Cebeci", "Binzet", "Torun", "Berzeg"};
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args)
    {
        Bank.initRates();
        System.out.println("welcome to the bank!");
        System.out.println("current conversion rates:");
        System.out.println();
        Bank.displayRates();
        System.out.println();
        boolean whileBoolean = true;

        while (whileBoolean)
        {
            printMenu();
            System.out.print("option: ");
            int userOption = scanner.nextInt();
            switch (userOption) 
            {
                case 1: 
                    generateRandomUsers();
                    break;
                case 2:
                    listUsers();
                    break;
                case 3: 
                    showUserById();
                    break;
                case 4: 
                    setConversionRates();
                    break;
                case 5:
                    sortUsers();
                    break;
                case 6: 
                    resetUsers();
                    break;
                case 0:
                    System.out.println("byeee");
                    whileBoolean=false;
            }
            System.out.println();
        }
    }

    private static void printMenu() 
    {
        System.out.println("what do you want to do?");
        System.out.println();
        System.out.println("1. generate random users");
        System.out.println("2. list users");
        System.out.println("3. show user with ID");
        System.out.println("4. set conversion rates");
        System.out.println("5. sort users");
        System.out.println("6. reset to the original unsorted array");
        System.out.println("0. exit");
    }
    private static void generateRandomUsers()
    {
        System.out.print("generate how many?: ");
        int randomUserCount=scanner.nextInt();
        userCount=0;
        System.out.println();
        System.out.println("generating " + randomUserCount + " random users");
        System.out.println();
        for (int i = 0; i < randomUserCount; i++)
        {
            String id = String.format("%09d", random.nextInt(1000000000));
            String randomFirstName = firstName[random.nextInt(firstName.length)];
            String randomLastName = lastName[random.nextInt(lastName.length)];
            User newUser = new User(id, randomFirstName, randomLastName);
    
            int accounttCount = random.nextInt(9)+2;
            for (int j = 0; j < accounttCount; j++)
            {
                char type="ABCD".charAt(random.nextInt(4));
                double amount=random.nextDouble() * 1000;
                newUser.addAccount(new Account(type, amount));
            }
            users[userCount++]=newUser;
        }
        actualUserCount = userCount;
        for (int i = 0; i < userCount; i++)
        {
            originalUsers[i] = users[i].deepCopy();
        }
    }
    private static void listUsers()
    {
        for (int i = 0; i < userCount; i++)
        {
            User user = users[i];
            System.out.printf("%s %s %s total Amount: %.4f%n", user.getId(), user.getName(), user.getSurname(), user.getTotalAmountInCommonCurrency());
        }
    }
    private static void showUserById()
    {
        System.out.print("enter user id: ");
        String id = scanner.next();
        try
        {
            showUser(id);
        }
        catch (UserNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
    private static void showUser(String id) throws UserNotFoundException
    {
        for (int i = 0; i < userCount; i++)
        {
            User u = users[i];
            if (u.getId().equals(id))
            {
                System.out.print("total Amount: " + u.getId()+ u.getName() + u.getSurname() +u.getTotalAmountInCommonCurrency());
                System.out.println("accounts:");
                int idx = 1;
                for (Account a : u.getAccounts())
                {
                    System.out.printf("%d. type: %c amount: %.4f common: %.4f%n", idx++, a.getType(), a.getAmount(), a.getAmountInCommonCurrency());
                }
                return;
            }
        }
        throw new UserNotFoundException("cant find user!");
    }
    private static void setConversionRates()
    {
        System.out.print("set A: ");
        Bank.setConversionRate('A', scanner.nextDouble());
        System.out.print("set B: ");
        Bank.setConversionRate('B', scanner.nextDouble());
        System.out.print("set C: ");
        Bank.setConversionRate('C', scanner.nextDouble());
        System.out.print("set D: ");
        Bank.setConversionRate('D', scanner.nextDouble());
    }
    private static void resetUsers()
    {
        userCount = actualUserCount;
        for (int i = 0; i < actualUserCount; i++)
        {
            users[i] = originalUsers[i].deepCopy();
        }
    }
    private static void sortUsers()
    {
        System.out.println("choose sort type:");
        System.out.println("1. by id");
        System.out.println("2. by total amount");
        System.out.print("option: ");
        int type = scanner.nextInt();

        System.out.print("enter sort limit: ");
        int limit = scanner.nextInt();

        boolean byId;
        if (type == 1)
        {
            byId = true;
        }
        else if (type == 2)
        {
            byId = false;
        }
        else
        {
            System.out.println("invalid sort type.");
            return;
        }

        long start = System.currentTimeMillis();
        hybridSort(users, 0, userCount, limit, byId);
        long finish = System.currentTimeMillis();
        System.out.println("sort duration: " + (finish - start));
    }

    private static void hybridSort(User[] arr, int from, int to, int limit, boolean byId)
    {
        if (to - from <= 1) 
        {
            return;
        }
        if (to - from > limit)
        {
            int pivot = partition(arr, from, to, byId);
            hybridSort(arr, from, pivot, limit, byId);
            hybridSort(arr, pivot + 1, to, limit, byId);
        }
        else
        {
            insertionSort(arr, from, to, byId);
        }
    }
    private static int partition(User[] arr, int from, int to, boolean byId)
    {
        User pivot = arr[to - 1];
        int i = from;
        for (int j = from; j < to - 1; j++)
        {
            if (compare(arr[j], pivot, byId) < 0)
            {
                User tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
            }
        }
        arr[to - 1] = arr[i];
        arr[i] = pivot;
        return i;
    }
    private static void insertionSort(User[] arr, int from, int to, boolean Id)
    {
        for (int i=from+1;i<to; i++)
        {
            User key = arr[i];
            int j=i-1;
            while ((j >= from) && (compare(arr[j], key, Id) > 0))
            {
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }
    private static int compare(User user1, User user2, boolean Id)
    {
        if (Id)
        {
            return (user1.getId().compareTo(user2.getId()));
        }
        else
        {
            return (Double.compare(user1.getTotalAmountInCommonCurrency(), user2.getTotalAmountInCommonCurrency()));
        }
    } 
}