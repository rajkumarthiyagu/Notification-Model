<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="normalize.css">
<link rel="stylesheet" href="style.css">
</head>
<body>
<div align = "center">
<h1>Ticket Raise Form</h1></div>
	<section class="loginform cf">
		<form action="ne.html" method="get">
			
			Employee ID&nbsp;&nbsp;&nbsp;<input type="text" name="txt_empId" /><br>
			<br>
			Group ID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="txt_groupId" />
		<p>
				 Category &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name ="txt_deptName">
  <option value="HR">HR</option>
  <option value="IT">IT</option>
 <option value="HRGroup">HR-Group</option>
 <option value="ITGroup">IT-Group</option>


</select><br>
			</p>
			<p>
			
Sub Category <select name="txt_sc"><option value="accomodation">accomodation</option>

  <option value="adrenalin">adrenalin</option>
  <option value="certificate">certificate</option>
  <option value="compensation">compensation</option>
    <option value="correction in record">correction in record</option>
  <option value="insurance">insurance</option>
  <option value="leave">leave</option>
  <option value="library">library</option>
 
    <option value="perfomance management">perfomance management</option>
  <option value="requests for letter from HR">requests for letter from HR</option>
  
  
   <option value ="Antivirus - Issues">Antivirus - Issues</option>
    <option value ="Backup Request ">Backup Request </option>
      <option value ="Backup Restore Request">Backup Restore Request</option>
        <option value ="DL Addition/ Deletion / Update">DL Addition/ Deletion / Update</option>
          <option value ="Dual Network Ports">Dual Network Ports</option>
          <option value ="Email ID Creation / Deletion Request">Email ID Creation / Deletion Request</option>
            <option value ="Email Transfer">Email Transfer</option>
              <option value ="Enabling datacard port for laptops">Enabling datacard port for laptops</option>
  <option value="Recruitment">Recruitment</option>
  <option value="Network problem">Network problem</option>
</select>
			</p>
			<p>
			
				Subject&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="txt_task" /><br>
			</p>
			<p>
			 
			
				Query&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<textarea rows="2" cols="25" name="txt_comm"></textarea><br>
			</p>
			
			<p>
				<input type="submit" name="sub" value="Submit" />
			</p>
		</form>
	</section>

     
   
</body>
</html>