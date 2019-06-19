


function register(){
  $.globalMessenger().post({
     message: "未开放!",
      hideAfter: 3,
      type: 'info'
  });

};


function login(id,password){

     var result;

     var handleSuccess = function(data){
            if(data !== undefined) {
                    try {

                       if(data.code==200){

                        $.globalMessenger().post({
                                message: "登录成功",
                                hideAfter: 1,
                                type: 'success',
                         });

                        window.open("http://www.qq.com")

                        }else{

                            $.globalMessenger().post({
                                message: "登录失败",
                                hideAfter: 3,
                                type: 'info'
                            });

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