package au.edu.sydney.brawndo.erp.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToDoListImpl implements ToDoList {

    public List<Task> tdl;
    private int idCount;
    private boolean setFlag;

    public ToDoListImpl() {
        idCount = 0;
        tdl = new ArrayList<>();
        setFlag = false;
    }

    @Override
    public Task add(Integer id, LocalDateTime dateTime, String location, String description) throws IllegalArgumentException, IllegalStateException {
        /** return IllegalArgumentException if date time of location is null**/
        if(dateTime == null){
            throw new IllegalArgumentException("dateTime cannot be null");
        }
        if(location==null){
            throw new IllegalArgumentException("location cannot be null");
        }

        int idHolder;

        if(setFlag == false){
            if(id==null){
                idHolder = idCount;
                idCount++;
            } else {
                idHolder = id;
                setFlag = true;
            }
        } else {
            if(id==null){
                throw new IllegalArgumentException("Id cannot be null after an ID is set");
            } else {
                idHolder = id;
            }
        }

        if(id!=null){
            for(Task t: this.tdl){
                if(idHolder == t.getID()){
                    throw new IllegalArgumentException("ID is taken");
                }
            }
        }

        Task task = new TaskImpl(idHolder,dateTime,location,description);

        System.out.printf("add task.location " + task.getLocation() + " and ID " + task.getID());

        this.tdl.add(task);

        for(Task i : this.tdl){
            System.out.println(i.getID() +" " +i.getLocation()+ " " +i.getDescription());
        }
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
        List<Task> findAll = findAll();
        for(Task task: findAll){
            if(completed==true) {
                if (task.isCompleted() == false) {
                    findAll.remove(task);
                }
            } else if (completed == false){
                if  (task.isCompleted()==true){
                    findAll.remove(task);
                }
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
        if(to.isBefore(from)){
            throw new IllegalArgumentException("end dateTime cannot be before starting datetime");
        }

        List<Task> findAll = findAll(completed);
        for(Task task: findAll) {
            if (task.getDateTime().isBefore(from) || task.getDateTime().isAfter(to)) {
                findAll.remove(task);
            }
        }

        return findAll;
    }

    @Override
    public List<Task> findAll(Map<Task.Field, String> params, LocalDateTime from, LocalDateTime to, Boolean completed, boolean andSearch) throws IllegalArgumentException {
        List<Task> findAll = findAll(to,from,completed);
        List<Task> findAllM = new ArrayList<>(findAll);

        if(params == null) {
            return findAll;
        }
        for(Task t: findAll) {
            boolean removeFlag = false;
            for (Map.Entry<Task.Field, String> entry : params.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    throw new IllegalArgumentException("null values in Map");
                }
                if (andSearch) {
                    /** if fields do not contain value then mark task to remove **/
                    if(entry.getKey()== Task.Field.LOCATION){
                        if(!t.getLocation().contains(entry.getValue())){
                            removeFlag = true;
                        }
                    } else {
                        if(!t.getDescription().contains(entry.getValue())){
                            removeFlag = true;
                        }
                    }
                } else {
                    removeFlag = true;
                    if(entry.getKey()== Task.Field.LOCATION){
                        if(t.getLocation().contains(entry.getValue())){
                            removeFlag = false;
                        }
                    } else {
                        if(t.getDescription().contains(entry.getValue())){
                            removeFlag = false;
                        }
                    }
                }
            }
            if(removeFlag){
                findAllM.remove(t);
            }
        }
        return findAllM;
    }

    @Override
    public void clear() {
        this.tdl.clear();
    }
}