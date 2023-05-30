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

function validarFechas() {
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

function validarCorreo() {
    let correo = document.getElementById("correo").value; 
    let correoExp = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/;
    let correoValido = correoExp.test(correo);
    let mensajeError = document.getElementById("mensajeError");

    if (correoValido) {
        mensajeError.style.display = "none";
        return true;
    } else {
        mensajeError.textContent = "El correo NO es válido";
        mensajeError.style.display = "block";
        return false;
    }
}


function validarDNI() {
    let dni = document.getElementById("dni").value; 
    let patronDNI = /^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$/;
    let dniValido = patronDNI.test(dni);
    let mensajeError = document.getElementById("mensajeError");

    if (dniValido) {
        mensajeError.style.display = "none";
        return true;
    } else {
        mensajeError.textContent = "El DNI NO es válido";
        mensajeError.style.display = "block";
        return false;
    }
}


function validacion() {
	let correoValido = true;
	let fechasValidas = true;
	let dniValido = true;


	if (document.getElementById("correo")) {
		correoValido = validarCorreo();
	}

	if (document.getElementById("fnac") && document.getElementById("fAlta")) {
		fechasValidas = validarFechas();
	}
	
	if(document.getElementById("dni")){
		dniValido = validarDNI();
	}

	return correoValido && fechasValidas && dniValido;
}








