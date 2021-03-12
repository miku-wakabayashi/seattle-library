$(function(){
	 $('#add-btn').on('click', function(e) {
		 var bookList =[]
		 for (var i=1; i<6; i++) {
			 var BookRegistInfo = {
					  bookId: $('#bookId'+i).val(),
					  title: $('#title'+i).val(),
					  author: $('#author'+i).val(),
					  publisher: $('#publisher'+i).val(),
					  description: $('#description'+i).val(),
					  isbn: $('#isbn'+i).val(),
					  publishDate: $('#publishDate'+i).val(),
					  thumbnail: $('#thumbnail'+i).val()
					}
			 bookList.push(BookRegistInfo);
			  }
		 $('#bookList').val(bookList);
	 });
});
