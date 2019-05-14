$(document).ready(function () {
  $('.mark-checked-btn').click(function (e) {
    var containerE = $(this).parent().parent();
    var roid = containerE.attr("id");
    var url = "/admin/manage/guests/bad-transactions/" + roid + "/checked";
    $.get(url, {},
      function (data, textStatus, jqXHR) {
        if (textStatus == "success") {
          containerE.fadeOut();
        }
      },
      "text"
    );
  });
});