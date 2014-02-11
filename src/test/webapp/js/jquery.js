/***********************************
    jquery.js
***********************************/

/*!
 * jQuery JavaScript Library v1.4.4
 * http://jquery.com/
 *
 * Copyright 2010, John Resig
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * Includes Sizzle.js
 * http://sizzlejs.com/
 * Copyright 2010, The Dojo Foundation
 * Released under the MIT, BSD, and GPL Licenses.
 *
 * Date: Thu Nov 11 19:04:53 2010 -0500
 */
(function( window, undefined ) {

// Use the correct document accordingly with window argument (sandbox)
var document = window.document;
var jQuery = (function() {

// Define a local copy of jQuery
var jQuery = function( selector, context ) {
        // The jQuery object is actually just the init constructor 'enhanced'
        return new jQuery.fn.init( selector, context );
    },

    // Map over jQuery in case of overwrite
    _jQuery = window.jQuery,

    // Map over the $ in case of overwrite
    _$ = window.$,

    // A central reference to the root jQuery(document)
    rootjQuery,

    // A simple way to check for HTML strings or ID strings
    // (both of which we optimize for)
    quickExpr = /^(?:[^<]*(<[\w\W]+>)[^>]*$|#([\w\-]+)$)/,

    // Is it a simple selector
    isSimple = /^.[^:#\[\.,]*$/,

    // Check if a string has a non-whitespace character in it
    rnotwhite = /\S/,
    rwhite = /\s/,

    // Used for trimming whitespace
    trimLeft = /^\s+/,
    trimRight = /\s+$/,

    // Check for non-word characters
    rnonword = /\W/,

    // Check for digits
    rdigit = /\d/,

    // Match a standalone tag
    rsingleTag = /^<(\w+)\s*\/?>(?:<\/\1>)?$/,

    // JSON RegExp
    rvalidchars = /^[\],:{}\s]*$/,
    rvalidescape = /\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g,
    rvalidtokens = /"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
    rvalidbraces = /(?:^|:|,)(?:\s*\[)+/g,

    // Useragent RegExp
    rwebkit = /(webkit)[ \/]([\w.]+)/,
    ropera = /(opera)(?:.*version)?[ \/]([\w.]+)/,
    rmsie = /(msie) ([\w.]+)/,
    rmozilla = /(mozilla)(?:.*? rv:([\w.]+))?/,

    // Keep a UserAgent string for use with jQuery.browser
    userAgent = navigator.userAgent,

    // For matching the engine and version of the browser
    browserMatch,

    // Has the ready events already been bound?
    readyBound = false,

    // The functions to execute on DOM ready
    readyList = [],

    // The ready event handler
    DOMContentLoaded,

    // Save a reference to some core methods
    toString = Object.prototype.toString,
    hasOwn = Object.prototype.hasOwnProperty,
    push = Array.prototype.push,
    slice = Array.prototype.slice,
    trim = String.prototype.trim,
    indexOf = Array.prototype.indexOf,

    // [[Class]] -> type pairs
    class2type = {};

jQuery.fn = jQuery.prototype = {
    init: function( selector, context ) {
        var match, elem, ret, doc;

        // Handle $(""), $(null), or $(undefined)
        if ( !selector ) {
            return this;
        }

        // Handle $(DOMElement)
        if ( selector.nodeType ) {
            this.context = this[0] = selector;
            this.length = 1;
            return this;
        }

        // The body element only exists once, optimize finding it
        if ( selector === "body" && !context && document.body ) {
            this.context = document;
            this[0] = document.body;
            this.selector = "body";
            this.length = 1;
            return this;
        }

        // Handle HTML strings
        if ( typeof selector === "string" ) {
            // Are we dealing with HTML string or an ID?
            match = quickExpr.exec( selector );

            // Verify a match, and that no context was specified for #id
            if ( match && (match[1] || !context) ) {

                // HANDLE: $(html) -> $(array)
                if ( match[1] ) {
                    doc = (context ? context.ownerDocument || context : document);

                    // If a single string is passed in and it's a single tag
                    // just do a createElement and skip the rest
                    ret = rsingleTag.exec( selector );

                    if ( ret ) {
                        if ( jQuery.isPlainObject( context ) ) {
                            selector = [ document.createElement( ret[1] ) ];
                            jQuery.fn.attr.call( selector, context, true );

                        } else {
                            selector = [ doc.createElement( ret[1] ) ];
                        }

                    } else {
                        ret = jQuery.buildFragment( [ match[1] ], [ doc ] );
                        selector = (ret.cacheable ? ret.fragment.cloneNode(true) : ret.fragment).childNodes;
                    }

                    return jQuery.merge( this, selector );

                // HANDLE: $("#id")
                } else {
                    elem = document.getElementById( match[2] );

                    // Check parentNode to catch when Blackberry 4.6 returns
                    // nodes that are no longer in the document #6963
                    if ( elem && elem.parentNode ) {
                        // Handle the case where IE and Opera return items
                        // by name instead of ID
                        if ( elem.id !== match[2] ) {
                            return rootjQuery.find( selector );
                        }

                        // Otherwise, we inject the element directly into the jQuery object
                        this.length = 1;
                        this[0] = elem;
                    }

                    this.context = document;
                    this.selector = selector;
                    return this;
                }

            // HANDLE: $("TAG")
            } else if ( !context && !rnonword.test( selector ) ) {
                this.selector = selector;
                this.context = document;
                selector = document.getElementsByTagName( selector );
                return jQuery.merge( this, selector );

            // HANDLE: $(expr, $(...))
            } else if ( !context || context.jquery ) {
                return (context || rootjQuery).find( selector );

            // HANDLE: $(expr, context)
            // (which is just equivalent to: $(context).find(expr)
            } else {
                return jQuery( context ).find( selector );
            }

        // HANDLE: $(function)
        // Shortcut for document ready
        } else if ( jQuery.isFunction( selector ) ) {
            return rootjQuery.ready( selector );
        }

        if (selector.selector !== undefined) {
            this.selector = selector.selector;
            this.context = selector.context;
        }

        return jQuery.makeArray( selector, this );
    },

    // Start with an empty selector
    selector: "",

    // The current version of jQuery being used
    jquery: "1.4.4",

    // The default length of a jQuery object is 0
    length: 0,

    // The number of elements contained in the matched element set
    size: function() {
        return this.length;
    },

    toArray: function() {
        return slice.call( this, 0 );
    },

    // Get the Nth element in the matched element set OR
    // Get the whole matched element set as a clean array
    get: function( num ) {
        return num == null ?

            // Return a 'clean' array
            this.toArray() :

            // Return just the object
            ( num < 0 ? this.slice(num)[ 0 ] : this[ num ] );
    },

    // Take an array of elements and push it onto the stack
    // (returning the new matched element set)
    pushStack: function( elems, name, selector ) {
        // Build a new jQuery matched element set
        var ret = jQuery();

        if ( jQuery.isArray( elems ) ) {
            push.apply( ret, elems );

        } else {
            jQuery.merge( ret, elems );
        }

        // Add the old object onto the stack (as a reference)
        ret.prevObject = this;

        ret.context = this.context;

        if ( name === "find" ) {
            ret.selector = this.selector + (this.selector ? " " : "") + selector;
        } else if ( name ) {
            ret.selector = this.selector + "." + name + "(" + selector + ")";
        }

        // Return the newly-formed element set
        return ret;
    },

    // Execute a callback for every element in the matched set.
    // (You can seed the arguments with an array of args, but this is
    // only used internally.)
    each: function( callback, args ) {
        return jQuery.each( this, callback, args );
    },

    ready: function( fn ) {
        // Attach the listeners
        jQuery.bindReady();

        // If the DOM is already ready
        if ( jQuery.isReady ) {
            // Execute the function immediately
            fn.call( document, jQuery );

        // Otherwise, remember the function for later
        } else if ( readyList ) {
            // Add the function to the wait list
            readyList.push( fn );
        }

        return this;
    },

    eq: function( i ) {
        return i === -1 ?
            this.slice( i ) :
            this.slice( i, +i + 1 );
    },

    first: function() {
        return this.eq( 0 );
    },

    last: function() {
        return this.eq( -1 );
    },

    slice: function() {
        return this.pushStack( slice.apply( this, arguments ),
            "slice", slice.call(arguments).join(",") );
    },

    map: function( callback ) {
        return this.pushStack( jQuery.map(this, function( elem, i ) {
            return callback.call( elem, i, elem );
        }));
    },

    end: function() {
        return this.prevObject || jQuery(null);
    },

    // For internal use only.
    // Behaves like an Array's method, not like a jQuery method.
    push: push,
    sort: [].sort,
    splice: [].splice
};

// Give the init function the jQuery prototype for later instantiation
jQuery.fn.init.prototype = jQuery.fn;

jQuery.extend = jQuery.fn.extend = function() {
     var options, name, src, copy, copyIsArray, clone,
        target = arguments[0] || {},
        i = 1,
        length = arguments.length,
        deep = false;

    // Handle a deep copy situation
    if ( typeof target === "boolean" ) {
        deep = target;
        target = arguments[1] || {};
        // skip the boolean and the target
        i = 2;
    }

    // Handle case when target is a string or something (possible in deep copy)
    if ( typeof target !== "object" && !jQuery.isFunction(target) ) {
        target = {};
    }

    // extend jQuery itself if only one argument is passed
    if ( length === i ) {
        target = this;
        --i;
    }

    for ( ; i < length; i++ ) {
        // Only deal with non-null/undefined values
        if ( (options = arguments[ i ]) != null ) {
            // Extend the base object
            for ( name in options ) {
                src = target[ name ];
                copy = options[ name ];

                // Prevent never-ending loop
                if ( target === copy ) {
                    continue;
                }

                // Recurse if we're merging plain objects or arrays
                if ( deep && copy && ( jQuery.isPlainObject(copy) || (copyIsArray = jQuery.isArray(copy)) ) ) {
                    if ( copyIsArray ) {
                        copyIsArray = false;
                        clone = src && jQuery.isArray(src) ? src : [];

                    } else {
                        clone = src && jQuery.isPlainObject(src) ? src : {};
                    }

                    // Never move original objects, clone them
                    target[ name ] = jQuery.extend( deep, clone, copy );

                // Don't bring in undefined values
                } else if ( copy !== undefined ) {
                    target[ name ] = copy;
                }
            }
        }
    }

    // Return the modified object
    return target;
};

jQuery.extend({
    noConflict: function( deep ) {
        window.$ = _$;

        if ( deep ) {
            window.jQuery = _jQuery;
        }

        return jQuery;
    },

    // Is the DOM ready to be used? Set to true once it occurs.
    isReady: false,

    // A counter to track how many items to wait for before
    // the ready event fires. See #6781
    readyWait: 1,

    // Handle when the DOM is ready
    ready: function( wait ) {
        // A third-party is pushing the ready event forwards
        if ( wait === true ) {
            jQuery.readyWait--;
        }

        // Make sure that the DOM is not already loaded
        if ( !jQuery.readyWait || (wait !== true && !jQuery.isReady) ) {
            // Make sure body exists, at least, in case IE gets a little overzealous (ticket #5443).
            if ( !document.body ) {
                return setTimeout( jQuery.ready, 1 );
            }

            // Remember that the DOM is ready
            jQuery.isReady = true;

            // If a normal DOM Ready event fired, decrement, and wait if need be
            if ( wait !== true && --jQuery.readyWait > 0 ) {
                return;
            }

            // If there are functions bound, to execute
            if ( readyList ) {
                // Execute all of them
                var fn,
                    i = 0,
                    ready = readyList;

                // Reset the list of functions
                readyList = null;

                while ( (fn = ready[ i++ ]) ) {
                    fn.call( document, jQuery );
                }

                // Trigger any bound ready events
                if ( jQuery.fn.trigger ) {
                    jQuery( document ).trigger( "ready" ).unbind( "ready" );
                }
            }
        }
    },

    bindReady: function() {
        if ( readyBound ) {
            return;
        }

        readyBound = true;

        // Catch cases where $(document).ready() is called after the
        // browser event has already occurred.
        if ( document.readyState === "complete" ) {
            // Handle it asynchronously to allow scripts the opportunity to delay ready
            return setTimeout( jQuery.ready, 1 );
        }

        // Mozilla, Opera and webkit nightlies currently support this event
        if ( document.addEventListener ) {
            // Use the handy event callback
            document.addEventListener( "DOMContentLoaded", DOMContentLoaded, false );

            // A fallback to window.onload, that will always work
            window.addEventListener( "load", jQuery.ready, false );

        // If IE event model is used
        } else if ( document.attachEvent ) {
            // ensure firing before onload,
            // maybe late but safe also for iframes
            document.attachEvent("onreadystatechange", DOMContentLoaded);

            // A fallback to window.onload, that will always work
            window.attachEvent( "onload", jQuery.ready );

            // If IE and not a frame
            // continually check to see if the document is ready
            var toplevel = false;

            try {
                toplevel = window.frameElement == null;
            } catch(e) {}

            if ( document.documentElement.doScroll && toplevel ) {
                doScrollCheck();
            }
        }
    },

    // See test/unit/core.js for details concerning isFunction.
    // Since version 1.3, DOM methods and functions like alert
    // aren't supported. They return false on IE (#2968).
    isFunction: function( obj ) {
        return jQuery.type(obj) === "function";
    },

    isArray: Array.isArray || function( obj ) {
        return jQuery.type(obj) === "array";
    },

    // A crude way of determining if an object is a window
    isWindow: function( obj ) {
        return obj && typeof obj === "object" && "setInterval" in obj;
    },

    isNaN: function( obj ) {
        return obj == null || !rdigit.test( obj ) || isNaN( obj );
    },

    type: function( obj ) {
        return obj == null ?
            String( obj ) :
            class2type[ toString.call(obj) ] || "object";
    },

    isPlainObject: function( obj ) {
        // Must be an Object.
        // Because of IE, we also have to check the presence of the constructor property.
        // Make sure that DOM nodes and window objects don't pass through, as well
        if ( !obj || jQuery.type(obj) !== "object" || obj.nodeType || jQuery.isWindow( obj ) ) {
            return false;
        }

        // Not own constructor property must be Object
        if ( obj.constructor &&
            !hasOwn.call(obj, "constructor") &&
            !hasOwn.call(obj.constructor.prototype, "isPrototypeOf") ) {
            return false;
        }

        // Own properties are enumerated firstly, so to speed up,
        // if last one is own, then all properties are own.

        var key;
        for ( key in obj ) {}

        return key === undefined || hasOwn.call( obj, key );
    },

    isEmptyObject: function( obj ) {
        for ( var name in obj ) {
            return false;
        }
        return true;
    },

    error: function( msg ) {
        throw msg;
    },

    parseJSON: function( data ) {
        if ( typeof data !== "string" || !data ) {
            return null;
        }

        // Make sure leading/trailing whitespace is removed (IE can't handle it)
        data = jQuery.trim( data );

        // Make sure the incoming data is actual JSON
        // Logic borrowed from http://json.org/json2.js
        if ( rvalidchars.test(data.replace(rvalidescape, "@")
            .replace(rvalidtokens, "]")
            .replace(rvalidbraces, "")) ) {

            // Try to use the native JSON parser first
            return window.JSON && window.JSON.parse ?
                window.JSON.parse( data ) :
                (new Function("return " + data))();

        } else {
            jQuery.error( "Invalid JSON: " + data );
        }
    },

    noop: function() {},

    // Evalulates a script in a global context
    globalEval: function( data ) {
        if ( data && rnotwhite.test(data) ) {
            // Inspired by code by Andrea Giammarchi
            // http://webreflection.blogspot.com/2007/08/global-scope-evaluation-and-dom.html
            var head = document.getElementsByTagName("head")[0] || document.documentElement,
                script = document.createElement("script");

            script.type = "text/javascript";

            if ( jQuery.support.scriptEval ) {
                script.appendChild( document.createTextNode( data ) );
            } else {
                script.text = data;
            }

            // Use insertBefore instead of appendChild to circumvent an IE6 bug.
            // This arises when a base node is used (#2709).
            head.insertBefore( script, head.firstChild );
            head.removeChild( script );
        }
    },

    nodeName: function( elem, name ) {
        return elem.nodeName && elem.nodeName.toUpperCase() === name.toUpperCase();
    },

    // args is for internal usage only
    each: function( object, callback, args ) {
        var name, i = 0,
            length = object.length,
            isObj = length === undefined || jQuery.isFunction(object);

        if ( args ) {
            if ( isObj ) {
                for ( name in object ) {
                    if ( callback.apply( object[ name ], args ) === false ) {
                        break;
                    }
                }
            } else {
                for ( ; i < length; ) {
                    if ( callback.apply( object[ i++ ], args ) === false ) {
                        break;
                    }
                }
            }

        // A special, fast, case for the most common use of each
        } else {
            if ( isObj ) {
                for ( name in object ) {
                    if ( callback.call( object[ name ], name, object[ name ] ) === false ) {
                        break;
                    }
                }
            } else {
                for ( var value = object[0];
                    i < length && callback.call( value, i, value ) !== false; value = object[++i] ) {}
            }
        }

        return object;
    },

    // Use native String.trim function wherever possible
    trim: trim ?
        function( text ) {
            return text == null ?
                "" :
                trim.call( text );
        } :

        // Otherwise use our own trimming functionality
        function( text ) {
            return text == null ?
                "" :
                text.toString().replace( trimLeft, "" ).replace( trimRight, "" );
        },

    // results is for internal usage only
    makeArray: function( array, results ) {
        var ret = results || [];

        if ( array != null ) {
            // The window, strings (and functions) also have 'length'
            // The extra typeof function check is to prevent crashes
            // in Safari 2 (See: #3039)
            // Tweaked logic slightly to handle Blackberry 4.7 RegExp issues #6930
            var type = jQuery.type(array);

            if ( array.length == null || type === "string" || type === "function" || type === "regexp" || jQuery.isWindow( array ) ) {
                push.call( ret, array );
            } else {
                jQuery.merge( ret, array );
            }
        }

        return ret;
    },

    inArray: function( elem, array ) {
        if ( array.indexOf ) {
            return array.indexOf( elem );
        }

        for ( var i = 0, length = array.length; i < length; i++ ) {
            if ( array[ i ] === elem ) {
                return i;
            }
        }

        return -1;
    },

    merge: function( first, second ) {
        var i = first.length,
            j = 0;

        if ( typeof second.length === "number" ) {
            for ( var l = second.length; j < l; j++ ) {
                first[ i++ ] = second[ j ];
            }

        } else {
            while ( second[j] !== undefined ) {
                first[ i++ ] = second[ j++ ];
            }
        }

        first.length = i;

        return first;
    },

    grep: function( elems, callback, inv ) {
        var ret = [], retVal;
        inv = !!inv;

        // Go through the array, only saving the items
        // that pass the validator function
        for ( var i = 0, length = elems.length; i < length; i++ ) {
            retVal = !!callback( elems[ i ], i );
            if ( inv !== retVal ) {
                ret.push( elems[ i ] );
            }
        }

        return ret;
    },

    // arg is for internal usage only
    map: function( elems, callback, arg ) {
        var ret = [], value;

        // Go through the array, translating each of the items to their
        // new value (or values).
        for ( var i = 0, length = elems.length; i < length; i++ ) {
            value = callback( elems[ i ], i, arg );

            if ( value != null ) {
                ret[ ret.length ] = value;
            }
        }

        return ret.concat.apply( [], ret );
    },

    // A global GUID counter for objects
    guid: 1,

    proxy: function( fn, proxy, thisObject ) {
        if ( arguments.length === 2 ) {
            if ( typeof proxy === "string" ) {
                thisObject = fn;
                fn = thisObject[ proxy ];
                proxy = undefined;

            } else if ( proxy && !jQuery.isFunction( proxy ) ) {
                thisObject = proxy;
                proxy = undefined;
            }
        }

        if ( !proxy && fn ) {
            proxy = function() {
                return fn.apply( thisObject || this, arguments );
            };
        }

        // Set the guid of unique handler to the same of original handler, so it can be removed
        if ( fn ) {
            proxy.guid = fn.guid = fn.guid || proxy.guid || jQuery.guid++;
        }

        // So proxy can be declared as an argument
        return proxy;
    },

    // Mutifunctional method to get and set values to a collection
    // The value/s can be optionally by executed if its a function
    access: function( elems, key, value, exec, fn, pass ) {
        var length = elems.length;

        // Setting many attributes
        if ( typeof key === "object" ) {
            for ( var k in key ) {
                jQuery.access( elems, k, key[k], exec, fn, value );
            }
            return elems;
        }

        // Setting one attribute
        if ( value !== undefined ) {
            // Optionally, function values get executed if exec is true
            exec = !pass && exec && jQuery.isFunction(value);

            for ( var i = 0; i < length; i++ ) {
                fn( elems[i], key, exec ? value.call( elems[i], i, fn( elems[i], key ) ) : value, pass );
            }

            return elems;
        }

        // Getting an attribute
        return length ? fn( elems[0], key ) : undefined;
    },

    now: function() {
        return (new Date()).getTime();
    },

    // Use of jQuery.browser is frowned upon.
    // More details: http://docs.jquery.com/Utilities/jQuery.browser
    uaMatch: function( ua ) {
        ua = ua.toLowerCase();

        var match = rwebkit.exec( ua ) ||
            ropera.exec( ua ) ||
            rmsie.exec( ua ) ||
            ua.indexOf("compatible") < 0 && rmozilla.exec( ua ) ||
            [];

        return { browser: match[1] || "", version: match[2] || "0" };
    },

    browser: {}
});

// Populate the class2type map
jQuery.each("Boolean Number String Function Array Date RegExp Object".split(" "), function(i, name) {
    class2type[ "[object " + name + "]" ] = name.toLowerCase();
});

browserMatch = jQuery.uaMatch( userAgent );
if ( browserMatch.browser ) {
    jQuery.browser[ browserMatch.browser ] = true;
    jQuery.browser.version = browserMatch.version;
}

// Deprecated, use jQuery.browser.webkit instead
if ( jQuery.browser.webkit ) {
    jQuery.browser.safari = true;
}

if ( indexOf ) {
    jQuery.inArray = function( elem, array ) {
        return indexOf.call( array, elem );
    };
}

// Verify that \s matches non-breaking spaces
// (IE fails on this test)
if ( !rwhite.test( "\xA0" ) ) {
    trimLeft = /^[\s\xA0]+/;
    trimRight = /[\s\xA0]+$/;
}

// All jQuery objects should point back to these
rootjQuery = jQuery(document);

// Cleanup functions for the document ready method
if ( document.addEventListener ) {
    DOMContentLoaded = function() {
        document.removeEventListener( "DOMContentLoaded", DOMContentLoaded, false );
        jQuery.ready();
    };

} else if ( document.attachEvent ) {
    DOMContentLoaded = function() {
        // Make sure body exists, at least, in case IE gets a little overzealous (ticket #5443).
        if ( document.readyState === "complete" ) {
            document.detachEvent( "onreadystatechange", DOMContentLoaded );
            jQuery.ready();
        }
    };
}

// The DOM ready check for Internet Explorer
function doScrollCheck() {
    if ( jQuery.isReady ) {
        return;
    }

    try {
        // If IE is used, use the trick by Diego Perini
        // http://javascript.nwbox.com/IEContentLoaded/
        document.documentElement.doScroll("left");
    } catch(e) {
        setTimeout( doScrollCheck, 1 );
        return;
    }

    // and execute any waiting functions
    jQuery.ready();
}

// Expose jQuery to the global object
return (window.jQuery = window.$ = jQuery);

})();


(function() {

    jQuery.support = {};

    var root = document.documentElement,
        script = document.createElement("script"),
        div = document.createElement("div"),
        id = "script" + jQuery.now();

    div.style.display = "none";
    div.innerHTML = "   <link/><table></table><a href='/a' style='color:red;float:left;opacity:.55;'>a</a><input type='checkbox'/>";

    var all = div.getElementsByTagName("*"),
        a = div.getElementsByTagName("a")[0],
        select = document.createElement("select"),
        opt = select.appendChild( document.createElement("option") );

    // Can't get basic test support
    if ( !all || !all.length || !a ) {
        return;
    }

    jQuery.support = {
        // IE strips leading whitespace when .innerHTML is used
        leadingWhitespace: div.firstChild.nodeType === 3,

        // Make sure that tbody elements aren't automatically inserted
        // IE will insert them into empty tables
        tbody: !div.getElementsByTagName("tbody").length,

        // Make sure that link elements get serialized correctly by innerHTML
        // This requires a wrapper element in IE
        htmlSerialize: !!div.getElementsByTagName("link").length,

        // Get the style information from getAttribute
        // (IE uses .cssText insted)
        style: /red/.test( a.getAttribute("style") ),

        // Make sure that URLs aren't manipulated
        // (IE normalizes it by default)
        hrefNormalized: a.getAttribute("href") === "/a",

        // Make sure that element opacity exists
        // (IE uses filter instead)
        // Use a regex to work around a WebKit issue. See #5145
        opacity: /^0.55$/.test( a.style.opacity ),

        // Verify style float existence
        // (IE uses styleFloat instead of cssFloat)
        cssFloat: !!a.style.cssFloat,

        // Make sure that if no value is specified for a checkbox
        // that it defaults to "on".
        // (WebKit defaults to "" instead)
        checkOn: div.getElementsByTagName("input")[0].value === "on",

        // Make sure that a selected-by-default option has a working selected property.
        // (WebKit defaults to false instead of true, IE too, if it's in an optgroup)
        optSelected: opt.selected,

        // Will be defined later
        deleteExpando: true,
        optDisabled: false,
        checkClone: false,
        scriptEval: false,
        noCloneEvent: true,
        boxModel: null,
        inlineBlockNeedsLayout: false,
        shrinkWrapBlocks: false,
        reliableHiddenOffsets: true
    };

    // Make sure that the options inside disabled selects aren't marked as disabled
    // (WebKit marks them as diabled)
    select.disabled = true;
    jQuery.support.optDisabled = !opt.disabled;

    script.type = "text/javascript";
    try {
        script.appendChild( document.createTextNode( "window." + id + "=1;" ) );
    } catch(e) {}

    root.insertBefore( script, root.firstChild );

    // Make sure that the execution of code works by injecting a script
    // tag with appendChild/createTextNode
    // (IE doesn't support this, fails, and uses .text instead)
    if ( window[ id ] ) {
        jQuery.support.scriptEval = true;
        delete window[ id ];
    }

    // Test to see if it's possible to delete an expando from an element
    // Fails in Internet Explorer
    try {
        delete script.test;

    } catch(e) {
        jQuery.support.deleteExpando = false;
    }

    root.removeChild( script );

    if ( div.attachEvent && div.fireEvent ) {
        div.attachEvent("onclick", function click() {
            // Cloning a node shouldn't copy over any
            // bound event handlers (IE does this)
            jQuery.support.noCloneEvent = false;
            div.detachEvent("onclick", click);
        });
        div.cloneNode(true).fireEvent("onclick");
    }

    div = document.createElement("div");
    div.innerHTML = "<input type='radio' name='radiotest' checked='checked'/>";

    var fragment = document.createDocumentFragment();
    fragment.appendChild( div.firstChild );

    // WebKit doesn't clone checked state correctly in fragments
    jQuery.support.checkClone = fragment.cloneNode(true).cloneNode(true).lastChild.checked;

    // Figure out if the W3C box model works as expected
    // document.body must exist before we can do this
    jQuery(function() {
        var div = document.createElement("div");
        div.style.width = div.style.paddingLeft = "1px";

        document.body.appendChild( div );
        jQuery.boxModel = jQuery.support.boxModel = div.offsetWidth === 2;

        if ( "zoom" in div.style ) {
            // Check if natively block-level elements act like inline-block
            // elements when setting their display to 'inline' and giving
            // them layout
            // (IE < 8 does this)
            div.style.display = "inline";
            div.style.zoom = 1;
            jQuery.support.inlineBlockNeedsLayout = div.offsetWidth === 2;

            // Check if elements with layout shrink-wrap their children
            // (IE 6 does this)
            div.style.display = "";
            div.innerHTML = "<div style='width:4px;'></div>";
            jQuery.support.shrinkWrapBlocks = div.offsetWidth !== 2;
        }

        div.innerHTML = "<table><tr><td style='padding:0;display:none'></td><td>t</td></tr></table>";
        var tds = div.getElementsByTagName("td");

        // Check if table cells still have offsetWidth/Height when they are set
        // to display:none and there are still other visible table cells in a
        // table row; if so, offsetWidth/Height are not reliable for use when
        // determining if an element has been hidden directly using
        // display:none (it is still safe to use offsets if a parent element is
        // hidden; don safety goggles and see bug #4512 for more information).
        // (only IE 8 fails this test)
        jQuery.support.reliableHiddenOffsets = tds[0].offsetHeight === 0;

        tds[0].style.display = "";
        tds[1].style.display = "none";

        // Check if empty table cells still have offsetWidth/Height
        // (IE < 8 fail this test)
        jQuery.support.reliableHiddenOffsets = jQuery.support.reliableHiddenOffsets && tds[0].offsetHeight === 0;
        div.innerHTML = "";

        document.body.removeChild( div ).style.display = "none";
        div = tds = null;
    });

    // Technique from Juriy Zaytsev
    // http://thinkweb2.com/projects/prototype/detecting-event-support-without-browser-sniffing/
    var eventSupported = function( eventName ) {
        var el = document.createElement("div");
        eventName = "on" + eventName;

        var isSupported = (eventName in el);
        if ( !isSupported ) {
            el.setAttribute(eventName, "return;");
            isSupported = typeof el[eventName] === "function";
        }
        el = null;

        return isSupported;
    };

    jQuery.support.submitBubbles = eventSupported("submit");
    jQuery.support.changeBubbles = eventSupported("change");

    // release memory in IE
    root = script = div = all = a = null;
})();



var windowData = {},
    rbrace = /^(?:\{.*\}|\[.*\])$/;

jQuery.extend({
    cache: {},

    // Please use with caution
    uuid: 0,

    // Unique for each copy of jQuery on the page
    expando: "jQuery" + jQuery.now(),

    // The following elements throw uncatchable exceptions if you
    // attempt to add expando properties to them.
    noData: {
        "embed": true,
        // Ban all objects except for Flash (which handle expandos)
        "object": "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000",
        "applet": true
    },

    data: function( elem, name, data ) {
        if ( !jQuery.acceptData( elem ) ) {
            return;
        }

        elem = elem == window ?
            windowData :
            elem;

        var isNode = elem.nodeType,
            id = isNode ? elem[ jQuery.expando ] : null,
            cache = jQuery.cache, thisCache;

        if ( isNode && !id && typeof name === "string" && data === undefined ) {
            return;
        }

        // Get the data from the object directly
        if ( !isNode ) {
            cache = elem;

        // Compute a unique ID for the element
        } else if ( !id ) {
            elem[ jQuery.expando ] = id = ++jQuery.uuid;
        }

        // Avoid generating a new cache unless none exists and we
        // want to manipulate it.
        if ( typeof name === "object" ) {
            if ( isNode ) {
                cache[ id ] = jQuery.extend(cache[ id ], name);

            } else {
                jQuery.extend( cache, name );
            }

        } else if ( isNode && !cache[ id ] ) {
            cache[ id ] = {};
        }

        thisCache = isNode ? cache[ id ] : cache;

        // Prevent overriding the named cache with undefined values
        if ( data !== undefined ) {
            thisCache[ name ] = data;
        }

        return typeof name === "string" ? thisCache[ name ] : thisCache;
    },

    removeData: function( elem, name ) {
        if ( !jQuery.acceptData( elem ) ) {
            return;
        }

        elem = elem == window ?
            windowData :
            elem;

        var isNode = elem.nodeType,
            id = isNode ? elem[ jQuery.expando ] : elem,
            cache = jQuery.cache,
            thisCache = isNode ? cache[ id ] : id;

        // If we want to remove a specific section of the element's data
        if ( name ) {
            if ( thisCache ) {
                // Remove the section of cache data
                delete thisCache[ name ];

                // If we've removed all the data, remove the element's cache
                if ( isNode && jQuery.isEmptyObject(thisCache) ) {
                    jQuery.removeData( elem );
                }
            }

        // Otherwise, we want to remove all of the element's data
        } else {
            if ( isNode && jQuery.support.deleteExpando ) {
                delete elem[ jQuery.expando ];

            } else if ( elem.removeAttribute ) {
                elem.removeAttribute( jQuery.expando );

            // Completely remove the data cache
            } else if ( isNode ) {
                delete cache[ id ];

            // Remove all fields from the object
            } else {
                for ( var n in elem ) {
                    delete elem[ n ];
                }
            }
        }
    },

    // A method for determining if a DOM node can handle the data expando
    acceptData: function( elem ) {
        if ( elem.nodeName ) {
            var match = jQuery.noData[ elem.nodeName.toLowerCase() ];

            if ( match ) {
                return !(match === true || elem.getAttribute("classid") !== match);
            }
        }

        return true;
    }
});

jQuery.fn.extend({
    data: function( key, value ) {
        var data = null;

        if ( typeof key === "undefined" ) {
            if ( this.length ) {
                var attr = this[0].attributes, name;
                data = jQuery.data( this[0] );

                for ( var i = 0, l = attr.length; i < l; i++ ) {
                    name = attr[i].name;

                    if ( name.indexOf( "data-" ) === 0 ) {
                        name = name.substr( 5 );
                        dataAttr( this[0], name, data[ name ] );
                    }
                }
            }

            return data;

        } else if ( typeof key === "object" ) {
            return this.each(function() {
                jQuery.data( this, key );
            });
        }

        var parts = key.split(".");
        parts[1] = parts[1] ? "." + parts[1] : "";

        if ( value === undefined ) {
            data = this.triggerHandler("getData" + parts[1] + "!", [parts[0]]);

            // Try to fetch any internally stored data first
            if ( data === undefined && this.length ) {
                data = jQuery.data( this[0], key );
                data = dataAttr( this[0], key, data );
            }

            return data === undefined && parts[1] ?
                this.data( parts[0] ) :
                data;

        } else {
            return this.each(function() {
                var $this = jQuery( this ),
                    args = [ parts[0], value ];

                $this.triggerHandler( "setData" + parts[1] + "!", args );
                jQuery.data( this, key, value );
                $this.triggerHandler( "changeData" + parts[1] + "!", args );
            });
        }
    },

    removeData: function( key ) {
        return this.each(function() {
            jQuery.removeData( this, key );
        });
    }
});

function dataAttr( elem, key, data ) {
    // If nothing was found internally, try to fetch any
    // data from the HTML5 data-* attribute
    if ( data === undefined && elem.nodeType === 1 ) {
        data = elem.getAttribute( "data-" + key );

        if ( typeof data === "string" ) {
            try {
                data = data === "true" ? true :
                data === "false" ? false :
                data === "null" ? null :
                !jQuery.isNaN( data ) ? parseFloat( data ) :
                    rbrace.test( data ) ? jQuery.parseJSON( data ) :
                    data;
            } catch( e ) {}

            // Make sure we set the data so it isn't changed later
            jQuery.data( elem, key, data );

        } else {
            data = undefined;
        }
    }

    return data;
}




jQuery.extend({
    queue: function( elem, type, data ) {
        if ( !elem ) {
            return;
        }

        type = (type || "fx") + "queue";
        var q = jQuery.data( elem, type );

        // Speed up dequeue by getting out quickly if this is just a lookup
        if ( !data ) {
            return q || [];
        }

        if ( !q || jQuery.isArray(data) ) {
            q = jQuery.data( elem, type, jQuery.makeArray(data) );

        } else {
            q.push( data );
        }

        return q;
    },

    dequeue: function( elem, type ) {
        type = type || "fx";

        var queue = jQuery.queue( elem, type ),
            fn = queue.shift();

        // If the fx queue is dequeued, always remove the progress sentinel
        if ( fn === "inprogress" ) {
            fn = queue.shift();
        }

        if ( fn ) {
            // Add a progress sentinel to prevent the fx queue from being
            // automatically dequeued
            if ( type === "fx" ) {
                queue.unshift("inprogress");
            }

            fn.call(elem, function() {
                jQuery.dequeue(elem, type);
            });
        }
    }
});

jQuery.fn.extend({
    queue: function( type, data ) {
        if ( typeof type !== "string" ) {
            data = type;
            type = "fx";
        }

        if ( data === undefined ) {
            return jQuery.queue( this[0], type );
        }
        return this.each(function( i ) {
            var queue = jQuery.queue( this, type, data );

            if ( type === "fx" && queue[0] !== "inprogress" ) {
                jQuery.dequeue( this, type );
            }
        });
    },
    dequeue: function( type ) {
        return this.each(function() {
            jQuery.dequeue( this, type );
        });
    },

    // Based off of the plugin by Clint Helfers, with permission.
    // http://blindsignals.com/index.php/2009/07/jquery-delay/
    delay: function( time, type ) {
        time = jQuery.fx ? jQuery.fx.speeds[time] || time : time;
        type = type || "fx";

        return this.queue( type, function() {
            var elem = this;
            setTimeout(function() {
                jQuery.dequeue( elem, type );
            }, time );
        });
    },

    clearQueue: function( type ) {
        return this.queue( type || "fx", [] );
    }
});




var rclass = /[\n\t]/g,
    rspaces = /\s+/,
    rreturn = /\r/g,
    rspecialurl = /^(?:href|src|style)$/,
    rtype = /^(?:button|input)$/i,
    rfocusable = /^(?:button|input|object|select|textarea)$/i,
    rclickable = /^a(?:rea)?$/i,
    rradiocheck = /^(?:radio|checkbox)$/i;

jQuery.props = {
    "for": "htmlFor",
    "class": "className",
    readonly: "readOnly",
    maxlength: "maxLength",
    cellspacing: "cellSpacing",
    rowspan: "rowSpan",
    colspan: "colSpan",
    tabindex: "tabIndex",
    usemap: "useMap",
    frameborder: "frameBorder"
};

jQuery.fn.extend({
    attr: function( name, value ) {
        return jQuery.access( this, name, value, true, jQuery.attr );
    },

    removeAttr: function( name, fn ) {
        return this.each(function(){
            jQuery.attr( this, name, "" );
            if ( this.nodeType === 1 ) {
                this.removeAttribute( name );
            }
        });
    },

    addClass: function( value ) {
        if ( jQuery.isFunction(value) ) {
            return this.each(function(i) {
                var self = jQuery(this);
                self.addClass( value.call(this, i, self.attr("class")) );
            });
        }

        if ( value && typeof value === "string" ) {
            var classNames = (value || "").split( rspaces );

            for ( var i = 0, l = this.length; i < l; i++ ) {
                var elem = this[i];

                if ( elem.nodeType === 1 ) {
                    if ( !elem.className ) {
                        elem.className = value;

                    } else {
                        var className = " " + elem.className + " ",
                            setClass = elem.className;

                        for ( var c = 0, cl = classNames.length; c < cl; c++ ) {
                            if ( className.indexOf( " " + classNames[c] + " " ) < 0 ) {
                                setClass += " " + classNames[c];
                            }
                        }
                        elem.className = jQuery.trim( setClass );
                    }
                }
            }
        }

        return this;
    },

    removeClass: function( value ) {
        if ( jQuery.isFunction(value) ) {
            return this.each(function(i) {
                var self = jQuery(this);
                self.removeClass( value.call(this, i, self.attr("class")) );
            });
        }

        if ( (value && typeof value === "string") || value === undefined ) {
            var classNames = (value || "").split( rspaces );

            for ( var i = 0, l = this.length; i < l; i++ ) {
                var elem = this[i];

                if ( elem.nodeType === 1 && elem.className ) {
                    if ( value ) {
                        var className = (" " + elem.className + " ").replace(rclass, " ");
                        for ( var c = 0, cl = classNames.length; c < cl; c++ ) {
                            className = className.replace(" " + classNames[c] + " ", " ");
                        }
                        elem.className = jQuery.trim( className );

                    } else {
                        elem.className = "";
                    }
                }
            }
        }

        return this;
    },

    toggleClass: function( value, stateVal ) {
        var type = typeof value,
            isBool = typeof stateVal === "boolean";

        if ( jQuery.isFunction( value ) ) {
            return this.each(function(i) {
                var self = jQuery(this);
                self.toggleClass( value.call(this, i, self.attr("class"), stateVal), stateVal );
            });
        }

        return this.each(function() {
            if ( type === "string" ) {
                // toggle individual class names
                var className,
                    i = 0,
                    self = jQuery( this ),
                    state = stateVal,
                    classNames = value.split( rspaces );

                while ( (className = classNames[ i++ ]) ) {
                    // check each className given, space seperated list
                    state = isBool ? state : !self.hasClass( className );
                    self[ state ? "addClass" : "removeClass" ]( className );
                }

            } else if ( type === "undefined" || type === "boolean" ) {
                if ( this.className ) {
                    // store className if set
                    jQuery.data( this, "__className__", this.className );
                }

                // toggle whole className
                this.className = this.className || value === false ? "" : jQuery.data( this, "__className__" ) || "";
            }
        });
    },

    hasClass: function( selector ) {
        var className = " " + selector + " ";
        for ( var i = 0, l = this.length; i < l; i++ ) {
            if ( (" " + this[i].className + " ").replace(rclass, " ").indexOf( className ) > -1 ) {
                return true;
            }
        }

        return false;
    },

    val: function( value ) {
        if ( !arguments.length ) {
            var elem = this[0];

            if ( elem ) {
                if ( jQuery.nodeName( elem, "option" ) ) {
                    // attributes.value is undefined in Blackberry 4.7 but
                    // uses .value. See #6932
                    var val = elem.attributes.value;
                    return !val || val.specified ? elem.value : elem.text;
                }

                // We need to handle select boxes special
                if ( jQuery.nodeName( elem, "select" ) ) {
                    var index = elem.selectedIndex,
                        values = [],
                        options = elem.options,
                        one = elem.type === "select-one";

                    // Nothing was selected
                    if ( index < 0 ) {
                        return null;
                    }

                    // Loop through all the selected options
                    for ( var i = one ? index : 0, max = one ? index + 1 : options.length; i < max; i++ ) {
                        var option = options[ i ];

                        // Don't return options that are disabled or in a disabled optgroup
                        if ( option.selected && (jQuery.support.optDisabled ? !option.disabled : option.getAttribute("disabled") === null) &&
                                (!option.parentNode.disabled || !jQuery.nodeName( option.parentNode, "optgroup" )) ) {

                            // Get the specific value for the option
                            value = jQuery(option).val();

                            // We don't need an array for one selects
                            if ( one ) {
                                return value;
                            }

                            // Multi-Selects return an array
                            values.push( value );
                        }
                    }

                    return values;
                }

                // Handle the case where in Webkit "" is returned instead of "on" if a value isn't specified
                if ( rradiocheck.test( elem.type ) && !jQuery.support.checkOn ) {
                    return elem.getAttribute("value") === null ? "on" : elem.value;
                }


                // Everything else, we just grab the value
                return (elem.value || "").replace(rreturn, "");

            }

            return undefined;
        }

        var isFunction = jQuery.isFunction(value);

        return this.each(function(i) {
            var self = jQuery(this), val = value;

            if ( this.nodeType !== 1 ) {
                return;
            }

            if ( isFunction ) {
                val = value.call(this, i, self.val());
            }

            // Treat null/undefined as ""; convert numbers to string
            if ( val == null ) {
                val = "";
            } else if ( typeof val === "number" ) {
                val += "";
            } else if ( jQuery.isArray(val) ) {
                val = jQuery.map(val, function (value) {
                    return value == null ? "" : value + "";
                });
            }

            if ( jQuery.isArray(val) && rradiocheck.test( this.type ) ) {
                this.checked = jQuery.inArray( self.val(), val ) >= 0;

            } else if ( jQuery.nodeName( this, "select" ) ) {
                var values = jQuery.makeArray(val);

                jQuery( "option", this ).each(function() {
                    this.selected = jQuery.inArray( jQuery(this).val(), values ) >= 0;
                });

                if ( !values.length ) {
                    this.selectedIndex = -1;
                }

            } else {
                this.value = val;
            }
        });
    }
});

jQuery.extend({
    attrFn: {
        val: true,
        css: true,
        html: true,
        text: true,
        data: true,
        width: true,
        height: true,
        offset: true
    },

    attr: function( elem, name, value, pass ) {
        // don't set attributes on text and comment nodes
        if ( !elem || elem.nodeType === 3 || elem.nodeType === 8 ) {
            return undefined;
        }

        if ( pass && name in jQuery.attrFn ) {
            return jQuery(elem)[name](value);
        }

        var notxml = elem.nodeType !== 1 || !jQuery.isXMLDoc( elem ),
            // Whether we are setting (or getting)
            set = value !== undefined;

        // Try to normalize/fix the name
        name = notxml && jQuery.props[ name ] || name;

        // These attributes require special treatment
        var special = rspecialurl.test( name );

        // Safari mis-reports the default selected property of an option
        // Accessing the parent's selectedIndex property fixes it
        if ( name === "selected" && !jQuery.support.optSelected ) {
            var parent = elem.parentNode;
            if ( parent ) {
                parent.selectedIndex;

                // Make sure that it also works with optgroups, see #5701
                if ( parent.parentNode ) {
                    parent.parentNode.selectedIndex;
                }
            }
        }

        // If applicable, access the attribute via the DOM 0 way
        // 'in' checks fail in Blackberry 4.7 #6931
        if ( (name in elem || elem[ name ] !== undefined) && notxml && !special ) {
            if ( set ) {
                // We can't allow the type property to be changed (since it causes problems in IE)
                if ( name === "type" && rtype.test( elem.nodeName ) && elem.parentNode ) {
                    jQuery.error( "type property can't be changed" );
                }

                if ( value === null ) {
                    if ( elem.nodeType === 1 ) {
                        elem.removeAttribute( name );
                    }

                } else {
                    elem[ name ] = value;
                }
            }

            // browsers index elements by id/name on forms, give priority to attributes.
            if ( jQuery.nodeName( elem, "form" ) && elem.getAttributeNode(name) ) {
                return elem.getAttributeNode( name ).nodeValue;
            }

            // elem.tabIndex doesn't always return the correct value when it hasn't been explicitly set
            // http://fluidproject.org/blog/2008/01/09/getting-setting-and-removing-tabindex-values-with-javascript/
            if ( name === "tabIndex" ) {
                var attributeNode = elem.getAttributeNode( "tabIndex" );

                return attributeNode && attributeNode.specified ?
                    attributeNode.value :
                    rfocusable.test( elem.nodeName ) || rclickable.test( elem.nodeName ) && elem.href ?
                        0 :
                        undefined;
            }

            return elem[ name ];
        }

        if ( !jQuery.support.style && notxml && name === "style" ) {
            if ( set ) {
                elem.style.cssText = "" + value;
            }

            return elem.style.cssText;
        }

        if ( set ) {
            // convert the value to a string (all browsers do this but IE) see #1070
            elem.setAttribute( name, "" + value );
        }

        // Ensure that missing attributes return undefined
        // Blackberry 4.7 returns "" from getAttribute #6938
        if ( !elem.attributes[ name ] && (elem.hasAttribute && !elem.hasAttribute( name )) ) {
            return undefined;
        }

        var attr = !jQuery.support.hrefNormalized && notxml && special ?
                // Some attributes require a special call on IE
                elem.getAttribute( name, 2 ) :
                elem.getAttribute( name );

        // Non-existent attributes return null, we normalize to undefined
        return attr === null ? undefined : attr;
    }
});




var rnamespaces = /\.(.*)$/,
    rformElems = /^(?:textarea|input|select)$/i,
    rperiod = /\./g,
    rspace = / /g,
    rescape = /[^\w\s.|`]/g,
    fcleanup = function( nm ) {
        return nm.replace(rescape, "\\$&");
    },
    focusCounts = { focusin: 0, focusout: 0 };

/*
 * A number of helper functions used for managing events.
 * Many of the ideas behind this code originated from
 * Dean Edwards' addEvent library.
 */
jQuery.event = {

    // Bind an event to an element
    // Original by Dean Edwards
    add: function( elem, types, handler, data ) {
        if ( elem.nodeType === 3 || elem.nodeType === 8 ) {
            return;
        }

        // For whatever reason, IE has trouble passing the window object
        // around, causing it to be cloned in the process
        if ( jQuery.isWindow( elem ) && ( elem !== window && !elem.frameElement ) ) {
            elem = window;
        }

        if ( handler === false ) {
            handler = returnFalse;
        } else if ( !handler ) {
            // Fixes bug #7229. Fix recommended by jdalton
          return;
        }

        var handleObjIn, handleObj;

        if ( handler.handler ) {
            handleObjIn = handler;
            handler = handleObjIn.handler;
        }

        // Make sure that the function being executed has a unique ID
        if ( !handler.guid ) {
            handler.guid = jQuery.guid++;
        }

        // Init the element's event structure
        var elemData = jQuery.data( elem );

        // If no elemData is found then we must be trying to bind to one of the
        // banned noData elements
        if ( !elemData ) {
            return;
        }

        // Use a key less likely to result in collisions for plain JS objects.
        // Fixes bug #7150.
        var eventKey = elem.nodeType ? "events" : "__events__",
            events = elemData[ eventKey ],
            eventHandle = elemData.handle;

        if ( typeof events === "function" ) {
            // On plain objects events is a fn that holds the the data
            // which prevents this data from being JSON serialized
            // the function does not need to be called, it just contains the data
            eventHandle = events.handle;
            events = events.events;

        } else if ( !events ) {
            if ( !elem.nodeType ) {
                // On plain objects, create a fn that acts as the holder
                // of the values to avoid JSON serialization of event data
                elemData[ eventKey ] = elemData = function(){};
            }

            elemData.events = events = {};
        }

        if ( !eventHandle ) {
            elemData.handle = eventHandle = function() {
                // Handle the second event of a trigger and when
                // an event is called after a page has unloaded
                return typeof jQuery !== "undefined" && !jQuery.event.triggered ?
                    jQuery.event.handle.apply( eventHandle.elem, arguments ) :
                    undefined;
            };
        }

        // Add elem as a property of the handle function
        // This is to prevent a memory leak with non-native events in IE.
        eventHandle.elem = elem;

        // Handle multiple events separated by a space
        // jQuery(...).bind("mouseover mouseout", fn);
        types = types.split(" ");

        var type, i = 0, namespaces;

        while ( (type = types[ i++ ]) ) {
            handleObj = handleObjIn ?
                jQuery.extend({}, handleObjIn) :
                { handler: handler, data: data };

            // Namespaced event handlers
            if ( type.indexOf(".") > -1 ) {
                namespaces = type.split(".");
                type = namespaces.shift();
                handleObj.namespace = namespaces.slice(0).sort().join(".");

            } else {
                namespaces = [];
                handleObj.namespace = "";
            }

            handleObj.type = type;
            if ( !handleObj.guid ) {
                handleObj.guid = handler.guid;
            }

            // Get the current list of functions bound to this event
            var handlers = events[ type ],
                special = jQuery.event.special[ type ] || {};

            // Init the event handler queue
            if ( !handlers ) {
                handlers = events[ type ] = [];

                // Check for a special event handler
                // Only use addEventListener/attachEvent if the special
                // events handler returns false
                if ( !special.setup || special.setup.call( elem, data, namespaces, eventHandle ) === false ) {
                    // Bind the global event handler to the element
                    if ( elem.addEventListener ) {
                        elem.addEventListener( type, eventHandle, false );

                    } else if ( elem.attachEvent ) {
                        elem.attachEvent( "on" + type, eventHandle );
                    }
                }
            }

            if ( special.add ) {
                special.add.call( elem, handleObj );

                if ( !handleObj.handler.guid ) {
                    handleObj.handler.guid = handler.guid;
                }
            }

            // Add the function to the element's handler list
            handlers.push( handleObj );

            // Keep track of which events have been used, for global triggering
            jQuery.event.global[ type ] = true;
        }

        // Nullify elem to prevent memory leaks in IE
        elem = null;
    },

    global: {},

    // Detach an event or set of events from an element
    remove: function( elem, types, handler, pos ) {
        // don't do events on text and comment nodes
        if ( elem.nodeType === 3 || elem.nodeType === 8 ) {
            return;
        }

        if ( handler === false ) {
            handler = returnFalse;
        }

        var ret, type, fn, j, i = 0, all, namespaces, namespace, special, eventType, handleObj, origType,
            eventKey = elem.nodeType ? "events" : "__events__",
            elemData = jQuery.data( elem ),
            events = elemData && elemData[ eventKey ];

        if ( !elemData || !events ) {
            return;
        }

        if ( typeof events === "function" ) {
            elemData = events;
            events = events.events;
        }

        // types is actually an event object here
        if ( types && types.type ) {
            handler = types.handler;
            types = types.type;
        }

        // Unbind all events for the element
        if ( !types || typeof types === "string" && types.charAt(0) === "." ) {
            types = types || "";

            for ( type in events ) {
                jQuery.event.remove( elem, type + types );
            }

            return;
        }

        // Handle multiple events separated by a space
        // jQuery(...).unbind("mouseover mouseout", fn);
        types = types.split(" ");

        while ( (type = types[ i++ ]) ) {
            origType = type;
            handleObj = null;
            all = type.indexOf(".") < 0;
            namespaces = [];

            if ( !all ) {
                // Namespaced event handlers
                namespaces = type.split(".");
                type = namespaces.shift();

                namespace = new RegExp("(^|\\.)" +
                    jQuery.map( namespaces.slice(0).sort(), fcleanup ).join("\\.(?:.*\\.)?") + "(\\.|$)");
            }

            eventType = events[ type ];

            if ( !eventType ) {
                continue;
            }

            if ( !handler ) {
                for ( j = 0; j < eventType.length; j++ ) {
                    handleObj = eventType[ j ];

                    if ( all || namespace.test( handleObj.namespace ) ) {
                        jQuery.event.remove( elem, origType, handleObj.handler, j );
                        eventType.splice( j--, 1 );
                    }
                }

                continue;
            }

            special = jQuery.event.special[ type ] || {};

            for ( j = pos || 0; j < eventType.length; j++ ) {
                handleObj = eventType[ j ];

                if ( handler.guid === handleObj.guid ) {
                    // remove the given handler for the given type
                    if ( all || namespace.test( handleObj.namespace ) ) {
                        if ( pos == null ) {
                            eventType.splice( j--, 1 );
                        }

                        if ( special.remove ) {
                            special.remove.call( elem, handleObj );
                        }
                    }

                    if ( pos != null ) {
                        break;
                    }
                }
            }

            // remove generic event handler if no more handlers exist
            if ( eventType.length === 0 || pos != null && eventType.length === 1 ) {
                if ( !special.teardown || special.teardown.call( elem, namespaces ) === false ) {
                    jQuery.removeEvent( elem, type, elemData.handle );
                }

                ret = null;
                delete events[ type ];
            }
        }

        // Remove the expando if it's no longer used
        if ( jQuery.isEmptyObject( events ) ) {
            var handle = elemData.handle;
            if ( handle ) {
                handle.elem = null;
            }

            delete elemData.events;
            delete elemData.handle;

            if ( typeof elemData === "function" ) {
                jQuery.removeData( elem, eventKey );

            } else if ( jQuery.isEmptyObject( elemData ) ) {
                jQuery.removeData( elem );
            }
        }
    },

    // bubbling is internal
    trigger: function( event, data, elem /*, bubbling */ ) {
        // Event object or event type
        var type = event.type || event,
            bubbling = arguments[3];

        if ( !bubbling ) {
            event = typeof event === "object" ?
                // jQuery.Event object
                event[ jQuery.expando ] ? event :
                // Object literal
                jQuery.extend( jQuery.Event(type), event ) :
                // Just the event type (string)
                jQuery.Event(type);

            if ( type.indexOf("!") >= 0 ) {
                event.type = type = type.slice(0, -1);
                event.exclusive = true;
            }

            // Handle a global trigger
            if ( !elem ) {
                // Don't bubble custom events when global (to avoid too much overhead)
                event.stopPropagation();

                // Only trigger if we've ever bound an event for it
                if ( jQuery.event.global[ type ] ) {
                    jQuery.each( jQuery.cache, function() {
                        if ( this.events && this.events[type] ) {
                            jQuery.event.trigger( event, data, this.handle.elem );
                        }
                    });
                }
            }

            // Handle triggering a single element

            // don't do events on text and comment nodes
            if ( !elem || elem.nodeType === 3 || elem.nodeType === 8 ) {
                return undefined;
            }

            // Clean up in case it is reused
            event.result = undefined;
            event.target = elem;

            // Clone the incoming data, if any
            data = jQuery.makeArray( data );
            data.unshift( event );
        }

        event.currentTarget = elem;

        // Trigger the event, it is assumed that "handle" is a function
        var handle = elem.nodeType ?
            jQuery.data( elem, "handle" ) :
            (jQuery.data( elem, "__events__" ) || {}).handle;

        if ( handle ) {
            handle.apply( elem, data );
        }

        var parent = elem.parentNode || elem.ownerDocument;

        // Trigger an inline bound script
        try {
            if ( !(elem && elem.nodeName && jQuery.noData[elem.nodeName.toLowerCase()]) ) {
                if ( elem[ "on" + type ] && elem[ "on" + type ].apply( elem, data ) === false ) {
                    event.result = false;
                    event.preventDefault();
                }
            }

        // prevent IE from throwing an error for some elements with some event types, see #3533
        } catch (inlineError) {}

        if ( !event.isPropagationStopped() && parent ) {
            jQuery.event.trigger( event, data, parent, true );

        } else if ( !event.isDefaultPrevented() ) {
            var old,
                target = event.target,
                targetType = type.replace( rnamespaces, "" ),
                isClick = jQuery.nodeName( target, "a" ) && targetType === "click",
                special = jQuery.event.special[ targetType ] || {};

            if ( (!special._default || special._default.call( elem, event ) === false) &&
                !isClick && !(target && target.nodeName && jQuery.noData[target.nodeName.toLowerCase()]) ) {

                try {
                    if ( target[ targetType ] ) {
                        // Make sure that we don't accidentally re-trigger the onFOO events
                        old = target[ "on" + targetType ];

                        if ( old ) {
                            target[ "on" + targetType ] = null;
                        }

                        jQuery.event.triggered = true;
                        target[ targetType ]();
                    }

                // prevent IE from throwing an error for some elements with some event types, see #3533
                } catch (triggerError) {}

                if ( old ) {
                    target[ "on" + targetType ] = old;
                }

                jQuery.event.triggered = false;
            }
        }
    },

    handle: function( event ) {
        var all, handlers, namespaces, namespace_re, events,
            namespace_sort = [],
            args = jQuery.makeArray( arguments );

        event = args[0] = jQuery.event.fix( event || window.event );
        event.currentTarget = this;

        // Namespaced event handlers
        all = event.type.indexOf(".") < 0 && !event.exclusive;

        if ( !all ) {
            namespaces = event.type.split(".");
            event.type = namespaces.shift();
            namespace_sort = namespaces.slice(0).sort();
            namespace_re = new RegExp("(^|\\.)" + namespace_sort.join("\\.(?:.*\\.)?") + "(\\.|$)");
        }

        event.namespace = event.namespace || namespace_sort.join(".");

        events = jQuery.data(this, this.nodeType ? "events" : "__events__");

        if ( typeof events === "function" ) {
            events = events.events;
        }

        handlers = (events || {})[ event.type ];

        if ( events && handlers ) {
            // Clone the handlers to prevent manipulation
            handlers = handlers.slice(0);

            for ( var j = 0, l = handlers.length; j < l; j++ ) {
                var handleObj = handlers[ j ];

                // Filter the functions by class
                if ( all || namespace_re.test( handleObj.namespace ) ) {
                    // Pass in a reference to the handler function itself
                    // So that we can later remove it
                    event.handler = handleObj.handler;
                    event.data = handleObj.data;
                    event.handleObj = handleObj;

                    var ret = handleObj.handler.apply( this, args );

                    if ( ret !== undefined ) {
                        event.result = ret;
                        if ( ret === false ) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                    }

                    if ( event.isImmediatePropagationStopped() ) {
                        break;
                    }
                }
            }
        }

        return event.result;
    },

    props: "altKey attrChange attrName bubbles button cancelable charCode clientX clientY ctrlKey currentTarget data detail eventPhase fromElement handler keyCode layerX layerY metaKey newValue offsetX offsetY pageX pageY prevValue relatedNode relatedTarget screenX screenY shiftKey srcElement target toElement view wheelDelta which".split(" "),

    fix: function( event ) {
        if ( event[ jQuery.expando ] ) {
            return event;
        }

        // store a copy of the original event object
        // and "clone" to set read-only properties
        var originalEvent = event;
        event = jQuery.Event( originalEvent );

        for ( var i = this.props.length, prop; i; ) {
            prop = this.props[ --i ];
            event[ prop ] = originalEvent[ prop ];
        }

        // Fix target property, if necessary
        if ( !event.target ) {
            // Fixes #1925 where srcElement might not be defined either
            event.target = event.srcElement || document;
        }

        // check if target is a textnode (safari)
        if ( event.target.nodeType === 3 ) {
            event.target = event.target.parentNode;
        }

        // Add relatedTarget, if necessary
        if ( !event.relatedTarget && event.fromElement ) {
            event.relatedTarget = event.fromElement === event.target ? event.toElement : event.fromElement;
        }

        // Calculate pageX/Y if missing and clientX/Y available
        if ( event.pageX == null && event.clientX != null ) {
            var doc = document.documentElement,
                body = document.body;

            event.pageX = event.clientX + (doc && doc.scrollLeft || body && body.scrollLeft || 0) - (doc && doc.clientLeft || body && body.clientLeft || 0);
            event.pageY = event.clientY + (doc && doc.scrollTop  || body && body.scrollTop  || 0) - (doc && doc.clientTop  || body && body.clientTop  || 0);
        }

        // Add which for key events
        if ( event.which == null && (event.charCode != null || event.keyCode != null) ) {
            event.which = event.charCode != null ? event.charCode : event.keyCode;
        }

        // Add metaKey to non-Mac browsers (use ctrl for PC's and Meta for Macs)
        if ( !event.metaKey && event.ctrlKey ) {
            event.metaKey = event.ctrlKey;
        }

        // Add which for click: 1 === left; 2 === middle; 3 === right
        // Note: button is not normalized, so don't use it
        if ( !event.which && event.button !== undefined ) {
            event.which = (event.button & 1 ? 1 : ( event.button & 2 ? 3 : ( event.button & 4 ? 2 : 0 ) ));
        }

        return event;
    },

    // Deprecated, use jQuery.guid instead
    guid: 1E8,

    // Deprecated, use jQuery.proxy instead
    proxy: jQuery.proxy,

    special: {
        ready: {
            // Make sure the ready event is setup
            setup: jQuery.bindReady,
            teardown: jQuery.noop
        },

        live: {
            add: function( handleObj ) {
                jQuery.event.add( this,
                    liveConvert( handleObj.origType, handleObj.selector ),
                    jQuery.extend({}, handleObj, {handler: liveHandler, guid: handleObj.handler.guid}) );
            },

            remove: function( handleObj ) {
                jQuery.event.remove( this, liveConvert( handleObj.origType, handleObj.selector ), handleObj );
            }
        },

        beforeunload: {
            setup: function( data, namespaces, eventHandle ) {
                // We only want to do this special case on windows
                if ( jQuery.isWindow( this ) ) {
                    this.onbeforeunload = eventHandle;
                }
            },

            teardown: function( namespaces, eventHandle ) {
                if ( this.onbeforeunload === eventHandle ) {
                    this.onbeforeunload = null;
                }
            }
        }
    }
};

jQuery.removeEvent = document.removeEventListener ?
    function( elem, type, handle ) {
        if ( elem.removeEventListener ) {
            elem.removeEventListener( type, handle, false );
        }
    } :
    function( elem, type, handle ) {
        if ( elem.detachEvent ) {
            elem.detachEvent( "on" + type, handle );
        }
    };

jQuery.Event = function( src ) {
    // Allow instantiation without the 'new' keyword
    if ( !this.preventDefault ) {
        return new jQuery.Event( src );
    }

    // Event object
    if ( src && src.type ) {
        this.originalEvent = src;
        this.type = src.type;
    // Event type
    } else {
        this.type = src;
    }

    // timeStamp is buggy for some events on Firefox(#3843)
    // So we won't rely on the native value
    this.timeStamp = jQuery.now();

    // Mark it as fixed
    this[ jQuery.expando ] = true;
};

function returnFalse() {
    return false;
}
function returnTrue() {
    return true;
}

// jQuery.Event is based on DOM3 Events as specified by the ECMAScript Language Binding
// http://www.w3.org/TR/2003/WD-DOM-Level-3-Events-20030331/ecma-script-binding.html
jQuery.Event.prototype = {
    preventDefault: function() {
        this.isDefaultPrevented = returnTrue;

        var e = this.originalEvent;
        if ( !e ) {
            return;
        }

        // if preventDefault exists run it on the original event
        if ( e.preventDefault ) {
            e.preventDefault();

        // otherwise set the returnValue property of the original event to false (IE)
        } else {
            e.returnValue = false;
        }
    },
    stopPropagation: function() {
        this.isPropagationStopped = returnTrue;

        var e = this.originalEvent;
        if ( !e ) {
            return;
        }
        // if stopPropagation exists run it on the original event
        if ( e.stopPropagation ) {
            e.stopPropagation();
        }
        // otherwise set the cancelBubble property of the original event to true (IE)
        e.cancelBubble = true;
    },
    stopImmediatePropagation: function() {
        this.isImmediatePropagationStopped = returnTrue;
        this.stopPropagation();
    },
    isDefaultPrevented: returnFalse,
    isPropagationStopped: returnFalse,
    isImmediatePropagationStopped: returnFalse
};

// Checks if an event happened on an element within another element
// Used in jQuery.event.special.mouseenter and mouseleave handlers
var withinElement = function( event ) {
    // Check if mouse(over|out) are still within the same parent element
    var parent = event.relatedTarget;

    // Firefox sometimes assigns relatedTarget a XUL element
    // which we cannot access the parentNode property of
    try {
        // Traverse up the tree
        while ( parent && parent !== this ) {
            parent = parent.parentNode;
        }

        if ( parent !== this ) {
            // set the correct event type
            event.type = event.data;

            // handle event if we actually just moused on to a non sub-element
            jQuery.event.handle.apply( this, arguments );
        }

    // assuming we've left the element since we most likely mousedover a xul element
    } catch(e) { }
},

// In case of event delegation, we only need to rename the event.type,
// liveHandler will take care of the rest.
delegate = function( event ) {
    event.type = event.data;
    jQuery.event.handle.apply( this, arguments );
};

// Create mouseenter and mouseleave events
jQuery.each({
    mouseenter: "mouseover",
    mouseleave: "mouseout"
}, function( orig, fix ) {
    jQuery.event.special[ orig ] = {
        setup: function( data ) {
            jQuery.event.add( this, fix, data && data.selector ? delegate : withinElement, orig );
        },
        teardown: function( data ) {
            jQuery.event.remove( this, fix, data && data.selector ? delegate : withinElement );
        }
    };
});

// submit delegation
if ( !jQuery.support.submitBubbles ) {

    jQuery.event.special.submit = {
        setup: function( data, namespaces ) {
            if ( this.nodeName.toLowerCase() !== "form" ) {
                jQuery.event.add(this, "click.specialSubmit", function( e ) {
                    var elem = e.target,
                        type = elem.type;

                    if ( (type === "submit" || type === "image") && jQuery( elem ).closest("form").length ) {
                        e.liveFired = undefined;
                        return trigger( "submit", this, arguments );
                    }
                });

                jQuery.event.add(this, "keypress.specialSubmit", function( e ) {
                    var elem = e.target,
                        type = elem.type;

                    if ( (type === "text" || type === "password") && jQuery( elem ).closest("form").length && e.keyCode === 13 ) {
                        e.liveFired = undefined;
                        return trigger( "submit", this, arguments );
                    }
                });

            } else {
                return false;
            }
        },

        teardown: function( namespaces ) {
            jQuery.event.remove( this, ".specialSubmit" );
        }
    };

}

// change delegation, happens here so we have bind.
if ( !jQuery.support.changeBubbles ) {

    var changeFilters,

    getVal = function( elem ) {
        var type = elem.type, val = elem.value;

        if ( type === "radio" || type === "checkbox" ) {
            val = elem.checked;

        } else if ( type === "select-multiple" ) {
            val = elem.selectedIndex > -1 ?
                jQuery.map( elem.options, function( elem ) {
                    return elem.selected;
                }).join("-") :
                "";

        } else if ( elem.nodeName.toLowerCase() === "select" ) {
            val = elem.selectedIndex;
        }

        return val;
    },

    testChange = function testChange( e ) {
        var elem = e.target, data, val;

        if ( !rformElems.test( elem.nodeName ) || elem.readOnly ) {
            return;
        }

        data = jQuery.data( elem, "_change_data" );
        val = getVal(elem);

        // the current data will be also retrieved by beforeactivate
        if ( e.type !== "focusout" || elem.type !== "radio" ) {
            jQuery.data( elem, "_change_data", val );
        }

        if ( data === undefined || val === data ) {
            return;
        }

        if ( data != null || val ) {
            e.type = "change";
            e.liveFired = undefined;
            return jQuery.event.trigger( e, arguments[1], elem );
        }
    };

    jQuery.event.special.change = {
        filters: {
            focusout: testChange,

            beforedeactivate: testChange,

            click: function( e ) {
                var elem = e.target, type = elem.type;

                if ( type === "radio" || type === "checkbox" || elem.nodeName.toLowerCase() === "select" ) {
                    return testChange.call( this, e );
                }
            },

            // Change has to be called before submit
            // Keydown will be called before keypress, which is used in submit-event delegation
            keydown: function( e ) {
                var elem = e.target, type = elem.type;

                if ( (e.keyCode === 13 && elem.nodeName.toLowerCase() !== "textarea") ||
                    (e.keyCode === 32 && (type === "checkbox" || type === "radio")) ||
                    type === "select-multiple" ) {
                    return testChange.call( this, e );
                }
            },

            // Beforeactivate happens also before the previous element is blurred
            // with this event you can't trigger a change event, but you can store
            // information
            beforeactivate: function( e ) {
                var elem = e.target;
                jQuery.data( elem, "_change_data", getVal(elem) );
            }
        },

        setup: function( data, namespaces ) {
            if ( this.type === "file" ) {
                return false;
            }

            for ( var type in changeFilters ) {
                jQuery.event.add( this, type + ".specialChange", changeFilters[type] );
            }

            return rformElems.test( this.nodeName );
        },

        teardown: function( namespaces ) {
            jQuery.event.remove( this, ".specialChange" );

            return rformElems.test( this.nodeName );
        }
    };

    changeFilters = jQuery.event.special.change.filters;

    // Handle when the input is .focus()'d
    changeFilters.focus = changeFilters.beforeactivate;
}

function trigger( type, elem, args ) {
    args[0].type = type;
    return jQuery.event.handle.apply( elem, args );
}

// Create "bubbling" focus and blur events
if ( document.addEventListener ) {
    jQuery.each({ focus: "focusin", blur: "focusout" }, function( orig, fix ) {
        jQuery.event.special[ fix ] = {
            setup: function() {
                if ( focusCounts[fix]++ === 0 ) {
                    document.addEventListener( orig, handler, true );
                }
            },
            teardown: function() {
                if ( --focusCounts[fix] === 0 ) {
                    document.removeEventListener( orig, handler, true );
                }
            }
        };

        function handler( e ) {
            e = jQuery.event.fix( e );
            e.type = fix;
            return jQuery.event.trigger( e, null, e.target );
        }
    });
}

jQuery.each(["bind", "one"], function( i, name ) {
    jQuery.fn[ name ] = function( type, data, fn ) {
        // Handle object literals
        if ( typeof type === "object" ) {
            for ( var key in type ) {
                this[ name ](key, data, type[key], fn);
            }
            return this;
        }

        if ( jQuery.isFunction( data ) || data === false ) {
            fn = data;
            data = undefined;
        }

        var handler = name === "one" ? jQuery.proxy( fn, function( event ) {
            jQuery( this ).unbind( event, handler );
            return fn.apply( this, arguments );
        }) : fn;

        if ( type === "unload" && name !== "one" ) {
            this.one( type, data, fn );

        } else {
            for ( var i = 0, l = this.length; i < l; i++ ) {
                jQuery.event.add( this[i], type, handler, data );
            }
        }

        return this;
    };
});

jQuery.fn.extend({
    unbind: function( type, fn ) {
        // Handle object literals
        if ( typeof type === "object" && !type.preventDefault ) {
            for ( var key in type ) {
                this.unbind(key, type[key]);
            }

        } else {
            for ( var i = 0, l = this.length; i < l; i++ ) {
                jQuery.event.remove( this[i], type, fn );
            }
        }

        return this;
    },

    delegate: function( selector, types, data, fn ) {
        return this.live( types, data, fn, selector );
    },

    undelegate: function( selector, types, fn ) {
        if ( arguments.length === 0 ) {
                return this.unbind( "live" );

        } else {
            return this.die( types, null, fn, selector );
        }
    },

    trigger: function( type, data ) {
        return this.each(function() {
            jQuery.event.trigger( type, data, this );
        });
    },

    triggerHandler: function( type, data ) {
        if ( this[0] ) {
            var event = jQuery.Event( type );
            event.preventDefault();
            event.stopPropagation();
            jQuery.event.trigger( event, data, this[0] );
            return event.result;
        }
    },

    toggle: function( fn ) {
        // Save reference to arguments for access in closure
        var args = arguments,
            i = 1;

        // link all the functions, so any of them can unbind this click handler
        while ( i < args.length ) {
            jQuery.proxy( fn, args[ i++ ] );
        }

        return this.click( jQuery.proxy( fn, function( event ) {
            // Figure out which function to execute
            var lastToggle = ( jQuery.data( this, "lastToggle" + fn.guid ) || 0 ) % i;
            jQuery.data( this, "lastToggle" + fn.guid, lastToggle + 1 );

            // Make sure that clicks stop
            event.preventDefault();

            // and execute the function
            return args[ lastToggle ].apply( this, arguments ) || false;
        }));
    },

    hover: function( fnOver, fnOut ) {
        return this.mouseenter( fnOver ).mouseleave( fnOut || fnOver );
    }
});

var liveMap = {
    focus: "focusin",
    blur: "focusout",
    mouseenter: "mouseover",
    mouseleave: "mouseout"
};

jQuery.each(["live", "die"], function( i, name ) {
    jQuery.fn[ name ] = function( types, data, fn, origSelector /* Internal Use Only */ ) {
        var type, i = 0, match, namespaces, preType,
            selector = origSelector || this.selector,
            context = origSelector ? this : jQuery( this.context );

        if ( typeof types === "object" && !types.preventDefault ) {
            for ( var key in types ) {
                context[ name ]( key, data, types[key], selector );
            }

            return this;
        }

        if ( jQuery.isFunction( data ) ) {
            fn = data;
            data = undefined;
        }

        types = (types || "").split(" ");

        while ( (type = types[ i++ ]) != null ) {
            match = rnamespaces.exec( type );
            namespaces = "";

            if ( match )  {
                namespaces = match[0];
                type = type.replace( rnamespaces, "" );
            }

            if ( type === "hover" ) {
                types.push( "mouseenter" + namespaces, "mouseleave" + namespaces );
                continue;
            }

            preType = type;

            if ( type === "focus" || type === "blur" ) {
                types.push( liveMap[ type ] + namespaces );
                type = type + namespaces;

            } else {
                type = (liveMap[ type ] || type) + namespaces;
            }

            if ( name === "live" ) {
                // bind live handler
                for ( var j = 0, l = context.length; j < l; j++ ) {
                    jQuery.event.add( context[j], "live." + liveConvert( type, selector ),
                        { data: data, selector: selector, handler: fn, origType: type, origHandler: fn, preType: preType } );
                }

            } else {
                // unbind live handler
                context.unbind( "live." + liveConvert( type, selector ), fn );
            }
        }

        return this;
    };
});

function liveHandler( event ) {
    var stop, maxLevel, related, match, handleObj, elem, j, i, l, data, close, namespace, ret,
        elems = [],
        selectors = [],
        events = jQuery.data( this, this.nodeType ? "events" : "__events__" );

    if ( typeof events === "function" ) {
        events = events.events;
    }

    // Make sure we avoid non-left-click bubbling in Firefox (#3861)
    if ( event.liveFired === this || !events || !events.live || event.button && event.type === "click" ) {
        return;
    }

    if ( event.namespace ) {
        namespace = new RegExp("(^|\\.)" + event.namespace.split(".").join("\\.(?:.*\\.)?") + "(\\.|$)");
    }

    event.liveFired = this;

    var live = events.live.slice(0);

    for ( j = 0; j < live.length; j++ ) {
        handleObj = live[j];

        if ( handleObj.origType.replace( rnamespaces, "" ) === event.type ) {
            selectors.push( handleObj.selector );

        } else {
            live.splice( j--, 1 );
        }
    }

    match = jQuery( event.target ).closest( selectors, event.currentTarget );

    for ( i = 0, l = match.length; i < l; i++ ) {
        close = match[i];

        for ( j = 0; j < live.length; j++ ) {
            handleObj = live[j];

            if ( close.selector === handleObj.selector && (!namespace || namespace.test( handleObj.namespace )) ) {
                elem = close.elem;
                related = null;

                // Those two events require additional checking
                if ( handleObj.preType === "mouseenter" || handleObj.preType === "mouseleave" ) {
                    event.type = handleObj.preType;
                    related = jQuery( event.relatedTarget ).closest( handleObj.selector )[0];
                }

                if ( !related || related !== elem ) {
                    elems.push({ elem: elem, handleObj: handleObj, level: close.level });
                }
            }
        }
    }

    for ( i = 0, l = elems.length; i < l; i++ ) {
        match = elems[i];

        if ( maxLevel && match.level > maxLevel ) {
            break;
        }

        event.currentTarget = match.elem;
        event.data = match.handleObj.data;
        event.handleObj = match.handleObj;

        ret = match.handleObj.origHandler.apply( match.elem, arguments );

        if ( ret === false || event.isPropagationStopped() ) {
            maxLevel = match.level;

            if ( ret === false ) {
                stop = false;
            }
            if ( event.isImmediatePropagationStopped() ) {
                break;
            }
        }
    }

    return stop;
}

function liveConvert( type, selector ) {
    return (type && type !== "*" ? type + "." : "") + selector.replace(rperiod, "`").replace(rspace, "&");
}

jQuery.each( ("blur focus focusin focusout load resize scroll unload click dblclick " +
    "mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave " +
    "change select submit keydown keypress keyup error").split(" "), function( i, name ) {

    // Handle event binding
    jQuery.fn[ name ] = function( data, fn ) {
        if ( fn == null ) {
            fn = data;
            data = null;
        }

        return arguments.length > 0 ?
            this.bind( name, data, fn ) :
            this.trigger( name );
    };

    if ( jQuery.attrFn ) {
        jQuery.attrFn[ name ] = true;
    }
});

// Prevent memory leaks in IE
// Window isn't included so as not to unbind existing unload events
// More info:
//  - http://isaacschlueter.com/2006/10/msie-memory-leaks/
if ( window.attachEvent && !window.addEventListener ) {
    jQuery(window).bind("unload", function() {
        for ( var id in jQuery.cache ) {
            if ( jQuery.cache[ id ].handle ) {
                // Try/Catch is to handle iframes being unloaded, see #4280
                try {
                    jQuery.event.remove( jQuery.cache[ id ].handle.elem );
                } catch(e) {}
            }
        }
    });
}


/*!
 * Sizzle CSS Selector Engine - v1.0
 *  Copyright 2009, The Dojo Foundation
 *  Released under the MIT, BSD, and GPL Licenses.
 *  More information: http://sizzlejs.com/
 */
(function(){

var chunker = /((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^\[\]]*\]|['"][^'"]*['"]|[^\[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?((?:.|\r|\n)*)/g,
    done = 0,
    toString = Object.prototype.toString,
    hasDuplicate = false,
    baseHasDuplicate = true;

// Here we check if the JavaScript engine is using some sort of
// optimization where it does not always call our comparision
// function. If that is the case, discard the hasDuplicate value.
//   Thus far that includes Google Chrome.
[0, 0].sort(function() {
    baseHasDuplicate = false;
    return 0;
});

var Sizzle = function( selector, context, results, seed ) {
    results = results || [];
    context = context || document;

    var origContext = context;

    if ( context.nodeType !== 1 && context.nodeType !== 9 ) {
        return [];
    }

    if ( !selector || typeof selector !== "string" ) {
        return results;
    }

    var m, set, checkSet, extra, ret, cur, pop, i,
        prune = true,
        contextXML = Sizzle.isXML( context ),
        parts = [],
        soFar = selector;

    // Reset the position of the chunker regexp (start from head)
    do {
        chunker.exec( "" );
        m = chunker.exec( soFar );

        if ( m ) {
            soFar = m[3];

            parts.push( m[1] );

            if ( m[2] ) {
                extra = m[3];
                break;
            }
        }
    } while ( m );

    if ( parts.length > 1 && origPOS.exec( selector ) ) {

        if ( parts.length === 2 && Expr.relative[ parts[0] ] ) {
            set = posProcess( parts[0] + parts[1], context );

        } else {
            set = Expr.relative[ parts[0] ] ?
                [ context ] :
                Sizzle( parts.shift(), context );

            while ( parts.length ) {
                selector = parts.shift();

                if ( Expr.relative[ selector ] ) {
                    selector += parts.shift();
                }

                set = posProcess( selector, set );
            }
        }

    } else {
        // Take a shortcut and set the context if the root selector is an ID
        // (but not if it'll be faster if the inner selector is an ID)
        if ( !seed && parts.length > 1 && context.nodeType === 9 && !contextXML &&
                Expr.match.ID.test(parts[0]) && !Expr.match.ID.test(parts[parts.length - 1]) ) {

            ret = Sizzle.find( parts.shift(), context, contextXML );
            context = ret.expr ?
                Sizzle.filter( ret.expr, ret.set )[0] :
                ret.set[0];
        }

        if ( context ) {
            ret = seed ?
                { expr: parts.pop(), set: makeArray(seed) } :
                Sizzle.find( parts.pop(), parts.length === 1 && (parts[0] === "~" || parts[0] === "+") && context.parentNode ? context.parentNode : context, contextXML );

            set = ret.expr ?
                Sizzle.filter( ret.expr, ret.set ) :
                ret.set;

            if ( parts.length > 0 ) {
                checkSet = makeArray( set );

            } else {
                prune = false;
            }

            while ( parts.length ) {
                cur = parts.pop();
                pop = cur;

                if ( !Expr.relative[ cur ] ) {
                    cur = "";
                } else {
                    pop = parts.pop();
                }

                if ( pop == null ) {
                    pop = context;
                }

                Expr.relative[ cur ]( checkSet, pop, contextXML );
            }

        } else {
            checkSet = parts = [];
        }
    }

    if ( !checkSet ) {
        checkSet = set;
    }

    if ( !checkSet ) {
        Sizzle.error( cur || selector );
    }

    if ( toString.call(checkSet) === "[object Array]" ) {
        if ( !prune ) {
            results.push.apply( results, checkSet );

        } else if ( context && context.nodeType === 1 ) {
            for ( i = 0; checkSet[i] != null; i++ ) {
                if ( checkSet[i] && (checkSet[i] === true || checkSet[i].nodeType === 1 && Sizzle.contains(context, checkSet[i])) ) {
                    results.push( set[i] );
                }
            }

        } else {
            for ( i = 0; checkSet[i] != null; i++ ) {
                if ( checkSet[i] && checkSet[i].nodeType === 1 ) {
                    results.push( set[i] );
                }
            }
        }

    } else {
        makeArray( checkSet, results );
    }

    if ( extra ) {
        Sizzle( extra, origContext, results, seed );
        Sizzle.uniqueSort( results );
    }

    return results;
};

Sizzle.uniqueSort = function( results ) {
    if ( sortOrder ) {
        hasDuplicate = baseHasDuplicate;
        results.sort( sortOrder );

        if ( hasDuplicate ) {
            for ( var i = 1; i < results.length; i++ ) {
                if ( results[i] === results[ i - 1 ] ) {
                    results.splice( i--, 1 );
                }
            }
        }
    }

    return results;
};

Sizzle.matches = function( expr, set ) {
    return Sizzle( expr, null, null, set );
};

Sizzle.matchesSelector = function( node, expr ) {
    return Sizzle( expr, null, null, [node] ).length > 0;
};

Sizzle.find = function( expr, context, isXML ) {
    var set;

    if ( !expr ) {
        return [];
    }

    for ( var i = 0, l = Expr.order.length; i < l; i++ ) {
        var match,
            type = Expr.order[i];

        if ( (match = Expr.leftMatch[ type ].exec( expr )) ) {
            var left = match[1];
            match.splice( 1, 1 );

            if ( left.substr( left.length - 1 ) !== "\\" ) {
                match[1] = (match[1] || "").replace(/\\/g, "");
                set = Expr.find[ type ]( match, context, isXML );

                if ( set != null ) {
                    expr = expr.replace( Expr.match[ type ], "" );
                    break;
                }
            }
        }
    }

    if ( !set ) {
        set = context.getElementsByTagName( "*" );
    }

    return { set: set, expr: expr };
};

Sizzle.filter = function( expr, set, inplace, not ) {
    var match, anyFound,
        old = expr,
        result = [],
        curLoop = set,
        isXMLFilter = set && set[0] && Sizzle.isXML( set[0] );

    while ( expr && set.length ) {
        for ( var type in Expr.filter ) {
            if ( (match = Expr.leftMatch[ type ].exec( expr )) != null && match[2] ) {
                var found, item,
                    filter = Expr.filter[ type ],
                    left = match[1];

                anyFound = false;

                match.splice(1,1);

                if ( left.substr( left.length - 1 ) === "\\" ) {
                    continue;
                }

                if ( curLoop === result ) {
                    result = [];
                }

                if ( Expr.preFilter[ type ] ) {
                    match = Expr.preFilter[ type ]( match, curLoop, inplace, result, not, isXMLFilter );

                    if ( !match ) {
                        anyFound = found = true;

                    } else if ( match === true ) {
                        continue;
                    }
                }

                if ( match ) {
                    for ( var i = 0; (item = curLoop[i]) != null; i++ ) {
                        if ( item ) {
                            found = filter( item, match, i, curLoop );
                            var pass = not ^ !!found;

                            if ( inplace && found != null ) {
                                if ( pass ) {
                                    anyFound = true;

                                } else {
                                    curLoop[i] = false;
                                }

                            } else if ( pass ) {
                                result.push( item );
                                anyFound = true;
                            }
                        }
                    }
                }

                if ( found !== undefined ) {
                    if ( !inplace ) {
                        curLoop = result;
                    }

                    expr = expr.replace( Expr.match[ type ], "" );

                    if ( !anyFound ) {
                        return [];
                    }

                    break;
                }
            }
        }

        // Improper expression
        if ( expr === old ) {
            if ( anyFound == null ) {
                Sizzle.error( expr );

            } else {
                break;
            }
        }

        old = expr;
    }

    return curLoop;
};

Sizzle.error = function( msg ) {
    throw "Syntax error, unrecognized expression: " + msg;
};

var Expr = Sizzle.selectors = {
    order: [ "ID", "NAME", "TAG" ],

    match: {
        ID: /#((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,
        CLASS: /\.((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,
        NAME: /\[name=['"]*((?:[\w\u00c0-\uFFFF\-]|\\.)+)['"]*\]/,
        ATTR: /\[\s*((?:[\w\u00c0-\uFFFF\-]|\\.)+)\s*(?:(\S?=)\s*(['"]*)(.*?)\3|)\s*\]/,
        TAG: /^((?:[\w\u00c0-\uFFFF\*\-]|\\.)+)/,
        CHILD: /:(only|nth|last|first)-child(?:\((even|odd|[\dn+\-]*)\))?/,
        POS: /:(nth|eq|gt|lt|first|last|even|odd)(?:\((\d*)\))?(?=[^\-]|$)/,
        PSEUDO: /:((?:[\w\u00c0-\uFFFF\-]|\\.)+)(?:\((['"]?)((?:\([^\)]+\)|[^\(\)]*)+)\2\))?/
    },

    leftMatch: {},

    attrMap: {
        "class": "className",
        "for": "htmlFor"
    },

    attrHandle: {
        href: function( elem ) {
            return elem.getAttribute( "href" );
        }
    },

    relative: {
        "+": function(checkSet, part){
            var isPartStr = typeof part === "string",
                isTag = isPartStr && !/\W/.test( part ),
                isPartStrNotTag = isPartStr && !isTag;

            if ( isTag ) {
                part = part.toLowerCase();
            }

            for ( var i = 0, l = checkSet.length, elem; i < l; i++ ) {
                if ( (elem = checkSet[i]) ) {
                    while ( (elem = elem.previousSibling) && elem.nodeType !== 1 ) {}

                    checkSet[i] = isPartStrNotTag || elem && elem.nodeName.toLowerCase() === part ?
                        elem || false :
                        elem === part;
                }
            }

            if ( isPartStrNotTag ) {
                Sizzle.filter( part, checkSet, true );
            }
        },

        ">": function( checkSet, part ) {
            var elem,
                isPartStr = typeof part === "string",
                i = 0,
                l = checkSet.length;

            if ( isPartStr && !/\W/.test( part ) ) {
                part = part.toLowerCase();

                for ( ; i < l; i++ ) {
                    elem = checkSet[i];

                    if ( elem ) {
                        var parent = elem.parentNode;
                        checkSet[i] = parent.nodeName.toLowerCase() === part ? parent : false;
                    }
                }

            } else {
                for ( ; i < l; i++ ) {
                    elem = checkSet[i];

                    if ( elem ) {
                        checkSet[i] = isPartStr ?
                            elem.parentNode :
                            elem.parentNode === part;
                    }
                }

                if ( isPartStr ) {
                    Sizzle.filter( part, checkSet, true );
                }
            }
        },

        "": function(checkSet, part, isXML){
            var nodeCheck,
                doneName = done++,
                checkFn = dirCheck;

            if ( typeof part === "string" && !/\W/.test(part) ) {
                part = part.toLowerCase();
                nodeCheck = part;
                checkFn = dirNodeCheck;
            }

            checkFn( "parentNode", part, doneName, checkSet, nodeCheck, isXML );
        },

        "~": function( checkSet, part, isXML ) {
            var nodeCheck,
                doneName = done++,
                checkFn = dirCheck;

            if ( typeof part === "string" && !/\W/.test( part ) ) {
                part = part.toLowerCase();
                nodeCheck = part;
                checkFn = dirNodeCheck;
            }

            checkFn( "previousSibling", part, doneName, checkSet, nodeCheck, isXML );
        }
    },

    find: {
        ID: function( match, context, isXML ) {
            if ( typeof context.getElementById !== "undefined" && !isXML ) {
                var m = context.getElementById(match[1]);
                // Check parentNode to catch when Blackberry 4.6 returns
                // nodes that are no longer in the document #6963
                return m && m.parentNode ? [m] : [];
            }
        },

        NAME: function( match, context ) {
            if ( typeof context.getElementsByName !== "undefined" ) {
                var ret = [],
                    results = context.getElementsByName( match[1] );

                for ( var i = 0, l = results.length; i < l; i++ ) {
                    if ( results[i].getAttribute("name") === match[1] ) {
                        ret.push( results[i] );
                    }
                }

                return ret.length === 0 ? null : ret;
            }
        },

        TAG: function( match, context ) {
            return context.getElementsByTagName( match[1] );
        }
    },
    preFilter: {
        CLASS: function( match, curLoop, inplace, result, not, isXML ) {
            match = " " + match[1].replace(/\\/g, "") + " ";

            if ( isXML ) {
                return match;
            }

            for ( var i = 0, elem; (elem = curLoop[i]) != null; i++ ) {
                if ( elem ) {
                    if ( not ^ (elem.className && (" " + elem.className + " ").replace(/[\t\n]/g, " ").indexOf(match) >= 0) ) {
                        if ( !inplace ) {
                            result.push( elem );
                        }

                    } else if ( inplace ) {
                        curLoop[i] = false;
                    }
                }
            }

            return false;
        },

        ID: function( match ) {
            return match[1].replace(/\\/g, "");
        },

        TAG: function( match, curLoop ) {
            return match[1].toLowerCase();
        },

        CHILD: function( match ) {
            if ( match[1] === "nth" ) {
                // parse equations like 'even', 'odd', '5', '2n', '3n+2', '4n-1', '-n+6'
                var test = /(-?)(\d*)n((?:\+|-)?\d*)/.exec(
                    match[2] === "even" && "2n" || match[2] === "odd" && "2n+1" ||
                    !/\D/.test( match[2] ) && "0n+" + match[2] || match[2]);

                // calculate the numbers (first)n+(last) including if they are negative
                match[2] = (test[1] + (test[2] || 1)) - 0;
                match[3] = test[3] - 0;
            }

            // TODO: Move to normal caching system
            match[0] = done++;

            return match;
        },

        ATTR: function( match, curLoop, inplace, result, not, isXML ) {
            var name = match[1].replace(/\\/g, "");

            if ( !isXML && Expr.attrMap[name] ) {
                match[1] = Expr.attrMap[name];
            }

            if ( match[2] === "~=" ) {
                match[4] = " " + match[4] + " ";
            }

            return match;
        },

        PSEUDO: function( match, curLoop, inplace, result, not ) {
            if ( match[1] === "not" ) {
                // If we're dealing with a complex expression, or a simple one
                if ( ( chunker.exec(match[3]) || "" ).length > 1 || /^\w/.test(match[3]) ) {
                    match[3] = Sizzle(match[3], null, null, curLoop);

                } else {
                    var ret = Sizzle.filter(match[3], curLoop, inplace, true ^ not);

                    if ( !inplace ) {
                        result.push.apply( result, ret );
                    }

                    return false;
                }

            } else if ( Expr.match.POS.test( match[0] ) || Expr.match.CHILD.test( match[0] ) ) {
                return true;
            }

            return match;
        },

        POS: function( match ) {
            match.unshift( true );

            return match;
        }
    },

    filters: {
        enabled: function( elem ) {
            return elem.disabled === false && elem.type !== "hidden";
        },

        disabled: function( elem ) {
            return elem.disabled === true;
        },

        checked: function( elem ) {
            return elem.checked === true;
        },

        selected: function( elem ) {
            // Accessing this property makes selected-by-default
            // options in Safari work properly
            elem.parentNode.selectedIndex;

            return elem.selected === true;
        },

        parent: function( elem ) {
            return !!elem.firstChild;
        },

        empty: function( elem ) {
            return !elem.firstChild;
        },

        has: function( elem, i, match ) {
            return !!Sizzle( match[3], elem ).length;
        },

        header: function( elem ) {
            return (/h\d/i).test( elem.nodeName );
        },

        text: function( elem ) {
            return "text" === elem.type;
        },
        radio: function( elem ) {
            return "radio" === elem.type;
        },

        checkbox: function( elem ) {
            return "checkbox" === elem.type;
        },

        file: function( elem ) {
            return "file" === elem.type;
        },
        password: function( elem ) {
            return "password" === elem.type;
        },

        submit: function( elem ) {
            return "submit" === elem.type;
        },

        image: function( elem ) {
            return "image" === elem.type;
        },

        reset: function( elem ) {
            return "reset" === elem.type;
        },

        button: function( elem ) {
            return "button" === elem.type || elem.nodeName.toLowerCase() === "button";
        },

        input: function( elem ) {
            return (/input|select|textarea|button/i).test( elem.nodeName );
        }
    },
    setFilters: {
        first: function( elem, i ) {
            return i === 0;
        },

        last: function( elem, i, match, array ) {
            return i === array.length - 1;
        },

        even: function( elem, i ) {
            return i % 2 === 0;
        },

        odd: function( elem, i ) {
            return i % 2 === 1;
        },

        lt: function( elem, i, match ) {
            return i < match[3] - 0;
        },

        gt: function( elem, i, match ) {
            return i > match[3] - 0;
        },

        nth: function( elem, i, match ) {
            return match[3] - 0 === i;
        },

        eq: function( elem, i, match ) {
            return match[3] - 0 === i;
        }
    },
    filter: {
        PSEUDO: function( elem, match, i, array ) {
            var name = match[1],
                filter = Expr.filters[ name ];

            if ( filter ) {
                return filter( elem, i, match, array );

            } else if ( name === "contains" ) {
                return (elem.textContent || elem.innerText || Sizzle.getText([ elem ]) || "").indexOf(match[3]) >= 0;

            } else if ( name === "not" ) {
                var not = match[3];

                for ( var j = 0, l = not.length; j < l; j++ ) {
                    if ( not[j] === elem ) {
                        return false;
                    }
                }

                return true;

            } else {
                Sizzle.error( "Syntax error, unrecognized expression: " + name );
            }
        },

        CHILD: function( elem, match ) {
            var type = match[1],
                node = elem;

            switch ( type ) {
                case "only":
                case "first":
                    while ( (node = node.previousSibling) )  {
                        if ( node.nodeType === 1 ) {
                            return false;
                        }
                    }

                    if ( type === "first" ) {
                        return true;
                    }

                    node = elem;

                case "last":
                    while ( (node = node.nextSibling) )  {
                        if ( node.nodeType === 1 ) {
                            return false;
                        }
                    }

                    return true;

                case "nth":
                    var first = match[2],
                        last = match[3];

                    if ( first === 1 && last === 0 ) {
                        return true;
                    }

                    var doneName = match[0],
                        parent = elem.parentNode;

                    if ( parent && (parent.sizcache !== doneName || !elem.nodeIndex) ) {
                        var count = 0;

                        for ( node = parent.firstChild; node; node = node.nextSibling ) {
                            if ( node.nodeType === 1 ) {
                                node.nodeIndex = ++count;
                            }
                        }

                        parent.sizcache = doneName;
                    }

                    var diff = elem.nodeIndex - last;

                    if ( first === 0 ) {
                        return diff === 0;

                    } else {
                        return ( diff % first === 0 && diff / first >= 0 );
                    }
            }
        },

        ID: function( elem, match ) {
            return elem.nodeType === 1 && elem.getAttribute("id") === match;
        },

        TAG: function( elem, match ) {
            return (match === "*" && elem.nodeType === 1) || elem.nodeName.toLowerCase() === match;
        },

        CLASS: function( elem, match ) {
            return (" " + (elem.className || elem.getAttribute("class")) + " ")
                .indexOf( match ) > -1;
        },

        ATTR: function( elem, match ) {
            var name = match[1],
                result = Expr.attrHandle[ name ] ?
                    Expr.attrHandle[ name ]( elem ) :
                    elem[ name ] != null ?
                        elem[ name ] :
                        elem.getAttribute( name ),
                value = result + "",
                type = match[2],
                check = match[4];

            return result == null ?
                type === "!=" :
                type === "=" ?
                value === check :
                type === "*=" ?
                value.indexOf(check) >= 0 :
                type === "~=" ?
                (" " + value + " ").indexOf(check) >= 0 :
                !check ?
                value && result !== false :
                type === "!=" ?
                value !== check :
                type === "^=" ?
                value.indexOf(check) === 0 :
                type === "$=" ?
                value.substr(value.length - check.length) === check :
                type === "|=" ?
                value === check || value.substr(0, check.length + 1) === check + "-" :
                false;
        },

        POS: function( elem, match, i, array ) {
            var name = match[2],
                filter = Expr.setFilters[ name ];

            if ( filter ) {
                return filter( elem, i, match, array );
            }
        }
    }
};

var origPOS = Expr.match.POS,
    fescape = function(all, num){
        return "\\" + (num - 0 + 1);
    };

for ( var type in Expr.match ) {
    Expr.match[ type ] = new RegExp( Expr.match[ type ].source + (/(?![^\[]*\])(?![^\(]*\))/.source) );
    Expr.leftMatch[ type ] = new RegExp( /(^(?:.|\r|\n)*?)/.source + Expr.match[ type ].source.replace(/\\(\d+)/g, fescape) );
}

var makeArray = function( array, results ) {
    array = Array.prototype.slice.call( array, 0 );

    if ( results ) {
        results.push.apply( results, array );
        return results;
    }

    return array;
};

// Perform a simple check to determine if the browser is capable of
// converting a NodeList to an array using builtin methods.
// Also verifies that the returned array holds DOM nodes
// (which is not the case in the Blackberry browser)
try {
    Array.prototype.slice.call( document.documentElement.childNodes, 0 )[0].nodeType;

// Provide a fallback method if it does not work
} catch( e ) {
    makeArray = function( array, results ) {
        var i = 0,
            ret = results || [];

        if ( toString.call(array) === "[object Array]" ) {
            Array.prototype.push.apply( ret, array );

        } else {
            if ( typeof array.length === "number" ) {
                for ( var l = array.length; i < l; i++ ) {
                    ret.push( array[i] );
                }

            } else {
                for ( ; array[i]; i++ ) {
                    ret.push( array[i] );
                }
            }
        }

        return ret;
    };
}

var sortOrder, siblingCheck;

if ( document.documentElement.compareDocumentPosition ) {
    sortOrder = function( a, b ) {
        if ( a === b ) {
            hasDuplicate = true;
            return 0;
        }

        if ( !a.compareDocumentPosition || !b.compareDocumentPosition ) {
            return a.compareDocumentPosition ? -1 : 1;
        }

        return a.compareDocumentPosition(b) & 4 ? -1 : 1;
    };

} else {
    sortOrder = function( a, b ) {
        var al, bl,
            ap = [],
            bp = [],
            aup = a.parentNode,
            bup = b.parentNode,
            cur = aup;

        // The nodes are identical, we can exit early
        if ( a === b ) {
            hasDuplicate = true;
            return 0;

        // If the nodes are siblings (or identical) we can do a quick check
        } else if ( aup === bup ) {
            return siblingCheck( a, b );

        // If no parents were found then the nodes are disconnected
        } else if ( !aup ) {
            return -1;

        } else if ( !bup ) {
            return 1;
        }

        // Otherwise they're somewhere else in the tree so we need
        // to build up a full list of the parentNodes for comparison
        while ( cur ) {
            ap.unshift( cur );
            cur = cur.parentNode;
        }

        cur = bup;

        while ( cur ) {
            bp.unshift( cur );
            cur = cur.parentNode;
        }

        al = ap.length;
        bl = bp.length;

        // Start walking down the tree looking for a discrepancy
        for ( var i = 0; i < al && i < bl; i++ ) {
            if ( ap[i] !== bp[i] ) {
                return siblingCheck( ap[i], bp[i] );
            }
        }

        // We ended someplace up the tree so do a sibling check
        return i === al ?
            siblingCheck( a, bp[i], -1 ) :
            siblingCheck( ap[i], b, 1 );
    };

    siblingCheck = function( a, b, ret ) {
        if ( a === b ) {
            return ret;
        }

        var cur = a.nextSibling;

        while ( cur ) {
            if ( cur === b ) {
                return -1;
            }

            cur = cur.nextSibling;
        }

        return 1;
    };
}

// Utility function for retreiving the text value of an array of DOM nodes
Sizzle.getText = function( elems ) {
    var ret = "", elem;

    for ( var i = 0; elems[i]; i++ ) {
        elem = elems[i];

        // Get the text from text nodes and CDATA nodes
        if ( elem.nodeType === 3 || elem.nodeType === 4 ) {
            ret += elem.nodeValue;

        // Traverse everything else, except comment nodes
        } else if ( elem.nodeType !== 8 ) {
            ret += Sizzle.getText( elem.childNodes );
        }
    }

    return ret;
};

// Check to see if the browser returns elements by name when
// querying by getElementById (and provide a workaround)
(function(){
    // We're going to inject a fake input element with a specified name
    var form = document.createElement("div"),
        id = "script" + (new Date()).getTime(),
        root = document.documentElement;

    form.innerHTML = "<a name='" + id + "'/>";

    // Inject it into the root element, check its status, and remove it quickly
    root.insertBefore( form, root.firstChild );

    // The workaround has to do additional checks after a getElementById
    // Which slows things down for other browsers (hence the branching)
    if ( document.getElementById( id ) ) {
        Expr.find.ID = function( match, context, isXML ) {
            if ( typeof context.getElementById !== "undefined" && !isXML ) {
                var m = context.getElementById(match[1]);

                return m ?
                    m.id === match[1] || typeof m.getAttributeNode !== "undefined" && m.getAttributeNode("id").nodeValue === match[1] ?
                        [m] :
                        undefined :
                    [];
            }
        };

        Expr.filter.ID = function( elem, match ) {
            var node = typeof elem.getAttributeNode !== "undefined" && elem.getAttributeNode("id");

            return elem.nodeType === 1 && node && node.nodeValue === match;
        };
    }

    root.removeChild( form );

    // release memory in IE
    root = form = null;
})();

(function(){
    // Check to see if the browser returns only elements
    // when doing getElementsByTagName("*")

    // Create a fake element
    var div = document.createElement("div");
    div.appendChild( document.createComment("") );

    // Make sure no comments are found
    if ( div.getElementsByTagName("*").length > 0 ) {
        Expr.find.TAG = function( match, context ) {
            var results = context.getElementsByTagName( match[1] );

            // Filter out possible comments
            if ( match[1] === "*" ) {
                var tmp = [];

                for ( var i = 0; results[i]; i++ ) {
                    if ( results[i].nodeType === 1 ) {
                        tmp.push( results[i] );
                    }
                }

                results = tmp;
            }

            return results;
        };
    }

    // Check to see if an attribute returns normalized href attributes
    div.innerHTML = "<a href='#'></a>";

    if ( div.firstChild && typeof div.firstChild.getAttribute !== "undefined" &&
            div.firstChild.getAttribute("href") !== "#" ) {

        Expr.attrHandle.href = function( elem ) {
            return elem.getAttribute( "href", 2 );
        };
    }

    // release memory in IE
    div = null;
})();

if ( document.querySelectorAll ) {
    (function(){
        var oldSizzle = Sizzle,
            div = document.createElement("div"),
            id = "__sizzle__";

        div.innerHTML = "<p class='TEST'></p>";

        // Safari can't handle uppercase or unicode characters when
        // in quirks mode.
        if ( div.querySelectorAll && div.querySelectorAll(".TEST").length === 0 ) {
            return;
        }

        Sizzle = function( query, context, extra, seed ) {
            context = context || document;

            // Make sure that attribute selectors are quoted
            query = query.replace(/\=\s*([^'"\]]*)\s*\]/g, "='$1']");

            // Only use querySelectorAll on non-XML documents
            // (ID selectors don't work in non-HTML documents)
            if ( !seed && !Sizzle.isXML(context) ) {
                if ( context.nodeType === 9 ) {
                    try {
                        return makeArray( context.querySelectorAll(query), extra );
                    } catch(qsaError) {}

                // qSA works strangely on Element-rooted queries
                // We can work around this by specifying an extra ID on the root
                // and working up from there (Thanks to Andrew Dupont for the technique)
                // IE 8 doesn't work on object elements
                } else if ( context.nodeType === 1 && context.nodeName.toLowerCase() !== "object" ) {
                    var old = context.getAttribute( "id" ),
                        nid = old || id;

                    if ( !old ) {
                        context.setAttribute( "id", nid );
                    }

                    try {
                        return makeArray( context.querySelectorAll( "#" + nid + " " + query ), extra );

                    } catch(pseudoError) {
                    } finally {
                        if ( !old ) {
                            context.removeAttribute( "id" );
                        }
                    }
                }
            }

            return oldSizzle(query, context, extra, seed);
        };

        for ( var prop in oldSizzle ) {
            Sizzle[ prop ] = oldSizzle[ prop ];
        }

        // release memory in IE
        div = null;
    })();
}

(function(){
    var html = document.documentElement,
        matches = html.matchesSelector || html.mozMatchesSelector || html.webkitMatchesSelector || html.msMatchesSelector,
        pseudoWorks = false;

    try {
        // This should fail with an exception
        // Gecko does not error, returns false instead
        matches.call( document.documentElement, "[test!='']:sizzle" );

    } catch( pseudoError ) {
        pseudoWorks = true;
    }

    if ( matches ) {
        Sizzle.matchesSelector = function( node, expr ) {
            // Make sure that attribute selectors are quoted
            expr = expr.replace(/\=\s*([^'"\]]*)\s*\]/g, "='$1']");

            if ( !Sizzle.isXML( node ) ) {
                try {
                    if ( pseudoWorks || !Expr.match.PSEUDO.test( expr ) && !/!=/.test( expr ) ) {
                        return matches.call( node, expr );
                    }
                } catch(e) {}
            }

            return Sizzle(expr, null, null, [node]).length > 0;
        };
    }
})();

(function(){
    var div = document.createElement("div");

    div.innerHTML = "<div class='test e'></div><div class='test'></div>";

    // Opera can't find a second classname (in 9.6)
    // Also, make sure that getElementsByClassName actually exists
    if ( !div.getElementsByClassName || div.getElementsByClassName("e").length === 0 ) {
        return;
    }

    // Safari caches class attributes, doesn't catch changes (in 3.2)
    div.lastChild.className = "e";

    if ( div.getElementsByClassName("e").length === 1 ) {
        return;
    }

    Expr.order.splice(1, 0, "CLASS");
    Expr.find.CLASS = function( match, context, isXML ) {
        if ( typeof context.getElementsByClassName !== "undefined" && !isXML ) {
            return context.getElementsByClassName(match[1]);
        }
    };

    // release memory in IE
    div = null;
})();

function dirNodeCheck( dir, cur, doneName, checkSet, nodeCheck, isXML ) {
    for ( var i = 0, l = checkSet.length; i < l; i++ ) {
        var elem = checkSet[i];

        if ( elem ) {
            var match = false;

            elem = elem[dir];

            while ( elem ) {
                if ( elem.sizcache === doneName ) {
                    match = checkSet[elem.sizset];
                    break;
                }

                if ( elem.nodeType === 1 && !isXML ){
                    elem.sizcache = doneName;
                    elem.sizset = i;
                }

                if ( elem.nodeName.toLowerCase() === cur ) {
                    match = elem;
                    break;
                }

                elem = elem[dir];
            }

            checkSet[i] = match;
        }
    }
}

function dirCheck( dir, cur, doneName, checkSet, nodeCheck, isXML ) {
    for ( var i = 0, l = checkSet.length; i < l; i++ ) {
        var elem = checkSet[i];

        if ( elem ) {
            var match = false;

            elem = elem[dir];

            while ( elem ) {
                if ( elem.sizcache === doneName ) {
                    match = checkSet[elem.sizset];
                    break;
                }

                if ( elem.nodeType === 1 ) {
                    if ( !isXML ) {
                        elem.sizcache = doneName;
                        elem.sizset = i;
                    }

                    if ( typeof cur !== "string" ) {
                        if ( elem === cur ) {
                            match = true;
                            break;
                        }

                    } else if ( Sizzle.filter( cur, [elem] ).length > 0 ) {
                        match = elem;
                        break;
                    }
                }

                elem = elem[dir];
            }

            checkSet[i] = match;
        }
    }
}

if ( document.documentElement.contains ) {
    Sizzle.contains = function( a, b ) {
        return a !== b && (a.contains ? a.contains(b) : true);
    };

} else if ( document.documentElement.compareDocumentPosition ) {
    Sizzle.contains = function( a, b ) {
        return !!(a.compareDocumentPosition(b) & 16);
    };

} else {
    Sizzle.contains = function() {
        return false;
    };
}

Sizzle.isXML = function( elem ) {
    // documentElement is verified for cases where it doesn't yet exist
    // (such as loading iframes in IE - #4833)
    var documentElement = (elem ? elem.ownerDocument || elem : 0).documentElement;

    return documentElement ? documentElement.nodeName !== "HTML" : false;
};

var posProcess = function( selector, context ) {
    var match,
        tmpSet = [],
        later = "",
        root = context.nodeType ? [context] : context;

    // Position selectors must be done after the filter
    // And so must :not(positional) so we move all PSEUDOs to the end
    while ( (match = Expr.match.PSEUDO.exec( selector )) ) {
        later += match[0];
        selector = selector.replace( Expr.match.PSEUDO, "" );
    }

    selector = Expr.relative[selector] ? selector + "*" : selector;

    for ( var i = 0, l = root.length; i < l; i++ ) {
        Sizzle( selector, root[i], tmpSet );
    }

    return Sizzle.filter( later, tmpSet );
};

// EXPOSE
jQuery.find = Sizzle;
jQuery.expr = Sizzle.selectors;
jQuery.expr[":"] = jQuery.expr.filters;
jQuery.unique = Sizzle.uniqueSort;
jQuery.text = Sizzle.getText;
jQuery.isXMLDoc = Sizzle.isXML;
jQuery.contains = Sizzle.contains;


})();


var runtil = /Until$/,
    rparentsprev = /^(?:parents|prevUntil|prevAll)/,
    // Note: This RegExp should be improved, or likely pulled from Sizzle
    rmultiselector = /,/,
    isSimple = /^.[^:#\[\.,]*$/,
    slice = Array.prototype.slice,
    POS = jQuery.expr.match.POS;

jQuery.fn.extend({
    find: function( selector ) {
        var ret = this.pushStack( "", "find", selector ),
            length = 0;

        for ( var i = 0, l = this.length; i < l; i++ ) {
            length = ret.length;
            jQuery.find( selector, this[i], ret );

            if ( i > 0 ) {
                // Make sure that the results are unique
                for ( var n = length; n < ret.length; n++ ) {
                    for ( var r = 0; r < length; r++ ) {
                        if ( ret[r] === ret[n] ) {
                            ret.splice(n--, 1);
                            break;
                        }
                    }
                }
            }
        }

        return ret;
    },

    has: function( target ) {
        var targets = jQuery( target );
        return this.filter(function() {
            for ( var i = 0, l = targets.length; i < l; i++ ) {
                if ( jQuery.contains( this, targets[i] ) ) {
                    return true;
                }
            }
        });
    },

    not: function( selector ) {
        return this.pushStack( winnow(this, selector, false), "not", selector);
    },

    filter: function( selector ) {
        return this.pushStack( winnow(this, selector, true), "filter", selector );
    },

    is: function( selector ) {
        return !!selector && jQuery.filter( selector, this ).length > 0;
    },

    closest: function( selectors, context ) {
        var ret = [], i, l, cur = this[0];

        if ( jQuery.isArray( selectors ) ) {
            var match, selector,
                matches = {},
                level = 1;

            if ( cur && selectors.length ) {
                for ( i = 0, l = selectors.length; i < l; i++ ) {
                    selector = selectors[i];

                    if ( !matches[selector] ) {
                        matches[selector] = jQuery.expr.match.POS.test( selector ) ?
                            jQuery( selector, context || this.context ) :
                            selector;
                    }
                }

                while ( cur && cur.ownerDocument && cur !== context ) {
                    for ( selector in matches ) {
                        match = matches[selector];

                        if ( match.jquery ? match.index(cur) > -1 : jQuery(cur).is(match) ) {
                            ret.push({ selector: selector, elem: cur, level: level });
                        }
                    }

                    cur = cur.parentNode;
                    level++;
                }
            }

            return ret;
        }

        var pos = POS.test( selectors ) ?
            jQuery( selectors, context || this.context ) : null;

        for ( i = 0, l = this.length; i < l; i++ ) {
            cur = this[i];

            while ( cur ) {
                if ( pos ? pos.index(cur) > -1 : jQuery.find.matchesSelector(cur, selectors) ) {
                    ret.push( cur );
                    break;

                } else {
                    cur = cur.parentNode;
                    if ( !cur || !cur.ownerDocument || cur === context ) {
                        break;
                    }
                }
            }
        }

        ret = ret.length > 1 ? jQuery.unique(ret) : ret;

        return this.pushStack( ret, "closest", selectors );
    },

    // Determine the position of an element within
    // the matched set of elements
    index: function( elem ) {
        if ( !elem || typeof elem === "string" ) {
            return jQuery.inArray( this[0],
                // If it receives a string, the selector is used
                // If it receives nothing, the siblings are used
                elem ? jQuery( elem ) : this.parent().children() );
        }
        // Locate the position of the desired element
        return jQuery.inArray(
            // If it receives a jQuery object, the first element is used
            elem.jquery ? elem[0] : elem, this );
    },

    add: function( selector, context ) {
        var set = typeof selector === "string" ?
                jQuery( selector, context || this.context ) :
                jQuery.makeArray( selector ),
            all = jQuery.merge( this.get(), set );

        return this.pushStack( isDisconnected( set[0] ) || isDisconnected( all[0] ) ?
            all :
            jQuery.unique( all ) );
    },

    andSelf: function() {
        return this.add( this.prevObject );
    }
});

// A painfully simple check to see if an element is disconnected
// from a document (should be improved, where feasible).
function isDisconnected( node ) {
    return !node || !node.parentNode || node.parentNode.nodeType === 11;
}

jQuery.each({
    parent: function( elem ) {
        var parent = elem.parentNode;
        return parent && parent.nodeType !== 11 ? parent : null;
    },
    parents: function( elem ) {
        return jQuery.dir( elem, "parentNode" );
    },
    parentsUntil: function( elem, i, until ) {
        return jQuery.dir( elem, "parentNode", until );
    },
    next: function( elem ) {
        return jQuery.nth( elem, 2, "nextSibling" );
    },
    prev: function( elem ) {
        return jQuery.nth( elem, 2, "previousSibling" );
    },
    nextAll: function( elem ) {
        return jQuery.dir( elem, "nextSibling" );
    },
    prevAll: function( elem ) {
        return jQuery.dir( elem, "previousSibling" );
    },
    nextUntil: function( elem, i, until ) {
        return jQuery.dir( elem, "nextSibling", until );
    },
    prevUntil: function( elem, i, until ) {
        return jQuery.dir( elem, "previousSibling", until );
    },
    siblings: function( elem ) {
        return jQuery.sibling( elem.parentNode.firstChild, elem );
    },
    children: function( elem ) {
        return jQuery.sibling( elem.firstChild );
    },
    contents: function( elem ) {
        return jQuery.nodeName( elem, "iframe" ) ?
            elem.contentDocument || elem.contentWindow.document :
            jQuery.makeArray( elem.childNodes );
    }
}, function( name, fn ) {
    jQuery.fn[ name ] = function( until, selector ) {
        var ret = jQuery.map( this, fn, until );

        if ( !runtil.test( name ) ) {
            selector = until;
        }

        if ( selector && typeof selector === "string" ) {
            ret = jQuery.filter( selector, ret );
        }

        ret = this.length > 1 ? jQuery.unique( ret ) : ret;

        if ( (this.length > 1 || rmultiselector.test( selector )) && rparentsprev.test( name ) ) {
            ret = ret.reverse();
        }

        return this.pushStack( ret, name, slice.call(arguments).join(",") );
    };
});

jQuery.extend({
    filter: function( expr, elems, not ) {
        if ( not ) {
            expr = ":not(" + expr + ")";
        }

        return elems.length === 1 ?
            jQuery.find.matchesSelector(elems[0], expr) ? [ elems[0] ] : [] :
            jQuery.find.matches(expr, elems);
    },

    dir: function( elem, dir, until ) {
        var matched = [],
            cur = elem[ dir ];

        while ( cur && cur.nodeType !== 9 && (until === undefined || cur.nodeType !== 1 || !jQuery( cur ).is( until )) ) {
            if ( cur.nodeType === 1 ) {
                matched.push( cur );
            }
            cur = cur[dir];
        }
        return matched;
    },

    nth: function( cur, result, dir, elem ) {
        result = result || 1;
        var num = 0;

        for ( ; cur; cur = cur[dir] ) {
            if ( cur.nodeType === 1 && ++num === result ) {
                break;
            }
        }

        return cur;
    },

    sibling: function( n, elem ) {
        var r = [];

        for ( ; n; n = n.nextSibling ) {
            if ( n.nodeType === 1 && n !== elem ) {
                r.push( n );
            }
        }

        return r;
    }
});

// Implement the identical functionality for filter and not
function winnow( elements, qualifier, keep ) {
    if ( jQuery.isFunction( qualifier ) ) {
        return jQuery.grep(elements, function( elem, i ) {
            var retVal = !!qualifier.call( elem, i, elem );
            return retVal === keep;
        });

    } else if ( qualifier.nodeType ) {
        return jQuery.grep(elements, function( elem, i ) {
            return (elem === qualifier) === keep;
        });

    } else if ( typeof qualifier === "string" ) {
        var filtered = jQuery.grep(elements, function( elem ) {
            return elem.nodeType === 1;
        });

        if ( isSimple.test( qualifier ) ) {
            return jQuery.filter(qualifier, filtered, !keep);
        } else {
            qualifier = jQuery.filter( qualifier, filtered );
        }
    }

    return jQuery.grep(elements, function( elem, i ) {
        return (jQuery.inArray( elem, qualifier ) >= 0) === keep;
    });
}




var rinlinejQuery = / jQuery\d+="(?:\d+|null)"/g,
    rleadingWhitespace = /^\s+/,
    rxhtmlTag = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/ig,
    rtagName = /<([\w:]+)/,
    rtbody = /<tbody/i,
    rhtml = /<|&#?\w+;/,
    rnocache = /<(?:script|object|embed|option|style)/i,
    // checked="checked" or checked (html5)
    rchecked = /checked\s*(?:[^=]|=\s*.checked.)/i,
    raction = /\=([^="'>\s]+\/)>/g,
    wrapMap = {
        option: [ 1, "<select multiple='multiple'>", "</select>" ],
        legend: [ 1, "<fieldset>", "</fieldset>" ],
        thead: [ 1, "<table>", "</table>" ],
        tr: [ 2, "<table><tbody>", "</tbody></table>" ],
        td: [ 3, "<table><tbody><tr>", "</tr></tbody></table>" ],
        col: [ 2, "<table><tbody></tbody><colgroup>", "</colgroup></table>" ],
        area: [ 1, "<map>", "</map>" ],
        _default: [ 0, "", "" ]
    };

wrapMap.optgroup = wrapMap.option;
wrapMap.tbody = wrapMap.tfoot = wrapMap.colgroup = wrapMap.caption = wrapMap.thead;
wrapMap.th = wrapMap.td;

// IE can't serialize <link> and <script> tags normally
if ( !jQuery.support.htmlSerialize ) {
    wrapMap._default = [ 1, "div<div>", "</div>" ];
}

jQuery.fn.extend({
    text: function( text ) {
        if ( jQuery.isFunction(text) ) {
            return this.each(function(i) {
                var self = jQuery( this );

                self.text( text.call(this, i, self.text()) );
            });
        }

        if ( typeof text !== "object" && text !== undefined ) {
            return this.empty().append( (this[0] && this[0].ownerDocument || document).createTextNode( text ) );
        }

        return jQuery.text( this );
    },

    wrapAll: function( html ) {
        if ( jQuery.isFunction( html ) ) {
            return this.each(function(i) {
                jQuery(this).wrapAll( html.call(this, i) );
            });
        }

        if ( this[0] ) {
            // The elements to wrap the target around
            var wrap = jQuery( html, this[0].ownerDocument ).eq(0).clone(true);

            if ( this[0].parentNode ) {
                wrap.insertBefore( this[0] );
            }

            wrap.map(function() {
                var elem = this;

                while ( elem.firstChild && elem.firstChild.nodeType === 1 ) {
                    elem = elem.firstChild;
                }

                return elem;
            }).append(this);
        }

        return this;
    },

    wrapInner: function( html ) {
        if ( jQuery.isFunction( html ) ) {
            return this.each(function(i) {
                jQuery(this).wrapInner( html.call(this, i) );
            });
        }

        return this.each(function() {
            var self = jQuery( this ),
                contents = self.contents();

            if ( contents.length ) {
                contents.wrapAll( html );

            } else {
                self.append( html );
            }
        });
    },

    wrap: function( html ) {
        return this.each(function() {
            jQuery( this ).wrapAll( html );
        });
    },

    unwrap: function() {
        return this.parent().each(function() {
            if ( !jQuery.nodeName( this, "body" ) ) {
                jQuery( this ).replaceWith( this.childNodes );
            }
        }).end();
    },

    append: function() {
        return this.domManip(arguments, true, function( elem ) {
            if ( this.nodeType === 1 ) {
                this.appendChild( elem );
            }
        });
    },

    prepend: function() {
        return this.domManip(arguments, true, function( elem ) {
            if ( this.nodeType === 1 ) {
                this.insertBefore( elem, this.firstChild );
            }
        });
    },

    before: function() {
        if ( this[0] && this[0].parentNode ) {
            return this.domManip(arguments, false, function( elem ) {
                this.parentNode.insertBefore( elem, this );
            });
        } else if ( arguments.length ) {
            var set = jQuery(arguments[0]);
            set.push.apply( set, this.toArray() );
            return this.pushStack( set, "before", arguments );
        }
    },

    after: function() {
        if ( this[0] && this[0].parentNode ) {
            return this.domManip(arguments, false, function( elem ) {
                this.parentNode.insertBefore( elem, this.nextSibling );
            });
        } else if ( arguments.length ) {
            var set = this.pushStack( this, "after", arguments );
            set.push.apply( set, jQuery(arguments[0]).toArray() );
            return set;
        }
    },

    // keepData is for internal use only--do not document
    remove: function( selector, keepData ) {
        for ( var i = 0, elem; (elem = this[i]) != null; i++ ) {
            if ( !selector || jQuery.filter( selector, [ elem ] ).length ) {
                if ( !keepData && elem.nodeType === 1 ) {
                    jQuery.cleanData( elem.getElementsByTagName("*") );
                    jQuery.cleanData( [ elem ] );
                }

                if ( elem.parentNode ) {
                     elem.parentNode.removeChild( elem );
                }
            }
        }

        return this;
    },

    empty: function() {
        for ( var i = 0, elem; (elem = this[i]) != null; i++ ) {
            // Remove element nodes and prevent memory leaks
            if ( elem.nodeType === 1 ) {
                jQuery.cleanData( elem.getElementsByTagName("*") );
            }

            // Remove any remaining nodes
            while ( elem.firstChild ) {
                elem.removeChild( elem.firstChild );
            }
        }

        return this;
    },

    clone: function( events ) {
        // Do the clone
        var ret = this.map(function() {
            if ( !jQuery.support.noCloneEvent && !jQuery.isXMLDoc(this) ) {
                // IE copies events bound via attachEvent when
                // using cloneNode. Calling detachEvent on the
                // clone will also remove the events from the orignal
                // In order to get around this, we use innerHTML.
                // Unfortunately, this means some modifications to
                // attributes in IE that are actually only stored
                // as properties will not be copied (such as the
                // the name attribute on an input).
                var html = this.outerHTML,
                    ownerDocument = this.ownerDocument;

                if ( !html ) {
                    var div = ownerDocument.createElement("div");
                    div.appendChild( this.cloneNode(true) );
                    html = div.innerHTML;
                }

                return jQuery.clean([html.replace(rinlinejQuery, "")
                    // Handle the case in IE 8 where action=/test/> self-closes a tag
                    .replace(raction, '="$1">')
                    .replace(rleadingWhitespace, "")], ownerDocument)[0];
            } else {
                return this.cloneNode(true);
            }
        });

        // Copy the events from the original to the clone
        if ( events === true ) {
            cloneCopyEvent( this, ret );
            cloneCopyEvent( this.find("*"), ret.find("*") );
        }

        // Return the cloned set
        return ret;
    },

    html: function( value ) {
        if ( value === undefined ) {
            return this[0] && this[0].nodeType === 1 ?
                this[0].innerHTML.replace(rinlinejQuery, "") :
                null;

        // See if we can take a shortcut and just use innerHTML
        } else if ( typeof value === "string" && !rnocache.test( value ) &&
            (jQuery.support.leadingWhitespace || !rleadingWhitespace.test( value )) &&
            !wrapMap[ (rtagName.exec( value ) || ["", ""])[1].toLowerCase() ] ) {

            value = value.replace(rxhtmlTag, "<$1></$2>");

            try {
                for ( var i = 0, l = this.length; i < l; i++ ) {
                    // Remove element nodes and prevent memory leaks
                    if ( this[i].nodeType === 1 ) {
                        jQuery.cleanData( this[i].getElementsByTagName("*") );
                        this[i].innerHTML = value;
                    }
                }

            // If using innerHTML throws an exception, use the fallback method
            } catch(e) {
                this.empty().append( value );
            }

        } else if ( jQuery.isFunction( value ) ) {
            this.each(function(i){
                var self = jQuery( this );

                self.html( value.call(this, i, self.html()) );
            });

        } else {
            this.empty().append( value );
        }

        return this;
    },

    replaceWith: function( value ) {
        if ( this[0] && this[0].parentNode ) {
            // Make sure that the elements are removed from the DOM before they are inserted
            // this can help fix replacing a parent with child elements
            if ( jQuery.isFunction( value ) ) {
                return this.each(function(i) {
                    var self = jQuery(this), old = self.html();
                    self.replaceWith( value.call( this, i, old ) );
                });
            }

            if ( typeof value !== "string" ) {
                value = jQuery( value ).detach();
            }

            return this.each(function() {
                var next = this.nextSibling,
                    parent = this.parentNode;

                jQuery( this ).remove();

                if ( next ) {
                    jQuery(next).before( value );
                } else {
                    jQuery(parent).append( value );
                }
            });
        } else {
            return this.pushStack( jQuery(jQuery.isFunction(value) ? value() : value), "replaceWith", value );
        }
    },

    detach: function( selector ) {
        return this.remove( selector, true );
    },

    domManip: function( args, table, callback ) {
        var results, first, fragment, parent,
            value = args[0],
            scripts = [];

        // We can't cloneNode fragments that contain checked, in WebKit
        if ( !jQuery.support.checkClone && arguments.length === 3 && typeof value === "string" && rchecked.test( value ) ) {
            return this.each(function() {
                jQuery(this).domManip( args, table, callback, true );
            });
        }

        if ( jQuery.isFunction(value) ) {
            return this.each(function(i) {
                var self = jQuery(this);
                args[0] = value.call(this, i, table ? self.html() : undefined);
                self.domManip( args, table, callback );
            });
        }

        if ( this[0] ) {
            parent = value && value.parentNode;

            // If we're in a fragment, just use that instead of building a new one
            if ( jQuery.support.parentNode && parent && parent.nodeType === 11 && parent.childNodes.length === this.length ) {
                results = { fragment: parent };

            } else {
                results = jQuery.buildFragment( args, this, scripts );
            }

            fragment = results.fragment;

            if ( fragment.childNodes.length === 1 ) {
                first = fragment = fragment.firstChild;
            } else {
                first = fragment.firstChild;
            }

            if ( first ) {
                table = table && jQuery.nodeName( first, "tr" );

                for ( var i = 0, l = this.length; i < l; i++ ) {
                    callback.call(
                        table ?
                            root(this[i], first) :
                            this[i],
                        i > 0 || results.cacheable || this.length > 1  ?
                            fragment.cloneNode(true) :
                            fragment
                    );
                }
            }

            if ( scripts.length ) {
                jQuery.each( scripts, evalScript );
            }
        }

        return this;
    }
});

function root( elem, cur ) {
    return jQuery.nodeName(elem, "table") ?
        (elem.getElementsByTagName("tbody")[0] ||
        elem.appendChild(elem.ownerDocument.createElement("tbody"))) :
        elem;
}

function cloneCopyEvent(orig, ret) {
    var i = 0;

    ret.each(function() {
        if ( this.nodeName !== (orig[i] && orig[i].nodeName) ) {
            return;
        }

        var oldData = jQuery.data( orig[i++] ),
            curData = jQuery.data( this, oldData ),
            events = oldData && oldData.events;

        if ( events ) {
            delete curData.handle;
            curData.events = {};

            for ( var type in events ) {
                for ( var handler in events[ type ] ) {
                    jQuery.event.add( this, type, events[ type ][ handler ], events[ type ][ handler ].data );
                }
            }
        }
    });
}

jQuery.buildFragment = function( args, nodes, scripts ) {
    var fragment, cacheable, cacheresults,
        doc = (nodes && nodes[0] ? nodes[0].ownerDocument || nodes[0] : document);

    // Only cache "small" (1/2 KB) strings that are associated with the main document
    // Cloning options loses the selected state, so don't cache them
    // IE 6 doesn't like it when you put <object> or <embed> elements in a fragment
    // Also, WebKit does not clone 'checked' attributes on cloneNode, so don't cache
    if ( args.length === 1 && typeof args[0] === "string" && args[0].length < 512 && doc === document &&
        !rnocache.test( args[0] ) && (jQuery.support.checkClone || !rchecked.test( args[0] )) ) {

        cacheable = true;
        cacheresults = jQuery.fragments[ args[0] ];
        if ( cacheresults ) {
            if ( cacheresults !== 1 ) {
                fragment = cacheresults;
            }
        }
    }

    if ( !fragment ) {
        fragment = doc.createDocumentFragment();
        jQuery.clean( args, doc, fragment, scripts );
    }

    if ( cacheable ) {
        jQuery.fragments[ args[0] ] = cacheresults ? fragment : 1;
    }

    return { fragment: fragment, cacheable: cacheable };
};

jQuery.fragments = {};

jQuery.each({
    appendTo: "append",
    prependTo: "prepend",
    insertBefore: "before",
    insertAfter: "after",
    replaceAll: "replaceWith"
}, function( name, original ) {
    jQuery.fn[ name ] = function( selector ) {
        var ret = [],
            insert = jQuery( selector ),
            parent = this.length === 1 && this[0].parentNode;

        if ( parent && parent.nodeType === 11 && parent.childNodes.length === 1 && insert.length === 1 ) {
            insert[ original ]( this[0] );
            return this;

        } else {
            for ( var i = 0, l = insert.length; i < l; i++ ) {
                var elems = (i > 0 ? this.clone(true) : this).get();
                jQuery( insert[i] )[ original ]( elems );
                ret = ret.concat( elems );
            }

            return this.pushStack( ret, name, insert.selector );
        }
    };
});

jQuery.extend({
    clean: function( elems, context, fragment, scripts ) {
        context = context || document;

        // !context.createElement fails in IE with an error but returns typeof 'object'
        if ( typeof context.createElement === "undefined" ) {
            context = context.ownerDocument || context[0] && context[0].ownerDocument || document;
        }

        var ret = [];

        for ( var i = 0, elem; (elem = elems[i]) != null; i++ ) {
            if ( typeof elem === "number" ) {
                elem += "";
            }

            if ( !elem ) {
                continue;
            }

            // Convert html string into DOM nodes
            if ( typeof elem === "string" && !rhtml.test( elem ) ) {
                elem = context.createTextNode( elem );

            } else if ( typeof elem === "string" ) {
                // Fix "XHTML"-style tags in all browsers
                elem = elem.replace(rxhtmlTag, "<$1></$2>");

                // Trim whitespace, otherwise indexOf won't work as expected
                var tag = (rtagName.exec( elem ) || ["", ""])[1].toLowerCase(),
                    wrap = wrapMap[ tag ] || wrapMap._default,
                    depth = wrap[0],
                    div = context.createElement("div");

                // Go to html and back, then peel off extra wrappers
                div.innerHTML = wrap[1] + elem + wrap[2];

                // Move to the right depth
                while ( depth-- ) {
                    div = div.lastChild;
                }

                // Remove IE's autoinserted <tbody> from table fragments
                if ( !jQuery.support.tbody ) {

                    // String was a <table>, *may* have spurious <tbody>
                    var hasBody = rtbody.test(elem),
                        tbody = tag === "table" && !hasBody ?
                            div.firstChild && div.firstChild.childNodes :

                            // String was a bare <thead> or <tfoot>
                            wrap[1] === "<table>" && !hasBody ?
                                div.childNodes :
                                [];

                    for ( var j = tbody.length - 1; j >= 0 ; --j ) {
                        if ( jQuery.nodeName( tbody[ j ], "tbody" ) && !tbody[ j ].childNodes.length ) {
                            tbody[ j ].parentNode.removeChild( tbody[ j ] );
                        }
                    }

                }

                // IE completely kills leading whitespace when innerHTML is used
                if ( !jQuery.support.leadingWhitespace && rleadingWhitespace.test( elem ) ) {
                    div.insertBefore( context.createTextNode( rleadingWhitespace.exec(elem)[0] ), div.firstChild );
                }

                elem = div.childNodes;
            }

            if ( elem.nodeType ) {
                ret.push( elem );
            } else {
                ret = jQuery.merge( ret, elem );
            }
        }

        if ( fragment ) {
            for ( i = 0; ret[i]; i++ ) {
                if ( scripts && jQuery.nodeName( ret[i], "script" ) && (!ret[i].type || ret[i].type.toLowerCase() === "text/javascript") ) {
                    scripts.push( ret[i].parentNode ? ret[i].parentNode.removeChild( ret[i] ) : ret[i] );

                } else {
                    if ( ret[i].nodeType === 1 ) {
                        ret.splice.apply( ret, [i + 1, 0].concat(jQuery.makeArray(ret[i].getElementsByTagName("script"))) );
                    }
                    fragment.appendChild( ret[i] );
                }
            }
        }

        return ret;
    },

    cleanData: function( elems ) {
        var data, id, cache = jQuery.cache,
            special = jQuery.event.special,
            deleteExpando = jQuery.support.deleteExpando;

        for ( var i = 0, elem; (elem = elems[i]) != null; i++ ) {
            if ( elem.nodeName && jQuery.noData[elem.nodeName.toLowerCase()] ) {
                continue;
            }

            id = elem[ jQuery.expando ];

            if ( id ) {
                data = cache[ id ];

                if ( data && data.events ) {
                    for ( var type in data.events ) {
                        if ( special[ type ] ) {
                            jQuery.event.remove( elem, type );

                        } else {
                            jQuery.removeEvent( elem, type, data.handle );
                        }
                    }
                }

                if ( deleteExpando ) {
                    delete elem[ jQuery.expando ];

                } else if ( elem.removeAttribute ) {
                    elem.removeAttribute( jQuery.expando );
                }

                delete cache[ id ];
            }
        }
    }
});

function evalScript( i, elem ) {
    if ( elem.src ) {
        jQuery.ajax({
            url: elem.src,
            async: false,
            dataType: "script"
        });
    } else {
        jQuery.globalEval( elem.text || elem.textContent || elem.innerHTML || "" );
    }

    if ( elem.parentNode ) {
        elem.parentNode.removeChild( elem );
    }
}




var ralpha = /alpha\([^)]*\)/i,
    ropacity = /opacity=([^)]*)/,
    rdashAlpha = /-([a-z])/ig,
    rupper = /([A-Z])/g,
    rnumpx = /^-?\d+(?:px)?$/i,
    rnum = /^-?\d/,

    cssShow = { position: "absolute", visibility: "hidden", display: "block" },
    cssWidth = [ "Left", "Right" ],
    cssHeight = [ "Top", "Bottom" ],
    curCSS,

    getComputedStyle,
    currentStyle,

    fcamelCase = function( all, letter ) {
        return letter.toUpperCase();
    };

jQuery.fn.css = function( name, value ) {
    // Setting 'undefined' is a no-op
    if ( arguments.length === 2 && value === undefined ) {
        return this;
    }

    return jQuery.access( this, name, value, true, function( elem, name, value ) {
        return value !== undefined ?
            jQuery.style( elem, name, value ) :
            jQuery.css( elem, name );
    });
};

jQuery.extend({
    // Add in style property hooks for overriding the default
    // behavior of getting and setting a style property
    cssHooks: {
        opacity: {
            get: function( elem, computed ) {
                if ( computed ) {
                    // We should always get a number back from opacity
                    var ret = curCSS( elem, "opacity", "opacity" );
                    return ret === "" ? "1" : ret;

                } else {
                    return elem.style.opacity;
                }
            }
        }
    },

    // Exclude the following css properties to add px
    cssNumber: {
        "zIndex": true,
        "fontWeight": true,
        "opacity": true,
        "zoom": true,
        "lineHeight": true
    },

    // Add in properties whose names you wish to fix before
    // setting or getting the value
    cssProps: {
        // normalize float css property
        "float": jQuery.support.cssFloat ? "cssFloat" : "styleFloat"
    },

    // Get and set the style property on a DOM Node
    style: function( elem, name, value, extra ) {
        // Don't set styles on text and comment nodes
        if ( !elem || elem.nodeType === 3 || elem.nodeType === 8 || !elem.style ) {
            return;
        }

        // Make sure that we're working with the right name
        var ret, origName = jQuery.camelCase( name ),
            style = elem.style, hooks = jQuery.cssHooks[ origName ];

        name = jQuery.cssProps[ origName ] || origName;

        // Check if we're setting a value
        if ( value !== undefined ) {
            // Make sure that NaN and null values aren't set. See: #7116
            if ( typeof value === "number" && isNaN( value ) || value == null ) {
                return;
            }

            // If a number was passed in, add 'px' to the (except for certain CSS properties)
            if ( typeof value === "number" && !jQuery.cssNumber[ origName ] ) {
                value += "px";
            }

            // If a hook was provided, use that value, otherwise just set the specified value
            if ( !hooks || !("set" in hooks) || (value = hooks.set( elem, value )) !== undefined ) {
                // Wrapped to prevent IE from throwing errors when 'invalid' values are provided
                // Fixes bug #5509
                try {
                    style[ name ] = value;
                } catch(e) {}
            }

        } else {
            // If a hook was provided get the non-computed value from there
            if ( hooks && "get" in hooks && (ret = hooks.get( elem, false, extra )) !== undefined ) {
                return ret;
            }

            // Otherwise just get the value from the style object
            return style[ name ];
        }
    },

    css: function( elem, name, extra ) {
        // Make sure that we're working with the right name
        var ret, origName = jQuery.camelCase( name ),
            hooks = jQuery.cssHooks[ origName ];

        name = jQuery.cssProps[ origName ] || origName;

        // If a hook was provided get the computed value from there
        if ( hooks && "get" in hooks && (ret = hooks.get( elem, true, extra )) !== undefined ) {
            return ret;

        // Otherwise, if a way to get the computed value exists, use that
        } else if ( curCSS ) {
            return curCSS( elem, name, origName );
        }
    },

    // A method for quickly swapping in/out CSS properties to get correct calculations
    swap: function( elem, options, callback ) {
        var old = {};

        // Remember the old values, and insert the new ones
        for ( var name in options ) {
            old[ name ] = elem.style[ name ];
            elem.style[ name ] = options[ name ];
        }

        callback.call( elem );

        // Revert the old values
        for ( name in options ) {
            elem.style[ name ] = old[ name ];
        }
    },

    camelCase: function( string ) {
        return string.replace( rdashAlpha, fcamelCase );
    }
});

// DEPRECATED, Use jQuery.css() instead
jQuery.curCSS = jQuery.css;

jQuery.each(["height", "width"], function( i, name ) {
    jQuery.cssHooks[ name ] = {
        get: function( elem, computed, extra ) {
            var val;

            if ( computed ) {
                if ( elem.offsetWidth !== 0 ) {
                    val = getWH( elem, name, extra );

                } else {
                    jQuery.swap( elem, cssShow, function() {
                        val = getWH( elem, name, extra );
                    });
                }

                if ( val <= 0 ) {
                    val = curCSS( elem, name, name );

                    if ( val === "0px" && currentStyle ) {
                        val = currentStyle( elem, name, name );
                    }

                    if ( val != null ) {
                        // Should return "auto" instead of 0, use 0 for
                        // temporary backwards-compat
                        return val === "" || val === "auto" ? "0px" : val;
                    }
                }

                if ( val < 0 || val == null ) {
                    val = elem.style[ name ];

                    // Should return "auto" instead of 0, use 0 for
                    // temporary backwards-compat
                    return val === "" || val === "auto" ? "0px" : val;
                }

                return typeof val === "string" ? val : val + "px";
            }
        },

        set: function( elem, value ) {
            if ( rnumpx.test( value ) ) {
                // ignore negative width and height values #1599
                value = parseFloat(value);

                if ( value >= 0 ) {
                    return value + "px";
                }

            } else {
                return value;
            }
        }
    };
});

if ( !jQuery.support.opacity ) {
    jQuery.cssHooks.opacity = {
        get: function( elem, computed ) {
            // IE uses filters for opacity
            return ropacity.test((computed && elem.currentStyle ? elem.currentStyle.filter : elem.style.filter) || "") ?
                (parseFloat(RegExp.$1) / 100) + "" :
                computed ? "1" : "";
        },

        set: function( elem, value ) {
            var style = elem.style;

            // IE has trouble with opacity if it does not have layout
            // Force it by setting the zoom level
            style.zoom = 1;

            // Set the alpha filter to set the opacity
            var opacity = jQuery.isNaN(value) ?
                "" :
                "alpha(opacity=" + value * 100 + ")",
                filter = style.filter || "";

            style.filter = ralpha.test(filter) ?
                filter.replace(ralpha, opacity) :
                style.filter + ' ' + opacity;
        }
    };
}

if ( document.defaultView && document.defaultView.getComputedStyle ) {
    getComputedStyle = function( elem, newName, name ) {
        var ret, defaultView, computedStyle;

        name = name.replace( rupper, "-$1" ).toLowerCase();

        if ( !(defaultView = elem.ownerDocument.defaultView) ) {
            return undefined;
        }

        if ( (computedStyle = defaultView.getComputedStyle( elem, null )) ) {
            ret = computedStyle.getPropertyValue( name );
            if ( ret === "" && !jQuery.contains( elem.ownerDocument.documentElement, elem ) ) {
                ret = jQuery.style( elem, name );
            }
        }

        return ret;
    };
}

if ( document.documentElement.currentStyle ) {
    currentStyle = function( elem, name ) {
        var left, rsLeft,
            ret = elem.currentStyle && elem.currentStyle[ name ],
            style = elem.style;

        // From the awesome hack by Dean Edwards
        // http://erik.eae.net/archives/2007/07/27/18.54.15/#comment-102291

        // If we're not dealing with a regular pixel number
        // but a number that has a weird ending, we need to convert it to pixels
        if ( !rnumpx.test( ret ) && rnum.test( ret ) ) {
            // Remember the original values
            left = style.left;
            rsLeft = elem.runtimeStyle.left;

            // Put in the new values to get a computed value out
            elem.runtimeStyle.left = elem.currentStyle.left;
            style.left = name === "fontSize" ? "1em" : (ret || 0);
            ret = style.pixelLeft + "px";

            // Revert the changed values
            style.left = left;
            elem.runtimeStyle.left = rsLeft;
        }

        return ret === "" ? "auto" : ret;
    };
}

curCSS = getComputedStyle || currentStyle;

function getWH( elem, name, extra ) {
    var which = name === "width" ? cssWidth : cssHeight,
        val = name === "width" ? elem.offsetWidth : elem.offsetHeight;

    if ( extra === "border" ) {
        return val;
    }

    jQuery.each( which, function() {
        if ( !extra ) {
            val -= parseFloat(jQuery.css( elem, "padding" + this )) || 0;
        }

        if ( extra === "margin" ) {
            val += parseFloat(jQuery.css( elem, "margin" + this )) || 0;

        } else {
            val -= parseFloat(jQuery.css( elem, "border" + this + "Width" )) || 0;
        }
    });

    return val;
}

if ( jQuery.expr && jQuery.expr.filters ) {
    jQuery.expr.filters.hidden = function( elem ) {
        var width = elem.offsetWidth,
            height = elem.offsetHeight;

        return (width === 0 && height === 0) || (!jQuery.support.reliableHiddenOffsets && (elem.style.display || jQuery.css( elem, "display" )) === "none");
    };

    jQuery.expr.filters.visible = function( elem ) {
        return !jQuery.expr.filters.hidden( elem );
    };
}




var jsc = jQuery.now(),
    rscript = /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi,
    rselectTextarea = /^(?:select|textarea)/i,
    rinput = /^(?:color|date|datetime|email|hidden|month|number|password|range|search|tel|text|time|url|week)$/i,
    rnoContent = /^(?:GET|HEAD)$/,
    rbracket = /\[\]$/,
    jsre = /\=\?(&|$)/,
    rquery = /\?/,
    rts = /([?&])_=[^&]*/,
    rurl = /^(\w+:)?\/\/([^\/?#]+)/,
    r20 = /%20/g,
    rhash = /#.*$/,

    // Keep a copy of the old load method
    _load = jQuery.fn.load;

jQuery.fn.extend({
    load: function( url, params, callback ) {
        if ( typeof url !== "string" && _load ) {
            return _load.apply( this, arguments );

        // Don't do a request if no elements are being requested
        } else if ( !this.length ) {
            return this;
        }

        var off = url.indexOf(" ");
        if ( off >= 0 ) {
            var selector = url.slice(off, url.length);
            url = url.slice(0, off);
        }

        // Default to a GET request
        var type = "GET";

        // If the second parameter was provided
        if ( params ) {
            // If it's a function
            if ( jQuery.isFunction( params ) ) {
                // We assume that it's the callback
                callback = params;
                params = null;

            // Otherwise, build a param string
            } else if ( typeof params === "object" ) {
                params = jQuery.param( params, jQuery.ajaxSettings.traditional );
                type = "POST";
            }
        }

        var self = this;

        // Request the remote document
        jQuery.ajax({
            url: url,
            type: type,
            dataType: "html",
            data: params,
            complete: function( res, status ) {
                // If successful, inject the HTML into all the matched elements
                if ( status === "success" || status === "notmodified" ) {
                    // See if a selector was specified
                    self.html( selector ?
                        // Create a dummy div to hold the results
                        jQuery("<div>")
                            // inject the contents of the document in, removing the scripts
                            // to avoid any 'Permission Denied' errors in IE
                            .append(res.responseText.replace(rscript, ""))

                            // Locate the specified elements
                            .find(selector) :

                        // If not, just inject the full result
                        res.responseText );
                }

                if ( callback ) {
                    self.each( callback, [res.responseText, status, res] );
                }
            }
        });

        return this;
    },

    serialize: function() {
        return jQuery.param(this.serializeArray());
    },

    serializeArray: function() {
        return this.map(function() {
            return this.elements ? jQuery.makeArray(this.elements) : this;
        })
        .filter(function() {
            return this.name && !this.disabled &&
                (this.checked || rselectTextarea.test(this.nodeName) ||
                    rinput.test(this.type));
        })
        .map(function( i, elem ) {
            var val = jQuery(this).val();

            return val == null ?
                null :
                jQuery.isArray(val) ?
                    jQuery.map( val, function( val, i ) {
                        return { name: elem.name, value: val };
                    }) :
                    { name: elem.name, value: val };
        }).get();
    }
});

// Attach a bunch of functions for handling common AJAX events
jQuery.each( "ajaxStart ajaxStop ajaxComplete ajaxError ajaxSuccess ajaxSend".split(" "), function( i, o ) {
    jQuery.fn[o] = function( f ) {
        return this.bind(o, f);
    };
});

jQuery.extend({
    get: function( url, data, callback, type ) {
        // shift arguments if data argument was omited
        if ( jQuery.isFunction( data ) ) {
            type = type || callback;
            callback = data;
            data = null;
        }

        return jQuery.ajax({
            type: "GET",
            url: url,
            data: data,
            success: callback,
            dataType: type
        });
    },

    getScript: function( url, callback ) {
        return jQuery.get(url, null, callback, "script");
    },

    getJSON: function( url, data, callback ) {
        return jQuery.get(url, data, callback, "json");
    },

    post: function( url, data, callback, type ) {
        // shift arguments if data argument was omited
        if ( jQuery.isFunction( data ) ) {
            type = type || callback;
            callback = data;
            data = {};
        }

        return jQuery.ajax({
            type: "POST",
            url: url,
            data: data,
            success: callback,
            dataType: type
        });
    },

    ajaxSetup: function( settings ) {
        jQuery.extend( jQuery.ajaxSettings, settings );
    },

    ajaxSettings: {
        url: location.href,
        global: true,
        type: "GET",
        contentType: "application/x-www-form-urlencoded",
        processData: true,
        async: true,
        /*
        timeout: 0,
        data: null,
        username: null,
        password: null,
        traditional: false,
        */
        // This function can be overriden by calling jQuery.ajaxSetup
        xhr: function() {
            return new window.XMLHttpRequest();
        },
        accepts: {
            xml: "application/xml, text/xml",
            html: "text/html",
            script: "text/javascript, application/javascript",
            json: "application/json, text/javascript",
            text: "text/plain",
            _default: "*/*"
        }
    },

    ajax: function( origSettings ) {
        var s = jQuery.extend(true, {}, jQuery.ajaxSettings, origSettings),
            jsonp, status, data, type = s.type.toUpperCase(), noContent = rnoContent.test(type);

        s.url = s.url.replace( rhash, "" );

        // Use original (not extended) context object if it was provided
        s.context = origSettings && origSettings.context != null ? origSettings.context : s;

        // convert data if not already a string
        if ( s.data && s.processData && typeof s.data !== "string" ) {
            s.data = jQuery.param( s.data, s.traditional );
        }

        // Handle JSONP Parameter Callbacks
        if ( s.dataType === "jsonp" ) {
            if ( type === "GET" ) {
                if ( !jsre.test( s.url ) ) {
                    s.url += (rquery.test( s.url ) ? "&" : "?") + (s.jsonp || "callback") + "=?";
                }
            } else if ( !s.data || !jsre.test(s.data) ) {
                s.data = (s.data ? s.data + "&" : "") + (s.jsonp || "callback") + "=?";
            }
            s.dataType = "json";
        }

        // Build temporary JSONP function
        if ( s.dataType === "json" && (s.data && jsre.test(s.data) || jsre.test(s.url)) ) {
            jsonp = s.jsonpCallback || ("jsonp" + jsc++);

            // Replace the =? sequence both in the query string and the data
            if ( s.data ) {
                s.data = (s.data + "").replace(jsre, "=" + jsonp + "$1");
            }

            s.url = s.url.replace(jsre, "=" + jsonp + "$1");

            // We need to make sure
            // that a JSONP style response is executed properly
            s.dataType = "script";

            // Handle JSONP-style loading
            var customJsonp = window[ jsonp ];

            window[ jsonp ] = function( tmp ) {
                if ( jQuery.isFunction( customJsonp ) ) {
                    customJsonp( tmp );

                } else {
                    // Garbage collect
                    window[ jsonp ] = undefined;

                    try {
                        delete window[ jsonp ];
                    } catch( jsonpError ) {}
                }

                data = tmp;
                jQuery.handleSuccess( s, xhr, status, data );
                jQuery.handleComplete( s, xhr, status, data );

                if ( head ) {
                    head.removeChild( script );
                }
            };
        }

        if ( s.dataType === "script" && s.cache === null ) {
            s.cache = false;
        }

        if ( s.cache === false && noContent ) {
            var ts = jQuery.now();

            // try replacing _= if it is there
            var ret = s.url.replace(rts, "$1_=" + ts);

            // if nothing was replaced, add timestamp to the end
            s.url = ret + ((ret === s.url) ? (rquery.test(s.url) ? "&" : "?") + "_=" + ts : "");
        }

        // If data is available, append data to url for GET/HEAD requests
        if ( s.data && noContent ) {
            s.url += (rquery.test(s.url) ? "&" : "?") + s.data;
        }

        // Watch for a new set of requests
        if ( s.global && jQuery.active++ === 0 ) {
            jQuery.event.trigger( "ajaxStart" );
        }

        // Matches an absolute URL, and saves the domain
        var parts = rurl.exec( s.url ),
            remote = parts && (parts[1] && parts[1].toLowerCase() !== location.protocol || parts[2].toLowerCase() !== location.host);

        // If we're requesting a remote document
        // and trying to load JSON or Script with a GET
        if ( s.dataType === "script" && type === "GET" && remote ) {
            var head = document.getElementsByTagName("head")[0] || document.documentElement;
            var script = document.createElement("script");
            if ( s.scriptCharset ) {
                script.charset = s.scriptCharset;
            }
            script.src = s.url;

            // Handle Script loading
            if ( !jsonp ) {
                var done = false;

                // Attach handlers for all browsers
                script.onload = script.onreadystatechange = function() {
                    if ( !done && (!this.readyState ||
                            this.readyState === "loaded" || this.readyState === "complete") ) {
                        done = true;
                        jQuery.handleSuccess( s, xhr, status, data );
                        jQuery.handleComplete( s, xhr, status, data );

                        // Handle memory leak in IE
                        script.onload = script.onreadystatechange = null;
                        if ( head && script.parentNode ) {
                            head.removeChild( script );
                        }
                    }
                };
            }

            // Use insertBefore instead of appendChild  to circumvent an IE6 bug.
            // This arises when a base node is used (#2709 and #4378).
            head.insertBefore( script, head.firstChild );

            // We handle everything using the script element injection
            return undefined;
        }

        var requestDone = false;

        // Create the request object
        var xhr = s.xhr();

        if ( !xhr ) {
            return;
        }

        // Open the socket
        // Passing null username, generates a login popup on Opera (#2865)
        if ( s.username ) {
            xhr.open(type, s.url, s.async, s.username, s.password);
        } else {
            xhr.open(type, s.url, s.async);
        }

        // Need an extra try/catch for cross domain requests in Firefox 3
        try {
            // Set content-type if data specified and content-body is valid for this type
            if ( (s.data != null && !noContent) || (origSettings && origSettings.contentType) ) {
                xhr.setRequestHeader("Content-Type", s.contentType);
            }

            // Set the If-Modified-Since and/or If-None-Match header, if in ifModified mode.
            if ( s.ifModified ) {
                if ( jQuery.lastModified[s.url] ) {
                    xhr.setRequestHeader("If-Modified-Since", jQuery.lastModified[s.url]);
                }

                if ( jQuery.etag[s.url] ) {
                    xhr.setRequestHeader("If-None-Match", jQuery.etag[s.url]);
                }
            }

            // Set header so the called script knows that it's an XMLHttpRequest
            // Only send the header if it's not a remote XHR
            if ( !remote ) {
                xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
            }

            // Set the Accepts header for the server, depending on the dataType
            xhr.setRequestHeader("Accept", s.dataType && s.accepts[ s.dataType ] ?
                s.accepts[ s.dataType ] + ", */*; q=0.01" :
                s.accepts._default );
        } catch( headerError ) {}

        // Allow custom headers/mimetypes and early abort
        if ( s.beforeSend && s.beforeSend.call(s.context, xhr, s) === false ) {
            // Handle the global AJAX counter
            if ( s.global && jQuery.active-- === 1 ) {
                jQuery.event.trigger( "ajaxStop" );
            }

            // close opended socket
            xhr.abort();
            return false;
        }

        if ( s.global ) {
            jQuery.triggerGlobal( s, "ajaxSend", [xhr, s] );
        }

        // Wait for a response to come back
        var onreadystatechange = xhr.onreadystatechange = function( isTimeout ) {
            // The request was aborted
            if ( !xhr || xhr.readyState === 0 || isTimeout === "abort" ) {
                // Opera doesn't call onreadystatechange before this point
                // so we simulate the call
                if ( !requestDone ) {
                    jQuery.handleComplete( s, xhr, status, data );
                }

                requestDone = true;
                if ( xhr ) {
                    xhr.onreadystatechange = jQuery.noop;
                }

            // The transfer is complete and the data is available, or the request timed out
            } else if ( !requestDone && xhr && (xhr.readyState === 4 || isTimeout === "timeout") ) {
                requestDone = true;
                xhr.onreadystatechange = jQuery.noop;

                status = isTimeout === "timeout" ?
                    "timeout" :
                    !jQuery.httpSuccess( xhr ) ?
                        "error" :
                        s.ifModified && jQuery.httpNotModified( xhr, s.url ) ?
                            "notmodified" :
                            "success";

                var errMsg;

                if ( status === "success" ) {
                    // Watch for, and catch, XML document parse errors
                    try {
                        // process the data (runs the xml through httpData regardless of callback)
                        data = jQuery.httpData( xhr, s.dataType, s );
                    } catch( parserError ) {
                        status = "parsererror";
                        errMsg = parserError;
                    }
                }

                // Make sure that the request was successful or notmodified
                if ( status === "success" || status === "notmodified" ) {
                    // JSONP handles its own success callback
                    if ( !jsonp ) {
                        jQuery.handleSuccess( s, xhr, status, data );
                    }
                } else {
                    jQuery.handleError( s, xhr, status, errMsg );
                }

                // Fire the complete handlers
                if ( !jsonp ) {
                    jQuery.handleComplete( s, xhr, status, data );
                }

                if ( isTimeout === "timeout" ) {
                    xhr.abort();
                }

                // Stop memory leaks
                if ( s.async ) {
                    xhr = null;
                }
            }
        };

        // Override the abort handler, if we can (IE 6 doesn't allow it, but that's OK)
        // Opera doesn't fire onreadystatechange at all on abort
        try {
            var oldAbort = xhr.abort;
            xhr.abort = function() {
                if ( xhr ) {
                    // oldAbort has no call property in IE7 so
                    // just do it this way, which works in all
                    // browsers
                    Function.prototype.call.call( oldAbort, xhr );
                }

                onreadystatechange( "abort" );
            };
        } catch( abortError ) {}

        // Timeout checker
        if ( s.async && s.timeout > 0 ) {
            setTimeout(function() {
                // Check to see if the request is still happening
                if ( xhr && !requestDone ) {
                    onreadystatechange( "timeout" );
                }
            }, s.timeout);
        }

        // Send the data
        try {
            xhr.send( noContent || s.data == null ? null : s.data );

        } catch( sendError ) {
            jQuery.handleError( s, xhr, null, sendError );

            // Fire the complete handlers
            jQuery.handleComplete( s, xhr, status, data );
        }

        // firefox 1.5 doesn't fire statechange for sync requests
        if ( !s.async ) {
            onreadystatechange();
        }

        // return XMLHttpRequest to allow aborting the request etc.
        return xhr;
    },

    // Serialize an array of form elements or a set of
    // key/values into a query string
    param: function( a, traditional ) {
        var s = [],
            add = function( key, value ) {
                // If value is a function, invoke it and return its value
                value = jQuery.isFunction(value) ? value() : value;
                s[ s.length ] = encodeURIComponent(key) + "=" + encodeURIComponent(value);
            };

        // Set traditional to true for jQuery <= 1.3.2 behavior.
        if ( traditional === undefined ) {
            traditional = jQuery.ajaxSettings.traditional;
        }

        // If an array was passed in, assume that it is an array of form elements.
        if ( jQuery.isArray(a) || a.jquery ) {
            // Serialize the form elements
            jQuery.each( a, function() {
                add( this.name, this.value );
            });

        } else {
            // If traditional, encode the "old" way (the way 1.3.2 or older
            // did it), otherwise encode params recursively.
            for ( var prefix in a ) {
                buildParams( prefix, a[prefix], traditional, add );
            }
        }

        // Return the resulting serialization
        return s.join("&").replace(r20, "+");
    }
});

function buildParams( prefix, obj, traditional, add ) {
    if ( jQuery.isArray(obj) && obj.length ) {
        // Serialize array item.
        jQuery.each( obj, function( i, v ) {
            if ( traditional || rbracket.test( prefix ) ) {
                // Treat each array item as a scalar.
                add( prefix, v );

            } else {
                // If array item is non-scalar (array or object), encode its
                // numeric index to resolve deserialization ambiguity issues.
                // Note that rack (as of 1.0.0) can't currently deserialize
                // nested arrays properly, and attempting to do so may cause
                // a server error. Possible fixes are to modify rack's
                // deserialization algorithm or to provide an option or flag
                // to force array serialization to be shallow.
                buildParams( prefix + "[" + ( typeof v === "object" || jQuery.isArray(v) ? i : "" ) + "]", v, traditional, add );
            }
        });

    } else if ( !traditional && obj != null && typeof obj === "object" ) {
        if ( jQuery.isEmptyObject( obj ) ) {
            add( prefix, "" );

        // Serialize object item.
        } else {
            jQuery.each( obj, function( k, v ) {
                buildParams( prefix + "[" + k + "]", v, traditional, add );
            });
        }

    } else {
        // Serialize scalar item.
        add( prefix, obj );
    }
}

// This is still on the jQuery object... for now
// Want to move this to jQuery.ajax some day
jQuery.extend({

    // Counter for holding the number of active queries
    active: 0,

    // Last-Modified header cache for next request
    lastModified: {},
    etag: {},

    handleError: function( s, xhr, status, e ) {
        // If a local callback was specified, fire it
        if ( s.error ) {
            s.error.call( s.context, xhr, status, e );
        }

        // Fire the global callback
        if ( s.global ) {
            jQuery.triggerGlobal( s, "ajaxError", [xhr, s, e] );
        }
    },

    handleSuccess: function( s, xhr, status, data ) {
        // If a local callback was specified, fire it and pass it the data
        if ( s.success ) {
            s.success.call( s.context, data, status, xhr );
        }

        // Fire the global callback
        if ( s.global ) {
            jQuery.triggerGlobal( s, "ajaxSuccess", [xhr, s] );
        }
    },

    handleComplete: function( s, xhr, status ) {
        // Process result
        if ( s.complete ) {
            s.complete.call( s.context, xhr, status );
        }

        // The request was completed
        if ( s.global ) {
            jQuery.triggerGlobal( s, "ajaxComplete", [xhr, s] );
        }

        // Handle the global AJAX counter
        if ( s.global && jQuery.active-- === 1 ) {
            jQuery.event.trigger( "ajaxStop" );
        }
    },

    triggerGlobal: function( s, type, args ) {
        (s.context && s.context.url == null ? jQuery(s.context) : jQuery.event).trigger(type, args);
    },

    // Determines if an XMLHttpRequest was successful or not
    httpSuccess: function( xhr ) {
        try {
            // IE error sometimes returns 1223 when it should be 204 so treat it as success, see #1450
            return !xhr.status && location.protocol === "file:" ||
                xhr.status >= 200 && xhr.status < 300 ||
                xhr.status === 304 || xhr.status === 1223;
        } catch(e) {}

        return false;
    },

    // Determines if an XMLHttpRequest returns NotModified
    httpNotModified: function( xhr, url ) {
        var lastModified = xhr.getResponseHeader("Last-Modified"),
            etag = xhr.getResponseHeader("Etag");

        if ( lastModified ) {
            jQuery.lastModified[url] = lastModified;
        }

        if ( etag ) {
            jQuery.etag[url] = etag;
        }

        return xhr.status === 304;
    },

    httpData: function( xhr, type, s ) {
        var ct = xhr.getResponseHeader("content-type") || "",
            xml = type === "xml" || !type && ct.indexOf("xml") >= 0,
            data = xml ? xhr.responseXML : xhr.responseText;

        if ( xml && data.documentElement.nodeName === "parsererror" ) {
            jQuery.error( "parsererror" );
        }

        // Allow a pre-filtering function to sanitize the response
        // s is checked to keep backwards compatibility
        if ( s && s.dataFilter ) {
            data = s.dataFilter( data, type );
        }

        // The filter can actually parse the response
        if ( typeof data === "string" ) {
            // Get the JavaScript object, if JSON is used.
            if ( type === "json" || !type && ct.indexOf("json") >= 0 ) {
                data = jQuery.parseJSON( data );

            // If the type is "script", eval it in global context
            } else if ( type === "script" || !type && ct.indexOf("javascript") >= 0 ) {
                jQuery.globalEval( data );
            }
        }

        return data;
    }

});

/*
 * Create the request object; Microsoft failed to properly
 * implement the XMLHttpRequest in IE7 (can't request local files),
 * so we use the ActiveXObject when it is available
 * Additionally XMLHttpRequest can be disabled in IE7/IE8 so
 * we need a fallback.
 */
if ( window.ActiveXObject ) {
    jQuery.ajaxSettings.xhr = function() {
        if ( window.location.protocol !== "file:" ) {
            try {
                return new window.XMLHttpRequest();
            } catch(xhrError) {}
        }

        try {
            return new window.ActiveXObject("Microsoft.XMLHTTP");
        } catch(activeError) {}
    };
}

// Does this browser support XHR requests?
jQuery.support.ajax = !!jQuery.ajaxSettings.xhr();




var elemdisplay = {},
    rfxtypes = /^(?:toggle|show|hide)$/,
    rfxnum = /^([+\-]=)?([\d+.\-]+)(.*)$/,
    timerId,
    fxAttrs = [
        // height animations
        [ "height", "marginTop", "marginBottom", "paddingTop", "paddingBottom" ],
        // width animations
        [ "width", "marginLeft", "marginRight", "paddingLeft", "paddingRight" ],
        // opacity animations
        [ "opacity" ]
    ];

jQuery.fn.extend({
    show: function( speed, easing, callback ) {
        var elem, display;

        if ( speed || speed === 0 ) {
            return this.animate( genFx("show", 3), speed, easing, callback);

        } else {
            for ( var i = 0, j = this.length; i < j; i++ ) {
                elem = this[i];
                display = elem.style.display;

                // Reset the inline display of this element to learn if it is
                // being hidden by cascaded rules or not
                if ( !jQuery.data(elem, "olddisplay") && display === "none" ) {
                    display = elem.style.display = "";
                }

                // Set elements which have been overridden with display: none
                // in a stylesheet to whatever the default browser style is
                // for such an element
                if ( display === "" && jQuery.css( elem, "display" ) === "none" ) {
                    jQuery.data(elem, "olddisplay", defaultDisplay(elem.nodeName));
                }
            }

            // Set the display of most of the elements in a second loop
            // to avoid the constant reflow
            for ( i = 0; i < j; i++ ) {
                elem = this[i];
                display = elem.style.display;

                if ( display === "" || display === "none" ) {
                    elem.style.display = jQuery.data(elem, "olddisplay") || "";
                }
            }

            return this;
        }
    },

    hide: function( speed, easing, callback ) {
        if ( speed || speed === 0 ) {
            return this.animate( genFx("hide", 3), speed, easing, callback);

        } else {
            for ( var i = 0, j = this.length; i < j; i++ ) {
                var display = jQuery.css( this[i], "display" );

                if ( display !== "none" ) {
                    jQuery.data( this[i], "olddisplay", display );
                }
            }

            // Set the display of the elements in a second loop
            // to avoid the constant reflow
            for ( i = 0; i < j; i++ ) {
                this[i].style.display = "none";
            }

            return this;
        }
    },

    // Save the old toggle function
    _toggle: jQuery.fn.toggle,

    toggle: function( fn, fn2, callback ) {
        var bool = typeof fn === "boolean";

        if ( jQuery.isFunction(fn) && jQuery.isFunction(fn2) ) {
            this._toggle.apply( this, arguments );

        } else if ( fn == null || bool ) {
            this.each(function() {
                var state = bool ? fn : jQuery(this).is(":hidden");
                jQuery(this)[ state ? "show" : "hide" ]();
            });

        } else {
            this.animate(genFx("toggle", 3), fn, fn2, callback);
        }

        return this;
    },

    fadeTo: function( speed, to, easing, callback ) {
        return this.filter(":hidden").css("opacity", 0).show().end()
                    .animate({opacity: to}, speed, easing, callback);
    },

    animate: function( prop, speed, easing, callback ) {
        var optall = jQuery.speed(speed, easing, callback);

        if ( jQuery.isEmptyObject( prop ) ) {
            return this.each( optall.complete );
        }

        return this[ optall.queue === false ? "each" : "queue" ](function() {
            // XXX 'this' does not always have a nodeName when running the
            // test suite

            var opt = jQuery.extend({}, optall), p,
                isElement = this.nodeType === 1,
                hidden = isElement && jQuery(this).is(":hidden"),
                self = this;

            for ( p in prop ) {
                var name = jQuery.camelCase( p );

                if ( p !== name ) {
                    prop[ name ] = prop[ p ];
                    delete prop[ p ];
                    p = name;
                }

                if ( prop[p] === "hide" && hidden || prop[p] === "show" && !hidden ) {
                    return opt.complete.call(this);
                }

                if ( isElement && ( p === "height" || p === "width" ) ) {
                    // Make sure that nothing sneaks out
                    // Record all 3 overflow attributes because IE does not
                    // change the overflow attribute when overflowX and
                    // overflowY are set to the same value
                    opt.overflow = [ this.style.overflow, this.style.overflowX, this.style.overflowY ];

                    // Set display property to inline-block for height/width
                    // animations on inline elements that are having width/height
                    // animated
                    if ( jQuery.css( this, "display" ) === "inline" &&
                            jQuery.css( this, "float" ) === "none" ) {
                        if ( !jQuery.support.inlineBlockNeedsLayout ) {
                            this.style.display = "inline-block";

                        } else {
                            var display = defaultDisplay(this.nodeName);

                            // inline-level elements accept inline-block;
                            // block-level elements need to be inline with layout
                            if ( display === "inline" ) {
                                this.style.display = "inline-block";

                            } else {
                                this.style.display = "inline";
                                this.style.zoom = 1;
                            }
                        }
                    }
                }

                if ( jQuery.isArray( prop[p] ) ) {
                    // Create (if needed) and add to specialEasing
                    (opt.specialEasing = opt.specialEasing || {})[p] = prop[p][1];
                    prop[p] = prop[p][0];
                }
            }

            if ( opt.overflow != null ) {
                this.style.overflow = "hidden";
            }

            opt.curAnim = jQuery.extend({}, prop);

            jQuery.each( prop, function( name, val ) {
                var e = new jQuery.fx( self, opt, name );

                if ( rfxtypes.test(val) ) {
                    e[ val === "toggle" ? hidden ? "show" : "hide" : val ]( prop );

                } else {
                    var parts = rfxnum.exec(val),
                        start = e.cur() || 0;

                    if ( parts ) {
                        var end = parseFloat( parts[2] ),
                            unit = parts[3] || "px";

                        // We need to compute starting value
                        if ( unit !== "px" ) {
                            jQuery.style( self, name, (end || 1) + unit);
                            start = ((end || 1) / e.cur()) * start;
                            jQuery.style( self, name, start + unit);
                        }

                        // If a +=/-= token was provided, we're doing a relative animation
                        if ( parts[1] ) {
                            end = ((parts[1] === "-=" ? -1 : 1) * end) + start;
                        }

                        e.custom( start, end, unit );

                    } else {
                        e.custom( start, val, "" );
                    }
                }
            });

            // For JS strict compliance
            return true;
        });
    },

    stop: function( clearQueue, gotoEnd ) {
        var timers = jQuery.timers;

        if ( clearQueue ) {
            this.queue([]);
        }

        this.each(function() {
            // go in reverse order so anything added to the queue during the loop is ignored
            for ( var i = timers.length - 1; i >= 0; i-- ) {
                if ( timers[i].elem === this ) {
                    if (gotoEnd) {
                        // force the next step to be the last
                        timers[i](true);
                    }

                    timers.splice(i, 1);
                }
            }
        });

        // start the next in the queue if the last step wasn't forced
        if ( !gotoEnd ) {
            this.dequeue();
        }

        return this;
    }

});

function genFx( type, num ) {
    var obj = {};

    jQuery.each( fxAttrs.concat.apply([], fxAttrs.slice(0,num)), function() {
        obj[ this ] = type;
    });

    return obj;
}

// Generate shortcuts for custom animations
jQuery.each({
    slideDown: genFx("show", 1),
    slideUp: genFx("hide", 1),
    slideToggle: genFx("toggle", 1),
    fadeIn: { opacity: "show" },
    fadeOut: { opacity: "hide" },
    fadeToggle: { opacity: "toggle" }
}, function( name, props ) {
    jQuery.fn[ name ] = function( speed, easing, callback ) {
        return this.animate( props, speed, easing, callback );
    };
});

jQuery.extend({
    speed: function( speed, easing, fn ) {
        var opt = speed && typeof speed === "object" ? jQuery.extend({}, speed) : {
            complete: fn || !fn && easing ||
                jQuery.isFunction( speed ) && speed,
            duration: speed,
            easing: fn && easing || easing && !jQuery.isFunction(easing) && easing
        };

        opt.duration = jQuery.fx.off ? 0 : typeof opt.duration === "number" ? opt.duration :
            opt.duration in jQuery.fx.speeds ? jQuery.fx.speeds[opt.duration] : jQuery.fx.speeds._default;

        // Queueing
        opt.old = opt.complete;
        opt.complete = function() {
            if ( opt.queue !== false ) {
                jQuery(this).dequeue();
            }
            if ( jQuery.isFunction( opt.old ) ) {
                opt.old.call( this );
            }
        };

        return opt;
    },

    easing: {
        linear: function( p, n, firstNum, diff ) {
            return firstNum + diff * p;
        },
        swing: function( p, n, firstNum, diff ) {
            return ((-Math.cos(p*Math.PI)/2) + 0.5) * diff + firstNum;
        }
    },

    timers: [],

    fx: function( elem, options, prop ) {
        this.options = options;
        this.elem = elem;
        this.prop = prop;

        if ( !options.orig ) {
            options.orig = {};
        }
    }

});

jQuery.fx.prototype = {
    // Simple function for setting a style value
    update: function() {
        if ( this.options.step ) {
            this.options.step.call( this.elem, this.now, this );
        }

        (jQuery.fx.step[this.prop] || jQuery.fx.step._default)( this );
    },

    // Get the current size
    cur: function() {
        if ( this.elem[this.prop] != null && (!this.elem.style || this.elem.style[this.prop] == null) ) {
            return this.elem[ this.prop ];
        }

        var r = parseFloat( jQuery.css( this.elem, this.prop ) );
        return r && r > -10000 ? r : 0;
    },

    // Start an animation from one number to another
    custom: function( from, to, unit ) {
        var self = this,
            fx = jQuery.fx;

        this.startTime = jQuery.now();
        this.start = from;
        this.end = to;
        this.unit = unit || this.unit || "px";
        this.now = this.start;
        this.pos = this.state = 0;

        function t( gotoEnd ) {
            return self.step(gotoEnd);
        }

        t.elem = this.elem;

        if ( t() && jQuery.timers.push(t) && !timerId ) {
            timerId = setInterval(fx.tick, fx.interval);
        }
    },

    // Simple 'show' function
    show: function() {
        // Remember where we started, so that we can go back to it later
        this.options.orig[this.prop] = jQuery.style( this.elem, this.prop );
        this.options.show = true;

        // Begin the animation
        // Make sure that we start at a small width/height to avoid any
        // flash of content
        this.custom(this.prop === "width" || this.prop === "height" ? 1 : 0, this.cur());

        // Start by showing the element
        jQuery( this.elem ).show();
    },

    // Simple 'hide' function
    hide: function() {
        // Remember where we started, so that we can go back to it later
        this.options.orig[this.prop] = jQuery.style( this.elem, this.prop );
        this.options.hide = true;

        // Begin the animation
        this.custom(this.cur(), 0);
    },

    // Each step of an animation
    step: function( gotoEnd ) {
        var t = jQuery.now(), done = true;

        if ( gotoEnd || t >= this.options.duration + this.startTime ) {
            this.now = this.end;
            this.pos = this.state = 1;
            this.update();

            this.options.curAnim[ this.prop ] = true;

            for ( var i in this.options.curAnim ) {
                if ( this.options.curAnim[i] !== true ) {
                    done = false;
                }
            }

            if ( done ) {
                // Reset the overflow
                if ( this.options.overflow != null && !jQuery.support.shrinkWrapBlocks ) {
                    var elem = this.elem,
                        options = this.options;

                    jQuery.each( [ "", "X", "Y" ], function (index, value) {
                        elem.style[ "overflow" + value ] = options.overflow[index];
                    } );
                }

                // Hide the element if the "hide" operation was done
                if ( this.options.hide ) {
                    jQuery(this.elem).hide();
                }

                // Reset the properties, if the item has been hidden or shown
                if ( this.options.hide || this.options.show ) {
                    for ( var p in this.options.curAnim ) {
                        jQuery.style( this.elem, p, this.options.orig[p] );
                    }
                }

                // Execute the complete function
                this.options.complete.call( this.elem );
            }

            return false;

        } else {
            var n = t - this.startTime;
            this.state = n / this.options.duration;

            // Perform the easing function, defaults to swing
            var specialEasing = this.options.specialEasing && this.options.specialEasing[this.prop];
            var defaultEasing = this.options.easing || (jQuery.easing.swing ? "swing" : "linear");
            this.pos = jQuery.easing[specialEasing || defaultEasing](this.state, n, 0, 1, this.options.duration);
            this.now = this.start + ((this.end - this.start) * this.pos);

            // Perform the next step of the animation
            this.update();
        }

        return true;
    }
};

jQuery.extend( jQuery.fx, {
    tick: function() {
        var timers = jQuery.timers;

        for ( var i = 0; i < timers.length; i++ ) {
            if ( !timers[i]() ) {
                timers.splice(i--, 1);
            }
        }

        if ( !timers.length ) {
            jQuery.fx.stop();
        }
    },

    interval: 13,

    stop: function() {
        clearInterval( timerId );
        timerId = null;
    },

    speeds: {
        slow: 600,
        fast: 200,
        // Default speed
        _default: 400
    },

    step: {
        opacity: function( fx ) {
            jQuery.style( fx.elem, "opacity", fx.now );
        },

        _default: function( fx ) {
            if ( fx.elem.style && fx.elem.style[ fx.prop ] != null ) {
                fx.elem.style[ fx.prop ] = (fx.prop === "width" || fx.prop === "height" ? Math.max(0, fx.now) : fx.now) + fx.unit;
            } else {
                fx.elem[ fx.prop ] = fx.now;
            }
        }
    }
});

if ( jQuery.expr && jQuery.expr.filters ) {
    jQuery.expr.filters.animated = function( elem ) {
        return jQuery.grep(jQuery.timers, function( fn ) {
            return elem === fn.elem;
        }).length;
    };
}

function defaultDisplay( nodeName ) {
    if ( !elemdisplay[ nodeName ] ) {
        var elem = jQuery("<" + nodeName + ">").appendTo("body"),
            display = elem.css("display");

        elem.remove();

        if ( display === "none" || display === "" ) {
            display = "block";
        }

        elemdisplay[ nodeName ] = display;
    }

    return elemdisplay[ nodeName ];
}




var rtable = /^t(?:able|d|h)$/i,
    rroot = /^(?:body|html)$/i;

if ( "getBoundingClientRect" in document.documentElement ) {
    jQuery.fn.offset = function( options ) {
        var elem = this[0], box;

        if ( options ) {
            return this.each(function( i ) {
                jQuery.offset.setOffset( this, options, i );
            });
        }

        if ( !elem || !elem.ownerDocument ) {
            return null;
        }

        if ( elem === elem.ownerDocument.body ) {
            return jQuery.offset.bodyOffset( elem );
        }

        try {
            box = elem.getBoundingClientRect();
        } catch(e) {}

        var doc = elem.ownerDocument,
            docElem = doc.documentElement;

        // Make sure we're not dealing with a disconnected DOM node
        if ( !box || !jQuery.contains( docElem, elem ) ) {
            return box || { top: 0, left: 0 };
        }

        var body = doc.body,
            win = getWindow(doc),
            clientTop  = docElem.clientTop  || body.clientTop  || 0,
            clientLeft = docElem.clientLeft || body.clientLeft || 0,
            scrollTop  = (win.pageYOffset || jQuery.support.boxModel && docElem.scrollTop  || body.scrollTop ),
            scrollLeft = (win.pageXOffset || jQuery.support.boxModel && docElem.scrollLeft || body.scrollLeft),
            top  = box.top  + scrollTop  - clientTop,
            left = box.left + scrollLeft - clientLeft;

        return { top: top, left: left };
    };

} else {
    jQuery.fn.offset = function( options ) {
        var elem = this[0];

        if ( options ) {
            return this.each(function( i ) {
                jQuery.offset.setOffset( this, options, i );
            });
        }

        if ( !elem || !elem.ownerDocument ) {
            return null;
        }

        if ( elem === elem.ownerDocument.body ) {
            return jQuery.offset.bodyOffset( elem );
        }

        jQuery.offset.initialize();

        var computedStyle,
            offsetParent = elem.offsetParent,
            prevOffsetParent = elem,
            doc = elem.ownerDocument,
            docElem = doc.documentElement,
            body = doc.body,
            defaultView = doc.defaultView,
            prevComputedStyle = defaultView ? defaultView.getComputedStyle( elem, null ) : elem.currentStyle,
            top = elem.offsetTop,
            left = elem.offsetLeft;

        while ( (elem = elem.parentNode) && elem !== body && elem !== docElem ) {
            if ( jQuery.offset.supportsFixedPosition && prevComputedStyle.position === "fixed" ) {
                break;
            }

            computedStyle = defaultView ? defaultView.getComputedStyle(elem, null) : elem.currentStyle;
            top  -= elem.scrollTop;
            left -= elem.scrollLeft;

            if ( elem === offsetParent ) {
                top  += elem.offsetTop;
                left += elem.offsetLeft;

                if ( jQuery.offset.doesNotAddBorder && !(jQuery.offset.doesAddBorderForTableAndCells && rtable.test(elem.nodeName)) ) {
                    top  += parseFloat( computedStyle.borderTopWidth  ) || 0;
                    left += parseFloat( computedStyle.borderLeftWidth ) || 0;
                }

                prevOffsetParent = offsetParent;
                offsetParent = elem.offsetParent;
            }

            if ( jQuery.offset.subtractsBorderForOverflowNotVisible && computedStyle.overflow !== "visible" ) {
                top  += parseFloat( computedStyle.borderTopWidth  ) || 0;
                left += parseFloat( computedStyle.borderLeftWidth ) || 0;
            }

            prevComputedStyle = computedStyle;
        }

        if ( prevComputedStyle.position === "relative" || prevComputedStyle.position === "static" ) {
            top  += body.offsetTop;
            left += body.offsetLeft;
        }

        if ( jQuery.offset.supportsFixedPosition && prevComputedStyle.position === "fixed" ) {
            top  += Math.max( docElem.scrollTop, body.scrollTop );
            left += Math.max( docElem.scrollLeft, body.scrollLeft );
        }

        return { top: top, left: left };
    };
}

jQuery.offset = {
    initialize: function() {
        var body = document.body, container = document.createElement("div"), innerDiv, checkDiv, table, td, bodyMarginTop = parseFloat( jQuery.css(body, "marginTop") ) || 0,
            html = "<div style='position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;'><div></div></div><table style='position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;' cellpadding='0' cellspacing='0'><tr><td></td></tr></table>";

        jQuery.extend( container.style, { position: "absolute", top: 0, left: 0, margin: 0, border: 0, width: "1px", height: "1px", visibility: "hidden" } );

        container.innerHTML = html;
        body.insertBefore( container, body.firstChild );
        innerDiv = container.firstChild;
        checkDiv = innerDiv.firstChild;
        td = innerDiv.nextSibling.firstChild.firstChild;

        this.doesNotAddBorder = (checkDiv.offsetTop !== 5);
        this.doesAddBorderForTableAndCells = (td.offsetTop === 5);

        checkDiv.style.position = "fixed";
        checkDiv.style.top = "20px";

        // safari subtracts parent border width here which is 5px
        this.supportsFixedPosition = (checkDiv.offsetTop === 20 || checkDiv.offsetTop === 15);
        checkDiv.style.position = checkDiv.style.top = "";

        innerDiv.style.overflow = "hidden";
        innerDiv.style.position = "relative";

        this.subtractsBorderForOverflowNotVisible = (checkDiv.offsetTop === -5);

        this.doesNotIncludeMarginInBodyOffset = (body.offsetTop !== bodyMarginTop);

        body.removeChild( container );
        body = container = innerDiv = checkDiv = table = td = null;
        jQuery.offset.initialize = jQuery.noop;
    },

    bodyOffset: function( body ) {
        var top = body.offsetTop,
            left = body.offsetLeft;

        jQuery.offset.initialize();

        if ( jQuery.offset.doesNotIncludeMarginInBodyOffset ) {
            top  += parseFloat( jQuery.css(body, "marginTop") ) || 0;
            left += parseFloat( jQuery.css(body, "marginLeft") ) || 0;
        }

        return { top: top, left: left };
    },

    setOffset: function( elem, options, i ) {
        var position = jQuery.css( elem, "position" );

        // set position first, in-case top/left are set even on static elem
        if ( position === "static" ) {
            elem.style.position = "relative";
        }

        var curElem = jQuery( elem ),
            curOffset = curElem.offset(),
            curCSSTop = jQuery.css( elem, "top" ),
            curCSSLeft = jQuery.css( elem, "left" ),
            calculatePosition = (position === "absolute" && jQuery.inArray('auto', [curCSSTop, curCSSLeft]) > -1),
            props = {}, curPosition = {}, curTop, curLeft;

        // need to be able to calculate position if either top or left is auto and position is absolute
        if ( calculatePosition ) {
            curPosition = curElem.position();
        }

        curTop  = calculatePosition ? curPosition.top  : parseInt( curCSSTop,  10 ) || 0;
        curLeft = calculatePosition ? curPosition.left : parseInt( curCSSLeft, 10 ) || 0;

        if ( jQuery.isFunction( options ) ) {
            options = options.call( elem, i, curOffset );
        }

        if (options.top != null) {
            props.top = (options.top - curOffset.top) + curTop;
        }
        if (options.left != null) {
            props.left = (options.left - curOffset.left) + curLeft;
        }

        if ( "using" in options ) {
            options.using.call( elem, props );
        } else {
            curElem.css( props );
        }
    }
};


jQuery.fn.extend({
    position: function() {
        if ( !this[0] ) {
            return null;
        }

        var elem = this[0],

        // Get *real* offsetParent
        offsetParent = this.offsetParent(),

        // Get correct offsets
        offset       = this.offset(),
        parentOffset = rroot.test(offsetParent[0].nodeName) ? { top: 0, left: 0 } : offsetParent.offset();

        // Subtract element margins
        // note: when an element has margin: auto the offsetLeft and marginLeft
        // are the same in Safari causing offset.left to incorrectly be 0
        offset.top  -= parseFloat( jQuery.css(elem, "marginTop") ) || 0;
        offset.left -= parseFloat( jQuery.css(elem, "marginLeft") ) || 0;

        // Add offsetParent borders
        parentOffset.top  += parseFloat( jQuery.css(offsetParent[0], "borderTopWidth") ) || 0;
        parentOffset.left += parseFloat( jQuery.css(offsetParent[0], "borderLeftWidth") ) || 0;

        // Subtract the two offsets
        return {
            top:  offset.top  - parentOffset.top,
            left: offset.left - parentOffset.left
        };
    },

    offsetParent: function() {
        return this.map(function() {
            var offsetParent = this.offsetParent || document.body;
            while ( offsetParent && (!rroot.test(offsetParent.nodeName) && jQuery.css(offsetParent, "position") === "static") ) {
                offsetParent = offsetParent.offsetParent;
            }
            return offsetParent;
        });
    }
});


// Create scrollLeft and scrollTop methods
jQuery.each( ["Left", "Top"], function( i, name ) {
    var method = "scroll" + name;

    jQuery.fn[ method ] = function(val) {
        var elem = this[0], win;

        if ( !elem ) {
            return null;
        }

        if ( val !== undefined ) {
            // Set the scroll offset
            return this.each(function() {
                win = getWindow( this );

                if ( win ) {
                    win.scrollTo(
                        !i ? val : jQuery(win).scrollLeft(),
                         i ? val : jQuery(win).scrollTop()
                    );

                } else {
                    this[ method ] = val;
                }
            });
        } else {
            win = getWindow( elem );

            // Return the scroll offset
            return win ? ("pageXOffset" in win) ? win[ i ? "pageYOffset" : "pageXOffset" ] :
                jQuery.support.boxModel && win.document.documentElement[ method ] ||
                    win.document.body[ method ] :
                elem[ method ];
        }
    };
});

function getWindow( elem ) {
    return jQuery.isWindow( elem ) ?
        elem :
        elem.nodeType === 9 ?
            elem.defaultView || elem.parentWindow :
            false;
}




// Create innerHeight, innerWidth, outerHeight and outerWidth methods
jQuery.each([ "Height", "Width" ], function( i, name ) {

    var type = name.toLowerCase();

    // innerHeight and innerWidth
    jQuery.fn["inner" + name] = function() {
        return this[0] ?
            parseFloat( jQuery.css( this[0], type, "padding" ) ) :
            null;
    };

    // outerHeight and outerWidth
    jQuery.fn["outer" + name] = function( margin ) {
        return this[0] ?
            parseFloat( jQuery.css( this[0], type, margin ? "margin" : "border" ) ) :
            null;
    };

    jQuery.fn[ type ] = function( size ) {
        // Get window width or height
        var elem = this[0];
        if ( !elem ) {
            return size == null ? null : this;
        }

        if ( jQuery.isFunction( size ) ) {
            return this.each(function( i ) {
                var self = jQuery( this );
                self[ type ]( size.call( this, i, self[ type ]() ) );
            });
        }

        if ( jQuery.isWindow( elem ) ) {
            // Everyone else use document.documentElement or document.body depending on Quirks vs Standards mode
            return elem.document.compatMode === "CSS1Compat" && elem.document.documentElement[ "client" + name ] ||
                elem.document.body[ "client" + name ];

        // Get document width or height
        } else if ( elem.nodeType === 9 ) {
            // Either scroll[Width/Height] or offset[Width/Height], whichever is greater
            return Math.max(
                elem.documentElement["client" + name],
                elem.body["scroll" + name], elem.documentElement["scroll" + name],
                elem.body["offset" + name], elem.documentElement["offset" + name]
            );

        // Get or set width or height on the element
        } else if ( size === undefined ) {
            var orig = jQuery.css( elem, type ),
                ret = parseFloat( orig );

            return jQuery.isNaN( ret ) ? orig : ret;

        // Set the width or height on the element (default to pixels if value is unitless)
        } else {
            return this.css( type, typeof size === "string" ? size : size + "px" );
        }
    };

});


})(window);



/***********************************
    jquery-ui-1.8.16.custom.min.js
***********************************/

/*!
 * jQuery UI 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI
 */
(function(c,j){function k(a,b){var d=a.nodeName.toLowerCase();if("area"===d){b=a.parentNode;d=b.name;if(!a.href||!d||b.nodeName.toLowerCase()!=="map")return false;a=c("img[usemap=#"+d+"]")[0];return!!a&&l(a)}return(/input|select|textarea|button|object/.test(d)?!a.disabled:"a"==d?a.href||b:b)&&l(a)}function l(a){return!c(a).parents().andSelf().filter(function(){return c.curCSS(this,"visibility")==="hidden"||c.expr.filters.hidden(this)}).length}c.ui=c.ui||{};if(!c.ui.version){c.extend(c.ui,{version:"1.8.16",
keyCode:{ALT:18,BACKSPACE:8,CAPS_LOCK:20,COMMA:188,COMMAND:91,COMMAND_LEFT:91,COMMAND_RIGHT:93,CONTROL:17,DELETE:46,DOWN:40,END:35,ENTER:13,ESCAPE:27,HOME:36,INSERT:45,LEFT:37,MENU:93,NUMPAD_ADD:107,NUMPAD_DECIMAL:110,NUMPAD_DIVIDE:111,NUMPAD_ENTER:108,NUMPAD_MULTIPLY:106,NUMPAD_SUBTRACT:109,PAGE_DOWN:34,PAGE_UP:33,PERIOD:190,RIGHT:39,SHIFT:16,SPACE:32,TAB:9,UP:38,WINDOWS:91}});c.fn.extend({propAttr:c.fn.prop||c.fn.attr,_focus:c.fn.focus,focus:function(a,b){return typeof a==="number"?this.each(function(){var d=
this;setTimeout(function(){c(d).focus();b&&b.call(d)},a)}):this._focus.apply(this,arguments)},scrollParent:function(){var a;a=c.browser.msie&&/(static|relative)/.test(this.css("position"))||/absolute/.test(this.css("position"))?this.parents().filter(function(){return/(relative|absolute|fixed)/.test(c.curCSS(this,"position",1))&&/(auto|scroll)/.test(c.curCSS(this,"overflow",1)+c.curCSS(this,"overflow-y",1)+c.curCSS(this,"overflow-x",1))}).eq(0):this.parents().filter(function(){return/(auto|scroll)/.test(c.curCSS(this,
"overflow",1)+c.curCSS(this,"overflow-y",1)+c.curCSS(this,"overflow-x",1))}).eq(0);return/fixed/.test(this.css("position"))||!a.length?c(document):a},zIndex:function(a){if(a!==j)return this.css("zIndex",a);if(this.length){a=c(this[0]);for(var b;a.length&&a[0]!==document;){b=a.css("position");if(b==="absolute"||b==="relative"||b==="fixed"){b=parseInt(a.css("zIndex"),10);if(!isNaN(b)&&b!==0)return b}a=a.parent()}}return 0},disableSelection:function(){return this.bind((c.support.selectstart?"selectstart":
"mousedown")+".ui-disableSelection",function(a){a.preventDefault()})},enableSelection:function(){return this.unbind(".ui-disableSelection")}});c.each(["Width","Height"],function(a,b){function d(f,g,m,n){c.each(e,function(){g-=parseFloat(c.curCSS(f,"padding"+this,true))||0;if(m)g-=parseFloat(c.curCSS(f,"border"+this+"Width",true))||0;if(n)g-=parseFloat(c.curCSS(f,"margin"+this,true))||0});return g}var e=b==="Width"?["Left","Right"]:["Top","Bottom"],h=b.toLowerCase(),i={innerWidth:c.fn.innerWidth,innerHeight:c.fn.innerHeight,
outerWidth:c.fn.outerWidth,outerHeight:c.fn.outerHeight};c.fn["inner"+b]=function(f){if(f===j)return i["inner"+b].call(this);return this.each(function(){c(this).css(h,d(this,f)+"px")})};c.fn["outer"+b]=function(f,g){if(typeof f!=="number")return i["outer"+b].call(this,f);return this.each(function(){c(this).css(h,d(this,f,true,g)+"px")})}});c.extend(c.expr[":"],{data:function(a,b,d){return!!c.data(a,d[3])},focusable:function(a){return k(a,!isNaN(c.attr(a,"tabindex")))},tabbable:function(a){var b=c.attr(a,
"tabindex"),d=isNaN(b);return(d||b>=0)&&k(a,!d)}});c(function(){var a=document.body,b=a.appendChild(b=document.createElement("div"));c.extend(b.style,{minHeight:"100px",height:"auto",padding:0,borderWidth:0});c.support.minHeight=b.offsetHeight===100;c.support.selectstart="onselectstart"in b;a.removeChild(b).style.display="none"});c.extend(c.ui,{plugin:{add:function(a,b,d){a=c.ui[a].prototype;for(var e in d){a.plugins[e]=a.plugins[e]||[];a.plugins[e].push([b,d[e]])}},call:function(a,b,d){if((b=a.plugins[b])&&
a.element[0].parentNode)for(var e=0;e<b.length;e++)a.options[b[e][0]]&&b[e][1].apply(a.element,d)}},contains:function(a,b){return document.compareDocumentPosition?a.compareDocumentPosition(b)&16:a!==b&&a.contains(b)},hasScroll:function(a,b){if(c(a).css("overflow")==="hidden")return false;b=b&&b==="left"?"scrollLeft":"scrollTop";var d=false;if(a[b]>0)return true;a[b]=1;d=a[b]>0;a[b]=0;return d},isOverAxis:function(a,b,d){return a>b&&a<b+d},isOver:function(a,b,d,e,h,i){return c.ui.isOverAxis(a,d,h)&&
c.ui.isOverAxis(b,e,i)}})}})(jQuery);
;/*!
 * jQuery UI Widget 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Widget
 */
(function(b,j){if(b.cleanData){var k=b.cleanData;b.cleanData=function(a){for(var c=0,d;(d=a[c])!=null;c++)try{b(d).triggerHandler("remove")}catch(e){}k(a)}}else{var l=b.fn.remove;b.fn.remove=function(a,c){return this.each(function(){if(!c)if(!a||b.filter(a,[this]).length)b("*",this).add([this]).each(function(){try{b(this).triggerHandler("remove")}catch(d){}});return l.call(b(this),a,c)})}}b.widget=function(a,c,d){var e=a.split(".")[0],f;a=a.split(".")[1];f=e+"-"+a;if(!d){d=c;c=b.Widget}b.expr[":"][f]=
function(h){return!!b.data(h,a)};b[e]=b[e]||{};b[e][a]=function(h,g){arguments.length&&this._createWidget(h,g)};c=new c;c.options=b.extend(true,{},c.options);b[e][a].prototype=b.extend(true,c,{namespace:e,widgetName:a,widgetEventPrefix:b[e][a].prototype.widgetEventPrefix||a,widgetBaseClass:f},d);b.widget.bridge(a,b[e][a])};b.widget.bridge=function(a,c){b.fn[a]=function(d){var e=typeof d==="string",f=Array.prototype.slice.call(arguments,1),h=this;d=!e&&f.length?b.extend.apply(null,[true,d].concat(f)):
d;if(e&&d.charAt(0)==="_")return h;e?this.each(function(){var g=b.data(this,a),i=g&&b.isFunction(g[d])?g[d].apply(g,f):g;if(i!==g&&i!==j){h=i;return false}}):this.each(function(){var g=b.data(this,a);g?g.option(d||{})._init():b.data(this,a,new c(d,this))});return h}};b.Widget=function(a,c){arguments.length&&this._createWidget(a,c)};b.Widget.prototype={widgetName:"widget",widgetEventPrefix:"",options:{disabled:false},_createWidget:function(a,c){b.data(c,this.widgetName,this);this.element=b(c);this.options=
b.extend(true,{},this.options,this._getCreateOptions(),a);var d=this;this.element.bind("remove."+this.widgetName,function(){d.destroy()});this._create();this._trigger("create");this._init()},_getCreateOptions:function(){return b.metadata&&b.metadata.get(this.element[0])[this.widgetName]},_create:function(){},_init:function(){},destroy:function(){this.element.unbind("."+this.widgetName).removeData(this.widgetName);this.widget().unbind("."+this.widgetName).removeAttr("aria-disabled").removeClass(this.widgetBaseClass+
"-disabled ui-state-disabled")},widget:function(){return this.element},option:function(a,c){var d=a;if(arguments.length===0)return b.extend({},this.options);if(typeof a==="string"){if(c===j)return this.options[a];d={};d[a]=c}this._setOptions(d);return this},_setOptions:function(a){var c=this;b.each(a,function(d,e){c._setOption(d,e)});return this},_setOption:function(a,c){this.options[a]=c;if(a==="disabled")this.widget()[c?"addClass":"removeClass"](this.widgetBaseClass+"-disabled ui-state-disabled").attr("aria-disabled",
c);return this},enable:function(){return this._setOption("disabled",false)},disable:function(){return this._setOption("disabled",true)},_trigger:function(a,c,d){var e=this.options[a];c=b.Event(c);c.type=(a===this.widgetEventPrefix?a:this.widgetEventPrefix+a).toLowerCase();d=d||{};if(c.originalEvent){a=b.event.props.length;for(var f;a;){f=b.event.props[--a];c[f]=c.originalEvent[f]}}this.element.trigger(c,d);return!(b.isFunction(e)&&e.call(this.element[0],c,d)===false||c.isDefaultPrevented())}}})(jQuery);
;/*!
 * jQuery UI Mouse 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Mouse
 *
 * Depends:
 *  jquery.ui.widget.js
 */
(function(b){var d=false;b(document).mouseup(function(){d=false});b.widget("ui.mouse",{options:{cancel:":input,option",distance:1,delay:0},_mouseInit:function(){var a=this;this.element.bind("mousedown."+this.widgetName,function(c){return a._mouseDown(c)}).bind("click."+this.widgetName,function(c){if(true===b.data(c.target,a.widgetName+".preventClickEvent")){b.removeData(c.target,a.widgetName+".preventClickEvent");c.stopImmediatePropagation();return false}});this.started=false},_mouseDestroy:function(){this.element.unbind("."+
this.widgetName)},_mouseDown:function(a){if(!d){this._mouseStarted&&this._mouseUp(a);this._mouseDownEvent=a;var c=this,f=a.which==1,g=typeof this.options.cancel=="string"&&a.target.nodeName?b(a.target).closest(this.options.cancel).length:false;if(!f||g||!this._mouseCapture(a))return true;this.mouseDelayMet=!this.options.delay;if(!this.mouseDelayMet)this._mouseDelayTimer=setTimeout(function(){c.mouseDelayMet=true},this.options.delay);if(this._mouseDistanceMet(a)&&this._mouseDelayMet(a)){this._mouseStarted=
this._mouseStart(a)!==false;if(!this._mouseStarted){a.preventDefault();return true}}true===b.data(a.target,this.widgetName+".preventClickEvent")&&b.removeData(a.target,this.widgetName+".preventClickEvent");this._mouseMoveDelegate=function(e){return c._mouseMove(e)};this._mouseUpDelegate=function(e){return c._mouseUp(e)};b(document).bind("mousemove."+this.widgetName,this._mouseMoveDelegate).bind("mouseup."+this.widgetName,this._mouseUpDelegate);a.preventDefault();return d=true}},_mouseMove:function(a){if(b.browser.msie&&
!(document.documentMode>=9)&&!a.button)return this._mouseUp(a);if(this._mouseStarted){this._mouseDrag(a);return a.preventDefault()}if(this._mouseDistanceMet(a)&&this._mouseDelayMet(a))(this._mouseStarted=this._mouseStart(this._mouseDownEvent,a)!==false)?this._mouseDrag(a):this._mouseUp(a);return!this._mouseStarted},_mouseUp:function(a){b(document).unbind("mousemove."+this.widgetName,this._mouseMoveDelegate).unbind("mouseup."+this.widgetName,this._mouseUpDelegate);if(this._mouseStarted){this._mouseStarted=
false;a.target==this._mouseDownEvent.target&&b.data(a.target,this.widgetName+".preventClickEvent",true);this._mouseStop(a)}return false},_mouseDistanceMet:function(a){return Math.max(Math.abs(this._mouseDownEvent.pageX-a.pageX),Math.abs(this._mouseDownEvent.pageY-a.pageY))>=this.options.distance},_mouseDelayMet:function(){return this.mouseDelayMet},_mouseStart:function(){},_mouseDrag:function(){},_mouseStop:function(){},_mouseCapture:function(){return true}})})(jQuery);
;/*
 * jQuery UI Position 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Position
 */
(function(c){c.ui=c.ui||{};var n=/left|center|right/,o=/top|center|bottom/,t=c.fn.position,u=c.fn.offset;c.fn.position=function(b){if(!b||!b.of)return t.apply(this,arguments);b=c.extend({},b);var a=c(b.of),d=a[0],g=(b.collision||"flip").split(" "),e=b.offset?b.offset.split(" "):[0,0],h,k,j;if(d.nodeType===9){h=a.width();k=a.height();j={top:0,left:0}}else if(d.setTimeout){h=a.width();k=a.height();j={top:a.scrollTop(),left:a.scrollLeft()}}else if(d.preventDefault){b.at="left top";h=k=0;j={top:b.of.pageY,
left:b.of.pageX}}else{h=a.outerWidth();k=a.outerHeight();j=a.offset()}c.each(["my","at"],function(){var f=(b[this]||"").split(" ");if(f.length===1)f=n.test(f[0])?f.concat(["center"]):o.test(f[0])?["center"].concat(f):["center","center"];f[0]=n.test(f[0])?f[0]:"center";f[1]=o.test(f[1])?f[1]:"center";b[this]=f});if(g.length===1)g[1]=g[0];e[0]=parseInt(e[0],10)||0;if(e.length===1)e[1]=e[0];e[1]=parseInt(e[1],10)||0;if(b.at[0]==="right")j.left+=h;else if(b.at[0]==="center")j.left+=h/2;if(b.at[1]==="bottom")j.top+=
k;else if(b.at[1]==="center")j.top+=k/2;j.left+=e[0];j.top+=e[1];return this.each(function(){var f=c(this),l=f.outerWidth(),m=f.outerHeight(),p=parseInt(c.curCSS(this,"marginLeft",true))||0,q=parseInt(c.curCSS(this,"marginTop",true))||0,v=l+p+(parseInt(c.curCSS(this,"marginRight",true))||0),w=m+q+(parseInt(c.curCSS(this,"marginBottom",true))||0),i=c.extend({},j),r;if(b.my[0]==="right")i.left-=l;else if(b.my[0]==="center")i.left-=l/2;if(b.my[1]==="bottom")i.top-=m;else if(b.my[1]==="center")i.top-=
m/2;i.left=Math.round(i.left);i.top=Math.round(i.top);r={left:i.left-p,top:i.top-q};c.each(["left","top"],function(s,x){c.ui.position[g[s]]&&c.ui.position[g[s]][x](i,{targetWidth:h,targetHeight:k,elemWidth:l,elemHeight:m,collisionPosition:r,collisionWidth:v,collisionHeight:w,offset:e,my:b.my,at:b.at})});c.fn.bgiframe&&f.bgiframe();f.offset(c.extend(i,{using:b.using}))})};c.ui.position={fit:{left:function(b,a){var d=c(window);d=a.collisionPosition.left+a.collisionWidth-d.width()-d.scrollLeft();b.left=
d>0?b.left-d:Math.max(b.left-a.collisionPosition.left,b.left)},top:function(b,a){var d=c(window);d=a.collisionPosition.top+a.collisionHeight-d.height()-d.scrollTop();b.top=d>0?b.top-d:Math.max(b.top-a.collisionPosition.top,b.top)}},flip:{left:function(b,a){if(a.at[0]!=="center"){var d=c(window);d=a.collisionPosition.left+a.collisionWidth-d.width()-d.scrollLeft();var g=a.my[0]==="left"?-a.elemWidth:a.my[0]==="right"?a.elemWidth:0,e=a.at[0]==="left"?a.targetWidth:-a.targetWidth,h=-2*a.offset[0];b.left+=
a.collisionPosition.left<0?g+e+h:d>0?g+e+h:0}},top:function(b,a){if(a.at[1]!=="center"){var d=c(window);d=a.collisionPosition.top+a.collisionHeight-d.height()-d.scrollTop();var g=a.my[1]==="top"?-a.elemHeight:a.my[1]==="bottom"?a.elemHeight:0,e=a.at[1]==="top"?a.targetHeight:-a.targetHeight,h=-2*a.offset[1];b.top+=a.collisionPosition.top<0?g+e+h:d>0?g+e+h:0}}}};if(!c.offset.setOffset){c.offset.setOffset=function(b,a){if(/static/.test(c.curCSS(b,"position")))b.style.position="relative";var d=c(b),
g=d.offset(),e=parseInt(c.curCSS(b,"top",true),10)||0,h=parseInt(c.curCSS(b,"left",true),10)||0;g={top:a.top-g.top+e,left:a.left-g.left+h};"using"in a?a.using.call(b,g):d.css(g)};c.fn.offset=function(b){var a=this[0];if(!a||!a.ownerDocument)return null;if(b)return this.each(function(){c.offset.setOffset(this,b)});return u.call(this)}}})(jQuery);
;/*
 * jQuery UI Draggable 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Draggables
 *
 * Depends:
 *  jquery.ui.core.js
 *  jquery.ui.mouse.js
 *  jquery.ui.widget.js
 */
(function(d){d.widget("ui.draggable",d.ui.mouse,{widgetEventPrefix:"drag",options:{addClasses:true,appendTo:"parent",axis:false,connectToSortable:false,containment:false,cursor:"auto",cursorAt:false,grid:false,handle:false,helper:"original",iframeFix:false,opacity:false,refreshPositions:false,revert:false,revertDuration:500,scope:"default",scroll:true,scrollSensitivity:20,scrollSpeed:20,snap:false,snapMode:"both",snapTolerance:20,stack:false,zIndex:false},_create:function(){if(this.options.helper==
"original"&&!/^(?:r|a|f)/.test(this.element.css("position")))this.element[0].style.position="relative";this.options.addClasses&&this.element.addClass("ui-draggable");this.options.disabled&&this.element.addClass("ui-draggable-disabled");this._mouseInit()},destroy:function(){if(this.element.data("draggable")){this.element.removeData("draggable").unbind(".draggable").removeClass("ui-draggable ui-draggable-dragging ui-draggable-disabled");this._mouseDestroy();return this}},_mouseCapture:function(a){var b=
this.options;if(this.helper||b.disabled||d(a.target).is(".ui-resizable-handle"))return false;this.handle=this._getHandle(a);if(!this.handle)return false;if(b.iframeFix)d(b.iframeFix===true?"iframe":b.iframeFix).each(function(){d('<div class="ui-draggable-iframeFix" style="background: #fff;"></div>').css({width:this.offsetWidth+"px",height:this.offsetHeight+"px",position:"absolute",opacity:"0.001",zIndex:1E3}).css(d(this).offset()).appendTo("body")});return true},_mouseStart:function(a){var b=this.options;
this.helper=this._createHelper(a);this._cacheHelperProportions();if(d.ui.ddmanager)d.ui.ddmanager.current=this;this._cacheMargins();this.cssPosition=this.helper.css("position");this.scrollParent=this.helper.scrollParent();this.offset=this.positionAbs=this.element.offset();this.offset={top:this.offset.top-this.margins.top,left:this.offset.left-this.margins.left};d.extend(this.offset,{click:{left:a.pageX-this.offset.left,top:a.pageY-this.offset.top},parent:this._getParentOffset(),relative:this._getRelativeOffset()});
this.originalPosition=this.position=this._generatePosition(a);this.originalPageX=a.pageX;this.originalPageY=a.pageY;b.cursorAt&&this._adjustOffsetFromHelper(b.cursorAt);b.containment&&this._setContainment();if(this._trigger("start",a)===false){this._clear();return false}this._cacheHelperProportions();d.ui.ddmanager&&!b.dropBehaviour&&d.ui.ddmanager.prepareOffsets(this,a);this.helper.addClass("ui-draggable-dragging");this._mouseDrag(a,true);d.ui.ddmanager&&d.ui.ddmanager.dragStart(this,a);return true},
_mouseDrag:function(a,b){this.position=this._generatePosition(a);this.positionAbs=this._convertPositionTo("absolute");if(!b){b=this._uiHash();if(this._trigger("drag",a,b)===false){this._mouseUp({});return false}this.position=b.position}if(!this.options.axis||this.options.axis!="y")this.helper[0].style.left=this.position.left+"px";if(!this.options.axis||this.options.axis!="x")this.helper[0].style.top=this.position.top+"px";d.ui.ddmanager&&d.ui.ddmanager.drag(this,a);return false},_mouseStop:function(a){var b=
false;if(d.ui.ddmanager&&!this.options.dropBehaviour)b=d.ui.ddmanager.drop(this,a);if(this.dropped){b=this.dropped;this.dropped=false}if((!this.element[0]||!this.element[0].parentNode)&&this.options.helper=="original")return false;if(this.options.revert=="invalid"&&!b||this.options.revert=="valid"&&b||this.options.revert===true||d.isFunction(this.options.revert)&&this.options.revert.call(this.element,b)){var c=this;d(this.helper).animate(this.originalPosition,parseInt(this.options.revertDuration,
10),function(){c._trigger("stop",a)!==false&&c._clear()})}else this._trigger("stop",a)!==false&&this._clear();return false},_mouseUp:function(a){this.options.iframeFix===true&&d("div.ui-draggable-iframeFix").each(function(){this.parentNode.removeChild(this)});d.ui.ddmanager&&d.ui.ddmanager.dragStop(this,a);return d.ui.mouse.prototype._mouseUp.call(this,a)},cancel:function(){this.helper.is(".ui-draggable-dragging")?this._mouseUp({}):this._clear();return this},_getHandle:function(a){var b=!this.options.handle||
!d(this.options.handle,this.element).length?true:false;d(this.options.handle,this.element).find("*").andSelf().each(function(){if(this==a.target)b=true});return b},_createHelper:function(a){var b=this.options;a=d.isFunction(b.helper)?d(b.helper.apply(this.element[0],[a])):b.helper=="clone"?this.element.clone().removeAttr("id"):this.element;a.parents("body").length||a.appendTo(b.appendTo=="parent"?this.element[0].parentNode:b.appendTo);a[0]!=this.element[0]&&!/(fixed|absolute)/.test(a.css("position"))&&
a.css("position","absolute");return a},_adjustOffsetFromHelper:function(a){if(typeof a=="string")a=a.split(" ");if(d.isArray(a))a={left:+a[0],top:+a[1]||0};if("left"in a)this.offset.click.left=a.left+this.margins.left;if("right"in a)this.offset.click.left=this.helperProportions.width-a.right+this.margins.left;if("top"in a)this.offset.click.top=a.top+this.margins.top;if("bottom"in a)this.offset.click.top=this.helperProportions.height-a.bottom+this.margins.top},_getParentOffset:function(){this.offsetParent=
this.helper.offsetParent();var a=this.offsetParent.offset();if(this.cssPosition=="absolute"&&this.scrollParent[0]!=document&&d.ui.contains(this.scrollParent[0],this.offsetParent[0])){a.left+=this.scrollParent.scrollLeft();a.top+=this.scrollParent.scrollTop()}if(this.offsetParent[0]==document.body||this.offsetParent[0].tagName&&this.offsetParent[0].tagName.toLowerCase()=="html"&&d.browser.msie)a={top:0,left:0};return{top:a.top+(parseInt(this.offsetParent.css("borderTopWidth"),10)||0),left:a.left+(parseInt(this.offsetParent.css("borderLeftWidth"),
10)||0)}},_getRelativeOffset:function(){if(this.cssPosition=="relative"){var a=this.element.position();return{top:a.top-(parseInt(this.helper.css("top"),10)||0)+this.scrollParent.scrollTop(),left:a.left-(parseInt(this.helper.css("left"),10)||0)+this.scrollParent.scrollLeft()}}else return{top:0,left:0}},_cacheMargins:function(){this.margins={left:parseInt(this.element.css("marginLeft"),10)||0,top:parseInt(this.element.css("marginTop"),10)||0,right:parseInt(this.element.css("marginRight"),10)||0,bottom:parseInt(this.element.css("marginBottom"),
10)||0}},_cacheHelperProportions:function(){this.helperProportions={width:this.helper.outerWidth(),height:this.helper.outerHeight()}},_setContainment:function(){var a=this.options;if(a.containment=="parent")a.containment=this.helper[0].parentNode;if(a.containment=="document"||a.containment=="window")this.containment=[a.containment=="document"?0:d(window).scrollLeft()-this.offset.relative.left-this.offset.parent.left,a.containment=="document"?0:d(window).scrollTop()-this.offset.relative.top-this.offset.parent.top,
(a.containment=="document"?0:d(window).scrollLeft())+d(a.containment=="document"?document:window).width()-this.helperProportions.width-this.margins.left,(a.containment=="document"?0:d(window).scrollTop())+(d(a.containment=="document"?document:window).height()||document.body.parentNode.scrollHeight)-this.helperProportions.height-this.margins.top];if(!/^(document|window|parent)$/.test(a.containment)&&a.containment.constructor!=Array){a=d(a.containment);var b=a[0];if(b){a.offset();var c=d(b).css("overflow")!=
"hidden";this.containment=[(parseInt(d(b).css("borderLeftWidth"),10)||0)+(parseInt(d(b).css("paddingLeft"),10)||0),(parseInt(d(b).css("borderTopWidth"),10)||0)+(parseInt(d(b).css("paddingTop"),10)||0),(c?Math.max(b.scrollWidth,b.offsetWidth):b.offsetWidth)-(parseInt(d(b).css("borderLeftWidth"),10)||0)-(parseInt(d(b).css("paddingRight"),10)||0)-this.helperProportions.width-this.margins.left-this.margins.right,(c?Math.max(b.scrollHeight,b.offsetHeight):b.offsetHeight)-(parseInt(d(b).css("borderTopWidth"),
10)||0)-(parseInt(d(b).css("paddingBottom"),10)||0)-this.helperProportions.height-this.margins.top-this.margins.bottom];this.relative_container=a}}else if(a.containment.constructor==Array)this.containment=a.containment},_convertPositionTo:function(a,b){if(!b)b=this.position;a=a=="absolute"?1:-1;var c=this.cssPosition=="absolute"&&!(this.scrollParent[0]!=document&&d.ui.contains(this.scrollParent[0],this.offsetParent[0]))?this.offsetParent:this.scrollParent,f=/(html|body)/i.test(c[0].tagName);return{top:b.top+
this.offset.relative.top*a+this.offset.parent.top*a-(d.browser.safari&&d.browser.version<526&&this.cssPosition=="fixed"?0:(this.cssPosition=="fixed"?-this.scrollParent.scrollTop():f?0:c.scrollTop())*a),left:b.left+this.offset.relative.left*a+this.offset.parent.left*a-(d.browser.safari&&d.browser.version<526&&this.cssPosition=="fixed"?0:(this.cssPosition=="fixed"?-this.scrollParent.scrollLeft():f?0:c.scrollLeft())*a)}},_generatePosition:function(a){var b=this.options,c=this.cssPosition=="absolute"&&
!(this.scrollParent[0]!=document&&d.ui.contains(this.scrollParent[0],this.offsetParent[0]))?this.offsetParent:this.scrollParent,f=/(html|body)/i.test(c[0].tagName),e=a.pageX,h=a.pageY;if(this.originalPosition){var g;if(this.containment){if(this.relative_container){g=this.relative_container.offset();g=[this.containment[0]+g.left,this.containment[1]+g.top,this.containment[2]+g.left,this.containment[3]+g.top]}else g=this.containment;if(a.pageX-this.offset.click.left<g[0])e=g[0]+this.offset.click.left;
if(a.pageY-this.offset.click.top<g[1])h=g[1]+this.offset.click.top;if(a.pageX-this.offset.click.left>g[2])e=g[2]+this.offset.click.left;if(a.pageY-this.offset.click.top>g[3])h=g[3]+this.offset.click.top}if(b.grid){h=b.grid[1]?this.originalPageY+Math.round((h-this.originalPageY)/b.grid[1])*b.grid[1]:this.originalPageY;h=g?!(h-this.offset.click.top<g[1]||h-this.offset.click.top>g[3])?h:!(h-this.offset.click.top<g[1])?h-b.grid[1]:h+b.grid[1]:h;e=b.grid[0]?this.originalPageX+Math.round((e-this.originalPageX)/
b.grid[0])*b.grid[0]:this.originalPageX;e=g?!(e-this.offset.click.left<g[0]||e-this.offset.click.left>g[2])?e:!(e-this.offset.click.left<g[0])?e-b.grid[0]:e+b.grid[0]:e}}return{top:h-this.offset.click.top-this.offset.relative.top-this.offset.parent.top+(d.browser.safari&&d.browser.version<526&&this.cssPosition=="fixed"?0:this.cssPosition=="fixed"?-this.scrollParent.scrollTop():f?0:c.scrollTop()),left:e-this.offset.click.left-this.offset.relative.left-this.offset.parent.left+(d.browser.safari&&d.browser.version<
526&&this.cssPosition=="fixed"?0:this.cssPosition=="fixed"?-this.scrollParent.scrollLeft():f?0:c.scrollLeft())}},_clear:function(){this.helper.removeClass("ui-draggable-dragging");this.helper[0]!=this.element[0]&&!this.cancelHelperRemoval&&this.helper.remove();this.helper=null;this.cancelHelperRemoval=false},_trigger:function(a,b,c){c=c||this._uiHash();d.ui.plugin.call(this,a,[b,c]);if(a=="drag")this.positionAbs=this._convertPositionTo("absolute");return d.Widget.prototype._trigger.call(this,a,b,
c)},plugins:{},_uiHash:function(){return{helper:this.helper,position:this.position,originalPosition:this.originalPosition,offset:this.positionAbs}}});d.extend(d.ui.draggable,{version:"1.8.16"});d.ui.plugin.add("draggable","connectToSortable",{start:function(a,b){var c=d(this).data("draggable"),f=c.options,e=d.extend({},b,{item:c.element});c.sortables=[];d(f.connectToSortable).each(function(){var h=d.data(this,"sortable");if(h&&!h.options.disabled){c.sortables.push({instance:h,shouldRevert:h.options.revert});
h.refreshPositions();h._trigger("activate",a,e)}})},stop:function(a,b){var c=d(this).data("draggable"),f=d.extend({},b,{item:c.element});d.each(c.sortables,function(){if(this.instance.isOver){this.instance.isOver=0;c.cancelHelperRemoval=true;this.instance.cancelHelperRemoval=false;if(this.shouldRevert)this.instance.options.revert=true;this.instance._mouseStop(a);this.instance.options.helper=this.instance.options._helper;c.options.helper=="original"&&this.instance.currentItem.css({top:"auto",left:"auto"})}else{this.instance.cancelHelperRemoval=
false;this.instance._trigger("deactivate",a,f)}})},drag:function(a,b){var c=d(this).data("draggable"),f=this;d.each(c.sortables,function(){this.instance.positionAbs=c.positionAbs;this.instance.helperProportions=c.helperProportions;this.instance.offset.click=c.offset.click;if(this.instance._intersectsWith(this.instance.containerCache)){if(!this.instance.isOver){this.instance.isOver=1;this.instance.currentItem=d(f).clone().removeAttr("id").appendTo(this.instance.element).data("sortable-item",true);
this.instance.options._helper=this.instance.options.helper;this.instance.options.helper=function(){return b.helper[0]};a.target=this.instance.currentItem[0];this.instance._mouseCapture(a,true);this.instance._mouseStart(a,true,true);this.instance.offset.click.top=c.offset.click.top;this.instance.offset.click.left=c.offset.click.left;this.instance.offset.parent.left-=c.offset.parent.left-this.instance.offset.parent.left;this.instance.offset.parent.top-=c.offset.parent.top-this.instance.offset.parent.top;
c._trigger("toSortable",a);c.dropped=this.instance.element;c.currentItem=c.element;this.instance.fromOutside=c}this.instance.currentItem&&this.instance._mouseDrag(a)}else if(this.instance.isOver){this.instance.isOver=0;this.instance.cancelHelperRemoval=true;this.instance.options.revert=false;this.instance._trigger("out",a,this.instance._uiHash(this.instance));this.instance._mouseStop(a,true);this.instance.options.helper=this.instance.options._helper;this.instance.currentItem.remove();this.instance.placeholder&&
this.instance.placeholder.remove();c._trigger("fromSortable",a);c.dropped=false}})}});d.ui.plugin.add("draggable","cursor",{start:function(){var a=d("body"),b=d(this).data("draggable").options;if(a.css("cursor"))b._cursor=a.css("cursor");a.css("cursor",b.cursor)},stop:function(){var a=d(this).data("draggable").options;a._cursor&&d("body").css("cursor",a._cursor)}});d.ui.plugin.add("draggable","opacity",{start:function(a,b){a=d(b.helper);b=d(this).data("draggable").options;if(a.css("opacity"))b._opacity=
a.css("opacity");a.css("opacity",b.opacity)},stop:function(a,b){a=d(this).data("draggable").options;a._opacity&&d(b.helper).css("opacity",a._opacity)}});d.ui.plugin.add("draggable","scroll",{start:function(){var a=d(this).data("draggable");if(a.scrollParent[0]!=document&&a.scrollParent[0].tagName!="HTML")a.overflowOffset=a.scrollParent.offset()},drag:function(a){var b=d(this).data("draggable"),c=b.options,f=false;if(b.scrollParent[0]!=document&&b.scrollParent[0].tagName!="HTML"){if(!c.axis||c.axis!=
"x")if(b.overflowOffset.top+b.scrollParent[0].offsetHeight-a.pageY<c.scrollSensitivity)b.scrollParent[0].scrollTop=f=b.scrollParent[0].scrollTop+c.scrollSpeed;else if(a.pageY-b.overflowOffset.top<c.scrollSensitivity)b.scrollParent[0].scrollTop=f=b.scrollParent[0].scrollTop-c.scrollSpeed;if(!c.axis||c.axis!="y")if(b.overflowOffset.left+b.scrollParent[0].offsetWidth-a.pageX<c.scrollSensitivity)b.scrollParent[0].scrollLeft=f=b.scrollParent[0].scrollLeft+c.scrollSpeed;else if(a.pageX-b.overflowOffset.left<
c.scrollSensitivity)b.scrollParent[0].scrollLeft=f=b.scrollParent[0].scrollLeft-c.scrollSpeed}else{if(!c.axis||c.axis!="x")if(a.pageY-d(document).scrollTop()<c.scrollSensitivity)f=d(document).scrollTop(d(document).scrollTop()-c.scrollSpeed);else if(d(window).height()-(a.pageY-d(document).scrollTop())<c.scrollSensitivity)f=d(document).scrollTop(d(document).scrollTop()+c.scrollSpeed);if(!c.axis||c.axis!="y")if(a.pageX-d(document).scrollLeft()<c.scrollSensitivity)f=d(document).scrollLeft(d(document).scrollLeft()-
c.scrollSpeed);else if(d(window).width()-(a.pageX-d(document).scrollLeft())<c.scrollSensitivity)f=d(document).scrollLeft(d(document).scrollLeft()+c.scrollSpeed)}f!==false&&d.ui.ddmanager&&!c.dropBehaviour&&d.ui.ddmanager.prepareOffsets(b,a)}});d.ui.plugin.add("draggable","snap",{start:function(){var a=d(this).data("draggable"),b=a.options;a.snapElements=[];d(b.snap.constructor!=String?b.snap.items||":data(draggable)":b.snap).each(function(){var c=d(this),f=c.offset();this!=a.element[0]&&a.snapElements.push({item:this,
width:c.outerWidth(),height:c.outerHeight(),top:f.top,left:f.left})})},drag:function(a,b){for(var c=d(this).data("draggable"),f=c.options,e=f.snapTolerance,h=b.offset.left,g=h+c.helperProportions.width,n=b.offset.top,o=n+c.helperProportions.height,i=c.snapElements.length-1;i>=0;i--){var j=c.snapElements[i].left,l=j+c.snapElements[i].width,k=c.snapElements[i].top,m=k+c.snapElements[i].height;if(j-e<h&&h<l+e&&k-e<n&&n<m+e||j-e<h&&h<l+e&&k-e<o&&o<m+e||j-e<g&&g<l+e&&k-e<n&&n<m+e||j-e<g&&g<l+e&&k-e<o&&
o<m+e){if(f.snapMode!="inner"){var p=Math.abs(k-o)<=e,q=Math.abs(m-n)<=e,r=Math.abs(j-g)<=e,s=Math.abs(l-h)<=e;if(p)b.position.top=c._convertPositionTo("relative",{top:k-c.helperProportions.height,left:0}).top-c.margins.top;if(q)b.position.top=c._convertPositionTo("relative",{top:m,left:0}).top-c.margins.top;if(r)b.position.left=c._convertPositionTo("relative",{top:0,left:j-c.helperProportions.width}).left-c.margins.left;if(s)b.position.left=c._convertPositionTo("relative",{top:0,left:l}).left-c.margins.left}var t=
p||q||r||s;if(f.snapMode!="outer"){p=Math.abs(k-n)<=e;q=Math.abs(m-o)<=e;r=Math.abs(j-h)<=e;s=Math.abs(l-g)<=e;if(p)b.position.top=c._convertPositionTo("relative",{top:k,left:0}).top-c.margins.top;if(q)b.position.top=c._convertPositionTo("relative",{top:m-c.helperProportions.height,left:0}).top-c.margins.top;if(r)b.position.left=c._convertPositionTo("relative",{top:0,left:j}).left-c.margins.left;if(s)b.position.left=c._convertPositionTo("relative",{top:0,left:l-c.helperProportions.width}).left-c.margins.left}if(!c.snapElements[i].snapping&&
(p||q||r||s||t))c.options.snap.snap&&c.options.snap.snap.call(c.element,a,d.extend(c._uiHash(),{snapItem:c.snapElements[i].item}));c.snapElements[i].snapping=p||q||r||s||t}else{c.snapElements[i].snapping&&c.options.snap.release&&c.options.snap.release.call(c.element,a,d.extend(c._uiHash(),{snapItem:c.snapElements[i].item}));c.snapElements[i].snapping=false}}}});d.ui.plugin.add("draggable","stack",{start:function(){var a=d(this).data("draggable").options;a=d.makeArray(d(a.stack)).sort(function(c,f){return(parseInt(d(c).css("zIndex"),
10)||0)-(parseInt(d(f).css("zIndex"),10)||0)});if(a.length){var b=parseInt(a[0].style.zIndex)||0;d(a).each(function(c){this.style.zIndex=b+c});this[0].style.zIndex=b+a.length}}});d.ui.plugin.add("draggable","zIndex",{start:function(a,b){a=d(b.helper);b=d(this).data("draggable").options;if(a.css("zIndex"))b._zIndex=a.css("zIndex");a.css("zIndex",b.zIndex)},stop:function(a,b){a=d(this).data("draggable").options;a._zIndex&&d(b.helper).css("zIndex",a._zIndex)}})})(jQuery);
;/*
 * jQuery UI Droppable 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Droppables
 *
 * Depends:
 *  jquery.ui.core.js
 *  jquery.ui.widget.js
 *  jquery.ui.mouse.js
 *  jquery.ui.draggable.js
 */
(function(d){d.widget("ui.droppable",{widgetEventPrefix:"drop",options:{accept:"*",activeClass:false,addClasses:true,greedy:false,hoverClass:false,scope:"default",tolerance:"intersect"},_create:function(){var a=this.options,b=a.accept;this.isover=0;this.isout=1;this.accept=d.isFunction(b)?b:function(c){return c.is(b)};this.proportions={width:this.element[0].offsetWidth,height:this.element[0].offsetHeight};d.ui.ddmanager.droppables[a.scope]=d.ui.ddmanager.droppables[a.scope]||[];d.ui.ddmanager.droppables[a.scope].push(this);
a.addClasses&&this.element.addClass("ui-droppable")},destroy:function(){for(var a=d.ui.ddmanager.droppables[this.options.scope],b=0;b<a.length;b++)a[b]==this&&a.splice(b,1);this.element.removeClass("ui-droppable ui-droppable-disabled").removeData("droppable").unbind(".droppable");return this},_setOption:function(a,b){if(a=="accept")this.accept=d.isFunction(b)?b:function(c){return c.is(b)};d.Widget.prototype._setOption.apply(this,arguments)},_activate:function(a){var b=d.ui.ddmanager.current;this.options.activeClass&&
this.element.addClass(this.options.activeClass);b&&this._trigger("activate",a,this.ui(b))},_deactivate:function(a){var b=d.ui.ddmanager.current;this.options.activeClass&&this.element.removeClass(this.options.activeClass);b&&this._trigger("deactivate",a,this.ui(b))},_over:function(a){var b=d.ui.ddmanager.current;if(!(!b||(b.currentItem||b.element)[0]==this.element[0]))if(this.accept.call(this.element[0],b.currentItem||b.element)){this.options.hoverClass&&this.element.addClass(this.options.hoverClass);
this._trigger("over",a,this.ui(b))}},_out:function(a){var b=d.ui.ddmanager.current;if(!(!b||(b.currentItem||b.element)[0]==this.element[0]))if(this.accept.call(this.element[0],b.currentItem||b.element)){this.options.hoverClass&&this.element.removeClass(this.options.hoverClass);this._trigger("out",a,this.ui(b))}},_drop:function(a,b){var c=b||d.ui.ddmanager.current;if(!c||(c.currentItem||c.element)[0]==this.element[0])return false;var e=false;this.element.find(":data(droppable)").not(".ui-draggable-dragging").each(function(){var g=
d.data(this,"droppable");if(g.options.greedy&&!g.options.disabled&&g.options.scope==c.options.scope&&g.accept.call(g.element[0],c.currentItem||c.element)&&d.ui.intersect(c,d.extend(g,{offset:g.element.offset()}),g.options.tolerance)){e=true;return false}});if(e)return false;if(this.accept.call(this.element[0],c.currentItem||c.element)){this.options.activeClass&&this.element.removeClass(this.options.activeClass);this.options.hoverClass&&this.element.removeClass(this.options.hoverClass);this._trigger("drop",
a,this.ui(c));return this.element}return false},ui:function(a){return{draggable:a.currentItem||a.element,helper:a.helper,position:a.position,offset:a.positionAbs}}});d.extend(d.ui.droppable,{version:"1.8.16"});d.ui.intersect=function(a,b,c){if(!b.offset)return false;var e=(a.positionAbs||a.position.absolute).left,g=e+a.helperProportions.width,f=(a.positionAbs||a.position.absolute).top,h=f+a.helperProportions.height,i=b.offset.left,k=i+b.proportions.width,j=b.offset.top,l=j+b.proportions.height;
switch(c){case "fit":return i<=e&&g<=k&&j<=f&&h<=l;case "intersect":return i<e+a.helperProportions.width/2&&g-a.helperProportions.width/2<k&&j<f+a.helperProportions.height/2&&h-a.helperProportions.height/2<l;case "pointer":return d.ui.isOver((a.positionAbs||a.position.absolute).top+(a.clickOffset||a.offset.click).top,(a.positionAbs||a.position.absolute).left+(a.clickOffset||a.offset.click).left,j,i,b.proportions.height,b.proportions.width);case "touch":return(f>=j&&f<=l||h>=j&&h<=l||f<j&&h>l)&&(e>=
i&&e<=k||g>=i&&g<=k||e<i&&g>k);default:return false}};d.ui.ddmanager={current:null,droppables:{"default":[]},prepareOffsets:function(a,b){var c=d.ui.ddmanager.droppables[a.options.scope]||[],e=b?b.type:null,g=(a.currentItem||a.element).find(":data(droppable)").andSelf(),f=0;a:for(;f<c.length;f++)if(!(c[f].options.disabled||a&&!c[f].accept.call(c[f].element[0],a.currentItem||a.element))){for(var h=0;h<g.length;h++)if(g[h]==c[f].element[0]){c[f].proportions.height=0;continue a}c[f].visible=c[f].element.css("display")!=
"none";if(c[f].visible){e=="mousedown"&&c[f]._activate.call(c[f],b);c[f].offset=c[f].element.offset();c[f].proportions={width:c[f].element[0].offsetWidth,height:c[f].element[0].offsetHeight}}}},drop:function(a,b){var c=false;d.each(d.ui.ddmanager.droppables[a.options.scope]||[],function(){if(this.options){if(!this.options.disabled&&this.visible&&d.ui.intersect(a,this,this.options.tolerance))c=c||this._drop.call(this,b);if(!this.options.disabled&&this.visible&&this.accept.call(this.element[0],a.currentItem||
a.element)){this.isout=1;this.isover=0;this._deactivate.call(this,b)}}});return c},dragStart:function(a,b){a.element.parents(":not(body,html)").bind("scroll.droppable",function(){a.options.refreshPositions||d.ui.ddmanager.prepareOffsets(a,b)})},drag:function(a,b){a.options.refreshPositions&&d.ui.ddmanager.prepareOffsets(a,b);d.each(d.ui.ddmanager.droppables[a.options.scope]||[],function(){if(!(this.options.disabled||this.greedyChild||!this.visible)){var c=d.ui.intersect(a,this,this.options.tolerance);
if(c=!c&&this.isover==1?"isout":c&&this.isover==0?"isover":null){var e;if(this.options.greedy){var g=this.element.parents(":data(droppable):eq(0)");if(g.length){e=d.data(g[0],"droppable");e.greedyChild=c=="isover"?1:0}}if(e&&c=="isover"){e.isover=0;e.isout=1;e._out.call(e,b)}this[c]=1;this[c=="isout"?"isover":"isout"]=0;this[c=="isover"?"_over":"_out"].call(this,b);if(e&&c=="isout"){e.isout=0;e.isover=1;e._over.call(e,b)}}}})},dragStop:function(a,b){a.element.parents(":not(body,html)").unbind("scroll.droppable");
a.options.refreshPositions||d.ui.ddmanager.prepareOffsets(a,b)}}})(jQuery);
;/*
 * jQuery UI Sortable 1.8.6
 *
 * Copyright 2010, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Sortables
 *
 * Depends:
 *  jquery.ui.core.js
 *  jquery.ui.mouse.js
 *  jquery.ui.widget.js
 */
(function(d){d.widget("ui.sortable",d.ui.mouse,{widgetEventPrefix:"sort",options:{appendTo:"parent",axis:false,connectWith:false,containment:false,cursor:"auto",cursorAt:false,dropOnEmpty:true,forcePlaceholderSize:false,forceHelperSize:false,grid:false,handle:false,helper:"original",items:"> *",opacity:false,placeholder:false,revert:false,scroll:true,scrollSensitivity:20,scrollSpeed:20,scope:"default",tolerance:"intersect",zIndex:1E3},_create:function(){this.containerCache={};this.element.addClass("ui-sortable");
this.refresh();this.floating=this.items.length?/left|right/.test(this.items[0].item.css("float")):false;this.offset=this.element.offset();this._mouseInit()},destroy:function(){this.element.removeClass("ui-sortable ui-sortable-disabled").removeData("sortable").unbind(".sortable");this._mouseDestroy();for(var a=this.items.length-1;a>=0;a--)this.items[a].item.removeData("sortable-item");return this},_setOption:function(a,b){if(a==="disabled"){this.options[a]=b;this.widget()[b?"addClass":"removeClass"]("ui-sortable-disabled")}else d.Widget.prototype._setOption.apply(this,
arguments)},_mouseCapture:function(a,b){if(this.reverting)return false;if(this.options.disabled||this.options.type=="static")return false;this._refreshItems(a);var c=null,e=this;d(a.target).parents().each(function(){if(d.data(this,"sortable-item")==e){c=d(this);return false}});if(d.data(a.target,"sortable-item")==e)c=d(a.target);if(!c)return false;if(this.options.handle&&!b){var f=false;d(this.options.handle,c).find("*").andSelf().each(function(){if(this==a.target)f=true});if(!f)return false}this.currentItem=
c;this._removeCurrentsFromItems();return true},_mouseStart:function(a,b,c){b=this.options;var e=this;this.currentContainer=this;this.refreshPositions();this.helper=this._createHelper(a);this._cacheHelperProportions();this._cacheMargins();this.scrollParent=this.helper.scrollParent();this.offset=this.currentItem.offset();this.offset={top:this.offset.top-this.margins.top,left:this.offset.left-this.margins.left};this.helper.css("position","absolute");this.cssPosition=this.helper.css("position");d.extend(this.offset,
{click:{left:a.pageX-this.offset.left,top:a.pageY-this.offset.top},parent:this._getParentOffset(),relative:this._getRelativeOffset()});this.originalPosition=this._generatePosition(a);this.originalPageX=a.pageX;this.originalPageY=a.pageY;b.cursorAt&&this._adjustOffsetFromHelper(b.cursorAt);this.domPosition={prev:this.currentItem.prev()[0],parent:this.currentItem.parent()[0]};this.helper[0]!=this.currentItem[0]&&this.currentItem.hide();this._createPlaceholder();b.containment&&this._setContainment();
if(b.cursor){if(d("body").css("cursor"))this._storedCursor=d("body").css("cursor");d("body").css("cursor",b.cursor)}if(b.opacity){if(this.helper.css("opacity"))this._storedOpacity=this.helper.css("opacity");this.helper.css("opacity",b.opacity)}if(b.zIndex){if(this.helper.css("zIndex"))this._storedZIndex=this.helper.css("zIndex");this.helper.css("zIndex",b.zIndex)}if(this.scrollParent[0]!=document&&this.scrollParent[0].tagName!="HTML")this.overflowOffset=this.scrollParent.offset();this._trigger("start",
a,this._uiHash());this._preserveHelperProportions||this._cacheHelperProportions();if(!c)for(c=this.containers.length-1;c>=0;c--)this.containers[c]._trigger("activate",a,e._uiHash(this));if(d.ui.ddmanager)d.ui.ddmanager.current=this;d.ui.ddmanager&&!b.dropBehaviour&&d.ui.ddmanager.prepareOffsets(this,a);this.dragging=true;this.helper.addClass("ui-sortable-helper");this._mouseDrag(a);return true},_mouseDrag:function(a){this.position=this._generatePosition(a);this.positionAbs=this._convertPositionTo("absolute");
if(!this.lastPositionAbs)this.lastPositionAbs=this.positionAbs;if(this.options.scroll){var b=this.options,c=false;if(this.scrollParent[0]!=document&&this.scrollParent[0].tagName!="HTML"){if(this.overflowOffset.top+this.scrollParent[0].offsetHeight-a.pageY<b.scrollSensitivity)this.scrollParent[0].scrollTop=c=this.scrollParent[0].scrollTop+b.scrollSpeed;else if(a.pageY-this.overflowOffset.top<b.scrollSensitivity)this.scrollParent[0].scrollTop=c=this.scrollParent[0].scrollTop-b.scrollSpeed;if(this.overflowOffset.left+
this.scrollParent[0].offsetWidth-a.pageX<b.scrollSensitivity)this.scrollParent[0].scrollLeft=c=this.scrollParent[0].scrollLeft+b.scrollSpeed;else if(a.pageX-this.overflowOffset.left<b.scrollSensitivity)this.scrollParent[0].scrollLeft=c=this.scrollParent[0].scrollLeft-b.scrollSpeed}else{if(a.pageY-d(document).scrollTop()<b.scrollSensitivity)c=d(document).scrollTop(d(document).scrollTop()-b.scrollSpeed);else if(d(window).height()-(a.pageY-d(document).scrollTop())<b.scrollSensitivity)c=d(document).scrollTop(d(document).scrollTop()+
b.scrollSpeed);if(a.pageX-d(document).scrollLeft()<b.scrollSensitivity)c=d(document).scrollLeft(d(document).scrollLeft()-b.scrollSpeed);else if(d(window).width()-(a.pageX-d(document).scrollLeft())<b.scrollSensitivity)c=d(document).scrollLeft(d(document).scrollLeft()+b.scrollSpeed)}c!==false&&d.ui.ddmanager&&!b.dropBehaviour&&d.ui.ddmanager.prepareOffsets(this,a)}this.positionAbs=this._convertPositionTo("absolute");if(!this.options.axis||this.options.axis!="y")this.helper[0].style.left=this.position.left+
"px";if(!this.options.axis||this.options.axis!="x")this.helper[0].style.top=this.position.top+"px";for(b=this.items.length-1;b>=0;b--){c=this.items[b];var e=c.item[0],f=this._intersectsWithPointer(c);if(f)if(e!=this.currentItem[0]&&this.placeholder[f==1?"next":"prev"]()[0]!=e&&!d.ui.contains(this.placeholder[0],e)&&(this.options.type=="semi-dynamic"?!d.ui.contains(this.element[0],e):true)){this.direction=f==1?"down":"up";if(this.options.tolerance=="pointer"||this._intersectsWithSides(c))this._rearrange(a,
c);else break;this._trigger("change",a,this._uiHash());break}}this._contactContainers(a);d.ui.ddmanager&&d.ui.ddmanager.drag(this,a);this._trigger("sort",a,this._uiHash());this.lastPositionAbs=this.positionAbs;return false},_mouseStop:function(a,b){if(a){d.ui.ddmanager&&!this.options.dropBehaviour&&d.ui.ddmanager.drop(this,a);if(this.options.revert){var c=this;b=c.placeholder.offset();c.reverting=true;d(this.helper).animate({left:b.left-this.offset.parent.left-c.margins.left+(this.offsetParent[0]==
document.body?0:this.offsetParent[0].scrollLeft),top:b.top-this.offset.parent.top-c.margins.top+(this.offsetParent[0]==document.body?0:this.offsetParent[0].scrollTop)},parseInt(this.options.revert,10)||500,function(){c._clear(a)})}else this._clear(a,b);return false}},cancel:function(){var a=this;if(this.dragging){this._mouseUp();this.options.helper=="original"?this.currentItem.css(this._storedCSS).removeClass("ui-sortable-helper"):this.currentItem.show();for(var b=this.containers.length-1;b>=0;b--){this.containers[b]._trigger("deactivate",
null,a._uiHash(this));if(this.containers[b].containerCache.over){this.containers[b]._trigger("out",null,a._uiHash(this));this.containers[b].containerCache.over=0}}}this.placeholder[0].parentNode&&this.placeholder[0].parentNode.removeChild(this.placeholder[0]);this.options.helper!="original"&&this.helper&&this.helper[0].parentNode&&this.helper.remove();d.extend(this,{helper:null,dragging:false,reverting:false,_noFinalSort:null});this.domPosition.prev?d(this.domPosition.prev).after(this.currentItem):
d(this.domPosition.parent).prepend(this.currentItem);return this},serialize:function(a){var b=this._getItemsAsjQuery(a&&a.connected),c=[];a=a||{};d(b).each(function(){var e=(d(a.item||this).attr(a.attribute||"id")||"").match(a.expression||/(.+)[-=_](.+)/);if(e)c.push((a.key||e[1]+"[]")+"="+(a.key&&a.expression?e[1]:e[2]))});!c.length&&a.key&&c.push(a.key+"=");return c.join("&")},toArray:function(a){var b=this._getItemsAsjQuery(a&&a.connected),c=[];a=a||{};b.each(function(){c.push(d(a.item||this).attr(a.attribute||
"id")||"")});return c},_intersectsWith:function(a){var b=this.positionAbs.left,c=b+this.helperProportions.width,e=this.positionAbs.top,f=e+this.helperProportions.height,g=a.left,h=g+a.width,i=a.top,k=i+a.height,j=this.offset.click.top,l=this.offset.click.left;j=e+j>i&&e+j<k&&b+l>g&&b+l<h;return this.options.tolerance=="pointer"||this.options.forcePointerForContainers||this.options.tolerance!="pointer"&&this.helperProportions[this.floating?"width":"height"]>a[this.floating?"width":"height"]?j:g<b+
this.helperProportions.width/2&&c-this.helperProportions.width/2<h&&i<e+this.helperProportions.height/2&&f-this.helperProportions.height/2<k},_intersectsWithPointer:function(a){var b=d.ui.isOverAxis(this.positionAbs.top+this.offset.click.top,a.top,a.height);a=d.ui.isOverAxis(this.positionAbs.left+this.offset.click.left,a.left,a.width);b=b&&a;a=this._getDragVerticalDirection();var c=this._getDragHorizontalDirection();if(!b)return false;return this.floating?c&&c=="right"||a=="down"?2:1:a&&(a=="down"?
2:1)},_intersectsWithSides:function(a){var b=d.ui.isOverAxis(this.positionAbs.top+this.offset.click.top,a.top+a.height/2,a.height);a=d.ui.isOverAxis(this.positionAbs.left+this.offset.click.left,a.left+a.width/2,a.width);var c=this._getDragVerticalDirection(),e=this._getDragHorizontalDirection();return this.floating&&e?e=="right"&&a||e=="left"&&!a:c&&(c=="down"&&b||c=="up"&&!b)},_getDragVerticalDirection:function(){var a=this.positionAbs.top-this.lastPositionAbs.top;return a!=0&&(a>0?"down":"up")},
_getDragHorizontalDirection:function(){var a=this.positionAbs.left-this.lastPositionAbs.left;return a!=0&&(a>0?"right":"left")},refresh:function(a){this._refreshItems(a);this.refreshPositions();return this},_connectWith:function(){var a=this.options;return a.connectWith.constructor==String?[a.connectWith]:a.connectWith},_getItemsAsjQuery:function(a){var b=[],c=[],e=this._connectWith();if(e&&a)for(a=e.length-1;a>=0;a--)for(var f=d(e[a]),g=f.length-1;g>=0;g--){var h=d.data(f[g],"sortable");if(h&&h!=
this&&!h.options.disabled)c.push([d.isFunction(h.options.items)?h.options.items.call(h.element):d(h.options.items,h.element).not(".ui-sortable-helper").not(".ui-sortable-placeholder"),h])}c.push([d.isFunction(this.options.items)?this.options.items.call(this.element,null,{options:this.options,item:this.currentItem}):d(this.options.items,this.element).not(".ui-sortable-helper").not(".ui-sortable-placeholder"),this]);for(a=c.length-1;a>=0;a--)c[a][0].each(function(){b.push(this)});return d(b)},_removeCurrentsFromItems:function(){for(var a=
this.currentItem.find(":data(sortable-item)"),b=0;b<this.items.length;b++)for(var c=0;c<a.length;c++)a[c]==this.items[b].item[0]&&this.items.splice(b,1)},_refreshItems:function(a){this.items=[];this.containers=[this];var b=this.items,c=[[d.isFunction(this.options.items)?this.options.items.call(this.element[0],a,{item:this.currentItem}):d(this.options.items,this.element),this]],e=this._connectWith();if(e)for(var f=e.length-1;f>=0;f--)for(var g=d(e[f]),h=g.length-1;h>=0;h--){var i=d.data(g[h],"sortable");
if(i&&i!=this&&!i.options.disabled){c.push([d.isFunction(i.options.items)?i.options.items.call(i.element[0],a,{item:this.currentItem}):d(i.options.items,i.element),i]);this.containers.push(i)}}for(f=c.length-1;f>=0;f--){a=c[f][1];e=c[f][0];h=0;for(g=e.length;h<g;h++){i=d(e[h]);i.data("sortable-item",a);b.push({item:i,instance:a,width:0,height:0,left:0,top:0})}}},refreshPositions:function(a){if(this.offsetParent&&this.helper)this.offset.parent=this._getParentOffset();for(var b=this.items.length-1;b>=
0;b--){var c=this.items[b],e=this.options.toleranceElement?d(this.options.toleranceElement,c.item):c.item;if(!a){c.width=e.outerWidth();c.height=e.outerHeight()}e=e.offset();c.left=e.left;c.top=e.top}if(this.options.custom&&this.options.custom.refreshContainers)this.options.custom.refreshContainers.call(this);else for(b=this.containers.length-1;b>=0;b--){e=this.containers[b].element.offset();this.containers[b].containerCache.left=e.left;this.containers[b].containerCache.top=e.top;this.containers[b].containerCache.width=
this.containers[b].element.outerWidth();this.containers[b].containerCache.height=this.containers[b].element.outerHeight()}return this},_createPlaceholder:function(a){var b=a||this,c=b.options;if(!c.placeholder||c.placeholder.constructor==String){var e=c.placeholder;c.placeholder={element:function(){var f=d(document.createElement(b.currentItem[0].nodeName)).addClass(e||b.currentItem[0].className+" ui-sortable-placeholder").removeClass("ui-sortable-helper")[0];if(!e)f.style.visibility="hidden";return f},
update:function(f,g){if(!(e&&!c.forcePlaceholderSize)){g.height()||g.height(b.currentItem.innerHeight()-parseInt(b.currentItem.css("paddingTop")||0,10)-parseInt(b.currentItem.css("paddingBottom")||0,10));g.width()||g.width(b.currentItem.innerWidth()-parseInt(b.currentItem.css("paddingLeft")||0,10)-parseInt(b.currentItem.css("paddingRight")||0,10))}}}}b.placeholder=d(c.placeholder.element.call(b.element,b.currentItem));b.currentItem.after(b.placeholder);c.placeholder.update(b,b.placeholder)},_contactContainers:function(a){for(var b=
null,c=null,e=this.containers.length-1;e>=0;e--)if(!d.ui.contains(this.currentItem[0],this.containers[e].element[0]))if(this._intersectsWith(this.containers[e].containerCache)){if(!(b&&d.ui.contains(this.containers[e].element[0],b.element[0]))){b=this.containers[e];c=e}}else if(this.containers[e].containerCache.over){this.containers[e]._trigger("out",a,this._uiHash(this));this.containers[e].containerCache.over=0}if(b)if(this.containers.length===1){this.containers[c]._trigger("over",a,this._uiHash(this));
this.containers[c].containerCache.over=1}else if(this.currentContainer!=this.containers[c]){b=1E4;e=null;for(var f=this.positionAbs[this.containers[c].floating?"left":"top"],g=this.items.length-1;g>=0;g--)if(d.ui.contains(this.containers[c].element[0],this.items[g].item[0])){var h=this.items[g][this.containers[c].floating?"left":"top"];if(Math.abs(h-f)<b){b=Math.abs(h-f);e=this.items[g]}}if(e||this.options.dropOnEmpty){this.currentContainer=this.containers[c];e?this._rearrange(a,e,null,true):this._rearrange(a,
null,this.containers[c].element,true);this._trigger("change",a,this._uiHash());this.containers[c]._trigger("change",a,this._uiHash(this));this.options.placeholder.update(this.currentContainer,this.placeholder);this.containers[c]._trigger("over",a,this._uiHash(this));this.containers[c].containerCache.over=1}}},_createHelper:function(a){var b=this.options;a=d.isFunction(b.helper)?d(b.helper.apply(this.element[0],[a,this.currentItem])):b.helper=="clone"?this.currentItem.clone():this.currentItem;a.parents("body").length||
d(b.appendTo!="parent"?b.appendTo:this.currentItem[0].parentNode)[0].appendChild(a[0]);if(a[0]==this.currentItem[0])this._storedCSS={width:this.currentItem[0].style.width,height:this.currentItem[0].style.height,position:this.currentItem.css("position"),top:this.currentItem.css("top"),left:this.currentItem.css("left")};if(a[0].style.width==""||b.forceHelperSize)a.width(this.currentItem.width());if(a[0].style.height==""||b.forceHelperSize)a.height(this.currentItem.height());return a},_adjustOffsetFromHelper:function(a){if(typeof a==
"string")a=a.split(" ");if(d.isArray(a))a={left:+a[0],top:+a[1]||0};if("left"in a)this.offset.click.left=a.left+this.margins.left;if("right"in a)this.offset.click.left=this.helperProportions.width-a.right+this.margins.left;if("top"in a)this.offset.click.top=a.top+this.margins.top;if("bottom"in a)this.offset.click.top=this.helperProportions.height-a.bottom+this.margins.top},_getParentOffset:function(){this.offsetParent=this.helper.offsetParent();var a=this.offsetParent.offset();if(this.cssPosition==
"absolute"&&this.scrollParent[0]!=document&&d.ui.contains(this.scrollParent[0],this.offsetParent[0])){a.left+=this.scrollParent.scrollLeft();a.top+=this.scrollParent.scrollTop()}if(this.offsetParent[0]==document.body||this.offsetParent[0].tagName&&this.offsetParent[0].tagName.toLowerCase()=="html"&&d.browser.msie)a={top:0,left:0};return{top:a.top+(parseInt(this.offsetParent.css("borderTopWidth"),10)||0),left:a.left+(parseInt(this.offsetParent.css("borderLeftWidth"),10)||0)}},_getRelativeOffset:function(){if(this.cssPosition==
"relative"){var a=this.currentItem.position();return{top:a.top-(parseInt(this.helper.css("top"),10)||0)+this.scrollParent.scrollTop(),left:a.left-(parseInt(this.helper.css("left"),10)||0)+this.scrollParent.scrollLeft()}}else return{top:0,left:0}},_cacheMargins:function(){this.margins={left:parseInt(this.currentItem.css("marginLeft"),10)||0,top:parseInt(this.currentItem.css("marginTop"),10)||0}},_cacheHelperProportions:function(){this.helperProportions={width:this.helper.outerWidth(),height:this.helper.outerHeight()}},
_setContainment:function(){var a=this.options;if(a.containment=="parent")a.containment=this.helper[0].parentNode;if(a.containment=="document"||a.containment=="window")this.containment=[0-this.offset.relative.left-this.offset.parent.left,0-this.offset.relative.top-this.offset.parent.top,d(a.containment=="document"?document:window).width()-this.helperProportions.width-this.margins.left,(d(a.containment=="document"?document:window).height()||document.body.parentNode.scrollHeight)-this.helperProportions.height-
this.margins.top];if(!/^(document|window|parent)$/.test(a.containment)){var b=d(a.containment)[0];a=d(a.containment).offset();var c=d(b).css("overflow")!="hidden";this.containment=[a.left+(parseInt(d(b).css("borderLeftWidth"),10)||0)+(parseInt(d(b).css("paddingLeft"),10)||0)-this.margins.left,a.top+(parseInt(d(b).css("borderTopWidth"),10)||0)+(parseInt(d(b).css("paddingTop"),10)||0)-this.margins.top,a.left+(c?Math.max(b.scrollWidth,b.offsetWidth):b.offsetWidth)-(parseInt(d(b).css("borderLeftWidth"),
10)||0)-(parseInt(d(b).css("paddingRight"),10)||0)-this.helperProportions.width-this.margins.left,a.top+(c?Math.max(b.scrollHeight,b.offsetHeight):b.offsetHeight)-(parseInt(d(b).css("borderTopWidth"),10)||0)-(parseInt(d(b).css("paddingBottom"),10)||0)-this.helperProportions.height-this.margins.top]}},_convertPositionTo:function(a,b){if(!b)b=this.position;a=a=="absolute"?1:-1;var c=this.cssPosition=="absolute"&&!(this.scrollParent[0]!=document&&d.ui.contains(this.scrollParent[0],this.offsetParent[0]))?
this.offsetParent:this.scrollParent,e=/(html|body)/i.test(c[0].tagName);return{top:b.top+this.offset.relative.top*a+this.offset.parent.top*a-(d.browser.safari&&this.cssPosition=="fixed"?0:(this.cssPosition=="fixed"?-this.scrollParent.scrollTop():e?0:c.scrollTop())*a),left:b.left+this.offset.relative.left*a+this.offset.parent.left*a-(d.browser.safari&&this.cssPosition=="fixed"?0:(this.cssPosition=="fixed"?-this.scrollParent.scrollLeft():e?0:c.scrollLeft())*a)}},_generatePosition:function(a){var b=
this.options,c=this.cssPosition=="absolute"&&!(this.scrollParent[0]!=document&&d.ui.contains(this.scrollParent[0],this.offsetParent[0]))?this.offsetParent:this.scrollParent,e=/(html|body)/i.test(c[0].tagName);if(this.cssPosition=="relative"&&!(this.scrollParent[0]!=document&&this.scrollParent[0]!=this.offsetParent[0]))this.offset.relative=this._getRelativeOffset();var f=a.pageX,g=a.pageY;if(this.originalPosition){if(this.containment){if(a.pageX-this.offset.click.left<this.containment[0])f=this.containment[0]+
this.offset.click.left;if(a.pageY-this.offset.click.top<this.containment[1])g=this.containment[1]+this.offset.click.top;if(a.pageX-this.offset.click.left>this.containment[2])f=this.containment[2]+this.offset.click.left;if(a.pageY-this.offset.click.top>this.containment[3])g=this.containment[3]+this.offset.click.top}if(b.grid){g=this.originalPageY+Math.round((g-this.originalPageY)/b.grid[1])*b.grid[1];g=this.containment?!(g-this.offset.click.top<this.containment[1]||g-this.offset.click.top>this.containment[3])?
g:!(g-this.offset.click.top<this.containment[1])?g-b.grid[1]:g+b.grid[1]:g;f=this.originalPageX+Math.round((f-this.originalPageX)/b.grid[0])*b.grid[0];f=this.containment?!(f-this.offset.click.left<this.containment[0]||f-this.offset.click.left>this.containment[2])?f:!(f-this.offset.click.left<this.containment[0])?f-b.grid[0]:f+b.grid[0]:f}}return{top:g-this.offset.click.top-this.offset.relative.top-this.offset.parent.top+(d.browser.safari&&this.cssPosition=="fixed"?0:this.cssPosition=="fixed"?-this.scrollParent.scrollTop():
e?0:c.scrollTop()),left:f-this.offset.click.left-this.offset.relative.left-this.offset.parent.left+(d.browser.safari&&this.cssPosition=="fixed"?0:this.cssPosition=="fixed"?-this.scrollParent.scrollLeft():e?0:c.scrollLeft())}},_rearrange:function(a,b,c,e){c?c[0].appendChild(this.placeholder[0]):b.item[0].parentNode.insertBefore(this.placeholder[0],this.direction=="down"?b.item[0]:b.item[0].nextSibling);this.counter=this.counter?++this.counter:1;var f=this,g=this.counter;window.setTimeout(function(){g==
f.counter&&f.refreshPositions(!e)},0)},_clear:function(a,b){this.reverting=false;var c=[];!this._noFinalSort&&this.currentItem[0].parentNode&&this.placeholder.before(this.currentItem);this._noFinalSort=null;if(this.helper[0]==this.currentItem[0]){for(var e in this._storedCSS)if(this._storedCSS[e]=="auto"||this._storedCSS[e]=="static")this._storedCSS[e]="";this.currentItem.css(this._storedCSS).removeClass("ui-sortable-helper")}else this.currentItem.show();this.fromOutside&&!b&&c.push(function(f){this._trigger("receive",
f,this._uiHash(this.fromOutside))});if((this.fromOutside||this.domPosition.prev!=this.currentItem.prev().not(".ui-sortable-helper")[0]||this.domPosition.parent!=this.currentItem.parent()[0])&&!b)c.push(function(f){this._trigger("update",f,this._uiHash())});if(!d.ui.contains(this.element[0],this.currentItem[0])){b||c.push(function(f){this._trigger("remove",f,this._uiHash())});for(e=this.containers.length-1;e>=0;e--)if(d.ui.contains(this.containers[e].element[0],this.currentItem[0])&&!b){c.push(function(f){return function(g){f._trigger("receive",
g,this._uiHash(this))}}.call(this,this.containers[e]));c.push(function(f){return function(g){f._trigger("update",g,this._uiHash(this))}}.call(this,this.containers[e]))}}for(e=this.containers.length-1;e>=0;e--){b||c.push(function(f){return function(g){f._trigger("deactivate",g,this._uiHash(this))}}.call(this,this.containers[e]));if(this.containers[e].containerCache.over){c.push(function(f){return function(g){f._trigger("out",g,this._uiHash(this))}}.call(this,this.containers[e]));this.containers[e].containerCache.over=
0}}this._storedCursor&&d("body").css("cursor",this._storedCursor);this._storedOpacity&&this.helper.css("opacity",this._storedOpacity);if(this._storedZIndex)this.helper.css("zIndex",this._storedZIndex=="auto"?"":this._storedZIndex);this.dragging=false;if(this.cancelHelperRemoval){if(!b){this._trigger("beforeStop",a,this._uiHash());for(e=0;e<c.length;e++)c[e].call(this,a);this._trigger("stop",a,this._uiHash())}return false}b||this._trigger("beforeStop",a,this._uiHash());this.placeholder[0].parentNode.removeChild(this.placeholder[0]);
this.helper[0]!=this.currentItem[0]&&this.helper.remove();this.helper=null;if(!b){for(e=0;e<c.length;e++)c[e].call(this,a);this._trigger("stop",a,this._uiHash())}this.fromOutside=false;return true},_trigger:function(){d.Widget.prototype._trigger.apply(this,arguments)===false&&this.cancel()},_uiHash:function(a){var b=a||this;return{helper:b.helper,placeholder:b.placeholder||d([]),position:b.position,originalPosition:b.originalPosition,offset:b.positionAbs,item:b.currentItem,sender:a?a.element:null}}});
d.extend(d.ui.sortable,{version:"1.8.6"})})(jQuery);
;/*
 * jQuery UI Resizable 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Resizables
 *
 * Depends:
 *  jquery.ui.core.js
 *  jquery.ui.mouse.js
 *  jquery.ui.widget.js
 */
(function(e){e.widget("ui.resizable",e.ui.mouse,{widgetEventPrefix:"resize",options:{alsoResize:false,animate:false,animateDuration:"slow",animateEasing:"swing",aspectRatio:false,autoHide:false,containment:false,ghost:false,grid:false,handles:"e,s,se",helper:false,maxHeight:null,maxWidth:null,minHeight:10,minWidth:10,zIndex:1E3},_create:function(){var b=this,a=this.options;this.element.addClass("ui-resizable");e.extend(this,{_aspectRatio:!!a.aspectRatio,aspectRatio:a.aspectRatio,originalElement:this.element,
_proportionallyResizeElements:[],_helper:a.helper||a.ghost||a.animate?a.helper||"ui-resizable-helper":null});if(this.element[0].nodeName.match(/canvas|textarea|input|select|button|img/i)){/relative/.test(this.element.css("position"))&&e.browser.opera&&this.element.css({position:"relative",top:"auto",left:"auto"});this.element.wrap(e('<div class="ui-wrapper" style="overflow: hidden;"></div>').css({position:this.element.css("position"),width:this.element.outerWidth(),height:this.element.outerHeight(),
top:this.element.css("top"),left:this.element.css("left")}));this.element=this.element.parent().data("resizable",this.element.data("resizable"));this.elementIsWrapper=true;this.element.css({marginLeft:this.originalElement.css("marginLeft"),marginTop:this.originalElement.css("marginTop"),marginRight:this.originalElement.css("marginRight"),marginBottom:this.originalElement.css("marginBottom")});this.originalElement.css({marginLeft:0,marginTop:0,marginRight:0,marginBottom:0});this.originalResizeStyle=
this.originalElement.css("resize");this.originalElement.css("resize","none");this._proportionallyResizeElements.push(this.originalElement.css({position:"static",zoom:1,display:"block"}));this.originalElement.css({margin:this.originalElement.css("margin")});this._proportionallyResize()}this.handles=a.handles||(!e(".ui-resizable-handle",this.element).length?"e,s,se":{n:".ui-resizable-n",e:".ui-resizable-e",s:".ui-resizable-s",w:".ui-resizable-w",se:".ui-resizable-se",sw:".ui-resizable-sw",ne:".ui-resizable-ne",
nw:".ui-resizable-nw"});if(this.handles.constructor==String){if(this.handles=="all")this.handles="n,e,s,w,se,sw,ne,nw";var c=this.handles.split(",");this.handles={};for(var d=0;d<c.length;d++){var f=e.trim(c[d]),g=e('<div class="ui-resizable-handle '+("ui-resizable-"+f)+'"></div>');/sw|se|ne|nw/.test(f)&&g.css({zIndex:++a.zIndex});"se"==f&&g.addClass("ui-icon ui-icon-gripsmall-diagonal-se");this.handles[f]=".ui-resizable-"+f;this.element.append(g)}}this._renderAxis=function(h){h=h||this.element;for(var i in this.handles){if(this.handles[i].constructor==
String)this.handles[i]=e(this.handles[i],this.element).show();if(this.elementIsWrapper&&this.originalElement[0].nodeName.match(/textarea|input|select|button/i)){var j=e(this.handles[i],this.element),l=0;l=/sw|ne|nw|se|n|s/.test(i)?j.outerHeight():j.outerWidth();j=["padding",/ne|nw|n/.test(i)?"Top":/se|sw|s/.test(i)?"Bottom":/^e$/.test(i)?"Right":"Left"].join("");h.css(j,l);this._proportionallyResize()}e(this.handles[i])}};this._renderAxis(this.element);this._handles=e(".ui-resizable-handle",this.element).disableSelection();
this._handles.mouseover(function(){if(!b.resizing){if(this.className)var h=this.className.match(/ui-resizable-(se|sw|ne|nw|n|e|s|w)/i);b.axis=h&&h[1]?h[1]:"se"}});if(a.autoHide){this._handles.hide();e(this.element).addClass("ui-resizable-autohide").hover(function(){if(!a.disabled){e(this).removeClass("ui-resizable-autohide");b._handles.show()}},function(){if(!a.disabled)if(!b.resizing){e(this).addClass("ui-resizable-autohide");b._handles.hide()}})}this._mouseInit()},destroy:function(){this._mouseDestroy();
var b=function(c){e(c).removeClass("ui-resizable ui-resizable-disabled ui-resizable-resizing").removeData("resizable").unbind(".resizable").find(".ui-resizable-handle").remove()};if(this.elementIsWrapper){b(this.element);var a=this.element;a.after(this.originalElement.css({position:a.css("position"),width:a.outerWidth(),height:a.outerHeight(),top:a.css("top"),left:a.css("left")})).remove()}this.originalElement.css("resize",this.originalResizeStyle);b(this.originalElement);return this},_mouseCapture:function(b){var a=
false;for(var c in this.handles)if(e(this.handles[c])[0]==b.target)a=true;return!this.options.disabled&&a},_mouseStart:function(b){var a=this.options,c=this.element.position(),d=this.element;this.resizing=true;this.documentScroll={top:e(document).scrollTop(),left:e(document).scrollLeft()};if(d.is(".ui-draggable")||/absolute/.test(d.css("position")))d.css({position:"absolute",top:c.top,left:c.left});e.browser.opera&&/relative/.test(d.css("position"))&&d.css({position:"relative",top:"auto",left:"auto"});
this._renderProxy();c=m(this.helper.css("left"));var f=m(this.helper.css("top"));if(a.containment){c+=e(a.containment).scrollLeft()||0;f+=e(a.containment).scrollTop()||0}this.offset=this.helper.offset();this.position={left:c,top:f};this.size=this._helper?{width:d.outerWidth(),height:d.outerHeight()}:{width:d.width(),height:d.height()};this.originalSize=this._helper?{width:d.outerWidth(),height:d.outerHeight()}:{width:d.width(),height:d.height()};this.originalPosition={left:c,top:f};this.sizeDiff=
{width:d.outerWidth()-d.width(),height:d.outerHeight()-d.height()};this.originalMousePosition={left:b.pageX,top:b.pageY};this.aspectRatio=typeof a.aspectRatio=="number"?a.aspectRatio:this.originalSize.width/this.originalSize.height||1;a=e(".ui-resizable-"+this.axis).css("cursor");e("body").css("cursor",a=="auto"?this.axis+"-resize":a);d.addClass("ui-resizable-resizing");this._propagate("start",b);return true},_mouseDrag:function(b){var a=this.helper,c=this.originalMousePosition,d=this._change[this.axis];
if(!d)return false;c=d.apply(this,[b,b.pageX-c.left||0,b.pageY-c.top||0]);this._updateVirtualBoundaries(b.shiftKey);if(this._aspectRatio||b.shiftKey)c=this._updateRatio(c,b);c=this._respectSize(c,b);this._propagate("resize",b);a.css({top:this.position.top+"px",left:this.position.left+"px",width:this.size.width+"px",height:this.size.height+"px"});!this._helper&&this._proportionallyResizeElements.length&&this._proportionallyResize();this._updateCache(c);this._trigger("resize",b,this.ui());return false},
_mouseStop:function(b){this.resizing=false;var a=this.options,c=this;if(this._helper){var d=this._proportionallyResizeElements,f=d.length&&/textarea/i.test(d[0].nodeName);d=f&&e.ui.hasScroll(d[0],"left")?0:c.sizeDiff.height;f=f?0:c.sizeDiff.width;f={width:c.helper.width()-f,height:c.helper.height()-d};d=parseInt(c.element.css("left"),10)+(c.position.left-c.originalPosition.left)||null;var g=parseInt(c.element.css("top"),10)+(c.position.top-c.originalPosition.top)||null;a.animate||this.element.css(e.extend(f,
{top:g,left:d}));c.helper.height(c.size.height);c.helper.width(c.size.width);this._helper&&!a.animate&&this._proportionallyResize()}e("body").css("cursor","auto");this.element.removeClass("ui-resizable-resizing");this._propagate("stop",b);this._helper&&this.helper.remove();return false},_updateVirtualBoundaries:function(b){var a=this.options,c,d,f;a={minWidth:k(a.minWidth)?a.minWidth:0,maxWidth:k(a.maxWidth)?a.maxWidth:Infinity,minHeight:k(a.minHeight)?a.minHeight:0,maxHeight:k(a.maxHeight)?a.maxHeight:
Infinity};if(this._aspectRatio||b){b=a.minHeight*this.aspectRatio;d=a.minWidth/this.aspectRatio;c=a.maxHeight*this.aspectRatio;f=a.maxWidth/this.aspectRatio;if(b>a.minWidth)a.minWidth=b;if(d>a.minHeight)a.minHeight=d;if(c<a.maxWidth)a.maxWidth=c;if(f<a.maxHeight)a.maxHeight=f}this._vBoundaries=a},_updateCache:function(b){this.offset=this.helper.offset();if(k(b.left))this.position.left=b.left;if(k(b.top))this.position.top=b.top;if(k(b.height))this.size.height=b.height;if(k(b.width))this.size.width=
b.width},_updateRatio:function(b){var a=this.position,c=this.size,d=this.axis;if(k(b.height))b.width=b.height*this.aspectRatio;else if(k(b.width))b.height=b.width/this.aspectRatio;if(d=="sw"){b.left=a.left+(c.width-b.width);b.top=null}if(d=="nw"){b.top=a.top+(c.height-b.height);b.left=a.left+(c.width-b.width)}return b},_respectSize:function(b){var a=this._vBoundaries,c=this.axis,d=k(b.width)&&a.maxWidth&&a.maxWidth<b.width,f=k(b.height)&&a.maxHeight&&a.maxHeight<b.height,g=k(b.width)&&a.minWidth&&
a.minWidth>b.width,h=k(b.height)&&a.minHeight&&a.minHeight>b.height;if(g)b.width=a.minWidth;if(h)b.height=a.minHeight;if(d)b.width=a.maxWidth;if(f)b.height=a.maxHeight;var i=this.originalPosition.left+this.originalSize.width,j=this.position.top+this.size.height,l=/sw|nw|w/.test(c);c=/nw|ne|n/.test(c);if(g&&l)b.left=i-a.minWidth;if(d&&l)b.left=i-a.maxWidth;if(h&&c)b.top=j-a.minHeight;if(f&&c)b.top=j-a.maxHeight;if((a=!b.width&&!b.height)&&!b.left&&b.top)b.top=null;else if(a&&!b.top&&b.left)b.left=
null;return b},_proportionallyResize:function(){if(this._proportionallyResizeElements.length)for(var b=this.helper||this.element,a=0;a<this._proportionallyResizeElements.length;a++){var c=this._proportionallyResizeElements[a];if(!this.borderDif){var d=[c.css("borderTopWidth"),c.css("borderRightWidth"),c.css("borderBottomWidth"),c.css("borderLeftWidth")],f=[c.css("paddingTop"),c.css("paddingRight"),c.css("paddingBottom"),c.css("paddingLeft")];this.borderDif=e.map(d,function(g,h){g=parseInt(g,10)||
0;h=parseInt(f[h],10)||0;return g+h})}e.browser.msie&&(e(b).is(":hidden")||e(b).parents(":hidden").length)||c.css({height:b.height()-this.borderDif[0]-this.borderDif[2]||0,width:b.width()-this.borderDif[1]-this.borderDif[3]||0})}},_renderProxy:function(){var b=this.options;this.elementOffset=this.element.offset();if(this._helper){this.helper=this.helper||e('<div style="overflow:hidden;"></div>');var a=e.browser.msie&&e.browser.version<7,c=a?1:0;a=a?2:-1;this.helper.addClass(this._helper).css({width:this.element.outerWidth()+
a,height:this.element.outerHeight()+a,position:"absolute",left:this.elementOffset.left-c+"px",top:this.elementOffset.top-c+"px",zIndex:++b.zIndex});this.helper.appendTo("body").disableSelection()}else this.helper=this.element},_change:{e:function(b,a){return{width:this.originalSize.width+a}},w:function(b,a){return{left:this.originalPosition.left+a,width:this.originalSize.width-a}},n:function(b,a,c){return{top:this.originalPosition.top+c,height:this.originalSize.height-c}},s:function(b,a,c){return{height:this.originalSize.height+
c}},se:function(b,a,c){return e.extend(this._change.s.apply(this,arguments),this._change.e.apply(this,[b,a,c]))},sw:function(b,a,c){return e.extend(this._change.s.apply(this,arguments),this._change.w.apply(this,[b,a,c]))},ne:function(b,a,c){return e.extend(this._change.n.apply(this,arguments),this._change.e.apply(this,[b,a,c]))},nw:function(b,a,c){return e.extend(this._change.n.apply(this,arguments),this._change.w.apply(this,[b,a,c]))}},_propagate:function(b,a){e.ui.plugin.call(this,b,[a,this.ui()]);
b!="resize"&&this._trigger(b,a,this.ui())},plugins:{},ui:function(){return{originalElement:this.originalElement,element:this.element,helper:this.helper,position:this.position,size:this.size,originalSize:this.originalSize,originalPosition:this.originalPosition}}});e.extend(e.ui.resizable,{version:"1.8.16"});e.ui.plugin.add("resizable","alsoResize",{start:function(){var b=e(this).data("resizable").options,a=function(c){e(c).each(function(){var d=e(this);d.data("resizable-alsoresize",{width:parseInt(d.width(),
10),height:parseInt(d.height(),10),left:parseInt(d.css("left"),10),top:parseInt(d.css("top"),10),position:d.css("position")})})};if(typeof b.alsoResize=="object"&&!b.alsoResize.parentNode)if(b.alsoResize.length){b.alsoResize=b.alsoResize[0];a(b.alsoResize)}else e.each(b.alsoResize,function(c){a(c)});else a(b.alsoResize)},resize:function(b,a){var c=e(this).data("resizable");b=c.options;var d=c.originalSize,f=c.originalPosition,g={height:c.size.height-d.height||0,width:c.size.width-d.width||0,top:c.position.top-
f.top||0,left:c.position.left-f.left||0},h=function(i,j){e(i).each(function(){var l=e(this),q=e(this).data("resizable-alsoresize"),p={},r=j&&j.length?j:l.parents(a.originalElement[0]).length?["width","height"]:["width","height","top","left"];e.each(r,function(n,o){if((n=(q[o]||0)+(g[o]||0))&&n>=0)p[o]=n||null});if(e.browser.opera&&/relative/.test(l.css("position"))){c._revertToRelativePosition=true;l.css({position:"absolute",top:"auto",left:"auto"})}l.css(p)})};typeof b.alsoResize=="object"&&!b.alsoResize.nodeType?
e.each(b.alsoResize,function(i,j){h(i,j)}):h(b.alsoResize)},stop:function(){var b=e(this).data("resizable"),a=b.options,c=function(d){e(d).each(function(){var f=e(this);f.css({position:f.data("resizable-alsoresize").position})})};if(b._revertToRelativePosition){b._revertToRelativePosition=false;typeof a.alsoResize=="object"&&!a.alsoResize.nodeType?e.each(a.alsoResize,function(d){c(d)}):c(a.alsoResize)}e(this).removeData("resizable-alsoresize")}});e.ui.plugin.add("resizable","animate",{stop:function(b){var a=
e(this).data("resizable"),c=a.options,d=a._proportionallyResizeElements,f=d.length&&/textarea/i.test(d[0].nodeName),g=f&&e.ui.hasScroll(d[0],"left")?0:a.sizeDiff.height;f={width:a.size.width-(f?0:a.sizeDiff.width),height:a.size.height-g};g=parseInt(a.element.css("left"),10)+(a.position.left-a.originalPosition.left)||null;var h=parseInt(a.element.css("top"),10)+(a.position.top-a.originalPosition.top)||null;a.element.animate(e.extend(f,h&&g?{top:h,left:g}:{}),{duration:c.animateDuration,easing:c.animateEasing,
step:function(){var i={width:parseInt(a.element.css("width"),10),height:parseInt(a.element.css("height"),10),top:parseInt(a.element.css("top"),10),left:parseInt(a.element.css("left"),10)};d&&d.length&&e(d[0]).css({width:i.width,height:i.height});a._updateCache(i);a._propagate("resize",b)}})}});e.ui.plugin.add("resizable","containment",{start:function(){var b=e(this).data("resizable"),a=b.element,c=b.options.containment;if(a=c instanceof e?c.get(0):/parent/.test(c)?a.parent().get(0):c){b.containerElement=
e(a);if(/document/.test(c)||c==document){b.containerOffset={left:0,top:0};b.containerPosition={left:0,top:0};b.parentData={element:e(document),left:0,top:0,width:e(document).width(),height:e(document).height()||document.body.parentNode.scrollHeight}}else{var d=e(a),f=[];e(["Top","Right","Left","Bottom"]).each(function(i,j){f[i]=m(d.css("padding"+j))});b.containerOffset=d.offset();b.containerPosition=d.position();b.containerSize={height:d.innerHeight()-f[3],width:d.innerWidth()-f[1]};c=b.containerOffset;
var g=b.containerSize.height,h=b.containerSize.width;h=e.ui.hasScroll(a,"left")?a.scrollWidth:h;g=e.ui.hasScroll(a)?a.scrollHeight:g;b.parentData={element:a,left:c.left,top:c.top,width:h,height:g}}}},resize:function(b){var a=e(this).data("resizable"),c=a.options,d=a.containerOffset,f=a.position;b=a._aspectRatio||b.shiftKey;var g={top:0,left:0},h=a.containerElement;if(h[0]!=document&&/static/.test(h.css("position")))g=d;if(f.left<(a._helper?d.left:0)){a.size.width+=a._helper?a.position.left-d.left:
a.position.left-g.left;if(b)a.size.height=a.size.width/c.aspectRatio;a.position.left=c.helper?d.left:0}if(f.top<(a._helper?d.top:0)){a.size.height+=a._helper?a.position.top-d.top:a.position.top;if(b)a.size.width=a.size.height*c.aspectRatio;a.position.top=a._helper?d.top:0}a.offset.left=a.parentData.left+a.position.left;a.offset.top=a.parentData.top+a.position.top;c=Math.abs((a._helper?a.offset.left-g.left:a.offset.left-g.left)+a.sizeDiff.width);d=Math.abs((a._helper?a.offset.top-g.top:a.offset.top-
d.top)+a.sizeDiff.height);f=a.containerElement.get(0)==a.element.parent().get(0);g=/relative|absolute/.test(a.containerElement.css("position"));if(f&&g)c-=a.parentData.left;if(c+a.size.width>=a.parentData.width){a.size.width=a.parentData.width-c;if(b)a.size.height=a.size.width/a.aspectRatio}if(d+a.size.height>=a.parentData.height){a.size.height=a.parentData.height-d;if(b)a.size.width=a.size.height*a.aspectRatio}},stop:function(){var b=e(this).data("resizable"),a=b.options,c=b.containerOffset,d=b.containerPosition,
f=b.containerElement,g=e(b.helper),h=g.offset(),i=g.outerWidth()-b.sizeDiff.width;g=g.outerHeight()-b.sizeDiff.height;b._helper&&!a.animate&&/relative/.test(f.css("position"))&&e(this).css({left:h.left-d.left-c.left,width:i,height:g});b._helper&&!a.animate&&/static/.test(f.css("position"))&&e(this).css({left:h.left-d.left-c.left,width:i,height:g})}});e.ui.plugin.add("resizable","ghost",{start:function(){var b=e(this).data("resizable"),a=b.options,c=b.size;b.ghost=b.originalElement.clone();b.ghost.css({opacity:0.25,
display:"block",position:"relative",height:c.height,width:c.width,margin:0,left:0,top:0}).addClass("ui-resizable-ghost").addClass(typeof a.ghost=="string"?a.ghost:"");b.ghost.appendTo(b.helper)},resize:function(){var b=e(this).data("resizable");b.ghost&&b.ghost.css({position:"relative",height:b.size.height,width:b.size.width})},stop:function(){var b=e(this).data("resizable");b.ghost&&b.helper&&b.helper.get(0).removeChild(b.ghost.get(0))}});e.ui.plugin.add("resizable","grid",{resize:function(){var b=
e(this).data("resizable"),a=b.options,c=b.size,d=b.originalSize,f=b.originalPosition,g=b.axis;a.grid=typeof a.grid=="number"?[a.grid,a.grid]:a.grid;var h=Math.round((c.width-d.width)/(a.grid[0]||1))*(a.grid[0]||1);a=Math.round((c.height-d.height)/(a.grid[1]||1))*(a.grid[1]||1);if(/^(se|s|e)$/.test(g)){b.size.width=d.width+h;b.size.height=d.height+a}else if(/^(ne)$/.test(g)){b.size.width=d.width+h;b.size.height=d.height+a;b.position.top=f.top-a}else{if(/^(sw)$/.test(g)){b.size.width=d.width+h;b.size.height=
d.height+a}else{b.size.width=d.width+h;b.size.height=d.height+a;b.position.top=f.top-a}b.position.left=f.left-h}}});var m=function(b){return parseInt(b,10)||0},k=function(b){return!isNaN(parseInt(b,10))}})(jQuery);
;/*
 * jQuery UI Slider 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Slider
 *
 * Depends:
 *  jquery.ui.core.js
 *  jquery.ui.mouse.js
 *  jquery.ui.widget.js
 */
(function(d){d.widget("ui.slider",d.ui.mouse,{widgetEventPrefix:"slide",options:{animate:false,distance:0,max:100,min:0,orientation:"horizontal",range:false,step:1,value:0,values:null},_create:function(){var a=this,b=this.options,c=this.element.find(".ui-slider-handle").addClass("ui-state-default ui-corner-all"),f=b.values&&b.values.length||1,e=[];this._mouseSliding=this._keySliding=false;this._animateOff=true;this._handleIndex=null;this._detectOrientation();this._mouseInit();this.element.addClass("ui-slider ui-slider-"+
this.orientation+" ui-widget ui-widget-content ui-corner-all"+(b.disabled?" ui-slider-disabled ui-disabled":""));this.range=d([]);if(b.range){if(b.range===true){if(!b.values)b.values=[this._valueMin(),this._valueMin()];if(b.values.length&&b.values.length!==2)b.values=[b.values[0],b.values[0]]}this.range=d("<div></div>").appendTo(this.element).addClass("ui-slider-range ui-widget-header"+(b.range==="min"||b.range==="max"?" ui-slider-range-"+b.range:""))}for(var j=c.length;j<f;j+=1)e.push("<a class='ui-slider-handle ui-state-default ui-corner-all' href='#'></a>");
this.handles=c.add(d(e.join("")).appendTo(a.element));this.handle=this.handles.eq(0);this.handles.add(this.range).filter("a").click(function(g){g.preventDefault()}).hover(function(){b.disabled||d(this).addClass("ui-state-hover")},function(){d(this).removeClass("ui-state-hover")}).focus(function(){if(b.disabled)d(this).blur();else{d(".ui-slider .ui-state-focus").removeClass("ui-state-focus");d(this).addClass("ui-state-focus")}}).blur(function(){d(this).removeClass("ui-state-focus")});this.handles.each(function(g){d(this).data("index.ui-slider-handle",
g)});this.handles.keydown(function(g){var k=true,l=d(this).data("index.ui-slider-handle"),i,h,m;if(!a.options.disabled){switch(g.keyCode){case d.ui.keyCode.HOME:case d.ui.keyCode.END:case d.ui.keyCode.PAGE_UP:case d.ui.keyCode.PAGE_DOWN:case d.ui.keyCode.UP:case d.ui.keyCode.RIGHT:case d.ui.keyCode.DOWN:case d.ui.keyCode.LEFT:k=false;if(!a._keySliding){a._keySliding=true;d(this).addClass("ui-state-active");i=a._start(g,l);if(i===false)return}break}m=a.options.step;i=a.options.values&&a.options.values.length?
(h=a.values(l)):(h=a.value());switch(g.keyCode){case d.ui.keyCode.HOME:h=a._valueMin();break;case d.ui.keyCode.END:h=a._valueMax();break;case d.ui.keyCode.PAGE_UP:h=a._trimAlignValue(i+(a._valueMax()-a._valueMin())/5);break;case d.ui.keyCode.PAGE_DOWN:h=a._trimAlignValue(i-(a._valueMax()-a._valueMin())/5);break;case d.ui.keyCode.UP:case d.ui.keyCode.RIGHT:if(i===a._valueMax())return;h=a._trimAlignValue(i+m);break;case d.ui.keyCode.DOWN:case d.ui.keyCode.LEFT:if(i===a._valueMin())return;h=a._trimAlignValue(i-
m);break}a._slide(g,l,h);return k}}).keyup(function(g){var k=d(this).data("index.ui-slider-handle");if(a._keySliding){a._keySliding=false;a._stop(g,k);a._change(g,k);d(this).removeClass("ui-state-active")}});this._refreshValue();this._animateOff=false},destroy:function(){this.handles.remove();this.range.remove();this.element.removeClass("ui-slider ui-slider-horizontal ui-slider-vertical ui-slider-disabled ui-widget ui-widget-content ui-corner-all").removeData("slider").unbind(".slider");this._mouseDestroy();
return this},_mouseCapture:function(a){var b=this.options,c,f,e,j,g;if(b.disabled)return false;this.elementSize={width:this.element.outerWidth(),height:this.element.outerHeight()};this.elementOffset=this.element.offset();c=this._normValueFromMouse({x:a.pageX,y:a.pageY});f=this._valueMax()-this._valueMin()+1;j=this;this.handles.each(function(k){var l=Math.abs(c-j.values(k));if(f>l){f=l;e=d(this);g=k}});if(b.range===true&&this.values(1)===b.min){g+=1;e=d(this.handles[g])}if(this._start(a,g)===false)return false;
this._mouseSliding=true;j._handleIndex=g;e.addClass("ui-state-active").focus();b=e.offset();this._clickOffset=!d(a.target).parents().andSelf().is(".ui-slider-handle")?{left:0,top:0}:{left:a.pageX-b.left-e.width()/2,top:a.pageY-b.top-e.height()/2-(parseInt(e.css("borderTopWidth"),10)||0)-(parseInt(e.css("borderBottomWidth"),10)||0)+(parseInt(e.css("marginTop"),10)||0)};this.handles.hasClass("ui-state-hover")||this._slide(a,g,c);return this._animateOff=true},_mouseStart:function(){return true},_mouseDrag:function(a){var b=
this._normValueFromMouse({x:a.pageX,y:a.pageY});this._slide(a,this._handleIndex,b);return false},_mouseStop:function(a){this.handles.removeClass("ui-state-active");this._mouseSliding=false;this._stop(a,this._handleIndex);this._change(a,this._handleIndex);this._clickOffset=this._handleIndex=null;return this._animateOff=false},_detectOrientation:function(){this.orientation=this.options.orientation==="vertical"?"vertical":"horizontal"},_normValueFromMouse:function(a){var b;if(this.orientation==="horizontal"){b=
this.elementSize.width;a=a.x-this.elementOffset.left-(this._clickOffset?this._clickOffset.left:0)}else{b=this.elementSize.height;a=a.y-this.elementOffset.top-(this._clickOffset?this._clickOffset.top:0)}b=a/b;if(b>1)b=1;if(b<0)b=0;if(this.orientation==="vertical")b=1-b;a=this._valueMax()-this._valueMin();return this._trimAlignValue(this._valueMin()+b*a)},_start:function(a,b){var c={handle:this.handles[b],value:this.value()};if(this.options.values&&this.options.values.length){c.value=this.values(b);
c.values=this.values()}return this._trigger("start",a,c)},_slide:function(a,b,c){var f;if(this.options.values&&this.options.values.length){f=this.values(b?0:1);if(this.options.values.length===2&&this.options.range===true&&(b===0&&c>f||b===1&&c<f))c=f;if(c!==this.values(b)){f=this.values();f[b]=c;a=this._trigger("slide",a,{handle:this.handles[b],value:c,values:f});this.values(b?0:1);a!==false&&this.values(b,c,true)}}else if(c!==this.value()){a=this._trigger("slide",a,{handle:this.handles[b],value:c});
a!==false&&this.value(c)}},_stop:function(a,b){var c={handle:this.handles[b],value:this.value()};if(this.options.values&&this.options.values.length){c.value=this.values(b);c.values=this.values()}this._trigger("stop",a,c)},_change:function(a,b){if(!this._keySliding&&!this._mouseSliding){var c={handle:this.handles[b],value:this.value()};if(this.options.values&&this.options.values.length){c.value=this.values(b);c.values=this.values()}this._trigger("change",a,c)}},value:function(a){if(arguments.length){this.options.value=
this._trimAlignValue(a);this._refreshValue();this._change(null,0)}else return this._value()},values:function(a,b){var c,f,e;if(arguments.length>1){this.options.values[a]=this._trimAlignValue(b);this._refreshValue();this._change(null,a)}else if(arguments.length)if(d.isArray(arguments[0])){c=this.options.values;f=arguments[0];for(e=0;e<c.length;e+=1){c[e]=this._trimAlignValue(f[e]);this._change(null,e)}this._refreshValue()}else return this.options.values&&this.options.values.length?this._values(a):
this.value();else return this._values()},_setOption:function(a,b){var c,f=0;if(d.isArray(this.options.values))f=this.options.values.length;d.Widget.prototype._setOption.apply(this,arguments);switch(a){case "disabled":if(b){this.handles.filter(".ui-state-focus").blur();this.handles.removeClass("ui-state-hover");this.handles.propAttr("disabled",true);this.element.addClass("ui-disabled")}else{this.handles.propAttr("disabled",false);this.element.removeClass("ui-disabled")}break;case "orientation":this._detectOrientation();
this.element.removeClass("ui-slider-horizontal ui-slider-vertical").addClass("ui-slider-"+this.orientation);this._refreshValue();break;case "value":this._animateOff=true;this._refreshValue();this._change(null,0);this._animateOff=false;break;case "values":this._animateOff=true;this._refreshValue();for(c=0;c<f;c+=1)this._change(null,c);this._animateOff=false;break}},_value:function(){var a=this.options.value;return a=this._trimAlignValue(a)},_values:function(a){var b,c;if(arguments.length){b=this.options.values[a];
return b=this._trimAlignValue(b)}else{b=this.options.values.slice();for(c=0;c<b.length;c+=1)b[c]=this._trimAlignValue(b[c]);return b}},_trimAlignValue:function(a){if(a<=this._valueMin())return this._valueMin();if(a>=this._valueMax())return this._valueMax();var b=this.options.step>0?this.options.step:1,c=(a-this._valueMin())%b;a=a-c;if(Math.abs(c)*2>=b)a+=c>0?b:-b;return parseFloat(a.toFixed(5))},_valueMin:function(){return this.options.min},_valueMax:function(){return this.options.max},_refreshValue:function(){var a=
this.options.range,b=this.options,c=this,f=!this._animateOff?b.animate:false,e,j={},g,k,l,i;if(this.options.values&&this.options.values.length)this.handles.each(function(h){e=(c.values(h)-c._valueMin())/(c._valueMax()-c._valueMin())*100;j[c.orientation==="horizontal"?"left":"bottom"]=e+"%";d(this).stop(1,1)[f?"animate":"css"](j,b.animate);if(c.options.range===true)if(c.orientation==="horizontal"){if(h===0)c.range.stop(1,1)[f?"animate":"css"]({left:e+"%"},b.animate);if(h===1)c.range[f?"animate":"css"]({width:e-
g+"%"},{queue:false,duration:b.animate})}else{if(h===0)c.range.stop(1,1)[f?"animate":"css"]({bottom:e+"%"},b.animate);if(h===1)c.range[f?"animate":"css"]({height:e-g+"%"},{queue:false,duration:b.animate})}g=e});else{k=this.value();l=this._valueMin();i=this._valueMax();e=i!==l?(k-l)/(i-l)*100:0;j[c.orientation==="horizontal"?"left":"bottom"]=e+"%";this.handle.stop(1,1)[f?"animate":"css"](j,b.animate);if(a==="min"&&this.orientation==="horizontal")this.range.stop(1,1)[f?"animate":"css"]({width:e+"%"},
b.animate);if(a==="max"&&this.orientation==="horizontal")this.range[f?"animate":"css"]({width:100-e+"%"},{queue:false,duration:b.animate});if(a==="min"&&this.orientation==="vertical")this.range.stop(1,1)[f?"animate":"css"]({height:e+"%"},b.animate);if(a==="max"&&this.orientation==="vertical")this.range[f?"animate":"css"]({height:100-e+"%"},{queue:false,duration:b.animate})}}});d.extend(d.ui.slider,{version:"1.8.16"})})(jQuery);
;/*
 * jQuery UI Tabs 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Tabs
 *
 * Depends:
 *  jquery.ui.core.js
 *  jquery.ui.widget.js
 */
(function(d,p){function u(){return++v}function w(){return++x}var v=0,x=0;d.widget("ui.tabs",{options:{add:null,ajaxOptions:null,cache:false,cookie:null,collapsible:false,disable:null,disabled:[],enable:null,event:"click",fx:null,idPrefix:"ui-tabs-",load:null,panelTemplate:"<div></div>",remove:null,select:null,show:null,spinner:"<em>Loading&#8230;</em>",tabTemplate:"<li><a href='#{href}'><span>#{label}</span></a></li>"},_create:function(){this._tabify(true)},_setOption:function(b,e){if(b=="selected")this.options.collapsible&&
e==this.options.selected||this.select(e);else{this.options[b]=e;this._tabify()}},_tabId:function(b){return b.title&&b.title.replace(/\s/g,"_").replace(/[^\w\u00c0-\uFFFF-]/g,"")||this.options.idPrefix+u()},_sanitizeSelector:function(b){return b.replace(/:/g,"\\:")},_cookie:function(){var b=this.cookie||(this.cookie=this.options.cookie.name||"ui-tabs-"+w());return d.cookie.apply(null,[b].concat(d.makeArray(arguments)))},_ui:function(b,e){return{tab:b,panel:e,index:this.anchors.index(b)}},_cleanup:function(){this.lis.filter(".ui-state-processing").removeClass("ui-state-processing").find("span:data(label.tabs)").each(function(){var b=
d(this);b.html(b.data("label.tabs")).removeData("label.tabs")})},_tabify:function(b){function e(g,f){g.css("display","");!d.support.opacity&&f.opacity&&g[0].style.removeAttribute("filter")}var a=this,c=this.options,h=/^#.+/;this.list=this.element.find("ol,ul").eq(0);this.lis=d(" > li:has(a[href])",this.list);this.anchors=this.lis.map(function(){return d("a",this)[0]});this.panels=d([]);this.anchors.each(function(g,f){var i=d(f).attr("href"),l=i.split("#")[0],q;if(l&&(l===location.toString().split("#")[0]||
(q=d("base")[0])&&l===q.href)){i=f.hash;f.href=i}if(h.test(i))a.panels=a.panels.add(a.element.find(a._sanitizeSelector(i)));else if(i&&i!=="#"){d.data(f,"href.tabs",i);d.data(f,"load.tabs",i.replace(/#.*$/,""));i=a._tabId(f);f.href="#"+i;f=a.element.find("#"+i);if(!f.length){f=d(c.panelTemplate).attr("id",i).addClass("ui-tabs-panel ui-widget-content ui-corner-bottom").insertAfter(a.panels[g-1]||a.list);f.data("destroy.tabs",true)}a.panels=a.panels.add(f)}else c.disabled.push(g)});if(b){this.element.addClass("ui-tabs ui-widget ui-widget-content ui-corner-all");
this.list.addClass("ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all");this.lis.addClass("ui-state-default ui-corner-top");this.panels.addClass("ui-tabs-panel ui-widget-content ui-corner-bottom");if(c.selected===p){location.hash&&this.anchors.each(function(g,f){if(f.hash==location.hash){c.selected=g;return false}});if(typeof c.selected!=="number"&&c.cookie)c.selected=parseInt(a._cookie(),10);if(typeof c.selected!=="number"&&this.lis.filter(".ui-tabs-selected").length)c.selected=
this.lis.index(this.lis.filter(".ui-tabs-selected"));c.selected=c.selected||(this.lis.length?0:-1)}else if(c.selected===null)c.selected=-1;c.selected=c.selected>=0&&this.anchors[c.selected]||c.selected<0?c.selected:0;c.disabled=d.unique(c.disabled.concat(d.map(this.lis.filter(".ui-state-disabled"),function(g){return a.lis.index(g)}))).sort();d.inArray(c.selected,c.disabled)!=-1&&c.disabled.splice(d.inArray(c.selected,c.disabled),1);this.panels.addClass("ui-tabs-hide");this.lis.removeClass("ui-tabs-selected ui-state-active");
if(c.selected>=0&&this.anchors.length){a.element.find(a._sanitizeSelector(a.anchors[c.selected].hash)).removeClass("ui-tabs-hide");this.lis.eq(c.selected).addClass("ui-tabs-selected ui-state-active");a.element.queue("tabs",function(){a._trigger("show",null,a._ui(a.anchors[c.selected],a.element.find(a._sanitizeSelector(a.anchors[c.selected].hash))[0]))});this.load(c.selected)}d(window).bind("unload",function(){a.lis.add(a.anchors).unbind(".tabs");a.lis=a.anchors=a.panels=null})}else c.selected=this.lis.index(this.lis.filter(".ui-tabs-selected"));
this.element[c.collapsible?"addClass":"removeClass"]("ui-tabs-collapsible");c.cookie&&this._cookie(c.selected,c.cookie);b=0;for(var j;j=this.lis[b];b++)d(j)[d.inArray(b,c.disabled)!=-1&&!d(j).hasClass("ui-tabs-selected")?"addClass":"removeClass"]("ui-state-disabled");c.cache===false&&this.anchors.removeData("cache.tabs");this.lis.add(this.anchors).unbind(".tabs");if(c.event!=="mouseover"){var k=function(g,f){f.is(":not(.ui-state-disabled)")&&f.addClass("ui-state-"+g)},n=function(g,f){f.removeClass("ui-state-"+
g)};this.lis.bind("mouseover.tabs",function(){k("hover",d(this))});this.lis.bind("mouseout.tabs",function(){n("hover",d(this))});this.anchors.bind("focus.tabs",function(){k("focus",d(this).closest("li"))});this.anchors.bind("blur.tabs",function(){n("focus",d(this).closest("li"))})}var m,o;if(c.fx)if(d.isArray(c.fx)){m=c.fx[0];o=c.fx[1]}else m=o=c.fx;var r=o?function(g,f){d(g).closest("li").addClass("ui-tabs-selected ui-state-active");f.hide().removeClass("ui-tabs-hide").animate(o,o.duration||"normal",
function(){e(f,o);a._trigger("show",null,a._ui(g,f[0]))})}:function(g,f){d(g).closest("li").addClass("ui-tabs-selected ui-state-active");f.removeClass("ui-tabs-hide");a._trigger("show",null,a._ui(g,f[0]))},s=m?function(g,f){f.animate(m,m.duration||"normal",function(){a.lis.removeClass("ui-tabs-selected ui-state-active");f.addClass("ui-tabs-hide");e(f,m);a.element.dequeue("tabs")})}:function(g,f){a.lis.removeClass("ui-tabs-selected ui-state-active");f.addClass("ui-tabs-hide");a.element.dequeue("tabs")};
this.anchors.bind(c.event+".tabs",function(){var g=this,f=d(g).closest("li"),i=a.panels.filter(":not(.ui-tabs-hide)"),l=a.element.find(a._sanitizeSelector(g.hash));if(f.hasClass("ui-tabs-selected")&&!c.collapsible||f.hasClass("ui-state-disabled")||f.hasClass("ui-state-processing")||a.panels.filter(":animated").length||a._trigger("select",null,a._ui(this,l[0]))===false){this.blur();return false}c.selected=a.anchors.index(this);a.abort();if(c.collapsible)if(f.hasClass("ui-tabs-selected")){c.selected=
-1;c.cookie&&a._cookie(c.selected,c.cookie);a.element.queue("tabs",function(){s(g,i)}).dequeue("tabs");this.blur();return false}else if(!i.length){c.cookie&&a._cookie(c.selected,c.cookie);a.element.queue("tabs",function(){r(g,l)});a.load(a.anchors.index(this));this.blur();return false}c.cookie&&a._cookie(c.selected,c.cookie);if(l.length){i.length&&a.element.queue("tabs",function(){s(g,i)});a.element.queue("tabs",function(){r(g,l)});a.load(a.anchors.index(this))}else throw"jQuery UI Tabs: Mismatching fragment identifier.";
d.browser.msie&&this.blur()});this.anchors.bind("click.tabs",function(){return false})},_getIndex:function(b){if(typeof b=="string")b=this.anchors.index(this.anchors.filter("[href$="+b+"]"));return b},destroy:function(){var b=this.options;this.abort();this.element.unbind(".tabs").removeClass("ui-tabs ui-widget ui-widget-content ui-corner-all ui-tabs-collapsible").removeData("tabs");this.list.removeClass("ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all");this.anchors.each(function(){var e=
d.data(this,"href.tabs");if(e)this.href=e;var a=d(this).unbind(".tabs");d.each(["href","load","cache"],function(c,h){a.removeData(h+".tabs")})});this.lis.unbind(".tabs").add(this.panels).each(function(){d.data(this,"destroy.tabs")?d(this).remove():d(this).removeClass("ui-state-default ui-corner-top ui-tabs-selected ui-state-active ui-state-hover ui-state-focus ui-state-disabled ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide")});b.cookie&&this._cookie(null,b.cookie);return this},add:function(b,
e,a){if(a===p)a=this.anchors.length;var c=this,h=this.options;e=d(h.tabTemplate.replace(/#\{href\}/g,b).replace(/#\{label\}/g,e));b=!b.indexOf("#")?b.replace("#",""):this._tabId(d("a",e)[0]);e.addClass("ui-state-default ui-corner-top").data("destroy.tabs",true);var j=c.element.find("#"+b);j.length||(j=d(h.panelTemplate).attr("id",b).data("destroy.tabs",true));j.addClass("ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide");if(a>=this.lis.length){e.appendTo(this.list);j.appendTo(this.list[0].parentNode)}else{e.insertBefore(this.lis[a]);
j.insertBefore(this.panels[a])}h.disabled=d.map(h.disabled,function(k){return k>=a?++k:k});this._tabify();if(this.anchors.length==1){h.selected=0;e.addClass("ui-tabs-selected ui-state-active");j.removeClass("ui-tabs-hide");this.element.queue("tabs",function(){c._trigger("show",null,c._ui(c.anchors[0],c.panels[0]))});this.load(0)}this._trigger("add",null,this._ui(this.anchors[a],this.panels[a]));return this},remove:function(b){b=this._getIndex(b);var e=this.options,a=this.lis.eq(b).remove(),c=this.panels.eq(b).remove();
if(a.hasClass("ui-tabs-selected")&&this.anchors.length>1)this.select(b+(b+1<this.anchors.length?1:-1));e.disabled=d.map(d.grep(e.disabled,function(h){return h!=b}),function(h){return h>=b?--h:h});this._tabify();this._trigger("remove",null,this._ui(a.find("a")[0],c[0]));return this},enable:function(b){b=this._getIndex(b);var e=this.options;if(d.inArray(b,e.disabled)!=-1){this.lis.eq(b).removeClass("ui-state-disabled");e.disabled=d.grep(e.disabled,function(a){return a!=b});this._trigger("enable",null,
this._ui(this.anchors[b],this.panels[b]));return this}},disable:function(b){b=this._getIndex(b);var e=this.options;if(b!=e.selected){this.lis.eq(b).addClass("ui-state-disabled");e.disabled.push(b);e.disabled.sort();this._trigger("disable",null,this._ui(this.anchors[b],this.panels[b]))}return this},select:function(b){b=this._getIndex(b);if(b==-1)if(this.options.collapsible&&this.options.selected!=-1)b=this.options.selected;else return this;this.anchors.eq(b).trigger(this.options.event+".tabs");return this},
load:function(b){b=this._getIndex(b);var e=this,a=this.options,c=this.anchors.eq(b)[0],h=d.data(c,"load.tabs");this.abort();if(!h||this.element.queue("tabs").length!==0&&d.data(c,"cache.tabs"))this.element.dequeue("tabs");else{this.lis.eq(b).addClass("ui-state-processing");if(a.spinner){var j=d("span",c);j.data("label.tabs",j.html()).html(a.spinner)}this.xhr=d.ajax(d.extend({},a.ajaxOptions,{url:h,success:function(k,n){e.element.find(e._sanitizeSelector(c.hash)).html(k);e._cleanup();a.cache&&d.data(c,
"cache.tabs",true);e._trigger("load",null,e._ui(e.anchors[b],e.panels[b]));try{a.ajaxOptions.success(k,n)}catch(m){}},error:function(k,n){e._cleanup();e._trigger("load",null,e._ui(e.anchors[b],e.panels[b]));try{a.ajaxOptions.error(k,n,b,c)}catch(m){}}}));e.element.dequeue("tabs");return this}},abort:function(){this.element.queue([]);this.panels.stop(false,true);this.element.queue("tabs",this.element.queue("tabs").splice(-2,2));if(this.xhr){this.xhr.abort();delete this.xhr}this._cleanup();return this},
url:function(b,e){this.anchors.eq(b).removeData("cache.tabs").data("load.tabs",e);return this},length:function(){return this.anchors.length}});d.extend(d.ui.tabs,{version:"1.8.16"});d.extend(d.ui.tabs.prototype,{rotation:null,rotate:function(b,e){var a=this,c=this.options,h=a._rotate||(a._rotate=function(j){clearTimeout(a.rotation);a.rotation=setTimeout(function(){var k=c.selected;a.select(++k<a.anchors.length?k:0)},b);j&&j.stopPropagation()});e=a._unrotate||(a._unrotate=!e?function(j){j.clientX&&
a.rotate(null)}:function(){t=c.selected;h()});if(b){this.element.bind("tabsshow",h);this.anchors.bind(c.event+".tabs",e);h()}else{clearTimeout(a.rotation);this.element.unbind("tabsshow",h);this.anchors.unbind(c.event+".tabs",e);delete this._rotate;delete this._unrotate}return this}})})(jQuery);
;/*
 * jQuery UI Progressbar 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Progressbar
 *
 * Depends:
 *   jquery.ui.core.js
 *   jquery.ui.widget.js
 */
(function(b,d){b.widget("ui.progressbar",{options:{value:0,max:100},min:0,_create:function(){this.element.addClass("ui-progressbar ui-widget ui-widget-content ui-corner-all").attr({role:"progressbar","aria-valuemin":this.min,"aria-valuemax":this.options.max,"aria-valuenow":this._value()});this.valueDiv=b("<div class='ui-progressbar-value ui-widget-header ui-corner-left'></div>").appendTo(this.element);this.oldValue=this._value();this._refreshValue()},destroy:function(){this.element.removeClass("ui-progressbar ui-widget ui-widget-content ui-corner-all").removeAttr("role").removeAttr("aria-valuemin").removeAttr("aria-valuemax").removeAttr("aria-valuenow");
this.valueDiv.remove();b.Widget.prototype.destroy.apply(this,arguments)},value:function(a){if(a===d)return this._value();this._setOption("value",a);return this},_setOption:function(a,c){if(a==="value"){this.options.value=c;this._refreshValue();this._value()===this.options.max&&this._trigger("complete")}b.Widget.prototype._setOption.apply(this,arguments)},_value:function(){var a=this.options.value;if(typeof a!=="number")a=0;return Math.min(this.options.max,Math.max(this.min,a))},_percentage:function(){return 100*
this._value()/this.options.max},_refreshValue:function(){var a=this.value(),c=this._percentage();if(this.oldValue!==a){this.oldValue=a;this._trigger("change")}this.valueDiv.toggle(a>this.min).toggleClass("ui-corner-right",a===this.options.max).width(c.toFixed(0)+"%");this.element.attr("aria-valuenow",a)}});b.extend(b.ui.progressbar,{version:"1.8.16"})})(jQuery);
;/*
 * jQuery UI Effects 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/
 */
jQuery.effects||function(f,j){function m(c){var a;if(c&&c.constructor==Array&&c.length==3)return c;if(a=/rgb\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*\)/.exec(c))return[parseInt(a[1],10),parseInt(a[2],10),parseInt(a[3],10)];if(a=/rgb\(\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*\)/.exec(c))return[parseFloat(a[1])*2.55,parseFloat(a[2])*2.55,parseFloat(a[3])*2.55];if(a=/#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})/.exec(c))return[parseInt(a[1],
16),parseInt(a[2],16),parseInt(a[3],16)];if(a=/#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])/.exec(c))return[parseInt(a[1]+a[1],16),parseInt(a[2]+a[2],16),parseInt(a[3]+a[3],16)];if(/rgba\(0, 0, 0, 0\)/.exec(c))return n.transparent;return n[f.trim(c).toLowerCase()]}function s(c,a){var b;do{b=f.curCSS(c,a);if(b!=""&&b!="transparent"||f.nodeName(c,"body"))break;a="backgroundColor"}while(c=c.parentNode);return m(b)}function o(){var c=document.defaultView?document.defaultView.getComputedStyle(this,null):this.currentStyle,
a={},b,d;if(c&&c.length&&c[0]&&c[c[0]])for(var e=c.length;e--;){b=c[e];if(typeof c[b]=="string"){d=b.replace(/\-(\w)/g,function(g,h){return h.toUpperCase()});a[d]=c[b]}}else for(b in c)if(typeof c[b]==="string")a[b]=c[b];return a}function p(c){var a,b;for(a in c){b=c[a];if(b==null||f.isFunction(b)||a in t||/scrollbar/.test(a)||!/color/i.test(a)&&isNaN(parseFloat(b)))delete c[a]}return c}function u(c,a){var b={_:0},d;for(d in a)if(c[d]!=a[d])b[d]=a[d];return b}function k(c,a,b,d){if(typeof c=="object"){d=
a;b=null;a=c;c=a.effect}if(f.isFunction(a)){d=a;b=null;a={}}if(typeof a=="number"||f.fx.speeds[a]){d=b;b=a;a={}}if(f.isFunction(b)){d=b;b=null}a=a||{};b=b||a.duration;b=f.fx.off?0:typeof b=="number"?b:b in f.fx.speeds?f.fx.speeds[b]:f.fx.speeds._default;d=d||a.complete;return[c,a,b,d]}function l(c){if(!c||typeof c==="number"||f.fx.speeds[c])return true;if(typeof c==="string"&&!f.effects[c])return true;return false}f.effects={};f.each(["backgroundColor","borderBottomColor","borderLeftColor","borderRightColor",
"borderTopColor","borderColor","color","outlineColor"],function(c,a){f.fx.step[a]=function(b){if(!b.colorInit){b.start=s(b.elem,a);b.end=m(b.end);b.colorInit=true}b.elem.style[a]="rgb("+Math.max(Math.min(parseInt(b.pos*(b.end[0]-b.start[0])+b.start[0],10),255),0)+","+Math.max(Math.min(parseInt(b.pos*(b.end[1]-b.start[1])+b.start[1],10),255),0)+","+Math.max(Math.min(parseInt(b.pos*(b.end[2]-b.start[2])+b.start[2],10),255),0)+")"}});var n={aqua:[0,255,255],azure:[240,255,255],beige:[245,245,220],black:[0,
0,0],blue:[0,0,255],brown:[165,42,42],cyan:[0,255,255],darkblue:[0,0,139],darkcyan:[0,139,139],darkgrey:[169,169,169],darkgreen:[0,100,0],darkkhaki:[189,183,107],darkmagenta:[139,0,139],darkolivegreen:[85,107,47],darkorange:[255,140,0],darkorchid:[153,50,204],darkred:[139,0,0],darksalmon:[233,150,122],darkviolet:[148,0,211],fuchsia:[255,0,255],gold:[255,215,0],green:[0,128,0],indigo:[75,0,130],khaki:[240,230,140],lightblue:[173,216,230],lightcyan:[224,255,255],lightgreen:[144,238,144],lightgrey:[211,
211,211],lightpink:[255,182,193],lightyellow:[255,255,224],lime:[0,255,0],magenta:[255,0,255],maroon:[128,0,0],navy:[0,0,128],olive:[128,128,0],orange:[255,165,0],pink:[255,192,203],purple:[128,0,128],violet:[128,0,128],red:[255,0,0],silver:[192,192,192],white:[255,255,255],yellow:[255,255,0],transparent:[255,255,255]},q=["add","remove","toggle"],t={border:1,borderBottom:1,borderColor:1,borderLeft:1,borderRight:1,borderTop:1,borderWidth:1,margin:1,padding:1};f.effects.animateClass=function(c,a,b,
d){if(f.isFunction(b)){d=b;b=null}return this.queue(function(){var e=f(this),g=e.attr("style")||" ",h=p(o.call(this)),r,v=e.attr("class");f.each(q,function(w,i){c[i]&&e[i+"Class"](c[i])});r=p(o.call(this));e.attr("class",v);e.animate(u(h,r),{queue:false,duration:a,easing:b,complete:function(){f.each(q,function(w,i){c[i]&&e[i+"Class"](c[i])});if(typeof e.attr("style")=="object"){e.attr("style").cssText="";e.attr("style").cssText=g}else e.attr("style",g);d&&d.apply(this,arguments);f.dequeue(this)}})})};
f.fn.extend({_addClass:f.fn.addClass,addClass:function(c,a,b,d){return a?f.effects.animateClass.apply(this,[{add:c},a,b,d]):this._addClass(c)},_removeClass:f.fn.removeClass,removeClass:function(c,a,b,d){return a?f.effects.animateClass.apply(this,[{remove:c},a,b,d]):this._removeClass(c)},_toggleClass:f.fn.toggleClass,toggleClass:function(c,a,b,d,e){return typeof a=="boolean"||a===j?b?f.effects.animateClass.apply(this,[a?{add:c}:{remove:c},b,d,e]):this._toggleClass(c,a):f.effects.animateClass.apply(this,
[{toggle:c},a,b,d])},switchClass:function(c,a,b,d,e){return f.effects.animateClass.apply(this,[{add:a,remove:c},b,d,e])}});f.extend(f.effects,{version:"1.8.16",save:function(c,a){for(var b=0;b<a.length;b++)a[b]!==null&&c.data("ec.storage."+a[b],c[0].style[a[b]])},restore:function(c,a){for(var b=0;b<a.length;b++)a[b]!==null&&c.css(a[b],c.data("ec.storage."+a[b]))},setMode:function(c,a){if(a=="toggle")a=c.is(":hidden")?"show":"hide";return a},getBaseline:function(c,a){var b;switch(c[0]){case "top":b=
0;break;case "middle":b=0.5;break;case "bottom":b=1;break;default:b=c[0]/a.height}switch(c[1]){case "left":c=0;break;case "center":c=0.5;break;case "right":c=1;break;default:c=c[1]/a.width}return{x:c,y:b}},createWrapper:function(c){if(c.parent().is(".ui-effects-wrapper"))return c.parent();var a={width:c.outerWidth(true),height:c.outerHeight(true),"float":c.css("float")},b=f("<div></div>").addClass("ui-effects-wrapper").css({fontSize:"100%",background:"transparent",border:"none",margin:0,padding:0}),
d=document.activeElement;c.wrap(b);if(c[0]===d||f.contains(c[0],d))f(d).focus();b=c.parent();if(c.css("position")=="static"){b.css({position:"relative"});c.css({position:"relative"})}else{f.extend(a,{position:c.css("position"),zIndex:c.css("z-index")});f.each(["top","left","bottom","right"],function(e,g){a[g]=c.css(g);if(isNaN(parseInt(a[g],10)))a[g]="auto"});c.css({position:"relative",top:0,left:0,right:"auto",bottom:"auto"})}return b.css(a).show()},removeWrapper:function(c){var a,b=document.activeElement;
if(c.parent().is(".ui-effects-wrapper")){a=c.parent().replaceWith(c);if(c[0]===b||f.contains(c[0],b))f(b).focus();return a}return c},setTransition:function(c,a,b,d){d=d||{};f.each(a,function(e,g){unit=c.cssUnit(g);if(unit[0]>0)d[g]=unit[0]*b+unit[1]});return d}});f.fn.extend({effect:function(c){var a=k.apply(this,arguments),b={options:a[1],duration:a[2],callback:a[3]};a=b.options.mode;var d=f.effects[c];if(f.fx.off||!d)return a?this[a](b.duration,b.callback):this.each(function(){b.callback&&b.callback.call(this)});
return d.call(this,b)},_show:f.fn.show,show:function(c){if(l(c))return this._show.apply(this,arguments);else{var a=k.apply(this,arguments);a[1].mode="show";return this.effect.apply(this,a)}},_hide:f.fn.hide,hide:function(c){if(l(c))return this._hide.apply(this,arguments);else{var a=k.apply(this,arguments);a[1].mode="hide";return this.effect.apply(this,a)}},__toggle:f.fn.toggle,toggle:function(c){if(l(c)||typeof c==="boolean"||f.isFunction(c))return this.__toggle.apply(this,arguments);else{var a=k.apply(this,
arguments);a[1].mode="toggle";return this.effect.apply(this,a)}},cssUnit:function(c){var a=this.css(c),b=[];f.each(["em","px","%","pt"],function(d,e){if(a.indexOf(e)>0)b=[parseFloat(a),e]});return b}});f.easing.jswing=f.easing.swing;f.extend(f.easing,{def:"easeOutQuad",swing:function(c,a,b,d,e){return f.easing[f.easing.def](c,a,b,d,e)},easeInQuad:function(c,a,b,d,e){return d*(a/=e)*a+b},easeOutQuad:function(c,a,b,d,e){return-d*(a/=e)*(a-2)+b},easeInOutQuad:function(c,a,b,d,e){if((a/=e/2)<1)return d/
2*a*a+b;return-d/2*(--a*(a-2)-1)+b},easeInCubic:function(c,a,b,d,e){return d*(a/=e)*a*a+b},easeOutCubic:function(c,a,b,d,e){return d*((a=a/e-1)*a*a+1)+b},easeInOutCubic:function(c,a,b,d,e){if((a/=e/2)<1)return d/2*a*a*a+b;return d/2*((a-=2)*a*a+2)+b},easeInQuart:function(c,a,b,d,e){return d*(a/=e)*a*a*a+b},easeOutQuart:function(c,a,b,d,e){return-d*((a=a/e-1)*a*a*a-1)+b},easeInOutQuart:function(c,a,b,d,e){if((a/=e/2)<1)return d/2*a*a*a*a+b;return-d/2*((a-=2)*a*a*a-2)+b},easeInQuint:function(c,a,b,
d,e){return d*(a/=e)*a*a*a*a+b},easeOutQuint:function(c,a,b,d,e){return d*((a=a/e-1)*a*a*a*a+1)+b},easeInOutQuint:function(c,a,b,d,e){if((a/=e/2)<1)return d/2*a*a*a*a*a+b;return d/2*((a-=2)*a*a*a*a+2)+b},easeInSine:function(c,a,b,d,e){return-d*Math.cos(a/e*(Math.PI/2))+d+b},easeOutSine:function(c,a,b,d,e){return d*Math.sin(a/e*(Math.PI/2))+b},easeInOutSine:function(c,a,b,d,e){return-d/2*(Math.cos(Math.PI*a/e)-1)+b},easeInExpo:function(c,a,b,d,e){return a==0?b:d*Math.pow(2,10*(a/e-1))+b},easeOutExpo:function(c,
a,b,d,e){return a==e?b+d:d*(-Math.pow(2,-10*a/e)+1)+b},easeInOutExpo:function(c,a,b,d,e){if(a==0)return b;if(a==e)return b+d;if((a/=e/2)<1)return d/2*Math.pow(2,10*(a-1))+b;return d/2*(-Math.pow(2,-10*--a)+2)+b},easeInCirc:function(c,a,b,d,e){return-d*(Math.sqrt(1-(a/=e)*a)-1)+b},easeOutCirc:function(c,a,b,d,e){return d*Math.sqrt(1-(a=a/e-1)*a)+b},easeInOutCirc:function(c,a,b,d,e){if((a/=e/2)<1)return-d/2*(Math.sqrt(1-a*a)-1)+b;return d/2*(Math.sqrt(1-(a-=2)*a)+1)+b},easeInElastic:function(c,a,b,
d,e){c=1.70158;var g=0,h=d;if(a==0)return b;if((a/=e)==1)return b+d;g||(g=e*0.3);if(h<Math.abs(d)){h=d;c=g/4}else c=g/(2*Math.PI)*Math.asin(d/h);return-(h*Math.pow(2,10*(a-=1))*Math.sin((a*e-c)*2*Math.PI/g))+b},easeOutElastic:function(c,a,b,d,e){c=1.70158;var g=0,h=d;if(a==0)return b;if((a/=e)==1)return b+d;g||(g=e*0.3);if(h<Math.abs(d)){h=d;c=g/4}else c=g/(2*Math.PI)*Math.asin(d/h);return h*Math.pow(2,-10*a)*Math.sin((a*e-c)*2*Math.PI/g)+d+b},easeInOutElastic:function(c,a,b,d,e){c=1.70158;var g=
0,h=d;if(a==0)return b;if((a/=e/2)==2)return b+d;g||(g=e*0.3*1.5);if(h<Math.abs(d)){h=d;c=g/4}else c=g/(2*Math.PI)*Math.asin(d/h);if(a<1)return-0.5*h*Math.pow(2,10*(a-=1))*Math.sin((a*e-c)*2*Math.PI/g)+b;return h*Math.pow(2,-10*(a-=1))*Math.sin((a*e-c)*2*Math.PI/g)*0.5+d+b},easeInBack:function(c,a,b,d,e,g){if(g==j)g=1.70158;return d*(a/=e)*a*((g+1)*a-g)+b},easeOutBack:function(c,a,b,d,e,g){if(g==j)g=1.70158;return d*((a=a/e-1)*a*((g+1)*a+g)+1)+b},easeInOutBack:function(c,a,b,d,e,g){if(g==j)g=1.70158;
if((a/=e/2)<1)return d/2*a*a*(((g*=1.525)+1)*a-g)+b;return d/2*((a-=2)*a*(((g*=1.525)+1)*a+g)+2)+b},easeInBounce:function(c,a,b,d,e){return d-f.easing.easeOutBounce(c,e-a,0,d,e)+b},easeOutBounce:function(c,a,b,d,e){return(a/=e)<1/2.75?d*7.5625*a*a+b:a<2/2.75?d*(7.5625*(a-=1.5/2.75)*a+0.75)+b:a<2.5/2.75?d*(7.5625*(a-=2.25/2.75)*a+0.9375)+b:d*(7.5625*(a-=2.625/2.75)*a+0.984375)+b},easeInOutBounce:function(c,a,b,d,e){if(a<e/2)return f.easing.easeInBounce(c,a*2,0,d,e)*0.5+b;return f.easing.easeOutBounce(c,
a*2-e,0,d,e)*0.5+d*0.5+b}})}(jQuery);
;/*
 * jQuery UI Effects Blind 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/Blind
 *
 * Depends:
 *  jquery.effects.core.js
 */
(function(b){b.effects.blind=function(c){return this.queue(function(){var a=b(this),g=["position","top","bottom","left","right"],f=b.effects.setMode(a,c.options.mode||"hide"),d=c.options.direction||"vertical";b.effects.save(a,g);a.show();var e=b.effects.createWrapper(a).css({overflow:"hidden"}),h=d=="vertical"?"height":"width";d=d=="vertical"?e.height():e.width();f=="show"&&e.css(h,0);var i={};i[h]=f=="show"?d:0;e.animate(i,c.duration,c.options.easing,function(){f=="hide"&&a.hide();b.effects.restore(a,
g);b.effects.removeWrapper(a);c.callback&&c.callback.apply(a[0],arguments);a.dequeue()})})}})(jQuery);
;/*
 * jQuery UI Effects Bounce 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/Bounce
 *
 * Depends:
 *  jquery.effects.core.js
 */
(function(e){e.effects.bounce=function(b){return this.queue(function(){var a=e(this),l=["position","top","bottom","left","right"],h=e.effects.setMode(a,b.options.mode||"effect"),d=b.options.direction||"up",c=b.options.distance||20,m=b.options.times||5,i=b.duration||250;/show|hide/.test(h)&&l.push("opacity");e.effects.save(a,l);a.show();e.effects.createWrapper(a);var f=d=="up"||d=="down"?"top":"left";d=d=="up"||d=="left"?"pos":"neg";c=b.options.distance||(f=="top"?a.outerHeight({margin:true})/3:a.outerWidth({margin:true})/
3);if(h=="show")a.css("opacity",0).css(f,d=="pos"?-c:c);if(h=="hide")c/=m*2;h!="hide"&&m--;if(h=="show"){var g={opacity:1};g[f]=(d=="pos"?"+=":"-=")+c;a.animate(g,i/2,b.options.easing);c/=2;m--}for(g=0;g<m;g++){var j={},k={};j[f]=(d=="pos"?"-=":"+=")+c;k[f]=(d=="pos"?"+=":"-=")+c;a.animate(j,i/2,b.options.easing).animate(k,i/2,b.options.easing);c=h=="hide"?c*2:c/2}if(h=="hide"){g={opacity:0};g[f]=(d=="pos"?"-=":"+=")+c;a.animate(g,i/2,b.options.easing,function(){a.hide();e.effects.restore(a,l);e.effects.removeWrapper(a);
b.callback&&b.callback.apply(this,arguments)})}else{j={};k={};j[f]=(d=="pos"?"-=":"+=")+c;k[f]=(d=="pos"?"+=":"-=")+c;a.animate(j,i/2,b.options.easing).animate(k,i/2,b.options.easing,function(){e.effects.restore(a,l);e.effects.removeWrapper(a);b.callback&&b.callback.apply(this,arguments)})}a.queue("fx",function(){a.dequeue()});a.dequeue()})}})(jQuery);
;/*
 * jQuery UI Effects Clip 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/Clip
 *
 * Depends:
 *  jquery.effects.core.js
 */
(function(b){b.effects.clip=function(e){return this.queue(function(){var a=b(this),i=["position","top","bottom","left","right","height","width"],f=b.effects.setMode(a,e.options.mode||"hide"),c=e.options.direction||"vertical";b.effects.save(a,i);a.show();var d=b.effects.createWrapper(a).css({overflow:"hidden"});d=a[0].tagName=="IMG"?d:a;var g={size:c=="vertical"?"height":"width",position:c=="vertical"?"top":"left"};c=c=="vertical"?d.height():d.width();if(f=="show"){d.css(g.size,0);d.css(g.position,
c/2)}var h={};h[g.size]=f=="show"?c:0;h[g.position]=f=="show"?0:c/2;d.animate(h,{queue:false,duration:e.duration,easing:e.options.easing,complete:function(){f=="hide"&&a.hide();b.effects.restore(a,i);b.effects.removeWrapper(a);e.callback&&e.callback.apply(a[0],arguments);a.dequeue()}})})}})(jQuery);
;/*
 * jQuery UI Effects Drop 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/Drop
 *
 * Depends:
 *  jquery.effects.core.js
 */
(function(c){c.effects.drop=function(d){return this.queue(function(){var a=c(this),h=["position","top","bottom","left","right","opacity"],e=c.effects.setMode(a,d.options.mode||"hide"),b=d.options.direction||"left";c.effects.save(a,h);a.show();c.effects.createWrapper(a);var f=b=="up"||b=="down"?"top":"left";b=b=="up"||b=="left"?"pos":"neg";var g=d.options.distance||(f=="top"?a.outerHeight({margin:true})/2:a.outerWidth({margin:true})/2);if(e=="show")a.css("opacity",0).css(f,b=="pos"?-g:g);var i={opacity:e==
"show"?1:0};i[f]=(e=="show"?b=="pos"?"+=":"-=":b=="pos"?"-=":"+=")+g;a.animate(i,{queue:false,duration:d.duration,easing:d.options.easing,complete:function(){e=="hide"&&a.hide();c.effects.restore(a,h);c.effects.removeWrapper(a);d.callback&&d.callback.apply(this,arguments);a.dequeue()}})})}})(jQuery);
;/*
 * jQuery UI Effects Explode 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/Explode
 *
 * Depends:
 *  jquery.effects.core.js
 */
(function(j){j.effects.explode=function(a){return this.queue(function(){var c=a.options.pieces?Math.round(Math.sqrt(a.options.pieces)):3,d=a.options.pieces?Math.round(Math.sqrt(a.options.pieces)):3;a.options.mode=a.options.mode=="toggle"?j(this).is(":visible")?"hide":"show":a.options.mode;var b=j(this).show().css("visibility","hidden"),g=b.offset();g.top-=parseInt(b.css("marginTop"),10)||0;g.left-=parseInt(b.css("marginLeft"),10)||0;for(var h=b.outerWidth(true),i=b.outerHeight(true),e=0;e<c;e++)for(var f=
0;f<d;f++)b.clone().appendTo("body").wrap("<div></div>").css({position:"absolute",visibility:"visible",left:-f*(h/d),top:-e*(i/c)}).parent().addClass("ui-effects-explode").css({position:"absolute",overflow:"hidden",width:h/d,height:i/c,left:g.left+f*(h/d)+(a.options.mode=="show"?(f-Math.floor(d/2))*(h/d):0),top:g.top+e*(i/c)+(a.options.mode=="show"?(e-Math.floor(c/2))*(i/c):0),opacity:a.options.mode=="show"?0:1}).animate({left:g.left+f*(h/d)+(a.options.mode=="show"?0:(f-Math.floor(d/2))*(h/d)),top:g.top+
e*(i/c)+(a.options.mode=="show"?0:(e-Math.floor(c/2))*(i/c)),opacity:a.options.mode=="show"?1:0},a.duration||500);setTimeout(function(){a.options.mode=="show"?b.css({visibility:"visible"}):b.css({visibility:"visible"}).hide();a.callback&&a.callback.apply(b[0]);b.dequeue();j("div.ui-effects-explode").remove()},a.duration||500)})}})(jQuery);
;/*
 * jQuery UI Effects Fade 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/Fade
 *
 * Depends:
 *  jquery.effects.core.js
 */
(function(b){b.effects.fade=function(a){return this.queue(function(){var c=b(this),d=b.effects.setMode(c,a.options.mode||"hide");c.animate({opacity:d},{queue:false,duration:a.duration,easing:a.options.easing,complete:function(){a.callback&&a.callback.apply(this,arguments);c.dequeue()}})})}})(jQuery);
;/*
 * jQuery UI Effects Fold 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/Fold
 *
 * Depends:
 *  jquery.effects.core.js
 */
(function(c){c.effects.fold=function(a){return this.queue(function(){var b=c(this),j=["position","top","bottom","left","right"],d=c.effects.setMode(b,a.options.mode||"hide"),g=a.options.size||15,h=!!a.options.horizFirst,k=a.duration?a.duration/2:c.fx.speeds._default/2;c.effects.save(b,j);b.show();var e=c.effects.createWrapper(b).css({overflow:"hidden"}),f=d=="show"!=h,l=f?["width","height"]:["height","width"];f=f?[e.width(),e.height()]:[e.height(),e.width()];var i=/([0-9]+)%/.exec(g);if(i)g=parseInt(i[1],
10)/100*f[d=="hide"?0:1];if(d=="show")e.css(h?{height:0,width:g}:{height:g,width:0});h={};i={};h[l[0]]=d=="show"?f[0]:g;i[l[1]]=d=="show"?f[1]:0;e.animate(h,k,a.options.easing).animate(i,k,a.options.easing,function(){d=="hide"&&b.hide();c.effects.restore(b,j);c.effects.removeWrapper(b);a.callback&&a.callback.apply(b[0],arguments);b.dequeue()})})}})(jQuery);
;/*
 * jQuery UI Effects Highlight 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/Highlight
 *
 * Depends:
 *  jquery.effects.core.js
 */
(function(b){b.effects.highlight=function(c){return this.queue(function(){var a=b(this),e=["backgroundImage","backgroundColor","opacity"],d=b.effects.setMode(a,c.options.mode||"show"),f={backgroundColor:a.css("backgroundColor")};if(d=="hide")f.opacity=0;b.effects.save(a,e);a.show().css({backgroundImage:"none",backgroundColor:c.options.color||"#ffff99"}).animate(f,{queue:false,duration:c.duration,easing:c.options.easing,complete:function(){d=="hide"&&a.hide();b.effects.restore(a,e);d=="show"&&!b.support.opacity&&
this.style.removeAttribute("filter");c.callback&&c.callback.apply(this,arguments);a.dequeue()}})})}})(jQuery);
;/*
 * jQuery UI Effects Pulsate 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/Pulsate
 *
 * Depends:
 *  jquery.effects.core.js
 */
(function(d){d.effects.pulsate=function(a){return this.queue(function(){var b=d(this),c=d.effects.setMode(b,a.options.mode||"show");times=(a.options.times||5)*2-1;duration=a.duration?a.duration/2:d.fx.speeds._default/2;isVisible=b.is(":visible");animateTo=0;if(!isVisible){b.css("opacity",0).show();animateTo=1}if(c=="hide"&&isVisible||c=="show"&&!isVisible)times--;for(c=0;c<times;c++){b.animate({opacity:animateTo},duration,a.options.easing);animateTo=(animateTo+1)%2}b.animate({opacity:animateTo},duration,
a.options.easing,function(){animateTo==0&&b.hide();a.callback&&a.callback.apply(this,arguments)});b.queue("fx",function(){b.dequeue()}).dequeue()})}})(jQuery);
;/*
 * jQuery UI Effects Scale 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/Scale
 *
 * Depends:
 *  jquery.effects.core.js
 */
(function(c){c.effects.puff=function(b){return this.queue(function(){var a=c(this),e=c.effects.setMode(a,b.options.mode||"hide"),g=parseInt(b.options.percent,10)||150,h=g/100,i={height:a.height(),width:a.width()};c.extend(b.options,{fade:true,mode:e,percent:e=="hide"?g:100,from:e=="hide"?i:{height:i.height*h,width:i.width*h}});a.effect("scale",b.options,b.duration,b.callback);a.dequeue()})};c.effects.scale=function(b){return this.queue(function(){var a=c(this),e=c.extend(true,{},b.options),g=c.effects.setMode(a,
b.options.mode||"effect"),h=parseInt(b.options.percent,10)||(parseInt(b.options.percent,10)==0?0:g=="hide"?0:100),i=b.options.direction||"both",f=b.options.origin;if(g!="effect"){e.origin=f||["middle","center"];e.restore=true}f={height:a.height(),width:a.width()};a.from=b.options.from||(g=="show"?{height:0,width:0}:f);h={y:i!="horizontal"?h/100:1,x:i!="vertical"?h/100:1};a.to={height:f.height*h.y,width:f.width*h.x};if(b.options.fade){if(g=="show"){a.from.opacity=0;a.to.opacity=1}if(g=="hide"){a.from.opacity=
1;a.to.opacity=0}}e.from=a.from;e.to=a.to;e.mode=g;a.effect("size",e,b.duration,b.callback);a.dequeue()})};c.effects.size=function(b){return this.queue(function(){var a=c(this),e=["position","top","bottom","left","right","width","height","overflow","opacity"],g=["position","top","bottom","left","right","overflow","opacity"],h=["width","height","overflow"],i=["fontSize"],f=["borderTopWidth","borderBottomWidth","paddingTop","paddingBottom"],k=["borderLeftWidth","borderRightWidth","paddingLeft","paddingRight"],
p=c.effects.setMode(a,b.options.mode||"effect"),n=b.options.restore||false,m=b.options.scale||"both",l=b.options.origin,j={height:a.height(),width:a.width()};a.from=b.options.from||j;a.to=b.options.to||j;if(l){l=c.effects.getBaseline(l,j);a.from.top=(j.height-a.from.height)*l.y;a.from.left=(j.width-a.from.width)*l.x;a.to.top=(j.height-a.to.height)*l.y;a.to.left=(j.width-a.to.width)*l.x}var d={from:{y:a.from.height/j.height,x:a.from.width/j.width},to:{y:a.to.height/j.height,x:a.to.width/j.width}};
if(m=="box"||m=="both"){if(d.from.y!=d.to.y){e=e.concat(f);a.from=c.effects.setTransition(a,f,d.from.y,a.from);a.to=c.effects.setTransition(a,f,d.to.y,a.to)}if(d.from.x!=d.to.x){e=e.concat(k);a.from=c.effects.setTransition(a,k,d.from.x,a.from);a.to=c.effects.setTransition(a,k,d.to.x,a.to)}}if(m=="content"||m=="both")if(d.from.y!=d.to.y){e=e.concat(i);a.from=c.effects.setTransition(a,i,d.from.y,a.from);a.to=c.effects.setTransition(a,i,d.to.y,a.to)}c.effects.save(a,n?e:g);a.show();c.effects.createWrapper(a);
a.css("overflow","hidden").css(a.from);if(m=="content"||m=="both"){f=f.concat(["marginTop","marginBottom"]).concat(i);k=k.concat(["marginLeft","marginRight"]);h=e.concat(f).concat(k);a.find("*[width]").each(function(){child=c(this);n&&c.effects.save(child,h);var o={height:child.height(),width:child.width()};child.from={height:o.height*d.from.y,width:o.width*d.from.x};child.to={height:o.height*d.to.y,width:o.width*d.to.x};if(d.from.y!=d.to.y){child.from=c.effects.setTransition(child,f,d.from.y,child.from);
child.to=c.effects.setTransition(child,f,d.to.y,child.to)}if(d.from.x!=d.to.x){child.from=c.effects.setTransition(child,k,d.from.x,child.from);child.to=c.effects.setTransition(child,k,d.to.x,child.to)}child.css(child.from);child.animate(child.to,b.duration,b.options.easing,function(){n&&c.effects.restore(child,h)})})}a.animate(a.to,{queue:false,duration:b.duration,easing:b.options.easing,complete:function(){a.to.opacity===0&&a.css("opacity",a.from.opacity);p=="hide"&&a.hide();c.effects.restore(a,
n?e:g);c.effects.removeWrapper(a);b.callback&&b.callback.apply(this,arguments);a.dequeue()}})})}})(jQuery);
;/*
 * jQuery UI Effects Shake 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/Shake
 *
 * Depends:
 *  jquery.effects.core.js
 */
(function(d){d.effects.shake=function(a){return this.queue(function(){var b=d(this),j=["position","top","bottom","left","right"];d.effects.setMode(b,a.options.mode||"effect");var c=a.options.direction||"left",e=a.options.distance||20,l=a.options.times||3,f=a.duration||a.options.duration||140;d.effects.save(b,j);b.show();d.effects.createWrapper(b);var g=c=="up"||c=="down"?"top":"left",h=c=="up"||c=="left"?"pos":"neg";c={};var i={},k={};c[g]=(h=="pos"?"-=":"+=")+e;i[g]=(h=="pos"?"+=":"-=")+e*2;k[g]=
(h=="pos"?"-=":"+=")+e*2;b.animate(c,f,a.options.easing);for(e=1;e<l;e++)b.animate(i,f,a.options.easing).animate(k,f,a.options.easing);b.animate(i,f,a.options.easing).animate(c,f/2,a.options.easing,function(){d.effects.restore(b,j);d.effects.removeWrapper(b);a.callback&&a.callback.apply(this,arguments)});b.queue("fx",function(){b.dequeue()});b.dequeue()})}})(jQuery);
;/*
 * jQuery UI Effects Slide 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/Slide
 *
 * Depends:
 *  jquery.effects.core.js
 */
(function(c){c.effects.slide=function(d){return this.queue(function(){var a=c(this),h=["position","top","bottom","left","right"],f=c.effects.setMode(a,d.options.mode||"show"),b=d.options.direction||"left";c.effects.save(a,h);a.show();c.effects.createWrapper(a).css({overflow:"hidden"});var g=b=="up"||b=="down"?"top":"left";b=b=="up"||b=="left"?"pos":"neg";var e=d.options.distance||(g=="top"?a.outerHeight({margin:true}):a.outerWidth({margin:true}));if(f=="show")a.css(g,b=="pos"?isNaN(e)?"-"+e:-e:e);
var i={};i[g]=(f=="show"?b=="pos"?"+=":"-=":b=="pos"?"-=":"+=")+e;a.animate(i,{queue:false,duration:d.duration,easing:d.options.easing,complete:function(){f=="hide"&&a.hide();c.effects.restore(a,h);c.effects.removeWrapper(a);d.callback&&d.callback.apply(this,arguments);a.dequeue()}})})}})(jQuery);
;/*
 * jQuery UI Effects Transfer 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Effects/Transfer
 *
 * Depends:
 *  jquery.effects.core.js
 */
(function(e){e.effects.transfer=function(a){return this.queue(function(){var b=e(this),c=e(a.options.to),d=c.offset();c={top:d.top,left:d.left,height:c.innerHeight(),width:c.innerWidth()};d=b.offset();var f=e('<div class="ui-effects-transfer"></div>').appendTo(document.body).addClass(a.options.className).css({top:d.top,left:d.left,height:b.innerHeight(),width:b.innerWidth(),position:"absolute"}).animate(c,a.duration,a.options.easing,function(){f.remove();a.callback&&a.callback.apply(b[0],arguments);
b.dequeue()})})}})(jQuery);
;/*
 * jQuery UI Autocomplete 1.8.16
 *
 * Copyright 2011, AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * http://docs.jquery.com/UI/Autocomplete
 *
 * Depends:
 *  jquery.ui.core.js
 *  jquery.ui.widget.js
 *  jquery.ui.position.js
 */
(function(d){var e=0;d.widget("ui.autocomplete",{options:{appendTo:"body",autoFocus:false,delay:300,minLength:1,position:{my:"left top",at:"left bottom",collision:"none"},source:null},pending:0,_create:function(){var a=this,b=this.element[0].ownerDocument,g;this.element.addClass("ui-autocomplete-input").attr("autocomplete","off").attr({role:"textbox","aria-autocomplete":"list","aria-haspopup":"true"}).bind("keydown.autocomplete",function(c){if(!(a.options.disabled||a.element.propAttr("readOnly"))){g=
false;var f=d.ui.keyCode;switch(c.keyCode){case f.PAGE_UP:a._move("previousPage",c);break;case f.PAGE_DOWN:a._move("nextPage",c);break;case f.UP:a._move("previous",c);c.preventDefault();break;case f.DOWN:a._move("next",c);c.preventDefault();break;case f.ENTER:case f.NUMPAD_ENTER:if(a.menu.active){g=true;c.preventDefault()}case f.TAB:if(!a.menu.active)return;a.menu.select(c);break;case f.ESCAPE:a.element.val(a.term);a.close(c);break;default:clearTimeout(a.searching);a.searching=setTimeout(function(){if(a.term!=
a.element.val()){a.selectedItem=null;a.search(null,c)}},a.options.delay);break}}}).bind("keypress.autocomplete",function(c){if(g){g=false;c.preventDefault()}}).bind("focus.autocomplete",function(){if(!a.options.disabled){a.selectedItem=null;a.previous=a.element.val()}}).bind("blur.autocomplete",function(c){if(!a.options.disabled){clearTimeout(a.searching);a.closing=setTimeout(function(){a.close(c);a._change(c)},150)}});this._initSource();this.response=function(){return a._response.apply(a,arguments)};
this.menu=d("<ul></ul>").addClass("ui-autocomplete").appendTo(d(this.options.appendTo||"body",b)[0]).mousedown(function(c){var f=a.menu.element[0];d(c.target).closest(".ui-menu-item").length||setTimeout(function(){d(document).one("mousedown",function(h){h.target!==a.element[0]&&h.target!==f&&!d.ui.contains(f,h.target)&&a.close()})},1);setTimeout(function(){clearTimeout(a.closing)},13)}).menu({focus:function(c,f){f=f.item.data("item.autocomplete");false!==a._trigger("focus",c,{item:f})&&/^key/.test(c.originalEvent.type)&&
a.element.val(f.value)},selected:function(c,f){var h=f.item.data("item.autocomplete"),i=a.previous;if(a.element[0]!==b.activeElement){a.element.focus();a.previous=i;setTimeout(function(){a.previous=i;a.selectedItem=h},1)}false!==a._trigger("select",c,{item:h})&&a.element.val(h.value);a.term=a.element.val();a.close(c);a.selectedItem=h},blur:function(){a.menu.element.is(":visible")&&a.element.val()!==a.term&&a.element.val(a.term)}}).zIndex(this.element.zIndex()+1).css({top:0,left:0}).hide().data("menu");
d.fn.bgiframe&&this.menu.element.bgiframe()},destroy:function(){this.element.removeClass("ui-autocomplete-input").removeAttr("autocomplete").removeAttr("role").removeAttr("aria-autocomplete").removeAttr("aria-haspopup");this.menu.element.remove();d.Widget.prototype.destroy.call(this)},_setOption:function(a,b){d.Widget.prototype._setOption.apply(this,arguments);a==="source"&&this._initSource();if(a==="appendTo")this.menu.element.appendTo(d(b||"body",this.element[0].ownerDocument)[0]);a==="disabled"&&
b&&this.xhr&&this.xhr.abort()},_initSource:function(){var a=this,b,g;if(d.isArray(this.options.source)){b=this.options.source;this.source=function(c,f){f(d.ui.autocomplete.filter(b,c.term))}}else if(typeof this.options.source==="string"){g=this.options.source;this.source=function(c,f){a.xhr&&a.xhr.abort();a.xhr=d.ajax({url:g,data:c,dataType:"json",autocompleteRequest:++e,success:function(h){this.autocompleteRequest===e&&f(h)},error:function(){this.autocompleteRequest===e&&f([])}})}}else this.source=
this.options.source},search:function(a,b){a=a!=null?a:this.element.val();this.term=this.element.val();if(a.length<this.options.minLength)return this.close(b);clearTimeout(this.closing);if(this._trigger("search",b)!==false)return this._search(a)},_search:function(a){this.pending++;this.element.addClass("ui-autocomplete-loading");this.source({term:a},this.response)},_response:function(a){if(!this.options.disabled&&a&&a.length){a=this._normalize(a);this._suggest(a);this._trigger("open")}else this.close();
this.pending--;this.pending||this.element.removeClass("ui-autocomplete-loading")},close:function(a){clearTimeout(this.closing);if(this.menu.element.is(":visible")){this.menu.element.hide();this.menu.deactivate();this._trigger("close",a)}},_change:function(a){this.previous!==this.element.val()&&this._trigger("change",a,{item:this.selectedItem})},_normalize:function(a){if(a.length&&a[0].label&&a[0].value)return a;return d.map(a,function(b){if(typeof b==="string")return{label:b,value:b};return d.extend({label:b.label||
b.value,value:b.value||b.label},b)})},_suggest:function(a){var b=this.menu.element.empty().zIndex(this.element.zIndex()+1);this._renderMenu(b,a);this.menu.deactivate();this.menu.refresh();b.show();this._resizeMenu();b.position(d.extend({of:this.element},this.options.position));this.options.autoFocus&&this.menu.next(new d.Event("mouseover"))},_resizeMenu:function(){var a=this.menu.element;a.outerWidth(Math.max(a.width("").outerWidth(),this.element.outerWidth()))},_renderMenu:function(a,b){var g=this;
d.each(b,function(c,f){g._renderItem(a,f)})},_renderItem:function(a,b){return d("<li></li>").data("item.autocomplete",b).append(d("<a></a>").text(b.label)).appendTo(a)},_move:function(a,b){if(this.menu.element.is(":visible"))if(this.menu.first()&&/^previous/.test(a)||this.menu.last()&&/^next/.test(a)){this.element.val(this.term);this.menu.deactivate()}else this.menu[a](b);else this.search(null,b)},widget:function(){return this.menu.element}});d.extend(d.ui.autocomplete,{escapeRegex:function(a){return a.replace(/[-[\]{}()*+?.,\\^$|#\s]/g,
"\\$&")},filter:function(a,b){var g=new RegExp(d.ui.autocomplete.escapeRegex(b),"i");return d.grep(a,function(c){return g.test(c.label||c.value||c)})}})})(jQuery);
(function(d){d.widget("ui.menu",{_create:function(){var e=this;this.element.addClass("ui-menu ui-widget ui-widget-content ui-corner-all").attr({role:"listbox","aria-activedescendant":"ui-active-menuitem"}).click(function(a){if(d(a.target).closest(".ui-menu-item a").length){a.preventDefault();e.select(a)}});this.refresh()},refresh:function(){var e=this;this.element.children("li:not(.ui-menu-item):has(a)").addClass("ui-menu-item").attr("role","menuitem").children("a").addClass("ui-corner-all").attr("tabindex",
-1).mouseenter(function(a){e.activate(a,d(this).parent())}).mouseleave(function(){e.deactivate()})},activate:function(e,a){this.deactivate();if(this.hasScroll()){var b=a.offset().top-this.element.offset().top,g=this.element.scrollTop(),c=this.element.height();if(b<0)this.element.scrollTop(g+b);else b>=c&&this.element.scrollTop(g+b-c+a.height())}this.active=a.eq(0).children("a").addClass("ui-state-hover").attr("id","ui-active-menuitem").end();this._trigger("focus",e,{item:a})},deactivate:function(){if(this.active){this.active.children("a").removeClass("ui-state-hover").removeAttr("id");
this._trigger("blur");this.active=null}},next:function(e){this.move("next",".ui-menu-item:first",e)},previous:function(e){this.move("prev",".ui-menu-item:last",e)},first:function(){return this.active&&!this.active.prevAll(".ui-menu-item").length},last:function(){return this.active&&!this.active.nextAll(".ui-menu-item").length},move:function(e,a,b){if(this.active){e=this.active[e+"All"](".ui-menu-item").eq(0);e.length?this.activate(b,e):this.activate(b,this.element.children(a))}else this.activate(b,
this.element.children(a))},nextPage:function(e){if(this.hasScroll())if(!this.active||this.last())this.activate(e,this.element.children(".ui-menu-item:first"));else{var a=this.active.offset().top,b=this.element.height(),g=this.element.children(".ui-menu-item").filter(function(){var c=d(this).offset().top-a-b+d(this).height();return c<10&&c>-10});g.length||(g=this.element.children(".ui-menu-item:last"));this.activate(e,g)}else this.activate(e,this.element.children(".ui-menu-item").filter(!this.active||
this.last()?":first":":last"))},previousPage:function(e){if(this.hasScroll())if(!this.active||this.first())this.activate(e,this.element.children(".ui-menu-item:last"));else{var a=this.active.offset().top,b=this.element.height();result=this.element.children(".ui-menu-item").filter(function(){var g=d(this).offset().top-a+b-d(this).height();return g<10&&g>-10});result.length||(result=this.element.children(".ui-menu-item:first"));this.activate(e,result)}else this.activate(e,this.element.children(".ui-menu-item").filter(!this.active||
this.first()?":last":":first"))},hasScroll:function(){return this.element.height()<this.element[d.fn.prop?"prop":"attr"]("scrollHeight")},select:function(e){this._trigger("selected",e,{item:this.active})}})})(jQuery);
;

/***********************************
    jquery.validate.js
***********************************/

/**
 * jQuery Validation Plugin 1.8.1
 *
 * http://bassistance.de/jquery-plugins/jquery-plugin-validation/
 * http://docs.jquery.com/Plugins/Validation
 *
 * Copyright (c) 2006 - 2011 Jörn Zaefferer
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 */

(function($) {

$.extend($.fn, {
    // http://docs.jquery.com/Plugins/Validation/validate
    validate: function( options ) {

        // if nothing is selected, return nothing; can't chain anyway
        if (!this.length) {
            options && options.debug && window.console && console.warn( "nothing selected, can't validate, returning nothing" );
            return;
        }

        // check if a validator for this form was already created
        var validator = $.data(this[0], 'validator');
        if ( validator ) {
            return validator;
        }

        validator = new $.validator( options, this[0] );
        $.data(this[0], 'validator', validator);

        if ( validator.settings.onsubmit ) {

            // allow suppresing validation by adding a cancel class to the submit button
            this.find("input, button").filter(".cancel").click(function() {
                validator.cancelSubmit = true;
            });

            // when a submitHandler is used, capture the submitting button
            if (validator.settings.submitHandler) {
                this.find("input, button").filter(":submit").click(function() {
                    validator.submitButton = this;
                });
            }

            // validate the form on submit
            this.submit( function( event ) {
                if ( validator.settings.debug )
                    // prevent form submit to be able to see console output
                    event.preventDefault();

                function handle() {
                    if ( validator.settings.submitHandler ) {
                        if (validator.submitButton) {
                            // insert a hidden input as a replacement for the missing submit button
                            var hidden = $("<input type='hidden'/>").attr("name", validator.submitButton.name).val(validator.submitButton.value).appendTo(validator.currentForm);
                        }
                        validator.settings.submitHandler.call( validator, validator.currentForm );
                        if (validator.submitButton) {
                            // and clean up afterwards; thanks to no-block-scope, hidden can be referenced
                            hidden.remove();
                        }
                        return false;
                    }
                    return true;
                }

                // prevent submit for invalid forms or custom submit handlers
                if ( validator.cancelSubmit ) {
                    validator.cancelSubmit = false;
                    return handle();
                }
                if ( validator.form() ) {
                    if ( validator.pendingRequest ) {
                        validator.formSubmitted = true;
                        return false;
                    }
                    return handle();
                } else {
                    validator.focusInvalid();
                    return false;
                }
            });
        }

        return validator;
    },
    // http://docs.jquery.com/Plugins/Validation/valid
    valid: function() {
        if ( $(this[0]).is('form')) {
            return this.validate().form();
        } else {
            var valid = true;
            var validator = $(this[0].form).validate();
            this.each(function() {
                valid &= validator.element(this);
            });
            return valid;
        }
    },
    // attributes: space seperated list of attributes to retrieve and remove
    removeAttrs: function(attributes) {
        var result = {},
            $element = this;
        $.each(attributes.split(/\s/), function(index, value) {
            result[value] = $element.attr(value);
            $element.removeAttr(value);
        });
        return result;
    },
    // http://docs.jquery.com/Plugins/Validation/rules
    rules: function(command, argument) {
        var element = this[0];

        if (command) {
            var settings = $.data(element.form, 'validator').settings;
            var staticRules = settings.rules;
            var existingRules = $.validator.staticRules(element);
            switch(command) {
            case "add":
                $.extend(existingRules, $.validator.normalizeRule(argument));
                staticRules[element.name] = existingRules;
                if (argument.messages)
                    settings.messages[element.name] = $.extend( settings.messages[element.name], argument.messages );
                break;
            case "remove":
                if (!argument) {
                    delete staticRules[element.name];
                    return existingRules;
                }
                var filtered = {};
                $.each(argument.split(/\s/), function(index, method) {
                    filtered[method] = existingRules[method];
                    delete existingRules[method];
                });
                return filtered;
            }
        }

        var data = $.validator.normalizeRules(
        $.extend(
            {},
            $.validator.metadataRules(element),
            $.validator.classRules(element),
            $.validator.attributeRules(element),
            $.validator.staticRules(element)
        ), element);

        // make sure required is at front
        if (data.required) {
            var param = data.required;
            delete data.required;
            data = $.extend({required: param}, data);
        }

        return data;
    }
});

// Custom selectors
$.extend($.expr[":"], {
    // http://docs.jquery.com/Plugins/Validation/blank
    blank: function(a) {return !$.trim("" + a.value);},
    // http://docs.jquery.com/Plugins/Validation/filled
    filled: function(a) {return !!$.trim("" + a.value);},
    // http://docs.jquery.com/Plugins/Validation/unchecked
    unchecked: function(a) {return !a.checked;}
});

// constructor for validator
$.validator = function( options, form ) {
    this.settings = $.extend( true, {}, $.validator.defaults, options );
    this.currentForm = form;
    this.init();
};

$.validator.format = function(source, params) {
    if ( arguments.length == 1 )
        return function() {
            var args = $.makeArray(arguments);
            args.unshift(source);
            return $.validator.format.apply( this, args );
        };
    if ( arguments.length > 2 && params.constructor != Array  ) {
        params = $.makeArray(arguments).slice(1);
    }
    if ( params.constructor != Array ) {
        params = [ params ];
    }
    $.each(params, function(i, n) {
        source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n);
    });
    return source;
};

$.extend($.validator, {

    defaults: {
        messages: {},
        groups: {},
        rules: {},
        errorClass: "error",
        validClass: "valid",
        errorElement: "label",
        focusInvalid: true,
        errorContainer: $( [] ),
        errorLabelContainer: $( [] ),
        onsubmit: true,
        ignore: [],
        ignoreTitle: false,
        onfocusin: function(element) {
            this.lastActive = element;

            // hide error label and remove error class on focus if enabled
            if ( this.settings.focusCleanup && !this.blockFocusCleanup ) {
                this.settings.unhighlight && this.settings.unhighlight.call( this, element, this.settings.errorClass, this.settings.validClass );
                this.addWrapper(this.errorsFor(element)).hide();
            }
        },
        onfocusout: function(element) {
            if ( !this.checkable(element) && (element.name in this.submitted || !this.optional(element)) ) {
                this.element(element);
            }
        },
        onkeyup: function(element) {
            if ( element.name in this.submitted || element == this.lastElement ) {
                this.element(element);
            }
        },
        onclick: function(element) {
            // click on selects, radiobuttons and checkboxes
            if ( element.name in this.submitted )
                this.element(element);
            // or option elements, check parent select in that case
            else if (element.parentNode.name in this.submitted)
                this.element(element.parentNode);
        },
        highlight: function(element, errorClass, validClass) {
            if (element.type === 'radio') {
                this.findByName(element.name).addClass(errorClass).removeClass(validClass);
            } else {
                $(element).addClass(errorClass).removeClass(validClass);
            }
        },
        unhighlight: function(element, errorClass, validClass) {
            if (element.type === 'radio') {
                this.findByName(element.name).removeClass(errorClass).addClass(validClass);
            } else {
                $(element).removeClass(errorClass).addClass(validClass);
            }
        }
    },

    // http://docs.jquery.com/Plugins/Validation/Validator/setDefaults
    setDefaults: function(settings) {
        $.extend( $.validator.defaults, settings );
    },

    messages: {
        required: "This field is required.",
        remote: "Please fix this field.",
        email: "Please enter a valid email address.",
        url: "Please enter a valid URL.",
        date: "Please enter a valid date.",
        dateISO: "Please enter a valid date (ISO).",
        number: "Please enter a valid number.",
        digits: "Please enter only digits.",
        creditcard: "Please enter a valid credit card number.",
        equalTo: "Please enter the same value again.",
        accept: "Please enter a value with a valid extension.",
        maxlength: $.validator.format("Please enter no more than {0} characters."),
        minlength: $.validator.format("Please enter at least {0} characters."),
        rangelength: $.validator.format("Please enter a value between {0} and {1} characters long."),
        range: $.validator.format("Please enter a value between {0} and {1}."),
        max: $.validator.format("Please enter a value less than or equal to {0}."),
        min: $.validator.format("Please enter a value greater than or equal to {0}.")
    },

    autoCreateRanges: false,

    prototype: {

        init: function() {
            this.labelContainer = $(this.settings.errorLabelContainer);
            this.errorContext = this.labelContainer.length && this.labelContainer || $(this.currentForm);
            this.containers = $(this.settings.errorContainer).add( this.settings.errorLabelContainer );
            this.submitted = {};
            this.valueCache = {};
            this.pendingRequest = 0;
            this.pending = {};
            this.invalid = {};
            this.reset();

            var groups = (this.groups = {});
            $.each(this.settings.groups, function(key, value) {
                $.each(value.split(/\s/), function(index, name) {
                    groups[name] = key;
                });
            });
            var rules = this.settings.rules;
            $.each(rules, function(key, value) {
                rules[key] = $.validator.normalizeRule(value);
            });

            function delegate(event) {
                var validator = $.data(this[0].form, "validator"),
                    eventType = "on" + event.type.replace(/^validate/, "");
                validator.settings[eventType] && validator.settings[eventType].call(validator, this[0] );
            }
            $(this.currentForm)
                .validateDelegate(":text, :password, :file, select, textarea", "focusin focusout keyup", delegate)
                .validateDelegate(":radio, :checkbox, select, option", "click", delegate);

            if (this.settings.invalidHandler)
                $(this.currentForm).bind("invalid-form.validate", this.settings.invalidHandler);
        },

        // http://docs.jquery.com/Plugins/Validation/Validator/form
        form: function() {
            this.checkForm();
            $.extend(this.submitted, this.errorMap);
            this.invalid = $.extend({}, this.errorMap);
            if (!this.valid())
                $(this.currentForm).triggerHandler("invalid-form", [this]);
            this.showErrors();
            return this.valid();
        },

        checkForm: function() {
            this.prepareForm();
            for ( var i = 0, elements = (this.currentElements = this.elements()); elements[i]; i++ ) {
                this.check( elements[i] );
            }
            return this.valid();
        },

        // http://docs.jquery.com/Plugins/Validation/Validator/element
        element: function( element ) {
            element = this.clean( element );
            this.lastElement = element;
            this.prepareElement( element );
            this.currentElements = $(element);
            var result = this.check( element );
            if ( result ) {
                delete this.invalid[element.name];
            } else {
                this.invalid[element.name] = true;
            }
            if ( !this.numberOfInvalids() ) {
                // Hide error containers on last error
                this.toHide = this.toHide.add( this.containers );
            }
            this.showErrors();
            return result;
        },

        // http://docs.jquery.com/Plugins/Validation/Validator/showErrors
        showErrors: function(errors) {
            if(errors) {
                // add items to error list and map
                $.extend( this.errorMap, errors );
                this.errorList = [];
                for ( var name in errors ) {
                    this.errorList.push({
                        message: errors[name],
                        element: this.findByName(name)[0]
                    });
                }
                // remove items from success list
                this.successList = $.grep( this.successList, function(element) {
                    return !(element.name in errors);
                });
            }
            this.settings.showErrors
                ? this.settings.showErrors.call( this, this.errorMap, this.errorList )
                : this.defaultShowErrors();
        },

        // http://docs.jquery.com/Plugins/Validation/Validator/resetForm
        resetForm: function() {
            if ( $.fn.resetForm )
                $( this.currentForm ).resetForm();
            this.submitted = {};
            this.prepareForm();
            this.hideErrors();
            this.elements().removeClass( this.settings.errorClass );
        },

        numberOfInvalids: function() {
            return this.objectLength(this.invalid);
        },

        objectLength: function( obj ) {
            var count = 0;
            for ( var i in obj )
                count++;
            return count;
        },

        hideErrors: function() {
            this.addWrapper( this.toHide ).hide();
        },

        valid: function() {
            return this.size() == 0;
        },

        size: function() {
            return this.errorList.length;
        },

        focusInvalid: function() {
            if( this.settings.focusInvalid ) {
                try {
                    $(this.findLastActive() || this.errorList.length && this.errorList[0].element || [])
                    .filter(":visible")
                    .focus()
                    // manually trigger focusin event; without it, focusin handler isn't called, findLastActive won't have anything to find
                    .trigger("focusin");
                } catch(e) {
                    // ignore IE throwing errors when focusing hidden elements
                }
            }
        },

        findLastActive: function() {
            var lastActive = this.lastActive;
            return lastActive && $.grep(this.errorList, function(n) {
                return n.element.name == lastActive.name;
            }).length == 1 && lastActive;
        },

        elements: function() {
            var validator = this,
                rulesCache = {};

            // select all valid inputs inside the form (no submit or reset buttons)
            return $(this.currentForm)
            .find("input, select, textarea")
            .not(":submit, :reset, :image, [disabled]")
            .not( this.settings.ignore )
            .filter(function() {
                !this.name && validator.settings.debug && window.console && console.error( "%o has no name assigned", this);

                // select only the first element for each name, and only those with rules specified
                if ( this.name in rulesCache || !validator.objectLength($(this).rules()) )
                    return false;

                rulesCache[this.name] = true;
                return true;
            });
        },

        clean: function( selector ) {
            return $( selector )[0];
        },

        errors: function() {
            return $( this.settings.errorElement + "." + this.settings.errorClass, this.errorContext );
        },

        reset: function() {
            this.successList = [];
            this.errorList = [];
            this.errorMap = {};
            this.toShow = $([]);
            this.toHide = $([]);
            this.currentElements = $([]);
        },

        prepareForm: function() {
            this.reset();
            this.toHide = this.errors().add( this.containers );
        },

        prepareElement: function( element ) {
            this.reset();
            this.toHide = this.errorsFor(element);
        },

        check: function( element ) {
            element = this.clean( element );

            // if radio/checkbox, validate first element in group instead
            if (this.checkable(element)) {
                element = this.findByName( element.name ).not(this.settings.ignore)[0];
            }

            var rules = $(element).rules();
            var dependencyMismatch = false;
            for (var method in rules ) {
                var rule = { method: method, parameters: rules[method] };
                try {
                    var result = $.validator.methods[method].call( this, element.value.replace(/\r/g, ""), element, rule.parameters );

                    // if a method indicates that the field is optional and therefore valid,
                    // don't mark it as valid when there are no other rules
                    if ( result == "dependency-mismatch" ) {
                        dependencyMismatch = true;
                        continue;
                    }
                    dependencyMismatch = false;

                    if ( result == "pending" ) {
                        this.toHide = this.toHide.not( this.errorsFor(element) );
                        return;
                    }

                    if( !result ) {
                        this.formatAndAdd( element, rule );
                        return false;
                    }
                } catch(e) {
                    this.settings.debug && window.console && console.log("exception occured when checking element " + element.id
                         + ", check the '" + rule.method + "' method", e);
                    throw e;
                }
            }
            if (dependencyMismatch)
                return;
            if ( this.objectLength(rules) )
                this.successList.push(element);
            return true;
        },

        // return the custom message for the given element and validation method
        // specified in the element's "messages" metadata
        customMetaMessage: function(element, method) {
            if (!$.metadata)
                return;

            var meta = this.settings.meta
                ? $(element).metadata()[this.settings.meta]
                : $(element).metadata();

            return meta && meta.messages && meta.messages[method];
        },

        // return the custom message for the given element name and validation method
        customMessage: function( name, method ) {
            var m = this.settings.messages[name];
            return m && (m.constructor == String
                ? m
                : m[method]);
        },

        // return the first defined argument, allowing empty strings
        findDefined: function() {
            for(var i = 0; i < arguments.length; i++) {
                if (arguments[i] !== undefined)
                    return arguments[i];
            }
            return undefined;
        },

        defaultMessage: function( element, method) {
            return this.findDefined(
                this.customMessage( element.name, method ),
                this.customMetaMessage( element, method ),
                // title is never undefined, so handle empty string as undefined
                !this.settings.ignoreTitle && element.title || undefined,
                $.validator.messages[method],
                "<strong>Warning: No message defined for " + element.name + "</strong>"
            );
        },

        formatAndAdd: function( element, rule ) {
            var message = this.defaultMessage( element, rule.method ),
                theregex = /\$?\{(\d+)\}/g;
            if ( typeof message == "function" ) {
                message = message.call(this, rule.parameters, element);
            } else if (theregex.test(message)) {
                message = jQuery.format(message.replace(theregex, '{$1}'), rule.parameters);
            }
            this.errorList.push({
                message: message,
                element: element
            });

            this.errorMap[element.name] = message;
            this.submitted[element.name] = message;
        },

        addWrapper: function(toToggle) {
            if ( this.settings.wrapper )
                toToggle = toToggle.add( toToggle.parent( this.settings.wrapper ) );
            return toToggle;
        },

        defaultShowErrors: function() {
            for ( var i = 0; this.errorList[i]; i++ ) {
                var error = this.errorList[i];
                this.settings.highlight && this.settings.highlight.call( this, error.element, this.settings.errorClass, this.settings.validClass );
                this.showLabel( error.element, error.message );
            }
            if( this.errorList.length ) {
                this.toShow = this.toShow.add( this.containers );
            }
            if (this.settings.success) {
                for ( var i = 0; this.successList[i]; i++ ) {
                    this.showLabel( this.successList[i] );
                }
            }
            if (this.settings.unhighlight) {
                for ( var i = 0, elements = this.validElements(); elements[i]; i++ ) {
                    this.settings.unhighlight.call( this, elements[i], this.settings.errorClass, this.settings.validClass );
                }
            }
            this.toHide = this.toHide.not( this.toShow );
            this.hideErrors();
            this.addWrapper( this.toShow ).show();
        },

        validElements: function() {
            return this.currentElements.not(this.invalidElements());
        },

        invalidElements: function() {
            return $(this.errorList).map(function() {
                return this.element;
            });
        },

        showLabel: function(element, message) {
            var label = this.errorsFor( element );
            if ( label.length ) {
                // refresh error/success class
                label.removeClass().addClass( this.settings.errorClass );

                // check if we have a generated label, replace the message then
                label.attr("generated") && label.html(message);
            } else {
                // create label
                label = $("<" + this.settings.errorElement + "/>")
                    .attr({"for":  this.idOrName(element), generated: true})
                    .addClass(this.settings.errorClass)
                    .html(message || "");
                if ( this.settings.wrapper ) {
                    // make sure the element is visible, even in IE
                    // actually showing the wrapped element is handled elsewhere
                    label = label.hide().show().wrap("<" + this.settings.wrapper + "/>").parent();
                }
                if ( !this.labelContainer.append(label).length )
                    this.settings.errorPlacement
                        ? this.settings.errorPlacement(label, $(element) )
                        : label.insertAfter(element);
            }
            if ( !message && this.settings.success ) {
                label.text("");
                typeof this.settings.success == "string"
                    ? label.addClass( this.settings.success )
                    : this.settings.success( label );
            }
            this.toShow = this.toShow.add(label);
        },

        errorsFor: function(element) {
            var name = this.idOrName(element);
            return this.errors().filter(function() {
                return $(this).attr('for') == name;
            });
        },

        idOrName: function(element) {
            return this.groups[element.name] || (this.checkable(element) ? element.name : element.id || element.name);
        },

        checkable: function( element ) {
            return /radio|checkbox/i.test(element.type);
        },

        findByName: function( name ) {
            // select by name and filter by form for performance over form.find("[name=...]")
            var form = this.currentForm;
            return $(document.getElementsByName(name)).map(function(index, element) {
                return element.form == form && element.name == name && element  || null;
            });
        },

        getLength: function(value, element) {
            switch( element.nodeName.toLowerCase() ) {
            case 'select':
                return $("option:selected", element).val().length;
            case 'input':
                if( this.checkable( element) )
                    return this.findByName(element.name).filter(':checked').length;
            }
            return value.length;
        },

        depend: function(param, element) {
            return this.dependTypes[typeof param]
                ? this.dependTypes[typeof param](param, element)
                : true;
        },

        dependTypes: {
            "boolean": function(param, element) {
                return param;
            },
            "string": function(param, element) {
                return !!$(param, element.form).length;
            },
            "function": function(param, element) {
                return param(element);
            }
        },

        optional: function(element) {
            return !$.validator.methods.required.call(this, $.trim(element.value), element) && "dependency-mismatch";
        },

        startRequest: function(element) {
            if (!this.pending[element.name]) {
                this.pendingRequest++;
                this.pending[element.name] = true;
            }
        },

        stopRequest: function(element, valid) {
            this.pendingRequest--;
            // sometimes synchronization fails, make sure pendingRequest is never < 0
            if (this.pendingRequest < 0)
                this.pendingRequest = 0;
            delete this.pending[element.name];
            if ( valid && this.pendingRequest == 0 && this.formSubmitted && this.form() ) {
                $(this.currentForm).submit();
                this.formSubmitted = false;
            } else if (!valid && this.pendingRequest == 0 && this.formSubmitted) {
                $(this.currentForm).triggerHandler("invalid-form", [this]);
                this.formSubmitted = false;
            }
        },

        previousValue: function(element) {
            return $.data(element, "previousValue") || $.data(element, "previousValue", {
                old: null,
                valid: true,
                message: this.defaultMessage( element, "remote" )
            });
        }

    },

    classRuleSettings: {
        required: {required: true},
        email: {email: true},
        url: {url: true},
        date: {date: true},
        dateISO: {dateISO: true},
        dateDE: {dateDE: true},
        number: {number: true},
        numberDE: {numberDE: true},
        digits: {digits: true},
        creditcard: {creditcard: true}
    },

    addClassRules: function(className, rules) {
        className.constructor == String ?
            this.classRuleSettings[className] = rules :
            $.extend(this.classRuleSettings, className);
    },

    classRules: function(element) {
        var rules = {};
        var classes = $(element).attr('class');
        classes && $.each(classes.split(' '), function() {
            if (this in $.validator.classRuleSettings) {
                $.extend(rules, $.validator.classRuleSettings[this]);
            }
        });
        return rules;
    },

    attributeRules: function(element) {
        var rules = {};
        var $element = $(element);

        for (var method in $.validator.methods) {
            var value = $element.attr(method);
            if (value) {
                rules[method] = value;
            }
        }

        // maxlength may be returned as -1, 2147483647 (IE) and 524288 (safari) for text inputs
        if (rules.maxlength && /-1|2147483647|524288/.test(rules.maxlength)) {
            delete rules.maxlength;
        }

        return rules;
    },

    metadataRules: function(element) {
        if (!$.metadata) return {};

        var meta = $.data(element.form, 'validator').settings.meta;
        return meta ?
            $(element).metadata()[meta] :
            $(element).metadata();
    },

    staticRules: function(element) {
        var rules = {};
        var validator = $.data(element.form, 'validator');
        if (validator.settings.rules) {
            rules = $.validator.normalizeRule(validator.settings.rules[element.name]) || {};
        }
        return rules;
    },

    normalizeRules: function(rules, element) {
        // handle dependency check
        $.each(rules, function(prop, val) {
            // ignore rule when param is explicitly false, eg. required:false
            if (val === false) {
                delete rules[prop];
                return;
            }
            if (val.param || val.depends) {
                var keepRule = true;
                switch (typeof val.depends) {
                    case "string":
                        keepRule = !!$(val.depends, element.form).length;
                        break;
                    case "function":
                        keepRule = val.depends.call(element, element);
                        break;
                }
                if (keepRule) {
                    rules[prop] = val.param !== undefined ? val.param : true;
                } else {
                    delete rules[prop];
                }
            }
        });

        // evaluate parameters
        $.each(rules, function(rule, parameter) {
            rules[rule] = $.isFunction(parameter) ? parameter(element) : parameter;
        });

        // clean number parameters
        $.each(['minlength', 'maxlength', 'min', 'max'], function() {
            if (rules[this]) {
                rules[this] = Number(rules[this]);
            }
        });
        $.each(['rangelength', 'range'], function() {
            if (rules[this]) {
                rules[this] = [Number(rules[this][0]), Number(rules[this][1])];
            }
        });

        if ($.validator.autoCreateRanges) {
            // auto-create ranges
            if (rules.min && rules.max) {
                rules.range = [rules.min, rules.max];
                delete rules.min;
                delete rules.max;
            }
            if (rules.minlength && rules.maxlength) {
                rules.rangelength = [rules.minlength, rules.maxlength];
                delete rules.minlength;
                delete rules.maxlength;
            }
        }

        // To support custom messages in metadata ignore rule methods titled "messages"
        if (rules.messages) {
            delete rules.messages;
        }

        return rules;
    },

    // Converts a simple string to a {string: true} rule, e.g., "required" to {required:true}
    normalizeRule: function(data) {
        if( typeof data == "string" ) {
            var transformed = {};
            $.each(data.split(/\s/), function() {
                transformed[this] = true;
            });
            data = transformed;
        }
        return data;
    },

    // http://docs.jquery.com/Plugins/Validation/Validator/addMethod
    addMethod: function(name, method, message) {
        $.validator.methods[name] = method;
        $.validator.messages[name] = message != undefined ? message : $.validator.messages[name];
        if (method.length < 3) {
            $.validator.addClassRules(name, $.validator.normalizeRule(name));
        }
    },

    methods: {

        // http://docs.jquery.com/Plugins/Validation/Methods/required
        required: function(value, element, param) {
            // check if dependency is met
            if ( !this.depend(param, element) )
                return "dependency-mismatch";
            switch( element.nodeName.toLowerCase() ) {
            case 'select':
                // could be an array for select-multiple or a string, both are fine this way
                var val = $(element).val();
                return val && val.length > 0;
            case 'input':
                if ( this.checkable(element) )
                    return this.getLength(value, element) > 0;
            default:
                return $.trim(value).length > 0;
            }
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/remote
        remote: function(value, element, param) {
            if ( this.optional(element) )
                return "dependency-mismatch";

            var previous = this.previousValue(element);
            if (!this.settings.messages[element.name] )
                this.settings.messages[element.name] = {};
            previous.originalMessage = this.settings.messages[element.name].remote;
            this.settings.messages[element.name].remote = previous.message;

            param = typeof param == "string" && {url:param} || param;

            if ( this.pending[element.name] ) {
                return "pending";
            }
            if ( previous.old === value ) {
                return previous.valid;
            }

            previous.old = value;
            var validator = this;
            this.startRequest(element);
            var data = {};
            data[element.name] = value;
            $.ajax($.extend(true, {
                url: param,
                mode: "abort",
                port: "validate" + element.name,
                dataType: "json",
                data: data,
                success: function(response) {
                    validator.settings.messages[element.name].remote = previous.originalMessage;
                    var valid = response === true;
                    if ( valid ) {
                        var submitted = validator.formSubmitted;
                        validator.prepareElement(element);
                        validator.formSubmitted = submitted;
                        validator.successList.push(element);
                        validator.showErrors();
                    } else {
                        var errors = {};
                        var message = response || validator.defaultMessage( element, "remote" );
                        errors[element.name] = previous.message = $.isFunction(message) ? message(value) : message;
                        validator.showErrors(errors);
                    }
                    previous.valid = valid;
                    validator.stopRequest(element, valid);
                }
            }, param));
            return "pending";
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/minlength
        minlength: function(value, element, param) {
            return this.optional(element) || this.getLength($.trim(value), element) >= param;
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/maxlength
        maxlength: function(value, element, param) {
            return this.optional(element) || this.getLength($.trim(value), element) <= param;
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/rangelength
        rangelength: function(value, element, param) {
            var length = this.getLength($.trim(value), element);
            return this.optional(element) || ( length >= param[0] && length <= param[1] );
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/min
        min: function( value, element, param ) {
            return this.optional(element) || value >= param;
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/max
        max: function( value, element, param ) {
            return this.optional(element) || value <= param;
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/range
        range: function( value, element, param ) {
            return this.optional(element) || ( value >= param[0] && value <= param[1] );
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/email
        email: function(value, element) {
            // contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
            return this.optional(element) || /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/url
        url: function(value, element) {
            // contributed by Scott Gonzalez: http://projects.scottsplayground.com/iri/
            return this.optional(element) || /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value);
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/date
        date: function(value, element) {
            return this.optional(element) || !/Invalid|NaN/.test(new Date(value));
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/dateISO
        dateISO: function(value, element) {
            return this.optional(element) || /^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/.test(value);
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/number
        number: function(value, element) {
            return this.optional(element) || /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value);
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/digits
        digits: function(value, element) {
            return this.optional(element) || /^\d+$/.test(value);
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/creditcard
        // based on http://en.wikipedia.org/wiki/Luhn
        creditcard: function(value, element) {
            if ( this.optional(element) )
                return "dependency-mismatch";
            // accept only digits and dashes
            if (/[^0-9-]+/.test(value))
                return false;
            var nCheck = 0,
                nDigit = 0,
                bEven = false;

            value = value.replace(/\D/g, "");

            for (var n = value.length - 1; n >= 0; n--) {
                var cDigit = value.charAt(n);
                var nDigit = parseInt(cDigit, 10);
                if (bEven) {
                    if ((nDigit *= 2) > 9)
                        nDigit -= 9;
                }
                nCheck += nDigit;
                bEven = !bEven;
            }

            return (nCheck % 10) == 0;
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/accept
        accept: function(value, element, param) {
            param = typeof param == "string" ? param.replace(/,/g, '|') : "png|jpe?g|gif";
            return this.optional(element) || value.match(new RegExp(".(" + param + ")$", "i"));
        },

        // http://docs.jquery.com/Plugins/Validation/Methods/equalTo
        equalTo: function(value, element, param) {
            // bind to the blur event of the target in order to revalidate whenever the target field is updated
            // TODO find a way to bind the event just once, avoiding the unbind-rebind overhead
            var target = $(param).unbind(".validate-equalTo").bind("blur.validate-equalTo", function() {
                $(element).valid();
            });
            return value == target.val();
        }

    }

});

// deprecated, use $.validator.format instead
$.format = $.validator.format;

})(jQuery);

// ajax mode: abort
// usage: $.ajax({ mode: "abort"[, port: "uniqueport"]});
// if mode:"abort" is used, the previous request on that port (port can be undefined) is aborted via XMLHttpRequest.abort()
;(function($) {
    var pendingRequests = {};
    // Use a prefilter if available (1.5+)
    if ( $.ajaxPrefilter ) {
        $.ajaxPrefilter(function(settings, _, xhr) {
            var port = settings.port;
            if (settings.mode == "abort") {
                if ( pendingRequests[port] ) {
                    pendingRequests[port].abort();
                }
                pendingRequests[port] = xhr;
            }
        });
    } else {
        // Proxy ajax
        var ajax = $.ajax;
        $.ajax = function(settings) {
            var mode = ( "mode" in settings ? settings : $.ajaxSettings ).mode,
                port = ( "port" in settings ? settings : $.ajaxSettings ).port;
            if (mode == "abort") {
                if ( pendingRequests[port] ) {
                    pendingRequests[port].abort();
                }
                return (pendingRequests[port] = ajax.apply(this, arguments));
            }
            return ajax.apply(this, arguments);
        };
    }
})(jQuery);

// provides cross-browser focusin and focusout events
// IE has native support, in other browsers, use event caputuring (neither bubbles)

// provides delegate(type: String, delegate: Selector, handler: Callback) plugin for easier event delegation
// handler is only called when $(event.target).is(delegate), in the scope of the jquery-object for event.target
;(function($) {
    // only implement if not provided by jQuery core (since 1.4)
    // TODO verify if jQuery 1.4's implementation is compatible with older jQuery special-event APIs
    if (!jQuery.event.special.focusin && !jQuery.event.special.focusout && document.addEventListener) {
        $.each({
            focus: 'focusin',
            blur: 'focusout'
        }, function( original, fix ){
            $.event.special[fix] = {
                setup:function() {
                    this.addEventListener( original, handler, true );
                },
                teardown:function() {
                    this.removeEventListener( original, handler, true );
                },
                handler: function(e) {
                    arguments[0] = $.event.fix(e);
                    arguments[0].type = fix;
                    return $.event.handle.apply(this, arguments);
                }
            };
            function handler(e) {
                e = $.event.fix(e);
                e.type = fix;
                return $.event.handle.call(this, e);
            }
        });
    };
    $.extend($.fn, {
        validateDelegate: function(delegate, type, handler) {
            return this.bind(type, function(event) {
                var target = $(event.target);
                if (target.is(delegate)) {
                    return handler.apply(target, arguments);
                }
            });
        }
    });
})(jQuery);


/***********************************
    jquery.fancybox-1.3.1.js
***********************************/

/*
 * MODIFIED FOR HELLOFAX USE - iframe handling updates, fancybox_get_viewport updated to deal with ios
 * FancyBox - jQuery Plugin
 * Simple and fancy lightbox alternative
 *
 * Examples and documentation at: http://fancybox.net
 *
 * Copyright (c) 2008 - 2010 Janis Skarnelis
 *
 * Version: 1.3.1 (05/03/2010)
 * Requires: jQuery v1.3+
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 */

(function($) {

    var tmp, loading, overlay, wrap, outer, inner, close, nav_left, nav_right,

        selectedIndex = 0, selectedOpts = {}, selectedArray = [], currentIndex = 0, currentOpts = {}, currentArray = [],

        ajaxLoader = null, imgPreloader = new Image(), imgRegExp = /\.(jpg|gif|png|bmp|jpeg)(.*)?$/i, swfRegExp = /[^\.]\.(swf)\s*$/i,

        loadingTimer, loadingFrame = 1,

        start_pos, final_pos, busy = false, shadow = 20, fx = $.extend($('<div/>')[0], { prop: 0 }), titleh = 0,

        isIE6 = !$.support.opacity && !window.XMLHttpRequest,

        /*
         * Private methods
         */

        fancybox_abort = function() {
            loading.hide();

            imgPreloader.onerror = imgPreloader.onload = null;

            if (ajaxLoader) {
                ajaxLoader.abort();
            }

            tmp.empty();
        },

        fancybox_error = function() {
            $.fancybox('<p id="fancybox_error">The requested content cannot be loaded.<br />Please try again later.</p>', {
                'scrolling'     : 'no',
                'padding'       : 20,
                'transitionIn'  : 'none',
                'transitionOut' : 'none'
            });
        },

        fancybox_get_viewport = function() {

            if ((navigator.userAgent.match(/iPhone/i)) || (navigator.userAgent.match(/iPod/i)) || (navigator.userAgent.match(/iPad/i))) {

                 var viewportwidth;
                 var viewportheight;

                 if (typeof window.innerWidth != 'undefined')
                 {
                      viewportwidth = window.innerWidth,
                      viewportheight = window.innerHeight
                 }

                 else if (typeof document.documentElement != 'undefined'
                     && typeof document.documentElement.clientWidth !=
                     'undefined' && document.documentElement.clientWidth != 0)
                 {
                       viewportwidth = document.documentElement.clientWidth,
                       viewportheight = document.documentElement.clientHeight
                 }

                 else
                 {
                       viewportwidth = document.getElementsByTagName('body')[0].clientWidth,
                       viewportheight = document.getElementsByTagName('body')[0].clientHeight
                 }

                return [ viewportwidth, viewportheight, $(document).scrollLeft(), $(document).scrollTop() ];
            }else{
                return [ $(window).width(), $(window).height(), $(document).scrollLeft(), $(document).scrollTop() ];
            }
        },

        fancybox_get_zoom_to = function () {
            var view    = fancybox_get_viewport(),
                to      = {},

                margin = currentOpts.margin,
                resize = currentOpts.autoScale,

                horizontal_space    = (shadow + margin) * 2,
                vertical_space      = (shadow + margin) * 2,
                double_padding      = (currentOpts.padding * 2),

                ratio;

            if (currentOpts.width.toString().indexOf('%') > -1) {
                to.width = ((view[0] * parseFloat(currentOpts.width)) / 100) - (shadow * 2) ;
                resize = false;

            } else {
                to.width = currentOpts.width + double_padding;
            }

            if (currentOpts.height.toString().indexOf('%') > -1) {
                to.height = ((view[1] * parseFloat(currentOpts.height)) / 100) - (shadow * 2);
                resize = false;

            } else {
                to.height = currentOpts.height + double_padding;
            }

            if (resize && (to.width > (view[0] - horizontal_space) || to.height > (view[1] - vertical_space))) {
                if (selectedOpts.type == 'image' || selectedOpts.type == 'swf') {
                    horizontal_space    += double_padding;
                    vertical_space      += double_padding;

                    ratio = Math.min(Math.min( view[0] - horizontal_space, currentOpts.width) / currentOpts.width, Math.min( view[1] - vertical_space, currentOpts.height) / currentOpts.height);

                    to.width    = Math.round(ratio * (to.width  - double_padding)) + double_padding;
                    to.height   = Math.round(ratio * (to.height - double_padding)) + double_padding;

                } else {
                    to.width    = Math.min(to.width,    (view[0] - horizontal_space));
                    to.height   = Math.min(to.height,   (view[1] - vertical_space));
                }
            }
            to.top  = view[3] + ((view[1] - (to.height  + (shadow * 2 ))) * 0.5);
            to.left = view[2] + ((view[0] - (to.width   + (shadow * 2 ))) * 0.5);

            if (currentOpts.autoScale === false) {
                to.top  = Math.max(view[3] + margin, to.top);
                to.left = Math.max(view[2] + margin, to.left);
            }

            return to;
        },

        fancybox_format_title = function(title) {
            if (title && title.length) {
                switch (currentOpts.titlePosition) {
                    case 'inside':
                        return title;
                    case 'over':
                        return '<span id="fancybox-title-over">' + title + '</span>';
                    default:
                        return '<span id="fancybox-title-wrap"><span id="fancybox-title-left"></span><span id="fancybox-title-main">' + title + '</span><span id="fancybox-title-right"></span></span>';
                }
            }

            return false;
        },

        fancybox_process_title = function() {
            var title   = currentOpts.title,
                width   = final_pos.width - (currentOpts.padding * 2),
                titlec  = 'fancybox-title-' + currentOpts.titlePosition;

            $('#fancybox-title').remove();

            titleh = 0;

            if (currentOpts.titleShow === false) {
                return;
            }

            title = $.isFunction(currentOpts.titleFormat) ? currentOpts.titleFormat(title, currentArray, currentIndex, currentOpts) : fancybox_format_title(title);

            if (!title || title === '') {
                return;
            }

            $('<div id="fancybox-title" class="' + titlec + '" />').css({
                'width'         : width,
                'paddingLeft'   : currentOpts.padding,
                'paddingRight'  : currentOpts.padding
            }).html(title).appendTo('body');

            switch (currentOpts.titlePosition) {
                case 'inside':
                    titleh = $("#fancybox-title").outerHeight(true) - currentOpts.padding;
                    final_pos.height += titleh;
                break;

                case 'over':
                    $('#fancybox-title').css('bottom', currentOpts.padding);
                break;

                default:
                    $('#fancybox-title').css('bottom', $("#fancybox-title").outerHeight(true) * -1);
                break;
            }

            $('#fancybox-title').appendTo( outer ).hide();
        },

        fancybox_set_navigation = function() {
            $(document).unbind('keydown.fb').bind('keydown.fb', function(e) {
                if (e.keyCode == 27 && currentOpts.enableEscapeButton) {
                        e.preventDefault();
                        $.fancybox.close();
                } else if (e.keyCode == 37 && !currentOpts.disableNavButtons) {
                        e.preventDefault();
                        $.fancybox.prev();
                } else if (e.keyCode == 39 && !currentOpts.disableNavButtons) {
                        e.preventDefault();
                        $.fancybox.next();
                }

            });

            if ($.fn.mousewheel) {
                wrap.unbind('mousewheel.fb');

                if (currentArray.length > 1) {
                    wrap.bind('mousewheel.fb', function(e, delta) {
                        e.preventDefault();

                        if (busy || delta === 0) {
                            return;
                        }

                        if (delta > 0) {
                            $.fancybox.prev();
                        } else {
                            $.fancybox.next();
                        }
                    });
                }
            }

            if (!currentOpts.showNavArrows) { return; }

            if ((currentOpts.cyclic && currentArray.length > 1) || currentIndex !== 0) {
                nav_left.show();
            }

            if ((currentOpts.cyclic && currentArray.length > 1) || currentIndex != (currentArray.length -1)) {
                nav_right.show();
            }
        },

        fancybox_preload_images = function() {
            var href,
                objNext;

            if ((currentArray.length -1) > currentIndex) {
                href = currentArray[ currentIndex + 1 ].href;

                if (typeof href !== 'undefined' && href.match(imgRegExp)) {
                    objNext = new Image();
                    objNext.src = href;
                }
            }

            if (currentIndex > 0) {
                href = currentArray[ currentIndex - 1 ].href;

                if (typeof href !== 'undefined' && href.match(imgRegExp)) {
                    objNext = new Image();
                    objNext.src = href;
                }
            }
        },

        _finish = function () {
            inner.css('overflow', (currentOpts.scrolling == 'auto' ? (currentOpts.type == 'image' || currentOpts.type == 'iframe' || currentOpts.type == 'swf' ? 'hidden' : 'auto') : (currentOpts.scrolling == 'yes' ? 'auto' : 'visible')));

            if (!$.support.opacity) {
                inner.get(0).style.removeAttribute('filter');
                wrap.get(0).style.removeAttribute('filter');
            }

            $('#fancybox-title').show();

            if (currentOpts.hideOnContentClick) {
                inner.one('click', $.fancybox.close);
            }
            if (currentOpts.hideOnOverlayClick) {
                overlay.one('click', $.fancybox.close);
            }

            if (currentOpts.showCloseButton) {
                close.show();
            }

            fancybox_set_navigation();

            $(window).bind("resize.fb", $.fancybox.center);

            if (currentOpts.centerOnScroll) {
                $(window).bind("scroll.fb", $.fancybox.center);
            } else {
                $(window).unbind("scroll.fb");
            }

            if ($.isFunction(currentOpts.onComplete)) {
                currentOpts.onComplete(currentArray, currentIndex, currentOpts);
            }

            busy = false;

            fancybox_preload_images();
        },

        fancybox_draw = function(pos) {
            var width   = Math.round(start_pos.width    + (final_pos.width  - start_pos.width)  * pos),
                height  = Math.round(start_pos.height   + (final_pos.height - start_pos.height) * pos),

                top     = Math.round(start_pos.top  + (final_pos.top    - start_pos.top)    * pos),
                left    = Math.round(start_pos.left + (final_pos.left   - start_pos.left)   * pos);

            wrap.css({
                'width'     : width     + 'px',
                'height'    : height    + 'px',
                'top'       : top       + 'px',
                'left'      : left      + 'px'
            });

            width   = Math.max(width - currentOpts.padding * 2, 0);
            height  = Math.max(height - (currentOpts.padding * 2 + (titleh * pos)), 0);

            inner.css({
                'width'     : width     + 'px',
                'height'    : height    + 'px'
            });

            if (typeof final_pos.opacity !== 'undefined') {
                wrap.css('opacity', (pos < 0.5 ? 0.5 : pos));
            }
        },

        fancybox_get_obj_pos = function(obj) {
            var pos     = obj.offset();

            pos.top     += parseFloat( obj.css('paddingTop') )  || 0;
            pos.left    += parseFloat( obj.css('paddingLeft') ) || 0;

            pos.top     += parseFloat( obj.css('border-top-width') )    || 0;
            pos.left    += parseFloat( obj.css('border-left-width') )   || 0;

            pos.width   = obj.width();
            pos.height  = obj.height();

            return pos;
        },

        fancybox_get_zoom_from = function() {
            var orig = selectedOpts.orig ? $(selectedOpts.orig) : false,
                from = {},
                pos,
                view;

            if (orig && orig.length) {
                pos = fancybox_get_obj_pos(orig);

                from = {
                    width   : (pos.width    + (currentOpts.padding * 2)),
                    height  : (pos.height   + (currentOpts.padding * 2)),
                    top     : (pos.top      - currentOpts.padding - shadow),
                    left    : (pos.left     - currentOpts.padding - shadow)
                };

            } else {
                view = fancybox_get_viewport();

                from = {
                    width   : 1,
                    height  : 1,
                    top     : view[3] + view[1] * 0.5,
                    left    : view[2] + view[0] * 0.5
                };
            }

            return from;
        },

        fancybox_show = function(isIframe) {
            loading.hide();

            if (wrap.is(":visible") && $.isFunction(currentOpts.onCleanup)) {
                if (currentOpts.onCleanup(currentArray, currentIndex, currentOpts) === false) {
                    $.event.trigger('fancybox-cancel');

                    busy = false;
                    return;
                }
            }

            currentArray    = selectedArray;
            currentIndex    = selectedIndex;
            currentOpts     = selectedOpts;

            inner.get(0).scrollTop  = 0;
            inner.get(0).scrollLeft = 0;

            if (currentOpts.overlayShow) {
                if (isIE6) {
                    $('select:not(#fancybox-tmp select)').filter(function() {
                        return this.style.visibility !== 'hidden';
                    }).css({'visibility':'hidden'}).one('fancybox-cleanup', function() {
                        this.style.visibility = 'inherit';
                    });
                }

                overlay.css({
                    'background-color'  : currentOpts.overlayColor,
                    'opacity'           : currentOpts.overlayOpacity
                }).unbind().show();
            }

            final_pos = fancybox_get_zoom_to();

            fancybox_process_title();

            if (wrap.is(":visible")) {
                $( close.add( nav_left ).add( nav_right ) ).hide();

                var pos = wrap.position(),
                    equal;

                start_pos = {
                    top     :   pos.top ,
                    left    :   pos.left,
                    width   :   wrap.width(),
                    height  :   wrap.height()
                };

                equal = (start_pos.width == final_pos.width && start_pos.height == final_pos.height);

                inner.fadeOut(currentOpts.changeFade, function() {
                    var finish_resizing = function() {
                        inner.html(isIframe === true ? '<iframe id="fancybox-frame" name="fancybox-frame' + new Date().getTime() + '" frameborder="0" hspace="0" scrolling="' + selectedOpts.scrolling + '" src="' + selectedOpts.href + '"></iframe>' : tmp.contents())
                        .fadeIn(currentOpts.changeFade, _finish);
                    };

                    $.event.trigger('fancybox-change');

                    inner.empty().css('overflow', 'hidden');

                    if (equal) {
                        inner.css({
                            top         : currentOpts.padding,
                            left        : currentOpts.padding,
                            width       : Math.max(final_pos.width  - (currentOpts.padding * 2), 1),
                            height      : Math.max(final_pos.height - (currentOpts.padding * 2) - titleh, 1)
                        });

                        finish_resizing();

                    } else {
                        inner.css({
                            top         : currentOpts.padding,
                            left        : currentOpts.padding,
                            width       : Math.max(start_pos.width  - (currentOpts.padding * 2), 1),
                            height      : Math.max(start_pos.height - (currentOpts.padding * 2), 1)
                        });

                        fx.prop = 0;

                        $(fx).animate({ prop: 1 }, {
                             duration   : currentOpts.changeSpeed,
                             easing     : currentOpts.easingChange,
                             step       : fancybox_draw,
                             complete   : finish_resizing
                        });
                    }
                });

                return;
            }

            wrap.css('opacity', 1);

            if (currentOpts.transitionIn == 'elastic') {
                start_pos = fancybox_get_zoom_from();

                inner.css({
                        top         : currentOpts.padding,
                        left        : currentOpts.padding,
                        width       : Math.max(start_pos.width  - (currentOpts.padding * 2), 1),
                        height      : Math.max(start_pos.height - (currentOpts.padding * 2), 1)
                    })
                    .html(isIframe === true ? '<iframe id="fancybox-frame" name="fancybox-frame' + new Date().getTime() + '" frameborder="0" hspace="0" scrolling="' + selectedOpts.scrolling + '" src="' + selectedOpts.href + '"></iframe>' : tmp.contents());

                wrap.css(start_pos).show();

                if (currentOpts.opacity) {
                    final_pos.opacity = 0;
                }

                fx.prop = 0;

                $(fx).animate({ prop: 1 }, {
                     duration   : currentOpts.speedIn,
                     easing     : currentOpts.easingIn,
                     step       : fancybox_draw,
                     complete   : _finish
                });

            } else {
                inner.css({
                        top         : currentOpts.padding,
                        left        : currentOpts.padding,
                        width       : Math.max(final_pos.width  - (currentOpts.padding * 2), 1),
                        height      : Math.max(final_pos.height - (currentOpts.padding * 2) - titleh, 1)
                    })
                    .html(isIframe === true ? '<iframe id="fancybox-frame" name="fancybox-frame' + new Date().getTime() + '" frameborder="0" hspace="0" scrolling="' + selectedOpts.scrolling + '" src="' + selectedOpts.href + '"></iframe>' : tmp.contents());


                wrap.css( final_pos ).fadeIn( currentOpts.transitionIn == 'none' ? 0 : currentOpts.speedIn, _finish );
            }
        },

        fancybox_process_inline = function() {
            tmp.width(  selectedOpts.width );
            tmp.height( selectedOpts.height );

            if (selectedOpts.width  == 'auto') {
                selectedOpts.width = tmp.width();
            }
            if (selectedOpts.height == 'auto') {
                selectedOpts.height = tmp.height();
            }

            fancybox_show();
        },

        fancybox_process_image = function() {
            busy = true;

            selectedOpts.width  = imgPreloader.width;
            selectedOpts.height = imgPreloader.height;

            $("<img />").attr({
                'id'    : 'fancybox-img',
                'src'   : imgPreloader.src,
                'alt'   : selectedOpts.title
            }).appendTo( tmp );

            fancybox_show();
        },

        fancybox_start = function() {
            fancybox_abort();

            var obj = selectedArray[ selectedIndex ],
                href,
                type,
                title,
                str,
                emb,
                selector,
                data;

            selectedOpts = $.extend({}, $.fn.fancybox.defaults, (typeof $(obj).data('fancybox') == 'undefined' ? selectedOpts : $(obj).data('fancybox')));
            title = obj.title || $(obj).title || selectedOpts.title || '';

            if (obj.nodeName && !selectedOpts.orig) {
                selectedOpts.orig = $(obj).children("img:first").length ? $(obj).children("img:first") : $(obj);
            }

            if (title === '' && selectedOpts.orig) {
                title = selectedOpts.orig.attr('alt');
            }

            if (obj.nodeName && (/^(?:javascript|#)/i).test(obj.href)) {
                href = selectedOpts.href || null;
            } else {
                href = selectedOpts.href || obj.href || null;
            }

            if (selectedOpts.type) {
                type = selectedOpts.type;

                if (!href) {
                    href = selectedOpts.content;
                }

            } else if (selectedOpts.content) {
                type    = 'html';

            } else if (href) {
                if (href.match(imgRegExp)) {
                    type = 'image';

                } else if (href.match(swfRegExp)) {
                    type = 'swf';

                } else if ($(obj).hasClass("iframe")) {
                    type = 'iframe';

                } else if (href.match(/#/)) {
                    obj = href.substr(href.indexOf("#"));

                    type = $(obj).length > 0 ? 'inline' : 'ajax';
                } else {
                    type = 'ajax';
                }
            } else {
                type = 'inline';
            }

            selectedOpts.type   = type;
            selectedOpts.href   = href;
            selectedOpts.title  = title;

            if (selectedOpts.autoDimensions && selectedOpts.type !== 'iframe' && selectedOpts.type !== 'swf') {
                selectedOpts.width      = 'auto';
                selectedOpts.height     = 'auto';
            }

            if (selectedOpts.modal) {
                selectedOpts.overlayShow        = true;
                selectedOpts.hideOnOverlayClick = false;
                selectedOpts.hideOnContentClick = false;
                selectedOpts.enableEscapeButton = false;
                selectedOpts.showCloseButton    = false;
            }

            if ($.isFunction(selectedOpts.onStart)) {
                if (selectedOpts.onStart(selectedArray, selectedIndex, selectedOpts) === false) {
                    busy = false;
                    return;
                }
            }

            tmp.css('padding', (shadow + selectedOpts.padding + selectedOpts.margin));

            $('.fancybox-inline-tmp').unbind('fancybox-cancel').bind('fancybox-change', function() {
                $(this).replaceWith(inner.children());
            });

            switch (type) {
                case 'html' :
                    tmp.html( selectedOpts.content );
                    fancybox_process_inline();
                break;

                case 'inline' :
                    $('<div class="fancybox-inline-tmp" />').hide().insertBefore( $(obj) ).bind('fancybox-cleanup', function() {
                        $(this).replaceWith(inner.children());
                    }).bind('fancybox-cancel', function() {
                        $(this).replaceWith(tmp.children());
                    });

                    $(obj).appendTo(tmp);

                    fancybox_process_inline();
                break;

                case 'image':
                    busy = false;

                    $.fancybox.showActivity();

                    imgPreloader = new Image();

                    imgPreloader.onerror = function() {
                        fancybox_error();
                    };

                    imgPreloader.onload = function() {
                        imgPreloader.onerror = null;
                        imgPreloader.onload = null;
                        fancybox_process_image();
                    };

                    imgPreloader.src = href;

                break;

                case 'swf':
                    str = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="' + selectedOpts.width + '" height="' + selectedOpts.height + '"><param name="movie" value="' + href + '"></param>';
                    emb = '';

                    $.each(selectedOpts.swf, function(name, val) {
                        str += '<param name="' + name + '" value="' + val + '"></param>';
                        emb += ' ' + name + '="' + val + '"';
                    });

                    str += '<embed src="' + href + '" type="application/x-shockwave-flash" width="' + selectedOpts.width + '" height="' + selectedOpts.height + '"' + emb + '></embed></object>';

                    tmp.html(str);

                    fancybox_process_inline();
                break;

                case 'ajax':
                    selector    = href.split('#', 2);
                    data        = selectedOpts.ajax.data || {};

                    if (selector.length > 1) {
                        href = selector[0];

                        if (typeof data == "string") {
                            data += '&selector=' + selector[1];
                        } else {
                            data.selector = selector[1];
                        }
                    }

                    busy = false;
                    $.fancybox.showActivity();

                    ajaxLoader = $.ajax($.extend(selectedOpts.ajax, {
                        url     : href,
                        data    : data,
                        error   : fancybox_error,
                        success : function(data, textStatus, XMLHttpRequest) {
                            if (ajaxLoader.status == 200) {
                                tmp.html( data );
                                fancybox_process_inline();
                            }
                        }
                    }));

                break;

                case 'iframe' :
                    fancybox_show(true);
                break;
            }
        },

        fancybox_animate_loading = function() {
            if (!loading.is(':visible')){
                clearInterval(loadingTimer);
                return;
            }

            $('div', loading).css('top', (loadingFrame * -40) + 'px');

            loadingFrame = (loadingFrame + 1) % 12;
        },

        fancybox_init = function() {
            if ($("#fancybox-wrap").length) {
                return;
            }

            $('body').append(
                tmp         = $('<div id="fancybox-tmp"></div>'),
                loading     = $('<div id="fancybox-loading"><div></div></div>'),
                overlay     = $('<div id="fancybox-overlay"></div>'),
                wrap        = $('<div id="fancybox-wrap"></div>')
            );

            if (!$.support.opacity) {
                wrap.addClass('fancybox-ie');
                loading.addClass('fancybox-ie');
            }

            outer = $('<div id="fancybox-outer"></div>')
                .append('<div class="fancy-bg" id="fancy-bg-n"></div><div class="fancy-bg" id="fancy-bg-ne"></div><div class="fancy-bg" id="fancy-bg-e"></div><div class="fancy-bg" id="fancy-bg-se"></div><div class="fancy-bg" id="fancy-bg-s"></div><div class="fancy-bg" id="fancy-bg-sw"></div><div class="fancy-bg" id="fancy-bg-w"></div><div class="fancy-bg" id="fancy-bg-nw"></div>')
                .appendTo( wrap );

            outer.append(
                inner       = $('<div id="fancybox-inner"></div>'),
                close       = $('<a id="fancybox-close"></a>'),

                nav_left    = $('<a href="javascript:;" id="fancybox-left"><span class="fancy-ico" id="fancybox-left-ico"></span></a>'),
                nav_right   = $('<a href="javascript:;" id="fancybox-right"><span class="fancy-ico" id="fancybox-right-ico"></span></a>')
            );

            close.click($.fancybox.close);
            loading.click($.fancybox.cancel);

            nav_left.click(function(e) {
                e.preventDefault();
                $.fancybox.prev();
            });


            nav_right.click(function(e) {
                e.preventDefault();
                $.fancybox.next();
            });

            if (isIE6) {
                overlay.get(0).style.setExpression('height',    "document.body.scrollHeight > document.body.offsetHeight ? document.body.scrollHeight : document.body.offsetHeight + 'px'");
                loading.get(0).style.setExpression('top',       "(-20 + (document.documentElement.clientHeight ? document.documentElement.clientHeight/2 : document.body.clientHeight/2 ) + ( ignoreMe = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop )) + 'px'");

                outer.prepend('<iframe id="fancybox-hide-sel-frame" src="javascript:\'\';" scrolling="no" frameborder="0" ></iframe>');
            }
        };

    /*
     * Public methods
     */

    $.fn.fancybox = function(options) {
        $(this)
            .data('fancybox', $.extend({}, options, ($.metadata ? $(this).metadata() : {})))
            .unbind('click.fb').bind('click.fb', function(e) {
                e.preventDefault();

                if (busy) {
                    return;
                }

                busy = true;

                $(this).blur();

                selectedArray   = [];
                selectedIndex   = 0;

                var rel = $(this).attr('rel') || '';

                if (!rel || rel == '' || rel === 'nofollow') {
                    selectedArray.push(this);

                } else {
                    selectedArray   = $("a[rel=" + rel + "], area[rel=" + rel + "]");
                    selectedIndex   = selectedArray.index( this );
                }

                fancybox_start();

                return false;
            });

        return this;
    };

    $.fancybox = function(obj) {
        if (busy) {
            return;
        }

        busy = true;

        var opts = typeof arguments[1] !== 'undefined' ? arguments[1] : {};

        selectedArray   = [];
        selectedIndex   = opts.index || 0;

        if ($.isArray(obj)) {
            for (var i = 0, j = obj.length; i < j; i++) {
                if (typeof obj[i] == 'object') {
                    $(obj[i]).data('fancybox', $.extend({}, opts, obj[i]));
                } else {
                    obj[i] = $({}).data('fancybox', $.extend({content : obj[i]}, opts));
                }
            }

            selectedArray = jQuery.merge(selectedArray, obj);

        } else {
            if (typeof obj == 'object') {
                $(obj).data('fancybox', $.extend({}, opts, obj));
            } else {
                obj = $({}).data('fancybox', $.extend({content : obj}, opts));
            }

            selectedArray.push(obj);
        }

        if (selectedIndex > selectedArray.length || selectedIndex < 0) {
            selectedIndex = 0;
        }

        fancybox_start();
    };

    $.fancybox.showActivity = function() {
        clearInterval(loadingTimer);

        loading.show();
        loadingTimer = setInterval(fancybox_animate_loading, 66);
    };

    $.fancybox.hideActivity = function() {
        loading.hide();
    };

    $.fancybox.next = function() {
        return $.fancybox.pos( currentIndex + 1);
    };

    $.fancybox.prev = function() {
        return $.fancybox.pos( currentIndex - 1);
    };

    $.fancybox.pos = function(pos) {
        if (busy) {
            return;
        }

        pos = parseInt(pos, 10);

        if (pos > -1 && currentArray.length > pos) {
            selectedIndex = pos;
            fancybox_start();
        }

        if (currentOpts.cyclic && currentArray.length > 1 && pos < 0) {
            selectedIndex = currentArray.length - 1;
            fancybox_start();
        }

        if (currentOpts.cyclic && currentArray.length > 1 && pos >= currentArray.length) {
            selectedIndex = 0;
            fancybox_start();
        }

        return;
    };

    $.fancybox.cancel = function() {
        if (busy) {
            return;
        }

        busy = true;

        $.event.trigger('fancybox-cancel');

        fancybox_abort();

        if (selectedOpts && $.isFunction(selectedOpts.onCancel)) {
            selectedOpts.onCancel(selectedArray, selectedIndex, selectedOpts);
        }

        busy = false;
    };

    // Note: within an iframe use - parent.$.fancybox.close();
    $.fancybox.close = function() {
        if (busy || wrap.is(':hidden')) {
            return;
        }

        busy = true;

        if (currentOpts && $.isFunction(currentOpts.onCleanup)) {
            if (currentOpts.onCleanup(currentArray, currentIndex, currentOpts) === false) {
                busy = false;
                return;
            }
        }

        fancybox_abort();

        $(close.add( nav_left ).add( nav_right )).hide();

        $('#fancybox-title').remove();

        wrap.add(inner).add(overlay).unbind();

        $(window).unbind("resize.fb scroll.fb");
        $(document).unbind('keydown.fb');

        function _cleanup() {
            overlay.fadeOut('fast');

            wrap.hide();

            $.event.trigger('fancybox-cleanup');

            inner.empty();

            if ($.isFunction(currentOpts.onClosed)) {
                currentOpts.onClosed(currentArray, currentIndex, currentOpts);
            }

            currentArray    = selectedOpts  = [];
            currentIndex    = selectedIndex = 0;
            currentOpts     = selectedOpts  = {};

            busy = false;
        }

        inner.css('overflow', 'hidden');

        if (currentOpts.transitionOut == 'elastic') {
            start_pos = fancybox_get_zoom_from();

            var pos = wrap.position();

            final_pos = {
                top     :   pos.top ,
                left    :   pos.left,
                width   :   wrap.width(),
                height  :   wrap.height()
            };

            if (currentOpts.opacity) {
                final_pos.opacity = 1;
            }

            fx.prop = 1;

            $(fx).animate({ prop: 0 }, {
                 duration   : currentOpts.speedOut,
                 easing     : currentOpts.easingOut,
                 step       : fancybox_draw,
                 complete   : _cleanup
            });

        } else {
            wrap.fadeOut( currentOpts.transitionOut == 'none' ? 0 : currentOpts.speedOut, _cleanup);
        }
    };

    $.fancybox.resize = function() {
        var c, h;

        if (busy || wrap.is(':hidden')) {
            return;
        }

        busy = true;
        c = inner.wrapInner("<div style='overflow:auto'></div>").children();
        h = c.height();

        wrap.css({height:   h + (currentOpts.padding * 2) + titleh});
        inner.css({height:  h});

        c.replaceWith(c.children());

        $.fancybox.center();
    };

    $.fancybox.center = function() {
        busy = true;

        var view    = fancybox_get_viewport(),
            margin  = currentOpts.margin,
            to      = {};

        to.top  = view[3] + ((view[1] - ((wrap.height() - titleh) + (shadow * 2 ))) * 0.5);
        to.left = view[2] + ((view[0] - (wrap.width() + (shadow * 2 ))) * 0.5);

        to.top  = Math.max(view[3] + margin, to.top);
        to.left = Math.max(view[2] + margin, to.left);

        wrap.css(to);

        busy = false;
    };

    $.fn.fancybox.defaults = {
        padding             :   10,
        margin              :   20,
        opacity             :   false,
        modal               :   false,
        cyclic              :   false,
        scrolling           :   'auto', // 'auto', 'yes' or 'no'

        width               :   560,
        height              :   340,

        autoScale           :   true,
        autoDimensions      :   true,
        centerOnScroll      :   false,

        ajax                :   {},
        swf                 :   { wmode: 'transparent' },

        hideOnOverlayClick  :   true,
        hideOnContentClick  :   false,

        overlayShow         :   true,
        overlayOpacity      :   0.3,
        overlayColor        :   '#666',

        titleShow           :   true,
        titlePosition       :   'outside',  // 'outside', 'inside' or 'over'
        titleFormat         :   null,

        transitionIn        :   'fade', // 'elastic', 'fade' or 'none'
        transitionOut       :   'fade', // 'elastic', 'fade' or 'none'

        speedIn             :   300,
        speedOut            :   300,

        changeSpeed         :   300,
        changeFade          :   'fast',

        easingIn            :   'swing',
        easingOut           :   'swing',

        showCloseButton     :   true,
        showNavArrows       :   true,
        enableEscapeButton  :   true,

        onStart             :   null,
        onCancel            :   null,
        onComplete          :   null,
        onCleanup           :   null,
        onClosed            :   null,

        disableNavButtons   :   true
    };

    $(document).ready(function() {
        fancybox_init();
    });

})(jQuery);

/***********************************
    jquery.customtabs.js
***********************************/

(function($) {
    $.extend($.ui.tabs.prototype, {
        _load25624: $.ui.tabs.prototype.load,
        itemOptions: [],
        load: function(index) {
            index = this._getIndex(index);

            var o = this.options,
                a = this.anchors.eq(index)[0];

            try {
                if(o.itemOptions[index].cache === false) {
                    $.data(a, "cache.tabs", false);
                    $.data(a, "hellofax.isCached", false);
                }
            }
            catch(e) { }

            return this._load25624(index);
        }
    });
})(jQuery);


/***********************************
    jquery.hellofax.js
***********************************/

var hellofaxJS = {

    SITE_CODE_HELLOFAX: 'F',
    SITE_CODE_HELLOSIGN: 'S',
    BRAND_NAMES: {
        'F': 'HelloFax',
        'S': 'HelloSign'
    },

    getSiteCode: function() {
        return hellofaxJS.getUserData().site_code;
    },

    getBrandName: function(site_code) {
        return hellofaxJS.BRAND_NAMES[site_code || hellofaxJS.getSiteCode()];
    },

    isHelloFax: function() {
        return hellofaxJS.getSiteCode() === hellofaxJS.SITE_CODE_HELLOFAX;
    },

    isHelloSign: function() {
        return hellofaxJS.getSiteCode() === hellofaxJS.SITE_CODE_HELLOSIGN;
    },

    isMobile: function() {
        //http://www.abeautifulsite.net/blog/2011/11/detecting-mobile-devices-with-javascript/
        var isMobile = {
            Android: function() { return navigator.userAgent.match(/Android/i); },
            BlackBerry: function() { return navigator.userAgent.match(/BlackBerry/i); },
            iOS: function() { return navigator.userAgent.match(/iPhone|iPad|iPod/i); },
            Opera: function() { return navigator.userAgent.match(/Opera Mini/i); },
            Windows: function() { return navigator.userAgent.match(/IEMobile/i); },
            any: function() { return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows()); }
        };

        return isMobile.any();
    },



    lastAjaxTimestamp : 0,

    processedNotifications: [],

    // Useful to get out of FB frames in a smooth way
    goToUrl: function(url) {
        $.fancybox.close();
        document.location = url;
    },

    topWindowInDomain: function() {
        try {
            currentWindow = window;
            while (currentWindow != window.parent) {
                if (window.parent.document) {
                    currentWindow = window.parent;
                }
                else {
                    // WebKit will end up here
                    break;
                }
            }
        }
        catch (e) {
            // most likely a "Permission denied" error due to being loaded in the Box.net iframe
        }

        return currentWindow;
    },

    wasUserAborted: function(xhr) {
        return !xhr.getAllResponseHeaders();
    },

    resizeFancyBoxPopup: function(dimensions) {
        var h = dimensions['height'];
        var w = dimensions['width'];
        $('#fancybox-wrap').css({
            height: h ? h + 20 : undefined,
            width: w
        });
        $('#fancybox-inner').css({
            height: h,
            width: w ? w - 10 : undefined
        });
    },

    openFancyBoxPopup: function(url, options) {
        var params = {
            'type'                  : 'iframe',
            'transitionIn'          : 'fade',
            'transitionOut'         : 'fade',
            'enableEscapeButton'    : true,
            'hideOnOverlayClick'    : true,
            'showCloseButton'       : true,
            'height'                : 370,
            'scrolling'             : 'yes',
            'href'                  : url
        };
        if (options) {
            for (var k in options) {
                params[k] = options[k];
            }
        }
        if (params.autoDimensions === true) {
            params['height'] = undefined;
            params['width'] = undefined;
        }
        $.fancybox(params);
    },

    setUserData: function (user_data) {
        var defaults = {
            is_logged_in: false,
            has_remember_me: false,
            email_address: null
        };

        user_data = $.extend(defaults, user_data);
        //NOTE: Need to use top-most in domain window's jQuery object because that's where the caching is done
        hellofaxJS.topWindowInDomain().$.data(hellofaxJS.topWindowInDomain(), 'HFUser', user_data);
    },

    getUserData: function(key) {
        var data = hellofaxJS.topWindowInDomain().$.data(hellofaxJS.topWindowInDomain(), 'HFUser');
        return key !== undefined ? data[key] : data;
    },

    setLastAjaxTimestamp: function(ts) {
        this.lastAjaxTimestamp = ts;
    },

    //checks for notifications to display onpageload in the cookies, displays them and removes them
    processQueuedNotifications: function() {
        var cookie = document.cookie.toString();
        var regex = /(notification\[\d+\])=([\w=\+\/]*)/g;
        var result = regex.exec(cookie);

        if (result) {
            //remove cookie
            document.cookie = result[1] + '=; expires=Thu, 01-Jan-70 00:00:01 GMT; path=/; domain=' + document.domain + ";";
            if ($.inArray(result[2], this.processedNotifications) === -1) { // to prevent displaying the same exact notification twice

                this.processedNotifications.push(result[2]);

                //decode notification
                var data = Base64.decode(result[2]);
                data = JSON.parse(data);

                if (data.class_name && data.class_name == "fancybox") {
                    hellofaxJS.createModalNotification(data);
                }
                else {
                    hellofaxJS.createNotification(data);
                }
            }
        }
        else {
            this.processedNotifications = [];
        }

    },

    //front end replacement for setPopupConfirmationMessage.  Displays a notification dropping down from the top
    createNotification: function(options) {
        //merge defaults and options
        var settings = $.extend({
            class_name: 'info', //info|error
            box_id: 'notification_' + Math.round(Math.random() * 99999),
            icon: '',
            header: '',
            text: '',
            display_time: 5500 //0 for persistent
        }, options);

        //get notification template
        var notification_html = $("#notification_template").html();

        //replace template variables
        for (var i in settings) {
            notification_html = notification_html.replace(new RegExp("{{" + i + "}}", 'g'), settings[i]);
        }

        //convert to html
        var notification = $(notification_html);

        //remove icon from template if not provided
        if (settings.icon == '') {
            notification.find('img.icon').remove();
        }

        //add click handler to close function
        notification.find('.close').click(function(e) {
            e.preventDefault();
            hellofaxJS.removeNotification($(this).parents('.notification'));
            return false;
        });

        //add to body and animate in
        notification.appendTo("body");
        notification.animate({
            'top': '0px'
        }, 800);

        //remove after specified time
        if (settings.display_time) {
            setTimeout(hellofaxJS.removeNotification, settings.display_time);
        }
    },

    createModalNotification: function(options) {
        if (options.text) {
            options.content = options.text;
            delete options.text;
        }
        var settings = $.extend({
            content: '',
            autoDimensions: true,
            display_time: 0, //0 for persistent, otherwise time in milliseconds
            height: 400,
            width: 400,
            enableEscapeButton: true,
            hideOnOverlayClick: false,
            showCloseButton: true,
            onComplete: null,
            onClosed: null
        }, options);
        //get notification template
        var notification_html = $("#modal_notification_template").html();

        //replace template variables
        for (var i in settings) {
            notification_html = notification_html.replace(new RegExp("{{" + i + "}}", 'g'), settings[i]);
        }

        //convert to html
        var notification = $(notification_html);

        var fancy_options = {
            content: notification.html(),
            autoDimensions: settings.autoDimensions,
            height: settings.height,
            width: settings.width,
            enableEscapeButton: settings.enableEscapeButton,
            onComplete: settings.onComplete,
            onClosed: settings.onClosed,
            showCloseButton: settings.showCloseButton,
            hideOnOverlayClick: settings.hideOnOverlayClick
        };

        //if there's a url provided don't pass content
        if (settings.href) {
            fancy_options.href = settings.href;
            fancy_options.type = 'iframe';
            delete fancy_options.content;
        }

        var wrap = $('#fancybox-wrap');
        var overlay = $('#fancybox-overlay');
        if (overlay.is(':visible') && (!wrap.is(':visible') || wrap.css('opacity') < 1))  {

            // Wait for the previous modal to have faded out
            var hasFadedOut = function() {
                return ((wrap.css('opacity') === 0 || !wrap.is(':visible')) && (overlay.css('opacity') === 0 || !overlay.is(':visible')));
            };
            var check = function() {
                if (!hasFadedOut()) {
                    // Check again in a little while
                    setTimeout(check, 50);
                }
                else {
                    // show the modal
                    $.fancybox(fancy_options);
                }
            };
            check();

        }
        else {

            //show modal without waiting
            $.fancybox(fancy_options);

        }

        //remove after specified time
        if (settings.display_time) {
            setTimeout(hellofaxJS.removeModalNotification, settings.display_time);
        }

        if (settings.hideOverflow) {
            setTimeout(function() {
                $("#fancybox-inner").css('overflow', 'hidden');
            }, 500);
        }
    },

    removeModalNotification: function() {
        $.fancybox.close();
    },

    removeNotification: function(elem) {
        if (!elem) {
            elem = $(".notification");
        }

        var animate_options = {};

        if(!$(elem).hasClass('notification-error')) {
            animate_options['top'] = '-100px';
        } else {
            animate_options = {
                height: '0px',
                opacity: '.1'
            };
        }

        $(elem).animate(animate_options, 800, function() {
            $(this).parent().remove();

            //show next notification in line if necessary
            hellofaxJS.processQueuedNotifications();
        });
    },

    // hellofaxJS.enableToolTips('.details', {top: -20, left: 25});
    enableToolTips: function(selector, offset) {
        selector = selector || ".hf-tooltip";
        offset = offset || { top: -20, left: 20};
        var infoBubble = $("#infoBubble");

        if (infoBubble.length === 0) {
            infoBubble = $(
                "<div id='infoBubble'>" +
                    "<span class='arrow'></span>" +
                    "<span class='content'></span>" +
                "</div>"
            ).appendTo('body');
        }

        $(selector)
            .mouseenter(function(e){
                var el = $(this);
                var descr = $(this).attr('data');
                var pos = {
                    top: el.offset().top,
                    left: el.offset().left
                };
                var content = $('.content', infoBubble);

                content.html(descr);

                // Render out of screen
                infoBubble
                    .css('top', '-10000px')
                    .show();

                // Adjust position
                infoBubble
                    .css({
                        'top': (pos.top + offset.top) + 'px',
                        'left': (pos.left + el.width() + offset.left) + 'px'
                    });
            })
            .mouseleave(function(e){
                infoBubble
                    .removeClass('large')
                    .hide();
            });
    },

    //url parameter of force_password_change=1 will make the user set a password in a fancybox.  This is called from _footer
    processForcePasswordChange: function() {
        if (window.location.href.indexOf('force_password_change') == -1) {
            return;
        }

        setTimeout(function() {
            //if there's a fancybox open, force refresh when this is complete
            if ($("#fancybox-outer iframe").length > 0) {
                $.fancybox.close = function() {
                    window.location = window.location.href.replace(/(&)?force_password_change=1/, "");
                };
            }

            hellofaxJS.openFancyBoxPopup('/onboarding/setPassword?nopre=1&is_iframe=1', { modal: true });
        }, 500);
    },

    isValidPhoneInternational: function(phone_number){
        var options = hellofaxJS.getFormattedPhoneOptions(phone_number);
        return options.length > 0 ? true : false;
    },

    isValidEmailAddress: function(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@\"]+(\.[^<>()\[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    },

    isIE7: function() {
        return (document.all && !window.opera && window.XMLHttpRequest && navigator.userAgent.toString().toLowerCase().indexOf('Trident/4.0') == -1) ? true : false;
    },

    sanitizeInputEmailAddress: function(emailInput, callback) {
        var input = $(emailInput);
        // Set timeout so it executes on the new value instead of the pre-paste value
        setTimeout(function() {
            if (input && input.length > 0) {
                var sanitized = $.trim(input.val().replace(/['"]+/g, ''));
                input.val(sanitized);
                if(callback != null) {
                    callback(sanitized);
                }
            }
        }, 1);
    },

    trackEvent: function(event) {
        hellofaxJS.trackGoogleAnalyticsEvent(event);
        hellofaxJS.trackKissMetricsEvent(event);
        hellofaxJS.trackOptimizelyEvent(event);
    },

    trackOptimizelyEvent: function(event,properties) {
        window.optimizely = window.optimizely || [];
        window.optimizely.push(["trackEvent", event,properties]);
    },

    trackGoogleAnalyticsEvent: function(event) {
        window._gaq = window._gaq || [];
        window._gaq.push(['_trackEvent', 'HelloFax.com', event]);
    },

    trackKissMetricsEvent: function(event, properties) {
        window._kmq = window._kmq || [];

        var km_properties = new Array();
        for(var key in properties) {
            km_properties[event + '-' + key] = properties[key];
        }

        km_properties[event + '-site_code'] = hellofaxJS.getSiteCode();

        window._kmq.push(['record', event, km_properties]);
    },

    getFormattedPhoneOptions: function(number) {

        //init phoneUtil if necessary
        if (!hellofaxJS.phoneUtil) {
            hellofaxJS.phoneUtil = i18n.phonenumbers.PhoneNumberUtil.getInstance();
        }
        return hellofaxJS.phoneUtil.getFormattedPhoneOptions(number, hellofaxJS.phoneUtil);

    },

    /*
        @number - internationally formatted fax number (digit wise, eg: these are all the same: '1555-555-5555' '15555555555' '1 (555) 555-5555'
    */
    formatInternationalFaxNumber: function(number) {
        //init phoneUtil if necessary
        if (!hellofaxJS.phoneUtil) {
            hellofaxJS.phoneUtil = i18n.phonenumbers.PhoneNumberUtil.getInstance();
        }

        var options = hellofaxJS.getFormattedPhoneOptions(number);
        return hellofaxJS.phoneUtil.formatInternationalFaxNumber(number, options);
    },

    updateDestinationOptions: function(){
        var el = $(this);
        // If not changed, skip
        if (el.attr('changed') == el.val()) {
            return true;
        }
        var destination = el.parent().find("span.destination").html('');
        var options = hellofaxJS.getFormattedPhoneOptions(el.val());
        if (!hellofaxJS.phoneUtil) {
            hellofaxJS.phoneUtil = i18n.phonenumbers.PhoneNumberUtil.getInstance();
        }
        hellofaxJS.phoneUtil.updateDestinationOptions(el, destination, options);

    },

    closeAllCountryDropdowns: function() {
    $(".showing_destination_picker").removeClass('showing_destination_picker');
    $(document).unbind('click', hellofaxJS.closeAllCountryDropdowns);
    },

    hasDST: function(tz_abbr) {
        var y = new Date().getFullYear();
        var d_winter = new Date(y + '/01/01' + (tz_abbr ? ' ' + tz_abbr : ''));
        var d_summer = new Date(y + '/06/01' + (tz_abbr ? ' ' + tz_abbr : ''));
        var offset_delta = Math.abs(d_winter.getTimezoneOffset() - d_summer.getTimezoneOffset());
        return offset_delta == 60;
    },

    getTZOffset: function(tz_abbr) {
        var y = new Date().getFullYear();
        var d1 = new Date(y + '/01/01' + (tz_abbr ? ' ' + tz_abbr : ''));
        var d2 = new Date(y + '/01/01 GMT');
        return d1.getTime() - d2.getTime();
    },

    getUserTZOffset: function() {
        var userData = hellofaxJS.getUserData();
        var tzOffset = userData['timezone_offset'];
        if (tzOffset === null) {
            return -hellofaxJS.getTZOffset() / (3600 * 1000);
        }
        return tzOffset;
    },

    generateMailToLink: function(to_name, to_domain, subject) {
        document.write("<a href=\"mailto");
        document.write(":" + to_name + "@");
        document.write(to_domain + "?subject=" + subject + "\">" + to_name + "@" + to_domain + "<\/a>");
    },

    trackUrlParams: function() {
        var params = {};
        var ps = window.location.search.split(/\?|&/);

        for (var i = 0; i < ps.length; i++) {
            var p = ps[i].split(/=/);

            if (params[p[0]]) {
                params[p[0]].push(p[1]);
            }
            else {
                params[p[0]] = [p[1]];
            }
        }

    },

    prettyPrintJSON: function() {
        $("pre.json-prettify").each(function() {
            var json = $(this).html();

            json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
            json = json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
                        var cls = 'number';
                        if (/^"/.test(match)) {
                            if (/:$/.test(match)) {
                                cls = 'key';
                            } else {
                                cls = 'string';
                            }
                        } else if (/true|false/.test(match)) {
                            cls = 'boolean';
                        } else if (/null/.test(match)) {
                            cls = 'null';
                        }
                        return '<span class="' + cls + '">' + match + '</span>';
                    });

            $(this).html(json);

        });
    },

    addGmailInstallBar: function() {
        if (hellofaxJS.isHelloSign()) {

            hellofaxJS.trackEvent('gmail_install_displayed');

            var topbar = $("#topbar").addClass('gmail-not-installed');
            var installbar = $("<div id='gmail_install_bar'><div/>");

            installbar  .addClass('cws-install-bar')
                        .addClass('gmail-extension-require-not-installed')
                        .html('<p>Sign documents in Gmail</p><a href="/gmail" class="install-btn">Install</a><a class="cws-close">&times;</a>')
                        .find('.cws-close')
                            .click(function(e) {
                                hellofaxJS.removeGmailInstallBar();
                                hellofaxJS.trackEvent('gmail_install_close_clicked');
                                return false;
                            })
                        .end()
                        .find('.install-btn')
                            .click(function(e) {
                                hellofaxJS.trackEvent('gmail_install_clicked');
                            })
                        .end()
                        .insertBefore(topbar);
        }

    },

    removeGmailInstallBar: function() {
        $("#gmail_install_bar").animate({
            marginTop: '-36px'
        }, 500, function() {
            $(this).remove();
        });
    },


    attemptChromeAppInstall: function() {
        if (!hellofaxJS.chromeAppInstallBarText) {
            hellofaxJS.chromeAppInstallBarText = hellofaxJS.isHelloFax() ? "HelloFax now has a Google Chrome App. Send faxes & sign documents from Google Drive." : "HelloSign now has a Google Chrome App.  Sign documents from Google Drive";
        }

        if (window.chrome && chrome.app && !chrome.app.isInstalled) {
            hellofaxJS.trackEvent('cws_install_displayed');

            var installbar = $("<div id='cws_install_bar'><div/>");

            installbar  .addClass('cws-install-bar')
                        .html('<p>' + hellofaxJS.chromeAppInstallBarText + '</p><a class="install-btn">Install</a><a class="cws-close">&times;</a>')
                        .find('.cws-close')
                            .click(function(e) {
                                hellofaxJS.removeChromeAppInstallBar();
                                hellofaxJS.trackEvent('cws_install_close_clicked');
                                return false;
                            })
                        .end()
                        .find('.install-btn')
                            .attr('href', $("#chrome-webstore-item").attr('href'))
                            .attr('target', '_blank')
                            .click(function(e) {
                                hellofaxJS.trackEvent('cws_install_clicked');
                                hellofaxJS.removeChromeAppInstallBar();
                            })
                        .end()
                        .insertBefore('#topbar');
            return true;
        }

        return false;

    },

    removeChromeAppInstallBar: function() {
        $("#cws_install_bar").animate({
            marginTop: '-36px'
        }, 500, function() {
            $(this).remove();
        });
    },

    logUserOut: function() {
        var userData = hellofaxJS.getUserData();

        if (userData['no_timeout'] === true) {
            return;
        }

        var logOutUrl = '/account/logOut?is_timeout=1&on_login_redirect_url=';
        if ($('#fancybox-frame').length) {
            var popUpFrame = window.frames[$('#fancybox-frame').attr("name")];
            if (popUpFrame) {
                popUpFrame.window.onbeforeunload = function() {};
                var editor = $(popUpFrame).data('editor');
                if (editor) {
                    editor.autoSave();
                }
            }
        }

        var tsm_group_guid = $('body').data('tsm_group_guid');

        $(window).unbind('beforeunload');

        var sameDomainTopWindow = hellofaxJS.topWindowInDomain();
        var inIntraDomainIFrame = window.location != sameDomainTopWindow.location;
        if (inIntraDomainIFrame) {
            sameDomainTopWindow.$(window).unbind('beforeunload');
            sameDomainTopWindow.$.fancybox.close();
            sameDomainTopWindow.location = logOutUrl + encodeURIComponent(tsm_group_guid ? '/send/resendDocs?tsm_group_guid=' + tsm_group_guid : sameDomainTopWindow.location);
        }
        else {
            window.location = logOutUrl + encodeURIComponent(tsm_group_guid ? '/send/resendDocs?tsm_group_guid=' + tsm_group_guid : sameDomainTopWindow.location);
        }
    },

    windowsLiveLogin: function(clientId, loggingEnabled, callback) {

        if (!WL) {
            l("Aborting Windows Live login - WL is not defined");
            return;
        }

        WL.init({
            client_id: clientId,
            logging: (loggingEnabled !== false)
        });

        WL.login({
            scope: ["wl.signin", "wl.emails"]
        }).then(function(tokenData){
            WL.api({
                path: "me",
                method: "GET"
            }).then(function(userData){
                if (callback) {
                    callback.call(this, userData);
                }
            });
        });
    }

};

var Cookie = {

    get: function(key) {
        var parts, pairs = document.cookie.split(";");
        for (var k in pairs) {
            parts = $.trim(pairs[k]).split("=");
            if (parts.shift() == key) {
                return parts.join("=");
            }
        }
    },

    set: function(key, value, ttl_ms) {
        var c = key + "=" + value;
        if (ttl_ms !== undefined) {
            var d = new Date();
            d.setTime(d.getTime() + ttl_ms);
            c += "; expires=" + d.toUTCString();
        }
        document.cookie = c;
    }

};

var Base64 = {

    // private property
    _keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",

    // public method for encoding
    encode : function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;

        input = Base64._utf8_encode(input);

        while (i < input.length) {

            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }

            output = output +
            this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
            this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);

        }

        return output;
    },

    // public method for decoding
    decode : function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;

        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

        while (i < input.length) {

            enc1 = this._keyStr.indexOf(input.charAt(i++));
            enc2 = this._keyStr.indexOf(input.charAt(i++));
            enc3 = this._keyStr.indexOf(input.charAt(i++));
            enc4 = this._keyStr.indexOf(input.charAt(i++));

            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;

            output = output + String.fromCharCode(chr1);

            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }

        }

        output = Base64._utf8_decode(output);

        return output;

    },

    // private method for UTF-8 encoding
    _utf8_encode : function (string) {
        string = string.replace(/\r\n/g,"\n");
        var utftext = "";

        for (var n = 0; n < string.length; n++) {

            var c = string.charCodeAt(n);

            if (c < 128) {
                utftext += String.fromCharCode(c);
            }
            else if((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            }
            else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }

        return utftext;
    },

    // private method for UTF-8 decoding
    _utf8_decode : function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;

        while ( i < utftext.length ) {

            c = utftext.charCodeAt(i);

            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            }
            else if((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i+1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            }
            else {
                c2 = utftext.charCodeAt(i+1);
                c3 = utftext.charCodeAt(i+2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }

        }

        return string;
    }
};

$(document).ready(function() {
    var sameDomainTopWindow = hellofaxJS.topWindowInDomain();
    var inIntraDomainIFrame = window.location != sameDomainTopWindow.location;

    var timestamp = new Date().getTime() / 1000;
    hellofaxJS.lastAjaxTimestamp = Math.round(timestamp);
    if (inIntraDomainIFrame && sameDomainTopWindow.hellofaxJS !== undefined) {
        sameDomainTopWindow.hellofaxJS.setLastAjaxTimestamp(Math.round(new Date().getTime() / 1000));
    }
    var userData = hellofaxJS.getUserData();
    if (userData !== undefined && userData.is_logged_in && !userData.has_remember_me) {
        var autoLogOut = setInterval(function() {
            var secondsSinceAjax = Math.round(new Date().getTime() / 1000) - hellofaxJS.lastAjaxTimestamp;
            if (secondsSinceAjax >= 55 * 60) { //55 * 60 = 55 minutes... this should be less than the factories.yml user timeout
                clearInterval(autoLogOut);

                var isAlreadyRunning = false;
                if (inIntraDomainIFrame && sameDomainTopWindow.$('body').data('is_redirecting') !== true) {
                    sameDomainTopWindow.$('body').data('is_redirecting', true);
                }
                else if ($('body').data('is_redirecting') !== true) {
                    $('body').data('is_redirecting', true);
                }
                else {
                    isAlreadyRunning = true;
                }

                if (!isAlreadyRunning) {
                    hellofaxJS.logUserOut();
                }
            }
        }, 30 * 1000);
    }
});

//force uservoice lightbox to open if necessary
$(window).load(function() {
    if (window.location.href.toString().indexOf('show_user_voice') != -1) {
        $("#uvTab img").click();
    }
});


(function() {
    var lastAjaxCallTime = Math.round(new Date().getTime() / 1000);
    var originalMethod = jQuery.ajax;
    var sameDomainTopWindow = hellofaxJS.topWindowInDomain();

    // Automatically execute any analytics_js property returned in JSON response
    jQuery.ajax = function() {
        var inIntraDomainIFrame = window.location != sameDomainTopWindow.location;
        var timestamp = Math.round(new Date().getTime() / 1000);
        hellofaxJS.setLastAjaxTimestamp(timestamp);
        if (inIntraDomainIFrame && sameDomainTopWindow.hellofaxJS !== undefined) {
            sameDomainTopWindow.hellofaxJS.setLastAjaxTimestamp(Math.round(new Date().getTime() / 1000) - 10);
        }

        if (arguments.length > 0 && arguments[0] !== null) {
            var originalOnSuccess = arguments[0].success !== undefined ? arguments[0].success : null;

            //force all ajax requests to include a cache buster
            var has_cache_buster = false;

            if (arguments[0].data && Object.prototype.toString.call(arguments[0].data) == '[object String]') { //string
                has_cache_buster = /[?&]?c=[\d.]+/.test(arguments[0].data);
            }
            else if (arguments[0].data && Object.prototype.toString.call(arguments[0].data) == '[object Object]') { //object
                has_cache_buster = arguments[0].data.c && /^[\d.]+$/.test(arguments[0].data.c);
            }

            //check url
            if (!has_cache_buster) {
                has_cache_buster = /[?&]?c=[\d.]+/.test(arguments[0].url);
            }

            //if still no cache buster, add one
            if (!has_cache_buster) {
                arguments[0].url += arguments[0].url.indexOf('?') > 0 ? '&c=' + Math.random() : '?c=' + Math.random();
            }


            arguments[0].success = function() {
                if (arguments.length > 0 && arguments[0] !== null && arguments[0] !== undefined) {
                    if (arguments[0].analytics_js !== undefined && arguments[0].analytics_js.length > 0) {
                        $(arguments[0].analytics_js).appendTo('body');
                    }

                    if (arguments[0].redirect_url !== undefined && arguments[0].redirect_url.length > 0) {
                        if (inIntraDomainIFrame) {
                            // in an iframe
                            sameDomainTopWindow.$('body').data('is_redirecting', true);
                            $(window).unbind('beforeunload');
                            sameDomainTopWindow.$.fancybox.close();
                            sameDomainTopWindow.location = arguments[0].redirect_url;
                        }
                        else {
                            $('body').data('is_redirecting', true);
                            window.location = arguments[0].redirect_url;
                        }

                        window.location = arguments[0].redirect_url;
                        return originalMethod.apply(this, arguments); // short-circuit and do not ever call originalOnSuccess
                    }
                }

                if (originalOnSuccess) {
                    originalOnSuccess.apply(this, arguments);
                }
            };
        }

        return originalMethod.apply(this, arguments);
    };
})();

function l(str){if(window.console&&console.log){console.log(str);}}

//IE8 don't need no stinking Date.now
Date.now = Date.now || function() { return +new Date; };

//So that we can get the "size" of objects (which take the place of associative arrays)
Object.size = function(obj) {
    var size = 0, key;
    for (key in obj) {
        if (obj.hasOwnProperty(key)) size++;
    }
    return size;
};

//IE8 and below
Object.keys = Object.keys || (function () {
    var hasOwnProperty = Object.prototype.hasOwnProperty,
        hasDontEnumBug = !{toString:null}.propertyIsEnumerable("toString"),
        DontEnums = [
            'toString',
            'toLocaleString',
            'valueOf',
            'hasOwnProperty',
            'isPrototypeOf',
            'propertyIsEnumerable',
            'constructor'
        ],
        DontEnumsLength = DontEnums.length;

    return function (o) {
        if (typeof o != "object" && typeof o != "function" || o === null)
            throw new TypeError("Object.keys called on a non-object");

        var result = [];
        for (var name in o) {
            if (hasOwnProperty.call(o, name))
                result.push(name);
        }

        if (hasDontEnumBug) {
            for (var i = 0; i < DontEnumsLength; i++) {
                if (hasOwnProperty.call(o, DontEnums[i]))
                    result.push(DontEnums[i]);
            }
        }

        return result;
    };
})();

//IE8 and below
String.prototype.trim = String.prototype.trim || (function() {
    return $.trim(this);
});

$("#tsm_group_send input.recipient").live('blur', hellofaxJS.updateDestinationOptions);

$("span.multiple_destinations").live('click', function(e) {
    $(this).toggleClass('showing_destination_picker');

    if ($(this).hasClass('showing_destination_picker')) {
        $(document).click(hellofaxJS.closeAllCountryDropdowns);
    }
});

$(".destination_option").live('click', function(e) {

    var country = $(this).attr('country');
    if (country) {
        $(this).parent().find(".destination_text").html(country.toLowerCase());
    }

    $(this).parent().find('.selected').removeClass('selected');

    var el = $(this).addClass('selected')
        .parents(".recipient_container")
        .find("input.recipient");

    var number = $(this).attr('number');
    if (number) {
        el  .val(number)
            .attr('changed', number);
    }

    destinationPicker.toggleMultiplierWarning();

    window.elem = this;
});

