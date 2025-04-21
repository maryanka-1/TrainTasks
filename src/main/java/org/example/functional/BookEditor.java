package org.example.functional;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Перед вами класс BookEditor, который обрабатывает текст книги как набор строк.
// Основной метод этого класса — processText(List<String> sourceText).
// Этот метод возвращает преобразованный набор строк, соответствующий правилам форматирования книг,
// принятым в издательстве.
//Отдельно преобразуется заголовок, отдельно — каждая строка текста.
// В BookEditor хранятся специальные обработчики — за них отвечают объекты интерфейсов
// HeaderDecorator для форматирования заголовка и LineProcessor для строк.
//Мы определили реализацию каждого из обработчиков в обычных Java-классах — ToUpperCaseHeaderDecorator
// приводит название книги к верхнему регистру, а CapitalizeFirstLetterProcessor делает
// первую букву в каждой строке заглавной.
//Ваша задача — переписать код с использованием лямбда-функций вместо классов
// ToUpperCaseHeaderDecorator и CapitalizeFirstLetterProcessor,
// а также добавить новый обработчик строк, который будет добавлять в конец
// каждой строки символ переноса строки \n.

public class BookEditor {

    private HeaderDecorator headerDecorator;
    private List<LineProcessor> lineProcessors = new ArrayList<>();

    public static void main(String[] args) {
        BookEditor bookEditor = new BookEditor();


        bookEditor.setHeaderDecorator(header -> header.toUpperCase() + "\n");
        bookEditor.addLineProcessor(line -> line.substring(0, 1).toUpperCase() + line.substring(1));

        List<String> content = Arrays.asList(
                "Приключения Java-программиста",
                "История началась рано утром, ",
                "когда программист вышел из дома, ",
                "решив выпить утренний кофе."
        );

        List<String> resultContent = bookEditor.processText(content);
        System.out.println(resultContent);
    }

    public List<String> processText(List<String> sourceText) {
        List<String> resultText = new ArrayList<>();

        String sourceHeader = sourceText.get(0);
        String decoratedHeader = headerDecorator.decorate(sourceHeader);
        resultText.add(decoratedHeader);

        for (int i = 1; i < sourceText.size(); i++) {
            String currentLine = sourceText.get(i);
            for (LineProcessor processor : lineProcessors) {
                currentLine = processor.processLine(currentLine);
            }
            resultText.add(currentLine);
        }

        return resultText;
    }

    public void setHeaderDecorator(HeaderDecorator headerDecorator) {
        this.headerDecorator = headerDecorator;
    }

    public void addLineProcessor(LineProcessor lineProcessor) {
        this.lineProcessors.add(lineProcessor);
    }
}

interface HeaderDecorator {
    String decorate(String header);
}

interface LineProcessor {
    String processLine(String line);
}

