document.onreadystatechange = function () {

    function trailer(id) {
        function renderTrailer(response) {
            let trailerTitle = response['title'];
            let trailer = response['trailer'];
            let trailerURL = `https://www.youtube.com/embed/${trailer}`;
            let trailerTitleHeader = $("#title");
            trailerTitleHeader.text(trailerTitle);
            let trailerFrame = $("#trailer");
            trailerFrame.attr("src", trailerURL);
        }

        let url = `/games/${id}/trailer`;
        $.ajax({
            url: url,
            dataType: "json",
            success: renderTrailer,
            error: function () {
                console.log("Error loading of game trailer.");
            }
        });
    }

    $(".btn-trailer").click(trailerOnClick);
    $("#myModal").on("hidden.bs.modal", modalOnClose);

    function trailerOnClick(event) {
        let element = $(event.target);
        let id = element.attr('data-id');
        trailer(id);
    }

    function modalOnClose() {
        let trailer = $("#trailer");
        trailer.attr("src", "");
    }
};
