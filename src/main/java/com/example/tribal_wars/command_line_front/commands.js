const terminal = document.getElementById("terminal");
const input = document.getElementById("input");
document.addEventListener("click", function() {
    input.focus();
});
input.focus();

function appendToTerminal(text) {
    terminal.innerHTML = "\n" + text;
}

input.addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        const command = input.value.trim();
        appendToTerminal("> " + command);
        input.value = "";
        handleCommand(command);
    }
});

function handleCommand(command) {
    if (command === "login") {
        appendToTerminal("Podaj login:");
    } else if (command === "register") {
        appendToTerminal("Podaj nowy login:");
    } else {
        appendToTerminal("Nieznana komenda. Użyj 'login' lub 'register'.");
    }
}