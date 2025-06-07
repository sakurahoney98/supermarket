package com.sakura.supermarket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import com.sakura.supermarket.enums.ProductEnum;
import com.sakura.supermarket.persistence.DatabaseManager;
import com.sakura.supermarket.persistence.ProductPersistence;


public class UpdateCSV {
	
	
    public static void main(String[] args) {
    	DatabaseManager db = new DatabaseManager();
    	Connection connection = db.getConexao();
    	ProductPersistence pp = new ProductPersistence();
    	pp.setConnection(connection);
    	
    		
        String csvFile = "C:/Users/User/Desktop/insert_db.csv"; // Defina o caminho do arquivo CSV
        String linha = "";
        String divisor = ";"; // Separador de colunas (pode ser adaptado se necessário)

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            // Lê cada linha do CSV
            while ((linha = br.readLine()) != null) {
            	
                // Divide a linha pelas vírgulas (ou outro separador)
                String[] colunas = linha.split(divisor);
                String id_aux = colunas[0].replace("\uFEFF", "");
                
                
                int id = Integer.parseInt(id_aux);
                int value = Integer.parseInt(colunas[2].replace("\uFEFF", ""));
                
                
           
            
                
                //pp.editProduct(id, ProductEnum.PURCHASE_DATE.getNome(), colunas[3]);
                pp.editProduct(id, ProductEnum.STOCK.getNome(), value);
                //pp.editProduct(id, ProductEnum.STOCK.getNome(), value, "07/06/2025");
                
				
				System.out.println("[" + id + "] " + colunas[1] + " atualizado!");

    			
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

