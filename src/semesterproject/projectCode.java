
package semesterproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
/**
 *
 * @author Afaq Mansoor
 */
public class projectCode extends JFrame implements ActionListener{
    

    JTable table;
   
    int totalPrice=0;
    int total=0;
    
     int QtyInt=0;
      int PriceInt=0;
      
    Connection con;
    Statement smt;
    ResultSet rs;
    
    private static final int FRAME_WIDTH=1000;
    private static final int FRAME_HEIGHT=650;
    private static final int FRAME_X_ORIGIN=200;
    private static final int FRAME_Y_ORIGIN=50;
    
    private final JButton btn1;
    private final JButton btn2;
    private final JButton btn3;
    private final JButton btn4;
    private final JButton btn5;
    private final JButton btn6,btn7;
    
    private final JLabel label1,label2,label3,label4,label5,label6,label7,label8;
    
    private final JTextArea textArea2,textArea3,textArea4,  textArea5;
    private final JTextField textField,textField2,textField3,textField4,textField5,textField6,textField7,textField8,textField9,textField10;
    
    private final JPanel  detailPanel,innerPanel,gigaPanel,itemPanel,btnPanel,btnInnerPanel;
    private final ImageIcon img; 
    Container contentPane;
    
     public projectCode()
     {
         
        setSize(FRAME_WIDTH,FRAME_HEIGHT); 
        setTitle("ShopEasy - Smart Shopping");
        setLocation(FRAME_X_ORIGIN,FRAME_Y_ORIGIN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         
       
        contentPane=this.getContentPane();
        contentPane.setLayout(new GridLayout(1,3));
        contentPane.setBackground(Color.GRAY);
        
        img = new ImageIcon("smile.jpg");
        setIconImage(img.getImage()); 
                
        btn1=new JButton("Add");
        btn2=new JButton("Clear");
        btn3=new JButton("Add");
        btn4=new JButton("Add");
        btn5=new JButton("Update");
        btn6=new JButton("Delete");
        btn7=new JButton("Generate");
        
        label1=new JLabel("Name:  ");
        label2=new JLabel("Contact: ");
        label3=new JLabel("Address:");
        label4=new JLabel(" ITEM:");
        label5=new JLabel(" QTY: ");
        label6=new JLabel("ID:");
        label7=new JLabel("ITEM:");
        label8=new JLabel("PRICE");
        
        textArea2=new JTextArea();
        textArea2.setColumns(50);
        textArea2.setRows(40);
//        textArea2.setBorder(BorderFactory.createTitledBorder("Bill"));
        textArea2.setEditable(false);
        
        textArea4=new JTextArea();
        textArea4.setColumns(50);
        textArea4.setRows(40);
        textArea4.setBorder(BorderFactory.createTitledBorder("ITEMS"));
        textArea4.setEditable(false);
        
        textArea5=new JTextArea();
        textArea5.setColumns(50);
        textArea5.setRows(40);
        textArea5.setEditable(false);
        
        textArea3=new JTextArea("", 36, 5 );
        textArea3.setColumns(50);
        textArea3.setRows(40);
//        textArea3.setBorder(BorderFactory.createTitledBorder("Customer"));
         // Create text area to display receipt
        textArea3.setEditable( false );
        textArea3.setText( "\t\tShopEasy - Smart Shopping \n\n" +
                "\t           1-A Sector E-5 Phase-7 Hayatabad Peshawar\n\n" +
                "\t\t         -- INVOICE --\n\n" +
                       "    CUSTOMER: \n\n" +
                       "    CONTACT: \n\n"   +
                       "    ADDRESS: \n\n\n"    +
                "\t      |     ITEM Name      |       QTY      |     PRICE   |\n\n"  );
        
//        textArea4.setText(
//                       "\t    |     ITEM Name      |       QTY      |     PRICE   |\n\n"  ) ;
        textArea2.setText(               
                       "\t\t\tTOTAL: \n\n" +
                       "\t\t\tCASH: \n\n"+
                       "\t\t\tBALANCE: \n\n\n" +
                       "\t\t       -- THANK YOU! --"+
        "\n\n\t\t  Please Visit Us Again :) \n\n" 
        +"\t             Software by ; www.facebook.com/afaqmnsr");
        
        
        //User Info
        textField=new JTextField();
        textField.setColumns(25);
        textField2=new JTextField();
        textField2.setColumns(25);   
        textField3=new JTextField();
        textField3.setColumns(25);
        
        //Items Buy
        textField4=new JTextField();
        textField4.setColumns(15);
        textField5=new JTextField();
        textField5.setColumns(15);
        textField9=new JTextField();
        textField9.setColumns(15);
        textField10=new JTextField();
        textField10.setColumns(15);
        
        //UPDATE RECORD
        textField6=new JTextField();
        textField6.setColumns(3);
        textField7=new JTextField();
        textField7.setColumns(15);
        textField8=new JTextField();
        textField8.setColumns(5);
       
        
        btnPanel=new JPanel();
        btnPanel.setBorder(BorderFactory.createTitledBorder("ITEMS"));
        btnPanel.setLayout(new FlowLayout());
        btnInnerPanel=new JPanel();
        
        btnInnerPanel.setBorder(BorderFactory.createTitledBorder("Update Record"));
        btnInnerPanel.setLayout(new GridLayout(1,3));
       
        detailPanel=new JPanel();
        detailPanel.setLayout(new GridLayout(1,1));
        detailPanel.setBorder(BorderFactory.createTitledBorder(""));
       
        
        itemPanel=new JPanel();
        itemPanel.setBorder(BorderFactory.createTitledBorder("Items Entry"));
        itemPanel.setLayout(new GridLayout(5,1));
        
        gigaPanel=new JPanel();
        gigaPanel.setLayout(new GridLayout(3,1));
        gigaPanel.setBorder(BorderFactory.createTitledBorder(""));
       
        innerPanel=new JPanel();
        innerPanel.setBorder(BorderFactory.createTitledBorder("Customer"));
        innerPanel.setLayout(new GridLayout(4,1));     
        
        DoConnect();
        Vector v1= new Vector();   
        Vector v2= new Vector();
        
        try{  
           
            ResultSetMetaData md = rs.getMetaData();
            int column = md.getColumnCount();
            
        for (int i = 1; i <= column; i++) {
            v2.addElement( md.getColumnName(i) );
        }
        
        while (rs.next()) {
            Vector row = new Vector(column);
            
        for (int i = 1; i <= column; i++) {
            row.addElement( rs.getObject(i) );
        }
        
        v1.addElement( row );
        }
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }       
        
       table = new JTable(v1, v2);

        table.setForeground(Color.WHITE);
        Font f = new Font("Arial",Font.BOLD,15);
        table.setFont(f);
       table.setCellSelectionEnabled(false);
       table.setSize(30,50);
       table.setBackground(Color.LIGHT_GRAY);
       JScrollPane scrollPane2 = new JScrollPane( table );
       scrollPane2.setSize(30, 30);
       scrollPane2.setBackground(Color.LIGHT_GRAY);
       
        JPanel t1=new JPanel();
        t1.add(label6);
        t1.add(textField6);
        t1.setBackground(Color.LIGHT_GRAY);
        
        JPanel t2=new JPanel();
        t2.add(label7);
        t2.add(textField7);
        t2.setBackground(Color.LIGHT_GRAY);
        
        JPanel t3=new JPanel();
        t3.add(label8);
        t3.add(textField8);
        t3.setBackground(Color.LIGHT_GRAY);       
        
        btnInnerPanel.add(btn4);
        btnInnerPanel.add(btn5);   
        btnInnerPanel.setBackground(Color.LIGHT_GRAY);
        
        btnPanel.add(scrollPane2);
        btnPanel.add(t1);
        btnPanel.add(t2);
        btnPanel.add(t3);
        btnPanel.add(btnInnerPanel);
                   
        JPanel j1=new JPanel();
        j1.add(label1);
        j1.add(textField);
        innerPanel.add(j1);
              
        JPanel j2=new JPanel();
        j2.add(label2);
        j2.add(textField2);
        innerPanel.add(j2);
             
        JPanel j3=new JPanel();
        j3.add(label3);
        j3.add(textField3);
        innerPanel.add(j3);
        
        JPanel j4=new JPanel();
        j4.add(btn3);
        innerPanel.add(j4);
       
        //Item Adding in reciept
        JPanel i1=new JPanel();
        i1.add(label4);
        i1.add(textField4);
        
        JPanel i2=new JPanel();       
        i2.add(label5);
        i2.add(textField5);
        
        JPanel i3=new JPanel();
        JLabel price=new JLabel("PRICE:");
        i3.add(price);
        i3.add(textField9);
        
        JPanel i4=new JPanel();
        
        
        
        JPanel i5=new JPanel();
        i5.add(btn1);   
        i5.add(btn2);
        
        JPanel a1=new JPanel();
        JLabel cash=new JLabel("CASH:");
        a1.add(cash);
        a1.add(textField10);
        a1.add(btn7);
        
        itemPanel.add(i1);
        itemPanel.add(i2);
        itemPanel.add(i3);
        itemPanel.add(i4);
        itemPanel.add(i5);
        
//ITem Entry Code ends Here!!!
         
       gigaPanel.add(innerPanel);     
       gigaPanel.add(itemPanel);
      gigaPanel.add(a1);
        JPanel three=new JPanel();
        three.setBorder(BorderFactory.createTitledBorder("INVOICE"));
        three.setLayout(new GridLayout(3,1));
        three.add(textArea3);
        three.add(textArea4);
        three.add(textArea2);
        
         btnPanel.setBackground(Color.LIGHT_GRAY);
       
       contentPane.add(btnPanel);
       contentPane.add(gigaPanel);
       contentPane.add(three);
       
       btn1.addActionListener(this);
       btn2.addActionListener(this);
       btn3.addActionListener(this);
       btn4.addActionListener(this);
       btn5.addActionListener(this);
       btn7.addActionListener(this);
       
       textField.addActionListener(this);
       textField2.addActionListener(this);
       textField3.addActionListener(this);
       textField4.addActionListener(this);
       textField5.addActionListener(this);
       textField6.addActionListener(this);
       textField7.addActionListener(this);
       textField8.addActionListener(this);
       textField9.addActionListener(this);
       textField10.addActionListener(this);
    }  
          
    @Override
       public void actionPerformed(ActionEvent e)
       {   
           
           
       String name="";
       String name2="";
       String name3="";
       String Itemname="";
       String Qty="0" ;
       String Price="0";
       String Cash="0" ;
       
       Qty = textField5.getText();
       Price = textField9.getText();
       Cash = textField10.getText();
        int CashInt=0;
      
       try{
            QtyInt=Integer.parseInt(Qty);
            PriceInt=Integer.parseInt(Price); 
            CashInt=Integer.parseInt(Cash); 
       }
       catch(NumberFormatException ex){
           //JOptionPane.showMessageDialog(rootPane, "PLease Fill the Field.");
       }
       total=PriceInt*QtyInt;
       totalPrice = totalPrice+total;
       
       textField5.setText("0");
       textField9.setText("0");
       textField10.setText("0");
       
       
        if (e.getSource()==btn2 )
           {
                textField.setText(null);
                textField2.setText(null);
                textField3.setText(null);
                textField4.setText(null);
                 textField5.setText(null);
                textField9.setText(null);
                textField10.setText(null);
               
           
           }
           if (e.getSource()==btn3 )
           {
              
               name = textField.getText();
               name2 = textField2.getText();
               name3 = textField3.getText();
             
               textField.setText(null);
                textField2.setText(null);
                textField3.setText(null);
               

           
    textArea3.setText( "\t\tShopEasy - Smart Shopping \n\n" +
                "\t           1-A Sector E-5 Phase-7 Hayatabad Peshawar\n\n" +
                "\t\t         -- INVOICE --\n\n" +
                       "    CUSTOMER: "+name+"\n\n" +
                       "    CONTACT: "+name2+"\n\n"   +
                       "    ADDRESS: "+name3+"\n\n"   +
           
                       "\t    |       ITEM Name      |       QTY      |     PRICE   |"  ) ;
     }
           if (e.getSource()==btn1 )
           {
                
               Itemname = textField4.getText();
              
                textField4.setText(null);
    textArea4.append("\t    |        "+Itemname+"      |       "+QtyInt+"      |          "+PriceInt+"   |\n\n"  ) ;
    textArea2.setText(               
                       "\t\t\tTOTAL: \n\n" +
                       "\t\t\tCASH: \n\n"+
                       "\t\t\tBALANCE: \n\n\n" +
                       "\t\t       -- THANK YOU! --"+
        "\n\n\t\t  Please Visit Us Again :) \n\n" 
        +"\t             Software by ; www.facebook.com/afaqmnsr");
    
           }
           
            else if (e.getSource()==btn7)
           {
//                int CashInt=Integer.parseInt(Cash);
                int balance=CashInt-totalPrice;
                textArea2.setText(               
                       "\t\t\tTOTAL: "+totalPrice+"\n\n" +
                       "\t\t\tCASH: "+CashInt+"\n\n"+
                       "\t\t\tBALANCE: "+balance+"\n\n\n" +
                       "\t\t       -- THANK YOU! --"+
                "\n\n\t\t  Please Visit Us Again :) \n\n" 
                +"\t             Software by ; www.facebook.com/afaqmnsr");
           }
           
           else if (e.getSource()==btn4)
           {
               String id = textField6.getText();
               String nam = textField7.getText( );
               String price="0";
           price = textField8.getText( );
               
               int newID=Integer.parseInt(price);
        try {
        rs.moveToInsertRow( );
          //int id_col = rs.getInt("ID");

        rs.updateString("ID", id);
        rs.updateString("ITEM", nam);
        rs.updateInt("PRICE", newID);
        
        rs.insertRow( );
       
        JOptionPane.showMessageDialog(projectCode.this, "New Record Added");

        smt.close();  
        rs.close( );
             
        smt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        String sql = "SELECT * FROM SHOPEASY";
        rs = smt.executeQuery(sql);  
                
        rs.next( );
              
    } catch (SQLException ex) {}
           }
            
           else if (e.getSource()==btn5)
           {
                String id = textField6.getText();
                String nam = textField7.getText( );
                String price="0";
                price = textField8.getText();
                int newID=0;
                newID = Integer.parseInt(price);
    try {
                
                rs.updateString("ID", id);
                rs.updateString("ITEM", nam);
                rs.updateInt("PRICE", newID);
                rs.updateRow();
                
                smt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      String sql = "SELECT * FROM SHOPEASY";
      smt.executeUpdate(sql);
                
                JOptionPane.showMessageDialog(projectCode.this, "Record Updated!");
                 
    } 
    catch (SQLException ex) {
        JOptionPane.showMessageDialog(projectCode.this, "SQL Exception.");
    }
    
           }
       }
       
       public void DoConnect( ) {
        
        try {
             
        String host = "jdbc:derby://localhost:1527//Afaq";
        String username = "ims";
        String userpass = "ims";
        Connection con = DriverManager.getConnection(host, username, userpass);
        
        smt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE );
        String sql = "SELECT * FROM SHOPEASY";
        rs = smt.executeQuery(sql);

    }
      catch (SQLException err){
          System.out.println(err.getMessage());
      }    
    }
}
