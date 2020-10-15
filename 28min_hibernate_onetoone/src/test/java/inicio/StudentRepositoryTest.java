package inicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import inicio.entity.Address;
import inicio.entity.Passport;
import inicio.entity.Student;
import inicio.repository.CourseRepository;
import inicio.repository.StudentRepository;

@SpringBootTest(classes=Application.class)
class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
	//Session Session Factory
	//EntityManager PersistanceContext
	//Transaction
	
//	@Test
//	@Transactional
//	public void someTest() {
//		Student student = em.find(Student.class,2003L);
//		Passport passport = student.getPassport();
//		
//		passport.setName("E111111");
//		student.setName("chacho - updated");
//	}
	public void someTest() {
		repository.someDummyOperation();
	}
	
//	@Test
//	@Transactional
//	public void someTest() {
//		Student student = em.find(Student.class,2003L);
//		Passport passport = student.getPassport();
//		
//		passport.setName("E111111");
//		student.setName("chacho - updated");
//	}
	
//	@Test
	@Transactional
	public void retrieveStudentAndPassport() {
		Student student = em.find(Student.class,2003L);
		logger.info("student -> {}",student);
		logger.info("passport -> {}",student.getPassport());
	}
	
//	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class,4003L);
		logger.info("passport -> {}",passport);
		logger.info("student -> {}",passport.getStudent());
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 2001L);
		logger.info("passport -> {}",student);
		logger.info("aqui courses -> {}",student.getCourses());
		
	}
	
	@Test
	@Transactional
	public void setAddressDetails() {
		Student student = em.find(Student.class,2001L);
		student.setAddresss(new Address("No 1010","and roma","VW"));
		em.flush();
		logger.info("student -> {}",student);
		logger.info("passport -> {}",student.getPassport());
	}
	
}
