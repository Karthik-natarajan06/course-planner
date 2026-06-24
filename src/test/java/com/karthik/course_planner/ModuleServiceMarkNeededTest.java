package com.karthik.course_planner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ModuleServiceMarkNeededTest {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private ModuleService moduleService;

    @Test
    public void testMarkNeededWithRemainingWeight() {
        Module module = new Module();
        module.setName("Test Module");
        module.setCreditWeight(12);
        module.setCategory("core");
        module = moduleRepository.save(module);

        Assessment graded = new Assessment();
        graded.setName("Test 1");
        graded.setWeight(40);
        graded.setMark(70.0);
        graded.setModule(module);
        assessmentRepository.save(graded);

        Assessment ungraded = new Assessment();
        ungraded.setName("Exam");
        ungraded.setWeight(60);
        ungraded.setMark(null);
        ungraded.setModule(module);
        assessmentRepository.save(ungraded);

        // target 75 overall, total weight 100, earned = 70*40 = 2800
        // (75*100 - 2800) / 60 = (7500 - 2800) / 60 = 4700 / 60 = 78.333...
        double markNeeded = moduleService.calculateMarkNeeded(module.getId(), 75);

        assertEquals(78.333, markNeeded, 0.01);
    }

    @Test
    public void testMarkNeededWithNoRemainingWeight() {
        Module module = new Module();
        module.setName("Fully Graded Module");
        module.setCreditWeight(12);
        module.setCategory("core");
        module = moduleRepository.save(module);

        Assessment graded = new Assessment();
        graded.setName("Only Test");
        graded.setWeight(100);
        graded.setMark(65.0);
        graded.setModule(module);
        assessmentRepository.save(graded);

        double markNeeded = moduleService.calculateMarkNeeded(module.getId(), 75);

        assertEquals(-1, markNeeded, 0.001);
    }

}