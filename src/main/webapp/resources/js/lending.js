$(function(){
	var lendingStatus = $('#lendingStatus').val();
	if(lendingStatus =='1'){
		// 貸し出し中の場合返却ボタン有効化
		$(".btn_returnBook").prop('disabled', false);
	}else if(lendingStatus =='0'){
		// 貸し出しOKの場合借りるボタン有効化
		$(".btn_rentBook").prop('disabled', false);
	}

});

