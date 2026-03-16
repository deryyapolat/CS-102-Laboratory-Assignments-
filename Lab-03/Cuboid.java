class Cuboid extends GeometricShape3D
{
    protected float width;
    protected float height;
    protected float depth;
    
    public Cuboid(float w, float h, float d)
    {
        this.width=w;
        this.height=h;
        this.depth=d;
    }

    public float calculateArea()
    {
        float area=2*(width*height+height*depth+depth*width);
        return(area);
    }

    public float calculateVolume()
    {
        float volume = width*height*depth;
        return(volume);
    }

    public void printInfo()
    {
        System.out.println("Cuboid, " + width + " by " + height + " by " + depth);
    }
}
