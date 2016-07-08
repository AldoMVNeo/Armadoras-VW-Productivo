$.download = function (url, data, method) {
    if (url && data) {
        //convert the data object into input HTML fields
        var inputs = '';
        var convertToInput = function (key, keyStr, obj) {
            if (typeof obj === 'undefined') {
                return;
            } else if (typeof obj === "object") {
                for (var innerKey in obj) {
                    if (obj.hasOwnProperty(innerKey)) {
                        var innerKeyStr = '';
                        if (keyStr === '') {
                            innerKeyStr = innerKey.toString();
                        } else {
                            innerKeyStr = keyStr + "[" + innerKey.toString() + "]";
                        }
                        convertToInput(innerKey, innerKeyStr, obj[innerKey]);
                    }
                }
                return;
            } else if ($.isArray(obj)) {
                obj.forEach(function (item) {
                    convertToInput(key, keyStr + "[]", item);
                });
                return;
            }

            inputs += "<input type='hidden' name='" + keyStr + "' value='" + obj + "' />";
        };
        convertToInput(null, '', data);

        //send request
        jQuery('<form action="' + url + '" method="' + (method || 'post') + '">' + inputs + '</form>').appendTo('body').submit().remove();
    };
};