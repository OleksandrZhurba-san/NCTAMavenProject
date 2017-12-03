package ua.edu.sumdu.ta.zhurba.pr4;

public class LinkedTaskList extends AbstractTaskList {

    // private static int counter;
    private Link head;

    public LinkedTaskList(){
        head = null;
        // numberElements = 0;
    }

    public boolean isEmpty(){
        return (head == null);
    }

    @Override
    public void add(Task task){
        // if (task == null) {
        //     System.out.println();
        // }
        if (head == null) {
            head = new Link(task);
            numberElements++;
            return;
        }
        Link tempLink = new Link(task);
        Link currentLink = head;
        while (currentLink.getNext() != null) {
            currentLink = currentLink.getNext();
        }
        currentLink.setNext(tempLink);
        numberElements++;
    }

    @Override
    public void remove(Task task) {
        if (task == null) {
            System.out.println();
        }
        if (head != null) {
            Link currentLink = head;
            if (currentLink.getTask().equals(task)){
                head = currentLink.getNext();
                numberElements--;
                return;
            }
            do {
                Link previousLink = currentLink;
                if (currentLink.getNext() != null) {
                    currentLink = currentLink.getNext();
                }
                if (currentLink.getTask().equals(task)) {
                    previousLink.setNext(currentLink.getNext());
                    numberElements--;
                }
            } while (currentLink.getNext() != null);
        }
    }

    @Override
    public Task getTask(int index) {

        if (index < 0 || index >= this.size()){
            System.out.println();
        }

        Link currentLink = head;
        if (currentLink != null){
            for (int i = 0; i < index; i++){
                if (currentLink.getNext() == null){
                    //throw new ();
                    return null;
                }
                currentLink = currentLink.getNext();
            }
            return currentLink.getTask();
        }
        return null;
    }

    public String toString(){
        String output = "";

        if (head != null){
            Link currentLink = head;
            while (currentLink != null){
                output += currentLink.getTask().toString() + "\n";
                currentLink = currentLink.getNext();
            }
        }
        return output;
    }

}
