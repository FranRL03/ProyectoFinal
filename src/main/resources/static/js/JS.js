function login() {
	let user, pass

	let mensajeError = document.getElementById("loginError");
	user = document.getElementById("username").value;
	pass = document.getElementById("password").value;

	if (user == "user" && pass == "1234") {
		window.location = "Principal.html"
	} else if (user == "admin" && pass == "admin") {
		window.location = "Principal.html"
	} else {
		mensajeError.textContent = "Usuario o contraseña incorrectos";
		mensajeError.style.display = "block";
		
		return false
	}
}

function ValidarFechas() {
	let fechainicial = document.getElementById("fnac").value;
	let fechafinal = document.getElementById("fAlta").value;
	let mensajeError = document.getElementById("mensajeError");

	if (Date.parse(fechafinal) < Date.parse(fechainicial)) {
		mensajeError.textContent = "La fecha de nacimiento no puede ser mayor que la fecha de alta";
		mensajeError.style.display = "block";
		return false;
	} else {
		mensajeError.style.display = "none";
		return true;
	}
}






