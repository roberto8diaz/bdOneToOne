package inicio.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import inicio.entity.Course;
import inicio.entity.Passport;
import inicio.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public Student findById(long id) {
		return em.find(Student.class, id);
	}
	
	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}
	
	public Student save(Student student) {
		if(student.getId()==null) {
			em.persist(student);
		}else {
			em.merge(student);
		}
		
		return student;
	}
	
	public void playWithEntityManager() {
		Student student1 = new Student("web services");
//		em.persist(course1);
//		Student course2 = new Student("web services updated dos");
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
		em.persist(student1);
		
		Student student2 = findById(1001L);
		student2.setName("cambio registro 1002");
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);
		
		Student student = new Student("Pepe");
		student.setPassport(passport);
		em.persist(student);
		
	}
	
	public void someDummyOperation() {
		Student student = em.find(Student.class,2003L);
		Passport passport = student.getPassport();
		
		passport.setName("E22222");
		student.setName("dummy - updated");
	}
	
	public void insertStudentAndCourse() {
		
		Student student = new Student("chucho");
		Course course = new Course("Madera avanzada");
		em.persist(student);
		em.persist(course);
		
		student.addCourse(course);
		course.addStudent(student);
		
		em.persist(student);
		
		
	}
	
public void insertStudentAndCourse(Student student,Course course) {
		
//		Student student = new Student("chucho");
//		Course course = new Course("Madera avanzada");
//		em.persist(student);
//		em.persist(course);
		
		student.addCourse(course);
		course.addStudent(student);
		
		em.persist(student);
		em.persist(course);
		
		
	}
	
}
