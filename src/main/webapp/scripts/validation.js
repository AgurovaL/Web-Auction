
function validateRegistrationForm(form) {
  var elems = form.elements,
      regex = /^\w+[ +\w+]$/, //строка не из пробелов
      flag = true;

  resetError(elems.name.parentNode);
  if (!elems.name.value || !regex.test(elems.name.value)) {
   flag = false;
   showError(elems.name.parentNode, 'enter name');
 } else {
   var re = /.*[0-9].*$/; //строка из чисел
   if (re.test(elems.name.value)) {
    flag = false;
    showError(elems.name.parentNode, 'name must contain only letters');
        } //в имени есть цифры
      }

      resetError(elems.address.parentNode);
      if (!elems.address.value || !regex.test(elems.address.value)) {
       flag = false;
       showError(elems.address.parentNode, 'enter address');
     }

     resetError(elems.login.parentNode);
     if (!elems.login.value || !regex.test(elems.login.value)) {
      flag = false;
      showError(elems.login.parentNode, 'enter login');
    }

    resetError(elems.password.parentNode);
    if (!elems.password.value || !regex.test(elems.password.value)) {
      flag = false;
      showError(elems.password.parentNode, 'enter password');
    } else
    {
      var re = /^([a-zа-яё]+|\d+)$/;
      if (elems.password.value.length < 5 || re.test(elems.password.value) ){
        flag = false;
        showError(elems.password.parentNode, 'password is not save: must have more than 5 symbols and contain both letters and digits');
      } else
      if (elems.password.value != elems.password2.value) {
       flag = false;
       showError(elems.password.parentNode, 'passwords not equal');
     }
   }

   if (flag === true){
    form.submit();
  } 
}

function validateLoginForm() {
  var login = document.getElementById("login"),
      password = document.getElementById("password"),
      flag = true,
      regex = /^\w+[ +\w+]$/; //строка не из пробелов

  resetError(login.parentNode);
  if (!login.value || !regex.test(login.value)) {
    flag = false;
    showError(login.parentNode, 'enter login');
  }

  resetError(password.parentNode);
  if (!password.value || !regex.test(password.value)) {
   flag = false;
   showError(password.parentNode, 'enter password');
 }

 if (flag === true){
  var form = document.getElementById("myform");
  form.submit();
}
}

function validateBidForm(formId) {
     var form = document.getElementById(formId);
         elems = form.elements,
         flag = true;
     resetError(elems.bid.parentNode);
     if (!elems.bid.value){
         flag = false;
         showError(elems.bid.parentNode, 'enter bid');
     }

     if (flag === true){
         form.submit();
     }
 }

function validateElementForm(form) {
  var elems = form.elements,
      regex = /^\w+[ +\w+]$/, //строка не из пробелов
      flag = true;

  resetError(elems.title.parentNode);
  if (!elems.title.value || !regex.test(elems.title.value)) {
    flag = false;
    showError(elems.title.parentNode, 'enter title');
  }

  resetError(elems.startPrice.parentNode);
  if (!elems.startPrice.value || !regex.test(elems.startPrice.value)) {
   flag = false;
   showError(elems.startPrice.parentNode, 'enter start price');
 }

 resetError(elems.bidInc.parentNode);
 if (!elems.bidInc.value || !regex.test(elems.bidInc.value)) {
  flag = false;
  showError(elems.bidInc.parentNode, 'enter bid increment');
}

resetError(elems.stopDate.parentNode);
  var isDateRight=testDate(elems.stopDate.value); //проверяем дату
  if(!isDateRight){ //если вернулась строка, а не false
    flag = false;
    showError(elems.stopDate.parentNode, 'invalid date');
  }

  if (flag === true){
    form.submit();
  }
}

function testDate(str){
   strParts=str.split("-"); //разбить дату
   if(strParts.length!=3){return false;} //если меньше трех частей - уже неверно

  if( strParts[3] < new Date().getFullYear() || strParts[3] > new Date().getFullYear() + 5){
    return false;}

  return str;
  }
  