import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Введите опреацию с числами: ");
        Scanner scn = new Scanner(System.in);
        String st = scn.nextLine();

        calc(st);
    }
    public static String calc(String input) throws IOException {
        String[] dop = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] symb = {"/", "+", "*", "-"};
        //Scanner scn = new Scanner(System.in);
        Roman rr = new Roman();
        int res = 0;
        //System.out.println("Введите опреацию с числами: ");
        //String st = scn.nextLine();
        if (!(input.contains(".") | input.contains(","))) {
            if (input.length() >= 5) {
                String[] str = input.split(" ");
                if (rr.isRoman(str[0]) == rr.isRoman(str[2])) {

                    boolean tg = rr.isRoman(str[0]);
                    if (tg) {
                        str[0] = Integer.toString(rr.romanToInt(str[0]));
                        str[2] = Integer.toString(rr.romanToInt(str[2]));
                    }
                    if (Arrays.asList(symb).contains(str[1])) {
                        int countSymb = 0;
                        for (int i = 0; i < input.length(); i++){
                            if (Arrays.asList(symb).contains(Character.toString(input.toCharArray()[i]))) countSymb += 1;
                        }
                        if (countSymb == 1) {
                            if (Arrays.asList(dop).contains(str[0]) & Arrays.asList(dop).contains(str[2])) {
                                switch (str[1]) {
                                    case "+":
                                        res = Integer.parseInt(str[0]) + Integer.parseInt(str[2]);
                                        break;
                                    case "-":
                                        res = Integer.parseInt(str[0]) - Integer.parseInt(str[2]);
                                        break;
                                    case "/":
                                        res = Integer.parseInt(str[0]) / Integer.parseInt(str[2]);
                                        break;
                                    case "*":
                                        res = Integer.parseInt(str[0]) * Integer.parseInt(str[2]);
                                        break;
                                }
                                if (tg) {
                                    if (res >= 1) System.out.println(rr.intToRoman(Integer.toString(res)));
                                    else {
                                        System.out.println("В римской системе нет отрицательных числе");
                                        throw new ArithmeticException();
                                    }
                                } else System.out.println(res);
                            } else {
                                System.out.println("Используются числа не от 1 до 10");
                                throw new IOException();
                            }
                        } else {
                            System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                            throw new IOException();
                        }
                    }else {
                        System.out.println("Строка содержит недопустимые операции");
                        throw new IOException();
                    }
                } else {
                    System.out.println("Числа в разных системах счисления");
                    throw new NumberFormatException();
                }
            } else {
                System.out.println("Ошибка ввода");
                throw new IOException();
            }
        }else {
            System.out.println("Калькулятор работает только с целыми числами");
            throw new NumberFormatException();
        }
        return input;
    }
}

class Roman{
    public int romanToInt(String romanNum) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);


        int end = -1;
        char[] arr = romanNum.toCharArray();
        end = (arr.length - 1);
        int result = map.get(arr[end]);
        for (int i = (end-1); i>= 0; i--){
            if (map.get(arr[i]) >= map.get(arr[i+1])) {
                result += map.get(arr[i]);
            }
            else{
                result -= map.get(arr[i]);
            }
        }
        return result;
    }

    public StringBuilder intToRoman(String arab1) {
        Map<Integer, Character> map = new HashMap<>();
        map.put(1, 'I');
        map.put(5, 'V');
        map.put(10, 'X');
        map.put(50, 'L');
        map.put(100, 'C');
        map.put(500, 'D');
        map.put(1000, 'M');
        int arab = Integer.parseInt(arab1);
        int provarab = Integer.parseInt(arab1)+1;
        StringBuilder str = new StringBuilder();
        StringBuilder str1 = new StringBuilder();
        int[] numbers = {1, 5, 10, 50, 100, 500, 1000};
        for (int i = 6; i >= 0; i--){
            boolean b = arab / numbers[i] != 0;
            if (b){
                int gg = arab / numbers[i];
                for (int j = arab / numbers[i]; j>=1; j--) str.append(Character.toString(map.get(numbers[i])));
                arab -= gg*numbers[i];
            }

        }
        for (int i = 6; i >= 0; i--){
            boolean b = provarab / numbers[i] != 0;
            if (b){
                int gg = provarab / numbers[i];
                for (int j = provarab / numbers[i]; j>=1; j--) str1.append(Character.toString(map.get(numbers[i])));
                provarab -= gg*numbers[i];
            }

        }
        str1 = new StringBuilder(str1.substring(0, str1.length() - 1) + "I" + str1.substring(str1.length() - 1, str1.length()));
        if (str1.toString().length() < str.toString().length()) return str1;
        else return str;
    }

    public boolean isRoman(String num){
        int intValue;
        try {
            intValue = Integer.parseInt(num);
            return false;
        }catch (NumberFormatException e) {
            return true;
        }
    }

}