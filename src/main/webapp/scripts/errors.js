function showError(container, errorMessage) {
  container.className = 'error';
  var msgElem = document.createElement('span');
  msgElem.className = "error-message";
  msgElem.innerHTML = errorMessage;
  container.appendChild(msgElem);
}

function resetError(container) {
  container.className = '';
  if (container.lastChild.className == "error-message") {
    container.removeChild(container.lastChild);
  }
}