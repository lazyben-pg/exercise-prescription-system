async function isLoginFn(){
    let isLogin = undefined;
    await axios.get("http://www.myapp.com:8080/auth").then((response)=>{isLogin = response.data.isLogin})
    return isLogin
}
// var apiUrl=""
//     axios({
//         url: apiUrl,
//         method: "get",
//         headers: {
//             "Content-Type": "application/json"
//         }
//     }).then(function(response){
  
//     })




