import * as DATABASE from "./database.js"
let userData = DATABASE.database[DATABASE.currentAccount];
const data = ["Username", "Email", "Password", "Program", "Programtype", "Admin"];
const datauser = [userData._username, 
                  userData._email, 
                  userData._password, 
                  userData._program, 
                  userData._programtype]

//pfp
const img = document.getElementById("pfp");
const file = document.getElementById("file");
const username = document.querySelector(".username");
const email = document.querySelector(".email");
let tempsrc;

img.src = userData._pfp;
username.value = userData._username;
email.value = userData._email;

// change pfp
file.addEventListener("change", function(){
    const reader = new FileReader();
    reader.addEventListener("load", () => {
        tempsrc = reader.result;
        img.src = tempsrc;
    })

    reader.readAsDataURL(this.files[0]);
})
document.getElementById("save").onclick = function(){
    if(email.value && username.value)
    {
        const tempdata = DATABASE.database;
        if(tempsrc)
        {
            userData._pfp = tempsrc;
        }
        userData._email = email.value;
        userData._username = username.value;
        tempdata[DATABASE.currentAccount] = userData;
        localStorage.setItem("Database", JSON.stringify(tempdata));     
    }
    else
    {
        window.alert("Please fill in the required fields");
        username.value = userData._username;
        email.value = userData._email;
    }
}

if(userData == null)
{
    window.location.href = "home-loggedin.html";
}
const body = document.querySelector(".test");
const welcomemsg = document.createElement("h1");
welcomemsg.textContent = `Welcome, ${userData._username}`;

const table = document.createElement("table");
table.classList.add("infotable");

const row1 = document.createElement("tr");
const row2 = document.createElement("tr");
for(let i = 0; i < 5; i++)
{
    const theader = document.createElement("th");
    theader.textContent = data[i];   
    const tdata = document.createElement("td");
    tdata.textContent = datauser[i];

    row1.appendChild(theader);
    row2.appendChild(tdata);
}
table.appendChild(row1);
table.appendChild(row2);

body.appendChild(welcomemsg);
body.appendChild(table);