package example.com.teachme.User;

import example.com.teachme.model.Student;
import example.com.teachme.model.Teacher;
import example.com.teachme.model.User;

/**
 * Created by MrHacker on 5/19/2017.
 */

public abstract class UserFactory {
    private static final StudentUser student = new StudentUser();
    private static final TeacherUser teacher = new TeacherUser();

    public static UserFactory getFactory(String type) {
        UserFactory user = null ;
        if (type == null) {
            return null;
        } else if (type.equalsIgnoreCase("Student")) {
            user = student ;
        } else if (type.equalsIgnoreCase("Teacher")) {
            user =  teacher;
        }
        return user;
    }

    public abstract User createUser();

}
