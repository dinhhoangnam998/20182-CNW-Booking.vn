$(document).ready(function () {


  function showResult(content) {
    $('#search-result').html(content);
    $('#search-result').show();
    $('.list-group-item').click(function () {
      var address = $(this).text();
      $('#search-box').val(address);
      $('#search-result').hide();
    })
  }

  function processResult(districts) {
    var content = "";
    content = '<ul class="list-group">';

    districts.forEach(dis => {
      content +=
        '<li class="list-group-item">' +
        dis.name + ", " + dis.province.name +
        ' </li>';
    });
    content += '</ul>';
    showResult(content);
  }

  $('#search-box').keyup(function (e) {
    var q = $('#search-box').val();
    $.getJSON("/guest/get-recomment", { querry: q },
      function (data, textStatus, jqXHR) {
        if (textStatus == "success") {
          processResult(data);
        }
      }
    );
  });
});