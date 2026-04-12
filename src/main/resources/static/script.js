import {
    GoogleAuthProvider,
    signInWithPopup,
} from "https://www.gstatic.com/firebasejs/10.12.0/firebase-auth.js";

const messages = document.getElementById("messages")

let stompClient = null

function connect() {

    const socket = new SockJS('/chat')
    stompClient = Stomp.over(socket)

    stompClient.connect({}, function (frame) {

        console.log("Connected: " + frame)

        stompClient.subscribe('/userMessage/messages', function (response) {
            removeTyping()
            const message = document.createElement("div")
            message.classList.add("message","received")

            addAiMessage(response.body)
            messages.scrollTop = messages.scrollHeight;
        })
    })
}

function sendMessage(){

    const input = document.getElementById("messageInput")

    if(input.value === "") return

    if(!stompClient || !stompClient.connected){
        alert("WebSocket ainda não conectado")
        return
    }

    const messageText = input.value

    const message = document.createElement("div")
    message.classList.add("message","sent")

    message.innerText = messageText

    messages.appendChild(message)

    stompClient.send("/gemini/send", {}, messageText)
    showTyping()

    input.value=""

    messages.scrollTop = messages.scrollHeight
}

window.onload = connect

function addAiMessage(text) {

    const messageDiv = document.createElement("div");
    messageDiv.classList.add("message", "received");

    const html = marked.parse(text)

    messageDiv.innerHTML = `    
        <div>
            <span class="avatar">Butter AI</span>
            <div class="message-content">${html}</div>
        </div>
    `;
    messages.appendChild(messageDiv);
    window.sendMessage = sendMessage;
}

function startChat() {
    window.location.href = "chat.html";
}

let typingIndicator = null;

function showTyping(){

    typingIndicator = document.createElement("div");
    typingIndicator.classList.add("typing");

    typingIndicator.innerHTML =
        "Butter AI está digitando <span>.</span><span>.</span><span>.</span>";

    messages.appendChild(typingIndicator);

    messages.scrollTop = messages.scrollHeight;
}

function removeTyping(){

    if(typingIndicator){
        typingIndicator.remove();
        typingIndicator = null;
    }

}


window.cadastrarComGoogle = async function () {
    const provider = new GoogleAuthProvider();

    try {
        const result = await signInWithPopup(window.auth, provider);

        const user = result.user;

        console.log("Usuário:", user);

        alert("Cadastro/Login com Google realizado!");
        salvarUsuarioBanco(user)

        // opcional: redirecionar
        window.location.href = "chat.html";

    } catch (error) {
        console.error(error);
        alert("Erro: " + error.message);
    }
}

function salvarUsuarioBanco(user) {

    const usuarioDTO = {
        username: user.displayName,
        email: user.email,
        photo: user.photoURL,
        uid: user.uid
    };

    fetch("http://localhost:8080/user", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(usuarioDTO)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao salvar usuário");
            }
            return response.json();
        })
        .then(data => {
            console.log("Usuário salvo:", data);
        })
        .catch(error => {
            console.error("Erro:", error);
        });
}

