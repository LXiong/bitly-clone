$(document).ready(function(){
	var WEBSERVICE="https://on-device-research-test-josh1313.c9users.io/bitly";

	$.get( WEBSERVICE, function( data ) {
		for (var i = 0; i < data.length; i++) {
			var row = $("<tr />")
			row.append($("<td>" + data[i].shortenUrl + "</td>"));
			row.append($("<td>" + data[i].originalUrl + "</td>"));
			$("#shortenUrlList").append(row);
		}
	});
	
	$('#btnShorten').click(function() {
		$.ajax({
			url:WEBSERVICE,
			type:"POST",
			data:$("#originalUrl").val(),
			contentType:"text/plain",
			dataType:"json",
			success: function(data){
				var newLink = $("<a />", {
					href : data.shortenUrl,
					text : data.shortenUrl,
					target: "_new"
				});

				
				$( "#shortenUrl" ).html( newLink );
			}
		});
	});	
	
});