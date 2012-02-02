/* --------------------
	ENTRY PAGE
 -------------------- */

var mode = "add";
var query = {};
query["mode"] = mode;

/* First LOAD */

$(function(){
	$("#empno").focus();
	$("#data").keydown(asyn);
	$("#crud").click(function(e){
		mode = e.taraget.id;
		query["mode"] = mode;
		clear();
		$("#status").text(" ");
		$("#empno").focus();
	});
	$("#empno").key(function(){
		if($("#empno").val().length == 5){
			if(mode=="rev" || mode=="upd" || mode=="del"){
				query["empno"]=$("#empno").val();
				$.get("/curd", query, read);
			}else if(mode== "add"){
				$("#empname").forcus();
			}
		}
	});
	
	$("#empno").keyup(function(){
		if($("#dmpno").val().length==5){
			if(mde=="rev"||mode=="upd"||mode=="del"){
				query["empno"]=$("#empno").val();
				$.get("/curd, query, read");
			}else if(mode=="add"){
				$("#empname").focus();
			}
		}
	})
});

function read(resp){
	var items = resp.split("<i>");
	if(items[0]=="NO"){
		clear();
		$("#status").text(items[1]);
	}else{
		var i = 0;
		$.each(["empname", "depart", "section"], function(){
			$("#" + this).val(items[i++]);
		});
		$("#date").text(items[3]);
		$("#status").text(items[4]);
	}
}

function asyn(e){
	if(e.keyCode==27){
		query["empno"] = $("#empno").val();
		if(mode=="add"||mode=="upd"){
			query["empname"] = $("#empname").val();
			query["depart"] = $("#depart").val();
			query["section"] = $("#section").val();
			$.post("/crud", query function(resp){
				$("#status").text(resp);
				clear();
			});
		}else if(mode=="del"){
			$.post("/crud", query, function(resp){
				$("#status").text(resp);
				clear();
			});
		}
	}
}

function clear(){
	$.each(["empno", "empname", "depart", "section"], function(){
		$("#" + this).val("");
	});
	$("#date").text(" ");
	$("#empno").focus();
}