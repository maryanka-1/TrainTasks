package org.example.ArrayListHandMade;

public class HandMadeArrayList<T> {

    private int size = 0;
    private Object[] elements;

    public HandMadeArrayList() {
        this.elements = new Object[10];
    }

    //    /
//            * Сначала проверяем, достиг ли размер массива вместимости.
//            * Если достиг — увеличиваем вместимость, иначе сразу добавляем элемент
//     */
    public void add(T newElement) {
        // Допишите проверку
        if (size == elements.length) {
            grow();
        }
        elements[size] = newElement;
        size++;


        /* Допишите код, который добавит очередной элемент в массив
           и увеличит размер массива на единицу.*/
    }
    public void add(T newElement, int index) {
        if (index >= elements.length) {
            grow();
        }
        for (int i = size; i >index; i--) {
            elements[i] = elements[i-1];
        }
        elements[index] = newElement;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Element with index " + index + " does not exist");
        }
        return (T) this.elements[index];
    }
    //
//    /
//            * Заменяем текущий массив элементов elements на новый с вместимостью +50%
//            */
    private void grow() {
        // Новый массив
        Object[] newArray = new Object[(int) (elements.length *1.5 + 1)];
        for(int i = 0; i < elements.length; i++) {
            newArray[i] = elements[i];
        }
        // Допишите цикл, который копирует все элементы из массива elements в новый массив newArray
        // Копируем элементы из старого массива в новый


        // Возвращаемый новый массив
        this.elements = newArray;
    }

    public static void main(String[] args) {
        final var ar = new HandMadeArrayList<Integer>();
        for (int i = 0; i < 2000; i++) {
            ar.add(i);
        }
        System.out.println(ar.size);
    }
}
