package com.mock_project.mock_project.service.impl;

import com.mock_project.mock_project.dto.ProductDTO;
import com.mock_project.mock_project.dto.ProductDetailDTO;
import com.mock_project.mock_project.dto.VariantProductDTO;
import com.mock_project.mock_project.mapper.CategoryMapper;
import com.mock_project.mock_project.mapper.ProductMapper;
import com.mock_project.mock_project.mapper.VariantProductMapper;
import com.mock_project.mock_project.model.Category;
import com.mock_project.mock_project.model.Product;
import com.mock_project.mock_project.model.VariantProduct;
import com.mock_project.mock_project.repository.CategoryRepository;
import com.mock_project.mock_project.repository.ProductRepository;
import com.mock_project.mock_project.repository.VariantProductRepository;
import com.mock_project.mock_project.service.CategoryService;
import com.mock_project.mock_project.service.ProductService;
import com.mock_project.mock_project.service.VariantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMappers;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private VariantProductMapper variantProductMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VariantProductService variantProductService;
    @Autowired
    private VariantProductRepository variantProductRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Product createProduct(ProductDTO productDTO) {
        // Tạo một đối tượng Product từ ProductDTO
        Product product = new Product();
        product.setName(productDTO.getName());
        // Lấy đối tượng Category từ categoryId
        Category category = categoryService.getCategoryById(productDTO.getCategoryId());
        // Đặt danh mục cho sản phẩm
        product.setCategory(category);
        // Lưu sản phẩm vào cơ sở dữ liệu
        Product savedProduct = productRepository.save(product);
        return savedProduct;

    }

    @Override
    public Product updateProduct(Long productId, ProductDTO updatedProductDTO) {
        // Kiểm tra xem sản phẩm có tồn tại hay không
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            // Cập nhật thông tin sản phẩm từ updatedProductDTO
            existingProduct.setName(updatedProductDTO.getName());
            Category category = categoryService.getCategoryById(updatedProductDTO.getCategoryId());
            existingProduct.setCategory(category);
            // Lưu lại sản phẩm đã cập nhật vào cơ sở dữ liệu
            Product updatedProduct = productRepository.save(existingProduct);
            return updatedProduct;
        } else {
            return null;
        }
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public ProductDetailDTO getProductDetailById(Long productId) {
        // Lấy sản phẩm theo ID từ productRepository
        Product product = productRepository.findById(productId).orElse(null);

        if (product != null) {
            // Lấy thông tin danh mục sản phẩm
            Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);

            // Lấy danh sách các biến thể sản phẩm (nếu có)
            List<VariantProductDTO> variantProducts = getVariantProductsById(productId);

            // Tạo và điền thông tin vào ProductDetailDTO
            ProductDetailDTO productDetailDTO = new ProductDetailDTO();
            productDetailDTO.setProduct(productMappers.toProductDTO(product));
            productDetailDTO.setCategory(categoryMapper.toCategoryDTO(category));
            productDetailDTO.setVariantProducts(variantProducts);

            return productDetailDTO;
        }

        return null;
    }
    @Override
    public List<VariantProductDTO> getVariantProductsById(Long id) {
        // Thực hiện logic để lấy danh sách các biến thể sản phẩm từ cơ sở dữ liệu
        List<VariantProduct> variantProductList = variantProductRepository.findByProductId(id);

        // Chuyển đổi danh sách biến thể sản phẩm sang DTO
        List<VariantProductDTO> variantProductDTOList = variantProductList.stream()
                .map(variantProductMapper::toVariantProductDTO)
                .collect(Collectors.toList());

        return variantProductDTOList;
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> productPage = productRepository.findByCategoryId(categoryId, pageable);
        return productPage.toList();
    }

}
