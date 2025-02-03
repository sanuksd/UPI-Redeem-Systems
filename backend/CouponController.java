@RestController
@RequestMapping("/api")
public class CouponController {

    @Autowired
    private CouponRepository couponRepository;

    @GetMapping("/validate")
    public ResponseEntity<?> validateCoupon(@RequestParam String code) {
        Optional<Coupon> coupon = couponRepository.findByCode(code);
        if (coupon.isPresent() && !coupon.get().isUsed()) {
            coupon.get().setUsed(true);
            couponRepository.save(coupon.get());
            return ResponseEntity.ok(Collections.singletonMap("valid", true));
        }
        return ResponseEntity.ok(Collections.singletonMap("valid", false));
    }

    @PostMapping("/store-upi")
    public ResponseEntity<?> storeUPI(@RequestBody Map<String, String> request) {
        String upi = request.get("upi");
        UPIEntry entry = new UPIEntry(upi);
        couponRepository.save(entry);
        return ResponseEntity.ok(Collections.singletonMap("status", "UPI Saved"));
    }
}