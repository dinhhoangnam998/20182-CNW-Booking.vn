$(document).ready(function () {
  function checkState() {
    var state = $('#parent').attr("name");
    if (state == "ACTIVE") {
      $('#unwarning-btn').hide();
      $('#unblock-btn').hide();
      $('#warning-btn').show();
      $('#block-btn').show();
    } else if (state == "WARNING") {
      $('#unwarning-btn').show();
      $('#unblock-btn').hide();
      $('#warning-btn').hide();
      $('#block-btn').show();
    } else if (state == "BLOCKED") {
      $('#unwarning-btn').hide();
      $('#unblock-btn').show();
      $('#warning-btn').hide();
      $('#block-btn').hide();
    } else if (state == "WATTING") {
      $('#unwarning-btn').hide();
      $('#unblock-btn').hide();
      $('#warning-btn').hide();
      $('#block-btn').hide();
    }
  }

  checkState();

  function callAjax(action) {
    var gid = $('main').attr("id");
    var url = "/admin/manage/guests/" + gid + "/" + action;
    $.get(url, {},
      function (response, textStatus, jqXHR) {
        if (textStatus == "success") {
          $('#parent').attr("name", response);
          $('#activeState').text(response);
          checkState();

          var alertMsg = "Account state now is: " + response;
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
          $('#alertHTML').html(alertPattern);
        }
      },
      "text"
    );
  }

  $('#warning-btn').click(function (e) {
    callAjax("warning");
  });

  $('#unwarning-btn').click(function (e) {
    callAjax("unwarning");
  });

  $('#block-btn').click(function (e) {
    callAjax("block");
  });

  $('#unblock-btn').click(function (e) {
    callAjax("unblock");
  });

});