
function getDefaultZTreeSetting(rootPId,url){
    var setting = {
            view: {
                 dblClickExpand: false,
                 showLine: true,
                 fontCss:{'color':'black','font-weight':'bold'},
                 selectedMulti: true,
                 showIcon: true,
                 showTitle: true

            },
            edit:{
                enable: true,
                editNameSelectAll: false,
                showRemoveBtn : false,
                showRenameBtn : false,
                removeTitle : "remove",
                renameTitle : "rename"
            },
            data: {
                simpleData: {
                       enable: true,
                       idKey: "id",
                       pIdKey: "parentId",
                       rootPId: rootPId
                },
                key: {
                       name: "name"
                }
            },
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: { "Y": "p", "N": "s" }
            },
            callback: {
                 onClick: onSelected,
                 onCheck: onChecked,
                 onExpand: onExpanded
            }
    };

    if(url!=null){
        var async = new Object();
        async.url=url;
        setting.async=async;
    }

    return setting;
};


// 初始化树结构,全量加载树结构数据
// treeId: 存放树结构的容器(必填)
// nodes: 节点数据(非必填)
// rootPId: 根节点父id,默认-1(非必填)
// url: 节点展开时默认请求的加载子节点的url，(非必填)
//       post请求，json格式送参，参数格式{"id": ${对应节点的id}}
function ZTreeInit(treeId,nodes,rootPId,url){

   if(rootPId==null){
     rootPId=-1;
   }

   if(nodes==null){
     nodes=[];
   }

  zTreeObj = $.fn.zTree.init($("#"+treeId), getDefaultZTreeSetting(rootPId,url), nodes); //初始化树
  zTreeObj.expandAll(false);    //true 节点全部展开、false节点收缩
  return zTreeObj;
};


// zTree 的 callback.onCheck 回调的共用方法
function onChecked(event, treeId, treeNode) {

         var tree = $.fn.zTree.getZTreeObj(treeId);

         // 更新子节点的选中状态
         updateChildrenCheckStatus(tree,treeNode);
         // 更新父节点的选中状态
         updateParentCheckStatus(tree,treeNode);

        getCheckNodes(tree);
};

// zTree 的 callback.onExpand 回调的共用方法
function onExpanded(event, treeId, treeNode) {
   var tree = $.fn.zTree.getZTreeObj(treeId);
   var url = tree.setting.async.url;

   var handleSuccess = function(response){
            if(response !== undefined) {
                    try {
                       if(response.code==200){
                            var data=eval(response.data);
                            treeNode.children = data;

                            for(var node of data){

                               if(node.children=='undefined' || node.children==null){
                                 node.children=[];
                                 node.open = false;
                               }

                            }

                            //tree.updateNode(treeNode,false);
                            tree.refresh();
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

   var isAjaxing = treeNode.isAjaxing;
 	 if(isAjaxing){
 	   showMessage('该节点正在加载，请稍候重试...');
 	   return;
   }

   if(url==null||""==url){
 	  // do nothing
 	   return;
   }
   var json = '{"id":'+treeNode.id+'}';
   sync('POST',BASE_URL+url,json,handleSuccess,handleFailure);
};

// 获取勾选节点的信息
function getCheckNodes(tree){

        // 选中的节点，但不包括半勾选节点
         var selectedNodes = tree.getCheckedNodes();

         // 半选节点
         var halfSelectNodeSet = new Set();
         // 全选节点
         var selectedNodeSet = new Set();

         // 要获取半勾选的节点，必须遍历选中的节点的所有父节点，
         // 因为半选的节点只会在这些被选中的节点的父节点中出现

         for(var node of selectedNodes) {
            collectNodesInfo(node,selectedNodeSet,halfSelectNodeSet);
         }

          var result = new Object();
          var selected = new Array();
          var half = new Array();

          var index = 0;
          for (var node of selectedNodeSet.keys()){
              var item = new Object();
              item.id=node.id;
              item.name=node.name;
              item.parentId=node.parentId;
              selected[index++]=item;
          }
          index = 0;
          for (var node of halfSelectNodeSet.keys()){
               var item = new Object();
               item.id=node.id;
               item.name=node.name;
               item.parentId=node.parentId;
               half[index++]=item;
          }

          result.selected=selected;
          result.half=half;

          return result;
}


// 更新父节点的选中状态,当节点被取消选中状态时，
// 需要对父节点的选中状态做更新，如果某个节点下
// 已经没有任何节点被选中,应该对该的半勾选状态取消

// tree: 树对象
// treeNode : 被选中/取消选中的节点

function updateParentCheckStatus(tree,treeNode){

   // 当前节点的选中状态
   var checkStatus = treeNode.checked;
   // 获取当前节点的父节点
   var parent = treeNode.getParentNode();

   if(parent==undefined){
       return;
   }

   // 当前节点的选中状态
   var pCheckStatus = parent.getCheckStatus();

   // 当前节点被取消选中时，需要检查父节点的勾选状态
   // 如果父节点是处于半勾选状态，需要检查父节点的子节点是否有选中，如果子节点都没有选中，那么取消该节点的半勾选状态

   var hasChildrenChecked = false;
   var children = parent.children;

   if(children!=undefined){
       for(var node of children) {
           var chkStu = node.getCheckStatus();
           // 如果发现被选字节点有选中（含半勾选），则该节点的父节点半勾选状态保留
           if(chkStu.checked==true){
              hasChildrenChecked=true;
              break;
           }
       }
   }

   if(!hasChildrenChecked){
       parent.checked=false;
       parent.half=false;
       tree.updateNode(parent,false);
   }

   updateParentCheckStatus(tree,parent);
};


// 更新子节点的选中状态
// tree: 树对象
// treeNode : 被选中/取消选中的节点

function updateChildrenCheckStatus(tree,treeNode){

   // 当前节点的选中状态
   var checkStatus = treeNode.checked;
   // 获取当前节点的子节点
   var children = treeNode.children;

   if(children==undefined){
       return;
   }
   for(var node of children) {
       node.checked=checkStatus;
       tree.updateNode(node,false);
       updateChildrenCheckStatus(tree,node);
   }
};

function collectNodesInfo(treeNode,selectedNodeSet,halfSelectedNodeSet){
   var parent = treeNode;
   while(parent!=null){
        var checkStatus = parent.getCheckStatus();
        if(checkStatus.half==true){
             halfSelectedNodeSet.add(parent);
        }else{
          selectedNodeSet.add(parent);
        }
        parent = parent.getParentNode();
    }
};