* Start the app and access following urls
	
		curl -d '{"code":"SUPERSALE","discount":"100","expDate":"10/10/2022"}' -H "Content-Type: application/json" -X POST http://localhost:9091/couponapi/coupons
		curl -H "Content-Type: application/json" -X GET http://localhost:9091/couponapi/coupons/SUPERSALE
		curl -d '{"name":"MAC","description":"Its Cool","price":2000,"couponCode":"SUPERSALE"}' -H "Content-Type: application/json" -X POST http://localhost:9090/productapi/products

		curl -d '{"code":"SUPERSALE","discount":"100","expDate":"10/10/2022"}' -H "Content-Type: application/json" -X POST http://couponservice-env-1.eba-4xhmqdcp.us-east-2.elasticbeanstalk.com/couponapi/coupons
		curl -H "Content-Type: application/json" -X GET http://couponservice-env-1.eba-4xhmqdcp.us-east-2.elasticbeanstalk.com/couponapi/coupons/SUPERSALE
		curl -d '{"name":"MAC","description":"Its Cool","price":2000,"couponCode":"SUPERSALE"}' -H "Content-Type: application/json" -X POST http://Productservice-env.eba-zpmp33j2.us-east-2.elasticbeanstalk.com/productapi/products
