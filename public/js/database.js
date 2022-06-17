export let database;

if(localStorage.getItem("Database") == null)
{
    database = [];
}
else
{
    database = JSON.parse(localStorage.getItem("Database"))
}

export let currentAccount = localStorage.getItem("cur-acc")