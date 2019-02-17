// $(document).ready({
//     initTestbed();
//     console.log("Done loading from jQuery");
// });

function setup() {
    initTestbed();
    console.log("Done loading");

    var par = document.createElement("P");
    var textNode = document.createTextNode("My output");         // Create a text node
    par.appendChild(textNode);

    document.body.appendChild(par);
}
