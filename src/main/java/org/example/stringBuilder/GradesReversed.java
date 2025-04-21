package org.example.stringBuilder;

//Программа по учёту оценок сломалась, но в нашей части системы сохранились бэкапы!
// Проведите преобразование, обратное тому, которое было в предыдущем задании.
// На вход теперь подаётся массив строк:
//Вероника Чехова физика — Безупречно
//Анна Строкова математика — Потрясающе
//Иван Петров геометрия — Безупречно
//Требуется превратить их в одну запись вида "имя,фамилия,предмет,оценка;имя,фамилия,предмет,оценка".
// Метод для перевода оценки в строку-число уже реализован.

import java.util.Arrays;

public class GradesReversed {

    public static void main(String[] args) {
        String[] students = {
                "Вероника Чехова физика — Безупречно", //5
                "Анна Строкова математика — Потрясающе", //4
                "Иван Петров геометрия — Безупречно"
        };
        GradesReversed gr = new GradesReversed();
        System.out.println(gr.serializeGrades(students));
    }

    private String gradeStringToInt(String grade) {
        if (grade.contains("Безупречно")) {
            return "5";
        } else return "4";
    }

    public String serializeGrades(String[] grades) {
        StringBuilder stringBuilder = new StringBuilder();
        GradesReversed gr = new GradesReversed();
        for (int i = 0; i < grades.length; i++) {
            String[] str = grades[i].split("—");
            stringBuilder.append(str[0].replace(" ", ",").toLowerCase()).append(gr.gradeStringToInt(grades[i]));
            if (i + 1 < grades.length) {
                stringBuilder.append(";");
            }
        }
        return String.valueOf(stringBuilder);
    }

}
