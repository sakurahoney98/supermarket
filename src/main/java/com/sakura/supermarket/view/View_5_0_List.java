package com.sakura.supermarket.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.sakura.supermarket.controller.ProductController;
import com.sakura.supermarket.model.ProductModel;

public class View_5_0_List {
	
	
	
	public static void main(String[] args) {
		ProductController productController = new ProductController();
		List<ProductModel> list = productController.listOfProducts(2);
		List<ProductModel> list_confirm = new ArrayList<ProductModel>();
		List<ProductModel> new_list = new ArrayList<ProductModel>();
		
		for(ProductModel p : list) {
			if(p.getStock_quantity() > 0) {
			int buy = 0;
			
			if(p.getDuration() < 30) {
				
				buy =  (int) Math.ceil(30 / (double) p.getDuration()) - p.getStock_quantity();
				
				
				if(buy > 0) {
					if(!p.isConfirm()) {
						new_list.add(p);
					}else {
						list_confirm.add(p);
					}
				}
				
				
					
					
			}else {
				
				try {
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); 
					Date date = format.parse(p.getPurchase_date());
					
					long diff = new Date().getTime() - date.getTime();
					
					 TimeUnit time = TimeUnit.DAYS;
					 long days = time.convert(diff, TimeUnit.MILLISECONDS);
					 
					 if(days >= (p.getDuration() * p.getStock_quantity())) {
						 if(!p.isConfirm()) {
							 new_list.add(p);
						 }else {
							 list_confirm.add(p);
						 }
					 }
						 
					
				} catch (ParseException e) {
					
					e.printStackTrace();
				} 
				
			}
			}else {
				if(!p.isConfirm()) {
					new_list.add(p);
				}else {
					list_confirm.add(p);
				}
			}
			
		}
		
		
		View_5_1_List.setList(list_confirm);
		View_5_2_List.setListProducts(new_list);
		if(!list_confirm.isEmpty())
		new View_5_1_List().setVisible(true);
		else
			View_5_2_List.main(args);
	}

}
