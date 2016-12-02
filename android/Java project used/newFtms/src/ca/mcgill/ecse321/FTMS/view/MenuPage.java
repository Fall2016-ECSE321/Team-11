package ca.mcgill.ecse321.FTMS.view;

import java.awt.Color;

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

import ca.mcgill.ecse321.FTMS.controller.FTMSController;
import ca.mcgill.ecse321.FTMS.controller.InvalidInputException;
import ca.mcgill.ecse321.FTMS.model.Menu;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.Supply;

public class MenuPage extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 919724868025009115L;
	//UI elements
	private JLabel errorMessage;
	private JComboBox<String> menuList;
	private JLabel menuLabel;
	private JComboBox<String> ingredientList;
	private JLabel ingredientLabel;
	
	private JTextField menuNameTextField;
	private JLabel menuNameLabel;
	private JSpinner ingredientQtySpinner;
	private JLabel ingredientQtyLabel;
	private JButton addMenuButton;
	
	//data elements
	private String error = null;
	private Integer selectedMenu = -1;
	private HashMap<Integer, Menu> menus;
	private Integer selectedIngredient = -1;
	private HashMap<Integer, Supply> ingredients;
	
	/** Creates new form FTMSPage */
	public MenuPage(){
		initComponents();
		refreshData();
	}
	
	/** This method is called from within the constructor to initialize the form */
	
	private void initComponents(){
		
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
				
		// elements for Menu
		menuNameTextField = new JTextField();
		menuNameLabel = new JLabel();
		ingredientLabel = new JLabel();
		ingredientQtyLabel = new JLabel();
		SpinnerModel ingredientqty = new SpinnerNumberModel();
		ingredientQtySpinner = new JSpinner(ingredientqty);
		
		menuList = new JComboBox<String>(new String[0]);
		menuList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb =(JComboBox<String>)evt.getSource();
				selectedMenu = cb.getSelectedIndex();
			}
		});
		menuLabel = new JLabel();
		
		ingredientList = new JComboBox<String>(new String[0]);
		ingredientList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb =(JComboBox<String>)evt.getSource();
				selectedIngredient = cb.getSelectedIndex();
			}
		});
		ingredientLabel = new JLabel();
		
		addMenuButton = new JButton();
		
		//global settings and listeners
		
		setTitle("Menu Registration");
				
		menuNameLabel.setText("New Menu:");
		menuLabel.setText("Select Menu:");
		ingredientLabel.setText("Select Ingredient:");
		ingredientQtyLabel.setText("Quantity per Serving");
		addMenuButton.setText("Add Menu");
		addMenuButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addMenuButtonActionPerformed(evt);
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
						.addComponent(menuNameLabel)
						.addComponent(menuLabel)
						.addComponent(ingredientLabel)
						.addComponent(ingredientQtyLabel))

				.addGroup(layout.createParallelGroup()
						.addComponent(menuNameTextField, 200, 200, 400)					
						.addComponent(menuList)
						.addComponent(ingredientList)
						.addComponent(ingredientQtySpinner)
						.addComponent(addMenuButton)))
			
				);
			layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]{addMenuButton, menuLabel});
				
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(menuNameLabel)
						.addComponent(menuNameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(menuLabel)
						.addComponent(menuList))
				.addGroup(layout.createParallelGroup()
						.addComponent(ingredientLabel)
						.addComponent(ingredientList))
				.addGroup(layout.createParallelGroup()

						.addComponent(ingredientQtyLabel)
						.addComponent(ingredientQtySpinner))
				.addGroup(layout.createParallelGroup()

						.addComponent(addMenuButton))
						
				);
		pack();		

	}
	
	private void refreshData(){
		OrderManager om = OrderManager.getInstance();
		//error
		errorMessage.setText(error);
		if(error==null|| error.length()== 0){
			
			//food list
			menus = new HashMap<Integer, Menu>();
			menuList.removeAllItems();
			Iterator<Menu> mIt = om.getMenus().iterator();
			Integer index = 0;
			while(mIt.hasNext()){
				Menu m = mIt.next();
				menus.put(index, m);
				menuList.addItem(m.getMealName());
				index++;
			}
			selectedMenu = -1;
			menuList.setSelectedIndex(selectedMenu);
			
			//equipment list
			ingredients = new HashMap<Integer, Supply>();
			ingredientList.removeAllItems();
			Iterator<Supply> sIt = om.getFoodSupplies().iterator();
			index = 0;
			while(sIt.hasNext()){
				Supply s = sIt.next();
				ingredients.put(index, s);
				ingredientList.addItem(s.getFoodName());
				index++;
			}
			selectedIngredient = -1;
			ingredientList.setSelectedIndex(selectedIngredient);
			
			//Food
			menuNameTextField.setText("");
			ingredientQtySpinner.setValue(0);
			//Equipment
			}

	}
	
	private void addMenuButtonActionPerformed(java.awt.event.ActionEvent evt){
		//call the controller
		FTMSController erc = new FTMSController();
		String menuName;
		String error = "";
		if(menuNameTextField.getText() == null)
			menuName= selectedMenu.toString();
		else menuName=menuNameTextField.getText();
		int ingredientqty = (int) ingredientQtySpinner.getValue(); 
		
				
		Supply ingredient= ingredients.get(selectedIngredient);
		
		try{
			erc.createMenu(menuName, ingredient, ingredientqty);
		}catch(InvalidInputException e){
			error = e.getMessage();
		
		}
		//update visuals
		refreshData();
	}
}