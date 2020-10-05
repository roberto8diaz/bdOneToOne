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
	
}
