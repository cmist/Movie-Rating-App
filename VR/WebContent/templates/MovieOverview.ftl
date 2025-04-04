<#include "header.ftl">


<table id="MovieOverview">
	<tr>
		<th>#</th>
		<th>Title</th>
		<th>Director</th>
		<th>MainActors</th>
		<th>publishingDate</th>
		<th>avgRating</th>
	</tr>
	<#list moviesList as m>
	<tr>
		<td><a href="RegisteredUser?page=RateMovie&amp;movieID=${m.movieID}" title="Rate the movie">${m.movieID}</a></td>
		<td>${m.title}</td>
		<td>${m.director}</td>
		<td>${m.mainActors}</td>
		<td>${m.publishingDate}</td>
		<td>${m.avgRating}</td>
	</tr>
	</#list>
</table>
<#include "footer.ftl">