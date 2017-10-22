/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Driver;

import Board.Grid;
import Board.Node;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jack
 */


public class MazePanel{

    /**
     * @param args the command line arguments
     */
    
    static int size;
    static ArrayList<JPanel> panelArr;
    static Grid grid;
    static JPanel panel;
    static JTextField sizeField;
    static JFrame frame;
    static JPanel background;
    static JPanel options;
    static JButton findPathButton;
    static JButton resetButton;
    
    public static void createGUI(int n){
        
        size = n;
        grid = new Grid(size);
        grid.buildMaze();
        
        
        
       
        frame = new JFrame("A Fun Maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        background = new JPanel();
        background.setPreferredSize(new Dimension(1200,1000));
        
        panel = new JPanel();
        panel.setBackground(java.awt.Color.BLUE);
        panel.setPreferredSize(new Dimension((int)(1000),(int)(1000)));
        
        createArray();
        
        
        
        options = new JPanel();
        options.setPreferredSize(new Dimension(100,1000));
        options.setBackground(Color.GREEN);
        options.setLayout(new GridLayout(40,1));
        
        findPathButton = new JButton("Find Path");
        resetButton = new JButton("Reset");
        
        sizeField = new JTextField(size);
        sizeField.setPreferredSize(new Dimension(10,20));
        sizeField.setText(Integer.toString(n));
        sizeField.setEditable(false);
        
        
        findPathButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                findPathButtonMouseClicked(evt);
            }
        });
        resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetButtonMouseClicked(evt);
            }
        });
        
        
        
        frame.add(background);
        background.add(panel);
        background.add(options);
        options.add(findPathButton);
        options.add(resetButton);
        options.add(sizeField);
        
        
        drawMaze();
       
        frame.pack();
        frame.setVisible(true);
        
    }
    
    private static void createArray(){
        
        
        
        panel.setLayout(new GridLayout(size,size));
        panelArr = new ArrayList();
        for(int i=0;i<size*size;i++){
            panelArr.add(new JPanel());
            
            panel.add(panelArr.get(i));
        }
    }
    
    public static void drawMaze(){
        
        
        
        int index = 0;
        for(int x=0;x<size;x++){
            for(int y=0;y<size;y++){
                if(grid.get(x, y).getType()==Node.Type.WALL){
                    panelArr.get(index).setBackground(Color.BLACK);
                }
                if(grid.get(x, y).getType()==Node.Type.START){
                    panelArr.get(index).setBackground(Color.GREEN);
                }
                if(grid.get(x, y).getType()==Node.Type.END){
                    panelArr.get(index).setBackground(Color.RED);
                }
                if(grid.get(x, y).getType()==Node.Type.PATH){
                    panelArr.get(index).setBackground(Color.RED);
                }
                if(grid.get(x, y).getType()==Node.Type.FLOOR){
                    panelArr.get(index).setBackground(Color.WHITE);
                }
                index++;
            }
        }
    }
    
    private static void resetButtonMouseClicked(MouseEvent evt){
        
        grid.reset();
        grid.buildMaze();
        drawMaze();
        
        
       
        
    }
    private static void findPathButtonMouseClicked(MouseEvent evt){
        grid.findPath();
        drawMaze();
        
    }
    
    
    
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI(101);
            }
        });
    }

    
    
}
