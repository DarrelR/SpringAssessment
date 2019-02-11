package com.spring.assessment;

import com.spring.assessment.controller.ProductRestController;
import com.spring.assessment.dto.ProductDto;
import com.spring.assessment.exception.ProductNotFoundException;
import com.spring.assessment.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Darrel Rayen on 2/11/19.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProductRestController.class)
public class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void calculatePriceForBoxesOnly() throws Exception {
        given(productService.calculatePrice(anyInt(),anyInt(),anyString())).willReturn(20.0);
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/products/1?quantity=10&orderType=BOX"))
                .andExpect(status().isOk());
                //.andExpect(jsonPath())

    }

    @Test
    public void getProductNotFound(){
        given(productService.findOne(1)).willThrow(new ProductNotFoundException());
    }
}
