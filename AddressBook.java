import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Contact implements Comparable<Contact>
{
    String Name,Address,PhoneNo,Email;
    Contact(String Name,String Address,String PhoneNo,String Email)
    {
        this.Name = Name;
        this.Address = Address;
        this.PhoneNo = PhoneNo;
        this.Email = Email;
    }

    public int compareTo(Contact c)
    {
        return this.Name.toLowerCase().compareTo(c.Name.toLowerCase());
    }

    public String toString()
    {
        return this.Name + " " + this.Address + " " + this.PhoneNo + " " + this.Email;
    }
}

public class AddressBook
{
    File file;
    CustomTree<Contact> contacts;
    
    AddressBook()
    {
        file = new File("Contacts.txt");
        contacts = new CustomTree<>();
    }

    boolean addContact(String Name,String Address,String PhoneNo,String Email)throws IOException
    {
        if(!Name.trim().isEmpty() && (!Address.isEmpty() || !PhoneNo.isEmpty() || !Email.isEmpty())) 
        {
            // if(!((Name.split("\\s"))[0].chars()).allMatch(x->Character.isLetter(x)))
            //     return false;
            if(!PhoneNo.isEmpty())  // To check if phone no is valid or not
            {
                try
                {
                    Long.parseLong(PhoneNo);
                }
                catch(NumberFormatException e){
                    return false;
                }
                if(PhoneNo.length() != 10)
                    return false;
            }

            if(!Email.isEmpty())  // To check if email is valid or not
            {
                if(!Email.contains("@") || !Email.contains(".") || Email.contains(" "))
                {
                    return false;
                }
                if(Email.split("@")[0].isEmpty())
                {
                    return false;
                }
                if(Email.split("\\.")[1].isEmpty())
                {
                    return false;
                }
            }

            contacts.add(new Contact(Name, Address, PhoneNo, Email));
            loadContacts();
            return true;
        }
        return false;
    }

    boolean deleteContact(String NameOrPhone)
    {
        Iterator<Contact> itr = contacts.iterator();
        boolean flag = false;
        while(itr.hasNext())
        {
            Contact c=(Contact)itr.next();
            if((c.Name).equals(NameOrPhone))
            {
                contacts.remove(c);
                // return true;
                flag = true;
                break;
            }
            else if(!(c.PhoneNo).isEmpty() && (c.PhoneNo).equals(NameOrPhone))
            {
                contacts.remove(c);
                // return true;
                flag = true;
                break;
            }

        }
        
        if(!flag)
            return false;

        try
        {
            loadContacts();
        }
        catch(IOException e){}

        return true;
    }

    Contact searchContact(String Name)
    {
        for(Contact c:contacts)
        {
            if((c.Name).equals(Name))
            {
                return c;
            }
        }

        for(Contact c:contacts)
        {
            String n = c.Name.split(" ")[0];
            if(Name.equals(n))
            {
                return c;
            }
        }
        
        return null;
    }

    // void viewContacts()throws IOException
    // {
    //     Iterator<Contact> itr = contacts.iterator();
    //     while(itr.hasNext())
    //     {
    //         Contact c=itr.next();
    //         System.out.println(c.Name+","+c.Address+","+c.PhoneNo+","+c.Email);
    //     }
    // }

    void loadContacts() throws IOException
    {
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        Iterator<Contact> itr = contacts.iterator();
        while(itr.hasNext())
        {
            Contact c=itr.next();
            pw.println(c.Name+","+c.Address+","+c.PhoneNo+","+c.Email);
        }
        pw.flush();
        pw.close();
        fw.close();
    }

    void loadContactsFromFile()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("Contacts.txt"));
            String contact;
            while((contact = br.readLine()) != null)
            {
                String[] contactDetails = contact.split(",");
                String Name = contactDetails[0];
                String Address = contactDetails[1];
                String PhoneNo = contactDetails[2];
                String Email = contactDetails[3];
                addContact(Name, Address, PhoneNo, Email);
            }
            br.close();
        }
        catch(Exception e){}

        try
        {
            loadContacts();
        }
        catch(IOException e){}
    }

    public static void main(String[] args) throws IOException
    {
        AddressBook ab = new AddressBook();

        ab.loadContactsFromFile();

        // ab.addContact("Charan","chch","1839","chchd@gf.con");
        // ab.addContact("Suraj","chch","1839","chchdsjdfl@ghsdl.ckl");
        // ab.addContact("Mahesh","chch","1839","chchd@dsjl.cod");
        // ab.viewContacts();
        // ab.deleteContact("Charan");
        // ab.deleteContact("hdiud");
        // ab.viewContacts();
        // System.out.println(ab.searchContact("Mahesh"));

        JFrame f = new JFrame("Address Book");
        f.setLayout(null);
        f.setSize(420,600);
        f.setVisible(true);
        
        JButton viewButton = new JButton("  View");  // View Button
        viewButton.setBounds(25,30,150,60);

        JButton addButton = new JButton("  Add");  // Add Button
        addButton.setBounds(125,140,150,60);

        JButton exitButton = new JButton("  Exit");  // ExitButton
        exitButton.setBounds(150,460,100,50);

        addButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                JFrame jf = new JFrame("Add Contact");
                jf.setLayout(null);
                jf.setSize(400,400);
                jf.setVisible(true);

                JLabel nameLabel = new JLabel("Name:");
                nameLabel.setBounds(50,25,300,25);
                JTextField nameTextField = new JTextField("");
                nameTextField.setBounds(50,50,300,25);

                JLabel addressLabel = new JLabel("Address:");
                addressLabel.setBounds(50,100,300,25);
                JTextField addressTextField = new JTextField("");
                addressTextField.setBounds(50,125,300,25);

                JLabel phoneLabel = new JLabel("Phone Number:");
                phoneLabel.setBounds(50,150,300,25);
                JTextField phoneTextField = new JTextField("");
                phoneTextField.setBounds(50,175,300,25);

                JLabel emailLabel = new JLabel("Email ID:");
                emailLabel.setBounds(50,200,300,25);
                JTextField emailTextField = new JTextField("");
                emailTextField.setBounds(50,225,300,25);

                JButton addContactButton = new JButton("  Add Contact");
                addContactButton.setBounds(100,300,200,25);

                jf.add(nameLabel);
                jf.add(nameTextField);
                jf.add(addressLabel);
                jf.add(addressTextField);
                jf.add(phoneLabel);
                jf.add(phoneTextField);
                jf.add(emailLabel);
                jf.add(emailTextField);
                jf.add(addContactButton);

                jf.setIconImage(new ImageIcon("Icons//Frame Icons//AddContactIcon.png").getImage());
                jf.getContentPane().setBackground(f.getContentPane().getBackground());

                addContactButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//AddContactButtonIcon.png").getImage()).getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

                addContactButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            boolean addStatus = ab.addContact(nameTextField.getText(),addressTextField.getText(),phoneTextField.getText(),emailTextField.getText());
                            if(!addStatus)
                            {
                                JFrame jf1 = new JFrame("Adding Contact...");
                                jf1.setSize(300,100);
                                jf1.setVisible(true);

                                JLabel deleteStatusLabel = new JLabel("Contact Not Added");
                                deleteStatusLabel.setBounds(150,50,100,25);

                                JButton closeButton = new JButton("Close");

                                jf1.add(deleteStatusLabel);
                                jf1.add(closeButton,BorderLayout.SOUTH);

                                jf1.setIconImage(new ImageIcon("Icons//Frame Icons//CrossIcon.png").getImage());
                                jf1.getContentPane().setBackground(f.getContentPane().getBackground());

                                closeButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//CloseButtonIcon.png").getImage()).getScaledInstance(23, 23, Image.SCALE_SMOOTH)));

                                closeButton.addActionListener(new ActionListener()
                                {
                                public void actionPerformed(ActionEvent e){
                                    jf1.dispose();
                                }
                                });
                            }
                            else
                            {
                                JFrame jf1 = new JFrame("Adding Contact...");
                                jf1.setSize(300,100);
                                jf1.setVisible(true);

                                JLabel deleteStatusLabel = new JLabel("Contact Added");
                                deleteStatusLabel.setBounds(150,50,100,25);

                                JButton closeButton = new JButton("Close");

                                jf1.add(deleteStatusLabel);
                                jf1.add(closeButton,BorderLayout.SOUTH);

                                jf1.setIconImage(new ImageIcon("Icons//Frame Icons//CheckIcon.png").getImage());
                                jf1.getContentPane().setBackground(f.getContentPane().getBackground());

                                closeButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//CloseButtonIcon.png").getImage()).getScaledInstance(23, 23, Image.SCALE_SMOOTH)));

                                closeButton.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent e){
                                        jf1.dispose();

                                    }
                                });
                            }
                            jf.dispose();
                        }
                        catch(IOException ioe){}
                    }
                });
            }
        });

        JButton deleteButton = new JButton("  Delete");  // Delete Button
        deleteButton.setBounds(125,250,150,60);

        // Adding ActionListener to Button
        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                JFrame jf = new JFrame("Delete Contact");
                jf.setLayout(null);
                jf.setSize(400,400);
                jf.setVisible(true);

                JRadioButton nameRadioButton = new JRadioButton("Name");
                nameRadioButton.setBounds(50,25,200,25);
                
                JRadioButton phoneRadioButton = new JRadioButton("Phone Number");
                phoneRadioButton.setBounds(50,100,200,25);

                ButtonGroup bg = new ButtonGroup();
                bg.add(nameRadioButton);
                bg.add(phoneRadioButton);
                jf.add(nameRadioButton);
                jf.add(phoneRadioButton);

                jf.setIconImage(new ImageIcon("Icons//Frame Icons//DeleteContactIcon.png").getImage());
                jf.getContentPane().setBackground(f.getContentPane().getBackground());

                JLabel nameorphoneLabel = new JLabel("Name / Phone Number : (Default Name) ");
                nameorphoneLabel.setBounds(50,175,300,25);

                jf.add(nameorphoneLabel);

                JButton nameorphoneButton = new JButton("  Select");
                nameorphoneButton.setBounds(250,100,100,25);

                nameorphoneButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//SelectButtonIcon.png").getImage()).getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

                jf.add(nameorphoneButton);

                nameorphoneButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        if(nameRadioButton.isSelected())
                        {
                            nameorphoneLabel.setText("Name :");
                        }
                        else
                        {
                            nameorphoneLabel.setText("Phone Number :");
                        }
                    }
                });

                JTextField nameorphoneField = new JTextField();
                nameorphoneField.setBounds(50,225,300,25);

                jf.add(nameorphoneField);

                JButton deleteContactButton = new JButton("  Delete Contact");
                deleteContactButton.setBounds(100,325,200,25);

                jf.add(deleteContactButton);

                deleteContactButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//DeleteButtonIcon1.png").getImage()).getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

                deleteContactButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        boolean deleteStatus = ab.deleteContact(nameorphoneField.getText());
                        if(deleteStatus)
                        {
                            JFrame jf1 = new JFrame("Deleting Contact...");
                            jf1.setSize(300,100);
                            jf1.setVisible(true);

                            JLabel deleteStatusLabel = new JLabel("                                Contact Deleted");
                            deleteStatusLabel.setBounds(150,50,100,25);

                            JButton closeButton = new JButton("Close");

                            jf1.add(deleteStatusLabel);
                            jf1.add(closeButton,BorderLayout.SOUTH);

                            jf1.setIconImage(new ImageIcon("Icons//Frame Icons//CheckIcon.png").getImage());
                            jf1.getContentPane().setBackground(f.getContentPane().getBackground());

                            closeButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//CloseButtonIcon.png").getImage()).getScaledInstance(23, 23, Image.SCALE_SMOOTH)));

                            closeButton.addActionListener(new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    jf1.dispose();
                                }
                            });
                        }
                        else
                        {
                            JFrame jf1 = new JFrame("Deleting Contact...");
                            jf1.setSize(300,100);
                            jf1.setVisible(true);

                            JLabel deleteStatusLabel = new JLabel("                            Contact Not Deleted");
                            deleteStatusLabel.setBounds(150,50,100,25);

                            JButton closeButton = new JButton("Close");

                            jf1.add(deleteStatusLabel);
                            jf1.add(closeButton,BorderLayout.SOUTH);

                            jf1.setIconImage(new ImageIcon("Icons//Frame Icons//CrossIcon.png").getImage());
                            jf1.getContentPane().setBackground(f.getContentPane().getBackground());

                            closeButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//CloseButtonIcon.png").getImage()).getScaledInstance(23, 23, Image.SCALE_SMOOTH)));

                            closeButton.addActionListener(new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    jf1.dispose();
                                }
                            });

                        }
                        jf.dispose();
                    }
                });
            }
        });

        JButton searchButton = new JButton("  Search"); // Search Button
        searchButton.setBounds(125,360,150,60);

        // Adding action listener to button
        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                // Setting up jf
                JFrame jf = new JFrame("Search Contact");
                jf.setLayout(null);
                jf.setSize(400,400);
                jf.setVisible(true);

                //Adding name field , search button and label
                JLabel nameLabel = new JLabel("Name :");
                nameLabel.setBounds(50,100,300,25);

                JTextField nameField = new JTextField();
                nameField.setBounds(50,125,300,25);

                JButton searchButton = new JButton("  Search");
                searchButton.setBounds(100,275,200,25);

                //Adding Buttons
                jf.add(nameLabel);
                jf.add(nameField);
                jf.add(searchButton);

                //Setting icons, background to frame
                jf.setIconImage(new ImageIcon("Icons//Frame Icons//SearchContactIcon.png").getImage());
                jf.getContentPane().setBackground(f.getContentPane().getBackground());

                //Setting icon to button
                searchButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//SearchButtonIcon1.png").getImage()).getScaledInstance(17, 17, Image.SCALE_SMOOTH)));

                // Adding ActionListener to Button
                searchButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        Contact contact = ab.searchContact(nameField.getText());

                        jf.dispose();
                        if(contact == null)
                        {
                            JFrame jf1=new JFrame("Contact Not Found");
                            jf1.setLayout(null);
                            jf1.setSize(400,400);
                            jf1.setVisible(true);

                            JLabel msg = new JLabel("Contact Not Found!!!");
                            msg.setBounds(100,125,200,25);

                            JButton closeButton = new JButton("Close");
                            closeButton.setBounds(150,250,100,25);

                            jf1.add(msg);
                            jf1.add(closeButton);

                            jf1.setIconImage(new ImageIcon("Icons//Frame Icons//CrossIcon.png").getImage());
                            jf1.getContentPane().setBackground(f.getContentPane().getBackground());

                            closeButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//CloseButtonIcon.png").getImage()).getScaledInstance(23, 23, Image.SCALE_SMOOTH)));

                            closeButton.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    jf1.dispose();
                                }
                            });
                        }
                        else
                        {
                            JFrame jf1=new JFrame("Contact Found");
                            jf1.setLayout(null);
                            jf1.setSize(400,400);
                            jf1.setVisible(true);

                            JLabel name1Label = new JLabel("Name                           :            " + contact.Name);
                            name1Label.setBounds(50,50,300,25);

                            JLabel address1Label = new JLabel("Address                      :            " + contact.Address);
                            address1Label.setBounds(50,125,300,25);

                            JLabel phone1Label = new JLabel("Phone Number          :            " + contact.PhoneNo);
                            phone1Label.setBounds(50,200,300,25);

                            JLabel email1Label = new JLabel("Email                            :            " + contact.Email);
                            email1Label.setBounds(50,275,300,25);

                            JButton closeButton = new JButton("Close");
                            closeButton.setBounds(100,325,200,25);

                            jf1.add(name1Label);
                            jf1.add(phone1Label);
                            jf1.add(address1Label);
                            jf1.add(email1Label);
                            jf1.add(closeButton);

                            jf1.setIconImage(new ImageIcon("Icons//Frame Icons//CheckIcon.png").getImage());
                            jf1.getContentPane().setBackground(f.getContentPane().getBackground());

                            closeButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//CloseButtonIcon.png").getImage()).getScaledInstance(23, 23, Image.SCALE_SMOOTH)));

                            closeButton.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    jf1.dispose();
                                }
                            });
                        }
                    }
                });
            }
        });


        viewButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                JFrame jf = new JFrame("All Contacts");
                jf.setSize(700,400);
                jf.setVisible(true);

                String[][] contact = new String[ab.contacts.size()][4];

                Iterator<Contact> itr = ab.contacts.iterator();

                for(int i=0;i<ab.contacts.size();i++)
                {
                    Contact c = itr.next();
                    contact[i][0] = c.Name;
                    contact[i][1] = c.Address;
                    contact[i][2] = c.PhoneNo;
                    contact[i][3] = c.Email;
                }

                String[] headings = {"Name","Address","Phone No.","Email ID"};

                JTable contactTable = new JTable(contact,headings);
                contactTable.setBounds(50,50,380,380);
                JScrollPane scrollPane = new JScrollPane(contactTable);

                jf.add(scrollPane,BorderLayout.CENTER);

                JButton closeButton = new JButton("Close");
                closeButton.setBounds(140,350,100,20);

                jf.add(closeButton,BorderLayout.SOUTH);

                jf.setIconImage(new ImageIcon("Icons//Frame Icons//ViewContactIcon.png").getImage());
                jf.getContentPane().setBackground(f.getContentPane().getBackground());

                closeButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//CloseButtonIcon.png").getImage()).getScaledInstance(23, 23, Image.SCALE_SMOOTH)));

                closeButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        jf.dispose();
                    }
                });
            }
        });

        JButton colorButton = new JButton("  Change Color");  // Color Chooser button
        colorButton.setBounds(230,30,150,60);

        // Adding ActionListener to Button
        colorButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                // Setting up JFrame
                JFrame jf = new JFrame("Choose Color");
                jf.setLayout(new FlowLayout(FlowLayout.CENTER));
                jf.setSize(200,200);
                jf.setVisible(true);

                // Creating buttons
                JButton backgroundColorButton = new JButton(" Change Background Color");
                JButton buttonsColorButton = new JButton("  Change Buttons Color");
                JButton closeButton = new JButton("Close");
                
                
                //Adding Buttons
                jf.add(backgroundColorButton);
                jf.add(buttonsColorButton);
                jf.add(closeButton,BorderLayout.SOUTH);
                // Setting icon to frame
                jf.setIconImage(new ImageIcon("Icons//Frame Icons//ChooseColorIcon.png").getImage());
                //Setting background to frame
                jf.getContentPane().setBackground(f.getContentPane().getBackground());
                
                //Setting icons to buttons
                backgroundColorButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//BackgroundColorButtonIcon.png").getImage()).getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
                buttonsColorButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//ButtonColorButtonIcon.png").getImage()).getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
                closeButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//CloseButtonIcon.png").getImage()).getScaledInstance(23, 23, Image.SCALE_SMOOTH)));

                // Adding ActionListener to Buttons
                backgroundColorButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        Color c = JColorChooser.showDialog(jf, "Choose Background Color", null);
                        f.getContentPane().setBackground(c);
                        jf.getContentPane().setBackground(f.getContentPane().getBackground());
                    }
                });

                buttonsColorButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        Color c = JColorChooser.showDialog(jf, "Choose Button Color", null);
                        viewButton.setBackground(c);
                        colorButton.setBackground(c);
                        addButton.setBackground(c);
                        deleteButton.setBackground(c);
                        searchButton.setBackground(c);
                        exitButton.setBackground(c);
                    }
                });

                closeButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        jf.dispose();
                    }
                });
            }
        });
        
        exitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                f.dispose();
            }
        });

        //Adding all buttons to Frames
        f.add(addButton);
        f.add(deleteButton);
        f.add(searchButton);
        f.add(viewButton);
        f.add(colorButton);
        f.add(exitButton);

        //Setting Icons to Frames And Buttons
        f.setIconImage(new ImageIcon("Icons//Frame Icons//AddressBookIcon.png").getImage());

        viewButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//ViewButtonIcon.png").getImage()).getScaledInstance(21, 21, Image.SCALE_SMOOTH)));
        addButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//AddButtonIcon.png").getImage()).getScaledInstance(23, 23, Image.SCALE_SMOOTH)));
        deleteButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//DeleteButtonIcon.png").getImage()).getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        searchButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//SearchButtonIcon.png").getImage()).getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        colorButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//ColorButtonIcon.png").getImage()).getScaledInstance(23, 23, Image.SCALE_SMOOTH)));
        exitButton.setIcon(new ImageIcon((new ImageIcon("Icons//Button Icons//ExitButtonIcon.png").getImage()).getScaledInstance(23, 23, Image.SCALE_SMOOTH)));

        // To Close Application on clicking X
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //This is to exit the application
    }
}