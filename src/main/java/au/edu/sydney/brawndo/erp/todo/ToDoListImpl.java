package au.edu.sydney.brawndo.erp.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToDoListImpl implements ToDoList {

    private List<Task> tdl = new ArrayList<>();
    private int idCount;

    public ToDoListImpl() {
    }

    @Override
    public Task add(Integer id, LocalDateTime dateTime, String location, String description) throws IllegalArgumentException, IllegalStateException {
        Task task = new TaskImpl(this.idCount, dateTime, location, description);
        if(dateTime == null){
            throw new IllegalArgumentException("dateTime cannot be null");
        }
        if(location==null){
            throw new IllegalArgumentException("location cannot be null");
        }
        tdl.add(task);
        this.idCount++;
        return task;
    }

    @Override
    public boolean remove(int id) {
        boolean exists=false;
        for(Task task: this.tdl){
            if(id == task.getID()){
                exists = true;
                this.tdl.remove(task);
                break;
            }
        }
        return exists;
    }

    @Override
    public Task findOne(int id) {
        return null;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public List<Task> findAll(boolean completed) {
        return null;
    }

    @Override
    public List<Task> findAll(LocalDateTime from, LocalDateTime to, Boolean completed) throws IllegalArgumentException {
        return null;
    }

    @Override
    public List<Task> findAll(Map<Task.Field, String> params, LocalDateTime from, LocalDateTime to, Boolean completed, boolean andSearch) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void clear() {

    }
}