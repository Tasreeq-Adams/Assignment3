/*
*
*@Auther: Moegammad Tasreeq Adams Student Number: 216173027
* APD Assignment3
*/
package za.ac.cput.assignment3;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadValuesSer 
{

    private ObjectInputStream input;
    ArrayList<Customer> customer = new ArrayList<>();
    ArrayList<Supplier> supplier = new ArrayList<>();

    //Opens the ser File
    public void openfile() 
    {
        try 
        {
            input = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
            System.out.println("File open for reading");
        }
        catch (FileNotFoundException ex) 
        {
            System.out.println(ex.getMessage());
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());

        }
    }

    //Closes the ser File
    public void closefile() 
    {
        try 
        {
            input.close();
        }
        catch (IOException ex) 
        {
            System.out.println("error closing ser file: " + ex.getMessage());
            System.exit(1);
        }
    }

    //Adds objects to ArrayLists
    public void readFromFile() 
    {
        try 
        {
            Object obj;
            obj = null;
            while (!(obj = input.readObject()).equals(null)) 
            {
                if (obj instanceof Customer) 
                {
                    customer.add((Customer) obj);
                    System.out.println("Adding To Customer:" + ((Customer) obj).getFirstName());
                }
                if (obj instanceof Supplier) 
                {
                    supplier.add((Supplier) obj);
                    System.out.println("Adding supplier to Supllier list" + ((Supplier) obj).getName());
                }
            }
        } 
        
        catch (IOException ioe) 
        {
            System.out.println("File not read");
            System.out.println(ioe.getMessage());
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(ReadValuesSer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //writing to text
    public void sortCustomer() 
    {
        Collections.sort(customer, (c1, c2) -> {
            return c1.getStHolderId().compareTo(c2.getStHolderId());
        });
    }

    public String formatDate(Customer c) 
    {
        DateFormat dateF = new SimpleDateFormat("dd MMM yyyy");
        try 
        {
            Date dob = new SimpleDateFormat("dd-MM-yyyy").parse(c.getDateOfBirth());
            return dateF.format(dob);

        } 
        catch (ParseException ex) 
        {
            Logger.getLogger(ReadValuesSer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Prints the formated data to the text file
    public void writeToFileCus() 
    {
        try 
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter("customerOutFile.txt"));
            String str = "=====================CUSTOMERS======================";
            bw.write(str);
            bw.newLine();
            str = "ID\t Name\t Surname\t Date of Birth\t  Age";
            bw.write(str);
            bw.newLine();
            str = "=======================================================";
            bw.write(str);
            bw.newLine();
            for (int j = 0; j < customer.size(); j++) 
            {
                str = customer.get(j).getStHolderId() + "\t" + customer.get(j).getFirstName() + " \t" + customer.get(j).getSurName() + "\t" + customer.get(j).getDateOfBirth();
                bw.write(str);
                bw.newLine();
            }
            bw.close();
        } 
        catch (IOException ioe) 
        {
            System.out.println(ioe.getMessage());
        }

    }

   //Prints the formated data to the text file
    public void sortSupplier() 
    {
        Collections.sort(supplier, (s1, s2) -> {
            return s1.getName().compareTo(s2.getName());
        });
    }

    public void writeToFileSup() 
    {
        try 
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter("SupplierOutFile.txt"));
            String str = "================SUPPLIERS=================";
            bw.write(str);
            bw.newLine();
            str = "ID\t Name\t Prod Type\t Description";
            bw.write(str);
            bw.newLine();
            str = "=============================================";
            bw.write(str);
            bw.newLine();
            for (int i = 0; i < supplier.size(); i++) 
            {
                str = supplier.get(i).getStHolderId() + "\t " + supplier.get(i).getName() + "\t" + supplier.get(i).getProductType() + "\t" + supplier.get(i).getProductDescription();
                bw.write(str);
                bw.newLine();
            }
            bw.close();
        } 
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    
    //The main section in order to execute
    public static void main(String[] args) {
        ReadValuesSer obj = new ReadValuesSer();
        obj.openfile();
        obj.readFromFile();
        obj.sortCustomer();
        obj.sortSupplier();
        obj.writeToFileCus();
        obj.writeToFileSup();
    }
    
}
