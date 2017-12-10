package ua.edu.sumdu.ta.zhurba.pr6;

import java.util.Iterator;


public abstract class AbstractTaskList implements Cloneable {
    protected int numberElements = 0;
    protected static final String PREF = "[EDUCTR][TA]";
    public abstract void add(Task task);

    public abstract void remove(Task task);

    // abstract int size();
    public int size() {
        return numberElements;
    }

    public abstract Task getTask(int index);

    public AbstractTaskList clone() {
        try {
            return (AbstractTaskList) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new InternalError();

        }
    }
    @Override
    public String toString(){
        String result = "";
        if (this.getClass() == LinkedTaskList.class){

            result = "LinkedTaskList [";

        }
        if (this.getClass() == ArrayTaskList.class){
            result = "ArrayTaskList [";
        }
        for (int i = 0; i < size();i++){
            result += getTask(i).getTitle().replace(PREF,"");
            if (i < size() - 1){
                result += ", ";
            }
        }
        result += "]";
        return result;
    }
}
