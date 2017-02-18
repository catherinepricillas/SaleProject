function validateConfirm(){
        if (confirm("Are you sure your data is correct?")){
        	return true;
        }
		else
			return false;
	}


function validateQuantity(){
	document.getElementById('cekquantity').innerHTML = "";
	var x = document.getElementById('quantity').value;
	if (x == null || x == "" || x=="0"){
		document.getElementById('cekquantity').innerHTML = 'Quantity must be filled';
		return false;
	} else {
		return true;
	}
}

function validateConsignee(){
	document.getElementById('cekconsignee').innerHTML = "";
	var x = document.getElementById('consignee').value;
	if (x == null || x == ""){
		document.getElementById('cekconsignee').innerHTML = 'Consignee must be filled';
		return false;
	} else {
		return true;
	}
}

function validateAddress(){
	document.getElementById('cekaddress').innerHTML = "";
	var x = document.getElementById('fulladdress').value;
	if (x == null || x == ""){
		document.getElementById('cekaddress').innerHTML = 'Address must be filled';
		return false;
	} else {
		return true;
	}
}

function validatePostal(){
	document.getElementById('cekpostal').innerHTML = "";
	var x = document.getElementById('postalcode').value;
	if (x == null || x == ""){
		document.getElementById('cekpostal').innerHTML = 'Postal code must be filled';
		return false;
	} else {
		return true;
	}
}

function validatePhone(){
	document.getElementById('cekphone').innerHTML = "";
	var x = document.getElementById('phonenumber').value;
	if (x == null || x == ""){
		document.getElementById('cekphone').innerHTML = 'Phone number must be filled';
		return false;
	} else {
		return true;
	}
}

function validateCC(){
	document.getElementById('cekcc').innerHTML = "";
	var x = document.getElementById('ccnumber').value;
	if (x == null || x == ""){
		document.getElementById('cekcc').innerHTML = 'Credit card number must be filled';
		return false;
	} else if (x.length<12){
		document.getElementById('cekcc').innerHTML = 'Credit card number must be 12 digits';
		return false;
	} else {
		return true;
	}
}

function validateVer(){
	document.getElementById('cekver').innerHTML = "";
	var x = document.getElementById('ver').value;
	if (x == null || x == ""){
		document.getElementById('cekver').innerHTML = 'Verification value must be filled';
		return false;
	} else if (x.length<3){
		document.getElementById('cekver').innerHTML = 'Verification value must be 3 digits';
		return false;
	} else {
		return true;
	}
}

function validateForm(){
	if (validateQuantity() == true && validateConsignee() == true && validateAddress() == true && validatePostal() == true
		&& validatePhone() == true && validateCC() == true && validateVer() == true){
		return true;
	}
	else {
		var a = validateQuantity();
		var b = validateConsignee();
		var c = validateAddress();
		var d= validatePostal();
		var e = validatePhone();
		var f = validateCC();
		var g = validateVer();
		return false;
	}
}

function validateNumber(event) {
    var key = window.event ? event.keyCode : event.which;

    if (event.keyCode === 8 || event.keyCode === 46
     || event.keyCode === 37 || event.keyCode === 39) {
        return true;
    }
    else if ( key < 48 || key > 57 ) {
        return false;
    }
    else return true;
};

function validateCardNumber(event){
	var a = document.confirmform.creditcard;
	if (a.value.length >= 12){
		a.value = a.value.substring(0,11);
	}
	return validateNumber(event);
}

function validateVerification(event){
	var a = document.confirmform.verification;
	if (a.value.length >= 3){
		a.value = a.value.substring(0,2);
	}
	return validateNumber(event);
}

function countTotal(price){
	var total = document.confirmform.quantity.value * price;
	document.getElementById('totalprice').innerHTML = "IDR " +total.toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.");

}