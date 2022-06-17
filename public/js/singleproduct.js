const fetchProducts = async () => {
    try {
        const params = new Proxy(new URLSearchParams(window.location.search), {
            get: (searchParams, prop) => searchParams.get(prop),
        });
        let value = params.id;
        const { data } = await axios.get(`/api/products/${value}`)
        
        const img = document.querySelector(".img")
        const price = document.querySelector(".price")
        const desc = document.querySelector(".description")
        const location = document.querySelector(".location")

        img.src = data.item.image
        price.textContent = data.item.price
        desc.textContent = data.item.desc
        location.textContent = data.item.name

    }
    catch(error){
        console.log(error)
    }
}

fetchProducts()

//search
document.querySelector(".search_button").onclick = function(){
    const items = document.getElementById("search").value
    if(items){
        window.location = `/?search=${items}`
    }
}

//home
const housify = document.getElementById("home")
housify.addEventListener("click", () => {
    window.location = "/"
})