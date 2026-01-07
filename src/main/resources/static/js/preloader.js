window.addEventListener("load", function () {
  const preloader = document.getElementById("preloader-overlay");
  const content = document.getElementById("content");

  preloader.style.opacity = "0";

  setTimeout(function () {
    preloader.style.display = "none";
    content.style.display = "block";
  }, 3000);
});
