<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="apprepo.Application"%> 
<%@page import="java.util.ArrayList"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Application Repository</title>
<style>
        .highlight {
            background-color: yellow;
        }
    </style>
        <script>
        function highlightText(searchText) {
            if (!searchText) return;

            // Get all table cells
            var cells = document.querySelectorAll('table td');

            cells.forEach(function(cell) {
                var text = cell.innerHTML;
                var highlightedText = text.replace(new RegExp('(' + searchText + ')', 'gi'), '<span class="highlight">$1</span>');
                cell.innerHTML = highlightedText;
            });
        }
        function onSearchButtonClick() {
            var searchText = document.getElementById('searchInput').value;
            highlightText(searchText);
        }

        </script>
</head>
<body>
    <form action="FilterServlet" method="post">
        <input type="text" name="query" value="${query}" placeholder="Filter...">
        <input type="submit" value="Filter">
    </form>    
    <br/>
    <form action="SortServlet" method="post">
    	<input type="submit" value="SortByAppName">
    </form>
    <br/>    
    <!-- Search Form -->
    <form method="get" action="">
        <input type="text" id="searchInput" name="search" placeholder="Enter search term" value="<%= request.getParameter("search") %>">
        <input type="button" value="Search" onclick="onSearchButtonClick()">
    </form>
    
    <br/>
    
    <div  align="center" style="max-width:1400px; border:2px solid ;">
    New Application Form
    <form action="NewAppServlet" method="post">
        <table border="1">
        <tr>
            <th><input type="text" name="name" value="${name}" placeholder="Name"></th>
            <th><input type="text" name="description" value="${description}" placeholder="Description"></th>
            <th><input type="text" name="organization" value="${organization}" placeholder="Organization"></th>
            <th><input type="text" name="platforms" value="${platforms}" placeholder="Platforms"></th>
            <th><input type="text" name="links" value="${links}" placeholder="Links"></th>
            <th><input type="text" name="price" value="${price}" placeholder="Price"></th>
        </tr>
        </table>
    	<input type="submit" value="AddNewApplication">
    </form>
    </div>
    <h1>Application Repository</h1>
    <p><a href="http://localhost:8082/NextGenRent/NextGenRentServlet" target="_blank">NextRent home page</a></p>
    <form method = "GET" action = "NextGenRentServlet" >
        
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Organization</th>
            <th>Platforms</th>
            <th>Links</th>
            <th>Price</th>
        </tr>
        <p class="searchable"> 
        <%ArrayList<Application> appList =  
            (ArrayList<Application>)session.getAttribute("applications"); 
        for(Application app:appList){%>
     
            <tr>
          
                <td><%=app.getName()%></td>
                <td><%=app.getDescription()%></td>
                <td><%=app.getOrganization()%></td>
                <td><%=app.getPlatforms()%></td>
                <td><%=app.getLinks()%></td>
                <td><%=app.getPrice()%></td>
            </tr>
           <%}%> 
        </p>
    </table>
    </form>
        
</body>
</html>
