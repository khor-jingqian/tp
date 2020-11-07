package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.calorie.DailyCalorie;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Body;
import seedu.address.model.person.Height;
import seedu.address.model.person.Weight;
import seedu.address.model.routine.Routine;
import seedu.address.model.routine.RoutineNameContainsKeywordsPredicate;
import seedu.address.model.slot.Slot;
import seedu.address.model.slot.SlotDayPredicate;

/**
 * Represents the in-memory model of the fitNUS data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FitNus fitNus;
    private final UserPrefs userPrefs;
    private final FilteredList<Exercise> filteredExercises;
    private final FilteredList<Routine> filteredRoutine;
    private final FilteredList<Lesson> filteredLessons;
    private final FilteredList<Slot> filteredSlots;
    private final FilteredList<DailyCalorie> filteredDailyCalories;
    private final Body bodyCopy;

    /**
     * Initializes a ModelManager with the given fitNus and userPrefs.
     */
    public ModelManager(ReadOnlyFitNus fitNus, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(fitNus, userPrefs);

        logger.fine("Initializing with fitNUS: " + fitNus + " and user prefs " + userPrefs);

        this.fitNus = new FitNus(fitNus);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredExercises = new FilteredList<>(this.fitNus.getExerciseList());
        filteredRoutine = new FilteredList<>(this.fitNus.getRoutineList());
        filteredLessons = new FilteredList<>(this.fitNus.getLessonList());
        filteredSlots = new FilteredList<>(this.fitNus.getSlotList());
        filteredDailyCalories = new FilteredList<>(this.fitNus.getDailyCalorieList());
        bodyCopy = this.fitNus.getBody();
    }

    public ModelManager() {
        this(new FitNus(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getFitNusFilePath() {
        return userPrefs.getFitNusFilePath();
    }

    @Override
    public void setFitNusFilePath(Path fitNusFilePath) {
        requireNonNull(fitNusFilePath);
        userPrefs.setFitNusFilePath(fitNusFilePath);
    }

    //=========== FitNus ================================================================================

    @Override
    public void setFitNus(ReadOnlyFitNus fitNus) {
        this.fitNus.resetData(fitNus);
    }

    @Override
    public ReadOnlyFitNus getFitNus() {
        return fitNus;
    }

    @Override
    public void deleteExercise(Exercise target) {
        fitNus.removeExercise(target);
    }

    @Override
    public void deleteLesson(Lesson target) {
        fitNus.removeLesson(target);
    }

    @Override
    public void deleteRoutine(Routine target) {
        fitNus.removeRoutine(target);
    }

    @Override
    public void deleteSlotFromTimetable(Slot target) {
        fitNus.removeSlotFromTimetable(target);
    }

    @Override
    public void addExercise(Exercise exercise) {
        fitNus.addExercise(exercise);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
    }

    @Override
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireAllNonNull(target, editedExercise);
        fitNus.setExercise(target, editedExercise);
    }


    @Override
    public void addRoutine(Routine routine) {
        fitNus.addRoutine(routine);
        updateFilteredRoutineList(PREDICATE_SHOW_ALL_ROUTINES);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
    }

    @Override
    public void addCalories(int calories) {
        fitNus.addCalories(calories);
        updateFilteredCalorieLog(PREDICATE_SHOW_ALL_LOGS);
    }

    @Override
    public void updateFilteredCalorieLog(Predicate<DailyCalorie> predicate) {
        requireNonNull(predicate);
        filteredDailyCalories.setPredicate(predicate);
    }

    @Override
    public void minusCalories(int calories) {
        fitNus.minusCalories(calories);
    }

    @Override
    public int getCalories() {
        return fitNus.getCalories();
    }

    @Override
    public ObservableList<DailyCalorie> getFilteredDailyCalorie() {
        return filteredDailyCalories;
    }

    @Override
    public boolean hasRoutine(Routine r) {
        requireNonNull(r);
        return fitNus.hasRoutine(r);
    }

    @Override
    public void addExerciseToRoutine(Routine r, Exercise e) {
        fitNus.addExerciseToRoutine(r, e);
        updateFilteredRoutineList(PREDICATE_SHOW_ALL_ROUTINES);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
    }

    @Override
    public void deleteExerciseFromRoutine(Routine r, Exercise e) {
        fitNus.deleteExerciseFromRoutine(r, e);
        updateFilteredRoutineList(PREDICATE_SHOW_ALL_ROUTINES);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
    }

    @Override
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return fitNus.hasExercise(exercise);
    }

    @Override
    public void addHeight(Height height) {
        fitNus.addHeight(height);
    }

    @Override
    public void addWeight(Weight weight) {
        fitNus.addWeight(weight);
    }

    @Override
    public double getBmi() {
        return fitNus.getBmi();
    }

    /**
     * Adds a Lesson into fitNUS.
     *
     * @param lesson Lesson object that is to be added to fitNUS.
     */
    public void addLesson(Lesson lesson) {
        fitNus.addLesson(lesson);
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
    }

    @Override
    public void setLesson(Lesson target, Lesson editedLesson) {
        requireAllNonNull(target, editedLesson);

        fitNus.setLesson(target, editedLesson);
    }

    @Override
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return fitNus.hasLesson(lesson);
    }

    @Override
    public boolean hasSlot(Slot slot) {
        requireNonNull(slot);
        return fitNus.hasSlot(slot);
    }

    @Override
    public boolean hasOverlappingSlot(Slot slot) {
        requireNonNull(slot);
        return fitNus.hasOverlappingDurationInSlot(slot);
    }

    @Override
    public void addSlotToTimetable(Slot slot) {
        fitNus.addSlotToTimetable(slot);
        updateFilteredCalorieLog(PREDICATE_SHOW_ALL_LOGS);
    }

    @Override
    public Lesson retrieveLesson(Lesson lesson) {
        return fitNus.retrieveLesson(lesson);
    }

    @Override
    public Routine retrieveRoutine(Routine routine) {
        return fitNus.retrieveRoutine(routine);
    }

    @Override
    public void viewRoutine(Routine r) {
        String argument = r.getName().fullName;
        String[] nameKeywords = argument.split("\\s+");
        filteredRoutine.setPredicate(
                new RoutineNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)
        ));
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Routine} backed by the internal list of
     * {@code versionedFitNus}
     */
    @Override
    public ObservableList<Routine> getFilteredRoutineList() {
        return filteredRoutine;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Exercise} backed by the internal list of
     * {@code versionedFitNus}
     */
    @Override
    public ObservableList<Exercise> getFilteredExerciseList() {
        return filteredExercises;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Body} backed by the internal list of
     * {@code versionedFitNus}
     */
    @Override
    public Body getFilteredBody() {
        return bodyCopy;
    }


    /**
     * Returns an unmodifiable view of the list of {@code Lesson} backed by the internal list of
     * {@code versionedFitNus}
     */
    @Override
    public ObservableList<Lesson> getFilteredLessonList() {
        return filteredLessons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code DailyCalorie} backed by the internal list of
     * {@code versionedFitNus}
     */
    @Override
    public ObservableList<DailyCalorie> getFilteredDailyCalorieList() {
        return filteredDailyCalories;
    }

    @Override
    public ObservableList<Slot> getFilteredSlotList() {
        return filteredSlots;
    }

    @Override
    public ObservableList<Slot> getFilteredSlotList(String day) {
        SlotDayPredicate predicate = new SlotDayPredicate(Arrays.asList(day));

        ObservableList<Slot> slotObservableList = this.fitNus.getSlotList();
        FilteredList<Slot> filteredSlotsByDay = new FilteredList<>(slotObservableList);

        requireNonNull(predicate);
        filteredSlotsByDay.setPredicate(predicate);
        return filteredSlotsByDay;
    }

    @Override
    public void updateFilteredRoutineList(Predicate<Routine> predicate) {
        requireNonNull(predicate);
        filteredRoutine.setPredicate(predicate);
    }

    @Override
    public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
        requireNonNull(predicate);
        filteredExercises.setPredicate(predicate);
    }

    @Override
    public void updateFilteredLessonList(Predicate<Lesson> predicate) {
        requireNonNull(predicate);
        filteredLessons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return fitNus.equals(other.fitNus)
                && userPrefs.equals(other.userPrefs)
                && filteredLessons.equals(other.filteredLessons);
    }

}
