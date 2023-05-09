import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // Проверка валидации телефонного номера
        String phoneNumber = "(+380)50-333-33-33";
        boolean isValid = validatePhoneNumber(phoneNumber);
        System.out.println(phoneNumber + " is " + (isValid ? "valid" : "invalid"));

        // Замена лишних пробелов на один
        String input = "Я хочу    бути     програмістом";
        String output = replaceMultipleSpaces(input);
        System.out.println(output);

        // Удаление HTML-тегов из текста
        String htmlText = "<div>\n" +
                "<p>Символи круглих дужок <code>'('</code> та <code>')'</code>. <br />Ці символи\n" +
                "дозволяють отримати з рядка додаткову інформацію.\n" +
                "<br/>Зазвичай, якщо парсер регулярних виразів шукає в тексті інформацію\n" +
                "за заданим виразом і знаходить її - він просто повертає\n" +
                "знайдений рядок.</p>\n" +
                "<p align=\"right\">А ось тут <a href=\"google.com\">посилання</a>, щоб життя медом не здавалося.</p>\n" +
                "</div>";
        String textWithoutTags = removeHtmlTags(htmlText);
        System.out.println(textWithoutTags);
    }

    // Валидация телефонного номера украинского оператора
    public static boolean validatePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("\\(\\+380\\)\\d{2}-\\d{3}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    // Замена лишних пробелов на один
    public static String replaceMultipleSpaces(String input) {
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(" ");
    }

    // Удаление HTML-тегов из текста
    public static String removeHtmlTags(String htmlText) {
        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(htmlText);
        return matcher.replaceAll("");
    }
}
