// $(document).ready({
//     initTestbed();
//     console.log("Done loading from jQuery");
// });

function setup() {
    initTestbed();
    console.log("Done loading");

    var canvas = document.getElementsByTagName("canvas")[0];
    document.body.removeChild(canvas);
    document.body.insertBefore(canvas, document.body.childNodes[0]);
    canvas.style.zIndex = "0";
    canvas.height = "866";
    canvas.style.height = "720px";

    $("textarea").keyup(function(event) {
        if (event.which == 13) {
            alert("Submitting!");
            submit();
        }
    });

    $("#wLevel").focusout(function() {
        submit();
    });
}
