//Group 16 - 3/19/26

/**
 * StudentArray.java
 * This class wraps an ArrayList to store Graduate and Undergraduate StudentRecord objects.
 */

package to_use;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class StudentArray  implements java.io.Serializable {
    private ArrayList<StudentRecord> studentList = new ArrayList<>();

    public StudentArray()
    {
        
    }

    public StudentArray(StudentRecord... startList)
    {
        for (StudentRecord r : startList) {
            this.addStudent(r);
        }
    }

    //returns true if a StudentRecord is Undergraduate or Graduate
    private boolean checkStudent(StudentRecord studentToCheck)
    {
        if(studentToCheck instanceof StudentRecord && (studentToCheck instanceof Undergraduate || studentToCheck instanceof Graduate))
        {
            return true;
        }
        return false; 
    }

    public void addStudent(StudentRecord studentToAdd)
    {
        if(!checkStudent(studentToAdd))
        {
            throw new InvalidParameterException("Cannot add an object that is not Graduate or Undergraduate");
        }

        studentList.add(studentToAdd);
    }

    //returns the first index with a matching ID, -1 otherwise 
    public int getIndexByStudentId(int findId)
    {
        for (StudentRecord t : studentList) {
            if(t.getId() == findId)
            {
                return t.getId();
            }
        }
        
        return -1;
    }

    //returns the StudentRecord object with a matching ID
    public StudentRecord getStudentRecordByStudentId(int findId)
    {
        for (StudentRecord t : studentList) {
            if(t.getId() == findId)
            {
                return t;
            }
        }
        
        return null;
    }

    //returns removal index if the element has been found + removed, else -1
    public int removeStudentById(int rmvId)
    {
        int rmvIdx = getIndexByStudentId(rmvId);
        if(rmvIdx > -1)
        {
            studentList.remove(rmvIdx);
            return rmvIdx;
        }
        else
        {
            return -1;
        }
    }

    public void setStudentList(ArrayList<StudentRecord> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<StudentRecord> getStudentList() {
        return studentList;
    }
}
