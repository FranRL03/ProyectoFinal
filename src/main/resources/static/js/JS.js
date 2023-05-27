function login() {
    let user, pass

    user = document.getElementById("username").value;
    pass = document.getElementById("password").value;

    if (user == "user" && pass == "1234") {
        window.location = "Principal.html"
    }else if (user == "admin" && pass == "admin") {
        window.location = "Principal.html"
    } else {
		alert("Usuario o contraseña incorrecto")
	}
}






