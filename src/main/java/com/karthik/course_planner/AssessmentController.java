package com.karthik.course_planner;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public List<AssessmentDto> getAssessmentsForModule(@PathVariable Long moduleId, @RequestHeader("X-User-Id") Long userId) {
        Module module = moduleRepository.findById(moduleId).orElseThrow();
        if (!module.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized to view this module's assessments");
        }
        List<Assessment> assessments = assessmentRepository.findByModuleId(moduleId);
        return assessments.stream().map(AssessmentDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public AssessmentDto createAssessment(@PathVariable Long moduleId, @RequestHeader("X-User-Id") Long userId, @RequestBody Assessment assessment) {
        Module module = moduleRepository.findById(moduleId).orElseThrow();
        if (!module.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Not authorized to add assessment to this module");
        }
        assessment.setModule(module);
        Assessment saved = assessmentRepository.save(assessment);
        return new AssessmentDto(saved);
    }

}