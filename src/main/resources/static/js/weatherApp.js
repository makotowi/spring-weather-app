const boot = document.getElementById('boot');
if (boot && boot.dataset.alert) {
    window.alert(boot.dataset.alert);
}

const openBtn = document.getElementById("openModal");
const closeBtn = document.getElementById("closeModal");
const modal = document.getElementById("modal");

openBtn.addEventListener("click", () => {
    modal.classList.add("open");
})

closeBtn.addEventListener("click", ()=>{
    modal.classList.remove("open");
})