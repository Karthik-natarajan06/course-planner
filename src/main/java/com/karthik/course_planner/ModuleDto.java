package com.karthik.course_planner;

public class ModuleDto {

    private Long id;
    private String name;
    private double creditWeight;
    private String category;
    private UserDto user;

    public ModuleDto(Module module) {
        this.id = module.getId();
        this.name = module.getName();
        this.creditWeight = module.getCreditWeight();
        this.category = module.getCategory();
        this.user = new UserDto(module.getUser());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getCreditWeight() { return creditWeight; }
    public void setCreditWeight(double creditWeight) { this.creditWeight = creditWeight; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public UserDto getUser() { return user; }
    public void setUser(UserDto user) { this.user = user; }

}
