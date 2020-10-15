package inicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import inicio.entity.Course;
import inicio.entity.Review;
import inicio.repository.CourseRepository;

@SpringBootTest(classes=Application.class)
class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	public void findById_Basic() {
		logger.info("Test is running");
		Course course = repository.findById(1001L);
		assertEquals("JPA IN JOKE",course.getName());
	}

//	@Test
//	@DirtiesContext
//	public void deleteById_Basic() {
//		repository.deleteById(1001L);
//		assertNull(repository.findById(1001L));
//		assertEquals("JPA IN JOKE",course.getName());
//	}
	
	@Test
	@DirtiesContext
	public void save_Basic() {
		Course course = repository.findById(1001L);
		assertEquals("JPA IN JOKE",course.getName());
//		assertEquals("JPA IN JOKE",course.getName());
		course.setName("JPA IN TWO JOKES");
		
		repository.save(course);
		Course course1 = repository.findById(1001L);
		assertEquals("JPA IN TWO JOKES",course1.getName());
	}
	
	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		repository.playWithEntityManager();
	}
	
	@Test
	@Transactional
	public void retrieveReviewsForCourse() {
		Course course = repository.findById(1001L);
		logger.info("{}",course.getReviews());
	}
	
	@Test
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public void retrieveCourseForReview() {
		
		Review review = em.find(Review.class,5001L);
		logger.info("{}",review.getCourse());
	}
	
	@Test
//	@Transactional
	public void findById_FirstLevelCache() {
		Course course = repository.findById(1001L);
		logger.info("First course RETRIEVED {}",course);
		
		Course course1 = repository.findById(1001L);
		logger.info("First course retrieved again {}",course1);
		
		assertEquals("JPA IN JOKE",course.getName());
		assertEquals("JPA IN JOKE",course1.getName());
	}

	@Test
	@DirtiesContext
	public void deleteById_basic() {
		logger.info("deletebyid");
		repository.deleteById(1001L);
		assertNull(repository.findById(1001L));
	}
	
}
