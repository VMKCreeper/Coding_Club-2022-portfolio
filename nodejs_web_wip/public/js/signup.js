import * as DATABASE from "./database.js"
let database = DATABASE.database

const programs = [
    ["hhh", "fff"],
    ["ggg", "ddd"],
    ["kkk", "aaa"],
    ["jjj", "ccc"]
];

class user{
    constructor(username, email, password, program, programtype)
    {
        this._username = username
        this._email = email
        this._password = password
        this._program = program
        this._programtype = programtype
        this._pfp = "https://upload.wikimedia.org/wikipedia/commons/a/ac/Default_pfp.jpg"

        this._admin = false
    }
}

document.getElementById("submit-button").onclick = function()
{
    const username = document.getElementById("username").value
    const email = document.getElementById("email").value.toLowerCase()
    const password = document.getElementById("password").value
    const password_c = document.getElementById("password-confirm").value
    
    const program_type = document.getElementById("program")
    const newUser = new user(username, email, password, program_choice.children[program_value].id, program_type.children[program_type.value].id)
    console.log(program_choice.children[program_value].id)
    
    let create = true
    
    if(username != false && email != false && password != false && password_c != false)
    {
        for(let i = 0; i < database.length; i++)
        {
            if(database[i]._username.toLowerCase() == username.toLowerCase() || database[i]._email == email){
                create = false;
                window.alert("Account already exists")
                break
            }
        }
        
        if(create)
        {
            if(password == password_c)
            {
                database.push(newUser)
                if(document.getElementById("admin").checked)
                {
                    newUser._admin = true
                }
                window.alert("Created")
                
                localStorage.setItem("Database", JSON.stringify(database))
                const items = document.querySelectorAll(".login input")
                items.forEach(element => {
                    element.value = ""
                });
            }
            else
            {
                window.alert("Passwords don't match")
            }
        }
    }
    else{
        window.alert("Please fill out the required fields")
    }
}

// program choice
const program_choice = document.getElementById("program-type")
const selection = document.getElementById("selections")
let program_value = program_choice.value

program_choice.addEventListener("click", () => 
{
    if(program_value != program_choice.value)
    {
        const type = parseInt(program_choice.value)
        const select = document.createElement("select")
        select.id = "program"
        
        selection.removeChild(document.getElementById("program"))
        
        for(let i = 0; i < programs[type].length; i++)
        {
            const new_option = document.createElement("option")
            const program_name = programs[type][i]
            new_option.textContent = program_name
            new_option.id = program_name
            new_option.value = i
            select.appendChild(new_option)
        }
        selection.appendChild(select)
        
        program_value = program_choice.value
    }
})