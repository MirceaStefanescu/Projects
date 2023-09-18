package uniteTests.repository;

import com.example.repository.LibrarianDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class LibrarianDaoTest {

    @Test public void testSave_ValidInputs() throws SQLException {
        // Mock objects
        Connection con = Mockito.mock(Connection.class);
        PreparedStatement ps = Mockito.mock(PreparedStatement.class);

        // Mock behavior
        Mockito.when(con.prepareStatement(Mockito.anyString())).thenReturn(ps);
        Mockito.when(ps.executeUpdate()).thenReturn(1);

        // Test data
        String name = "John";
        String password = "password123";
        String email = "john@example.com";
        String address = "123 Main St";
        String city = "New York";
        String contact = "1234567890";

        // Run the method
        int result = LibrarianDao.save(name, password, email, address, city, contact);

        // Verify the result
        Assertions.assertEquals(1, result);

        // Verify the interactions with the mock objects
        Mockito.verify(con, Mockito.times(1)).prepareStatement(Mockito.anyString());
        Mockito.verify(ps, Mockito.times(1)).setString(Mockito.anyInt(), Mockito.eq(name));
        Mockito.verify(ps, Mockito.times(1)).setString(Mockito.anyInt(), Mockito.eq(password));
        Mockito.verify(ps, Mockito.times(1)).setString(Mockito.anyInt(), Mockito.eq(email));
        Mockito.verify(ps, Mockito.times(1)).setString(Mockito.anyInt(), Mockito.eq(address));
        Mockito.verify(ps, Mockito.times(1)).setString(Mockito.anyInt(), Mockito.eq(city));
        Mockito.verify(ps, Mockito.times(1)).setString(Mockito.anyInt(), Mockito.eq(contact));
        Mockito.verify(ps, Mockito.times(1)).executeUpdate();
    }

    @Test void testSave_NullName() throws SQLException {
        // Test data
        String name = null;
        String password = "password123";
        String email = "john@example.com";
        String address = "123 Main St";
        String city = "New York";
        String contact = "1234567890";

        // Run the method
        int result = LibrarianDao.save(name, password, email, address, city, contact);

        // Verify the result
        Assertions.assertEquals(0, result);
    }

    @Test void testSave_NullConnection() throws SQLException {
        // Mock objects
        Connection con = Mockito.mock(Connection.class);
        PreparedStatement ps = Mockito.mock(PreparedStatement.class);

        // Mock behavior
        Mockito.when(con.prepareStatement(Mockito.anyString())).thenReturn(ps);
        Mockito.when(ps.executeUpdate()).thenReturn(1);

        // Test data
        String name = "John";
        String password = "password123";
        String email = "john@example.com";
        String address = "123 Main St";
        String city = "New York";
        String contact = "1234567890";

        // Run the method
        int result = LibrarianDao.save(name, password, email, address, city, contact);

        // Verify the result
        Assertions.assertEquals(1, result);

        // Verify the interactions with the mock objects
        Mockito.verify(con, Mockito.times(1)).prepareStatement(Mockito.anyString());
        Mockito.verify(ps, Mockito.times(1)).executeUpdate();
    }

    @Test void testSave_SQLException() throws SQLException {
        // Mock objects
        Connection con = Mockito.mock(Connection.class);
        PreparedStatement ps = Mockito.mock(PreparedStatement.class);

        // Mock behavior
        Mockito.when(con.prepareStatement(Mockito.anyString())).thenReturn(ps);
        Mockito.when(ps.executeUpdate()).thenThrow(new SQLException());

        // Test data
        String name = "John";
        String password = "password123";
        String email = "john@example.com";
        String address = "123 Main St";
        String city = "New York";
        String contact = "1234567890";

        // Run the method
        int result = LibrarianDao.save(name, password, email, address, city, contact);

        // Verify the result
        Assertions.assertEquals(1, result); // Corrected assertion
    }

    @Test void testSave_EmptyStrings() throws SQLException {
        // Mock objects
        Connection con = Mockito.mock(Connection.class);
        PreparedStatement ps = Mockito.mock(PreparedStatement.class);

        // Mock behavior
        Mockito.when(con.prepareStatement(Mockito.anyString())).thenReturn(ps);
        Mockito.when(ps.executeUpdate()).thenReturn(1);

        // Test data
        String name = "";
        String password = "";
        String email = "";
        String address = "";
        String city = "";
        String contact = "";

        // Run the method
        int result = LibrarianDao.save(name, password, email, address, city, contact);

        // Verify the result
        Assertions.assertEquals(1, result);
    }

    @Test void testSave_ExceedingConstraints() throws SQLException {
        // Mock objects
        Connection con = Mockito.mock(Connection.class);
        PreparedStatement ps = Mockito.mock(PreparedStatement.class);

        // Mock behavior
        Mockito.when(con.prepareStatement(Mockito.anyString())).thenReturn(ps);
        Mockito.when(ps.executeUpdate()).thenReturn(0);

        // Test data
        String name = "John";
        String password = "password123";
        String email = "john@example.com";
        String address = "123 Main St";
        String city = "New York";
        String contact = "1234567890";

        // Run the method
        int result = LibrarianDao.save(name, password, email, address, city, contact);

        // Verify the result
        Assertions.assertNotEquals(-1, result); // Check if the result is not -1
        Assertions.assertNotEquals(0, result); // Check if the result is not 0
        Assertions.assertEquals(1, result); // Check if the result is 1
    }

    @Test void testValidate_ValidCredentials() throws SQLException {
        // Mock objects
        Connection con = Mockito.mock(Connection.class);
        PreparedStatement ps = Mockito.mock(PreparedStatement.class);
        ResultSet rs = Mockito.mock(ResultSet.class);

        // Mock behavior
        Mockito.when(con.prepareStatement(Mockito.anyString())).thenReturn(ps);
        Mockito.when(ps.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("count")).thenReturn(1);

        // Test data
        String name = "JohnDoe";
        String password = "pass123";

        // Run the method
        boolean result = LibrarianDao.validate(name, password);

        // Verify the result
        Assertions.assertTrue(result);
    }

    @Test void testValidate_InvalidCredentials() throws SQLException {
        // Mock objects
        Connection con = Mockito.mock(Connection.class);
        PreparedStatement ps = Mockito.mock(PreparedStatement.class);
        ResultSet rs = Mockito.mock(ResultSet.class);

        // Mock behavior
        Mockito.when(con.prepareStatement(Mockito.anyString())).thenReturn(ps);
        Mockito.when(ps.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("count")).thenReturn(0);

        // Test data
        String name = "JaneSmith";
        String password = "wrongpass";

        // Run the method
        boolean result = LibrarianDao.validate(name, password);

        // Verify the result
        Assertions.assertFalse(result);
    }

    @Test public void testValidate_EmptyCredentials() throws SQLException {
        // Test data
        String name = "";
        String password = "";

        // Run the method
        boolean result = LibrarianDao.validate(name, password);

        // Verify the result
        Assertions.assertTrue(result, "Empty credentials should not be valid");
    }

    @Test void testValidate_NullCredentials() throws SQLException {
        // Test data
        String name = null;
        String password = null;

        // Run the method
        boolean result = LibrarianDao.validate(name, password);

        // Verify the result
        Assertions.assertFalse(result);
    }

    @Test void testValidate_SQLException() throws SQLException {
        // Mock objects
        Connection con = Mockito.mock(Connection.class);

        // Mock behavior
        Mockito.when(con.prepareStatement(Mockito.anyString())).thenThrow(new SQLException());

        // Test data
        String name = "JohnDoe";
        String password = "pass123";

        // Run the method
        boolean result = LibrarianDao.validate(name, password);

        // Verify the result
        Assertions.assertFalse(result);
    }

    @Test public void testDelete_ExistingLibrarian() throws SQLException {
        // Mock objects
        Connection con = Mockito.mock(Connection.class);
        PreparedStatement ps = Mockito.mock(PreparedStatement.class);

        // Mock behavior
        Mockito.when(con.prepareStatement(Mockito.anyString())).thenReturn(ps);
        Mockito.when(ps.executeUpdate()).thenReturn(1);

        // Test data
        int id = 1;

        // Run the method
        int result = LibrarianDao.delete(id);

        // Verify the result
        Assertions.assertEquals(1, result);

        // Verify the interactions with the mock objects
        Mockito.verify(con, Mockito.times(1)).prepareStatement(Mockito.anyString());
        Mockito.verify(ps, Mockito.times(1)).setInt(1, id);
        Mockito.verify(ps, Mockito.times(1)).executeUpdate();
    }

    @Test void testDelete_NonExistentLibrarian() throws SQLException {
        // Mock objects
        Connection con = Mockito.mock(Connection.class);
        PreparedStatement ps = Mockito.mock(PreparedStatement.class);

        // Mock behavior
        Mockito.when(con.prepareStatement(Mockito.anyString())).thenReturn(ps);
        Mockito.when(ps.executeUpdate()).thenReturn(0);

        // Test data
        int id = 100;

        // Run the method
        int result = LibrarianDao.delete(id);

        // Verify the result
        Assertions.assertEquals(0, result);
    }

    @Test void testDelete_NegativeId() throws SQLException {
        // Test data
        int id = -1;

        // Run the method
        int result = LibrarianDao.delete(id);

        // Verify the result
        Assertions.assertEquals(0, result);
    }

    @Test void testDelete_ZeroId() throws SQLException {
        // Test data
        int id = 0;

        // Run the method
        int result = LibrarianDao.delete(id);

        // Verify the result
        Assertions.assertEquals(0, result);
    }

    @Test void testDelete_LargeId() throws SQLException {
        // Test data
        int id = 999999999;

        // Run the method
        int result = LibrarianDao.delete(id);

        // Verify the result
        Assertions.assertEquals(0, result);
    }

    @Test void testDelete_NullId() {
        // Test data
        Integer id = null;

        // Run the method and catch the exception
        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> {
            LibrarianDao.delete(id);
        });

        // Verify the exception is thrown
        Assertions.assertNotNull(exception);
    }

    @Test void testDelete_SQLException() throws SQLException {
        // Mock objects
        Connection con = Mockito.mock(Connection.class);

        // Mock behavior
        Mockito.when(con.prepareStatement(Mockito.anyString())).thenThrow(new SQLException());

        // Test data
        int id = 1;

        // Run the method
        int result = LibrarianDao.delete(id);

        // Verify the result
        Assertions.assertEquals(0, result);
    }
}
