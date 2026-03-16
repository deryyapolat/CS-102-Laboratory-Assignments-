import java.util.Scanner;

public class Main
{
    static String indent ="        -";
    static GeometricShape2D[] twoDShapes = new GeometricShape2D[0];
    static GeometricShape3D[] threeDShapes = new GeometricShape3D[0];

    public static void addShape(GeometricShape2D addedShape)
    {
        GeometricShape2D[] userShapes = new GeometricShape2D[twoDShapes.length+1];
        System.arraycopy(twoDShapes,0,userShapes,0,twoDShapes.length);
        userShapes[twoDShapes.length]=addedShape;
        twoDShapes= userShapes;
    }

    public static void addShape(GeometricShape3D addedShape)
    {
        GeometricShape3D[] userShapes = new GeometricShape3D[threeDShapes.length+1];
        System.arraycopy(threeDShapes,0,userShapes,0,threeDShapes.length);
        userShapes[threeDShapes.length]=addedShape;
        threeDShapes= userShapes;
    }

    public static void removeShape(GeometricShape2D removingShape)
    {
        int pos=-1;
        for(int i=0; i<twoDShapes.length; i++)
        {
            if(twoDShapes[i]==removingShape)
            {
                pos=i;
                break;
            }
        }
        if(pos==-1)
        {
            return;
        }
        GeometricShape2D[] newArray = new GeometricShape2D[twoDShapes.length - 1];
        for (int i = 0, j = 0; i < twoDShapes.length; i++) 
        {
            if (i != pos)
            {
                newArray[j++] = twoDShapes[i];
            }
        }
        twoDShapes = newArray;
    }

    public static void listShapes()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("2D Shapes:");
        for (int i = 0; i < twoDShapes.length; i++)
        {
            System.out.print("[" + i + "] ");
            twoDShapes[i].printInfo();
            indent ="        -";
        }
        System.out.println();
        System.out.println("3D Shapes:");
        for (int i = 0; i < threeDShapes.length; i++)
        {
            System.out.print("[" + (twoDShapes.length+i) + "] ");
            threeDShapes[i].printInfo();
        }
        System.out.println("Do you want details for a specific shape?\r\n" +"Enter shape index or -1 to return:");
        int answer=scan.nextInt();
        if(answer==-1)
        {
            return;
        }
        else
        {
            if(answer<twoDShapes.length)
            {
                twoDShapes[answer].printInfo();
                indent ="        -";
                System.out.println(" -Area: " + twoDShapes[answer].calculateArea());
            }
            else
            {
                threeDShapes[answer-twoDShapes.length].printInfo();
                indent ="        -";
                System.out.println(" -Area: " + threeDShapes[answer-twoDShapes.length].calculateArea());
                System.out.println(" -Volume: " + threeDShapes[answer-twoDShapes.length].calculateVolume());
            }
        }
    }

    public static void addShapeToMultiShape()
    {
        int counter=0;
        for(int i=0; i<twoDShapes.length; i++)
        {
            if(twoDShapes[i].getClass().getSimpleName().equals("MultiShape2D"))
            {
                counter++;
            }
        }
        if(counter==0)
        {
            System.out.println("No multi shapes.");
            return;
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Select a MultiShape2D to add a shape to: ");
        for (int i = 0; i < twoDShapes.length; i++)
        {
            if (twoDShapes[i].getClass().getSimpleName().equals("MultiShape2D"))
            {
                System.out.print("[" + i + "] MultiShape2D");
                twoDShapes[i].printInfo();
                indent ="        -";
            }
        }
        int multiShapeIndex = scan.nextInt();
        System.out.println("Select a shape to add:");
        for (int i = 0; i < twoDShapes.length; i++)
        {
            if (i != multiShapeIndex)
            {
                System.out.print("[" + i + "] ");
                twoDShapes[i].printInfo();
                indent ="        -";
            }
        }
        int shapeIndex = scan.nextInt();
        ((MultiShape2D) twoDShapes[multiShapeIndex]).addMultiShape(twoDShapes[shapeIndex]);
        twoDShapes[multiShapeIndex].calculateArea();
        removeShape(twoDShapes[shapeIndex]);
        System.out.println("Shape added to MultiShapes.");
    }

    public static void mergeAllMultiShapes()
    {
        for (int i = 0; i < twoDShapes.length; i++)
        {
            if (twoDShapes[i] instanceof MultiShape2D)
            {
                ((MultiShape2D) twoDShapes[i]).mergeShapes();
            }
        }
        System.out.println("Shapes have been merged.");
    }
    

   public static void main(String[] args)
   {
        Scanner scan = new Scanner(System.in);
        boolean stay = true;
        while(stay)
        {
            System.out.print("\nMENU: \n 1. Create and Store a New Shape \n 2. Add an Existing Shape to Multi-shape \n 3. List All Shapes \n 4. Merge Multi-shapes \n 5. Edit Shape \n 6. Exit\n Answer: ");
            int answer = scan.nextInt();
            if(answer==1)
            {
                HelperMethods.shapeMaker();
            }
            else if(answer==2)
            {
                addShapeToMultiShape();
            }
            else if(answer==3)
            {
                listShapes();
            }
            else if(answer==4)
            {
                mergeAllMultiShapes();
            }
            else if(answer==5)
            {
                HelperMethods.editShape();
                System.out.println("Shape updated.");
            }
            else
            {
                stay=false;
            }
        }
   }
}
