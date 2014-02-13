/*
	处理修改用户信息的ajax操作
 */
$(document).ready(function(){
	$('#saveResetInfoButton').click(function(){
		var resetName = $("#resetName").val();
		var resetIntro = $("#resetIntro").val();
		$.ajax({
			type : "post",  
			url : 'userResetInfoAction!updateUserInfo?resetName=' + resetName + '&resetIntro=' + resetIntro,
			dataType : 'json',
			success : function(json){
				if(json.flag == true){
					$('#alertDiv').html(
					 "<div class='alert alert-success'>" +
					 	"<button type='button' class='close' data-dismiss='alert'>&times;</button>" +
					 	"保存成功！" + "</div>");
				}
			}
		});
	});

	//修改密码
	$('#resetPasswordButton').click(function(){
		//var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword").val();
		//var confirmPassword = $("#confirmPassword").val();
		$.ajax({
			type : "post",  
			url : 'userResetInfoAction!updateUserPassword?resetPassword=' + newPassword,
			dataType : 'json',
			success : function(json){
				if(json.flag == true){
					$('#alertDiv2').html(
					 "<div class='alert alert-success'>" +
					 	"<button type='button' class='close' data-dismiss='alert'>&times;</button>" +
					 	"修改成功！" + "</div>");
				}
			}
		});
	});
	
});










