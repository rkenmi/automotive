<%@ page import="java.util.*, Model.Automotive" %>
<HTML>
<HEAD><TITLE>Basic Car Report</TITLE></HEAD>
<BODY>
<%	
	Automotive a = (Automotive)session.getAttribute("car");	
%>
<H1 ALIGN = "CENTER">Basic Car Report</H1>
<CENTER><IMG SRC = "car.jpg"><BR>Your selections for: <B><%=a.toString()%></B></CENTER>
<%
	Iterator iter = a.getOptionSetNamesIterator();
	String optionset = "";
	boolean exists = false;
	int total_sum = 0;	
%>
<TABLE BORDER = "1", ALIGN = "CENTER">
<TR><TD>Car</TD><TD>Base Price</TD><TD>$<%=(int)a.getBaseprice()%></TD></TR>
<%	total_sum += a.getBaseprice(); %>
<%	while(iter.hasNext()) {
		optionset = iter.next().toString(); %><TR><TD>
<%		out.println(optionset);		%></TD><TD><%=request.getParameter(optionset)%></TD>
<%		if ( a.getOption(optionset, request.getParameter(optionset)) != null){	%><TD>$<%=(int)a.getOption(optionset, request.getParameter(optionset)).getBaseprice()%>
<%			total_sum += a.getOption(optionset, request.getParameter(optionset)).getBaseprice(); %></TD>
<%			exists = true;
		}%></TR>
<%	} // end while %>
<TR><TD><B><U>Total Cost</U></B></TD><TD></TD><TD><B><U>$<%=total_sum%></U></B></TD></TR>
</TABLE>
</BODY>
</HTML>