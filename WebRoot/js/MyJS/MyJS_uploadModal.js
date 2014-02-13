/*
	头像上传的弹框事件添加
 */
  $(document).ready(function(){
	  alert("ssss");
	  $.teninedialog({
                    showCloseButton:true,
                    bootstrapModalOption:{keyboard: true},
                    dialogHidden:function(){
                        alert('关闭对话框');
                        window.location.href = "userRegisterAction!register";
                    },                    
           });
   });