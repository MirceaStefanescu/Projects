package uniteTests.db;

import com.example.singleton.DB;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

public class TestDB {
    @Test public void testGetConnection() {
        Connection connection = DB.getConnection();
        assertNotNull("Connection should not be null", connection);
    }
}
