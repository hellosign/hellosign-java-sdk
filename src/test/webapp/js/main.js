
$(document).ready(function(){
	
	$('input[defaultvalue], textarea[defaultvalue]')
		.live('focus', function(){
			var el = $(this);
			var val = $.trim(el.val());
			if (val == el.attr('defaultvalue')) {
				el.val('');
			}
			
		})
		.live('blur', function(){
			var el = $(this);
			var val = $.trim(el.val());
			if (!val) {
				el.val(el.attr('defaultvalue'));
			}
		})
		.each(function(i, v){
			var el = $(v);
			el.val(el.attr('defaultvalue'));
		});
	
});