import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;
public class a
{
    public static void main(String[] args)
    {
        JFrame jf = new JFrame();
        JButton jb = new JButton("OK");
        jb.setBounds(20,20,40,30);
        JDialog jd= new JDialog(jf,"MESSAGE",false);
        jd.setSize(200,100);
        jb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                jd.setVisible(true);
            }
        });
        JLabel jl = new JLabel("Contact Deleted");
        jd.add(jl,BorderLayout.CENTER);
        jf.add(jb);
        jf.setSize(300,300);
        jf.setLayout(null);
        jf.setVisible(true);
    }
}