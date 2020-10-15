package inicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import inicio.entity.Course;
import inicio.entity.Student;
import inicio.repository.CourseRepository;

@SpringBootTest(classes=Application.class)
class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void jpql_Basic() {
		List resultlist = em.createQuery("Select c From Course c").getResultList();
		logger.info("Select c From Course c -> {}",resultlist);
	}
	
	@Test
	public void jpql_typed() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class);
		
		List resultlist = query.getResultList();
		
		logger.info("Select c From Course c -> {}",resultlist);
	}
	
	@Test
	public void jpql_where() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c where name like '%none%'", Course.class);
		
		List resultlist = query.getResultList();
		
		logger.info("Select c From Course c ->  where name like '%none%' {}",resultlist);
	}
	
	@Test
	public void namedQuery() {
		Query query = em.createNamedQuery("query_get_all_courses");
		
		List resultlist = query.getResultList();
		
		logger.info("named query Select c From Course c -> {}",resultlist);
	}
	
	@Test
	public void namedQueryNone() {
		Query query = em.createNamedQuery("query_get_none");
		
		List resultlist = query.getResultList();
		
		logger.info("named query none Select c From Course c where name like '%JOKE%' -> {}",resultlist);
	}
	
	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = 
				em.createQuery("Select c From Course c where c.students is empty", 
						Course.class);
		
		List<Course> resultlist = query.getResultList();
		
		logger.info("Results -> {}",resultlist);
	}
	
	@Test
	public void jpql_courses_with_atleast_2_students() {
		TypedQuery<Course> query = 
				em.createQuery("Select c From Course c where size(c.students) >= 2", 
						Course.class);
		
		List<Course> resultlist = query.getResultList();
		
		logger.info("Results >= 2 -> {}",resultlist);
	}
	
	@Test
	public void jpql_courses_ordered_by_students() {
		TypedQuery<Course> query = 
				em.createQuery("Select c From Course c order by size(c.students) desc", 
						Course.class);
		
		List<Course> resultlist = query.getResultList();
		
		logger.info("Ordered >= 2 -> {}",resultlist);
	}
	
	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query = em.createQuery("Select s From Student s where s.passport.number like '%1234%'", Student.class);
		
		List<Student> resultlist = query.getResultList();
		
		logger.info("Select s From Student s where s.passport.number like '%1234%'' {}",resultlist);
	}
	
	@Test
	public void join() {
		Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> resultlist = query.getResultList();
		logger.info("Result list join {}",resultlist.size());
		for(Object[] result:resultlist) {
			logger.info("Course{}  Student{}",result[0],result[1]);
		}
	}
	
	@Test
	public void left_join() {
		Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultlist = query.getResultList();
		logger.info("Result list left join {}",resultlist.size());
		for(Object[] result:resultlist) {
			logger.info("Course{}  Student{}",result[0],result[1]);
		}
	}
	
	@Test
	public void cross_join() {
		Query query = em.createQuery("Select c, s from Course c, Student s");
		List<Object[]> resultlist = query.getResultList();
		logger.info("Result list cross join {}",resultlist.size());
		for(Object[] result:resultlist) {
			logger.info("Course{}  Student{}",result[0],result[1]);
		}
	}
	
}
