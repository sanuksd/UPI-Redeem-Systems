function validateCoupon() {
    let coupon = document.getElementById("coupon").value;
    fetch("http://localhost:8080/api/validate?code=" + coupon)
        .then(response => response.json())
        .then(data => {
            if (data.valid) {
                alert("Coupon Valid!");
                document.getElementById("upi-section").style.display = "block";
            } else {
                alert("Invalid or Already Used Coupon!");
            }
        });
}

function submitUPI() {
    let upi = document.getElementById("upi").value;
    fetch("http://localhost:8080/api/store-upi", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ upi: upi })
    })
    .then(response => response.json())
    .then(data => {
        alert("UPI Saved Successfully!");
    });
}