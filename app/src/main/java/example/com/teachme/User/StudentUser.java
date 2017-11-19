package example.com.teachme.User;

import example.com.teachme.model.Student;
import example.com.teachme.model.Teacher;
import example.com.teachme.model.User;

/**
 * Created by MrHacker on 5/19/2017.
 */

public class StudentUser extends UserFactory {
    @Override
    public User createUser()
    {
        return new Student();
    }

}
