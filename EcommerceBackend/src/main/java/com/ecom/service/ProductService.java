package com.ecom.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.ProductDTO;
import com.ecom.entity.Category;
import com.ecom.entity.Product;
import com.ecom.repository.CategoryRepository;
import com.ecom.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired 
	CategoryRepository categoryRepository;
	
	public ProductDTO addProduct(ProductDTO productDTO) throws IOException {
		Product product=new Product();
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setImg(productDTO.getImg().getBytes());
		
		Category category=categoryRepository.findById(productDTO.getCategoryid()).orElseThrow();
		product.setCategory(category);
		return productRepository.save(product).getDTO();
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}

}
