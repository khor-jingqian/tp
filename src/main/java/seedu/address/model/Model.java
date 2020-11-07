package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.calorie.DailyCalorie;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Body;
import seedu.address.model.person.Height;
import seedu.address.model.person.Weight;
import seedu.address.model.routine.Routine;
import seedu.address.model.slot.Slot;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true.
     */
    Predicate<Exercise> PREDICATE_SHOW_ALL_EXERCISES = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true.
     */
    Predicate<Routine> PREDICATE_SHOW_ALL_ROUTINES = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true.
     */
    Predicate<Lesson> PREDICATE_SHOW_ALL_LESSONS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true.
     */
    Predicate<DailyCalorie> PREDICATE_SHOW_ALL_LOGS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' fitNUS file path.
     */
    Path getFitNusFilePath();

    /**
     * Sets the user prefs' fitNUS file path.
     */
    void setFitNusFilePath(Path fitNusFilePath);

    /**
     * Replaces fitNUS data with the data in {@code fitNus}.
     */
    void setFitNus(ReadOnlyFitNus fitNus);

    /**
     * Returns the fitNUS.
     */
    ReadOnlyFitNus getFitNus();

    /**
     * Deletes the given exercise.
     * The exercise must exist in fitNUS.
     */
    void deleteExercise(Exercise target);

    /**
     * Deletes the given lesson.
     * The lesson must exist in fitNUS.
     */
    void deleteLesson(Lesson target);

    /**
     * Returns an unmodifiable view of the filtered exercise list.
     */
    ObservableList<Exercise> getFilteredExerciseList();

    /**
     * Returns an unmodifiable view of the filtered lesson list.
     */
    ObservableList<Lesson> getFilteredLessonList();

    /**
     * Returns an unmodifiable view of the filtered slot list
     */
    ObservableList<Slot> getFilteredSlotList();

    /**
     * Returns an unmodifiable view of the filtered slot list, filtered by the chosen day.
     */
    ObservableList<Slot> getFilteredSlotList(String day);

    /**
     * Returns an unmodifiable view of the filtered body list
     */
    Body getFilteredBody();

    /**
     * Updates the filter of the filtered calorie log to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCalorieLog(Predicate<DailyCalorie> predicate);

    /**
     * Updates the filter of the filtered exercise list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExerciseList(Predicate<Exercise> predicate);

    /**
     * Updates the filter of the filtered lesson list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredLessonList(Predicate<Lesson> predicate);

    /**
     * Returns true if a exercise with the same details as {@code exercise} exists in fitNUS.
     */
    boolean hasExercise(Exercise exercise);

    /**
     * Adds the given exercise.
     * {@code exercise} must not already exist in fitNUS.
     */
    void addExercise(Exercise exercise);

    /**
     * Replaces the given exercise {@code target} with {@code editedExercise}.
     * {@code target} must exist in fitNUS.
     * The exercise identity of {@code editedExercise} must not be the same as another existing exercise in FitNUS.
     */
    void setExercise(Exercise target, Exercise editedExercise);

    /**
     * Adds the given routine.
     * {@code routine} must not already exist in fitNUS.
     */
    void addRoutine(Routine routine);

    /**
     * Returns true if a routine with the same identity as {@code routine} exists in fitNUS.
     */
    boolean hasRoutine(Routine r);

    /**
     * Adds the given exercise to the given routine.
     * {@code r} must already exist in fitNUS.
     * {@code e} must already exist in fitNUS.
     */
    void addExerciseToRoutine(Routine r, Exercise e);

    /**
     * Updates the filter of the filtered routine list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRoutineList(Predicate<Routine> predicate);

    /**
     * Deletes the target routine.
     * The routine must exist in the fitNUS.
     */
    void deleteRoutine(Routine target);

    boolean hasSlot(Slot slot);

    boolean hasOverlappingSlot(Slot slot);

    void addSlotToTimetable(Slot slot);

    void deleteSlotFromTimetable(Slot target);

    void addHeight(Height height);

    void addWeight(Weight weight);

    double getBmi();

    /**
     * Returns true if a lesson with the same details as {@code lesson} exists in timetable.
     */
    boolean hasLesson(Lesson lesson);

    /**
     * Adds the given lesson.
     * {@code lesson} must not already exist in fitNUS.
     */
    void addLesson(Lesson lesson);

    /**
     * Replaces the given person {@code target} with {@code editedLesson}.
     * {@code target} must exist in FitNUS.
     * The lesson identity of {@code editedLesson} must not be the same as another existing lesson in FitNUS.
     */
    void setLesson(Lesson target, Lesson editedLesson);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Routine> getFilteredRoutineList();

    /**
     * Retrieves the Lesson object from UniqueLessonList that the user specified.
     *
     * @param lesson Lesson object that the user wants.
     * @return Lesson object that exists within fitNUS that the user is looking for.
     */
    Lesson retrieveLesson(Lesson lesson);

    /**
     * Retrieves the Routine object from UniqueRoutineList that the user specified.
     *
     * @param routine Routine object that the user wants.
     * @return Routine object that exists within fitNUS that the user is looking for.
     */
    Routine retrieveRoutine(Routine routine);

    /**
     * Deletes the specified exercise from an existing routine.
     * {@code routine} must already exist within fitNUS.
     * {@code exercise} must already exist within fitNUS.
     */
    void deleteExerciseFromRoutine(Routine routine, Exercise exercise);

    void viewRoutine(Routine routineToView);

    void addCalories(int calories);

    void minusCalories(int calories);

    ObservableList<DailyCalorie> getFilteredDailyCalorie();

    ObservableList<DailyCalorie> getFilteredDailyCalorieList();

    int getCalories();
}
