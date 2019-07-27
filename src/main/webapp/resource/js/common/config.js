// 后端服务跟路径
var BASE_URL="/teclan-spring-mvc";

//  全局分页大小
var PAGE_SIZE=5;

// 用于编辑页面，缓存被选中行的标识
var SELECT_ID=-1;

$._messengerDefaults = {
           extraClasses: 'messenger-fixed messenger-theme-future messenger-on-top messenger-on-center'
};

// 版权
var  COPY_RIGHT = "©2019 Teclan 广西xxxx公司";

var COPY_RIGHT_HTML ='<div id="default_copyright" style="text-align:center">'+COPY_RIGHT+'</div>';

// 全局的页脚信息（页码信息）
var FOOTER_HTML='<nav aria-label="..."> '
                +'   <ul class="pager"> '
                +'         <li><a href="#" id="first" onclick="getFirst()">首页</a></li> '
                +'         <li><a href="#" id="previous" onclick="getPrevious()">上一页</a></li> '
                +'         <li><a href="#" id="info"></a></li> '
                +'         <li><a href="#" id="next" onclick="getNext()">下一页</a></li> '
                +'         <li><a href="#" id="last" onclick="getLast()">末页</a></li> '
                +'    </ul>'
                +' </nav>';