function getCart() {
    function addClearCartParagraph(cartItemsDiv) {
        let clearCartParagraph = $("<p>Clear</p>");
        clearCartParagraph.addClass("dropdown-item");
        clearCartParagraph.click(clearCart);
        clearCartParagraph.appendTo(cartItemsDiv);
        let horizontalLine = $("<hr>");
        horizontalLine.appendTo(cartItemsDiv);
    }

    function renderGameTitles(response) {
        let cartItemsDiv = ("#cart-items");
        $(cartItemsDiv).empty();
        if (Object.keys(response).length > 0) {
            addClearCartParagraph(cartItemsDiv);
        } else {
            let emptyCartParagraph = $("<p>Empty Cart</p>");
            emptyCartParagraph.addClass("dropdown-item");
            emptyCartParagraph.appendTo(cartItemsDiv);
        }

        for (let title of response) {
            let cartItem = $(`<p>${title}</p>`);
            cartItem.addClass("dropdown-item");
            cartItem.appendTo(cartItemsDiv);
        }
    }

    let url = "/cart/all";
    $.ajax({
        url: url,
        dataType: "json",
        success: renderGameTitles,
        error: function () {
            console.log("Error while retrieving cart data");
        }
    })
}

function clearCart() {
    let url = "/cart/clear";
    $.ajax({
        url: url,
        error: function () {
            console.log("Error clearing cart");
        }
    });
}
