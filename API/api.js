const express = require("express");
const app = express();

var application = require("router/router.js")(app);
app.listen(1957,function(){
    console.log("Server is start");
})
