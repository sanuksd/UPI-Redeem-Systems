@Entity
public class UPIEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String upi;

    // Constructor
    public UPIEntry(String upi) {
        this.upi = upi;
    }
}