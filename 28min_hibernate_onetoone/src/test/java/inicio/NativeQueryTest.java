package inicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import inicio.entity.Course;
import inicio.repository.CourseRepository;

@SpringBootTest(classes=Application.class)
class NativeQueryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void native_queries_basic() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE",Course.class);
		List resultlist = query.getResultList();
		logger.info("SELECT * FROM COURSE -> {}",resultlist);
	}
	
	@Test
	public void native_queries_basic_param() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = ?",Course.class);
		query.setParameter(1, 1002L);
		List resultlist = query.getResultList();
		logger.info("SELECT * FROM COURSE where id = 1002 -> {}",resultlist);
	}
	
	@Test
	public void native_queries_basic_named_param() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id",Course.class);
		query.setParameter("id", 1002L);
		List resultlist = query.getResultList();
		logger.info("SELECT * FROM COURSE where id = :id -> {}",resultlist);
	}
	
	@Test
	@Transactional
	public void native_queries_basic_update() {
		Query query = em.createNativeQuery("Update COURSE set last_updated_date=sysdate()",Course.class);
		int noOfRowsAffected = query.executeUpdate();
		logger.info("Update COURSE set lastupdated_date=sysdate() -> {}",noOfRowsAffected);
	}
	
}
