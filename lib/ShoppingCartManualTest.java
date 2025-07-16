package lib ;
import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));      // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }
        // Test 4 : ซื้อ 1 แถม 1 
        ArrayList<CartItem> BOGOCart = new ArrayList<>();
        BOGOCart.add(new CartItem("BOGO", "cooky", 20.0, 2)); // 20+20=40
        double total4 = ShoppingCartCalculator.calculateTotalPrice(BOGOCart);
        if (total4 == 20.0) {
            System.out.println("PASSED:BOGOcart total is correct (20.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGOcart total expected 20.0 but got " + total4);
            failedCount++;
        }

        // Test 5 :6 ชิ้น ขึ้นไป ลด 10 %
        ArrayList<CartItem> BULKCart = new ArrayList<>();
        BULKCart.add(new CartItem("BULK", "water", 10.0, 7)); // 70*10% = 63
        double total5 = ShoppingCartCalculator.calculateTotalPrice(BULKCart);
        if (total5 == 63.0) {
            System.out.println("PASSED:BULKcart total is correct (63.0)");
            passedCount++;
        } else {
            System.out.println("FAILED:BULKcart total expected 63.0 but got " + total5);
            failedCount++;
        }
        // Test 6 : สินค้า มี 6 ชิ้น ขึ้นไป ลด 10 % และ ซื้อ 1 แถม 1
        ArrayList<CartItem> MixedCart = new ArrayList<>();
        MixedCart.add(new CartItem("BOGO", "sauce", 50.0, 3)); // 50+50=50 = 150
        MixedCart.add(new CartItem("BULK", "chicken", 200.0, 7)); // 1400*10% = 1260
        double total6 = ShoppingCartCalculator.calculateTotalPrice(MixedCart);
        if (total6 == 1360.0) {
            System.out.println("PASSED: Mixed cart total is correct (1360.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Mixed cart total expected 1360.0 but got " + total6);
            failedCount++;
        }

        // Test 7: สินค้าที่ราคาต่ำกว่า 0
        ArrayList<CartItem> negativePriceCart = new ArrayList<>();
        negativePriceCart.add(new CartItem("negativePrice", "Discounted Item", -10.0, 1)); // -10
        double total7 = ShoppingCartCalculator.calculateTotalPrice(negativePriceCart);
        if (total7 == -10.0) {
            System.out.println("PASSED: Negative price item total is correct (-10.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Negative price item total expected -10.0 but got " + total7);
            failedCount++;
        }
        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}