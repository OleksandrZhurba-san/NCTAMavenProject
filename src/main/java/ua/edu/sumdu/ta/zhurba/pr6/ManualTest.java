package ua.edu.sumdu.ta.zhurba.pr6;

public class ManualTest {
    public static void main(String[] args) {
        AbstractTaskList myList = new LinkedTaskList();
        System.out.println(myList);
        Task myTask1 = new Task("Task1",5,10,15);
        Task myTask2 = new Task("Task2",5,10,15);
        myList.add(myTask1);
        myList.add(myTask2);
        System.out.println(myList);
    }
}
