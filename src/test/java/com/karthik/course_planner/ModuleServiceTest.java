package com.karthik.course_planner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ModuleServiceTest {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private ModuleService moduleService;

    @Test
    public void testCalculateAverageWithGradedAssessments() {
        Module module = new Module();
        module.setName("Test Module");
        module.setCreditWeight(12);
        module.setCategory("core");
        module = moduleRepository.save(module);

        Assessment a1 = new Assessment();
        a1.setName("Test 1");
        a1.setWeight(30);
        a1.setMark(80.0);
        a1.setModule(module);
        assessmentRepository.save(a1);

        Assessment a2 = new Assessment();
        a2.setName("Test 2");
        a2.setWeight(70);
        a2.setMark(60.0);
        a2.setModule(module);
        assessmentRepository.save(a2);

        double average = moduleService.calculateAverage(module.getId());

        // (80*30 + 60*70) / (30+70) = (2400 + 4200) / 100 = 66
        assertEquals(66.0, average, 0.001);
    }

    @Test
    public void testCalculateAverageIgnoresUngradedAssessments() {
        Module module = new Module();
        module.setName("Test Module 2");
        module.setCreditWeight(12);
        module.setCategory("core");
        module = moduleRepository.save(module);

        Assessment graded = new Assessment();
        graded.setName("Test 1");
        graded.setWeight(50);
        graded.setMark(90.0);
        graded.setModule(module);
        assessmentRepository.save(graded);

        Assessment ungraded = new Assessment();
        ungraded.setName("Test 2 (not marked yet)");
        ungraded.setWeight(50);
        ungraded.setMark(null);
        ungraded.setModule(module);
        assessmentRepository.save(ungraded);

        double average = moduleService.calculateAverage(module.getId());

        // only the graded one counts: 90*50 / 50 = 90
        assertEquals(90.0, average, 0.001);
    }

    @Test
    public void testCalculateAverageWithNoAssessments() {
        Module module = new Module();
        module.setName("Empty Module");
        module.setCreditWeight(12);
        module.setCategory("core");
        module = moduleRepository.save(module);

        double average = moduleService.calculateAverage(module.getId());

        assertEquals(0.0, average, 0.001);
    }

}