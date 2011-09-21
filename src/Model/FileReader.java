package Model;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Point;

/**
 * class for reading txt files with cluster information
 * 
 * @author  OGO 1.2 groep 1
 *          Carl van Dueren den Hollander
 *          Ferry
 *          Nicky Advokaat
 *          Roby Visser
 *          Tim v Dalen
 */
public class FileReader {
    
    public FileReader(){
        
    }
    
    /**
     * Reads input from a file
     * -File must be in project folder
     * @pre     file must be in proper format specified in project description
     * @param   file  example: test.txt
     * @throws  FileNotFoundException 
     * @return  an Input with all info from the file 
     */
    public Input readFromFile(String file){
        ArrayList< Point > points = new ArrayList< Point >();
        Scanner scanner;
        
        try{
            scanner = new Scanner(new File(file));
        } catch(FileNotFoundException e) {
            System.out.println("file " + file + " was not found in project folder");
            return null;
        }
        
        scanner.next(); // skip "find"
        int nrOfClusters = scanner.nextInt();
        scanner.nextLine(); // skip "clusters"
        int nrOfPoints = scanner.nextInt();
        scanner.nextLine(); // skip "points"
        
        for(int i = 0; i < nrOfPoints; i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points.add( new Point(x,y));
        }
        
        return new Input( nrOfClusters, nrOfPoints, points );
    }
    
    public Input readFromSystem(){
        ArrayList< Point > points = new ArrayList< Point >();
        Scanner scanner = new Scanner(System.in);
        
        scanner.next(); // skip "find"
        int nrOfClusters = scanner.nextInt();
        scanner.nextLine(); // skip "clusters"
        int nrOfPoints = scanner.nextInt();
        scanner.nextLine(); // skip "points"
        
        for(int i = 0; i < nrOfPoints; i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points.add( new Point(x,y));
        }
        
        return new Input( nrOfClusters, nrOfPoints, points );
    }
}
