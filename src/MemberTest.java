import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {

    @Test
    void constructorShouldSetIdAndNameCorrectly() {
        Member member = new Member(1, "Manolya");

        assertEquals(1, member.getId(), "ID doğru atanmalı");
        assertEquals("Manolya", member.getName(), "İsim doğru atanmalı");
    }

    @Test
    void toStringShouldReturnCorrectFormat() {
        Member member = new Member(2, "Ahmet");

        String expected = "Member ID: 2, Name: Ahmet";

        assertEquals(expected, member.toString());
    }
}
