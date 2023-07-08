<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Currency Converter</title>
    <!-- I have not learned AJAX yet. For this, I used following tutorial https://www.digitalocean.com/community/tutorials/submitting-ajax-forms-with-jquery -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#conversionForm").submit(function (event) {
                const formData = {
                    toConvert: $("#toConvert").val(),
                    code: $("#code").val(),
                };
                $.ajax({
                    type: "POST",
                    url: "api/convert",
                    data: formData,
                    dataType: "text",
                    encode: true,
                    beforeSend: function () {
                        $("#dispConv")
                            .css("display", "none");
                        $("#dispError")
                            .css("display", "none");
                        $("#spinner")
                            .css("display", "inline-block");
                    },
                    error: function () {
                        $("#spinner")
                            .css("display", "none");
                        $("#dispError")
                            .css("display", "block")
                            .text("There was an error fetching data.");
                    },
                    timeout: 10000
                }).done(function (data) {
                    $("#pln")
                        .text(formData.toConvert.replace('.', ',') + " PLN");
                    $("#conv")
                        .text((data + " " + formData.code).toUpperCase());
                    $("#spinner")
                        .css("display", "none");
                    $("#dispConv")
                        .css("display", "block");
                });
                event.preventDefault();
            });
        });
    </script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
            integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link
            rel="stylesheet"
            href="//cdn.jsdelivr.net/gh/lipis/flag-icons@6.6.6/css/flag-icons.min.css"
    />
</head>
<body class="container mt-3">
    <h1 class="display-1">
        <i class="bi bi-currency-exchange"></i>
        Currency Converter
    </h1>
    <h4 class="fw-light text-muted">Made by JulWas797</h4>

    <form action="api/convert" method="POST" id="conversionForm">
        <div class="mb-3">
            <label for="toConvert" class="form-label">How much money to convert?</label>
            <div class="input-group">
                <span class="input-group-text">PLN<span class="fi fi-pl"></span></span>
                <input class="form-control" id="toConvert" name="toConvert" type="number" step=".01" value="0.00"
                       min="0.00">
            </div>
        </div>
        <div class="mb-3 col-12">
            <label for="code" class="form-label">To which currency?</label>
            <select class="form-select" name="code" id="code">
                <option value="usd" selected>USD</option>
                <option value="eur">EUR</option>
                <option value="jpy">JPY</option>
                <option value="chf">CHF</option>
                <option value="ils">ILS</option>
                <option value="sek">SEK</option>
                <option value="gbp">GBP</option>
                <option value="aud">AUD</option>
            </select>
        </div>
        <button id="btn" type="submit" class="btn btn-primary">Convert
            <span id="spinner" class="spinner-border spinner-border-sm" role="status" style="display: none"
                  aria-hidden="true"></span>
        </button>
    </form>

    <br>

    <div id="dispConv" style="display: none" class="alert alert-primary" role="alert">
        <b class="fw-bold" id="pln">0 PLN</b> is equal to <b class="fw-bold" id="conv"></b>
    </div>
    <div id="dispError" style="display: none" class="alert alert-danger" role="alert">
        Err
    </div>
</body>
</html>