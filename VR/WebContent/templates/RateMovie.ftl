<#include "header.ftl">

 
<form method="POST" action="RegisteredUser?action=RateMovie&movieID=${movieID}">
	<fieldset id="insertoffer">
		<legend>Required Information</legend>
		<div>
			<label>Username</label>
			<select name="userName" id="userName">
				<#list users as u>
					<option value="${u}">${u}</option>
				</#list>
            </select>
	    </div>

		<div>
			<label>Rating</label>
			<input type="number" name="rating" min="0" max="10" required>
	    </div>

		<div>
			<label>Comment</label>
			<input type="text" name="comment">
	    </div>
	    
 		<br>
	</fieldset>
	<button type="submit" id="submit">Rate Movie</button>
</form>

<#include "footer.ftl">  