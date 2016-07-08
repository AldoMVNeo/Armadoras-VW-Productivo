<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<html:html locale="true">
  <head>
    <html:base/>
  </head>
  <body>
    <table width="100%"  cellspacing="5">
      <tbody>   
      <tr align="center">
          <td>
            <tiles:insert attribute="titulo"/>
          </td>
        </tr>        
        <tr>
          <td>
            <tiles:insert attribute="cabeceraMenu"/>
          </td>  
          </tr>          
        <tr>
          <td>
            <tiles:insert attribute="cabeceraGrabacion"/>
          </td>  
          </tr>
    <tr> 
    <td>
            <tiles:insert attribute="cuerpo"/>
          </td>  
        </tr>    
        <tr>
 <td width="700">
            <tiles:insert attribute="pie"/>
          </td>  
        </tr>    
      </tbody>
    </table>    
  </body>
</html:html>
