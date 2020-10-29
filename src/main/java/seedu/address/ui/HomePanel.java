package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

public class HomePanel extends UiPart<Region> {

    private static final String FXML = "HomePanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ExerciseListPanel exerciseListPanel;
    private LessonListPanel lessonListPanel;
    private RoutineListPanel routineListPanel;
    private CalorieGraph lineChart;

    @FXML
    private StackPane routineListPanelPlaceholder;

    @FXML
    private StackPane exerciseListPanelPlaceholder;

    @FXML
    private StackPane lessonListPanelPlaceholder;

    @FXML
    private HBox graph;

    /** Constructs HomePanel
     *
     * @param logic
     */
    public HomePanel(Logic logic) {
        super(FXML);

        this.logic = logic;

        exerciseListPanel = new ExerciseListPanel(logic.getFilteredExerciseList());
        exerciseListPanelPlaceholder.getChildren().add(exerciseListPanel.getRoot());

        lessonListPanel = new LessonListPanel(logic.getFilteredLessonList());
        lessonListPanelPlaceholder.getChildren().add(lessonListPanel.getRoot());

        routineListPanelPlaceholder.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY,
                Insets.EMPTY)));
        routineListPanel = new RoutineListPanel(logic.getFilteredRoutineList());
        routineListPanelPlaceholder.getChildren().add(routineListPanel.getRoot());

        lineChart = new CalorieGraph(logic.getFilteredDailyCalorie());
        graph.getChildren().add(lineChart.getRoot());
    }
}
