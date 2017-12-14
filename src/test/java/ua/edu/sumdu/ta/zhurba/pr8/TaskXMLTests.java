package ua.edu.sumdu.ta.zhurba.pr8;

import org.junit.Test;
//import ua.edu.sumdu.ta.zhurba.pr6.AbstractTaskList;
//import ua.edu.sumdu.ta.zhurba.pr6.ArrayTaskList;
//import ua.edu.sumdu.ta.zhurba.pr6.Task;

public class TaskXMLTests {
    private TaskXMLSerializer xml = new TaskXMLSerializer();
    @Test
    public void loadXML(){
        String path = "src\\test\\resources\\tasks.xml";
        xml.load(path);
    }

    @Test
    public void saveXML(){
        AbstractTaskList tasks = new ArrayTaskList();
        Task[] ts = new Task[5];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Task("Task#"+ i, i);
            tasks.add(ts[i]);
            ts[i].setActive(true);
//            System.out.println(tasks.getTask(i).toString());
        }
        xml.save(tasks,"SavedTasks.xml");
    }
}
