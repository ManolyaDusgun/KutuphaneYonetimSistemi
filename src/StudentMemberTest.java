import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentMemberTest {

    @Test
    void constructorShouldSetAllValuesCorrectly() {
        StudentMember student = new StudentMember(1, "Manolya", "Üniversite A");

        assertEquals(1, student.getId());
        assertEquals("Manolya", student.getName());
        assertEquals("Üniversite A", student.getSchoolName());
    }

    @Test
    void shouldBehaveAsMemberPolymorphically() {
        Member member = new StudentMember(2, "Ahmet", "Üniversite B");

        assertEquals("Ahmet", member.getName());
        assertTrue(member instanceof StudentMember);
    }

    @Test
    void toStringShouldIncludeSchoolName() {
        StudentMember student = new StudentMember(3, "Ayşe", "Üniversite C");

        String result = student.toString();

        assertTrue(result.contains("School: Üniversite C"));
    }
}
