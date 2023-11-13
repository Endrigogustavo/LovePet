/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Telas;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
public class AtvPet extends JFrame{
    JLabel img;
    JButton logar;
    public AtvPet(){
    super("LovePet");
    
        
        Container tela = getContentPane();
        
        setTitle("LovePet");
        setResizable(false);
        tela.setLayout(null);
        
          
        Color button = new Color(91,194,217);  
        
        
        
        ImageIcon fun = new ImageIcon("src/Img/fun.png");
        img = new JLabel(fun);
        img.setBounds(0,0, 1253, 863);
        
        logar = new JButton("Entrar");
        logar.setBounds(540, 600, 190, 30);
        
        logar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    
                   Login log = new Login();
                   log.setVisible(true);
                   dispose();
                        }
        });

        
        tela.add(logar);
        tela.add(img);
                setSize(1253, 863);
        setVisible(true);
        setLocationRelativeTo(null);
        
}
    

    public static void main(String[] args) {
        AtvPet lov = new AtvPet();
        lov.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {

            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();

        } catch (InstantiationException e) {

            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();

        } catch (IllegalAccessException e) {

            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }

    }

}

