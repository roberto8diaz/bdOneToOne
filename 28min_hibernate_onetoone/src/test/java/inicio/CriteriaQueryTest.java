package inicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
class CriteriaQueryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void jpql_Basic() {
		
//		"Select c From Course c"
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultlist = query.getResultList();
		logger.info("Select c From Course c -> {}",resultlist);
	}
	
	@Test
	public void all_courses_having_JOKE() {
		
//		"Select c From Course c where name = JOKE"
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		Predicate like = cb.like(courseRoot.get("name"),"%JOKE%");
		cq.where(like);
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultlist = query.getResultList();
		logger.info("Select c From Course c where name like JOKE -> {}",resultlist);
	}
	
	@Test
	public void all_courses_without_students() {
		
//		"Select c From Course c where name = JOKE"
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
		cq.where(studentsIsEmpty);
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultlist = query.getResultList();
		logger.info("Select c From Course c where student is empty -> {}",resultlist);
	}
	
	@Test
	public void join_students() {
		
//		"Select c From Course c where name = JOKE"
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		Join<Object,Object> join = courseRoot.join("students");
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultlist = query.getResultList();
		logger.info("JOIN -> {}",resultlist);
	}
	
	@Test
	public void LEFT_join_students() {
		
//		"Select c From Course c where name = JOKE"
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		Join<Object,Object> join = courseRoot.join("students",JoinType.LEFT);
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultlist = query.getResultList();
		logger.info("JOIN -> {}",resultlist);
	}
	
}
