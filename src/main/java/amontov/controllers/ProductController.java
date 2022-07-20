package amontov.controllers;

import amontov.models.Product;
import amontov.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    @Autowired
    public void setProductDao(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    @ResponseBody
    public HashMap<Integer, String> allProduct () {
       return productService.showAll();
    }
    @PostMapping("/add")
    @ResponseBody
    public String addProduct (@ModelAttribute @Valid Product product,
                              BindingResult result) {
        productService.addProduct(product);

    }
    @GetMapping("/search/{id}")
    @ResponseBody
    public String searchProduct(int id) {
        productService.getOneProduct(id);
    }
}
