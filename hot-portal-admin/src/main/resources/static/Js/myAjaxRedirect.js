$(function() {
	/** *************************************导航来源start************************************************ */
	// 跳转到导航新增页面
	$('#toPageSaveNav').click(function() {
		window.location.href = "ajaxRedirectAdd?ajaxPath=nav/add_nav";
	});
	$('#saveNav').click(function() {
		var name = $("#navName").val().trim();
		if (name == "") {
			artDialog.alert("请输入导航类别！");
			return false;
		}
		MyAjaxHelper.SaveNav(name);
	});
	$('#updateNav').click(function() {
		var name = $("#navName").val().trim();
		var nav_id = $("#nav_id").val().trim();
		if (name == "") {
			artDialog.alert("请输入导航类别！");
			return false;
		}
		MyAjaxHelper.UpdateNav(nav_id, name);
	});
	$('#back_Nav').click(function() {
		window.location.href = "navigation_index.php";
	});
	/** **********************************导航end************************************************ */
	/** *************************************视频来源start************************************************ */
	// 跳转到视频数据来源页面
	$('#toPageSaveSource').click(function() {
		window.location.href = "ajaxRedirectAdd?ajaxPath=source/add_source";
	});
	$('#saveSource').click(function() {
		var name = $("#sourceName").val().trim();
		if (name == "") {
			artDialog.alert("请输入视频数据源！");
			return false;
		}
		MyAjaxHelper.SaveSource(name);
	});
	$('#updateSource').click(function() {
		var name = $("#sourceName").val().trim();
		var source_id = $("#source_id").val().trim();
		if (name == "") {
			artDialog.alert("请输入视频数据源！");
			return false;
		}
		MyAjaxHelper.UpdateSource(source_id, name);
	});
	$('#back_source').click(function() {
		window.location.href = "video_source_index.php";
	});
	$('#back_source_index').click(function() {
		window.location.href = "video_source_index.php";
	});
	/** *************************************视频来源end************************************************ */
});

/** *************************************用户新增start************************************************ */
$(function() {
$('#toPageSaveUser').click(function() {
	window.location.href = "ajaxRedirectAdd?ajaxPath=user/add_user";
});
$('#saveUser').click(function() {
	var username = $("#userName").val().trim();
	var password = $("#password").val().trim();
	var description = $("#description").val().trim();
	if (username == "") {
		artDialog.alert("请输入用户名名称！");
		return false;
	}
	if (password == "") {
		artDialog.alert("请输入用户名密码！");
		return false;
	}
	if(password.length < 12){
		artDialog.alert("密码长度不能小于12,请重新输入用户密码!");
		return false;
	}
	if (description == "") {
		artDialog.alert("请输入用户名描述！");
		return false;
	}
	MyAjaxHelper.SaveUser(username,password,description);
});
$('#updateUser').click(function() {
	var userName = $("#userName").val().trim();
	var user_id = $("#user_id").val().trim();
	var userPassword = $("#userPassword").val().trim();
	var userDescription = $("#userDescription").val().trim();
	if (userName == "") {
		artDialog.alert("请输入用户名名称！");
		return false;
	}
	if (userPassword == "") {
		artDialog.alert("请输入用户名密码！");
		return false;
	}
	if(userPassword.length < 12){
		artDialog.alert("密码长度不能小于12,请重新输入用户密码!");
		return false;
	}
	if (userDescription == "") {
		artDialog.alert("请输入用户名描述！");
		return false;
	}
	
	MyAjaxHelper.UpdateUser(user_id, userName,userPassword,userDescription);
});
$('#back_user').click(function() {
	window.location.href = "user_index.php";
});

$('#searchUser').click(function() {
	var userName = $("#userName").val().trim();
	window.location.href = "user_index.php?userName=" + userName;
});

});
/** *************************************用户新增end************************************************ */
/** *************************************新增视频类别start************************************************ */
$(function() {
	$('#addVideoType').click(function() {
		window.location.href = "ajaxRedirectAdd?ajaxPath=videotype/add_videotype";
	});
	$('#saveVideoType').click(function() {
		var name = $("#videoType_name").val().trim();
		var param = $("#videoType_param").val().trim();
		var navigation_name = $("[name='videoType_navigation_name']:selected").val().trim();
		if (name == "") {
			artDialog.alert("请输入电影类别名名称！");
			return false;
		}
		if (param == "") {
			artDialog.alert("请输入电影类别参数！");
			return false;
		}
		if (navigation_name == "") {
			artDialog.alert("请输入电影类别导航！");
			return false;
		}
		MyAjaxHelper.SaveVideoType(name,param,navigation_name);
	});
	
	$('#updateVideoType').click(function() {
		var name = $("#videotype_name").val().trim();
		var id = $("#videotype_id").val().trim();
		var param = $("#param").val().trim();
		var navigation_name = $("[name='videoType_navigation_name']:selected").val().trim();
		if (name == "") {
			artDialog.alert("请输入视频类别名名称！");
			return false;
		}
		if (param == "") {
			artDialog.alert("请输入视频类别参数！");
			return false;
		}
		if (navigation_name == "") {
			artDialog.alert("请输入视频类别导航！");
			return false;
		}
		
		MyAjaxHelper.UpdateVideoType(id, name,param,navigation_name);
	});
	
	$('#back_videotype').click(function() {
		var nav_id = $("#videotype_id").val().trim();
		var pageNum = $("#pageNum").val().trim();
		window.location.href = "video_type_index.php?nav_id" + nav_id+"&pageNum=" +pageNum;
		
	});
	
	$('#back_videotype_index').click(function() {
		window.location.href = "video_type_index.php";
	});
	
	$('#searchVideoType').click(function() {
		var videoTypeName = $("#videoTypeName").val().trim();
		window.location.href = "video_type_index.php?videoTypeName=" + videoTypeName;
	});

});

/** *************************************新增视频类别end************************************************ */

/** *************************************新增视频地区start************************************************ */
$(function() {
	$('#addVideoArea').click(function() {
		window.location.href = "ajaxRedirectAdd?ajaxPath=videoarea/add_videoarea";
	});
	$('#saveVideoArea').click(function() {
		var name = $("#videoArea_name").val().trim();
		var param = $("#videoArea_param").val().trim();
		var navigation_name = $("[name=videoArea_navigation_name]:selected").val().trim();
		if (name == "") {
			artDialog.alert("请输入视频地区名称！");
			return false;
		}
		if (param == "") {
			artDialog.alert("请输入视频地区参数！");
			return false;
		}
		if (navigation_name == "") {
			artDialog.alert("请输入视频地区导航！");
			return false;
		}
		MyAjaxHelper.SaveVideoArea(name,param,navigation_name);
	});
	
	$('#updateVideoArea').click(function() {
		var name = $("#videoarea_name").val().trim();
		var id = $("#videoarea_id").val().trim();
		var param = $("#param").val().trim();
		var navigation_name = $("[name='videoArea_navigation_name']:selected").val().trim();
		if (name == "") {
			artDialog.alert("请输入视频地区名称！");
			return false;
		}
		if (param == "") {
			artDialog.alert("请输入视频地区参数！");
			return false;
		}
		if (navigation_name == "") {
			artDialog.alert("请输入视频地区导航！");
			return false;
		}
		
		MyAjaxHelper.UpdateVideoArea(id, name,param,navigation_name);
	});
	$('#back_videoarea').click(function() {
		var nav_id = $("#videoarea_id").val().trim();
		var pageNum = $("#pageNum").val().trim();
		window.location.href = "video_area_index.php?nav_id" + nav_id+"&pageNum=" +pageNum;
		
	});
	
	$('#back_videoarea_index').click(function() {
		window.location.href = "video_area_index.php";
	});
	
	
	$('#searchUser').click(function() {
		var userName = $("#userName").val().trim();
		window.location.href = "user_index.php?userName=" + userName;
	});
});
/** *************************************新增视频地区end************************************************ */

/** *************************************新增视频年代start************************************************ */
$(function() {
	$('#addVideoYear').click(function() {
		window.location.href = "ajaxRedirectAdd?ajaxPath=videoyear/add_videoyear";
	});
	$('#saveVideoYear').click(function() {
		var year = $("#videoYear_year").val().trim();
		var param = $("#videoYear_param").val().trim();
		var navigation_name = $("[name='videoYear_navigation_name']:selected").val().trim();
		if (year == "") {
			artDialog.alert("请输入视频年代！");
			return false;
		}
		if (param == "") {
			artDialog.alert("请输入视频年代参数！");
			return false;
		}
		if (navigation_name == "") {
			artDialog.alert("请输入视频年代导航！");
			return false;
		}
		MyAjaxHelper.SaveVideoYear(year,param,navigation_name);
	});
	
	$('#updateVideoYear').click(function() {
		var year = $("#year").val().trim();
		var id = $("#videoyear_id").val().trim();
		var param = $("#param").val().trim();
		var navigation_name = $("[name='videoYear_navigation_name']:selected").val().trim();
		if (year == "") {
			artDialog.alert("请输入视频年代！");
			return false;
		}
		if (param == "") {
			artDialog.alert("请输入视频年代参数！");
			return false;
		}
		if (navigation_name == "") {
			artDialog.alert("请输入视频年代导航！");
			return false;
		}
		
		MyAjaxHelper.UpdateVideoYear(id, year,param,navigation_name);
	});
	$('#back_videoyear').click(function() {
		var nav_id = $("#videoyear_id").val().trim();
		var pageNum = $("#pageNum").val().trim();
		window.location.href = "video_year_index.php?nav_id" + nav_id+"&pageNum=" +pageNum;
	});
	
	$('#back_videoyear_index').click(function() {
		window.location.href = "video_year_index.php";
	});
	
	$('#searchUser').click(function() {
		var userName = $("#userName").val().trim();
		window.location.href = "user_index.php?userName=" + userName;
	});
});
/** *************************************新增视频年代end************************************************ */

/** *************************************新增视频明星start************************************************ */
$(function() {
	$('#addVideoActor').click(function() {
		window.location.href = "ajaxRedirectAdd?ajaxPath=videoactor/add_videoactor";
	});
	$('#saveVideoActor').click(function() {
		var name = $("#videoActor_name").val().trim();
		var param = $("#videoActor_param").val().trim();
		var navigation_name = $("[name='videoActor_navigation_name']:selected").val().trim();
		
		if (name == "") {
			artDialog.alert("请输入视频明星名称！");
			return false;
		}
		if (param == "") {
			artDialog.alert("请输入视频明星参数！");
			return false;
		}
		if (navigation_name == "") {
			artDialog.alert("请输入视频明星导航！");
			return false;
		}
		MyAjaxHelper.SaveVideoActor(name,param,navigation_name);
	});
	
	$('#updateVideoActor').click(function() {
		var name = $("#videoactor_name").val().trim();
		var id = $("#videoactor_id").val().trim();
		var param = $("#param").val().trim();
		var navigation_name = $("[name='videoActor_navigation_name']:selected").val().trim();
		if (name == "") {
			artDialog.alert("请输入视频明星名称！");
			return false;
		}
		if (param == "") {
			artDialog.alert("请输入视频明星参数！");
			return false;
		}
		if (navigation_name == "") {
			artDialog.alert("请输入视频明星导航！");
			return false;
		}
		
		MyAjaxHelper.UpdateVideoActor(id, name,param,navigation_name);
	});
	
	
	$('#back_videoactor').click(function() {
		var nav_id = $("#videoactor_id").val().trim();
		var pageNum = $("#pageNum").val().trim();
		window.location.href = "video_actor_index.php?nav_id" + nav_id+"&pageNum=" +pageNum;
	});
	
	$('#back_videoactor_index').click(function() {
		window.location.href = "video_actor_index.php";
	});
	$('#searchUser').click(function() {
		var userName = $("#userName").val().trim();
		window.location.href = "user_index.php?userName=" + userName;
	});
});
/** *************************************新增视频明星end************************************************ */

/** *************************************新增热点视频类别start************************************************ */

$(function() {
	$('#toPageSaveVideoHotType').click(function() {
		window.location.href = "ajaxRedirectAdd?ajaxPath=videohottype/add_videohottype";
	});
	$('#saveVideoHotType').click(function() {
		var typeName = $("#videoHotType_name").val().trim();
		var nav_id = $("[name='videoHotType_nav_id']:selected").val().trim();
		
		if (typeName == "") {
			artDialog.alert("请输入热点视频名称！");
			return false;
		}
		
		if (nav_id == "") {
			artDialog.alert("请输入热点视频类别！");
			return false;
		}
		MyAjaxHelper.SaveVideoHotType(typeName,nav_id);
	});
	
	$('#updateVideoHotType').click(function() {
		var typeName = $("#videohuttype_name").val().trim();
		var id = $("#videohuttype_id").val().trim();
		var nav_id = $("[name='nav_id']:selected").val().trim();
		if (typeName == "") {
			artDialog.alert("请输入热点视频类别名称！");
			return false;
		}
		
		if (nav_id == "") {
			artDialog.alert("请输入热点视频类别分类！");
			return false;
		}
		
		MyAjaxHelper.UpdateVideoHotType(id, typeName,nav_id);
	});
	$('#back_videohottype').click(function() {
		window.location.href = "video_hottype_index.php";
	});
	
	
	$('#nav_select').change(function(){
		var nav_id = $("#nav_select").val();
		if(nav_id != -1){
			window.location.href = "video_hottype_index.php?nav_id=" + nav_id; 
		}else{
			window.location.href = "video_hottype_index.php";
		}
	});
	
});
/** *************************************新增热点视频类别end************************************************ */
/** *************************************热点视频列表start************************************************ */
$(function(){
	$('#hotlist_nav_select').change(function(){
		var nav_id = $('#hotlist_nav_select').val();
		var data = {
			nav_id : nav_id
		};
		RequestHelper.post("./SelectVideoHotData.ajax", data, function(ret) {
			$('#hot_id_select').empty();
			for(var i=0;i<ret.data.length;i++){
					$("#hot_id_select").append('<option name="hot_id_select" value="'+ret.data[i].id+'">'+ret.data[i].typeName+'</option>');
			}
		});
	});
	
	$('#hot_id_select').change(function(){
		var hot_id = $('#hot_id_select').val();
		var data={
			hot_id:hot_id
		};
		RequestHelper.post("./SelectVideoHotVideoData.ajax", data, function(ret) {
			$('#tbodyData').empty();
			for(var i=0;i<ret.data.length;i++){
			    var nav_type="";
				var trTag="<tr>";
				var tdTag="<td>"+ret.data[i].id+"</td>";
				tdTag+="<td>"+ret.data[i].videoName+"</td>";
				tdTag+="<td>"+ret.data[i].director+"</td>";
				tdTag+="<td>"+ret.data[i].actorsStr+"</td>";
				//tdTat+='<td width="10%">' +ret.data[i].actorsStr+'</td>';
				//tdTag+="<td>width="+ret.data[i].adImgUrl+"</td>";
				tdTag+= '<td><img src="'+ret.data[i].adImgUrl+'" style="width:80px;height:80px"></td>';  
           		//<td><img src="$!{item.adImgUrl}" style="width:80px;height:80px;"></td>
				tdTag+="<td>"+ret.data[i].score+"分</td>";
				tdTag+="<td>"+ret.data[i].length+"</td>";
				tdTag+="<td>"+ret.data[i].typeStr+"</td>";
				tdTag+="<td>"+ret.data[i].areaStr+"</td>";
				tdTag+="<td>"+ret.data[i].yearStr+"</td>";
				tdTag+="<td>"+ret.data[i].navType+"</td>";
				trTag+=tdTag+"</tr>";
				$("#tbodyData").append(trTag);
			}
		});
	});
	
});

/** *************************************热点视频列表end************************************************ */

/** *************************************电影视频新增start************************************************ */
$(function(){
	
	$('#select_Nav').change(function(){
		var nav_id = $('#select_Nav').val();
		var data = {
			nav_id : nav_id
		};
		RequestHelper.post("./selectMovieVideoData.ajax", data, function(ret) {
			for(var i=0;i < ret.data.strAreaList.length;i++){
				$('#select_Area').append('<option name="addMovie_Area_name" value="'+
						ret.data.strAreaList[i].name+'">'+ret.data.strAreaList[i].name+'</option>');
			}
			for(var i=0;i < ret.data.strTypeList.length;i++){
				$('#select_Type').append('<option name="addMovie_type_name" value="'+
						ret.data.strTypeList[i].name+'">'+ret.data.strTypeList[i].name+'</option>');
			}
			for(var i=0;i < ret.data.strYearList.length;i++){
				$('#select_Year').append('<option name="addMovie_year_name" value="'+
						ret.data.strYearList[i].name+'">'+ret.data.strYearList[i].name+'</option>');
			}
		});
	});
	
	$('#saveAddMovie').click(function(){
		var videoName = $('#addMovie_videoName').val();
		var navType = $('#select_Nav').val();
		var director = $('#addMovie_director').val();
		var actorsStr = $('#addMovie_actorsStr').val();
		var areaStr = $('#select_Area').val();
		var typeStr = $('#select_Type').val();
		var yearStr = $("#select_Year").val();
		var intro = $('#addMovie_intro').val();
		var data={
			videoName : videoName,
			navType : navType,
			director : director,
			actorsStr : actorsStr,
			areaStr : areaStr,
			typeStr : typeStr,
			yearStr : yearStr,
			intro : intro
		};
		RequestHelper.post("./addMovieSource", data, function(ret) {
			artDialog.alert("成功");
			window.location.href = "video_addmovievideo_index.php";
		});
	});
});
/** *************************************电影视频新增end************************************************ */

/** *************************************缓存日志上传start************************************************ */
$(function(){
	function LimitAttach(file,type) {
		var extArrayZIP = new Array(".zip");
		var allowSubmit = false;
		if (!file) {
			return;
		}
		while (file.indexOf("\\") != -1) {
			file = file.slice(file.indexOf("\\") + 1);
		}
		var ext = file.slice(file.indexOf(".")).toLowerCase();
		var extArray=extArrayZIP;
		
		if(type=="ZIP"){
			extArray=extArrayZIP;
		}
		for ( var i = 0; i < extArray.length; i++) {
			if (extArray[i] == ext) {
				allowSubmit = true;
				break;
			}
		}
		if (allowSubmit) {
			return true;
		} else {
			alert("只能上传以下格式的文件:" + (extArray.join(""))
					+ "\n请重新选择再上传.");
			return false;
		}
	}
	$('#upLoadData').click(function(){
		var dataSource = $('#addLoadDataSource').val();
		if(dataSource == ""){
			alert("请选择数据源路径");
			return false;
		}
		var rfileZip=LimitAttach(dataSource,"ZIP");
		if(!rfileZip){
			$('#addLoadDataSource').attr("value","");
			return false;
		}
		$.ajaxFileUpload({
			url:'./uploadZip',
			data :{},
			secureuri : false,
			fileElementId : 'addLoadDataSource',
			dataType:'xml',
			success : function(data, status) //相当于java中try语句块的用法  
			{
				
				artDialog.alert("数据源压缩包上传成功！");
				window.location.href = "log_analyse_index.php";
			}
		});
		
	});
	
});
/** *************************************缓存日志上传end************************************************ */

/** *************************************缓存日志展示start************************************************ */
$(function(){
	$('#searchLogData').click(function(){
		var startDate = $('#TCStartDate').val();
		var endDate = $('#TCEndDate').val();
		if(startDate == null){
			artDialog.alert("请选择起始时间");
		}
		if(endDate == null){
			artDialog.alert("请选择终止时间");
		}
		window.location.href = "log_showData_index.php?startDate=" + startDate+"&endDate=" + endDate;
	});
});
/** *************************************缓存日志展示end************************************************ */
/** *************************************缓存日志分析start************************************************ */
$(function(){
	$('#searchData').click(function(){
		var startLogDate = $('#StartDate').val();
		var endLogDate = $('#EndDate').val();
		if(startLogDate == null){
			artDialog.alert("请选择起始时间");
		}
		if(endLogDate == null){
			artDialog.alert("请选择终止时间");
		}
		window.location.href = "log_analyseLog_index.php?startLogDate=" + startLogDate+"&endLogDate=" + endLogDate;
	});
	$('#searchHotData').click(function(){
		var startHotDate = $('#StartHotDate').val();
		var endHotDate = $('#EndHotDate').val();
		if(startHotDate == null){
			artDialog.alert("请选择起始时间");
		}
		if(endHotDate == null){
			artDialog.alert("请选择终止时间");
		}
		window.location.href = "log_analyseLog_index.php?startHotDate=" + startHotDate+"&endHotDate=" + endHotDate;
	});
});
/** *************************************缓存日志分析end************************************************ */


function MyAjaxHelper() {
};
/** *************************************导航start************************************************ */
// 获得导航的子类型
MyAjaxHelper.getNavType = function(nav_id) {
	console.info(nav_id);
	RequestHelper.post("./getNavChildTypes.php", {
		nav_id : nav_id
	}, function(ret) {
		console.info(ret);
		window.location.href = "navigation_index.php";
		artDialog.alert("成功");
	});
};
// 保存导航
MyAjaxHelper.SaveNav = function(name) {
	RequestHelper.post("./save_nav", {
		name : name
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "navigation_index.php";
	});
};
// 跳转到修改导航
MyAjaxHelper.ToUpdateNav = function(id) {
	window.location.href = "toUpdateNav.php?id=" + id;
};
// 修改导航
MyAjaxHelper.UpdateNav = function(id, name) {
	RequestHelper.post("./update_nav", {
		id : id,
		name : name
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "navigation_index.php";
	});
};
/** *************************************导航end************************************************ */
/** *************************************视频来源start************************************************ */
// 保存视频来源
MyAjaxHelper.SaveSource = function(name) {
	RequestHelper.post("./save_source", {
		name : name
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "video_source_index.php";
	});
};
// 跳转到修改视频来源
MyAjaxHelper.ToUpdateSource = function(id) {
	window.location.href = "toUpdateSource.php?id=" + id;
};
// 修改视频来源
MyAjaxHelper.UpdateSource = function(id, name) {
	RequestHelper.post("./update_source", {
		id : id,
		name : name
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "video_source_index.php";
	});
};
/** *************************************视频来源end************************************************ */

/** *************************************用户新增start************************************************ */
MyAjaxHelper.SaveUser = function(username,password,description) {
	RequestHelper.post("./save_user", {
		username : username,
		password : password,
		description : description
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "user_index.php";
	});
};
// 跳转到修改用户
MyAjaxHelper.ToUpdateUser = function(id) {
	window.location.href = "toUpdateUser.php?id=" + id;
};
//删除
MyAjaxHelper.ToDeleteUser = function(id) {
	artDialog(
	        {	
	            content:'是否删除该用户',
	            lock:true,
	            style:'succeed noClose'
	        },
	        function(){
	            window.location.href = "toDeleteUser.php?id=" + id;
	        },
	        function(){
	        	window.location.href = "user_index.php";
	        }
	);
	
};
// 修改用户
MyAjaxHelper.UpdateUser = function(user_id, userName,userPassword,userDescription) {
	RequestHelper.post("./update_user", {
		userName : userName,
		user_id : user_id,
		userPassword:userPassword,
		userDescription : userDescription
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "user_index.php";
	});
};
/** *************************************用户新增end************************************************ */
/** *************************************新增视频类别start************************************************ */
MyAjaxHelper.SaveVideoType = function(name,param,navigation_name) {
	RequestHelper.post("./save_videotype", {
		name : name,
		param : param,
		navigation_name : navigation_name
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "video_type_index.php";
	});
};
// 跳转到修改视频类型
MyAjaxHelper.ToUpdateVideoType = function(id,pageNum) {
	window.location.href = "toUpdateVideoType.php?id=" + id+ "&pageNum=" + pageNum;
};

// 修改视频类型
MyAjaxHelper.UpdateVideoType = function(id, name,param,navigation_name) {
	RequestHelper.post("./update_videotype", {
		name : name,
		id : id,
		param:param,
		navigation_name : navigation_name
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "video_type_index.php";
	});
};
/** *************************************新增视频类别end************************************************ */

/** *************************************新增视频地区start************************************************ */

MyAjaxHelper.SaveVideoArea = function(name,param,navigation_name) {
	RequestHelper.post("./save_videoarea", {
		name : name,
		param : param,
		navigation_name : navigation_name
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "video_area_index.php";
	});
};
// 跳转到修改地区
MyAjaxHelper.ToUpdateVideoArea = function(id,pageNum) {
	window.location.href = "toUpdateVideoArea.php?id=" + id+ "&pageNum=" + pageNum;
};

// 修改地区
MyAjaxHelper.UpdateVideoArea = function(id, name,param,navigation_name) {
	RequestHelper.post("./update_videoarea", {
		name : name,
		id : id,
		param:param,
		navigation_name : navigation_name
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "video_area_index.php";
	});
};
/** *************************************新增视频地区end************************************************ */
/** *************************************新增视频年代start************************************************ */
MyAjaxHelper.SaveVideoYear = function(year,param,navigation_name) {
	RequestHelper.post("./save_videoyear", {
		year : year,
		param : param,
		navigation_name : navigation_name
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "video_year_index.php";
	});
};
// 跳转到修改年代
MyAjaxHelper.ToUpdateVideoYear = function(id,pageNum) {
	window.location.href = "toUpdateVideoYear.php?id=" + id + "&pageNum=" + pageNum;
};

// 修改年代
MyAjaxHelper.UpdateVideoYear = function(id, year,param,navigation_name) {
	RequestHelper.post("./update_videoyear", {
		year : year,
		id : id,
		param:param,
		navigation_name : navigation_name
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "video_year_index.php";
	});
};
/** *************************************新增视频年代end************************************************ */
/** *************************************新增视频明星start************************************************ */
MyAjaxHelper.SaveVideoActor = function(name,param,navigation_name) {
	RequestHelper.post("./save_videoactor", {
		name : name,
		param : param,
		navigation_name : navigation_name
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "video_actor_index.php";
	});
};
// 跳转到修改明星
MyAjaxHelper.ToUpdateVideoActor = function(id,pageNum) {
	window.location.href = "toUpdateVideoActor.php?id=" + id+ "&pageNum=" + pageNum;
};

// 修改明星
MyAjaxHelper.UpdateVideoActor = function(id, name,param,navigation_name) {
	RequestHelper.post("./update_videoactor", {
		name : name,
		id : id,
		param:param,
		navigation_name : navigation_name
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "video_actor_index.php";
	});
};
/** *************************************新增视频明星end************************************************ */
/** *************************************新增热点视频类别start************************************************ */
MyAjaxHelper.SaveVideoHotType = function(typeName,nav_id) {
	RequestHelper.post("./save_videohottype", {
		typeName : typeName,
		nav_id : nav_id,
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "video_hottype_index.php";
	});
};
// 跳转到热点视频类型
MyAjaxHelper.ToUpdateVideoHotType = function(id) {
	window.location.href = "toUpdateVideoHotType.php?id=" + id;
};

//修改热点视频类型
MyAjaxHelper.UpdateVideoHotType = function(id, typeName,nav_id) {
	RequestHelper.post("./update_videohottype", {
		typeName : typeName,
		id : id,
		nav_id : nav_id
	}, function(ret) {
		artDialog.alert("成功");
		window.location.href = "video_hottype_index.php";
	});
};

/** *************************************新增热点视频类别end************************************************ */