import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input:");
        String given = scanner.nextLine().trim().replaceAll(" +", " ");
        System.out.println("Output:");
        System.out.println(calc(given));
    }

    public static String calc(String userInput) {
        String[] strings;
        strings = userInput.split(" ");
        if (strings.length != 3) {
            throw new IllegalArgumentException("Пожалуйста, введите пример формата 'a + b'");
        }
        String num1 = strings[0];
        String operator = strings[1];
        String num2 = strings[2];
        int res = 0;
        if (checkRoman(num1) && checkRoman(num2)) {
            int agent1 = convertToArabic(num1);
            int agent2 = convertToArabic(num2);
            if (agent1 > 0 && agent1 <= 10 && agent2 > 0 && agent2 <= 10) {
                if (operator.equals("+")) {
                    res = agent1 + agent2;
                } else if (operator.equals("-")) {
                    res = agent1 - agent2;
                } else if (operator.equals("*")) {
                    res = agent1 * agent2;
                } else if (operator.equals("/")) {
                    res = agent1 / agent2;
                } else {
                    throw new IllegalArgumentException("Введите верный оператор");
                } if(res <= 0){
                    throw new IllegalArgumentException("Результат операции с римскими числами должен быть положительным");
                } else{
                    String result = convertToRoman(res);
                    return result;
                }
            } else {
                throw new IllegalArgumentException("Используйте числа от I до X");
            }
        } if (parseNumber(num1) >= 0 && parseNumber(num1) <= 10 && parseNumber(num2) > 0 && parseNumber(num2) <= 10){
            int agent1s = parseNumber(num1);
            int agent2s = parseNumber(num2);
            if (operator.equals("+")){
                res = agent1s + agent2s;
            } else if (operator.equals("-")){
                res = agent1s - agent2s;
            } else if (operator.equals("*")){
                res = agent1s * agent2s;
            } else if (operator.equals("/")){
                    res = agent1s / agent2s;
            } else {
                throw new IllegalArgumentException("Введите верный оператор");
            }
        } else {
            throw new IllegalArgumentException("от 1 до 10");
        }
        String result = Integer.toString(res);
        return result;
    }

    public static boolean checkRoman(String number){
        return number.matches("^[IVX]+$");
    }
    public static int parseNumber(String digit) {
        try {
            int i = Integer.parseInt(digit);
            return i;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Используйте только римские положительные цифры или только арабские цифры от 1 до 10");
        }
    }

    public static int convertToArabic(String roman) {
        String[] romanNumerals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] arabicEquivalent = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        int i = 0;
        int convertedToArabic = 0;

        for (int j = 0; j<roman.length();){
            if(roman.startsWith(romanNumerals[i], j)){
                convertedToArabic += arabicEquivalent[i];
                j += romanNumerals[i].length();
            } else {
                i++;
            }
        }
        return convertedToArabic;
    }
    public static String convertToRoman(int arabic){
        String[] romanNumerals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] arabicEquivalent = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        StringBuilder convertedToRoman = new StringBuilder();
        int i = 0;
        while (arabic > 0){
            if(arabic - arabicEquivalent[i] >= 0){
                convertedToRoman.append(romanNumerals[i]);
                arabic = arabic - arabicEquivalent[i];
            } else{
                i++;
            }
        }
        return convertedToRoman.toString();
    }
}

