package com.example.thuctap.controller;

import com.example.thuctap.bean.*;
import com.example.thuctap.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("idPromotion")
public class AdminController {
    List<Account> listAccount = new ArrayList<>();
    List<Role> listRole = new ArrayList<>();
    List<Category> listCategory = new ArrayList<>();
    List<Product> listProduct = new ArrayList<>();
    List<Color> listColor = new ArrayList<>();
    List<Size> listSize = new ArrayList<>();
    List<Producer> listProducer = new ArrayList<>();
    List<ProductDetail> listProductDetail = new ArrayList<>();

    @Autowired
    AdminService service;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String viewAdmin(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Account> itemsAdmin = service.getAllAdmin(PageRequest.of(page, 5));
        Page<Account> itemsClient = service.getAllClient(PageRequest.of(page, 5));
        model.addAttribute("listAdmin", itemsAdmin);
        model.addAttribute("listClient", itemsClient);
        model.addAttribute("currentPage", page);
        return "admin/welcomeAdmin";
    }

    // CONTROLLER QUYEN
    @RequestMapping(value = "/admin/role", method = RequestMethod.GET)
    public String viewRole(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Role> itemRole = service.getAllRolePage(PageRequest.of(page, 5));
        model.addAttribute("listRole", itemRole);
        model.addAttribute("currentPage", page);
        return "admin/account/role/viewRole";
    }

    @RequestMapping(value = "/admin/role/view-add", method = RequestMethod.GET)
    public String viewAddRole(Model model) {
        Role role = new Role();
        model.addAttribute("role", role);
        return "admin/account/role/view-add";
    }

    @RequestMapping(value = "/admin/role/add", method = RequestMethod.POST)
    public String addRole(Role role) {
        service.addRole(role);
        return "redirect:/admin/role";
    }

    @RequestMapping(value = "/admin/role/detail/{idRole}", method = RequestMethod.GET)
    public String detailRole(Model model, @PathVariable("idRole") String idRole) {
        Role detailRole = service.detailRole(idRole);
        model.addAttribute("detailRole", detailRole);
        return "admin/account/role/view-update";
    }

    @RequestMapping(value = "/admin/role/update", method = RequestMethod.POST)
    public String updateRole(Role role) {
        service.updateRole(role);
        return "redirect:/admin/role";
    }

    @RequestMapping(value = "/admin/role/delete/{idRole}", method = RequestMethod.GET)
    public String deleteRole(@PathVariable("idRole") String idRole) {
        service.deleteRole(idRole);
        return "redirect:/admin/role";
    }

    // CONTROLLER ACCOUNT
    @RequestMapping(value = "/admin/account", method = RequestMethod.GET)
    public String viewAccount(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Account> itemAccount = service.getAllAccounts(PageRequest.of(page, 5));
        model.addAttribute("listAccount", itemAccount);
        model.addAttribute("currentPage", page);
        return "admin/account/user/viewAccount";
    }

    @RequestMapping(value = "/admin/account/view-add", method = RequestMethod.GET)
    public String viewAddAccount(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "admin/account/user/view-add";
    }

    @RequestMapping(value = "/admin/account/add", method = RequestMethod.POST)
    public String addAccount(Account account) {
        service.addAccount(account);
        return "redirect:/admin/account";
    }

    @RequestMapping(value = "/admin/account/detail/{username}", method = RequestMethod.GET)
    public String detailAccount(Model model, @PathVariable("username") String username) {
        Account detailAccount = service.detailAccount(username);
        model.addAttribute("detailAccount", detailAccount);
        return "admin/account/user/view-update";
    }

    @RequestMapping(value = "/admin/account/update", method = RequestMethod.POST)
    public String updateAccount(Account account) {
        service.updateAccount(account);
        return "redirect:/admin/account";
    }

    @RequestMapping(value = "/admin/account/delete/{username}", method = RequestMethod.GET)
    public String deleteAccount(@PathVariable("username") String username) {
        service.deleteAccount(username);
        return "redirect:/admin/account";
    }

    // CONTROLLER AUTHORITY
    @RequestMapping(value = "/admin/authority", method = RequestMethod.GET)
    public String viewAuthority(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Authority> itemAuthority = service.getAllAuthorities(PageRequest.of(page, 5));
        model.addAttribute("listAuthority", itemAuthority);
        model.addAttribute("currentPage", page);
        return "admin/account/authority/viewAuthority";
    }

    @RequestMapping(value = "/admin/authority/view-add", method = RequestMethod.GET)
    public String viewAddAuthority(Model model) {
        Authority authorities = new Authority();
        listAccount = service.getAllAccounts();
        listRole = service.getAllRoles();
        model.addAttribute("authorities", authorities);
        model.addAttribute("listAccount", listAccount);
        model.addAttribute("listRole", listRole);
        return "admin/account/authority/view-add";
    }

    @RequestMapping(value = "/admin/authority/add", method = RequestMethod.POST)
    public String addAuthority(Authority authorities) {
        service.addAuthorities(authorities);
        return "redirect:/admin/authority";
    }

    @RequestMapping(value = "/admin/authority/detail/{idAuthority}", method = RequestMethod.GET)
    public String detailAuthority(Model model, @PathVariable("idAuthority") Long idAuthority) {
        Authority detailAuthorities = service.detailAuthorities(idAuthority);
        listAccount = service.getAllAccounts();
        listRole = service.getAllRoles();
        model.addAttribute("detailAuthority", detailAuthorities);
        model.addAttribute("listAccount", listAccount);
        model.addAttribute("listRole", listRole);
        return "admin/account/authority/view-update";
    }

    @RequestMapping(value = "/admin/authority/update", method = RequestMethod.POST)
    public String updateAuthority(Authority authority) {
        service.updateAuthorities(authority);
        return "redirect:/admin/authority";
    }

    // CONTROLLER SAN PHAM
    @RequestMapping(value = "/admin/product", method = RequestMethod.GET)
    public String viewProduct(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Product> itemProduct = service.getAllProduct(PageRequest.of(page, 5));
        model.addAttribute("listProduct", itemProduct);
        model.addAttribute("currentPage", page);
        return "admin/detail/product/viewProduct";
    }

    @RequestMapping(value = "/admin/product/view-add", method = RequestMethod.GET)
    public String viewAddProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/detail/product/view-add";
    }

    @RequestMapping(value = "/admin/product/add", method = RequestMethod.POST)
    public String addProduct(Product product) {
        service.addProduct(product);
        return "redirect:/admin/product";
    }

    @RequestMapping(value = "/admin/product/detail/{idProduct}", method = RequestMethod.GET)
    public String detailProduct(Model model, @PathVariable("idProduct") Long idProduct) {
        Product detailProduct = service.detailProduct(idProduct);
        model.addAttribute("detailProduct", detailProduct);
        return "admin/detail/product/view-update";
    }

    @RequestMapping(value = "/admin/product/update", method = RequestMethod.POST)
    public String updateProduct(Product product) {
        service.updateProduct(product);
        return "redirect:/admin/product";
    }

    @RequestMapping(value = "/admin/product/delete/{idProduct}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("idProduct") Long idProduct) {
        service.deleteProduct(idProduct);
        return "redirect:/admin/product";
    }

    // CONTROLLER MAU SAC
    @RequestMapping(value = "/admin/color", method = RequestMethod.GET)
    public String viewColor(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Color> itemColor = service.getAllColor(PageRequest.of(page, 5));
        model.addAttribute("listColor", itemColor);
        model.addAttribute("currentPage", page);
        return "admin/detail/color/viewColor";
    }

    @RequestMapping(value = "/admin/color/view-add", method = RequestMethod.GET)
    public String viewAddColor(Model model) {
        Color color = new Color();
        model.addAttribute("color", color);
        return "admin/detail/color/view-add";
    }

    @RequestMapping(value = "/admin/color/add", method = RequestMethod.POST)
    public String addColor(Color color) {
        service.addColor(color);
        return "redirect:/admin/color";
    }

    @RequestMapping(value = "/admin/color/detail/{idColor}", method = RequestMethod.GET)
    public String detailColor(Model model, @PathVariable("idColor") Long idColor) {
        Color detailColor = service.detailColor(idColor);
        model.addAttribute("detailColor", detailColor);
        return "admin/detail/color/view-update";
    }

    @RequestMapping(value = "/admin/color/update", method = RequestMethod.POST)
    public String updateColor(Color color) {
        service.updateColor(color);
        return "redirect:/admin/color";
    }

    @RequestMapping(value = "/admin/color/delete/{idColor}", method = RequestMethod.GET)
    public String deleteColor(@PathVariable("idColor") Long idColor) {
        service.deleteColor(idColor);
        return "redirect:/admin/color";
    }

    // CONTROLLER SIZE
    @RequestMapping(value = "/admin/size", method = RequestMethod.GET)
    public String viewSize(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Size> itemSize = service.getAllSize(PageRequest.of(page, 5));
        model.addAttribute("listSize", itemSize);
        model.addAttribute("currentPage", page);
        return "admin/detail/size/viewSize";
    }

    @RequestMapping(value = "/admin/size/view-add", method = RequestMethod.GET)
    public String viewAddSize(Model model) {
        Size size = new Size();
        model.addAttribute("size", size);
        return "admin/detail/size/view-add";
    }

    @RequestMapping(value = "/admin/size/add", method = RequestMethod.POST)
    public String addSize(Size size) {
        service.addSize(size);
        return "redirect:/admin/size";
    }

    @RequestMapping(value = "/admin/size/detail/{idSize}", method = RequestMethod.GET)
    public String detailSize(Model model, @PathVariable("idSize") Long idSize) {
        Size detailSize = service.detailSize(idSize);
        model.addAttribute("detailSize", detailSize);
        return "admin/detail/size/view-update";
    }

    @RequestMapping(value = "/admin/size/update", method = RequestMethod.POST)
    public String updateSize(Size size) {
        service.updateSize(size);
        return "redirect:/admin/size";
    }

    @RequestMapping(value = "/admin/size/delete/{idSize}", method = RequestMethod.GET)
    public String deleteSize(@PathVariable("idSize") Long idSize) {
        service.deleteSize(idSize);
        return "redirect:/admin/size";
    }

    // CONTROLLER NHÀ SẢN XUẤT
    @RequestMapping(value = "/admin/producer", method = RequestMethod.GET)
    public String viewProducer(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Producer> itemProducer = service.getAllProducers(PageRequest.of(page, 5));
        model.addAttribute("listProducer", itemProducer);
        model.addAttribute("currentPage", page);
        return "admin/detail/producer/viewProducer";
    }

    @RequestMapping(value = "/admin/producer/view-add", method = RequestMethod.GET)
    public String viewAddProducer(Model model) {
        Producer producer = new Producer();
        model.addAttribute("producer", producer);
        return "admin/detail/producer/view-add";
    }

    @RequestMapping(value = "/admin/producer/add", method = RequestMethod.POST)
    public String addProducer(Producer producer) {
        service.addProducer(producer);
        return "redirect:/admin/producer";
    }

    @RequestMapping(value = "/admin/producer/detail/{idProducer}", method = RequestMethod.GET)
    public String detailProducer(Model model, @PathVariable("idProducer") Long idProducer) {
        Producer detailProducer = service.detailProducer(idProducer);
        model.addAttribute("detailProducer", detailProducer);
        return "admin/detail/producer/view-update";
    }

    @RequestMapping(value = "/admin/producer/update", method = RequestMethod.POST)
    public String updateProducer(Producer producer) {
        service.updateProducer(producer);
        return "redirect:/admin/producer";
    }

    @RequestMapping(value = "/admin/producer/delete/{idProducer}", method = RequestMethod.GET)
    public String deleteProducer(@PathVariable("idProducer") Long idProducer) {
        service.deleteProducer(idProducer);
        return "redirect:/admin/producer";
    }

    // CONTROLLER CHI TIET SAN PHAM
    @RequestMapping(value = "/admin/productdetail", method = RequestMethod.GET)
    public String viewProducDetail(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<ProductDetail> itemProductDetail = service.getAllProductDetails(PageRequest.of(page, 5));
        model.addAttribute("listProductDetail", itemProductDetail);
        model.addAttribute("currentPage", page);
        return "admin/detail/productDetail/viewProductDetail";
    }

    @RequestMapping(value = "/admin/productdetail/view-add", method = RequestMethod.GET)
    public String viewAddProductDetail(Model model) {
        ProductDetail productDetail = new ProductDetail();
        listProduct = service.cbbProduct();
        listColor = service.cbbColor();
        listSize = service.cbbSize();
        listProducer = service.viewProducer();
        listCategory = service.viewCategory();
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("listColor", listColor);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listProducer", listProducer);
        model.addAttribute("listCategory", listCategory);
        return "admin/detail/productDetail/view-add";
    }

    @RequestMapping(value = "/admin/productDetail/add", method = RequestMethod.POST)
    public String addProductDetail(ProductDetail productDetail) {
        service.addProductDetail(productDetail);
        return "redirect:/admin/productDetail";
    }

    @RequestMapping(value = "/admin/productdetail/detail/{idProductDetail}", method = RequestMethod.GET)
    public String detailProductDetail(Model model, @PathVariable("idProductDetail") Long idProductDetail) {
        ProductDetail detailProductDetail = service.detailProductDetail(idProductDetail);
        listProduct = service.cbbProduct();
        listColor = service.cbbColor();
        listSize = service.cbbSize();
        listProducer = service.viewProducer();
        listCategory = service.viewCategory();
        model.addAttribute("detailProductDetail", detailProductDetail);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("listColor", listColor);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listProducer", listProducer);
        model.addAttribute("listCategory", listCategory);
        return "admin/detail/productDetail/view-update";
    }

    @RequestMapping(value = "/admin/productdetail/update", method = RequestMethod.POST)
    public String updateProductDetail(@RequestParam("file") MultipartFile file, ProductDetail productDetail) {
        try {
            service.updateProductDetail(productDetail, file);
            return "redirect:/admin/productdetail";
        } catch (IOException e) {
            // Xử lý ngoại lệ ở đây
            return "error";
        }
    }

    @RequestMapping(value = "/admin/productdetail/delete/{idProductDetail}", method = RequestMethod.GET)
    public String deleteProductDetail(@PathVariable("idProductDetail") Long idProductDetail) {
        service.deleteProductDetail(idProductDetail);
        return "redirect:/admin/productdetail";
    }

    // CONTROLLER DANH MUC
    @RequestMapping(value = "/admin/category", method = RequestMethod.GET)
    public String viewCategory(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Category> itemCategory = service.getAllCategory(PageRequest.of(page, 5));
        model.addAttribute("listCategory", itemCategory);
        model.addAttribute("currentPage", page);
        return "admin/detail/category/viewCategory";
    }

    @RequestMapping(value = "/admin/category/view-add", method = RequestMethod.GET)
    public String viewAddCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/detail/category/view-add";
    }

    @RequestMapping(value = "/admin/category/add", method = RequestMethod.POST)
    public String addCategory(Category category) {
        service.addCategory(category);
        return "redirect:/admin/category";
    }

    @RequestMapping(value = "/admin/category/detail/{idCategory}", method = RequestMethod.GET)
    public String detailCategory(Model model, @PathVariable("idCategory") Long idCategory) {
        Category detailCategory = service.detailCategory(idCategory);
        model.addAttribute("detailCategory", detailCategory);
        return "admin/detail/category/view-update";
    }

    @RequestMapping(value = "/admin/category/update", method = RequestMethod.POST)
    public String updateCategory(Category category) {
        service.updateCategory(category);
        return "redirect:/admin/category";
    }

    @RequestMapping(value = "/admin/category/delete/{idCategory}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("idCategory") Long idCategory) {
        service.deleteCategory(idCategory);
        return "redirect:/admin/category";
    }

    // CONTROLLER VOURCHER
    @RequestMapping(value = "/admin/voucher", method = RequestMethod.GET)
    public String getAllVoucher(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Promotion> itemsPromotion = service.getAllPromotion(PageRequest.of(page, 5));
        model.addAttribute("listPromotion", itemsPromotion);
        model.addAttribute("currentPage", page);
        return "admin/voucher/promotion/viewPromotion";
    }


    @RequestMapping(value = "/admin/voucher/view-add", method = RequestMethod.GET)
    public String viewAddVoucher(Model model) {
        Promotion promotion = new Promotion();
        listProductDetail = service.cbbProductDetails();
        model.addAttribute("promotion", promotion);
        model.addAttribute("listProductDetail", listProductDetail);
        return "admin/voucher/promotion/view-add";
    }

    @RequestMapping(value = "/admin/voucher/add", method = RequestMethod.POST)
    public String addVoucher(Promotion promotion) {
        service.addPromotion(promotion);
        return "redirect:/admin/voucher";
    }

    @RequestMapping(value = "/admin/voucher/detail/{idPromotion}", method = RequestMethod.GET)
    public String detailPromotionProduct(Model model, @PathVariable("idPromotion") Long idPromotion, @RequestParam(defaultValue = "0") int page) {
        Promotion promotion = service.detailPromotion(idPromotion);
        Page<PromotionProduct> itemsPromotionProduct = service.getAllPromotionProducts(PageRequest.of(page, 5), promotion.getIdPromotion());
        model.addAttribute("detailPromotion", promotion);
        model.addAttribute("listPromotionProduct", itemsPromotionProduct);
        model.addAttribute("currentPage", page);
        model.addAttribute("idPromotion", idPromotion);
        return "admin/voucher/promotionProduct/viewPromotionProduct";
    }

    @RequestMapping(value = "/admin/voucher/update", method = RequestMethod.POST)
    public String updatePromotionProduct(Promotion promotion) {
        service.updatePromotion(promotion);
        return "redirect:/admin/voucher";
    }

    @RequestMapping(value = "/admin/voucherdetail/view-add", method = RequestMethod.GET)
    public String addProductVoucher(Model model, @ModelAttribute("idPromotion") Long idPromotion) {
        PromotionProduct promotionProduct = new PromotionProduct();
        Promotion promotion = service.detailPromotion(idPromotion);
        listProductDetail = service.cbbProductDetails();
        model.addAttribute("detailPromotion", promotion);
        model.addAttribute("promotionProduct", promotionProduct);
        model.addAttribute("listProductDetail", listProductDetail);
        return "admin/voucher/promotionProduct/view-add";
    }

    @RequestMapping(value = "/admin/voucherdetail/add", method = RequestMethod.POST)
    public String addPromotionProduct(PromotionProduct promotionsProduct, RedirectAttributes redirectAttributes) {
        service.addPromotionProduct(promotionsProduct);
        redirectAttributes.addAttribute("idPromotion", promotionsProduct.getPromotion().getIdPromotion());
        return "redirect:/admin/voucher/detail/{idPromotion}";
    }

    @RequestMapping(value = "/admin/voucherdetail/remove/{idPromotionProduct}", method = RequestMethod.GET)
    public String deletePromotionProduct(PromotionProduct promotionProduct, @PathVariable("idPromotionProduct") Long idPromotionProduct, RedirectAttributes redirectAttributes) {
        PromotionProduct removedPromotionProduct = service.deletePromotionProduct(idPromotionProduct);
        if (removedPromotionProduct != null && removedPromotionProduct.getPromotion() != null && removedPromotionProduct.getPromotion().getIdPromotion() != null) {
            redirectAttributes.addAttribute("idPromotion", removedPromotionProduct.getPromotion().getIdPromotion());
        }
        return "redirect:/admin/voucher/detail/{idPromotion}";
    }

    // VIEW CUA HOA DON
    @RequestMapping(value = "/admin/order", method = RequestMethod.GET)
    public String viewListOrder(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Order> getAllOrder = service.getAllOrder(PageRequest.of(page, 5));
        model.addAttribute("listOrder", getAllOrder);
        model.addAttribute("currentPage", page);
        return "admin/order/orderList";
    }

    @RequestMapping(value = "/admin/order/detail/{idOrder}", method = RequestMethod.GET)
    public String detailOrderList(Model model, @PathVariable("idOrder") Long idOrder, @RequestParam(defaultValue = "0") int page) {
        List<OrderDetail> orderDetails = service.getOneOrderByID(idOrder);
        Page<Order> getAllOrder = service.getAllOrder(PageRequest.of(page, 5));
        model.addAttribute("listOrder", getAllOrder);
        model.addAttribute("currentPage", page);
        model.addAttribute("detailOrder", orderDetails);
        return "admin/order/orderList";
    }

    // VIEW CUA THONG KE
}
