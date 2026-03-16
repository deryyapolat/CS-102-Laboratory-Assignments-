public class Account
{
    char type;
    double amount;
    public Account(char type, double amount)
    {
        this.type = type;
        this.amount = amount;
    }
    public char getType()
    {
        return (type);
    }
    public double getAmount()
    {
        return (amount);
    }
    public double getAmountInCommonCurrency() //bakiye ile donusum orani
    {
        return (amount * Bank.getConversionRate(type));
    }
}
