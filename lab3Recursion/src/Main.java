public class Main {
    public static void main(String[] args) {

        // Перевірка факторіалу
        int n = 5;
        FactorialUsingRecursion fcR = new FactorialUsingRecursion();
        int factorialRecursive = fcR.recursionExecute(n);
        FactorialUsingLoop fcL = new FactorialUsingLoop();
        int factorialCycle = fcL.cycleExecute(n);
        assert factorialRecursive == factorialCycle : "Different values";
        System.out.println("Factorial of " + n + ": " + factorialRecursive);

        // Перевірка чисел Фібоначчі
        n = 10;
        FibonacciUsingRecursion fibR = new FibonacciUsingRecursion();
        int fibonacciRecursive = fibR.recursionExecute(n);
        FibonacciUsingLoop fibL = new FibonacciUsingLoop();
        int fibonacciCycle = fibL.cycleExecute(n);
        assert fibonacciRecursive == fibonacciCycle : "Different values";
        System.out.println("Fibonacci number " + n + ": " + fibonacciRecursive);

        // Перевірка суми цифр числа
        n = 12345;
        SumOfDigitsUsingRecursion dsc = new SumOfDigitsUsingRecursion();
        int digitSum = dsc.execute(n);
        assert digitSum == 15 : "Different values";
        System.out.println("Digit sum of " + n + ": " + digitSum);

        // Перевірка суми позитивних цілих чисел
        int a = 3;
        int b = 5;
        SumWithoutPlusOperatorUsingRecursion psc = new SumWithoutPlusOperatorUsingRecursion();
        int integerSum = psc.execute(a, b);
        assert integerSum == 8 : "Different values";
        System.out.println("Sum of " + a + " and " + b + ": " + integerSum);
    }
}

class FactorialUsingLoop {
    public int cycleExecute(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

class FactorialUsingRecursion {
    public int recursionExecute(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * recursionExecute(n - 1);
        }
    }
}
class FibonacciUsingLoop {
    public int cycleExecute(int n) {
        int a = 0, b = 1, result = 0;
        if (n == 0) {
            return a;
        }
        for (int i = 2; i <= n; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return b;
    }
}
class FibonacciUsingRecursion {
    public int recursionExecute(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            return recursionExecute(n - 1) + recursionExecute(n - 2);
        }
    }
}
class SumOfDigitsUsingRecursion {
    public int execute(int n) {
        if (n == 0) {
            return 0;
        } else {
            return (n % 10) + execute(n / 10);
        }
    }
}
class SumWithoutPlusOperatorUsingRecursion {
    public static int execute(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            int sum = a ^ b;
            int carry = (a & b) << 1;
            return execute(sum, carry);
        }
    }
}