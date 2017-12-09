package ua.edu.sumdu.ta.zhurba.pr8;

public class ArrayTaskList extends AbstractTaskList {

    private static final String PREF = "[EDUCTR][TA]";
    public Task[] arr;
    // private int numberElements = 0;

    public ArrayTaskList(){
        arr = new Task[1];
    }

    @Override
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("Incorrect task");
        }
        Task[] arrNext;
        if (numberElements == arr.length){
             arrNext = new Task[arr.length+10];
             for (int i = 0; i < numberElements; i++){
                 arrNext[i] = arr[i];
             }
             arr = arrNext;
        }
        if (task.getTitle().contains(PREF)){
            arr[numberElements] = task;
        } else{
            task.setTitle(PREF + task.getTitle());
            arr[numberElements] = task;
        }
        numberElements++;
    }

    @Override
    public void remove(Task task) throws IllegalArgumentException{
        if (task == null) {
            throw new IllegalArgumentException("Incorrect task");
        }
        for (int i = 0; i < arr.length; i++) {
            int j;
            for (j = 0; j < numberElements; j++) {
                if (arr[j].equals(task)) {
                    break;
                }
            }
            if (j != numberElements) {
                for (int k = j; k < numberElements; k++) {
                    arr[k] = arr[k + 1];
                }
                numberElements--;
            }
        }
    }

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index >= 0 || index < size()) {
            return arr[index];
        }
        // return null;
        throw new IndexOutOfBoundsException();
    }
}