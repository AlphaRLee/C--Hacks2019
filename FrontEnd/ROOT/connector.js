function submit() {
    $.get("server", {
        requestName: "submit",
        day: $("#day").val(),
        month: $("#month").val(),
        year: $("#year").val(),
        flow: $("#flow").val(),
        wLevel: $("#wLevel").val()
    }, function(data, status) {
        var waterLevelChange = data;
        // alert("Data is: " + data);
        setGate(waterLevelChange);
        // setGate(8);
    });
}
