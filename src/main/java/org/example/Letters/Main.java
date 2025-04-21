package org.example.Letters;

//Представьте, что вы работаете в крупной компании над программой для учёта всей входящей корреспонденции.
// В эту систему попадает информация о каждом письме, которое поступает в компанию.
// Письма хранятся в порядке занесения информации о них в систему.
// Вам нужно добавить новую функцию printOrderedByDateReceived — возможность отсортировать письма
// по дате их получения (от ранних к поздним).
// Используйте тот же формат вывода на консоль, что уже используется в программе.

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static Set<Letter> letters = new LinkedHashSet<>();

    public static void main(String[] args) {
        // информация о письмах (в порядке занесения в систему)
        letters.add(new Letter("Джон Смит", LocalDate.of(2021, 7, 7), "текст письма №1 ..."));
        letters.add(new Letter("Аманда Линс", LocalDate.of(2021, 6, 17), "текст письма №2 ..."));
        letters.add(new Letter("Джо Кью", LocalDate.of(2021, 7, 5), "текст письма №3 ..."));
        letters.add(new Letter("Мишель Фернандес", LocalDate.of(2021, 8, 23), "текст письма №4 ..."));

        printOrderedById(letters);
        printOrderedByDateReceived(letters);
        printFirstLetterByDate(letters);
        printLettersSpecificDate(letters, 2021, 10);
    }

    private static void printOrderedById(Set<Letter> letters) {
        System.out.println("Все письма с сортировкой по ID: ");

        for (Letter letter : letters) {
            System.out.println("    * Письмо от " + letter.authorName + " поступило " + letter.dateReceived);
        }
    }

    //сортировка от ранних и поздним по получению
    private static void printOrderedByDateReceived(Set<Letter> letters) {
        System.out.println("Все письма с сортировкой по дате получения: ");
        List<Letter> sortedByDate = new ArrayList<>(letters);
        LettersComparator lettersComparator = new LettersComparator();
        sortedByDate.sort(lettersComparator);
        for (Letter letter : sortedByDate) {
            System.out.println("    * Письмо от " + letter.authorName + " поступило " + letter.dateReceived);
        }
    }

    ////получение саммого раннего письма по дате
    private static void printFirstLetterByDate(Set<Letter> letters) {
        System.out.println("Первое письмо по дате получения: ");
        List<Letter> sortFirst = new ArrayList<>(letters);
        LettersComparator lettersComparator = new LettersComparator();
        sortFirst.sort(lettersComparator);
        System.out.println("    * Письмо от " + sortFirst.get(0).authorName + " поступило " + sortFirst.get(0).dateReceived);
    }

    // получение всех писем за период (год+мес)
    private static void printLettersSpecificDate(Set<Letter> letters, int year, int month) {
        String formatMonth = String.format("%02d", month);
        System.out.println("Все полученные письма за " + year + "-" + formatMonth + ": ");
        int count = 0;
        for (Letter letter : letters) {
            int yearRec = letter.getDateReceived().getYear();
            int monthRec = letter.getDateReceived().getMonthValue();

            if (yearRec == year && monthRec == month) {
                System.out.println("    * Письмо от " + letter.authorName + " поступило " + letter.dateReceived);
                count++;
            }
        }
        if(count == 0){
            System.out.println("     Писем не найдено");
        }
    }

    static class Letter {
        public String authorName;      // имя отправителя
        public LocalDate dateReceived; // дата получения письма
        public String text;            // текст письма

        public Letter(String senderName, LocalDate dateReceived, String text) {
            this.authorName = senderName;
            this.dateReceived = dateReceived;
            this.text = text;
        }

        public LocalDate getDateReceived() {
            return dateReceived;
        }
    }
}