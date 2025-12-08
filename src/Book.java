/**
 * Book sınıfı, kütüphanedeki bir kitabı temsil eder.
 * Kitabın ID, başlık, yazar ve mevcut durumu (ödünç alınabilir/müsait) tutulur.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;

    /**
     * Kitap oluşturucu.
     * @param id Kitap ID'si
     * @param title Kitap başlığı
     * @param author Kitap yazarı
     */
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }

    /**
     * Kitap ödünç alındığında durumunu false yapar.
     */
    public void borrow() { available = false; }

    /**
     * Kitap iade edildiğinde durumunu true yapar.
     */
    public void giveBack() { available = true; }

    @Override
    public String toString() {
        return id + ". " + title + " by " + author + ", Available: " + available;
    }
}
