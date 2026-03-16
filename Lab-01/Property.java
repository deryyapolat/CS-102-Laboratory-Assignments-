public class Property {
    public String propertyName;
    int price;
    int pos;
    int housePrice;
    int houseCount;
    Player owner;
    int[] rent;
    
    public Property(String propertyName, int price, int housePrice, int[] rent, int pos)
    {
        this.propertyName = propertyName;
        this.price = price;
        this.housePrice = housePrice;
        this.rent = rent;
        this.houseCount = 0;
        this.owner = null;
        this.pos = pos;
    }
    public String getName()
    {
        return(this.propertyName);
    }
    public int getPos()
    {
        return(pos);
    }
    public int getPrice()
    {
        return (price);
    }
    public int getRent()
    {
        return (rent[houseCount]);
    }
    public boolean hasAOwner()
    {
        return (owner != null);
    }
    public Player getOwner()
    {
        return (owner);
    }
    public int getHouseCount(Property x)
    {
        return (this.houseCount);
    }
    public int getHousePrice()
    {
        return (housePrice);
    }
    public void setOwner(Player player)
    {
        this.owner = player;
    }
    public void buyHouse()
    {
        if (houseCount < 4)
        {
            houseCount++;
        }
        else
        {
            System.out.println("Maximum houses reached on property " + propertyName);
        }
    }
    public void sellProperty()
    {
        houseCount = 0;
        owner = null;
    }
}