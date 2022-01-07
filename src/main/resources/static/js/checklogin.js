async function isLoginFn(){
    let isLogin = undefined;
    await axios.get("http://192.168.1.175:8080/auth").then((response)=>{isLogin = response.data.isLogin})
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




