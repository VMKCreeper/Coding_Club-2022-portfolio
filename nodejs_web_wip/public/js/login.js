import * as DATABASE from "./database.js"
let database = DATABASE.database;

document.getElementById("submit-button").onclick = function()
{
    const username = document.getElementById("username").value.toLowerCase();
    const password = document.getElementById("password").value;
    
    for(let i = 0; i < database.length; i++)
    {
        if(database[i]._username.toLowerCase() == username)
        {
            if(database[i]._password == password)
            {
                window.alert("Success");
                localStorage.setItem("cur-acc", i);
                if(database[i]._admin)
                {
                    window.location.href = "admin.html";
                }
                else
                {
                    window.location.href = "index.html";
                }
            }
            else
            {
                window.alert("Incorrect password");
            }
            return;
        }
    }
    window.alert("No account exists");
}