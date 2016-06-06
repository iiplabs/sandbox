<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%@ page import="ca.canon.cmac2013web.web.IndexController" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="cache-control" content="max-age=0" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />
        
        <meta http-equiv="X-UA-Compatible" content="IE=8, IE=9, IE=10, IE=11, IE=edge" />
        
        <title><tiles:insertAttribute name="pageTitle" ignore="true" /></title>
        
        <link type="text/css" 
          href="${pageContext.request.contextPath}/resources/css/blitzer/jquery-ui-1.10.3.custom.css" rel="Stylesheet" />  
        <link type="text/css" 
          href="${pageContext.request.contextPath}/resources/css/ui.jqgrid.css" rel="Stylesheet" />

        <link rel="stylesheet" type="text/css" 
          href="${pageContext.request.contextPath}/resources/js/packages/ext-theme-gray/build/resources/ext-theme-gray-all.css">
  
        <link type="text/css" 
          href="${pageContext.request.contextPath}/resources/css/cmac2013web.css" rel="Stylesheet" />
            
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.3.custom.min.js"></script>
          
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/i18n/grid.locale-${pageContext.response.locale}.js"></script>
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/jquery.jqGrid.min.js"></script>
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/jquery.fileDownload.js"></script>      
        
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/ext-all.js"></script>
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/packages/ext-theme-gray/build/ext-theme-gray.js"></script>
    
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/MessageBus.js"></script>            
        
        <script type="text/javascript" 
          src="${pageContext.request.contextPath}/resources/js/cmac2013web.js"></script>            
    </head>
    
    <body>
<script type="text/javascript">
    var userAdmin = ${cmacSessionObject.sessionUser.userAdmin };
    
    var pageSizeData = {pages: [{key:25, value:25}, 
                                {key:50, value:50}, 
                                {key:100, value:100}, 
                                {key:200, value:200}, 
                                {key:500, value:500}, 
                                {key:1000, value:1000}]};
    var pagesSizeStore = new Ext.data.Store({
        autoLoad: true,
        storeId: 'pagesSizeStore',
        data: pageSizeData,
        fields: ['key', 'value'],
        proxy: {
            type: 'memory',
            reader: {
                rootProperty: 'pages',
                type: 'json'
            }
        }
    });
    var pageSizeCombo = new Ext.form.field.ComboBox({
        allowBlank: false,
        displayField: 'value',
        editable: false,
        forceSelection: true,
        name: 'pageSizeCombo',
        preventMark: true,
        queryMode: 'local',
        store: 'pagesSizeStore',
        typeAhead: false,
        value: 25,
        valueField: 'key',
        width: 60,
        listeners: {
            select: function(combo, record, index) {
                this.ownerCt.getStore().pageSize = this.getValue();
                this.ownerCt.getStore().loadPage(1);
            }
        }
    });
    
    // models
    Ext.define('cmac2013web.model.RequestDto', {
        extend: 'Ext.data.Model',
        idProperty: 'webId',
        fields: [
          {name: 'webId', type: 'string'},
          {name: 'accountName', type: 'string'},
          {name: 'salesRepName', type: 'string'},
          {name: 'channelNameDescription', type: 'string'},
          {name: 'status', type: 'string'},
          {name: 'requestedBy', type: 'string'},
          {name: 'requestDate'},
          {name: 'startDate'},
          {name: 'expiryDate'}
        ]
    });
    Ext.define('CMACAdministrationWeb.model.UserDto', {
        extend: 'Ext.data.Model',
        idProperty: 'cmacRowId',
        fields: [
          {name: 'cmacRowId', type: 'int', useNull : true},
          {name: 'userId', type: 'string'},
          {name: 'districtAccountExecutiveFlag', type: 'string'},
          {name: 'administratorFlag', type: 'string'},
          {name: 'directorFlag', type: 'string'},
          {name: 'importFlag', type: 'string'},
          {name: 'group', type: 'string'},
          {name: 'userAdminFlag', type: 'string'}
        ]
    });
    Ext.define('CMACPricingApprovalWeb.model.MainUnitDto', {
        extend: 'Ext.data.Model',
    	idProperty: 'uniqueId',
    	fields: [
    	    {name: 'itemCode', type: 'string'},
    	    {name: 'itemName', type: 'string'},
    	    {name: 'itemQuantity', type: 'int'},
    	    {name: 'wholesalePrice', type: 'number'},
    	    {name: 'wholesaleDiscount', type: 'number'},
    	    {name: 'requestedPrice', type: 'number'},
    	    {name: 'msrpPrice', type: 'number'},
    	    {name: 'support', type: 'number'},
    	    {name: 'itemType', type: 'string'},
    	    {name: 'setItemFlag', type: 'boolean', defaultValue: false},
    	    {name: 'standardCost', type: 'number'},
    	    {name: 'discontinuedFlag', type: 'boolean', defaultValue: false},
    	    {name: 'uniqueId', type: 'string'}
    	]
    });

 // configured main units
    var mainUnitsProxy = {
            type: 'rest',
            url: 'getmainunits',
            reader: {
              type: 'json'
            },
            writer: {
              type: 'json',
              writeAllFields: true
            },
            pageParam: undefined,
            startParam: undefined,
            limitParam: undefined
          };

    Ext.define('CMACPricingApprovalWeb.store.MainUnitsStore', {
      extend: 'Ext.data.Store',
      model: 'CMACPricingApprovalWeb.model.MainUnitDto',
      proxy: mainUnitsProxy,
      remoteFilter: false,
      remoteSort: false,
      sorters: [
        {property: 'itemName', direction: 'ASC'}
      ],
      autoLoad: true, //
      autoSync: true
    });

    // configured accessories
    var accessoriesProxy = {
            type: 'rest',
            url: 'getaccessories',
            reader: {
              type: 'json'
            },
            writer: {
              type: 'json',
              writeAllFields: true
            },
            pageParam: undefined,
            startParam: undefined,
            limitParam: undefined
          };

    Ext.define('CMACPricingApprovalWeb.store.AccessoriesStore', {
      extend: 'Ext.data.Store',
      model: 'CMACPricingApprovalWeb.model.MainUnitDto',
      proxy: accessoriesProxy,
      remoteFilter: false,
      remoteSort: false,
      sorters: [
        {property: 'itemName', direction: 'ASC'}
      ],
      autoLoad: false, //
      autoSync: true
    });
    
    // functions
    var statusRenderer = function(value, meta, record, rowIndex) {
        var ret = null;
        var allStatusStore = Ext.data.StoreManager.lookup('StatusStore');
        if (null != allStatusStore) {
            var statusOptionDto = allStatusStore.findRecord('key', value, 0, false, false, true);
            if (null != statusOptionDto) {
                ret = statusOptionDto.get('value');
            }
        }
        return ret;
    };
    
    var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
        clicksToEdit: 2
    });    
</script>

		<div class="main">
            <div class="header">
                <table style="height: 28px; width: 100%">
                    <tr>
                        <td style="width: 134px" valign="middle"><div class="canon_logo"></div></td>
                        <td style="width: 220px" valign="middle">
                            <span class="thick" style="margin-left: 10px">
                                <spring:message code="label.main.welcome"/>
                            </span>
                            <span class="thick" style="margin-left: 2px">
                                ${cmacSessionObject.sessionUser.userId}
                            </span>
                        </td>
                        <td valign="middle">
                            <span class="head_title">
                                <a href="${pageContext.request.contextPath}/requests/${fn:escapeXml(cmacSessionObject.sessionUser.userId)}">
                                    <spring:message code="label.main.title"/>
                                </a>
                            </span>
                        </td>
                        <td style="width: 50px" valign="middle">
                            <div class="logout"> 
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            
            <div class="container">
                <div class="menu">
                    <%@include file="appMenu.jsp" %>
                </div>

                <div class="content">
    
    				<tiles:insertAttribute name="body" />

                    <div class="item">
                        <div class="user_list">
                        <c:forEach items="${authorizedUsers}" var="authorizedUser">
                            <a href="${pageContext.request.contextPath}/requests/${fn:escapeXml(authorizedUser.userId)}">
                                <span>${authorizedUser.userId}</span>
                            </a>
                        </c:forEach>
                        </div>
                    </div>
                    
                </div>

		        <div id="alert-message" title="<spring:message code="label.alert.title" />" style="display:none">
		          <p>
		            <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		            <spring:message code="label.alert.body" />
		          </p>
		        </div>

                <%@include file="defaultFooter.jsp" %>        
            </div>    
        </div>
    <script type="text/javascript">
      $(document).ready(function() {
        // Logout link handler
        $(".logout").click(function() {
            location.href='${pageContext.request.contextPath}/logout';
        });
      });
    </script>
    </body>    
</html>
