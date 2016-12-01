package ca.mcgill.ecse321.FTMS.view;

import java.awt.Color;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ca.mcgill.ecse321.FTMS.controller.FTMSController;
import ca.mcgill.ecse321.FTMS.controller.InvalidInputException;
import ca.mcgill.ecse321.FTMS.model.Equipment;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.Supply;

/**
 * This class is use to perform the view of the Inventory window
 * @author Group 11
 *
 */
public class InventoryPage extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 919724868025009115L;
	//UI elements
	private JLabel errorMessage;
	private JComboBox<String> foodList;
	private JLabel foodLabel;
	private JComboBox<String> equipmentList;
	private JLabel equipmentLabel;
	
	private JTextField foodNameTextField;
	private JLabel foodNameLabel;
	private JSpinner foodQtySpinner;
	private JLabel foodQtyLabel;
	private JButton addFoodButton;
	
	private JLabel equipmentNameLabel;
	private JTextField equipmentNameTextField;
	private JLabel equipmentQtyLabel;
	private JSpinner equipmentQtySpinner;
	private JButton addEquipmentButton;	
	//data elements
	private String error = null;
	private Integer selectedFood = -1;
	private HashMap<Integer, Supply> foodSupplies;
	private Integer selectedEquipment = -1;
	private HashMap<Integer, Equipment> equipments;

	
	/** Creates new form FTMSPage */
	public InventoryPage(){
		initComponents();
		refreshData();
	}
	
	/** This method is called from within the constructor to initialize the form */
	
	private void initComponents(){
		
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
				
		// elements for food
		foodNameTextField = new JTextField();
		foodNameLabel = new JLabel();
		foodQtyLabel = new JLabel();
		SpinnerModel food = new SpinnerNumberModel();
		foodQtySpinner = new JSpinner(food);
		
		foodList = new JComboBox<String>(new String[0]);
		foodList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb =(JComboBox<String>)evt.getSource();
				selectedFood = cb.getSelectedIndex();
			}
				});

		foodLabel = new JLabel();
		
		addFoodButton = new JButton();
		
		//elements for equipment
		equipmentNameTextField = new JTextField();
		equipmentNameLabel = new JLabel();
		SpinnerModel equip = new SpinnerNumberModel();
		equipmentQtySpinner = new JSpinner(equip);
		equipmentQtyLabel = new JLabel();
		
		equipmentList = new JComboBox<String>(new String[0]);
		equipmentList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedEquipment = cb.getSelectedIndex();
			}
		});
		equipmentLabel = new JLabel();
		addEquipmentButton = new JButton();
				
		//global settings and listeners
		setTitle("Inventory Registration");
				
		foodNameLabel.setText("New Food:");
		foodLabel.setText("Select Food:");
		foodQtyLabel.setText("Add or Remove Quantity:");
		addFoodButton.setText("Add Food Inventory");
		addFoodButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addFoodButtonActionPerformed(evt);
			}
		});
		equipmentNameLabel.setText("New Equiment:");
		equipmentLabel.setText("Select Equipment:");
		equipmentQtyLabel.setText("Add or Remove Quantity:");

		
		addEquipmentButton.setText("Add Equipment Inventory:");
		addEquipmentButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addEquipmentButtonActionPerformed(evt);
			}
		});
		
		//layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
			.addComponent(errorMessage)
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(foodNameLabel)
						.addComponent(foodLabel)
						.addComponent(foodQtyLabel))
						
				.addGroup(layout.createParallelGroup()
						.addComponent(foodNameTextField, 200, 200, 400)
						.addComponent(foodList)
						.addComponent(foodQtySpinner)
						.addComponent(addFoodButton))
				
				.addGroup(layout.createParallelGroup()
						.addComponent(equipmentNameLabel)
						.addComponent(equipmentLabel)
						.addComponent(equipmentQtyLabel))
				.addGroup(layout.createParallelGroup()
						.addComponent(equipmentNameTextField)
						.addComponent(equipmentList)
						.addComponent(equipmentQtySpinner)
						.addComponent(addEquipmentButton)))
				);
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]{addFoodButton, foodNameTextField});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]{addEquipmentButton, equipmentNameTextField});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(foodNameLabel)
						.addComponent(foodNameTextField)
						.addComponent(equipmentNameLabel)
						.addComponent(equipmentNameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(foodLabel)
						.addComponent(foodList)
						.addComponent(equipmentLabel)
						.addComponent(equipmentList))
				.addGroup(layout.createParallelGroup()
						.addComponent(foodQtyLabel)
						.addComponent(foodQtySpinner)
						.addComponent(equipmentQtyLabel)
						.addComponent(equipmentQtySpinner))
				.addGroup(layout.createParallelGroup()
						.addComponent(addFoodButton)
						.addComponent(addEquipmentButton))
				
				);
		pack();		

	}
	/**
	 * This method refreshes the view page
	 */
	private void refreshData(){
		OrderManager om = OrderManager.getInstance();
		//error
		errorMessage.setText(error);
		if(error==null|| error.length()== 0){
			
			//food list
			foodSupplies = new HashMap<Integer, Supply>();
			foodList.removeAllItems();
			Iterator<Supply> fIt = om.getFoodSupplies().iterator();
			Integer index = 0;
			while(fIt.hasNext()){
				Supply f = fIt.next();
				foodSupplies.put(index, f);
				foodList.addItem(f.getFoodName());
				index++;
			}
			selectedFood = -1;
			foodList.setSelectedIndex(selectedFood);
			
			//equipment list
			equipments = new HashMap<Integer, Equipment>();
			equipmentList.removeAllItems();
			Iterator<Equipment> eIt = om.getEquipments().iterator();
			index = 0;
			while(eIt.hasNext()){
				Equipment e = eIt.next();
				equipments.put(index, e);
				equipmentList.addItem(e.getEquipmentName());
				index++;
			}
			selectedEquipment = -1;
			equipmentList.setSelectedIndex(selectedEquipment);
			
			//Food
			foodNameTextField.setText("");
			foodQtySpinner.setValue(0);
			//Equipment
			equipmentNameTextField.setText("");
			equipmentQtySpinner.setValue(0);
		}
		pack();		

	}
	/**
	 * This method implement the actions to create a Food element when the addFoodbutton is selected
	 * @param evt
	 */
	private void addFoodButtonActionPerformed(java.awt.event.ActionEvent evt)
	{
		//call the controller
		
		FTMSController erc = new FTMSController();
		OrderManager om = OrderManager.getInstance();
		
		// Error handling on view page 
		String name;
		error="";
		int foodqty = 0, index;
		// for null entry
		if (selectedFood < 0 && foodNameTextField.getText()==null)
			error = error + "Food must be selected or entered";
		// for double entry
		if (selectedFood >0 && foodNameTextField!=null){
			error=error+"Only one Food must be selected or entered";
			foodNameTextField.setText("");
			selectedFood=-1;
		refreshData();
		}
		
		// translation of value given by the view page
		if(foodNameTextField.getText() == null)
		name= foodSupplies.get(selectedFood).toString();
		else name=foodNameTextField.getText();
		foodqty = (int) foodQtySpinner.getValue();
		
		// inputs to controller 
		if (error.length()==0){
		try{
			erc.createSupply(name, foodqty);
		}catch(InvalidInputException e){
			error = e.getMessage();
		}
		}
		
		//update visuals
		refreshData();
	}
	/**
	 * This method implement the actions to create an equipment element when the addEquipmentbutton is selected
	 * @param evt
	 */
	private void addEquipmentButtonActionPerformed(java.awt.event.ActionEvent evt){
		

		//call the controller
		FTMSController erc = new FTMSController();
		int equipmentqty= (int) equipmentQtySpinner.getValue();

		String name;
		// error handling
		error= "";
		// for null entry
		if (selectedEquipment< 0 && equipmentNameTextField.getText()==null)
		error=error+"Equipment must be selected or entered";
		// for double entry
		if (selectedEquipment== 0 && equipmentNameTextField.getText()!=null){
			error=error+"Only one Equipment must be selected or entered";
		equipmentNameTextField.setText("");
		selectedFood=-1;
			refreshData();
		}

		// translation of value given by the view page
		if(equipmentNameTextField.getText() == null)
			name= selectedEquipment.toString();
		else name=equipmentNameTextField.getText();
		if (error.length()==0){
		
		// inputs to controller
		try{
			erc.createEquipment(name,equipmentqty);
		}catch(InvalidInputException e){
			error = e.getMessage();
		}
		}
		
		//update visuals
		refreshData();	
	}
	}


