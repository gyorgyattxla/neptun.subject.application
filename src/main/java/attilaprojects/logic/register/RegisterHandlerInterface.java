package attilaprojects.logic.register;

public interface RegisterHandlerInterface {
    /*** When the 'Confirm Register' button is pressed as 'student view'
     *  the 'registerStudent' method is called.
     *  The method takes the provided username and password,
     *  and assigns and ID for the user, making it a complete Student object. ***/
    public boolean registerStudent(String username, String password);
}
