package seedu.address.model.person;

import java.util.Objects;

/**
 * Represents the user's height measured in centimetres in fitNUS.
 */
public class Height {
    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Height should only contain positive numbers up to 2 decimal places, and it should not be blank.";

    public static final String MESSAGE_CONSTRAINTS_LIMIT =
            "Height should be greater than 100cm and smaller than 250cm.";

    public static final double HEIGHT_LOWER_LIMIT = 100;

    public static final double HEIGHT_UPPER_LIMIT = 250;

    public static final String VALIDATION_REGEX = "\\d+(\\.\\d{1,2})?";

    private final double height;

    /**
     * Constructs a {@code Height}.
     *
     * @param height A valid height.
     */
    public Height(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    /**
     * Returns true if a given string is a valid height.
     *
     * @param test The string to be tested.
     * @return True if the given string is a valid height.
     */
    public static boolean isValidHeight(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given height is within a reasonable range.
     *
     * @param test The height to be tested.
     * @return True if the given height is within a reasonable range.
     */
    public static boolean isValidHeight(double test) {
        return test > HEIGHT_LOWER_LIMIT && test < HEIGHT_UPPER_LIMIT;
    }

    @Override
    public String toString() {
        return String.format("%.2f cm", height);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Height // instanceof handles nulls
                && height == ((Height) other).getHeight()); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(height);
    }

}
