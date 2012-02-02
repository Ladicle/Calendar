$(document).ready(function(){
	$("#wrap").height($(document).height()-50);
	$("#main-c").height($("#calendar").height()-64).width($("#calendar").width()-10);
	//$("#main-c .today span").after('<img src="/pic/star.png" id="today">');
	$("#main-c .today span").after('★');
	
	$("#planform form").jqTransform();
});