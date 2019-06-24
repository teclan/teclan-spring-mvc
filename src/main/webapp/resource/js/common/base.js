function showMessage(msg){
  $.globalMessenger().post({
     message: msg,
     hideAfter: 1,
     type: 'info'
  });
};

function showMessageWithTimeOut(msg,time){
  $.globalMessenger().post({
     message: msg,
     hideAfter: time,
     type: 'info'
  });
};
