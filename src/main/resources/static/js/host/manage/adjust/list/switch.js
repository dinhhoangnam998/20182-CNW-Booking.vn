$(document).ready(function () {
  $('input.custom-control-input').change(function (e) {
    var alertDiv = $(this).parent().prev();
    var hid = $('main').attr("id");
    var fid = $(this).attr("name");
    var url = "/host/manage/hotels/" + hid + "/floors/adjust/" + fid + "/toggle";

    var msg = "";
    $.get(url, {},
      function (data, textStatus, jqXHR) {
        if (textStatus == "success") {
          if (data == "true") {
            msg = "The rooms is on now!";
          } else {
            msg = "The rooms if off now!"
          }

          var alert =
            '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
            '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
            '<span aria-hidden="true">&times;</span>' +
            '<span class="sr-only">Close</span>' +
            '</button>' +
            '<strong>' +
            msg +
            '</strong>' +
            '</div>';

          alertDiv.prepend(alert);
        }
      },
      "text"
    );
  });
});