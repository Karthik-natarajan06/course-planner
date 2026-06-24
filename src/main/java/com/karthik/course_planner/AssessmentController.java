package com.karthik.course_planner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modules/{moduleId}/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @GetMapping
    public List<Assessment> getAssessmentsForModule(@PathVariable Long moduleId) {
        return assessmentRepository.findByModuleId(moduleId);
    }

    @PostMapping
    public Assessment createAssessment(@PathVariable Long moduleId, @RequestBody Assessment assessment) {
        Module module = moduleRepository.findById(moduleId).orElseThrow();
        assessment.setModule(module);
        return assessmentRepository.save(assessment);
    }

}