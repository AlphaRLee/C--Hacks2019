function testAjax() { // FIXME Deleteme
    alert("Starting testAjax()");
    $.get("hello", {a: 6}, function(data, status) {
        console.log("!!! AJAX RESPONSE: " + data);
        $("#output").text(data);
    });
}
