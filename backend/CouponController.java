import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@CrossOrigin(origins = "*") // Allows frontend to access API
@RestController
@RequestMapping("/api")
public class CouponController {

    // List of valid coupon codes
    private List<String> validCoupons = Arrays.asList(
        "SAVE50", "DISCOUNT100", "UPI2025", "COUPON123",
        "FREECASH", "OFFER75", "LUCKY500", "GIFT250",
        "SPECIAL25", "CASHBACK99"
    );

    private Set<String> usedCoupons = new HashSet<>();

    // Validate Coupon Code
    @GetMapping("/validate")
    public ResponseEntity<Map<String, Boolean>> validateCoupon(@RequestParam String code) {
        Map<String, Boolean> response = new HashMap<>();
        
        if (validCoupons.contains(code) && !usedCoupons.contains(code)) {
            usedCoupons.add(code); // Mark as used
            response.put("valid", true);
        } else {
            response.put("valid", false);
        }
        return ResponseEntity.ok(response);
    }
}
