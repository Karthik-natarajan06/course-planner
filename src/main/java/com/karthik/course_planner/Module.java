package com.karthik.course_planner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Module 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private double creditWeight;
    private String category;

    public Long getId() 
    { 
        return id; 
    }

    public void setId(Long id) 
    { 
        this.id = id; 
    }

    public String getName() 
    { 
        return name; 
    }

    public void setName(String name) 
    { 
        this.name = name; 
    }

    public double getCreditWeight() 
    { 
        return creditWeight; 
    }

    public void setCreditWeight(double creditWeight) 
    { 
        this.creditWeight = creditWeight; 
    }

    public String getCategory() 
    { 
        return category; 
    }

    public void setCategory(String category) 
    { 
        this.category = category; 
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
