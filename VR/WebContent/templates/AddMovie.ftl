<#include "header.ftl">

<form method="POST" action="RegisteredUser?action=AddMovie">
	<fieldset id="insertoffer">
		<legend>Required Information</legend>
		<div>
			<label>Title</label>
			<input type="text" name="title" required>
	    </div>

		<div>
			<label>Director</label>
			<input type="text" name="director" required>
	    </div>

		<div>
			<label>Main Actors</label>
			<input type="text" name="actors" required>
	    </div>
		<div>
			<label>Publishing Date</label>
			<input type="text" name="publishingDate" id="datepicker1">
	    </div>
	    
 		<br>
	</fieldset>
	<button type="submit" id="submit">Add Movie</button>
</form>


<#include "footer.ftl"> 