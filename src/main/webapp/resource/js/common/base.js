function showMessage(msg){
  $.globalMessenger().post({
     message: msg,
     hideAfter: 1,
     type: 'info'
  });
}