package com.spring.cloud.warehouseservice.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.cloud.warehouseservice.domain.entity.Box;
import com.spring.cloud.warehouseservice.domain.entity.Location;
import com.spring.cloud.warehouseservice.domain.entity.Product;
import com.spring.cloud.warehouseservice.domain.model.ProductType;
import com.spring.cloud.warehouseservice.service.BoxService;
import com.spring.cloud.warehouseservice.service.ProductService;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WithMockUser(username = "admin", roles = {"WAREHOUSE_ADMIN"})
class ProductControllerTest
{

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Product> json;

    @MockBean
    private ProductService productService;

    @Test
    void createProductTest() throws Exception
    {
        Product product = getProduct();
        given(productService.save(any())).willReturn(product);
        mvc.perform(
                post(new URI("/warehouse/product"))
                    .content(json.write(product).getJson())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void getProductsTest() throws Exception
    {
        Product product = getProduct();
        given(productService.getAll()).willReturn(Collections.singletonList(product));
        mvc.perform(
                get(new URI("/warehouse/product"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id", is(product.getId().intValue())))
            .andExpect(jsonPath("$[0].name", is(product.getName())))
            .andExpect(jsonPath("$[0].type", is(product.getType().name())));

        verify(productService, times(1)).getAll();

    }

    private Product getProduct()
    {
        Product product = new Product();

        product.setId(1L);
        product.setName("abc");
        product.setType(ProductType.PACKING_MATERIALS);

        return product;
    }
}