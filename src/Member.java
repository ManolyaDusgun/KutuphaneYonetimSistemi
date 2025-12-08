/**
 * Member sınıfı, kütüphane üyesini temsil eder.
 * Üyenin ID ve adı tutulur.
 */
public class Member {
    private int id;
    private String name;

    /**
     * Üye oluşturucu.
     * @param id Üye ID'si
     * @param name Üye adı
     */
    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Member ID: " + id + ", Name: " + name;
    }
}
