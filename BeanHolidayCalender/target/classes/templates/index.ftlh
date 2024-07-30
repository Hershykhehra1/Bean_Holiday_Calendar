<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>US Holidays</title>
<link rel="stylesheet" type="text/css" href="css/Styles.css">
</head>
<body>
	<h2>US Holidays</h2>
	<table>
		<tr>
			<th>Date</th>
			<th>Holiday</th>
			<th>Update | Delete</th>
		</tr>
		<#list entries as entry>
		<tr>
			<td>${entry.formattedDate}</td>
			<td>${entry.name}</td>
			<td><a href='update/${entry.id}'>Update</a> | <a
				href='/delete/${entry.id}'>Delete</a></td>
		</tr>
		</#list>
	</table>
	<br />
	<form action="add">
		<button type="action">Add Holiday</button>
	</form>
</body>
</html>