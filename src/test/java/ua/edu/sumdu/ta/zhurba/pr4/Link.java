package ua.edu.sumdu.ta.zhurba.pr4;

public class Link {
    Link next;
    Task task;

    public Link (Task task){
        next = null;
        this.task = task;
    }

    public Task getTask(){
        return task;
    }

    public void setTask (Task task){
        this.task = task;
    }

    public Link getNext(){
        return next;
    }

    public void setNext(Link next){
        this.next = next;
    }
}