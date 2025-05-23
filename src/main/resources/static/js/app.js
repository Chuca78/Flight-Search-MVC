// Run once the DOM is fully loaded
document.addEventListener('DOMContentLoaded', () => {
  // ====== Restrict Past Dates in Date Picker ======
  const dateInput = document.getElementById('departure-date');
  if (dateInput) {
    // Get today's date in YYYY-MM-DD format
    const today = new Date().toISOString().split("T")[0];
    // Set the minimum selectable date to today
    dateInput.setAttribute("min", today);
  }

  // ====== Show Loader on Form Submission ======
  const form = document.querySelector("form");
  const loader = document.getElementById("loading-indicator");

  if (form && loader) {
    // When form is submitted, show the loading spinner
    form.addEventListener("submit", () => {
      loader.classList.remove("d-none");
    });
  }
});
