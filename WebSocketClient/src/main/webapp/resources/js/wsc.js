Date.prototype.format = function (format) { //author: meizz
	var days = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
	
	var hours = this.getHours();
    var ttime = "AM";
    if(format.indexOf("t") > -1 && hours > 12)
    {
        hours = hours - 12;
        ttime = "PM";
     }

	var o = {
	    "M+": this.getMonth() + 1,   //month
	    "d+": this.getDate(),        //day
	    "h+": hours,                 //hour
	    "m+": this.getMinutes(),     //minute
	    "s+": this.getSeconds(),     //second
	    "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
	    "S": this.getMilliseconds(), //millisecond,
	    "t+": ttime,
	    "l+": days[this.getDay()]
	}
	
	if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
	  (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o) if (new RegExp("(" + k + ")").test(format))
	    format = format.replace(RegExp.$1,
	      RegExp.$1.length == 1 ? o[k] :
	        ("00" + o[k]).substr(("" + o[k]).length));
	
	return format;
}

var jsonReader = {
        repeatitems: false,
        id: "uniqueId",
        root: function (obj) { return obj; },
        page: function (obj) { return 1; },
        total: function (obj) { return 1; },
        records: function (obj) { return obj.length; }
};

var jsonReaderListBody = {
        repeatitems: false,
        id: "uniqueId",
        root: 'accountList',
        page: 'resultSpec.page',
        total: 'resultSpec.totalPages',
        records: 'resultSpec.totalElements'
};

var requestLinkUnformat = function(cellvalue, options, cell) {
    return $('a', cell).attr('href');
};

// Sales Prices
var salesPriceCustomFormatter = function(cellValue, options, rowObject) {
	var formattedVal = '';
	
	if (rowObject.salesPrices.length > 1) {
		formattedVal = '*';
	} else if (rowObject.salesPrices.length <= 0) {
		formattedVal = 'N/A';
	} else {
		formattedVal = rowObject.salesPrices[0].salesPrice;
	}
    
    return formattedVal;
};

// Custom Formatter for Date fields
var dateCustomFormatter = function(cellValue, options, rowObject) {
	if (null != cellValue) {
		var d = new Date(cellValue);
        // output date format                        
        return d.format('yyyy/MM/dd l');
	}

    return '';
};
var dateUnformat = function(cellValue, rowData) {
	var val = (cellValue != null && cellValue.length > 0) ? cellValue : '1900/01/01 00:00:00'
	return new Date(val);
}

// Set Item Indicator
var setItemCustomFormatter = function(cellValue, options, rowObject) {
	var formattedVal = '';
	if (rowObject.setItemIndicator == true) {
		formattedVal = 'Y';
	}
	return formattedVal;
};
var setItemFlagCustomFormatter = function(cellValue, options, rowObject) {
    var formattedVal = '';
    if (rowObject.setItemFlag == true) {
        formattedVal = 'Y';
    }
    return formattedVal;
};

// resize grid when page is resized
var jqGridResizeFunction = function() {
    if (grid = $('.ui-jqgrid-btable:visible')) {
        grid.each(function(index) {
            gridId = $(this).attr('id');
            gridParentWidth = $('#gbox_' + gridId).parent().width();
            $('#' + gridId).setGridWidth(gridParentWidth);
        });
    }
};

var postProduct = function(url, jsonData, callbackSuccess, refresh, callbackFail) {
    $.ajax(url, {
        cache: false,
        contentType: 'application/json; charset=utf-8',
        type: 'PUT',
        data: JSON.stringify(jsonData),
        dataType: 'json',
        success: function(data) {
          if (null != callbackSuccess) {
      	    callbackSuccess(data, refresh);
          }
        },
        error: function (jqXHR, textStatus, errorThrown) {
          if (null != callbackFail) {
          	callbackFail();
          }
        }
    });
};

var serializeArray = function(a, sep) {
	var s = '';
	if (a) {
		for (var ii=0; ii<a.length; ii++) {
			s += a[ii];
			if (ii < a.length) {
				s += sep;
			}
		}
	}
	return s;
}

//var mainUnitsFields = ['itemCode', 'itemName', 'itemQuantity', 'wholesalePrice', 'wholesaleDiscount', 
//                       'requestedPrice', 'msrpPrice', 'support', 'itemType', 'setItemFlag', 'standardCost', 'discontinuedFlag', 'uniqueId'];