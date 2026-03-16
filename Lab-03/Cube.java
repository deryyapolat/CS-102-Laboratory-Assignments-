class Cube extends Cuboid
{
    public Cube(float x)
    {
        super(x,x,x);
    }   
    
    public void printInfo()
    {
        System.out.println("Cube, side length " + width);
    }
}
