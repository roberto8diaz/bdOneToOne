package inicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import inicio.entity.Course;
import inicio.entity.Review;
import inicio.repository.CourseRepository;
import inicio.repository.CourseSpringDataRepository;

@SpringBootTest(classes=Application.class)
class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseSpringDataRepository repository;
	
	@Test
	public void findById_CoursePresent() {
		Optional<Course> courseOptional = repository.findById(1001L);
		logger.info(" present {}",courseOptional.isPresent());
	}
	
	@Test
	public void findById_CourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(10001L);
		logger.info("not present {}",courseOptional.isPresent());
	}
	
	@Test
	public void playingAroundWithSpringDataRepository() {
//		Course course = new Course("nuevo curso SPRING REPOSITORY");
//		repository.save(course);
//		
//		course.setName("nuevo curso SPRING REPOSITORY actualizado");
//		repository.save(course);
//		logger.info("not present {}",courseOptional.isPresent());
		logger.info("Courses -> {} ",repository.findAll());
		logger.info("Courses count -> {} ",repository.count());
	}
	
	@Test
	public void sort() {
//		Course course = new Course("nuevo curso SPRING REPOSITORY");
//		repository.save(course);
//		
//		course.setName("nuevo curso SPRING REPOSITORY actualizado");
//		repository.save(course);
//		logger.info("not present {}",courseOptional.isPresent());
		logger.info("Courses sorted -> {} ",repository.findAll(Sort.by(Sort.Direction.ASC, "name")));
		logger.info("Courses count -> {} ",repository.count());
	}
	
	@Test
	public void pagination() {
//		Course course = new Course("nuevo curso SPRING REPOSITORY");
//		repository.save(course);
//		
//		course.setName("nuevo curso SPRING REPOSITORY actualizado");
//		repository.save(course);
//		logger.info("not present {}",courseOptional.isPresent());
		Page<Course> firstPage = repository.findAll(PageRequest.of(0,3)); 
		logger.info("Courses pagination 1 -> {} ",firstPage.getContent());
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("Courses pagination 2 -> {} ",secondPage.getContent());
		
	}
	
	@Test
	public void findUsingNamet() {
		logger.info("Findbyname -> {} ",repository.findByName("ionic"));
	}
	
}
