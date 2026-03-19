/*STRUCTURE

-package
class(
-private student attributes
constructor(->id checker()
	->new transcript)
private id checker()
public id setter()
public getter methods()
public transcript methods()
object overrides()
)

END*/

package to_use;

public class StudentRecord  implements java.io.Serializable {
	private int id;
	private PersonInfo personInfo;
	private Transcript transcript;

	// CONSTRUCTOR
	public StudentRecord(String idInput, PersonInfo personInfo) {
		this.id = IdChecker(idInput);
		if (personInfo == null)
			throw new IllegalArgumentException("Personal info may be missing!");
		this.personInfo = personInfo;
		this.transcript = new Transcript();
	}
	// END

	// ID CHECKER
	private int IdChecker(String idInput) {
		if (idInput == null || idInput.isBlank())
			throw new IllegalArgumentException("Student ID cannot be empty.");
		idInput = idInput.trim();
		if (!idInput.matches("\\d+"))
			throw new IllegalArgumentException("Student ID must contain only digits.");
		int converted = Integer.parseInt(idInput);
		if (converted < 1000000 || converted > 9999999)
			throw new IllegalArgumentException("Student ID must be between 1000000 and 9999999.");
		return converted;
	}
	// END

	// SETTER METHOD FOR ID
	public void setId(String newId) {
		this.id = IdChecker(newId);
	}
	// END

	// GETTER METHODS
	public int getId() {
		return id;
	}

	public String getStudentName() {
		return personInfo.getName();
	}

	public String getStudentAddress() {
		return personInfo.getAddress();
	}

	public Transcript getStudentTranscript() {
		return transcript;
	}

	public String getStudentPhoneString() {
		return personInfo.getPhoneNumbers();
	}
	// END

	// ADD/REMOVE/VIEW TRANSCRIPT
	public void addCourse(Course course) {
		transcript.addCourse(course);
	}

	public void removeCourse(Course course) {
		transcript.removeCourse(course);
	}

	public void viewTranscript() {
		transcript.viewCourses();
	}
	// END

	// OBJECT OVERRIDES
	@Override
	public String toString() {
		return "Student ID: " + id +
				"\nName: " + getStudentName() +
				"\nAddress: " + getStudentAddress() +
				"\n" + getStudentTranscript();
	}

	// EASIEST TO CHECK DUPLICATE STUDENTS BY THEIR ID'S
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		StudentRecord other = (StudentRecord) obj;
		return this.id == other.id;
	}
	// END

}