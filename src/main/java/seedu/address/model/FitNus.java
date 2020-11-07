package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.calorie.CalorieLog;
import seedu.address.model.calorie.DailyCalorie;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.UniqueExerciseList;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.UniqueLessonList;
import seedu.address.model.person.Body;
import seedu.address.model.person.Height;
import seedu.address.model.person.Weight;
import seedu.address.model.routine.Routine;
import seedu.address.model.routine.UniqueRoutineList;
import seedu.address.model.slot.Slot;
import seedu.address.model.timetable.Timetable;

/**
 * Wraps all data at the fitNUS level
 * Duplicates are not allowed (by .isSameExercise comparison)
 */
public class FitNus implements ReadOnlyFitNus {

    private final UniqueExerciseList exercises;
    private final UniqueRoutineList routines;
    private final UniqueLessonList lessons;
    private final Timetable timetable;
    private final CalorieLog calorieLog;
    private Body body;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        exercises = new UniqueExerciseList();
        routines = new UniqueRoutineList();
        lessons = new UniqueLessonList();
        timetable = new Timetable();
        calorieLog = new CalorieLog();
        body = new Body();
    }

    public FitNus() {
    }

    /**
     * Creates a fitNUS using the data in the {@code toBeCopied}
     */
    public FitNus(ReadOnlyFitNus toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// user-level operations

    /**
     * Adds the height of the user to fitNUS.
     */
    public void addHeight(Height height) {
        Body newBody = this.body;
        newBody.setHeight(height);
    }

    /**
     * Adds the weight of the user to fitNUS.
     */
    public void addWeight(Weight weight) {
        Body newBody = this.body;
        newBody.setWeight(weight);
    }

    /**
     * Returns the height of the user.
     */
    public Height getHeight() {
        Body newBody = this.body;
        return newBody.getHeight();
    }

    /**
     * Returns the weight of the user.
     */
    public Weight getWeight() {
        Body newBody = this.body;
        return newBody.getWeight();
    }

    /**
     * Returns the BMI of the user.
     */
    public double getBmi() {
        Body newBody = body;
        return newBody.getBmi();
    }

    /**
     * Returns the number of calories for today.
     */
    public int getCalories() {
        return calorieLog.getCalories();
    }

    //// list overwrite operations

    /**
     * Replaces the contents of exercise list with {@code exercises}.
     * {@code exercises} must not contain duplicate exercises.
     */
    public void setExercises(List<Exercise> exercises) {
        this.exercises.setExercises(exercises);
    }

    /**
     * Replaces the contents of routines list with {@code routines}.
     * {@code routines} must not contain duplicate routines.
     */
    public void setRoutines(List<Routine> routines) {
        this.routines.setRoutines(routines);
    }

    /**
     * Replaces the contents of lesson list with {@code lessons}.
     * {@code lessons} must not contain duplicate lessons.
     */
    public void setLessons(List<Lesson> lessons) {
        this.lessons.setLessons(lessons);
    }

    /**
     * Replaces the contents of slot list with {@code slots}.
     * {@code slots} must not contain duplicate slots or overlapping slots.
     */
    public void setSlots(List<Slot> slots) {
        timetable.setSlots(slots);
    }

    /**
     * Resets the existing data of this {@code FitNus} with {@code newData}.
     */
    public void resetData(ReadOnlyFitNus newData) {
        requireNonNull(newData);

        setExercises(newData.getExerciseList());
        setLessons(newData.getLessonList());
        setRoutines(newData.getRoutineList());
        setSlots(newData.getSlotList());
        addCalorieEntries(newData.getDailyCalorieList());
        this.body = newData.getBody();
//        Body newBody = newData.getBody().get(0);
//        addHeight(newBody.getHeight());
//        addWeight(newBody.getWeight());
    }

    //// person-level operations

    /**
     * Returns true if an exercise with the same identity as {@code exercise} exists in fitNUS.
     */
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return exercises.contains(exercise);
    }

    /**
     * Returns true if a routine with the same identity as {@code routine} exists in fitNUS.
     */
    public boolean hasRoutine(Routine r) {
        requireNonNull(r);
        return routines.contains(r);
    }

    /**
     * Returns true if a lesson with the same identity as {@code lesson} exists in fitNUS.
     */
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessons.contains(lesson);
    }

    /**
     * Returns true if a slot with the same identity as {@code slot} exists in the timetable.
     */
    public boolean hasSlot(Slot slot) {
        requireNonNull(slot);
        return timetable.hasSlot(slot);
    }

    /**
     * Returns true if a slot has an overlapping duration with another {@code slot} in the timetable.
     */
    public boolean hasOverlappingDurationInSlot(Slot slot) {
        requireNonNull(slot);
        return timetable.hasOverlapDuration(slot);
    }

    /**
     * Returns true if a dailyCalorie entry with the same identity as {@code dailyCalorie} exists in fitNUS.
     */
    public boolean hasDailyCalorie(DailyCalorie dailyCalorie) {
        requireNonNull(dailyCalorie);
        return calorieLog.contains(dailyCalorie);
    }

    /**
     * Adds an exercise to fitNUS.
     * The exercise must not already exist in fitNUS.
     */
    public void addExercise(Exercise e) {
        exercises.add(e);
    }

    /**
     * Adds a routine to fitNUS.
     * The routine must not already exist in fitNUS.
     */
    public void addRoutine(Routine routine) {
        routines.add(routine);
    }

    /**
     * Adds a lesson to fitNUS.
     * The lesson must not already exist in fitNUS.
     */
    public void addLesson(Lesson e) {
        lessons.add(e);
    }

    /**
     * Adds an exercise in fitNUS to an existing routine.
     * The exercise must not already exist in the routine.
     */
    public void addExerciseToRoutine(Routine r, Exercise e) {
        requireNonNull(r);
        requireNonNull(e);

        Exercise retrievedExercise = exercises.retrieveExercise(e);
        Routine retrievedRoutine = routines.retrieveRoutine(r);
        routines.addExercise(retrievedRoutine, retrievedExercise);
    }

    /**
     * Adds a slot to the timetable in fitNUS.
     * The slot must not already exist nor overlap with any of the slots in the timetable.
     */
    public void addSlotToTimetable(Slot slot) {
        timetable.addSlot(slot);
    }

    /**
     * Adds all the daily calorie entries into the calorie log.
     * @param entries Collection of all the entries of daily calorie.
     */
    public void addCalorieEntries(List<DailyCalorie> entries) {
        calorieLog.setCalorieLog(entries);
    }

    /**
     * Adds calories into today's calorie log.
     * @param calories The amount of calories that the user wants to add.
     */
    public void addCalories(int calories) {
        calorieLog.addCalories(calories);
    }

    /**
     * Deducts calories from today's calorie log.
     * @param calories The amount of calories that the user wants to deduct.
     */
    public void minusCalories(int calories) {
        calorieLog.minusCalories(calories);
    }

    /**
     * Replaces the given exercise {@code target} in the list with {@code editedExercise}.
     * {@code target} must exist in FitNUS.
     * The exercise identity of {@code editedExercise} must not be the same as another existing exercise in FitNUS.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireNonNull(editedExercise);

        exercises.setExercise(target, editedExercise);
        routines.setExercise(target, editedExercise);
    }

    /**
     * Replaces the given lesson {@code target} in the list with {@code editedLesson}.
     * {@code target} must exist in FitNUS.
     * The lesson identity of {@code editedLesson} must not be the same as another existing lesson in FitNUS.
     */
    public void setLesson(Lesson target, Lesson editedLesson) {
        requireNonNull(editedLesson);

        lessons.setLesson(target, editedLesson);
        timetable.setSlot(target, editedLesson);
    }

    /**
     * Removes {@code key} from {@code fitNUS}.
     * {@code key} must exist in fitNUS.
     */
    public void removeExercise(Exercise key) {
        exercises.remove(key);
        routines.deleteExercise(key);
    }

    /**
     * Removes {@code key} from {@code fitNUS}.
     * {@code key} must exist in fitNUS.
     */
    public void removeRoutine(Routine key) {
        routines.remove(key);
        timetable.deleteSlot(key);
    }

    /**
     * Removes {@code key} from {@code fitNUS}.
     * {@code key} must exist in fitNUS.
     */
    public void removeLesson(Lesson key) {
        lessons.remove(key);
        timetable.deleteSlot(key);
    }

    /**
     * Removes {@code exercise} from {@code routine}.
     * {@code exercise} must exist in {@code routine}.
     */
    public void deleteExerciseFromRoutine(Routine routine, Exercise exercise) {
        requireNonNull(routine);
        requireNonNull(exercise);

        Exercise retrievedExercise = exercises.retrieveExercise(exercise);
        Routine retrievedRoutine = routines.retrieveRoutine(routine);
        routines.deleteExerciseFromRoutine(retrievedRoutine, retrievedExercise);
    }

    /**
     * Removes {@code key} from timetable in {@code fitNUS}.
     * {@code key} must exist in timetable.
     */
    public void removeSlotFromTimetable(Slot key) {
        timetable.deleteSlot(key);
    }

    /**
     * Retrieves the routine object from UniqueRoutineList that the user specified.
     *
     * @param routine Routine object that the user wants.
     * @return Routine object that exists within fitNUS that the user is looking for.
     */
    public Routine retrieveRoutine(Routine routine) {
        return routines.retrieveRoutine(routine);
    }

    /**
     * Retrieves the lesson object from UniqueLessonList that the user specified.
     *
     * @param lesson Lesson object that the user wants.
     * @return Lesson object that exists within fitNUS that the user is looking for.
     */
    public Lesson retrieveLesson(Lesson lesson) {
        return lessons.retrieveLesson(lesson);
    }

    /**
     * Checks the index given is within the bounds of Routine.
     *
     * @param index index that is input by user.
     * @return False if out of bounds.
     */
    public boolean checkBounds(int index) {
        return index > 0 && index <= routines.checkSize();
    }

    //// util methods

    @Override
    public String toString() {
        return lessons.asUnmodifiableObservableList().size() + " lessons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Exercise> getExerciseList() {
        return exercises.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Routine> getRoutineList() {
        return routines.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Lesson> getLessonList() {
        return lessons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Slot> getSlotList() {
        return timetable.getSlotList();
    }

    @Override
    public ObservableList<DailyCalorie> getDailyCalorieList() {
        return calorieLog.asUnmodifiableObservableList();
    }

    @Override
    public Body getBody() {
//        ObservableList<Body> unmodifiableBody = FXCollections.unmodifiableObservableList(body);
        return this.body;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FitNus // instanceof handles nulls
                && lessons.equals(((FitNus) other).lessons));
    }

    @Override
    public int hashCode() {
        return lessons.hashCode();
    }
}
