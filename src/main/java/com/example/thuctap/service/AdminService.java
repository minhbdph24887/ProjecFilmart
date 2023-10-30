package com.example.thuctap.service;

import com.example.thuctap.bean.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    // SERVICE ACCOUNT
    Page<Account> getAllAccounts(Pageable pageable);

    List<Account> getAllAccounts();

    Account detailAccount(String username);

    Account addAccount(Account account);

    Account updateAccount(Account account);

    void deleteAccount(String username);

    // SERVICE QUYEN
    Page<Role> getAllRolePage(Pageable pageable);

    List<Role> getAllRoles();

    Role detailRole(String idRole);

    Role addRole(Role role);

    Role updateRole(Role role);

    void deleteRole(String idRole);

    // SERVICE PHAN QUYEN
    Page<Authority> getAllAuthorities(Pageable pageable);

    Authority detailAuthorities(Long idAuthority);

    Authority addAuthorities(Authority authority);

    Authority updateAuthorities(Authority authority);

    // SERVICE SAN PHAM
    Page<Product> getAllProduct(Pageable pageable);

    List<Product> cbbProduct();

    Product detailProduct(Long idProduct);

    Product addProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long idProduct);

    // SERVICE MAU SAC
    Page<Color> getAllColor(Pageable pageable);

    List<Color> cbbColor();

    Color detailColor(Long idColor);

    Color addColor(Color color);

    Color updateColor(Color color);

    void deleteColor(Long idColor);

    // SERVICE SIZE
    Page<Size> getAllSize(Pageable pageable);

    List<Size> cbbSize();

    Size detailSize(Long idSize);

    Size addSize(Size size);

    Size updateSize(Size size);

    void deleteSize(Long idSize);

    // SERVICE CATEGORY
    Page<Category> getAllCategory(Pageable pageable);

    List<Category> viewCategory();

    Category detailCategory(Long idCategory);

    Category addCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(Long idCategory);

    // SERVICE PRODUCER
    Page<Producer> getAllProducers(Pageable pageable);

    List<Producer> viewProducer();

    Producer detailProducer(Long idProducer);

    Producer addProducer(Producer producer);

    Producer updateProducer(Producer producer);

    void deleteProducer(Long idProducer);

    // SERVICE CHI TIET SAN PHAM
    Page<ProductDetail> getAllProductDetails(Pageable pageable);

    List<ProductDetail> cbbProductDetails();

    ProductDetail detailProductDetail(Long idProductDetail);

    ProductDetail addProductDetail(ProductDetail productDetail);

    ProductDetail updateProductDetail(ProductDetail productDetail, MultipartFile file) throws IOException;

    void deleteProductDetail(Long idProductDetail);

    // SERVICE VOURCHER
    Page<Promotion> getAllPromotion(Pageable pageable);

    Promotion detailPromotion(Long idPromotion);

    Promotion addPromotion(Promotion promotion);

    Promotion updatePromotion(Promotion promotion);

    // SERVICE VOURCHER DETAIL
    Page<PromotionProduct> getAllPromotionProducts(Pageable pageable, Long idPromotion);

    PromotionProduct addPromotionProduct(PromotionProduct promotionProduct);

    PromotionProduct deletePromotionProduct(Long idPromotionProduct);

    // SERVICE ORDER
    Page<Order> getAllOrder(Pageable pageable);

    List<OrderDetail> getOneOrderByID(Long idOrder);

    // SERVICE WELCOME ADMIN
    Page<Account> getAllAdmin(Pageable pageable);

    Page<Account> getAllClient(Pageable pageable);
}
