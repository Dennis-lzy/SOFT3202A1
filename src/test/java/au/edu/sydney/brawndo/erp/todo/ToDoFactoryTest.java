package au.edu.sydney.brawndo.erp.todo;
import static org.junit.Assert.*;
import org.junit.Test;

public class ToDoFactoryTest {

    @Test
    public void makeToDoList(){
        ToDoFactory TDF = new ToDoFactory();
        assertNotNull(TDF.makeToDoList());

    }
}