function scriptLogin(){

	function readCookie(name) {
		var nameEQ = name + "=";
		var ca = document.cookie.split(';');
				
		for(var i=0;i < ca.length;i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1,c.length);
			if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
		}
		return null;
	}
		// Get all the cookies pairs in an array
	var name = readCookie("user");
	console.log(name);
	var login_button = document.getElementById('head_login');
	var logout_button = document.getElementById('head_logout');

	if (name == undefined || name == "null") {
		login_button.style.visibility = 'visible';
		logout_button.style.visibility = 'hidden';
	} else {
		$('#head_login').hide();
		$('#head_logout').show();
		alert($('#head_login').html());
	}

}