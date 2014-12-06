package Model;

/**
 * Model for a class course.
 * 
 * @author Kevin Tan
 * @version 1.0
 * @since 11/16/2014
 */
public class Course 
{
    private int section;
    private String name, instructor;
    
    /**
     * Constructs a course.
     * @param section
     * @param name
     * @param instructor 
     */
    public Course(int section, String name, String instructor) 
    {
        this.section = section;
        this.name = name;
        this.instructor = instructor;
    }

    public int getSection() {
        return section;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
