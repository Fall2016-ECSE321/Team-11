package ca.mcgill.ecse321.FTMS.view;

import java.awt.Color;


import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse321.FTMS.controller.FTMSController;
import ca.mcgill.ecse321.FTMS.controller.InvalidInputException;
import ca.mcgill.ecse321.FTMS.model.Employee;
import ca.mcgill.ecse321.FTMS.model.Schedule;
import ca.mcgill.ecse321.FTMS.model.StaffManager;

public class SchedulePage extends JFrame {


	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 919724868025009115L;
	//UI elements
	private JLabel errorMessage;
	private JComboBox<String> employeeList;
	private JLabel employeeLabel;
	private JComboBox<String> scheduleList;
	private JLabel scheduleLabel;
	private JButton registerButton;
	private JTextField employeeNameTextField;
	private JLabel employeeNameLabel;
	private JTextField roleTextField;
	private JLabel roleLabel;
	private JSpinner hourSpinner;
	private JLabel hourLabel;
	
	private JButton addEmployeeButton;
	private JTextField weekdayTextField;
	private JLabel weekdayLabel;
	private JDatePickerImpl scheduleDatePicker;
	private JLabel scheduleDateLabel;
	private JSpinner startTimeSpinner;
	private JLabel startTimeLabel;
	private JSpinner endTimeSpinner;
	private JLabel endTimeLabel;
	private JButton addScheduleButton;
	
	//data elements
	private String error = null;
	private Integer selectedEmployee = -1;
	private HashMap<Integer, Employee> employees;
	private Integer selectedSchedule = -1;
	private HashMap<Integer, Schedule> schedules;
	
	*//** Creates new form FTMSPage *//*
	public SchedulePage(){
		initComponents();
		refreshData();
	}
	
	*//** This method is called from within the constructor to initialize the form *//*
	
	private void initComponents(){
		
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		//elements for registration
		employeeList = new JComboBox<String>(new String[0]);
		employeeList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb =(JComboBox<String>)evt.getSource();
				selectedEmployee = cb.getSelectedIndex();
			}
		});
		employeeLabel = new JLabel();
		scheduleList = new JComboBox<String>(new String[0]);
		scheduleList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSchedule = cb.getSelectedIndex();
			}
		});
		scheduleLabel = new JLabel();
		registerButton = new JButton();
				
		// elements for employee
		SpinnerModel hour = new SpinnerNumberModel();
		
		employeeNameTextField = new JTextField();
		employeeNameLabel = new JLabel();
		roleTextField = new JTextField();
		roleLabel = new JLabel();
		hourLabel = new JLabel();
		hourSpinner = new JSpinner(hour);
		
		addEmployeeButton = new JButton();
		
		//elements for schedule
		weekdayTextField = new JTextField();
		weekdayLabel = new JLabel();
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		scheduleDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		scheduleDateLabel = new JLabel();
		startTimeSpinner = new JSpinner (new SpinnerDateModel());
		JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
		startTimeSpinner.setEditor(startTimeEditor); //will only show the current time
		startTimeLabel = new JLabel();
		endTimeSpinner = new JSpinner (new SpinnerDateModel());
		JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
		endTimeSpinner.setEditor(endTimeEditor);// will only show the current time
		endTimeLabel = new JLabel();
		addScheduleButton = new JButton();
		
				
		//global settings and listeners
		setTitle("Schedule Registration");
		
		employeeLabel.setText("Select Employee:");
		scheduleLabel.setText("Select Schedule:");
		registerButton.setText("Register");
		registerButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				registerButtonActionPerformed(evt);
				}
		});
		
		employeeNameLabel.setText("Name:");
		roleLabel.setText("Staff Role:");
		hourLabel.setText("Hours to Work:");
		
		addEmployeeButton.setText("Add Employee");
		addEmployeeButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addEmployeeButtonActionPerformed(evt);
			}
		});
		weekdayLabel.setText("Weekday:");
		scheduleDateLabel.setText("Date:");
		startTimeLabel.setText("Start Time:");
		endTimeLabel.setText("End Time:");
		addScheduleButton.setText("Add Schedule:");
		addScheduleButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addScheduleButtonActionPerformed(evt);
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
						.addComponent(employeeLabel)
						.addComponent(registerButton)
						.addComponent(employeeNameLabel)
						.addComponent(roleLabel)
						.addComponent(hourLabel))
						
				.addGroup(layout.createParallelGroup()
						.addComponent(employeeList)
						.addComponent(employeeNameTextField, 200, 200, 400)
						.addComponent(roleTextField)
						.addComponent(hourSpinner)
						.addComponent(addEmployeeButton))
				
				.addGroup(layout.createParallelGroup()
						.addComponent(scheduleLabel)
						.addComponent(weekdayLabel)
						.addComponent(scheduleDateLabel)
						.addComponent(startTimeLabel)
						.addComponent(endTimeLabel))
				.addGroup(layout.createParallelGroup()
						.addComponent(scheduleList)
						.addComponent(weekdayTextField)
						.addComponent(scheduleDatePicker)
						.addComponent(startTimeSpinner)
						.addComponent(endTimeSpinner)
						.addComponent(addScheduleButton)))
				);
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]{registerButton, scheduleLabel});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]{addEmployeeButton, employeeNameTextField});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]{addScheduleButton, weekdayTextField});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(employeeLabel)
						.addComponent(employeeList)
						.addComponent(scheduleLabel)
						.addComponent(scheduleList))
				.addComponent(registerButton)
				.addGroup(layout.createParallelGroup()
						.addComponent(employeeNameLabel)
						.addComponent(employeeNameTextField)
						.addComponent(weekdayLabel)
						.addComponent(weekdayTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(roleLabel)
						.addComponent(roleTextField)
						.addComponent(scheduleDateLabel)
						.addComponent(scheduleDatePicker))
				.addGroup(layout.createParallelGroup()
						.addComponent(hourLabel)
						.addComponent(hourSpinner)
						.addComponent(startTimeLabel)
						.addComponent(startTimeSpinner))
				.addGroup(layout.createParallelGroup()
						.addComponent(endTimeLabel)
						.addComponent(endTimeSpinner))
				.addGroup(layout.createParallelGroup()
						.addComponent(addEmployeeButton)
						.addComponent(addScheduleButton))
				
				);
		pack();		

	}
	
	private void refreshData(){
		StaffManager sm = StaffManager.getInstance();
		//error
		errorMessage.setText(error);
		if(error==null|| error.length()== 0){
			
			//employee list
			employees = new HashMap<Integer, Employee>();
			employeeList.removeAllItems();
			Iterator<Employee> eIt = sm.getEmployees().iterator();
			Integer index = 0;
			while(eIt.hasNext()){
				Employee e = eIt.next();
				employees.put(index, e);
				employeeList.addItem(e.getStaffName());
				index++;
			}
			selectedEmployee = -1;
			employeeList.setSelectedIndex(selectedEmployee);
			//schedule list
			schedules = new HashMap<Integer, Schedule>();
			scheduleList.removeAllItems();
			Iterator<Schedule> sIt = sm.getSchedules().iterator();
			index = 0;
			while(sIt.hasNext()){
				Schedule s = sIt.next();
				schedules.put(index, s);
				scheduleList.addItem(s.getWeekday());
				index++;
			}
			selectedSchedule = -1;
			scheduleList.setSelectedIndex(selectedSchedule);
			//Employee
			employeeNameTextField.setText("");
			roleTextField.setText("");
			hourSpinner.setValue(0);
			
			//Schedule
			weekdayTextField.setText("");
			scheduleDatePicker.getModel().setValue(null);
			startTimeSpinner.setValue(new Date());
			endTimeSpinner.setValue(new Date());
			
		}
		pack();		

	}
	
	private void addEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt){
		//call the controller
		FTMSController erc = new FTMSController();
		int hour = (int) hourSpinner.getValue();
		error=null;
		try{
			erc.createEmployee(employeeNameTextField.getText(), roleTextField.getText(), hour);
		}catch(InvalidInputException e){
			error = e.getMessage();
		}
		
		//update visuals
		refreshData();
	}
	private void addScheduleButtonActionPerformed(java.awt.event.ActionEvent evt){
		//call the controller
		FTMSController erc = new FTMSController();
		//JSpinner actually returns a date and time
		//force the same date for start and end time to ensure that only the times differ
		Calendar calendar = Calendar.getInstance();
		calendar.setTime((Date)startTimeSpinner.getValue());
		calendar.set(2016, 1, 1);
		Time startTime = new Time(calendar.getTime().getTime());
		calendar.setTime((Date)endTimeSpinner.getValue());
		calendar.set(2016, 1, 1);
		Time endTime = new Time(calendar.getTime().getTime());
		error= null;
		try{
			erc.createSchedule(weekdayTextField.getText(),(java.sql.Date) scheduleDatePicker.getModel().getValue(), startTime, endTime);
		}catch(InvalidInputException e){
			error = e.getMessage();
		}
		
		//update visuals
		refreshData();
		
	}
	
	private void registerButtonActionPerformed(java.awt.event.ActionEvent evt){
		error = "";
		if (selectedEmployee < 0)
			error = error + "Employee needs to be selected for registration! ";
		if (selectedSchedule < 0)
			error = error + "Schedule needs to be selected for registration!";
		error = error.trim();
		if (error.length() == 0){
			//call the controller
			FTMSController erc = new FTMSController();
			try{
				erc.scheduleRegister(employees.get(selectedEmployee), schedules.get(selectedSchedule));
			}catch(InvalidInputException e){
				error = e.getMessage();
			}
		}
		//update visuals
		 refreshData();
	}
}


