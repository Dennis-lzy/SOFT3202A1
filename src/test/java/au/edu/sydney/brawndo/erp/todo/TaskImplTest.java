package au.edu.sydney.brawndo.erp.todo;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TaskImplTest {

    @Test
    public void getID_idHigh() {
        int id = 1231290872;
        Task task = new TaskImpl(id,LocalDateTime.now(),"a","a");
        assertEquals("returned wrong ID, high", id,task.getID());
    }

    @Test
    public void getID_idLOW(){
        int id = 1;
        Task task = new TaskImpl(id,LocalDateTime.now(),"a","a");
        assertEquals("returned wrong ID", id,task.getID());
    }



}