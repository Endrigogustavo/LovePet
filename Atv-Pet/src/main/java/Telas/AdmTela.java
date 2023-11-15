/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;

import Conexão.Conexao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.*;
import javax.swing.*;


import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class AdmTela extends JFrame { 
    Conexao con_cliente;
    JLabel rCodigo, rNome,rEspecie,rRaca,rCor,rPesquisar,rDes, img, rsexo;
    JTextField tCodigo, tNome,tEspecie,tRaca,tCor, tPesquisar,tDes, tsexo;
    JButton primeiro, anterior, proximo, ultimo, registro, gravar, alterar, excluir,pesquisar,sair;
    
    JTable tblClientes;
    JScrollPane scp_tabela;
    
     
    
    public AdmTela() throws SQLException, ParseException{

        
             ImageIcon fun = new ImageIcon("src/Img/2.png");
        img = new JLabel(fun);
        img.setBounds(0,0, 1253, 863); 
        
        Container tela = getContentPane();
        rCodigo = new JLabel("Codigo");
        rNome = new JLabel("Nome");
        rEspecie = new JLabel("Especie");
        rRaca = new JLabel("Raça");
        rCor = new JLabel("Cor");
        rPesquisar = new JLabel("Pesquisar");
        rDes = new JLabel("Nascimento");
        rsexo = new JLabel("Sexo");
        tCodigo = new JTextField();
        tNome = new JTextField();
        tEspecie= new JTextField();
        tRaca = new JTextField();
        tCor= new JTextField();
        tPesquisar= new JTextField();
        tDes= new JTextField();
        tsexo= new JTextField();



        
        con_cliente = new Conexao();
        con_cliente.conecta();
        
        setTitle("Pets");
        setResizable(false);
        tela.setLayout(null);
        
        primeiro = new JButton ("Primeiro");
        anterior = new JButton ("Anterior");
        proximo = new JButton ("Próximo");
        ultimo = new JButton ("Último");
        
        registro = new JButton ("Novo Registro");
        gravar = new JButton ("Gravar");
        alterar = new JButton ("Alterar");
        excluir = new JButton ("Excluir");
        pesquisar = new JButton ("Pesquisar");
        sair = new JButton ("Sair");
        
         sair.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
         
        primeiro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    con_cliente.resultset.first();
                    mostrar_Dados();
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Não foi possivel acessar o primeiro registro"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        anterior.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    if(con_cliente.resultset.isFirst()){
                    JOptionPane.showMessageDialog(null, "Ja esta no primeiro registro");
                    }else{
                    con_cliente.resultset.previous();
                    mostrar_Dados();
                    }
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Não foi possivel acessar o primeiro registro"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        
        
        proximo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                     if(con_cliente.resultset.isLast()){
                    JOptionPane.showMessageDialog(null, "Ja esta no ultimo registro");
                    }else{
                    con_cliente.resultset.next();
                    mostrar_Dados();
                    }
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Não foi possivel acessar o primeiro registro"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        ultimo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    con_cliente.resultset.last();
                    mostrar_Dados();
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Não foi possivel acessar o primeiro registro"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        
        registro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tCodigo.setText("");
                tCor.setText("");
                tEspecie.setText("");
                tNome.setText("");
                tRaca.setText("");
                tDes.setText("");
                tsexo.setText("");
            }
        });
        
        gravar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nome = tNome.getText();
                String dt = tCor.getText();
                String tel = tRaca.getText();
                String email = tEspecie.getText();
                String des = tDes.getText();
                String sex = tsexo.getText();
                try{
                 String insert_sql = "insert into pets(Nome,Especie,Raca,Cor,Nas,Sexo)values('"+nome+"','"+tel+"','"+email+"','"+dt+"','"+des+"','"+sex+"')";
                 con_cliente.statement.executeUpdate(insert_sql);
                 JOptionPane.showMessageDialog(null, "Gravado com sucesso");
                 
                 con_cliente.executaSQL("select * from pets order by Id_pet");
                 preencherTabela();
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Não foi possivel gravar registro"+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        alterar.addActionListener(new ActionListener(){           
            public void actionPerformed(ActionEvent e){
                String nome = tNome.getText();
                String dt = tCor.getText();
                String tel = tRaca.getText();
                String email = tEspecie.getText();
                String des = tDes.getText();
                String sex = tsexo.getText();
                String sql;
                String msg="";
               
                try {
                    if (tCodigo.getText().equals("")) {
                       sql = "insert into pets(Nome,Especie,Raca,Cor,Nas,Sexo)values('"+nome+"','"+tel+"','"+email+"','"+dt+"','"+des+"','"+sex+"')";
                       msg="Gravado com sucesso";
                    }
                    else{
                        sql = "update pets set Nome='"+nome+"',Especie='"+email+"',Raca='"+tel+"',Cor='"+dt+"',Nas='"+des+"',Sexo='"+sex+"'where Id_pet="+tCodigo.getText();
                        msg="Alterado com sucesso";
                    }
                    
                    con_cliente.statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Gravado com sucesso");
                    con_cliente.executaSQL("select * from pets order by Id_pet ");
                    preencherTabela();
                    
                } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar");
                }
            }
        });
        
       
         excluir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String sql;
                
                try {
                    int resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja excluir?");
                    if (resposta==0) {
                      sql = "delete from pets where Id_pet = " +tCodigo.getText();
                      int excluir = con_cliente.statement.executeUpdate(sql);                   
                    if(excluir==1){
                        JOptionPane.showMessageDialog(null, "Excluido com sucesso");
                        con_cliente.executaSQL("select * from pets order by Id_pet");
                        con_cliente.resultset.first();
                        preencherTabela();
                        posicionarRegistro();
                    }
                    else{
                    JOptionPane.showMessageDialog(null, "Operaçao cancelada pelo usuario");
                    }
                }
                    

             
                } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null, "Erro ao deletar");
                }
            }
        });
         
           pesquisar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    String pesquisa = "select * from pets where Nome like'"+tPesquisar.getText()+"%'";
                    con_cliente.executaSQL(pesquisa);
                    if(con_cliente.resultset.first()){
                    preencherTabela();
                    }
                    else{
                    JOptionPane.showMessageDialog(null, "\n Nao existe");
                    }
                } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null, "\n Dados nao encontrados");
                }
            }
        });
                
     
                primeiro.setBounds(60, 320,100, 30);
                tela.add(primeiro);
                anterior.setBounds(150, 320,100, 30);
                tela.add(anterior);
                proximo.setBounds(240, 320,100, 30);
                tela.add(proximo);
                ultimo.setBounds(330, 320,100, 30);
                tela.add(ultimo);
                
                
                registro.setBounds(500, 320,130, 30);
                tela.add(registro);
                gravar.setBounds(635, 320,100, 30);
                tela.add(gravar);
                alterar.setBounds(730, 320,100, 30);
                tela.add(alterar);
                excluir.setBounds(830, 320,100, 30);
                tela.add(excluir);
                
                pesquisar.setBounds(60, 355,150, 30);
                tela.add(pesquisar);
                
                sair.setBounds(750, 355,150, 30);
                tela.add(sair);
                
               
        rPesquisar.setBounds(50, 320, 200, 50);
        tPesquisar.setBounds(220, 355, 250, 30);
        

                
        
        tblClientes = new javax.swing.JTable();
        scp_tabela = new javax.swing.JScrollPane();

        tblClientes.setBounds(50,400,900,350);
        scp_tabela.setBounds(50,400,900,350);
        

        
        tela.add(tblClientes);

        tela.add(scp_tabela);

        
        
        tblClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
        
        tblClientes.setFont(new java.awt.Font("Arial",1,12)); 
        
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][]{
            {null, null,null,null,null,null},
            {null, null,null,null,null,null},
            {null, null,null,null,null,null},
            {null, null,null,null,null,null},
        },
                new String [] {"Id_pet", "Nome", "Especie", "Raca", "Cor", "Nas","Sexo"})
        {
            boolean[] canEdit = new boolean[]{
                false, false,false,false,false,false
            };
          
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return canEdit [columnIndex];}
});          
        scp_tabela.setViewportView(tblClientes); 
        tblClientes.setAutoCreateRowSorter(true);

       
        rCodigo.setBounds(50, 20, 150, 50);
        rNome.setBounds(50, 60, 150, 50);
        rEspecie.setBounds(50, 100, 150, 50);
        rRaca.setBounds(50, 140, 150, 50);
        rCor.setBounds(50, 180, 150, 50);
        rDes.setBounds(50, 220, 150, 50);
        rsexo.setBounds(450, 20, 150, 50);
        
        rCodigo.setForeground(Color.white);
        rNome.setForeground(Color.white);
        rEspecie.setForeground(Color.white);
        rRaca.setForeground(Color.white);
        rCor.setForeground(Color.white);
        rPesquisar.setForeground(Color.white);
        rDes.setForeground(Color.white);
        rsexo.setForeground(Color.white);
        
        rCodigo.setFont(new Font("Tahoma",Font.BOLD,15));
        rNome.setFont(new Font("Tahoma",Font.BOLD,15));
        rEspecie.setFont(new Font("Tahoma",Font.BOLD,15));
        rRaca.setFont(new Font("Tahoma",Font.BOLD,15));
        rCor.setFont(new Font("Tahoma",Font.BOLD,15));
        rPesquisar.setFont(new Font("Tahoma",Font.BOLD,15));
        rDes.setFont(new Font("Tahoma",Font.BOLD,15));
        rsexo.setFont(new Font("Tahoma",Font.BOLD,15));
        
        tCodigo.setBounds(130, 30, 80, 30);
        tNome.setBounds(130, 70, 220, 30);
        tEspecie.setBounds(130, 120, 200, 30);
        tRaca.setBounds(130, 160, 100, 30);
        tCor.setBounds(130, 200, 80, 30);
        tDes.setBounds(130, 240, 250, 60);
        tsexo.setBounds(530, 30, 80, 30);
        
        
        tela.add(tEspecie);
        tela.add(tCodigo);
        tela.add(tNome);
        tela.add(tRaca);
        tela.add(tCor);
        tela.add(rCodigo);
        tela.add(rNome);
        tela.add(rEspecie);
        tela.add(rRaca);
        tela.add(rCor);
        tela.add(tDes);
        tela.add(rDes);
        tela.add(tsexo);
        tela.add(rsexo);
        tela.add(tPesquisar);
                
                tela.add(img);
                setSize(1253, 863);
        setVisible(true);
        setLocationRelativeTo(null);

        
        con_cliente.executaSQL("select * from pets order by Id_pet");
                preencherTabela();
        posicionarRegistro();

 }
    
        //método posicionarRegistro
    public void posicionarRegistro() {
        try {
            con_cliente.resultset.first(); // posiciona no 1° registro da tabela
            mostrar_Dados(); // chama o método que irá buscar o dado da tabela
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível posicionar no primeiro registro: " + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

      public void mostrar_Dados(){
        try{
            tCodigo.setText(con_cliente.resultset.getString("Id_pet"));
            tNome.setText(con_cliente.resultset.getString("Nome"));
            tEspecie.setText(con_cliente.resultset.getString("Especie"));
            tRaca.setText(con_cliente.resultset.getString("Raca"));
            tCor.setText(con_cliente.resultset.getString("Cor"));
            tDes.setText(con_cliente.resultset.getString("Nas"));
            tsexo.setText(con_cliente.resultset.getString("Sexo"));
            
        }catch(SQLException erro){
             JOptionPane.showMessageDialog(null, "Não localizou dados: "+erro,"Mensagem do prograna", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    public void preencherTabela() throws SQLException
    {
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(4);
        tblClientes.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblClientes.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblClientes.getColumnModel().getColumn(3).setPreferredWidth(40);
        tblClientes.getColumnModel().getColumn(4).setPreferredWidth(20);
        tblClientes.getColumnModel().getColumn(5).setPreferredWidth(4);
 
        DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
        modelo.setNumRows(0);
        
        try{
            con_cliente.resultset.beforeFirst();
            while(con_cliente.resultset.next()){
                modelo.addRow(new Object[]{
                con_cliente.resultset.getString("Id_pet"),
                con_cliente.resultset.getString("Nome"), 
                con_cliente.resultset.getString("Especie"),
                con_cliente.resultset.getString("Raca"), 
                con_cliente.resultset.getString("Cor"),
                con_cliente.resultset.getString("Nas"), 
                con_cliente.resultset.getString("Sexo"), 
            });
            }
        }catch(SQLException erro){
    JOptionPane.showMessageDialog(null,"erro ao listar dados da tabela!! \n "+erro,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
}
        

    }
    
public static void main(String args[]) throws SQLException, ParseException{
    
try {
    
		    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
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