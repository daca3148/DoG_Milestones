function editProfile(){
	var btn = document.getElementById("edit_profile_button")
	if(btn.innerHTML == "Edit Profile"){
		btn.innerHTML = "Done";
		var oldText = document.getElementById('bio').innerHTML;
		document.getElementById('bio').innerHTML = "<form><textarea rows='4' cols='40' maxlength='250' input type='text' id='bioText'>" + oldText + "</textarea></form>"
		document.getElementById('img_div').innerHTML = "<form style='text-align:center' method='post'>Enter Profile Picture URL<br><input type='text' id='user_img' name='pic''><button type=\"submit\">Submit</button></form>"
	}
	else if(btn.innerHTML == "Done"){
		var newImg = document.getElementById('user_img').value;
		if(document.getElementById('user_img').value){
			document.getElementById('img_div').innerHTML = "<img class='card-img-top img-responsive fit-image rounded-circle' id='user_img' style='border:0.3em solid #6491DD' src=" + newImg + " alt='Profile picture'>"
		}
		else{
			document.getElementById('img_div').innerHTML = "<img class='card-img-top img-responsive fit-image rounded-circle' id='user_img' style='border:0.3em solid #6491DD' src='https://media1.tenor.com/images/ae96ee628ef967b0e7dcdc4b3dbff0e8/tenor.gif?itemid=14116367' alt='Profile picture'>"
		}
		var newText = document.getElementById('bioText').value;
		document.getElementById('bio').innerHTML = newText;
		btn.innerHTML = "Edit Profile";
	}
}
