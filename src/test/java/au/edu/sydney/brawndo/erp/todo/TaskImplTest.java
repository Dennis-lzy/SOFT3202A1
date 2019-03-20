package au.edu.sydney.brawndo.erp.todo;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TaskImplTest {
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
    public void getID_idHigh() {
        int id = 1231290872;
        Task task = new TaskImpl(id, testLDT,testLocation,testDescription);
        assertEquals("returned wrong ID, high", id,task.getID());
    }

    @Test
    public void getID_idLOW(){
        int id = 1;
        Task task = new TaskImpl(id, testLDT,testLocation,testDescription);
        assertEquals("returned wrong ID", id,task.getID());
    }

    @Test
    public void getDateTime_timeHigh(){
        LocalDateTime LDT = LocalDateTime.MAX;
        Task task = new TaskImpl(testId,LDT,testLocation,testDescription);
        assertEquals("High LDT not as expected", LocalDateTime.MAX, task.getDateTime());
    }

    @Test
    public void getDateTime_timeLow(){
        LocalDateTime LDT = LocalDateTime.MIN;
        Task task = new TaskImpl(testId,LDT,testLocation,testDescription);
        assertEquals("High LDT not as expected", LocalDateTime.MIN, task.getDateTime());
    }

    @Test
    public void getLocation_Long(){
        String location = "fuahndslfhakj fwjamdhcflnanhfcla";
        Task task = new TaskImpl(testId,testLDT,location,testDescription);
        assertEquals("Long location not as expected", location, task.getLocation());
    }

    @Test
    public void getLocation_short(){
        String location = "a";
        Task task = new TaskImpl(testId,testLDT,location,testDescription);
        assertEquals("Long location not as expected", location, task.getLocation());
    }

    @Test
    public void getDescription_Long(){
        String description = "rqcnqewyrcqouweycn #$@# fwjamdhcflnanhfcla";
        Task task = new TaskImpl(testId,testLDT,testLocation,description);
        assertEquals("Long location not as expected", description, task.getDescription());
    }

    @Test
    public void getDescription_Short(){
        String description = "r";
        Task task = new TaskImpl(testId,testLDT,testLocation,description);
        assertEquals("Long location not as expected", description, task.getDescription());
    }

    @Test
    public void isCompleted_false(){
        Task task = new TaskImpl(testId,testLDT,testLocation,testDescription);
        assertEquals("isCompleted not false as expected", false, task.isCompleted());
    }

    @Test
    public void isCompleted_true(){
        Task task = new TaskImpl(testId,testLDT,testLocation,testDescription);
        task.complete();
        assertEquals("isCompleted not true as expected", true, task.isCompleted());
    }

    @Test(expected = IllegalStateException.class)
    public void complete_exception(){
        Task task = new TaskImpl(testId,testLDT,testLocation,testDescription);
        task.complete();
        task.complete();
    }

    @Test
    public void setDateTime_High(){
        LocalDateTime LDT = LocalDateTime.MAX;
        Task task = new TaskImpl(testId,testLDT,testLocation,testDescription);
        task.setDateTime(LDT);
        assertEquals("High LDT not as expected", LocalDateTime.MAX, task.getDateTime());
    }

    @Test
    public void setDateTime_Low(){
        LocalDateTime LDT = LocalDateTime.MIN;
        Task task = new TaskImpl(testId,testLDT,testLocation,testDescription);
        task.setDateTime(LDT);
        assertEquals("High LDT not as expected", LocalDateTime.MIN, task.getDateTime());
    }

    @Test
    public void setLocation_Long(){
        String location = "Testtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt";
        Task task = new TaskImpl(testId,testLDT,testLocation,testDescription);
        task.setLocation(location);
        assertEquals("Long location not as expected", location, task.getLocation());
    }

    @Test
    public void setLocation_Short(){
        String location = "#";
        Task task = new TaskImpl(testId,testLDT,testLocation,testDescription);
        task.setLocation(location);
        assertEquals("short location not as expected", location, task.getLocation());
    }

    @Test
    public void setDescription_Long(){
        String description = "Testtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt";
        Task task = new TaskImpl(testId,testLDT,testLocation,testDescription);
        task.setDescription(description);
        assertEquals("Long location not as expected", description, task.getDescription());
    }



}