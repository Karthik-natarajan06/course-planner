package com.karthik.course_planner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    public double calculateAverage(Long moduleId) {
        List<Assessment> assessments = assessmentRepository.findByModuleId(moduleId);

        double totalWeightedMarks = 0;
        double totalWeight = 0;

        for (Assessment a : assessments) {
            if (a.getMark() != null) {
                totalWeightedMarks += a.getMark() * a.getWeight();
                totalWeight += a.getWeight();
            }
        }

        if (totalWeight == 0) {
            return 0;
        }

        return totalWeightedMarks / totalWeight;
    }

    public double calculateMarkNeeded(Long moduleID, double target) {
        List<Assessment> assessments = assessmentRepository.findByModuleId(moduleID);

        double totalWeight = 0;
        double earnedPoints = 0;   
        double remainingWeight = 0;

        for (Assessment a : assessments) {
            totalWeight += a.getWeight();
            if (a.getMark() != null){
                earnedPoints += a.getMark() * a.getWeight();
            }
            else{
                remainingWeight += a.getWeight();
            }
        }

        if (remainingWeight == 0) 
            {
            return -1;
        }
        return (target * totalWeight - earnedPoints) / remainingWeight;
    }

}