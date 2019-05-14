$(document).ready(function () {
  function checkState() {
    var state = $('#submit-state').text();
    if (state == "NEW") {
      $('#checked-btn').show();
      $('#approval-btn').show();
      $('#decline-btn').show();
    } else if (state == "PROCESSING") {
      $('#checked-btn').hide();
      $('#approval-btn').show();
      $('#decline-btn').show();
    } else if (state == "APPROVALED") {
      $('#checked-btn').hide();
      $('#approval-btn').hide();
      $('#decline-btn').hide();
    } else if (state == "DECLINED") {
      $('#checked-btn').hide();
      $('#approval-btn').hide();
      $('#decline-btn').hide();
    }
  }

  checkState();


  function callAjax(action) {
    var hotelId = $('.my-container').attr("id");
    var url = "/admin/manage/submits/" + hotelId + "/" + action;
    $.get(url, {},
      function (response, textStatus, jqXHR) {
        if (textStatus == "success") {
          $('#submit-state').text(response);
          checkState();

          var alertMsg = "Submit state now is: " + response;
          var alertPattern =
            '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
            '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
            '<span aria-hidden="true">&times;</span>' +
            '<span class="sr-only">Close</span>' +
            '</button>' +
            '<strong>' +
            alertMsg +
            '</strong>' +
            '</div>';
          $('#alert-container').html(alertPattern);
        }
      },
      "text"
    );
  }



  $('#checked-btn').click(function (e) {
    callAjax("checked");
  });

  $('#approval-btn').click(function (e) {
    callAjax("approval");
  });

  $('#decline-btn').click(function (e) {
    callAjax("decline");
  });
});