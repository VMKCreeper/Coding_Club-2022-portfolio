const express = require("express")
const app = express()
const path = require('path')

// db
let db = require("./utils/db")

// static assets
app.use(express.static("./public"))

//parse form data (built in middleware)
app.use(express.urlencoded({extended:false}))
//parse json
app.use(express.json())

// router
// api's
app.get("/api/products/query", (req, res) => {
    const {bed, search} = req.query
    let newDB = [...db]
    if(bed){
        // filters list depending on condition
        newDB = db.filter((product) => {
            return product.bed == (bed)
        })
    }
    if(search && search != "null"){
        newDB = db.filter((product) => {
            return product.name.toLowerCase().startsWith(search.toLowerCase())
        })
    }
    res.status(200).json({success: true, data: newDB})
})

app.get("/api/products/:id", (req, res) => {
    const {id} = req.params
    for(let i = 0; i < db.length; i++){
        if(db[i].id == id){
            res.json({success: true, item: db[i]})
            return
        }
    }
    res.json({item: {}})
})

app.get("/api/dblength", (req, res) => {
    res.status(200).json({list: db.length})
})

app.post("/api/products", (req, res) => {
    const {item} = req.body
    db.push(item)
    console.log(db[db.length - 1])
})

// html files
app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "/public/index.html"))
})

app.get("/singleproduct", (req, res) => {
    res.sendFile(path.join(__dirname, "/public/singleproduct.html"))
})

app.get("/sell", (req, res) => {
    res.sendFile(path.join(__dirname ,"/public/sell.html"))
})

app.listen(5000, () => {
    console.log("Server listening on port 5000")
})