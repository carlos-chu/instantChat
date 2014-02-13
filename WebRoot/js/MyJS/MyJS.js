/*
	弹窗提示格式设置
*/
 $(document).ready(function() {
	dwr.engine.setActiveReverseAjax(true);
	//弹框提示位置设置
	$._messengerDefaults = {
		extraClasses: 'messenger-fixed messenger-theme-future messenger-on-top messenger-on-right'
	};
	
	//匹配按钮的处理函数
	$('#startLoad').click(function() {
			var userId = $('#user_id').val();
			var tag1 = $('#tag1').val();
			var tag2 = $('#tag2').val();
			var tag3 = $('#tag3').val();
			var title = $('#title').val();
			if(tag1 != null && tag1.trim() !="" && tag2 != null && tag2.trim() !="" && title != null && title.trim() !=""){
				$('#startLoad').button('loading');
				PushMsgDwrToUser.pushMsgToUserForAnswer(userId, tag1, tag2, tag3, title);
			}else{
				
			}
	});
	
	//邀请好友的处理函数
	$('#OkButton').live('click',function(){
		var userId = $('#user_id').val();
		var friendId = $('#friend_id').val();
		var title = $('#title').val();
		if(userId != null && title != ""){
			$('#OkButton').button('loading');
			//推送提示给好友
			PushMsgDwrToUser.pushMsgToUserFriend(userId, friendId, title);
			//跳转到聊天页面
			//window.location.href = "userInviteFriendToChatAction!saveProblem?friendId=" + friendId + "&title=" + title;
		}
	});
	
	//单人问答模式下的发送按钮
	$('#sendMsgButton').click(function(){
		var receiverId = $('#receiverId').val();
		var msg = $('#msgTextsOfUser').val();
		var userId = $('#tuserId').val();
		var userName = $('#tuserName').val();
		if(msg != null && msg != ""){
			PushMsgDwrToUser.chatWithOne(receiverId, userName, msg, userId);
		}
	});
	
	//好友问答模式下的发送按钮
	$('#sendMsgToFriendButton').click(function(){
		var receiverId = $('#receiverId').val();
		var msg = $('#msgTextsOfUser').val();
		var userId = $('#tuserId').val();
		var userName = $('#tuserName').val();
		if(msg != null && msg != ""){
			PushMsgDwrToUser.chatWithFriend(receiverId, userName, msg, userId);
		}
	});
	
});

/*function matchBtn(){
	var userId = document.getElementById("user_id").value;
	var tag1 = document.getElementById("tag1").value;
	var tag2 = document.getElementById("tag2").value;
	var tag3 = document.getElementById("tag3").value;
	var title = document.getElementById("title").value;
	if(tag1 != null && tag1.trim() !="" && tag2 != null && tag2.trim() !="" && title != null && title.trim() !=""){
		$('#startLoad').button('loading');
alert(tag1);
		PushMsgDwrToUser.pushMsgToUserForAnswer(userId, tag1, tag2, tag3, title);
	}
}*/
 
 
 
 
 
 
 
 