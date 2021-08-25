package services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExamPassingServiceTest {

    private ExamPassingService examPassingService;
    private Object ExamInfo;

    @BeforeEach
    public void setUp()
    {
        ExamPassingService examPassingService = new ExamPassingService(new FileService());
    }

    @Test
    public void testGetCorrectGradeWhenGradeIsNotInteger() {

    }

}