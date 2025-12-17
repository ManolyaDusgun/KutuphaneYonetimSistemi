import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryManagerTest {

    private LibraryManager manager;

    @BeforeEach
    void setUp() {
        manager = new LibraryManager();
    }

    @Test
    void addBookShouldAddBookSuccessfully() {
        Book book = new Book(1, "Java", "James");

        manager.addBook(book);

        Book found = manager.getBookById(1);
        assertNotNull(found);
        assertEquals("Java", found.getTitle());
    }

    @Test
    void addMemberShouldAddMemberSuccessfully() {
        Member member = new Member(1, "Ali");

        manager.addMember(member);

        Member found = manager.getMemberById(1);
        assertNotNull(found);
        assertEquals("Ali", found.getName());
    }

    @Test
    void searchBooksByTitleShouldReturnMatchingBooks() {
        manager.addBook(new Book(1, "Java Programming", "A"));
        manager.addBook(new Book(2, "Python Basics", "B"));

        List<Book> result = manager.searchBooksByTitle("java");

        assertEquals(1, result.size());
        assertEquals("Java Programming", result.get(0).getTitle());
    }

    @Test
    void borrowBookShouldWorkIfBookAndMemberExist() {
        Book book = new Book(1, "Clean Code", "Martin");
        Member member = new Member(1, "Ayşe");

        manager.addBook(book);
        manager.addMember(member);

        String message = manager.borrowBook(1, 1);

        assertEquals("Book borrowed successfully!", message);
        assertFalse(book.isAvailable());
        assertEquals(1, manager.getLoans().size());
    }

    @Test
    void borrowBookShouldFailIfBookNotFound() {
        Member member = new Member(1, "Ayşe");
        manager.addMember(member);

        String message = manager.borrowBook(99, 1);

        assertEquals("Book not found!", message);
    }

    @Test
    void borrowBookShouldFailIfMemberNotFound() {
        Book book = new Book(1, "Java", "James");
        manager.addBook(book);

        String message = manager.borrowBook(1, 99);

        assertEquals("Member not found!", message);
    }

    @Test
    void returnBookShouldWorkCorrectly() {
        Book book = new Book(1, "Design Patterns", "GoF");
        Member member = new Member(1, "Mehmet");

        manager.addBook(book);
        manager.addMember(member);
        manager.borrowBook(1, 1);

        String message = manager.returnBook(1, 1);

        assertEquals("Book returned successfully!", message);
        assertTrue(book.isAvailable());
    }

    @Test
    void returnBookShouldFailIfLoanNotFound() {
        String message = manager.returnBook(1, 1);

        assertEquals("Loan record not found!", message);
    }

    @Test
    void getLoansShouldReturnLoanList() {
        Book book = new Book(1, "Java", "James");
        Member member = new Member(1, "Zeynep");

        manager.addBook(book);
        manager.addMember(member);
        manager.borrowBook(1, 1);

        List<Loan> loans = manager.getLoans();

        assertEquals(1, loans.size());
        assertEquals(book, loans.get(0).getBook());
        assertEquals(member, loans.get(0).getMember());
    }
}
