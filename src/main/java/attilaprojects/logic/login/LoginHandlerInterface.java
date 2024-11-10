package attilaprojects.logic.login;

public interface LoginHandlerInterface {
    /*** 'loginAsStudent' method is called when 'Log in as Student'
     *   button is clicked on the 'loginScene'.
     *   First it needs to check if the inputted credentials match an existing
     *   Student object's studentName and studentPassword.
     *      If it does, the login is successful.
     *      If it doesn't, the login will fail. ***/
    public boolean loginAsStudent(String studentName, String studentPassword);

    /*** 'loginAsTeacher' method is called when 'Log in as Teacher'
     *   button is clicked on the 'loginScene'.
     *   First it needs to check if the inputted credentials match an existing
     *   Teacher object's teacherName and teacherPassword.
     *      If it does, the login is successful.
     *      If it doesn't, the login will fail. ***/
    public boolean loginAsTeacher(String teacherName, String teacherPassword);
}
