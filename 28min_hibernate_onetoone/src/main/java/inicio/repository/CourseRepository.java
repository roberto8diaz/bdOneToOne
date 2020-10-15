package inicio.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import inicio.entity.Course;
import inicio.entity.Review;
import inicio.entity.ReviewRating;

@Repository
@Transactional
public class CourseRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public Course findById(long id) {
		return em.find(Course.class, id);
	}
	
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
	public Course save(Course course) {
		if(course.getId()==null) {
			em.persist(course);
		}else {
			em.merge(course);
		}
		
		return course;
	}
	
	public void playWithEntityManager() {
		Course course1 = new Course("web services");
//		em.persist(course1);
//		Course course2 = new Course("web services updated dos");
//		em.persist(course2);
		
//		em.flush();
		
//		course1.setName("web services myname updated");
//		course2.setName("web services none 3");
//		em.flush();
//		
//		em.refresh(course1);
//		em.refresh(course2);
//		course1.setName(null);
//		em.persist(course1);
//		em.flush();
		em.persist(course1);
		
		Course course2 = findById(1001L);
		course2.setName("cambio registro 1002");
	}

	public void addReviewsForCourse() {
		Course course = findById(1001L);
		logger.info("course.getRewviews -> {}",course.getReviews());
		Review review1 = new Review(ReviewRating.FIVE,"Great");
		Review review2 = new Review(ReviewRating.FIVE,"Really good");
		
		course.addReviews(review1);
		review1.setCourse(course);
		course.addReviews(review2);
		review2.setCourse(course);
		
		em.persist(review1);
		em.persist(review2);
		
	}
	
	public void addReviewsForCourse(Long courseId,List<Review> reviews) {
		Course course = findById(courseId);
		logger.info("course.getRewviews -> {}",course.getReviews());
		
		for(Review review:reviews) {
		course.addReviews(review);
		review.setCourse(course);
		em.persist(review);
		}
	}
	
}
