package ua.edu.sumdu.ta.zhurba.pr4.Optional;

import org.junit.Before;
import ua.edu.sumdu.ta.zhurba.pr4.*;

public class LinkedTaskListTest extends TaskListTest {

    @Before
    public void before()
    {
        tasks = new LinkedTaskList();
    }
}
