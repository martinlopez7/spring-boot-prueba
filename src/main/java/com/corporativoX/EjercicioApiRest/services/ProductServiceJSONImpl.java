package com.corporativoX.EjercicioApiRest.services;

import com.corporativoX.EjercicioApiRest.entities.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Primary
@Service("jsonResourceService")
@Lazy
public class ProductServiceJSONImpl implements ProductService {

    /*
    public ProductServiceJSONImpl() {
        System.out.println("Iniciando jsonResourceService");
    }
    */

    @Override
    public List<Product> getProducts() {
        List<Product> products;

        try{
            products = new ObjectMapper()
                    .readValue(this.getClass().getResourceAsStream("/products.json"),
                            new TypeReference<List<Product>>(){});

            return products;
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
