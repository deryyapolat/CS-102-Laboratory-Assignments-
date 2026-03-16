public class Board
{
    public static Property[] properties = new Property[12];
    public Board()
    {
        int[] rentABC = {1, 2, 3, 4, 6};
        int[] rentDEF = {2, 2, 3, 3, 7};
        int[] rentGHI = {1, 3, 4, 6, 7};
        int[] rentJKL = {3, 3, 6, 6, 9};
        int priceABC = 2;
        int priceDEF = 4;
        int priceGHI = 6;
        int priceJKL = 8;
        int houseABCDEF = 1;
        int houseGHI = 2;
        int houseJKL = 3;
        properties[0] = new Property("A", priceABC, houseABCDEF, rentABC, 7);
        properties[1] = new Property("B", priceABC, houseABCDEF, rentABC, 8);
        properties[2] = new Property("C", priceABC, houseABCDEF, rentABC, 9);
        properties[3] = new Property("D", priceDEF, houseABCDEF, rentDEF, 16);
        properties[4] = new Property("E", priceDEF, houseABCDEF, rentDEF, 22);
        properties[5] = new Property("F", priceDEF, houseABCDEF, rentDEF, 28);
        properties[6] = new Property("G", priceGHI, houseGHI, rentGHI, 37);
        properties[7] = new Property("H", priceGHI, houseGHI, rentGHI, 36);
        properties[8] = new Property("I", priceGHI, houseGHI, rentGHI, 35);
        properties[9] = new Property("J", priceJKL, houseJKL, rentJKL, 26);
        properties[10] = new Property("K", priceJKL, houseJKL, rentJKL, 20);
        properties[11] = new Property("L", priceJKL, houseJKL, rentJKL, 14);
    }
    public Property getProperty(int pos)
    {
        switch(pos)
        {
            case 7: 
            return (properties[0]);
            case 8: 
            return (properties[1]);
            case 9: 
            return (properties[2]);
            case 16:
            return (properties[3]);
            case 22:
            return (properties[4]);
            case 28:
            return (properties[5]);
            case 37:
            return (properties[6]);
            case 36:
            return (properties[7]);
            case 35:
            return (properties[8]);
            case 26:
            return (properties[9]);
            case 20:
            return (properties[10]);
            case 14:
            return (properties[11]);
            default:
            return null;
        }
    }
}