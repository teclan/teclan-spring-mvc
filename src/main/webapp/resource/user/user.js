var currentPage=1;
var totals=0;
var totalPages=0;
var isFirst=false;
var isLast=false;


function query(currentPage){

     var result;

     var handleSuccess = function(response){
            if(response !== undefined) {
                    try {

                       if(response.code==200){

                            // 内容相关
                            var data=eval(response.data);

                            var tbody = document.getElementById('tbody');

                            var tableContent='';
                            $(data).each(function (index){
                                var val=data[index];
                                var tr='<tr class="active text-center"> ';
                                tr+='<td> '+val.id+' </td>';
                                tr+='<td> '+val.name+' </td>';
                                tr+='<td> '+val.phone+' </td>';
                                tr+='<td> '+val.id_card+' </td>';
                                tr+='<td class="active text-left" style="width: 200px;"> '
                                +'<button class="btn btn-default" type="button" data="'+val.id+'" onclick="del(this)">删除</button> '
                                +'<button class="btn btn-default" type="button" data="'+val.id+'" onclick="edit(this)">编辑</button> '
                                +' </td>';

                                tr+='</tr>';

                                tableContent+=tr;
                            });
                            tbody.innerHTML = tableContent;

                           showMessage(response.message);

                            var pageInfo=eval(response.pageInfo);
                            flushPageInfo(pageInfo);
                       }else{
                            showMessage(response.message);
                       }

                    } catch(e) {
                        alert("error!"+e);
                        return false;
                    }
           }
      };

 	 var handleFailure = function(o){
 	 };

   var json = '{"currentPage":'+currentPage+',"pageSize":'+PAGE_SIZE+'}';
   async('POST',BASE_URL+'/user/page.do',json,handleSuccess,handleFailure);

};


function get(){
 query(currentPage);
};

// 访问首页
function getFirst(){

     if(isFirst){
       showMessage( '当前已经是首页了！');
        return ;
     }

    currentPage=1;
    get(currentPage,PAGE_SIZE);
};

// 访问末页
function getLast(){

     if(isLast){
        showMessage( '当前已经是末页了！');
        return ;
     }

    currentPage=totalPages;
    get(currentPage,PAGE_SIZE);
};

// 访问上一页
function getPrevious(){
    if(currentPage-1<1){
       showMessage( '不存在上一页！');
        return ;
    }
    currentPage=currentPage-1;
    get(currentPage,PAGE_SIZE);
};

// 访问下一页
function getNext(){
    if(currentPage+1>totalPages){
       showMessage( '不存在下一页！');
        return ;
    }
    currentPage=currentPage+1;
    get(currentPage,PAGE_SIZE);
};

function flushPageInfo(pageInfo){
   currentPage=pageInfo.currentPage;
   totals=pageInfo.totals;
   totalPages=pageInfo.totalPages;
   isFirst=pageInfo.isFirst;
   isLast=pageInfo.isLast;
   $('#info').text('第'+currentPage+'页/共'+totalPages+'页/总数'+totals);
};

function del(val){
   // var id = eval(val.attributes.data.nodeValue);
    var id=$(val).attr('data');

    var handleSuccess = function(response){
                if(response !== undefined) {
                        try {

                           if(response.code==200){
                               showMessage(response.message);
                               get();
                           }else{
                                showMessage(response.message);
                           }

                        } catch(e) {
                            alert("error!"+e);
                            return false;
                        }
               }
          };

     	 var handleFailure = function(o){
     	 };

    var json = '{"id":'+id+'}';
     async('POST',BASE_URL+'/user/delete.do',json,handleSuccess,handleFailure);
};

function edit(val){
    // var id = eval(val.attributes.data.nodeValue);
    var id=$(val).attr('data');
    SELECT_ID = id;
    localStorage.setItem("SELECT_ID",id);
    window.location.href=BASE_URL+"/resource/user/edit.html";
};

function getById(){

    var handleSuccess = function(response){
         if(response !== undefined) {
             try {

                 if(response.code==200){
                    showMessage(response.message);

                    var data = response.data;

                    $("#id").attr("value",data.id);
                    $("#name").attr("value",data.name);
                    $("#phone").attr("value",data.phone);
                    $("#id_card").attr("value",data.id_card);

                 }else{
                    showMessage(response.message);
                 }

             } catch(e) {
                 alert("error!"+e);
                 return false;
             }
         }
    };

    var handleFailure = function(o){
    };

    var SELECT_ID = localStorage.getItem("SELECT_ID");

    var json = '{"id":'+SELECT_ID+'}';
    async('POST',BASE_URL+'/user/get.do',json,handleSuccess,handleFailure);

}


function doUpdate(json){

        var handleSuccess = function(response){
           if(response !== undefined) {
               try {

                  if(response.code==200){
                      showMessage(response.message);
                         //get();
                  }else{
                          showMessage(response.message);
                  }

               } catch(e) {
                   alert("error!"+e);
                   return false;
               }
           }
        };

     	var handleFailure = function(o){
     	};

     async('POST',BASE_URL+'/user/update.do',json,handleSuccess,handleFailure);

};

