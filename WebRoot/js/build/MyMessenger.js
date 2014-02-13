/*
	弹窗提示格式设置
*/
 $(document).ready(function() {
	 $._messengerDefaults = {
			 extraClasses: 'messenger-fixed messenger-theme-future messenger-on-top messenger-on-right'
	 };
	 //点击接收问题开启接收模式
	 $('#startAnswer').live('click',function(){
		 //更新user状态变为接受状态
		 $.ajax({
				type : "post",
				url : 'userUpdateIfAcceptAction!updateUserAcceptAnswer.action',
				dataType : 'json',
				success : function(json) {
					if (json.flag == true) {
						
						 var messages = "开启成功！您可以接受其他用户的问答请求了，" +
							"请您热情回答问答请求并遵守我们的合约，您会有意想不到的收获的！";
						 $.globalMessenger().post({
							 message: messages,
							 type: 'success',
							 showCloseButton: true,
						 });
						 
						 $('#startAnswer').hide();
						 $('#starButtonSpan').html(
								 "<button id='cancelAnswer' class='btn btn-large' type='button'>取消接受</button>");
					}
				}
		 });

	 });
	 
	 $('#cancelAnswer').live('click',function(){
		 //更新user状态变为接受状态
		 $.ajax({
				type : "post",
				url : 'userUpdateCancelAcceptAction!updateUserCancelAcceptAnswer.action',
				dataType : 'json',
				success : function(json) {
					if (json.flag == true) {
						$('#cancelAnswer').hide();
						 $('#starButtonSpan').html(
						 		"<button id='startAnswer' class='btn btn-large' type='button'>接受问题</button>");
					}
				}
		 });
	 });
	 
	//用户讨论问题
	$('#discussQuestion').click(function(){
		var questionId = $('#questionId').val();
		var msgOfUser = $('#msgTextsOfUser').val();
		//通过ajax保存用户的评论
		$.ajax({
			type : "post",  
			url : 'userSaveInstantRespondAction!saveDiscussAndWarn?questionId='+questionId+'&respondContent='+msgOfUser,
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
		if(msgOfUser != "" && msgOfUser != null){
			//清空输入框
			$('#msgTextsOfUser').val('');
		}
	});
});
//弹出是否接受问题的提示框
	function alertReceiverUser(title, userId, isWhat){
		var i = 1;
		var j = isWhat;
		$.globalMessenger()["do"]({
		  errorMessage: "问题的标题：" + title,
		  successMessage: '正在处理中...',
			  action: function(opts) {
			  	if (++i < 3) {
		    		return opts.error({
		    	  		status: 1000,
		        		readyState: 0,
		        		responseText: 0
		      		});
		    	} else if(i == 3 && j == 0){
		    	    return opts.success(
		    	    	window.location.href = "userPublicInstantQuestionAction!saveProblem?publishUserId=" + userId
					);  
		    	}else if(i == 3 && j == 1){
		    	    return opts.success(
			    	   	window.location.href = "userInviteFriendToChatAction!saveProblem?publishUserId=" + userId
					);  
			    }else{
		    		return opts.cancel(
		    				
		    		);		
		    	}
		  	  }
		});
	}
	
 
 
 
 