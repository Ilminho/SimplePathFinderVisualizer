/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pathfindingalgorithms;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author ilkkaniemelainen
 */
public class InformationPanel extends JPanel {
    JTextArea iterationTA = new JTextArea();
    JTextArea infoTA = new JTextArea();
    ArrayList<JTextArea> textAreaList = new ArrayList<>();
    
    public InformationPanel(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;  
        
        infoTA.setRows(6);
        iterationTA.setText(" \n How many tiles searched: ");
        
        textAreaList.add(infoTA);
        textAreaList.add(iterationTA);
        for(int x = 0; x<textAreaList.size();x++){
            constraints.gridx=x;
            this.add(textAreaList.get(x), constraints);
            textAreaList.get(x).setEditable(false);
            textAreaList.get(x).setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
            
        }
        
        
        
        
    }
    
    public void setIterationText(int x){
        iterationTA.setText(" \n How many tiles searched: " + x);
    }

    public void setInfoText(String x){
        infoTA.setText(x);
    }
    
    
    
    
  
    
}
