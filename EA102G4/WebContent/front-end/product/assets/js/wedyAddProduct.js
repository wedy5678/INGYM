$(document).ready(

		function check() {
			$('input[name=poPayment]').change(
					function() {

						if ($("#blankCheckbox1").prop('checked')
								|| $("#blankCheckbox2").prop('checked')) {
							$("#blankCheckbox4").prop('checked', true);
						} else if (!$("#blankCheckbox1").prop('checked')
								&& !$("#blankCheckbox2").prop('checked')) {
							$("#blankCheckbox4").prop('checked', false);
						}

						if ($("#blankCheckbox3").prop('checked')) {
							console.log("checked");
							$("#blankCheckbox5").prop('checked', true);
						} else {
							$("#blankCheckbox5").prop('checked', false);
						}
					});
			$('input[name=poDelivery]').change(function() {

				if ($("#blankCheckbox4").prop('checked')) {
					$("#blankCheckbox1").prop('checked', true);
					$("#blankCheckbox2").prop('checked', true);
				} else {
					$("#blankCheckbox1").prop('checked', false);
					$("#blankCheckbox2").prop('checked', false);
				}

				if ($("#blankCheckbox5").prop('checked')) {
					$("#blankCheckbox3").prop('checked', true);
				} else {
					$("#blankCheckbox3").prop('checked', false);
				}
			});

		})
$(document).ready(
		function c() {
			var sub = document.getElementById("productSubmit");
			sub.addEventListener('click', function(e) {
				document.getElementById("form1").submit();
				console.log("succes");
			});

			// Add the following code if you want the name of the file appear on
			// select
			$(".custom-file-input").on(
					"change",
					function() {
						var fileName = $(this).val().split("\\").pop();
						$(this).siblings(".custom-file-label").addClass(
								"selected").html(fileName);
					});
		})
		
$(document).ready(afterErrorPay);
$(document).ready(afterErrorDel);

$(document).ready(magicalButton);
function magicalButton(){
  $("#magicalButton").on('click',function(){
  $("#usr").val("黑色二手啞鈴(兩個一組)");
  $("#st").val("20");
  $("#sel1 option")[6].selected='true';
  $("#pr").val("1299");
  $("#blankCheckbox1").prop('checked', true);
  $("#blankCheckbox3").prop('checked', true);
  $("#blankCheckbox4").prop('checked', true);
  $("#blankCheckbox5").prop('checked', true);
  $("#cate").val("買了兩三年放在家裡沒有用，便宜賣~，當初買兩支2000元，請好心人收留^^");
 })
}

function afterErrorPay(){
	var afterErrorPay = document.getElementById('afterErrorPay').value;
//	console.log(afterErrorPay);
	if(afterErrorPay.indexOf('10') != -1){
		$("#blankCheckbox1").prop('checked', true);
	}
	if(afterErrorPay.indexOf('20') != -1){
		$("#blankCheckbox2").prop('checked', true);
	}
	if(afterErrorPay.indexOf('30') != -1){
		$("#blankCheckbox3").prop('checked', true);
	}
}
function afterErrorDel(){
	var afterErrorDel = document.getElementById('afterErrorDel').value;
//	console.log(afterErrorDel);
	if(afterErrorDel.indexOf('10') != -1){
		$("#blankCheckbox4").prop('checked', true);
	}
	if(afterErrorDel.indexOf('20') != -1){
		$("#blankCheckbox5").prop('checked', true);
	}
}
