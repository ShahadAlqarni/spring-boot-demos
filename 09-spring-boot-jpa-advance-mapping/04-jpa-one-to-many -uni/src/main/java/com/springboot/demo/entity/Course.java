package com.springboot.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="course")
public class Course {

    // define our fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @ManyToOne(cascade ={ CascadeType.PERSIST, CascadeType.MERGE,
   CascadeType.DETACH, CascadeType.REFRESH } )

    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH } )
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            //inverse mean the other side from table
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    //define constructors
    public Course() {
    }
    public Course(String title) {
        this.title = title;
    }

    //define getter setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Review> getReview() {
        return reviews;
    }

    public void setReview(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    //add a convenience method
    public void  addReview(Review theReview){
        if (reviews==null){
            reviews = new ArrayList<>();
        }
        reviews.add(theReview);
    }

    //add a convenience method to add a single student to the course
    public void  addStudent(Student theStudent){
        if (students==null){
            students = new ArrayList<>();
        }
        students.add(theStudent);
    }

    //define toString
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
