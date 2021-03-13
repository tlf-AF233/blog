// 下拉菜单
$('.ui.dropdown').dropdown({
    allowAdditions: true,
    on: 'hover'
})

$('.toc.mm').click(function () {
  $('.m-item').toggleClass('m-mobile-hide')
})

// 退出
$('#logout-btn').click(function () {
    $('.ui.login.out.tiny.modal')
        .modal({
            onApprove: function () {
                $.ajax({
                    type: 'get',
                    url: '/logout',
                    success: function (res) {
                        if (res.code === 1000) {
                            window.location.href = '/login'
                        }
                    }
                })
            }
        })
    .modal('show')
})

