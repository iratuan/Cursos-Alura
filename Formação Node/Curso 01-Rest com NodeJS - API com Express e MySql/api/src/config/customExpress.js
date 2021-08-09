const express = require("express")
const consign = require("consign")
const bodyParser = require("body-parser")
const cors = require("cors")

module.exports = () => {
    const app = express()
    app.use(bodyParser.json())
    app.use(bodyParser.urlencoded({ extended:true }))
    app.use(cors({
        origin: "http://localhost:8081"
      }))

    
    consign()
        .include("src/controllers")
        .into(app)

    return app
}

