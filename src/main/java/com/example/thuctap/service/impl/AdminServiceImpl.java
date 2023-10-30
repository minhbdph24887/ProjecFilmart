package com.example.thuctap.service.impl;

import com.example.thuctap.bean.*;
import com.example.thuctap.repository.*;
import com.example.thuctap.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {
    private static final String CHAR_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUM = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_UPPER + NUM;
    private static final Random random = new Random();

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    PromotionProductRepository promotionProductRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public Page<Account> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccount1();
    }

    @Override
    public Account detailAccount(String username) {
        return accountRepository.findById(username).orElse(null);
    }

    @Override
    public Account addAccount(Account account) {
        Account account1 = Account.builder()
                .username(account.getUsername())
                .fullName(account.getFullName())
                .email(account.getEmail())
                .numberPhone(account.getNumberPhone())
                .sex(account.getSex())
                .birthday(account.getBirthday())
                .address(account.getAddress())
                .password(account.getPassword())
                .status(1)
                .build();
        return accountRepository.save(account1);
    }

    @Override
    public Account updateAccount(Account account) {
        Account account1 = accountRepository.findById(account.getUsername()).orElse(null).builder()
                .username(account.getUsername())
                .fullName(account.getFullName())
                .email(account.getEmail())
                .numberPhone(account.getNumberPhone())
                .sex(account.getSex())
                .birthday(account.getBirthday())
                .address(account.getAddress())
                .password(account.getPassword())
                .status(account.getStatus())
                .build();
        return accountRepository.save(account1);
    }

    @Override
    public void deleteAccount(String username) {
        accountRepository.deleteAccount(username);
    }

    @Override
    public Page<Role> getAllRolePage(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.getAllRole1();
    }

    @Override
    public Role detailRole(String idRole) {
        return roleRepository.findById(idRole).orElse(null);
    }

    @Override
    public Role addRole(Role role) {
        Role role1 = Role.builder()
                .idRole(role.getIdRole())
                .nameRole(role.getNameRole())
                .status(1)
                .build();
        return roleRepository.save(role1);
    }

    @Override
    public Role updateRole(Role role) {
        Role role1 = roleRepository.findById(role.getIdRole()).orElse(null).builder()
                .idRole(role.getIdRole())
                .nameRole(role.getNameRole())
                .status(role.getStatus())
                .build();
        return roleRepository.save(role1);
    }

    @Override
    public void deleteRole(String idRole) {
        roleRepository.deleteRole(idRole);
    }

    @Override
    public Page<Authority> getAllAuthorities(Pageable pageable) {
        return authorityRepository.findAll(pageable);
    }

    @Override
    public Authority detailAuthorities(Long idAuthority) {
        return authorityRepository.findById(idAuthority).orElse(null);
    }

    @Override
    public Authority addAuthorities(Authority authority) {
        Authority authorities1 = Authority.builder()
                .account(authority.getAccount())
                .role(authority.getRole())
                .build();
        return authorityRepository.save(authorities1);
    }

    @Override
    public Authority updateAuthorities(Authority authority) {
        Authority authorities1 = authorityRepository.findById(authority.getIdAuthority()).orElse(null).builder()
                .idAuthority(authority.getIdAuthority())
                .account(authority.getAccount())
                .role(authority.getRole())
                .build();
        return authorityRepository.save(authorities1);
    }

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> cbbProduct() {
        return productRepository.cbbProduct();
    }

    @Override
    public Product detailProduct(Long idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    @Override
    public Product addProduct(Product product) {
        Product product1 = Product.builder()
                .nameProduct(product.getNameProduct())
                .status(1)
                .build();
        return productRepository.save(product1);
    }

    @Override
    public Product updateProduct(Product product) {
        Product product1 = productRepository.findById(product.getIdProduct()).orElse(null).builder()
                .idProduct(product.getIdProduct())
                .nameProduct(product.getNameProduct())
                .status(product.getStatus())
                .build();
        return productRepository.save(product1);
    }

    @Override
    public void deleteProduct(Long idProduct) {
        productRepository.deleteProduct(idProduct);
    }

    @Override
    public Page<Color> getAllColor(Pageable pageable) {
        return colorRepository.findAll(pageable);
    }

    @Override
    public List<Color> cbbColor() {
        return colorRepository.cbbColor();
    }

    @Override
    public Color detailColor(Long idColor) {
        return colorRepository.findById(idColor).orElse(null);
    }

    @Override
    public Color addColor(Color color) {
        Color color1 = Color.builder()
                .codeColor(color.getCodeColor())
                .status(1)
                .build();
        return colorRepository.save(color1);
    }

    @Override
    public Color updateColor(Color color) {
        Color color1 = colorRepository.findById(color.getIdColor()).orElse(null).builder()
                .idColor(color.getIdColor())
                .codeColor(color.getCodeColor())
                .status(color.getStatus())
                .build();
        return colorRepository.save(color1);
    }

    @Override
    public void deleteColor(Long idColor) {
        colorRepository.deleteColor(idColor);
    }

    @Override
    public Page<Size> getAllSize(Pageable pageable) {
        return sizeRepository.findAll(pageable);
    }

    @Override
    public List<Size> cbbSize() {
        return sizeRepository.cbbSize();
    }

    @Override
    public Size detailSize(Long idSize) {
        return sizeRepository.findById(idSize).orElse(null);
    }

    @Override
    public Size addSize(Size size) {
        Size size1 = Size.builder()
                .nameSize("Size" + " " + size.getNameSize())
                .status(1)
                .build();
        return sizeRepository.save(size1);
    }

    @Override
    public Size updateSize(Size size) {
        Size size1 = sizeRepository.findById(size.getIdSize()).orElse(null).builder()
                .idSize(size.getIdSize())
                .nameSize("Size" + " " + size.getNameSize())
                .status(size.getStatus())
                .build();
        return sizeRepository.save(size1);
    }

    @Override
    public void deleteSize(Long idSize) {
        sizeRepository.deleteSize(idSize);
    }

    @Override
    public Page<Category> getAllCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<Category> viewCategory() {
        return categoryRepository.viewCategoryByStatus1();
    }

    @Override
    public Category detailCategory(Long idCategory) {
        return categoryRepository.findById(idCategory).orElse(null);
    }

    @Override
    public Category addCategory(Category category) {
        Category category1 = Category.builder()
                .nameCategory(category.getNameCategory())
                .status(1)
                .build();
        return categoryRepository.save(category1);
    }

    @Override
    public Category updateCategory(Category category) {
        Category category1 = categoryRepository.findById(category.getIdCategory()).orElse(null).builder()
                .idCategory(category.getIdCategory())
                .nameCategory(category.getNameCategory())
                .status(category.getStatus())
                .build();
        return categoryRepository.save(category1);
    }

    @Override
    public void deleteCategory(Long idCategory) {
        categoryRepository.deleteCategory(idCategory);
    }

    @Override
    public Page<Producer> getAllProducers(Pageable pageable) {
        return producerRepository.findAll(pageable);
    }

    @Override
    public List<Producer> viewProducer() {
        return producerRepository.cbbProducer();
    }

    @Override
    public Producer detailProducer(Long idProducer) {
        return producerRepository.findById(idProducer).orElse(null);
    }

    @Override
    public Producer addProducer(Producer producer) {
        Producer producer1 = Producer.builder()
                .nameProducer(producer.getNameProducer())
                .status(1)
                .build();
        return producerRepository.save(producer1);
    }

    @Override
    public Producer updateProducer(Producer producer) {
        Producer producer1 = producerRepository.findById(producer.getIdProducer()).orElse(null).builder()
                .idProducer(producer.getIdProducer())
                .nameProducer(producer.getNameProducer())
                .status(producer.getStatus())
                .build();
        return producerRepository.save(producer1);
    }

    @Override
    public void deleteProducer(Long idProducer) {
        producerRepository.deleteProducer(idProducer);
    }

    @Override
    public Page<ProductDetail> getAllProductDetails(Pageable pageable) {
        return productDetailRepository.findAll(pageable);
    }

    @Override
    public List<ProductDetail> cbbProductDetails() {
        return productDetailRepository.cbbProductDetails();
    }

    @Override
    public ProductDetail detailProductDetail(Long idProductDetail) {
        return productDetailRepository.findById(idProductDetail).orElse(null);
    }

    @Override
    public ProductDetail addProductDetail(ProductDetail productDetail) {
        ProductDetail productDetail1 = ProductDetail.builder()
                .product(productDetail.getProduct())
                .color(productDetail.getColor())
                .size(productDetail.getSize())
                .producer(productDetail.getProducer())
                .category(productDetail.getCategory())
                .images(productDetail.getImages())
                .quantity(productDetail.getQuantity())
                .purchasePrice(productDetail.getPurchasePrice())
                .costPrice(productDetail.getCostPrice())
                .status(1)
                .build();
        return productDetailRepository.save(productDetail1);
    }

    @Override
    public ProductDetail updateProductDetail(ProductDetail productDetail, MultipartFile file) throws IOException {
        // Lấy đường dẫn của ảnh cũ từ cơ sở dữ liệu
        String oldImagePath = productDetailRepository.findById(productDetail.getIdProductDetail()).get().getImages();
        // Kiểm tra xem file có rỗng hay không
        if (file.isEmpty()) {
            // Nếu rỗng, sử dụng đường dẫn của ảnh cũ
            productDetail.setImages(oldImagePath);
        } else {
            // Nếu không rỗng, lấy đường dẫn của ảnh mới và lưu vào thư mục images
            String fileName = file.getOriginalFilename();
            String imagePath = "images/" + fileName;
            // Sử dụng đường dẫn của ảnh mới
            productDetail.setImages(imagePath);
        }
        // Cập nhật lại thông tin sản phẩm vào cơ sở dữ liệu
        ProductDetail updatedProductDetail1 = productDetailRepository.findById(productDetail.getIdProductDetail()).orElse(null).builder()
                .idProductDetail(productDetail.getIdProductDetail())
                .product(productDetail.getProduct())
                .color(productDetail.getColor())
                .size(productDetail.getSize())
                .producer(productDetail.getProducer())
                .category(productDetail.getCategory())
                .images(productDetail.getImages()) // Đường dẫn hình ảnh đã được cập nhật
                .quantity(productDetail.getQuantity())
                .purchasePrice(productDetail.getPurchasePrice())
                .costPrice(productDetail.getCostPrice())
                .status(productDetail.getStatus())
                .build();
        return productDetailRepository.save(updatedProductDetail1);
    }

    @Override
    public void deleteProductDetail(Long idProductDetail) {
        productDetailRepository.deleteProductDetail(idProductDetail);
    }

    @Override
    public Page<Promotion> getAllPromotion(Pageable pageable) {
        Page<Promotion> promotionPage = promotionRepository.findAll(pageable);
        for (Promotion promotion : promotionPage) {
            promotion.updateStatusPromotions();
            promotionRepository.save(promotion); // Lưu lại trạng thái đã cập nhật
        }
        return promotionPage;
    }

    @Override
    public Promotion detailPromotion(Long idPromotion) {
        return promotionRepository.findById(idPromotion).orElse(null);
    }

    // Khởi tạo chuỗi kí tự cả chữ cái in hoa và số
    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }

    @Override
    public Promotion addPromotion(Promotion promotion) {
        Promotion promotion1 = Promotion.builder()
                .codePromotion(generateRandomString())
                .namePromotion(promotion.getNamePromotion())
                .person(promotion.getPerson())
                .quantity(promotion.getQuantity())
                .startDate(promotion.getStartDate())
                .endDate(promotion.getEndDate())
                .description(promotion.getDescription())
                .status(1)
                .build();
        return promotionRepository.save(promotion1);
    }

    @Override
    public Promotion updatePromotion(Promotion promotion) {
        Promotion promotion1 = Promotion.builder()
                .idPromotion(promotion.getIdPromotion())
                .codePromotion(promotion.getCodePromotion())
                .namePromotion(promotion.getNamePromotion())
                .person(promotion.getPerson())
                .quantity(promotion.getQuantity())
                .startDate(promotion.getStartDate())
                .endDate(promotion.getEndDate())
                .description(promotion.getDescription())
                .status(promotion.getStatus())
                .build();
        return promotionRepository.save(promotion1);
    }

    @Override
    public Page<PromotionProduct> getAllPromotionProducts(Pageable pageable, Long idPromotion) {
        return promotionProductRepository.getAllPromotionProduct(pageable, idPromotion);
    }

    @Override
    public PromotionProduct addPromotionProduct(PromotionProduct promotionProduct) {
        PromotionProduct promotionProduct1 = PromotionProduct.builder()
                .promotion(promotionProduct.getPromotion())
                .productDetail(promotionProduct.getProductDetail())
                .status(1)
                .build();
        return promotionProductRepository.save(promotionProduct1);
    }

    @Override
    public PromotionProduct deletePromotionProduct(Long idPromotionProduct) {
        Optional<PromotionProduct> removePromotionProduct = promotionProductRepository.findById(idPromotionProduct);
        if (removePromotionProduct.isPresent()) {
            promotionProductRepository.deleteById(idPromotionProduct);
            return removePromotionProduct.get();
        }
        return null;
    }

    @Override
    public Page<Order> getAllOrder(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public List<OrderDetail> getOneOrderByID(Long idOrder) {
        return orderDetailRepository.getAllOrderDetailByOrderID(idOrder);
    }

    @Override
    public Page<Account> getAllAdmin(Pageable pageable) {
        return accountRepository.getAllAccountAdmin(pageable);
    }

    @Override
    public Page<Account> getAllClient(Pageable pageable) {
        return accountRepository.getAllAccountClient(pageable);
    }
}
