import * as DATABASE from "./database.js"
let userdata = DATABASE.database[DATABASE.currentAccount]

const navbar = document.querySelector(".nav-button")
const account = document.createElement("div")
const list = document.querySelector(".content-list")

if(userdata == null){
    account.classList.add("account")
    const login = document.createElement("a")
    const signup = document.createElement("a")

    login.href = "login.html"
    login.textContent = "Log in"
    signup.href = "signup.html"
    signup.textContent = "Sign up"

    account.appendChild(login)
    account.appendChild(signup)
}
else{
    account.classList.add("profile")
    const username = document.createElement("h3")
    username.classList.add("username")
    username.textContent = userdata._username
    
    username.addEventListener("click", () => {
        window.location.href = "profile.html"
    })
    
    const pfp = document.createElement("img")
    pfp.id = "pfp"
    pfp.src = userdata._pfp
    
    const logout = document.createElement("h3")
    logout.textContent = "Log out"
    logout.classList.add("logout")
    
    logout.addEventListener("click", () => {
        localStorage.setItem("cur-acc", null)
        window.alert("Logged out")
        location.reload()
    })

    account.appendChild(username)
    account.appendChild(pfp)
    account.appendChild(logout)
}
navbar.appendChild(account)

// show products
const fetchProducts = async (link) => {
    try {
        const { data } = await axios.get(link)
        const items = data.data

        if(items.length == 0){
            // needs edit
            const error = document.createElement("h1")
            error.textContent = "Item not found"
            list.appendChild(error)
            console.log("YE")
        }
        else{
            for(let i = 0; i < items.length; i++)
            {
                const item = document.createElement("div")
                item.classList.add("content")

                const img = document.createElement("img")
                img.classList.add("img")
                img.src = items[i].image
                
                const price = document.createElement("h2")
                price.id = "price"
                price.textContent = `$${items[i].price}`
                
                const description = document.createElement("div")
                description.id = "description"
                
                const brief = document.createElement("p")
                brief.id = "brief"
                brief.textContent = items[i].desc
                
                const location = document.createElement("p")
                location.id = "location"
                location.textContent = items[i].name
                
                description.appendChild(brief)
                description.appendChild(location)
                
                item.addEventListener("click", () => {
                    window.location = `singleproduct/?id=${items[i].id}`
                })

                item.appendChild(img)
                item.appendChild(price)
                item.appendChild(description)
                
                list.appendChild(item)
            }
        }
    } catch (error) {
        console.log(error)
    }
}

// search
const params = new Proxy(new URLSearchParams(window.location.search), {
    get: (searchParams, prop) => searchParams.get(prop),
});
let value = params.search;
fetchProducts(`/api/products/query?search=${value}`)

document.querySelector(".search_button").onclick = function(){
    const items = document.getElementById("search").value
    if(items){
        window.location = `/?search=${items}`
    }
}

// filter
document.querySelector(".filter-submit").onclick = function(){
    const beds = document.getElementById("beds").value

    //maybe need await (also find way to do load more)
    while (list.firstChild) {
        list.removeChild(list.firstChild);
    }
    fetchProducts(`/api/products/query?bed=${beds}`)
}

//home
const housify = document.getElementById("home")
housify.addEventListener("click", () => {
    window.location = "/"
})