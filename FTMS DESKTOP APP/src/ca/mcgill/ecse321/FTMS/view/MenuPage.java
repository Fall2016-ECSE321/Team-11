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
/**
 * This class is use to perform the view of the Menu and Tracker window
 * @author Group 11
 *
 */
public class MenuPage extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1962263549412602773L;
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
	private JTextField priceTextField;
	private JLabel priceLabel;
	
	private JComboBox<String> saleList;
	private JLabel saleLabel;
	private JSpinner saleQtySpinner;
	private JLabel saleQtyLabel;
	private JButton addTrackerButton;
	
	//data elements
	private String error =null;
	private Integer selectedMenu = -1;
	private HashMap<Integer, Menu> menus;
	private Integer selectedIngredient = -1;
	private HashMap<Integer, Supply> ingredients;
	private Integer selectedSales = -1;
	private HashMap<Integer,Menu> sales;


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
		priceTextField = new JTextField();
		priceLabel = new JLabel();
		
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
		
		// elements for Tracker
		saleQtyLabel = new JLabel();
		SpinnerModel saleqty = new SpinnerNumberModel();
		saleQtySpinner = new JSpinner(saleqty);
		saleList = new JComboBox<String>(new String[0]);
		saleList.addActionListener(new java.awt.event.ActionListener(){
		public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb =(JComboBox<String>)evt.getSource();
					selectedSales = cb.getSelectedIndex();
				}
			});
				saleLabel = new JLabel();
				
				addTrackerButton = new JButton();
				
		//global settings and listeners
		
		setTitle("Menu Registration and Tracker");
				
		menuNameLabel.setText("New Menu:");
		menuLabel.setText("Select Menu:");
		ingredientLabel.setText("Select Ingredient:");
		ingredientQtyLabel.setText("Quantity per Serving");
		priceLabel.setText("Price");		
		
		addMenuButton.setText("Add Menu");
		addMenuButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addMenuButtonActionPerformed(evt);
			}
		});
		
		saleLabel.setText("Sold Menu:");
		saleQtyLabel.setText("Sale quantity");
		addTrackerButton.setText("Add Sales");
		addTrackerButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addTrackerButtonActionPerformed(evt);
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
						.addComponent(ingredientQtyLabel)
						.addComponent(priceLabel))

				.addGroup(layout.createParallelGroup()
						.addComponent(menuNameTextField)					
						.addComponent(menuList)
						.addComponent(ingredientList)
						.addComponent(ingredientQtySpinner)
						.addComponent(priceTextField)
						.addComponent(addMenuButton))
			.addGroup(layout.createParallelGroup()
					.addComponent(saleLabel)
					.addComponent(saleQtyLabel))
			.addGroup(layout.createParallelGroup()
					.addComponent(saleList)
					.addComponent(saleQtySpinner)
					.addComponent(addTrackerButton)))
			
					);
			layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]{addMenuButton, menuNameTextField});
			layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]{addTrackerButton, saleList});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(menuNameLabel)
						.addComponent(menuNameTextField)
						.addComponent(saleLabel)
						.addComponent(saleList))
				.addGroup(layout.createParallelGroup()
						.addComponent(menuLabel)
						.addComponent(menuList)
						.addComponent(saleQtyLabel)
						.addComponent(saleQtySpinner))
				.addGroup(layout.createParallelGroup()
						.addComponent(ingredientLabel)
						.addComponent(ingredientList)
						.addComponent(addTrackerButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(ingredientQtyLabel)
						.addComponent(ingredientQtySpinner))
				.addGroup(layout.createParallelGroup()
						.addComponent(priceLabel)
						.addComponent(priceTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(addMenuButton))
				);
		pack();

	}
	/**
	 * Method to refresh the view page
	 */
	
	private void refreshData(){
		OrderManager om = OrderManager.getInstance();
		//error
		errorMessage.setText(error);
		if(error==null|| error.length()== 0){
			
			//menu and sale list
			menus = new HashMap<Integer, Menu>();
			menuList.removeAllItems();
			sales = new HashMap<Integer, Menu>();
			saleList.removeAllItems();
			
			Iterator<Menu> mIt = om.getMenus().iterator();
			Integer index = 0;
			while(mIt.hasNext()){
				Menu m = mIt.next();
				menus.put(index, m);
				menuList.addItem(m.getMealName());
				sales.put(index, m);
				saleList.addItem(m.getMealName());
				index++;
			}
			selectedMenu = -1;
			menuList.setSelectedIndex(selectedMenu);
			
			//ingredient list
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
			priceTextField.setText("");
			
			//sale list
			selectedSales = -1;
			saleList.setSelectedIndex(selectedSales);
			
			//tracker
			saleQtySpinner.setValue(0);
			}
		pack();

	}
	
	/**
	  * This method implement the actions to create an Menu element when the addMenu is selected
	  * * @param evt
	 */
	private void addMenuButtonActionPerformed(java.awt.event.ActionEvent evt){
		//call the controller
		FTMSController erc = new FTMSController();

		String menuName="";
		int ingredientqty = (int) ingredientQtySpinner.getValue(); 
		// error handling
		error = "";
		// for null entry
		if (selectedMenu < 0 && menuNameTextField.getText()==null)
			error = error + "Menu must be entered or selected";
		if (selectedIngredient < 0)
			error = error + "Ingredient needs to be selected";
		
		// for double entry of menu
		if(selectedMenu > 0 && menuNameTextField.getText()!=null){
			error = error + "Only one Menu must be entered or selected";
		menuNameTextField.setText("");
		selectedMenu=-1;
			refreshData();
		}
		// 
		if (ingredientqty<0)
			error=error+"Positive quantity must be entered";
		
		//translation of view page input
		else if(menuNameTextField.getText() == null)
			menuName= menus.get(selectedMenu).getMealName().toString();
		else menuName=menuNameTextField.getText();
		// input to controller
		if (error.length()==0){
		try{
			erc.createMenu(menuName, ingredients.get(selectedIngredient).getFoodName().toString(), ingredientqty, Double.parseDouble(priceTextField.getText()));
		}catch(InvalidInputException e){
			error = e.getMessage();
		}	
		}
		//update visuals
		refreshData();
	}
	/**
	  * This method implement the actions to create an Tracker element when the addSales is selected
	  * * @param evt
	 */
	private void addTrackerButtonActionPerformed(java.awt.event.ActionEvent evt){
		//call the controller
		FTMSController erc = new FTMSController();
		
		int saleqty = (int) saleQtySpinner.getValue();
		// error handling
		error = "";
		if (selectedSales<0)
			error=error+ "Sale item must be selected";
		
		// input to controller
		try{
			erc.createTracker(sales.get(selectedSales).getMealName().toString(), saleqty);
		}catch(InvalidInputException e){
			error = e.getMessage();
		
		}
		//update visuals
		refreshData();
	}
}