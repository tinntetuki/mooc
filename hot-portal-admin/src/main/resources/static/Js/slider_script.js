$(function(){	
	

	var 
		 index = 0 ;
		Swidth0 = 911 ;
		 timer = null ;
		   len = $(".Slider_title span a").length ; 
		
		function NextPage()
		{	
			if(index>2)
			{
				index = 0 ;
			}
			$(".Slider_title span a").removeClass("Slider_title_a1").eq(index).addClass("Slider_title_a1");
			$(".Slider_main").stop(true, false).animate({left: -index*Swidth0+"px"},600)		
		}
		
		function PrevPage()
		{	
			if(index<0)
			{
				index = 2 ;
			}
			$(".Slider_title span a").removeClass("Slider_title_a1").eq(index).addClass("Slider_title_a1");
			$(".Slider_main").stop(true, false).animate({left: -index*Swidth0+"px"},600)		
		}
		
		$(".Slider_title span a").each(function(a){
            $(this).mouseover(function(){
				index = a ;
				NextPage();
			});
        });
		//下一页
		$(".Slider_next img").click(function(){
			 index++ ;
			 NextPage();
		});
		//上一页
		$(".Slider_prev img").click(function(){
			 index-- ;
			 PrevPage();
		});
		//自动滚动
		var timer = setInterval(function(){
				index++ ;
				NextPage();
			},4000);
			
		$(".Slider_next img , .Slider_main , .Slider_prev img , .Slider_title span").mouseover(function(){
			clearInterval(timer);
		});
		$(".Slider_next img , .Slider_main , .Slider_prev img , .Slider_title span").mouseleave(function(){
			timer = setInterval(function(){
				index++ ;
				NextPage();
			},4000);	
		});
			
})//

$(function(){	
	
	var 
		 index = 0 ;
		Swidth = 1214 ;
		 timer = null ;
		   len = $(".fullSlider_title span a").length ; 
		
		function NextPage()
		{	
			if(index>2)
			{
				index = 0 ;
			}
			$(".fullSlider_title span a").removeClass("fullSlider_title_a1").eq(index).addClass("fullSlider_title_a1");
			$(".fullSlider_main").stop(true, false).animate({left: -index*Swidth+"px"},600)		
		}
		
		function PrevPage()
		{	
			if(index<0)
			{
				index = 2 ;
			}
			$(".fullSlider_title span a").removeClass("fullSlider_title_a1").eq(index).addClass("fullSlider_title_a1");
			$(".fullSlider_main").stop(true, false).animate({left: -index*Swidth+"px"},600)		
		}
		
		$(".fullSlider_title span a").each(function(a){
            $(this).mouseover(function(){
				index = a ;
				NextPage();
			});
        });
		//下一页
		$(".fullSlider_next img").click(function(){
			 index++ ;
			 NextPage();
		});
		//上一页
		$(".fullSlider_prev img").click(function(){
			 index-- ;
			 PrevPage();
		});
		//自动滚动
		var timer = setInterval(function(){
				index++ ;
				NextPage();
			},4000);
			
		$(".fullSlider_next img , .fullSlider_main , .fullSlider_prev img , .fullSlider_title span").mouseover(function(){
			clearInterval(timer);
		});
		$(".fullSlider_next img , .fullSlider_main , .fullSlider_prev img , .fullSlider_title span").mouseleave(function(){
			timer = setInterval(function(){
				index++ ;
				NextPage();
			},4000);	
		});
			
})//

