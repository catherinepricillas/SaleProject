	function validateDelete(x,y){
        if (confirm("Do you want to delete this product?")){
        	location.href='delete_db.php?id_active='+x+'&product_id='+y;
        }
	}