// 顶部弹窗提示，1秒后自动消失，msg为提示内容
function showMessage(msg){
  $.globalMessenger().post({
     message: msg,
     hideAfter: 1,
     type: 'info'
  });
};

// 顶部弹窗提示，指定时间后自动消失，msg为提示内容，time为指定时间，单位：秒
function showMessageWithTimeOut(msg,time){
  $.globalMessenger().post({
     message: msg,
     hideAfter: time,
     type: 'info'
  });
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

// 刷新当前页面信息
function flushPageInfo(pageInfo){
   currentPage=pageInfo.currentPage;
   totals=pageInfo.totals;
   totalPages=pageInfo.totalPages;
   isFirst=pageInfo.isFirst;
   isLast=pageInfo.isLast;
   $('#info').text('第'+currentPage+'页/共'+totalPages+'页/总数'+totals);
};

// 获取编辑页面
function getEditPage(val,url){
    var id=$(val).attr('data');
    SELECT_ID = id;
    localStorage.setItem("SELECT_ID",id);
    window.location.href=BASE_URL+url;
};

// 获取普通页面
function getPage(url){
    window.location.href=BASE_URL+url;
};

// 获取详细页面
function getDetail(url){
    var handleSuccess = function(response){
         if(response !== undefined) {
             try {

                 if(response.code==200){
                    showMessage(response.message);

                    var data = response.data;

                   // 用于详情页的值设值
                   setDataForDetail(data);

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
    async('POST',BASE_URL+url,json,handleSuccess,handleFailure);

}

// 通用的更新方法
function commonUpdate(url,json){
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

     async('POST',BASE_URL+url,json,handleSuccess,handleFailure);

};

// 通用的添加方法
function commonAdd(url,json){
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

     async('POST',BASE_URL+url,json,handleSuccess,handleFailure);

};

// 通用的删除方法
function commonDel(val,url){

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
     async('POST',BASE_URL+url,json,handleSuccess,handleFailure);
};

// 获取版权信息
function getCopyright(){
    return COPY_RIGHT;
}

// 设置版权
function setDefaultCopyright(id){
   if(id==null){
     id='body';
   }
   document.getElementById(id).innerHTML += COPY_RIGHT_HTML;
}

// 设置默认页脚（页码信息）
function setDefaultFooter(){

  var el = document.getElementById('footer');
  if(el!=null){
    el.innerHTML += FOOTER_HTML;
  }

}

// 设置导航
function setNavigation(){
  var el = document.getElementById('navigation');
  if(el!=null){
    el.innerHTML += NAVIGATION_HTML;
  }

}

// 构造导航代码
function generateNavigationHtml(configs ){

   var code = '<nav class="nav navbar-inverse navbar-fixed-top"> '
                    +'<div class="container"> '
                    +'<div id="menu" class="collapse navbar-collapse"> '
                    +'  <ul class="nav navbar-nav"> ';


   for(var i in configs){
        var item = configs[i];
        var id = item.id; // 元素id
        var name = item.name; // 导航名称
        var dropdown = item.dropdown; // 是否存在下拉项


        if(dropdown==true){

             var dropdown_items = item.dropdown_items;

             code += '              <li class="dropdown" id="'+id+'"> ';
             code += '                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"> ';
             code += '                      '+name+' ';
             code += '                      <span class="caret"></span> ';
             code += '                  </a> ';
             code += '                  <ul class="dropdown-menu"> ';

             for(var index in dropdown_items){

                 var id = dropdown_items[index].id; // 元素id
                 var name = dropdown_items[index].name; // 导航名称
                 code += '                      <li><a href="#" id='+id+'>'+name+'</a></li> ';
             }

              code += '                  </ul> ';
               code += '              </li> ';

        }else{
             code += '              <li><a id="'+id+'" class="navbar-brand"  href="">'+name+'</a></li> ';
        }
   }


    code += '      </ul> ';
    code += '  </div> ';
    code += '</div> ';
    code += '</nav>';

    return code;


}

// 初始化页面
function initPage(){

$.ajaxSettings.async = false;
  // 设置导航
  setNavigation();
  // 设置页脚
  setDefaultFooter();
  // 设置版权
  setDefaultCopyright();

}


// 读取菜单配置文件
function setNavigation(){

    $.getJSON("../json/menu.json", function (data,status){
        if( status=='success'){
          var code =  generateNavigationHtml(data);

            var el = document.getElementById('navigation');
              if(el!=null){
                el.innerHTML = code;
              }

            return data;
        }else{
             console.log("json/menu.json文件读取失败："+status);
             return false;
        }
    });
}

