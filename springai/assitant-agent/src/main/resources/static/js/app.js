document.addEventListener("DOMContentLoaded", () => {

    const submitBtn = document.getElementById("submitBtn");
    const promptInput = document.getElementById("promptInput");
    const responseArea = document.getElementById("responseArea");

    submitBtn.addEventListener("click", async () => {

        responseArea.textContent = "Loading...";

        try {
            const response = await fetch("/ai/devopsgpt", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    prompt: promptInput.value
                })
            });

            const json = await response.json();

            if (!response.ok) {
                responseArea.textContent = JSON.stringify(json, null, 2);
                return;
            }

            responseArea.textContent = JSON.stringify(json, null, 2);

        } catch (error) {
            responseArea.textContent = JSON.stringify({
                error: error.message
            }, null, 2);
        }
    });

});
