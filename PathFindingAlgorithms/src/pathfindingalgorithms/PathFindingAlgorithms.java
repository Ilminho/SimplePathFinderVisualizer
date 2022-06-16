/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pathfindingalgorithms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;

/**
 *
 * @author ilkkaniemelainen
 */
public class PathFindingAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        PathPanel panel = new PathPanel(20,20);
        JButton runButton = new JButton("Run");
        JButton startNew = new JButton("Start new");
        JButton deletePath = new JButton("Delete path");
        InformationPanel informationPanel = new InformationPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.setInformationPanel(informationPanel);
        
        String info = "\n Click with mouse to draw wall  \n Start new initalizes new random map"
                + " \n Run executes selected pathfindignalgorithm \n Delete path deletes path from current map";
        
        informationPanel.setInfoText(info);
        
        
        
        deletePath.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                panel.deleteShortestPath();
            }
        });
        
        runButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                panel.run();
            }
        });
        startNew.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                panel.initializeNewIteration();
            }
        });
        
        
        
        frame.add(radioButtons(panel), BorderLayout.WEST);
        frame.add(informationPanel, BorderLayout.NORTH);
        
        //These adds the "Run" "Start new" and "Something else button"
        
        JPanel buttongroup = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;   
        
        ArrayList<JButton> Jbuttons = new ArrayList<>();        
        
        Jbuttons.add(runButton);
        Jbuttons.add(startNew);
        Jbuttons.add(deletePath);
        
        ButtonGroup pathButtons = new ButtonGroup();
        for(int x = 0; x< Jbuttons.size(); x++){
            constraints.gridy=x;    
            pathButtons.add(Jbuttons.get(x));
            buttongroup.add(Jbuttons.get(x), constraints);
            Jbuttons.get(x).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));

        }
 
        frame.add(buttongroup, BorderLayout.EAST);
        
        //Next stuff
        
        frame.add(panel, BorderLayout.CENTER);

        frame.pack();
        
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        
        
        // TODO code application logic here
    }
    
    static public JPanel radioButtons(PathPanel ppanel){
        
        ArrayList<JRadioButton> Jbuttons = new ArrayList<>();
        
        JPanel buttongroup = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;   
         
        JRadioButton algo1 = new JRadioButton("Ilkka's stupid greedy algorithm " );
        JRadioButton algo2 = new JRadioButton("Dijkstra's Algorithm");
        JRadioButton algo4 = new JRadioButton("Ilkka's smarter greedy algorithm");
        
        Jbuttons.add(algo1);
        Jbuttons.add(algo2);
        Jbuttons.add(algo4);
        
        ButtonGroup algorithms = new ButtonGroup();
        
        
        
        for(int x = 0; x< Jbuttons.size(); x++){
            constraints.gridy=x;    
            algorithms.add(Jbuttons.get(x));
            buttongroup.add(Jbuttons.get(x), constraints);

        }
        
        buttongroup.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
        
        algo1.setSelected(true);
        
        algo1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ppanel.setAlgorithm(0);
                ppanel.deleteShortestPath();
            }        
        });
        algo2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ppanel.setAlgorithm(1);
                ppanel.deleteShortestPath();
            }        
        });
        algo4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ppanel.setAlgorithm(3);
                ppanel.deleteShortestPath();
            }        
        });
        




        
        return buttongroup;
    }
    
    
}
