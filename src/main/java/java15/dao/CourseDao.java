package java15.dao;

import java15.entity.Course;

import java.util.List;

public interface CourseDao {

    // TODO  save  add

    String saveCourse(Course course);

    // TODO get All
    List<Course> getAllCourses();

    // Todo getBtId
    Course getCourseById(Long courseId);

    // TODO  delete

    String deleteCourseById(Long courseId);

    // TODO  update
    String updateCourse(Long courseId ,Course course);
    // TODO sort BY Price
    List<Course> getCoursesByPrice(Long price);

}