function selecionarMenu(menuId) {
    $('.menuItemWantList').removeClass('custom-menu-item');
    $('.menuItemHaveList').removeClass('custom-menu-item');
    $('.menuItemMyCards').removeClass('custom-menu-item');
    $(menuId).addClass('custom-menu-item');
}