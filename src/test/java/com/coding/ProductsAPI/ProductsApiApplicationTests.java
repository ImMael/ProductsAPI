package com.coding.ProductsAPI;

import com.coding.ProductsAPI.Controller.CategoryController;
import com.coding.ProductsAPI.Controller.ProductsController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductsApiApplicationTests {

	@Autowired
	ProductsController productsController;

	@Autowired
	CategoryController categoryController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(productsController);
		Assertions.assertNotNull(categoryController);
	}

}
