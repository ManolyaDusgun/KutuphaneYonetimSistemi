import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * LibraryManager sınıfı, kütüphane içerisindeki kitapları, üyeleri ve ödünç işlemlerini yönetmek için kullanılır.
 * Kitap ekleme, üye ekleme, ödünç alma/iade, listeleme, arama ve dosya kaydı yapılabilir.
 */
public class LibraryManager {

    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    private static final String BOOK_FILE = "books.txt";
    private static final String MEMBER_FILE = "members.txt";

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public void addMember(Member member) {
        members.add(member);
        saveMembers();
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    public String borrowBook(int bookId, int memberId) {
        Book book = getBookById(bookId);
        if (book == null) return "Book not found!";
        if (!book.isAvailable()) return "Book is not available!";

        Member member = getMemberById(memberId);
        if (member == null) return "Member not found!";

        book.borrow();
        Loan loan = new Loan(member, book, LocalDate.now());
        loans.add(loan);
        return "Book borrowed successfully!";
    }

    public String returnBook(int bookId, int memberId) {
        for (Loan loan : loans) {
            if (loan.getBook().getId() == bookId &&
                    loan.getMember().getId() == memberId &&
                    !loan.isReturned()) {
                loan.returnBook(LocalDate.now());
                return "Book returned successfully!";
            }
        }
        return "Loan record not found!";
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public Book getBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public Member getMemberById(int id) {
        for (Member m : members) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    public void listBooks() {
        System.out.println("\n--- Books in Library ---");
        for (Book b : books) {
            System.out.println(b.getId() + ". " + b.getTitle() + " by " + b.getAuthor() +
                    ", Available: " + b.isAvailable());
        }
    }

    public void listMembers() {
        System.out.println("\n--- Members ---");
        for (Member m : members) {
            System.out.println(m.getId() + ". " + m.getName());
        }
    }

    // ----------------- DOSYA KAYDI -----------------

    private void saveBooks() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BOOK_FILE))) {
            for (Book b : books) {
                pw.println(b.getId() + ";" + b.getTitle() + ";" + b.getAuthor() + ";" + b.isAvailable());
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    private void saveMembers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(MEMBER_FILE))) {
            for (Member m : members) {
                if (m instanceof StudentMember) {
                    pw.println(m.getId() + ";" + m.getName() + ";student;" + ((StudentMember) m).getSchoolName());
                } else {
                    pw.println(m.getId() + ";" + m.getName() + ";member;");
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving members: " + e.getMessage());
        }
    }

    public void loadBooks() {
        books.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOK_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String author = parts[2];
                boolean available = Boolean.parseBoolean(parts[3]);
                Book book = new Book(id, title, author);
                if (!available) book.borrow();
                books.add(book);
            }
        } catch (IOException e) {
            System.out.println("No books loaded.");
        }
    }

    public void loadMembers() {
        members.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(MEMBER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String type = parts[2];
                if (type.equals("student")) {
                    String school = parts[3];
                    members.add(new StudentMember(id, name, school));
                } else {
                    members.add(new Member(id, name));
                }
            }
        } catch (IOException e) {
            System.out.println("No members loaded.");
        }
    }
}