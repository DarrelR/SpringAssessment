package com.spring.assessment;

import com.spring.assessment.controller.ProductRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Darrel Rayen on 2/11/19.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProductRestController.class)
public class ProductRestControllerTest {

    @Test
    public void calculatePriceForBoxesOnly() throws Exception{

    }
}
