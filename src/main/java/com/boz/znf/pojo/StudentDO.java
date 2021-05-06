package com.boz.znf.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author ZhangNanFu
 * @date 2021年05月04日 1:11
 */
public class StudentDO implements Serializable {
    private String id;
    private String name;
    private LocalDate birthday;
    private String description;
    private Integer avgScore;

    public StudentDO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Integer avgScore) {
        this.avgScore = avgScore;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", description='" + description + '\'' +
                ", avgScore=" + avgScore +
                '}';
    }
}
