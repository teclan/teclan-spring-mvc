


function register(){
 alert('注册，未实现！');

};


function login(id,password){
    alert('登录,ID='+id+' ,password='+password);

     var handleSuccess = function(o){
            if(o !== undefined) {
                    try {
                        var reseult = eval("("+o+")");
                        if('1'==reseult.flag){
                          return true; // 已收取
                        }else{
                            return false; // 未收取
                        }
                    } catch(e) {
                        alert("error!"+e);
                        return false;
                    }
           }
      };

 	 var handleFailure = function(o){
 	 };

    var json = '{"id":"'+id+'","password":"'+password+'"}';

 	sync('POST',BASE_URL+'/user/login.do?id='+id+'&password='+password,json,handleSuccess,handleFailure);


};