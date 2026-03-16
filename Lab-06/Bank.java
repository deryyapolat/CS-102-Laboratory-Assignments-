import java.util.Random;
public class Bank
{
    static char[] currencies = {'A','B','C','D'};
    static double[] conversionRates = new double[4];
    public static void initRates()
    {
        Random r = new Random();
        for (int i = 0; i < 4; i++)
        {
            conversionRates[i] = 0.2 + r.nextDouble() * (5.0 - 0.2);
        }
    }
    public static void displayRates()
    {
        for (int i = 0; i < 4; i++) {
            System.out.println(currencies[i] + "  "+ conversionRates[i]);
        }
    }
    public static double getConversionRate(char type)
    {
        int index = type - 'A';
        return (conversionRates[index]);
    }
    public static void setConversionRate(char type, double rate)
    {
        int idx = type - 'A';
        conversionRates[idx] = rate;
    }
}