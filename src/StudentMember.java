/**
 * StudentMember sınıfı, Member sınıfından türetilmiş bir öğrenciyi temsil eder.
 * Öğrencinin okul adı da tutulur.
 */
public class StudentMember extends Member {
    private String schoolName;

    /**
     * Öğrenci üye oluşturucu.
     * @param id Üye ID'si
     * @param name Üye adı
     * @param schoolName Okul adı
     */
    public StudentMember(int id, String name, String schoolName) {
        super(id, name);
        this.schoolName = schoolName;
    }

    public String getSchoolName() { return schoolName; }

    @Override
    public String toString() {
        return super.toString() + ", School: " + schoolName;
    }
}
