package org.example.File;//Вам нужно создать файловый менеджер. Приложение должно уметь:
//        просматривать содержимое директории;
//        создавать файлы и директории;
//        переименовывать файлы или директории;
//        полностью перемещать файлы или директории;
//        удалять файлы или директории.
//        Пользователю необходимо выбрать, какую операцию он хочет выполнить с файлом или папкой,
//        а затем ввести путь к нужному файлу.

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while_loop:
        while (true) {
            printMenu();
            String command = scanner.nextLine();
            System.out.println("Введите путь к файлу/директории: ");
            String enteredPath = scanner.nextLine();
            Path path = Paths.get(enteredPath);
            // Объявите переменную path с типом Path.

            // Выполните действия в зависимости от введённой команды.
            switch (command) {
                case "exit":
                    System.out.println("Выход.");
                    break while_loop;
                case "ls":
                    try {
                        System.out.println(Files.list(path).map(Path::getFileName).collect(Collectors.toList()));
                        // выведите список элементов директории
                    }
                    catch (IOException e) {
                        System.out.println("Произошла ошибка при запросе содержимого директории.");
                        e.printStackTrace();
                    }
                    break;

                case "mkdir":
                    try {
                        // создайте директорию
                        Files.createDirectory(path);
                    }
                    catch (IOException e) {
                        System.out.println("Произошла ошибка при создании директории.");
                        e.printStackTrace();
                    }
                    break;
                case "touch":
                    try {
                        Files.createFile(path);
                        // создайте файл
                    }
                    catch (IOException e) {
                        System.out.println("Произошла ошибка при создании файла.");
                        e.printStackTrace();
                    }
                    break;
                case "rename":
                    System.out.println("Введите новое имя файла/директории: ");
                    String newName = scanner.nextLine();

                    try {
                        // переименуйте файл
                        Path resolved = path.resolveSibling(newName);
                        Files.move(path, resolved, StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch (IOException e) {
                        System.out.println("Произошла ошибка при переименовании файла/директории.");
                        e.printStackTrace();
                    }
                    break;
                case "rm_file":
                    try {
                        if (!Files.isDirectory(path)) {
                            // удалите файл
                            Files.deleteIfExists(path);
                        }
                        else {
                            System.out.println("С помощью этой команды можно удалить только файл!");
                        }
                    }
                    catch (IOException e) {
                        System.out.println("Произошла ошибка при удалении файла.");
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Извините, такой команды пока нет.");
            }

        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("ls - посмотреть содержимое директории.");
        System.out.println("mkdir - создать директорию.");
        System.out.println("touch - создать файл.");
        System.out.println("rename - переименовать директорию/файл.");
        System.out.println("rm_file - удалить файл.");
        System.out.println("exit - выход.");
    }
}