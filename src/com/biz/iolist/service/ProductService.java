package com.biz.iolist.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.ProductDao;
import com.biz.iolist.model.ProductVO;

public class ProductService {
/*
 * 상품정보의 등록, 수정, 삭제 method
 */
	SqlSession sqlSession = null;
	Scanner scan = null;
	ProductDao proDao = null;
	
	public ProductService() {

		sqlSession = DBConnection.getsqlSessionFactory().openSession(true);
		scan = new Scanner(System.in);
		proDao = (ProductDao) sqlSession.getMapper(ProductDao.class);

	}
	
	
	public void viewProduct() {
		System.out.println("-------------------------------------------");
		System.out.println("상품정보");
		System.out.println("-------------------------------------------");
		System.out.println("상품코드\t상품이름\t매입단가\t매출단가\n");
		
		List<ProductVO> proList = proDao.selectAll();
		
		for(ProductVO vo : proList) {
			
			System.out.print(vo.getP_code());
			System.out.print(vo.getP_name());
			System.out.print(vo.getP_iprice());
			System.out.print(vo.getP_oprice());
		}
		
	}
	public boolean intsertPRO() {

		System.out.println("상품코드 입력>>");
		String strPcode = scan.nextLine();
		System.out.println("상품이름 입력>>");
		String strPname = scan.nextLine();
		System.out.println("매입금액 입력>>");
		String strIprice = scan.nextLine();
		int intIprice = Integer.valueOf(strIprice);
		System.out.println("매출금액 입력>>");
		String strOprice = scan.nextLine();
		int intOprice = Integer.valueOf(strOprice);
		
		ProductVO vo = new ProductVO(strPcode,strPname,intIprice,intOprice);
//		vo.setP_code(strPcode);
//		vo.setP_name(strPname);
//		vo.setP_iprice(intIprice);
//		vo.setP_oprice(intOprice);
//		
		if(proDao.insert(vo) > 0) return true;
		else return false;
		
		
	}
	
	public boolean updatePRO() {

		ProductVO vo = new ProductVO();
	
		System.out.println("상품코드 입력");
		String strPcode = scan.nextLine();
		if(strPcode.isEmpty()) strPcode = vo.getP_code();  
		System.out.println("상품이름 입력");
		String strPname = scan.nextLine();
		if(strPname.isEmpty()) strPname = vo.getP_name();
		System.out.println("매입금액 입력");
		String strIprice = scan.nextLine();
		int intIprice = 0;
		if(strIprice.isEmpty()) intIprice = vo.getP_iprice();
		System.out.println("매출금액 입력");
		String strOprice = scan.nextLine();
		int intOprice = 0;
		if(strOprice.isEmpty()) intOprice = vo.getP_oprice();
		
		
		vo.setP_code(strPcode);
		vo.setP_name(strPname);
		vo.setP_iprice(intIprice);
		vo.setP_oprice(intOprice);
		
		if(proDao.update(vo) > 0) return true;
		else return false;

	}
	
	public boolean deletePRO() {
		System.out.println("삭제할 거래 내역 >>");
		String strPcode = scan.nextLine();
		
		ProductVO vo = proDao.findByCode(strPcode);
		System.out.println(vo);
		System.out.println("정말 삭제할까요? (YES)");
		String confirm = scan.nextLine();
		if(confirm.equalsIgnoreCase("YES")) {
			if(proDao.delete(strPcode) > 0 ) return true;
			else return false;
		}
		return false;
		
		
		
		
	}
	
}
