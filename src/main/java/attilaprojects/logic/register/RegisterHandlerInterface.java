package attilaprojects.logic.register;

public interface RegisterHandlerInterface {
    /*** When the 'Register as Student' button is pressed
     * the 'registerStudent' method is called.
     *  The method takes the provided username and password,
     *  and assigns and ID for the user, making it a complete Student object. ***/
    public boolean registerStudent(String username, String password);

    /*** When the 'Register as Teacher' button is pressed
     * the 'registerTeacher' method is called.
     *  The method takes the provided username and password,
     *  and assigns and ID for the user, making it a complete Teacher object. ***/
    public boolean registerTeacher(String username, String password);
}
