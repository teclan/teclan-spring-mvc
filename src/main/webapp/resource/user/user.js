
    function get(){

     var result;

     var handleSuccess = function(data){
            if(data !== undefined) {
                    try {

                       if(data.code==200){
                            var data=eval(data.data);

                            var tbody = document.getElementById('tbody');

                            var tableContent='';
                            $(data).each(function (index){
                                var val=data[index];
                                var tr='<tr class="active"> ';
                                tr+='<td> '+val.id+' </td>';
                                tr+='<td> '+val.name+' </td>';
                                tr+='<td> '+val.phone+' </td>';
                                tr+='<td> '+val.address+' </td>';
                                 tr+='<td> <a href="https://www.baidu.com"> 删除</a>  <a href="https://www.baidu.com"> 编辑</a> </td>';

                                tr+='</tr>';

                                tableContent+=tr;
                            });
                            tbody.innerHTML = tableContent;

                             $.globalMessenger().post({
                                message: '查询成功',
                                hideAfter: 3,
                                type: 'info'
                            });

                       }else{

                            $.globalMessenger().post({
                                message: 'xx',
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


 	sync('POST',BASE_URL+'/user/get.do',handleSuccess,handleFailure);

};