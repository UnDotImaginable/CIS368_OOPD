/*STRUCTURE

-package
class(
-private course attributes
public constuctor(->setters())
private content checkers()
public setters(->content checkers())
public getters()
object overrides()	
)

END*/

package to_use;

public class Course implements java.io.Serializable{
	private String courseName;
	private String department;
	private int courseNumber;
	private double grade;

	public Course(String courseName, String department, String courseNumber, String grade) {
		setCourseName(courseName);
		setDepartment(department);
		setCourseNumber(courseNumber);
		setGrade(grade);
		}

//CONTENT CHECKERS
	private boolean letterCheck(String s){
		for (int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if (!Character.isLetter(c) && c != '-' && c != '\'')
				return false;
			}
		return true;
		}

	private String capitalize(String s){
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
		}
//END

//SETTER METHODS
	public void setCourseName(String newCourseName){
		if (newCourseName == null || newCourseName.isBlank())
			throw new IllegalArgumentException("Course name is required!");
		String[] words = newCourseName.trim().split("\\s+");
		StringBuilder formatted = new StringBuilder();
		for (String word : words){
			if (!letterCheck(word))
				throw new IllegalArgumentException("Course name may only include letters!");
			formatted.append(capitalize(word)).append(" ");
			}
		this.courseName = formatted.toString().trim();
		}

	public void setDepartment(String newDepartment){
		if (newDepartment == null || newDepartment.length() != 3)
			throw new IllegalArgumentException("Department must be 3 letters!");
		if (!letterCheck(newDepartment))
			throw new IllegalArgumentException("Department may only include letters!");
		this.department = newDepartment.toUpperCase();
		}

	public void setCourseNumber(String newCourseNumber){
		int number;
		try {
			number = Integer.parseInt(newCourseNumber.trim());
			}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("Course number must be an integer between 100 and 999!");
			}
		if (number < 100 || number > 999)
			throw new IllegalArgumentException("Course number must be between 100 and 999!");
		this.courseNumber = number;
		}

	public void setGrade(String newGrade){
		double value;
		try {
			value = Double.parseDouble(newGrade.trim());
			}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("Grade must be a number between 0.0 and 100.0!");
			}
		if (value < 0.0 || value > 100.0)
			throw new IllegalArgumentException("Grade must be between 0.0 and 100.0!");
		this.grade = value;
		}
//END

//GETTER METHODS
	public String getCourseName(){
		return courseName;
		}

	public String getDepartment(){
		return department;
		}

	public int getCourseNumber(){
		return courseNumber;
		}

	public double getGrade(){
		return grade;
		}
//END

// OBJECT OVERRIDES
	@Override
	public String toString(){
		return department + " " + courseNumber + " - " + courseName + " | Grade: " + grade;
		}

	@Override
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return this.courseNumber == other.courseNumber
			&& Double.compare(this.grade, other.grade) == 0
			&& this.department.equals(other.department)
			&& this.courseName.equals(other.courseName);
		}
//END

	}