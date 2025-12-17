import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {

    @Test
    void loanConstructorShouldSetInitialValuesCorrectly() {
        Member member = new Member(1, "Manolya");
        Book book = new Book(101, "Java Programming", "James Gosling");
        LocalDate loanDate = LocalDate.of(2025, 1, 1);

        Loan loan = new Loan(member, book, loanDate);

        assertEquals(member, loan.getMember());
        assertEquals(book, loan.getBook());
        assertEquals(loanDate, loan.getLoanDate());
        assertNull(loan.getReturnDate());
        assertFalse(loan.isReturned());
    }

    @Test
    void returnBookShouldSetReturnDateAndMakeBookAvailable() {
        Member member = new Member(2, "Ahmet");
        Book book = new Book(102, "Clean Code", "Robert C. Martin");
        LocalDate loanDate = LocalDate.now();

        Loan loan = new Loan(member, book, loanDate);

        // kitap ödünç alındı varsayımı
        book.borrow();
        assertFalse(book.isAvailable());

        LocalDate returnDate = LocalDate.now().plusDays(5);
        loan.returnBook(returnDate);

        assertEquals(returnDate, loan.getReturnDate());
        assertTrue(loan.isReturned());
        assertTrue(book.isAvailable());
    }

    @Test
    void toStringShouldContainLoanInformation() {
        Member member = new Member(3, "Ayşe");
        Book book = new Book(103, "Design Patterns", "GoF");
        LocalDate loanDate = LocalDate.of(2025, 2, 10);

        Loan loan = new Loan(member, book, loanDate);

        String result = loan.toString();

        assertTrue(result.contains("Design Patterns"));
        assertTrue(result.contains("Ayşe"));
        assertTrue(result.contains("Loan Date: " + loanDate));
        assertTrue(result.contains("Not yet"));
    }
}
