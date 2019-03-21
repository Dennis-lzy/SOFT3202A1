package au.edu.sydney.brawndo.erp.todo;

import java.time.LocalDateTime;

import static au.edu.sydney.brawndo.erp.todo.Task.Field.DESCRIPTION;
import static au.edu.sydney.brawndo.erp.todo.Task.Field.LOCATION;

public class TaskImpl implements Task{

    private int id;
    private LocalDateTime dateTime;
    private String location;
    private String description;
    private boolean isComp =false;

    public TaskImpl(Integer id, LocalDateTime dateTime, String location, String description){
        this.id = id;
        this.setDateTime(dateTime);
        this.setLocation(location);
        this.setDescription(description);
        this.isComp = false;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isCompleted() {
        return this.isComp;
    }

    @Override
    public void setDateTime(LocalDateTime dateTime) throws IllegalArgumentException{
        if(dateTime == null){
            throw new IllegalArgumentException("Datetime cannot be null");
        }
        this.dateTime = dateTime;
    }

    @Override
    public void setLocation(String location) throws IllegalArgumentException {
        if(location == null){
            throw new IllegalArgumentException("location cannot be null");
        }
        this.location = location;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void complete() throws IllegalStateException {
        if(this.isCompleted()==true){
            throw new IllegalStateException("task already completed");
        }
        this.isComp = true;
    }

    @Override
    public String getField(Task.Field field) throws IllegalArgumentException {

            if (field == LOCATION){
                return this.location;
            }
            if (field==DESCRIPTION){
                return this.description;
            }
            return null;

    }


    enum Field{
        LOCATION, DESCRIPTION
    }
}