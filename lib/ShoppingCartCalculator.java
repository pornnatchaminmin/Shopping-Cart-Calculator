package lib ;
import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * คำนวณราคารวมของตะกร้าและส่วนลดต่างๆ
        - ราคารวมพื้นฐานคำนวณจากราคาสินค้าและจำนวน
        - ถ้ามีส่วนลดจะคำนวณตามกฎที่กำหนด
        - 1 (ซื้อ 1 แถม 1): สำหรับสินค้าที่มีรหัสการซื้อ เป็น "BOGO" ให้ใช้โปรโมชันซื้อ 1 แถม 1 (เช่น ซื้อ 2 จ่าย 1, ซื้อ 3 จ่าย 2, ซื้อ 4 จ่าย 2)
        - 2 (ส่วนลดเมื่อซื้อเยอะ): สำหรับสินค้าที่มีรหัสการซื้อเป็น "BULK" หากซื้อตั้งแต่ 6 ชิ้นขึ้นไป จะได้รับส่วนลด 10% จากราคารวมของสินค้านั้น
        * @param items รายการสินค้าที่อยู่ในตะกร้า
        * @return ราคารวมของตะกร้า ถ้าเป็น null หรือว่างจะคืนค่า 0.0
        * @throws IllegalArgumentException ถ้า items ไม่ใช่ ArrayList<CartItem>
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (CartItem item : items) {
            total += calculateItemPrice(item);
        }
        
        return total;
    }

        private static double calculateItemPrice(CartItem item) {
        double price = item.price() * item.quantity();
        switch (item.sku()) {
            case "BOGO":
                // ซื้อ 1 แถม 1
                int pairs = item.quantity() / 2;
                int singles = item.quantity() % 2;
                price = (pairs * item.price()) + (singles * item.price());
                break;
            case "BULK":
                // ส่วนลดเมื่อซื้อเยอะ
                if (item.quantity() >= 6) {
                    price *= 0.9; // ลด 10%
                }
                break;
            default:
                // ไม่มีส่วนลด
                break;
        }
        return price;
        
    }
}