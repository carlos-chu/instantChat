
$(document).ready(function() {
	$('#indexPage').click(function() {
		alert("ssss");
		$('#indexPage').removeClass("active");
		$('#indexPage').addClass("active");
	});
	$('#questionsPage').click(function() {
		$('#indexPage').removeClass("active");
		$('#questionsPage').addClass("active");
	});
	$('#usersPage').click(function() {
		$('#indexPage').removeClass("active");
		$('#usersPage').addClass("active");
	});
	$('#discussesPage').click(function() {
		$('#indexPage').removeClass("active");
		$('#discussesPage').addClass("active");
	});
});








