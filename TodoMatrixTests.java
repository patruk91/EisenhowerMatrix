import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.util.*;

class TodoMatrixTests {
    TodoMatrix todoMatrix;

    @BeforeEach
    void init() {
        this.todoMatrix = new TodoMatrix();
    }

    @Test
    void testAddItem() {
        String title = "implement Matrix class";
        LocalDate today = LocalDate.now();
        LocalDate dateUrgent = today.plusDays(1);
        LocalDate dateNotUrgent = today.plusDays(30);

        this.todoMatrix.addItem(title, dateUrgent, true);
        this.todoMatrix.addItem(title, dateUrgent, false);
        this.todoMatrix.addItem(title, dateNotUrgent, true);
        this.todoMatrix.addItem(title, dateNotUrgent, false);

        TodoItem todoItem = (TodoItem) this.todoMatrix.getQuarters().get("IU").getItem(0);
        assertEquals(title, todoItem.getTitle());

        todoItem = (TodoItem) this.todoMatrix.getQuarters().get("IN").getItem(0);
        assertEquals(title, todoItem.getTitle());

        todoItem = (TodoItem) this.todoMatrix.getQuarters().get("NU").getItem(0);
        assertEquals(title, todoItem.getTitle());

        todoItem = (TodoItem) this.todoMatrix.getQuarters().get("NN").getItem(0);
        assertEquals(title, todoItem.getTitle());
    }

    @Test
    void testArchiveItems() {
        String title = "implement Matrix class";
        LocalDate today = LocalDate.now();
        LocalDate dateUrgent = today.plusDays(1);
        LocalDate dateNotUrgent = today.plusDays(30);

        this.todoMatrix.addItem(title, dateUrgent, true);
        this.todoMatrix.addItem(title, dateUrgent, false);
        this.todoMatrix.addItem(title, dateNotUrgent, true);
        this.todoMatrix.addItem(title, dateNotUrgent, false);

        this.todoMatrix.getQuarters().get("IU").getItem(0).mark();
        this.todoMatrix.getQuarters().get("IN").getItem(0).mark();
        this.todoMatrix.getQuarters().get("NU").getItem(0).mark();
        this.todoMatrix.getQuarters().get("NN").getItem(0).mark();

        this.todoMatrix.archiveItems();

        TodoQuarter todoQuarter = this.todoMatrix.getQuarters().get("IU");
        assertTrue(todoQuarter.getItems().isEmpty(), "IU");

        todoQuarter = this.todoMatrix.getQuarters().get("IN");
        assertTrue(todoQuarter.getItems().isEmpty(), "IN");

        todoQuarter = this.todoMatrix.getQuarters().get("NU");
        assertTrue(todoQuarter.getItems().isEmpty(), "NU");

        todoQuarter = this.todoMatrix.getQuarters().get("NN");
        assertTrue(todoQuarter.getItems().isEmpty(), "NN");
    }

    @Test
    void testSaveItemsToFile() throws IOException {
        String fileIn = "todo_items_read_test.csv";
        String fileOut = "todo_items_save_test.csv";

        this.todoMatrix.addItemsFromFile(fileIn);
        this.todoMatrix.saveItemsToFile(fileOut);

        assertReaders(new BufferedReader(new FileReader(fileIn)), new BufferedReader(new FileReader(fileOut)));
    }

    public static void assertReaders(BufferedReader expected, BufferedReader actual) throws IOException {
        String expectedLine;
        while ((expectedLine = expected.readLine()) != null) {
            String actualLine = actual.readLine();
            assertNotNull("Expected had more lines then the actual.", actualLine);
            assertEquals(expectedLine, actualLine);
        }
        assertNull("Actual had more lines then the expected.", actual.readLine());
    }
}
