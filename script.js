function validateCoupon() {
    let coupon = document.getElementById("coupon").value;
    fetch("https://upi-redeem-systems-production.up.railway.app/api/validate?code=" + coupon)
        .then(response => response.json())
        .then(data => {
            if (data.valid) {
                alert("Coupon Valid!");
                document.getElementById("upi-section").style.display = "block";
            } else {
                alert("Invalid or Already Used Coupon!");
            }
        })
        .catch(error => {
            alert("Error connecting to the server.");
            console.error(error);
        });
}

function submitUPI() {
    let upi = document.getElementById("upi").value;
    fetch("https://upi-redeem-systems-production.up.railway.app/api/store-upi", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ upi: upi })
    })
    .then(response => response.json())
    .then(data => {
        alert("UPI Saved Successfully!");
    })
    .catch(error => {
        alert("Error submitting UPI.");
        console.error(error);
    });
}
