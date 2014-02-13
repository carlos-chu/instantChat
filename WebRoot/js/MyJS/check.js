	function checkFrom() {
		var email = document.getElementById("email").value;
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;
		var name = document.getElementById("name").value;
		var sex = document.getElementById("sex").value;
		if (email.trim().length == 0) {
			email.focus();
			return false;
		}else if (name.trim().length < 2 || 35 < name.trim().length) {
			$('#nameWarn').append("名字长度不合适！");
			name.focus();
			return false;
		} else if (sex.trim().length < 2 || 25 < sex.trim().length) {
			$('#sexWarn').append("亲~长度不合适！");
			name.focus();
			return false;
		} else if(password.trim().length < 6 || 25 < password.trim().length) {
			$('#passwordWarn').append("密码长度必须在6-25个字符之间！");
			password.focus();
			return false;
		} else if(password.trim() != confirmPassword.trim()) {
			$('#confirmPasswordWarn').append("两次填写的密码不相符！");
			confirmPassword.focus();
			return false;
		} else {
			return true;
		};
	}

	function submitFrom() {
		if (checkFrom() == true) {
			document.getElementById("registerForm").submit();
		}
	};
	
	
	
	
	
	
	
	
	