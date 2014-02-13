/*
	聊天模式的数据响应，加载聊天信息（刷新信息消失问题没解决）
*/

//dwr响应函数，向接收者显示发送者的消息
function dialogical_msg(userName, msg, userId){
	$('#chatDiv').append("<span class='showInfoContent'>" +
			"<a target='_blank' href='userInitUserHomeDataAction!getUserHomeDataByUserId?userId=" + userId +
				"'>" + userName + "</a> 说：" +
				"<p>" + msg + "</p>" +
				"<hr /></span>");
	//让div的滚动条显示在最底部
	$('#chatDiv')[0].scrollTop = $('#chatDiv')[0].scrollHeight;
}

$(document).ready(function() {
	//普通用户的处理函数
	$('#sendMsgButton').click(function(){
		var questionId = $('#tquestionId').val();
		var msgOfUser = $('#msgTextsOfUser').val();

		//通过ajax保存用户的问答对话
		$.ajax({
			type : "post",  
			url : 'userSaveInstantRespondAction!saveInstantChat?questionId='+questionId+'&respondContent='+msgOfUser,
			dataType : 'json',
			success : function(json){
				if(json.flag == true && json.msg != "" && json.msg != null){
					$('#chatDiv').append("<span class='showInfoContent'>" +
							"<a target='_blank' href='userInitUserHomeDataAction!getUserHomeDataByUserId?userId=" + 
							json.msg1 +
							"'>" + json.userName + "</a> 说：" +
								"<p>" + json.msg + "</p>" +
								"<hr /></span>");
				}
			}
		});
		//这是发送者看到自己的消息
		if(msgOfUser != "" && msgOfUser != null){
			//清空输入框
			$('#msgTextsOfUser').val('');
			//让div的滚动条显示在最底部
			$('#chatDiv')[0].scrollTop = $('#chatDiv')[0].scrollHeight;
		}
		
	});
	
	//好友的处理函数
	$('#sendMsgToFriendButton').click(function(){
		var questionId = $('#tquestionId').val();
		var msgOfUser = $('#msgTextsOfUser').val();

		//通过ajax保存用户的问答对话
		$.ajax({
			type : "post",  
			url : 'userSaveInstantRespondAction!saveInstantChat?questionId='+questionId+'&respondContent='+msgOfUser,
			dataType : 'json',
			success : function(json){
				if(json.flag == true && json.msg != "" && json.msg != null){
					$('#chatDiv').append("<span class='showInfoContent'>" +
							"<a target='_blank' href='userInitUserHomeDataAction!getUserHomeDataByUserId?userId=" + 
							json.msg1 +
							"'>" + json.userName + "</a> 说：" +
								"<p>" + json.msg + "</p>" +
								"<hr /></span>");
				}
			}
		});
		
		//这是发送者看到自己的消息
		if(msgOfUser != "" && msgOfUser != null){
			//清空输入框
			$('#msgTextsOfUser').val('');
			//让div的滚动条显示在最底部
			$('#chatDiv')[0].scrollTop = $('#chatDiv')[0].scrollHeight;
		}
		
	});
	
});

 
 
 
 
 
 
 
 
 