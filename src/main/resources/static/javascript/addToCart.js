const app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function ($scope, $http) {
    /*
        QUẢN LÝ GIỎ HÀNG
    */
    $scope.cart = {
        listProductDetail: [],
        // Thêm sản phẩm vào giỏ hàng
        add(idProductDetail) {
            var index = this.listProductDetail.find(index => index.idProductDetail == idProductDetail);
            var urlAdd = "http://localhost:8080/product/" + idProductDetail;
            if (index) {
                index.quantity++;
                this.saveToLoaclStorage();
            } else {
                $http.get(urlAdd).then(resp => {
                    resp.data.quantity = 1;
                    this.listProductDetail.push(resp.data);
                    this.saveToLoaclStorage();
                })
            }
        },

        // Lưu giỏ hàng vào local storage
        saveToLoaclStorage() {
            var json = JSON.stringify(angular.copy(this.listProductDetail));
            localStorage.setItem("cart", json);
        },

        // Tính tổng số lượng mặt hàng trong giỏ
        get count() {
            return this.listProductDetail
                .map(listProductDetail => listProductDetail.quantity)
                .reduce((total, quantity) => total += quantity, 0);
        },

        // Tổng số tiền các mặt hàng trong giỏ
        get totalCost() {
            return this.listProductDetail
                .map(listProductDetail => listProductDetail.quantity * listProductDetail.costPrice)
                .reduce((total, quantity) => total += quantity, 0);
        },

        // Đọc giỏ hàng từ Local Storage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.listProductDetail = json ? JSON.parse(json) : [];
        },

        // Xoá từng sản phẩm trong giỏ hàng
        remove(idProductDetail) {
            var index = this.listProductDetail.findIndex(listProductDetail => listProductDetail.idProductDetail == idProductDetail);
            this.listProductDetail.splice(index, 1);
            this.saveToLoaclStorage();
        },

        // Xoá toàn bộ sản phẩm trong giỏ hàng
        clear() {
            this.listProductDetail = [];
            this.saveToLoaclStorage();
        }
    }
    $scope.cart.loadFromLocalStorage();

    $scope.order = {
        createDate: new Date(),
        totalAmount: $scope.cart.totalCost,
        address: "",
        note: "",
        status: 1,
        account: {username: $("#username").text()},
        get orderDetails(){
            return $scope.cart.listProductDetail.map(item => {
                return{
                    productDetail: {idProductDetail: item.idProductDetail},
                    price: item.costPrice,
                    quantity: item.quantity
                }
            });
        },
        purchase() {
            var order = angular.copy(this);
            // Thực Hiện Đặt Hàng
            $http.post("/rest/order", order).then(resp => {
                alert('Đặt Hàng Thành Công');
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.idOrder;
            }).catch(error => {
                alert('Đặt Hàng Thất Bại');
                console.log(error)
            })

        }
    }
});