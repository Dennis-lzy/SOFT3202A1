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
    public void findAllMapAnd(){
        ToDoList TDList = new ToDoListImpl();
        Map params = new HashMap();
        params.put(Task.Field.LOCATION,tLoc);
        params.put(Task.Field.DESCRIPTION,tDes);
        TDList.add(tId,tLDT,tLoc,tDes);
        TDList.add(tId2,tLDT2,tLoc2,tDes2);
        TDList.add(tId2+1,tLDT2,tLoc2+"qqqqqk",tDes+"aqqqqqda");
        List<Task> test = TDList.findAll(params,null,null,false,true);
        for(Task i : test){
            System.out.println("test list And " +i.getLocation()+" " +i.getDescription());
        }
    }

    @Test
    public void findAllMapOR(){
        ToDoList TDList = new ToDoListImpl();
        Map params = new HashMap();
        params.put(Task.Field.LOCATION,tLoc);
        params.put(Task.Field.DESCRIPTION,tDes);
        params.put(Task.Field.LOCATION,tLoc2);
        params.put(Task.Field.LOCATION,tDes2);
        TDList.add(tId,tLDT,tLoc,tDes);
        TDList.add(tId2,tLDT2,tLoc2,tDes2);
        TDList.add(tId2+1,tLDT2,tLoc2+"djkfsak",tDes+"asda");
        List<Task> test = TDList.findAll(params,null,null,false,false);
        for(Task i : test){
            System.out.println("test list OR " +i.getLocation()+ " " +i.getDescription());
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

    @clear

}