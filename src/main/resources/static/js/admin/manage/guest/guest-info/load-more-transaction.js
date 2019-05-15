$(document).ready(function () {
  var counter = 1;
  var gid = $('main').attr("id");
  var url = "/admin/manage/guests/" + gid + "/get-more-transactions";

  $('#load-more-btn').click(function () {
    $.getJSON(url, { ith: counter },
      function (data, textStatus, jqXHR) {
        if (textStatus == "success") {
          var ros = data;
          if (ros.length > 0) {
            ros.forEach(ro => {
              var content =
                '<div class="my-2 ro-container">' +
                '<div>RO Id: ' +
                ro.id +
                '</div>' +
                '<div>RO Date:' +
                ro.date +
                ' </div> ' +
                '<div>' +
                'Hotel Name:' +
                ro.hotel.name +
                '</div>' +
                '<div>Vote by Hotel:' +
                ro.voteByHost +
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