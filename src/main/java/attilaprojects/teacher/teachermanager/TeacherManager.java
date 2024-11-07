package attilaprojects.teacher.teachermanager;

import attilaprojects.teacher.Teacher;

public class TeacherManager implements TeacherManagerInterface{
    @Override
    public boolean registerTeacher(Teacher teacher) {
        return false;
    }

    @Override
    public boolean removeTeacher(Teacher teacher) {
        return false;
    }

    /*** !!!TODO IN ALL MANAGER CLASSES TO NOT LOSE LIST CONTENT
     *          IT MUST SAVE THE LIST'S CONTENT INTO A TXT BEFORE EXITING
     *          IT MUST LOAD THE LIST'S CONTENT FROM TXT ON START!!! ***/
}
