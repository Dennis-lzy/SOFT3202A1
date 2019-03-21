package au.edu.sydney.brawndo.erp.todo;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ToDoListImplTest {
    int testId;
    LocalDateTime testLDT;
    String testLocation;
    String testDescription;

    @Before
    public void initDefaultParam(){
        testId = 10;
        testLDT= LocalDateTime.now();
        testLocation = "Sydney";
        testDescription = "Test";
    }
    @Test
    public void add_setID(){
        ToDoList tdl = new ToDoListImpl();
        tdl.add(1,testLDT,testLocation,testDescription);
        assertEquals("Task not added to list", 1, tdl.findAll().size());
        tdl.add(2,testLDT,testLocation,testDescription);
        assertEquals("Task not added to list", 2, tdl.findAll().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_SameSetID(){
        ToDoList tdl = new ToDoListImpl();
        tdl.add(1,testLDT,testLocation,testDescription);
        tdl.add(1,testLDT,testLocation,testDescription);
    }

    @Test
    public void add_nullId(){
        ToDoList tdl = new ToDoListImpl();
        tdl.add(null,testLDT,testLocation,testDescription);
        assertEquals("Task not added to list", 1, tdl.findAll().size());

    }

}