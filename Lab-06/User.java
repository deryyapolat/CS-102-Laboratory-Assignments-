import java.util.ArrayList;
import java.util.List;

public class User
{
    private String id;
    private String name;
    private String surname;
    private List<Account> accounts;
    public User(String id, String name, String surname)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.accounts = new ArrayList<>();
    }
    public String getId()
    {
        return (id);
    }
    public String getName()
    {
        return (name);
    }
    public String getSurname()
    {
        return (surname);
    }
    public void addAccount(Account account)
    {
        accounts.add(account);
    }
    public List<Account> getAccounts()
    {
        return (accounts);
    }
    public double getTotalAmountInCommonCurrency()
    {
        double sum = 0;
        for (Account a:accounts)
        {
            sum+=a.getAmountInCommonCurrency();
        }
        return (sum);
    }
    public User deepCopy()
    {
        User copy = new User(id, name, surname);
        for (Account a : accounts)
        {
            copy.addAccount(new Account(a.getType(), a.getAmount()));
        }
        return (copy);
    }
}
