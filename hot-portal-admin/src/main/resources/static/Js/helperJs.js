function loadScript(url){
    document.write('<script type="text/javascript" src="' + url + '" charset="utf-8"></script>');
}
function getBasePath(){
	var strFullPath = window.document.location.href;
	var strPath = window.document.location.pathname;
	var pos = strFullPath.indexOf(strPath);
	var prePath = strFullPath.substring(0, pos);
	var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
	return (prePath + postPath);
}
var maxtx=0.0,maxtxstr;
var maxrx=0.0,maxrxstr;
var avgtx=0.0,avgtxstr;
var avgrx=0.0,avgrxstr;
function ControlHelper(){}
/*ControlHelper.newTimeScope = function(start_time_id, end_time_id) {
	function startChange() {
        var startDate = start.value(),
        endDate = end.value();

        if (startDate) {
            startDate = new Date(startDate);
            startDate.setDate(startDate.getDate());
            end.min(startDate);
        } else if (endDate) {
            start.max(new Date(endDate));
        } else {
            endDate = new Date();
            start.max(endDate);
            end.min(endDate);
        }
    }

    function endChange() {
        var endDate = end.value(),
        startDate = start.value();

        if (endDate) {
            endDate = new Date(endDate);
            endDate.setDate(endDate.getDate());
            start.max(endDate);
        } else if (startDate) {
            end.min(new Date(startDate));
        } else {
            endDate = new Date();
            start.max(endDate);
            end.min(endDate);
        }
    }
	var now = new Date();
	var yes = new Date(now);
	yes.setDate(now.getDate()-1);
	var today_min = new Date(kendo.format('{0:yyyy-MM-dd HH:mm:00}', yes));
	var today_max = new Date(kendo.format('{0:yyyy-MM-dd HH:mm:00}', now));
	//var today_min = new Date(kendo.format('{0:yyyy-MM-dd "00:00:00"}', new Date()));
	//var today_max = new Date(kendo.format('{0:yyyy-MM-dd "23:59:59"}', new Date()));
    var start = $("#"+start_time_id).kendoDateTimePicker({
    	 format: "yyyy-MM-dd HH:mm:ss",
        value: today_min,
        timeFormat: "HH:mm",
        culture: "zh-CN",
        change: startChange,
        parseFormats: ["yyyy-MM-dd HH:mm:ss"]
    }).data("kendoDateTimePicker");

    var end = $("#"+end_time_id).kendoDateTimePicker({
    	format: "yyyy-MM-dd HH:mm:ss",
        value: today_max,
        timeFormat: "HH:mm",
        culture: "zh-CN",
        change: endChange,
        parseFormats: ["yyyy-MM-dd HH:mm:ss"]
    }).data("kendoDateTimePicker");

    start.max(end.value());
    end.min(start.value());
};*/

//时间控件接受传参--2014/04/18 zqx add
ControlHelper.newTimeScope = function(start_time_id, end_time_id,start_time,end_time) {
	function startChange() {
        var startDate = start.value(),
        endDate = end.value();

        if (startDate) {
            startDate = new Date(startDate);
            startDate.setDate(startDate.getDate());
            end.min(startDate);
        } else if (endDate) {
            start.max(new Date(endDate));
        } else {
            endDate = new Date();
            start.max(endDate);
            end.min(endDate);
        }
    }

    function endChange() {
        var endDate = end.value(),
        startDate = start.value();

        if (endDate) {
            endDate = new Date(endDate);
            endDate.setDate(endDate.getDate());
            start.max(endDate);
        } else if (startDate) {
            end.min(new Date(startDate));
        } else {
            endDate = new Date();
            start.max(endDate);
            end.min(endDate);
        }
    }
	var now = new Date();
	var yes = new Date(now);
	yes.setDate(now.getDate()-1);
	if(start_time != null && end_time != null){
		yes = MyUtil.parseDateTime(start_time);
		now = MyUtil.parseDateTime(end_time);
	}
    var start = $("#"+start_time_id).kendoDateTimePicker({
    	 format: "yyyy-MM-dd HH:mm:ss",
        value: yes,
        timeFormat: "HH:mm",
        culture: "zh-CN",
        change: startChange,
        parseFormats: ["yyyy-MM-dd HH:mm:ss"]
    }).data("kendoDateTimePicker");

    var end = $("#"+end_time_id).kendoDateTimePicker({
    	format: "yyyy-MM-dd HH:mm:ss",
        value: now,
        timeFormat: "HH:mm",
        culture: "zh-CN",
        change: endChange,
        parseFormats: ["yyyy-MM-dd HH:mm:ss"]
    }).data("kendoDateTimePicker");

    start.max(end.value());
    end.min(start.value());
};

ControlHelper.newOrderBy = function(orderById) {
	 $("#" + orderById).kendoDropDownList({
         dataTextField: "text",
         dataValueField: "value",
         dataSource: [{text:'按流量排行',value:'traffic'},{text:'按请求数排行',value:'requestCount'}],
         index: 0
     });
};
ControlHelper.newTop = function(topId) {
	 $("#" + topId).kendoDropDownList({
        dataTextField: "text",
        dataValueField: "value",
        dataSource: [{text:'100',value:100},{text:'500',value:500},{text:'1000',value:1000}],
        index: 0
    });
};
ControlHelper.newPieGraphic = function(graphic_id, graphic_data) {
	// Build the chart
	var name_field = 'name';
	var value_field = 'value';
	if (graphic_data.name_field) name_field = graphic_data.name_field;
	if (graphic_data.value_field) value_field = graphic_data.value_field;
	var datas = [];
	for (var i = 0; i < graphic_data['datas'].length; ++i) {
		var item = graphic_data['datas'][i];
		datas.push([item[name_field],item[value_field]]);
	}
	var title = '';
	if (graphic_data.title) {
		title = graphic_data.title;
	}
	var pie_name = '区域';
	if (graphic_data.pie_name) {
		graphic_data.pie_name = pie_name;
	}
        var chartData = {
            chart: {
                renderTo:graphic_id,
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: title
            },
            tooltip: {
            	 formatter: function(){
                 	return '<b>'+this.point.name+'</b>:'+this.percentage.toFixed(2)+'%';
                 },
            	percentageDecimals: 1
            },
            exporting: {
                buttons: {
                    contextButton: {
                        menuItems: [{
                            text: '导出为PNG(高清)',
                            onclick: function() {
                                this.exportChart();
                            },
                            separator: false
                        }]
                    }
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false,
                        formatter: function(){
                        	return '<b>'+this.point.name+'</b>:'+this.percentage.toFixed(2)+'%';
                        }
                    },
                    showInLegend: true/*,
                    events:{
                    	mouseOver:function(e) {
                    		e.target.select(true);
                    	}
                    }*/
                }
            },
            series: [{
                type: 'pie',
                name: pie_name,
                data: datas                        
            }]
        };
        new Highcharts.Chart(chartData);
};

var factor = 1000.0;
function toMorG(traffic) {
	var mb= (traffic*1.0)/(factor*factor);
	if(mb>=1024.0){
		return ((mb/factor).toFixed(2)).toString()+"G";
	}else{
		return mb.toFixed(2).toString()+"M";
	}
}
function toMorT(traffic) {
	var gb= (traffic*1.0)/(factor*factor);
	if(gb>=1024.0){
		return ((gb/factor).toFixed(3)).toString()+"T";
	}else{
		return gb.toFixed(3).toString()+"G";
	}
}
/*
 * {titile:'流量趋势图',time_field:'stat_time','rx_field':'rx','tx_field':'tx',datas:[{stat_time,tx,rx}]}
 */
ControlHelper.newTrafficTrendencyGraphic = function(graphic_id, graphic_data) {
	var datas_old = graphic_data['datas'];//graphic_data.datas
	if (!graphic_data.title) graphic_data.title = '流量趋势图';//
	var time_field = 'statTime';
	if (graphic_data.time_field) time_field = graphic_data.time_field;
	var rx_field = 'rx';
	var tx_field = 'tx';
	if (graphic_data.rx_field) rx_field = graphic_data.rx_field;
	if (graphic_data.tx_field) tx_field = graphic_data.tx_field;
	var datas = [[],[]];
	var sumtx=0.0;
	var sumrx=0.0;
	var txi = 0;
	var rxi = 0;
	
	for (var i = 0; i < datas_old.length; ++i) {
		var t = MyUtil.parseDateTime(datas_old[i][time_field]).getTime() + 8*3600000;
		//sum
		sumtx += datas_old[i][tx_field];
		sumrx += datas_old[i][rx_field];
		//max
		if(txi<datas_old[i][tx_field]){
			txi = datas_old[i][tx_field];
		   }
		if(rxi<datas_old[i][rx_field]){
			rxi = datas_old[i][rx_field];
		   }
		//push into array
		datas[0].push([t,datas_old[i][tx_field]]);
		datas[1].push([t,datas_old[i][rx_field]]);
	}
	
	if(datas_old.length>0){
		avgtx = sumtx/datas_old.length;
		avgrx = sumrx/datas_old.length;		
	}
	/*var ret ="(吐出流量[平均值："+avgtxstr+", 峰值："+maxtxstr+"]"
			+" 回源流量[平均值："+avgrxstr+", 峰值："+maxrxstr+"])";*/
	var span="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	  	+"<span  style=\"color:#4572A7\">(吐出流量[平均值："+toMorG(avgtx)+", 峰值："+toMorG(txi)+"] </span>"
	  	+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	  	+"<span style=\"color:#55BF3B\">回源流量[平均值："+toMorG(avgrx)+", 峰值："+toMorG(rxi)+"])</span>";
	
	if("graphic_day"==graphic_id){
		$("h5").eq(0).find("span").empty();
		$("h5").eq(0).append(span);
	}
	if("graphic_week"==graphic_id){
		$("h5").eq(1).find("span").empty();
		$("h5").eq(1).append(span);
	}
	if("graphic_month"==graphic_id){
		$("h5").eq(2).find("span").empty();
		$("h5").eq(2).append(span);
	}
	if(graphic_id.indexOf("graphic_areas_summany") >= 0){
		var toolBar_id = graphic_id+'toolbar';
		$('#'+toolBar_id).find("span").empty();
		$('#'+toolBar_id).append(span);
	}
	if(graphic_id.indexOf("graphic_machine_summany") >= 0){
		var serviceDesc = '服务类型:' +'&nbsp;&nbsp;'+ graphic_data.serviceType;
		var toolBar_id = graphic_id+'toolbar';
		$('#'+toolBar_id).find("span").empty();
		$('#'+toolBar_id).append(serviceDesc+span);
	}
	
	if(graphic_id.indexOf("nic_machine_summany") >= 0){
		var toolBar_id = graphic_id+'toolbar';
		$('#'+toolBar_id).find("span").empty();
		$('#'+toolBar_id).append(span);
	}
	
	if("graphic_query1"==graphic_id){
		$("#queryTraffic_span").find("span").empty();
		$("#queryTraffic_span").append(span);
	}
	
    var charData = {
        chart: {
            renderTo: graphic_id,
            type: 'spline',
            marginRight: 20,
            zoomType:'xy'
        },
        colors:['#4572A7','#55BF3B'],
        legend: {
                //layout:'vertical'
            },
        title: {
        	useHTML:true,
            text: graphic_data.title//峰值："+this.yAxis.max+"
        },
    /*    subtitle: {
        	useHTML:true,
            text: ret,
            style: {
            	fontSize:4
            }
        },*/
        xAxis: {
        	gridLineWidth: 1,
	        type: 'datetime',
	        dateTimeLabelFormats: { // don't display the dummy year
	                day:'%m月%d',
	            month: '%e 月 %b',
	            year: '%b'
		    },
	        title: {
                text: "时间"
            }
        },
        yAxis: {
        	gridLineWidth: 1,
            title: {
                text: "流量"
            }
        },
        exporting: {
            buttons: {
                contextButton: {
                    menuItems: [{
                        text: '导出为PNG(高清)',
                        onclick: function() {
                            this.exportChart();
                        },
                        separator: false
                    }]
                }
            }
        },
        plotOptions: {
            line: {
                lineWidth: 1,
                states: {
                    hover: {
                        lineWidth: 2
                    }
                },
                marker: {
                    enabled: false,
                    states: {
                        hover: {
                            enabled: true,
                            symbol: 'circle',
                            radius: 1,
                            lineWidth: 1
                        }
                    }
                }
            },
            area:{
                    fillOpacity:0.85,
                    lineWidth:0,
                    states: {
                    hover: {
                        lineWidth: 2
                    }
                },
                marker: {
                    enabled: false,
                    states: {
                        hover: {
                            enabled: true,
                            symbol: 'circle',
                            radius: 1,
                            lineWidth: 1
                        }
                    }
                }
            }
        },
        tooltip: {
    	 formatter: function() {
                 var s = '<span style="color:#034BA0">'+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'</span>';
                 $.each(this.points, function(i, point) {
                	   var valy = point.y;
                	   var valuestr = toMorG(valy);
                	   s += '<br/><span style=\"'+"color:"+point.series.color+"\">"+ point.series.name +'</span>:<b>'
                           + valuestr +'</b>';                	   
                   });
                   return s;
               },
            //pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> <br/>',
            xDateFormat: '%Y-%m-%d %H:%M:%S',
            shared: true,
            crosshairs: true
        },
        series: [
            //{name:'回源流量',type:'area',data:datas[0]},
            //{name:'吐出流量',type:'line',data:datas[1]}         
            {name:'吐出流量',type:'line',data:datas[0]}, 
            {name:'回源流量',type:'area',data:datas[1]},
            ]
    };
    new Highcharts.Chart(charData);
};

/***
 * 特殊样式的流量图，用于截图和特殊机器，
 */
ControlHelper.newSpecialTrafficTrendencyGraphic = function(graphic_id, graphic_data) {
	var datas_old = graphic_data['datas'];//graphic_data.datas
	if (!graphic_data.title) graphic_data.title = '流量趋势图';//
	var time_field = 'statTime';
	if (graphic_data.time_field) time_field = graphic_data.time_field;
	var rx_field = 'rx';
	var tx_field = 'tx';
	if (graphic_data.rx_field) rx_field = graphic_data.rx_field;
	if (graphic_data.tx_field) tx_field = graphic_data.tx_field;
	var datas = [[],[]];
	var sumtx=0.0;
	var sumrx=0.0;
	var txi = 0;
	var rxi = 0;
	
	for (var i = 0; i < datas_old.length; ++i) {
		var t = MyUtil.parseDateTime(datas_old[i][time_field]).getTime() + 8*3600000;
		//sum
		sumtx += datas_old[i][tx_field];
		sumrx += datas_old[i][rx_field];
		//max
		if(txi<datas_old[i][tx_field]){
			txi = datas_old[i][tx_field];
		   }
		if(rxi<datas_old[i][rx_field]){
			rxi = datas_old[i][rx_field];
		   }
		//push into array
		datas[0].push([t,datas_old[i][tx_field]]);
		datas[1].push([t,datas_old[i][rx_field]]);
	}
	
	if(datas_old.length>0){
		avgtx = sumtx/datas_old.length;
		avgrx = sumrx/datas_old.length;		
	}
	/*var ret ="(吐出流量[平均值："+avgtxstr+", 峰值："+maxtxstr+"]"
			+" 回源流量[平均值："+avgrxstr+", 峰值："+maxrxstr+"])";*/
	var span="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	  	+"<span  style=\"color:#55BF3B\">(吐出流量[平均值："+toMorG(avgtx)+", 峰值："+toMorG(txi)+"] </span>"
	  	+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	  	+"<span style=\"color:#4573a7\">回源流量[平均值："+toMorG(avgrx)+", 峰值："+toMorG(rxi)+"])</span>";
	
	if("graphic_day"==graphic_id){
		$("h5").eq(0).find("span").empty();
		$("h5").eq(0).append(span);
	}
	if("graphic_week"==graphic_id){
		$("h5").eq(1).find("span").empty();
		$("h5").eq(1).append(span);
	}
	if("graphic_month"==graphic_id){
		$("h5").eq(2).find("span").empty();
		$("h5").eq(2).append(span);
	}
	if(graphic_id.indexOf("graphic_machine_summany") >= 0){
		var toolBar_id = graphic_id+'toolbar';
		$('#'+toolBar_id).find("span").empty();
		$('#'+toolBar_id).append(span);
	}
	
	if("graphic_query1"==graphic_id){
		$("#queryTraffic_span").find("span").empty();
		$("#queryTraffic_span").append(span);
	}
	
    var charData = {
        chart: {
            renderTo: graphic_id,
            type: 'spline',
            marginRight: 20,
            zoomType:'xy'
        },
        colors:['#55BF3B','#4573a7'],
        legend: {
                //layout:'vertical'
            },
        title: {
        	useHTML:true,
            text: graphic_data.title//峰值："+this.yAxis.max+"
        },
        subtitle: {
        	useHTML:true,
            text: graphic_data.serviceType,
            style: {
            	fontSize:4
            }
        },
        xAxis: {
        	gridLineWidth: 1,
        	lineColor: '#000',
            tickColor: '#000',
	        type: 'datetime',
	        dateTimeLabelFormats: { // don't display the dummy year
	                day:'%m月%d',
	            month: '%e 月 %b',
	            year: '%b'
		    },
	        title: {
                text: "时间"
            },
            labels: {
                style: {
                   color: '#000',
                   font: '11px Trebuchet MS, Verdana, sans-serif'
                }
             },
        },
        yAxis: {
        	minorTickInterval: 'auto',
            lineColor: '#000',
            lineWidth: 1,
            tickWidth: 1,
            tickColor: '#000',
            labels: {
                style: {
                   color: '#000',
                   font: '11px Trebuchet MS, Verdana, sans-serif'
                }
             },
            title: {
                text: "流量",
                style: {
                    color: '#333',
                    fontWeight: 'bold',
                    fontSize: '12px',
                    fontFamily: 'Trebuchet MS, Verdana, sans-serif'
                 }
            }
        },
        plotOptions: {
            line: {
                lineWidth: 1,
                states: {
                    hover: {
                        lineWidth: 2
                    }
                },
                marker: {
                    enabled: false,
                    states: {
                        hover: {
                            enabled: true,
                            symbol: 'circle',
                            radius: 1,
                            lineWidth: 1
                        }
                    }
                }
            },
            area:{
                    fillOpacity:0.85,
                    lineWidth:0,
                    states: {
                    hover: {
                        lineWidth: 2
                    }
                },
                marker: {
                    enabled: false,
                    states: {
                        hover: {
                            enabled: true,
                            symbol: 'circle',
                            radius: 1,
                            lineWidth: 1
                        }
                    }
                }
            }
        },
        tooltip: {
    	 formatter: function() {
                 var s = '<span style="color:#034BA0">'+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'</span>';
                 $.each(this.points, function(i, point) {
                	   var valy = point.y;
                	   var valuestr = toMorG(valy);
                	   s += '<br/><span style=\"'+"color:"+point.series.color+"\">"+ point.series.name +'</span>:<b>'
                           + valuestr +'</b>';                	   
                   });
                   return s;
               },
            //pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> <br/>',
            xDateFormat: '%Y-%m-%d %H:%M:%S',
            shared: true,
            crosshairs: true
        },
        series: [
            //{name:'回源流量',type:'area',data:datas[0]},
            //{name:'吐出流量',type:'line',data:datas[1]}         
            {name:'吐出流量',type:'line',data:datas[0]}, 
            {name:'回源流量',type:'area',data:datas[1]},
            ]
    };
    new Highcharts.Chart(charData);
};

//添加了基准线的 2013-12-05 add
ControlHelper.newTrafficTrendencyGraphic1 = function(graphic_id, graphic_data) {
	var datas_old = graphic_data['datas'];//graphic_data.datas
	if (!graphic_data.title) graphic_data.title = '流量趋势图';//
	var time_field = 'statTime';
	if (graphic_data.time_field) time_field = graphic_data.time_field;
	var rx_field = 'rx';
	var tx_field = 'tx';
	
	
	var eRx_field = 'estimateRx';
	var eTx_field = 'estimateTx';
	
	
	if (graphic_data.rx_field) rx_field = graphic_data.rx_field;
	if (graphic_data.tx_field) tx_field = graphic_data.tx_field;
	
	
	if (graphic_data.eRx_field) eRx_field = graphic_data.eRx_field;
	if (graphic_data.eTx_field) eTx_field = graphic_data.eTx_field;
	
	
	var datas = [[],[],[],[]];
	var sumtx=0.0;
	var sumrx=0.0;
	var txi = 0;
	var rxi = 0;
	
	for (var i = 0; i < datas_old.length; ++i) {
		var t = MyUtil.parseDateTime(datas_old[i][time_field]).getTime() + 8*3600000;
		//sum
		sumtx += datas_old[i][tx_field];
		sumrx += datas_old[i][rx_field];
		//max
		if(txi<datas_old[i][tx_field]){
			txi = datas_old[i][tx_field];
		   }
		if(rxi<datas_old[i][rx_field]){
			rxi = datas_old[i][rx_field];
		   }
		//push into array
		datas[0].push([t,datas_old[i][tx_field]]);
		datas[1].push([t,datas_old[i][rx_field]]);
		
		datas[2].push([t,datas_old[i][eRx_field]]);
		datas[3].push([t,datas_old[i][eTx_field]]);
		
	}
	if(datas_old.length>0){
		avgtx = sumtx/datas_old.length;
		avgrx = sumrx/datas_old.length;		
	}
	/*var ret ="(吐出流量[平均值："+avgtxstr+", 峰值："+maxtxstr+"]"
			+" 回源流量[平均值："+avgrxstr+", 峰值："+maxrxstr+"])";*/
	var span="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	  	+"<span  style=\"color:#4572A7\">(吐出流量[平均值："+toMorG(avgtx)+", 峰值："+toMorG(txi)+"] </span>"
	  	+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	  	+"<span style=\"color:#55BF3B\">回源流量[平均值："+toMorG(avgrx)+", 峰值："+toMorG(rxi)+"])</span>";
	
	if("graphic_day"==graphic_id){
		$("h5").eq(0).find("span").empty();
		$("h5").eq(0).append(span);
	}
	if("graphic_week"==graphic_id){
		$("h5").eq(1).find("span").empty();
		$("h5").eq(1).append(span);
	}
	if("graphic_month"==graphic_id){
		$("h5").eq(2).find("span").empty();
		$("h5").eq(2).append(span);
	}
	if("graphic_query1"==graphic_id){
		$("#queryTraffic_span").find("span").empty();
		$("#queryTraffic_span").append(span);
	}
	if(graphic_id.indexOf("graphic_machine_summany") >= 0){
		var serviceDesc = '服务类型:' +'&nbsp;&nbsp;'+ graphic_data.serviceType;
		var toolBar_id = graphic_id+'toolbar';
		$('#'+toolBar_id).find("span").empty();
		$('#'+toolBar_id).append(serviceDesc+span);
	}
    var charData = {
        chart: {
            renderTo: graphic_id,
            type: 'spline',
            marginRight: 20,
            zoomType:'xy'
        },
        colors:['#4572A7','#55BF3B','#FF0000','#FF0099'],
        legend: {
                //layout:'vertical'
            },
        title: {
        	useHTML:true,
            text: graphic_data.title//峰值："+this.yAxis.max+"
        },
    /*    subtitle: {
        	useHTML:true,
            text: ret,
            style: {
            	fontSize:4
            }
        },*/
        xAxis: {
        	gridLineWidth: 1,
	        type: 'datetime',
	        dateTimeLabelFormats: { // don't display the dummy year
	                day:'%m月%d',
	            month: '%e 月 %b',
	            year: '%b'
		    },
	        title: {
                text: "时间"
            }
        },
        yAxis: {
        	gridLineWidth: 1,
            title: {
                text: "流量"
            }
        },
        exporting: {
            buttons: {
                contextButton: {
                    menuItems: [{
                        text: '导出为PNG(高清)',
                        onclick: function() {
                            this.exportChart();
                        },
                        separator: false
                    }]
                }
            }
        },
        plotOptions: {
            line: {
                lineWidth: 1,
                states: {
                    hover: {
                        lineWidth: 2
                    }
                },
                marker: {
                    enabled: false,
                    states: {
                        hover: {
                            enabled: true,
                            symbol: 'circle',
                            radius: 1,
                            lineWidth: 1
                        }
                    }
                }
            },
            area:{
                    fillOpacity:0.85,
                    lineWidth:0,
                    states: {
                    hover: {
                        lineWidth: 2
                    }
                },
                marker: {
                    enabled: false,
                    states: {
                        hover: {
                            enabled: true,
                            symbol: 'circle',
                            radius: 1,
                            lineWidth: 1
                        }
                    }
                }
            }
        },
        tooltip: {
    	 formatter: function() {
                 var s = '<span style="color:#034BA0">'+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'</span>';
                 $.each(this.points, function(i, point) {
                	   var valy = point.y;
                	   var valuestr = toMorG(valy);
                	   s += '<br/><span style=\"'+"color:"+point.series.color+"\">"+ point.series.name +'</span>：<b>'
                           + valuestr +'</b>';                	   
                   });
                   return s;
               },
            //pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> <br/>',
            xDateFormat: '%Y-%m-%d %H:%M:%S',
            shared: true,
            crosshairs: true
        },
        series: [
            //{name:'回源流量',type:'area',data:datas[0]},
            //{name:'吐出流量',type:'line',data:datas[1]}         
            {name:'吐出流量',type:'line',data:datas[0]}, 
            {name:'回源流量',type:'area',data:datas[1]},
            {name:'预估回源流量',type:'line',data:datas[2]},
            {name:'预估吐出流量',type:'line',data:datas[3]}
            ]
    };
    new Highcharts.Chart(charData);
};
//单位判断
ControlHelper.unitControl=function(val){
	if(val>=1024.00){
		return ((val/1024).toFixed(2)).toString()+"G";
	}else{
		return val.toFixed(2).toString()+"M";
	}
};
//---------------------------------

function RequestHelper(){}

function mask() {
	$('#loadingPanel').css('display','block');
	$('#loadingPanel').mask('努力加载中...');
}
function unmask() {
	$('#loadingPanel').css('display','none');
	$('#loadingPanel').unmask();	
}
RequestHelper.submitForm = function(form_id, url, funCallback) {
	//mask();
	$.ajax({
        cache: true,
        type: "POST",
        url:url,
        dataType:'json',
        data:$('#' + form_id).serialize(),
        async: true,
        error: function(request) {
        	//unmask();
            if (request.status == 200) {
            	try {eval("var json="+request.responseText);
            	funCallback(json);}
            	catch(err){
            		//alert('error: ' + (request.status?request.status:0));
            	}
            	
            } else {
            	//alert('error: ' + (request.status?request.status:0));
            }
        },
        success: function(data) {
        	//unmask();
            funCallback(data);
        }
    });
};
RequestHelper.serializeForm = function(form_id, options) {
	/*var data = $('#'+form_id).serialize();
	var attrs = data.split('&');
	var ret = {};
	if (options) ret = options;
	for (var i = 0; i < attrs.length; ++i) {
		var attr_pair = attrs[i].split('=');
		ret[attr_pair[0]] = decodeURIComponent(attr_pair[1]);
	}*/
	var ret = {};
	if (options) ret = options;
	$('#' + form_id + " [name]").each(function(){
		var name = $(this).attr('name');
		var val = $(this).val();
		ret[name] = val;
	});
	return ret;
};
RequestHelper.get = function(url, data, funCallback) {
	//mask();
	$.ajax({
        cache: true,
        type: "GET",
        url:url,
        dataType:'json',
        data:data,
        async: true,
        error: function(request) {
        	//unmask();
            if (request.status == 200) {
            	try {eval("var json="+request.responseText);
            	funCallback(json);}
            	catch(err){
            		//alert('error: ' + (request.status?request.status:0));
            	}
            } else {
            	//alert('error: ' + (request.status?request.status:0));
            }
        },
        success: function(data) {
        	//unmask();
            funCallback(data);
        }
    });
};
RequestHelper.post = function(url, data, funCallback) {
	//mask();
	$.ajax({
        cache: true,
        type: "POST",
        url:url,
        dataType:'json',
        data:data,
        async: true,
        error: function(request) {
        	//unmask();
            if (request.status == 200) {
            	try {eval("var json="+request.responseText);
            	funCallback(json);}
            	catch(err){
            		//alert('error: ' + (request.status?request.status:0));
            	}
            } else {
            	//alert('error: ' + (request.status?request.status:0));
            }
        },
        success: function(data) {
        	//unmask();
            funCallback(data);
        }
    });
};
RequestHelper.get_content_page = function(url, data, funCallback) {
	mask();
	$.ajax({
        cache: true,
        type: "GET",
        url:url,
        data:data,
        async: true,
        error: function(request) {
        	unmask();
            alert("连接错误");
        },
        success: function(data) {
        	unmask();
            funCallback(data);
        }
    });
};

RequestHelper.newDataGrid = function(grid_id, url, data, columns, options) {
	var pageSize = 50;
	var initSort = [];
	if (options) {
		if (options.pageSize) pageSize = options.pageSize;
		if (options.sort) initSort = options.sort;
	}
	
	if (!data) data = {};	
	var my_extra = {url:url,columns:columns,pageSize:pageSize,initSort:initSort};
	var grid_ = $('#'+grid_id).kendoGrid({
		autoBind:false,
		height:400,
		selectable: "multiple",
		navigatable: true,
    	dataSource: {
            type: "json",
            serverPaging: true,
            serverSorting: true,
            serverFiltering: false,
            sort: initSort,
            pageSize: pageSize,
            transport: { 
                read: { 
                    url: url,
                    type: "POST",
                    dataType: "json",
                 	data:data,
                    contentType: "application/json; charset=utf-8"
                },
                parameterMap: function(options) {
                    return JSON.stringify(options);
                }
            },
            schema: { data: "data", total: "total" }
        },
        dataBound: function(e) {
		    //unmask();
		},
        pageable: {
            input: true,
            numeric: true
        },
        sortable: {
            mode: "single",
            allowUnsort: false
        },
        filterable: false,
        columns: columns
    }).data('kendoGrid');
    grid_.my_extra = my_extra;
    grid_.dataSource.fetch();
    return grid_;
};

RequestHelper.updateDataGrid =function(grid_, data) {
	var my_extra = grid_.my_extra;
	var ds = grid_.dataSource;
	//mask();
	var new_ds =  {
            type: "json",
            serverPaging: true,
            serverSorting: true,
            serverFiltering: false,
            sort:my_extra.initSort,
            pageSize: my_extra.pageSize,
            transport: { 
                read: { 
                    url: my_extra.url,
                    type: "POST",
                    dataType: "json",
                 	data:data,
                    contentType: "application/json; charset=utf-8"
                },
                parameterMap: function(options) {
                    return JSON.stringify(options);
                }
            },
            schema: { data: "data", total: "total" }
       };
    grid_.setDataSource(new_ds);
    grid_.dataSource.fetch();
};

function test(options) {
	alert('test');
}

ControlHelper.newMachineTrafficGraphic = function(graphic_id, graphic_data) {
	var datas_old = graphic_data['datas'];//graphic_data.datas
	var pointArr = datas_old.trafficPoint;
	var time_field = 'statTime';
	var rx_field = 'rxRate';
	var tx_field = 'txRate';
	if (!graphic_data.title) graphic_data.title = '';//
	if (graphic_data.time_field) time_field = graphic_data.time_field;
	if (graphic_data.rx_field) rx_field = graphic_data.rx_field;
	if (graphic_data.tx_field) tx_field = graphic_data.tx_field;
	var sumtx=0.0;
	var sumrx=0.0;
	var txi = 0;
	var rxi = 0;

	    var datas = [[],[]];
		for(var i = 0 ;i < pointArr.length; i++){
		  if(pointArr[i] != null){
			var statTime = pointArr[i].statTime;
			var t = MyUtil.parseDateTime(statTime).getTime() + 8*3600000;
			datas[0].push([t,pointArr[i][rx_field]]);
			datas[1].push([t,pointArr[i][tx_field]]);
			//sum
			sumtx += pointArr[i][tx_field];
			sumrx += pointArr[i][rx_field];
			//max
			if(txi<pointArr[i][tx_field]){
				txi = pointArr[i][tx_field];
			   }
			if(rxi<pointArr[i][rx_field]){
				rxi = pointArr[i][rx_field];
			   }
		  }
		  
		  if(pointArr.length > 0){
			  //TODO
			  avgtx = sumtx/pointArr.length;
			  avgrx = sumrx/pointArr.length;
		  }
		}
		
		var span="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
		  	+"<span  style=\"color:#4572A7\">(吐出流量[平均值："+toMorG(avgtx)+", 峰值："+toMorG(txi)+"] </span>"
		  	+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
		  	+"<span style=\"color:#55BF3B\">回源流量[平均值："+toMorG(avgrx)+", 峰值："+toMorG(rxi)+"])</span>";
		
		/*$("h5").eq(0).find("span").empty();   //TODO
		$("h5").eq(0).append(span);*/
		var toolBar_id = graphic_id+'toolbar';
		$('#'+toolBar_id).find("span").empty();
		$('#'+toolBar_id).append(span);
	
	var charData = {
	        chart: {
	            renderTo: graphic_id,
	            type: 'spline',
	            marginRight: 20,
	            zoomType:'xy'
	        },
	        colors:['#4572A7','#55BF3B'],
	        legend: {
	                //layout:'vertical'
	            },
	        title: {
	        	useHTML:true,
	            text: graphic_data.title//峰值："+this.yAxis.max+"
	        },
	    /*    subtitle: {
	        	useHTML:true,
	            text: ret,
	            style: {
	            	fontSize:4
	            }
	        },*/
	        xAxis: {
	        	gridLineWidth: 1,
		        type: 'datetime',
		        dateTimeLabelFormats: { // don't display the dummy year
		                day:'%m月%d',
		            month: '%e 月 %b',
		            year: '%b'
			    },
		        title: {
	                text: "时间"
	            }
	        },
	        yAxis: {
	        	gridLineWidth: 1,
	            title: {
	                text: "流量"
	            }
	        },
	        plotOptions: {
	            line: {
	                lineWidth: 1,
	                states: {
	                    hover: {
	                        lineWidth: 2
	                    }
	                },
	                marker: {
	                    enabled: false,
	                    states: {
	                        hover: {
	                            enabled: true,
	                            symbol: 'circle',
	                            radius: 1,
	                            lineWidth: 1
	                        }
	                    }
	                }
	            },
	            area:{
	                    fillOpacity:0.85,
	                    lineWidth:0,
	                    states: {
	                    hover: {
	                        lineWidth: 2
	                    }
	                },
	                marker: {
	                    enabled: false,
	                    states: {
	                        hover: {
	                            enabled: true,
	                            symbol: 'circle',
	                            radius: 1,
	                            lineWidth: 1
	                        }
	                    }
	                }
	            }
	        },
	        tooltip: {
	    	 formatter: function() {
	                 var s = '<span style="color:#034BA0">'+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'</span>';
	                 $.each(this.points, function(i, point) {
	                	   var valy = point.y;
	                	   var valuestr = toMorG(valy);
	                	   s += '<br/><span style=\"'+"color:"+point.series.color+"\">"+ point.series.name +'</span>：<b>'
	                           + valuestr +'</b>';                	   
	                   });
	                   return s;
	               },
	            //pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> <br/>',
	            xDateFormat: '%Y-%m-%d %H:%M:%S',
	            shared: true,
	            crosshairs: true
	        },
	        series: [
	            //{name:'回源流量',type:'area',data:datas[0]},
	            //{name:'吐出流量',type:'line',data:datas[1]}         
	            {name:'吐出流量',type:'line',data:datas[1]}, 
	            {name:'回源流量',type:'area',data:datas[0]},
	            ]
	    };
	    new Highcharts.Chart(charData);
	};
	
ControlHelper.newTrafficGraphic = function(graphic_id, graphic_data) {
	var charData = {
	        chart: {
	            renderTo: graphic_id,
	            type: 'spline',
	            marginRight: 20,
	            zoomType:'xy'
	        },
	        colors:['#4572A7','#55BF3B'],
	        legend: {
	                //layout:'vertical'
	            },
	        title: {
	        	useHTML:true,
	            text: graphic_data.title//峰值："+this.yAxis.max+"
	        },
	        xAxis: {
	        	gridLineWidth: 1,
		        type: 'datetime',
		        dateTimeLabelFormats: { // don't display the dummy year
		                day:'%m月%d',
		            month: '%e 月 %b',
		            year: '%b'
			    },
		        title: {
	                text: "时间"
	            }
	        },
	        yAxis: {
	        	gridLineWidth: 1,
	            title: {
	                text: "流量"
	            }
	        },
	        plotOptions: {
	            line: {
	                lineWidth: 1,
	                states: {
	                    hover: {
	                        lineWidth: 2
	                    }
	                },
	                marker: {
	                    enabled: false,
	                    states: {
	                        hover: {
	                            enabled: true,
	                            symbol: 'circle',
	                            radius: 1,
	                            lineWidth: 1
	                        }
	                    }
	                }
	            },
	            area:{
	                    fillOpacity:0.85,
	                    lineWidth:0,
	                    states: {
	                    hover: {
	                        lineWidth: 2
	                    }
	                },
	                marker: {
	                    enabled: false,
	                    states: {
	                        hover: {
	                            enabled: true,
	                            symbol: 'circle',
	                            radius: 1,
	                            lineWidth: 1
	                        }
	                    }
	                }
	            }
	        },
	        tooltip: {
	    	 formatter: function() {
	                 var s = '<span style="color:#034BA0">'+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'</span>';
	                 $.each(this.points, function(i, point) {
	                	   var valy = point.y;
	                	   var valuestr = toMorG(valy);
	                	   s += '<br/><span style=\"'+"color:"+point.series.color+"\">"+ point.series.name +'</span>：<b>'
	                           + valuestr +'</b>';                	   
	                   });
	                   return s;
	               },
	            //pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> <br/>',
	            xDateFormat: '%Y-%m-%d %H:%M:%S',
	            shared: true,
	            crosshairs: true
	        },
	        series: [   
	            {name:'吐出流量',type:'line',data:datas[1]}, 
	            {name:'回源流量',type:'area',data:datas[0]},
	            ]
	    };
	    new Highcharts.Chart(charData);
};
function debug_test() {
	alert('test');
}


// others
var pageVars ={"macFlag":false};
//机器认证的操作
function authSubmitValHandler(filter) {
	var auth = $(filter);
	if (auth.is(":checked")) {
		auth.val(true);
	} else {
		auth.val(false);
	}
}
//
	//mac唯一性检查
function validateMac() {
	$("#mac_info").css("color", "red");
	var mac = $.trim($("#macAddr").val());
	if (mac == "") {
		$("#mac_info").text("mac地址不能为空！");
		return;
	}
	$.ajax({
		type : "post",
		async : false,
		url : "machine_manage_validateMac.php",
		data : "machine.mac=" + mac,
		success : function(data) {// 返回值为服务器的ok属性
			var obj = $.parseJSON(data);
			$("#mac_info").text("正在检测...！");
			if (obj.success) {
				$("#mac_info").css("color", "green");
				$("#mac_info").text("Mac地址可用！");
				pageVars.macFlag = true;
			} else {
				$("#mac_info").text("Mac地址已被占用！");
			}
		}
	});
}

//消息提示框-右下角
function showSuccMessage(cont){
  art.dialog.notice({
	    title: '消息提示',
	    width: 220,// 必须指定一个像素宽度值或者百分比，否则浏览器窗口改变可能导致artDialog收缩
	    content: cont,
	    icon: 'succeed',
	    time: 4
	});
}
//
function showFailedMessage(){
  art.dialog({
	    content:'操作失败',
	});
}
function showFailedMessage(msg){
  art.dialog({
	    content:msg,
	});
}
var webRootPath = getBasePath('cachems');
loadScript(webRootPath + "/js/HighchartsHelper.js");

function tipTime(startTime,endTime){
	if(startTime != null && endTime != null){
		var minus = dateDiff(startTime,endTime);
	  	var timeDiff = minus / (31 * 24 * 3600);
	  	if(timeDiff > 1){
	  		artDialog.alert("请设定时间范围在一个月内！");
	  		return false;
	  	}else
	  		return true;
	}
}
//比较两个时间，返回时间差（单位秒）
function dateDiff(date1, date2){ 
    var type1 = typeof date1, type2 = typeof date2; 
    if(type1 == 'string') 
    date1 = stringToTime(date1); 
    else if(date1.getTime) 
    date1 = date1.getTime(); 
    if(type2 == 'string') 
    date2 = stringToTime(date2); 
    else if(date2.getTime) 
    date2 = date2.getTime(); 
    if(date1 - date2 < 0)
    	return (date2 - date1) / 1000;
    else
    	return (date1 - date2) / 1000;//结果是秒 
}

//字符串转成Time(dateDiff)所需方法 
function stringToTime(string){ 
    var f = string.split(' ', 2); 
    var d = (f[0] ? f[0] : '').split('-', 3); 
    var t = (f[1] ? f[1] : '').split(':', 3); 
    return (new Date( 
    parseInt(d[0], 10) || null, 
    (parseInt(d[1], 10) || 1)-1, 
    parseInt(d[2], 10) || null, 
    parseInt(t[0], 10) || null, 
    parseInt(t[1], 10) || null, 
    parseInt(t[2], 10) || null 
    )).getTime(); 

} 

//控制页面两次查询的间隔时间必须大于5秒
function queryTimeLimit(i,perTime,nextTime,str){
	if(i == 0){
		  perTime = (new Date()).getTime();
		  nextTime = (new Date()).getTime();
		  $(str).val(nextTime);
		  return true;
	  }else{
		  
		  perTime = $(str).val();//前一次的时间
		  nextTime = (new Date()).getTime();//现在的时间
		  
		  if((nextTime-perTime)/1000 < 5){
			  artDialog.alert("查询过于频繁！");
			  return false;
		  }
		  return true;
	  }
}
