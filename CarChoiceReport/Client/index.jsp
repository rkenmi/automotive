<%@ page import="java.util.*, Client.CarModelOptionsIO, Model.*" %>
<HTML>
<HEAD><TITLE>Basic Car Choice</TITLE></HEAD>
<BODY>
<H1 ALIGN = "CENTER">Basic Car Choice</H1>
<CENTER><IMG SRC = "car.jpg"></CENTER>
<%
	String filename = this.getServletContext().getRealPath("/Client/prop.txt");
   	Server s1 = new Server();
	s1.start();
	CarModelOptionsIO t1 = new CarModelOptionsIO(filename);
	t1.start();
	try {
		s1.turnOff();
		t1.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		System.err.println("Thread interrupted");
	}
	LinkedHashMap<String, Automotive> cars = s1.getContainer();
	Automotive a = cars.get("Focus Wagon ZTW");
	Iterator<OptionSet> iter_optset = a.getOptionSetNamesIterator();
%>
<TABLE BORDER = "1", ALIGN = "CENTER">
<TR><TD><B>Car:</B></TD><TD><B><%=a.toString()%></B></TD>
</TR>
<FORM ACTION = "report.jsp" METHOD = "POST"> 
<%
	while(iter_optset.hasNext()){
		OptionSet os = iter_optset.next() ;%><TR><TD><%=os.toString() %>: </TD><TD><SELECT NAME = "<%=os.toString()%>">
<%		System.out.println(os.toString());
		Iterator<Option> iter_opt = os.getOptions().iterator();
		while(iter_opt.hasNext()){
			Option o = iter_opt.next();
			if(o.getName() != null ){
				System.out.println( "\t" + o.getName() );%><OPTION VALUE = "<%=o.getName()%>"><%= o.getName() %></OPTION>
<%			} //if
		} // end while %></TD></TR></select><%
	} // end while
%>
<BR>
<%
session.setAttribute("car", a);
%>
<TR ALIGN = RIGHT><TD ALIGN = RIGHT, COLSPAN = 2><INPUT TYPE="submit" VALUE="Submit"></TD></FORM></TR>
</TABLE>
</BODY>
</HTML>