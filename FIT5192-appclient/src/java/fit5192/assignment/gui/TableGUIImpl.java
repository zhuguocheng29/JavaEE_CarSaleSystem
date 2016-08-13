/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.gui;

import fit5192.assignment.repository.entities.Car;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Think
 */
public class TableGUIImpl extends JFrame implements CarSaleSystemGUI {
    private static final String[] TABLE_COLUMNS = {"Model Name","Make","Model No.","Type","VIN","Color","Price","Preview URL"};
    
    private final JButton resultButton;
    private final JButton detailsButton;
    
    private final JPanel inputPanel;
    private final JPanel buttonPanel;
    private final JPanel bottomPanel;
    private final JPanel picturePanel;
    //add
    private final JPanel descriptionPanel;
    private final JPanel bottombottomPanel;
    
    private JLabel showpicLabel;
    //private JPanel showpicPanel;
    
    private final JLabel makeLabel;
    private final JLabel modelNameLabel;
    private final JLabel modelNoLabel;
    private final JLabel typeLabel;
    private final JLabel tipsLabel;
  //  private final JLabel descriptionLabel;
  //  private final JLabel pictureLabel;
    
    private final JTextField makeTextField;
    private final JTextField modelNameTextField;
    private final JTextField modelNoTextField;
    private final JTextField descriptionTextField;  
   
    
    private final JComboBox typecomboBox;
    
    private final JTable showTable;
    
    public TableGUIImpl (ActionListener actionListener, ListSelectionListener listSelectionListener)
    {
        // create buttons
        this.resultButton = new JButton("Result");
        this.detailsButton = new JButton("Details");
        
        //create container
        Container container = this.getContentPane();
        
        //create labels
        this.makeLabel = new JLabel("Make: ");
        this.modelNameLabel = new JLabel("Model Name: ");
        this.modelNoLabel = new JLabel("Model No.: ");
        this.typeLabel = new JLabel("Type: ");
        this.tipsLabel = new JLabel("Tips: Clicking Result will only show Make and Model and Clicking Details will show full details");
        this.tipsLabel.setHorizontalAlignment(JLabel.LEFT);
    //    this.descriptionLabel = new JLabel("Description: ");
        //this.pictureLabel = new JLabel("Picture: ");
        
        //add pic
        this.showpicLabel = new JLabel("");
        
        //create comboBox
        this.typecomboBox = new JComboBox();
        this.typecomboBox.addItem("Please choose types of car:");
        this.typecomboBox.addItem("Sedan");
        this.typecomboBox.addItem("4 wheel drive");
        this.typecomboBox.addItem("Truck");
        
        //create text fields
        this.makeTextField = new JTextField();
        this.modelNameTextField = new JTextField();
        this.modelNoTextField = new JTextField();
        this.descriptionTextField = new JTextField();
        
        
        //create table
        this.showTable = new JTable(new DefaultTableModel(TABLE_COLUMNS,0));
        this.showTable.getSelectionModel().addListSelectionListener(listSelectionListener);
        this.showTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        TableColumnModel showTableColumnModel = this.showTable.getColumnModel();
        showTableColumnModel.getColumn(0).setPreferredWidth(100);
        showTableColumnModel.getColumn(1).setPreferredWidth(100);
        showTableColumnModel.getColumn(2).setPreferredWidth(100);
        showTableColumnModel.getColumn(3).setPreferredWidth(100);
        showTableColumnModel.getColumn(4).setPreferredWidth(200);
        showTableColumnModel.getColumn(5).setPreferredWidth(100);
        showTableColumnModel.getColumn(6).setPreferredWidth(100);
        showTableColumnModel.getColumn(7).setPreferredWidth(200);
      
        
        //create panels
        this.inputPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.bottomPanel = new JPanel();
        this.picturePanel = new JPanel();
        //add
        this.descriptionPanel = new JPanel();
        this.bottombottomPanel = new JPanel();
        //this.showpicPanel = new JPanel();
        
        //set layout manager
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        this.inputPanel.setLayout(new GridLayout(4,2));
        this.buttonPanel.setLayout(new GridLayout(1,2));
        this.bottomPanel.setLayout(new GridLayout(1,2));
        //add
        this.descriptionPanel.setLayout(new BoxLayout(descriptionPanel,BoxLayout.Y_AXIS));
      //  this.picturePanel.setLayout(new BoxLayout(picturePanel,BoxLayout.Y_AXIS));
        
        //add action listeners
        this.resultButton.addActionListener(actionListener);
        this.detailsButton.addActionListener(actionListener);
                
        //add components
        this.inputPanel.add(makeLabel);
        this.inputPanel.add(makeTextField);
        this.inputPanel.add(modelNameLabel);
        this.inputPanel.add(modelNameTextField);
        this.inputPanel.add(modelNoLabel);
        this.inputPanel.add(modelNoTextField);
        this.inputPanel.add(typeLabel);
        this.inputPanel.add(typecomboBox);
        
        //add buttons to panel
        this.buttonPanel.add(this.resultButton);
        this.buttonPanel.add(this.detailsButton);
        
        //add description and thumbnail to panel
        //this.bottomPanel.add(this.descriptionTextField);
        
        //add
        this.descriptionTextField.setEditable(false);
 //       this.descriptionPanel.add(this.descriptionLabel);
        this.descriptionPanel.add(this.descriptionTextField);
        this.descriptionPanel.setBorder(BorderFactory.createTitledBorder("Description"));
        
        //add pic
       // this.picturePanel.add(this.pictureLabel);
        this.picturePanel.setBorder(BorderFactory.createTitledBorder("Picture"));
        this.picturePanel.add(this.showpicLabel);
        
  //      this.descriptionTextField.setPreferredSize(new Dimension(500,200));
  //      this.picturePanel.setPreferredSize(new Dimension(500,200));
        
   //     this.bottomPanel.add(this.descriptionLabel);
        this.bottomPanel.add(this.descriptionPanel);
        this.bottomPanel.add(this.picturePanel);
        this.bottomPanel.setPreferredSize(new Dimension(1000, 200));
        
        //add tips
        this.bottombottomPanel.add(tipsLabel);
        
        //add panels to content panel
        container.add(inputPanel);
        container.add(buttonPanel);
        JScrollPane studentTableScrollPane = new JScrollPane(this.showTable);
        studentTableScrollPane.setPreferredSize(new Dimension(1000, 200));
        
        container.add(studentTableScrollPane);
        container.add(bottomPanel);
        container.add(bottombottomPanel);
        
        // change the default behaviour of the close button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(1000, 800);       
        this.setVisible(true);
                
    }

    @Override
    public void clearTextFields() {
        this.makeTextField.setText("");
        this.modelNameTextField.setText("");
        this.modelNoTextField.setText("");
   //     this.descriptionTextField.setText("");
        //设置picture的panel
        this.typecomboBox.setSelectedItem("Please choose types of car: ");
    }
    
    @Override
    public void clearTable() {     
    int numberOfRow = this.showTable.getModel().getRowCount();
        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.showTable.getModel();
            for (int index = (numberOfRow - 1); index >= 0; index --) {
                tableModel.removeRow(index);
            }            
        }
    }
    
    @Override
    public void displayMessageInDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }


    @Override
    public String getMake() {
        String make = this.makeTextField.getText();
        if(make == null || make.isEmpty() || make.equals(""))
        {
            return null;
        }
        else
        {
            return make;
        }
    }

    @Override
    public String getModelName() {
        String modelName = this.modelNameTextField.getText();
        if(modelName == null || modelName.isEmpty() || modelName.equals(""))
        {
            return null;
        }
        else
        {
            return modelName;
        }
    }

    @Override
    public String getModelNo() {
        String modelNo = this.modelNoTextField.getText();
        if(modelNo == null || modelNo.isEmpty() || modelNo.equals(""))
        {
            return null;
        }
        else
        {
            return modelNo;
        }
    }

    @Override
    public String getModelType() {
        String modelType = this.typecomboBox.getSelectedItem().toString();
        if(modelType == null || modelType.isEmpty() || modelType.equals(""))
        {
            return null;
        }
        else
        {
            return modelType;
        }
    }
    
    
    @Override
    public JButton getResultButton() {
        return resultButton;
    }

    @Override
    public JButton getDetailButton() {
        return detailsButton;
    }
    
    
    
    
    @Override
    public void displaySimpleTable(Car car)
    {
         this.clearTable();
        ((DefaultTableModel)this.showTable.getModel()).addRow(new Object[]{car.getModelName(),car.getMake(),car.getModelNo(),"","","","",""});
    }
    
    @Override
    public void displaySimpleTable(List<Car> car)
    {
         this.clearTable();
         for (Car cars : car) {
            ((DefaultTableModel)this.showTable.getModel()).addRow(new Object[]{cars.getModelName(), cars.getMake(),cars.getModelNo(),"","","","",""});
    
         }
    }
    
    @Override
     public void displayComplexTable(Car car)
    {
         this.clearTable();
        ((DefaultTableModel)this.showTable.getModel()).addRow(new Object[]{car.getModelName(),car.getMake(),car.getModelNo(),car.getType(),car.getVin(),
            car.getColor(),car.getPrice(),car.getPreview_url()});
        
    }
    
    @Override
    public void displayComplexTable(List<Car> car)
    {
         this.clearTable();
         for(Car cars : car)
         {
            ((DefaultTableModel)this.showTable.getModel()).addRow(new Object[]{cars.getModelName(),cars.getMake(),cars.getModelNo(),cars.getType(),cars.getVin(),
            cars.getColor(),cars.getPrice(),cars.getPreview_url()});
         }

    }

    @Override
    public void displaySimpleNull() {
        this.clearTable();
    }
    
    @Override
    public boolean isCarSelected() {
        return (this.showTable.getSelectedRow() >= 0);
    }

    @Override
    public JTable getCarTable() {
        return showTable;
    }

    @Override
    public void displayDescription(Car car) {
        descriptionTextField.setText("");
        descriptionTextField.setText(car.getDescription());
        descriptionTextField.setHorizontalAlignment(JTextField.CENTER);
        descriptionTextField.setEditable(false);
       //this.descriptionLabel.setText("");
       //this.descriptionLabel.setText(car.getDescription());
       
    }
    
    @Override
    public void displayDescriptionNull() {
        descriptionTextField.setText("");
        descriptionTextField.setEditable(false);
         //this.descriptionLabel.setText("");
       
    }

    @Override
    public String getSelectedCarVIN() {
        int selectedRowIndex = this.showTable.getSelectedRow();
        String CarVIN = this.showTable.getValueAt(selectedRowIndex, 4).toString();
        System.out.println("CarVIN=======1" + CarVIN +"2222");
        if((CarVIN == null) || (CarVIN.equals("")))
            return "1";
        else
            return CarVIN;
    }
    
    @Override
    public void showPic(byte[] image)
    {
        showpicLabel.setVisible(true);
        System.out.println("1111111111111111111" +image);
        //showpicLabel.setPreferredSize(new Dimension(450,200));
//        showpicLabel = new JLabel("");
        //showpicLabel.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon imageIcon = new ImageIcon(image);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(300, 180, Image.SCALE_DEFAULT));      
        showpicLabel.setIcon(imageIcon);
        System.out.println("22222222");
        
//        showpicLabel.setSize(100, 50);
//        showpicLabel.setVisible(true);
        
    }
    
    @Override
    public void showPicNull()
    {
        showpicLabel.setVisible(false);
    }
    
    
}
