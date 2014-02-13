/*
	处理关注用户的ajax操作
 */
$(document).ready(function() {
	$('#collectButton').live('click',function(){
		var fid = $("#friendId").val();
		//先通过ajax保存下userFriend表
		$.ajax({
			type : "post",  
			url : 'userFellowPeopleAction!saveUserFriends?fid=' + fid,
			dataType : 'json',
			success : function(json){
				//改变关注按钮状态
				if(json.flag == true){
					$('#fellowPeopleButton').hide();
					$('#fellowPeopleSpan').html(
					 "<button id='cancelfellowPeopleButton' class='btn btn-small' type='button'>我已关注</button>");
				}
			}
		});
	});
	
	$('#cancelfellowPeopleButton').live('click',function(){
		var fid = $("#friendId").val();
		//通过ajax删除userFriend表
		$.ajax({
			type : "post",  
			url : 'userCancelFellowPeopleAction!deleteUserFriendsByFid?fid=' + fid,
			dataType : 'json',
			success : function(json){
				//改变关注按钮状态
				if(json.flag == true){
					$('#cancelfellowPeopleButton').hide();
					$('#fellowPeopleSpan').html(
					 "<button id='fellowPeopleButton' class='btn btn-small btn-success' type='button'>关注此人</button>");
				}
			}
		});
	});
	
});










