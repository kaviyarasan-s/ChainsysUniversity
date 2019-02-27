package com.chainsys.intership.chainsysuniversity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chainsys.intership.chainsysuniversity.dao.CourseDAO;
import com.chainsys.intership.chainsysuniversity.dao.CourseEnrollmentDAO;
import com.chainsys.intership.chainsysuniversity.model.Course;
import com.chainsys.intership.chainsysuniversity.model.CourseEnrollment;

@Service
public class Services {
	@Autowired
	CourseDAO courseDAO;
	@Autowired
	CourseEnrollmentDAO courseEnrollmentDAO;

	public String addCourse(Course course) {
		int courseInsertResult = courseDAO.addCourse(course);
		String courseInsertMessage = null;
		if (courseInsertResult > 0)
			courseInsertMessage = "Course added successfully.";
		else
			courseInsertMessage = "Course added failed.";

		return courseInsertMessage;
	}

	public String courseEnrollment(CourseEnrollment courseEnrollment) {
		int courseEnrolledResult = courseEnrollmentDAO
				.courseEnrollment(courseEnrollment);
		String courseEnrolledMessage = null;
		if (courseEnrolledResult > 0)
			courseEnrolledMessage = "Course Enrolled successfully.";
		else
			courseEnrolledMessage = "Course Enrollment failed.";

		return courseEnrolledMessage;
	}

	public List<Course> displayUserCoursesById(CourseEnrollment courseEnrollment) {

		List<Course> courseIdList = null;
		String status = courseEnrollment.getStatus();		
		if (status == null) {

			courseIdList = courseEnrollmentDAO
					.getUserCourseIdWithOutStatus(courseEnrollment);
		} else {

			courseIdList = courseEnrollmentDAO
					.getUserCourseIdWithStatus(courseEnrollment);
		}

		List<Course> courseDetailsList = new ArrayList<Course>();
		for (Course courseDetail : courseIdList) {
			courseDetail = courseDAO.selectCourseDetailsById(courseDetail);
			courseDetailsList.add(courseDetail);

		}

		return courseDetailsList;
	}

	// public List<Course> displayCompletedCoursesById(User user) {
	// List<Course> courseIdList = courseEnrollmentDAO
	// .getCompletedCourseId(user);
	//
	// List<Course> courseDetailsList=new ArrayList<Course>();
	// for (Course courseDetail : courseIdList) {
	// courseDetail= courseDAO
	// .selectCourseDetailsById(courseDetail);
	// courseDetailsList.add(courseDetail);
	//
	// }
	//
	// return courseDetailsList;
	// }

	public String courseComplete(CourseEnrollment courseEnrollment) {
		int courseCompletedResult = courseEnrollmentDAO
				.courseComplete(courseEnrollment);
		String courseCompletedMessage = null;
		if (courseCompletedResult > 0)
			courseCompletedMessage = "Course Completed successfully.";
		else
			courseCompletedMessage = "Course Not completed";

		return courseCompletedMessage;
	}
}
