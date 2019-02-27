package com.chainsys.intership.chainsysuniversity.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.intership.chainsysuniversity.model.Course;
import com.chainsys.intership.chainsysuniversity.model.CourseEnrollment;

@Repository
public class CourseEnrollmentDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int courseEnrollment(CourseEnrollment courseEnrollment) {

		String query = "INSERT INTO course_enrollment(id,user_id,course_id,start_date,end_date,status) VALUES(course_enrollment_id_seq.nextval,?,?,?,?,?)";
		Object[] parameters = new Object[] {
				courseEnrollment.getUser().getId(),
				courseEnrollment.getCourse().getId(),
				courseEnrollment.getStartDate(), courseEnrollment.getEndDate(),
				courseEnrollment.getStatus() };
		int courseEnrollmentResult = jdbcTemplate.update(query, parameters);
		return courseEnrollmentResult;

	}
	
	public int courseComplete(CourseEnrollment courseEnrollment) {

		String query = "UPDATE course_enrollment set end_date=?,status=? WHERE user_id=?";
		Object[] parameters = new Object[] {
				courseEnrollment.getEndDate(),
				courseEnrollment.getStatus(),
				courseEnrollment.getUser().getId()			
				 };
		int courseCompleteResult = jdbcTemplate.update(query, parameters);
		return courseCompleteResult;

	}

	public List<Course> getUserCourseIdWithStatus(CourseEnrollment courseEnrollment) {

		String query = "SELECT course_id from course_enrollment WHERE user_id=? and status=?";
		
		Object[] parameters = new Object[] { courseEnrollment.getUser().getId(),courseEnrollment.getStatus() };
		List<Course> courseList = jdbcTemplate.query(query, parameters, (
				resultSet, row) -> {
			Course course = courseInitialization(resultSet);
			return course;
		});
		return courseList;

	}
	
	public List<Course> getUserCourseIdWithOutStatus(CourseEnrollment courseEnrollment) {

		String query = "SELECT course_id from course_enrollment WHERE user_id=?";
		
		Object[] parameters = new Object[] { courseEnrollment.getUser().getId() };
		List<Course> courseList = jdbcTemplate.query(query, parameters, (
				resultSet, row) -> {
			Course course = courseInitialization(resultSet);
			return course;
		});
		return courseList;

	}
	
//	public List<Course> getCompletedCourseId(User user) {
//
//		String query = "SELECT course_id from course_enrollment WHERE user_id=? and status=?";
//		
//		Object[] parameters = new Object[] { user.getId(),"Completed" };
//		List<Course> courseList = jdbcTemplate.query(query, parameters, (
//				resultSet, row) -> {
//			Course course = courseInitialization(resultSet);
//			return course;
//		});
//		return courseList;
//
//	}

	public Course courseInitialization(ResultSet resultSet) throws SQLException {
		Course course = new Course();
		course.setId(resultSet.getInt("course_id"));
		return course;
	}

}
