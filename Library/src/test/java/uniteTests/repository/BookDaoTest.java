package uniteTests.repository;

import com.example.repository.BookDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookDaoTest {

    @Test public void testSave_ValidInput() {
        int result =
                BookDao.save("C123", "Introduction to Java", "John Smith", "ABC Publications", 10);
        Assertions.assertEquals(1, result);
    }

    @Test public void testSave_EmptyCallNo() {
        int result = BookDao.save("", "Introduction to Python", "Jane Doe", "XYZ Publications", 5);
        Assertions.assertEquals(-1, result);
    }

    @Test public void testSave_EmptyName() {
        int result = BookDao.save("C456", "", "John Smith", "ABC Publications", 3);
        Assertions.assertEquals(-1, result);
    }

    @Test public void testSave_EmptyAuthor() {
        int result =
                BookDao.save("C789", "Introduction to Machine Learning", "", "XYZ Publications", 7);
        Assertions.assertEquals(-1, result);
    }

    @Test public void testSave_EmptyPublisher() {
        int result = BookDao.save("C101", "Data Structures and Algorithms", "Jane Doe", "", 2);
        Assertions.assertEquals(-1, result);
    }

    @Test public void testSave_ZeroQuantity() {
        int result = BookDao.save("C111", "Database Management Systems", "John Smith",
                                  "ABC Publications", 0);
        Assertions.assertEquals(-1, result);
    }

    @Test public void testSave_DuplicateCallNo() {
        int result =
                BookDao.save("C123", "Introduction to Java", "John Smith", "ABC Publications", 10);
        Assertions.assertEquals(-2, result);
    }
}
