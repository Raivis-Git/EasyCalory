package com.example.calories.model;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client extends AuditModel {

    //Little to no exercise   Daily kilocalories needed = BMR x 1.2
    private static final Integer NO_EXERCISE = 1;
    //Light exercise (1–3 days per week)  Daily kilocalories needed = BMR x 1.375
    private static final Integer LIGHT_EXERCISE = 2;
    //Moderate exercise (3–5 days per week)   Daily kilocalories needed = BMR x 1.55
    private static final Integer MODERATE_EXERCISE = 3;
    //Heavy exercise (6–7 days per week)  Daily kilocalories needed = BMR x 1.725
    private static final Integer HEAVY_EXERCISE = 4;
    //Very heavy exercise (twice per day, extra heavy workouts)   Daily kilocalories needed = BMR x 1.9
    private static final Integer VERY_HEAVY_EXERCISE = 5;

    private static final String MALE = "male";
    private static final String FEMALE = "female";
    // Some creature
    private static final String MIRACLE = "miracle";

    @Id
    @GeneratedValue(generator = "client_generator")
    @SequenceGenerator(
            name = "client_generator",
            sequenceName = "client_sequence",
            initialValue = 1000
    )
    private Long client_id;
    private String first_name;
    private String last_name;
    private Integer weight;
    private Integer height;
    private Integer age;
    private String sex;
    private Integer activity_level;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getActivity_level() {
        return activity_level;
    }

    public void setActivity_level(Integer activity_level) {
        this.activity_level = activity_level;
    }
}
