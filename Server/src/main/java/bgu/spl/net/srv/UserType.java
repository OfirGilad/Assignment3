package bgu.spl.net.srv;

import java.util.TreeMap;

public abstract class UserType {

    protected final String username;
    protected final String password;
    protected boolean isLoggedIn = false;

    public UserType(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn() {
        isLoggedIn = true;
    }

    public void setLoggedOut() {
        isLoggedIn = false;
    }
}

class Student extends UserType {
    private final TreeMap<Integer, Course> courses;

    public Student(String username, String password) {
        super(username, password);
        courses = new TreeMap<>();
    }

    public TreeMap<Integer, Course> getCourses() {
        return courses;
    }

    public String registerToCourse(Course course) {
        if (!courses.containsValue(course)) {
            if (!course.isFull()) {
                if (!course.isEligible(courses)) {
                    course.registerStudent(this);
                    courses.put(course.getCourseNum(), course);
                    return "User registered successfully";
                }
                else {
                    return "User isn't meet the Kdam curses requirement";
                }
            }
            else {
                return "Course is full";
            }
        }
        else {
            return "User is already registered";
        }
    }

    public String unregisterToCourse(Course course) {
        if (courses.containsValue(course)) {
            course.unregisterStudent(this);
            courses.remove(course.getCourseNum(), course);
            return "User unregistered successfully";
        }
        else {
            return "User is already unregistered";
        }
    }

    public boolean isRegisteredToCourse(int courseNumber) {
        return courses.containsKey(courseNumber);
    }
}

class Admin extends UserType {

    public Admin(String username, String password) {
        super(username, password);
    }
}
