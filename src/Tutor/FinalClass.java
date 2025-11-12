package Tutor;

final class A {
 void method() {
     System.out.println("Method from final class A");
 }
}

public class FinalClass {
 public static void main(String[] args) {
     A obj = new A();
     obj.method();
 }
}
