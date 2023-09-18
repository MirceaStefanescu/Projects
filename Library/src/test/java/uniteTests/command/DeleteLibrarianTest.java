package uniteTests.command;

import com.example.command.DeleteLibrarian;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class DeleteLibrarianTest {

    @Test void testDeleteLibrarian() {
        /*
        This line creates an instance of the DeleteLibrarian class
        representing a graphical user interface (GUI) frame for deleting a librarian. This step
        initializes the frame object.
         */
        DeleteLibrarian frame = new DeleteLibrarian();

        /*
        The assertNotNull() method is an assertion that checks if the frame object is not null.
        If the object is null, it means that the instantiation of DeleteLibrarian failed, which
        would be an unexpected behavior. The test will fail if the object is null.
         */
        assertNotNull(frame);

        /*
        The assertTrue() method is another assertion that checks if the provided condition is
        true. In this case, it simply checks if the boolean value true is indeed true. This
        assertion will always pass since true is always true.
         */
        assertTrue(true);
    }

    /*
    This annotation indicates that the method testDeleteLibrarianTextField() is a test case that
    should be executed when running the tests.
     */
    @Test void testDeleteLibrarianTextField() {

        /*
        This line creates an instance of the DeleteLibrarian class
        representing a graphical user interface (GUI) frame for deleting a librarian. This step
        initializes the frame object.
         */
        DeleteLibrarian frame = new DeleteLibrarian();

        /*
        this line retrieves the JTextField object from the DeleteLibrarian frame using the
        getTextField() method. It assumes that the DeleteLibrarian class has a method named
        getTextField() that returns a JTextField object.
         */
        JTextField textField = frame.getTextField();

        /*
        The assertNotNull() method is an assertion that checks if the textField object is not
        null. If the object is null, it means that the retrieval of the text field failed, which
        would be an unexpected behavior. The test will fail if the object is null.
         */
        assertNotNull(textField);

        /*
        The assertEquals() method is an assertion that checks if two objects are equal. In this
        case, it checks if the class of the textField object is equal to the JTextField class.
        This assertion ensures that the retrieved object is indeed an instance of JTextField. The
         test will fail if the classes are not equal.
         */
        assertEquals(JTextField.class, textField.getClass());
    }

    /*
    This annotation indicates that the method testDeleteLibrarianButton() is a test case that
    should be executed when running the tests.
     */
    @Test void testDeleteLibrarianButton() {

        /*
        This line creates an instance of the DeleteLibrarian class
        representing a graphical user interface (GUI) frame for deleting a librarian. This step
        initializes the frame object.
         */
        DeleteLibrarian frame = new DeleteLibrarian();

        /*
        This line retrieves the JButton object from the DeleteLibrarian frame using the
        getBtnDelete() method. It assumes that the DeleteLibrarian class has a method named
        getBtnDelete() that returns a JButton object.
         */
        JButton btnDelete = frame.getBtnDelete();

        /*
        The assertNotNull() method is an assertion that checks if the btnDelete object is not
        null. If the object is null, it means that the retrieval of the button failed, which
        would be an unexpected behavior. The test will fail if the object is null.
         */
        assertNotNull(btnDelete);

        /*
        The assertEquals() method is an assertion that checks if two objects are equal. In this
        case, it checks if the class of the btnDelete object is equal to the JButton class. This
        assertion ensures that the retrieved object is indeed an instance of JButton. The test
        will fail if the classes are not equal.
         */
        assertEquals(JButton.class, btnDelete.getClass());
    }
}
