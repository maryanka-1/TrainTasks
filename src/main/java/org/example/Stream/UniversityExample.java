package org.example.Stream;
//
//Частный университет решил автоматизировать процесс завершения студентами обучения.
//Для студентов, которые не планируют дальше учиться, выполняются следующие действия:
//Проверяется, сдал ли студент экзамен.
//Указывается группа, где учился студент (на основе года начала обучения).
//В список клуба выпускников добавляется email студента.
//Для решения этой задачи используются стрим и лямбда-функции.
//Ваша задача — реализовать недостающие функции так, чтобы поддержать логику кода.
//Обратите внимание — вам придётся использовать механизм замыканий,
//поскольку список названий групп и список клуба выпускников,
//с которыми вам придётся работать в лямбда-функциях, определены в теле метода main.
//

import java.util.*;
import java.util.stream.Collectors;

public class UniversityExample {


    public UniversityExample() {
    }

//    public static void main(String[] args) {
//        //множество студентов, успешно сдавших экзамен
//        Set<String> examPassedNames = new HashSet<>();
//        examPassedNames.add("Иванов Иван");
//        examPassedNames.add("Попова Яна");
//
//        //соответствие года поступления и названия группы
//        Map<Integer, String> groupNames = new HashMap<>();
//        groupNames.put(2020, "2020-ГР1");
//        groupNames.put(2021, "2021-ГР0");
//
//        //список с адресами email выпускников
//        List<String> graduatesClub = new ArrayList<>();
//        //студенты, планирующие завершить обучение
//        List<Student> students = new ArrayList<>();
//        students.add(new Student("Попова", "Яна", "yana@yandex.ru", 2021,
//                List.of(new Course("Эконом. безопасность"),
//                        new Course("Инвестиционные риски"),
//                        new Course("Производные инструменты"))));
//        students.add(new Student("Иванов", "Иван", "ivan_ivanov@mail.ru", 2020,
//                List.of(new Course("Эконом. безопасность"),
//                        new Course("Бух. учет"),
//                        new Course("Налогообложение"))));
//        students.add(new Student("Сергеев", "Дмитрий", "iamdmitry@gmail.com", 2021,
//                List.of(new Course("Эконом. безопасность"),
//                        new Course("Банковское дело"),
//                        new Course("Международная экономика"))));
//        students.add(new Student("Пупкин", "Василий", "vasya@gmail.com", 2020,
//                List.of(new Course("Физкультура"))));
//
//        List<Student> graduatedStudents = getStudents(students, examPassedNames, groupNames, graduatesClub);
//
//        List<Course> uniqueAttendedCourse = getUniqueAttendedCourse(students);
//
//        List<String> top3Students = top3Students(students);
//
//        Course popularCourse = getPopularCourse(students);
//
//
//        for (Student student : graduatedStudents) {
//            System.out.println(student);
//        }
//
//        for (String email : graduatesClub) {
//            System.out.println(email);
//        }
//
//        for (Course course : uniqueAttendedCourse) {
//            System.out.println(course.name);
//        }
//
//        for (String string: top3Students){
//            System.out.println(string);
//        }
//
//
//    }

    public Course getPopularCourse(List<Student> students) {
        Optional<Course> popularCourse = students.stream()
                .flatMap(student -> student.course.stream())
                .collect(Collectors.groupingBy(course -> course, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        if(popularCourse.isPresent()){
            Course course = popularCourse.get();
            return course;
        }
        return null;
    }

    public List<Student> getStudents(List<Student> students, Set<String> examPassedNames, Map<Integer, String> groupNames, List<String> graduatesClub) {
        return students.stream()
                .filter(student -> examPassedNames.contains(student.surname + " " + student.name))
                .peek(student -> student.groupName = groupNames.get(student.entranceYear))
                .peek(student -> graduatesClub.add(student.email))
                .collect(Collectors.toList());
    }

    public void courseForAllStudent(List<Student> students){

        students.stream()
                .flatMap(student -> student.course.stream())
                .collect(Collectors.groupingBy(course -> course, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry ->entry.getValue()==students.size())
                .map(Map.Entry::getKey)
                .findFirst()
                .ifPresentOrElse(
                        course -> System.out.print(course.name),
                        ()-> System.out.print("Нет общего курса")
                );

    }

    public List<String> getStudentsByCourse(List<Student> students, Course course){
        return students.stream()
                .filter(student -> student.course.contains(course))
                .map(student -> student.surname)
                .toList();
    }

    public List<String> top3Students(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparingInt(student -> -student.getCountCourse()))
                .limit(3)
                .map(student -> student.surname)
                .toList();
    }

    public List<Course> getUniqueAttendedCourse(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.course.stream())
                .distinct()
                .collect(Collectors.toList());
    }
}

class Student {
    String surname;
    String name;
    String email;
    int entranceYear;
    String groupName;
    List<Course> course;

    public Student(String surname, String name, String email, int entranceYear, List<Course> courses) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.entranceYear = entranceYear;
        this.course = courses;
    }

    public Student(String surname, String name, String email, int entranceYear, String groupName){
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.entranceYear = entranceYear;
        this.groupName = groupName;
    }

    public int getCountCourse() {
        return course.size();
    }


    @Override
    public String toString() {
        return "Student{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", entranceYear=" + entranceYear +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return entranceYear == student.entranceYear && Objects.equals(surname, student.surname) && Objects.equals(name, student.name) && Objects.equals(email, student.email) && Objects.equals(groupName, student.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, email, entranceYear, groupName);
    }
}

class Course {
    String name;

    public Course(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return name.equals(course.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
}