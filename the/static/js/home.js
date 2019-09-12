$(function () {
  'use strict'

  $('[data-toggle="offcanvas"]').on('click', function () {
    $('.offcanvas-collapse').toggleClass('open')
  })
})


$.get( "home.php", function( data ) {
  $.each(JSON.parse(JSON.stringify(data)), function(idx, obj) {
    $('#posts').append(`
    <div class="media text-muted pt-3">
        <img data-src="holder.js/32x32?theme=thumb&bg=007bff&fg=007bff&size=1" alt="" class="mr-2 rounded">
        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
          <strong class="d-block text-gray-dark">` + obj.Username + `</strong>
          ` + obj.Text + `
        </p>
      </div>
    `);

  });  
});
