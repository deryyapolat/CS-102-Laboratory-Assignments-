class Circle extends GeometricShape2D
{
    protected float radius;

    public Circle(float r)
    {
        this.radius=r;
    }

    public float calculateArea()
    {
        float area = (float)(Math.PI*radius*radius);
        return(area);
    }

    public void printInfo()
    {
        System.out.println("Circle, radius " + radius);
    }
}
