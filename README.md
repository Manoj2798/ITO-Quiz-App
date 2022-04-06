# ITO-Quiz-App
Build an ITO online Exam Portal which will be helpful for smooth the assessment process.
Note: In is Assessment you must generate REST APIs for the following operations.

 There will be following Module in the Application
o	Admin Module
	Questions Set CRUD Operation.
	Evaluate 

o	User Module
	Generate Exam ID
	Assessment.

Database Tables:
Question (question_id, question, option1, option2, option3, option4, answer)
Answer (id, candidate_id, question_id, answer)
Candidate (candidate_id, name, emailId, isStarted, isSubmit)

Admin:
	Admin can create, read, update, and delete questions.

Create Question:
•	Admin will take following input for create a question:
o	String question
o	List<String> options (4 Options)
o	Integer answer //index of correct answer based on options. User will input index b/w 1 to 4
•	User can insert one and more than one question in single POST call.
•	For Generate the Id use the sequence generator.
•	If question created successfully then return success message “Question <id> generated successfully”
•	If failed to generate than return failure message “Failed to Generate Question”
 
Read Questions List:
•	Admin can get all the list of question which are stored in database.
•	If Questions are available than return the JSON array list for all the question.
•	If no question available in database, then return Message “No Question available”.



Read By Id:
•	Admin can read the question by auto generate ID.
•	If data is available than return the JSON object of question
•	If ID is not available in database return message “Invalid Question Number”


Update Question:
•	Admin will update the question by ID
•	Admin will take following input for update a question:
o	String question
o	List<String> options (4 Options)
o	Integer answer (index of correct answer based on options. User will input index b/w 1 to 4)

•	If update successfully, then return message “Updated question number <id> successfully.”
•	If failed, then return message “Failed to Update question number <id>”
•	If ID is not available in database return message “Invalid Question Number”

Delete Question:
•	User can delete one and more then question from database.
•	User will take comma separated Question Id if want to delete more than one.
•	If all the question deleted the return message “Deleted selected message successfully”
•	If All are the invalid ids than return “Failed to delete selected message”
•	If few are valid ids return “<list of delete question id with comma separated> are deleted successfully and failed to delete “<list of failed question id with comma separated>.

Evaluate:
	Evaluation will be based on CandidateId
•		Check if CandidateId is present in candidate table.
o	If Yes, Process to Next
o	Else throw the exception and Send Message “Candidate Id doesn’t exist”.

Generate the Result 

•	Fetch all the Answer based on candidate Id
•	Compare all the question number and answer index with stored question in DB.
•	If more than 6 answer are correct, then return the message: 
o	<Candidate ID> : <Candidate Name> is selected for next Round.
o	Correct Answer: Count
o	Incorrect Answer: Count
•	Else return the message 
o	<Candidate ID>:  <Candidate Name> is rejected in this Round
o	Correct Answer: Count
o	Incorrect Answer: Count



Candidate:
Assessment:
Get the Candidate Id:
•	Candidate will take input as Name and Emailed and return the unique Candidate Id
a.	String: Name
b.	String: Email Id
•	IsStarted and IsSubmit Value will be false when exam id will generate.
•	Candidate Id will be auto generated sequence value.
•	If emailId available in database than return message “Email Id exist, please enter different email id.



Get the question Set:
Candidate will make a rest call using this Candidate Id (Request Param) and get the question set.
•	Check if Candidate Id is present in candidate table.
o	If Yes, Process to Next
o	Else throw the exception and Send Message “Candidate Id doesn’t exist”.

•	When receive the request then based on CandidateId set the isStarted value as true. And return random list of 10 questions.
•	In response return only question and options.
•	If isStarted value is already true for the received CandidateId then return the message “Exam Assessment Running.”
Submit Answer Sheet:
	Candidate can submit the answer sheet using the generated Candidate Id
	Send Candidate Id in (Request Param) and send the JSON body
	Which contain list of Object (Question Number and Answer Option Index)
	
	Check if Candidate Id is present in candidate table.
•	If Yes, Process to Next
•	Else throw the exception and Send Message “<Candidate Id> doesn’t exist”
	Check If isSubmit is true for that Candidate Id return the message “Answer already submitted” and don’t save any data
	Else Store all the answer in Answer table with CandidateId.
	Set IsSubmit as true.
	Generate the result and send the response to Candidate
•	Compare all the question number and answer index with stored question in DB.
•	If more than 6 answer are correct, then return the message: Selected for the next Round.
•	Else return the message “Sorry you are not selected. Better luck next time”.

