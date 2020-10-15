package inicio.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Review {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated()
	private ReviewRating rating;
	
	private String description;
	
	@ManyToOne
	private Course course;
	
	public Review() {
	}
	
	

	public Review(ReviewRating rating,String description) {
		super();
		this.rating = rating;
		this.description = description;
	}

	public ReviewRating getRating() {
		return rating;
	}



	public void setRating(ReviewRating rating) {
		this.rating = rating;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	
	

	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}



	@Override
	public String toString() {
		return "Review [rating= " +rating +" description= " + description + "]";
	}
	
	

}
