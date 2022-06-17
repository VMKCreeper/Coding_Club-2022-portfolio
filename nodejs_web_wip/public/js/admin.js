import * as DATABASE from "./database.js"
let database = DATABASE.database;
let userdata = database[DATABASE.currentAccount];

const data = ["Username", "Email", "Password", "Program", "Programtype", "Admin"];

const welcomemsg = document.createElement("h1");
welcomemsg.textContent = `Welcome, ${userdata._username}`;

const body = document.querySelector(".test-admin");
const table = document.createElement("table");
table.classList.add("infotable");

const row1 = document.createElement("tr");
for(let i = 0; i < data.length; i++)
{
    const theader = document.createElement("th");
    theader.textContent = data[i];
    row1.appendChild(theader);
}
table.appendChild(row1);

for(let i = 0; i < database.length; i++)
{
    const username = database[i]._username;
    const row2 = document.createElement("tr");
    const datauser = [database[i]._username, 
                    database[i]._email, 
                    database[i]._password, 
                    database[i]._program, 
                    database[i]._programtype,
                    database[i]._admin];
    for(let j = 0; j < datauser.length; j++)
    {
        const tdata = document.createElement("td");
        tdata.textContent = datauser[j];
        row2.appendChild(tdata);
    }

    // delete account
    const delete_el = document.createElement("td");
    delete_el.id = "delete";
    delete_el.textContent = "Delete";
    delete_el.addEventListener("click", () => {
        let index;
        for(let k = 0; k < database.length; k++)
        {
            if(database[k]._username == username)
            {
                index = k;
            }
        }
        
        let string = window.prompt("Are you sure you want to delete this account? Type \"YES\" to confirm");
        if(string == "YES")
        {
            database.splice(index, 1);
            table.removeChild(row2);
            localStorage.setItem("Database", JSON.stringify(database));
        }
    })

    row2.appendChild(delete_el);
    table.appendChild(row2);
}

body.appendChild(welcomemsg);
body.appendChild(table);
