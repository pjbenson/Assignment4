$(document).ready(function() {
    $(function() {
        $("#employee").autocomplete({
            source: function(request, response) {
                $.ajax({
                    url: "/autocomplete/getEmployees",
                    type: "POST",
                    data: { term: request.term },

                    dataType: "json",

                    success: function(data) {
                    	response($.map(data, function(v,i){
                    	    return {
                    	                label: v.empName,
                    	                value: v.empName
                    	               };
                    	}));
                    }
               });              
            }   
        });
    });
});