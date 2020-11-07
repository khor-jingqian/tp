package seedu.address.model.person;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Body {

    private Height height;
    private Weight weight;

    private ObjectProperty<Height> observedHeight;
    private ObjectProperty<Weight> observedWeight;

    /**
     * Creates a Body object.
     */
    public Body() {
        this.height = new Height(160);
        this.weight = new Weight(45);

        this.observedHeight = new SimpleObjectProperty<>(new Height(160));
        this.observedWeight = new SimpleObjectProperty<>(new Weight(45));
    }

    /**
     * Sets Height of this Body.
     */
    public void setHeight(Height h) {
        requireNonNull(h);
        this.height = h;
        this.observedHeight.setValue(h);
    }

    /**
     * Sets Weight of this Body.
     */
    public void setWeight(Weight w) {
        requireNonNull(w);
        this.weight = w;
        this.observedWeight.setValue(w);
    }

    /**
     * Returns Height of this Body.
     * @return Height of this Body.
     */
    public Height getHeight() {
        return this.height;
    }

    /**
     * Returns Weight of this Body.
     * @return Weight of this Body.
     */
    public Weight getWeight() {
        return this.weight;
    }

    /**
     * Returns true if both Body have the same data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Body)) {
            return false;
        }

        Body otherBody = (Body) other;
        return height.equals(otherBody.getHeight()) && weight.equals(otherBody.getWeight());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.height, this.weight);
    }

    @Override
    public String toString() {
        return "Body measurements: \n"
                + "Height: " + height + "\n"
                + "Weight: " + weight + "\n";
    }

    /**
     * Calculates the BMI of this Body.
     * @return Double representing the BMI.
     */
    public double getBmi() {
        return weight.getWeight() / Math.pow((height.getHeight() / 100.0), 2);
    }

    public ObjectProperty<Height> getObservedHeight() {
        return this.observedHeight;
    }

    public ObjectProperty<Weight> getObservedWeight() {
        return this.observedWeight;
    }

}
