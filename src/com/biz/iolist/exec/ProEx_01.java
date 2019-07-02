package com.biz.iolist.exec;

import com.biz.iolist.service.ProductService;

public class ProEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ProductService ps = new ProductService();
		
		
		ps.viewProduct();
		ps.updatePRO();
		ps.deletePRO();
		
	}

}
