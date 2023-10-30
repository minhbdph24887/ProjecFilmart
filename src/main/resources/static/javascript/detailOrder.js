$(document).ready(function() {
    $('.action-button').click(function() {
        var idOrder = $(this).data('idOrder'); // Lấy idOrder từ nút đã nhấp

        $.get('/admin/order/detail/' + idOrder, function(data) {
            // Xử lý dữ liệu trả về từ máy chủ
            // Ví dụ: thêm các hàng mới vào bảng chi tiết hóa đơn
            for (var i = 0; i < data.length; i++) {
                var orderDetail = data[i];
                $('#detailOrderTable tbody').append(
                    '<tr>' +
                    '<td>' + orderDetail.productDetail.idProductDetail + '</td>' +
                    '<td><img src="/images/' + orderDetail.productDetail.images + '" style="width: 200px; height: 200px;" alt=""/></td>' +
                    '<td>' + orderDetail.productDetail.product.nameProduct + '</td>' +
                    '<td>' + orderDetail.price + '</td>' +
                    '<td>' + orderDetail.quantity + '</td>' +
                    '<td>' + (orderDetail.price * orderDetail.quantity) + '</td>' +
                    '</tr>'
                );
            }
        });
    });
});
