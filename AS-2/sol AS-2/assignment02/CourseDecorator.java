package assignment02;

import java.util.List;

public class CourseDecorator extends StudentDecorator {
	String courseRubric; // such as "CS", "MATH", "CHEM", "ISE"
	String courseNumber; // such as "350", "520", "480A", "580T"
	String courseTitle;  // such as "Design Patterns"
	int credits;		// such as 3, 4
	String section;		// such as "01", "90", "A52"
	String grade;		// such as "IP", "A", "C+"
	public CourseDecorator(AbstractStudent s, String courseRubricIn, 
			String courseNumberIn, String courseTitleIn, int creditsIn,
			String sectionIn, String gradeIn) {
		super(s);
		courseRubric = courseRubricIn;
		courseNumber = courseNumberIn;
		courseTitle = courseTitleIn;
		credits = creditsIn;
		section = sectionIn;
		grade = gradeIn;
	}
	
	public String getCourses() {
        List<String> coursesList = getCourseList();
        StringBuilder courses = new StringBuilder();
        for (String course : coursesList) {
            courses.append(course).append("\n");
        }
        return courses.toString();
    }

    public List<String> getCourseList() {
        List<String> courseList = delegate.getCourseList();
        courseList.add(this.toString());
        return courseList;
    }

    public void setGrade(String courseRubricIn, String courseNumberIn, String sectionIn, String gradeIn) {
        if (courseRubricIn.equals(courseRubric) && courseNumberIn.equals(courseNumber) &&
                sectionIn.equals(section)) {
            grade = gradeIn;
        } else {
            delegate.setGrade(courseRubricIn, courseNumberIn, sectionIn, gradeIn);
        }
    }

	@Override
	public String toString() {
		return courseRubric + " " + courseNumber + "-" + section + ": "
				+ courseTitle + "(" + credits + "cr), grade=" + grade;
	}
	

}
