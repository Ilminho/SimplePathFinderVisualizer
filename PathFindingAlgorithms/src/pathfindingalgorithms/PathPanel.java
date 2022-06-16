/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pathfindingalgorithms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author ilkkaniemelainen
 */
public class PathPanel extends JPanel implements Runnable {
    
    ArrayList<Tile> tiles = new ArrayList<>();
    ArrayList<Tile> shortestPath = new ArrayList<>();
    ArrayList<Tile> shortestPathPaint = new ArrayList<>();
    public int sizeX;
    public int sizeY;
    final int boxSize=30;
    final int startingPointX=0;
    final int startingPointY=0;
    int endPointX;
    int endPointY;
    boolean mousePressed = false;
    Tile currentSetTile=null;
    public int whichAlgorithm=0;
    CheckNearestBlock CNB = new CheckNearestBlock(this, tiles);
    InformationPanel iPanel;
    
    
    public PathPanel(int xx, int yy){
        sizeX=xx;
        sizeY=yy;
        endPointX=sizeX-7;
        endPointY=sizeY-7;
        this.setPreferredSize(new Dimension(sizeX*boxSize, sizeY*boxSize));
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                try{
                if(!(mousePressed))
                    return;
                int xxx = e.getX();
                int yyy = e.getY();
                int setTile=(xxx/boxSize)*sizeX+yyy/boxSize;   
                if(tiles.get(setTile).startingPoint||tiles.get(setTile).endPoint)
                    return;
                if(tiles.get(setTile)!=currentSetTile){
                    currentSetTile=tiles.get(setTile);
                    currentSetTile.setVisitable();
                }
                
                repaint();
                } catch (Exception ee){
                    System.out.println("Something went wrong with mouseDragged");
                }
                    
                
            }
        
        });
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressed=true;
                int xxx=e.getX();
                int yyy=e.getY();
                int setTile=(xxx/boxSize)*sizeX+yyy/boxSize;
                if(tiles.get(setTile).startingPoint||tiles.get(setTile).endPoint)
                    return;
                tiles.get(setTile).setVisitable();
                currentSetTile=tiles.get(setTile);
                repaint();
                
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                mousePressed=false;
            }
        });
        initializeMap();




        
    }
    
    public void setInformationPanel(InformationPanel iipanel){
        iPanel=iipanel;
    }
    
    void initializeMap(){
        for(int x=0; x<sizeX; x++){
            for(int y=0; y<sizeY; y++){
                tiles.add(new Tile(x,y));
            }
        }
        
        for(var x:tiles)
            x.setTileImage();
        
        tiles.get((startingPointX)*sizeX+startingPointY).setStartPoint();
        tiles.get((endPointX)*sizeX+endPointY).setEndPoint();
  
    }
    public void initializeNewIteration(){
        tiles=new ArrayList<Tile>();
        shortestPath=new ArrayList<Tile>();
        shortestPathPaint=new ArrayList<Tile>();
        for(int x=0; x<sizeX; x++){
            for(int y=0; y<sizeY; y++){
                tiles.add(new Tile(x,y));
            }
        }
        if(iPanel!=null)
            iPanel.setIterationText(0);
        
        CNB = new CheckNearestBlock(this, tiles);
        CNB.nullifyIterations();
        
        
        tiles.get((startingPointX)*sizeX+startingPointY).setStartPoint();
        tiles.get((endPointX)*sizeX+endPointY).setEndPoint();
        for(var x: tiles){      
            x.setTileImage();
        }   
        repaint();
  
    }
    
    public void deleteShortestPath(){
        shortestPath=new ArrayList<Tile>();
        shortestPathPaint=new ArrayList<Tile>();
        for(var x: tiles){
            x.setRouteFalse();            
            x.setUnVisited();            
            x.setTileImage();
            x.setNullLastTile();
            x.setSumDistanceInf();

        }        
        if(iPanel!=null)
            iPanel.setIterationText(0);
        CNB.nullifyIterations();
        
        
        repaint();
    }
    
    public void setAlgorithm(int x){
        whichAlgorithm=x;
        System.out.println("Selected algorithm is: " +x);
    }
    
    
    
    @Override
    public  void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawTiles(g2);
    }
    
    void drawTiles(Graphics2D g){
        
        
        for(var x: tiles){
                g.drawImage(x.tileImage, x.getX()*boxSize, x.getY()*boxSize, boxSize, boxSize, this);  
                
        }
        
/*
        for(var x: tiles){
        g.setColor(Color.BLACK);            
            if(x.startingPoint){
                g.setColor(Color.GREEN);
                g.fillRect(x.getX()*boxSize, x.getY()*boxSize, boxSize, boxSize);            
                continue;
            }
            if(x.endPoint){
                g.setColor(Color.BLUE);
                g.fillRect(x.getX()*boxSize, x.getY()*boxSize, boxSize, boxSize);            
                continue;  
            }
            
            if(x.visited){
                
                
                g.setColor(Color.PINK);
                g.fillRect(x.getX()*boxSize, x.getY()*boxSize, boxSize, boxSize);
                continue;
                
                
            }
            if(!(x.visitable)){
                g.setColor(Color.red);
                g.fillRect(x.getX()*boxSize, x.getY()*boxSize, boxSize, boxSize);
                
            } else {
                g.drawRect(x.getX()*boxSize, x.getY()*boxSize, boxSize, boxSize);
                g.setColor(new Color(255,255,0,x.getDistance()));
                
                g.fillRect(x.getX()*boxSize, x.getY()*boxSize, boxSize, boxSize);
            }
            

        
   
            

            }
        
            for(var x: shortestPathPaint){
                g.setColor(new Color(0,0,0,255));
                g.fillRect(x.getX()*boxSize, x.getY()*boxSize, boxSize, boxSize);   
            }              
*/
        
    }

    
    
    

    @Override
    public void run() {
        switch(whichAlgorithm){
            case 0:
                CNB.greedyShortestPath(tiles,tiles.get((startingPointX)*sizeX+startingPointY),tiles.get((endPointX)*sizeX+endPointY));
                break;
            case 1:
                CNB.dijkstraAlgorithm();
                break;
            case 2:
                System.out.println("There might be possibility for another algroithm, but I was just too lazy");
                break;
            case 3:
                CNB.recursiveGreedyAlgorithm(tiles.get((startingPointX)*sizeX+startingPointY));
                break;
                
        }
        
        if(iPanel!=null)
            iPanel.setIterationText(CNB.iterations);

                              

                    
        for(var x:tiles)
            x.setTileImage();
        repaint();
                    



        System.out.println("Run stopped");
    }
    
    public int iterations(){
        return CNB.iterations;
    }
    
    
    
    
    
    
    
    
}
