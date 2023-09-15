package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dao.ProductDao;
import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductDao dao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<Product>();
		structure.setMessage("Product added succefully");
		structure.setBody(dao.saveProduct(product));
		structure.setCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<Product>();
		structure.setMessage("Update Product Succesfully");
		structure.setBody(dao.updateProduct(product));
		structure.setCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);

	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findAll(){
		ResponseStructure<List<Product>> structure=new ResponseStructure<>();
		structure.setMessage("All Users are displayed");
		structure.setBody(dao.findAll());
		structure.setCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Product>> findProduct(int id){
		ResponseStructure<Product> structure=new ResponseStructure<Product>();
		Optional<Product> recProduct=dao.findProduct(id);
		if(recProduct.isPresent()) {
			structure.setMessage("Product Found");
			structure.setBody(recProduct.get());
			structure.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.OK);
		}
		throw new IdNotFoundException();
		
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id){
		ResponseStructure<String> structure=new ResponseStructure<String>();
		Optional<Product> recProduct=dao.findProduct(id);
		if(recProduct.isPresent()) {
			dao.deleteProduct(id);
			structure.setMessage("user Found");
			structure.setBody("User deleted");
			structure.setCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	
}
