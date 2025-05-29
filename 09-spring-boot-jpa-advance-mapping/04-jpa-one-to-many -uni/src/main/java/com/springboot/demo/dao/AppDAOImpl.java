package com.springboot.demo.dao;

import com.springboot.demo.entity.Course;
import com.springboot.demo.entity.Instructor;
import com.springboot.demo.entity.InstructorDetail;
import com.springboot.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injections
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve the Instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        //get the course
        List<Course> courses = tempInstructor.getCourses();

        //break association of all courses for the instructor
        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null);
        }
        // delete the Instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        //retrieve the Instructor
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference, break bi-directional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the Instructor
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCourseByInstructorId(int theId) {
        // create the query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );
        //set the actual parameter value
        query.setParameter("data", theId);

        // execute the query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByJoinFetch(int theId) {
        // create the query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        + "JOIN FETCH i.courses "
                        + "JOIN FETCH i.instructorDetail "
                        + "where i.id = :data", Instructor.class);

        query.setParameter("data", theId);


        // execute the query
        Instructor instructor = query.getSingleResult();
        return instructor;

    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {
        entityManager.merge(theInstructor);
    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        //retrieve the Course
        Course tempCourse = entityManager.find(Course.class, theId);

        // delete the Course
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.reviews "
                        + "where c.id = :data", Course.class);

        query.setParameter("data", theId);

        // execute the query
        Course course = query.getSingleResult();
        return course;

    }

    @Override
    public Course findCourseAndStudentByCourseId(int theId) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data", Course.class);

        query.setParameter("data", theId);

        // execute the query
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentAndCourseByCourseId(int theId) {
        //create query
        TypedQuery<Student> query = entityManager.createQuery(
                "select c from Student c "
                        + "JOIN FETCH c.courses "
                        + "where c.id = :data", Student.class);

        query.setParameter("data", theId);

        // execute the query
        Student student = query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);

    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        //retrieve the student
        Student tempStudent = entityManager.find(Student.class, theId);

        if (tempStudent != null) {
            //get the courses
            List<Course> courses = tempStudent.getCourses();
            //break association of all course for the student
            for (Course tempCourse : courses) {
                tempCourse.getStudents().remove(tempStudent);
            }
            //Now delete the Student
            entityManager.remove(tempStudent);
        }

    }
}
