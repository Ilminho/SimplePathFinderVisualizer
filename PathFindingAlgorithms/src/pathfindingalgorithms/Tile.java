/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pathfindingalgorithms;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author ilkkaniemelainen
 */
public class Tile {
    Random random = new Random();
    int x,y;
    boolean visited=false;
    boolean visitable=true;
    int distance=random.nextInt(4);
    boolean startingPoint=false;
    boolean endPoint=false;
    boolean route=false;
    BufferedImage tileImage = null;
    int sumDistance=Integer.MAX_VALUE;
    Tile lastTile =null;
    int hDistance;
    int fDistance;
    
    
    public Tile(int xx, int yy){
        x=xx;
        y=yy;
        

    }
    
    //Manhattan distance to the endpoint
    public void setHDistance(int xx, int yy){
        hDistance = xx-x+yy-y;
    }
    
    public int getFDistance(){
        return fDistance;
    }
    
    public void setFDistance(){
        fDistance = hDistance+sumDistance;
    }
    
    
    
    public void setLastTile(Tile t){
        lastTile=t;
    }
    
    public boolean getVisited(){
        
        return visited;
    }
    
    public void setVisited(){
        visited=true;
    }
    
    public void setUnVisited(){
        visited=false;
    }
    
    public boolean checkIfVisitable(){
        return visitable;
    }
    
    public void setSumDistanceInf(){
        sumDistance=Integer.MAX_VALUE;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setVisitable(){
        if(startingPoint||endPoint)
            return;
        visitable=!visitable;
        setTileImage();
    }
    
    public void setStartPoint(){
        startingPoint=true;
        visitable=false;
        setTileImage();
    }
    
    public void setEndPoint(){
        endPoint=true;
        setTileImage();
    }
    
    public int getDistance(){
        return distance;
    }
    
    public void setRouteBoolean(){
        route=!route;
    }
    
    public void setRouteFalse(){
        if(route)
            route=!route;
    }
    
    public void setSumDistance(int x){
        if(startingPoint){
            sumDistance=0;
            
            return;
        }
        
        if(sumDistance==Integer.MAX_VALUE)
            sumDistance=x+distance;
        else
            sumDistance+=x;
    }
    
    public void setNullLastTile(){
        lastTile=null;
    }
    
    public void setTileImage(){
        try{
        switch(distance){
            case 0:
                tileImage=ImageIO.read(new File("SimplePathFinderVisualizer/PathFindingAlgorithms/src/pathfindingalgorithms/res/earth.png"));
                break;
            case 1:
                tileImage=ImageIO.read(new File("SimplePathFinderVisualizer/PathFindingAlgorithms/src/pathfindingalgorithms/res/grass00.png"));
                break;
            case 2:
                tileImage=ImageIO.read(new File("SimplePathFinderVisualizer/PathFindingAlgorithms/src/pathfindingalgorithms/res/tree.png"));
                break;
            case 3:
                tileImage=ImageIO.read(new File("SimplePathFinderVisualizer/PathFindingAlgorithms/src/pathfindingalgorithms/res/water00.png"));
                break;
        }
        
        
        
        if(route)
            tileImage=ImageIO.read(new File("SimplePathFinderVisualizer/PathFindingAlgorithms/src/pathfindingalgorithms/res/road00.png"));
        
        if(!visitable)
            tileImage=ImageIO.read(new File("SimplePathFinderVisualizer/PathFindingAlgorithms/src/pathfindingalgorithms/res/wall.png"));
        
        if(endPoint)
            tileImage=ImageIO.read(new File("SimplePathFinderVisualizer/PathFindingAlgorithms/src/pathfindingalgorithms/res/hut.png"));
        
        if(startingPoint)
            tileImage=ImageIO.read(new File("SimplePathFinderVisualizer/PathFindingAlgorithms/src/pathfindingalgorithms/res/hut.png"));
        
        
        } catch (Exception e){
            System.out.println("Couldn't load the image");
        }
    }
    
    
}
