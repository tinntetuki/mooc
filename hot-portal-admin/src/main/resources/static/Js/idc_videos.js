$(function(){
	   	$('.figure_list ul li').hover(function(){
	   		$('.mark',this).show();
	   	},function(){
	   		$('.mark',this).hide();
	   	});
	   	$('.figure_playsource ul li img').hover(function(){
	   		$('.mark',this).show();
	   	},function(){
	   		$('.mark',this).hide();
	   	});
	   });
   $(function(){
	   	$('.figure_l_crosslist ul li').hover(function(){
	   		$('.mark',this).show();
	   	},function(){
	   		$('.mark',this).hide();
	   	});
	   	$('.figure_fulcrosslist ul li').hover(function(){
	   		$('.mark',this).show();
	   	},function(){
	   		$('.mark',this).hide();
	   	});
	   	$('.figure_biglist ul li').hover(function(){
	   		$('.mark',this).show();
	   	},function(){
	   		$('.mark',this).hide();
	   	});
		$('.figure_right_list ul li').hover(function(){
	   		$('.mark',this).show();
	   	},function(){
	   		$('.mark',this).hide();
	   	});
		$('.figure_crosslist ul li').hover(function(){
	   		$('.mark',this).show();
	   	},function(){
	   		$('.mark',this).hide();
	   	});
		$('.figure_bcrosslist ul li').hover(function(){
	   		$('.mark',this).show();
	   	},function(){
	   		$('.mark',this).hide();
	   	});
		$('.figure_fullbiglist ul li').hover(function(){
	   		$('.mark',this).show();
	   	},function(){
	   		$('.mark',this).hide();
	   	});
		$('.figure_full_lbiglist ul li').hover(function(){
	   		$('.mark',this).show();
	   	},function(){
	   		$('.mark',this).hide();
	   	});
		$('.rank_w_list ul li.first').hover(function(){
	   		$('.mark',this).show();
	   	},function(){
	   		$('.mark',this).hide();
	   	});
	   });
   
   $(function() {
		$(window).scroll(function(){
			var scrolltop=$(this).scrollTop();
			if(scrolltop>=120){
				$("#elevator").css("position", "fixed");
				$("#elevator").css("z-index",1000);
				$("#elevator").fadeIn(300);
			}
			else{			
				$("#elevator").fadeOut(300);
			}
		});
		$("#elevator").click(function(){
			$("html,body").animate({scrollTop: 0}, 500);
			});
		});
   $(function() {
		$("#unfold_more").click(function(){
			$("#part_intro").css("display", "none");
			$("#unfold_more").css("display", "none");
			$("#fold_more").css("display", "inline");
			$("#full_intro").css("display", "inline");
			});
		$("#fold_more").click(function(){
			$("#part_intro").css("display", "inline");
			$("#unfold_more").css("display", "inline");
			$("#fold_more").css("display", "none");
			$("#full_intro").css("display", "none");
			});
		});
   
   $(function(){
	    var scrollPic_02 = new ScrollPic();
		scrollPic_02.scrollContId   = "scrollbox"; //��������ID
		scrollPic_02.arrLeftId      = "arrLeft";//���ͷID
		scrollPic_02.arrRightId     = "arrRight"; //�Ҽ�ͷID
		scrollPic_02.frameWidth     = 980;//��ʾ����
		scrollPic_02.pageWidth      = 330; //��ҳ���
		scrollPic_02.speed          = 8; //�ƶ��ٶ�(��λ���룬ԽСԽ��)
		scrollPic_02.space          = 8; //ÿ���ƶ�����(��λpx��Խ��Խ��)
		scrollPic_02.autoPlay       = false; //�Զ�����
		scrollPic_02.autoPlayTime   = 3; //�Զ����ż��ʱ��(��)
		scrollPic_02.initialize(); //��ʼ��
	    });
	    
   $(function() {
	   $('.am-slider-manual').flexslider({
	   // options
	   });
	 });
   
    function home_tab(a,b,c) 
    { 
    for(i=1;i<=b;i++){ 
    if(c==i)
    { 
    // �ж�ѡ��ģ��
    document.getElementById(a+"_mo_"+i).style.display = "block";  // ��ʾģ������
    document.getElementById(a+"_to_"+i).className = "no";   // �ı�˵�Ϊѡ����ʽ
    } 
    else{ 
    // û��ѡ���ģ��
    document.getElementById(a+"_mo_"+i).style.display = "none"; // ����û��ѡ���ģ��
    document.getElementById(a+"_to_"+i).className = "q";  // ���û��ѡ��Ĳ˵���ʽ
    } 
    } 
    }
    
    function panel_tab(a,b,c) 
    { 
    for(i=1;i<=b;i++){ 
    if(c==i)
    { 
    // �ж�ѡ��ģ��
    document.getElementById(a+"_bd_"+i).style.display = "block";  // ��ʾģ������
    document.getElementById(a+"_to_"+i).className = "no";   // �ı�˵�Ϊѡ����ʽ
    } 
    else{ 
    // û��ѡ���ģ��
    document.getElementById(a+"_bd_"+i).style.display = "none"; // ����û��ѡ���ģ��
    document.getElementById(a+"_to_"+i).className = "q";  // ���û��ѡ��Ĳ˵���ʽ
    } 
    } 
    }
    
    function chart_tab(a,b,c) 
    { 
    for(i=1;i<=b;i++){ 
    if(c==i)
    { 
    // 判断选择模块
    document.getElementById(a+"_bd_"+i).style.display = "block";  // 显示模块内容
    document.getElementById(a+"_to_"+i).className = "active";   // 改变菜单为选中样式
    } 
    else{ 
    // 没有选择的模块
    document.getElementById(a+"_bd_"+i).style.display = "none"; // 隐藏没有选择的模块
    document.getElementById(a+"_to_"+i).className = "q";  // 清空没有选择的菜单样式
    } 
    } 
    }