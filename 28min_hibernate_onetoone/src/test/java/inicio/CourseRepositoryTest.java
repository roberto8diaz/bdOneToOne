package inicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import inicio.entity.Course;
import inicio.repository.CourseRepository;

@SpringBootTest(classes=Application.class)
class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Test
	public void findById_Basic() {
		logger.info("Test is running");
		Course course = repository.findById(1001L);
		assertEquals("JPA IN JOKE",course.getName());
	}

	@Test
	@DirtiesContext
	public void deleteById_Basic() {
		repository.deleteById(1001L);
		assertNull(repository.findById(1001L));
//		assertEquals("JPA IN JOKE",course.getName());
	}
	
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
	
}
