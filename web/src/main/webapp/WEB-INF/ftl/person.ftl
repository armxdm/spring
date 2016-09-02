<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/bootstrap.css'/>"/>
		<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/custom.css'/>"/>
        <title>Spring Exercise</title>
	</head>
	<script type="text/javascript">
		function changeRoleName(x){
            if(x.checked){
				x.setAttribute("name","roleChecked"); 
      		} else {
				x.setAttribute("name","role");
			}
		}
		
		function addContact() {
			var contactInfo = document.getElementById("info").value;
			if(contactInfo == ""){
				document.getElementById("errors").innerHTML = "<p>Contact Information must be filled out.</p>";
        		return false;
			}
			var contactType = document.getElementById("contactType");
			var selectedContactType = contactType.options[contactType.selectedIndex].value;
			document.getElementById("info").value == "";
			var table = document.getElementById("contactTable");
			var row = contactTable.insertRow(0);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);

			cell1.innerHTML = "<input type='hidden' name='contactId' value='-1'><input name='contactType' value='" + selectedContactType + "' readonly>";
			cell2.innerHTML = "<input name='contactInfo' value='" + contactInfo + "' readonly>";
			cell3.innerHTML = "<a onClick='deleteContact(this)'>Delete</a>";
			
		}
		
		function deleteContact(row){
			var i = row.parentNode.parentNode.rowIndex;
			document.getElementById("contactTable").deleteRow(i);
		}
		
	</script>
	<body>
		<div class="container">
			<div class="header">
				 <img src="/resources/exist-logo1.png" alt="Exist"> 
				 <h1 class="title"><strong>Spring Exercise</strong></h1>
			</div>
			
			<div class="row" style="padding: 0 100px" >
				<center>
				<#if RequestParameters.action! == "edit">
					<h2><b>Edit Person</b></h2>
				<#else>
					<h2><b>Add Person</b></h2>
				</#if>
				</center>
				<span id="errors" style="color:red; font-style: italic"><p><#list errors! as error>
					${error}<br>
				</#list></p></span>
				<span style="color:green;"><p>${success!}</p></span>
				<form class="form-inline" action="${ctx.contextPath}/person?action=submit" method="post" accept-charset="utf-8">
					<fieldset class="form-group">
						<legend>Name<span class="required">*</span></legend>
							<input type="text" class="form-control" name="lastName" placeholder="Last Name" value='${(person.lastName)!}' required>				
							<input type="text" class="form-control" name="firstName" placeholder="First Name" value="${(person.firstName)!}" required>
							<input type="text" class="form-control" name="middleName" placeholder="Middle Name" value="${(person.middleName)!}" required>				
							<input type="text" class="form-control" name="suffix" placeholder="Suffix" value="${(person.suffix)!}" style="width:100px" >
					
							<select class="form-control" placeholder="Title" name="title">
								<option>Title</option>
								<option value="Mr." <#if (person.title)??><#if person.title == "Mr.">selected</#if></#if>>Mr.</option>
								<option value="Ms." <#if (person.title)??><#if person.title == "Ms.">selected</#if></#if>>Ms.</option>
								<option value="Mrs." <#if (person.title)??><#if person.title == "Mrs.">selected</#if></#if>>Mrs.</option>
							</select>
						
					</fieldset>
					<br><br>
					<div class="form-group">
						<label for="birthday">Birthday:<span class="required">* </span></label>
						<input type="date" id="birthday" class="form-control" name="birthday" placeholder="mm/dd/yyyy" value="${(person.birthdayStr)!}" style="width:150px" >
					</div>
					&nbsp;&nbsp;
					<div class="form-group">
						<label for="dateHired">Date Hired:<span class="required">* </span></label>
						<input type="date" id="dateHired" class="form-control" name="dateHired" placeholder="mm/dd/yyyy" value="${(person.dateHiredStr)!}" style="width:150px" required>
					</div>
					&nbsp;&nbsp;
					<div class="form-group">
						<label for="currentlyEmployed">Currently Employed:</label>
						<select class="form-control" name="currentlyEmployed" id="currentlyEmployed">
							<option value="Yes" <#if (person.currentlyEmployed)??><#if person.currentlyEmployed == true>selected</#if></#if>>Yes</option>
							<option value="No" <#if (person.currentlyEmployed)??><#if person.currentlyEmployed == false>selected</#if></#if>>No</option>
						</select>
					</div>
					&nbsp;&nbsp;
					<div class="form-group">
						<label for="gwa">GWA:<span class="required">* </span></label>
						<input type="number" id="number" value="${(person.gwa)!}" class="form-control" name="gwa" placeholder="1.0-5.0" style="width:90px" step="0.01" min="1" max="5" >
					</div>
					<br><br>
  					<fieldset class="form-group">
						<legend>Home Address<span class="required">*</span></legend>
						<input type="text" class="form-control" name="homeAddressNum" placeholder="No." style="width:80px" value="${(person.homeAddress.number?c)!}" required>
						<input type="text" class="form-control" name="homeAddressStreet" placeholder="Street" value="${(person.homeAddress.street)!}" required>
						<input type="text" class="form-control" name="homeAddressBarangay" placeholder="Barangay" value="${(person.homeAddress.barangay)!}" required>
						<input type="text" class="form-control" name="homeAddressCity" placeholder="City" value="${(person.homeAddress.city)!}" required>
						<input type="text" class="form-control" name="homeAddressZipCode" placeholder="Zip Code" style="width:120px" value="${(person.homeAddress.zipCode?c)!}" required>
					</fieldset>
					<br><br>
					<fieldset class="form-group">
						<legend>Work Address</legend>
						<input type="text" class="form-control" name="workAddressNum" placeholder="No." style="width:80px" value="${(person.workAddress.number?c)!}">
						<input type="text" class="form-control" name="workAddressStreet" placeholder="Street" value="${(person.workAddress.street)!}">
						<input type="text" class="form-control" name="workAddressBarangay" placeholder="Barangay" value="${(person.workAddress.barangay)!}">
						<input type="text" class="form-control" name="workAddressCity" placeholder="City" value="${(person.workAddress.city)!}">
						<input type="text" class="form-control" name="workAddressZipCode" placeholder="Zip Code" style="width:120px" value="${(person.workAddress.zipCode?c)!}">
					</fieldset>
					<br><br>
					<fieldset class="form-group col-lg-9">
						<legend>Contacts</legend>
						<table id="contactTable">
							<#list (person.contacts)! as contact>
								<tr><input type="hidden" name="contactId" value="${contact.id}">
								<td><input name="contactType" value="${contact.getContactType().getName()}" class="field left" readonly></td>
								<td><input name="contactInfo" value="${contact.getInfo()}" class="field left" readonly></td>
								<td><a onClick="deleteContact(this)">Delete</a></td></tr>
							</#list>
						</table>
						<br>
							<label for="contactType">Contact Types: </label>
							<select class="form-control" id="contactType">
								<#list (contactTypes)! as contactType>
									<option value="${contactType.getName()}">${contactType.getName()}</option>
								</#list>
							</select>
							<input type="text" id="info" name="info" value="" placeholder="Contact Info" class="form-control">
							<button type="button" onclick="addContact();" class="btn btn-default"> Add Contact</button>
					</fieldset>
					<fieldset class="form-group col-lg-3">
						<legend>Roles</legend>
						<#list roles! as role>
							<#assign checked=false>
							<#list (person.roles)! as personRole>
								<#if role == personRole>
									<#assign checked=true>
								</#if>
							</#list>
							<input type="checkbox" onchange="changeRoleName(this)" value="${role.id}" <#if checked == true>name='roleChecked' checked<#else>name='role'</#if>>&nbsp;&nbsp;${role} <br>
						</#list>
					</fieldset>
					<div class="col-lg-12" align="center" style="padding-top:20px">
						<span class="required" align="left"><p>*Required</p></span>
						<#if RequestParameters.action! == "edit">
							<input type="hidden" name="id" value ="${person.id}"> 
							<input type="hidden" name="edit" value ="true"> 
						<#else>
							<input type="hidden" name="action" value ="add"> 
						</#if>
						<input type="submit" class="btn btn-primary btn-lg" value="Submit"/>
						<br><br>
						<a href="${ctx.contextPath}/main"> Back to Main Page</a>
					</div>
					
				</form>
			</div>
		</div>
				
	</body>
</html>