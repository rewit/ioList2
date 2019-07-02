package com.biz.iolist.service;

import java.util.Scanner;

public class MainProduct {

	ProductService productService = null;
	Scanner scan = null;
	public MainProduct() {

		productService = new ProductService();
		scan = new Scanner(System.in);
	}
	
	public void start() {
		
		while(true) {
		productService.viewProduct();
		
		System.out.println("--------------------------------------------------");
		System.out.println("1.상품등록	2.상품수정	3.삭제	4.종료");
		System.out.println("--------------------------------------------------");
		System.out.println("업무선택 >>");
		String strMenu = scan.nextLine();
		int intMenu = Integer.valueOf(strMenu);
		
		if(intMenu == 1	) productService.intsertPRO();
		if(intMenu == 2	) productService.updatePRO();
		if(intMenu == 3 ) productService.deletePRO();
		if(intMenu == 4	) break;

		}
		
	}
	
}
