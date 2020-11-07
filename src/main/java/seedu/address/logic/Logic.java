package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyFitNus;
import seedu.address.model.calorie.DailyCalorie;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Body;
import seedu.address.model.routine.Routine;
import seedu.address.model.slot.Slot;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the fitNUS.
     *
     * @see seedu.address.model.Model#getFitNus()
     */
    ReadOnlyFitNus getFitNus();

    /** Returns an unmodifiable view of the filtered list of exercises */
    ObservableList<Exercise> getFilteredExerciseList();

    /** Returns an unmodifiable view of the filtered list of routines */
    ObservableList<Routine> getFilteredRoutineList();

    /** Returns an unmodifiable view of the filtered list of lessons */
    ObservableList<Lesson> getFilteredLessonList();

    /** Returns an unmodifiable view of the filtered list of slots */
    ObservableList<Slot> getFilteredSlotList();

    /** Returns an unmodifiable view of the filtered list of slots, filtered by chosen day */
    ObservableList<Slot> getFilteredSlotList(String day);

    /** Returns an unmodifiable view of the filtered list of calorie logs */
    ObservableList<DailyCalorie> getFilteredDailyCalorie();

    /** Returns an unmodifiable view of the filtered list of body */
    Body getFilteredBody();

    /**
     * Returns the user prefs' fitNUS file path.
     */
    Path getFitNusFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
