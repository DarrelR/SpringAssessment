var totals = 0;
$(document).ready(function () {

    var $dataRows = $('#products-table').find('tr');
    var totalColumn = $("#total-price-text");
    var calculateButton = $("#calculate-total-btn");

    calculateButton.on('click', function (e) {
        totals = 0.0;
        e.preventDefault();
        $dataRows.each(function () {
            $(this).find('.totalColumn').each(function (i) {
                totals += parseFloat($(this).html());
            });
        });
        totalColumn.text(totals);
    });

});
var port = window.location.port;
var baseUrl = window.location.protocol + '//' + window.location.hostname + (port !== 80 ? ':' + port : '' );

function addOrder(id) {
    var orderType = $('#orderType' + id).val();
    var quantity = $('#itemQuantity' + id).val();
    var totalPriceText = $('#totalPrice' + id);
    $.ajax({
        url: baseUrl + '/rest/products/order/' + id,
        type: "GET",
        data: {quantity: quantity, orderType: orderType},
        success: function (data) {
            totalPriceText.text(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            window.location.href = baseUrl + "/products"
        }
    });
}