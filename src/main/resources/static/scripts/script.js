function spoiler(param) {
    const elements = document.getElementsByClassName("spoiler" + param);
    const text = document.getElementById(param + "Spoiler");
    for (let item of elements) {
        if (item.style.display === "table-cell") {
            item.style.display = "none";
            text.innerHTML = param + " System";
        } else {
            item.style.display = "table-cell";
            text.innerHTML = "Hide " + param;
        }
    }
}

function showWarning() {
    return confirm('Delete system? It\'ll delete the information about influence');
}
