const express = require("express");
const router = require("router/router.js");
const app = express();

var application = new router(app);
app.listen(1957,function(){
    console.log("Server is start");
})
