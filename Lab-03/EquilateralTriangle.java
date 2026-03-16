class EquilateralTriangle extends GeometricShape2D
{
    protected float side;

    public EquilateralTriangle(float x)
    {
        this.side=x;
    }

    public float calculateArea()
    {
        float area=(float)(side*side*Math.sqrt(3)/4);
        return(area);
    }

    public void printInfo()
    {
        System.out.println("Equilateral triangle, side " + side);
    }
}
