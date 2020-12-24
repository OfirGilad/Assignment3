package bgu.spl.net.srv;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {

	private final ConcurrentHashMap<String, UserType> users;
	private ConcurrentHashMap<Integer, Course> courses;
	private final ConcurrentHashMap<String, UserType> loggedInUsers;

	private static class SingletonHolder {
		private static final Database getInstance = new Database();
	}

	//to prevent user from creating new Database
	private Database() {
		// TODO: implement
		users = new ConcurrentHashMap<>();
		courses = new ConcurrentHashMap<>();
		loggedInUsers = new ConcurrentHashMap<>();
	}

	/**
	 * Retrieves the single instance of this class.
	 */
	public static Database getInstance() {
		return SingletonHolder.getInstance;
	}
	
	/**
	 * loads the courses from the file path specified
	 * into the Database, returns true if successful.
	 */
	boolean initialize(String coursesFilePath) {
		// TODO: implement
		courses = null;
		return false;
	}

	public String userType(String username) {
		return users.get(username).getClass().toString();
	}

	public boolean checkIfCourseExists(int courseNumber) {
		return courses.containsKey(courseNumber);
	}

	public boolean checkIfStudentExists(String userName) {
		return users.containsKey(userName);
	}

	//Used for: ADMINREG, STUDENTREG
	public boolean registerUser(String username, String password, String userType) {
		if (users.containsKey(username)) {
			//"User is already registered"
			return false;
		}
		else {
			if (userType.equals("Admin"))
				users.put(username,new Admin(username, password));
			else {
				users.put(username,new Student(username, password));
			}
			//"User registered successfully"
			return true;
		}
	}

	//Used for: LOGIN
	public boolean login(String username, String password) {
		if (!users.containsKey(username)) {
			//"User is not registered";
			return false;
		}
		else {
			if (users.get(username).getPassword().equals(password)) {
				//"true if user hasn't logged in yet, else false"
				return !loggedInUsers.containsKey(username);
			}
			else {
				//"User password is incorrect"
				return false;
			}
		}
	}

	//Used for: LOGOUT
	public void logout(String username) {
		if (loggedInUsers.containsKey(username)) {
			loggedInUsers.remove(username, users.get(username));
			//"User logged out successfully"
		}
	}

	//Used for: COURSEREG
	public boolean registerToCourse(String username, int courseNumber) {
		UserType user = users.get(username);
		if (user.getClass() != Admin.class) {
			if (courses.containsKey(courseNumber)) {
				Course course = courses.get(courseNumber);
				return ((Student)user).registerToCourse(course);
			}
			else {
				//"Course number is not valid"
				return false;
			}
		}
		else {
			//"User is Admin"
			return false;
		}
	}

	//Used for: KDAMCHECK
	public String KdamCheck(int courseNumber) {
		if (courses.containsKey(courseNumber)) {
			Course course = courses.get(courseNumber);
			int[] kdamCourses = course.getKdamCoursesList();
			StringBuilder kdamCoursesString = new StringBuilder("[");
			for (int i = 0; i < kdamCourses.length; i ++) {
				kdamCoursesString.append(kdamCourses[i]);
				if (i + 1 != kdamCourses.length) {
					kdamCoursesString.append(",");
				}
			}
			kdamCoursesString.append("]");
			return kdamCoursesString.toString();
		}
		else  {
			return "false";
		}
	}

	//Used for: COURSESTAT
	public String courseStatsSeatsAvailable(int courseNumber) {
		if (courses.containsKey(courseNumber)) {
			Course course = courses.get(courseNumber);
			return course.getCurrentNumberOfStudents() + "/" + course.getNumOfMaxStudent();
		}
		else  {
			return null;
		}
	}

	//Used for: COURSESTAT
	public String courseStatsStudentRegistered(int courseNumber) {
		if (courses.containsKey(courseNumber)) {
			Course course = courses.get(courseNumber);
			Set courseSet = course.getRegisteredStudents().entrySet();
			Iterator courseIterator = courseSet.iterator();
			StringBuilder studentRegistered = new StringBuilder("[");
			while (courseIterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry)courseIterator.next();
				studentRegistered.append(mapEntry.getKey());
				if (courseIterator.hasNext()) {
					studentRegistered.append(",");
				}
			}
			studentRegistered.append("]");
			return studentRegistered.toString();
		}
		else  {
			return null;
		}
	}

	//used for: STUDENTSTAT, MYCOURSES
	public String studentStats(String username) {
		if (users.containsKey(username)) {
			UserType user = users.get(username);
			if (user.getClass() != Admin.class) {
				Set coursesSet = ((Student) user).getCourses().entrySet();
				Iterator coursesIterator = coursesSet.iterator();
				StringBuilder coursesRegistered = new StringBuilder("[");
				while (coursesIterator.hasNext()) {
					Map.Entry mapEntry = (Map.Entry) coursesIterator.next();
					coursesRegistered.append(mapEntry.getKey());
					if (coursesIterator.hasNext()) {
						coursesRegistered.append(",");
					}
				}
				coursesRegistered.append("]");
				return coursesRegistered.toString();
			} else {
				//"User is Admin"
				return "false";
			}
		}
		else  {
			//"User does not exist"
			return "false";
		}
	}

	//used for: ISREGISTERED
	public String isRegistered(String username, int courseNumber) {
		UserType user = users.get(username);
		if (user.getClass() != Admin.class) {
			if (((Student)user).isRegisteredToCourse(courseNumber)) {
				//"Student is registered"
				return "REGISTERED";
			}
			else {
				//"Student is not registered"
				return "UNREGISTERED";
			}
		}
		else {
			//"User is Admin"
			return "UNREGISTERED";
		}
	}

	//used for: UNREGISTER
	public boolean unregisterToCourse(String username, int courseNumber) {
		UserType user = users.get(username);
		if (user.getClass() != Admin.class) {
			if (courses.containsKey(courseNumber)) {
				Course course = courses.get(courseNumber);
				return ((Student) user).unregisterToCourse(course);
			}
			else  {
				//"Course number is not valid"
				return false;
			}
		}
		else {
			//"User is Admin"
			return false;
		}
	}

}