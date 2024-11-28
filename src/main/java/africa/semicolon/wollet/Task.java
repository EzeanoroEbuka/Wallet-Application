package africa.semicolon.wollet;

public class Task {

    public static void main(String[] args) {
        int number1 = 456;
        int number2 = 123;
        swapNumber(number1,number2);

    }

    public static void swapNumber(int number1, int number2){
        number2 = number2 + number1;
        number1 = number2 - number1;
        number2 = number2 - number1;

        System.out.println("number1 = " + number1);
        System.out.println("number2 = " + number2);
    }
}
