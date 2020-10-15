package inicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import inicio.entity.Course;

@RepositoryRestResource(path="courses")
public interface CourseSpringDataRepository extends JpaRepository<Course,Long>{
	List<Course> findByName(String name);
	List<Course> findByNameAndId(String name,Long id);
	int countByName(String name);
	List<Course> findByNameOrderByIdDesc(String name);
	boolean deleteByName(String name);
	
	
	@Query("Select c From Course c where name like '%JOKE%'")
	List<Course> courseWithJokeInName();
	
	@Query(value="Select * From Course c where name like '%JOKE%'",
			nativeQuery=true)
	List<Course> courseWithJokeInNameUsingNativeQuery();
	
	@Query(name="query_get_joke")
	List<Course> courseWithJokeInNameUsingNamedQuery();
}
