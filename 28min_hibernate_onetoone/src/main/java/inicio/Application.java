package inicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import inicio.entity.Course;
import inicio.repository.CourseRepository;
import inicio.repository.StudentRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository cRepository;
	
	@Autowired
	private StudentRepository sRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		sRepository.saveStudentWithPassport();
		cRepository.playWithEntityManager();
//		Course course = repository.findById(1001L);
//		logger.info("Course -> {}",course);
//		repository.save(new Course("Android kipplin"));
//		repository.deleteById(1001L);
	}

}
