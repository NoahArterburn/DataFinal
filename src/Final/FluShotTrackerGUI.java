/***************************************************************
* Name : Noah Arterburn
* Author: Noah
* Created : 4/4/2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows 11
* IDE: eclipse 2023,
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This is the Person add or an HR staff entering information due to the amount of information given of personal order. This program is entering vaccination information. 
*            Input: gui and user dependent 
*            Output:gui and user dependent 
*            BigO: 0(n^2)
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
***************************************************************/
package Final;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class FluShotTrackerGUI extends JFrame implements ActionListener {
	private JTextField firstNameField, lastNameField, employeeIDField, batchNumberField, dateField;
	private JButton addButton, markButton, reportButton; // extendedReportButton;
	private JTextArea reportArea;
	private FluShotTracker tracker;

	public FluShotTrackerGUI() {
		tracker = new FluShotTracker();

		setTitle("Flu Shot Tracker");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;

		// First Name
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(new JLabel("First Name:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		firstNameField = new JTextField(20);
		panel.add(firstNameField, gbc);

		// Last Name
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JLabel("Last Name:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		lastNameField = new JTextField(20);
		panel.add(lastNameField, gbc);

		// Employee ID
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(new JLabel("Employee ID:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		employeeIDField = new JTextField(20);
		panel.add(employeeIDField, gbc);

		// Vaccine Batch Number
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(new JLabel("Vaccine Batch Number:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		batchNumberField = new JTextField(20);
		panel.add(batchNumberField, gbc);

		// Vaccination Date
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(new JLabel("Vaccination Date (MM/DD/YYYY):"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		dateField = new JTextField(20);
		panel.add(dateField, gbc);

		// Buttons
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		addButton = new JButton("Add Person");
		addButton.addActionListener(this);
		panel.add(addButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		markButton = new JButton("Mark Flu Shot");
		markButton.addActionListener(this);
		panel.add(markButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		reportButton = new JButton("Generate Report");
		reportButton.addActionListener(this);
		panel.add(reportButton, gbc);

		// Report Area
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		reportArea = new JTextArea();
		reportArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(reportArea);
		panel.add(scrollPane, gbc);

		/*
		 * extendedReportButton = new JButton("Extended Vax Info");
		 * extendedReportButton.addActionListener(this);
		 * panel.add(extendedReportButton);
		 */

		add(panel);
		setVisible(true);
	}
	
	// Method to validate a name (only alphabet)
	boolean isValidName(String name) {
		return name.matches("[a-zA-Z]+$");
	}
	
	//method to handle button clicks
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String employeeIDStr = employeeIDField.getText();
			String batchNumber = batchNumberField.getText();
			String date = dateField.getText();
			if (!firstName.isEmpty() && !lastName.isEmpty() && !employeeIDStr.isEmpty()) {
				if (isValidName(firstName) && isValidName(lastName)) {
					try {
						long employeeID = Long.parseLong(employeeIDStr);
						if (!employeeIDStr.equals("000000000") && String.valueOf(employeeID).length() == 9) {
							if (!tracker.employeeIDExists(employeeID)) {
								tracker.addPerson(new Person(firstName, lastName, employeeID));
								JOptionPane.showMessageDialog(this, "Person added successfully!");
								firstNameField.setText("");
								lastNameField.setText("");
								employeeIDField.setText("");
								batchNumberField.setText("");
								dateField.setText("");
							} else {
								JOptionPane.showMessageDialog(this, "Employee ID already exists in the list.");
							}
						} else {
							JOptionPane.showMessageDialog(this, "Employee ID must be 9 digits long.");
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(this, "Invalid employee ID format.");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Invalid first name or last name. Please enter valid names.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Please enter First Name, Last Name & Employee ID");
			}
		} else if (e.getSource() == markButton) {
			// Marking a flu shot
			String employeeIDStr = employeeIDField.getText();
			String batchNumber = batchNumberField.getText();
			String date = dateField.getText();
			if (!employeeIDStr.isEmpty() && !batchNumber.isEmpty() && !date.isEmpty()) {
				try {
					long employeeID = Long.parseLong(employeeIDStr);
					if (!employeeIDStr.equals("000000000")) {
						tracker.markFluShot(employeeID);
						JOptionPane.showMessageDialog(this, "Flu shot marked successfully!");
						employeeIDField.setText("");
					} else {
						JOptionPane.showMessageDialog(this, "Invalid Employee ID.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "Invalid employee ID format.");
				}
			} else {
				JOptionPane.showMessageDialog(this,
						"Please enter all fields,valid 9 digit ID & valid date in MM/DD/YYYY format.");
			}
			// Generating report
		} else if (e.getSource() == reportButton) {
			String report = tracker.generateReport();
			reportArea.setText(report);
		}

	}

	// Main method
	public static void main(String[] args) {
		new FluShotTrackerGUI();
	}
}