package app.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import app.src.Student;

public class testStudent {

    @Test
    public void testContactPublicSafety() {
        Student student = new Student();
        String result = student.contactPublicSafety();
        assertEquals("Public safety contacted successfully", result); // Expected real value
    }
}
