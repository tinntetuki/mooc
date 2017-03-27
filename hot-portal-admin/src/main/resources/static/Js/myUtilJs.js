function ExcelHelper() {};
function LoginHelper() {};
var extArray = new Array(".xls", ".xlsx");
/**
 * 文件格式验证
 */
ExcelHelper.LimitAttach = function(file) {
	var allowSubmit = false;
	if (!file) {
		return;
	}
	while (file.indexOf("\\") != -1) {
		file = file.slice(file.indexOf("\\") + 1);
	}
	var ext = file.slice(file.indexOf(".")).toLowerCase();
	for ( var i = 0; i < extArray.length; i++) {
		if (extArray[i] == ext) {
			allowSubmit = true;
			break;
		}
	}
	if (allowSubmit) {
		return true;
	} else {
		artDialog.alert("只能上传以下格式的文件:" + (extArray.join("")) + "\n请重新选择再上传.");
		return false;
	}
};
/**
 * 得到excel类型
 * 
 * @param fileUpload
 * @returns
 */
ExcelHelper.getOperateType = function(fileUpload) {
	var strtype = fileUpload
			.substring(fileUpload.length - 3, fileUpload.length);
	var operateType;
	if (strtype == "xls") {
		operateType = true;
	}
	if (strtype == "lsx") {
		operateType = false;
	}
	return operateType;
};
/**
excel文件上传
 */
ExcelHelper.ajaxFileUpload = function(fileUploadId, dataJson,dataType,url,funCallback) {
	var fileUpload = $('#' + fileUploadId).val();
	if (fileUpload == "") {
		artDialog.alert("请选择您需要上传的Excel文件！");
		return false;
	}
	var rfile = ExcelHelper.LimitAttach(fileUpload);
	if (!rfile) {
		return false;
	}
	var operateType=ExcelHelper.getOperateType(fileUpload);
	dataJson.operateType=operateType;
	// kendui自带的遮罩效果（开启）
	maskPageContent();
	$.ajaxFileUpload({
		url : url, // 需要链接到服务器地址
		/*data : {
			"operateType" : operateType,
			"typeId" : typeId
		},*/
		data:dataJson,
		secureuri : false,
		fileElementId : fileUploadId, // 文件选择框的id属性
		dataType : dataType, // 服务器返回的格式，可以是json
		success : function(data, status) // 相当于java中try语句块的用法
		{
			// kendui自带的遮罩效果(释放)
			unmaskPageContent();
			funCallback(data);
		},
		error : function(ret, status, e) // 相当于java中catch语句块的用法
		{
			artDialog.alert("系统繁忙，请稍后再试！");
			// kendui自带的遮罩效果(释放)
			unmaskPageContent();
		}
	}

	);

};
//////////////////////////复选框//////////////////////////////////////
function CheckBoxHelper(){};
/**
 * 复选框全选，
 */
CheckBoxHelper.checkAll=function(allCheckBoxId,deploy_CheckboxId){
	var allCheck = document.getElementById(allCheckBoxId).checked;
	var checkboxs=document.getElementsByName(deploy_CheckboxId);
	 for (var i=0;i<checkboxs.length;i++) {
	  var e=checkboxs[i];
	  e.checked=allCheck;
	 };
};
/**
 * 批量修改(选中)
 */
CheckBoxHelper.batchUpdate=function(deploy_CheckboxId){	
	   var checkItem=document.getElementsByName(deploy_CheckboxId);
	   var idsStr = "";
	   for(var i=0;i<checkItem.length;i++){
	        if(checkItem[i].checked){
	        	idsStr += checkItem[i].value;
	        	idsStr += "|";
	      };
	   }
	   return idsStr;
};
/**
 * 批量修改(未选中)
 */
CheckBoxHelper.unBatchUpdate=function(deploy_CheckboxId){	
	   var checkItem=document.getElementsByName(deploy_CheckboxId);
	   var idsStr = "";
	   for(var i=0;i<checkItem.length;i++){
	        if(!checkItem[i].checked){
	        	idsStr += checkItem[i].value;
	        	idsStr += "|";
	      };
	   }
	   return idsStr;
};
//////////////////////ajax提交（artDialog.alert()）//////////////////////////////

////////////////////////kenduiHelper//////////////////////////////
function KendUiHelper(){};
/**
 * 下拉框
 */
KendUiHelper.kendoDropDownList=function(dataUrl, params,dropDownListId,textField,valueField,isContainAll,firstName,funCallback){
	$(document).ready(function(){
		RequestHelper.get(dataUrl,params,function(data){
			if(isContainAll){
				data.splice(0,0,{id:-1,name:firstName});
			}
			$("#"+dropDownListId).kendoDropDownList({
				dataTextField: textField,
                dataValueField: valueField,
                dataSource: data,
                index: 0,
                change:function(e) {
                	funCallback(e);
             },
			});
		});
	});
};
//验证码刷新
LoginHelper.reLoadImage=function(){
	document.getElementById("valimg").src = "/LazyVideo/createImage.php?r="
		+ Math.random();
};
