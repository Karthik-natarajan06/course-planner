package com.karthik.course_planner;

public class AssessmentDto {

    private Long id;
    private String name;
    private double weight;
    private Double mark;
    private Long moduleId;

    public AssessmentDto(Assessment assessment) {
        this.id = assessment.getId();
        this.name = assessment.getName();
        this.weight = assessment.getWeight();
        this.mark = assessment.getMark();
        this.moduleId = assessment.getModule().getId();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public Double getMark() { return mark; }
    public void setMark(Double mark) { this.mark = mark; }

    public Long getModuleId() { return moduleId; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }

}