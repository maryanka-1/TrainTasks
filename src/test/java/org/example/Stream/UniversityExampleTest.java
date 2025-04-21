package org.example.Stream;

import org.example.Letters.LettersComparator;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UniversityExampleTest {
    private final List<Student> students = new ArrayList<>();
    private final List<Student> specialStudent = new ArrayList<>();
    private final Set<String> examPassedNames = new HashSet<>();
    private final Map<Integer, String> groupNames = new HashMap<>();
    private UniversityExample university = new UniversityExample();
    private List<String> graduatesClub = new ArrayList<>();

    @BeforeEach
    void setUp() {
        examPassedNames.add("Иванов Иван");
        examPassedNames.add("Попова Яна");
        //соответствие года поступления и названия группы
        groupNames.put(2020, "2020-ГР1");
        groupNames.put(2021, "2021-ГР0");
        //список с адресами email выпускников

        //студенты, планирующие завершить обучение
        students.add(new Student("Попова", "Яна", "yana@yandex.ru", 2021,
                List.of(new Course("Эконом. безопасность"),
                        new Course("Инвестиционные риски"),
                        new Course("Производные инструменты"))));
        students.add(new Student("Иванов", "Иван", "ivan_ivanov@mail.ru", 2020,
                List.of(new Course("Эконом. безопасность"),
                        new Course("Бух. учет"),
                        new Course("Налогообложение"))));
        students.add(new Student("Сергеев", "Дмитрий", "iamdmitry@gmail.com", 2021,
                List.of(new Course("Эконом. безопасность"),
                        new Course("Банковское дело"),
                        new Course("Международная экономика"))));
        specialStudent.add(new Student("Пупкин", "Василий", "vasya@gmail.com", 2020,
                List.of()));
        specialStudent.add(new Student("Алексин", "Алексей", "aleksin@gmail.com", 2020,
                List.of()));
    }

    @Test
    public void shouldGetPopularCourse() {
        Course expected = new Course("Эконом. безопасность");
        Course actual = university.getPopularCourse(students);
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void shouldGetPopularCourseIsNull() {
        Course actual = university.getPopularCourse(specialStudent);
        assertThat(actual).isNull();
    }

    @Test
    public void shouldGetStudents() {
        List<Student> expected = new ArrayList<>(Arrays.asList(
                new Student("Попова", "Яна", "yana@yandex.ru", 2021, "2021-ГР0"),
                new Student("Иванов", "Иван", "ivan_ivanov@mail.ru", 2020, "2020-ГР1")
        ));
        List<Student> actual = university.getStudents(students, examPassedNames, groupNames, graduatesClub);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCourseForAllStudents() {
        String expected = "Эконом. безопасность";
        ByteArrayOutputStream actual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actual));
        university.courseForAllStudent(students);
        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    public void shouldCourseForAllStudentNotFound() {
        String expected = "Нет общего курса";
        ByteArrayOutputStream actual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actual));
        university.courseForAllStudent(specialStudent);
        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    public void shouldGetStudentsByCourse() {
        Course course = new Course("Бух. учет");
        List<String> expected = new ArrayList<>(List.of("Иванов"));
        List<String> actual = university.getStudentsByCourse(students, course);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldTop3Students() {
        List<String> expected = new ArrayList<>(List.of("Попова", "Иванов", "Сергеев"));
        List<String> actual = university.top3Students(students);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldGetUniqueAttendedCourse() {
        List<Course> expected = new ArrayList<>(Arrays.asList(
                new Course("Эконом. безопасность"),
                new Course("Инвестиционные риски"),
                new Course("Производные инструменты"),
                new Course("Бух. учет"),
                new Course("Налогообложение"),
                new Course("Банковское дело"),
                new Course("Международная экономика")
        ));
        List<Course> actual = university.getUniqueAttendedCourse(students);
        assertThat(actual).isEqualTo(expected);
    }

}