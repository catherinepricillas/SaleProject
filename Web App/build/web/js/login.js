function validateNameorEmail() {
	document.getElementById("login1").innerHTML="";
    var x = document.forms["formlogin"]["EmailorUsername"].value;
    if (x == null || x == "") {
		document.getElementById("login1").innerHTML="Username or Email must be filled out";
        return false;
    }
    return true;
}

function validatePassword() {
	document.getElementById("login2").innerHTML="";
    var x = document.forms["formlogin"]["password"].value;
    if (x == null || x == "") {
		document.getElementById("login2").innerHTML="Password must be filled out";
        return false;
    }
    return true;
}

function validateForm() {
	if (validateNameorEmail()&&validatePassword()) {
		return true;
	}
	else {
		var a = validateNameorEmail();
		var b = validatePassword();
		return false;
	}
	
}
