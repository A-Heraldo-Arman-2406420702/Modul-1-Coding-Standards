package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        mockMvc.perform(post("/product/create")
                .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> allProducts = Arrays.asList(product);
        when(productService.findAll()).thenReturn(allProducts);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attribute("products", allProducts));
    }

    @Test
    void testEditProductPage() throws Exception {
        when(productService.findById(product.getProductId())).thenReturn(product);

        mockMvc.perform(get("/product/edit/" + product.getProductId()))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", product));
    }

    @Test
    void testEditProductPost() throws Exception {
        mockMvc.perform(post("/product/edit")
                .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
        verify(productService, times(1)).edit(any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(get("/product/delete/" + product.getProductId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("../list"));
        verify(productService, times(1)).delete(product.getProductId());
    }
}
