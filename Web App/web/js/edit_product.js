function validateName() {
	document.getElementById('edit1').innerHTML="";
    var x = document.getElementById('name').value;
    if (x == null || x == "") {
		document.getElementById('edit1').innerHTML='Name must be filled out';
        return false;
    }
    else{
        return true;	
    }

}

function validateDesc() {
	document.getElementById('edit2').innerHTML="";
    var x = document.getElementById('desc').value;
    if (x == null || x == "") {
		document.getElementById('edit2').innerHTML="Desc must be filled out";
        return false;
    }
    return true;
}

function validateDesc200() {
    var x = document.getElementById('desc');
    if (x.value.length>=200) {
		x.value = x.value.substring(0,199);
		return false;
	}
	else 
		return true;	
}

function validatePrice() {
	document.getElementById("edit3").innerHTML="";
    var x = document.forms["add_edit_form"]["price_box"].value;
    if (x == null || x == "") {
		document.getElementById("edit3").innerHTML="Price must be filled out";
        return false;
    }
    return true;
}

function validatePriceNumber(event) {
	var key = window.event ? event.keyCode : event.which;

    if (event.keyCode === 8 || event.keyCode === 46
     || event.keyCode === 37 || event.keyCode === 39) {
        return true;
    }
    else if ( key < 48 || key > 57 ) {
        return false;
    }
    else return true;
}


function validateForm(){

	if (validateName()&&validateDesc()&&validatePrice()) {
		return true;
	}
	else {
		var a = validateName();
		var b = validateDesc();
		var c = validatePrice();
		return false;
	}
}



