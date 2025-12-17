import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {

    private Book book;

    // Her testten önce çalışır
    @BeforeEach
    void setUp() {
        book = new Book(1, "1984", "George Orwell");
    }

    @Test
    void testBookIsAvailableWhenCreated() {
        assertTrue(book.isAvailable());
    }

    @Test
    void testBorrowBook() {
        book.borrow();
        assertFalse(book.isAvailable());
    }

    @Test
    void testGiveBackBook() {
        book.borrow();
        book.giveBack();
        assertTrue(book.isAvailable());
    }

    @Test
    void testBookDetails() {
        assertEquals(1, book.getId());
        assertEquals("1984", book.getTitle());
        assertEquals("George Orwell", book.getAuthor());
    }
}
