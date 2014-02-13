package com.instantChat.action;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.instantChat.model.Tuser;
import com.instantChat.pageModel.Json;
import com.instantChat.serviceI.UploadServiceI;
import com.instantChat.serviceI.UserServiceI;
import com.instantChat.util.CreateFileUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 处理jquery上传头像时的action
 */
public class UploadTouxiangAction extends BaseAction{
	private File fileInput;
	private String fileInputFileName;
	private String savePath;
	private UploadServiceI uploadService;
	private UserServiceI userService;
	//接收useId
	private String useId;

	public String getUseId() {
		return useId;
	}
	public void setUseId(String useId) {
		this.useId = useId;
	}

	public UserServiceI getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	
	public String execute() throws Exception {
		//图片的uuid，防止重命名
		UUID uuid = UUID.randomUUID();
		//得到request
		HttpServletRequest request = ServletActionContext.getRequest();
		String tuserId = request.getParameter("useId");
		//得到当前用户
		Tuser tuser = userService.getUserById(tuserId);
		String uid = tuser.getId();
		String newFileName = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String savePath = this.getSavePath();
		String oldFileName = this.getFileInputFileName().substring(this.getFileInputFileName().lastIndexOf('.') + 1);
		newFileName =uuid + "." + oldFileName;
		this.getFileInput().renameTo(new File(savePath + "/" + newFileName));
		//获取服务器上传文件的绝对路径
		ActionContext ac = ActionContext.getContext();
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		String basePath = sc.getRealPath("/");
		// 上传图片的存放路径
		String destDirName = basePath + "UserPic/UserPicSrc/";
		//本地目录
		String localPicDir = destDirName + uid;
		//创建本地目录
		CreateFileUtil.createDir(localPicDir);
		//复制服务器下的文件到本地目录
		CreateFileUtil.copyFile(basePath + "upload/" + newFileName, 
									localPicDir + "/" + newFileName);

		//删除服务器中的文件，减少负荷
		CreateFileUtil.delFile(basePath + "upload/" + newFileName);
		//图片的相对地址，有文件实体
		String webRootPicSrc = "UserPic" + "/" + "UserPicSrc" + "/" + uid + "/" + newFileName;
		//执行更新头像操作
		uploadService.uploadTxImg(webRootPicSrc, uid);
		response.getWriter().print("<font color='green'>头像上传成功！</font>");
		return null;
	}

	public File getFileInput() {
		return fileInput;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public String getSavePath() {
		return ServletActionContext.getRequest().getRealPath(this.savePath);
	}

	public UploadServiceI getUploadService() {
		return uploadService;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	@Resource
	public void setUploadService(UploadServiceI uploadService) {
		this.uploadService = uploadService;
	}

}

	













