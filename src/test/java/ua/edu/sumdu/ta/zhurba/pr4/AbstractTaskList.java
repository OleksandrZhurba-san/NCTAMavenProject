package ua.edu.sumdu.ta.zhurba.pr4;

public abstract class AbstractTaskList {
    protected  int numberElements = 0;
    public abstract void add(Task task);
    public abstract void remove(Task task);
    // abstract int size();
    public int size(){
        return numberElements;
    }
    public abstract Task getTask(int index);

}
