/*function getMoreWarns() {
	//加载更多提醒的处理函数
	$('#moreWarnsBtn').live('click', function(){
		$('#moreWarnsBtn').button('loading');
		//通过ajax加载更多的内容
		$.ajax({
			type : "post",  
			url : 'getMoreWarnsAction!getAjaxWarns',
			dataType : 'json',
			success : function(json){
				if(json != null){
					//原先的btn消失
					$('#moreWarnsBtn').hide();
					for(var i=0; i<3; i++){
						alert(i + " " + json);
						$('#showWarnsDiv').append("22  ");
					}
					$.each(json.obj,function(index,value){
						alert(index + " dddd " + value.id + "  " + value.tuser);
						$('#showWarnsDiv').append("22  ");
						$('#showWarnsP').append("<a class='warnUserStyle titleFont questionStyle' target='_blank'" +
								"href='userInitUserHomeDataAction!getUserHomeDataByUserId?userId='" + 
									value.friendtouserId.id +
								">" + value.friendtouserId.name + "</a> &nbsp;" +
								"");
					});
				}
			}
		});
	});
}*/
function getPreviousWarns() {
	$('#showWarnsDiv').html(
			$('#buttonSpan').load("getMoreWarnsAction2!getWarns?ifNext=" + -1));
}
function getNextWarns() {
	$('#showWarnsDiv').html(
			$('#buttonSpan').load("getMoreWarnsAction2!getWarns?ifNext=" + 1));
}
 
 
 
 
 
 
 
 