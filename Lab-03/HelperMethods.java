import java.util.Scanner;

public class HelperMethods
{
    public static void editShape()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Select a shape to edit: ");

        System.out.println("2D Shapes:");
        for (int i = 0; i < Main.twoDShapes.length; i++)
        {
            System.out.print("[" + i + "] ");
            Main.twoDShapes[i].printInfo();
            Main.indent ="        -";
        }
        System.out.println();
        System.out.println("3D Shapes:");
        for (int i = 0; i < Main.threeDShapes.length; i++)
        {
            System.out.print("[" + (Main.twoDShapes.length+i) + "] ");
            Main.threeDShapes[i].printInfo();
            Main.indent ="        -";
        }
        int shapeIndex = scan.nextInt();
        String shapeName=" ";

        if(shapeIndex>=Main.twoDShapes.length)
        {
            shapeIndex=shapeIndex-Main.twoDShapes.length;
            shapeName=Main.threeDShapes[shapeIndex].getClass().getSimpleName();
        }
        else
        {
            shapeName=Main.twoDShapes[shapeIndex].getClass().getSimpleName();
        }

        if (shapeName.equals("Square"))
        {
            System.out.print("New side length: ");
            float newSide = scan.nextFloat();
            Main.twoDShapes[shapeIndex] = new Square(newSide);
        }
        else if (shapeName.equals("Sphere"))
        {
            System.out.print("New radius length: ");
            float newRadius = scan.nextFloat();
            Main.threeDShapes[shapeIndex] = new Sphere(newRadius);
        }
        else if (shapeName.equals("Rectangle"))
        {
            System.out.print("New width length: ");
            float newWidth = scan.nextFloat();
            System.out.print("New height length: ");
            float newHeight = scan.nextFloat();
            Main.twoDShapes[shapeIndex] = new Rectangle(newWidth, newHeight);
        }
        else if (shapeName.equals("Pyramid"))
        {
            System.out.print("New base area: ");
            float newBase = scan.nextFloat();
            System.out.print("New height length: ");
            float newHeight = scan.nextFloat();
            Main.threeDShapes[shapeIndex] = new Pyramid(newBase, newHeight);
        }
        else if (shapeName.equals("EquilateralTriangle"))
        {
            System.out.print("New side length: ");
            float newSide = scan.nextFloat();
            Main.twoDShapes[shapeIndex] = new EquilateralTriangle(newSide);
        }
        else if (shapeName.equals("Ellipse"))
        {
            System.out.print("New axis1 length: ");
            float newAxis1 = scan.nextFloat();
            System.out.print("New axis2 length: ");
            float newAxis2 = scan.nextFloat();
            Main.twoDShapes[shapeIndex] = new Ellipse(newAxis1, newAxis2);
        }
        else if (shapeName.equals("Cylinder"))
        {
            System.out.print("New radius length: ");
            float newRadius = scan.nextFloat();
            System.out.print("New height length: ");
            float newHeight = scan.nextFloat();
            Main.threeDShapes[shapeIndex] = new Cylinder(newRadius, newHeight);
        }
        else if (shapeName.equals("Cuboid"))
        {
            System.out.print("New width length: ");
            float newWidth = scan.nextFloat();
            System.out.print("New depth length: ");
            float newDepth = scan.nextFloat();
            System.out.print("New height length: ");
            float newHeight = scan.nextFloat();
            Main.threeDShapes[shapeIndex] = new Cuboid(newWidth, newHeight, newDepth);
        }
        else if (shapeName.equals("Cube"))
        {
            System.out.print("New side length: ");
            float newSide = scan.nextFloat();
            Main.threeDShapes[shapeIndex] = new Cube(newSide);
        }
        else if (shapeName.equals("Circle"))
        {
            System.out.print("New radius length: ");
            float newRadius = scan.nextFloat();
            Main.twoDShapes[shapeIndex] = new Circle(newRadius);
        }
    }
    public static void shapeMaker()
    {
        System.out.println("\nShape Types: \n 1. Square \n 2. Rectangle \n 3. Circle \n 4. Cube \n 5. Cuboid \n 6. Cylinder \n 7. Ellipse \n 8. Equilateral Triangle \n 9. Pyramid \n 10. Sphere \n 11.MultiShape2D");
        System.out.print("  Answer:");
        Scanner scan = new Scanner(System.in);
        System.out.println();
        int answer= scan.nextInt();

        if(answer==1)
        {
            System.out.print("Choose side length: ");
            float side = scan.nextFloat();
            Square userSquare = new Square(side);
            System.out.println();
            Main.addShape(userSquare);
            userSquare.printInfo();
            Main.indent ="        -";
        }
        else if(answer==2)
        {
            System.out.print("Choose width length: ");
            float width = scan.nextFloat();
            System.out.print("\nChoose height length: ");
            float height = scan.nextFloat();
            Rectangle userRectangle = new Rectangle(width, height);
            System.out.println();
            Main.addShape(userRectangle);
            userRectangle.printInfo();
            Main.indent ="        -";
        }
        else if(answer==3)
        {
            System.out.print("Choose radius length: ");
            float radius = scan.nextFloat();
            Circle userCircle = new Circle(radius);
            System.out.println();
            Main.addShape(userCircle);
            userCircle.printInfo();
            Main.indent ="        -";
        }
        else if(answer==4)
        {
            System.out.print("Choose side length: ");
            float side = scan.nextFloat();
            Cube userCube = new Cube(side);
            System.out.println();
            Main.addShape(userCube);
            userCube.printInfo();
            Main.indent ="        -";
        }
        else if (answer == 5)
        {
            System.out.print("Choose width length:");
            float width = scan.nextFloat();
            System.out.print("\nChoose height length:");
            float height = scan.nextFloat();
            System.out.print("\nChoose depth length:");
            float depth = scan.nextFloat();
            Cuboid userCuboid = new Cuboid(width, height, depth);
            System.out.println();
            Main.addShape(userCuboid);
            userCuboid.printInfo();
            Main.indent ="        -";
        }
        else if(answer==6)
        {
            System.out.print("Choose radius length:");
            float radius = scan.nextFloat();
            System.out.print("Choose height length:");
            float height = scan.nextFloat();
            Cylinder userCylinder = new Cylinder(radius, height);
            Main.addShape(userCylinder);
            userCylinder.printInfo();
            Main.indent ="        -";
        }
        else if(answer==7)
        {
            System.out.print("Choose first axis length:");
            float axis1 = scan.nextFloat();
            System.out.print("Choose second axis length:");
            float axis2 = scan.nextFloat();
            Ellipse userEllipse = new Ellipse(axis1, axis2);
            Main.addShape(userEllipse);
            userEllipse.printInfo();
            Main.indent ="        -";
        }
        else if(answer==8)
        {
            System.out.print("Choose side length:");
            float side = scan.nextFloat();
            EquilateralTriangle userTriangle = new EquilateralTriangle(side);
            Main.addShape(userTriangle);
            userTriangle.printInfo();
            Main.indent ="        -";
        }
        else if(answer==9)
        {
            System.out.print("Choose base area:");
            float base = scan.nextFloat();
            System.out.print("Choose height length:");
            float height = scan.nextFloat();
            Pyramid userPyramid = new Pyramid(base, height);
            Main.addShape(userPyramid);
            userPyramid.printInfo();
            Main.indent ="        -";
        }
        else if(answer==10)
        {
            System.out.print("Choose radius length:");
            float radius = scan.nextFloat();
            Sphere userSphere = new Sphere(radius);
            Main.addShape(userSphere);
            userSphere.printInfo();
            Main.indent ="        -";
        }
        else if(answer==11)
        {
                MultiShape2D multiShape = new MultiShape2D();
                Main.addShape(multiShape);
                System.out.println("An empty multishape has been created."); 
            
        }
    } 
}
