package seedu.address.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.DailyCalorie;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.Routine;
import seedu.address.model.person.Slot;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "fitnus")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    private static final String MESSAGE_DUPLICATE_ROUTINE = "Routines list contains duplicate routine(s).";
    private static final String MESSAGE_DUPLICATE_SLOT = "Slot list contains duplicate slot(s).";
    private static final String MESSAGE_DUPLICATE_DAILYCALORIE = "Calorie log contains duplicate calorie log(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedExercise> exercises = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();
    private final List<JsonAdaptedRoutine> routines = new ArrayList<>();
    private final List<JsonAdaptedSlot> slots = new ArrayList<>();
    private final List<JsonAdaptedDailyCalorie> dailyCalories = new ArrayList<>();

    private double height;
    private double weight;

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("exercises") List<JsonAdaptedExercise> exercises,
                                       @JsonProperty("lessons") List<JsonAdaptedLesson> lessons,
                                       @JsonProperty("routines") List<JsonAdaptedRoutine> routines,
                                       @JsonProperty("slots") List<JsonAdaptedSlot> slots,
                                       @JsonProperty("dailyCalories") List<JsonAdaptedDailyCalorie> dailyCalories,
                                       @JsonProperty("height") double height,
                                       @JsonProperty("weight") double weight) {
        this.persons.addAll(persons);
        this.exercises.addAll(exercises);
        this.lessons.addAll(lessons);
        this.routines.addAll(routines);
        this.slots.addAll(slots);
        this.dailyCalories.addAll(dailyCalories);
        this.height = height;
        this.weight = weight;
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        exercises.addAll(source.getExerciseList().stream().map(JsonAdaptedExercise::new).collect(Collectors.toList()));
        lessons.addAll(source.getLessonList().stream().map(JsonAdaptedLesson::new).collect(Collectors.toList()));
        routines.addAll(source.getRoutineList().stream().map(JsonAdaptedRoutine::new).collect(Collectors.toList()));
        slots.addAll(source.getSlotList().stream().map(JsonAdaptedSlot::new).collect(Collectors.toList()));
        dailyCalories.addAll(source.getDailyCalorieList().stream().map(JsonAdaptedDailyCalorie::new)
                .collect(Collectors.toList()));
        height = source.getHeight();
        weight = source.getWeight();
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (JsonAdaptedExercise jsonAdaptedExercise : exercises) {
            Exercise exercise = jsonAdaptedExercise.toModelType();
            if (addressBook.hasExercise(exercise)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addExercise(exercise);
        }
        for (JsonAdaptedLesson jsonAdaptedLesson : lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType();
            if (addressBook.hasLesson(lesson)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addLesson(lesson);
        }
        for (JsonAdaptedRoutine jsonAdaptedRoutine : routines) {
            Routine routine = jsonAdaptedRoutine.toModelType();
            if (addressBook.hasRoutine(routine)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ROUTINE);
            }
            addressBook.addRoutine(routine);
        }
        for (JsonAdaptedSlot jsonAdaptedSlot : slots) {
            Slot slot = jsonAdaptedSlot.toModelType();
            if (addressBook.hasSlot(slot)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SLOT);
            }
            addressBook.addSlotToTimetable(slot);
        }

        List<DailyCalorie> calorieLog = new ArrayList<>();

        for (JsonAdaptedDailyCalorie jsonAdaptedDailyCalorie: dailyCalories) {
            DailyCalorie dailyCalorie = jsonAdaptedDailyCalorie.toModelType();
            if (addressBook.hasDailyCalorie(dailyCalorie)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DAILYCALORIE);
            }
            calorieLog.add(dailyCalorie);
        }
        Collections.sort(calorieLog);
        addressBook.addCalorieEntries(calorieLog);

        addressBook.addHeight(height);
        addressBook.addWeight(weight);

        return addressBook;
    }

}
