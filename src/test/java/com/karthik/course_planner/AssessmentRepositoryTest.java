package com.karthik.course_planner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AssessmentRepositoryTest {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Test
    public void testAssessmentLinksToCorrectModule() {
        Module module = new Module();
        module.setName("Test Module");
        module.setCreditWeight(12);
        module.setCategory("core");
        module = moduleRepository.save(module);

        Assessment assessment = new Assessment();
        assessment.setName("Test 1");
        assessment.setWeight(30);
        assessment.setModule(module);
        assessmentRepository.save(assessment);

        List<Assessment> results = assessmentRepository.findByModuleId(module.getId());

        assertEquals(1, results.size());
        assertEquals("Test 1", results.get(0).getName());
    }

}
