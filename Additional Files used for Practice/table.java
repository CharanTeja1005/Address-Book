import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
public class table
{
    public static void main(String[] args) {
        JFrame f = new JFrame();
        Integer r[][] = {{1,2},{1,2},{1,2},{1,2}};
        Integer c[] = {1,2};
        final JTable t = new JTable(r,c);
        t.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(t);
        // f.add(t,BorderLayout.CENTER);
        f.add(sp);
        f.setSize(300,400);
        // f.setLayout(null);
        f.setVisible(true);
        
    }
}