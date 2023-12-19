const userUrl = '/api/user';


function getPage() {
    fetch(userUrl).then(response => response.json()).then(user =>
        getInformation(user))
}

function getInformation(user) {
    console.log(user)

    document.getElementById('basicTable').innerHTML = `<tr>
            <td>${user.id}</td>
            <td>${user.firstname}</td>
            <td>${user.lastname}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${listRoles(user)}</td>
         
        </tr>`;

   document.getElementById('buttonAdmin').hidden = isNotAdminRole(user);

    document.getElementById('UserInfo').innerHTML = `<tr>
        ${user.email} with roles: ${listRoles(user)}</tr>`;

}

function isNotAdminRole(user){

    let isNotAdminRole = true;

    for (let i = 0; i < user.roles.length; i++) {
        if (user.roles[i].role == 'ROLE_ADMIN'){
            isNotAdminRole = false;
            break;
        };
    }

    return isNotAdminRole;
}

getPage();