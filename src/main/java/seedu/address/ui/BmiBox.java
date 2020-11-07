package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Body;
import seedu.address.model.person.Height;
import seedu.address.model.person.Weight;

public class BmiBox extends UiPart<Region> {

    private static final String FXML = "BmiBox.fxml";

    private final Logger logger = LogsCenter.getLogger(CalorieGraph.class);

    private Body body;

    // Independent Ui parts residing in this Ui container

    @FXML
    private Label bmi2;


    /** Constructs BmiBox
     *
     * @param body
     */
    public BmiBox(Body body) {
        super(FXML);
        this.body = body;
        Body observedBody = this.body;
        String text = "BMI Metrics: \n\n"
                + "Height: " + observedBody.getHeight().toString() + "\n\n"
                + "Weight: " + observedBody.getWeight().toString() + "\n\n"
                + "BMI: " + String.format("%.2f", observedBody.getBmi());
        bmi2.setText(text);

        this.body.getObservedHeight().addListener((observableValue, number, t1) -> updateHeight(t1));
        this.body.getObservedWeight().addListener((observableValue, number, t1) -> updateWeight(t1));

    }

    private void updateHeight(Height newHeight) {
        Body observedBody = body;
        String text = "BMI Metrics: \n\n"
                + "Height: " + newHeight.toString() + "\n\n"
                + "Weight: " + observedBody.getWeight().toString() + "\n\n"
                + "BMI: " + String.format("%.2f", observedBody.getBmi());
        bmi2.setText(text);
    }

    private void updateWeight(Weight newWeight) {

        String text = "BMI Metrics: \n\n"
                + "Height: " + this.body.getHeight().toString() + "\n\n"
                + "Weight: " + newWeight.toString() + "\n\n"
                + "BMI: " + String.format("%.2f", this.body.getBmi());
        bmi2.setText(text);
    }
}
