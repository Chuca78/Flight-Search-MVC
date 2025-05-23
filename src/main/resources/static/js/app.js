

document.addEventListener('DOMContentLoaded', () => {
  const dateInput = document.getElementById('departure-date');
  if (dateInput) {
    const today = new Date().toISOString().split("T")[0];
    dateInput.setAttribute("min", today);
  }
});


document.addEventListener('DOMContentLoaded', () => {
  const form = document.querySelector("form");
  const loader = document.getElementById("loading-indicator");

  if (form && loader) {
    form.addEventListener("submit", () => {
      loader.classList.remove("d-none");
    });
  }
});
