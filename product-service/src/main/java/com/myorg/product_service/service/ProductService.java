package com.myorg.product_service.service;

import com.myorg.product_service.dto.ProductRequest;
import com.myorg.product_service.dto.ProductResponse;
import com.myorg.product_service.model.Product;
import com.myorg.product_service.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
//    @Autowired
    @NonNull
    private ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();
        productRepository.save(product);
        log.info("product {} is saved",product.getId());
    }

    public List<ProductResponse> getAllProducts(){
        List<Product>products = productRepository.findAll();

//       method 1
        List<ProductResponse>productResponses = new ArrayList<>();

        for(Product p : products)
        {
            productResponses.add(ProductResponse.builder().id(p.getId()).name(p.getName()).description(p.getDescription())
                    .price(p.getPrice()).build());
        }
        return productResponses;


//        return products.stream().map(product -> mapToProductResponse(product)).toList();
    }

    private ProductResponse mapToProductResponse(Product product)
    {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
