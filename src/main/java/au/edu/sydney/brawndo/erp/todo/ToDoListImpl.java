package au.edu.sydney.brawndo.erp.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToDoListImpl implements ToDoList {

    private List<Task> tdl;
    private int idCount;

    public ToDoListImpl() {
        idCount = 0;
        tdl = new ArrayList<>();
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
        for(Task task: this.tdl){
            if(id == task.getID()){
               return task;
            }
        }
        return null;
    }

    @Override
    public List<Task> findAll() {
        List<Task> findAll = new ArrayList<>(tdl);
        return findAll;
    }

    @Override
    public List<Task> findAll(boolean completed) {
        List<Task> findAll = new ArrayList<>(tdl);
        for(Task task: findAll){
            if(task.isCompleted()==false){
                findAll.remove(task);
            }
        }
        return findAll;
    }

    @Override
    public List<Task> findAll(LocalDateTime from, LocalDateTime to, Boolean completed) throws IllegalArgumentException {
        if(from == null){
            from = LocalDateTime.MIN;
        }

        if(to==null){
            to = LocalDateTime.MAX;
        }
        List<Task> findAll = new ArrayList<>(tdl);
        for(Task task: findAll){
            if(task.getDateTime().isBefore(from) || task.getDateTime().isAfter(to)){
                findAll.remove(task);
            }
            if(task.isCompleted()==false){
                findAll.remove(task);
            }
        }
        return findAll;
    }

    @Override
    public List<Task> findAll(Map<Task.Field, String> params, LocalDateTime from, LocalDateTime to, Boolean completed, boolean andSearch) throws IllegalArgumentException {


        if(from == null){
            from = LocalDateTime.MIN;
        }
        if(to==null){
            to = LocalDateTime.MAX;
        }
        List<Task> findAll = new ArrayList<>(tdl);
        for(Task task: findAll){
            if(task.getDateTime().isBefore(from) || task.getDateTime().isAfter(to)){
                findAll.remove(task);
            }
            if(task.isCompleted()==false){
                findAll.remove(task);
            }
        }
        return findAll;
    }

    @Override
    public void clear() {
        this.tdl.clear();
    }
}