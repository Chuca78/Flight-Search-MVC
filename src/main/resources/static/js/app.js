document.addEventListener('DOMContentLoaded', () => {
  const dateInput = document.getElementById('departure-date');
  if (dateInput) {
    const today = new Date().toISOString().split("T")[0];
    dateInput.setAttribute("min", today);
  }
});
