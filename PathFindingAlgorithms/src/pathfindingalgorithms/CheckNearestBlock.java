/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pathfindingalgorithms;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author ilkkaniemelainen
 */
public class CheckNearestBlock {
    
    ArrayList<Tile> tiles;
    PathPanel panel;
    public int iterations=0;
    boolean foundPath = false;
    
    public CheckNearestBlock(PathPanel i, ArrayList<Tile> tileList){
        panel=i;
        tiles=tileList;
    }
    
    public Tile nearestBlock(ArrayList<Tile> tileList){
        
        int distance = Integer.MAX_VALUE;
        Tile returnTile =null;
        for(var x: tileList){
            iterations++;
                if(x.endPoint){
                    returnTile=x;
                    break;
                }
                if(x.visitable)
                    if(!(x.visited))
                        if(x.getDistance()<distance){
                            distance=x.getDistance();
                            returnTile=x;
                        }
        }
        try{
            returnTile.setVisited();

        
        } catch (Exception e) {
            System.out.println("Algorithm went to a corner");
        }
        return returnTile;
    }
    public Tile nearestBlockHDistance(ArrayList<Tile> tileList){
        
        int distance = Integer.MAX_VALUE;
        Tile returnTile =null;
        for(var x: tileList){
            iterations++;
            /*
                if(x.endPoint){
                    returnTile=x;
                    break;
                }*/
                if(x.visitable)
                    if(!(x.visited))
                        if(x.getFDistance()<distance){
                            distance=x.getFDistance();
                            returnTile=x;
                        }
        }
        try{
            returnTile.setVisited();

        
        } catch (Exception e) {
            System.out.println("Algorithm went to a corner");
        }
        return returnTile;
    }
    
    public ArrayList<Tile> returnClosestTiles(Tile tile){
        
        Tile current=tile;
        ArrayList<Tile> closestTiles = new ArrayList<Tile>();
 
        if(tile.getX()-1>-1)
            closestTiles.add(tiles.get((tile.getX()-1)*panel.sizeX+tile.getY()));
        if(tile.getY()-1>-1)
            closestTiles.add(tiles.get((tile.getX())*panel.sizeX+tile.getY()-1));
        
        
        if(tile.getX()+1<panel.sizeX)
            closestTiles.add(tiles.get((tile.x+1)*panel.sizeX+tile.getY()));
        if(tile.getY()+1<panel.sizeY)
            closestTiles.add(tiles.get((tile.x)*panel.sizeX+tile.getY()+1));
        
        return closestTiles;    
            
    }

    /**
     *
     * @param tile
     * @return
     */
    
    public LinkedList<Tile> returnClosestTilesLinkedList(Tile tile){
        
        Tile current=tile;
        LinkedList<Tile> closestTiles = new LinkedList<Tile>();
 
        if(tile.getX()-1>-1)
            closestTiles.add(tiles.get((tile.getX()-1)*panel.sizeX+tile.getY()));
        if(tile.getY()-1>-1)
            closestTiles.add(tiles.get((tile.getX())*panel.sizeX+tile.getY()-1));
        
        
        if(tile.getX()+1<panel.sizeX)
            closestTiles.add(tiles.get((tile.x+1)*panel.sizeX+tile.getY()));
        if(tile.getY()+1<panel.sizeY)
            closestTiles.add(tiles.get((tile.x)*panel.sizeX+tile.getY()+1));
        
        return closestTiles;    
            
    }

    
    //Dumb af algorithm, prolly get stuck
    
    public void greedyShortestPath(ArrayList<Tile> tiles, Tile startPoint, Tile endPoint){
        Tile current=startPoint;
        if(startPoint==null){
            System.out.println("Returning null");
            return;
        }

        while(true){

            try{
            current = nearestBlock(returnClosestTiles(current));
            if(current==null){
                break;
            }     
            } catch (Exception e){
                System.out.println("Something went wrong...aborting");
                break;
            }
            
            current.setRouteBoolean();

            if(current.equals(endPoint))
                break;
        }

    }

    //Guarantee to find a path to endpoint
    
    
    boolean recursiveGreedyAlgorithm(Tile t){
        
        if(t==null){
            return false;
        }
        
        if(t.endPoint){
            return true;
        }
        
        ArrayList<Tile> closest = returnClosestTiles(t);
        
        if(closest.size()<1)
            return false;
        
        if(closest!=null){
            closest = sortArray(closest);

            for(var x: closest){
                if(recursiveGreedyAlgorithm(x)){
                    t.setRouteBoolean();
                    t.setVisited();
                    return true;
                }
            }
        }
        
        t.setVisited();
        return false;
    }
    
    public ArrayList sortArray(ArrayList<Tile> sortingList){

               
        ArrayList<Tile> sortedList = new ArrayList<>();
        ArrayList<Tile> helpList = new ArrayList<>();
        
        for(var x: sortingList){
            helpList.add(x);
        }
        
       int sortingSize=sortingList.size();
        
       Tile removableTile = null;
       
       for(int x = 0; x<sortingSize; x++){
           removableTile=nearestBlock(sortingList);
           sortedList.add(removableTile);
           sortingList.remove(removableTile);     
       }
       




        
        
        return sortedList;
    }
    /*
    public void aStarAlgorithm(){

        
        
        LinkedList<Tile> nodes = new LinkedList<>();
        
        for(var x: tiles){
            nodes.add(x);
            x.setHDistance(panel.endPointX, panel.endPointY);
        }
        
        
        Tile current=nodes.get(panel.startingPointX*panel.sizeX+panel.startingPointY);
        nodes.remove(panel.startingPointX*panel.sizeX+panel.startingPointY);
        current.setVisited();
        current.setStartPoint();
        current.setSumDistance(0);
        current.setFDistance();
        current.setHDistance(panel.endPointX, panel.endPointY);        
                
        while(true){          
            if(current.endPoint){
                break;
            }
            for(var x: returnClosestTilesLinkedList(current)){
                if(x.visited)
                    continue;
                if(!x.visitable){
                    continue;
                }
                if(x.sumDistance>current.sumDistance){
                    x.setSumDistance(current.sumDistance);
                    x.setFDistance();
                    x.setLastTile(current);
                    nodes.remove(x);
                    addToLinkedList(nodes,x);
                }
                  
            }
            
            
            current=nodes.poll();
            if(current==null)
                break;
            current.setVisited();
        }

        for(var x: tiles){
            if(x.endPoint){
                current=x;
                break;
            }
                
            
            
            
        } 
        while(current.lastTile!=null){
            current.setRouteBoolean();
            current=current.lastTile;
        }
        
        
    }

*/
    
    public void dijkstraAlgorithm(){
        
        
        LinkedList<Tile> nodes = new LinkedList<>();
        
        for(var x: tiles)
            nodes.add(x);
        
        Tile current=nodes.get(panel.startingPointX*panel.sizeX+panel.startingPointY);
        nodes.remove(panel.startingPointX*panel.sizeX+panel.startingPointY);
        current.setVisited();
        current.setStartPoint();
        current.setSumDistance(0);
        
        int i = 0;
        

        
        while(true){          
            if(current.endPoint){
                break;
            }
            for(var x: returnClosestTilesLinkedList(current)){

                if(x.visited)
                    continue;
                if(!x.visitable){
                    continue;
                }
                if(x.sumDistance>current.sumDistance){
                    x.setSumDistance(current.sumDistance);
                    x.setLastTile(current);
                    nodes.remove(x);
                    addToLinkedList(nodes,x);
                }
                
                
            }
            
            
            current=nodes.poll();
            if(current==null)
                break;
            current.setVisited();
        }

        for(var x: tiles){
            if(x.endPoint){
                current=x;
                break;
            }
                
        }    

        while(current.lastTile!=null){
            current.setRouteBoolean();
            current=current.lastTile;
        }

        
    }
    

    
    public void addToLinkedList(LinkedList<Tile> list, Tile t){
        if(t==null)
            return;
        if(list.isEmpty()){
            list.add(t);
            return;
        }
        
        System.out.println("Which algorithm " + panel.whichAlgorithm);
        
        if(panel.whichAlgorithm==1){        
            for(int x = 0; x<list.size();x++){
                iterations++;
                if(list.get(x).sumDistance>t.sumDistance){
                    list.add(x, t);
                    return;
                }
            }
        } else if(panel.whichAlgorithm==2) {
            iterations++;
            for(int x = 0; x<list.size();x++){
                if(list.get(x).fDistance>t.fDistance){
                    list.add(x, t);
                    return;
                }
            }            
            
            
        }
   
    }
    
    public void nullifyIterations(){
        iterations=0;
    }
    
}
