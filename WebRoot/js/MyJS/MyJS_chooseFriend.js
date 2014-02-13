/*
	处理用户选择好友相关的js
 */
/*function okChoose(n){
	var friendId = n ;  
		$.ajax({
			type : "post",  
			url : 'userInviteFriendAction!chooseFriend?friendId=' + friendId ,
			dataType : 'json',
			success : function(json){
				if(json.flag == true){
					$('#showQueding').html(
						"<button id='OkChooseButton' class='btn btn-large btn-primary' type='button'>确  定</button>" +
							"<input id='pushFriendId' type='hidden' value=" + json.msg2 + "/>"
					);
				}
			}
		});
}*/

$(document).ready(function() {
	$('#OkButton').click(function() {
		
	});
});








