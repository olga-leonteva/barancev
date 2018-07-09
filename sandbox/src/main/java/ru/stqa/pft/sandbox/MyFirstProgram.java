package ru.stqa.pft.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        System.out.println("Hello, world!");

        System.out.println(2 + 2);
        System.out.println(2 * 2);
        System.out.println(2 / 2);
        System.out.println(2 - 2);

        System.out.println(1 / 2); // результат будет 0, тк идет округление до ближайшего целого
        System.out.println(1.0 / 2); // результат 0,5 , тк указано число с плавающей точкой
        System.out.println(1 / 2.0);
        System.out.println(2.0 / 2);

        System.out.println("2" + "2"); // склеивание строк - конкатенация
        System.out.println("2" + 2); //число преобразуется в строку и значение будет 22

        System.out.println("2 + 2 = " + (2 + 2));


    }
}