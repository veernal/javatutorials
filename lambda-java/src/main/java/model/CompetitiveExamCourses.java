package model;

import java.time.Duration;

public class CompetitiveExamCourses {

    private Integer course_id;
    private String course_name;
    private String course_teacher;
    private Duration course_duration;
    private Integer total_fees;

    public CompetitiveExamCourses(Integer course_id, String course_name, String course_teacher, Duration course_duration, Integer total_fees) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_teacher = course_teacher;
        this.course_duration = course_duration;
        this.total_fees = total_fees;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setCourse_teacher(String course_teacher) {
        this.course_teacher = course_teacher;
    }

    public void setCourse_duration(Duration course_duration) {
        this.course_duration = course_duration;
    }

    public void setTotal_fees(Integer total_fees) {
        this.total_fees = total_fees;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getCourse_teacher() {
        return course_teacher;
    }

    public Duration getCourse_duration() {
        return course_duration;
    }

    public Integer getTotal_fees() {
        return total_fees;
    }
}
