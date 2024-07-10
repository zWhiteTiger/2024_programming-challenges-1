
// ข้อที่ 1

// โจทย์ 1 : การหาค่าผสมที่เป็นไปได้ของเลขชุดที่ให้ผลรวมเป็นจำนวนเฉพาะ
// **รายละเอียด:** เขียนโปรแกรมที่รับอาร์เรย์ของจำนวนเต็มและจำนวนเต็มหนึ่งจำนวน (ผลรวมที่ต้องการ) แล้วทำการหาค่าผสมของตัวเลขในอาร์เรย์ที่มีผลรวมเป็นจำนวนเฉพาะ
// - **อินพุต:** อาร์เรย์ของจำนวนเต็ม และจำนวนเต็มหนึ่งจำนวน
// - **เอาท์พุต:** ลิสต์ของค่าผสมที่ผลรวมเป็นจำนวนเฉพาะ

// **ตัวอย่าง:**
// - อินพุต: [2, 3, 5, 7, 11], 10
// - เอาท์พุต: [[3, 7], [5, 5]]


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeSumCombinations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: "); // <---- รับค่าเลขจำนวนเต็มมาหนึ่งจำนวน
        int n = scanner.nextInt();
        scanner.close();

        List<Integer> primeNumbers = new ArrayList<>();

        // หาจำนวนเฉพาะ แล้วเก็บไว้ใน Array primeNumber
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                primeNumbers.add(i);
            }
        }

        System.out.println("Prime numbers up to " + n + ": " + primeNumbers);

        // จาก 1 - 100 หาจำนวนเฉพาะ เริ่มนับจาก 1 และ บวก 1 ไปเรื่อยๆ
        int sum = 0; // < ----- 
        for (int prime : primeNumbers) {
            sum += (prime + 1);
        }

        System.out.println("Sum of prime numbers incremented by 1: " + sum);

        
        if (isPrime(sum)) { // <---- ตรวจสอบ ผลรวมว่าเป็นจำนวนเฉพาะหรือไม่
            System.out.println("The sum " + sum + " is a prime number.");
        } else {
            System.out.println("The sum " + sum + " is not a prime number.");
        }
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2 || num == 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
