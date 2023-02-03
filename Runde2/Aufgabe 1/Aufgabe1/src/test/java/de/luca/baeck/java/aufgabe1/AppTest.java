package de.luca.baeck.java.aufgabe1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import de.luca.baeck.java.point.Class.Point2;
import de.luca.baeck.java.point.Interface.Point;
import de.luca.baeck.java.route.Interface.Route;
import de.luca.baeck.java.routeplanner.Class.RoutePlanner2;
import de.luca.baeck.java.routeplanner.Interface.RoutePlanner;

public class AppTest 
{
    public static void main(String[] args) throws Exception{
      List<Point> points = new ArrayList<>();
      try {
          File myObj = new File("Aufgabe1/src/test/java/de/luca/baeck/java/aufgabe1/wenigerkrumm5.txt");
          Scanner myReader = new Scanner(myObj);
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            String[] coordinatesString = data.split(" ");

            double[] coordinates = new double[coordinatesString.length];
            for(int i = 0; i < coordinatesString.length; i++) {
              coordinates[i] = Double.parseDouble(coordinatesString[i]);
            }
            Point newPoint = new Point2(coordinates);
            points.add(newPoint);
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
      }
      RoutePlanner rp = new RoutePlanner2(points);
      Route route = rp.execute();
      System.out.println(route);

      try {
        File myObj = new File("Aufgabe1/src/test/java/de/luca/baeck/java/aufgabe1/result.txt");
        if (myObj.createNewFile()) {
          System.out.println("File created: " + myObj.getName());
        } else {
          myObj.delete();
          myObj.createNewFile();
        }
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
      try {
        FileWriter myWriter = new FileWriter("Aufgabe1/src/test/java/de/luca/baeck/java/aufgabe1/result.txt");
        myWriter.write(route.toString());
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
}
