package com.karthik.course_planner;

import jakarta.persistence.*;

@Entity
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //find out why it is Long and not long

    private String name;
    private double weight;
    private Double mark;//Double and not double as mark can be null

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public Double getMark() { return mark; }
    public void setMark(Double mark) { this.mark = mark; }

    public Module getModule() { return module; }
    public void setModule(Module module) { this.module = module; }

}
