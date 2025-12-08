import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LibraryManager library = new LibraryManager();
        Scanner sc = new Scanner(System.in);

        // ----------------- ÖNCE VERİLERİ YÜKLE -----------------
        library.loadBooks();
        library.loadMembers();

        // Basit kullanıcı adı / şifre
        final String USERNAME = "admin";
        final String PASSWORD = "1234";

        // Giriş sistemi
        boolean loggedIn = false;
        for (int i = 0; i < 3; i++) { // 3 deneme hakkı
            System.out.print("Username: ");
            String user = sc.nextLine();
            System.out.print("Password: ");
            String pass = sc.nextLine();

            if (USERNAME.equals(user) && PASSWORD.equals(pass)) {
                loggedIn = true;
                System.out.println("\nLogin successful!\n");
                break;
            } else {
                System.out.println("Incorrect username or password. Try again.\n");
            }
        }

        if (!loggedIn) {
            System.out.println("Too many failed attempts. Exiting...");
            return;
        }

        int choice;
        do {
            // Menü
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Loans");
            System.out.println("6. Search Books");
            System.out.println("7. List Books & Members");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Book ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(id, title, author));
                    System.out.println("Book added successfully!");
                    break;
                case 2:
                    System.out.print("Member ID: ");
                    int mid = Integer.parseInt(sc.nextLine());
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Is student? (y/n): ");
                    String ans = sc.nextLine();
                    if (ans.equalsIgnoreCase("y")) {
                        System.out.print("School: ");
                        String school = sc.nextLine();
                        library.addMember(new StudentMember(mid, name, school));
                    } else {
                        library.addMember(new Member(mid, name));
                    }
                    System.out.println("Member added successfully!");
                    break;
                case 3:
                    System.out.print("Book ID to borrow: ");
                    int bid = Integer.parseInt(sc.nextLine());
                    System.out.print("Member ID: ");
                    int bmid = Integer.parseInt(sc.nextLine());
                    System.out.println(library.borrowBook(bid, bmid));
                    break;
                case 4:
                    System.out.print("Book ID to return: ");
                    int rid = Integer.parseInt(sc.nextLine());
                    System.out.print("Member ID: ");
                    int rmid = Integer.parseInt(sc.nextLine());
                    System.out.println(library.returnBook(rid, rmid));
                    break;
                case 5:
                    System.out.println("\n--- Current Loans ---");
                    for (Loan loan : library.getLoans()) {
                        System.out.println(loan);
                    }
                    break;
                case 6:
                    System.out.print("Search by title: ");
                    String search = sc.nextLine();
                    List<Book> results = library.searchBooksByTitle(search);
                    for (Book b : results) {
                        System.out.println(b);
                    }
                    break;
                case 7:
                    library.listBooks();
                    library.listMembers();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        } while (choice != 8);
    }
}
