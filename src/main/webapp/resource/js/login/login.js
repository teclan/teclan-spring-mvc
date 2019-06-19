


function register(){
 alert('注册，未实现！');

};


function login(id,password){

     var result;

     var handleSuccess = function(o){
            if(o !== undefined) {
                    try {
                       result = o;

                       if(result.code==200){

                        	alert('登录成功');

                        	window.open("http://www.qq.com")

                        }else{

                            alert('登录失败');

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

 	async('POST',BASE_URL+'/user/login.do?id='+id+'&password='+password,json,handleSuccess,handleFailure);


};