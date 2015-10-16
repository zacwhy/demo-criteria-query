package hello;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        employeeRepository.save(new Employee("FirstName1", "LastName1"));
        employeeRepository.save(new Employee("FirstName2", "LastName2"));
        employeeRepository.save(new Employee("FirstName3", "LastName3"));
    }

    @After
    public void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void shouldCreateEmployee() {

        // Arrange
        Employee employee = new Employee("FirstName4", "LastName4");

        // Act
        employeeRepository.save(employee);
        Employee found = employeeRepository.findOne(employee.getId());

        // Assert
        assertEquals(4, employeeRepository.count());
        assertEquals("FirstName4", found.getFirstName());
        assertEquals("LastName4", found.getLastName());
    }

    @Test
    public void shouldSelectOneColumn() {

        // Arrange
        String[] columnNames = {"firstName"};

        // Act
        List<Map<String, Object>> results1 = employeeRepository.findAllSelectingColumns(columnNames);

        /**
         * [
         *   {firstName=FirstName1},
         *   {firstName=FirstName2},
         *   {firstName=FirstName3}
         * ]
         */

        // Assert
        assertEquals("item count", 3, results1.size());

        for (Map<String, Object> map : results1) {
            assertEquals("columns selected", 1, map.size());
            assertTrue(map.containsKey("firstName"));
        }
    }

    @Test
    public void shouldSelectTwoColumns() {

        // Arrange
        String[] columnNames = {"firstName", "lastName"};

        // Act
        List<Map<String, Object>> results2 = employeeRepository.findAllSelectingColumns(columnNames);

        /**
         * [
         *   {firstName=FirstName1, lastName=LastName1},
         *   {firstName=FirstName2, lastName=LastName2},
         *   {firstName=FirstName3, lastName=LastName3}
         * ]
         */

        // Assert
        assertEquals("item count", 3, results2.size());

        for (Map<String, Object> map : results2) {
            assertEquals("columns selected", 2, map.size());
            assertTrue(map.containsKey("firstName"));
            assertTrue(map.containsKey("lastName"));
        }
    }

    @Test
    public void shouldSelectThreeColumns() {

        // Arrange
        String[] columnNames = {"id", "firstName", "lastName"};

        // Act
        List<Map<String, Object>> results3 = employeeRepository.findAllSelectingColumns(columnNames);

        /**
         * [
         *   {firstName=FirstName1, lastName=LastName1, id=1},
         *   {firstName=FirstName2, lastName=LastName2, id=2},
         *   {firstName=FirstName3, lastName=LastName3, id=3}
         * ]
         */

        // Assert
        assertEquals("item count", 3, results3.size());

        for (Map<String, Object> map : results3) {
            assertEquals("columns selected", 3, map.size());
            assertTrue(map.containsKey("id"));
            assertTrue(map.containsKey("firstName"));
            assertTrue(map.containsKey("lastName"));
        }
    }

}
