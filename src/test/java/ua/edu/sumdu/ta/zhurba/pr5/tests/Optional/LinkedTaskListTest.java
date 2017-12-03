package ua.edu.sumdu.ta.zhurba.pr5.tests.Optional;

import org.junit.Before;
import ua.edu.sumdu.ta.zhurba.pr5.*;

public class LinkedTaskListTest extends TaskListTest {
    @Before 
    public void createTaskList() {
        tasks = new LinkedTaskList();
    }
}

