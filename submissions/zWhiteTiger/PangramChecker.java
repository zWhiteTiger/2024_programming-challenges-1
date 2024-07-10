
// ข้อที่ 2

// ### โจทย์ 2 : การหาคำที่ใช้ตัวอักษรทุกตัวในภาษาอังกฤษอย่างน้อยหนึ่งครั้ง (Pangram)
// **รายละเอียด:** เขียนโปรแกรมที่รับสตริงหนึ่งสตริงแล้วตรวจสอบว่าสตริงนั้นเป็น Pangram หรือไม่ ถ้าใช่ ให้หาคำที่ยาวที่สุดในสตริงนั้น
// - **อินพุต:** สตริงหนึ่งสตริง
// - **เอาท์พุต:** สตริงที่เป็นคำยาวที่สุดในสตริงที่เป็น Pangram หรือข้อความแสดงว่าไม่ใช่ Pangram

// **ตัวอย่าง:**
// - อินพุต: "The quick brown fox jumps over the lazy dog"
// - เอาท์พุต: "jumps"
// - อินพุต: "Hello world"
// - เอาท์พุต: "Not a Pangram"

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PangramChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: "); // <---- รับค่า String มา
        String input = scanner.nextLine();
        scanner.close();

        Map<Character, Integer> charFrequency = new HashMap<>();

        for (char ch : input.toCharArray()) { // <---- หาความยาวของตัวอักษร
            if (Character.isLetter(ch)) {
                char lowerCaseChar = Character.toLowerCase(ch);
                charFrequency.put(lowerCaseChar, charFrequency.getOrDefault(lowerCaseChar, 0) + 1);
            }
        }

        System.out.println("Character frequencies: " + charFrequency);

        boolean isPangram = true; // <----- ตรวจสอบว่า Input เป็น pangram หรือไม่ ("The quick brown fox jumps over the lazy dog")
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (!charFrequency.containsKey(ch)) {
                isPangram = false;
                break;
            }
        }

        if (isPangram) { // <---- ใช้เงื่อนไขตรวจสอบ pangram ถ้าตรงกัน จะ print ออกเป็น "The input string is a pangram."
            System.out.println("The input string is a pangram.");
        } else { // <---- นอกเงื่อนไขหรือไม่ตรงตามเงื่อนไขใดๆ print ออกเป็น "The input string is not a pangram."
            System.out.println("The input string is not a pangram.");
        }
    }
}
