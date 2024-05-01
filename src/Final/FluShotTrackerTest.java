package Final;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class FluShotTrackerTest {

	// Test method to validate valid names
	@Test
	public void testIsValidName_ValidNames() {
		FluShotTrackerGUI gui = new FluShotTrackerGUI();

		assertTrue(gui.isValidName("John"));
		assertTrue(gui.isValidName("Doe"));
		assertTrue(gui.isValidName("Alice"));
		assertTrue(gui.isValidName("Bob"));
	}

	// Test invalid names
	@Test
	public void testIsValidName_InvalidNames() {
		FluShotTrackerGUI gui = new FluShotTrackerGUI();

		assertFalse(gui.isValidName("John123"));
		assertFalse(gui.isValidName("Doe!"));
		assertFalse(gui.isValidName("Alice123"));
		assertFalse(gui.isValidName("Bob@"));
		assertFalse(gui.isValidName("123"));
		assertFalse(gui.isValidName("!@#"));
	}

	// Test method to add a person to the tracker
	@Test
	public void testAddPerson() {
		FluShotTracker tracker = new FluShotTracker();
		Person person = new Person("John", "Doe", 123456789);
		tracker.addPerson(person);
		assertTrue(tracker.employeeIDExists(123456789));
	}

	// Test method to mark a flu shot for a person
	@Test
	public void testMarkFluShot() {
		FluShotTracker tracker = new FluShotTracker();
		Person person = new Person("Jane", "Doe", 987654321);
		tracker.addPerson(person);
		tracker.markFluShot(987654321);
		assertTrue(tracker.generateReport().contains("Jane Doe"));
	}

	// Test method to generate a report
	@Test
	public void testGenerateReport() {
		FluShotTracker tracker = new FluShotTracker();
		Person person1 = new Person("Alice", "Smith", 900000035);
		Person person2 = new Person("Bob", "Johnson", 900000045);
		Person person3 = new Person("Jane", "Doe", 900000042);
		tracker.addPerson(person1);
		tracker.addPerson(person2);
		tracker.addPerson(person3);
		tracker.markFluShot(900000035);
		String report = tracker.generateReport();
		assertTrue(report.contains("Alice Smith"));
		assertTrue(report.contains("Bob Johnson"));
	}
	
	 // Test method to add a person with an invalid employee ID
	@Test
	public void testAddPersonWithInvalidEmployeeID() {
		FluShotTracker tracker = new FluShotTracker();
		Person person = new Person("Jane", "Doe", 000000000);
		tracker.addPerson(person);
		assertFalse(tracker.employeeIDExists(000000000));
	}

}
