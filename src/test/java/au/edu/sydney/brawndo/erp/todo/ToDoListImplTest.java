package au.edu.sydney.brawndo.erp.todo;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ToDoListImplTest {
    int tId;
    LocalDateTime tLDT;
    String tLoc;
    String tDes;
    int tId2;
    LocalDateTime tLDT2;
    String tLoc2;
    String tDes2;
    ToDoList tdl;

    @Before
    public void initDefaultParam(){
        tId = 10;
        tLDT = LocalDateTime.now();
        tLoc = "Sydney";
        tDes = "Test";
        tId2 = 5;
        tLDT2 = LocalDateTime.MAX;
        tLoc2 = "Victoria";
        tDes2 = "Test2";
        tdl = new ToDoListImpl();
    }
    @Test
    public void add_nullToSet(){
        tdl.add(null, tLDT, tLoc, tDes);
        assertEquals("Task1 not added to list", 1, tdl.findAll().size());
        assertEquals("Location not correct", tLoc, tdl.findOne(0).getLocation());
        tdl.add(4, tLDT, tLoc2, tDes);
        assertEquals("Task2 not added to list", 2, tdl.findAll().size());
        assertEquals("Location not correct", tLoc2, tdl.findOne(4).getLocation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_setToNull(){
        tdl.add(10,tLDT2,tLoc,tDes2);
        tdl.add(null,tLDT2,tLoc,tDes2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_SameSetID(){
       tdl.add(1, tLDT, tLoc, tDes);
       tdl.add(1, tLDT, tLoc, tDes);
    }

    @Test
    public void add_nullId(){
        tdl.add(null, tLDT, tLoc, tDes);
        assertEquals("Task not added to list", 1, tdl.findAll().size());
    }

    @Test
    public void findOne_normal(){
        tdl.add(null,tLDT,tLoc,tDes);
        tdl.add(null,tLDT,"Melbourne",tDes);
        tdl.add(null,tLDT,"Melbourne1",tDes);
        tdl.add(null,tLDT,"Melbourne2",tDes);
        tdl.add(null,tLDT,"Melbourne3",tDes);
        tdl.add(tId2,tLDT2,tLoc2,tDes2);
        assertEquals("Task not added to list", 6, tdl.findAll().size());
        for(Task i : tdl.findAll()){
            System.out.println(i.getID() +" " +i.getLocation()+ " " +i.getDescription());
        }
        assertEquals("find one normal is not working 1 ", "Sydney", tdl.findOne(0).getLocation());
        assertEquals("find one normal is not working 2 ", "Melbourne", tdl.findOne(1).getLocation());
        assertEquals("find one normal is not working 3 ", "Melbourne1", tdl.findOne(2).getLocation());
        assertEquals("find one normal is not working 4 ", "Melbourne2", tdl.findOne(3).getLocation());
    }

    @Test
    public void findOne_null(){
        assertEquals("Did not return null as expected for findOne", null, tdl.findOne(43));
    }

    @Test
    public void findOne_testFindAllDoesntChangeTDL(){
        tdl.add(null,tLDT,tLoc,tDes);
        tdl.add(null,tLDT,"Melbourne",tDes);
        tdl.add(null,tLDT,"Melbourne1",tDes);
        tdl.add(null,tLDT,"Melbourne2",tDes);
        tdl.add(null,tLDT,"Melbourne3",tDes);
        tdl.findAll().clear();
        assertNotNull("Changes to findALl affected ToDOList", tdl.findOne(2));
    }

    @Test
    public void findAll_normal (){
        tdl.add(null,tLDT,tLoc,tDes);
        tdl.add(null,tLDT,"Melbourne",tDes);
        tdl.add(null,tLDT,"Melbourne1",tDes);
        tdl.add(null,tLDT,"Melbourne2",tDes);
        assertEquals("Not all tasks included", 4, tdl.findAll().size());
        assertEquals("Not all tasks included", "Sydney", tdl.findOne(0).getLocation());
        assertEquals("Not all tasks included", "Melbourne", tdl.findOne(1).getLocation());
        assertEquals("Not all tasks included", "Melbourne1", tdl.findOne(2).getLocation());
        assertEquals("Not all tasks included", "Melbourne2", tdl.findOne(3).getLocation());
    }

    @Test
    public void findAll_empty(){
        assertTrue("findAll should be empty",tdl.findAll().isEmpty());
    }

    @Test
    public void findAllComp_normal(){
        tdl.add(null,tLDT,tLoc,tDes);
        tdl.add(null,tLDT,"Melbourne",tDes);
        tdl.add(null,tLDT,"Melbourne1",tDes);
        tdl.add(null,tLDT,"Melbourne2",tDes);
        tdl.findOne(0).complete();

        //for (Task t:tdl.findAll(true)) {
         //   System.out.println(t.getID() + " " +t.getLocation());
        //}
        for(Task t:tdl.findAll(true)) {
            assertTrue("Did not filter out not completed",t.isCompleted());
        }
        tdl.findOne(2).complete();
        tdl.findOne(3).complete();
        for(Task t:tdl.findAll(LocalDateTime.now(), LocalDateTime.MAX,true)) {
            assertTrue("Did not filter out not completed",t.isCompleted());
        }
    }

    @Test
    public void findAll_Time(){
        tdl.add(null,LocalDateTime.now().plusDays(5),tLoc,tDes);
        tdl.add(null,LocalDateTime.now().plusDays(1000),"Melbourne",tDes);
        tdl.add(null,LocalDateTime.now().minusDays(23),"Melbourne1",tDes);
        tdl.add(null,LocalDateTime.now().minusDays(1232),"Melbourne2",tDes);
        for(Task t:tdl.findAll(LocalDateTime.now(), LocalDateTime.MAX.minusDays(50),false)) {
            assertTrue("Time is after time boundary",t.getDateTime().isBefore(LocalDateTime.MAX.minusDays(50)));
            assertTrue("Time is before time boundary",t.getDateTime().isAfter(LocalDateTime.now()));
        }
        tdl.findOne(1).complete();
        for(Task t:tdl.findAll(LocalDateTime.now(), LocalDateTime.MAX.minusDays(50),true)) {
            assertTrue("Time is after time boundary",t.getDateTime().isBefore(LocalDateTime.MAX.minusDays(50)));
            assertTrue("Time is before time boundary",t.getDateTime().isAfter(LocalDateTime.now()));
            assertTrue("Did not filter out not completed",t.isCompleted());
        }

    }

    @Test
    public void findAll_TimeNull (){
        tdl.add(null,LocalDateTime.now().plusDays(5),tLoc,tDes);
        tdl.add(null,LocalDateTime.now().plusDays(1000),"Melbourne",tDes);
        tdl.add(null,LocalDateTime.now().minusDays(23),"Melbourne1",tDes);
        tdl.add(null,LocalDateTime.now().minusDays(1232),"Melbourne2",tDes);
        assertTrue("null param behaviour incorrect for findAll Time, comp", tdl.findAll(true).equals(tdl.findAll(null,null,true)));
        assertTrue("null param behaviour incorrect for findAll Time, not comp", tdl.findAll(false).equals(tdl.findAll(null,null,false)));
    }


    @Test
    public void findAllMapAnd(){
        ToDoList tdl = new ToDoListImpl();
        Map params = new HashMap();
        params.put(Task.Field.LOCATION,tLoc);
        params.put(Task.Field.DESCRIPTION,tDes);
        tdl.add(tId,tLDT,tLoc,tDes);
        tdl.add(tId2,tLDT2,tLoc2,tDes2);
        tdl.add(53,tLDT,tLoc,tDes);
        tdl.add(20,tLDT,"Melbourne1",tDes);
        tdl.add(742,tLDT,"Melbourne2",tDes);
        tdl.add(tId2+1,tLDT2,tLoc2+"qqqqqk",tDes+"aqqqqqda");
        List<Task> test = tdl.findAll(params,null,null,false,true);
        for(Task i : test){
            assertTrue(i.getLocation() == tLoc);
            assertTrue(i.getDescription() == tDes);
        }
    }


    @Test
    public void findAllMapOR(){
        ToDoList tdl = new ToDoListImpl();
        Map params = new HashMap();
        params.put(Task.Field.LOCATION,tLoc);
        params.put(Task.Field.DESCRIPTION,tDes);
        tdl.add(tId,tLDT,tLoc,tDes);
        tdl.add(tId2,tLDT2,tLoc2,tDes2);
        tdl.add(53,tLDT,tLoc,tDes);
        tdl.add(20,tLDT,"Melbourne1",tDes);
        tdl.add(742,tLDT,"Melbourne2",tDes);
        tdl.add(tId2+1,tLDT2,tLoc2+"qqqqqk",tDes+"aqqqqqda");
        List<Task> test = tdl.findAll(params,null,null,false,true);
        for(Task i : test){
            assertTrue(i.getLocation() == tLoc||i.getDescription() == tDes);
        }
    }

    @Test
    public void remove_empty(){
        ToDoList TDList = new ToDoListImpl();
        assertFalse("Remove empty list failed", TDList.remove(tId));
    }

    @Test
    public void remove_existingSingle(){
        ToDoList TDList = new ToDoListImpl();
        TDList.add(tId, tLDT, tLoc, tDes);
        assertTrue("Removing existing form list failed", TDList.remove(tId));
        assertTrue("List not empty after removing only element", TDList.findAll().isEmpty());
    }

    @Test
    public void remove_false(){
        assertFalse("did not return false as expected fr remove", tdl.remove(5));
    }

    @Test
    public void clear() {
        tdl.add(null, tLDT, tLoc, tDes);
        tdl.add(null, tLDT, "Melbourne", tDes);
        tdl.add(null, tLDT, "Melbourne1", tDes);
        tdl.add(null, tLDT, "Melbourne2", tDes);
        tdl.add(null, tLDT, "Melbourne3", tDes);
        tdl.clear();
        assertEquals("elements left after clear", 0, tdl.findAll().size());
    }

}