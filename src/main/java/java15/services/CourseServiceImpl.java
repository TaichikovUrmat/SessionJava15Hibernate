package java15.services;

import java15.dao.CourseDao;
import java15.dao.CourseDaoImpl;
import java15.entity.Course;

import java.util.List;

public class CourseServiceImpl  implements CourseService {
   private final CourseDao courseDao = new CourseDaoImpl();

    @Override

    public String saveCourse(Course course) {
        return  courseDao.saveCourse(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseDao.getCourseById(courseId);
    }

    @Override
    public String deleteCourseById(Long courseId) {
        return courseDao.deleteCourseById(courseId);
    }

    @Override
    public String updateCourse(Long courseId, Course course) {
        return courseDao.updateCourse(courseId, course);
    }

    @Override
    public List<Course> getCoursesByPrice() {
        return courseDao.getCoursesByPrice();
    }
}
