$(function() {
	/*Hide and show*/
	$(".switch").click(function() {
		$("#nav").slideToggle("fast");
		$('.switch').css("display","none");
		$('.switch2').css("display","block");
		});
	$(".switch2").click(function() {
		$("#nav").slideToggle("normal");
		$('.switch2').css("display","none");
		$('.switch').css("display","block");
		});
	$(".b2").click(function() {
		$("#tag").slideToggle("fast");
		$('.b2').css("display","none");
		$('.b1').css("display","block");
		});
	$(".b1").click(function() {
		$("#tag").slideToggle("fast");
		$('.b1').css("display","none");
		$('.b2').css("display","block");
		});
    $(".b3").click(function() {
		$("#sub-menu").slideToggle("fast");
		$('.b3').css("display","none");
		$('.b4').css("display","block");
		});
	$(".b4").click(function() {
		$("#sub-menu").slideToggle("fast");
		$('.b4').css("display","none");
		$('.b3').css("display","block");
		});
    $(".b5").click(function() {
		$("#search").slideToggle("fast");
		$('.b5').css("display","none");
		$('.b6').css("display","block");
		});
	$(".b6").click(function() {
		$("#search").slideToggle("fast");
		$('.b6').css("display","none");
		$('.b5').css("display","block");
		});
	
	/*Lighbox*/
    $('.lightbox').lightbox();
});