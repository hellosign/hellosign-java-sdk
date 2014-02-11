function initEmbeddedDemo() {
    // This is to fix an issue on chrome related to background images not loading.
    // We manually force all of them to load by resetting the background attribute to its own value.
    // Not sure why this is even happening but this fixes it....

    var ua = navigator.userAgent.toLowerCase();
    var isChrome = /chrome/.test(ua);
    var isSafari = !isChrome && /safari/.test(ua);

    if (isChrome || isSafari) {

        var isWindows = /windows/.test(ua);
        if (isChrome && isWindows) {
            // For font fixes on chrome on windows
            $('body').addClass('chrome-win-fix');
        }
        else if (isWindows) {
            $('body').addClass('win');
        }

        // Special case for the html tag
        var html = $('html');
        var b = html.css('background-image');
        if (b && b != "none") {
            html.attr('style', 'background-image: ' + b + ' !important;');
        }

        var selectors = [
            'li.nav_send a',
            'li.nav_request a',
            'li.nav_manage a',
            'li.nav_office a',
            'li.nav_create_template a',
            'li.nav_getting_started a',
            '#sitenav li .icon',
            '.btn.r',
            '.btn.l',
            '.btn.c',
            '.doc-actions .arrow',
            '.team_list .arrow',
            '#header h1 a'
        ];
        $(selectors.join(", "))
            .each(function(i, v) {
                var el = $(v);
                el.css('background', el.css('background'));
            });
    }
    else if (/msie/.test(ua)) {
        var version = (/msie 10/.test(ua) ? 10 : (/msie 9/.test(ua) ? 9 : (/msie 8/.test(ua) ? 8 : (/msie 7/.test(ua) ? 7 : undefined))));
        if (version) {
            $('body').addClass('ie' + version);
        }
    }
    prettyPrint();
}