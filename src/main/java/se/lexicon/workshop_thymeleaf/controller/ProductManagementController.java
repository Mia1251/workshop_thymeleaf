package se.lexicon.workshop_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.lexicon.workshop_thymeleaf.dto.ProductDetailsDto;
import se.lexicon.workshop_thymeleaf.dto.ProductDto;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductManagementController {

    private List<ProductDto> productDtoList;

    @PostConstruct
    public void init() {
        if (productDtoList == null) productDtoList = new ArrayList<>();

        ProductDto productDto = new ProductDto();
        productDto.setId(1);
        productDto.setName("Bowl");
        productDto.setPrice(25);
        ProductDetailsDto productDetailsDto = new ProductDetailsDto();
        productDetailsDto.setId(1);
        productDetailsDto.setName("Bowl");
        productDetailsDto.setDescription("Bowl Description");

        productDto.setProductDetailsDto(productDetailsDto);

        productDtoList.add(productDto);

        ProductDto productDto2 = new ProductDto();
        productDto2.setId(2);
        productDto2.setName("Plate");
        productDto2.setPrice(15);
        ProductDetailsDto productDetailsDto2 = new ProductDetailsDto();
        productDetailsDto2.setId(2);
        productDetailsDto2.setName("Plate");
        productDetailsDto2.setDescription("Plate Description");

        productDto2.setProductDetailsDto(productDetailsDto2);

        productDtoList.add(productDto2);

    }

    @GetMapping("/")
    public String getAllProducts(Model model) {
        model.addAttribute("productDtoList", productDtoList);
        return "productManagement";
    }

    @GetMapping("/find/{id}")
    public String getProductById(@PathVariable("id") Integer id, Model model) {
        System.out.println("ID " + id);
        Optional<ProductDto> optionalProductDto = productDtoList.stream().filter(productDto -> productDto.getId() == id).findFirst();
        if (optionalProductDto.isPresent())
            model.addAttribute("productDto", optionalProductDto.get());
        else
            model.addAttribute("productDto", new ProductDto());

        return "adminProductDetails";
    }


}
