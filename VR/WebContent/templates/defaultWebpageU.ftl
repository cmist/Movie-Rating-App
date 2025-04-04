 

<#include "header.ftl">

<b>Welcome to our little demonstration on the Movie rating Webapp</b><br><br>

<form method="POST" action="UnregisteredUser?action=register">
	<fieldset id="insertoffer">
		<legend>Required Information</legend>
		<div>
			<label>Username</label>
			<input type="text" name="username" required>
	    </div>

		<div>
			<label>Email</label>
			<input type="text" name="email" required>
	    </div>

		<div>
			<label>Age</label>
			<input type="number" name="age" required>
	    </div>
 		<br>
	</fieldset>
	<button type="submit" id="submit">Register</button>
</form>
<#include "footer.ftl">