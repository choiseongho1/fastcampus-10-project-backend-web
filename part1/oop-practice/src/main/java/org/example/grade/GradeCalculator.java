package org.example.grade;

import java.util.List;

public class GradeCalculator {
    private final Courses courses;

    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    public GradeCalculator(Courses courses){
        this.courses = courses;
    }

    public double calculateGrade() {
        return courses.multiplyCreditAndCourseGrade() /courses.calculateCompletedCredit();
    }
}
