6. Write a short answer in README.md. In your README.md, write a short answer to the following questions:
<br>
<br>

- Did you use the same type of route to update patient information as to update an employee's department?
<br><br>
 No, I used PUT to update all patient information and PATCH to update the doctor's department. The reason is that PUT 
is used for full updates, while PATCH is more suitable for partial updates.
<br> 
<br>
- Why did you choose the selected strategy?
<br><br>
  I used POST to create new resources, PATCH for partial changes, and PUT to replace entire resources. This follows the principles of REST, 
where each HTTP method has a specific purpose.
<br>
<br>
- What are the advantages and disadvantages of the strategies you chose for creating these routes?
<br><br>
  Advantages:
  Clarity and semantics of the routes.
  Flexibility when using PATCH for partial changes.
  Using PUT to completely replace information, maintaining data consistency.
  <br><br>
  Disadvantages:
  PATCH can be less efficient if abused for minor and frequent updates.
  PUT requires submitting the entire entity, which can be inefficient if only some fields change
<br>
<br>
- What is the cost-benefit between using `PUT` and `PATCH`? <br><br>
  PUT Cost: It is more expensive in terms of bandwidth, since it requires sending all the data of 
  the resource, even if only one field is changed.<br><br>
  PATCH Cost: More efficient because you only send the fields to update, but it can increase the complexity 
  of the code if there are many validations.<br><br>
  PUT Benefit: Ensures that the resource is completely up to date, helping to avoid inconsistencies.
<br>  

