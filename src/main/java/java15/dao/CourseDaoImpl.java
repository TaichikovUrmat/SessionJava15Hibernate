package java15.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java15.confic.HibernateConfig;
import java15.entity.Course;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();


    @Override
    public String saveCourse(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(course);
            entityManager.getTransaction().commit();
            return "Success ! ";
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        entityManager.close();
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            courses = entityManager.createQuery("select c from Course  c", Course.class).getResultList();
            entityManager.getTransaction().commit();
            return courses;
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        entityManager.close();
        return null;
    }

    @Override
    public Course getCourseById(Long courseId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Course course1 = entityManager.find(Course.class, courseId);
            entityManager.getTransaction().commit();
            return course1;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteCourseById(Long courseId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            entityManager.remove(course);
            entityManager.getTransaction().commit();
            return "Success ! ";
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }

    @Override
    public String updateCourse(Long courseId, Course course) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Course course1 = entityManager.find(Course.class, courseId);
            course1.setCourseName(course.getCourseName());
            course1.setPrice(course.getPrice());
            course1.setStudyFormat(course.getStudyFormat());
            entityManager.getTransaction().commit();
            return "Success ! ";

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "not found " + courseId;
    }

    @Override
    public List<Course> getCoursesByPrice(Long price) {
        List<Course> courses = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            courses = entityManager.createQuery("select c from Course c where c.id = : price", Course.class)
                    .setParameter("price", price).getResultList();


            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();

            }
            throw new RuntimeException();
        }
        return courses;
    }


}
