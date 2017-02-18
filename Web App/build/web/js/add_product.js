function validateName() {
	document.getElementById('add1').innerHTML="";
    var x = document.getElementById('name').value;
    if (x == null || x == "") {
		document.getElementById('add1').innerHTML='Name must be filled out';
        return false;
    }
    else{
        return true;	
    }

}

function validateDesc() {
	document.getElementById('add2').innerHTML="";
    var x = document.getElementById('desc').value;
    if (x == null || x == "") {
		document.getElementById("add2").innerHTML="Desc must be filled out";
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
	document.getElementById("add3").innerHTML="";
    var x = document.forms["add_edit_form"]["price_box"].value;
    if (x == null || x == "") {
		document.getElementById("add3").innerHTML="Price must be filled out";
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


function validateAddForm(){

	if (validateName()&&validateDesc()&&validatePrice()&&validateImageFile()) {
		return true;
	}
	else {
		var a = validateName();
		var b = validateDesc();
		var c = validatePrice();
        var d = validateImageFile();
		return false;
	}
}

function validateImageFile() {
    document.getElementById("add4").innerHTML="";
    var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];
    var sFileName = document.getElementById('pic').value;
                    
            var blnValid = false;
            for (var j = 0; j < _validFileExtensions.length; j++) {
                var sCurExtension = _validFileExtensions[j];
                if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                    blnValid = true;
                    break;
                }
            }
    if (blnValid == true){
        return true;
    }
    else{
        document.getElementById("add4").innerHTML="Please choose compatible image";
        return false;
    }
}



