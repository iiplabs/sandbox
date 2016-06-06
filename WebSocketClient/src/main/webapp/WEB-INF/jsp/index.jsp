<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="ca.canon.cmac2013web.web.IndexController" %>
    
        <form:form method="post" modelAttribute="<%=IndexController.FORM_ATTRIBUTE_NAME %>" >
            <div class="item">
                <div style="margin: 5px 0 15px 0">
                    <h1>
                        <spring:message code="label.list.existing_requests" 
                            arguments="${requestsListForm.currentUser.userId}"/>
                    </h1>
                </div>
                
                <div>
                    <table id="search_results"><tr><td/></tr></table>
                    <div id="search_results_pager"></div>
                </div>
            </div>                        
        </form:form>
                    
        <script type="text/javascript">
            $(document).ready(function() {
                var allStatusStore = new Ext.data.Store({
                    autoLoad: true,
                    storeId: 'allStatusStore',
                    data: ${requestsListForm.jsonStatusOptions},
                    fields: ['key', 'value'],
                    proxy: {
                        type : 'memory',
                        pageParam: '',
                        startParam: '',
                        limitParam: '',
                        reader: {
                            type: 'json'
                        }
                    }
                });

                var statusDescriptionCustomFormatter = function(cellValue, options, rowObject) {
                    var ret = null;
                    var statusOptionDto = allStatusStore.findRecord('key', rowObject.status, 0, false, false, true);
                    if (null != statusOptionDto) {
                        ret = statusOptionDto.data.value;
                    }
                    return ret;
                };

                // create request link from column value
                var requestLinkCustomFormatter = function(cellValue, options, rowObject) {
                    return '<a href="${pageContext.request.contextPath}/request/' + cellValue + '">' + cellValue + '</a>';
                };
                // follow-up flag
                var followUpCustomFormatter = function(cellValue, options, rowObject) {
                    var formattedVal = '<img width=19 heigt=19 onclick="doFollowUp(\'' + rowObject.webId + '\', ' + !cellValue + ')"'
                                       + ' src="${pageContext.request.contextPath}/resources/images/follow-up-'
                                       + (cellValue === true ? 'sel-' : '') + 'v1.gif"' 
                                       + ' style="cursor:pointer" />';
                    
                    return formattedVal;
                };                
                
                // initialize the search results grid
                var searchResultsGrid = $("#search_results").jqGrid({
                    altRows: true,
                    autowidth: true,
                    colModel: [
                            {name: 'webId', label: '<spring:message code="label.list.request.id"/>', index: 'webId', key: true, 
                                formatter: requestLinkCustomFormatter, width: 60},
                            {name: 'status', label: 'Status', index: 'status', hidden: true},
                            {name: 'statusDescription', label: '<spring:message code="label.list.status"/>', index: 'statusDescription', 
                            	formatter: statusDescriptionCustomFormatter, width: 60},
                            {name: 'accountName', label: '<spring:message code="label.list.account.name"/>', index: 'accountName'},
                            {name: 'starred', label: '<spring:message code="label.list.follow.up"/>', index: 'starred', align: 'center', 
                                formatter: followUpCustomFormatter, width: 60},
                            {name: 'requestedBy', label: '<spring:message code="label.list.requested.by"/>', index: 'requestedBy', width: 90},
                            {name: 'requestDate', label: '<spring:message code="label.list.request.date"/>', index: 'requestDate', align: 'center',
                            	sorttype: dateUnformat, formatter: dateCustomFormatter, width: 75},
                            {name: 'expiryDate', label: '<spring:message code="label.list.expiry.date"/>', index: 'expiryDate', align: 'center',
                                sorttype: dateUnformat, formatter: dateCustomFormatter, width: 75}
                            ],
                    datatype: 'json',
                    deepempty: true,
                    forceFit: true,
                    /*gridview: true,*/
                    height: 'auto',
                    jsonReader: jsonReaderListBody,
                    loadonce: false,
                    loadui: 'block',
                    /*multiboxonly: true,*/
                    /*multiselect: true,*/
                    ondblClickRow: function(rowid, iRow, iCol, e) {
                    },
                    onSelectAll: function(aRowids, status) {
                    },
                    onSelectRow: function(rowid, status, e) {
                    },
                    pager : '#search_results_pager',
                    rowattr: function (rd) {
                            // mark unsubscribed records with
                            // strikethrough text
                            //if (rd.statusCode == statusCodeUnsubscribed) {
                            //    return {'class': 'grid-withdrawals'};
                            //}
                    },
                    rownumbers: true,
                    sortname: 'requestDate',
                    sortorder: 'desc',
                    rowList: [20, 50, 100, 200, 500, 1000],
                    rowNum: 20, // set -1 to display all rows
                    url: '${pageContext.request.contextPath}/ajax/requests/${requestsListForm.currentUser.userId}',
                    viewrecords: true,
                    gridComplete: function() {
                        jqGridResizeFunction();
                        // jQuery("#search_results").jqGrid('setGridParam', {datatype:'local'}); // disable for paging
                    }                    
                });

                $(window).bind('resize', jqGridResizeFunction).trigger('resize');
            });

            // update follow up status
            function doFollowUp(webId, val) {
                var ajaxCallUrl = '${pageContext.request.contextPath}/request/' 
                                  + webId + '/'
                                  + (val ? 'sf' : 'uf');
                
                $.ajax(ajaxCallUrl, {
                    cache: false,
                    /*dataType: 'json',*/
                    success: function(data) {
                        if (data != null) {
                            jQuery("#search_results").setRowData(data.webId, 
                                    {webId: data.webId, starred: data.newFollowUp});
                        }
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        if (jqXHR != null) {
                            var errorMessage = jqXHR.status + "-" + jqXHR.statusText;
                            alert(errorMessage);
                        }
                    },
                    statusCode: {
                        404: function() {
                            var errorMessage = 'Error 404 (page not found) at ' + ajaxCallUrl;
                            alert(errorMessage);
                        },
                        406: function() {
                            var errorMessage = 'Error 406 (not acceptable) at ' + ajaxCallUrl;
                            alert(errorMessage);
                        } 
                    }
                });                
            }            
        </script>