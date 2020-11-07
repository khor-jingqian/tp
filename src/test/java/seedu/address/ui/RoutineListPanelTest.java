package seedu.address.ui;

import static seedu.address.testutil.TypicalRoutines.getTypicalRoutines;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.model.routine.Routine;

public class RoutineListPanelTest {

    private static final ObservableList<Routine> modelRoutine = FXCollections.
            observableArrayList(getTypicalRoutines());

    private static final RoutineListPanel panel = new RoutineListPanel(modelRoutine);

    @Test

}
