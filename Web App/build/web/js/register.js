

function validateName() {
	document.getElementById("register1").innerHTML ="";
    var x = document.forms["formregister"]["fullname"].value;
    if (x == null || x == "") {
		document.getElementById("register1").innerHTML ="Name must be filled";
        return false;
    }
    return true;
}

function validateUName() {
	document.getElementById("register2").innerHTML="";
    var x = document.forms["formregister"]["username"].value;
    if (x == null || x == "") {
        
		document.getElementById("register2").innerHTML="Username must be filled";
        return false;
    }
    return true;
}

function validateEmail() {
	document.getElementById("register3").innerHTML="";
    var x = document.forms["formregister"]["email"].value;
    if (x == null || x == "") {
		document.getElementById("register3").innerHTML="Email must be filled";
        return false;
    }
    
    return true;
}

function validateEmail2() {
	if (validateEmail()) {
		document.getElementById("register3").innerHTML="";
		var x = document.forms["formregister"]["email"].value;
		var atpos = x.indexOf("@");
		var dotpos = x.lastIndexOf(".");
		if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
			document.getElementById("register3").innerHTML="Wrong email format";
			return false;
		}
	}
	return true;
}


function validatePassword() {
	document.getElementById("register4").innerHTML="";
    var x = document.forms["formregister"]["password"].value;
    if (x == null || x == "") {
		document.getElementById("register4").innerHTML="Password must be filled";
        return false;
    }
    
    return true;
}

function validateConfirm() {
	if (validatePassword()) {
		document.getElementById("register5").innerHTML="";
		var x = document.forms["formregister"]["confirmpassword"].value;
		if (x == null || x == "") {
			document.getElementById("register5").innerHTML="You must confirm your password";
			return false;
		}
	}
    return true;
}

function validatePassAndConfirm() {
	if (validatePassword()&&validateConfirm()) {
		document.getElementById("register4").innerHTML="";
		document.getElementById("register5").innerHTML="";
		var x = document.forms["formregister"]["password"].value;
		var y = document.forms["formregister"]["confirmpassword"].value;
		if (x != y) {
			document.getElementById("register4").innerHTML="Type your password correctly";
			document.getElementById("register5").innerHTML="Type your password correctly";
			return false;
		}
	}
    return true;
}


function validateAddress() {
	document.getElementById("register6").innerHTML="";
    var x = document.forms["formregister"]["fulladdress"].value;
    if (x == null || x == "") {
		document.getElementById("register6").innerHTML="Address must be filled";
        return false;
    }
    return true;
}

function validatePostal() {
	document.getElementById("register7").innerHTML="";
    var x = document.forms["formregister"]["postalcode"].value;
    if (x == null || x == "") {
		document.getElementById("register7").innerHTML="Postal code must be filled";
        return false;
    }
    return true;
}

function validatePhone() {
	document.getElementById("register8").innerHTML="";
    var x = document.forms["formregister"]["phonenumber"].value;
    if (x == null || x == "") {
		document.getElementById("register8").innerHTML="Phone number must be filled";
        return false;
    }
    return true;
}

function validateForm() {
	
	if(validateName()&&validateUName()&&validateEmail()&&validateEmail2&&validatePassword()&&validateConfirm()&&validatePassAndConfirm()&&validateAddress()&&validatePostal()&&validatePhone()) {
		return true;
	}
	else {
		var a = validateName();
		var b = validateUName();
		var c = validateEmail();
		var d = validateEmail2();
		var e = validatePassword();
		var f = validateConfirm();
		var g = validatePassAndConfirm();
		var h = validateAddress();
		var i = validatePostal();
		var j = validatePhone();
	}

	
	return false;
}

