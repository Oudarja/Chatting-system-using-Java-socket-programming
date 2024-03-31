
package chatting.application;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
//for DataInputStream this package is needed
import java.io.*;

import java.net.*;


public class Server implements ActionListener
{
    JTextField text;
    JPanel a1;
    static Box vertical=Box.createVerticalBox();
    
    static JFrame f=new JFrame();
    
    
    static DataOutputStream dout;
    
     
    Server()
    {
          f.setLayout(null);
          
          JPanel p1=new JPanel();
          
          p1.setBackground(new Color(7,94,84));
          
          p1.setBounds(0,0,450,70);
          p1.setLayout(null);
          f.add(p1);
          
          //Here image scalation  has been done
          ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
          Image i2=i1.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
          ImageIcon i3=new ImageIcon(i2);
          
          JLabel back=new JLabel(i3);
          back.setBounds(5, 20, 25, 25);
          
          //In panel the image should be added
          p1.add(back);
          
          //need action in back button
          
         back.addMouseListener(new MouseAdapter(){
          
          public void mouseClicked(MouseEvent ae){
              
              System.exit(0);
          }
          });
          
          
          
          ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
          Image i5=i4.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
          ImageIcon i6=new ImageIcon(i5);
          
          JLabel profile=new JLabel(i6);
          profile.setBounds(40, 10, 50, 50);
          
          //In panel the image should be added
          p1.add(profile);
          
          
          
          
          ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
          Image i8=i7.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
          ImageIcon i9=new ImageIcon(i8);
          
          JLabel video=new JLabel(i9);
          video.setBounds(300, 20, 30, 30);
          
          //In panel the image should be added
          p1.add(video);
          
          
          
          ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
          Image i11=i10.getImage().getScaledInstance(35, 30,Image.SCALE_DEFAULT);
          ImageIcon i12=new ImageIcon(i11);
          
          JLabel phone=new JLabel(i12);
          phone.setBounds(360, 20, 35, 30);
          
          //In panel the image should be added
          p1.add(phone);
          
          
          
          
          ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
          Image i14=i13.getImage().getScaledInstance(10, 25,Image.SCALE_DEFAULT);
          ImageIcon i15=new ImageIcon(i14);
          
          JLabel threeicon=new JLabel(i15);
          threeicon.setBounds(420, 20, 10, 25);
          
          //In panel the image should be added
          p1.add(threeicon);
          
          
          
          JLabel name=new JLabel("Oudarja");
          name.setBounds(110,15,100,18);
          name.setForeground(Color.white);
          
          name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
          
          
          p1.add(name);
          
          
          
          
          
          JLabel status=new JLabel("Active now");
          status.setBounds(110,35,100,18);
          status.setForeground(Color.white);
          
          status.setFont(new Font("SAN_SERIF",Font.BOLD,18));
          
          
          p1.add(status);
          
          
          a1=new JPanel();
          a1.setBounds(5,75,425,570);
          f.add(a1);
          
          text=new JTextField();
          text.setBounds(5,655,310,40);
          text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
          f.add(text);
          
          
          JButton send=new JButton("Send");
          
          send.setBounds(320,655,123,40);
          
          send.setBackground(new Color(7,94,84));
          
          send.setForeground(Color.white);
          send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
          send.addActionListener(this);
          f.add(send);
                   
          
           f.setSize(450,700); 
          
           f.setUndecorated(true);
           
           f.setLocation(200,50);
           
           f.getContentPane().setBackground(Color.white);
           
           
           f.setVisible(true);
           
           
        
    }
    
    /*
    When an action occurs, an event object of type ActionEvent
    is created and passed to the ActionListener's actionPerformed method.
    The actionPerformed method is then invoked, allowing the ActionListener
    to respond to the action event.
    */
    
    public void actionPerformed(ActionEvent ae)
    {
        try{
       String out=text.getText();
       //System.out.print(out);
       
       //Border layout can set text top bottom left or right
       //across the border
       
       //JLabel output=new JLabel(out);
       JPanel p2=formatLabel(out);
       //p2.add(output);
       
       a1.setLayout(new BorderLayout());
       
       JPanel right=new JPanel(new BorderLayout());
       right.add(p2,BorderLayout.LINE_END);
       
       vertical.add(right);
       
       vertical.add(Box.createVerticalStrut(15));
       a1.add(vertical,BorderLayout.PAGE_START);
       dout.writeUTF(out);
       
       text.setText("");
       
       f.repaint();
       f.invalidate();
       f.validate();   
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public static JPanel formatLabel(String out)
    {
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        JLabel output=new JLabel("<html><p style=\"width:150px\">"+out+"</p></html>");
        
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(output);
        
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        JLabel time=new JLabel();
        
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        
        return panel;
    }
    
   
    public static void main(String[] args)
    {
        
        new Server();
        
       try
       {
           //made server with ServerSocket class
           ServerSocket skt=new ServerSocket(6001);
           
           //All time message receive and sending of server and client should 
           //be open
           while(true)
           {
               //If something comes from client that accept that
               Socket s= skt.accept();
               
               //for receiving message
               DataInputStream din=new DataInputStream(s.getInputStream());
               //for sending message
               dout=new DataOutputStream(s.getOutputStream());
               
               while(true)
               {
                   String msg=din.readUTF();
                   //formatLabel will add  all the necessary thing time background 
                   //color
                   JPanel panel=formatLabel(msg);
                   
                   JPanel left =new JPanel(new BorderLayout());
                   //received message should be in left side so Line start
                   left.add(panel,BorderLayout.LINE_START);
                   vertical.add(left);
                   
                   f.validate();
                   
               }
           }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
            
    }
}
