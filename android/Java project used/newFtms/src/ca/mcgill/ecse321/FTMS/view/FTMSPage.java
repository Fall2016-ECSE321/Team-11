package ca.mcgill.ecse321.FTMS.view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class FTMSPage extends JFrame {

		private static final long serialVersionUID = 919724868025009115L;
		//UI elements
		private JLabel selectLabel;
		private JButton viewScheduleButton;
		private JButton viewInventoryButton;
		private JButton viewMenuButton;
		
		
		/** Creates new form FTMSPage */
		public FTMSPage(){
			initComponents();
		}
		
		/** This method is called from within the constructor to initialize the form */
		
		private void initComponents(){
			
			//elements for FTMS Manager
			selectLabel = new JLabel();
			viewScheduleButton = new JButton();
			viewInventoryButton = new JButton();
			viewMenuButton = new JButton();
			
					
			//global settings and listeners
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setTitle("FTMS MANAGER");
			
			selectLabel.setText("Select to Access Manager:");
			viewScheduleButton.setText("Schedule Manager");
			viewScheduleButton.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent evt){
					viewScheduleButtonActionPerformed(evt);
					}
			});
			viewInventoryButton.setText("Inventory Manager");
			viewInventoryButton.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent evt){
					viewInventoryButtonActionPerformed(evt);
				}
			});
			
			viewMenuButton.setText("Menu Manager");
			viewMenuButton.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent evt){
					viewMenuButtonActionPerformed(evt);
				}
			});
			
			//layout
			GroupLayout layout = new GroupLayout(getContentPane());
			getContentPane().setLayout(layout);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(selectLabel)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
							.addComponent(viewScheduleButton))
					.addGroup(layout.createParallelGroup()
							.addComponent(viewInventoryButton))
					.addGroup(layout.createParallelGroup()
						.addComponent(viewMenuButton)))
					);
			
			layout.setVerticalGroup(
					layout.createSequentialGroup()
					.addComponent(selectLabel)
					.addGroup(layout.createParallelGroup()
							.addComponent(viewScheduleButton)
							.addComponent(viewInventoryButton)
							.addComponent(viewMenuButton))
					
					);
			pack();		

		}
		
				private void viewScheduleButtonActionPerformed(java.awt.event.ActionEvent evt){
					java.awt.EventQueue.invokeLater(new Runnable(){
						public void run(){
							new SchedulePage().setVisible(true);
							
						}
					});
		}
		private void viewInventoryButtonActionPerformed(java.awt.event.ActionEvent evt){
			java.awt.EventQueue.invokeLater(new Runnable(){
				public void run(){
					new InventoryPage().setVisible(true);

				}
			});

		}
		
		private void viewMenuButtonActionPerformed(java.awt.event.ActionEvent evt){
			java.awt.EventQueue.invokeLater(new Runnable(){
				public void run(){
					new MenuPage().setVisible(true);
					
				}
			});

		}
	}




