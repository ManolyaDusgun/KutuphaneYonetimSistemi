import java.time.LocalDate;

/**
 * Loan sınıfı, bir üyenin ödünç aldığı kitabı temsil eder
 * Ödünç alma ve iade tarihlerini içerir.
 */
public class Loan {
    private Member member;
    private Book book;
    private LocalDate loanDate;
    private LocalDate returnDate;

    /**
     * Ödünç alma oluşturucu.
     * @param member Ödünç alan üye
     * @param book Ödünç alınan kitap
     * @param loanDate Ödünç alma tarihi
     */
    public Loan(Member member, Book book, LocalDate loanDate) {
        this.member = member;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = null;
    }

    public Member getMember() { return member; }
    public Book getBook() { return book; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getReturnDate() { return returnDate; }

    /**
     * Kitabı iade eder ve iade tarihini ayarlar.
     * @param returnDate İade tarihi
     */
    public void returnBook(LocalDate returnDate) {
        this.returnDate = returnDate;
        book.giveBack();
    }

    /**
     * Kitabın iade edilip edilmediğini kontrol eder.
     * @return true iade edilmişse
     */
    public boolean isReturned() {
        return returnDate != null;
    }

    @Override
    public String toString() {
        return "Loan: " + book.getTitle() + " borrowed by " + member.getName() +
                ", Loan Date: " + loanDate +
                ", Returned: " + (isReturned() ? returnDate : "Not yet");
    }
}
