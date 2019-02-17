function testAjax() { // FIXME Deleteme
    $.get("server", {requestName: "getDate"}, function(data, status) {
        console.log("!!! AJAX RESPONSE: " + data);
        $("#output").text(data);
    });
}
