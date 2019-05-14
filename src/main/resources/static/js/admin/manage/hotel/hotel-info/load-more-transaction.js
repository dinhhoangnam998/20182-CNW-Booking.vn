$(document).ready(function () {
  var counter = 2;
  var hid = $('main').attr("id");
  var url = "/admin/manage/hotels/" + hid + "/get-more-transactions";

  $('#load-more-btn').click(function () {
    $.getJSON(url, { ith: counter },
      function (data, textStatus, jqXHR) {
        if (textStatus == "success") {
          var ros = data;
          if (ros.length > 0) {
            ros.forEach(ro => {
              var content =
                '<div>' +
                '<div>RO Id: ' +
                ro.id +
                '</div>' +
                '<div>RO Date:' +
                ro.date +
                ' </div> ' +
                '<div>Vote by Guest:' +
                ro.voteByGuest +
                '</div>' +
                '</div>';

              $('#transacions').append(content);
            });
          } else {
            $('#load-more-btn').hide();
          var content = '<i>No more transaction available!</i>';
          $('#transacions').append(content);
          }
        }
      }
    );

  })

});