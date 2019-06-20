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
                                var tr='<tr class="active"> ';
                                tr+='<td> '+val.id+' </td>';
                                tr+='<td> '+val.name+' </td>';
                                tr+='<td> '+val.phone+' </td>';
                                tr+='<td> '+val.id_card+' </td>';
                                 tr+='<td> <a href="https://www.baidu.com"> 删除</a> | <a href="https://www.baidu.com"> 编辑</a> </td>';

                                tr+='</tr>';

                                tableContent+=tr;
                            });
                            tbody.innerHTML = tableContent;

                             $.globalMessenger().post({
                                message: '查询成功',
                                hideAfter: 1,
                                type: 'info'
                            });

                            var pageInfo=eval(response.pageInfo);
                            flushPageInfo(pageInfo);
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

   var json = '{"currentPage":'+currentPage+',"pageSize":'+PAGE_SIZE+'}';
   async('POST',BASE_URL+'/user/get.do',json,handleSuccess,handleFailure);

};

function get(){
 query(currentPage);
};

// 访问首页
function getFirst(){

     if(isFirst){
        $.globalMessenger().post({
            message: '当前已经是首页了！',
            hideAfter: 1,
            type: 'info'
        });

        return ;
     }

    currentPage=1;
    get(currentPage,PAGE_SIZE);
};

// 访问末页
function getLast(){

     if(isLast){
        $.globalMessenger().post({
            message: '当前已经是末页了！',
            hideAfter: 1,
            type: 'info'
        });

        return ;
     }

    currentPage=totalPages;
    get(currentPage,PAGE_SIZE);
};

// 访问上一页
function getPrevious(){
    if(currentPage-1<1){
        $.globalMessenger().post({
                message: '不存在上一页！',
                hideAfter: 1,
                type: 'info'
         });

        return ;

    }
    currentPage=currentPage-1;
    get(currentPage,PAGE_SIZE);
};

// 访问下一页
function getNext(){


    if(currentPage+1>totalPages){
        $.globalMessenger().post({
                message: '不存在下一页！',
                hideAfter: 1,
                type: 'info'
         });

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

