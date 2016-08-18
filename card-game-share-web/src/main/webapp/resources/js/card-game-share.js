function selecionarMenu(menuId) {
    $('.menuItemWantList').removeClass('custom-menu-item');
    $('.menuItemSellList').removeClass('custom-menu-item');
    $('.menuItemMyCards').removeClass('custom-menu-item');
    $(menuId).addClass('custom-menu-item');
}