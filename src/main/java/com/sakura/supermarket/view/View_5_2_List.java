package com.sakura.supermarket.view;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sakura.supermarket.model.ProductModel;
import com.sakura.supermarket.model.ProductModelComparator;


public class View_5_2_List {
	
	private static List<ProductModel> list_products = new ArrayList<ProductModel>();
	private static List<ProductModel> list_products_confirm = new ArrayList<ProductModel>();
	
	public static void setListProducts(List<ProductModel> list) {
		
		list_products = list;
		
	}
	
	public static void setListProductsConfirm(List<ProductModel> list) {
		
		list_products_confirm = list;
	}
	
	
	
	
	
	 public static void main(String[] args) {
		 if(!list_products_confirm.isEmpty())
		list_products.addAll(list_products_confirm);
		List<String> categories = getCategories(list_products);
		
		
	
	        try {
	            Document document = new Document(PageSize.A4); // Use A4 ou outro tamanho desejado
	            String file = salvarArquivo();
	            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
	            writer.setCompressionLevel(0); 

	            document.open();
int columns = 0;
	            if(categories.size() > 1)
	            	columns = 4;
	            else
	            	columns = 2;

	            // Adiciona uma tabela
	            PdfPTable table = new PdfPTable(columns); 
	          

	        while(!categories.isEmpty()) {
	        	int i = 0;
	        	String category_1 = categories.get(i);
	        	List<ProductModel> list_1 = categoryPorductList(category_1);
	        	categories.remove(i);
	
	        	 PdfPCell cell = new PdfPCell();
	        	 Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
	             cell.addElement(new Phrase(category_1, font));
	        	 cell.setColspan(2);
	        	 cell.setBackgroundColor(new BaseColor(255, 190, 203));
	             table.addCell(cell);
	        	if(categories.size() > 0) {
	        		i = categories.size() == 1 ? 0 : i;
	        		String category_2 = categories.get(i);
		        	List<ProductModel> list_2 = categoryPorductList(category_2);
		        	categories.remove(i);
		        	
		        	 PdfPCell cell_2 = new PdfPCell();
		        	 cell_2.addElement(new Phrase(category_2, font));
		        	 cell_2.setColspan(2);
		        	 cell_2.setBackgroundColor(new BaseColor(255, 190, 203));
		             table.addCell(cell_2);
		        	
		        	for(int j = 0; j < list_1.size() || j < list_2.size(); j++) {
		        		if(j < list_1.size()) {
		        			ProductModel product = list_1.get(j);
		        			
		        			
		        			
		        			table.addCell(product.getName());
		        			
		        			table.addCell(String.valueOf(product.getBuy()) + "x" + product.getQuantity());
		        			
		        		}else {
		        			table.addCell("");
		        			table.addCell("");
		        		}
		        		
		        		if(j < list_2.size()) {
		        			ProductModel product = list_2.get(j);
		        			
		        			
		        			
		        			table.addCell(product.getName());
		        			
		        			table.addCell(String.valueOf(product.getBuy()) + "x" + product.getQuantity());
		        			
		        		}else {
		        			table.addCell("");
		        			table.addCell("");
		        		}
		        	}
		        	
		        	table.addCell(" ");
	    			table.addCell(" ");
	    			if(columns > 2) {
	        			table.addCell(" ");
	        			table.addCell(" ");
	        			}
	        	}else {
	        		if(columns > 2) {
	        			table.addCell("");
	        			table.addCell("");
	        			}
	        		
	        		for(int j = 0; j < list_1.size(); j++) {
	        			
	        		
		        			ProductModel product = list_1.get(j);
		        			
		        			
		        			
		        			table.addCell(product.getName());
		        			
		        			table.addCell(String.valueOf(product.getBuy()) + "x" + list_1.get(j).getQuantity());
		        			 
		        			if(columns > 2) {
			        			table.addCell("");
			        			table.addCell("");
			        			}
		        		
		        	}
	        	
	        	}
	        	
	        		
	        	
	        	
	        }
	            
	            
	           

	            
	            // Adiciona a tabela ao documento
	            document.add(table);

	            document.close();

	            openPDF(file);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	  
	    }

	    private static String salvarArquivo() {
	        JFileChooser fileChooser = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos PDF", "pdf");
	        fileChooser.setFileFilter(filter);

	        int result = fileChooser.showSaveDialog(null);

	        if (result == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fileChooser.getSelectedFile();

	            // Verifique se o arquivo possui a extensão desejada (por exemplo, .pdf)
	            String filePath = selectedFile.getAbsolutePath();
	            if (!filePath.toLowerCase().endsWith(".pdf")) {
	                filePath += ".pdf";
	            }

	            return filePath;
	        } else {
	            return null; // Usuário cancelou a operação
	        }
	    }

	    private static void openPDF(String filePath) {
	        try {
	            File file = new File(filePath);
	            Desktop.getDesktop().open(file);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	   
	    
	    private static List<String> getCategories(List<ProductModel> list) {
	    	List<String> categories = new ArrayList<String>();
	    	
	    	
	    	for(ProductModel p : list) {
	    		boolean contains = false;
	    		for(String s : categories) {
	    			if(s.equals(p.getDescription_category())) {
	    				contains = true;
	    				break;
	    			}
	    		}
	    		
	    		if (!contains)
	    			categories.add(p.getDescription_category());
	    	}
	    	
	    	return categories;
	    	
	    }
	    
	    private static List<ProductModel> categoryPorductList(String category){
	    	List<ProductModel> list = new ArrayList<ProductModel>();
	    	
	    	for(ProductModel p : list_products) {
	    		if(p.getDescription_category().equals(category))
	    			list.add(p);
	    	}
	    	
	    	Collections.sort(list, new ProductModelComparator());
	    	
	    	return list;
	    }
}
