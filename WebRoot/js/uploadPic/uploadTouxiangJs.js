	function showResult() {//删除显示的上传成功结果   
		$("#result").html("");
	}

	$(document).ready(function() {
		$('#fileInput').uploadify( {
			'uploader' : 'js/uploadPic/uploadify.swf',
			'script' : 'uploadTouxiang.action', //指定服务端处理类的入口 ;jsessionid=<%=session.getId()%>
			'scriptData' : {'useId':$('#userId').val()},
			'folder' : 'upload',
			'cancelImg' : 'images/cancel.png',
			'fileDataName' : 'fileInput', //和input的name属性值保持一致就好，Struts2就能处理了   
			'queueID' : 'fileQueue',
			'auto' : false,//是否选取文件后自动上传   
			'multi' : true,//是否支持多文件上传 
			'simUploadLimit' :1,//一次同步上传的文件数目   
			'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.', //如果配置了以下的'fileExt'属性，那么这个属性是必须的 
			'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//允许的格式
			'queueSizeLimit':1,
			'sizeLimit' : 5120000, //单位为字节
			'buttonText' : '增加上传文件',//按钮上的文字   
			'displayData' : 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比    
			'onError' : function(event, ID, fileObj, errorObj) {
				if (errorObj.type === "File Size") {
					alert('超过文件上传大小限制,文件会被自动清除！');
					$('#fileInput').uploadifyClearQueue(ID);
				}
			},
			'onQueueFull': function(event,data) {  //清除一个的时候.对应的循序清楚数组中的，后面的向前赋值。
     					alert("上传数目已满. 最多上传1个文件！");
    		 },
			'onComplete' : function(event, queueID, fileObj, response, data) {
			   	$("#result").html(response); 					//显示上传成功结果
			    setInterval("showResult()", 5000); 				//5秒后删除显示的上传成功结果 
			}

		});

	});