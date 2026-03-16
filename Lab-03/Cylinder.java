class Cylinder extends GeometricShape3D
{
    protected float radius;
    protected float height;

    public Cylinder(float r, float h)
    {
        this.radius=r;
        this.height=h;
    }

    public float calculateArea()
    {
        float area=(float)(2*Math.PI*radius*(radius+height));
        return(area);
    }

    public float calculateVolume()
    {
        float volume= (float)(Math.PI*radius*radius*height);
        return(volume);

    }

    public void printInfo()
    {
        System.out.println("Cylinder, radius" + radius);
    }

}
