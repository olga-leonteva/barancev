package ru.stqa.pft.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Olga");


        Square s = new Square(5); //задаем новый объект типа Square
        System.out.println( "Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    }
    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + " !");
    }





}