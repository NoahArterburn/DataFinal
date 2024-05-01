/***************************************************************
* Name : Noah Arterburn
* Author: Noah
* Created : 3/28/2024
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

import java.util.HashMap;
import java.util.LinkedList;

class FluShotTracker {
    private LinkedList<Person> personList;
    private HashMap<Long, Person> personMap; // HashMap to store flu shot records by employee ID

    public FluShotTracker() {
        personList = new LinkedList<>();
        personMap = new HashMap<>();
    }

    // Add a new person to the list
    public void addPerson(Person person) {
        boolean idExists = false;
        if (person.getEmployeeID() != 0) {
            for (Person p : personList) {
                if (p.getEmployeeID() == person.getEmployeeID()) {
                    idExists = true;
                    break;
                }
            }
            if (!idExists) {
                personList.add(person);
                personMap.put(person.getEmployeeID(), person);
            } else {
                System.out.println("Employee ID " + person.getEmployeeID() + " already exists in the list.");
            }
        } else {
            System.out.println("Invalid Employee ID.");
        }
    }

    // Mark a person as having received the flu shot
    public void markFluShot(long employeeID) {
        if (employeeID != 0) {
        	Person person = personMap.get(employeeID);
            if (person != null) {
                person.setFluShot(true);
                // Update person in the HashMap
                personMap.put(employeeID, person);
            } else {
                System.out.println("Employee ID not found.");
            }
        } else {
            System.out.println("Invalid Employee ID.");
        }
    }

    // Check if Employee ID already exists in the list
    public boolean employeeIDExists(long employeeID) {
    	return personMap.containsKey(employeeID);
    }

    // Sort and remove duplicates based on Employee ID
    private void sortAndRemoveDuplicates() {
        LinkedList<Person> sortedList = new LinkedList<>();
        for (Person person : personList) {
            boolean found = false;
            for (Person sortedPerson : sortedList) {
                if (person.getEmployeeID() == sortedPerson.getEmployeeID()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                sortedList.add(person);
            }
        }
        personList = sortedList;
    }

    // Selection Sort implementation
    private void selectionSort() {
        int n = personList.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (personList.get(j).getEmployeeID() < personList.get(minIndex).getEmployeeID()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Person temp = personList.get(i);
                personList.set(i, personList.get(minIndex));
                personList.set(minIndex, temp);
            }
        }
    }

    // Validate the date format (MM/DD/YYYY)
    public boolean isValidDateFormat(String date) {
        // Check if the date string matches the MM/DD/YYYY format using regular expression
        return date.matches("^(0[1-9]|1[0-2])/(0[1-9]|1\\d|2\\d|3[01])/(19|20)\\d{2}$");
    }

    // Generate a report of individuals who have and have not received the flu shot
    public String generateReport() {
        sortAndRemoveDuplicates(); // Remove duplicates from the personList
        selectionSort(); // Sort the personList based on employee ID

        StringBuilder report = new StringBuilder();
        report.append("People who have received the flu shot:\n");
        for (Person person : personList) {
            if (person.hasFluShot()) {
                report.append("Name: ").append(person.getFirstName()).append(" ").append(person.getLastName())
                        .append(" (Employee ID: ").append(person.getEmployeeID()).append(")")
                        .append(" Vaccine Batch Number: ").append(person.getBatchNumber())
                        .append(" Vaccination Date: ").append(person.getVaccinationDate()).append("\n");
            }
        }
        report.append("\nPeople who have not received the flu shot:\n");
        for (Person person : personList) {
            if (!person.hasFluShot()) {
                report.append(person.getFirstName()).append(" ").append(person.getLastName()).append(" (Employee ID: ")
                        .append(person.getEmployeeID()).append(")\n");
            }
        }
        return report.toString();
    }
}
