package ua.edu.sumdu.ta.zhurba.pr6;

public class Task implements Cloneable{

//    private static final String PREF = "[EDUCTR][TA]";
    private final static int NOT_REPEATABLE = 0;
    private boolean active = false;
    private String title = null;
    private int time = 0;
    private int start = 0;
    private int end = 0; 
    private int repeat = 0;

    /**
     * <p>
     *     Class construction for not repeatable Task
     * </p>
     * @param title the name of Task
     * @param time start time of Task
     */
    public Task(String title, int time) throws IllegalArgumentException{
        if (title == null || title.equals("")){
            throw new IllegalArgumentException("Please fill title");
        }
        this.title = title;
        if (time < 0){
            throw new IllegalArgumentException("Invalide value of time. Time must be greater or equel to 0");
        } else {
            this.start = time;
            this.end = time;
            this.time = time;
            this.active = false;
            this.repeat = NOT_REPEATABLE;
            
        }
        
    }

  

    /**
     * <p>
     *     Class construction for repeatable Tasks
     * </p>
     * @param title the name of Task
     * @param start start time of Task
     * @param end end time of Task
     * @param repeat the interval of time through which notifications will be sent
     */

    public Task(String title, int start, int end, int repeat) throws IllegalArgumentException{
        if (title == null || title == ""){
            throw new IllegalArgumentException("Please fill title");
        }
        this.title = title;
        if (start < 0 ){
            throw new IllegalArgumentException("Incorrect values of Start:" + start);
        }      
        else if (end <= 0){
            throw new IllegalArgumentException("Incorrect values of End: " + end);
        }
        else if (repeat <= 0){
            throw new IllegalArgumentException("Incorrect values of Repeat:" + repeat);
        }
        else if (end < start){
            throw new IllegalArgumentException("Value of end can't be lower than start");
        }
        else{
            this.time = start;
            this.start = start;
            this.end = end;
            this.repeat = repeat; 
        }
        
        this.active = false;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    /**
     * <p>
     *     Checks Task activity
     * </p>
     * @return true if Task is active, false - inactive
     */
    public boolean isActive(){
        return this.active;
    }
    /**
     * <p>
     *     Setter for Task title
     * </p>
     * @param title - string with Task title
     */
    public void setTitle(String title) throws IllegalArgumentException{
        if (title == null || title.equals("")){
            throw new IllegalArgumentException("Please fill title");
        }
        this.title = title;
    }
    /**
     * <p>
     *     Setter for Task activity
     * </p>
     * @param isActive - Giving activity status of Task
     */
    public void setActive(boolean isActive){
        this.active = isActive;
    }
    /**
     * <p>
     *     Setter for starting time of non repeatable Tasks
     * </p>
     * @param time Giving time when Task will be start. Must be greater than 0
     */
    public void setTime(int time) throws IllegalArgumentException{
        if (time < 0){
            throw new IllegalArgumentException("Invalide value of time. Time must be greater or equel to 0");
        }
        else{
            this.start = time;
            this.end = time;
            this.repeat = NOT_REPEATABLE;
            this.time = time;
        }
    }

    /**
     * <p>
     *     Setter for repeatable Tasks
     * </p>
     * @param start start time of Task
     * @param end end time of Task
     * @param repeat the interval of time through which notifications will be sent
     */
    public void setTime(int start,int end,int repeat) throws IllegalArgumentException{
        if (start < 0 || end < 0 || repeat < 0 || end < start) {
            throw new IllegalArgumentException("Invalide value of time. Time must be greater or equel to 0");
        }
        else {
            this.time = start;
            this.start = start;
            this.end = end;
            this.repeat = repeat; 
        }
    }

    /**
     * <p>
     *     Get title of Task
     * </p>
     * @return string with Task title
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * Get start time of non repeatable Task
     * @return start time of Task
     */
    public int getTime(){
        return this.time;
    }

    /**
     * Get start time of repeatable Task.
     * @return start time of Task
     */
    public int getStartTime(){
        return this.start;
    }

    /**
     * Get end time of repeatable Task. Will return 0 if call in non repeatable Task
     * @return end time of Task;
     */
    public int getEndTime(){
        return this.end;
    }

    /**
     * <p>
     *     Returns the interval through which the notification will be repeated.
     *     thingy is considered non repeatable if 0
     * </p>
     *
     * @return number after what time will be repeated.
     */
    public int getRepeatInterval(){
        return this.repeat;
    }

    /**
     *
     * @return
     */
    public boolean isRepeated(){
        return (repeat > NOT_REPEATABLE);
    }

    /**
     *
     * @param time
     * @return
     */
    public int nextTimeAfter(int time) throws IllegalArgumentException {

        if (time < 0){
            throw new IllegalArgumentException("Invalid value of time. Time must be greater or equel to 0");
        }
        int nextTime = getStartTime();
        boolean isTimeAfterEnd = time >= end;
        boolean outOfBoundary = (time + getRepeatInterval()) > getEndTime();

        if (isTimeAfterEnd || !isActive() || outOfBoundary) {
            return -1;
        } else if (time < getStartTime()) {
            return nextTime;
        } else while (nextTime <= time) {
            nextTime = nextTime + getRepeatInterval();
        }
        return nextTime;
    }

    @Override
    protected Task clone(){
        try {
            return (Task)super.clone();
        }
        catch(CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }

    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;

    //     Task task = (Task) o;

    //     if (active != task.active) return false;
    //     if (time != task.time) return false;
    //     if (start != task.start) return false;
    //     if (end != task.end) return false;
    //     if (repeat != task.repeat) return false;
    //     return title.equals(task.title);
    // }

    // @Override
    // public int hashCode() {
    //     int result = (active ? 1 : 0);
    //     result = 31 * result + title.hashCode();
    //     result = 31 * result + time;
    //     result = 31 * result + start;
    //     result = 31 * result + end;
    //     result = 31 * result + repeat;
    //     return result;
    // }


       @Override
    public boolean equals(Object obj) {
       if (obj == null && !(obj instanceof Task)){
           return false;
       }

       Task task = (Task) obj;
       return this.start == task.getStartTime() &&
            this.end == task.getEndTime() &&
            this.time == task.getTime() &&
            this.active == task.isActive() &&
            this.repeat == task.getRepeat() &&
            this.title.equals(task.getTitle());
    }

   @Override
   public int hashCode(){
       int hash = 0;
       hash = hash + title.hashCode();
       return hash;
   }

    /**
     *
     * @return
     */
    public String toString(){
        if (this.active == false){
            return "Task " + "\"" + getTitle() + "\"" + " is inactive";
        }
        else if(this.active == true && !isRepeated()){
            return "Task " + "\"" + getTitle() + "\"" +" at " + getTime();
        }
        else if(this.active == true && isRepeated()){
            return "Task " + "\"" + getTitle() + "\"" + " from " + getStartTime() + " to " + getEndTime() + " every " + getRepeatInterval() + " seconds";
        } 
        return "";
    }
}