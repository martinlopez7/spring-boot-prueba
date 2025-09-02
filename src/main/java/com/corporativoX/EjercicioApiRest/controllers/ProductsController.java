package com.corporativoX.EjercicioApiRest.controllers;

import com.corporativoX.EjercicioApiRest.entities.Product;
import com.corporativoX.EjercicioApiRest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productsService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getProducts() {
        List <Product> products = productsService.getProducts();

        return ResponseEntity.ok(products);
    }


}
