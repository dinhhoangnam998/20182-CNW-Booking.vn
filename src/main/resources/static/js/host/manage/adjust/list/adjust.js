 $(document).ready(function () {

      var hid = $('main').attr('id');

      $('input[name="openDates"]').datepicker({
        multidate: true,
        format: "dd-mm-yyyy",
        clearBtn: true,
        todayHighlight: true,
        language: "vi",
        enableOnReadonly: false
      });

      $('.save-btn').hide();

      $('.adjust-btn').click(function (e) {
        var parent = $(this).parent();
        var opendatesInp = $(this).prev().prev().find('input');
        var priceInp = $(this).prev().find('input');
        var saveBtn = $(this).next();

        opendatesInp.attr("readonly", false);
        priceInp.attr("readonly", false);
        $(this).hide();;
        saveBtn.show();
      });

      $('.save-btn').click(function (e) {
        var parent = $(this).parent();
        var opendatesInp = $(this).prev().prev().prev().find('input');
        var priceInp = $(this).prev().prev().find('input');
        var adjustBtn = $(this).prev();

        opendatesInp.attr("readonly", true);
        priceInp.attr("readonly", true);

        $(this).hide();
        adjustBtn.show();

        var fid = parent.attr('id');
        var opd = opendatesInp.val();
        var price = priceInp.val();
        var url = "/host/manage/hotels/" + hid + "/floors/adjust/" + fid;

        $.get(url, { openDates: opd, price: price },
          function (data, textStatus, jqXHR) {
            if (textStatus == "success") {
              var alert =
                '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
                '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                '<span aria-hidden="true">&times;</span>' +
                '<span class="sr-only">Close</span>' +
                '</button>' +
                '<strong>Adjust success!</strong>' +
                '</div>';
              parent.prepend(alert);
            }
          },
          "text"
        );

      });
    });