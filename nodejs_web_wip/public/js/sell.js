document.getElementById("submit").addEventListener("click", async (e) => {
    e.preventDefault()
    const name = document.getElementById("name").value
    const price = document.getElementById("price").value
    const image = document.getElementById("image").value
    const desc = document.getElementById("desc").value
    const bed = document.getElementById("bed").value
    const {data} = await axios.get("/api/dblength")
    
    axios.post('/api/products', {item: {id: data.list, 
                                        bed: bed, 
                                        name: name, 
                                        image: image, 
                                        price: price, 
                                        desc: desc}})
    window.location = "/"
})