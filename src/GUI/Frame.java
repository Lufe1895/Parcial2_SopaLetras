/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author luisl
 */
public class Frame extends JFrame {
    
    private JPanel pnlCenter;
    private JPanel pnlButtons;
    
    private JButton [][] buttons;
    private JButton btnExit;
    private JButton btnCheck;
    private JButton btnQuit;
    
    private ArrayList<Character> answer;
    
    private String auxAnswer;
    
    private FileReader fr;
    
    private Boolean exists;
    
    private Integer number;
    private Integer rows;
    private Integer columns;
    private Integer ans;
    private Integer acerts;
    
    private String [] answers;
    
    public Frame() throws FileNotFoundException, IOException{
        
        super("Crossword");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(800, 800);
        super.setVisible(true);
        super.setLayout(new BorderLayout());
        
        acerts=0;
        
        fr = new FileReader("C:\\Users\\luisl\\OneDrive\\Escritorio\\text.txt");
        
        Scanner sc = new Scanner(fr);
        
        rows = sc.nextInt();
        columns = sc.nextInt();
        ans = sc.nextInt();
        
        System.out.println(fr);
        System.out.println(rows);
        System.out.println(columns);
        System.out.println(ans);
        
        //----------------------------------------------------------------------Valores por defecto de la ventana
        
        pnlCenter = new JPanel(new GridLayout(rows,columns));
        buttons = new JButton[rows][columns];
        
        fr = new FileReader("C:\\Users\\luisl\\OneDrive\\Escritorio\\text.txt");
        
        fr.read();
        fr.read();
        fr.read();
        fr.read();
        fr.read();
        fr.read();
        fr.read();
        
        answer = new ArrayList<>();
        
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                this.buttons[i][j] = new JButton(String.valueOf((char)(fr.read())));
                pnlCenter.add(this.buttons[i][j]);
                this.buttons[i][j].setBackground(java.awt.Color.LIGHT_GRAY);
                char a = this.buttons[i][j].getText().charAt(0);
                this.buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        answer.add(a);
                    }
                });
            }
            fr.read();
            fr.read();
        }
        
        fr = new FileReader("C:\\Users\\luisl\\OneDrive\\Escritorio\\text.txt");
        
        Scanner Sc = new Scanner(fr);
        
        for(int i=0;i<=rows;i++){
            Sc.nextLine();
        }
        
        this.answers = new String[ans];
        
        System.out.println("\n\nESTAS SON LAS RESPUESTAS--------------------------------\n");
        
        for(int i=0;i<ans;i++){
            this.answers[i]=Sc.nextLine().toString();
            System.out.println(this.answers[i]);
        }
        
        super.add(pnlCenter,BorderLayout.CENTER);
        
        //----------------------------------------------------------------------Inicialización de botones
        
        this.btnCheck = new JButton("Check!");
        btnCheck.setPreferredSize(new Dimension(100,40));
        this.btnQuit = new JButton("Give Up!");
        btnQuit.setPreferredSize(new Dimension(100,40));
        this.btnExit = new JButton("Close");
        btnExit.setPreferredSize(new Dimension(100,40));
        
        this.pnlButtons = new JPanel(new FlowLayout());
        pnlButtons.add(btnCheck);
        //pnlButtons.add(btnQuit);
        pnlButtons.add(btnExit);
        
        super.add(pnlButtons,BorderLayout.SOUTH);
        
        //----------------------------------------------------------------------Definición del comportamiento de los botones
        
        btnExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
        Integer ans1 = ans;
        btnCheck.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                auxAnswer = convertString(answer);
                exists=false;
                System.out.println(auxAnswer);
                for(int i=0;i<ans;i++){
                    if(auxAnswer.charAt(0) == answers[i].charAt(0) && auxAnswer.charAt(1) == answers[i].charAt(1) && auxAnswer.charAt(2) == answers[i].charAt(2)){
                        exists=true;
                        acerts++;
                        answers[i]="aAbBcCdD";
                        JOptionPane.showMessageDialog(null,"Well done!","CONGRATULATIONS!",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                if(!exists){
                    JOptionPane.showMessageDialog(null,"OOPS! Wrong answer.","OOPS!",JOptionPane.ERROR_MESSAGE);
                }
                answer.clear();
                if(acerts==ans1){
                    JOptionPane.showMessageDialog(null,"You've Won!","CONGRATULATIONS!",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    
    public String convertString(ArrayList<Character> answer){
        StringBuilder builder = new StringBuilder(answer.size());
        
        for(Character ch: answer){
            builder.append(ch);
        }
        
        return builder.toString();
    }
    
}
