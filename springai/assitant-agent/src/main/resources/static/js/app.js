document.addEventListener("DOMContentLoaded", () => {

    function getSessionId() {
        let sessionId = sessionStorage.getItem("sessionId");

        if (!sessionId) {
            sessionId = crypto.randomUUID();
            sessionStorage.setItem("sessionId", sessionId);
        }
        return sessionId;
    }

    const submitBtn = document.getElementById("submitBtn");
    const promptInput = document.getElementById("promptInput");
    const responseArea = document.getElementById("responseArea");

    submitBtn.addEventListener("click", async () => {

        responseArea.textContent = "Loading...";

        const sessionId = getSessionId();
        console.log("Session ID:", sessionId);

        try {
            const response = await fetch("/ai/devopsgpt", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    prompt: promptInput.value,
                    sessionId: sessionId
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
