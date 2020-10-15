package inicio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import inicio.entity.Course;
import inicio.entity.FullTimeEmployee;
import inicio.entity.PartTimeEmployee;
import inicio.entity.Review;
import inicio.entity.Student;
import inicio.repository.CourseRepository;
import inicio.repository.EmployeeRepository;
import inicio.repository.StudentRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository cRepository;
	
	@Autowired
	private StudentRepository sRepository;
	
	@Autowired
	private EmployeeRepository eRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		sRepository.saveStudentWithPassport();
//		cRepository.playWithEntityManager();
//		Course course = repository.findById(1001L);
//		logger.info("Course -> {}",course);
//		repository.save(new Course("Android kipplin"));
//		repository.deleteById(1001L);
//		cRepository.addReviewsForCourse();
		
//		List<Review> reviews = new ArrayList<>();
//		Review review1 = new Review("5","Great");
//		Review review2 = new Review("5","Really good");
//		reviews.add(review1);
//		reviews.add(review2);
//		cRepository.addReviewsForCourse(1003L, reviews);
		
//		sRepository.insertStudentAndCourse();
		
//		sRepository.insertStudentAndCourse(new Student("Chesito"), new Course("Bebidas"));
		
//		eRepository.insert(new PartTimeEmployee("Jill",new BigDecimal("50")));
//		eRepository.insert(new FullTimeEmployee("Jack",new BigDecimal("1000")));
//		logger.info("All employees -> {}",eRepository.retrieveAllEmployees());
//		logger.info("Full employees -> {}",eRepository.retrieveAllFullTimeEmployees());
//		logger.info("Part employees -> {}",eRepository.retrieveAllPartTimeEmployees());
		
		
		
	}

}
