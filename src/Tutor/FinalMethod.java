package Tutor;

class A {
 final void method1() {
     System.out.println("This is a final method.");
 }
}

public class FinalMethod {
 public static void main(String[] args) {
     A obj = new A();
     obj.method1();
 }
}