package com.upi.redeem;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;

@RestController
@RequestMapping("/api")
public class CouponController {
    private static final String COUPON_FILE = "data/coupons.json";
    private static final String UPI_FILE = "data/upi_list.txt";

    @PostMapping("/redeem")
    public ResponseEntity<String> redeemCoupon(@RequestBody Map<String, String> request) {
        String coupon = request.get("coupon");

        try {
            File file = new File(COUPON_FILE);
            ObjectMapper mapper = new ObjectMapper();
            List<String> coupons = mapper.readValue(file, new TypeReference<List<String>>() {});

            if (coupons.contains(coupon)) {
                return ResponseEntity.ok("✅ Coupon Redeemed Successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Invalid Coupon!");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Error Processing Request");
        }
    }

    @PostMapping("/save-upi")
    public ResponseEntity<String> saveUPI(@RequestBody Map<String, String> request) {
        String upi = request.get("upi");

        try (FileWriter fw = new FileWriter(UPI_FILE, true)) {
            fw.write(upi + "\n");
            return ResponseEntity.ok("✅ UPI Address Saved Successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Error Saving UPI");
        }
    }
}
