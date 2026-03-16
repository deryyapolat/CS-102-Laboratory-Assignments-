class MultiShape2D extends GeometricShape2D
{
    protected GeometricShape2D[] multiShape = new GeometricShape2D[0];
    
    public void addMultiShape(GeometricShape2D addedShape)
    {
        GeometricShape2D[] copyArray =new GeometricShape2D[multiShape.length + 1];
        System.arraycopy(multiShape, 0, copyArray, 0, multiShape.length);
        copyArray[multiShape.length]=addedShape;
        multiShape=copyArray;
    }
        
    public void mergeShapes()
    {
        float totalArea=0;
        for(GeometricShape2D shape: multiShape)
        {
            totalArea=totalArea+shape.calculateArea();
        }
    
        float newSquareSide =Math.round(Math.sqrt(totalArea));
        Square newSquare = new Square(newSquareSide);
        
        multiShape = new GeometricShape2D[1];
        multiShape[0] = newSquare;
    }

    public float calculateArea()
    {
        float totalArea=0;
        for(int i=0; i<multiShape.length; i++)
        {
            totalArea=totalArea+multiShape[i].calculateArea();
        }

        return(totalArea);
    }

    public void printInfo()
    {
        System.out.println("MultiShape, area " + this.calculateArea());
        for(int i=0;i<this.multiShape.length; i++)
        {
            System.out.print(Main.indent);
            if(multiShape[i].getClass().getSimpleName().equals("MultiShape2D"))
            {
                Main.indent = "     "+ Main.indent;
            }
            multiShape[i].printInfo();
        }
    }

}
