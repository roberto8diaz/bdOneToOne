package inicio;
import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Subgraph;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import inicio.entity.Course;

@SpringBootTest(classes=Application.class)
class PerformanceTuningTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
//	@Test
//	@Transactional
//	public void creatingNPlusOneProblem() {
//		logger.info("Test is running");
//		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
//		for(Course course:courses) {
//			logger.info("Course -> {} Students -> {}",course,course.getStudents());
//		}
//	}
	
//	insert into course(id,name, last_updated_date,create_date,is_deleted) values(1004,'1004',sysdate(),sysdate(),false);
//	insert into course(id,name, last_updated_date, create_date,is_deleted) values(1005,'1005',sysdate(),sysdate(),false);
//	insert into course(id,name, last_updated_date, create_date,is_deleted) values(1006,'1006',sysdate(),sysdate(),false);
//	insert into course(id,name, last_updated_date,create_date,is_deleted) values(1007,'1007',sysdate(),sysdate(),false);
//	insert into course(id,name, last_updated_date, create_date,is_deleted) values(1008,'1008',sysdate(),sysdate(),false);
//	insert into course(id,name, last_updated_date, create_date,is_deleted) values(1009,'1009',sysdate(),sysdate(),false);
//	insert into course(id,name, last_updated_date,create_date,is_deleted) values(1010,'1010',sysdate(),sysdate(),false);
//	insert into course(id,name, last_updated_date, create_date,is_deleted) values(1011,'1011',sysdate(),sysdate(),false);
//	insert into course(id,name, last_updated_date, create_date,is_deleted) values(1012,'1012',sysdate(),sysdate(),false);
	
	@Test
	@Transactional
	public void solvingNPlusOneProblem() {
		
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> subGraph = entityGraph.addSubgraph("students");
		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
				.setHint("javax.persistence.loadgraph", entityGraph)
				.getResultList();
		System.out.println("coursesList" +courses.toString());
		for(Course course:courses) {
			logger.info(" here Course -> {} Students -> {}",course,course.getStudents());
		}
	}
	
}
