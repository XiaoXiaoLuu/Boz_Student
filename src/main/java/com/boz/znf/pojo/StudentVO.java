package com.boz.znf.pojo;

import java.time.LocalDateTime;

/**
 * @author ZhangNanFu
 * @date 2021年05月04日 1:04
 */
public class StudentVO {
    private String id;
    private String name;
    private String birthday;
    private String description;
    private String avgScore;

    public StudentVO() {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(String avgScore) {
        this.avgScore = avgScore;
    }

    @Override
    public String toString() {
        return "StudentVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", description='" + description + '\'' +
                ", avgScore='" + avgScore + '\'' +
                '}';
    }
}
