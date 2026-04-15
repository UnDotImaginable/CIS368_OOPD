/*STRUCTURE

-package
-collections imports
class(
-private course array
contructor()
public add_remove_view()
public getter methods()
object overrides()
)

END*/

package to_use;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transcript implements java.io.Serializable {
	private ArrayList<Course> courses;

	// CONSTRUCTOR, INITIALIZED FIRST AND IS EMTPY WHEN STRUDENT RECORD IS MADE.
	// COURSES ADDED OVER TIME
	public Transcript() {
		courses = new ArrayList<>();
	}
	// END

	// ADD, REMOVE, VIEW METHODS
	public void addCourse(Course course) {
		if (course == null)
			throw new IllegalArgumentException("Cannot add null course.");
		courses.add(course);
	}

    public void addCourse(String name, String dep, String courseNum, String numGrade) {
		courses.add(new Course(name, dep, courseNum, numGrade));

    }

	public boolean removeCourse(Course course) {
		if (course == null)
			throw new IllegalArgumentException("Cannot remove null course.");
		return courses.remove(course);
	}

	public String viewCourses() {
		if (courses.isEmpty()) {
			// System.out.println("Transcript is empty.");
			return "Transcript is empty.";
		}
		StringBuilder classList = new StringBuilder("Transcript:\n");
		for (Course course : courses) {
			// System.out.println(course);
			classList.append(course).append("\n");
		}
		return classList.toString();
	}
	// END

	// GETTER METHODS
	public List<Course> getCourses() {
		return Collections.unmodifiableList(courses);
	}

	public int size() {
		return courses.size();
	}
	// END

	// OBJECT OVERRIDES
	public String toString() {
		return viewCourses();
	}
	// END
}