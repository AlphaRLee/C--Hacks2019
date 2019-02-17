function testAjax() { // FIXME Deleteme
    $.get("server", {
        requestName: "stuff",
        day: "17",
        month: "1",
        year: "2019",
        wLevel: "1075",
        flow: "60"
    }, function(data, status) {
        console.log("!!! AJAX RESPONSE: " + data);
        $("#output").text(data);
    });
}
