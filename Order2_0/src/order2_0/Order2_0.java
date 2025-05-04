
package order2_0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Order2_0 {

    
    // Declare and initialize the maximum number of tables
    private static final int MAX_TABLES = 10;

    JFrame f;

    double total;
    private JProgressBar bar;

    private Task task;

    private ArrayList<JProgressBar> bar_arr=new ArrayList<JProgressBar>();

    // Declare and initialize array lists for menu items
    private ArrayList<String> appetizers = new ArrayList<String>() {{
        add("Garlic bread with sauce");
        add("Onion Rings");
        add("Mozzarella Sticks");
        add("Chicken Wings");
        add("Chips and Salsa");
    }};
    private ArrayList<String> entrees = new ArrayList<String>() {{
        add("Spaghetti and Meatballs");
        add("Chicken Parmesan");
        add("Grilled Salmon");
        add("Pesto Pasta");
        add("Steak");
    }};
    private ArrayList<String> beverages = new ArrayList<String>() {{
        add("Soda");
        add("Iced Tea");
        add("Lemonade");
        add("Water");
        add("Beer");
    }};
    private ArrayList<String> desserts = new ArrayList<String>() {{
        add("Cheesecake");
        add("Apple Pie");
        add("Chocolate Cake");
        add("Ice Cream");
        add("Fruit Salad");
    }};

    // Declare and initialize variables for GUI components
    private JComboBox<String> cbAppetizers = new JComboBox<String>(appetizers.toArray(new String[0]));
    private JComboBox<String> cbEntrees = new JComboBox<String>(entrees.toArray(new String[0]));
    private JComboBox<String> cbBeverages = new JComboBox<String>(beverages.toArray(new String[0]));
    private JComboBox<String> cbDesserts = new JComboBox<String>(desserts.toArray(new String[0]));
    private JComboBox<Integer> cbTables = new JComboBox<Integer>();
    private JLabel lblTotal = new JLabel("Total: $0.00");
    private JButton btnOrder = new JButton("Order");



    public Order2_0() {
        f=new JFrame();
        // Set up the frame
        f.setTitle("Order Form");
        f.setSize(400, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout());

        // Populate the table combo box with numbers from 1 to MAX_TABLES
        for (int i = 1; i <= MAX_TABLES; i++) {
            cbTables.addItem(i);
        }

        // Add the GUI components to the frame
        f.add(new JLabel("Appetizer:"));
        f.add(cbAppetizers);
        f.add(new JLabel("Entree:"));
        f.add(cbEntrees);
        f.add(new JLabel("Beverage:"));
        f.add(cbBeverages);
        f.add(new JLabel("Dessert:"));
        f.add(cbDesserts);
        f.add(new JLabel("Table:"));
        f.add(cbTables);
        f.add(lblTotal);
        f.add(btnOrder);
        f.setVisible(true);
        for(int i=0;i<=9;i++){
            bar=new JProgressBar();
            bar.setValue(0);
            bar.setStringPainted(true);

            f.add(bar);
            f.add(new JLabel("table  "+(i+1)+" processing"));
            bar_arr.add(bar);}




        // Set up the action listener for the Order button
        btnOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Calculate the total cost of the order
                double total = calculateTotal();

                // Update the total label with the calculated total
                lblTotal.setText(String.format("Total: $%.2f", total));

                task=new Task((int) cbTables.getSelectedItem());
                task.start();





            }
        });




    }


    // Method to calculate the total cost of the order
    private double calculateTotal() {
        // Set the base cost of the order
        double total = 15.00;

        // Get the selected items from the combo boxes
        String appetizer = (String) cbAppetizers.getSelectedItem();
        String entree = (String) cbEntrees.getSelectedItem();
        String beverage = (String) cbBeverages.getSelectedItem();
        String dessert = (String) cbDesserts.getSelectedItem();

        // Add additional costs based on the selected menu items
        switch (appetizer) {
            case "Garlic bread with sauce":
                total += 5.00;
                break;
            case "Onion Rings":
                total += 6.00;
                break;
            case "Chicken Wings":
                total += 7.00;
                break;
            case "Mozzarella Sticks":
                total += 6.00;
                break;
            case "Chips and Salsa":
                total += 4.00;
                break;
        }

        switch (entree) {
            case "Spaghetti and Meatballs":
                total += 10.00;
                break;
            case "Chicken Parmesan":
                total += 12.00;
                break;
            case "Grilled Salmon":
                total += 15.00;
                break;
            case "Pesto Pasta":
                total += 11.00;
                break;
            case "Steak":
                total += 9.00;
                break;
        }

        switch (beverage) {
            case "Soda":
                total += 2.00;
                break;
            case "Iced Tea":
                total += 2.50;
                break;
            case "Lemonade":
                total += 3.00;
                break;
            case "Water":
                total += 1.50;
                break;
            case "Beer":
                total += 4.00;
                break;
        }

        switch (dessert) {
            case "Cheesecake":
                total += 4.00;
                break;
            case "Apple Pie":
                total += 5.00;
                break;
            case "Chocolate Cake":
                total += 4.50;
                break;
            case "Ice Cream":
                total += 3.00;
                break;
            case "Fruit Salad":
                total += 2.50;
                break;
        }


        // Return the calculated total
        return total;

    }

    public static void main(String[] args) {
        // Create a new instance of the OrderForm class and make it visible
        Order2_0 form = new Order2_0();



    }
    private class Task extends Thread{
        int table;

        public Task(int table){
            this.table=table-1;
        }
        public void run(){
            for (int counter=0; counter<=100;counter++){
                bar_arr.get(table).setValue(counter);
                try{
                    Thread.sleep(100);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null,"The table "+table+"order completed");
        }
    }
}
    

