/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;

import Conexão.Conexao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Admin
 */
public class Login extends JFrame {
     
        
    Conexao con_cliente;

    JLabel Login, Senha1, img;
    JTextField Nome;
    JButton Logar;
    JPasswordField Senha;
int tentativas = 3;
    public Login() {
                ImageIcon fun = new ImageIcon("src/Img/1.png");
        img = new JLabel(fun);
        img.setBounds(0,0, 1253, 863); 
        
        Container tela = getContentPane();
       

        con_cliente = new Conexao();
        con_cliente.conecta();

        setTitle("Conexao Java com Mysql");
        setResizable(false);
        tela.setLayout(null);

        Login = new JLabel("Login");
        Senha1 = new JLabel("Senha");
        Nome = new JTextField();
        Senha = new JPasswordField();
        Logar = new JButton("Logar");
        Login.setBounds(510, 320, 190, 30);
        
        Senha1.setBounds(510, 420, 190, 30);
        
        Nome.setBounds(510, 350, 250, 30);
        
        Senha.setBounds(510, 450, 250, 30);
        
        Logar.setBounds(540, 550, 190, 30);
        
        Logar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
   
                    String pesquisa2 = "select * from user where Login like '" +Nome.getText() + "' && Senha = " +Senha.getText() + "";
                    con_cliente.executaSQL(pesquisa2);
               
                
                try {
 
                        if (con_cliente.resultset.first()) {
                            
                           AdmTela adm = new AdmTela();
                           adm.setVisible(true);
                           dispose();
                        } else {
                            
                            tentativas--;
                            JOptionPane.showMessageDialog(null, "Usuário ou Senha incorreta \n" + tentativas + "  tentativas restantes.");
                            Nome.setText("");
                            Senha.setText("");
                        }
                        if (tentativas <= 0) {
                            JOptionPane.showMessageDialog(null, "Você já realizou todas tentativas, fechando o programa. ");
                            con_cliente.desconecta();
                            System.exit(0);
                        }
                    } 
        
            
                catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Insira todos os campos");
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
                } 
               

            
        });
        tela.add(Nome);
        tela.add(Senha);
        tela.add(Logar);
        tela.add(Senha1);
        tela.add(Login);

                tela.add(img);
                setSize(1253, 863);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public static void main(String args[]) {

        Login app = new Login();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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