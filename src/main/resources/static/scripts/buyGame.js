window.onload = function () {
    function buy(id) {
        let csrfInputValue = $("#csrf").val();
        let csrf = {
            _csrf: csrfInputValue
        };
        let url = `/games/${id}/buy`;
        $.ajax({
            method: 'post',
            url: url,
            data: csrf,
            error: function (error) {
                console.log(error);
            }
        });
    }

    function buyOnClick(event) {
        let element = $(event.target);
        let id = element.attr('data-id');
        buy(id);
    }

    $(".btn-buy").click(buyOnClick);
};