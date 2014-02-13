/*
	处理用户评分相关的js
 */
$(document).ready(function() {
	$('#pingfenButton').click(function(){
		var uid = $("#receiverId").val();
		var grade= $("#pingfenGoal").val();
		var fidWarn = $("#tuserId").val();  
		var ifSolve = $("#ifSolve").val(); 
		var questionId = $("#tquestionId").val(); 
		window.location.href = 
			"userGradeAction!updateUserGrade?userId=" + 
				uid + "&friendId=" + fidWarn + "&grade=" + grade + "&questionId=" + questionId + "&ifSolve=" + ifSolve;
	});
	
});










