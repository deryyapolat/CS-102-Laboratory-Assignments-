class Ellipse extends GeometricShape2D
{
    protected float axis1;
    protected float axis2;

    public Ellipse(float x, float y)
    {
        this.axis1=x;
        this.axis2=y;
    }

    public float calculateArea()
    {
        float area = (float)(Math.PI*axis1*axis2);
        return(area);
    }

    public void printInfo()
    {
        System.out.println("Ellipse, first axis " + axis1 +", second axis" + axis2);
    }
}
